/****************************************************************/
/*								*/
/*								*/
/*			aemilia_compiler.c			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module generates the semantic models for .aem spec files. */
/* This module contains the following functions: */
/* - open_sem_model(): It opens the file where the semantic model or its size will be printed. */
/* - generate_aei_state_spaces(): It generates the integrated local state space for each AEI as well as */
/*				  the initial global state. */
/* - generate_global_state_space(): It generates the global state space and prints/determines the related */
/*				    semantic model or its size. */
/* - generate_global_transitions(): It generates the integrated global transitions of a global state. */
/* - check_vanishing(): It checks whether a global state is vanishing. */
/* - merge_global_transitions(): It merges together the global transitions of a global state that have */
/*				 the same action-related information and the same derivative global state, */
/*				 by aggregating their rates and rewards. All the global transitions of */
/*				 the global state are required to have the same rate index. */
/* - close_sem_model(): It closes the file where the semantic model or its size has been printed. */
/* and the following auxiliary functions: */
/* - generate_local_state_space(): It generates the integrated local state space of an AEI starting from */
/*				   its initial local state. */
/* - generate_local_transitions(): It generates the integrated transitions of a local state of an AEI. */
/* - compute_act_prefix_der_local_states(): It computes the derivative local states together with the */
/*					    related unfolding assignments for the local transitions */
/*					    stemming from an occurrence of the action prefix operator. */
/* - instantiate_var_list(): It instantiate a list of variables with all of their possible values. */
/* - merge_var_instances(): It merges the instances of several variables. */
/* - skip_local_state_comp(): It skips a substate in the lexeme of a local state. */
/* - select_passive_local_transitions(): It selects the passive local transitions of a local state that */
/*					 have the same action type or correlated types, the same */
/*					 structure, the same guard, and the highest priority. */
/* - remove_vanishing_global_states(): It removes the vanishing global states and their immediate */
/*				       transitions. */
/* - remove_immediate_selfloops(): It removes the immediate selfloops of a vanishing global state. */
/* - normalize_weights(): It normalizes the weights of the immediate transitions of a vanishing global */
/*			  state. */
/* - renumber_psm_global_states(): It assigns new serial numbers to the global states of the performance */
/*				   semantic model and counts its global transitions. */
/* - print_semantic_model(): It prints the semantic model on the .ism/.fms/.psm file. */
/* - print_global_state(): It prints a global state. */
/* - print_extended_local_state(): It prints the extended lexeme of a local state. */
/* - print_global_trans_list(): It prints the global transitions of a global state. */
/* - print_assign_list(): It prints a list of assignments. */
/* - mark_passive_or_local_transitions(): It marks the passive local transitions of the local states */
/*					  forming a global state that synchronize with the duplicates of */
/*					  the same or-interaction according to their priority levels, so */
/*					  that they are taken into account only when needed during the */
/*					  normalization of the rates and the yield rewards of the global */
/*					  transitions of the global state resulting from the same */
/*					  or-interaction. */
/* - compose_local_transitions(): It composes in parallel two groups of local transitions according to the */
/*				  EMPAgr parallel composition semantics. */
/* - merge_assign_lists(): It merges two assignment lists by letting the synchronization assignments */
/*			   precede the unfolding assignments. */
/* - insert_par_assign_list(): It inserts at the beginning of an assignment list a list of parameter */
/*			       assignments coming from the synchronization of two structured actions. */
/* - select_passive_global_transitions(): It selects the passive global transitions of a global state that */
/*					  have the same action type, structure, and guard and the highest */
/*					  priority. */
/* - unmark_passive_or_local_transitions(): It unmarks the passive local transitions of the local states */
/*					    forming a global state that synchronize with the duplicates of */
/*					    the same or-interaction. The unmarking is applied because the */
/*					    relationships among the priorities of correlated passive local */
/*					    transitions of different AEIs can vary from one global state */
/*					    to another. */
/* - normalize_or_global_transitions(): It normalizes the rates and the yield rewards of the global */
/*					transitions of a global state resulting from the synchronization */
/*					of the duplicates of the same or-interaction. */
/* - select_global_transitions(): It selects the global transitions of a global state that have the */
/*				  highest priority or are passive. */
/* - hide_rename_global_transitions(): It hides/renames the global transitions involving action types that */
/*				       must be hidden/renamed (e.g. because of behavioral variations). */
/* - check_global_state_eq(): It checks whether two local state vectors coincide up to a certain AEI. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/symbol_handler.h"

#include	"../headers/markov_solver.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/string_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

GST_BUCKET_CELL	*inv_vanishing_global_state_list[2];
			/* list of the invisible vanishing global states; */
			/* set by ; */
			/* used in */

long double     *init_prob_vector[2];
                        /* vector of the initial state probabilities (or previous state probabilities in */
                        /* the case of an iterative method); */
                        /* set by gen_sem_model() and renumber_psm_global_states() of empa-compiler.c and */
			/* by solve_assor() and solve_unif() of markov-solver.c; */
                        /* used in renumber_psm_global_states() of empa-compiler.c and in solve_assor() */
			/* and solve_unif() of markov-solver.c */

void		open_sem_model(char *),
		generate_aei_state_spaces(void),
		generate_global_state_space(void),
		generate_global_transitions(GST_BUCKET *);

BOOLEAN		check_vanishing(GST_BUCKET *);

void		merge_global_transitions(G_TRANS_CELL *),
		close_sem_model(void);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	BOOLEAN		exp_trans,
				/* flag specifying whether the interleaving semantic model has an */
				/* exponentially timed transition; */
				/* set by open_sem_model() and gen_sem_model(); */
				/* used in gen_sem_model() */
			imm_nonzero_bonus;
				/* flag specifying whether the interleaving semantic model has an */
				/* immediate transition with a nonzero bonus reward; */
				/* set by open_sem_model() and gen_sem_model(); */
				/* used in gen_sem_model() */

PRIVATE	FILE		*sem_model_file;
				/* .ism/.fsm/.psm/.siz file containing the semantic model or its size; */
				/* set by open_sem_model(); */
				/* used in open_sem_model(), handle_init_state(), gen_sem_model(), */
				/* close_sem_model(), print_assign_list(), and */
				/* renumber_psm_global_states() */

PRIVATE	GST_BUCKET_CELL	*vanishing_global_state_list;
				/* list of the vanishing states of the interleaving semantic mode; */
				/* set by open_sem_model() and gen_sem_model(); */
				/* used in gen_sem_model() and remove_vanishing_state() */

PRIVATE	long double	*aux_init_prob_vector;
				/* auxiliary vector of the initial state probabilities, used when there */
				/* are vanishing states to be eliminated; */
				/* set by gen_sem_model() and remove_vanishing_state(); */
				/* used in remove_vanishing_state() and renumber_psm_global_states() */


PRIVATE	void		generate_local_state_space(ST_BUCKET *,
						   LST_BUCKET *,
						   int *);

PRIVATE	L_TRANS_CELL	*generate_local_transitions(ST_BUCKET *,
						    LST_BUCKET *,
						    int *),
			*compute_act_prefix_der_local_states(ST_BUCKET *,
							     LST_BUCKET *,
							     ST_BUCKET_CELL *,
							     int *,
							     ST_BUCKET *,
							     ST_BUCKET *,
							     ST_BUCKET_CELL *,
							     ST_BUCKET *,
							     ST_BUCKET *,
							     REWARD_INFO);

PRIVATE	ST_BUCKET_CELL	*instantiate_var_list(ST_BUCKET_CELL *);

PRIVATE	void		merge_var_instances(ST_BUCKET_CELL **,
					    ST_BUCKET_CELL **,
					    ST_BUCKET *,
					    ST_BUCKET_CELL *,
					    int),
			skip_local_state_comp(char *,
					      int *),
			select_passive_local_transitions(L_TRANS_CELL **),
			remove_vanishing_global_states(void),
			remove_immediate_selfloops(GST_BUCKET *),
			normalize_weights(G_TRANS_CELL *),
			renumber_psm_global_states(void),
			print_semantic_model(int *),
			print_global_state(GST_BUCKET *),
			print_extended_local_state(char *,
						   int *),
			print_global_trans_list(G_TRANS_CELL *,
						int *),
			print_assign_list(ST_BUCKET_CELL *,
					  int,
					  int),
			mark_passive_or_local_transitions(GST_BUCKET *),
			compose_local_transitions(GST_BUCKET *,
						  ST_BUCKET *);

PRIVATE	ST_BUCKET_CELL	*merge_assign_lists(VP_INFO *,
					    VP_INFO *);

PRIVATE	void		insert_par_assign_list(VP_INFO *,
                                               ST_BUCKET_CELL *,
                                               ST_BUCKET_CELL *),
			select_passive_global_transitions(G_TRANS_CELL **),
			unmark_passive_or_local_transitions(GST_BUCKET *),
			normalize_or_global_transitions(GST_BUCKET *),
			select_global_transitions(GST_BUCKET *);

PRIVATE	BOOLEAN		hide_rename_global_transitions(G_TRANS_CELL *),
			check_global_state_eq(LST_BUCKET **,
					      LST_BUCKET **,
					      int);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		open_sem_model(char *aem_path)
{
	switch (option_index)
	{
	  case ISM_SIZE:
	  case FSM_SIZE:
	    sem_model_file = new_fopen(aem_path,
				       ".siz",
				       "w");
	    break;
	  case PSM_SIZE:
	    sem_model_file = new_fopen(aem_path,
				       ".siz",
				       "w");
	    exp_trans = FALSE;
	    vanishing_global_state_list = NULL;
	    break;
	  case ISM:
	    sem_model_file = new_fopen(aem_path,
				       ".ism",
				       "w");
	    break;
	  case FSM:
	    sem_model_file = new_fopen(aem_path,
				       ".fsm",
				       "w");
	    break;
	  case PSM:
	    sem_model_file = new_fopen(aem_path,
				       ".psm",
				       "w");
	    exp_trans = FALSE;
	    vanishing_global_state_list = NULL;
	    break;
	  case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	    exp_trans = FALSE;
	    inv_vanishing_global_state_list[0] = inv_vanishing_global_state_list[1] = NULL;
	    break;
	  case STAT_DISTR_GAUSS_ELIM:
	  case STAT_DISTR_ASSOR:
	  case TRANS_DISTR_UNIF:
	  case STAT_MEASURE_GAUSS_ELIM:
	  case STAT_MEASURE_ASSOR:
	  case TRANS_MEASURE_UNIF:
	    exp_trans = FALSE;
	    imm_nonzero_bonus = FALSE;
	    vanishing_global_state_list = NULL;
	    break;
	  default:
	    break;
	}
}


void		generate_aei_state_spaces(void)
{
		LST_BUCKET	**init_local_state_vector;
		ST_BUCKET	*aei;
		ST_BUCKET_CELL	*aei_cell;
	static  char            *prefixed_init_state		=       NULL;
                int             length,
				next_local_state_no,
				i;
        static  int             max_length			=       0;

	/* generate the local state space of each AEI */
	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	{
	  /* generate the initial local state of the AEI */
	  aei = aei_cell->st_bucket;
	  length =
	    compute_digit_num((double)(aei->info.aei->aei_no)) +
	    1 +
	    strlen(aei->info.aei->behavior_list->st_bucket->info.behavior->state_lexeme);
	  if (length > max_length)
          {
            if (prefixed_init_state != NULL)
              free(prefixed_init_state);
            prefixed_init_state = alloc_string(length);
            max_length = length;
          }
          sprintf(prefixed_init_state,
                  "%u#%s",
		  aei->info.aei->aei_no,
		  aei->info.aei->behavior_list->st_bucket->info.behavior->state_lexeme);
	  aei->info.aei->init_local_state = search_lst_table(prefixed_init_state,
							     aei->info.aei->init_state_var_assign_list);
	  /* generate the local state space of the AEI starting from its initial local state */
	  next_local_state_no = 1;
	  generate_local_state_space(aei,
			  	     aei->info.aei->init_local_state,
				     &next_local_state_no);
	  aei->info.aei->local_state_num = next_local_state_no - 1;
	}

	/* generate the initial global state */
	if ((option_index != CTL_MODEL_CHECK) &&
	    (option_index != LTL_MODEL_CHECK))
	{
	  init_local_state_vector = (LST_BUCKET **)new_calloc(archi_type[spec_no]->info.archi_type->aei_num,
							      sizeof(LST_BUCKET *));
	  for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list,
	       i = 0;
	       (aei_cell != NULL);
	       aei_cell = aei_cell->next_st_bucket_cell,
	       i++)
	    init_local_state_vector[i] = aei_cell->st_bucket->info.aei->init_local_state;
	  archi_type[spec_no]->info.archi_type->init_global_state =
	    search_gst_table(init_local_state_vector);
	}
}


void		generate_global_state_space(void)
{
	BOOLEAN		open_global_state,
			inv_vanishing_global_state;
	G_TRANS_CELL	*global_trans_cell;
	GST_BUCKET	*global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell,
			*reaching_global_state_cell;
	YB_CELL		*yb_cell;
	int		i,
			aux_state_num,
			next_global_state_no;

	/* initialize the stack of the global states to be considered with the initial global state, then */
	/* process one global state at a time until the stack is nonempty; recursion is not employed to */
	/* avoid run-time-support stack overflow in the case of a huge global state space */
	global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
						   NULL);
	archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered = TRUE;
	while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
	  global_state_cell = global_state_stack;
	  global_state_stack = global_state_stack->next_gst_bucket_cell;
	  global_state = global_state_cell->gst_bucket;
	  free(global_state_cell);
	  /* generate the global transitions for the global state */
	  generate_global_transitions(global_state);
	  /* compute additional information about the global state as well as the whole semantic model */
	  switch (option_index)
	  {
	    case ISM_SIZE:
	    case FSM_SIZE:
	    case ISM:
	    case FSM:
	    case STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK:
	    case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
	    case STRONG_MARKOVIAN_BISIM_EQUIV_CHECK:
	    case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	    case NON_INTERFERENCE_ANALYSIS:
	    case NON_DED_ON_COMP_ANALYSIS:
	      if (global_state->global_state_no == 0)
	        global_state->global_state_no = ++archi_type[spec_no]->info.archi_type->state_num;
	      break;
	    case PSM_SIZE:
	    case PSM:
	    case STAT_DISTR_GAUSS_ELIM:
	    case STAT_DISTR_ASSOR:
	    case TRANS_DISTR_UNIF:
	    case STAT_MEASURE_GAUSS_ELIM:
	    case STAT_MEASURE_ASSOR:
	    case TRANS_MEASURE_UNIF:
	      if (global_state->global_state_no == 0)
	        global_state->global_state_no = --archi_type[spec_no]->info.archi_type->state_num;
	      break;
	    default:
	      break;
	  }
	  if ((global_state->global_trans_list == NULL) &&
	      ((option_index == ISM_SIZE) ||
	       (option_index == FSM_SIZE) ||
	       (option_index == ISM) ||
	       (option_index == FSM)))
	    archi_type[spec_no]->info.archi_type->absorbing_state_num++;
	  /* compute additional information about the global state as well as the whole semantic model, */
	  /* and push into the stack the not-yet-considered derivative global states of the newly */
	  /* generated global transitions */
	  for (global_trans_cell = global_state->global_trans_list,
	       open_global_state = FALSE;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	  {
	    switch (option_index)
	    {
	      case ISM_SIZE:
	      case ISM:
	        if (global_trans_cell->der_global_state->global_state_no == 0)
	          global_trans_cell->der_global_state->global_state_no =
		    ++archi_type[spec_no]->info.archi_type->state_num;
	        archi_type[spec_no]->info.archi_type->trans_num++;
		if (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			    "invisible"))
		  archi_type[spec_no]->info.archi_type->invisible_trans_num++;
		if (global_trans_cell->action->info.action->rate_index == PASSIVE)
		{
		  archi_type[spec_no]->info.archi_type->passive_trans_num++;
		  open_global_state = TRUE;
		}
		else
		  if (global_trans_cell->action->info.action->rate_index == IMMEDIATE)
		    archi_type[spec_no]->info.archi_type->immediate_trans_num++;
		break;
	      case FSM_SIZE:
	      case FSM:
	        if (global_trans_cell->der_global_state->global_state_no == 0)
	          global_trans_cell->der_global_state->global_state_no =
		    ++archi_type[spec_no]->info.archi_type->state_num;
	        archi_type[spec_no]->info.archi_type->trans_num++;
		if (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			    "invisible"))
		  archi_type[spec_no]->info.archi_type->invisible_trans_num++;
		break;
	      case PSM_SIZE:
	      case PSM:
	      case STAT_DISTR_GAUSS_ELIM:
	      case STAT_DISTR_ASSOR:
	      case TRANS_DISTR_UNIF:
	      case STAT_MEASURE_GAUSS_ELIM:
	      case STAT_MEASURE_ASSOR:
	      case TRANS_MEASURE_UNIF:
	        if (global_trans_cell->der_global_state->global_state_no == 0)
	          global_trans_cell->der_global_state->global_state_no =
		    --archi_type[spec_no]->info.archi_type->state_num;
		if (global_trans_cell->action->info.action->rate_index == PASSIVE)
		  archi_type[spec_no]->info.archi_type->performance_closed = (char)FALSE;
		else
		  if (global_trans_cell->action->info.action->rate_index == EXP_TIMED)
		    exp_trans = TRUE;
		  else
		    for (yb_cell = global_trans_cell->reward_info.yb_list;
			 ((yb_cell != NULL) &&
			  !imm_nonzero_bonus);
			 yb_cell = yb_cell->next_yb_cell)
		      imm_nonzero_bonus = (yb_cell->bonus != 0.0L);
		if (global_trans_cell->der_global_state != global_state)
		{
		  for (reaching_global_state_cell =
			 global_trans_cell->der_global_state->reaching_global_state_list;
		       ((reaching_global_state_cell != NULL) &&
			(reaching_global_state_cell->gst_bucket != global_state));
		       reaching_global_state_cell = reaching_global_state_cell->next_gst_bucket_cell);
		  if (reaching_global_state_cell == NULL)
		    global_trans_cell->der_global_state->reaching_global_state_list =
		      alloc_gst_bucket_cell(global_state,
					    global_trans_cell->der_global_state->reaching_global_state_list);
		}
		break;
	      case STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK:
	      case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
	      case STRONG_MARKOVIAN_BISIM_EQUIV_CHECK:
	      case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	      case NON_INTERFERENCE_ANALYSIS:
	      case NON_DED_ON_COMP_ANALYSIS:
	        if (global_trans_cell->der_global_state->global_state_no == 0)
	          global_trans_cell->der_global_state->global_state_no =
		    ++archi_type[spec_no]->info.archi_type->state_num;
		if ((option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK) &&
		    (global_trans_cell->action->info.action->rate_index == EXP_TIMED))
		  exp_trans = TRUE;
		for (reaching_global_state_cell =
		       global_trans_cell->der_global_state->reaching_global_state_list;
		     ((reaching_global_state_cell != NULL) &&
		      (reaching_global_state_cell->gst_bucket != global_state));
		     reaching_global_state_cell = reaching_global_state_cell->next_gst_bucket_cell);
		if (reaching_global_state_cell == NULL)
		  global_trans_cell->der_global_state->reaching_global_state_list =
		    alloc_gst_bucket_cell(global_state,
					  global_trans_cell->der_global_state->reaching_global_state_list);
		break;
	      default:
		break;
	    }
	    if (!global_trans_cell->der_global_state->global_trans_considered)
	    {
	      global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
							 global_state_stack);
	      global_trans_cell->der_global_state->global_trans_considered = TRUE;
	    }
	  }
	  /* update general information about the whole semantic model */
	  switch (option_index)
	  {
	    case ISM_SIZE:
	    case ISM:
	      if (open_global_state)
	        archi_type[spec_no]->info.archi_type->open_state_num++;
	      else
	        if (check_vanishing(global_state))
		  archi_type[spec_no]->info.archi_type->vanishing_state_num++;
	      break;
	    case PSM_SIZE:
	    case PSM:
	    case STAT_DISTR_GAUSS_ELIM:
	    case STAT_DISTR_ASSOR:
	    case TRANS_DISTR_UNIF:
	    case STAT_MEASURE_GAUSS_ELIM:
	    case STAT_MEASURE_ASSOR:
	    case TRANS_MEASURE_UNIF:
	      if (check_vanishing(global_state))
	        vanishing_global_state_list = alloc_gst_bucket_cell(global_state,
								    vanishing_global_state_list);
	      break;
	    case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	      if (global_state->global_trans_list != NULL)
	      {
	        for (global_trans_cell = global_state->global_trans_list,
	             inv_vanishing_global_state = TRUE;
	             ((global_trans_cell != NULL) &&
	              inv_vanishing_global_state);
	             global_trans_cell = global_trans_cell->next_g_trans_cell)
	          inv_vanishing_global_state =
		    ((global_trans_cell->action->info.action->rate_index == IMMEDIATE) &&
		     !strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			     "invisible"));
	        if (inv_vanishing_global_state)
	          inv_vanishing_global_state_list[spec_no] =
		    alloc_gst_bucket_cell(global_state,
					  inv_vanishing_global_state_list[spec_no]);
	      }
	      break;
	    default:
	      break;
	  }
	}

	/* further processing needed by the semantic model */
	switch (option_index)
	{
	  case ISM:
	  case FSM:
	    next_global_state_no = 1;
	    print_semantic_model(&next_global_state_no);
	    break;
	  case PSM_SIZE:
	  case PSM:
	  case STAT_DISTR_GAUSS_ELIM:
	  case STAT_DISTR_ASSOR:
	  case TRANS_DISTR_UNIF:
	  case STAT_MEASURE_GAUSS_ELIM:
	  case STAT_MEASURE_ASSOR:
	  case TRANS_MEASURE_UNIF:
	    if (archi_type[spec_no]->info.archi_type->performance_closed)
	    {
	      /* determine the type of performance semantic model */
	      if (exp_trans)
	      {
	        if (imm_nonzero_bonus)
	          archi_type[spec_no]->info.archi_type->psm_index = HSMC;
	        else
	          archi_type[spec_no]->info.archi_type->psm_index = HCTMC;
	      }
	      else
	        archi_type[spec_no]->info.archi_type->psm_index = HDTMC;
	      /* remove the global vanishing states and compute the initial state probabilities */
	      if ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC) &&
	          (vanishing_global_state_list != NULL))
	      {
	        aux_init_prob_vector =
	          (long double *)new_calloc(-archi_type[spec_no]->info.archi_type->state_num + 1,
					    sizeof(long double));
	        aux_init_prob_vector[1] = 1.0L;
	        for (i = -archi_type[spec_no]->info.archi_type->state_num;
		     (i >= 2);
		     i--)
	          aux_init_prob_vector[i] = 0.0L;
	        aux_state_num = archi_type[spec_no]->info.archi_type->state_num;
	        remove_vanishing_global_states();
	        if (aux_state_num < archi_type[spec_no]->info.archi_type->state_num)
	          init_prob_vector[0] =
		    (long double *)new_calloc(-archi_type[spec_no]->info.archi_type->state_num + 1,
					      sizeof(long double));
	        else
	        {
	          init_prob_vector[0] = aux_init_prob_vector;
	          vanishing_global_state_list = NULL;
	        }
	      }
	      else
	      {
	        init_prob_vector[0] =
	          (long double *)new_calloc(-archi_type[spec_no]->info.archi_type->state_num + 1,
					    sizeof(long double));
	        init_prob_vector[0][1] = 1.0L;
	        for (i = -archi_type[spec_no]->info.archi_type->state_num;
		     (i >= 2);
		     i--)
	          init_prob_vector[0][i] = 0.0L;
	      }
	      /* print the performance semantic model or just renumber its states (and adjust the vector */
	      /* of the initial state probabilities if the vanishing states have been removed) to be ready */
	      /* for the Markovian analysis */
	      archi_type[spec_no]->info.archi_type->state_num = 0;
	      renumber_psm_global_states();
	      if ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC) &&
	          (vanishing_global_state_list != NULL))
	        free(aux_init_prob_vector);
	      if (option_index == PSM)
	      {
	        next_global_state_no = 1;
	        print_semantic_model(&next_global_state_no);
	      }
	    }
	    break;
	  case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	    if (exp_trans)
	    {
	      archi_type[spec_no]->info.archi_type->psm_index = HCTMC;
	      init_prob_vector[spec_no] =
	        (long double *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
					  sizeof(long double));
	      init_prob_vector[spec_no][1] = 1.0L;
	      for (i = archi_type[spec_no]->info.archi_type->state_num;
		   (i >= 2);
		   i--)
	        init_prob_vector[spec_no][i] = 0.0L;
	    }
	    else
	      archi_type[spec_no]->info.archi_type->psm_index = HDTMC;
	    break;
	  default:
	    break;
	}
}


