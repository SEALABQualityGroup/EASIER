/****************************************************************
 *								*
 *								*
 *		       nusmv_connector.c			*
 *								*
 *                                          (by Marco Bernardo) *
 ****************************************************************
 * This module checks whether a .aem spec file satisfies certain properties expressed through temporal
 * logic formulas by interacting with NuSMV.
 * This module contains the following function:
 * - check_property_list(): It checks whether a .aem spec file satisfies a list of property.
 * and the following auxiliary functions:
 * - init_all_local_act_type_list(): It initializes the list of all the local action types occurring in the
 *   				     translation by inserting into it the restricted action types of all the
 *   				     AEIs.
 * - translate_local_state_spaces(): It translates the local state spaces of the AEIs into the input
 *				     language of NuSMV.
 * - create_local_act_type_list(): It creates the list of action types labeling the local transitions of an
 *   				   AEI after lexicographically sorting the list of local transitions of each
 *   				   local state of the AEI.
 * - print_local_state_space(): It prints the local state space of an AEI in the input language of NuSMV.
 * - compose_local_state_spaces(): It composes the local state spaces of the AEIs according to the input
 *				   language of NuSMV.
 * - compose_local_act_type_lists(): It composes the action type list of a sequence of AEIs with the action
 *   				     type list of the next AEI by avoiding duplicates and taking into
 *   				     account the synchronization set in between.
 * - apply_hiding_renaming(): It applies the hiding and renaming operators to the action types of the
 *   			      composition of the local state spaces that are involved in behavioral
 *   			      variations.
 * - print_main_module(): It prints the main module for the translation into the NuSMV input language.
 * - check_local_act_type_list_membership(): It checks whether an action type is present in a local action
 *   					     type list.
 * - print_id(): It prints the identifier of an AEI or an action type while converting it into a format
 *   		 admitted by the input language of NuSMV (i.e. with possible dots and square brackets
 *   		 replaced by dashes).
 * - print_smv_script_file(): It prints the script file to instruct NuSMV.
 * - convert_nusmv_output(): It converts the NuSMV output into a more readable format to be written onto the
 *   			     .mcr file.
 ****************************************************************/


/****************************************************************
 * 1. Inclusion of libraries.					*
 ****************************************************************/

#include	<ctype.h>
#include	<stdio.h>
#include	<stdlib.h>
#include	<string.h>
#include	<unistd.h>

#include	"../headers/Library.h"


/****************************************************************
 * 2. Inclusion of external gvariables and functions.		*
 ****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/string_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************
 * 3. Definition/declaration of exporting gvariables/functions.	*
 ****************************************************************/

ST_BUCKET_CELL	*property_list;
			/* list of temporal logic formulas to be verified;
			 * set by ;
			 * used in */


void		check_property_list(char *);


/****************************************************************
 * 4. Definition/declaration of private gvariables/functions.	*
 ****************************************************************/

PRIVATE FILE            *smv_file;
                                /* .smv file containing the AEmilia spec translated into the language of
				 * NuSMV;
                                 * set by ;
                                 * used in */

PRIVATE	L_ACT_TYPE_CELL	*all_local_act_type_list;
                                /* list of all the action types occurring in the translation; this is needed
				 * in the main module when defining the domain of variable "_action_type" in
				 * order to avoid errors due to undefined symbols when NuSMV will flatten
				 * the translation (command "go");
                                 * set by ;
                                 * used in */


PRIVATE	void		init_all_local_act_type_list(void),
			translate_local_state_spaces(void),
			create_local_act_type_list(LST_BUCKET *,
						   L_ACT_TYPE_CELL **),
			print_local_state_space(LST_BUCKET *,
						L_ACT_TYPE_CELL *),
			compose_local_state_spaces(void),
			compose_local_act_type_lists(ST_BUCKET *,
						     ST_BUCKET *);

PRIVATE	int		apply_hiding_renaming(void);

PRIVATE	void		print_main_module(int);

PRIVATE	BOOLEAN		check_local_act_type_list_membership(ST_BUCKET *,
							     L_ACT_TYPE_CELL *);

PRIVATE	void		print_id(char *),
			print_smv_script_file(char *,
					      char *),
			convert_nusmv_output(char *);


/****************************************************************
 * 5. Definition of exporting functions.			*
 ****************************************************************/

void		check_property_list(char *aem_path)
{
	char		*last_slash,
			*smv_dir,
			*command;
	int		hiding_renaming_num,
			stderr_handle,
			length;

	/* translate the .aem spec file into a .smv spec file */
	smv_file = new_fopen(aem_path,
			     ".smv",
			     "w");
	init_all_local_act_type_list();
	translate_local_state_spaces();
	compose_local_state_spaces();
	hiding_renaming_num = apply_hiding_renaming();
	print_main_module(hiding_renaming_num);
	fclose(smv_file);

	/* redirect stderr (as requested by NuSMV) */
	fflush(stderr);
	stderr_handle = dup(STDERR_FILENO);
	new_freopen(aem_path,
		    ".smv_msgs",
		    "w",
		    stderr);
	fseek(stderr,
	      0L,
	      SEEK_END);

	/* change the current directory to the one containing the .smv file (necessary under Windows because
	 * NuSMV is unable to accept paths containing white spaces, not even if the paths are enclosed in
	 * double quotes) */
	last_slash = strrchr(aem_path,
			     '/');
	if (last_slash != NULL)
	{
	  length = last_slash - aem_path + 1;
	  smv_dir = alloc_string(length);
	  strncpy(smv_dir,
		  aem_path,
		  length);
	  smv_dir[length] = EOS;
	  chdir(smv_dir);
	  free(smv_dir);
	}

	/* run NuSMV through a script file, then make its output readable (after restoring stderr) */
	print_smv_script_file(aem_path,
			      last_slash);
	length = 12 +
		 ((last_slash == NULL)?
		    strlen(aem_path):
		    strlen(last_slash + 1)) +
		 16;
	command = alloc_string(length);
	sprintf(command,
		"NuSMV -load %s.smv_script -int",
		(last_slash == NULL)?
		  aem_path:
		  (last_slash + 1));
	system(command);
	free(command);
	fflush(stderr);
	dup2(stderr_handle,
	     STDERR_FILENO);
	convert_nusmv_output(aem_path);
}


/****************************************************************
 * 6. Definition of private functions.				*
 ****************************************************************/

void		init_all_local_act_type_list(void)
{
	ST_BUCKET_CELL	*aei_cell,
			*act_type_cell;

	all_local_act_type_list = NULL;
	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  for (act_type_cell = aei_cell->st_bucket->info.aei->act_type_list;
	       (act_type_cell != NULL);
	       act_type_cell = act_type_cell->next_st_bucket_cell)
	    if (act_type_cell->st_bucket->info.act_type->variation == (char)RESTRICTED)
	      all_local_act_type_list = alloc_l_act_type_cell(act_type_cell->st_bucket,
							      NULL,
							      0,
							      0,
							      FALSE,
							      all_local_act_type_list);
}


