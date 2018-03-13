/****************************************************************/
/*								*/
/*								*/
/*		     equivalence_verifier.c			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module checks two .aem spec files for (resp. minimizes an .aem spec w.r.t.) strong/weak */
/* functional/Markovian bisimulation equivalence, by employing (variants of) the Kanellakis-Smolka */
/* partition refinement algorithm, and returns diagnostic information in the form of a distinguishing */
/* modal logic formula in the case of inequivalence, by means of a tree representation of the various */
/* partitions computed during the application of the algorithm. */
/* Each block of equivalent global states in the current partition is represented by an aggregated global */
/* transition (a sequence of triples formed by an action type, a rate index, and an aggregated rate) */
/* together with the serial number of the current refinement phase (to avoid confusion with equal */
/* aggregated global transitions related to previous partitions). In the representation of an aggregated */
/* global transition, the rate index is needed to avoid equating terms with enabled actions of the same */
/* type and aggregated rate/weight but different rate index. In the case of strong/weak functional */
/* bisimulation equivalence, for each triple the rate index and the aggregated rate are replaced by a */
/* placeholder, which cannot be avoided because of possible clashes between the symbol table bucket for an */
/* action type and the symbol table bucket for an aggregated global transition composed of an action type */
/* only. */
/* This module contains the following functions: */
/* - open_equiv_check_result(): It opens the .evr file that will contain the result of the equivalence */
/*			        checking. */
/* - generate_weak_functional_global_trans_rel(): It generates the weak functional global transition */
/*						  relation. */
/* - generate_weak_markovian_global_trans_rel(): It generates the weak Markovian global transition */
/*						 relation. */
/* - check_bisim_equiv(): It checks whether two .aem spec files are strong bisimulation equivalent. */
/* - check_global_state_equiv(): It checks whether two global states are strong bisimulation equivalent. */
/* - minimize_bisim_equiv(): It minimizes an .aem spec w.r.t. strong bisimulation equivalence. */
/* - close_equiv_check_result(): It writes the result of the equivalence checking to the .evr file, then */
/*			         closes it. */
/* - compute_disting_formula(): It computes a distinguishing modal logic formula in case of inequivalence */
/*				for the initial states of the two .aem specs whenever a splitting of the */
/*				initial state probabilities has not occurred. */
/* - print_disting_formula(): It prints the distinguishing modal logic formula with suitable indentations. */
/* and the following auxiliary functions: */
/* - remove_inv_immediate_selfloops(): It removes the invisible immediate selfloops of an invisible */
/*				       vanishing global state. */
/* - sort_global_trans_lists(): It lexicographically sorts the list of global transitions of each global */
/*				state. */
/* - create_init_partition(): It scans the global state space to create the initial partition based on the */
/*			      necessary condition for bisimilarity. */
/* - refine_partition(): It refines the current partition. */
/* - compute_aggr_global_trans(): It computes the aggregated global transition of a global state towards */
/*				  a refining partition block and updates the partition block tree */
/*				  accordingly. */
/* - update_aggr_global_trans(): It updates the aggregated global transition of a global state towards a */
/*				 refining partition block. */
/* - move_global_state(): It moves a global state between two partition blocks. */
/* - check_block_membership(): It checks whether a global state belongs to a partition block. */
/* - check_stack_membership(): It checks whether a pair of inequivalent global states belongs to the */
/*			       inequivalent global state stack. */
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

#include	"../headers/aemilia_compiler.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		open_equiv_check_result(char *),
		generate_weak_functional_global_trans_rel(void),
		generate_weak_markovian_global_trans_rel(void);

BOOLEAN		check_bisim_equiv(void),
		check_global_state_equiv(GST_BUCKET *,
					 GST_BUCKET *);

void		minimize_bisim_equiv(void),
		close_equiv_check_result(BOOLEAN);

ST_BUCKET	*compute_disting_formula(GST_BUCKET *,
					 GST_BUCKET *);