void		generate_global_transitions(GST_BUCKET *global_state)
{
	BOOLEAN		passive_hidden_renamed;
	G_TRANS_CELL	*global_trans_cell;
	L_TRANS_CELL	*local_trans_cell;
	LST_BUCKET	**der_local_state_vector;
	ST_BUCKET_CELL	*aei_cell;
	int		i;

	if ((option_index != SIMULATION) ||
	    !global_state->global_trans_considered ||
	    ((archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
	     global_state->global_trans_need_norm))
	{
	  /* initialize the global transitions by transferring the information related to the local */
	  /* transitions of the local state of the first AEI */
	  if ((option_index == SIMULATION) &&
	      global_state->global_trans_considered)
	  {
	    free_g_trans_list(global_state->global_trans_list);
	    global_state->global_trans_list = NULL;
	  }
	  else
	    global_state->global_trans_considered = TRUE;
	  mark_passive_or_local_transitions(global_state);
	  for (local_trans_cell = global_state->local_state_vector[0]->local_trans_list,
	       global_trans_cell = NULL;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    if (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR)
	    {
	      der_local_state_vector =
	        (LST_BUCKET **)new_calloc(archi_type[spec_no]->info.archi_type->aei_num,
					  sizeof(LST_BUCKET *));
	      der_local_state_vector[0] = local_trans_cell->der_local_state;
	      for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
	           (i >= 1);
	           i--)
	        der_local_state_vector[i] = NULL;
	      if (global_trans_cell == NULL)
	        global_trans_cell = global_state->global_trans_list =
		  alloc_g_trans_cell(local_trans_cell->action,
				     (archi_type[spec_no]->info.archi_type->value_passing !=
				        (char)SYMBOLIC_VP)?
				       NULL:
				       alloc_vp_info(local_trans_cell->value_passing_info->guard,
						     duplicate_st_bucket_list(local_trans_cell->
										value_passing_info->
										assign_list),
						     local_trans_cell->value_passing_info->
						       input_output_assign_num),
				     duplicate_reward_info(local_trans_cell->reward_info),
				     (GST_BUCKET *)der_local_state_vector,
				     NULL);
	      else
	        global_trans_cell = global_trans_cell->next_g_trans_cell =
		  alloc_g_trans_cell(local_trans_cell->action,
				     (archi_type[spec_no]->info.archi_type->value_passing !=
				        (char)SYMBOLIC_VP)?
				       NULL:
				       alloc_vp_info(local_trans_cell->value_passing_info->guard,
				         	     duplicate_st_bucket_list(local_trans_cell->
							     			value_passing_info->
										assign_list),
				         	     local_trans_cell->value_passing_info->
						       input_output_assign_num),
				     duplicate_reward_info(local_trans_cell->reward_info),
				     (GST_BUCKET *)der_local_state_vector,
				     NULL);
	    }
	  /* compose in parallel the local transitions of the local states forming the global state by */
	  /* considering them from left to right (because of the elimination of low priority passive local */
	  /* transitions of the same type, the adoption of the dot notation for the action types, and the */
	  /* deferring of the application of hidings and renamings to the action types, unlike the SOS */
	  /* rules for the EMPAgr parallel composition it is not possible to generate two passive global */
	  /* transitions having the same type but different priorities, hence no selection is needed for */
	  /* the generated passive global transitions) */
	  for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list->next_st_bucket_cell;
	       (aei_cell != NULL);
	       aei_cell = aei_cell->next_st_bucket_cell)
	    compose_local_transitions(global_state,
				      aei_cell->st_bucket);
	  for (global_trans_cell = global_state->global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    global_trans_cell->der_global_state =
	      search_gst_table((LST_BUCKET **)(global_trans_cell->der_global_state));
	  /* normalize the rates and the yield rewards of the global transitions resulting from the same */
	  /* or-interaction */
	  normalize_or_global_transitions(global_state);
	  unmark_passive_or_local_transitions(global_state);
	  /* discard the lower priority non-passive global transitions */
	  select_global_transitions(global_state);
	  /* hide/rename the global transitions whenever needed */
	  passive_hidden_renamed = hide_rename_global_transitions(global_state->global_trans_list);
	  if (passive_hidden_renamed)
	    select_passive_global_transitions(&(global_state->global_trans_list));
	  /* merge the global transitions whenever needed */
	  if ((option_index != ISM_SIZE) &&
	      (option_index != ISM))
	    merge_global_transitions(global_state->global_trans_list);
	}
}


BOOLEAN		check_vanishing(GST_BUCKET *global_state)
{
	BOOLEAN		vanishing;
	G_TRANS_CELL	*global_trans_cell;

	if (global_state->global_trans_list == NULL)
	  vanishing = FALSE;
	else
	  for (global_trans_cell = global_state->global_trans_list,
	       vanishing = TRUE;
	       ((global_trans_cell != NULL) &&
	        vanishing);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    vanishing = (global_trans_cell->action->info.action->rate_index == IMMEDIATE);
	return(vanishing);
}


void		merge_global_transitions(G_TRANS_CELL *global_trans_list)
{
		G_TRANS_CELL	*global_trans_cell,
				*prev_global_trans_cell,
				*curr_global_trans_cell;
		YB_CELL		*yb_cell,
				*last_yb_cell,
				*curr_yb_cell;
	static	char		*aggregate_rate_lexeme		=	NULL;
		int		length;
	static	int		max_length			=	0;
		long double	aggregate_rate,
				new_aggregate_rate;

	for (global_trans_cell = global_trans_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	{
	  for (prev_global_trans_cell = global_trans_cell,
	       curr_global_trans_cell = global_trans_cell->next_g_trans_cell,
	       aggregate_rate = ((option_index == FSM_SIZE) ||
				 (option_index == FSM) ||
				 (option_index == CTL_MODEL_CHECK) ||
				 (option_index == LTL_MODEL_CHECK) ||
				 (option_index == STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK) ||
				 (option_index == WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK) ||
				 (option_index == NON_INTERFERENCE_ANALYSIS) ||
				 (option_index == NON_DED_ON_COMP_ANALYSIS))?
				  0.0L:
				  global_trans_cell->action->info.action->rate->info.expr->value;
	       (curr_global_trans_cell != NULL);
	       )
	    switch (option_index)
	    {
	      case FSM_SIZE:
	      case FSM:
	      case CTL_MODEL_CHECK:
	      case LTL_MODEL_CHECK:
	      case STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK:
	      case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
	      case NON_INTERFERENCE_ANALYSIS:
	      case NON_DED_ON_COMP_ANALYSIS:
		if ((curr_global_trans_cell->action->info.action->type ==
		       global_trans_cell->action->info.action->type) &&
		    ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		     check_lists_equality(curr_global_trans_cell->action->info.action->par_list,
				          global_trans_cell->action->info.action->par_list)) &&
		    ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		     ((curr_global_trans_cell->value_passing_info->guard ==
		         global_trans_cell->value_passing_info->guard) &&
		      check_lists_equality(curr_global_trans_cell->value_passing_info->assign_list,
				           global_trans_cell->value_passing_info->assign_list))) &&
		    (curr_global_trans_cell->der_global_state == global_trans_cell->der_global_state))
		{
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		  free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
		  prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
		}
		else
		{
		  prev_global_trans_cell = curr_global_trans_cell;
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		}
		break;
	      case PSM_SIZE:
	      case PSM:
	      case STAT_DISTR_GAUSS_ELIM:
	      case STAT_DISTR_ASSOR:
	      case TRANS_DISTR_UNIF:
	      case STAT_MEASURE_GAUSS_ELIM:
	      case STAT_MEASURE_ASSOR:
	      case TRANS_MEASURE_UNIF:
		if (curr_global_trans_cell->der_global_state == global_trans_cell->der_global_state)
		{
		  /* aggregate the yield and the bonus rewards (separately) */
		  for (yb_cell = last_yb_cell = global_trans_cell->reward_info.yb_list,
		       curr_yb_cell = curr_global_trans_cell->reward_info.yb_list,
		       new_aggregate_rate =
			 aggregate_rate +
			 curr_global_trans_cell->action->info.action->rate->info.expr->value;
		       ((yb_cell != NULL) &&
			(curr_yb_cell != NULL));
		       last_yb_cell = yb_cell,
		       yb_cell = yb_cell->next_yb_cell,
		       curr_yb_cell = curr_yb_cell->next_yb_cell)
		  {
		    yb_cell->yield += curr_yb_cell->yield;
		    yb_cell->bonus =
		      yb_cell->bonus *
			(aggregate_rate /
			 new_aggregate_rate) +
		      curr_yb_cell->bonus *
			(curr_global_trans_cell->action->info.action->rate->info.expr->value /
			 new_aggregate_rate);
		  }
		  if (yb_cell != NULL)
		    for (;
		         (yb_cell != NULL);
		         yb_cell = yb_cell->next_yb_cell)
		      yb_cell->bonus *= aggregate_rate /
					new_aggregate_rate;
		  else
		    for (;
		         (curr_yb_cell != NULL);
		         curr_yb_cell = curr_yb_cell->next_yb_cell)
		      if (last_yb_cell == NULL)
		        last_yb_cell = global_trans_cell->reward_info.yb_list =
			  alloc_yb_cell(curr_yb_cell->yield,
		            		curr_yb_cell->bonus *
			      		  (curr_global_trans_cell->action->info.action->rate->info.expr->
					     value / new_aggregate_rate),
					NULL);
		      else
		        last_yb_cell = last_yb_cell->next_yb_cell =
			  alloc_yb_cell(curr_yb_cell->yield,
		            		curr_yb_cell->bonus *
					  (curr_global_trans_cell->action->info.action->rate->info.expr->
					     value / new_aggregate_rate),
					NULL);
		  /* aggregate the rates/weights */
		  aggregate_rate += curr_global_trans_cell->action->info.action->rate->info.expr->value;
		  /* make the pointers advance */
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		  free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
		  prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
		}
		else
		{
		  prev_global_trans_cell = curr_global_trans_cell;
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		}
		break;
	      case STRONG_MARKOVIAN_BISIM_EQUIV_CHECK:
	      case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	      case SIMULATION:
		if ((curr_global_trans_cell->action->info.action->type ==
		       global_trans_cell->action->info.action->type) &&
		    ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		     check_lists_equality(curr_global_trans_cell->action->info.action->par_list,
				          global_trans_cell->action->info.action->par_list)) &&
		    ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		     ((curr_global_trans_cell->value_passing_info->guard ==
		         global_trans_cell->value_passing_info->guard) &&
		      check_lists_equality(curr_global_trans_cell->value_passing_info->assign_list,
				           global_trans_cell->value_passing_info->assign_list))) &&
		    (curr_global_trans_cell->der_global_state == global_trans_cell->der_global_state))
		{
		  /* aggregate the rates/weights */
		  aggregate_rate += curr_global_trans_cell->action->info.action->rate->info.expr->value;
		  /* make the pointers advance */
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		  free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
		  prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
		}
		else
		{
		  prev_global_trans_cell = curr_global_trans_cell;
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		}
		break;
	      default:
		break;
	    }
	  if (((option_index == PSM_SIZE) ||
	       (option_index == PSM) ||
	       (option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	       (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	       (option_index == STAT_DISTR_GAUSS_ELIM) ||
	       (option_index == STAT_DISTR_ASSOR) ||
	       (option_index == TRANS_DISTR_UNIF) ||
	       (option_index == STAT_MEASURE_GAUSS_ELIM) ||
	       (option_index == STAT_MEASURE_ASSOR) ||
	       (option_index == TRANS_MEASURE_UNIF) ||
	       (option_index == SIMULATION)) &&
	      (aggregate_rate > global_trans_cell->action->info.action->rate->info.expr->value))
	  {
	    length = compute_digit_num(aggregate_rate) +
		     F_FORMAT_PRECIS_LENGTH;
	    if (length > max_length)
	    {
  	      if (aggregate_rate_lexeme != NULL)
    	        free(aggregate_rate_lexeme);
  	      aggregate_rate_lexeme = alloc_string(length);
  	      max_length = length;
	    }
	    sprintf(aggregate_rate_lexeme,
		    "%f",
		    (double)aggregate_rate);
	    global_trans_cell->action =
	      set_action_bucket(global_trans_cell->action->info.action->type,
				global_trans_cell->action->info.action->action_index,
				global_trans_cell->action->info.action->par_list,
				global_trans_cell->action->info.action->rate_index,
				global_trans_cell->action->info.action->priority,
				set_number_bucket(aggregate_rate_lexeme));
	  }
	}
}


void		close_sem_model(void)
{
	switch (option_index)
	{
	  case ISM_SIZE:
	  case FSM_SIZE:
	  case PSM_SIZE:
	    if ((option_index != PSM_SIZE) ||
		(archi_type[spec_no]->info.archi_type->performance_closed))
	    {
	      fprintf(sem_model_file,
		      "Size of the %s underlying %s:\n\n- %d %s: ",
		      (option_index == ISM_SIZE)?
		        "integrated semantic model":
		        ((option_index == FSM_SIZE)?
			   "functional semantic model":
			   ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC)?
			      "homogeneous continuous-time Markov chain":
			      "homogeneous discrete-time Markov chain")),
		      archi_type[spec_no]->symbol_lexeme,
		      archi_type[spec_no]->info.archi_type->state_num,
		      (archi_type[spec_no]->info.archi_type->state_num == 1)?
			"state":
			"states");
	      switch (option_index)
	      {
		case ISM_SIZE:
	          fprintf(sem_model_file,
		          "\n  - %d tangible, \n  - %d vanishing, \n  - %d open, \n  - %d deadlocked;",
		          archi_type[spec_no]->info.archi_type->state_num -
		            archi_type[spec_no]->info.archi_type->vanishing_state_num -
		            archi_type[spec_no]->info.archi_type->open_state_num -
		            archi_type[spec_no]->info.archi_type->absorbing_state_num,
		          archi_type[spec_no]->info.archi_type->vanishing_state_num,
		          archi_type[spec_no]->info.archi_type->open_state_num,
		          archi_type[spec_no]->info.archi_type->absorbing_state_num);
		  break;
	        case FSM_SIZE:
	          fprintf(sem_model_file,
		          "\n  - %d nondeadlocked, \n  - %d deadlocked;",
		          archi_type[spec_no]->info.archi_type->state_num -
		            archi_type[spec_no]->info.archi_type->absorbing_state_num,
		          archi_type[spec_no]->info.archi_type->absorbing_state_num);
		  break;
	        case PSM_SIZE:
	          fprintf(sem_model_file,
		          "\n  - %d nonabsorbing, \n  - %d absorbing;",
		          archi_type[spec_no]->info.archi_type->state_num -
		            archi_type[spec_no]->info.archi_type->absorbing_state_num,
		          archi_type[spec_no]->info.archi_type->absorbing_state_num);
		  break;
		default:
		  break;
	      }
	      fprintf(sem_model_file,
		      "\n\n- %d %s",
		      archi_type[spec_no]->info.archi_type->trans_num,
		      (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			"transition":
			"transitions");
	      switch (option_index)
	      {
		case ISM_SIZE:
	          fprintf(sem_model_file,
		          ":\n  - %d observable,\n  - %d invisible;\n  ",
		          archi_type[spec_no]->info.archi_type->trans_num -
		            archi_type[spec_no]->info.archi_type->invisible_trans_num,
		          archi_type[spec_no]->info.archi_type->invisible_trans_num);
	          fprintf(sem_model_file,
		          "- %d exponentially timed,\n  - %d immediate,\n  - %d passive.",
		          archi_type[spec_no]->info.archi_type->trans_num -
		            archi_type[spec_no]->info.archi_type->immediate_trans_num -
		            archi_type[spec_no]->info.archi_type->passive_trans_num,
		          archi_type[spec_no]->info.archi_type->immediate_trans_num,
		          archi_type[spec_no]->info.archi_type->passive_trans_num);
		  break;
	        case FSM_SIZE:
	          fprintf(sem_model_file,
		          ":\n  - %d observable,\n  - %d invisible.",
		          archi_type[spec_no]->info.archi_type->trans_num -
		            archi_type[spec_no]->info.archi_type->invisible_trans_num,
		          archi_type[spec_no]->info.archi_type->invisible_trans_num);
		  break;
	        case PSM_SIZE:
	          fprintf(sem_model_file,
		          ".");
		  break;
		default:
		  break;
	      }
	    }
	    fclose(sem_model_file);
	    break;
	  case ISM:
	    fprintf(sem_model_file,
		    "\n\n\n>>>> %d %s (%d tangible, %d vanishing, %d open, %d deadlocked)\n",
		    archi_type[spec_no]->info.archi_type->state_num,
		    (archi_type[spec_no]->info.archi_type->state_num == 1)?
		      "state":
		      "states",
		    archi_type[spec_no]->info.archi_type->state_num -
		      archi_type[spec_no]->info.archi_type->vanishing_state_num -
		      archi_type[spec_no]->info.archi_type->open_state_num -
		      archi_type[spec_no]->info.archi_type->absorbing_state_num,
		    archi_type[spec_no]->info.archi_type->vanishing_state_num,
		    archi_type[spec_no]->info.archi_type->open_state_num,
		    archi_type[spec_no]->info.archi_type->absorbing_state_num);
	    fprintf(sem_model_file,
		    ">>>> %d %s (%d observable, %d invisible; %d exp. timed, %d immediate, %d passive)",
		    archi_type[spec_no]->info.archi_type->trans_num,
		    (archi_type[spec_no]->info.archi_type->trans_num == 1)?
		      "transition":
		      "transitions",
		    archi_type[spec_no]->info.archi_type->trans_num -
		      archi_type[spec_no]->info.archi_type->invisible_trans_num,
		    archi_type[spec_no]->info.archi_type->invisible_trans_num,
		    archi_type[spec_no]->info.archi_type->trans_num -
		      archi_type[spec_no]->info.archi_type->immediate_trans_num -
		      archi_type[spec_no]->info.archi_type->passive_trans_num,
		    archi_type[spec_no]->info.archi_type->immediate_trans_num,
		    archi_type[spec_no]->info.archi_type->passive_trans_num);
	    fclose(sem_model_file);
	    break;
	  case FSM:
	    fprintf(sem_model_file,
		    "\n\n\n>>>> %d %s (%d nondeadlocked, %d deadlocked)\n",
		    archi_type[spec_no]->info.archi_type->state_num,
		    (archi_type[spec_no]->info.archi_type->state_num == 1)?
		      "state":
		      "states",
		    archi_type[spec_no]->info.archi_type->state_num -
		      archi_type[spec_no]->info.archi_type->absorbing_state_num,
		    archi_type[spec_no]->info.archi_type->absorbing_state_num);
	    fprintf(sem_model_file,
		    ">>>> %d %s (%d observable, %d invisible)",
		    archi_type[spec_no]->info.archi_type->trans_num,
		    (archi_type[spec_no]->info.archi_type->trans_num == 1)?
		      "transition":
		      "transitions",
		    archi_type[spec_no]->info.archi_type->trans_num -
		      archi_type[spec_no]->info.archi_type->invisible_trans_num,
		    archi_type[spec_no]->info.archi_type->invisible_trans_num);
	    fclose(sem_model_file);
	    break;
	  case PSM:
	    if (archi_type[spec_no]->info.archi_type->performance_closed)
	      fprintf(sem_model_file,
		      "\n\n\n>>>> %d %s (%d nonabsorbing, %d absorbing)\n>>>> %d %s",
		      archi_type[spec_no]->info.archi_type->state_num,
		      (archi_type[spec_no]->info.archi_type->state_num == 1)?
			"state":
			"states",
		      archi_type[spec_no]->info.archi_type->state_num -
			archi_type[spec_no]->info.archi_type->absorbing_state_num,
		      archi_type[spec_no]->info.archi_type->absorbing_state_num,
		      archi_type[spec_no]->info.archi_type->trans_num,
		      (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			"transition":
			"transitions");
	    fclose(sem_model_file);
	    break;
	  default:
	    break;
	}
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		generate_local_state_space(ST_BUCKET  *aei,
					   LST_BUCKET *local_state,
					   int        *next_local_state_no)
{
	L_TRANS_CELL	*local_trans_cell;
	int		offset;

	if (!local_state->local_trans_considered)
	{
	  /* generate the integrated transitions of the local state */
	  local_state->local_trans_considered = TRUE;
	  local_state->local_state_no = (*next_local_state_no)++;
	  if (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)
	    eval_assign_list(local_state->state_var_assign_list,
			     0);
	  for (offset = 0;
	       (local_state->local_state_lexeme[offset] != '#');
	       offset++);
	  offset++;
	  local_state->local_trans_list = generate_local_transitions(aei,
								     local_state,
								     &offset);
	  select_passive_local_transitions(&(local_state->local_trans_list));
	  /* generate the integrated local state space starting from each of the derivative local states */
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    generate_local_state_space(aei,
			    	       local_trans_cell->der_local_state,
				       next_local_state_no);
	}
}


L_TRANS_CELL	*generate_local_transitions(ST_BUCKET  *aei,
					    LST_BUCKET *local_state,
					    int        *offset)
{
	L_TRANS_CELL	*local_trans_list,
			*aux_local_trans_list,
			*local_trans_list1,
			*local_trans_list2,
			*local_trans_cell;
	REWARD_INFO	reward_info;
	ST_BUCKET	*aet_action,
			*aei_act_type,
			*aei_act_priority,
			*aei_act_rate,
			*concrete_assign,
			*aet_guard,
			*aei_guard;
	ST_BUCKET_CELL	*aei_act_par_list,
			*symbolic_aei_act_par_cell,
			*concrete_aei_act_par_list,
			*concrete_aei_act_par_cell,
			*first_concrete_aei_act_par_cell,
			*last_concrete_aei_act_par_cell,
			*state_var_assign_list,
			*state_var_assign_cell,
			*last_state_var_assign_cell;
	int		aux_offset;

	/* offset is used as a displacement from the beginning of the string storing the concise prefix */
	/* lexeme of the local state whose transitions are being computed; upon entering a case, it */
	/* indicates the first character of the lexeme of the substate whose transitions are to be */
	/* computed; upon returning from a case, it indicates the last character of the lexeme of the */
	/* substate whose transitions have been computed */
	switch (local_state->local_state_lexeme[*offset])
	{
	  case '_':
	    local_trans_list = NULL;
	    break;
	  case '.':
	    /* decode the action */
	    (*offset)++;
	    aet_action = decode_local_state_comp(local_state->local_state_lexeme,
						 offset);
	    /* determine the type of the action */
	    aei_act_type =
	      (ST_BUCKET *)search_lexeme_table(strrchr(aet_action->info.action->type->symbol_lexeme,
						       '.') + 1,
					       SYT);
	    build_prefixed_id(aei,
			      &aei_act_type);
	    if ((aei_act_type->info.act_type->variation == RESTRICTED) ||
		((option_index == NON_INTERFERENCE_ANALYSIS) &&
		 (spec_no == 1) &&
	         (aei_act_type->info.act_type->variation == MADE_HIGH)))
	    {
	      /* the action is prevented from occurring */
	      local_trans_list = NULL;
	      *offset += 2;
	      skip_local_state_comp(local_state->local_state_lexeme,
				    offset);
	    }
	    else
	    {
	      /* determine the parameters associated with the action type */
	      aei_act_par_list = rewrite_expr_bucket_list(aet_action->info.action->par_list,
			    			          aei);
	      /* determine the rewards associated with the action type */
	      reward_info = aei_act_type->info.act_type->reward_info;
	      /* rename the action type if it is an uni- or an and-interaction in order to prepare it for */
	      /* the synchronization with its partners from other AEIs (the renaming of the action type in */
	      /* the case it is an or-interaction is deferred until the treatment of its duplicates) */
	      if (((aei_act_type->info.act_type->interaction_index == INPUT_UNI) ||
	           (aei_act_type->info.act_type->interaction_index == OUTPUT_UNI) ||
	           (aei_act_type->info.act_type->interaction_index == INPUT_AND) ||
	           (aei_act_type->info.act_type->interaction_index == OUTPUT_AND)) &&
	          (aei_act_type->info.act_type->renamed != NULL))
	        aei_act_type = aei_act_type->info.act_type->renamed;
	      /* determine the priority level of the action */
	      if (aet_action->info.action->priority->symbol_index == NUMBER)
	        aei_act_priority = aet_action->info.action->priority;
	      else
	      {
	        aei_act_priority = rewrite_expr_bucket(aet_action->info.action->priority,
						       aei);
	        eval_expr(aei_act_priority,
			  0);
	        if (aei_act_priority->info.expr->value > 0.0L)
		  aei_act_priority = set_concrete_expr_bucket(aei_act_priority);
	        else
		  signal_crash(PRIORITY_LE_ZERO_CRASH,
			       aei_act_priority->symbol_lexeme);
	      }
	      /* determine the rate/weight of the action */
	      if (aet_action->info.action->rate->symbol_index == NUMBER)
	        aei_act_rate = aet_action->info.action->rate;
	      else
	      {
	        aei_act_rate = rewrite_expr_bucket(aet_action->info.action->rate,
						   aei);
	        if ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		    (option_index == SIMULATION) ||
		    check_expr_undecl_const_par_free(aei_act_rate,
						     NULL,
						     NULL))
	        {
	          eval_expr(aei_act_rate,
			    0);
	          if (aei_act_rate->info.expr->value > 0.0L)
		    aei_act_rate = set_concrete_expr_bucket(aei_act_rate);
		  else
		    signal_crash((aet_action->info.action->rate_index == (char)EXP_TIMED)?
				   EXP_RATE_LE_ZERO_CRASH:
				   WEIGHT_LE_ZERO_CRASH,
			         aei_act_rate->symbol_lexeme);
	        }
	        else
		  signal_crash((aet_action->info.action->rate_index == (char)EXP_TIMED)?
			         EXP_RATE_UNEVAL_CRASH:
			         WEIGHT_UNEVAL_CRASH,
			       aei_act_rate->symbol_lexeme);
	      }
	      /* determine the derivative local state together with the related unfolding assignments */
	      if (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)
		switch ((ACTION_INDEX)(aet_action->info.action->action_index))
		{
		  case UNSTRUCTURED:
		    local_trans_list =
		      compute_act_prefix_der_local_states(aei,
							  local_state,
							  local_state->state_var_assign_list,
							  offset,
							  aet_action,
							  aei_act_type,
							  aei_act_par_list,
							  aei_act_priority,
							  aei_act_rate,
							  reward_info);
		    break;
		  case INPUT:
		    concrete_aei_act_par_list = instantiate_var_list(aei_act_par_list);
		    for (concrete_aei_act_par_cell = concrete_aei_act_par_list,
			 local_trans_list = aux_local_trans_list = NULL;
			 (concrete_aei_act_par_cell != NULL);
			 )
		    {
		      /* duplicate the concrete assignments to the variables of the local state in order */
		      /* to initialize the list of concrete assignments to the variables of the derivative */
		      /* local state for the current instantiation of the variables occurring in the input */
		      /* action */
		      for (state_var_assign_cell = local_state->state_var_assign_list,
			   state_var_assign_list = last_state_var_assign_cell = NULL;
			   (state_var_assign_cell != NULL);
			   state_var_assign_cell = state_var_assign_cell->next_st_bucket_cell)
			if (state_var_assign_list == NULL)
			  last_state_var_assign_cell = state_var_assign_list =
			    alloc_st_bucket_cell(state_var_assign_cell->st_bucket,
						 NULL);
			else
			  last_state_var_assign_cell = last_state_var_assign_cell->next_st_bucket_cell =
			    alloc_st_bucket_cell(state_var_assign_cell->st_bucket,
						 NULL);
		      /* append the set of concrete assignments based on the current instantiation of the */
		      /* variables occurring in the input action to the evaluated concrete assignments to */
		      /* the variables of the derivative local state, and evaluate each such additional */
		      /* concrete assignment */
		      for (symbolic_aei_act_par_cell = aei_act_par_list,
			   first_concrete_aei_act_par_cell = last_concrete_aei_act_par_cell =
			     concrete_aei_act_par_cell;
			   (symbolic_aei_act_par_cell != NULL);
			   symbolic_aei_act_par_cell = symbolic_aei_act_par_cell->next_st_bucket_cell,
			   last_concrete_aei_act_par_cell = concrete_aei_act_par_cell,
			   concrete_aei_act_par_cell = concrete_aei_act_par_cell->next_st_bucket_cell)
		      {
			concrete_assign = set_expr_bucket(ASSIGN_OP,
							  symbolic_aei_act_par_cell->st_bucket,
							  concrete_aei_act_par_cell->st_bucket,
							  NULL,
                                                          0.0L,
                                                          NULL,
                                                          FALSE),
		        eval_expr(concrete_assign,
				  0);
			assign_expr_eval(concrete_assign->info.expr->opn1->info.expr,
                                         concrete_assign->info.expr,
                                         ASSIGN_TO_LEFT,
                                         concrete_assign->symbol_lexeme);
			if (state_var_assign_list == NULL)
			  last_state_var_assign_cell = state_var_assign_list =
			    alloc_st_bucket_cell(concrete_assign,
						 NULL);
			else
			  last_state_var_assign_cell = last_state_var_assign_cell->next_st_bucket_cell =
			    alloc_st_bucket_cell(concrete_assign,
						 NULL);
		      }
		      /* add the local transitions for the current instantiation of the variables */
		      /* occurring in the input action to the local transitions for the previous */
		      /* instantiations */
		      last_concrete_aei_act_par_cell->next_st_bucket_cell = NULL;
		      aux_offset = *offset;
		      aux_local_trans_list =
		        compute_act_prefix_der_local_states(aei,
							    local_state,
							    state_var_assign_list,
							    &aux_offset,
							    aet_action,
							    aei_act_type,
							    first_concrete_aei_act_par_cell,
							    aei_act_priority,
							    aei_act_rate,
							    reward_info);
		      if (local_trans_list == NULL)
			local_trans_list = aux_local_trans_list;
		      else
		      {
		        for (local_trans_cell = local_trans_list;
			     (local_trans_cell->next_l_trans_cell != NULL);
			     local_trans_cell = local_trans_cell->next_l_trans_cell);
		        local_trans_cell->next_l_trans_cell = aux_local_trans_list;
		      }
		    }
		    *offset = aux_offset;
		    break;
		  case OUTPUT:
		    for (symbolic_aei_act_par_cell = aei_act_par_list,
			 concrete_aei_act_par_list = last_concrete_aei_act_par_cell = NULL;
			 (symbolic_aei_act_par_cell != NULL);
			 symbolic_aei_act_par_cell = symbolic_aei_act_par_cell->next_st_bucket_cell)
		    {
		      eval_expr(symbolic_aei_act_par_cell->st_bucket,
				0);
		      if (concrete_aei_act_par_list == NULL)
			last_concrete_aei_act_par_cell = concrete_aei_act_par_list =
			  alloc_st_bucket_cell(set_concrete_expr_bucket(symbolic_aei_act_par_cell->
									  st_bucket),
					       NULL);
		      else
			last_concrete_aei_act_par_cell =
			  last_concrete_aei_act_par_cell->next_st_bucket_cell =
			    alloc_st_bucket_cell(set_concrete_expr_bucket(symbolic_aei_act_par_cell->
									    st_bucket),
					         NULL);
		    }
		    local_trans_list =
		      compute_act_prefix_der_local_states(aei,
							  local_state,
							  local_state->state_var_assign_list,
							  offset,
							  aet_action,
							  aei_act_type,
							  concrete_aei_act_par_list,
							  aei_act_priority,
							  aei_act_rate,
							  reward_info);
		    break;
	          default:
	            local_trans_list = NULL;
	            break;
		}
	      else
		local_trans_list = compute_act_prefix_der_local_states(aei,
								       local_state,
								       NULL,
								       offset,
								       aet_action,
								       aei_act_type,
								       aei_act_par_list,
								       aei_act_priority,
								       aei_act_rate,
								       reward_info);
	    }
	    break;
	  case ':':
	    /* decode the guard */
	    (*offset)++;
	    aet_guard = decode_local_state_comp(local_state->local_state_lexeme,
						offset);
	    aei_guard = rewrite_expr_bucket(aet_guard,
			    	            aei);
	    if ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		check_expr_undecl_const_par_free(aei_guard,
						 NULL,
						 NULL))
	    {
	      /* the guard can be evaluated */
	      eval_expr(aei_guard,
			0);
	      if (aei_guard->info.expr->value)
	      {
	        *offset += 2;
	        local_trans_list = generate_local_transitions(aei,
							      local_state,
							      offset);
	      }
	      else
	      {
	        local_trans_list = NULL;
	        *offset += 2;
	        skip_local_state_comp(local_state->local_state_lexeme,
				      offset);
	      }
	    }
	    else
	    {
	      /* the guard cannot be evaluated */
	      archi_type[spec_no]->info.archi_type->performance_closed = (char)FALSE;
	      *offset += 2;
	      local_trans_list = generate_local_transitions(aei,
							    local_state,
							    offset);
	      for (local_trans_cell = local_trans_list;
		   (local_trans_cell != NULL);
		   local_trans_cell = local_trans_cell->next_l_trans_cell)
	        local_trans_cell->value_passing_info->guard =
		  set_expr_bucket(AND_OP,
				  aei_guard,
				  local_trans_cell->value_passing_info->guard,
				  NULL,
				  0.0L,
				  NULL,
				  FALSE);
	    }
	    break;
	  case '+':
	    (*offset)++;
	    local_trans_list1 = generate_local_transitions(aei,
							   local_state,
							   offset);
	    *offset += 2;
	    local_trans_list2 = generate_local_transitions(aei,
							   local_state,
							   offset);
	    if (local_trans_list1 == NULL)
	      local_trans_list = local_trans_list2;
	    else
	      if (local_trans_list2 == NULL)
		local_trans_list = local_trans_list1;
	      else
	      {
		local_trans_list = local_trans_list1;
	        for (local_trans_cell = local_trans_list1;
		     (local_trans_cell->next_l_trans_cell != NULL);
		     local_trans_cell = local_trans_cell->next_l_trans_cell);
	        local_trans_cell->next_l_trans_cell = local_trans_list2;
	      }
	    break;
	  default:
	    local_trans_list = NULL;
	    break;
	}
	return(local_trans_list);
}


