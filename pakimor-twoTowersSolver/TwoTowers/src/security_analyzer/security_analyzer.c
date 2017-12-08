/****************************************************************/
/*								*/
/*								*/
/*		     security_analyzer.c			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module checks an .aem spec file for noninterference or nondeducibility on composition, and returns */
/* diagnostic information in the form of a modal logic formula in the case of property violation. */
/* This module contains the following functions: */
/* - analyze_non_interference(): It verifies the noninterference security property and writes the result */
/*				 to the .sar file. */
/* - analyze_non_ded_on_comp(): It verifies the nondeducibility on composition security property and */
/*				writes the result to the .sar file. */
/* and the following auxiliary functions: */
/* - build_second_archi_type(): It builds the second architectural type used by the equivalence checker, */
/*				by sharing all the information related to neither the local state spaces */
/*				nor the global state space. */
/* - hide_all_non_high_non_low(): It hides all the action types that are neither high nor low. */
/* - record_high_global_state_pairs(): It records every pair of global states such that the first one */
/*				       reaches the second one through a global transition labeled with a */
/*				       high action type. */
/* - check_high_global_state_pairs(): It checks whether every two global states, such that the first one */
/*				      reaches the second one through a global transition labeled with a */
/*				      high action type, satisfy the nondeducibility on composition */
/*				      property and writes the result to the .sar file. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<stdio.h>
#include	<stdlib.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/aemilia_compiler.h"

#include	"../headers/equivalence_verifier.h"

#include	"../headers/file_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		analyze_non_interference(char *),
		analyze_non_ded_on_comp(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	FILE		*sar_file;
				/* .sar file containing the result of the security analysis; */
				/* set by ; */
				/* used in */


PRIVATE	void		build_second_archi_type(void),
			hide_all_non_high_non_low(void);

PRIVATE	GST_BUCKT2_CELL	*record_high_global_state_pairs(void);

PRIVATE	void		check_high_global_state_pairs(GST_BUCKT2_CELL *);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		analyze_non_interference(char *aem_path)
{
	BOOLEAN		positive_outcome,
			preceding_true;
	ST_BUCKET	*disting_formula;
	int		pos;

	/* hide all the actions that are irrelevant for the security analysis and prepare another */
	/* architectural type */
	hide_all_non_high_non_low();
	build_second_archi_type();

	/* generate the weak functional global transition relation for the .aem spec with the high actions */
	/* hidden */
	spec_no = 0;
	table_no = 0;
        generate_aei_state_spaces();
        generate_global_state_space();
	generate_weak_functional_global_trans_rel();

	/* generate the weak functional global transition relation for the .aem spec with the high actions */
	/* restricted after sharing the hash tables not related to the local and global state spaces */
	share_non_state_space_hash_tables();
	spec_no = 1;
	table_no = 1;
        generate_aei_state_spaces();
        generate_global_state_space();
	generate_weak_functional_global_trans_rel();

	/* check for weak bisimulation equivalence */
	spec_no = 0;
	table_no = 0;
	positive_outcome = check_bisim_equiv();

	/* print the result to the .sar file */
	sar_file = new_fopen(aem_path,
			     ".sar",
			     "w");
	if (positive_outcome)
	  fprintf(sar_file,
		  "%s satisfies the non-interference property.\n",
		  archi_type[0]->symbol_lexeme);
	else
	{
	  fprintf(sar_file,
		  "%s violates the non-interference property",
		  archi_type[0]->symbol_lexeme);
	  fprintf(sar_file,
		  "\nas demonstrated by the following modal logic formula");
	  fprintf(sar_file,
		  "\nsatisfied when the high security actions are hidden");
	  fprintf(sar_file,
		  "\nbut not when the high security actions are restricted:\n\t");
	  table_no = 0;
	  disting_formula = compute_disting_formula(archi_type[0]->info.archi_type->init_global_state,
						    archi_type[1]->info.archi_type->init_global_state);
	  pos = 0;
	  preceding_true = FALSE;
	  print_disting_formula(sar_file,
				disting_formula->symbol_lexeme,
				&pos,
				0,
				FALSE,
				&preceding_true);
	}
	fclose(sar_file);
}