void		translate_local_state_spaces(void)
{
	L_ACT_TYPE_CELL	*local_act_type_cell;
	LST_BUCKET_CELL	*local_state_cell;
	ST_BUCKET	*aei;
	ST_BUCKET_CELL	*aei_cell;
	int		i;

	/* translate the local state space of each AEI into a module of the input language of NuSMV */
	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	{
	  /* print the header of the sequential module for the AEI, whose name comprises the serial number
	   * and the identifier of the AEI and whose input parameters "running" and "action_type" represent
	   * a flag indicating whether the AEI participates in the action to be executed and, if so, the
	   * type of its contribution, respectively (since the computation model adopted in NuSMV is
	   * synchronous, STUTTER is used as the type of the contributing action whenever the AEI does not
	   * actually participate in the action to be executed) */
	  aei = aei_cell->st_bucket;
	  fprintf(smv_file,
		  "MODULE _seq_%d_",
		  aei->info.aei->aei_no);
	  print_id(aei->symbol_lexeme);
	  fprintf(smv_file,
		  "(_running, _action_type)\n\n");
	  /* print the sanity checks for the two input parameters "running" and "action_type" */
	  fprintf(smv_file,
		  "  SPEC AG((_running = FALSE) | (_running = TRUE))\n\n");
	  fprintf(smv_file,
		  "  SPEC AG(_action_type in {");
	  create_local_act_type_list(aei->info.aei->init_local_state,
				     &(aei->info.aei->local_act_type_list));
	  for (local_act_type_cell = aei->info.aei->local_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    ",\n                           ");
	  }
	  fprintf(smv_file,
		  "_STUTTER})\n\n");
	  fprintf(smv_file,
		  "  SPEC AG(_action_type = _STUTTER <-> !_running)\n\n");
	  /* print the declaration of the local variable "local_state" */
	  fprintf(smv_file,
		  "  VAR _local_state : {");
	  for (i = 1;
	       (i <= aei->info.aei->local_state_num);
	       i++)
	    fprintf(smv_file,
		    "_LS_%d,\n                      ",
		    i);
	  fprintf(smv_file,
		  "_NO_LS};\n\n");
	  /* print the definition of the output enabledness conditions "can_run_on_" based on the value of
	   * the local variable "local_state", which are propagated to the upper level to decide the next
	   * action to be executed */
	  for (local_act_type_cell = aei->info.aei->local_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    fprintf(smv_file,
		    "  DEFINE _can_run_on_");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    " := _local_state in {");
	    for (local_state_cell = local_act_type_cell->enabling_local_state_list;
		 (local_state_cell != NULL);
		 local_state_cell = local_state_cell->next_lst_bucket_cell)
	      fprintf(smv_file,
		      (local_state_cell->next_lst_bucket_cell != NULL)?
			"_LS_%d, ":
			"_LS_%d};\n",
		      local_state_cell->lst_bucket->local_state_no);
	  }
	  if (aei->info.aei->local_act_type_list != NULL)
	    fprintf(smv_file,
		    "\n");
	  /* print the sanity checks for the output enabledness conditions "can_run_on_" based on the value
	   * of the input parameter "action_type" */
	  for (local_act_type_cell = aei->info.aei->local_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    fprintf(smv_file,
		    "  SPEC AG(_action_type = ");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    " -> _can_run_on_");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    ")\n");
	  }
	  if (aei->info.aei->local_act_type_list != NULL)
	    fprintf(smv_file,
		    "\n");
	  /* print the initialization of the local variable "local_state" as well as the definition of its
	   * evolution based on the possible values of the input parameter "action_type" */
	  fprintf(smv_file,
		  "  ASSIGN init(_local_state) := _LS_1;\n");
	  fprintf(smv_file,
		  "         next(_local_state) :=\n");
	  fprintf(smv_file,
		  "           case\n");
	  fprintf(smv_file,
		  "             !_running : _local_state;\n");
	  print_local_state_space(aei->info.aei->init_local_state,
				  aei->info.aei->local_act_type_list);
	  fprintf(smv_file,
		  "             1 : _NO_LS;\n");
	  fprintf(smv_file,
		  "           esac;\n\n");
	  /* print the sanity check for the local variable "local_state" */
	  fprintf(smv_file,
		  "  SPEC AG(_local_state != _NO_LS)\n\n\n");
	}

	/* make the list of local action types of the first AEI be the list of composed action types of the
	 * AEIs up to the first one */
	archi_type[spec_no]->info.archi_type->aei_list->st_bucket->info.aei->composed_act_type_list =
	  archi_type[spec_no]->info.archi_type->aei_list->st_bucket->info.aei->local_act_type_list;
}