L_TRANS_CELL	*compute_act_prefix_der_local_states(ST_BUCKET      *aei,
						     LST_BUCKET     *local_state,
						     ST_BUCKET_CELL *state_var_assign_list,
						     int            *offset,
						     ST_BUCKET      *aet_action,
						     ST_BUCKET      *aei_act_type,
						     ST_BUCKET_CELL *aei_act_par_list,
						     ST_BUCKET      *aei_act_priority,
						     ST_BUCKET      *aei_act_rate,
						     REWARD_INFO    reward_info)
{
		LST_BUCKET	*der_local_state;
		L_TRANS_CELL	*local_trans_list,
				*last_local_trans_cell;
		ST_BUCKET	*behavior_invoc,
				*rewritten_actual_par;
		ST_BUCKET_CELL	*assign_list,
				*last_assign_cell,
				*dupl_aei_act_type_cell,
				*formal_par_cell,
				*actual_par_cell;
		VP_INFO		*value_passing_info;
	static	char		*lexeme				=	NULL,
				*der_local_state_lexeme		=	NULL;
		int		length,
				init_offset;
	static	int		max_length			=	0,
				max_der_local_state_length	=	0;

	/* determine the derivative local state */
	*offset += 2;
	init_offset = *offset;
	skip_local_state_comp(local_state->local_state_lexeme,
			      offset);
	length = *offset - init_offset + 1;
	if (length > max_length)
	{
  	  if (lexeme != NULL)
    	    free(lexeme);
  	  lexeme = alloc_string(length);
  	  max_length = length;
	}
	strncpy(lexeme,
		local_state->local_state_lexeme + init_offset,
		length);
	lexeme[length] = EOS;
	if ((lexeme[0] == '_') ||
	    (lexeme[0] == '.') ||
	    (lexeme[0] == ':') ||
	    (lexeme[0] == '+'))
	{
	  /* the derivative local state is not a behavior invocation */
	  length += compute_digit_num((double)(aei->info.aei->aei_no)) +
		    1;
	  if (length > max_der_local_state_length)
	  {
  	    if (der_local_state_lexeme != NULL)
    	      free(der_local_state_lexeme);
  	    der_local_state_lexeme = alloc_string(length);
  	    max_der_local_state_length = length;
	  }
	  sprintf(der_local_state_lexeme,
	          "%u#%s",
	          aei->info.aei->aei_no,
	          lexeme);
	  der_local_state = search_lst_table(der_local_state_lexeme,
					     state_var_assign_list);
	  assign_list = NULL;
	}
	else
	{
	  /* the derivative local state is a behavior invocation */
	  behavior_invoc = decode_local_state_comp(local_state->local_state_lexeme,
						   &init_offset);
	  length = compute_digit_num((double)(aei->info.aei->aei_no)) +
		   1 +
		   ((behavior_invoc->symbol_index == BEHAV_ID)?
		      strlen(behavior_invoc->info.behavior->state_lexeme):
		      strlen(behavior_invoc->info.par_behav_invoc->behavior->info.behavior->state_lexeme));
	  if (length > max_der_local_state_length)
	  {
  	    if (der_local_state_lexeme != NULL)
    	      free(der_local_state_lexeme);
  	    der_local_state_lexeme = alloc_string(length);
  	    max_der_local_state_length = length;
	  }
	  sprintf(der_local_state_lexeme,
		  "%u#%s",
		  aei->info.aei->aei_no,
		    ((behavior_invoc->symbol_index == BEHAV_ID)?
		       behavior_invoc->info.behavior->state_lexeme:
		       behavior_invoc->info.par_behav_invoc->behavior->info.behavior->state_lexeme));
	  if (behavior_invoc->symbol_index == BEHAV_ID)
	  {
	    /* the behavior invocation has no parameters */
	    der_local_state = search_lst_table(der_local_state_lexeme,
					       NULL);
	    assign_list = NULL;
	  }
	  else
	  {
	    /* the behavior invocation is parameterized */
	    for (formal_par_cell = behavior_invoc->info.par_behav_invoc->behavior->info.behavior->
				     formal_var_par_list,
		 actual_par_cell = behavior_invoc->info.par_behav_invoc->actual_par_list,
		 assign_list = last_assign_cell = NULL;
		 (formal_par_cell != NULL);
		 formal_par_cell = formal_par_cell->next_st_bucket_cell,
		 actual_par_cell = actual_par_cell->next_st_bucket_cell)
	    {
	      rewritten_actual_par = rewrite_expr_bucket(actual_par_cell->st_bucket,
						         aei);
	      if (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)
	      {
		eval_expr(rewritten_actual_par,
			  0);
		rewritten_actual_par = set_concrete_expr_bucket(rewritten_actual_par);
	      }
	      if (assign_list == NULL)
		last_assign_cell = assign_list =
		  alloc_st_bucket_cell(set_expr_bucket(ASSIGN_OP,
						       rewrite_expr_bucket(formal_par_cell->st_bucket,
								           aei),
						       rewritten_actual_par,
						       NULL,
						       0.0L,
						       NULL,
						       FALSE),
				       NULL);
	      else
		last_assign_cell = last_assign_cell->next_st_bucket_cell =
		  alloc_st_bucket_cell(set_expr_bucket(ASSIGN_OP,
						       rewrite_expr_bucket(formal_par_cell->st_bucket,
								           aei),
						       rewritten_actual_par,
						       NULL,
						       0.0L,
						       NULL,
						       FALSE),
				       NULL);
	    }
	    der_local_state =
	      search_lst_table(der_local_state_lexeme,
			       (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)?
			         assign_list:
				 NULL);
	  }
	}
	/* build the possible value passing related information */
	if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	  value_passing_info = alloc_vp_info((ST_BUCKET *)search_lexeme_table("true",
									      SYT),
					     assign_list,
					     0);
	else
	  value_passing_info = NULL;
	/* generate the local transition (multiple in the case of an or-interaction) */
	if (((aet_action->info.action->type->info.act_type->interaction_index != INPUT_OR) &&
	     (aet_action->info.action->type->info.act_type->interaction_index != OUTPUT_OR)) ||
	    (aei_act_type->info.act_type->duplicate_list == NULL))
	  local_trans_list = alloc_l_trans_cell(set_action_bucket(aei_act_type,
				      		                  aet_action->info.action->action_index,
						                  aei_act_par_list,
				      		                  aet_action->info.action->rate_index,
				      		                  aei_act_priority,
						                  aei_act_rate),
			      	                value_passing_info,
				                duplicate_reward_info(reward_info),
				                der_local_state,
				                NO_OR,
				                NULL);
	else
	  for (dupl_aei_act_type_cell = aei_act_type->info.act_type->duplicate_list,
	       last_local_trans_cell = local_trans_list = NULL;
	       (dupl_aei_act_type_cell != NULL);
	       dupl_aei_act_type_cell = dupl_aei_act_type_cell->next_st_bucket_cell)
	  {
	    aei_act_type = (dupl_aei_act_type_cell->st_bucket->info.act_type->renamed == NULL)?
			     dupl_aei_act_type_cell->st_bucket:
			     dupl_aei_act_type_cell->st_bucket->info.act_type->renamed;
	    if (last_local_trans_cell == NULL)
	      last_local_trans_cell = local_trans_list =
		alloc_l_trans_cell(set_action_bucket(aei_act_type,
				    		     aet_action->info.action->action_index,
						     aei_act_par_list,
						     aet_action->info.action->rate_index,
				      		     aei_act_priority,
						     aei_act_rate),
			      	   value_passing_info,
				   duplicate_reward_info(reward_info),
				   der_local_state,
				   DUPLICATE_OR,
				   NULL);
	    else
	      last_local_trans_cell = last_local_trans_cell->next_l_trans_cell =
		alloc_l_trans_cell(set_action_bucket(aei_act_type,
				      		     aet_action->info.action->action_index,
						     aei_act_par_list,
						     aet_action->info.action->rate_index,
				      		     aei_act_priority,
						     aei_act_rate),
			      	   value_passing_info,
				   duplicate_reward_info(reward_info),
				   der_local_state,
				   DUPLICATE_OR,
				   NULL);
	  }
	return(local_trans_list);
}