void		print_disting_formula(FILE *,
				      char *,
				      int *,
				      int,
				      BOOLEAN,
				      BOOLEAN *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	BOOLEAN		no_init_prob_split;
				/* flag indicating whether no splitting of the initial state probabilities */
				/* has occurred (option_index = WEAK_MARKOVIAN_BISIM_EQUIV_CHECK); */
				/* set by ; */
				/* used in */

PRIVATE	FILE		*evr_file;
				/* .evr file containing the result of the equivalence checking; */
				/* set by ; */
				/* used in */

PRIVATE	PART_BLOCK	**membership_vector[2],
				/* pointer to the partition blocks to which the global states belong; */
				/* set by verify_int_bisim_eq() and scan_state_space(); */
				/* used in verify_int_bisim_eq() */
			*part_block_tree;
				/* tree of partition blocks needed to determine a distinguishing modal */
				/* logic formula in case of inequivalence; */
				/* set by ; */
				/* used in */

PRIVATE	PART_BLOCK_CELL	*part_block_list,
				/* list of cells for the partition blocks forming the current partition; */
				/* set by ; */
				/* used in */
			*splitter_list;
				/* list of cells for the partition blocks to be used as splitters; */
				/* set by verify_int_bisim_eq(); */
				/* used in verify_int_bisim_eq() and scan_state_space() */

PRIVATE	int		refinement_phase;
				/* current refinement phase; */
				/* set by verify_int_bisim_eq(); */
				/* used in verify_int_bisim_eq() and compute_aggr_move() */


PRIVATE	void		remove_inv_immediate_selfloops(GST_BUCKET *),
			sort_global_trans_lists(void),
			create_init_partition(void),
			refine_partition(void);

PRIVATE	ST_BUCKET	*compute_aggr_global_trans(GST_BUCKET *,
						   PART_BLOCK *,
						   PART_BLOCK *);

PRIVATE	void		update_aggr_global_trans(GST_BUCKET *,
						 char **,
						 int *,
						 int *,
						 ST_BUCKET_CELL **,
						 ST_BUCKET *,
						 AGGR_RATE_CELL **,
						 RATE_INDEX,
						 long double,
						 long double),
			move_global_state(G_STATE_CELL *,
					  G_STATE_CELL **,
					  G_STATE_CELL **);

PRIVATE	BOOLEAN		check_block_membership(GST_BUCKET *,
					       PART_BLOCK *),
			check_stack_membership(GST_BUCKET *,
					       GST_BUCKET *,
					       INEQUIV_GS_CELL *);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		open_equiv_check_result(char *aem_path)
{
	evr_file = new_fopen(aem_path,
			     ".evr",
			     "w");
	no_init_prob_split = TRUE;
}


void		generate_weak_functional_global_trans_rel(void)
{
	BOOLEAN		found;
	GST_BUCKET	*global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell,
			*aux_global_state_cell,
			*inv_reached_global_state_list,
			*inv_reached_global_state_cell,
			*last_inv_reached_global_state_cell,
			*curr_inv_reached_global_state_cell;
	G_TRANS_CELL	*global_trans_cell,
			*inv_global_trans_cell,
			*aux_global_trans_cell;
	REWARD_INFO	reward_info;
	int		curr_length,
			max_length;

	/* compute the transitive closure of the invisible global transitions; */
	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[table_no]->info.archi_type->init_global_state->global_trans_considered =
	  (option_index != NON_DED_ON_COMP_ANALYSIS)?
	    FALSE:
	    TRUE;
	inv_reached_global_state_list = last_inv_reached_global_state_cell = NULL;
	reward_info.yb_list = NULL;
	max_length = 0;
        while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* generate an invisible global transition from the global state to itself if not any */
	  for (global_trans_cell = global_state->global_trans_list,
	       found = FALSE;
	       ((global_trans_cell != NULL) &&
		!found);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    found = (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			     "invisible") &&
		     (global_trans_cell->der_global_state == global_state));
	  if (!found)
	  {
	    global_state->global_trans_list =
	      alloc_g_trans_cell(set_action_bucket((ST_BUCKET *)search_lexeme_table("invisible",
										    SYT),
						   UNSTRUCTURED,
						   NULL,
						   PASSIVE,
						   set_number_bucket("1"),
						   set_number_bucket("1")),
				 NULL,
				 reward_info,
				 global_state,
				 global_state->global_trans_list);
	    for (aux_global_state_cell = global_state->reaching_global_state_list,
		 found = FALSE;
		 ((aux_global_state_cell != NULL) &&
		  !found);
		 aux_global_state_cell = aux_global_state_cell->next_gst_bucket_cell)
	      found = (aux_global_state_cell->gst_bucket == global_state);
	    if (!found)
	      global_state->reaching_global_state_list =
		alloc_gst_bucket_cell(global_state,
				      global_state->reaching_global_state_list);
	  }
	  /* initialize the list of global states invisibly reached by the considered global state in one */
	  /* step */
	  for (global_trans_cell = global_state->global_trans_list,
	       curr_inv_reached_global_state_cell = inv_reached_global_state_list,
	       curr_length = 0;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			"invisible") &&
		(global_trans_cell->der_global_state != global_state))
	    {
	      if (curr_length == max_length)
	      {
	        if (inv_reached_global_state_list == NULL)
	          inv_reached_global_state_list =
		    last_inv_reached_global_state_cell =
		      curr_inv_reached_global_state_cell =
		        alloc_gst_bucket_cell(global_trans_cell->der_global_state,
					      NULL);
	        else
	          last_inv_reached_global_state_cell =
		    curr_inv_reached_global_state_cell =
		      last_inv_reached_global_state_cell->next_gst_bucket_cell =
		        alloc_gst_bucket_cell(global_trans_cell->der_global_state,
					      NULL);
		curr_length++;
		max_length++;
	      }
	      else
	      {
		if (curr_length != 0)
	          curr_inv_reached_global_state_cell =
		    curr_inv_reached_global_state_cell->next_gst_bucket_cell;
		curr_inv_reached_global_state_cell->gst_bucket = global_trans_cell->der_global_state;
		curr_length++;
	      }
	    }
	  /* compute the weak global transitions of the global state */
	  for (inv_reached_global_state_cell = (curr_length == 0)?
						 NULL:
						 inv_reached_global_state_list;
	       (inv_reached_global_state_cell != NULL);
	       inv_reached_global_state_cell =
	         (inv_reached_global_state_cell == curr_inv_reached_global_state_cell)?
		   NULL:
		   inv_reached_global_state_cell->next_gst_bucket_cell)
	    for (global_trans_cell = inv_reached_global_state_cell->gst_bucket->global_trans_list;
	         (global_trans_cell != NULL);
	         global_trans_cell = global_trans_cell->next_g_trans_cell)
	      if (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			  "invisible"))
	      {
	        for (aux_global_state_cell = inv_reached_global_state_list,
		     found = FALSE;
	             ((aux_global_state_cell != NULL) &&
		      !found);
	             aux_global_state_cell = (aux_global_state_cell == curr_inv_reached_global_state_cell)?
					       NULL:
					       aux_global_state_cell->next_gst_bucket_cell)
		  found = (aux_global_state_cell->gst_bucket == global_trans_cell->der_global_state);
		if (!found)
	        {
	          global_state->global_trans_list =
	            alloc_g_trans_cell(set_action_bucket((ST_BUCKET *)search_lexeme_table("invisible",
										          SYT),
						         UNSTRUCTURED,
						         NULL,
						         PASSIVE,
						         set_number_bucket("1"),
						         set_number_bucket("1")),
				       NULL,
				       reward_info,
				       global_trans_cell->der_global_state,
				       global_state->global_trans_list);
		  for (aux_global_state_cell =
			 global_trans_cell->der_global_state->reaching_global_state_list,
		       found = FALSE;
		       ((aux_global_state_cell != NULL) &&
			!found);
		       aux_global_state_cell = aux_global_state_cell->next_gst_bucket_cell)
		    found = (aux_global_state_cell->gst_bucket == global_state);
		  if (!found)
		    global_trans_cell->der_global_state->reaching_global_state_list =
		      alloc_gst_bucket_cell(global_state,
					    global_trans_cell->der_global_state->reaching_global_state_list);
	          if (curr_length == max_length)
	          {
	            last_inv_reached_global_state_cell =
		      curr_inv_reached_global_state_cell =
		        last_inv_reached_global_state_cell->next_gst_bucket_cell =
		          alloc_gst_bucket_cell(global_trans_cell->der_global_state,
					        NULL);
		    curr_length++;
		    max_length++;
	          }
	          else
	          {
	            curr_inv_reached_global_state_cell =
		      curr_inv_reached_global_state_cell->next_gst_bucket_cell;
		    curr_inv_reached_global_state_cell->gst_bucket = global_trans_cell->der_global_state;
		    curr_length++;
	          }
	        }
	      }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (((option_index != NON_DED_ON_COMP_ANALYSIS) &&
	         global_trans_cell->der_global_state->global_trans_considered) ||
	        ((option_index == NON_DED_ON_COMP_ANALYSIS) &&
	         !global_trans_cell->der_global_state->global_trans_considered))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered =
		(option_index != NON_DED_ON_COMP_ANALYSIS)?
		  FALSE:
		  TRUE;
            }
	}

	/* compute the double composition of the observable global transition relation and the transitive */
	/* closure of the invisible global transitions; */
	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[table_no]->info.archi_type->init_global_state->global_trans_considered =
	  (option_index != NON_DED_ON_COMP_ANALYSIS)?
	    TRUE:
	    FALSE;
        while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* compute the list of global states invisibly reached by the considered global state in zero or */
	  /* more steps */
	  for (global_trans_cell = global_state->global_trans_list,
	       curr_inv_reached_global_state_cell = inv_reached_global_state_list,
	       curr_length = 0;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (!strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			"invisible"))
	    {
	      if (curr_length == max_length)
	      {
	        if (inv_reached_global_state_list == NULL)
	          inv_reached_global_state_list =
		    last_inv_reached_global_state_cell =
		      curr_inv_reached_global_state_cell =
		        alloc_gst_bucket_cell(global_trans_cell->der_global_state,
					      NULL);
	        else
	          last_inv_reached_global_state_cell =
		    curr_inv_reached_global_state_cell =
		      last_inv_reached_global_state_cell->next_gst_bucket_cell =
		        alloc_gst_bucket_cell(global_trans_cell->der_global_state,
					      NULL);
		curr_length++;
		max_length++;
	      }
	      else
	      {
		if (curr_length != 0)
	          curr_inv_reached_global_state_cell =
		    curr_inv_reached_global_state_cell->next_gst_bucket_cell;
		curr_inv_reached_global_state_cell->gst_bucket = global_trans_cell->der_global_state;
		curr_length++;
	      }
	    }
	  /* compute the double global transition relation composition */
	  for (inv_reached_global_state_cell = inv_reached_global_state_list;
	       (inv_reached_global_state_cell != NULL);
	       inv_reached_global_state_cell =
	         (inv_reached_global_state_cell == curr_inv_reached_global_state_cell)?
		   NULL:
		   inv_reached_global_state_cell->next_gst_bucket_cell)
	    for (global_trans_cell = inv_reached_global_state_cell->gst_bucket->global_trans_list;
	         (global_trans_cell != NULL);
	         global_trans_cell = global_trans_cell->next_g_trans_cell)
	      if (strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
			 "invisible"))
	        for (inv_global_trans_cell = global_trans_cell->der_global_state->global_trans_list;
	             (inv_global_trans_cell != NULL);
	             inv_global_trans_cell = inv_global_trans_cell->next_g_trans_cell)
	          if (!strcmp(inv_global_trans_cell->action->info.action->type->symbol_lexeme,
			      "invisible"))
		  {
	            for (aux_global_trans_cell = global_state->global_trans_list,
			 found = FALSE;
	                 ((aux_global_trans_cell != NULL) &&
			  !found);
	                 aux_global_trans_cell = aux_global_trans_cell->next_g_trans_cell)
		      found = ((aux_global_trans_cell->action == global_trans_cell->action) &&
			       (aux_global_trans_cell->der_global_state ==
				  inv_global_trans_cell->der_global_state));
		    if (!found)
		    {
	              global_state->global_trans_list =
	                alloc_g_trans_cell(global_trans_cell->action,
				           NULL,
				           reward_info,
				           inv_global_trans_cell->der_global_state,
				           global_state->global_trans_list);
		      for (aux_global_state_cell =
			     inv_global_trans_cell->der_global_state->reaching_global_state_list,
		           found = FALSE;
		           ((aux_global_state_cell != NULL) &&
			    !found);
		           aux_global_state_cell = aux_global_state_cell->next_gst_bucket_cell)
		        found = (aux_global_state_cell->gst_bucket == global_state);
		      if (!found)
		        inv_global_trans_cell->der_global_state->reaching_global_state_list =
		          alloc_gst_bucket_cell(global_state,
					        inv_global_trans_cell->der_global_state
					          ->reaching_global_state_list);
		    }
		  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (((option_index != NON_DED_ON_COMP_ANALYSIS) &&
	         !global_trans_cell->der_global_state->global_trans_considered) ||
	        ((option_index == NON_DED_ON_COMP_ANALYSIS) &&
	         global_trans_cell->der_global_state->global_trans_considered))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered =
		(option_index != NON_DED_ON_COMP_ANALYSIS)?
		  TRUE:
		  FALSE;
            }
	}
	free_gst_bucket_list(inv_reached_global_state_list);
}