void		analyze_non_ded_on_comp(char *aem_path)
{
	GST_BUCKT2_CELL	*high_global_state_pair_list;

	spec_no = 0;
	table_no = 0;
	hide_all_non_high_non_low();
        generate_aei_state_spaces();
        generate_global_state_space();
	high_global_state_pair_list = record_high_global_state_pairs();
	generate_weak_functional_global_trans_rel();
	minimize_bisim_equiv();
	sar_file = new_fopen(aem_path,
			     ".sar",
			     "w");
	check_high_global_state_pairs(high_global_state_pair_list);
	fclose(sar_file);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		build_second_archi_type(void)
{
	ST_BUCKET_CELL	*aei_cell,
			*last_new_aei_cell;

	archi_type[1] = alloc_st_bucket(archi_type[0]->symbol_lexeme);
	archi_type[1]->symbol_index = archi_type[0]->symbol_index;
	archi_type[1]->defined = archi_type[0]->defined;
	archi_type[1]->used = archi_type[0]->used;
	archi_type[1]->info.archi_type = alloc_archi_type();
	archi_type[1]->info.archi_type->formal_const_par_list =
	  archi_type[0]->info.archi_type->formal_const_par_list;
	archi_type[1]->info.archi_type->init_assign_list =
	  archi_type[0]->info.archi_type->init_assign_list;
	archi_type[1]->info.archi_type->aet_list = archi_type[0]->info.archi_type->aet_list;
	for (aei_cell = archi_type[0]->info.archi_type->aei_list,
	     last_new_aei_cell = NULL;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	{
	  if (last_new_aei_cell == NULL)
	    last_new_aei_cell = archi_type[1]->info.archi_type->aei_list =
	      alloc_st_bucket_cell(alloc_st_bucket(aei_cell->st_bucket->symbol_lexeme),
				   NULL);
	  else
	    last_new_aei_cell = last_new_aei_cell->next_st_bucket_cell =
	      alloc_st_bucket_cell(alloc_st_bucket(aei_cell->st_bucket->symbol_lexeme),
				   NULL);
	  last_new_aei_cell->st_bucket->symbol_index = aei_cell->st_bucket->symbol_index;
	  last_new_aei_cell->st_bucket->defined = aei_cell->st_bucket->defined;
	  last_new_aei_cell->st_bucket->used = aei_cell->st_bucket->used;
	  last_new_aei_cell->st_bucket->info.aei =
	    alloc_aei(aei_cell->st_bucket->info.aei->aet,
		      aei_cell->st_bucket->info.aei->formal_const_par_list,
		      aei_cell->st_bucket->info.aei->behavior_list,
		      aei_cell->st_bucket->info.aei->act_type_list,
		      FALSE);
	  last_new_aei_cell->st_bucket->info.aei->aei_no = aei_cell->st_bucket->info.aei->aei_no;
	  last_new_aei_cell->st_bucket->info.aei->sync_set = aei_cell->st_bucket->info.aei->sync_set;
	  last_new_aei_cell->st_bucket->info.aei->init_state_var_assign_list =
	    aei_cell->st_bucket->info.aei->init_state_var_assign_list;
	  last_new_aei_cell->st_bucket->code = aei_cell->st_bucket->code;
	}
	archi_type[1]->info.archi_type->ai_list = archi_type[0]->info.archi_type->ai_list;
	archi_type[1]->info.archi_type->aei_num = archi_type[0]->info.archi_type->aei_num;
	archi_type[1]->info.archi_type->value_passing = archi_type[0]->info.archi_type->value_passing;
	archi_type[1]->info.archi_type->performance_closed =
	  archi_type[0]->info.archi_type->performance_closed;
	archi_type[1]->info.archi_type->psm_index = archi_type[0]->info.archi_type->psm_index;
	archi_type[1]->code = archi_type[0]->code;
}


void		hide_all_non_high_non_low(void)
{
	ST_BUCKET_CELL  *aei_cell,
			*act_type_cell,
                        *duplicate_cell;

	for (aei_cell = archi_type[0]->info.archi_type->aei_list;
             (aei_cell != NULL);
             aei_cell = aei_cell->next_st_bucket_cell)
          for (act_type_cell = aei_cell->st_bucket->info.aei->act_type_list;
               (act_type_cell != NULL);
               act_type_cell = act_type_cell->next_st_bucket_cell)
            if (((act_type_cell->st_bucket->info.act_type->renamed == NULL) &&
                 (act_type_cell->st_bucket->info.act_type->variation == UNVARIED)) ||
                ((act_type_cell->st_bucket->info.act_type->renamed != NULL) &&
                 (act_type_cell->st_bucket->info.act_type->renamed->info.act_type->variation == UNVARIED)))
            {
              act_type_cell->st_bucket->info.act_type->variation = HIDDEN;
              if (act_type_cell->st_bucket->info.act_type->renamed == NULL)
                for (duplicate_cell = act_type_cell->st_bucket->info.act_type->duplicate_list;
                     (duplicate_cell != NULL);
                     duplicate_cell = duplicate_cell->next_st_bucket_cell)
                {
                  duplicate_cell->st_bucket->info.act_type->variation = HIDDEN;
                  duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation = HIDDEN;
                }
              else
                act_type_cell->st_bucket->info.act_type->renamed->info.act_type->variation = HIDDEN;
            }
}


GST_BUCKT2_CELL	*record_high_global_state_pairs(void)
{
	GST_BUCKET      *global_state;
        GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
	GST_BUCKT2_CELL	*high_global_state_pair_list;
	G_TRANS_CELL	*global_trans_cell;

        /* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[table_no]->info.archi_type->init_global_state,
						   NULL);
        archi_type[table_no]->info.archi_type->init_global_state->global_trans_considered = FALSE;
	high_global_state_pair_list = NULL;
        while (global_state_stack != NULL)
        {
          /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* record every pair formed by the global state and any other global state reachable from it via */
	  /* a global transition labeled with a high action type */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if ((global_trans_cell->action->info.action->type->info.act_type != NULL) &&
                (global_trans_cell->action->info.action->type->info.act_type->variation == MADE_HIGH) &&
		(global_trans_cell->der_global_state != global_state))
	      high_global_state_pair_list = alloc_gst_bucket2_cell(global_state,
								   global_trans_cell->der_global_state,
								   high_global_state_pair_list);
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
	return(high_global_state_pair_list);
}


void		check_high_global_state_pairs(GST_BUCKT2_CELL *high_global_state_pair_list)
{
	BOOLEAN		positive_outcome,
			preceding_true;
	GST_BUCKT2_CELL	*high_global_state_pair_cell;
	ST_BUCKET	*disting_formula;
	int		pos;

        /* check every pair of global states connected by a global transition labeled with a high action */
	/* type for nondeducibility on composition */
        for (high_global_state_pair_cell = high_global_state_pair_list,
	     positive_outcome = TRUE;
	     ((high_global_state_pair_cell != NULL) &&
	      positive_outcome);
	     high_global_state_pair_cell = (positive_outcome)?
					     high_global_state_pair_cell->next_gst_bucket2_cell:
					     high_global_state_pair_cell)
	  positive_outcome = check_global_state_equiv(high_global_state_pair_cell->gst_bucket1,
						      high_global_state_pair_cell->gst_bucket2);

	/* print the result to the .sar file */
	if (positive_outcome)
	{
	  fprintf(sar_file,
		  "%s satisfies the non-deducibility on composition property.",
		  archi_type[0]->symbol_lexeme);
	}
	else
	{
	  fprintf(sar_file,
		  "%s violates the non-deducibility on composition property",
		  archi_type[0]->symbol_lexeme);
	  fprintf(sar_file,
		  "\nas demonstrated by the following modal logic formula");
	  fprintf(sar_file,
		  "\nsatisfied by global state %d with the high security actions restricted",
		  high_global_state_pair_cell->gst_bucket1->global_state_no);
	  fprintf(sar_file,
		  "\nbut not by global state %d with the high security actions restricted:\n\t",
		  high_global_state_pair_cell->gst_bucket2->global_state_no);
	  table_no = 0;
	  disting_formula = compute_disting_formula(high_global_state_pair_cell->gst_bucket1,
						    high_global_state_pair_cell->gst_bucket2);
	  pos = 0;
	  preceding_true = FALSE;
	  print_disting_formula(sar_file,
				disting_formula->symbol_lexeme,
				&pos,
				0,
				FALSE,
				&preceding_true);
	}
}