ST_BUCKET_CELL	*instantiate_var_list(ST_BUCKET_CELL *var_list)
{
	ST_BUCKET	*variable;
	ST_BUCKET_CELL	*concrete_expr_list,
			*last_concrete_expr_cell,
			*aux_concrete_expr_list,
			*var_cell,
			*comp_var_list,
			*last_comp_var_cell,
			*concrete_comp_expr_list,
			*concrete_comp_expr_cell;
	VALUE_CELL      **array_elem,
                        *record_field,
			*concrete_struct_expr,
			*last_concrete_value_cell;
	int		other_var_num,
			value,
			min_value,
			max_value,
                        array_length,
                        array_index;

	if (var_list == NULL)
	  concrete_expr_list = NULL;
	else
	{
	  concrete_expr_list = last_concrete_expr_cell = NULL;
	  aux_concrete_expr_list = instantiate_var_list(var_list->next_st_bucket_cell);
	  for (var_cell = var_list->next_st_bucket_cell,
	       other_var_num = 0;
	       (var_cell != NULL);
	       var_cell = var_cell->next_st_bucket_cell,
	       other_var_num++);
	  variable = var_list->st_bucket;
	  switch (variable->info.expr->data_type->data_type_lexeme[0])
	  {
	    case 'i':
	    case 'b':
	      min_value = (variable->info.expr->data_type->data_type_lexeme[0] == 'i')?
			    (int)(variable->info.expr->opn1->info.expr->value):
			    0;
	      max_value = (variable->info.expr->data_type->data_type_lexeme[0] == 'i')?
			    (int)(variable->info.expr->opn2->info.expr->value):
			    1;
	      for (value = min_value;
		   (value <= max_value);
		   value++)
	      {
		variable->info.expr->value = (long double)value;
		merge_var_instances(&concrete_expr_list,
				    &last_concrete_expr_cell,
				    set_concrete_expr_bucket(variable),
				    aux_concrete_expr_list,
				    other_var_num);
	      }
	      break;
	    case 'a':
	      array_length = (int)(variable->info.expr->value);
	      for (array_elem = (VALUE_CELL **)(variable->info.expr->struct_value->struct_value),
		   array_index = 0,
		   comp_var_list = last_comp_var_cell = NULL;
		   (array_index < array_length);
		   array_elem++,
		   array_index++)
		if (comp_var_list == NULL)
		  last_comp_var_cell = comp_var_list = alloc_st_bucket_cell((*array_elem)->value_bucket,
									    NULL);
		else
		  last_comp_var_cell = last_comp_var_cell->next_st_bucket_cell =
		    alloc_st_bucket_cell((*array_elem)->value_bucket,
					 NULL);
	      concrete_comp_expr_list = instantiate_var_list(comp_var_list);
	      free_st_bucket_list(comp_var_list);
	      for (concrete_comp_expr_cell = concrete_comp_expr_list;
		   (concrete_comp_expr_cell != NULL);
		   )
	      {
		concrete_struct_expr = alloc_value_cell(concrete_comp_expr_cell->st_bucket,
                                                        array_length,
                                                        (VALUE_CELL *)new_calloc(array_length,
                                                                                 sizeof(VALUE_CELL *)),
                                                        NULL);
	        for (array_index = 0,
		     array_elem = (VALUE_CELL **)(concrete_struct_expr->struct_value);
		     (array_index < array_length);
		     array_index++,
                     array_elem++,
		     concrete_comp_expr_cell = concrete_comp_expr_cell->next_st_bucket_cell)
                  *array_elem =
		    alloc_value_cell(concrete_comp_expr_cell->st_bucket,
                                     concrete_comp_expr_cell->st_bucket->info.expr->value,
                                     concrete_comp_expr_cell->st_bucket->info.expr->struct_value,
                                     NULL);
		merge_var_instances(&concrete_expr_list,
				    &last_concrete_expr_cell,
				    set_expr_bucket(ARRAY_CONS_OP,
                                                    NULL,
                                                    NULL,
                                                    NULL,
                                                    (long double)array_length,
						    concrete_struct_expr,
                                                    FALSE),
				    aux_concrete_expr_list,
				    other_var_num);
	      }
	      free_st_bucket_list(concrete_comp_expr_list);
	      break;
	    case 'p':
	      for (record_field = variable->info.expr->struct_value,
		   comp_var_list = last_comp_var_cell = NULL;
                   (record_field != NULL);
                   record_field = record_field->next_value_cell)
		if (comp_var_list == NULL)
		  last_comp_var_cell = comp_var_list = alloc_st_bucket_cell(record_field->value_bucket,
									    NULL);
		else
		  last_comp_var_cell = last_comp_var_cell->next_st_bucket_cell =
		    alloc_st_bucket_cell(record_field->value_bucket,
					 NULL);
	      concrete_comp_expr_list = instantiate_var_list(comp_var_list);
	      free_st_bucket_list(comp_var_list);
	      for (concrete_comp_expr_cell = concrete_comp_expr_list;
		   (concrete_comp_expr_cell != NULL);
		   )
	      {
	        for (record_field = variable->info.expr->struct_value,
		     concrete_struct_expr = last_concrete_value_cell = NULL;
                     (record_field != NULL);
                     record_field = record_field->next_value_cell,
		     concrete_comp_expr_cell = concrete_comp_expr_cell->next_st_bucket_cell)
		  if (concrete_struct_expr == NULL)
		    last_concrete_value_cell = concrete_struct_expr =
		      alloc_value_cell(concrete_comp_expr_cell->st_bucket,
                                       concrete_comp_expr_cell->st_bucket->info.expr->value,
                                       concrete_comp_expr_cell->st_bucket->info.expr->struct_value,
                                       NULL);
		  else
		    last_concrete_value_cell = last_concrete_value_cell->next_value_cell =
		      alloc_value_cell(concrete_comp_expr_cell->st_bucket,
                                       concrete_comp_expr_cell->st_bucket->info.expr->value,
                                       concrete_comp_expr_cell->st_bucket->info.expr->struct_value,
                                       NULL);
		merge_var_instances(&concrete_expr_list,
				    &last_concrete_expr_cell,
				    set_expr_bucket(RECORD_CONS_OP,
                                                    NULL,
                                                    NULL,
                                                    NULL,
                                                    0.0L,
						    concrete_struct_expr,
                                                    FALSE),
				    aux_concrete_expr_list,
				    other_var_num);
	      }
	      free_st_bucket_list(concrete_comp_expr_list);
	      break;
	    default:
	      concrete_expr_list = NULL;
	      break;
	  }
	  free_st_bucket_list(aux_concrete_expr_list);
	}
	return(concrete_expr_list);
}


void		merge_var_instances(ST_BUCKET_CELL **concrete_expr_list,
				    ST_BUCKET_CELL **last_concrete_expr_cell,
				    ST_BUCKET      *concrete_expr,
				    ST_BUCKET_CELL *aux_concrete_expr_list,
				    int            other_var_num)
{
	ST_BUCKET_CELL	*aux_concrete_expr_cell;
	int		i;

	aux_concrete_expr_cell = aux_concrete_expr_list;
	do
	{
	  if (*concrete_expr_list == NULL)
	    *last_concrete_expr_cell = *concrete_expr_list =
	      alloc_st_bucket_cell(concrete_expr,
				   NULL);
	  else
	    *last_concrete_expr_cell = (*last_concrete_expr_cell)->next_st_bucket_cell =
	      alloc_st_bucket_cell(concrete_expr,
				   NULL);
	  for (i = 1;
	       (i <= other_var_num);
	       i++,
	       aux_concrete_expr_cell = aux_concrete_expr_cell->next_st_bucket_cell)
	    *last_concrete_expr_cell = (*last_concrete_expr_cell)->next_st_bucket_cell =
	      alloc_st_bucket_cell(aux_concrete_expr_cell->st_bucket,
				   NULL);
	}
	while (aux_concrete_expr_cell != NULL);
}


void            skip_local_state_comp(char *local_state_lexeme,
                                      int  *offset)
{
        /* offset is used as a displacement from the beginning of the string storing the concise prefix */
	/* lexeme of the local state whose substate is being skipped; upon entering a case, it indicates */
	/* the first character of the substate to be skipped; upon returning from a case, it indicates the */
	/* last character of the skipped substate */
        switch (local_state_lexeme[*offset])
        {
          case '_':
            break;
          case '.':
            for ((*offset)++;
                 (local_state_lexeme[*offset] != ' ');
                 (*offset)++);
            (*offset)++;
            skip_local_state_comp(local_state_lexeme,
                                  offset);
            break;
          case ':':
            for ((*offset)++;
                 (local_state_lexeme[*offset] != ' ');
                 (*offset)++);
            (*offset)++;
            skip_local_state_comp(local_state_lexeme,
                                  offset);
            break;
          case '+':
            (*offset)++;
            skip_local_state_comp(local_state_lexeme,
                                  offset);
            *offset += 2;
            skip_local_state_comp(local_state_lexeme,
                                  offset);
            break;
          default:
            for (;
                 ((local_state_lexeme[*offset] != ' ') &&
                  (local_state_lexeme[*offset] != EOS));
                 (*offset)++);
            (*offset)--;
            break;
        }
}


void		select_passive_local_transitions(L_TRANS_CELL **local_trans_list)
{
	L_TRANS_CELL	*curr_local_trans_cell,
			*prev_local_trans_cell,
			*local_trans_cell;
	long double	max_priority;

	for (curr_local_trans_cell = prev_local_trans_cell = *local_trans_list;
	     (curr_local_trans_cell != NULL);
	     )
	  if (curr_local_trans_cell->action->info.action->rate_index != PASSIVE)
	  {
	    prev_local_trans_cell = curr_local_trans_cell;
	    curr_local_trans_cell = curr_local_trans_cell->next_l_trans_cell;
	  }
	  else
	  {
	    for (local_trans_cell = *local_trans_list,
		 max_priority = curr_local_trans_cell->action->info.action->priority->info.expr->value;
	         (local_trans_cell != NULL);
		 local_trans_cell = local_trans_cell->next_l_trans_cell)
	      if (((local_trans_cell->action->info.action->type ==
		      curr_local_trans_cell->action->info.action->type) ||
		    ((curr_local_trans_cell->action->info.action->type->info.act_type != NULL) &&
		     check_list_membership(local_trans_cell->action->info.action->type,
		       			   curr_local_trans_cell->action->info.action->type->info.act_type->
					     duplicate_list,
					   FALSE))) &&
		  (local_trans_cell->action->info.action->action_index ==
		     curr_local_trans_cell->action->info.action->action_index) &&
		  (local_trans_cell->action->info.action->rate_index == PASSIVE) &&
		  ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		   (local_trans_cell->value_passing_info->guard ==
		      curr_local_trans_cell->value_passing_info->guard)) &&
		  (local_trans_cell->action->info.action->priority->info.expr->value > max_priority))
		max_priority = local_trans_cell->action->info.action->priority->info.expr->value;
	    if (curr_local_trans_cell->action->info.action->priority->info.expr->value == max_priority)
	    {
	      prev_local_trans_cell = curr_local_trans_cell;
	      curr_local_trans_cell = curr_local_trans_cell->next_l_trans_cell;
	    }
	    else
	      if (curr_local_trans_cell == prev_local_trans_cell)
	      {
		*local_trans_list = (*local_trans_list)->next_l_trans_cell;
	        free_l_trans_cell(curr_local_trans_cell);
		curr_local_trans_cell = prev_local_trans_cell = *local_trans_list;
	      }
	      else
	      {
	        curr_local_trans_cell = curr_local_trans_cell->next_l_trans_cell;
	        free_l_trans_cell(prev_local_trans_cell->next_l_trans_cell);
	        prev_local_trans_cell->next_l_trans_cell = curr_local_trans_cell;
	      }
	  }
}