void		generate_weak_markovian_global_trans_rel(void)
{
		BOOLEAN		found,
				all_reaching_splitted;
		GST_BUCKET	*inv_vanishing_global_state,
				*reaching_global_state;
		GST_BUCKET_CELL	*inv_vanishing_global_state_cell,
				*prev_reaching_global_state_cell,
				*curr_reaching_global_state_cell,
				*global_state_cell,
				*prev_global_state_cell,
				*curr_global_state_cell;
		G_TRANS_CELL	*inv_imm_global_trans_cell,
				*prev_global_trans_cell,
				*curr_global_trans_cell;
		REWARD_INFO	reward_info;
	static	char		*splitted_rate_lexeme			=	NULL;
		int		inv_imm_global_trans_num,
				length;
	static	int		max_length				=	0;
		long double	total_weight,
				splitted_rate;

	for (inv_vanishing_global_state_cell = inv_vanishing_global_state_list[spec_no];
	     (inv_vanishing_global_state_cell != NULL);
	     inv_vanishing_global_state_cell = inv_vanishing_global_state_cell->next_gst_bucket_cell)
	{
	  /* compute the number and the total weight of the global transitions leaving the invisible */
	  /* vanishing global state (implicit weight normalization) */
	  inv_vanishing_global_state = inv_vanishing_global_state_cell->gst_bucket;
	  remove_inv_immediate_selfloops(inv_vanishing_global_state);
	  for (inv_imm_global_trans_cell = inv_vanishing_global_state->global_trans_list,
	       inv_imm_global_trans_num = 0,
	       total_weight = 0.0L;
	       (inv_imm_global_trans_cell != NULL);
	       inv_imm_global_trans_cell = inv_imm_global_trans_cell->next_g_trans_cell)
	  {
	    inv_imm_global_trans_num++;
	    total_weight += inv_imm_global_trans_cell->action->info.action->rate->info.expr->value;
	  }
	  if (total_weight > 0.0L)
	  {
	    /* split the rate of each global transition of every global state reaching the invisible */
	    /* vanishing one */
	    for (prev_reaching_global_state_cell = curr_reaching_global_state_cell =
		   inv_vanishing_global_state->reaching_global_state_list;
		 (curr_reaching_global_state_cell != NULL);
		 )
	    {
	      reaching_global_state = curr_reaching_global_state_cell->gst_bucket;
	      all_reaching_splitted = TRUE;
	      for (prev_global_trans_cell = curr_global_trans_cell =
		     reaching_global_state->global_trans_list;
		   (curr_global_trans_cell != NULL);
		   )
		if (curr_global_trans_cell->der_global_state == inv_vanishing_global_state)
		{
		  if (curr_global_trans_cell->action->info.action->rate_index != PASSIVE)
		  {
		    if (inv_imm_global_trans_num > 1)
		    {
	              if (prev_global_trans_cell == curr_global_trans_cell)
		        reaching_global_state->global_trans_list =
			  curr_global_trans_cell->next_g_trans_cell;
	              else
		        prev_global_trans_cell->next_g_trans_cell =
			  curr_global_trans_cell->next_g_trans_cell;
		    }
	            for (inv_imm_global_trans_cell = inv_vanishing_global_state->global_trans_list;
		         (inv_imm_global_trans_cell != NULL);
		         inv_imm_global_trans_cell = inv_imm_global_trans_cell->next_g_trans_cell)
		    {
		      if (inv_imm_global_trans_num > 1)
		      {
		        splitted_rate =
			  curr_global_trans_cell->action->info.action->rate->info.expr->value *
			  (inv_imm_global_trans_cell->action->info.action->rate->info.expr->value /
			   total_weight);
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
			reward_info.yb_list = NULL;
		        reaching_global_state->global_trans_list =
		          alloc_g_trans_cell(set_action_bucket(curr_global_trans_cell->action->info.action
							         ->type,
		        		     		       curr_global_trans_cell->action->info.action
							         ->action_index,
		          		     		       curr_global_trans_cell->action->info.action
							         ->par_list,
		          		     		       curr_global_trans_cell->action->info.action
							         ->rate_index,
		          		     		       curr_global_trans_cell->action->info.action
							         ->priority,
		          		     		       set_number_bucket(splitted_rate_lexeme)),
		        	             NULL,
		        	             reward_info,
		        	             inv_imm_global_trans_cell->der_global_state,
		        	             reaching_global_state->global_trans_list);
		      }
		      else
		        curr_global_trans_cell->der_global_state =
			  inv_imm_global_trans_cell->der_global_state;
		      for (global_state_cell =
			     inv_imm_global_trans_cell->der_global_state->reaching_global_state_list,
			   found = FALSE;
			   ((global_state_cell != NULL) &&
			    !found);
			   global_state_cell = global_state_cell->next_gst_bucket_cell)
		        found = (global_state_cell->gst_bucket == reaching_global_state);
		      if (!found)
		        inv_imm_global_trans_cell->der_global_state->reaching_global_state_list =
		          alloc_gst_bucket_cell(reaching_global_state,
					        inv_imm_global_trans_cell->der_global_state
						  ->reaching_global_state_list);
		    }
		    if (inv_imm_global_trans_num > 1)
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
	            all_reaching_splitted = FALSE;
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
	      if (all_reaching_splitted)
	      {
		if (prev_reaching_global_state_cell == curr_reaching_global_state_cell)
		{
		  inv_vanishing_global_state->reaching_global_state_list =
		    inv_vanishing_global_state->reaching_global_state_list->next_gst_bucket_cell;
		  free(curr_reaching_global_state_cell);
		  prev_reaching_global_state_cell = curr_reaching_global_state_cell =
		    inv_vanishing_global_state->reaching_global_state_list;
		}
		else
		{
		  curr_reaching_global_state_cell = curr_reaching_global_state_cell->next_gst_bucket_cell;
		  free(prev_reaching_global_state_cell->next_gst_bucket_cell);
		  prev_reaching_global_state_cell->next_gst_bucket_cell = curr_reaching_global_state_cell;
		}
	      }
	      else
	      {
		prev_reaching_global_state_cell = curr_reaching_global_state_cell;
		curr_reaching_global_state_cell = curr_reaching_global_state_cell->next_gst_bucket_cell;
	      }
	    }
	  }
	  /* split the initial state probability of the invisible vanishing global state among the global */
	  /* states reachable from it in one step and update the list of global states from which they are */
	  /* reached by removing the invisible vanishing global state */
	  if (inv_vanishing_global_state->reaching_global_state_list == NULL)
	  {
	    for (inv_imm_global_trans_cell = inv_vanishing_global_state->global_trans_list;
		 (inv_imm_global_trans_cell != NULL);
		 inv_imm_global_trans_cell = inv_imm_global_trans_cell->next_g_trans_cell)
	    {
	      if (init_prob_vector[spec_no][inv_vanishing_global_state->global_state_no] > 0.0L)
	      {
	        no_init_prob_split = FALSE;
	        init_prob_vector[spec_no][inv_imm_global_trans_cell->der_global_state->global_state_no] +=
		  init_prob_vector[spec_no][inv_vanishing_global_state->global_state_no] *
		  inv_imm_global_trans_cell->action->info.action->rate->info.expr->value / total_weight;
	      }
	      for (curr_global_state_cell = prev_global_state_cell =
		     inv_imm_global_trans_cell->der_global_state->reaching_global_state_list;
		   ((curr_global_state_cell != NULL) &&
		    (curr_global_state_cell->gst_bucket != inv_vanishing_global_state));
		   prev_global_state_cell = curr_global_state_cell,
		   curr_global_state_cell = curr_global_state_cell->next_gst_bucket_cell);
	      if (curr_global_state_cell != NULL)
	      {
	        if (curr_global_state_cell == prev_global_state_cell)
		  inv_imm_global_trans_cell->der_global_state->reaching_global_state_list =
		    inv_imm_global_trans_cell->der_global_state->reaching_global_state_list
		      ->next_gst_bucket_cell;
	        else
		  prev_global_state_cell->next_gst_bucket_cell =
		    curr_global_state_cell->next_gst_bucket_cell;
	        free(curr_global_state_cell);
	      }
	    }
	    init_prob_vector[spec_no][inv_vanishing_global_state->global_state_no] = 0.0L;
	  }
	}
}


BOOLEAN		check_bisim_equiv(void)
{
		BOOLEAN		equivalent;
		GST_BUCKET	*global_state;
		PART_BLOCK_CELL	*part_block_cell;
		REWARD_INFO	reward_info;
	static	char		*weight_lexeme			=	NULL;
		int		init_global_state_no[2],
				global_state_no,
				length,
				i;
	static	int		max_length			=	0;

	/* initialize the data structures */
	part_block_list = NULL;
	splitter_list = NULL;
	part_block_tree = alloc_part_block(NULL);

	/* create the initial partition according to the necessary condition for bisimilarity */
	refinement_phase = 0;
	for (table_no = 0;
	     (table_no <= 1);
	     table_no++)
	{
	  if ((option_index != WEAK_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	      no_init_prob_split)
	    init_global_state_no[table_no] = 1;
	  else
	  {
	    /* create a new initial global state with an invisible immediate transition towards every */
	    /* global state whose initial state probability is greater than zero */
	    archi_type[table_no]->info.archi_type->init_global_state = alloc_gst_bucket(NULL);
	    init_global_state_no[table_no] = 0;
	    reward_info.yb_list = NULL;
	    for (i = 0;
		 (i < GST_SIZE);
		 i++)
	      for (global_state = global_state_table[table_no][i];
		   (global_state != NULL);
		   global_state = global_state->next_gst_bucket)
	        if (init_prob_vector[table_no][global_state->global_state_no] > 0.0L)
		{
		  length = compute_digit_num(init_prob_vector[table_no][global_state->global_state_no]) +
			   F_FORMAT_PRECIS_LENGTH;
		  if (length > max_length)
		  {
		    if (weight_lexeme != NULL)
		      free(weight_lexeme);
		    weight_lexeme = alloc_string(length);
		    max_length = length;
		  }
		  sprintf(weight_lexeme,
			  "%f",
			  (double)(init_prob_vector[table_no][global_state->global_state_no]));
		  archi_type[table_no]->info.archi_type->init_global_state->global_trans_list =
		    alloc_g_trans_cell(set_action_bucket((ST_BUCKET *)search_lexeme_table("invisible",
											  SYT),
							 UNSTRUCTURED,
							 NULL,
							 IMMEDIATE,
							 set_number_bucket("1"),
							 set_number_bucket(weight_lexeme)),
				       NULL,
				       reward_info,
				       global_state,
				       archi_type[table_no]->info.archi_type->init_global_state->
				         global_trans_list);
		  global_state->reaching_global_state_list =
		    alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
					  global_state->reaching_global_state_list);
		}
	  }
	  sort_global_trans_lists();
	  membership_vector[table_no] =
	    (PART_BLOCK **)new_calloc(archi_type[table_no]->info.archi_type->state_num + 1,
				      sizeof(PART_BLOCK *));
	  for (global_state_no = archi_type[table_no]->info.archi_type->state_num;
	       (global_state_no >= 0);
	       global_state_no--)
	    membership_vector[table_no][global_state_no] = NULL;
	  create_init_partition();
	}
	if (part_block_tree->children_part_block_list->next_part_block_cell != NULL)
	  for (part_block_cell = part_block_tree->children_part_block_list;
	       (part_block_cell != NULL);
	       part_block_cell = part_block_cell->next_part_block_cell)
	  {
	    part_block_list = alloc_part_block_cell(part_block_cell->part_block,
						    part_block_list);
	    splitter_list = alloc_part_block_cell(part_block_cell->part_block,
						  splitter_list);
	  }

	/* refine the current partition w.r.t. the splitters until there are no more splitters or the two */
	/* initial global states are no longer in the same partition block */
	while ((equivalent = (membership_vector[0][init_global_state_no[0]] ==
				membership_vector[1][init_global_state_no[1]])) &&
	       (splitter_list != NULL))
	  refine_partition();
	return(equivalent);
}


BOOLEAN		check_global_state_equiv(GST_BUCKET *global_state1,
					 GST_BUCKET *global_state2)
{
	return(membership_vector[(int)(global_state1->table_no)][global_state1->global_state_no] ==
		 membership_vector[(int)(global_state2->table_no)][global_state2->global_state_no]);
}


void		minimize_bisim_equiv(void)
{
	PART_BLOCK_CELL	*part_block_cell;
	int		global_state_no;

	/* initialize the data structures */
	part_block_list = NULL;
	splitter_list = NULL;
	part_block_tree = alloc_part_block(NULL);

	/* create the initial partition according to the necessary condition for bisimilarity */
	refinement_phase = 0;
	sort_global_trans_lists();
	membership_vector[table_no] =
	  (PART_BLOCK **)new_calloc(archi_type[table_no]->info.archi_type->state_num + 1,
				    sizeof(PART_BLOCK *));
	for (global_state_no = archi_type[table_no]->info.archi_type->state_num;
	     (global_state_no >= 1);
	     global_state_no--)
	  membership_vector[table_no][global_state_no] = NULL;
	create_init_partition();
	if (part_block_tree->children_part_block_list->next_part_block_cell != NULL)
	  for (part_block_cell = part_block_tree->children_part_block_list;
	       (part_block_cell != NULL);
	       part_block_cell = part_block_cell->next_part_block_cell)
	  {
	    part_block_list = alloc_part_block_cell(part_block_cell->part_block,
						    part_block_list);
	    splitter_list = alloc_part_block_cell(part_block_cell->part_block,
						  splitter_list);
	  }

	/* refine the current partition w.r.t. all the splitters */
	while (splitter_list != NULL)
	  refine_partition();
}


void		close_equiv_check_result(BOOLEAN equivalent)
{
	BOOLEAN		preceding_true;
	ST_BUCKET	*disting_formula;
	int		pos;

	fprintf(evr_file,
		"%s %s %s bisimulation equivalent to %s",
		archi_type[0]->symbol_lexeme,
		(equivalent)?
		  "is":
		  "isn't",
		(option_index == STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK)?
		  "strongly":
		  ((option_index == WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK)?
		    "weakly":
		    ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK)?
		      "strongly Markovian":
		      "weakly Markovian")),
		archi_type[1]->symbol_lexeme);
	if (equivalent)
	  fprintf(evr_file,
		  ".\n");
	else
	{
	  table_no = 0;
	  disting_formula = compute_disting_formula(archi_type[0]->info.archi_type->init_global_state,
						    archi_type[1]->info.archi_type->init_global_state);
	  fprintf(evr_file,
		  "\nas demonstrated by the following modal logic formula");
	  fprintf(evr_file,
		  "\nsatisfied by %s but not by %s:\n\t",
		  archi_type[0]->symbol_lexeme,
		  archi_type[1]->symbol_lexeme);
	  pos = 0;
	  preceding_true = FALSE;
	  print_disting_formula(evr_file,
				disting_formula->symbol_lexeme,
				&pos,
				0,
				FALSE,
				&preceding_true);
	}
	fclose(evr_file);
}