void		create_local_act_type_list(LST_BUCKET      *local_state,
					   L_ACT_TYPE_CELL **local_act_type_list)
{
	BOOLEAN		found,
			nondet;
	LST_BUCKET	*der_local_state;
	LST_BUCKET_CELL	*local_state_cell;
	L_ACT_TYPE_CELL	*local_act_type_cell;
	L_TRANS_CELL	*local_trans_cell,
			*aux_local_trans_cell,
			*min_local_trans_cell;
	ST_BUCKET	*action;
	VP_INFO		*value_passing_info;
	REWARD_INFO	reward_info;
	char		or_marked;
	int		priority;

	if (local_state->local_trans_considered)
	{
	  /* sort the local transition list of the local state in lexicographical order */
	  local_state->local_trans_considered = FALSE;
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	  {
	    for (aux_local_trans_cell = local_trans_cell->next_l_trans_cell,
	         min_local_trans_cell = local_trans_cell;
	         (aux_local_trans_cell != NULL);
	         aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	      if (strcmp(aux_local_trans_cell->action->info.action->type->symbol_lexeme,
			 min_local_trans_cell->action->info.action->type->symbol_lexeme) < 0)
	        min_local_trans_cell = aux_local_trans_cell;
	    if (min_local_trans_cell != local_trans_cell)
	    {
	      action = local_trans_cell->action;
	      local_trans_cell->action = min_local_trans_cell->action;
	      min_local_trans_cell->action = action;
	      value_passing_info = local_trans_cell->value_passing_info;
	      local_trans_cell->value_passing_info = min_local_trans_cell->value_passing_info;
	      min_local_trans_cell->value_passing_info = value_passing_info;
	      reward_info = local_trans_cell->reward_info;
	      local_trans_cell->reward_info = min_local_trans_cell->reward_info;
	      min_local_trans_cell->reward_info = reward_info;
	      der_local_state = local_trans_cell->der_local_state;
	      local_trans_cell->der_local_state = min_local_trans_cell->der_local_state;
	      min_local_trans_cell->der_local_state = der_local_state;
	      or_marked = local_trans_cell->or_marked;
	      local_trans_cell->or_marked = min_local_trans_cell->or_marked;
	      min_local_trans_cell->or_marked = or_marked;
	    }
	  }
	  /* update the list of action types occurring in the local state space of the AEI as well as the
	   * list of all the action types occurring in the translation */
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       )
	  {
	    for (local_act_type_cell = *local_act_type_list,
	         found = FALSE;
	         ((local_act_type_cell != NULL) &&
	          !found);
	         local_act_type_cell = (!found)?
					 local_act_type_cell->next_l_act_type_cell:
					 local_act_type_cell)
	      found = (local_act_type_cell->local_act_type == local_trans_cell->action->info.action->type);
	    if (!found)
	    {
	      priority = (int)(local_trans_cell->action->info.action->priority->info.expr->value) *
			 ((local_trans_cell->action->info.action->rate_index == (char)PASSIVE)?
			    -1:
			    1);
	      local_act_type_cell = *local_act_type_list =
		alloc_l_act_type_cell(local_trans_cell->action->info.action->type,
				      NULL,
				      priority,
				      ((local_trans_cell->action->info.action->type->info.act_type->
					  duplicate_list != NULL) &&
				       (local_trans_cell->action->info.action->rate_index ==
					  (char)PASSIVE) &&
				       (local_trans_cell->or_marked == NO_OR))?
				        priority:
					0,
				      FALSE,
				      *local_act_type_list);
	      if (!check_local_act_type_list_membership(local_act_type_cell->local_act_type,
							all_local_act_type_list))
	        all_local_act_type_list = alloc_l_act_type_cell(local_act_type_cell->local_act_type,
							        local_act_type_cell->basic_local_act_type,
							        local_act_type_cell->priority,
							        local_act_type_cell->passive_or_priority,
							        local_act_type_cell->synchronization,
							        all_local_act_type_list);
	    }
	    local_act_type_cell->enabling_local_state_list =
	      alloc_lst_bucket_cell(local_state,
				    local_act_type_cell->enabling_local_state_list);
	    for (aux_local_trans_cell = local_trans_cell->next_l_trans_cell,
		 nondet = FALSE;
	         ((aux_local_trans_cell != NULL) &&
		  (aux_local_trans_cell->action->info.action->type ==
		     local_trans_cell->action->info.action->type) &&
		  !nondet);
	         aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	      nondet = (aux_local_trans_cell->der_local_state != local_trans_cell->der_local_state);
	    if (nondet)
	    {
	      local_act_type_cell->local_nondet_list =
		alloc_l_nondet_cell(local_state,
				    alloc_lst_bucket_cell(local_trans_cell->der_local_state,
							  NULL),
				    local_act_type_cell->local_nondet_list);
	      for (aux_local_trans_cell = local_trans_cell->next_l_trans_cell;
	           ((aux_local_trans_cell != NULL) &&
		    (aux_local_trans_cell->action->info.action->type ==
		       local_trans_cell->action->info.action->type));
	           aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	      {
		for (local_state_cell =
		       local_act_type_cell->local_nondet_list->reached_local_state_list,
		     found = FALSE;
		     ((local_state_cell != NULL) &&
		      !found);
		     local_state_cell = local_state_cell->next_lst_bucket_cell)
		  found = (local_state_cell->lst_bucket == aux_local_trans_cell->der_local_state);
		if (!found)
		  local_act_type_cell->local_nondet_list->reached_local_state_list =
		    alloc_lst_bucket_cell(aux_local_trans_cell->der_local_state,
					  local_act_type_cell->local_nondet_list->reached_local_state_list);
	      }
	    }
	    local_trans_cell = aux_local_trans_cell;
	  }
	  /* process in the same way each derivative local state */
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    create_local_act_type_list(local_trans_cell->der_local_state,
				       local_act_type_list);
	}
}


void		print_local_state_space(LST_BUCKET      *local_state,
					L_ACT_TYPE_CELL *local_act_type_list)
{
	L_ACT_TYPE_CELL	*local_act_type_cell;
	L_NONDET_CELL	*local_nondet_cell;
	L_TRANS_CELL	*local_trans_cell,
			*aux_local_trans_cell;
	LST_BUCKET_CELL	*reached_local_state_cell;

	if (!local_state->local_trans_considered)
	{
	  /* print the local transitions of the local state */
	  local_state->local_trans_considered = TRUE;
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	  {
	    fprintf(smv_file,
		    "             _local_state = _LS_%d & _action_type = ",
		    local_state->local_state_no);
	    print_id(local_trans_cell->action->info.action->type->symbol_lexeme);
	    fprintf(smv_file,
		    " : ");
	    for (local_act_type_cell = local_act_type_list;
	         (local_act_type_cell->local_act_type != local_trans_cell->action->info.action->type);
		 local_act_type_cell = local_act_type_cell->next_l_act_type_cell);
	    for (local_nondet_cell = local_act_type_cell->local_nondet_list;
		 ((local_nondet_cell != NULL) &&
		  (local_nondet_cell->enabling_local_state != local_state));
		 local_nondet_cell = local_nondet_cell->next_l_nondet_cell);
	    if (local_nondet_cell == NULL)
	      fprintf(smv_file,
		      "_LS_%d;\n",
		      local_trans_cell->der_local_state->local_state_no);
	    else
	    {
	      fprintf(smv_file,
		      "{_LS_%d",
		      local_nondet_cell->reached_local_state_list->lst_bucket->local_state_no);
	      for (reached_local_state_cell =
		     local_nondet_cell->reached_local_state_list->next_lst_bucket_cell;
		   (reached_local_state_cell != NULL);
		   reached_local_state_cell = reached_local_state_cell->next_lst_bucket_cell)
	        fprintf(smv_file,
		        ", _LS_%d",
		        reached_local_state_cell->lst_bucket->local_state_no);
	      fprintf(smv_file,
		      "};\n");
	    }
	    for (aux_local_trans_cell = local_trans_cell->next_l_trans_cell;
	         ((aux_local_trans_cell != NULL) &&
		  (aux_local_trans_cell->action->info.action->type ==
		     local_trans_cell->action->info.action->type));
	         aux_local_trans_cell = aux_local_trans_cell->next_l_trans_cell)
	    local_trans_cell = aux_local_trans_cell;
	  }
	  /* print the local transitions of each derivative local state */
	  for (local_trans_cell = local_state->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    print_local_state_space(local_trans_cell->der_local_state,
				    local_act_type_list);
	}
}


void		compose_local_state_spaces(void)
{
	L_ACT_TYPE_CELL	*local_act_type_cell,
			*aux_local_act_type_cell;
	ST_BUCKET	*curr_aei,
			*prev_aei;
	ST_BUCKET_CELL	*curr_aei_cell,
			*prev_aei_cell;
	int		i;

	/* compose left-to-right the modules of the AEIs by means of additional modules of the input
	 * language of NuSMV */
	for (prev_aei_cell = archi_type[spec_no]->info.archi_type->aei_list,
	     curr_aei_cell = archi_type[spec_no]->info.archi_type->aei_list->next_st_bucket_cell;
	     (curr_aei_cell != NULL);
	     prev_aei_cell = curr_aei_cell,
	     curr_aei_cell = curr_aei_cell->next_st_bucket_cell)
	{
	  /* print the header of the module for the parallel composition of the AEIs up to the current one,
	   * whose input parameters "running" and "action_type" represent a flag indicating whether the
	   * parallel composition participates in the action to be executed and, if so, the type of its
	   * contribution, respectively (since the computation model adopted in NuSMV is synchronous,
	   * STUTTER is used as the type of the contributing action whenever the parallel composition does
	   * not actually participate in the action to be executed) */
	  curr_aei = curr_aei_cell->st_bucket;
	  prev_aei = prev_aei_cell->st_bucket;
	  fprintf(smv_file,
		  "MODULE _par_1_%d(_running, _action_type)\n\n",
		  curr_aei->info.aei->aei_no);
	  /* print the sanity checks for the two input parameters "running" and "action_type" */
	  fprintf(smv_file,
		  "  SPEC AG((_running = FALSE) | (_running = TRUE))\n\n");
	  fprintf(smv_file,
		  "  SPEC AG(_action_type in {");
	  compose_local_act_type_lists(prev_aei,
				       curr_aei);
	  for (local_act_type_cell = curr_aei->info.aei->composed_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    ",\n                           ");
	  }
	  fprintf(smv_file,
		  "_STUTTER})\n\n");
	  fprintf(smv_file,
		  "  SPEC AG(_action_type = _STUTTER <-> !_running)\n\n");
	  /* print the instantiation of the two modules to be composed together with their actual input
	   * parameters */
	  if (prev_aei->info.aei->aei_no == 1)
	  {
	    fprintf(smv_file,
		    "  VAR _M1 : _seq_1_");
	    print_id(prev_aei->symbol_lexeme);
	    fprintf(smv_file,
		    "(_running1, _action_type1);\n");
	  }
	  else
	    fprintf(smv_file,
		    "  VAR _M1 : _par_1_%d(_running1, _action_type1);\n",
		    prev_aei->info.aei->aei_no);
	  fprintf(smv_file,
		  "  VAR _M2 : _seq_%d_",
		  curr_aei->info.aei->aei_no);
	  print_id(curr_aei->symbol_lexeme);
	  fprintf(smv_file,
		  "(_running2, _action_type2);\n\n");
	  /* print the definition of the output enabledness conditions "can_run_on_" based on the same
	   * enabledness conditions for the two modules to be composed, which are propagated to the upper
	   * level to decide the next action to be executed (for or-synchronizations a selection is carried
	   * out based on the priority of the involved passive uni-interactions; this cannot be done in the
	   * top-level module due to possible hidings/renamings altering duplication-related information) */
	  for (local_act_type_cell = curr_aei->info.aei->composed_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    fprintf(smv_file,
		    "  DEFINE _can_run_on_");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    if (check_list_membership(local_act_type_cell->local_act_type,
				      curr_aei->info.aei->sync_set,
				      FALSE))
	    {
	      fprintf(smv_file,
		      " := _M1._can_run_on_");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & _M2._can_run_on_");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    }
	    else
	    {
	      fprintf(smv_file,
		      " := _M%d._can_run_on_",
		      (check_local_act_type_list_membership(local_act_type_cell->local_act_type,
							    prev_aei->info.aei->composed_act_type_list))?
			1:
			2);
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    }
	    if (local_act_type_cell->synchronization &&
	        (local_act_type_cell->passive_or_priority != 0))
	      for (aux_local_act_type_cell = curr_aei->info.aei->composed_act_type_list;
	           (aux_local_act_type_cell != NULL);
	           aux_local_act_type_cell = aux_local_act_type_cell->next_l_act_type_cell)
	        if (aux_local_act_type_cell->synchronization &&
		    (aux_local_act_type_cell->local_act_type->info.act_type->duplicate_list ==
		       local_act_type_cell->local_act_type->info.act_type->duplicate_list) &&
		    (aux_local_act_type_cell->passive_or_priority <
		       local_act_type_cell->passive_or_priority))
		{
	          if (check_list_membership(aux_local_act_type_cell->local_act_type,
				            curr_aei->info.aei->sync_set,
				            FALSE))
		  {
	            fprintf(smv_file,
		            " & !(_M1._can_run_on_");
		    print_id(aux_local_act_type_cell->local_act_type->symbol_lexeme);
	            fprintf(smv_file,
		            " & _M2._can_run_on_");
		    print_id(aux_local_act_type_cell->local_act_type->symbol_lexeme);
	            fprintf(smv_file,
		            ")");
		  }
		  else
	            if (check_list_membership(local_act_type_cell->local_act_type,
				              curr_aei->info.aei->sync_set,
				              FALSE))
		    {
	              fprintf(smv_file,
		              " & !_M%d._can_run_on_",
		              (check_local_act_type_list_membership(aux_local_act_type_cell->local_act_type,
							            prev_aei->info.aei->
								      composed_act_type_list))?
			        1:
			        2);
		      print_id(aux_local_act_type_cell->local_act_type->symbol_lexeme);
		    }
		}
	    fprintf(smv_file,
		    ";\n");
	  }
	  if (curr_aei->info.aei->composed_act_type_list != NULL)
	    fprintf(smv_file,
		    "\n");
	  /* print the sanity checks for the output enabledness conditions "can_run_on_" based on the value
	   * of the input parameter "action_type" */
	  for (local_act_type_cell = curr_aei->info.aei->composed_act_type_list;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    fprintf(smv_file,
		    "  SPEC AG(_action_type = ");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    " -> _can_run_on_");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    ")\n");
	  }
	  if (curr_aei->info.aei->composed_act_type_list != NULL)
	    fprintf(smv_file,
		    "\n");
	  /* print the definition of the actual input parameters for the two modules to be composed based on
	   * the value of the two input parameters of the composed module */
	  for (i = 1;
	       (i <= 2);
	       i++)
	  {
	    fprintf(smv_file,
		    "  DEFINE _running%d :=\n",
		    i);
	    fprintf(smv_file,
		    "           case\n");
	    fprintf(smv_file,
		    "             !_running : FALSE;\n");
	    for (local_act_type_cell = curr_aei->info.aei->composed_act_type_list;
	         (local_act_type_cell != NULL);
	         local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	      if (check_list_membership(local_act_type_cell->local_act_type,
				        curr_aei->info.aei->sync_set,
				        FALSE))
	      {
	        fprintf(smv_file,
		        "             _action_type = ");
		print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	        fprintf(smv_file,
		        " : TRUE;\n");
	      }
	      else
	      {
	        fprintf(smv_file,
		        "             _action_type = ");
		print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	        fprintf(smv_file,
		        " : %s;\n",
			(check_local_act_type_list_membership(local_act_type_cell->local_act_type,
							      (i == 1)?
								prev_aei->info.aei->composed_act_type_list:
							        curr_aei->info.aei->local_act_type_list))?
			  "TRUE":
			  "FALSE");
	      }
	    fprintf(smv_file,
		    "           esac;\n\n");
	    fprintf(smv_file,
		    "  DEFINE _action_type%d :=\n",
		    i);
	    fprintf(smv_file,
		    "           case\n");
	    fprintf(smv_file,
		    "             _running%d : _action_type;\n",
		    i);
	    fprintf(smv_file,
		    "             !_running%d : _STUTTER;\n",
		    i);
	    fprintf(smv_file,
		    "           esac;\n\n");
	  }
	  fprintf(smv_file,
		  "\n");
	}

	/* store the final list of composed action types into the symbol table bucket for the first AEI */
	archi_type[spec_no]->info.archi_type->aei_list->st_bucket->info.aei->composed_act_type_list =
	  prev_aei_cell->st_bucket->info.aei->composed_act_type_list;
}


void		compose_local_act_type_lists(ST_BUCKET *prev_aei,
					     ST_BUCKET *curr_aei)
{
	L_ACT_TYPE_CELL	*local_act_type_cell,
			*sync_local_act_type_cell;

	/* handle the action types of the AEIs up to the previous one, including the actions types
	 * synchronizing with the ones of the current AEI */
	for (local_act_type_cell = prev_aei->info.aei->composed_act_type_list;
	     (local_act_type_cell != NULL);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	{
	  if (!check_local_act_type_list_membership(local_act_type_cell->local_act_type,
						    curr_aei->info.aei->composed_act_type_list))
	  {
	    if (!check_list_membership(local_act_type_cell->local_act_type,
				       curr_aei->info.aei->sync_set,
				       FALSE))
	      curr_aei->info.aei->composed_act_type_list =
		alloc_l_act_type_cell(local_act_type_cell->local_act_type,
				      local_act_type_cell->basic_local_act_type,
				      local_act_type_cell->priority,
				      local_act_type_cell->passive_or_priority,
				      local_act_type_cell->synchronization,
				      curr_aei->info.aei->composed_act_type_list);
	    else
	    {
	      for (sync_local_act_type_cell = curr_aei->info.aei->local_act_type_list;
	           ((sync_local_act_type_cell != NULL) &&
	            (sync_local_act_type_cell->local_act_type != local_act_type_cell->local_act_type));
	           sync_local_act_type_cell = sync_local_act_type_cell->next_l_act_type_cell);
	      if ((sync_local_act_type_cell != NULL) &&
		  ((local_act_type_cell->priority < 0) ||
		   (sync_local_act_type_cell->priority < 0)))
	        curr_aei->info.aei->composed_act_type_list =
		  alloc_l_act_type_cell(local_act_type_cell->local_act_type,
					local_act_type_cell->basic_local_act_type,
					(local_act_type_cell->priority >= 0)?
					  local_act_type_cell->priority:
					  ((sync_local_act_type_cell->priority >= 0)?
					     sync_local_act_type_cell->priority:
					     ((local_act_type_cell->priority <=
					         sync_local_act_type_cell->priority)?
					      local_act_type_cell->priority:
					      sync_local_act_type_cell->priority)),
					(local_act_type_cell->passive_or_priority < 0)?
					  local_act_type_cell->passive_or_priority:
					  ((sync_local_act_type_cell->passive_or_priority < 0)?
					     sync_local_act_type_cell->passive_or_priority:
					     0),
					TRUE,
				        curr_aei->info.aei->composed_act_type_list);
	    }
	  }
	}

	/* handle the action types of the current AEI (excluding synchronizations) */
	for (local_act_type_cell = curr_aei->info.aei->local_act_type_list;
	     (local_act_type_cell != NULL);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  if (!check_local_act_type_list_membership(local_act_type_cell->local_act_type,
						    curr_aei->info.aei->composed_act_type_list) &&
	      !check_list_membership(local_act_type_cell->local_act_type,
				     curr_aei->info.aei->sync_set,
				     FALSE))
	    curr_aei->info.aei->composed_act_type_list =
	      alloc_l_act_type_cell(local_act_type_cell->local_act_type,
				    local_act_type_cell->basic_local_act_type,
				    local_act_type_cell->priority,
				    local_act_type_cell->passive_or_priority,
				    local_act_type_cell->synchronization,
				    curr_aei->info.aei->composed_act_type_list);
}


int		apply_hiding_renaming(void)
{
		BOOLEAN		found_renaming_act_type,
				nondet_on_renaming_act_type;
		L_ACT_TYPE_CELL	*curr_local_act_type_cell,
				*prev_local_act_type_cell,
				*to_be_renamed_act_type_cell,
				*local_act_type_cell;
		ST_BUCKET	*first_aei,
				*renaming_act_type;
	static  char            *renaming_lexeme		=       NULL;
		int		hiding_renaming_num,
				length;
	static  int             max_length			=       0;

	for (first_aei = archi_type[spec_no]->info.archi_type->aei_list->st_bucket,
	     curr_local_act_type_cell = prev_local_act_type_cell =
	       first_aei->info.aei->composed_act_type_list,
	     hiding_renaming_num = 0;
	     (curr_local_act_type_cell != NULL);
	     )
	  if ((curr_local_act_type_cell->local_act_type->info.act_type->variation != (char)HIDDEN) &&
	      (curr_local_act_type_cell->local_act_type->info.act_type->renamed == NULL))
	  {
	    /* the action type does not need to be hidden/renamed */
	    prev_local_act_type_cell = curr_local_act_type_cell;
	    curr_local_act_type_cell = curr_local_act_type_cell->next_l_act_type_cell;
	  }
	  else
	  {
	    /* remove the action type to be hidden/renamed from the final list of composed action types */
	    to_be_renamed_act_type_cell = curr_local_act_type_cell;
	    if (curr_local_act_type_cell == prev_local_act_type_cell)
	      first_aei->info.aei->composed_act_type_list =
		prev_local_act_type_cell = curr_local_act_type_cell =
		  curr_local_act_type_cell->next_l_act_type_cell;
	    else
	      prev_local_act_type_cell->next_l_act_type_cell = curr_local_act_type_cell =
		curr_local_act_type_cell->next_l_act_type_cell;
	    /* insert the renaming action type into the final list of composed action types as well as into
	     * the list of all the action types occurring in the translation, if absent */
	    length =
	      ((to_be_renamed_act_type_cell->local_act_type->info.act_type->variation == (char)HIDDEN)?
		 9:
		 strlen(to_be_renamed_act_type_cell->local_act_type->info.act_type->renamed->
			  symbol_lexeme)) +
	      1 +
	      ((to_be_renamed_act_type_cell->priority < 0)?
	         1:
		 0) +
	      compute_digit_num((double)(to_be_renamed_act_type_cell->priority));
	    if (length > max_length)
            {
              if (renaming_lexeme != NULL)
                free(renaming_lexeme);
              renaming_lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(renaming_lexeme,
                    "%s#%d",
	            (to_be_renamed_act_type_cell->local_act_type->info.act_type->variation == (char)HIDDEN)?
		      "invisible":
		      to_be_renamed_act_type_cell->local_act_type->info.act_type->renamed->symbol_lexeme,
		    to_be_renamed_act_type_cell->priority);
	    renaming_act_type = (ST_BUCKET *)search_lexeme_table(renaming_lexeme,
								 SYT);
	    for (local_act_type_cell = first_aei->info.aei->composed_act_type_list,
	         found_renaming_act_type = FALSE;
	         ((local_act_type_cell != NULL) &&
	          !found_renaming_act_type);
	         local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	      found_renaming_act_type = (local_act_type_cell->local_act_type == renaming_act_type);
	    if (!found_renaming_act_type)
	    {
	      first_aei->info.aei->composed_act_type_list =
	        alloc_l_act_type_cell(renaming_act_type,
				      to_be_renamed_act_type_cell->local_act_type,
				      to_be_renamed_act_type_cell->priority,
				      to_be_renamed_act_type_cell->passive_or_priority,
				      to_be_renamed_act_type_cell->synchronization,
				      first_aei->info.aei->composed_act_type_list);
	      all_local_act_type_list =
	        alloc_l_act_type_cell(renaming_act_type,
				      to_be_renamed_act_type_cell->local_act_type,
				      to_be_renamed_act_type_cell->priority,
				      to_be_renamed_act_type_cell->passive_or_priority,
				      to_be_renamed_act_type_cell->synchronization,
				      all_local_act_type_list);
	    }
	    /* print the header of the module for the hiding/renaming, whose input parameters "running" and
	     * "action_type" represent a flag indicating whether the hiding/renaming participates in the
	     * action to be executed and, if so, the type of its contribution, respectively (since the
	     * computation model adopted in NuSMV is synchronous, STUTTER is used as the type of the
	     * contributing action whenever the hiding/renaming does not actually participate in the action
	     * to be executed) */
	    fprintf(smv_file,
		    "MODULE _hiding_renaming_%d(_running, _action_type)\n\n",
		    ++hiding_renaming_num);
	    /* print the sanity checks for the two input parameters "running" and "action_type" */
	    fprintf(smv_file,
		    "  SPEC AG((_running = FALSE) | (_running = TRUE))\n\n");
	    fprintf(smv_file,
		    "  SPEC AG(_action_type in {");
	    for (local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	         (local_act_type_cell != NULL);
	         local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	    {
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ",\n                           ");
	    }
	    fprintf(smv_file,
		    "_STUTTER})\n\n");
	    fprintf(smv_file,
		    "  SPEC AG(_action_type = _STUTTER <-> !_running)\n\n");
	    /* print the instantiation of the module to be hidden/renamed together with its actual input
	     * parameters */
	    if (hiding_renaming_num > 1)
	      fprintf(smv_file,
		      "  VAR _M1 : _hiding_renaming_%d(_running1, _action_type1);\n\n",
		      hiding_renaming_num - 1);
	    else
	      if (archi_type[spec_no]->info.archi_type->aei_num == 1)
	      {
	        fprintf(smv_file,
		        "  VAR _M1 : _seq_1_");
		print_id(first_aei->symbol_lexeme);
	        fprintf(smv_file,
		        "(_running1, _action_type1);\n\n");
	      }
	      else
	        fprintf(smv_file,
		        "  VAR _M1 : _par_1_%d(_running1, _action_type1);\n\n",
		        archi_type[spec_no]->info.archi_type->aei_num);
	    /* print the declaration of the local variable "nondet_on_" if an action having the renaming
	     * type can be executed in several different ways */
	    if (found_renaming_act_type)
	    {
	      nondet_on_renaming_act_type = TRUE;
	      fprintf(smv_file,
		      "  VAR _nondet_on_");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " : {");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ", ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      "};\n\n");
	    }
	    else
	      nondet_on_renaming_act_type = FALSE;
	    /* print the definition of the output enabledness conditions "can_run_on_" based on the same
	     * enabledness conditions for the module to be hidden/renamed, which are propagated to the upper
	     * level to decide the next action to be executed */
	    for (local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	         (local_act_type_cell != NULL);
	         local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	    {
	      fprintf(smv_file,
		      "  DEFINE _can_run_on_");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      if ((local_act_type_cell->local_act_type == renaming_act_type) &&
		  nondet_on_renaming_act_type)
	      {
	        fprintf(smv_file,
		        " := _M1._can_run_on_");
	        print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	        fprintf(smv_file,
		        " | _M1._can_run_on_");
	        print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	        fprintf(smv_file,
		        ";\n");
	      }
	      else
	      {
	        fprintf(smv_file,
		        " := _M1._can_run_on_");
		print_id((local_act_type_cell->local_act_type == renaming_act_type)?
				    to_be_renamed_act_type_cell->local_act_type->symbol_lexeme:
		        	    local_act_type_cell->local_act_type->symbol_lexeme);
	        fprintf(smv_file,
		        ";\n");
	      }
	    }
	    if (first_aei->info.aei->composed_act_type_list != NULL)
	      fprintf(smv_file,
		      "\n");
	    /* print the sanity checks for the output enabledness conditions "can_run_on_" based on the value
	     * of the input parameter "action_type" */
	    for (local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	         (local_act_type_cell != NULL);
	         local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	    {
	      fprintf(smv_file,
		      "  SPEC AG(_action_type = ");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " -> _can_run_on_");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ")\n");
	    }
	    if (first_aei->info.aei->composed_act_type_list != NULL)
	      fprintf(smv_file,
		      "\n");
	    /* print the definition of the actual input parameters for the module to be hidden/renamed based
	     * on the value of the two input parameters of the hiding/renaming module */
	    fprintf(smv_file,
		    "  DEFINE _running1 := _running;\n\n");
	    fprintf(smv_file,
		    "  DEFINE _action_type1 :=\n");
	    fprintf(smv_file,
		    "           case\n");
	    fprintf(smv_file,
		    "             !_running : _STUTTER;\n");
	    if (first_aei->info.aei->composed_act_type_list->next_l_act_type_cell != NULL)
	    {
	      for (local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	           (local_act_type_cell->local_act_type == renaming_act_type);
	           local_act_type_cell = local_act_type_cell->next_l_act_type_cell);
	      fprintf(smv_file,
		      "             _action_type in {");
	      print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	      for (local_act_type_cell = local_act_type_cell->next_l_act_type_cell;
	           (local_act_type_cell != NULL);
	           local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
		if (local_act_type_cell->local_act_type != renaming_act_type)
		{
	          fprintf(smv_file,
		          ", ");
	          print_id(local_act_type_cell->local_act_type->symbol_lexeme);
		}
	      fprintf(smv_file,
		      "} : _action_type;\n");
	    }
	    if (!nondet_on_renaming_act_type)
	    {
	      fprintf(smv_file,
		      "             _action_type = ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " : ");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ";\n");
	    }
	    else
	    {
	      fprintf(smv_file,
		      "             _action_type = ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & _M1._can_run_on_");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & !_M1._can_run_on_");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " : ");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ";\n");
	      fprintf(smv_file,
		      "             _action_type = ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & !_M1._can_run_on_");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & _M1._can_run_on_");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " : ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ";\n");
	      fprintf(smv_file,
		      "             _action_type = ");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & _M1._can_run_on_");
	      print_id(to_be_renamed_act_type_cell->local_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " & _M1._can_run_on_");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      " : _nondet_on_");
	      print_id(renaming_act_type->symbol_lexeme);
	      fprintf(smv_file,
		      ";\n");
	    }
	    fprintf(smv_file,
		    "           esac;\n\n\n");
	  }
	return(hiding_renaming_num);
}


void		print_main_module(int hiding_renaming_num)
{
	BOOLEAN		first_non_exec_local_act_type;
	L_ACT_TYPE_CELL	*local_act_type_cell,
			*aux_local_act_type_cell;
	ST_BUCKET	*first_aei;

	/* print the header of the main module */
	fprintf(smv_file,
		"MODULE main\n\n");
	first_aei = archi_type[spec_no]->info.archi_type->aei_list->st_bucket;

	/* print the declaration of the two local variables "running" and "action_type", which represent a
	 * flag indicating whether an action is being executed and, if so, the type of this action (since
	 * the computation model adopted in NuSMV is synchronous, STUTTER is used as the type of the action
	 * whenever no action can actually be executed); all the action types occurring in the translation
	 * must be enumerated in the domain of "_action_type" in order to avoid errors due to undefined
	 * symbols when NuSMV will flatten the translation (command "go") */
	fprintf(smv_file,
		"  VAR _running : boolean;\n\n");
	fprintf(smv_file,
		"  VAR _action_type : {");
	for (local_act_type_cell = all_local_act_type_list;
	     (local_act_type_cell != NULL);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	{
	  print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	  fprintf(smv_file,
		  ",\n                      ");
	}
	fprintf(smv_file,
		"_STUTTER};\n\n");

	/* print the instantiation of the module representing the possibly hidden/renamed parallel
	 * composition of the local state spaces of the AEIs, whose actual input parameters coincide with
	 * the two local variables "running" and "action_type" */
	if (hiding_renaming_num >= 1)
	  fprintf(smv_file,
		  "  VAR _M1 : _hiding_renaming_%d(_running, _action_type);\n\n",
		  hiding_renaming_num);
	else
	  if (archi_type[spec_no]->info.archi_type->aei_num == 1)
	  {
	    fprintf(smv_file,
		    "  VAR _M1 : _seq_1_");
	    print_id(first_aei->symbol_lexeme);
	    fprintf(smv_file,
		    "(_running, _action_type);\n\n");
	  }
	  else
	    fprintf(smv_file,
		    "  VAR _M1 : _par_1_%d(_running, _action_type);\n\n",
		    archi_type[spec_no]->info.archi_type->aei_num);

	/* print the definition of the two local variables (and actual input parameters for the module
	 * above) "running" and "action_type" through invariants based on the output enabledness conditions
	 * "can_run_on_" of the module above as well as the priority levels of the related action types */
	if (first_aei->info.aei->composed_act_type_list != NULL)
	{
	  fprintf(smv_file,
		  "  INVAR !_running <-> !_M1._can_run_on_");
	  print_id(first_aei->info.aei->composed_act_type_list->local_act_type->symbol_lexeme);
	  for (local_act_type_cell = first_aei->info.aei->composed_act_type_list->next_l_act_type_cell;
	       (local_act_type_cell != NULL);
	       local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  {
	    fprintf(smv_file,
		    " &\n                      !_M1._can_run_on_");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	  }
	  fprintf(smv_file,
		  "\n\n");
	}
	fprintf(smv_file,
		"  INVAR !_running <-> _action_type = _STUTTER\n\n");
	for (local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	     (local_act_type_cell != NULL);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	{
	  fprintf(smv_file,
		  "  INVAR _action_type = ");
	  print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	  fprintf(smv_file,
		  " ->\n          _M1._can_run_on_");
	  print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	  if (local_act_type_cell->priority >= 0)
	  {
	    for (aux_local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	         (aux_local_act_type_cell != NULL);
	         aux_local_act_type_cell = aux_local_act_type_cell->next_l_act_type_cell)
	      if (aux_local_act_type_cell->priority > local_act_type_cell->priority)
	      {
	        fprintf(smv_file,
		        " &\n          !_M1._can_run_on_");
		print_id(aux_local_act_type_cell->local_act_type->symbol_lexeme);
	      }
	  }
	  else
	    if (local_act_type_cell->basic_local_act_type != NULL)
	    {
	      for (aux_local_act_type_cell = first_aei->info.aei->composed_act_type_list;
	           (aux_local_act_type_cell != NULL);
	           aux_local_act_type_cell = aux_local_act_type_cell->next_l_act_type_cell)
	        if ((aux_local_act_type_cell->basic_local_act_type ==
		       local_act_type_cell->basic_local_act_type) &&
	            (aux_local_act_type_cell->priority < local_act_type_cell->priority))
		{
	          fprintf(smv_file,
		          " &\n          !_M1._can_run_on_");
		  print_id(aux_local_act_type_cell->local_act_type->symbol_lexeme);
		}
	    }
	  fprintf(smv_file,
		  "\n");
	}
	for (local_act_type_cell = all_local_act_type_list,
	     first_non_exec_local_act_type = TRUE;
	     (local_act_type_cell != NULL);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  if (!check_local_act_type_list_membership(local_act_type_cell->local_act_type,
						    first_aei->info.aei->composed_act_type_list))
	  {
	    if (first_non_exec_local_act_type)
	    {
	      first_non_exec_local_act_type = FALSE;
	      fprintf(smv_file,
		      "\n");
	    }
	    fprintf(smv_file,
		    "  INVAR _action_type != ");
	    print_id(local_act_type_cell->local_act_type->symbol_lexeme);
	    fprintf(smv_file,
		    "\n");
	  }
}


BOOLEAN		check_local_act_type_list_membership(ST_BUCKET       *local_act_type,
						     L_ACT_TYPE_CELL *local_act_type_list)
{
	BOOLEAN		found;
	L_ACT_TYPE_CELL	*local_act_type_cell;

	for (local_act_type_cell = local_act_type_list,
	     found = FALSE;
	     ((local_act_type_cell != NULL) &&
	      !found);
	     local_act_type_cell = local_act_type_cell->next_l_act_type_cell)
	  found = (local_act_type_cell->local_act_type == local_act_type);
	return(found);
}


void		print_id(char *id)
{
	int		i;

	for (i = 0;
	     (id[i] != EOS);
	     i++)
	  if ((id[i] == '[') ||
	      (id[i] == ']'))
	    fprintf(smv_file,
		    "--");
	  else
	    fprintf(smv_file,
		    "%c",
		    (id[i] == '.')?
		      '-':
		      id[i]);
}


void		print_smv_script_file(char *aem_path,
				      char *last_slash)
{
	FILE		*smv_script_file;
	ST_BUCKET_CELL	*property_cell;

        smv_script_file = new_fopen(aem_path,
                                    ".smv_script",
                                    "w");
	fprintf(smv_script_file,
		"set nusmv_stdout %s.smv_output\n",
		(last_slash == NULL)?
		  aem_path:
		  (last_slash + 1));
	fprintf(smv_script_file,
		"set verbose_level 0\n");
	fprintf(smv_script_file,
		"read_model -i %s.smv\n",
		(last_slash == NULL)?
		  aem_path:
		  (last_slash + 1));
	fprintf(smv_script_file,
		"go\n");
	for (property_cell = property_list;
	     (property_cell != NULL);
	     property_cell = property_cell->next_st_bucket_cell)
	  fprintf(smv_script_file,
		  "check_%sspec -p \"%s\"\n",
		  (option_index == CTL_MODEL_CHECK)?
		    "":
		    "ltl",
		  property_cell->st_bucket->info.property_expr->symbol_lexeme);
	fprintf(smv_script_file,
		"quit\n");
	fclose(smv_script_file);
}


void		convert_nusmv_output(char *aem_path)
{
	BOOLEAN		found_end_of_sequence,
			found_loop_start,
			found_running_0,
			square_bracket_open;
	FILE		*smv_output_file,
			*mcr_file;
	ST_BUCKET_CELL	*property_cell;
	char		c,
			next_c;

	/* open the output file produced by NuSMV and the output file to be reported by TwoTowers, and
	 * print the header for the latter */
	smv_output_file = new_fopen(aem_path,
				    ".smv_output",
				    "r");
	mcr_file = new_fopen(aem_path,
			     ".mcr",
			     "w");
	fprintf(mcr_file,
                "Validity of the properties for %s:",
                archi_type[spec_no]->symbol_lexeme);

	/* handle one property at a time */
	for (property_cell = property_list;
	     (property_cell != NULL);
	     property_cell = property_cell->next_st_bucket_cell)
	{
	  fprintf(mcr_file,
                  "\n\n- Property \"%s\" ",
                  property_cell->st_bucket->symbol_lexeme);
	  while (!((fgetc(smv_output_file) == ' ') &&
		   (fgetc(smv_output_file) == 'i') &&
		   (fgetc(smv_output_file) == 's') &&
		   (fgetc(smv_output_file) == ' ')));
	  if (fgetc(smv_output_file) == 't')
	  {
	    /* the property is satisfied */
	    fprintf(mcr_file,
                    "is satisfied.");
	    if (property_cell->next_st_bucket_cell != NULL)
	      while (fgetc(smv_output_file) != '\n');
	  }
	  else
	  {
	    /* print the header for the counterexample */
	    fprintf(mcr_file,
		    "isn't satisfied\n  as demonstrated by the following execution sequence:");
	    while (fgetc(smv_output_file) != '\n');
	    while (fgetc(smv_output_file) != '\n');
	    while (fgetc(smv_output_file) != '\n');
	    while (fgetc(smv_output_file) != '\n');
	    found_end_of_sequence = found_loop_start = found_running_0 = FALSE;
	    /* handle one line of the counterexample at a time, avoiding printing all the unnecessary
	     * translation-related details */
	    do
	    {
	      c = fgetc(smv_output_file);
	      switch (c)
	      {
		case '-':
		  /* found "-> State ... <-", "-- Loop starts here --", or "-- specification ..." */
		  c = fgetc(smv_output_file);
		  if (c != '-')
		    do
		      c = fgetc(smv_output_file);
		    while ((c != '\n') &&
			   (c != EOF));
		  else
		  {
		    c = fgetc(smv_output_file);
		    c = fgetc(smv_output_file);
		    if ((c == 'L') ||
			(c == 'l'))
		    {
		      found_loop_start = TRUE;
		      do
		        c = fgetc(smv_output_file);
		      while ((c != '\n') &&
			     (c != EOF));
		    }
		    else
		      found_end_of_sequence = TRUE;
		  }
		  break;
		case '_':
		  /* found "_running = ...", "_action_type = ...", or "_M1._ ..." */
		  c = fgetc(smv_output_file);
		  if (c == 'r')
		  {
		    do
		      c = fgetc(smv_output_file);
		    while ((c != '='));
		    c = fgetc(smv_output_file);
		    c = fgetc(smv_output_file);
		    found_running_0 = (c == '0');
		    do
		      c = fgetc(smv_output_file);
		    while ((c != '\n') &&
			   (c != EOF));
		  }
		  else
		    if ((c == 'a') &&
		        !found_running_0)
		    {
		      do
		        c = fgetc(smv_output_file);
		      while ((c != '='));
		      c = fgetc(smv_output_file);
		      c = fgetc(smv_output_file);
		      if (found_loop_start)
		      {
		        fprintf(mcr_file,
			        "\n    <<loop starts here>>");
			found_loop_start = FALSE;
		      }
		      fprintf(mcr_file,
			      "\n    ");
		      square_bracket_open = FALSE;
		      do
		      {
			switch (c)
			{
			  case '-':
			    next_c = fgetc(smv_output_file);
			    if (next_c != '-')
			    {
		              fputc('.',
			            mcr_file);
		              ungetc(next_c,
			             smv_output_file);
			    }
			    else
			      if (!square_bracket_open)
			      {
		                fputc('[',
			              mcr_file);
				square_bracket_open = TRUE;
			      }
			      else
			      {
		                fputc(']',
			              mcr_file);
				square_bracket_open = FALSE;
			      }
			    break;
			  case '#':
			    next_c = fgetc(smv_output_file);
			    if (isalpha(next_c))
			    {
		              fputc(c,
			            mcr_file);
		              ungetc(next_c,
			             smv_output_file);
			    }
			    else
			    {
		              do
		                c = fgetc(smv_output_file);
		              while ((c != '\n') &&
			             (c != EOF));
		              ungetc(c,
			             smv_output_file);
			    }
			    break;
			  default:
		            fputc(c,
			          mcr_file);
			    break;
			}
		        c = fgetc(smv_output_file);
		      }
		      while ((c != '\n') &&
			     (c != EOF));
		    }
		    else
		      do
		        c = fgetc(smv_output_file);
		      while ((c != '\n') &&
			     (c != EOF));
		  break;
		default:
		  break;
	      }
	    }
	    while ((c != EOF) &&
		   !found_end_of_sequence);
	  }
	}

	/* close the output file produced by NuSMV and the output file to be reported by TwoTowers */
	fprintf(mcr_file,
		"\n");
	fclose(smv_output_file);
	fclose(mcr_file);
}