void		remove_vanishing_global_states(void)
{
		BOOLEAN		found;
		G_TRANS_CELL	*imm_global_trans_cell,
				*prev_global_trans_cell,
				*curr_global_trans_cell;
		GST_BUCKET	*vanishing_global_state,
				*reaching_global_state;
		GST_BUCKET_CELL	*vanishing_global_state_cell,
				*reaching_global_state_cell,
				*global_state_cell,
				*prev_global_state_cell,
				*curr_global_state_cell;
		REWARD_INFO	splitted_reward_info;
		YB_CELL		*yb_cell,
				*splitted_yb_cell;
	static	char		*splitted_rate_lexeme		=	NULL;
		int		imm_global_trans_num,
				length;
	static	int		max_length			=	0;
		long double	splitted_yield,
				splitted_rate;

	for (vanishing_global_state_cell = vanishing_global_state_list;
	     (vanishing_global_state_cell != NULL);
	     vanishing_global_state_cell = vanishing_global_state_cell->next_gst_bucket_cell)
	{
	  vanishing_global_state = vanishing_global_state_cell->gst_bucket;
	  remove_immediate_selfloops(vanishing_global_state);
	  if (vanishing_global_state->global_trans_list != NULL)
	  {
	    /* compute the number of global transitions leaving the vanishing global state */
	    for (imm_global_trans_cell = vanishing_global_state->global_trans_list,
	         imm_global_trans_num = 0;
	         (imm_global_trans_cell != NULL);
	         imm_global_trans_cell = imm_global_trans_cell->next_g_trans_cell,
		 imm_global_trans_num++);
	    /* split the rate and all the yield rewards of each global transition of every global state */
	    /* reaching the vanishing one */
	    archi_type[spec_no]->info.archi_type->state_num++;
	    normalize_weights(vanishing_global_state->global_trans_list);
	    for (reaching_global_state_cell = vanishing_global_state->reaching_global_state_list;
		 (reaching_global_state_cell != NULL);
		 reaching_global_state_cell = reaching_global_state_cell->next_gst_bucket_cell)
	    {
	      reaching_global_state = reaching_global_state_cell->gst_bucket;
	      for (prev_global_trans_cell = curr_global_trans_cell =
		     reaching_global_state->global_trans_list;
		   (curr_global_trans_cell != NULL);
		   )
		if (curr_global_trans_cell->der_global_state == vanishing_global_state)
	        {
		  if (imm_global_trans_num > 1)
		  {
	            if (prev_global_trans_cell == curr_global_trans_cell)
		      reaching_global_state_cell->gst_bucket->global_trans_list =
		        curr_global_trans_cell->next_g_trans_cell;
	            else
		      prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell->next_g_trans_cell;
		  }
	          for (imm_global_trans_cell = vanishing_global_state->global_trans_list;
		       (imm_global_trans_cell != NULL);
		       imm_global_trans_cell = imm_global_trans_cell->next_g_trans_cell)
	          {
		    if (imm_global_trans_num > 1)
		    {
		      for (yb_cell = curr_global_trans_cell->reward_info.yb_list,
			   splitted_reward_info.yb_list = splitted_yb_cell = NULL;
		           (yb_cell != NULL);
		           yb_cell = yb_cell->next_yb_cell)
		      {
		        splitted_yield =
			  yb_cell->yield *
			  imm_global_trans_cell->action->info.action->rate->info.expr->value;
		        if (splitted_reward_info.yb_list == NULL)
		          splitted_reward_info.yb_list = splitted_yb_cell = alloc_yb_cell(splitted_yield,
									                  yb_cell->bonus,
					    				                  NULL);
		        else
		          splitted_yb_cell = splitted_yb_cell->next_yb_cell = alloc_yb_cell(splitted_yield,
											    yb_cell->bonus,
											    NULL);
		      }
		      splitted_rate = curr_global_trans_cell->action->info.action->rate->info.expr->value *
				      imm_global_trans_cell->action->info.action->rate->info.expr->value;
	              length = compute_digit_num(splitted_rate) +
		               F_FORMAT_PRECIS_LENGTH;
	              if (length > max_length)
	              {
  	                if (splitted_rate_lexeme != NULL)
    	                  free(splitted_rate_lexeme);
  	                splitted_rate_lexeme = alloc_string(length);
  	                max_length = length;
	              }
	              sprintf(splitted_rate_lexeme,
		              "%f",
		              (double)splitted_rate);
		      reaching_global_state->global_trans_list =
		        alloc_g_trans_cell(set_action_bucket(curr_global_trans_cell->action->info.action->
							       type,
		        		   		     curr_global_trans_cell->action->info.action->
							       action_index,
		          		   		     curr_global_trans_cell->action->info.action->
							       par_list,
		          		   		     curr_global_trans_cell->action->info.action->
							       rate_index,
		          		   		     curr_global_trans_cell->action->info.action->
							       priority,
		          		   		     set_number_bucket(splitted_rate_lexeme)),
		        	           NULL,
		        	           splitted_reward_info,
		        	           imm_global_trans_cell->der_global_state,
		        	           reaching_global_state->global_trans_list);
		    }
		    else
		      curr_global_trans_cell->der_global_state = imm_global_trans_cell->der_global_state;
		    if (imm_global_trans_cell->der_global_state != reaching_global_state)
		    {
		      for (global_state_cell =
			     imm_global_trans_cell->der_global_state->reaching_global_state_list,
			   found = FALSE;
			   ((global_state_cell != NULL) &&
			    !found);
			   global_state_cell = global_state_cell->next_gst_bucket_cell)
			found = (global_state_cell->gst_bucket == reaching_global_state);
		      if (!found)
			imm_global_trans_cell->der_global_state->reaching_global_state_list =
		          alloc_gst_bucket_cell(reaching_global_state,
						imm_global_trans_cell->der_global_state->
						  reaching_global_state_list);
		    }
	          }
		  if (imm_global_trans_num > 1)
		  {
	            if (prev_global_trans_cell == curr_global_trans_cell)
		      prev_global_trans_cell = reaching_global_state->global_trans_list;
	            free_g_trans_cell(curr_global_trans_cell);
	            curr_global_trans_cell = prev_global_trans_cell->next_g_trans_cell;
		  }
	          else
	          {
		    prev_global_trans_cell = curr_global_trans_cell;
		    curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	          }
	        }
	        else
	        {
		  prev_global_trans_cell = curr_global_trans_cell;
		  curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	        }
	      merge_global_transitions(reaching_global_state->global_trans_list);
	    }
	    /* split the initial state probability of the vanishing global state among the global states */
	    /* reachable from it in one step and update the list of global states from which they are */
	    /* reached by removing the vanishing global state */
	    for (imm_global_trans_cell = vanishing_global_state->global_trans_list;
		 (imm_global_trans_cell != NULL);
		 imm_global_trans_cell = imm_global_trans_cell->next_g_trans_cell)
	    {
	      aux_init_prob_vector[-(imm_global_trans_cell->der_global_state->global_state_no)] +=
		aux_init_prob_vector[-(vanishing_global_state->global_state_no)] *
		imm_global_trans_cell->action->info.action->rate->info.expr->value;
	      for (curr_global_state_cell = prev_global_state_cell =
		     imm_global_trans_cell->der_global_state->reaching_global_state_list;
		   ((curr_global_state_cell != NULL) &&
		    (curr_global_state_cell->gst_bucket != vanishing_global_state));
		   prev_global_state_cell = curr_global_state_cell,
		   curr_global_state_cell = curr_global_state_cell->next_gst_bucket_cell);
	      if (curr_global_state_cell != NULL)
	      {
	        if (curr_global_state_cell == prev_global_state_cell)
		  imm_global_trans_cell->der_global_state->reaching_global_state_list =
		    imm_global_trans_cell->der_global_state->reaching_global_state_list->
		      next_gst_bucket_cell;
	        else
		  prev_global_state_cell->next_gst_bucket_cell =
		    curr_global_state_cell->next_gst_bucket_cell;
	        free(curr_global_state_cell);
	      }
	    }
	  }
	}
}


void		remove_immediate_selfloops(GST_BUCKET *global_state)
{
	G_TRANS_CELL	*prev_global_trans_cell,
			*curr_global_trans_cell;

	for (prev_global_trans_cell = curr_global_trans_cell = global_state->global_trans_list;
	     (curr_global_trans_cell != NULL);
	     )
	  if (curr_global_trans_cell->der_global_state == global_state)
	  {
	    if (prev_global_trans_cell == curr_global_trans_cell)
	    {
	      curr_global_trans_cell = global_state->global_trans_list =
		global_state->global_trans_list->next_g_trans_cell;
	      free_g_trans_cell(prev_global_trans_cell);
	      prev_global_trans_cell = curr_global_trans_cell;
	    }
	    else
	    {
	      curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	      free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
	      prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
	    }
	  }
	  else
	  {
	    prev_global_trans_cell = curr_global_trans_cell;
	    curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	  }
}


void		normalize_weights(G_TRANS_CELL *global_trans_list)
{
		G_TRANS_CELL	*global_trans_cell;
	static	char		*normalized_weight_lexeme	=	NULL;
		int		length;
	static	int		max_length			=	0;
		long double	aggregate_weight,
				normalized_weight;

	for (global_trans_cell = global_trans_list,
	     aggregate_weight = 0.0L;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  aggregate_weight += global_trans_cell->action->info.action->rate->info.expr->value;
	if (aggregate_weight != 1.0L)
	  for (global_trans_cell = global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	  {
	    normalized_weight = global_trans_cell->action->info.action->rate->info.expr->value /
			    	aggregate_weight;
	    length = compute_digit_num(normalized_weight) +
		     F_FORMAT_PRECIS_LENGTH;
	    if (length > max_length)
	    {
  	      if (normalized_weight_lexeme != NULL)
    	        free(normalized_weight_lexeme);
  	      normalized_weight_lexeme = alloc_string(length);
  	      max_length = length;
	    }
	    sprintf(normalized_weight_lexeme,
		    "%f",
		    (double)normalized_weight);
	    global_trans_cell->action =
	      set_action_bucket(global_trans_cell->action->info.action->type,
			        global_trans_cell->action->info.action->action_index,
			        global_trans_cell->action->info.action->par_list,
			        global_trans_cell->action->info.action->rate_index,
			        global_trans_cell->action->info.action->priority,
				set_number_bucket(normalized_weight_lexeme));
	  }
}


void		renumber_psm_global_states(void)
{
	BOOLEAN		exit_self;
	GST_BUCKET	*global_state,
			*der_global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell;
	G_TRANS_CELL	*global_trans_cell;
	int		neg_global_state_no;

	/* initialize the stack of the global states to be considered with the initial global state, then */
	/* process one global state at a time until the stack is nonempty; recursion is not employed to */
	/* avoid run-time-support stack overflow in the case of a huge global state space */
	global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
						   NULL);
	archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered = FALSE;
	while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
	  global_state_cell = global_state_stack;
	  global_state_stack = global_state_stack->next_gst_bucket_cell;
	  global_state = global_state_cell->gst_bucket;
	  free(global_state_cell);
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
	  /* case the performance semantic model is continuous-time (the vanishing global states from the */
	  /* initial global state to the first nonvanishing one must be overridden) */
	  if ((archi_type[spec_no]->info.archi_type->psm_index != HCTMC) ||
	      !check_vanishing(global_state))
	  {
	    /* assign a positive serial number and an initial state probability to the global state */
	    if (global_state->global_state_no < 0)
	    {
	      neg_global_state_no = global_state->global_state_no;
	      global_state->global_state_no = ++archi_type[spec_no]->info.archi_type->state_num;
	      if ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC) &&
		  (vanishing_global_state_list != NULL))
	        init_prob_vector[0][global_state->global_state_no] =
		  aux_init_prob_vector[-neg_global_state_no];
	    }
	    /* handle the global transitions of the global state */
	    if (global_state->global_trans_list == NULL)
	      archi_type[spec_no]->info.archi_type->absorbing_state_num++;
	    else
	    {
	      if (check_vanishing(global_state))
	      {
	        normalize_weights(global_state->global_trans_list);
		if (archi_type[spec_no]->info.archi_type->psm_index == HSMC)
		  archi_type[spec_no]->info.archi_type->vanishing_state_num++;
	      }
	      for (global_trans_cell = global_state->global_trans_list,
		   exit_self = FALSE;
	           (global_trans_cell != NULL);
	           global_trans_cell = global_trans_cell->next_g_trans_cell,
		   exit_self = (exit_self || (der_global_state != global_state)))
	      {
		der_global_state = global_trans_cell->der_global_state;
	        if (der_global_state->global_state_no < 0)
		{
		  neg_global_state_no = der_global_state->global_state_no;
	          der_global_state->global_state_no = ++archi_type[spec_no]->info.archi_type->state_num;
	          if ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC) &&
		      (vanishing_global_state_list != NULL))
		    init_prob_vector[0][der_global_state->global_state_no] =
		      aux_init_prob_vector[-neg_global_state_no];
		}
	        archi_type[spec_no]->info.archi_type->trans_num++;
		if ((archi_type[spec_no]->info.archi_type->psm_index == HSMC) &&
		    check_vanishing(global_state))
		  archi_type[spec_no]->info.archi_type->immediate_trans_num++;
	      }
	      if (!exit_self)
	        archi_type[spec_no]->info.archi_type->absorbing_state_num++;
	    }
	  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
	  for (global_trans_cell = global_state->global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (global_trans_cell->der_global_state->global_trans_considered)
	    {
	      global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
							 global_state_stack);
	      global_trans_cell->der_global_state->global_trans_considered = FALSE;
	    }
	}
}


void		print_semantic_model(int *next_global_state_no)
{
	GST_BUCKET      *global_state;
        GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
        G_TRANS_CELL    *global_trans_cell;

	/* print the .ism/.fms/.psm file header */
	fprintf(sem_model_file,
	        "%s underlying %s%s",
		(option_index == ISM)?
		  "Integrated semantic model":
		  ((option_index == FSM)?
		     "Functional semantic model":
		     ((archi_type[spec_no]->info.archi_type->psm_index == HCTMC)?
		        "Homogeneous continuous-time Markov chain":
		        "Homogeneous discrete-time Markov chain")),
		archi_type[spec_no]->symbol_lexeme,
		(archi_type[spec_no]->info.archi_type->value_passing == (char)NO_VP)?
		  "":
		  ((archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)?
		     "\n(the data variables are assigned concrete values in the local states)":
		     "\n(the data variables are assigned symbolic values at the beginning as well as in the transitions)"));
	fprintf(sem_model_file,
	        ":\n\n\n");
	if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	{
	  if (archi_type[spec_no]->info.archi_type->init_assign_list == NULL)
	    fprintf(sem_model_file,
		    ">> No initial assignments of symbolic values to the data variables.");
	  else
	  {
	    fprintf(sem_model_file,
		    ">> Initial assignments of symbolic values to the data variables:\n\n\t");
	    print_assign_list(archi_type[spec_no]->info.archi_type->init_assign_list,
			      1,
			      0);
	  }
	  fprintf(sem_model_file,
		  "\n\n\n");
	}

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered =
	  (option_index == PSM);
        while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
          /* case the performance semantic model is continuous-time (the vanishing global states from the */
          /* initial global state to the first nonvanishing one must be overridden) */
          if ((option_index != PSM) ||
	      (archi_type[spec_no]->info.archi_type->psm_index != HCTMC) ||
              !check_vanishing(global_state))
          {
	    if (global_state->global_state_no == *next_global_state_no)
	    {
              print_global_state(global_state);
              print_global_trans_list(global_state->global_trans_list,
				      next_global_state_no);
	    }
            for (global_trans_cell = global_state->global_trans_list;
                 (global_trans_cell != NULL);
                 global_trans_cell = global_trans_cell->next_g_trans_cell)
              if (global_trans_cell->der_global_state->global_state_no == *next_global_state_no)
	      {
                print_global_state(global_trans_cell->der_global_state);
                print_global_trans_list(global_trans_cell->der_global_state->global_trans_list,
				        next_global_state_no);
	      }
	  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (((option_index != PSM) &&
		 global_trans_cell->der_global_state->global_trans_considered) ||
                ((option_index == PSM) &&
		 !global_trans_cell->der_global_state->global_trans_considered))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered = (option_index == PSM);
            }
	}
}


void		print_global_state(GST_BUCKET *global_state)
{
	ST_BUCKET_CELL	*aei_cell;
	int		i,
			offset;

	fprintf(sem_model_file,
		"%s>> Global state %d:",
		(global_state->global_state_no == 1)?
		"":
		"\n\n\n",
		global_state->global_state_no);
	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list,
	     i = 0;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell,
	     i++)
	{
	  fprintf(sem_model_file,
		  "\n\n\t- Local state of %s:\n\n\t\t",
		  aei_cell->st_bucket->symbol_lexeme);
	  for (offset = 0;
	       (global_state->local_state_vector[i]->local_state_lexeme[offset] != '#');
	       offset++);
	  offset++;
	  print_extended_local_state(global_state->local_state_vector[i]->local_state_lexeme,
				     &offset);
	  if (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)
	  {
	    fprintf(sem_model_file,
		    "\n\n\t  ");
	    if (global_state->local_state_vector[i]->state_var_assign_list == NULL)
	      fprintf(sem_model_file,
		      "where no concrete values are assigned to the data variables.");
	    else
	    {
	      fprintf(sem_model_file,
		      "where the following concrete values are assigned to the data variables:\n\n\t\t");
	      print_assign_list(global_state->local_state_vector[i]->state_var_assign_list,
			        2,
			        0);
	    }
	  }
	}
	if (option_index == PSM)
	  fprintf(sem_model_file,
		  "\n\n\t- Initial state probability: %g",
		  (double)(init_prob_vector[0][global_state->global_state_no]));
}


void		print_extended_local_state(char *local_state_lexeme,
					   int  *offset)
{
	char		*behavior_lexeme;

        /* offset is used as a displacement from the beginning of the lexeme in concise prefix notation of */
	/* the local state being printed; upon entering a case, it indicates the first character of the */
	/* sublexeme to be extended and printed; upon returning from a case, it indicates the last */
	/* character of the extended and printed sublexeme */
        switch (local_state_lexeme[*offset])
        {
          case '_':
	    fprintf(sem_model_file,
		    "stop");
            break;
          case '.':
            (*offset)++;
	    fprintf(sem_model_file,
		    "%s . ",
		    decode_local_state_comp(local_state_lexeme,
					    offset)->symbol_lexeme);
            *offset += 2;
	    print_extended_local_state(local_state_lexeme,
			    	       offset);
            break;
          case ':':
            (*offset)++;
	    fprintf(sem_model_file,
		    "cond(%s) -> ",
		    decode_local_state_comp(local_state_lexeme,
					    offset)->symbol_lexeme);
            *offset += 2;
	    print_extended_local_state(local_state_lexeme,
			    	       offset);
            break;
          case '+':
	    fprintf(sem_model_file,
		    "choice{");
            (*offset)++;
	    print_extended_local_state(local_state_lexeme,
			    	       offset);
	    fprintf(sem_model_file,
		    ", ");
            *offset += 2;
	    print_extended_local_state(local_state_lexeme,
			    	       offset);
	    fprintf(sem_model_file,
		    "}");
            break;
          default:
	    behavior_lexeme = decode_local_state_comp(local_state_lexeme,
						      offset)->symbol_lexeme;
	    fprintf(sem_model_file,
		    (strchr(behavior_lexeme,
			    '(') == NULL)?
		      "%s()":
		      "%s",
		    behavior_lexeme);
            break;
        }
}