ST_BUCKET	*compute_disting_formula(GST_BUCKET *init_global_state1,
					 GST_BUCKET *init_global_state2)
{
	AGGR_RATE_CELL	*outer_aggr_rate_cell,
			*inner_aggr_rate_cell;
	BOOLEAN		found;
	GST_BUCKET	*global_state[2];
	G_TRANS_CELL	*outer_global_trans_cell,
			*inner_global_trans_cell;
	INEQUIV_GS_CELL	*inequiv_global_state_stack,
			*curr_inequiv_global_state_cell,
			*father_inequiv_global_state_cell;
	PART_BLOCK	*part_block[2],
			*father_part_block[2],
			*common_anc_part_block,
			*aux_part_block;
	PART_BLOCK_CELL	*children_part_block_cell;
	ST_BUCKET	*aggr_global_trans[2],
			*disting_formula;
	ST_BUCKET_CELL	*aggr_global_trans_cell,
			*outer_act_type_cell,
			*inner_act_type_cell,
			*disting_sub_formula_cell;
	int		height[2],
			i;

	/* build the stack of inequivalent global state pairs that are involved in the computation of the */
	/* distinguishing formula for the two initial inequivalent global states; recursion is not */
	/* employed to avoid run-time-support stack overflow in the case of two specs behaving differently */
        /* after executing a huge number of global transitions */
	for (inequiv_global_state_stack = curr_inequiv_global_state_cell =
	       alloc_inequiv_gs_cell(init_global_state1,
				     init_global_state2,
				     0.0L,
				     NULL,
				     NULL,
				     NULL);
	     (curr_inequiv_global_state_cell != NULL);
	     curr_inequiv_global_state_cell = curr_inequiv_global_state_cell->next_inequiv_gs_cell)
	{
	  /* extract the next pair of inequivalent global states from the stack and process them if they */
	  /* do not belong to the same partition block (the opposite can happen in the case of the */
	  /* strong/weak Markovian bisimulation equivalence if they reach the same partition block through */
	  /* the same action type with different aggregated rates) */
	  global_state[0] = curr_inequiv_global_state_cell->outer_global_state;
	  global_state[1] = curr_inequiv_global_state_cell->inner_global_state;
	  part_block[0] = membership_vector[(int)(global_state[0]->table_no)]
					   [global_state[0]->global_state_no];
	  part_block[1] = membership_vector[(int)(global_state[1]->table_no)]
					   [global_state[1]->global_state_no];
	  if (part_block[0] != part_block[1])
	  {
	    /* determine the deepest partition block in the partition block tree containing the two */
	    /* inequivalent global states */
	    for (i = 0;
	         (i <= 1);
	         i++)
	    {
	      father_part_block[i] = part_block[i];
	      for (aux_part_block = part_block[i],
	           height[i] = 0;
	           (aux_part_block != part_block_tree);
	           aux_part_block = aux_part_block->father_part_block,
	           height[i]++);
	    }
	    if (height[0] > height[1])
	    {
	      for (i = height[0] - height[1];
	           (i >= 1);
	           i--)
	        father_part_block[0] = father_part_block[0]->father_part_block;
	    }
	    else
	      if (height[0] < height[1])
	      {
	        for (i = height[1] - height[0];
	             (i >= 1);
	             i--)
	          father_part_block[1] = father_part_block[1]->father_part_block;
	      }
	    for (;
	         (father_part_block[0]->father_part_block != father_part_block[1]->father_part_block);
	         father_part_block[0] = father_part_block[0]->father_part_block,
	         father_part_block[1] = father_part_block[1]->father_part_block);
	    common_anc_part_block = father_part_block[0]->father_part_block;
	    /* determine the distinguishing action type, the distinguishing aggregated rate together with */
	    /* its index, and the information about field left for the two inequivalent global states */
	    /* with respect to the refining partition block for the common ancestor partition block */
	    for (i = 0;
	         (i <= 1);
	         i++)
	    {
	      for (children_part_block_cell = common_anc_part_block->children_part_block_list,
	           aggr_global_trans_cell = common_anc_part_block->aggr_global_trans_list,
	           found = FALSE;
	           (!found);
	           children_part_block_cell = (!found)?
					        children_part_block_cell->next_part_block_cell:
					        children_part_block_cell,
	           aggr_global_trans_cell = (!found)?
					      aggr_global_trans_cell->next_st_bucket_cell:
					      aggr_global_trans_cell)
	        found = (children_part_block_cell->part_block == father_part_block[i]);
	      aggr_global_trans[i] = aggr_global_trans_cell->st_bucket;
	    }
	    for (outer_act_type_cell = aggr_global_trans[0]->info.aggr_global_trans->act_type_list,
	         outer_aggr_rate_cell = aggr_global_trans[0]->info.aggr_global_trans->aggr_rate_list,
	         found = TRUE;
	         ((outer_act_type_cell != NULL) &&
	          found);
	         outer_act_type_cell = (found)?
				         outer_act_type_cell->next_st_bucket_cell:
				         outer_act_type_cell,
	         outer_aggr_rate_cell = (((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
				          (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) &&
				         found)?
				          outer_aggr_rate_cell->next_aggr_rate_cell:
				          outer_aggr_rate_cell)
	      for (inner_act_type_cell = aggr_global_trans[1]->info.aggr_global_trans->act_type_list,
	           inner_aggr_rate_cell = aggr_global_trans[1]->info.aggr_global_trans->aggr_rate_list,
	           found = FALSE;
	           ((inner_act_type_cell != NULL) &&
	            !found);
	           inner_act_type_cell = inner_act_type_cell->next_st_bucket_cell,
	           inner_aggr_rate_cell = ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
				           (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))?
				            inner_aggr_rate_cell->next_aggr_rate_cell:
				            inner_aggr_rate_cell)
	        found = (!strcmp(inner_act_type_cell->st_bucket->symbol_lexeme,
			         outer_act_type_cell->st_bucket->symbol_lexeme) &&
		         (((option_index != STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) &&
		           (option_index != WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) ||
		          ((outer_aggr_rate_cell->rate_index == inner_aggr_rate_cell->rate_index) &&
		           (outer_aggr_rate_cell->aggr_rate <= inner_aggr_rate_cell->aggr_rate))));
	    if (found)
	    {
	      curr_inequiv_global_state_cell->left = (char)FALSE;
	      for (outer_act_type_cell = aggr_global_trans[1]->info.aggr_global_trans->act_type_list,
	           outer_aggr_rate_cell = aggr_global_trans[1]->info.aggr_global_trans->aggr_rate_list;
	           ((outer_act_type_cell != NULL) &&
	            found);
	           outer_act_type_cell = (found)?
				           outer_act_type_cell->next_st_bucket_cell:
				           outer_act_type_cell,
	           outer_aggr_rate_cell = (((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
				            (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) &&
				           found)?
				            outer_aggr_rate_cell->next_aggr_rate_cell:
				            outer_aggr_rate_cell)
	        for (inner_act_type_cell = aggr_global_trans[0]->info.aggr_global_trans->act_type_list,
	             inner_aggr_rate_cell = aggr_global_trans[0]->info.aggr_global_trans->aggr_rate_list,
	             found = FALSE;
	             ((inner_act_type_cell != NULL) &&
	              !found);
	             inner_act_type_cell = inner_act_type_cell->next_st_bucket_cell,
	             inner_aggr_rate_cell = ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
				             (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))?
				              inner_aggr_rate_cell->next_aggr_rate_cell:
				              inner_aggr_rate_cell)
	          found = (!strcmp(inner_act_type_cell->st_bucket->symbol_lexeme,
			           outer_act_type_cell->st_bucket->symbol_lexeme) &&
		           (((option_index != STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) &&
		             (option_index != WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) ||
		            ((outer_aggr_rate_cell->rate_index == inner_aggr_rate_cell->rate_index) &&
		             (outer_aggr_rate_cell->aggr_rate <= inner_aggr_rate_cell->aggr_rate))));
	    }
	    curr_inequiv_global_state_cell->disting_act_type = outer_act_type_cell->st_bucket;
	    curr_inequiv_global_state_cell->disting_aggr_rate =
	      ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	       (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))?
	        outer_aggr_rate_cell->aggr_rate:
		0.0L;
	    curr_inequiv_global_state_cell->disting_rate_index =
	      ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	       (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))?
		outer_aggr_rate_cell->rate_index:
		(char)NO_RATE;
	    /* push onto the stack the pairs of global states reached by the two inequivalent global */
	    /* states through actions whose type is the distinguishing action type and whose rate index is */
	    /* the distinguishing rate index; no pushing takes place at all if the two inequivalent global */
	    /* states are inherently inequivalent (otherwise the execution may loop if strong/weak */
	    /* Markovian bisimulation equivalence is being checked for); the reached global states in the */
	    /* outer loop that are pushed onto the stack must belong to the refining partition block for */
	    /* the common ancestor partition block of the two inequivalent global states; a pair of */
	    /* reached global states are not pushed onto the stack if they are encountered while computing */
	    /* the distinguishing formula for them, otherwise the execution may loop if strong/weak */
	    /* Markovian bisimulation equivalence is being checked for */
	    if (common_anc_part_block != part_block_tree)
	      for (outer_global_trans_cell = (curr_inequiv_global_state_cell->left)?
				               global_state[0]->global_trans_list:
				               global_state[1]->global_trans_list;
	           (outer_global_trans_cell != NULL);
	           outer_global_trans_cell = outer_global_trans_cell->next_g_trans_cell)
	        if (!strcmp(outer_global_trans_cell->action->info.action->type->symbol_lexeme,
		            curr_inequiv_global_state_cell->disting_act_type->symbol_lexeme) &&
		    (((option_index != STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) &&
		      (option_index != WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) ||
		     (outer_global_trans_cell->action->info.action->rate_index ==
		        curr_inequiv_global_state_cell->disting_rate_index)) &&
	            check_block_membership(outer_global_trans_cell->der_global_state,
					   common_anc_part_block->refining_part_block))
	          for (inner_global_trans_cell = (curr_inequiv_global_state_cell->left)?
				                   global_state[1]->global_trans_list:
				                   global_state[0]->global_trans_list;
	               (inner_global_trans_cell != NULL);
	               inner_global_trans_cell = inner_global_trans_cell->next_g_trans_cell)
	            if (!strcmp(inner_global_trans_cell->action->info.action->type->symbol_lexeme,
		                curr_inequiv_global_state_cell->disting_act_type->symbol_lexeme) &&
		        (((option_index != STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) &&
		          (option_index != WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) ||
		         (inner_global_trans_cell->action->info.action->rate_index ==
		            curr_inequiv_global_state_cell->disting_rate_index)))
		      if (!check_stack_membership(outer_global_trans_cell->der_global_state,
					          inner_global_trans_cell->der_global_state,
					          curr_inequiv_global_state_cell))
		        inequiv_global_state_stack = inequiv_global_state_stack->next_inequiv_gs_cell =
		          alloc_inequiv_gs_cell(outer_global_trans_cell->der_global_state,
					        inner_global_trans_cell->der_global_state,
						outer_global_trans_cell->action->info.action->rate->
						  info.expr->value,
					        curr_inequiv_global_state_cell,
					        inequiv_global_state_stack,
					        NULL);
	  }
	}

	/* pop the pairs of inequivalent global states off the stack one at a time in order to compute */
	/* the distinguishing formula for the two initial inequivalent global states in a bottom-up way */
	for (curr_inequiv_global_state_cell = inequiv_global_state_stack,
	     disting_formula = NULL;
	     (curr_inequiv_global_state_cell != NULL);
	     curr_inequiv_global_state_cell = curr_inequiv_global_state_cell->prev_inequiv_gs_cell)
	{
	  /* compute the conjunction of the distinguishing subformulas for the two inequivalent global */
	  /* states */
	  if ((curr_inequiv_global_state_cell->min_disting_sub_formula_num == -1) ||
	      (curr_inequiv_global_state_cell->disting_sub_formula_num <=
		 curr_inequiv_global_state_cell->min_disting_sub_formula_num))
	  {
	    if (curr_inequiv_global_state_cell->disting_sub_formula_num ==
		  curr_inequiv_global_state_cell->min_disting_sub_formula_num)
	    {
	      if (check_unordered_lists_equality(curr_inequiv_global_state_cell->disting_sub_formula_list,
						 curr_inequiv_global_state_cell->
						   min_disting_sub_formula_list))
		curr_inequiv_global_state_cell->disting_aggr_rate +=
		  curr_inequiv_global_state_cell->curr_outer_reached_global_state_incoming_rate;
	    }
	    else
	    {
	      if (curr_inequiv_global_state_cell->min_disting_sub_formula_num >= 0)
	        curr_inequiv_global_state_cell->disting_aggr_rate =
		  curr_inequiv_global_state_cell->curr_outer_reached_global_state_incoming_rate;
	      curr_inequiv_global_state_cell->min_disting_sub_formula_num =
	        curr_inequiv_global_state_cell->disting_sub_formula_num;
	      if (curr_inequiv_global_state_cell->min_disting_sub_formula_list != NULL)
	        free_st_bucket_list(curr_inequiv_global_state_cell->min_disting_sub_formula_list);
	      curr_inequiv_global_state_cell->min_disting_sub_formula_list =
	        curr_inequiv_global_state_cell->disting_sub_formula_list;
	    }
	  }
	  if (curr_inequiv_global_state_cell->min_disting_sub_formula_num == 0)
	    disting_formula = (ST_BUCKET *)search_lexeme_table("TRUE",
							       SYT);
	  else
	    for (disting_formula = curr_inequiv_global_state_cell->min_disting_sub_formula_list->st_bucket,
		 disting_sub_formula_cell =
		   curr_inequiv_global_state_cell->min_disting_sub_formula_list->next_st_bucket_cell;
		 (disting_sub_formula_cell != NULL);
		 disting_sub_formula_cell = disting_sub_formula_cell->next_st_bucket_cell)
	      disting_formula = set_expr_bucket(PROP_AND_OP,
						disting_formula,
						disting_sub_formula_cell->st_bucket,
						NULL,
						0.0L,
						NULL,
						FALSE);
	  /* compute the distinguishing formula for the two inequivalent global states by prefixing their */
	  /* distinguishing subformula with a suitable modality */
	  if (curr_inequiv_global_state_cell->disting_act_type != NULL)
	  {
	    if (!curr_inequiv_global_state_cell->left &&
	        (disting_formula->info.expr->op_index == PROP_NOT_OP))
	      disting_formula =
	        set_expr_bucket((option_index == STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK)?
			          MODAL_MUST_OP:
			          (((option_index == WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK) ||
				    (option_index == NON_INTERFERENCE_ANALYSIS) ||
				    (option_index == NON_DED_ON_COMP_ANALYSIS))?
				     MODAL_WEAK_MUST_OP:
				     ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK)?
				        MODAL_MUST_PROB_OP:
				        MODAL_WEAK_MUST_PROB_OP)),
			        disting_formula->info.expr->opn1,
			        NULL,
			        NULL,
			        curr_inequiv_global_state_cell->disting_aggr_rate,
			        alloc_value_cell(curr_inequiv_global_state_cell->disting_act_type,
					         (long double)(curr_inequiv_global_state_cell->
							         disting_rate_index),
					         NULL,
					         NULL),
			        FALSE);
	    else
	    {
	      disting_formula =
	        set_expr_bucket((option_index == STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK)?
			          MODAL_CAN_OP:
				  (((option_index == WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK) ||
				    (option_index == NON_INTERFERENCE_ANALYSIS) ||
				    (option_index == NON_DED_ON_COMP_ANALYSIS))?
				    MODAL_WEAK_CAN_OP:
				    ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK)?
				       MODAL_CAN_PROB_OP:
				       MODAL_WEAK_CAN_PROB_OP)),
				disting_formula,
				NULL,
				NULL,
			        curr_inequiv_global_state_cell->disting_aggr_rate,
				alloc_value_cell(curr_inequiv_global_state_cell->disting_act_type,
						 (long double)(curr_inequiv_global_state_cell->
								disting_rate_index),
						 NULL,
						 NULL),
				FALSE);
	      if (!curr_inequiv_global_state_cell->left)
	        disting_formula = set_expr_bucket(PROP_NOT_OP,
					          disting_formula,
					          NULL,
					          NULL,
					          0.0L,
					          NULL,
					          FALSE);
	    }
	  }
	  /* report the computed distinguishing formula for the two inequivalent global states to the */
	  /* two reaching inequivalent global states */
	  if ((curr_inequiv_global_state_cell->father_inequiv_gs_cell != NULL) &&
	      (curr_inequiv_global_state_cell->disting_act_type != NULL))
	  {
	    father_inequiv_global_state_cell = curr_inequiv_global_state_cell->father_inequiv_gs_cell;
	    if (curr_inequiv_global_state_cell->outer_global_state !=
		  father_inequiv_global_state_cell->curr_outer_reached_global_state)
	    {
	      if (father_inequiv_global_state_cell->curr_outer_reached_global_state != NULL)
	      {
		if ((father_inequiv_global_state_cell->min_disting_sub_formula_num == -1) ||
		    (father_inequiv_global_state_cell->disting_sub_formula_num <=
		       father_inequiv_global_state_cell->min_disting_sub_formula_num))
		{
		  if (father_inequiv_global_state_cell->disting_sub_formula_num ==
		        father_inequiv_global_state_cell->min_disting_sub_formula_num)
		  {
		    if (check_unordered_lists_equality(father_inequiv_global_state_cell->
							 disting_sub_formula_list,
						       father_inequiv_global_state_cell->
						         min_disting_sub_formula_list))
		      father_inequiv_global_state_cell->disting_aggr_rate +=
		        father_inequiv_global_state_cell->curr_outer_reached_global_state_incoming_rate;
		  }
		  else
		  {
	            if (father_inequiv_global_state_cell->min_disting_sub_formula_num >= 0)
		      father_inequiv_global_state_cell->disting_aggr_rate =
		        father_inequiv_global_state_cell->curr_outer_reached_global_state_incoming_rate;
		    father_inequiv_global_state_cell->min_disting_sub_formula_num =
		      father_inequiv_global_state_cell->disting_sub_formula_num;
		    if (father_inequiv_global_state_cell->min_disting_sub_formula_list != NULL)
		      free_st_bucket_list(father_inequiv_global_state_cell->min_disting_sub_formula_list);
		    father_inequiv_global_state_cell->min_disting_sub_formula_list =
		      father_inequiv_global_state_cell->disting_sub_formula_list;
		  }
		}
	        father_inequiv_global_state_cell->disting_sub_formula_num = 0;
	        father_inequiv_global_state_cell->disting_sub_formula_list = NULL;
	      }
	      father_inequiv_global_state_cell->curr_outer_reached_global_state =
		curr_inequiv_global_state_cell->outer_global_state;
	      father_inequiv_global_state_cell->curr_outer_reached_global_state_incoming_rate =
		curr_inequiv_global_state_cell->outer_incoming_rate;
	    }
	    if (!check_list_membership(disting_formula,
				       father_inequiv_global_state_cell->disting_sub_formula_list,
				       FALSE))
	    {
	      father_inequiv_global_state_cell->disting_sub_formula_list =
		alloc_st_bucket_cell(disting_formula,
				     father_inequiv_global_state_cell->disting_sub_formula_list);
	      (father_inequiv_global_state_cell->disting_sub_formula_num)++;
	    }
	  }
	}
	return(disting_formula);
}