void		print_global_trans_list(G_TRANS_CELL *global_trans_list,
					int          *next_global_state_no)
{
	G_TRANS_CELL	*global_trans_cell;
	char		*act_type_lexeme;
	int		i,
			open_paren_num;

	if (global_trans_list == NULL)
	  fprintf(sem_model_file,
		  "\n\n\t- No transitions.");
	else
	{
	  for (global_trans_cell = global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	  {
	    fprintf(sem_model_file,
		    "\n\n\t- Transition:");
	    if ((archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
		(option_index != PSM))
	      fprintf(sem_model_file,
		      "\n\n\t\t- Guard:\t\t   %s",
		      global_trans_cell->value_passing_info->guard->symbol_lexeme);
	    fprintf(sem_model_file,
		    "\n\n\t\t- ");
	    switch (option_index)
	    {
	      case ISM:
	        fprintf(sem_model_file,
		        "Action:\t\t   %s",
			global_trans_cell->action->symbol_lexeme);
	        break;
	      case FSM:
	        fprintf(sem_model_file,
		        "Action type:\t\t   ");
	        for (act_type_lexeme = global_trans_cell->action->symbol_lexeme + 1,
		     i = open_paren_num = 0;
		     ((act_type_lexeme[i] != ',') ||
		      (open_paren_num > 0));
		     i++)
	        {
		  fprintf(sem_model_file,
			  "%c",
			  act_type_lexeme[i]);
		  if (act_type_lexeme[i] == '(')
		    open_paren_num++;
		  else
		    if (act_type_lexeme[i] == ')')
		      open_paren_num--;
	        }
	        break;
	      case PSM:
	        fprintf(sem_model_file,
		        "%s\t   %s",
		        (archi_type[spec_no]->info.archi_type->psm_index == HCTMC)?
		          "Rate:\t\t":
		          "Probability:\t",
		        global_trans_cell->action->info.action->rate->symbol_lexeme);
	        break;
	      default:
	        break;
	    }
	    if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	    {
	      fprintf(sem_model_file,
		      "\n\n\t\t- ");
	      if (global_trans_cell->value_passing_info->assign_list == NULL)
	        fprintf(sem_model_file,
		        "No symbolic assignments.");
	      else
	      {
	        fprintf(sem_model_file,
		        "Symbolic assignments:\t");
	        print_assign_list(global_trans_cell->value_passing_info->assign_list,
				  5,
				  3);
	      }
	    }
	    fprintf(sem_model_file,
		    "\n\n\t\t- Derivative global state: %d",
		    global_trans_cell->der_global_state->global_state_no);
	  }
	}
	(*next_global_state_no)++;
}


void		print_assign_list(ST_BUCKET_CELL *assign_list,
				  int            tab_num,
				  int            blank_num)
{
	ST_BUCKET_CELL	*assign_cell;
	int		tab_no,
			blank_no;

	for (assign_cell = assign_list;
	     (assign_cell != NULL);
	     assign_cell = assign_cell->next_st_bucket_cell)
	{
	  if (assign_cell != assign_list)
	  {
	    fprintf(sem_model_file,
		    "\n");
	    for (tab_no = 1;
	         (tab_no <= tab_num);
	         tab_no++)
	      fprintf(sem_model_file,
		      "\t");
	  }
	  for (blank_no = 1;
	       (blank_no <= blank_num);
	       blank_no++)
	    fprintf(sem_model_file,
		    " ");
	  fprintf(sem_model_file,
		  "%s",
		  assign_cell->st_bucket->symbol_lexeme);
	}
}


void		mark_passive_or_local_transitions(GST_BUCKET *global_state)
{
	L_TRANS_CELL	*local_trans_cell,
			*aux_local_trans_cell;
	long double	max_priority;
	int		i,
			aux_i;

	for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
	     (i >= 0);
	     i--)
	  for (local_trans_cell = global_state->local_state_vector[i]->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    if ((local_trans_cell->action->info.action->type->info.act_type->duplicate_list != NULL) &&
		(local_trans_cell->action->info.action->rate_index == (char)PASSIVE) &&
		(local_trans_cell->or_marked == (char)NO_OR))
	    {
	      for (aux_i = i,
		   max_priority = local_trans_cell->action->info.action->priority->info.expr->value;
	           (aux_i >= 0);
	           aux_i--)
	        for (aux_local_trans_cell = (aux_i == i)?
					      local_trans_cell->next_l_trans_cell:
					      global_state->local_state_vector[aux_i]->local_trans_list;
	             (aux_local_trans_cell != NULL);
	             aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	          if (check_list_membership(aux_local_trans_cell->action->info.action->type,
		        		    local_trans_cell->action->info.action->type->info.act_type->
					      duplicate_list,
					    FALSE) &&
	    	      (aux_local_trans_cell->or_marked == (char)NO_OR) &&
		      ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		       (aux_local_trans_cell->value_passing_info->guard ==
			  local_trans_cell->value_passing_info->guard)) &&
		      (aux_local_trans_cell->action->info.action->priority->info.expr->value > max_priority))
		    max_priority = aux_local_trans_cell->action->info.action->priority->info.expr->value;
	      for (aux_i = i;
	           (aux_i >= 0);
	           aux_i--)
	        for (aux_local_trans_cell = (aux_i == i)?
					      local_trans_cell:
					      global_state->local_state_vector[aux_i]->local_trans_list;
	             (aux_local_trans_cell != NULL);
	             aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	          if (check_list_membership(aux_local_trans_cell->action->info.action->type,
		        		    local_trans_cell->action->info.action->type->info.act_type->
					      duplicate_list,
					    FALSE) &&
	    	      (aux_local_trans_cell->or_marked == (char)NO_OR) &&
		      ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		       (aux_local_trans_cell->value_passing_info->guard ==
			  local_trans_cell->value_passing_info->guard)))
		    aux_local_trans_cell->or_marked =
		      (aux_local_trans_cell->action->info.action->priority->info.expr->value ==
		         max_priority)?
		        HIGH_PRIORITY_PASSIVE_OR:
			LOW_PRIORITY_PASSIVE_OR;
	    }
}


void		compose_local_transitions(GST_BUCKET *starting_global_state,
					  ST_BUCKET  *aei)
{
		BOOLEAN		found;
		G_TRANS_CELL	*global_trans_list,
				*last_new_global_trans_cell,
				*first_sync_new_global_trans_cell,
				*global_trans_cell,
				*curr_global_trans_cell,
				*prev_global_trans_cell,
				*next_global_trans_cell,
				*aux_global_trans_cell,
				*sync_global_trans_cell;
		LST_BUCKET	**der_local_state_vector;
		L_TRANS_CELL	*local_trans_list,
				*local_trans_cell,
				*aux_local_trans_cell;
		ST_BUCKET	*adjusted_priority_bucket,
				*normalized_rate_bucket;
		VP_INFO		*value_passing_info;
		YB_CELL		*yb_cell;
	static	char		*normalized_rate_lexeme		=	NULL;
		int		i,
		 		corr_passive_trans_num,
		 		corr_passive_trans_no,
				length;
	static	int		max_length			=	0;
		long double	corr_aggregate_passive_weight,
				aggregate_passive_weight,
				normalized_rate,
				adjusted_priority;

	/* determine the involved lists of transitions */
	global_trans_list = starting_global_state->global_trans_list;
	last_new_global_trans_cell = starting_global_state->global_trans_list = NULL;
	local_trans_list =
	  starting_global_state->local_state_vector[aei->info.aei->aei_no - 1]->local_trans_list;

	/* handle the left asynchronous transitions and the left-to-right synchronizations */
	for (curr_global_trans_cell = prev_global_trans_cell = global_trans_list;
	     (curr_global_trans_cell != NULL);
	     )
	{
	  aux_global_trans_cell = curr_global_trans_cell;
	  if (!check_list_membership(curr_global_trans_cell->action->info.action->type,
				     aei->info.aei->sync_set,
				     FALSE))
	  {
	    /* make the pointers advance; this is carried out first to avoid side effects on pointers */
	    if (curr_global_trans_cell == prev_global_trans_cell)
	      curr_global_trans_cell = prev_global_trans_cell = global_trans_list =
		curr_global_trans_cell->next_g_trans_cell;
	    else
	    {
	      curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	      prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
	    }
	    /* generate a left asynchronous transition */
	    if (last_new_global_trans_cell == NULL)
	      last_new_global_trans_cell = starting_global_state->global_trans_list = aux_global_trans_cell;
	    else
	      last_new_global_trans_cell = last_new_global_trans_cell->next_g_trans_cell =
		aux_global_trans_cell;
	    last_new_global_trans_cell->next_g_trans_cell = NULL;
	    ((LST_BUCKET **)(last_new_global_trans_cell->der_global_state))[aei->info.aei->aei_no - 1] =
	      starting_global_state->local_state_vector[aei->info.aei->aei_no - 1];
	  }
	  else
	  {
	    /* look for right transitions matching the left transition */
	    for (local_trans_cell = local_trans_list,
		 corr_passive_trans_num = 0,
		 corr_aggregate_passive_weight = 0.0L,
		 first_sync_new_global_trans_cell = NULL;
		 (local_trans_cell != NULL);
		 local_trans_cell = local_trans_cell->next_l_trans_cell)
	      if ((aux_global_trans_cell->action->info.action->type ==
		     local_trans_cell->action->info.action->type) &&
		  (((aux_global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		    (local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		    (aux_global_trans_cell->action->info.action->rate_index != PASSIVE) &&
		    (local_trans_cell->action->info.action->rate_index == PASSIVE) &&
		    (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR)) ||
		   ((aux_global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		    (local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		    (aux_global_trans_cell->action->info.action->rate_index == PASSIVE) &&
		    (local_trans_cell->action->info.action->rate_index == PASSIVE) &&
		    (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR)) ||
		   ((aux_global_trans_cell->action->info.action->action_index == OUTPUT) &&
		    (local_trans_cell->action->info.action->action_index == INPUT) &&
		    (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR)) ||
		   ((aux_global_trans_cell->action->info.action->action_index == INPUT) &&
		    (local_trans_cell->action->info.action->action_index == INPUT) &&
		    (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR))) &&
		  ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		   check_lists_equality(aux_global_trans_cell->action->info.action->par_list,
					local_trans_cell->action->info.action->par_list)))
	      {
	        /* update the information about the matching right transitions */
		corr_passive_trans_num++;
	        if ((option_index != SIMULATION) ||
		    (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		    local_trans_cell->value_passing_info->guard->info.expr->value)
		  corr_aggregate_passive_weight +=
		    local_trans_cell->action->info.action->rate->info.expr->value;
		/* compute the value passing related information about the left-to-right synchronization: */
		/* - guards must be joined; */
		/* - synchronization assignments must precede unfolding assignments */
		if (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP)
		  value_passing_info = NULL;
		else
		{
	          value_passing_info =
		    alloc_vp_info((!strcmp(aux_global_trans_cell->value_passing_info->guard->symbol_lexeme,
					   "true"))?
				    local_trans_cell->value_passing_info->guard:
				    ((!strcmp(local_trans_cell->value_passing_info->guard->symbol_lexeme,
					      "true"))?
				       aux_global_trans_cell->value_passing_info->guard:
				       set_expr_bucket(AND_OP,
					               aux_global_trans_cell->value_passing_info->guard,
					               local_trans_cell->value_passing_info->guard,
					               NULL,
					               0.0L,
					               NULL,
					               FALSE)),
				  merge_assign_lists(aux_global_trans_cell->value_passing_info,
						     local_trans_cell->value_passing_info),
				  aux_global_trans_cell->value_passing_info->input_output_assign_num +
				    local_trans_cell->value_passing_info->input_output_assign_num);
		  if (option_index == SIMULATION)
		    value_passing_info->guard->info.expr->value =
		      (long double)(aux_global_trans_cell->value_passing_info->guard->info.expr->value &&
				    local_trans_cell->value_passing_info->guard->info.expr->value);
		  if (local_trans_cell->action->info.action->action_index == INPUT)
		    insert_par_assign_list(value_passing_info,
					   local_trans_cell->action->info.action->par_list,
					   aux_global_trans_cell->action->info.action->par_list);
		}
		/* compute the derivative state of the left-to-right synchronization */
	        der_local_state_vector =
	          (LST_BUCKET **)new_calloc(archi_type[spec_no]->info.archi_type->aei_num,
					    sizeof(LST_BUCKET *));
	        for (i = aei->info.aei->aei_no - 2;
		     (i >= 0);
		     i--)
	          der_local_state_vector[i] =
		    ((LST_BUCKET **)(aux_global_trans_cell->der_global_state))[i];
	        der_local_state_vector[aei->info.aei->aei_no - 1] = local_trans_cell->der_local_state;
	        for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
		     (i >= aei->info.aei->aei_no);
		     i--)
	          der_local_state_vector[i] = NULL;
		/* generate the left-to-right synchronization */
	        if (last_new_global_trans_cell == NULL)
	          last_new_global_trans_cell = starting_global_state->global_trans_list =
		    alloc_g_trans_cell(((aux_global_trans_cell->action->info.action->rate_index !=
					   PASSIVE) ||
				        (local_trans_cell->or_marked != DUPLICATE_OR))?
				         aux_global_trans_cell->action:
				         local_trans_cell->action,
				       value_passing_info,
				       duplicate_reward_info(aux_global_trans_cell->reward_info),
				       (GST_BUCKET *)der_local_state_vector,
				       NULL);
	        else
	          last_new_global_trans_cell = last_new_global_trans_cell->next_g_trans_cell =
		    alloc_g_trans_cell(((aux_global_trans_cell->action->info.action->rate_index !=
					   PASSIVE) ||
				        (local_trans_cell->or_marked != DUPLICATE_OR))?
				         aux_global_trans_cell->action:
				         local_trans_cell->action,
				       value_passing_info,
				       duplicate_reward_info(aux_global_trans_cell->reward_info),
				       (GST_BUCKET *)der_local_state_vector,
				       NULL);
		if (first_sync_new_global_trans_cell == NULL)
		  first_sync_new_global_trans_cell = last_new_global_trans_cell;
	      }
	    /* normalize the rates and the yield rewards of the left-to-right synchronizations; this is */
	    /* postponed in the case of left-to-right synchronizations deriving from an or-interaction */
	    if ((corr_passive_trans_num > 0) &&
		(aux_global_trans_cell->action->info.action->type->info.act_type->interaction_index !=
		   SYNC_OR))
	    {
	      if (aux_global_trans_cell->action->info.action->rate_index != PASSIVE)
	      {
	        /* handle the normalization for the left-to-right generative-reactive synchronizations */
		/* involving the left transition, considering them one at a time */
	        if (corr_passive_trans_num > 1)
	          for (sync_global_trans_cell = first_sync_new_global_trans_cell,
		       local_trans_cell = local_trans_list,
		       corr_passive_trans_no = 1;
		       (corr_passive_trans_no <= corr_passive_trans_num);
		       corr_passive_trans_no++,
		       sync_global_trans_cell = sync_global_trans_cell->next_g_trans_cell)
		  {
	            for (found = FALSE;
		         (!found);
		         local_trans_cell = local_trans_cell->next_l_trans_cell)
	              if ((found =
			    ((aux_global_trans_cell->action->info.action->type ==
		                local_trans_cell->action->info.action->type) &&
		             (((aux_global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		               (local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		               (local_trans_cell->action->info.action->rate_index == PASSIVE)) ||
		              ((aux_global_trans_cell->action->info.action->action_index == OUTPUT) &&
		               (local_trans_cell->action->info.action->action_index == INPUT))) &&
		             ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		              check_lists_equality(aux_global_trans_cell->action->info.action->par_list,
					           local_trans_cell->action->info.action->par_list)) &&
			     (((LST_BUCKET **)(sync_global_trans_cell->der_global_state))
			      [aei->info.aei->aei_no - 1] == local_trans_cell->der_local_state))))
		      {
			/* update the normalization flag for the starting global state */
		        if ((option_index == SIMULATION) &&
			    (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			    strcmp(local_trans_cell->value_passing_info->guard->symbol_lexeme,
				   "true"))
			  starting_global_state->global_trans_need_norm = TRUE;
	                if ((option_index != SIMULATION) ||
		            (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		            sync_global_trans_cell->value_passing_info->guard->info.expr->value)
			{
		          /* normalize the rate of the left-to-right generative-reactive synchronization */
		          normalized_rate =
			    aux_global_trans_cell->action->info.action->rate->info.expr->value *
			    (local_trans_cell->action->info.action->rate->info.expr->value /
			     corr_aggregate_passive_weight);
	                  length = compute_digit_num(normalized_rate) +
		                   F_FORMAT_PRECIS_LENGTH;
	                  if (length > max_length)
	                  {
  	                    if (normalized_rate_lexeme != NULL)
    	                      free(normalized_rate_lexeme);
  	                    normalized_rate_lexeme = alloc_string(length);
  	                    max_length = length;
	                  }
	                  sprintf(normalized_rate_lexeme,
		                  "%f",
		                  (double)normalized_rate);
		          sync_global_trans_cell->action =
			    set_action_bucket(sync_global_trans_cell->action->info.action->type,
					      sync_global_trans_cell->action->info.action->action_index,
					      sync_global_trans_cell->action->info.action->par_list,
					      sync_global_trans_cell->action->info.action->rate_index,
					      sync_global_trans_cell->action->info.action->priority,
					      set_number_bucket(normalized_rate_lexeme));
		          /* normalize the yield rewards of the left-to-right generative-reactive */
			  /* synchronization */
			  if (option_index != SIMULATION)
                            for (yb_cell = sync_global_trans_cell->reward_info.yb_list;
                                 (yb_cell != NULL);
                                 yb_cell = yb_cell->next_yb_cell)
		              yb_cell->yield *=
			        local_trans_cell->action->info.action->rate->info.expr->value /
			        corr_aggregate_passive_weight;
			}
		      }
		  }
	      }
	      else
	      {
	        /* handle the normalization for the left-to-right reactive-reactive synchronizations */
		/* involving the left transition; start with the computation of the total passive weight */
		/* of the left process */
	        for (global_trans_cell = global_trans_list,
		     aggregate_passive_weight = 0.0L;
		     (global_trans_cell != NULL);
		     global_trans_cell = global_trans_cell->next_g_trans_cell)
		  if ((global_trans_cell->action->info.action->type ==
		         aux_global_trans_cell->action->info.action->type) &&
		      ((global_trans_cell->action->info.action->action_index ==
		          aux_global_trans_cell->action->info.action->action_index) ||
		       ((global_trans_cell->action->info.action->action_index == INPUT) &&
		        (aux_global_trans_cell->action->info.action->action_index == OUTPUT))) &&
		      ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		       check_lists_equality(global_trans_cell->action->info.action->par_list,
					    aux_global_trans_cell->action->info.action->par_list)))
		  {
		    if ((global_trans_cell != aux_global_trans_cell) &&
			(option_index == SIMULATION) &&
			(archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			strcmp(global_trans_cell->value_passing_info->guard->symbol_lexeme,
			       "true"))
		      starting_global_state->global_trans_need_norm = TRUE;
	            if ((option_index != SIMULATION) ||
		        (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		        global_trans_cell->value_passing_info->guard->info.expr->value)
		      aggregate_passive_weight +=
		        global_trans_cell->action->info.action->rate->info.expr->value;
		  }
		/* consider one left-to-right reactive-reactive synchronization at a time */
	        for (sync_global_trans_cell = first_sync_new_global_trans_cell,
		     local_trans_cell = local_trans_list,
		     corr_passive_trans_no = 1;
		     (corr_passive_trans_no <= corr_passive_trans_num);
		     corr_passive_trans_no++,
		     sync_global_trans_cell = sync_global_trans_cell->next_g_trans_cell)
		{
	          for (found = FALSE;
		       (!found);
		       local_trans_cell = local_trans_cell->next_l_trans_cell)
	            if ((found =
	                  ((aux_global_trans_cell->action->info.action->type ==
		              local_trans_cell->action->info.action->type) &&
		           (((aux_global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		             (local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		             (local_trans_cell->action->info.action->rate_index == PASSIVE)) ||
		            ((aux_global_trans_cell->action->info.action->action_index == OUTPUT) &&
		             (local_trans_cell->action->info.action->action_index == INPUT)) ||
		            ((aux_global_trans_cell->action->info.action->action_index == INPUT) &&
		             (local_trans_cell->action->info.action->action_index == INPUT))) &&
		           ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		            check_lists_equality(aux_global_trans_cell->action->info.action->par_list,
					         local_trans_cell->action->info.action->par_list)) &&
			   (((LST_BUCKET **)(sync_global_trans_cell->der_global_state))
			    [aei->info.aei->aei_no - 1] == local_trans_cell->der_local_state))))
		    {
		      /* update the normalization flag for the starting global state */
		      if ((corr_passive_trans_num > 1) &&
			  (option_index == SIMULATION) &&
			  (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			  strcmp(local_trans_cell->value_passing_info->guard->symbol_lexeme,
				 "true"))
			starting_global_state->global_trans_need_norm = TRUE;
	              if ((option_index != SIMULATION) ||
		          (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		          sync_global_trans_cell->value_passing_info->guard->info.expr->value)
		      {
		        /* adjust the priority and normalize the rate of the left-to-right */
			/* reactive-reactive synchronization */
		        adjusted_priority =
		          (aux_global_trans_cell->action->info.action->priority->info.expr->value >=
			     local_trans_cell->action->info.action->priority->info.expr->value)?
			    aux_global_trans_cell->action->info.action->priority->info.expr->value:
			    local_trans_cell->action->info.action->priority->info.expr->value;
		        normalized_rate =
		          (aux_global_trans_cell->action->info.action->rate->info.expr->value /
		           aggregate_passive_weight) *
		          (local_trans_cell->action->info.action->rate->info.expr->value /
		           corr_aggregate_passive_weight) *
		           ((aux_global_trans_cell->action->info.action->priority->info.expr->value ==
			       local_trans_cell->action->info.action->priority->info.expr->value)?
			      (aggregate_passive_weight + corr_aggregate_passive_weight):
			      ((aux_global_trans_cell->action->info.action->priority->info.expr->value >
			          local_trans_cell->action->info.action->priority->info.expr->value)?
			         aggregate_passive_weight:
			         corr_aggregate_passive_weight));
		        if ((sync_global_trans_cell->action->info.action->priority->info.expr->value !=
			       adjusted_priority) ||
			    (sync_global_trans_cell->action->info.action->rate->info.expr->value !=
			       normalized_rate))
		        {
			  adjusted_priority_bucket =
		            (aux_global_trans_cell->action->info.action->priority->info.expr->value >=
			       local_trans_cell->action->info.action->priority->info.expr->value)?
			      aux_global_trans_cell->action->info.action->priority:
			      local_trans_cell->action->info.action->priority;
			  if (sync_global_trans_cell->action->info.action->rate->info.expr->value ==
			        normalized_rate)
			    normalized_rate_bucket = sync_global_trans_cell->action->info.action->rate;
			  else
			  {
	                    length = compute_digit_num(normalized_rate) +
		                     F_FORMAT_PRECIS_LENGTH;
	                    if (length > max_length)
	                    {
  	                      if (normalized_rate_lexeme != NULL)
    	                        free(normalized_rate_lexeme);
  	                      normalized_rate_lexeme = alloc_string(length);
  	                      max_length = length;
	                    }
	                    sprintf(normalized_rate_lexeme,
		                    "%f",
		                    (double)normalized_rate);
			    normalized_rate_bucket = set_number_bucket(normalized_rate_lexeme);
			  }
		          sync_global_trans_cell->action =
		            set_action_bucket(sync_global_trans_cell->action->info.action->type,
			                      sync_global_trans_cell->action->info.action->action_index,
			                      sync_global_trans_cell->action->info.action->par_list,
			                      sync_global_trans_cell->action->info.action->rate_index,
			                      adjusted_priority_bucket,
			                      normalized_rate_bucket);
		        }
		      }
		    }
		}
	      }
	    }
	    /* make the pointers advance */
	    if (aux_global_trans_cell->action->info.action->rate_index != PASSIVE)
	    {
	      if (curr_global_trans_cell == prev_global_trans_cell)
	        curr_global_trans_cell = prev_global_trans_cell = global_trans_list =
		  curr_global_trans_cell->next_g_trans_cell;
	      else
	        prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell =
		  curr_global_trans_cell->next_g_trans_cell;
	      free((LST_BUCKET **)(aux_global_trans_cell->der_global_state));
	      free_g_trans_cell(aux_global_trans_cell);
	    }
	    else
	    {
	      prev_global_trans_cell = curr_global_trans_cell;
	      curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	    }
	  }
	}

	/* handle the right asynchronous transitions and the right-to-left synchronizations */
	for (local_trans_cell = local_trans_list;
	     (local_trans_cell != NULL);
	     local_trans_cell = local_trans_cell->next_l_trans_cell)
	  if (local_trans_cell->or_marked != LOW_PRIORITY_PASSIVE_OR)
	  {
	    if (!check_list_membership(local_trans_cell->action->info.action->type,
				       aei->info.aei->sync_set,
				       FALSE))
	    {
	      /* generate a right asynchronous transition */
	      der_local_state_vector =
	        (LST_BUCKET **)new_calloc(archi_type[spec_no]->info.archi_type->aei_num,
					  sizeof(LST_BUCKET *));
	      for (i = aei->info.aei->aei_no - 2;
		   (i >= 0);
		   i--)
	        der_local_state_vector[i] = starting_global_state->local_state_vector[i];
	      der_local_state_vector[aei->info.aei->aei_no - 1] = local_trans_cell->der_local_state;
	      for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
		   (i >= aei->info.aei->aei_no);
		   i--)
	        der_local_state_vector[i] = NULL;
	      if (last_new_global_trans_cell == NULL)
	        last_new_global_trans_cell = starting_global_state->global_trans_list =
	          alloc_g_trans_cell(local_trans_cell->action,
				     (archi_type[spec_no]->info.archi_type->value_passing !=
				        (char)SYMBOLIC_VP)?
				       NULL:
				       alloc_vp_info(local_trans_cell->value_passing_info->guard,
				                     duplicate_st_bucket_list(local_trans_cell->
							     			value_passing_info->
										assign_list),
				         	     local_trans_cell->value_passing_info->
						       input_output_assign_num),
				     duplicate_reward_info(local_trans_cell->reward_info),
				     (GST_BUCKET *)der_local_state_vector,
				     NULL);
	      else
	        last_new_global_trans_cell = last_new_global_trans_cell->next_g_trans_cell =
	          alloc_g_trans_cell(local_trans_cell->action,
				     (archi_type[spec_no]->info.archi_type->value_passing !=
				        (char)SYMBOLIC_VP)?
				       NULL:
				       alloc_vp_info(local_trans_cell->value_passing_info->guard,
				         	     duplicate_st_bucket_list(local_trans_cell->
							     			value_passing_info->
										assign_list),
				         	     local_trans_cell->value_passing_info->
						       input_output_assign_num),
				     duplicate_reward_info(local_trans_cell->reward_info),
				     (GST_BUCKET *)der_local_state_vector,
				     NULL);
	    }
	    else
	    {
	      /* look for left transitions matching the right transition */
	      for (global_trans_cell = global_trans_list,
		   corr_passive_trans_num = 0,
		   corr_aggregate_passive_weight = 0.0L,
		   first_sync_new_global_trans_cell = NULL;
		   (global_trans_cell != NULL);
		   global_trans_cell = global_trans_cell->next_g_trans_cell)
	        if ((local_trans_cell->action->info.action->type ==
		       global_trans_cell->action->info.action->type) &&
		    (((local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		      (global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		      (local_trans_cell->action->info.action->rate_index != PASSIVE) &&
		      (global_trans_cell->action->info.action->rate_index == PASSIVE)) ||
		     ((local_trans_cell->action->info.action->action_index == OUTPUT) &&
		      (global_trans_cell->action->info.action->action_index == INPUT))) &&
		    ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		     check_lists_equality(local_trans_cell->action->info.action->par_list,
					  global_trans_cell->action->info.action->par_list)))
	        {
	          /* update the information about the matching left transitions */
		  corr_passive_trans_num++;
	          if ((option_index != SIMULATION) ||
		      (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		      global_trans_cell->value_passing_info->guard->info.expr->value)
		    corr_aggregate_passive_weight +=
		      global_trans_cell->action->info.action->rate->info.expr->value;
		  /* compute the value passing related information about the right-to-left */
		  /* synchronization: */
		  /* - guards must be joined; */
		  /* - synchronization assignments must precede unfolding assignments */
		  if (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP)
		    value_passing_info = NULL;
		  else
		  {
	            value_passing_info =
		      alloc_vp_info((!strcmp(local_trans_cell->value_passing_info->guard->symbol_lexeme,
					     "true"))?
				      global_trans_cell->value_passing_info->guard:
				      ((!strcmp(global_trans_cell->value_passing_info->guard->symbol_lexeme,
					        "true"))?
				         local_trans_cell->value_passing_info->guard:
				         set_expr_bucket(AND_OP,
					   		 global_trans_cell->value_passing_info->guard,
					   		 local_trans_cell->value_passing_info->guard,
					   		 NULL,
					   		 0.0L,
					   		 NULL,
					   		 FALSE)),
				    merge_assign_lists(global_trans_cell->value_passing_info,
						       local_trans_cell->value_passing_info),
				    local_trans_cell->value_passing_info->input_output_assign_num +
				      global_trans_cell->value_passing_info->input_output_assign_num);
		    if (option_index == SIMULATION)
		      value_passing_info->guard->info.expr->value =
		        (long double)(global_trans_cell->value_passing_info->guard->info.expr->value &&
				      local_trans_cell->value_passing_info->guard->info.expr->value);
		    if (global_trans_cell->action->info.action->action_index == INPUT)
		      insert_par_assign_list(value_passing_info,
					     global_trans_cell->action->info.action->par_list,
					     local_trans_cell->action->info.action->par_list);
		  }
		  /* compute the derivative state of the right-to-left synchronization */
	          der_local_state_vector =
	            (LST_BUCKET **)new_calloc(archi_type[spec_no]->info.archi_type->aei_num,
					      sizeof(LST_BUCKET *));
	          for (i = aei->info.aei->aei_no - 2;
		       (i >= 0);
		       i--)
	            der_local_state_vector[i] = ((LST_BUCKET **)(global_trans_cell->der_global_state))[i];
	          der_local_state_vector[aei->info.aei->aei_no - 1] = local_trans_cell->der_local_state;
	          for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
		       (i >= aei->info.aei->aei_no);
		       i--)
	            der_local_state_vector[i] = NULL;
		  /* generate the right-to-left synchronization */
	          if (last_new_global_trans_cell == NULL)
	            last_new_global_trans_cell = starting_global_state->global_trans_list =
		      alloc_g_trans_cell(((local_trans_cell->action->info.action->rate_index != PASSIVE) ||
				          (local_trans_cell->or_marked != HIGH_PRIORITY_PASSIVE_OR))?
				           local_trans_cell->action:
				           global_trans_cell->action,
				         value_passing_info,
				         duplicate_reward_info(local_trans_cell->reward_info),
				         (GST_BUCKET *)der_local_state_vector,
				         NULL);
	          else
	            last_new_global_trans_cell = last_new_global_trans_cell->next_g_trans_cell =
		      alloc_g_trans_cell(((local_trans_cell->action->info.action->rate_index != PASSIVE) ||
				          (local_trans_cell->or_marked != HIGH_PRIORITY_PASSIVE_OR))?
				           local_trans_cell->action:
				           global_trans_cell->action,
				         value_passing_info,
				         duplicate_reward_info(local_trans_cell->reward_info),
				         (GST_BUCKET *)der_local_state_vector,
				         NULL);
		  if (first_sync_new_global_trans_cell == NULL)
		    first_sync_new_global_trans_cell = last_new_global_trans_cell;
	        }
	      /* normalize the rates and the yield rewards of the right-to-left synchronizations; this is */
	      /* postponed in the case of right-to-left synchronizations deriving from an or-interaction */
	      if ((corr_passive_trans_num > 0) &&
		  (local_trans_cell->action->info.action->type->info.act_type->interaction_index !=
		     SYNC_OR))
	      {
	        if (local_trans_cell->action->info.action->rate_index != PASSIVE)
	        {
	          /* handle the normalization for the right-to-left reactive-generative synchronizations */
		  /* involving the right transition, considering them one at a time */
	          if (corr_passive_trans_num > 1)
	            for (sync_global_trans_cell = first_sync_new_global_trans_cell,
			 global_trans_cell = global_trans_list,
		         corr_passive_trans_no = 1;
		         (corr_passive_trans_no <= corr_passive_trans_num);
		         corr_passive_trans_no++,
		         sync_global_trans_cell = sync_global_trans_cell->next_g_trans_cell)
		    {
	              for (found = FALSE;
		           (!found);
		           global_trans_cell = global_trans_cell->next_g_trans_cell)
	                if ((found =
			      ((local_trans_cell->action->info.action->type ==
		                  global_trans_cell->action->info.action->type) &&
		               (((local_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		                 (global_trans_cell->action->info.action->action_index == UNSTRUCTURED) &&
		                 (global_trans_cell->action->info.action->rate_index == PASSIVE)) ||
		                ((local_trans_cell->action->info.action->action_index == OUTPUT) &&
		                 (global_trans_cell->action->info.action->action_index == INPUT))) &&
		               ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		                check_lists_equality(local_trans_cell->action->info.action->par_list,
					             global_trans_cell->action->info.action->par_list)) &&
			       check_global_state_eq((LST_BUCKET **)(sync_global_trans_cell->
								       der_global_state),
						     (LST_BUCKET **)(global_trans_cell->der_global_state),
						     aei->info.aei->aei_no - 1))))
	                {
			  /* update the normalization flag for the starting global state */
		          if ((option_index == SIMULATION) &&
			      (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			      strcmp(global_trans_cell->value_passing_info->guard->symbol_lexeme,
				     "true"))
			    starting_global_state->global_trans_need_norm = TRUE;
	                  if ((option_index != SIMULATION) ||
		              (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		              sync_global_trans_cell->value_passing_info->guard->info.expr->value)
			  {
		            /* normalize the rate of the right-to-left reactive-generative synchronization */
		            normalized_rate =
			      local_trans_cell->action->info.action->rate->info.expr->value *
			      (global_trans_cell->action->info.action->rate->info.expr->value /
			       corr_aggregate_passive_weight);
	                    length = compute_digit_num(normalized_rate) +
		                     F_FORMAT_PRECIS_LENGTH;
	                    if (length > max_length)
	                    {
  	                      if (normalized_rate_lexeme != NULL)
    	                        free(normalized_rate_lexeme);
  	                      normalized_rate_lexeme = alloc_string(length);
  	                      max_length = length;
	                    }
	                    sprintf(normalized_rate_lexeme,
		                    "%f",
		                    (double)normalized_rate);
		            sync_global_trans_cell->action =
			      set_action_bucket(sync_global_trans_cell->action->info.action->type,
					        sync_global_trans_cell->action->info.action->action_index,
					        sync_global_trans_cell->action->info.action->par_list,
					        sync_global_trans_cell->action->info.action->rate_index,
					        sync_global_trans_cell->action->info.action->priority,
					        set_number_bucket(normalized_rate_lexeme));
		            /* normalize the yield rewards of the right-to-left reactive-generative */
			    /* synchronization */
			    if (option_index != SIMULATION)
                              for (yb_cell = sync_global_trans_cell->reward_info.yb_list;
                                   (yb_cell != NULL);
                                   yb_cell = yb_cell->next_yb_cell)
			        yb_cell->yield *=
			          global_trans_cell->action->info.action->rate->info.expr->value /
			          corr_aggregate_passive_weight;
			  }
	                }
		    }
	        }
	        else
	        {
	          /* handle the normalization for the right-to-left input-output reactive-reactive */
		  /* synchronizations involving the right transition; start with the computation of the */
		  /* total passive weight of the right process */
	          for (aux_local_trans_cell = local_trans_list,
		       aggregate_passive_weight = 0.0L;
		       (aux_local_trans_cell != NULL);
		       aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	            if ((aux_local_trans_cell->action->info.action->type ==
		           local_trans_cell->action->info.action->type) &&
		        ((aux_local_trans_cell->action->info.action->action_index ==
		            local_trans_cell->action->info.action->action_index) ||
		         ((aux_local_trans_cell->action->info.action->action_index == INPUT) &&
		          (local_trans_cell->action->info.action->action_index == OUTPUT))) &&
		        ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		         check_lists_equality(aux_local_trans_cell->action->info.action->par_list,
					      local_trans_cell->action->info.action->par_list)) &&
		        (aux_local_trans_cell->action->info.action->priority->info.expr->value ==
		           local_trans_cell->action->info.action->priority->info.expr->value))
		    {
		      if ((aux_local_trans_cell != local_trans_cell) &&
			  (option_index == SIMULATION) &&
			  (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			  strcmp(aux_local_trans_cell->value_passing_info->guard->symbol_lexeme,
			         "true"))
		        starting_global_state->global_trans_need_norm = TRUE;
	              if ((option_index != SIMULATION) ||
		          (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		          aux_local_trans_cell->value_passing_info->guard->info.expr->value)
		        aggregate_passive_weight +=
		          aux_local_trans_cell->action->info.action->rate->info.expr->value;
		    }
		  /* consider one right-to-left input-output reactive-reactive synchronization at a time */
	          for (sync_global_trans_cell = first_sync_new_global_trans_cell,
		       global_trans_cell = global_trans_list,
		       corr_passive_trans_no = 1;
		       (corr_passive_trans_no <= corr_passive_trans_num);
		       corr_passive_trans_no++,
		       sync_global_trans_cell = sync_global_trans_cell->next_g_trans_cell)
		  {
	            for (found = FALSE;
		         (!found);
		         global_trans_cell = global_trans_cell->next_g_trans_cell)
	              if ((found =
			    ((local_trans_cell->action->info.action->type ==
		                global_trans_cell->action->info.action->type) &&
		             (local_trans_cell->action->info.action->action_index == OUTPUT) &&
		             (global_trans_cell->action->info.action->action_index == INPUT) &&
		             ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		              check_lists_equality(local_trans_cell->action->info.action->par_list,
					           global_trans_cell->action->info.action->par_list)) &&
			     check_global_state_eq((LST_BUCKET **)(sync_global_trans_cell->der_global_state),
					           (LST_BUCKET **)(global_trans_cell->der_global_state),
					           aei->info.aei->aei_no - 1))))
		      {
		        /* update the normalization flag for the starting global state */
		        if ((corr_passive_trans_num > 1) &&
			    (option_index == SIMULATION) &&
			    (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			    strcmp(global_trans_cell->value_passing_info->guard->symbol_lexeme,
				   "true"))
			  starting_global_state->global_trans_need_norm = TRUE;
	                if ((option_index != SIMULATION) ||
		            (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		            sync_global_trans_cell->value_passing_info->guard->info.expr->value)
			{
		          /* adjust the priority and normalize the rate of the right-to-left input-output */
       			  /* reactive-reactive synchronization */
		          adjusted_priority =
		            (local_trans_cell->action->info.action->priority->info.expr->value >=
			       global_trans_cell->action->info.action->priority->info.expr->value)?
			      local_trans_cell->action->info.action->priority->info.expr->value:
			      global_trans_cell->action->info.action->priority->info.expr->value;
		          normalized_rate =
		            (local_trans_cell->action->info.action->rate->info.expr->value /
		             aggregate_passive_weight) *
		            (global_trans_cell->action->info.action->rate->info.expr->value /
		             corr_aggregate_passive_weight) *
		             ((local_trans_cell->action->info.action->priority->info.expr->value ==
			         global_trans_cell->action->info.action->priority->info.expr->value)?
			        (aggregate_passive_weight + corr_aggregate_passive_weight):
			        ((local_trans_cell->action->info.action->priority->info.expr->value >
			            global_trans_cell->action->info.action->priority->info.expr->value)?
			           aggregate_passive_weight:
			           corr_aggregate_passive_weight));
		          if ((sync_global_trans_cell->action->info.action->priority->info.expr->value !=
			         adjusted_priority) ||
			      (sync_global_trans_cell->action->info.action->rate->info.expr->value !=
			         normalized_rate))
		          {
			    adjusted_priority_bucket =
		              (local_trans_cell->action->info.action->priority->info.expr->value >=
			         global_trans_cell->action->info.action->priority->info.expr->value)?
			        local_trans_cell->action->info.action->priority:
			        global_trans_cell->action->info.action->priority;
			    if (sync_global_trans_cell->action->info.action->rate->info.expr->value ==
			          normalized_rate)
			      normalized_rate_bucket = sync_global_trans_cell->action->info.action->rate;
			    else
			    {
	                      length = compute_digit_num(normalized_rate) +
		                       F_FORMAT_PRECIS_LENGTH;
	                      if (length > max_length)
	                      {
  	                        if (normalized_rate_lexeme != NULL)
    	                          free(normalized_rate_lexeme);
  	                        normalized_rate_lexeme = alloc_string(length);
  	                        max_length = length;
	                      }
	                      sprintf(normalized_rate_lexeme,
		                      "%f",
		                      (double)normalized_rate);
			      normalized_rate_bucket = set_number_bucket(normalized_rate_lexeme);
			    }
		            sync_global_trans_cell->action =
		              set_action_bucket(sync_global_trans_cell->action->info.action->type,
			                        sync_global_trans_cell->action->info.action->action_index,
			                        sync_global_trans_cell->action->info.action->par_list,
			                        sync_global_trans_cell->action->info.action->rate_index,
			                        adjusted_priority_bucket,
			                        normalized_rate_bucket);
		          }
			}
		      }
		  }
		}
	      }
	    }
	  }

	/* dispose of the remaining cells of the left transition list */
	for (global_trans_cell = global_trans_list,
             next_global_trans_cell = (global_trans_list != NULL)?
					global_trans_list->next_g_trans_cell:
					NULL;
             (global_trans_cell != NULL);
             global_trans_cell = next_global_trans_cell,
             next_global_trans_cell = (next_global_trans_cell != NULL)?
					next_global_trans_cell->next_g_trans_cell:
					NULL)
	{
	  free((LST_BUCKET **)(global_trans_cell->der_global_state));
	  free_g_trans_cell(global_trans_cell);
	}
}


ST_BUCKET_CELL	*merge_assign_lists(VP_INFO *value_passing_info1,
				    VP_INFO *value_passing_info2)
{
	ST_BUCKET_CELL	*assign_list,
			*assign_cell,
			*assign_cell1,
			*assign_cell2;
	int		i;

	/* copy the synchronization assignments of the first list */
	for (assign_cell = assign_list = NULL,
	     assign_cell1 = value_passing_info1->assign_list,
	     i = 0;
	     (i < value_passing_info1->input_output_assign_num);
	     i++,
	     assign_cell1 = assign_cell1->next_st_bucket_cell)
	  if (assign_list == NULL)
	    assign_list = assign_cell = alloc_st_bucket_cell(assign_cell1->st_bucket,
							     NULL);
	  else
	    assign_cell = assign_cell->next_st_bucket_cell = alloc_st_bucket_cell(assign_cell1->st_bucket,
										  NULL);

	/* copy the synchronization assignments of the second list */
	for (assign_cell2 = value_passing_info2->assign_list,
	     i = 0;
	     (i < value_passing_info2->input_output_assign_num);
	     i++,
	     assign_cell2 = assign_cell2->next_st_bucket_cell)
	  if (assign_list == NULL)
	    assign_list = assign_cell = alloc_st_bucket_cell(assign_cell2->st_bucket,
							     NULL);
	  else
	    assign_cell = assign_cell->next_st_bucket_cell = alloc_st_bucket_cell(assign_cell2->st_bucket,
										  NULL);

	/* copy the unfolding assignments of the first list */
	for (;
	     (assign_cell1 != NULL);
	     assign_cell1 = assign_cell1->next_st_bucket_cell)
	  if (assign_list == NULL)
	    assign_list = assign_cell = alloc_st_bucket_cell(assign_cell1->st_bucket,
							     NULL);
	  else
	    assign_cell = assign_cell->next_st_bucket_cell = alloc_st_bucket_cell(assign_cell1->st_bucket,
										  NULL);

	/* copy the unfolding assignments of the second list */
	for (;
	     (assign_cell2 != NULL);
	     assign_cell2 = assign_cell2->next_st_bucket_cell)
	  if (assign_list == NULL)
	    assign_list = assign_cell = alloc_st_bucket_cell(assign_cell2->st_bucket,
							     NULL);
	  else
	    assign_cell = assign_cell->next_st_bucket_cell = alloc_st_bucket_cell(assign_cell2->st_bucket,
										  NULL);
	return(assign_list);
}


void            insert_par_assign_list(VP_INFO        *value_passing_info,
                                       ST_BUCKET_CELL *formal_par_list,
                                       ST_BUCKET_CELL *actual_par_list)
{
	ST_BUCKET_CELL	*assign_list,
			*last_assign_cell,
			*formal_par_cell,
			*actual_par_cell;

        for (formal_par_cell = formal_par_list,
	     actual_par_cell = actual_par_list,
	     last_assign_cell = assign_list = NULL;
             (formal_par_cell != NULL);
             formal_par_cell = formal_par_cell->next_st_bucket_cell,
             actual_par_cell = actual_par_cell->next_st_bucket_cell)
	{
	  value_passing_info->input_output_assign_num++;
	  if (last_assign_cell == NULL)
	    last_assign_cell = assign_list = alloc_st_bucket_cell(set_expr_bucket(ASSIGN_OP,
                                                                                  formal_par_cell->st_bucket,
                                                                                  actual_par_cell->st_bucket,
                                                                                  NULL,
                                                                                  0.0L,
                                                                                  NULL,
                                                                                  FALSE),
                                                                  NULL);
	  else
	    last_assign_cell = last_assign_cell->next_st_bucket_cell =
	      alloc_st_bucket_cell(set_expr_bucket(ASSIGN_OP,
                                                   formal_par_cell->st_bucket,
                                                   actual_par_cell->st_bucket,
                                                   NULL,
                                                   0.0L,
                                                   NULL,
                                                   FALSE),
                                                   NULL);
	}
	last_assign_cell->next_st_bucket_cell = value_passing_info->assign_list;
	value_passing_info->assign_list = assign_list;
}


void		select_passive_global_transitions(G_TRANS_CELL **global_trans_list)
{
	G_TRANS_CELL	*curr_global_trans_cell,
			*prev_global_trans_cell,
			*global_trans_cell;
	double		max_priority;

	for (curr_global_trans_cell = prev_global_trans_cell = *global_trans_list;
	     (curr_global_trans_cell != NULL);
	     )
	  if (curr_global_trans_cell->action->info.action->rate_index != PASSIVE)
	  {
	    prev_global_trans_cell = curr_global_trans_cell;
	    curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	  }
	  else
	  {
	    for (global_trans_cell = *global_trans_list,
		 max_priority = curr_global_trans_cell->action->info.action->priority->info.expr->value;
	         (global_trans_cell != NULL);
		 global_trans_cell = global_trans_cell->next_g_trans_cell)
	      if ((global_trans_cell->action->info.action->type ==
		     curr_global_trans_cell->action->info.action->type) &&
		  (global_trans_cell->action->info.action->action_index ==
		     curr_global_trans_cell->action->info.action->action_index) &&
		  (global_trans_cell->action->info.action->rate_index == PASSIVE) &&
		  ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		   (global_trans_cell->value_passing_info->guard ==
		      curr_global_trans_cell->value_passing_info->guard)) &&
		  (global_trans_cell->action->info.action->priority->info.expr->value > max_priority))
		max_priority = global_trans_cell->action->info.action->priority->info.expr->value;
	    if (curr_global_trans_cell->action->info.action->priority->info.expr->value == max_priority)
	    {
	      prev_global_trans_cell = curr_global_trans_cell;
	      curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	    }
	    else
	      if (curr_global_trans_cell == prev_global_trans_cell)
	      {
		*global_trans_list = (*global_trans_list)->next_g_trans_cell;
	        free_g_trans_cell(curr_global_trans_cell);
		curr_global_trans_cell = prev_global_trans_cell = *global_trans_list;
	      }
	      else
	      {
	        curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	        free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
	        prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
	      }
	  }
}


void		unmark_passive_or_local_transitions(GST_BUCKET *global_state)
{
	L_TRANS_CELL	*local_trans_cell;
	int		i;

	for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
	     (i >= 0);
	     i--)
	  for (local_trans_cell = global_state->local_state_vector[i]->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    if ((local_trans_cell->or_marked == HIGH_PRIORITY_PASSIVE_OR) ||
		(local_trans_cell->or_marked == LOW_PRIORITY_PASSIVE_OR))
	      local_trans_cell->or_marked = NO_OR;
}


void		normalize_or_global_transitions(GST_BUCKET *global_state)
{
		BOOLEAN		found,
				others_in_same_aei;
		G_TRANS_CELL	*global_trans_cell,
				*aux_global_trans_cell;
		L_TRANS_CELL	*local_trans_cell,
				*or_sync_local_trans_cell,
				*aux_local_trans_cell;
		ST_BUCKET	*adjusted_priority;
		YB_CELL		*yb_cell;
	static	char		*normalized_rate_lexeme		=	NULL;
		int		i,
				or_dupl_aei_no,
				corr_passive_local_trans_num,
				length;
	static	int		max_length			=	0;
		long double	corr_aggregate_passive_weight,
				dupl_aggregate_passive_weight,
				normalized_rate;

	for (global_trans_cell = global_state->global_trans_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  if (strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
		     "invisible") &&
	      (global_trans_cell->action->info.action->type->info.act_type->interaction_index == SYNC_OR))
	  {
	    /* look for correlate global transitions among the preceding global transitions */
	    for (aux_global_trans_cell = global_state->global_trans_list,
	         found = FALSE;
	         ((aux_global_trans_cell != global_trans_cell) &&
		  !found);
	         aux_global_trans_cell = aux_global_trans_cell->next_g_trans_cell)
	      found = check_list_membership(aux_global_trans_cell->action->info.action->type,
		        		    global_trans_cell->action->info.action->type->info.act_type->
					      duplicate_list,
					    FALSE);
	    if (!found)
	    {
	      /* identify the AEI participating in the correlated global transitions with the duplicates */
	      /* of the or-interaction */
	      for (or_dupl_aei_no = archi_type[spec_no]->info.archi_type->aei_num - 1,
		   found = FALSE;
		   (!found);
		   )
	      {
	        for (local_trans_cell = global_state->local_state_vector[or_dupl_aei_no]->local_trans_list;
		     ((local_trans_cell != NULL) &&
		      !found);
		     local_trans_cell = local_trans_cell->next_l_trans_cell)
		  found = ((local_trans_cell->or_marked == DUPLICATE_OR) &&
		           (local_trans_cell->action->info.action->type ==
			      global_trans_cell->action->info.action->type) &&
		           ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		            check_lists_equality(local_trans_cell->action->info.action->par_list,
					         global_trans_cell->action->info.action->par_list)) &&
		           (local_trans_cell->der_local_state ==
			      global_trans_cell->der_global_state->local_state_vector[or_dupl_aei_no]));
	        if (!found)
		  or_dupl_aei_no--;
	      }
	      /* determine the number of correlated passive local transitions synchronizing with the */
	      /* duplicates of the or-interaction as well as their aggregate weight */
	      for (i = archi_type[spec_no]->info.archi_type->aei_num - 1,
		   corr_passive_local_trans_num = 0,
		   corr_aggregate_passive_weight = 0.0L;
		   (i >= 0);
		   i--)
	        for (local_trans_cell = global_state->local_state_vector[i]->local_trans_list;
		     (local_trans_cell != NULL);
		     local_trans_cell = local_trans_cell->next_l_trans_cell)
		  if (check_list_membership(local_trans_cell->action->info.action->type,
		        		    global_trans_cell->action->info.action->type->info.act_type->
					      duplicate_list,
					    FALSE) &&
		      (local_trans_cell->or_marked == HIGH_PRIORITY_PASSIVE_OR))
		  {
		    corr_passive_local_trans_num++;
		    if ((option_index != SIMULATION) ||
	                (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
	                local_trans_cell->value_passing_info->guard->info.expr->value)
		      corr_aggregate_passive_weight +=
		        local_trans_cell->action->info.action->rate->info.expr->value;
		  }
	      if ((corr_passive_local_trans_num > 1) ||
		  (global_trans_cell->action->info.action->rate_index == PASSIVE))
	        for (aux_global_trans_cell = global_trans_cell,
		     others_in_same_aei = FALSE,
		     local_trans_cell = NULL;
		     (aux_global_trans_cell != NULL);
		     aux_global_trans_cell = aux_global_trans_cell->next_g_trans_cell)
	          if (check_list_membership(aux_global_trans_cell->action->info.action->type,
		        		    global_trans_cell->action->info.action->type->info.act_type->
					      duplicate_list,
					    FALSE))
		  {
	            /* identify the passive local transition synchronizing with the duplicate of the */
	            /* or-interaction in the correlated global transition under consideration */
		    if (others_in_same_aei)
		    {
		      or_sync_local_trans_cell = local_trans_cell;
		      others_in_same_aei = FALSE;
		    }
		    else
		      for (i = 0,
		           or_sync_local_trans_cell = NULL;
		           (or_sync_local_trans_cell == NULL);
		           i++)
	                for (local_trans_cell = global_state->local_state_vector[i]->local_trans_list;
		             ((local_trans_cell != NULL) &&
		              (or_sync_local_trans_cell == NULL));
		             local_trans_cell = local_trans_cell->next_l_trans_cell)
		          if ((local_trans_cell->or_marked == HIGH_PRIORITY_PASSIVE_OR) &&
		              (local_trans_cell->action->info.action->type ==
		                 aux_global_trans_cell->action->info.action->type) &&
		              ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		               check_lists_equality(local_trans_cell->action->info.action->par_list,
					            aux_global_trans_cell->action->info.action->par_list)) &&
		              (local_trans_cell->der_local_state ==
		                 aux_global_trans_cell->der_global_state->local_state_vector[i]))
		            or_sync_local_trans_cell = local_trans_cell;
	            /* check whether there are other passive local transitions synchronizing with the */
		    /* duplicate of the or-interaction and having the same derivative local state */
		    while ((local_trans_cell != NULL) &&
			   !others_in_same_aei)
		      if ((local_trans_cell->or_marked == HIGH_PRIORITY_PASSIVE_OR) &&
		          (local_trans_cell->action->info.action->type ==
		             aux_global_trans_cell->action->info.action->type) &&
		          ((archi_type[spec_no]->info.archi_type->value_passing != (char)CONCRETE_VP) ||
		           check_lists_equality(local_trans_cell->action->info.action->par_list,
					        aux_global_trans_cell->action->info.action->par_list)) &&
		          (local_trans_cell->der_local_state ==
		             aux_global_trans_cell->der_global_state->local_state_vector[i]))
			others_in_same_aei = TRUE;
		      else
			local_trans_cell = local_trans_cell->next_l_trans_cell;
		    /* carry out the normalization */
		    if (global_trans_cell->action->info.action->rate_index != PASSIVE)
		    {
		      /* update the normalization flag for the global state */
		      if ((option_index == SIMULATION) &&
			  (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			  strcmp(or_sync_local_trans_cell->value_passing_info->guard->symbol_lexeme,
				 "true"))
			global_state->global_trans_need_norm = TRUE;
	              if ((option_index != SIMULATION) ||
		          (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		          aux_global_trans_cell->value_passing_info->guard->info.expr->value)
		      {
		        /* normalize the rate of the generative-reactive synchronization */
		        normalized_rate =
		          aux_global_trans_cell->action->info.action->rate->info.expr->value *
		          (or_sync_local_trans_cell->action->info.action->rate->info.expr->value /
			     corr_aggregate_passive_weight);
	                length = compute_digit_num(normalized_rate) +
		                 F_FORMAT_PRECIS_LENGTH;
	                if (length > max_length)
	                {
  	                  if (normalized_rate_lexeme != NULL)
    	                    free(normalized_rate_lexeme);
  	                  normalized_rate_lexeme = alloc_string(length);
  	                  max_length = length;
	                }
	                sprintf(normalized_rate_lexeme,
		                "%f",
		                (double)normalized_rate);
		        aux_global_trans_cell->action =
		          set_action_bucket(aux_global_trans_cell->action->info.action->type,
					    aux_global_trans_cell->action->info.action->action_index,
					    aux_global_trans_cell->action->info.action->par_list,
					    aux_global_trans_cell->action->info.action->rate_index,
					    aux_global_trans_cell->action->info.action->priority,
					    set_number_bucket(normalized_rate_lexeme));
		        /* normalize the yield rewards of the generative-reactive synchronization */
			if (option_index != SIMULATION)
                          for (yb_cell = aux_global_trans_cell->reward_info.yb_list;
                               (yb_cell != NULL);
                               yb_cell = yb_cell->next_yb_cell)
			    yb_cell->yield *=
		              or_sync_local_trans_cell->action->info.action->rate->info.expr->value /
			        corr_aggregate_passive_weight;
		      }
		    }
	            else
		    {
	              /* determine the aggregate weight of the copies of the duplicate of the passive */
		      /* or-interaction */
	              for (aux_local_trans_cell =
			     global_state->local_state_vector[or_dupl_aei_no]->local_trans_list,
		           dupl_aggregate_passive_weight = 0.0L;
		           (aux_local_trans_cell != NULL);
		           aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
		        if (aux_local_trans_cell->action->info.action->type ==
		              aux_global_trans_cell->action->info.action->type)
			{
		          if ((aux_local_trans_cell != or_sync_local_trans_cell) &&
			      (option_index == SIMULATION) &&
			      (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			      strcmp(aux_local_trans_cell->value_passing_info->guard->symbol_lexeme,
			             "true"))
		            global_state->global_trans_need_norm = TRUE;
	                  if ((option_index != SIMULATION) ||
		              (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		              aux_local_trans_cell->value_passing_info->guard->info.expr->value)
		            dupl_aggregate_passive_weight +=
		              aux_local_trans_cell->action->info.action->rate->info.expr->value;
			}
		      /* update the normalization flag for the global state */
		      if ((corr_passive_local_trans_num > 1) &&
			  (option_index == SIMULATION) &&
			  (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP) &&
			  strcmp(or_sync_local_trans_cell->value_passing_info->guard->symbol_lexeme,
				 "true"))
			global_state->global_trans_need_norm = TRUE;
	              if ((option_index != SIMULATION) ||
		          (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		          aux_global_trans_cell->value_passing_info->guard->info.expr->value)
		      {
	                /* adjust the priority level */
		        adjusted_priority =
			  (aux_global_trans_cell->action->info.action->priority->info.expr->value >=
			     or_sync_local_trans_cell->action->info.action->priority->info.expr->value)?
			    aux_global_trans_cell->action->info.action->priority:
			    or_sync_local_trans_cell->action->info.action->priority;
	                /* normalize the rate */
	                normalized_rate = 
		          (aux_global_trans_cell->action->info.action->rate->info.expr->value /
		             dupl_aggregate_passive_weight) *
		          (or_sync_local_trans_cell->action->info.action->rate->info.expr->value /
			     corr_aggregate_passive_weight) *
		          ((aux_global_trans_cell->action->info.action->priority->info.expr->value ==
			      or_sync_local_trans_cell->action->info.action->priority->info.expr->value)?
		             (dupl_aggregate_passive_weight + corr_aggregate_passive_weight):
			     ((aux_global_trans_cell->action->info.action->priority->info.expr->value >
			         or_sync_local_trans_cell->action->info.action->priority->info.expr->value)?
			        dupl_aggregate_passive_weight:
			        corr_aggregate_passive_weight));
		        if ((aux_global_trans_cell->action->info.action->priority->info.expr->value !=
			       adjusted_priority->info.expr->value) ||
			    (aux_global_trans_cell->action->info.action->rate->info.expr->value !=
			       normalized_rate))
		        {
	                  length = compute_digit_num(normalized_rate) +
		                   F_FORMAT_PRECIS_LENGTH;
	                  if (length > max_length)
	                  {
  	                    if (normalized_rate_lexeme != NULL)
    	                      free(normalized_rate_lexeme);
  	                    normalized_rate_lexeme = alloc_string(length);
  	                    max_length = length;
	                  }
	                  sprintf(normalized_rate_lexeme,
		                  "%f",
		                  (double)normalized_rate);
		          aux_global_trans_cell->action =
		            set_action_bucket(aux_global_trans_cell->action->info.action->type,
					      aux_global_trans_cell->action->info.action->action_index,
					      aux_global_trans_cell->action->info.action->par_list,
					      aux_global_trans_cell->action->info.action->rate_index,
					      adjusted_priority,
					      set_number_bucket(normalized_rate_lexeme));
		        }
		      }
		    }
		  }
	    }
	  }
}


void		select_global_transitions(GST_BUCKET *global_state)
{
	G_TRANS_CELL	*global_trans_cell,
			*curr_global_trans_cell,
			*prev_global_trans_cell;
	long double	max_priority;

	for (curr_global_trans_cell = prev_global_trans_cell = global_state->global_trans_list;
	     (curr_global_trans_cell != NULL);
	     )
	  if (curr_global_trans_cell->action->info.action->rate_index == PASSIVE)
	  {
	    prev_global_trans_cell = curr_global_trans_cell;
	    curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	  }
	  else
	  {
	    for (global_trans_cell = global_state->global_trans_list,
		 max_priority = curr_global_trans_cell->action->info.action->priority->info.expr->value;
	         (global_trans_cell != NULL);
		 global_trans_cell = global_trans_cell->next_g_trans_cell)
	      if ((global_trans_cell->action->info.action->rate_index != PASSIVE) &&
		  ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
		   (global_trans_cell->value_passing_info->guard ==
		      curr_global_trans_cell->value_passing_info->guard) ||
		   !strcmp(global_trans_cell->value_passing_info->guard->symbol_lexeme,
			   "true")) &&
		  (global_trans_cell->action->info.action->priority->info.expr->value > max_priority))
		max_priority = global_trans_cell->action->info.action->priority->info.expr->value;
	    if (curr_global_trans_cell->action->info.action->priority->info.expr->value == max_priority)
	    {
	      prev_global_trans_cell = curr_global_trans_cell;
	      curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	    }
	    else
	      if (curr_global_trans_cell == prev_global_trans_cell)
	      {
		global_state->global_trans_list = global_state->global_trans_list->next_g_trans_cell;
	        free_g_trans_cell(curr_global_trans_cell);
		curr_global_trans_cell = prev_global_trans_cell = global_state->global_trans_list;
	      }
	      else
	      {
	        curr_global_trans_cell = curr_global_trans_cell->next_g_trans_cell;
	        free_g_trans_cell(prev_global_trans_cell->next_g_trans_cell);
	        prev_global_trans_cell->next_g_trans_cell = curr_global_trans_cell;
	      }
	  }
}


BOOLEAN		hide_rename_global_transitions(G_TRANS_CELL *global_trans_list)
{
	BOOLEAN		passive_hidden_renamed;
	G_TRANS_CELL	*global_trans_cell;

	for (global_trans_cell = global_trans_list,
	     passive_hidden_renamed = FALSE;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  if ((global_trans_cell->action->info.action->type->info.act_type->variation == HIDDEN) ||
	      ((option_index == NON_INTERFERENCE_ANALYSIS) &&
	       (spec_no == 0) &&
	       (global_trans_cell->action->info.action->type->info.act_type->variation == MADE_HIGH)))
	  {
	    passive_hidden_renamed = (passive_hidden_renamed ||
				      (global_trans_cell->action->info.action->rate_index == PASSIVE));
	    global_trans_cell->action =
	      set_action_bucket((ST_BUCKET *)search_lexeme_table("invisible",
								 SYT),
				UNSTRUCTURED,
				NULL,
				global_trans_cell->action->info.action->rate_index,
				global_trans_cell->action->info.action->priority,
				global_trans_cell->action->info.action->rate);
	  }
	  else
	    if (global_trans_cell->action->info.action->type->info.act_type->renamed != NULL)
	    {
	      passive_hidden_renamed = (passive_hidden_renamed ||
				        (global_trans_cell->action->info.action->rate_index == PASSIVE));
	      global_trans_cell->action =
		set_action_bucket(global_trans_cell->action->info.action->type->info.act_type->renamed,
				  global_trans_cell->action->info.action->action_index,
				  global_trans_cell->action->info.action->par_list,
				  global_trans_cell->action->info.action->rate_index,
				  global_trans_cell->action->info.action->priority,
				  global_trans_cell->action->info.action->rate);
	    }
	return(passive_hidden_renamed);
}


BOOLEAN		check_global_state_eq(LST_BUCKET **local_state_vector1,
				      LST_BUCKET **local_state_vector2,
				      int        last_aei_no)
{
	BOOLEAN		equal;
	int		i;

	for (i = 0,
	     equal = TRUE;
	     ((i < last_aei_no) &&
	      equal);
	     i++)
	  equal = (local_state_vector1[i] == local_state_vector2[i]);
	return(equal);
}