void		print_disting_formula(FILE    *result_file,
				      char    *disting_formula,
				      int     *pos,
				      int     align,
				      BOOLEAN preceding_not,
				      BOOLEAN *preceding_true)
{
	int		old_align,
			paren_num,
			i,
			j;

	switch (disting_formula[*pos])
	{
	  case 'E':
	  case 'F':
	    old_align = align;
	    if (!preceding_not)
	    {
	      fprintf(result_file,
		      "\n\t");
	      for (i = 1;
		   (i <= align);
		   i++)
	        fprintf(result_file,
		        " ");
	    }
	    do
	    {
	      fprintf(result_file,
		      "%c",
		      disting_formula[*pos]);
	      (*pos)++;
	    }
	    while (disting_formula[*pos] != '(');
	    fprintf(result_file,
		    "(");
	    (*pos)++;
	    align += 2;
	    print_disting_formula(result_file,
				  disting_formula,
				  pos,
				  align,
				  FALSE,
				  preceding_true);
	    fprintf(result_file,
		    "; ");
	    *pos += 2;
	    print_disting_formula(result_file,
				  disting_formula,
				  pos,
				  align,
				  FALSE,
				  preceding_true);
	    if (disting_formula[*pos] == ';')
	    {
	      fprintf(result_file,
		      "; ");
	      *pos += 2;
	      print_disting_formula(result_file,
				    disting_formula,
				    pos,
				    align,
				    FALSE,
				    preceding_true);
	    }
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= old_align);
		 i++)
	      fprintf(result_file,
		      " ");
	    fprintf(result_file,
		    ")");
	    (*pos)++;
	    break;
	  case 'L':
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= align);
		 i++)
	      fprintf(result_file,
		      " ");
	    do
	    {
	      fprintf(result_file,
		      "%c",
		      disting_formula[*pos]);
	      (*pos)++;
	    }
	    while (disting_formula[*pos] != ')');
	    fprintf(result_file,
		    ")");
	    (*pos)++;
	    break;
	  case 'M':
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= align);
		 i++)
	      fprintf(result_file,
		      " ");
	    do
	    {
	      fprintf(result_file,
		      "%c",
		      disting_formula[*pos]);
	      (*pos)++;
	    }
	    while (disting_formula[*pos] != ')');
	    fprintf(result_file,
		    ")");
	    (*pos)++;
	    break;
	  case 'R':
	    old_align = align;
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= align);
		 i++)
	      fprintf(result_file,
		      " ");
	    do
	    {
	      fprintf(result_file,
		      "%c",
		      disting_formula[*pos]);
	      (*pos)++;
	    }
	    while (disting_formula[*pos] != '(');
	    fprintf(result_file,
		    "(");
	    (*pos)++;
	    align += 2;
	    for (paren_num = 0;
		 (disting_formula[*pos] == '(');
		 paren_num++,
		 (*pos)++);
	    print_disting_formula(result_file,
				  disting_formula,
				  pos,
				  align,
				  FALSE,
				  preceding_true);
	    if (paren_num == 0)
	    {
	      if (disting_formula[*pos] == ' ')
	      {
	        *pos += 4;
	        fprintf(result_file,
		        " /\\");
	        print_disting_formula(result_file,
				      disting_formula,
				      pos,
				      align,
				      FALSE,
				      preceding_true);
	      }
	    }
	    else
	    {
	      for (j = paren_num + 1,
		   (*pos)--;
		   (j >= 1);
		   j--)
	      {
	        *pos += 5;
	        fprintf(result_file,
		        " /\\");
	        print_disting_formula(result_file,
				      disting_formula,
				      pos,
				      align,
				      FALSE,
				      preceding_true);
	      }
	    }
	    if (!*preceding_true)
	    {
	      fprintf(result_file,
		      "\n\t");
	      for (i = 1;
		   (i <= old_align);
		   i++)
	        fprintf(result_file,
		        " ");
	    }
	    else
	      *preceding_true = FALSE;
	    fprintf(result_file,
		    ")");
	    (*pos)++;
	    break;
	  case 'N':
	    old_align = align;
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= align);
		 i++)
	      fprintf(result_file,
		      " ");
	    do
	    {
	      fprintf(result_file,
		      "%c",
		      disting_formula[*pos]);
	      (*pos)++;
	      align++;
	    }
	    while (disting_formula[*pos] != '(');
	    fprintf(result_file,
		    "(");
	    (*pos)++;
	    align++;
	    print_disting_formula(result_file,
				  disting_formula,
				  pos,
				  align,
				  TRUE,
				  preceding_true);
	    fprintf(result_file,
		    "\n\t");
	    for (i = 1;
		 (i <= old_align);
		 i++)
	      fprintf(result_file,
		      " ");
	    fprintf(result_file,
		    ")");
	    (*pos)++;
	    break;
	  case 'T':
	    *pos += 4;
	    fprintf(result_file,
		    "TRUE");
	    *preceding_true = TRUE;
	    break;
	  default:
	    break;
	}
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		remove_inv_immediate_selfloops(GST_BUCKET *global_state)
{
	BOOLEAN		removed;
	GST_BUCKET_CELL	*prev_global_state_cell,
			*curr_global_state_cell;
	G_TRANS_CELL	*prev_global_trans_cell,
			*curr_global_trans_cell;

	/* remove the invisible immediate selfloops */
	for (prev_global_trans_cell = curr_global_trans_cell = global_state->global_trans_list,
	     removed = FALSE;
	     (curr_global_trans_cell != NULL);
	     )
	  if (curr_global_trans_cell->der_global_state == global_state)
	  {
	    removed = TRUE;
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

	/* remove the global state from the list of global states reaching it */
	if (removed)
	  for (prev_global_state_cell = curr_global_state_cell = global_state->reaching_global_state_list,
	       removed = FALSE;
	       ((curr_global_state_cell != NULL) &&
		!removed);
	       )
	  {
	    if (curr_global_state_cell->gst_bucket == global_state)
	    {
	      if (prev_global_state_cell == curr_global_state_cell)
	      {
	        global_state->reaching_global_state_list =
		  global_state->reaching_global_state_list->next_gst_bucket_cell;
	        free(curr_global_state_cell);
	      }
	      else
	      {
	        prev_global_state_cell->next_gst_bucket_cell = curr_global_state_cell->next_gst_bucket_cell;
	        free(curr_global_state_cell);
	      }
	      removed = TRUE;
	    }
	    else
	    {
	      prev_global_state_cell = curr_global_state_cell;
	      curr_global_state_cell = curr_global_state_cell->next_gst_bucket_cell;
	    }
	  }
}


void		sort_global_trans_lists(void)
{
	GST_BUCKET	*global_state,
			*der_global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell;
	G_TRANS_CELL	*dest_global_trans_cell,
			*global_trans_cell,
			*min_global_trans_cell;
	ST_BUCKET	*action;
	VP_INFO		*value_passing_info;
	REWARD_INFO	reward_info;
	int		comparison;

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[table_no]->info.archi_type->init_global_state->global_trans_considered =
	  (option_index != NON_DED_ON_COMP_ANALYSIS)?
	    FALSE:
	    TRUE;
        while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* sort the global transition list of the global state in lexicographical order */
	  for (dest_global_trans_cell = global_state->global_trans_list;
	       (dest_global_trans_cell != NULL);
	       dest_global_trans_cell = dest_global_trans_cell->next_g_trans_cell)
	  {
	    for (global_trans_cell = dest_global_trans_cell->next_g_trans_cell,
	         min_global_trans_cell = dest_global_trans_cell;
	         (global_trans_cell != NULL);
	         global_trans_cell = global_trans_cell->next_g_trans_cell)
	    {
	      comparison = strcmp(global_trans_cell->action->info.action->type->symbol_lexeme,
				  min_global_trans_cell->action->info.action->type->symbol_lexeme);
	      if ((comparison < 0) ||
		  ((comparison == 0) &&
		   ((global_trans_cell->action->info.action->rate_index <
		       min_global_trans_cell->action->info.action->rate_index) ||
		    (global_trans_cell->action->info.action->priority->info.expr->value <
		       min_global_trans_cell->action->info.action->priority->info.expr->value))))
	        min_global_trans_cell = global_trans_cell;
	    }
	    if (min_global_trans_cell != dest_global_trans_cell)
	    {
	      action = dest_global_trans_cell->action;
	      dest_global_trans_cell->action = min_global_trans_cell->action;
	      min_global_trans_cell->action = action;
	      value_passing_info = dest_global_trans_cell->value_passing_info;
	      dest_global_trans_cell->value_passing_info = min_global_trans_cell->value_passing_info;
	      min_global_trans_cell->value_passing_info = value_passing_info;
	      reward_info = dest_global_trans_cell->reward_info;
	      dest_global_trans_cell->reward_info = min_global_trans_cell->reward_info;
	      min_global_trans_cell->reward_info = reward_info;
	      der_global_state = dest_global_trans_cell->der_global_state;
	      dest_global_trans_cell->der_global_state = min_global_trans_cell->der_global_state;
	      min_global_trans_cell->der_global_state = der_global_state;
	    }
	  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (((option_index != NON_DED_ON_COMP_ANALYSIS) &&
		 global_trans_cell->der_global_state->global_trans_considered) ||
                ((option_index == NON_DED_ON_COMP_ANALYSIS) &&
		 !global_trans_cell->der_global_state->global_trans_considered))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered =
		(option_index != NON_DED_ON_COMP_ANALYSIS)?
	  	  FALSE:
	  	  TRUE;
            }
	}
}


void		create_init_partition(void)
{
	GST_BUCKET	*global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell;
	G_TRANS_CELL	*global_trans_cell;
	ST_BUCKET	*aggr_global_trans;

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[table_no]->info.archi_type->init_global_state->global_trans_considered =
	  (option_index != NON_DED_ON_COMP_ANALYSIS)?
	    TRUE:
	    FALSE;
        while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack */
	  aggr_global_trans = compute_aggr_global_trans(global_state,
							NULL,
							part_block_tree);
	  aggr_global_trans->info.aggr_global_trans->part_block->global_state_list =
	    alloc_g_state_cell(global_state,
			       aggr_global_trans->info.aggr_global_trans->part_block->global_state_list);
	  membership_vector[table_no][global_state->global_state_no] =
	    aggr_global_trans->info.aggr_global_trans->part_block;
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (((option_index != NON_DED_ON_COMP_ANALYSIS) &&
		 !global_trans_cell->der_global_state->global_trans_considered) ||
                ((option_index == NON_DED_ON_COMP_ANALYSIS) &&
		 global_trans_cell->der_global_state->global_trans_considered))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered =
		(option_index != NON_DED_ON_COMP_ANALYSIS)?
		  TRUE:
		  FALSE;
            }
	}
}


void		refine_partition(void)
{
	BOOLEAN		self_to_be_ref,
			found;
	G_STATE_CELL	*refining_global_state_cell,
			*global_state_cell,
			*aux_global_state_cell;
	GST_BUCKET_CELL *global_state_bucket_cell;
	PART_BLOCK	*refining_part_block,
			*part_block;
	PART_BLOCK_CELL	*part_block_cell,
			*splitter_cell,
			*to_be_ref_part_block_list,
			*to_be_ref_part_block_cell,
			*aux_to_be_ref_part_block_cell;
	ST_BUCKET	*aggr_global_trans;
	ST_BUCKET_CELL	*aggr_global_trans_list,
			*aggr_global_trans_cell,
			*last_aggr_global_trans_cell;

	/* select a refining partition block from the splitter list */
	refining_part_block = splitter_list->part_block;
	splitter_cell = splitter_list;
	splitter_list = splitter_list->next_part_block_cell;
	free(splitter_cell);

	/* detect the blocks in the current partition to be refined w.r.t. the selected refining partition */
	/* block (each such partition block must contain more than one global state) */
	for (refining_global_state_cell = refining_part_block->global_state_list,
	     to_be_ref_part_block_list = NULL,
	     self_to_be_ref = FALSE;
	     (refining_global_state_cell != NULL);
	     refining_global_state_cell = refining_global_state_cell->next_g_state_cell)
	  for (global_state_bucket_cell =
		 refining_global_state_cell->global_state->reaching_global_state_list;
	       (global_state_bucket_cell != NULL);
	       global_state_bucket_cell = global_state_bucket_cell->next_gst_bucket_cell)
	  {
	    part_block = membership_vector[(int)(global_state_bucket_cell->gst_bucket->table_no)]
					  [global_state_bucket_cell->gst_bucket->global_state_no];
	    if (part_block->global_state_list->next_g_state_cell != NULL)
	    {
	      if (part_block == refining_part_block)
		self_to_be_ref = TRUE;
	      else
	      {
	        for (to_be_ref_part_block_cell = to_be_ref_part_block_list,
		     found = FALSE;
		     ((to_be_ref_part_block_cell != NULL) &&
		      !found);
		     to_be_ref_part_block_cell = to_be_ref_part_block_cell->next_part_block_cell)
	          found = (to_be_ref_part_block_cell->part_block == part_block);
	        if (!found)
	          to_be_ref_part_block_list = alloc_part_block_cell(part_block,
								    to_be_ref_part_block_list);
	      }
	    }
	  }

	/* split each partition block to be refined and update the current partition and the splitter list */
	/* (in the case a partition block to be refined is split into more than one sub-block) */
	for (to_be_ref_part_block_cell = to_be_ref_part_block_list,
	     aux_to_be_ref_part_block_cell = (to_be_ref_part_block_cell == NULL)?
					       NULL:
					       to_be_ref_part_block_cell->next_part_block_cell;
	     (to_be_ref_part_block_cell != NULL);
	     to_be_ref_part_block_cell = aux_to_be_ref_part_block_cell,
	     aux_to_be_ref_part_block_cell = (to_be_ref_part_block_cell == NULL)?
					       NULL:
					       to_be_ref_part_block_cell->next_part_block_cell)

	{
	  refinement_phase++;
	  for (global_state_cell = to_be_ref_part_block_cell->part_block->global_state_list,
	       aux_global_state_cell = (global_state_cell == NULL)?
					 NULL:
					 global_state_cell->next_g_state_cell;
	       (global_state_cell != NULL);
	       global_state_cell = aux_global_state_cell,
	       aux_global_state_cell = (global_state_cell == NULL)?
					 NULL:
					 global_state_cell->next_g_state_cell)
	  {
	    table_no = global_state_cell->global_state->table_no;
	    aggr_global_trans = compute_aggr_global_trans(global_state_cell->global_state,
							  refining_part_block,
							  to_be_ref_part_block_cell->part_block);
	    move_global_state(global_state_cell,
			      &(to_be_ref_part_block_cell->part_block->global_state_list),
			      &(aggr_global_trans->info.aggr_global_trans->part_block->global_state_list));
	    membership_vector[table_no][global_state_cell->global_state->global_state_no] =
	      aggr_global_trans->info.aggr_global_trans->part_block;
	  }
	  if (to_be_ref_part_block_cell->part_block->children_part_block_list->next_part_block_cell != NULL)
	    for (part_block_cell = to_be_ref_part_block_cell->part_block->children_part_block_list;
	         (part_block_cell != NULL);
	         part_block_cell = part_block_cell->next_part_block_cell)
	    {
	      part_block_list = alloc_part_block_cell(part_block_cell->part_block,
						      part_block_list);
	      splitter_list = alloc_part_block_cell(part_block_cell->part_block,
						    splitter_list);
	    }
	  else
	  {
	    for (part_block_cell = part_block_list;
	         (part_block_cell->part_block != to_be_ref_part_block_cell->part_block);
	         part_block_cell = (part_block_cell->part_block != to_be_ref_part_block_cell->part_block)?
		 		     part_block_cell->next_part_block_cell:
				     part_block_cell);
	    part_block_cell->part_block =
	      to_be_ref_part_block_cell->part_block->children_part_block_list->part_block;
	    for (part_block_cell = splitter_list;
	         ((part_block_cell != NULL) &&
		  (part_block_cell->part_block != to_be_ref_part_block_cell->part_block));
	         part_block_cell = (part_block_cell->part_block != to_be_ref_part_block_cell->part_block)?
		   		     part_block_cell->next_part_block_cell:
				     part_block_cell);
	    if (part_block_cell != NULL)
	      part_block_cell->part_block =
		to_be_ref_part_block_cell->part_block->children_part_block_list->part_block;
	  }
	  free(to_be_ref_part_block_cell);
	}

	/* split the refining partition block if needed and update the current partition and the splitter */
	/* list (in the case the refining partition block is split into more than one sub-block) */
	if (self_to_be_ref)
	{
	  refinement_phase++;
	  for (global_state_cell = refining_part_block->global_state_list,
	       aggr_global_trans_list = last_aggr_global_trans_cell = NULL;
	       (global_state_cell != NULL);
	       global_state_cell = global_state_cell->next_g_state_cell)
	  {
	    table_no = global_state_cell->global_state->table_no;
	    aggr_global_trans = compute_aggr_global_trans(global_state_cell->global_state,
							  refining_part_block,
							  refining_part_block);
	    if (aggr_global_trans_list == NULL)
	      aggr_global_trans_list = last_aggr_global_trans_cell =
		alloc_st_bucket_cell(aggr_global_trans,
				     NULL);
	    else
	      last_aggr_global_trans_cell = last_aggr_global_trans_cell->next_st_bucket_cell =
		alloc_st_bucket_cell(aggr_global_trans,
				     NULL);
	  }
	  for (global_state_cell = refining_part_block->global_state_list,
	       aux_global_state_cell = (global_state_cell == NULL)?
					 NULL:
					 global_state_cell->next_g_state_cell,
	       aggr_global_trans_cell = aggr_global_trans_list;
	       (global_state_cell != NULL);
	       global_state_cell = aux_global_state_cell,
	       aux_global_state_cell = (global_state_cell == NULL)?
					 NULL:
					 global_state_cell->next_g_state_cell,
	       aggr_global_trans_cell = aggr_global_trans_cell->next_st_bucket_cell)
	  {
	    move_global_state(global_state_cell,
			      &(refining_part_block->global_state_list),
			      &(aggr_global_trans_cell->st_bucket->info.aggr_global_trans->part_block
				  ->global_state_list));
	    membership_vector[(int)(global_state_cell->global_state->table_no)]
			     [global_state_cell->global_state->global_state_no] =
	      aggr_global_trans_cell->st_bucket->info.aggr_global_trans->part_block;
	  }
	  free_st_bucket_list(aggr_global_trans_list);
	  if (refining_part_block->children_part_block_list->next_part_block_cell != NULL)
	    for (part_block_cell = refining_part_block->children_part_block_list;
	         (part_block_cell != NULL);
	         part_block_cell = part_block_cell->next_part_block_cell)
	    {
	      part_block_list = alloc_part_block_cell(part_block_cell->part_block,
						      part_block_list);
	      splitter_list = alloc_part_block_cell(part_block_cell->part_block,
						    splitter_list);
	    }
	  else
	  {
	    for (part_block_cell = part_block_list;
	         (part_block_cell->part_block != refining_part_block);
	         part_block_cell = (part_block_cell->part_block != refining_part_block)?
		   		     part_block_cell->next_part_block_cell:
				     part_block_cell);
	    part_block_cell->part_block = refining_part_block->children_part_block_list->part_block;
	  }
	}
}


ST_BUCKET	*compute_aggr_global_trans(GST_BUCKET *global_state,
					   PART_BLOCK *refining_part_block,
					   PART_BLOCK *father_part_block)
{
		AGGR_RATE_CELL	*aggr_rate_list;
		GST_BUCKET	*der_global_state;
		G_TRANS_CELL	*global_trans_cell;
		PART_BLOCK	*der_part_block;
		RATE_INDEX	rate_index;
		ST_BUCKET	*aggr_global_trans,
				*act_type;
		ST_BUCKET_CELL	*act_type_list;
	static	char		*aggr_global_trans_string	=	NULL;
		int		length,
				aux_table_no;
	static	int		max_length			=	0;
		long double	aggr_rate,
				total_weight;

	/* compute the aggregated global transition string of the global state towards the global states */
	/* in the refining partition block; since the refining partition block itself can be split during */
	/* a refinement phase, the reachability check considers not only the refining partition block but */
	/* also its children in the partition block tree */
	for (global_trans_cell = global_state->global_trans_list,
	     length = 0,
	     act_type = (global_trans_cell == NULL)?
		          NULL:
		          global_trans_cell->action->info.action->type,
	     rate_index = (global_trans_cell == NULL)?
		            NO_RATE:
		            (RATE_INDEX)(global_trans_cell->action->info.action->rate_index),
	     aggr_rate = total_weight = 0.0L,
	     act_type_list = NULL,
	     aggr_rate_list = NULL;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	{
	  der_global_state = global_trans_cell->der_global_state;
	  der_part_block = membership_vector[(int)(der_global_state->table_no)]
					    [der_global_state->global_state_no];
	  if (global_trans_cell->action->info.action->type == act_type)
	  {
	    if ((refining_part_block == NULL) ||
	        (der_part_block == refining_part_block))
	      aggr_rate += global_trans_cell->action->info.action->rate->info.expr->value;
	    total_weight += (rate_index == PASSIVE)?
			      global_trans_cell->action->info.action->rate->info.expr->value:
			      0.0L;
	  }
	  else
	  {
	    if ((aggr_rate != 0.0L) &&
	        ((option_index != NON_DED_ON_COMP_ANALYSIS) ||
	         (act_type->info.act_type == NULL) ||
	         (act_type->info.act_type->variation != MADE_HIGH)))
	      update_aggr_global_trans(global_state,
				       &aggr_global_trans_string,
				       &length,
				       &max_length,
				       &act_type_list,
				       act_type,
				       &aggr_rate_list,
				       rate_index,
				       aggr_rate,
				       total_weight);
	    act_type = global_trans_cell->action->info.action->type;
	    rate_index = (RATE_INDEX)(global_trans_cell->action->info.action->rate_index);
	    aggr_rate = 0.0L;
	    if ((refining_part_block == NULL) ||
	        (der_part_block == refining_part_block))
	      aggr_rate = global_trans_cell->action->info.action->rate->info.expr->value;
	    total_weight = (rate_index == PASSIVE)?
			     global_trans_cell->action->info.action->rate->info.expr->value:
			     0.0L;
	  }
	}
	if ((aggr_rate != 0.0L) &&
	    ((option_index != NON_DED_ON_COMP_ANALYSIS) ||
	     (act_type->info.act_type == NULL) ||
	     (act_type->info.act_type->variation != MADE_HIGH)))
	  update_aggr_global_trans(global_state,
				   &aggr_global_trans_string,
				   &length,
				   &max_length,
				   &act_type_list,
				   act_type,
				   &aggr_rate_list,
				   rate_index,
				   aggr_rate,
				   total_weight);
	aux_table_no = table_no;
	table_no = 0;
	aggr_global_trans = (ST_BUCKET *)search_lexeme_table((length != 0)?
							       aggr_global_trans_string:
							       " ",
							     SYT);
	table_no = aux_table_no;

	/* update the partition block tree */
	if ((aggr_global_trans->info.aggr_global_trans == NULL) ||
	    (aggr_global_trans->info.aggr_global_trans->refinement_phase < refinement_phase))
	{
	  father_part_block->refining_part_block = (refining_part_block == NULL)?
						     part_block_tree:
						     refining_part_block;
	  father_part_block->children_part_block_list =
	    alloc_part_block_cell(alloc_part_block(father_part_block),
				  father_part_block->children_part_block_list);
	  father_part_block->aggr_global_trans_list =
	    alloc_st_bucket_cell(aggr_global_trans,
				 father_part_block->aggr_global_trans_list);
	  if (aggr_global_trans->info.aggr_global_trans == NULL)
	  {
	    aggr_global_trans->symbol_index = AGGR_GLOBAL_TRANS_STRING;
	    aggr_global_trans->info.aggr_global_trans =
	      alloc_aggr_g_trans(father_part_block->children_part_block_list->part_block,
				 act_type_list,
				 aggr_rate_list,
				 refinement_phase);
	  }
	  else
	  {
	    aggr_global_trans->info.aggr_global_trans->part_block =
	      father_part_block->children_part_block_list->part_block;
	    aggr_global_trans->info.aggr_global_trans->refinement_phase = refinement_phase;
	  }
	}
	return(aggr_global_trans);
}


void		update_aggr_global_trans(GST_BUCKET     *global_state,
					 char           **aggr_global_trans_string,
					 int            *length,
					 int            *max_length,
					 ST_BUCKET_CELL **act_type_list,
					 ST_BUCKET      *act_type,
					 AGGR_RATE_CELL **aggr_rate_list,
					 RATE_INDEX     rate_index,
					 long double    aggr_rate,
					 long double    total_weight)
{
	BOOLEAN		nonempty_global_trans_string_to_free;
	G_TRANS_CELL	*global_trans_cell;
	char		*aux_aggr_global_trans_string;
	int		aux_length;

	/* set the parameters of the actions to be added to the aggregated global transition */
	*act_type_list = alloc_st_bucket_cell(act_type,
					      *act_type_list);
	if (rate_index == IMMEDIATE)
	  for (global_trans_cell = global_state->global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if ((RATE_INDEX)(global_trans_cell->action->info.action->rate_index) == IMMEDIATE)
	      total_weight += global_trans_cell->action->info.action->rate->info.expr->value;
	if (rate_index != EXP_TIMED)
	  aggr_rate /= total_weight;
	if ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	    (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))
	  *aggr_rate_list = alloc_aggr_rate_cell(aggr_rate,
			 			 rate_index,
						 *aggr_rate_list);

	/* update the string of the aggregated global transition */
	aux_aggr_global_trans_string = *aggr_global_trans_string;
	aux_length = *length;
	nonempty_global_trans_string_to_free = FALSE;
	*length = ((*length != 0)?
		     (*length + 1):
		     0) +
		  strlen(act_type->symbol_lexeme) +
		  (((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
		    (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK)) ?
		     (3 + E_FORMAT_LENGTH):
		     2);
	if (*length > *max_length)
	{
	  if (aux_aggr_global_trans_string != NULL)
	    nonempty_global_trans_string_to_free = TRUE;
          *aggr_global_trans_string = alloc_string(*length);
          *max_length = *length;
        }
	if (aux_length != 0)
	{
	  if ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	      (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))
	    sprintf(*aggr_global_trans_string,
		    "%s %s %d %e",
		    aux_aggr_global_trans_string,
		    act_type->symbol_lexeme,
		    (int)rate_index,
		    (double)aggr_rate);
	  else
	    sprintf(*aggr_global_trans_string,
		    "%s %s *",
		    aux_aggr_global_trans_string,
		    act_type->symbol_lexeme);
	}
	else
	{
	  if ((option_index == STRONG_MARKOVIAN_BISIM_EQUIV_CHECK) ||
	      (option_index == WEAK_MARKOVIAN_BISIM_EQUIV_CHECK))
	    sprintf(*aggr_global_trans_string,
		    "%s %d %e",
		    act_type->symbol_lexeme,
		    (int)rate_index,
		    (double)aggr_rate);
	  else
	    sprintf(*aggr_global_trans_string,
		    "%s *",
		    act_type->symbol_lexeme);
	}
	if (nonempty_global_trans_string_to_free)
	  free(aux_aggr_global_trans_string);
}


void		move_global_state(G_STATE_CELL *global_state_cell,
				  G_STATE_CELL **src_global_state_list,
				  G_STATE_CELL **dst_global_state_list)
{
	/* remove the global state from the source list */
	if (global_state_cell->prev_g_state_cell != NULL)
	{
	  global_state_cell->prev_g_state_cell->next_g_state_cell = global_state_cell->next_g_state_cell;
	  if (global_state_cell->next_g_state_cell != NULL)
	    global_state_cell->next_g_state_cell->prev_g_state_cell = global_state_cell->prev_g_state_cell;
	}
	else
	{
	  *src_global_state_list = global_state_cell->next_g_state_cell;
	  if (global_state_cell->next_g_state_cell != NULL)
	    global_state_cell->next_g_state_cell->prev_g_state_cell = NULL;
	}

	/* insert the global state into the destination list */
	global_state_cell->next_g_state_cell = *dst_global_state_list;
	if (*dst_global_state_list != NULL)
	  (*dst_global_state_list)->prev_g_state_cell = global_state_cell;
	global_state_cell->prev_g_state_cell = NULL;
	*dst_global_state_list = global_state_cell;
}


BOOLEAN		check_block_membership(GST_BUCKET *global_state,
				       PART_BLOCK *part_block)
{
	BOOLEAN		member;
	PART_BLOCK_CELL	*children_part_block_cell;

	if (membership_vector[(int)(global_state->table_no)][global_state->global_state_no] == part_block)
	  member = TRUE;
	else
	  for (children_part_block_cell = part_block->children_part_block_list,
	       member = FALSE;
	       ((children_part_block_cell != NULL) &&
		!member);
	       children_part_block_cell = children_part_block_cell->next_part_block_cell)
	    member = check_block_membership(global_state,
					    children_part_block_cell->part_block);
	return(member);
}


BOOLEAN		check_stack_membership(GST_BUCKET      *outer_global_state,
				       GST_BUCKET      *inner_global_state,
				       INEQUIV_GS_CELL *father_inequiv_global_state_cell)
{
	BOOLEAN		member;
	INEQUIV_GS_CELL	*inequiv_global_state_cell;

	for (inequiv_global_state_cell = father_inequiv_global_state_cell,
	     member = FALSE;
	     ((inequiv_global_state_cell != NULL) &&
	      !member);
	     inequiv_global_state_cell = inequiv_global_state_cell->father_inequiv_gs_cell)
	  member = (((inequiv_global_state_cell->outer_global_state == outer_global_state) &&
		     (inequiv_global_state_cell->inner_global_state == inner_global_state)) ||
		    ((inequiv_global_state_cell->outer_global_state == inner_global_state) &&
		     (inequiv_global_state_cell->inner_global_state == outer_global_state)));
	return(member);
}
