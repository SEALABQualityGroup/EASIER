/****************************************************************/
/*                                                              */
/*                                                              */
/*                      symbol_handler.c                        */
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module collects the symbol handling functionalities common to all the parsers and scanners as well */
/* as the semantic model generator. */
/* This module contains the following functions: */
/* - build_prefixed_id(): It builds an identifier from a given one by prefixing it with the identifier of */
/*			  the context in which the given identifier is defined/declared. */
/* - build_suffixed_id(): It builds an identifier from a given one by appending to it the identifier of */
/*			  an extension. */
/* - build_indexed_id(): It builds an identifier from a given one by appending to it an index. */
/* - build_typed_rec_field_id(): It builds a record field identifier from a given one by prefixing it with */
/*				 its type. */
/* - store_var_decl(): It stores the declaration related information into the bucket for a variable. */
/* - init_struct_var(): It initializes a formal variable parameter or local variable of array or record */
/*			type in case of simulation or concrete value passing. For simulation, this is */
/*			necessary because a sample may refer to a structured variable before it takes a */
/*                      value during the simulation run; this is also useful to avoid a cumbsersome, */
/*                      explicit inizialization of large structures. For concrete value passing, this is */
/*			necessary because a structured variable occurring in an input action must be */
/*			instantiated. */
/* - set_par_behav_invoc_bucket(): It sets the bucket for a parametrized behavior invocation. */
/* - set_action_bucket(): It sets the bucket for an action. */
/* - set_number_bucket(): It sets the bucket for a number. */
/* - set_sample_bucket(): It sets the bucket for a sample and updates the bucket for the involved action */
/*			  type. */
/* - set_expr_bucket(): It sets the bucket for an expression if well typed. */
/* - set_concrete_expr_bucket(): It sets the bucket for a concrete expression by transforming the */
/*				 corresponding evaluated symbolic one. */
/* - set_concrete_uneval_expr_bucket(): It sets the bucket for a concrete expression by transforming the */
/*				        corresponding symbolic one -- which may contain action types -- */
/*					without evaluating it. */
/* - set_nusmv_expr_bucket(): It sets the bucket for a property expression by preparing the action types */
/*			      occurring in it for possible synchronizations and converting them into a */
/*			      format admitted by the input language of NuSMV (i.e. without possible dots */
/*			      and square brackets). */
/* - get_expr_bucket(): It gets the bucket for an expression. */
/* - rewrite_aet_formal_const_par_list(): It rewrites an AET-prefixed formal constant parameter list into */
/*					  the corresponding AEI-prefixed formal constant parameter list. */
/* - rewrite_aet_behavior_list(): It rewrites an AET-prefixed behavior list into the corresponding */
/*				  AEI-prefixed behavior list. */
/* - rewrite_aet_behavior_par_list(): It rewrites an AET-prefixed behavior parameter list into the */
/*				      corresponding AEI-prefixed behavior parameter list. */
/* - rewrite_aet_act_type_list(): It rewrites an AET-prefixed action type list into the corresponding */
/*				  AEI-prefixed action type list. */
/* - rewrite_expr_bucket_list(): It rewrites an AET-prefixed expression bucket list into the corresponding */
/*				 AEI-prefixed expression bucket list. */
/* - rewrite_expr_bucket(): It rewrites an AET-prefixed expression bucket into the corresponding */
/*			    AEI-prefixed expression. */
/* - check_id(): It checks whether an identifier is correctly defined or used in a certain context. */
/* - check_rew_act_type(): It checks whether an action type can be attached a reward. */
/* - check_expr_undecl_const_par_free(): It checks whether an expression is free of identifiers that are */
/*					 not declared formal constant parameters nor possible selectors */
/*					 for AEI indices. */
/* - check_expr_randomness_free(): It checks whether an expression is free of invocations to pseudo random */
/*				   number generators. */
/* - check_expr_list_types(): It checks whether the corresponding expressions of two lists have matching */
/*                            types, where the expressions of the first (resp. second) list are formal */
/*			      resp. actual) parameters. */
/* - check_expr_types(): It checks whether two expressions have matching types up to array lengths, */
/*			 integer bounds, and nullity (if the first expression is a formal parameter, then */
/*			 the second expression is an actual parameter). If the check fails, a looser check */
/*			 can be performed on demand, in order to verify whether the two expressions have */
/*			 matching types up to integer/real compatibility, empty list subtypes, empty array */
/*			 subtypes, and wrong record field types. */
/* - check_expr_all(): It checks whether an integer/real expression is free of identifiers of undeclared */
/*		       formal constant parameters and possible selectors for AEI indices, free of */
/*		       invocations to pseudo random number generators, and well typed. */
/* - check_eval_var_lengths_bounds(): It checks whether the possible array lengths and integer bounds */
/*				      occurring in the data type for a variable are meaningful after */
/*				      evaluating them. */
/* - check_assign_lengths_bounds(): It checks whether the possible array lengths and bounds for integers, */
/*				    priorities, rates, and weights occurring in the evaluated right-hand */
/*				    side expression of an assignment being constructed match with the data */
/*				    type of the left-hand side variable of the assignment. */
/* - check_var_aet_aei_membership(): It checks whether the identifier of a formal variable parameter or */
/*				     local variable is prefixed by the identifier of an AET or AEI. */
/* - handle_concretely_indexed_aei(): It contains the actions to be undertaken when parsing a concretely */
/*				      indexed AEI. */
/* - handle_symbolically_indexed_aei(): It contains the actions to be undertaken when parsing a */
/*				        symbolically indexed AEI. */
/* - handle_prefixed_indexed_id(): It contains the actions to be undertaken when parsing an identifier */
/*				   prefixed by an indexed AEI. */
/* - handle_unprefixed_concretely_indexed_id(): It contains the actions to be undertaken when parsing a */
/*					        concretely indexed identifier not prefixed by an AEI. */
/* - handle_unprefixed_symbolically_indexed_id(): It contains the actions to be undertaken when parsing a */
/*					          symbolically indexed identifier not prefixed by an AEI. */
/* - handle_iteration_1(): It contains the actions to be undertaken when parsing the first part of an */
/*			   iteration. */
/* - handle_iteration_2(): It contains the actions to be undertaken when parsing the second part of an */
/*			   iteration. */
/* - handle_iteration_3(): It contains the actions to be undertaken when parsing the third part of an */
/*			   iteration. */
/* - handle_id_in_expr(): It contains the actions to be undertaken when parsing an identifier within an */
/*			  expression. */
/* - transform_list_into_array(): It transforms a list into an array. */
/* - encode_local_state_comp(): It encodes a symbol lexeme to be used in the concise notation of a local */
/*				state. */
/* - decode_local_state_comp(): It reads a code from the concise notation of a local state and transforms */
/*			        it into the corresponding symbol lexeme. */
/* - eval_local_transition_guards(): It evaluates the guards of the local transitions of the local states */
/*				     composing a global state. */
/* - eval_global_transition_guards(): It evaluates the guards of the global transitions of a global state. */
/* - eval_assign_list(): It evaluates a list of assignments in the appropriate order. For all assignments */
/*			 having in their left hand side variables declared in the same constant, first */
/*			 their right hand expressions are evaluated and their values are stored in the */
/*			 symbol table buckets for the assignments, then their values are stored in the */
/*			 symbol table buckets for the left hand variables. */
/* - eval_expr(): It evaluates an expression and stores its value into its symbol table bucket. */
/* - assign_expr_eval(): It assigns the result of the evaluation of an expression occurring in the right */
/*			 hand side of an assignment to the symbol table bucket of the related assignment */
/*			 expression or assigned variable. */
/* and the following auxiliary functions: */
/* - build_par_behav_invoc_lexeme(): It builds the lexeme for a parametrized behavior invocation. */
/* - build_action_lexeme(): It builds the lexeme of a possibly parametrized action. */
/* - build_par_list_lexeme(): It builds the lexeme of a parameter list surrounded by parentheses. */
/* - build_expr_lexeme(): It builds the lexeme of an expression. Every subexpression is enclosed in */
/*			  parentheses so as to reflect precedence and associativity of the operators. */
/* - build_concrete_expr_lexeme(): It builds the lexeme of a concrete expression. */
/* - build_nusmv_act_type_lexeme(): It builds the lexeme of an action type in the NuSMV format (i.e. */
/*				    without possible dots and square brackets). */
/* - rewrite_var_lengths_bounds(): It rewrites the AET-prefixed expressions for the array lengths and the */
/*				   integer bounds in the data type for a variable into the corresponding */
/*				   AEI-prefixed ones. */
/* - check_expr_array_lengths(): It checks whether two type-checked and evaluated expressions have */
/*				 matching array lengths. */
/* - copy_value(): It duplicates and stores a value by reusing the already allocated memory whenever */
/*		   possible. The copy proceeds in two different ways depending on whether it refers to a */
/*		   list, array or record construction operation or to another list, array or record */
/*		   related operation. In the latter case, since this function is driven by a data type and */
/*		   scalar values are copied before invoking the function, its call must be preceded by a */
/*		   test verifying that the next value to be copied in the recursive descent is structured. */
/* - compare_scalar_value(): It compares two scalar values based on a relational operator. */
/* - compare_struct_value(): It compares two structured values based on a relational operator. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<errno.h>
#include	<float.h>
#include	<math.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external variables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/listing_generator.h"

#include	"../headers/simulator.h"

#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/string_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

BOOLEAN		selector_enabled[2],
			/* flag indicating whether an AEI selector can occur within the expression being */
			/* parsed; */
			/* set by ; */
			/* used in */
		poss_aei_index_parsed;
			/* flag indicating whether a possible AEI index has been parsed; */
			/* set by ; */
			/* used in */

ST_BUCKET	*id_prefix_in_expr,
			/* pointer to the symbol table bucket for the prefix of the identifier being */
			/* parsed within an expression; this is needed to correctly prefix the variables */
			/* occurring within the expressions; */
			/* set by ; */
			/* used in */
		*selector[2],
			/* pointer to the symbol table bucket for the AEI selector being parsed; */
			/* set by ; */
			/* used in */
		*unindexed_aei[2],
			/* pointer to the symbol table bucket for the indexed AEI being parsed without the */
			/* index expression; */
			/* set by ; */
			/* used in */
		*index_expr[2],
			/* pointer to the symbol table bucket for the AEI index expression being parsed; */
			/* set by ; */
			/* used in */
		*unindexed_id[2];
			/* pointer to the symbol table bucket for the identifier being parsed after an */
			/* indexed AEI or without the index expression; */
			/* set by ; */
			/* used in */

int		selector_index;
			/* index of the last AEI selector that has been parsed; */
			/* set by ; */
			/* used in */


void		build_prefixed_id(ST_BUCKET *,
				  ST_BUCKET **),
		build_suffixed_id(ST_BUCKET **,
				  ST_BUCKET *),
		build_indexed_id(ST_BUCKET **,
				 ST_BUCKET *),
		build_typed_rec_field_id(EXPR *,
					 ST_BUCKET **),
		store_var_decl(ST_BUCKET *,
			       SYMBOL_INDEX,
			       EXPR *,
			       VP_INDEX),
		init_struct_var(VALUE_CELL **,
                                EXPR *,
                                int);

ST_BUCKET	*set_par_behav_invoc_bucket(ST_BUCKET *,
                                            ST_BUCKET_CELL *),
		*set_action_bucket(ST_BUCKET *,
				   ACTION_INDEX,
				   ST_BUCKET_CELL *,
				   RATE_INDEX,
				   ST_BUCKET *,
				   ST_BUCKET *),
		*set_number_bucket(char *),
		*set_sample_bucket(ST_BUCKET *,
				   ST_BUCKET *,
				   ST_BUCKET *,
				   BOOLEAN),
		*set_expr_bucket(EXPR_OP_INDEX,
				 ST_BUCKET *,
				 ST_BUCKET *,
				 ST_BUCKET *,
				 long double,
				 VALUE_CELL *,
				 BOOLEAN),
		*set_concrete_expr_bucket(ST_BUCKET *),
		*set_concrete_uneval_expr_bucket(ST_BUCKET *,
						 ST_BUCKET4_CELL *,
						 ST_BUCKET4_CELL *,
						 ST_BUCKET *),
		*set_nusmv_expr_bucket(ST_BUCKET *),
		*get_expr_bucket(EXPR_OP_INDEX,
				 ST_BUCKET *,
				 ST_BUCKET *,
				 ST_BUCKET *,
				 long double,
				 VALUE_CELL *,
				 BOOLEAN);

ST_BUCKET_CELL	*rewrite_aet_formal_const_par_list(ST_BUCKET_CELL *,
						   ST_BUCKET *),
		*rewrite_aet_behavior_list(ST_BUCKET_CELL *,
					   ST_BUCKET *),
		*rewrite_aet_behavior_par_list(ST_BUCKET_CELL *,
					       ST_BUCKET *,
					       ST_BUCKET *),
		*rewrite_aet_act_type_list(ST_BUCKET_CELL *,
					   ST_BUCKET *),
		*rewrite_expr_bucket_list(ST_BUCKET_CELL *,
				          ST_BUCKET *);

ST_BUCKET	*rewrite_expr_bucket(ST_BUCKET *,
			             ST_BUCKET *);

void		check_id(ID_CONTEXT_INDEX,
			 ST_BUCKET **,
			 BOOLEAN);

BOOLEAN         check_rew_act_type(ST_BUCKET *,
				   BOOLEAN),
		check_expr_undecl_const_par_free(ST_BUCKET *,
						 ST_BUCKET *,
						 ST_BUCKET *),
		check_expr_randomness_free(ST_BUCKET *),
		check_expr_list_types(ST_BUCKET_CELL *,
                                      ST_BUCKET_CELL *,
                                      BOOLEAN),
                check_expr_types(EXPR *,
                                 EXPR *,
                                 BOOLEAN),
		check_expr_all(ST_BUCKET *,
			       ST_BUCKET *,
			       ST_BUCKET *,
			       ST_BUCKET *,
			       ERROR_INDEX,
			       ERROR_INDEX,
			       ERROR_INDEX),
		check_eval_var_lengths_bounds(EXPR *,
					      BOOLEAN,
					      BOOLEAN,
					      char *),
		check_assign_lengths_bounds(EXPR *,
					    EXPR *,
					    BOOLEAN,
					    char *),
		check_var_aet_aei_membership(ST_BUCKET *,
					     SYMBOL_INDEX);

void		handle_concretely_indexed_aei(ST_BUCKET **,
					      EXPR_PARSE_INFO *,
					      int,
					      BOOLEAN,
					      BOOLEAN),
		handle_symbolically_indexed_aei(ST_BUCKET **,
					        EXPR_PARSE_INFO *,
						int,
					        BOOLEAN),
		handle_prefixed_indexed_id(ST_BUCKET *,
					   ST_BUCKET **,
					   int,
					   BOOLEAN,
					   BOOLEAN,
					   ID_CONTEXT_INDEX),
		handle_unprefixed_concretely_indexed_id(ST_BUCKET **,
						        EXPR_PARSE_INFO *,
							ID_CONTEXT_INDEX),
		handle_unprefixed_symbolically_indexed_id(ST_BUCKET **,
						          EXPR_PARSE_INFO *,
						          int,
							  ID_CONTEXT_INDEX),
		handle_iteration_1(ST_BUCKET **),
		handle_iteration_2(EXPR_PARSE_INFO **);

ST_BUCKET	*handle_iteration_3(ST_BUCKET *,
				    EXPR_PARSE_INFO *,
				    EXPR_PARSE_INFO *);

EXPR_PARSE_INFO	*handle_id_in_expr(ST_BUCKET **,
				   ST_BUCKET *,
				   ST_BUCKET_CELL **,
				   BOOLEAN,
				   BOOLEAN);

VALUE_CELL	*transform_list_into_array(VALUE_CELL *,
					   int);

void		encode_local_state_comp(ST_BUCKET *);

ST_BUCKET	*decode_local_state_comp(char *,
					 int *);

void		eval_local_transition_guards(LST_BUCKET **),
		eval_global_transition_guards(G_TRANS_CELL *),
		eval_assign_list(ST_BUCKET_CELL *,
				 int),
		eval_expr(ST_BUCKET *,
			  int),
		assign_expr_eval(EXPR *,
				 EXPR *,
				 ASSIGN_INDEX,
				 char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	char		*build_par_behav_invoc_lexeme(ST_BUCKET *,
						      ST_BUCKET_CELL *),
			*build_action_lexeme(ST_BUCKET *,
					     ACTION_INDEX,
					     ST_BUCKET_CELL *,
					     RATE_INDEX,
					     ST_BUCKET *,
					     ST_BUCKET *),
			*build_par_list_lexeme(ST_BUCKET_CELL *),
			*build_expr_lexeme(EXPR_OP_INDEX,
					   ST_BUCKET *,
					   ST_BUCKET *,
					   ST_BUCKET *,
					   long double,
					   VALUE_CELL *,
					   BOOLEAN),
			*build_concrete_expr_lexeme(ST_BUCKET *),
			*build_nusmv_act_type_lexeme(char *);

PRIVATE	void		rewrite_var_lengths_bounds(EXPR *,
						   ST_BUCKET  *);

PRIVATE	BOOLEAN		check_expr_array_lengths(EXPR *,
						 EXPR *);

PRIVATE	void		copy_value(VALUE_CELL **,
				   VALUE_CELL *,
				   BOOLEAN,
				   EXPR *,
				   int,
				   int,
				   char *);

PRIVATE	long double	compare_scalar_value(long double,
					     long double,
					     EXPR_OP_INDEX),
			compare_struct_value(VALUE_CELL *,
					     VALUE_CELL *,
					     EXPR_OP_INDEX,
					     EXPR *,
					     int,
					     int);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void            build_prefixed_id(ST_BUCKET *prefix,
                                  ST_BUCKET **id)
{
        static  char            *prefixed_name  =       NULL;
                int             length;
        static  int             max_length      =       0;

        length = ((prefix == NULL)?
                    2:
                    (strlen(prefix->symbol_lexeme) + 1)) +
                 strlen((*id)->symbol_lexeme);
        if (length > max_length)
        {
          if (prefixed_name != NULL)
            free(prefixed_name);
          prefixed_name = alloc_string(length);
          max_length = length;
        }
        sprintf(prefixed_name,
                "%s.%s",
                (prefix == NULL)?
                  "@":
                  prefix->symbol_lexeme,
                (*id)->symbol_lexeme);
        *id = (ST_BUCKET *)search_lexeme_table(prefixed_name,
                                               SYT);
}


void		build_suffixed_id(ST_BUCKET **id,
				  ST_BUCKET *suffix)
{
        static  char            *suffixed_name  =       NULL;
                int             length;
        static  int             max_length      =       0;

        length = strlen((*id)->symbol_lexeme) +
		 1 +
                 strlen(suffix->symbol_lexeme);
        if (length > max_length)
        {
          if (suffixed_name != NULL)
            free(suffixed_name);
          suffixed_name = alloc_string(length);
          max_length = length;
        }
        sprintf(suffixed_name,
                "%s.%s",
                (*id)->symbol_lexeme,
                suffix->symbol_lexeme);
        *id = (ST_BUCKET *)search_lexeme_table(suffixed_name,
                                               SYT);
}


void		build_indexed_id(ST_BUCKET **id,
				 ST_BUCKET *index)
{
	static  char            *indexed_name	=       NULL;
                int             length;
        static  int             max_length      =       0;

        length = strlen((*id)->symbol_lexeme) +
		 1 +
                 strlen(index->symbol_lexeme) +
		 1;
        if (length > max_length)
        {
          if (indexed_name != NULL)
            free(indexed_name);
          indexed_name = alloc_string(length);
          max_length = length;
        }
        sprintf(indexed_name,
                "%s[%s]",
                (*id)->symbol_lexeme,
                index->symbol_lexeme);
        *id = (ST_BUCKET *)search_lexeme_table(indexed_name,
                                               SYT);
}


void		build_typed_rec_field_id(EXPR      *rec_field_type,
					 ST_BUCKET **rec_field_id)
{
	static	char		*prefixed_name	=	NULL;
		int		length;
	static	int		max_length	=	0;

	length = strlen(rec_field_type->data_type->data_type_lexeme) +
		 1 +
		 strlen((*rec_field_id)->symbol_lexeme);
	if (length > max_length)
        {
          if (prefixed_name != NULL)
            free(prefixed_name);
          prefixed_name = alloc_string(length);
          max_length = length;
        }
	sprintf(prefixed_name,
		"%s;%s",
		rec_field_type->data_type->data_type_lexeme,
		(*rec_field_id)->symbol_lexeme);
	*rec_field_id = (ST_BUCKET *)search_lexeme_table(prefixed_name,
					                 SYT);
}


void            store_var_decl(ST_BUCKET    *variable,
                               SYMBOL_INDEX symbol_index,
                               EXPR         *variable_type,
			       VP_INDEX     value_passing)
{
        if (variable != NULL)
        {
	  /* store the variable declaration */
          variable->symbol_index = symbol_index;
          variable->info.expr = variable_type;
          variable->info.expr->op_index = VAR_OP;
	  /* update information about value passing */
          if ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) &&
	      ((symbol_index == FORMAL_VAR_PAR_ID) ||
               (symbol_index == LOCAL_VAR_ID)))
	    archi_type[spec_no]->info.archi_type->value_passing = (option_index == SIMULATION)?
								    (char)SYMBOLIC_VP:
								    (char)value_passing;
        }
}


void            init_struct_var(VALUE_CELL **value_cell,
                                EXPR       *expr,
                                int        depth)
{
        EXPR            *sub_expr;
        VALUE_CELL      **array_elem,
                        *field,
                        *aux_field;
        int             array_length,
                        array_index;

        /* exploit the type information stored by handle_struct_data_type_decl() of empa-parser.y and also */
	/* the value cells built by the same function when depth is 1 */
        if (expr->data_type->data_type_lexeme[1] != EOS)
          switch (expr->data_type->data_type_lexeme[0])
          {
            case 'a':
              array_length = (int)(expr->value);
              if (array_length != 0)
              {
                if (depth == 1)
                  (*value_cell)->struct_value = (VALUE_CELL *)new_calloc(array_length,
                                                                         sizeof(VALUE_CELL *));
                else
                  *value_cell = (VALUE_CELL *)new_calloc(array_length,
                                                         sizeof(VALUE_CELL *));
                sub_expr = expr->struct_value->value_bucket->info.expr;
                for (array_elem = (depth == 1)?
                                    (VALUE_CELL **)((*value_cell)->struct_value):
                                    (VALUE_CELL **)(*value_cell),
                     array_index = 0;
                     (array_index < array_length);
                     array_elem++,
                     array_index++)
                {
                  *array_elem = alloc_value_cell(expr->struct_value->value_bucket,
                                                 0.0L,
                                                 NULL,
                                                 NULL);
                  if ((sub_expr->data_type->data_type_lexeme[0] == 'a') ||
                      (sub_expr->data_type->data_type_lexeme[0] == 'p'))
                    init_struct_var(&((*array_elem)->struct_value),
                                    sub_expr,
                                    depth + 1);
                }
              }
              break;
            case 'p':
              if (depth == 1)
                aux_field = *value_cell;
              else
                aux_field = NULL;
              for (field = expr->struct_value;
                   (field != NULL);
                   field = field->next_value_cell)
              {
                sub_expr = field->value_bucket->info.expr;
                if (depth > 1)
                {
                  if (aux_field == NULL)
                    aux_field = *value_cell = alloc_value_cell(field->value_bucket,
                                                               0.0L,
                                                               NULL,
                                                               NULL);
                  else
                    aux_field = aux_field->next_value_cell = alloc_value_cell(field->value_bucket,
                                                                              0.0L,
                                                                              NULL,
                                                                              NULL);
                }
                if ((sub_expr->data_type->data_type_lexeme[0] == 'a') ||
                    (sub_expr->data_type->data_type_lexeme[0] == 'p'))
                  init_struct_var(&(aux_field->struct_value),
                                  field->value_bucket->info.expr,
                                  depth + 1);
                if (depth == 1)
                  aux_field = aux_field->next_value_cell;
              }
              break;
            default:
              break;
          }
}


ST_BUCKET	*set_par_behav_invoc_bucket(ST_BUCKET      *behavior,
                                            ST_BUCKET_CELL *actual_par_list)
{
	ST_BUCKET	*par_behav_invoc;
	char		*par_behav_invoc_lexeme;

	par_behav_invoc_lexeme = build_par_behav_invoc_lexeme(behavior,
							      actual_par_list);
	par_behav_invoc = search_lexeme_table(par_behav_invoc_lexeme,
					      SYT);
	if (par_behav_invoc->symbol_index == ID)
	{
	  par_behav_invoc->symbol_index = PAR_BEHAV_INVOC_STRING;
	  par_behav_invoc->info.par_behav_invoc = alloc_par_behav_invoc(behavior,
							                actual_par_list);
	}
	return(par_behav_invoc);
}


ST_BUCKET	*set_action_bucket(ST_BUCKET      *type,
				   ACTION_INDEX   action_index,
				   ST_BUCKET_CELL *par_list,
				   RATE_INDEX     rate_index,
				   ST_BUCKET      *priority,
				   ST_BUCKET      *rate)
{
	ST_BUCKET	*action;
	char		*action_lexeme;

	action_lexeme = build_action_lexeme(type,
					    action_index,
					    par_list,
					    rate_index,
					    priority,
					    rate);
	action = (ST_BUCKET *)search_lexeme_table(action_lexeme,
						  SYT);
	if (action->symbol_index == ID)
	{
	  action->symbol_index = ACTION_STRING;
	  action->info.action = alloc_action(type,
				             action_index,
				             par_list,
				             rate_index,
				             priority,
				             rate);
	}
	return(action);
}


ST_BUCKET	*set_number_bucket(char *number_lexeme)
{
	ST_BUCKET	*number;

	number = (ST_BUCKET *)search_lexeme_table(number_lexeme,
						  SYT);
	if (number->symbol_index == ID)
	{
	  number->symbol_index = NUMBER;
	  number->info.expr = alloc_expr(NUMBER_OP,
				         NULL,
				         NULL,
				         NULL,
				         (strchr(number_lexeme,
						 '.') == NULL)?
				           (DTT_BUCKET *)search_lexeme_table("i",
							                     DTT):
				           (DTT_BUCKET *)search_lexeme_table("r",
							                     DTT),
				         FALSE,
				         (long double)atof(number_lexeme),
				         NULL);
	  if (errno == ERANGE)
	  {
	    if ((number->info.expr->value == (long double)HUGE_VAL) ||
		(number->info.expr->value == (long double)-HUGE_VAL))
	      signal_error(NUMERIC_OVERFLOW,
			   NULL,
			   NULL);
	    else
	      signal_error(NUMERIC_UNDERFLOW,
			   NULL,
			   NULL);
	    /* the following assignment avoids spurious crashes or errors in case of priorities or */
	    /* rates/weights, as they must be put into a string and cannot be zero */
	    number->info.expr->value = 1.0L;
	  }
	}
	return(number);
}


ST_BUCKET	*set_sample_bucket(ST_BUCKET *sim_measure_id,
				   ST_BUCKET *act_type,
				   ST_BUCKET *reward,
				   BOOLEAN   cumulative)
{
	ST_BUCKET	*sample;

	sample = set_expr_bucket(SAMPLE_OP,
				 sim_measure_id,
				 act_type,
				 reward,
				 0.0L,
				 NULL,
				 cumulative);
	if (sample->info.expr->sample == NULL)
	{
	  sample->info.expr->sample = alloc_sample(sample);
	  if (act_type->info.act_type != NULL)
	    act_type->info.act_type->reward_info.sample_list =
	      alloc_sample_cell(sample->info.expr->sample,
			        act_type->info.act_type->reward_info.sample_list);
	}
	return(sample);
}


ST_BUCKET	*set_expr_bucket(EXPR_OP_INDEX op_index,
				 ST_BUCKET     *opn1,
				 ST_BUCKET     *opn2,
				 ST_BUCKET     *opn3,
				 long double   array_length,
				 VALUE_CELL    *struct_value,
				 BOOLEAN       cumulative)
{
		BOOLEAN		first_op_anomaly,
				read_op_anomaly,
				get_op_anomaly,
				put_op_anomaly,
				ill_typed_list,
				ill_typed_array,
				null_typed_expr;
		EXPR		*aux_expr,
				*expr1,
				*expr2,
				*expr3;
		DTT_BUCKET	*type;
		ST_BUCKET	*expr;
		VALUE_CELL	*list_elem,
				**array_elem,
				*record_field;
		char		*expr_lexeme;
        static  char            *data_type_lexeme	=       NULL;
                int             length,
				field_length,
				array_index;
        static  int             max_length		=       0;

	expr_lexeme = build_expr_lexeme(op_index,
					opn1,
					opn2,
					opn3,
					array_length,
					struct_value,
					cumulative);
	expr = (ST_BUCKET *)search_lexeme_table(expr_lexeme,
						SYT);
	if (expr->symbol_index == ID)
	{
	  if ((op_index != LIST_CONS_OP) &&
	      (op_index != ARRAY_CONS_OP) &&
	      (op_index != RECORD_CONS_OP) &&
	      (((op_index == GET_OP) &&
		(opn1 == NULL)) ||
	       ((op_index == PUT_OP) &&
		(opn1 == NULL)) ||
	       ((op_index != SAMPLE_OP) &&
		strcmp(opn1->symbol_lexeme,
		       "_action_type") &&
		(opn1->symbol_index != PROPERTY_ID) &&
		(opn1->symbol_index != PROPERTY_VAR_ID) &&
	        ((opn1->info.expr == NULL) ||
	         (opn1->info.expr->data_type == NULL))) ||
	       ((opn2 != NULL) &&
		strcmp(opn2->symbol_lexeme,
		       "_STUTTER") &&
		(opn2->symbol_index != ACT_TYPE_ID) &&
		(opn2->symbol_index != PROPERTY_ID) &&
		(opn2->symbol_index != PROPERTY_VAR_ID) &&
	        ((opn2->info.expr == NULL) ||
	         (opn2->info.expr->data_type == NULL))) ||
	       ((opn3 != NULL) &&
	        ((opn3->info.expr == NULL) ||
	         (opn3->info.expr->data_type == NULL)))))
	    type = NULL;
	  else
	  {
	    expr1 = ((opn1 != NULL) &&
		     (op_index != SAMPLE_OP) &&
		     strcmp(opn1->symbol_lexeme,
		            "_action_type") &&
		     (opn1->symbol_index != PROPERTY_ID) &&
		     (opn1->symbol_index != PROPERTY_VAR_ID))?
		      opn1->info.expr:
		      NULL;
	    expr2 = ((opn2 != NULL) &&
		     strcmp(opn2->symbol_lexeme,
		            "_STUTTER") &&
		     (opn2->symbol_index != ACT_TYPE_ID) &&
		     (opn2->symbol_index != PROPERTY_ID) &&
		     (opn2->symbol_index != PROPERTY_VAR_ID))?
		      opn2->info.expr:
		      NULL;
	    expr3 = (opn3 != NULL)?
		      opn3->info.expr:
		      NULL;
	    first_op_anomaly = FALSE;
	    read_op_anomaly = FALSE;
	    get_op_anomaly = FALSE;
	    put_op_anomaly = FALSE;
	    switch (op_index)
	    {
	      case ASSIGN_OP:
	        if (check_expr_types(expr1,
				     expr2,
				     FALSE))
		{
		  /* init_table() of table-handler.c and set_expr_bucket() set to l the type of the empty */
		  /* list, and expression lists/arrays/records may not carry complete type information in */
		  /* the case of record subtype; therefore, more information is provided if the data type */
		  /* of the left hand operand of the assignment is considered */
		  type = expr1->data_type;
		  array_length = expr1->value;
		  struct_value = duplicate_struct_expr(expr1->struct_value,
						       expr1->data_type->data_type_lexeme[0]);
		}
		else
		  type = NULL;
	        break;
	      case PLUS_OP:
	      case MINUS_OP:
	      case TIMES_OP:
	      case MIN_OP:
	      case MAX_OP:
	        if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr2->data_type->data_type_lexeme[0] == 'i'))
		  type = expr1->data_type;
	        else
	          if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		       (expr1->data_type->data_type_lexeme[0] == 'r') ||
		       (expr1->data_type->data_type_lexeme[0] == 'P') ||
		       (expr1->data_type->data_type_lexeme[0] == 'R') ||
		       (expr1->data_type->data_type_lexeme[0] == 'W')) &&
	              ((expr2->data_type->data_type_lexeme[0] == 'i') ||
		       (expr2->data_type->data_type_lexeme[0] == 'r') ||
		       (expr2->data_type->data_type_lexeme[0] == 'P') ||
		       (expr2->data_type->data_type_lexeme[0] == 'R') ||
		       (expr2->data_type->data_type_lexeme[0] == 'W')))
		    type = (DTT_BUCKET *)search_lexeme_table("r",
							     DTT);
		  else
		    type = NULL;
	        break;
	      case DIV_OP:
	      case POWER_OP:
	      case C_UNIFORM_OP:
	      case GAMMA_OP:
	      case WEIBULL_OP:
	      case BETA_OP:
	      case NORMAL_OP:
	        if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		     (expr1->data_type->data_type_lexeme[0] == 'r') ||
		     (expr1->data_type->data_type_lexeme[0] == 'P') ||
		     (expr1->data_type->data_type_lexeme[0] == 'R') ||
		     (expr1->data_type->data_type_lexeme[0] == 'W')) &&
	            ((expr2->data_type->data_type_lexeme[0] == 'i') ||
		     (expr2->data_type->data_type_lexeme[0] == 'r') ||
		     (expr2->data_type->data_type_lexeme[0] == 'P') ||
		     (expr2->data_type->data_type_lexeme[0] == 'R') ||
		     (expr2->data_type->data_type_lexeme[0] == 'W')))
		  type = (DTT_BUCKET *)search_lexeme_table("r",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case MOD_OP:
		if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr2->data_type->data_type_lexeme[0] == 'i'))
		  type = expr1->data_type;
	        else
		  type = NULL;
	        break;
	      case ABS_OP:
	        if ((expr1->data_type->data_type_lexeme[0] == 'i') ||
		    (expr1->data_type->data_type_lexeme[0] == 'r') ||
		    (expr1->data_type->data_type_lexeme[0] == 'P') ||
		    (expr1->data_type->data_type_lexeme[0] == 'R') ||
		    (expr1->data_type->data_type_lexeme[0] == 'W'))
		  type = expr1->data_type;
	        else
		  type = NULL;
	        break;
	      case CEIL_OP:
	      case FLOOR_OP:
	      case POISSON_OP:
	      case GEOMETRIC_OP:
	        if ((expr1->data_type->data_type_lexeme[0] == 'i') ||
		    (expr1->data_type->data_type_lexeme[0] == 'r') ||
		    (expr1->data_type->data_type_lexeme[0] == 'P') ||
		    (expr1->data_type->data_type_lexeme[0] == 'R') ||
		    (expr1->data_type->data_type_lexeme[0] == 'W'))
		  type = (DTT_BUCKET *)search_lexeme_table("i",
						           DTT);
	        else
		  type = NULL;
	        break;
	      case EPOWER_OP:
	      case LOGE_OP:
	      case LOG10_OP:
	      case SQRT_OP:
	      case SIN_OP:
	      case COS_OP:
	      case EXPONENTIAL_OP:
	      case PARETO_OP:
	        if ((expr1->data_type->data_type_lexeme[0] == 'i') ||
		    (expr1->data_type->data_type_lexeme[0] == 'r') ||
		    (expr1->data_type->data_type_lexeme[0] == 'P') ||
		    (expr1->data_type->data_type_lexeme[0] == 'R') ||
		    (expr1->data_type->data_type_lexeme[0] == 'W'))
		  type = (DTT_BUCKET *)search_lexeme_table("r",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case B_PARETO_OP:
	        if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		     (expr1->data_type->data_type_lexeme[0] == 'r') ||
		     (expr1->data_type->data_type_lexeme[0] == 'P') ||
		     (expr1->data_type->data_type_lexeme[0] == 'R') ||
		     (expr1->data_type->data_type_lexeme[0] == 'W')) &&
	            ((expr2->data_type->data_type_lexeme[0] == 'i') ||
		     (expr2->data_type->data_type_lexeme[0] == 'r') ||
		     (expr2->data_type->data_type_lexeme[0] == 'P') ||
		     (expr2->data_type->data_type_lexeme[0] == 'R') ||
		     (expr2->data_type->data_type_lexeme[0] == 'W')) &&
	            ((expr3->data_type->data_type_lexeme[0] == 'i') ||
		     (expr3->data_type->data_type_lexeme[0] == 'r') ||
		     (expr3->data_type->data_type_lexeme[0] == 'P') ||
		     (expr3->data_type->data_type_lexeme[0] == 'R') ||
		     (expr3->data_type->data_type_lexeme[0] == 'W')))
		  type = (DTT_BUCKET *)search_lexeme_table("r",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case ERLANG_OP:
	        if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		     (expr1->data_type->data_type_lexeme[0] == 'r') ||
		     (expr1->data_type->data_type_lexeme[0] == 'P') ||
		     (expr1->data_type->data_type_lexeme[0] == 'R') ||
		     (expr1->data_type->data_type_lexeme[0] == 'W')) &&
		    (expr2->data_type->data_type_lexeme[0] == 'i'))
		  type = (DTT_BUCKET *)search_lexeme_table("r",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case D_UNIFORM_OP:
		if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr2->data_type->data_type_lexeme[0] == 'i'))
		  type = (DTT_BUCKET *)search_lexeme_table("i",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case BERNOULLI_OP:
	        if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		     (expr1->data_type->data_type_lexeme[0] == 'r') ||
		     (expr1->data_type->data_type_lexeme[0] == 'P') ||
		     (expr1->data_type->data_type_lexeme[0] == 'R') ||
		     (expr1->data_type->data_type_lexeme[0] == 'W')) &&
		    (expr2->data_type->data_type_lexeme[0] == expr1->data_type->data_type_lexeme[0]) &&
	            ((expr3->data_type->data_type_lexeme[0] == 'i') ||
		     (expr3->data_type->data_type_lexeme[0] == 'r') ||
		     (expr3->data_type->data_type_lexeme[0] == 'P') ||
		     (expr3->data_type->data_type_lexeme[0] == 'R') ||
		     (expr3->data_type->data_type_lexeme[0] == 'W')))
		  type = expr1->data_type;
	        else
		  type = NULL;
	        break;
	      case BINOMIAL_OP:
	      case NEG_BINOMIAL_OP:
	      case PASCAL_OP:
	        if (((expr1->data_type->data_type_lexeme[0] == 'i') ||
		     (expr1->data_type->data_type_lexeme[0] == 'r') ||
		     (expr1->data_type->data_type_lexeme[0] == 'P') ||
		     (expr1->data_type->data_type_lexeme[0] == 'R') ||
		     (expr1->data_type->data_type_lexeme[0] == 'W')) &&
		    (expr2->data_type->data_type_lexeme[0] == 'i'))
		  type = (DTT_BUCKET *)search_lexeme_table("i",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case AND_OP:
	      case OR_OP:
	      case CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP:
	      case CTL_FOR_ALL_PATHS_WEAK_UNTIL_OP:
	      case CTL_EXISTS_PATH_STRONG_UNTIL_OP:
	      case CTL_EXISTS_PATH_WEAK_UNTIL_OP:
	      case LTL_UNTIL_OP:
	      case LTL_RELEASES_OP:
	      case LTL_SINCE_OP:
	      case LTL_TRIGGERED_OP:
	        if ((expr1->data_type->data_type_lexeme[0] == 'b') &&
		    (expr2->data_type->data_type_lexeme[0] == 'b'))
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case NOT_OP:
	      case PROP_NOT_OP:
	      case CTL_FOR_ALL_PATHS_ALL_STATES_SAT_OP:
	      case CTL_FOR_ALL_PATHS_SOME_STATE_SAT_OP:
	      case CTL_EXISTS_PATH_ALL_STATES_SAT_OP:
	      case CTL_EXISTS_PATH_SOME_STATE_SAT_OP:
	      case LTL_NEXT_STATE_SAT_OP:
	      case LTL_ALL_FUTURE_STATES_SAT_OP:
	      case LTL_SOME_FUTURE_STATE_SAT_OP:
	      case LTL_PREV_STATE_SAT_OP:
	      case LTL_ALL_PAST_STATES_SAT_OP:
	      case LTL_SOME_PAST_STATE_SAT_OP:
	        if (expr1->data_type->data_type_lexeme[0] == 'b')
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case EQ_OP:
	      case NE_OP:
	        if (!strcmp(opn1->symbol_lexeme,
			    "_action_type") ||
		    check_expr_types(expr1,
				     expr2,
				     TRUE))
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case LT_OP:
	      case LE_OP:
	      case GT_OP:
	      case GE_OP:
	        if (check_expr_types(expr1,
				     expr2,
				     TRUE))
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case LIST_CONS_OP:
		/* like in init_table() of table-handler.c, the type of the empty list is set to l; here */
		/* the type is set to l also in the case of an expression list containing type errors in */
		/* order to distinguish it from an expression array/record */
		if (struct_value == NULL)
		  type = (DTT_BUCKET *)search_lexeme_table("l",
							   DTT);
		else
		{
		  for (list_elem = struct_value,
		       aux_expr = (list_elem->value_bucket != NULL)?
				    list_elem->value_bucket->info.expr:
				    NULL,
		       ill_typed_list = FALSE,
		       null_typed_expr = (list_elem->value_bucket == NULL);
		       (!ill_typed_list &&
		        !null_typed_expr &&
			(list_elem != NULL));
		       list_elem = list_elem->next_value_cell)
		    if (list_elem->value_bucket->info.expr->data_type == NULL)
		      null_typed_expr = TRUE;
		    else
		      if (aux_expr->data_type == NULL)
			aux_expr = list_elem->value_bucket->info.expr;
		      else
		        if (!check_expr_types(list_elem->value_bucket->info.expr,
					      aux_expr,
					      FALSE))
			{
			  signal_error(ILL_TYPED_LIST,
				       NULL,
				       NULL);
			  ill_typed_list = TRUE;
			}
		  if (ill_typed_list ||
		      null_typed_expr)
		    type = (DTT_BUCKET *)search_lexeme_table("l",
							     DTT);
		  else
		  {
		    length = 2 +
			     strlen(aux_expr->data_type->data_type_lexeme) +
			     1;
        	    if (length > max_length)
        	    {
          	      if (data_type_lexeme != NULL)
            	        free(data_type_lexeme);
          	      data_type_lexeme = alloc_string(length);
          	      max_length = length;
        	    }
		    sprintf(data_type_lexeme,
			    "l(%s)",
			    aux_expr->data_type->data_type_lexeme);
		    type = (DTT_BUCKET *)search_lexeme_table(data_type_lexeme,
							     DTT);
		  }
		}
	        break;
	      case FIRST_OP:
		/* the check for empty list or expression list containing type errors is not an attempt to */
		/* catch errors statically, but is necessary to associate a meaningful type with the */
		/* expression */
		if (expr1->data_type->data_type_lexeme[0] == 'l')
		  if (expr1->data_type->data_type_lexeme[1] != EOS)
		  {
		    list_elem = expr1->struct_value;
                    type = list_elem->value_bucket->info.expr->data_type;
		    if (type->data_type_lexeme[0] == 'a')
		      array_length = list_elem->value_bucket->info.expr->value;
		    struct_value = duplicate_struct_expr(list_elem->value_bucket->info.expr->struct_value,
							 type->data_type_lexeme[0]);
		  }
	          else
		  {
		    type = NULL;
		    first_op_anomaly = TRUE;
		  }
		else
		  type = NULL;
	        break;
	      case TAIL_OP:
		if (expr1->data_type->data_type_lexeme[0] == 'l')
		{
		  type = expr1->data_type;
		  struct_value = duplicate_struct_expr(expr1->struct_value,
						       type->data_type_lexeme[0]);
		}
	        else
		  type = NULL;
	        break;
	      case CONCAT_OP:
		if ((expr1->data_type->data_type_lexeme[0] == 'l') &&
		    (expr2->data_type->data_type_lexeme[0] == 'l') &&
		    ((expr1->data_type->data_type_lexeme[1] == EOS) ||
		     (expr2->data_type->data_type_lexeme[1] == EOS) ||
		     check_expr_types(expr1->struct_value->value_bucket->info.expr,
				      expr2->struct_value->value_bucket->info.expr,
				      FALSE)))
		  if (expr1->data_type->data_type_lexeme[1] != EOS)
		  {
		    type = expr1->data_type;
		    struct_value = duplicate_struct_expr(expr1->struct_value,
							 type->data_type_lexeme[0]);
		  }
		  else
		  {
		    type = expr2->data_type;
		    struct_value = duplicate_struct_expr(expr2->struct_value,
							 type->data_type_lexeme[0]);
		  }
		else
		  type = NULL;
	        break;
	      case INSERT_OP:
		if ((expr2->data_type->data_type_lexeme[0] == 'l') &&
		    ((expr2->data_type->data_type_lexeme[1] == EOS) ||
		     check_expr_types(expr1,
				      expr2->struct_value->value_bucket->info.expr,
				      FALSE)))
		  if ((strlen(expr2->data_type->data_type_lexeme) - 2) <
			 strlen(expr1->data_type->data_type_lexeme))
		  {
		    length = 2 +
			     strlen(expr1->data_type->data_type_lexeme) +
			     1;
        	    if (length > max_length)
        	    {
          	      if (data_type_lexeme != NULL)
            	        free(data_type_lexeme);
          	      data_type_lexeme = alloc_string(length);
          	      max_length = length;
        	    }
		    sprintf(data_type_lexeme,
			    "l(%s)",
			    expr1->data_type->data_type_lexeme);
		    type = (DTT_BUCKET *)search_lexeme_table(data_type_lexeme,
							     DTT);
                    struct_value = alloc_value_cell(opn1,
						    (long double)DBL_MAX,
						    NULL,
						    NULL);
		  }
		  else
		  {
		    type = expr2->data_type;
		    struct_value = duplicate_struct_expr(expr2->struct_value,
							 type->data_type_lexeme[0]);
		  }
	        else
		  type = NULL;
	        break;
	      case REMOVE_OP:
		if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr2->data_type->data_type_lexeme[0] == 'l'))
		{
		  type = expr2->data_type;
		  struct_value = duplicate_struct_expr(expr2->struct_value,
						       type->data_type_lexeme[0]);
		}
	        else
		  type = NULL;
	        break;
	      case LENGTH_OP:
		if (expr1->data_type->data_type_lexeme[0] == 'l')
		  type = (DTT_BUCKET *)search_lexeme_table("i",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case ARRAY_CONS_OP:
		/* the type is set to a in the case of an expression array containing type errors in order */
	        /* to distinguish it from an expression list/record */
		for (array_elem = (VALUE_CELL **)(struct_value->struct_value),
		     aux_expr = (*array_elem)->value_bucket->info.expr,
		     ill_typed_array = null_typed_expr = FALSE,
		     array_index = 0;
		     (!ill_typed_array &&
		      (array_index < (int)array_length));
		     array_elem++,
		     array_index++)
		  if ((*array_elem)->value_bucket->info.expr->data_type == NULL)
		    null_typed_expr = TRUE;
		  else
		    if (aux_expr->data_type == NULL)
		      aux_expr = (*array_elem)->value_bucket->info.expr;
		    else
		      if (!check_expr_types((*array_elem)->value_bucket->info.expr,
					    aux_expr,
					    FALSE))
		      {
			signal_error(ILL_TYPED_ARRAY,
				     NULL,
				     NULL);
			ill_typed_array = TRUE;
		      }
		if (ill_typed_array ||
		    null_typed_expr)
		  type = (DTT_BUCKET *)search_lexeme_table("a",
							   DTT);
		else
		{
		  length = 2 +
			   strlen(aux_expr->data_type->data_type_lexeme) +
			   1;
        	  if (length > max_length)
        	  {
          	    if (data_type_lexeme != NULL)
            	      free(data_type_lexeme);
          	    data_type_lexeme = alloc_string(length);
          	    max_length = length;
        	  }
		  sprintf(data_type_lexeme,
			  "a(%s)",
			  aux_expr->data_type->data_type_lexeme);
		  type = (DTT_BUCKET *)search_lexeme_table(data_type_lexeme,
							   DTT);
		}
	        break;
	      case READ_OP:
		/* the check for expression array containing type errors is necessary to associate a */
		/* meaningful type with the expression */
		if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr2->data_type->data_type_lexeme[0] == 'a'))
		  if (expr2->data_type->data_type_lexeme[1] != EOS)
		  {
		    array_elem = (VALUE_CELL **)(expr2->struct_value->struct_value);
		    type = expr2->struct_value->value_bucket->info.expr->data_type;
		    if (type->data_type_lexeme[0] == 'a')
		      array_length = expr2->struct_value->value_bucket->info.expr->value;
		    struct_value =
		      duplicate_struct_expr(expr2->struct_value->value_bucket->info.expr->struct_value,
					    type->data_type_lexeme[0]);
		  }
		  else
		  {
		    type = NULL;
		    read_op_anomaly = TRUE;
		  }
	        else
		  type = NULL;
	        break;
	      case WRITE_OP:
		if ((expr1->data_type->data_type_lexeme[0] == 'i') &&
		    (expr3->data_type->data_type_lexeme[0] == 'a') &&
		    ((expr3->data_type->data_type_lexeme[1] == EOS) ||
		     check_expr_types(expr2,
				      expr3->struct_value->value_bucket->info.expr,
				      FALSE)))
		{
		  array_length = expr3->value;
		  if (strlen(expr3->struct_value->value_bucket->info.expr->data_type->data_type_lexeme) <
			strlen(expr2->data_type->data_type_lexeme))
		  {
		    length = 2 +
			     strlen(expr2->data_type->data_type_lexeme) +
			     1;
        	    if (length > max_length)
        	    {
          	      if (data_type_lexeme != NULL)
            	        free(data_type_lexeme);
          	      data_type_lexeme = alloc_string(length);
          	      max_length = length;
        	    }
		    sprintf(data_type_lexeme,
			    "a(%s)",
			    expr2->data_type->data_type_lexeme);
		    type = (DTT_BUCKET *)search_lexeme_table(data_type_lexeme,
							     DTT);
                    struct_value = alloc_value_cell(opn2,
				       		    0.0L,
				       		    NULL,
				       		    NULL);
		  }
		  else
		  {
		    type = expr3->data_type;
		    struct_value = duplicate_struct_expr(expr3->struct_value,
							 type->data_type_lexeme[0]);
		  }
		}
	        else
		  type = NULL;
	        break;
	      case RECORD_CONS_OP:
		/* the type is set to p in the case of an expression record containing type errors in */
	        /* order to distinguish it from an expression list/array */
		for (record_field = struct_value,
		     field_length = 0,
		     null_typed_expr = FALSE;
		     ((record_field != NULL) &&
		      (record_field->value_bucket->info.expr->data_type != NULL));
		     record_field = record_field->next_value_cell)
              	  field_length +=
		    strlen(record_field->value_bucket->info.expr->data_type->data_type_lexeme) +
                    ((record_field->next_value_cell != NULL)?
                       1:
                       0);
		if (record_field != NULL)
		  type = (DTT_BUCKET *)search_lexeme_table("p",
							   DTT);
		else
		{
                  length = 2 +
                           field_length +
                           1;
        	  if (length > max_length)
        	  {
          	    if (data_type_lexeme != NULL)
            	      free(data_type_lexeme);
          	    data_type_lexeme = alloc_string(length);
          	    max_length = length;
        	  }
                  strcpy(data_type_lexeme,
                         "p(");
                  for (record_field = struct_value;
                       (record_field != NULL);
                       record_field = record_field->next_value_cell)
                  {
                    strcat(data_type_lexeme,
                           record_field->value_bucket->info.expr->data_type->data_type_lexeme);
                    if (record_field->next_value_cell != NULL)
                      strcat(data_type_lexeme,
                             ",");
                  }
                  strcat(data_type_lexeme,
                         ")");
		  type = (DTT_BUCKET *)search_lexeme_table(data_type_lexeme,
							   DTT);
		}
	        break;
	      case GET_OP:
		/* the check for expression record containing type errors is necessary to associate a */
		/* meaningful type with the expression */
		if (expr2->data_type->data_type_lexeme[0] == 'p')
		  if (expr2->data_type->data_type_lexeme[1] != EOS)
		  {
		    for (record_field = expr2->struct_value;
			 ((record_field != NULL) &&
			  strcmp(strrchr(record_field->value_bucket->symbol_lexeme,
					 ';') + 1,
				 opn1->symbol_lexeme));
			 record_field = record_field->next_value_cell);
		    if (record_field != NULL)
		    {
		      record_field->value_bucket->used = TRUE;
		      type = record_field->value_bucket->info.expr->data_type;
		      if (type->data_type_lexeme[0] == 'a')
			array_length = record_field->value_bucket->info.expr->value;
		      struct_value =
			duplicate_struct_expr(record_field->value_bucket->info.expr->struct_value,
					      type->data_type_lexeme[0]);
		    }
		    else
		    {
		      signal_error(FIELD_NOT_IN_REC,
				   NULL,
				   NULL);
		      type = NULL;
		      get_op_anomaly = TRUE;
		    }
		  }
		  else
		  {
		    type = NULL;
		    get_op_anomaly = TRUE;
		  }
	        else
		  type = NULL;
	        break;
	      case PUT_OP:
		if ((expr3->data_type->data_type_lexeme[0] == 'p') &&
		    check_expr_types(expr1,
				     expr2,
				     FALSE))
		  if (expr3->data_type->data_type_lexeme[1] != EOS)
		  {
		    for (record_field = expr3->struct_value;
			 ((record_field != NULL) &&
			  strcmp(strrchr(record_field->value_bucket->symbol_lexeme,
					 ';') + 1,
				 opn1->symbol_lexeme));
			 record_field = record_field->next_value_cell);
		    if (record_field != NULL)
		    {
		      record_field->value_bucket->used = TRUE;
		      type = expr3->data_type;
		      struct_value = duplicate_struct_expr(expr3->struct_value,
							   type->data_type_lexeme[0]);
		    }
		    else
		    {
		      signal_error(FIELD_NOT_IN_REC,
				   NULL,
				   NULL);
		      type = NULL;
		      put_op_anomaly = TRUE;
		    }
		  }
		  else
		  {
		    type = expr3->data_type;
		    struct_value = duplicate_struct_expr(expr3->struct_value,
							 type->data_type_lexeme[0]);
		  }
	        else
		  type = NULL;
	        break;
	      case PROP_AND_OP:
	      case PROP_OR_OP:
	      case PROP_XOR_OP:
	      case PROP_IMPL_OP:
	      case PROP_BI_IMPL_OP:
	      case MODAL_MIN_FIXPOINT_OP:
	      case MODAL_MAX_FIXPOINT_OP:
	        if (((opn1->symbol_index == PROPERTY_ID) ||
		     (opn1->symbol_index == PROPERTY_VAR_ID) ||
		     (expr1->data_type->data_type_lexeme[0] == 'b')) &&
	            ((opn2->symbol_index == PROPERTY_ID) ||
		     (opn2->symbol_index == PROPERTY_VAR_ID) ||
		     (expr2->data_type->data_type_lexeme[0] == 'b')))
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case MODAL_CAN_OP:
	      case MODAL_WEAK_CAN_OP:
	      case MODAL_MUST_OP:
	      case MODAL_WEAK_MUST_OP:
	      case MODAL_CAN_PROB_OP:
	      case MODAL_WEAK_CAN_PROB_OP:
	      case MODAL_MUST_PROB_OP:
	      case MODAL_WEAK_MUST_PROB_OP:
	        if ((opn1->symbol_index == PROPERTY_ID) ||
		    (opn1->symbol_index == PROPERTY_VAR_ID) ||
		    (expr1->data_type->data_type_lexeme[0] == 'b'))
		  type = (DTT_BUCKET *)search_lexeme_table("b",
							   DTT);
	        else
		  type = NULL;
	        break;
	      case SAMPLE_OP:
		type = expr3->data_type;
	        break;
	      default:
		type = NULL;
	        break;
	    }
	    if ((type == NULL) &&
		!first_op_anomaly &&
		!read_op_anomaly &&
		!get_op_anomaly &&
		!put_op_anomaly)
	      signal_error(ILL_TYPED_EXPR,
			   NULL,
			   NULL);
	  }
	  expr->symbol_index = EXPR_STRING;
	  expr->info.expr = alloc_expr(op_index,
			               opn1,
			               opn2,
			               opn3,
			               type,
			               cumulative,
			               array_length,
			               struct_value);
	}
	return(expr);
}


ST_BUCKET	*set_concrete_expr_bucket(ST_BUCKET *symbolic_expr)
{
	EXPR_OP_INDEX	concrete_op_index;
	ST_BUCKET	*concrete_expr;
	VALUE_CELL	*concrete_struct_value;

	if ((symbolic_expr->info.expr->op_index == (char)NUMBER_OP) ||
	    (symbolic_expr->info.expr->op_index == (char)TRUE_OP) ||
	    (symbolic_expr->info.expr->op_index == (char)FALSE_OP))
	  concrete_expr = symbolic_expr;
	else
	{
	  concrete_expr = (ST_BUCKET *)search_lexeme_table(build_concrete_expr_lexeme(symbolic_expr),
							   SYT);
	  if ((concrete_expr != symbolic_expr) &&
	      (concrete_expr->symbol_index != NUMBER) &&
	      (concrete_expr->symbol_index != K_TRUE) &&
	      (concrete_expr->symbol_index != K_FALSE))
	  {
	    switch (symbolic_expr->info.expr->data_type->data_type_lexeme[0])
	    {
	      case 'i':
	      case 'P':
	      case 'r':
	      case 'R':
	      case 'W':
		concrete_expr->symbol_index = NUMBER;
		concrete_op_index = NUMBER_OP;
		break;
	      case 'b':
		concrete_expr->symbol_index = (symbolic_expr->info.expr->value)?
						K_TRUE:
						K_FALSE;
		concrete_op_index = (symbolic_expr->info.expr->value)?
				      TRUE_OP:
				      FALSE_OP;
		break;
	      case 'a':
	        concrete_expr->symbol_index = EXPR_STRING;
		concrete_op_index = ARRAY_CONS_OP;
		break;
	      case 'p':
	        concrete_expr->symbol_index = EXPR_STRING;
		concrete_op_index = RECORD_CONS_OP;
		break;
	      default:
	        concrete_expr->symbol_index = symbolic_expr->symbol_index;
		concrete_op_index = (EXPR_OP_INDEX)(symbolic_expr->info.expr->op_index);
		break;
	    }
	    if (symbolic_expr->info.expr->struct_value != NULL)
	      copy_value(&(concrete_struct_value),
		         (symbolic_expr->info.expr->data_type->data_type_lexeme[0] == 'a')?
		           symbolic_expr->info.expr->struct_value->struct_value:
		           symbolic_expr->info.expr->struct_value,
		         FALSE,
		         symbolic_expr->info.expr,
		         1,
		         (symbolic_expr->info.expr->data_type->data_type_lexeme[0] == 'a')?
		           2:
		           1,
		         NULL);
	    else
	      concrete_struct_value = NULL;
	    concrete_expr->info.expr = alloc_expr(concrete_op_index,
					          symbolic_expr->info.expr->opn1,
					          symbolic_expr->info.expr->opn2,
					          symbolic_expr->info.expr->opn3,
					          symbolic_expr->info.expr->data_type,
					          (BOOLEAN)(symbolic_expr->info.expr->illegal_use_cumul),
					          symbolic_expr->info.expr->value,
					          concrete_struct_value);
	  }
	}
	return(concrete_expr);
}


ST_BUCKET	*set_concrete_uneval_expr_bucket(ST_BUCKET       *symbolic_expr,
						 ST_BUCKET4_CELL *act_type_list,
						 ST_BUCKET4_CELL *par_list,
						 ST_BUCKET       *sim_measure_id)
{
	BOOLEAN		correct,
			modified;
	EXPR		*expr;
	ST_BUCKET	*concrete_uneval_expr,
			*concrete_aei,
			*concrete_index_expr,
			*concrete_uneval_expr1,
			*concrete_uneval_expr2,
			*concrete_uneval_expr3,
			*concrete_uneval_value_bucket,
			*concrete_act_type,
			*concrete_reward_expr;
	ST_BUCKET4_CELL	*act_type_cell,
			*par_cell;
	VALUE_CELL	*concrete_uneval_value_list,
			*last_concrete_uneval_value_cell,
			*list_elem,
			**array_elem,
			*record_field;
	int		array_length,
			array_index;

	if (!strcmp(symbolic_expr->symbol_lexeme,
		    "_action_type") ||
	    !strcmp(symbolic_expr->symbol_lexeme,
		    "_STUTTER"))
	  concrete_uneval_expr = symbolic_expr;
	else
	  if (symbolic_expr->symbol_index == ACT_TYPE_ID)
	  {
	    /* the symbolic expression is an action type occurring in the modal/temporal logic expression */
	    /* for a property */
	    act_type_cell = get_list4_cell(symbolic_expr,
				           act_type_list,
				           FALSE);
	    if (act_type_cell == NULL)
	      concrete_uneval_expr = symbolic_expr;
	    else
	    {
	      concrete_aei = act_type_cell->st_bucket2;
	      eval_expr(act_type_cell->st_bucket3,
		        0);
	      concrete_index_expr = set_concrete_expr_bucket(act_type_cell->st_bucket3);
	      build_indexed_id(&concrete_aei,
			       concrete_index_expr);
	      concrete_uneval_expr = act_type_cell->st_bucket4;
	      build_prefixed_id(concrete_aei,
			        &concrete_uneval_expr);
	    }
	    check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		     &concrete_uneval_expr,
		     TRUE);
	  }
	  else
	  {
	    /* the symbolic expression is not an action type */
	    expr = symbolic_expr->info.expr;
	    switch (expr->op_index)
	    {
	      case VAR_OP:
	        par_cell = get_list4_cell(symbolic_expr,
				          par_list,
				          FALSE);
	        if (par_cell == NULL)
	        {
	          if (symbolic_expr->symbol_index == VAR_ID)
	            /* since the parameter actually is a selector whose value changes during the */
	            /* expansion, it must be made concrete */
	            concrete_uneval_expr = set_concrete_expr_bucket(symbolic_expr);
	          else
	          {
	            /* since the parameter may have erroneously occurred without any index, it must be */
	            /* checked anyway */
	            concrete_uneval_expr = symbolic_expr;
	            check_id(VAR_ID_USE_AUX_SPEC,
		             &concrete_uneval_expr,
		             TRUE);
	          }
	        }
	        else
	        {
	          concrete_aei = par_cell->st_bucket2;
	          eval_expr(par_cell->st_bucket3,
			    0);
	          concrete_index_expr = set_concrete_expr_bucket(par_cell->st_bucket3);
	          build_indexed_id(&concrete_aei,
			           concrete_index_expr);
	          concrete_uneval_expr = par_cell->st_bucket4;
	          build_prefixed_id(concrete_aei,
			            &concrete_uneval_expr);
	          check_id(VAR_ID_USE_AUX_SPEC,
		           &concrete_uneval_expr,
		           TRUE);
	          }
	        break;
	      case NUMBER_OP:
	      case TRUE_OP:
	      case FALSE_OP:
	      case PROP_TRUE_OP:
	      case PROP_FALSE_OP:
	        concrete_uneval_expr = symbolic_expr;
	        break;
	      case PLUS_OP:
	      case MINUS_OP:
	      case TIMES_OP:
	      case DIV_OP:
              case MOD_OP:
	      case MIN_OP:
	      case MAX_OP:
              case POWER_OP:
	      case C_UNIFORM_OP:
	      case ERLANG_OP:
	      case GAMMA_OP:
	      case WEIBULL_OP:
	      case BETA_OP:
	      case NORMAL_OP:
	      case D_UNIFORM_OP:
	      case BINOMIAL_OP:
	      case NEG_BINOMIAL_OP:
	      case PASCAL_OP:
	      case AND_OP:
	      case OR_OP:
	      case EQ_OP:
	      case NE_OP:
	      case LT_OP:
	      case LE_OP:
	      case GT_OP:
	      case GE_OP:
	      case CONCAT_OP:
	      case INSERT_OP:
	      case REMOVE_OP:
              case READ_OP:
	      case PUT_OP:
	      case PROP_AND_OP:
	      case PROP_OR_OP:
	      case PROP_XOR_OP:
	      case PROP_IMPL_OP:
	      case PROP_BI_IMPL_OP:
	      case MODAL_MIN_FIXPOINT_OP:
	      case MODAL_MAX_FIXPOINT_OP:
	      case CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP:
	      case CTL_FOR_ALL_PATHS_WEAK_UNTIL_OP:
	      case CTL_EXISTS_PATH_STRONG_UNTIL_OP:
	      case CTL_EXISTS_PATH_WEAK_UNTIL_OP:
	      case LTL_UNTIL_OP:
	      case LTL_RELEASES_OP:
	      case LTL_SINCE_OP:
	      case LTL_TRIGGERED_OP:
	        concrete_uneval_expr1 = set_concrete_uneval_expr_bucket((expr->op_index != PUT_OP)?
								          expr->opn1:
								          expr->opn2,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr2 = set_concrete_uneval_expr_bucket((expr->op_index != PUT_OP)?
								          expr->opn2:
								          expr->opn3,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr = ((concrete_uneval_expr1 == NULL) ||
				        (concrete_uneval_expr2 == NULL))?
				         NULL:
	    			         (((concrete_uneval_expr1 == ((expr->op_index != PUT_OP)?
								        expr->opn1:
								        expr->opn2)) &&
				           (concrete_uneval_expr2 == ((expr->op_index != PUT_OP)?
							                expr->opn2:
								        expr->opn3)))?
				            symbolic_expr:
				            set_expr_bucket(expr->op_index,
						            (expr->op_index != PUT_OP)?
						              concrete_uneval_expr1:
						              expr->opn1,
						            (expr->op_index != PUT_OP)?
						              concrete_uneval_expr2:
						              concrete_uneval_expr1,
						            (expr->op_index != PUT_OP)?
						              expr->opn3:
						              concrete_uneval_expr2,
						            expr->value,
						            expr->struct_value,
						            (char)(expr->illegal_use_cumul)));
	        break;
	      case ABS_OP:
	      case CEIL_OP:
	      case FLOOR_OP:
              case EPOWER_OP:
              case LOGE_OP:
              case LOG10_OP:
              case SQRT_OP:
              case SIN_OP:
              case COS_OP:
	      case EXPONENTIAL_OP:
	      case PARETO_OP:
	      case POISSON_OP:
	      case GEOMETRIC_OP:
	      case NOT_OP:
	      case FIRST_OP:
	      case TAIL_OP:
	      case LENGTH_OP:
	      case GET_OP:
	      case PROP_NOT_OP:
	      case MODAL_CAN_OP:
	      case MODAL_WEAK_CAN_OP:
	      case MODAL_MUST_OP:
	      case MODAL_WEAK_MUST_OP:
	      case MODAL_CAN_PROB_OP:
	      case MODAL_WEAK_CAN_PROB_OP:
	      case MODAL_MUST_PROB_OP:
	      case MODAL_WEAK_MUST_PROB_OP:
	      case CTL_FOR_ALL_PATHS_ALL_STATES_SAT_OP:
	      case CTL_FOR_ALL_PATHS_SOME_STATE_SAT_OP:
	      case CTL_EXISTS_PATH_ALL_STATES_SAT_OP:
	      case CTL_EXISTS_PATH_SOME_STATE_SAT_OP:
	      case LTL_NEXT_STATE_SAT_OP:
	      case LTL_ALL_FUTURE_STATES_SAT_OP:
	      case LTL_SOME_FUTURE_STATE_SAT_OP:
	      case LTL_PREV_STATE_SAT_OP:
	      case LTL_ALL_PAST_STATES_SAT_OP:
	      case LTL_SOME_PAST_STATE_SAT_OP:
	        concrete_uneval_expr1 = set_concrete_uneval_expr_bucket((expr->op_index != GET_OP)?
								          expr->opn1:
								          expr->opn2,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr = (concrete_uneval_expr1 == NULL)?
				         NULL:
				         ((concrete_uneval_expr1 == ((expr->op_index != GET_OP)?
							               expr->opn1:
							               expr->opn2))?
				            symbolic_expr:
				            set_expr_bucket(expr->op_index,
						            (expr->op_index != GET_OP)?
						              concrete_uneval_expr1:
						              expr->opn1,
						            (expr->op_index != GET_OP)?
						              expr->opn2:
						              concrete_uneval_expr1,
						            expr->opn3,
						            expr->value,
						            expr->struct_value,
						            (char)(expr->illegal_use_cumul)));
	        break;
	      case B_PARETO_OP:
	      case BERNOULLI_OP:
              case WRITE_OP:
	        concrete_uneval_expr1 = set_concrete_uneval_expr_bucket(expr->opn1,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr2 = set_concrete_uneval_expr_bucket(expr->opn2,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr3 = set_concrete_uneval_expr_bucket(expr->opn3,
								        act_type_list,
								        par_list,
								        sim_measure_id);
	        concrete_uneval_expr = ((concrete_uneval_expr1 == NULL) ||
				        (concrete_uneval_expr2 == NULL) ||
				        (concrete_uneval_expr3 == NULL))?
				         NULL:
				         (((concrete_uneval_expr1 == expr->opn1) &&
				           (concrete_uneval_expr2 == expr->opn2) &&
				           (concrete_uneval_expr3 == expr->opn3))?
				            symbolic_expr:
				            set_expr_bucket(expr->op_index,
						            concrete_uneval_expr1,
						            concrete_uneval_expr2,
						            concrete_uneval_expr3,
						            expr->value,
						            expr->struct_value,
						            (char)(expr->illegal_use_cumul)));
	        break;
	      case LIST_CONS_OP:
	        for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			           expr->struct_value:
			           NULL,
		     concrete_uneval_value_list = last_concrete_uneval_value_cell = NULL,
		     correct = TRUE,
		     modified = FALSE;
	             ((list_elem != NULL) &&
		      correct);
	             list_elem = list_elem->next_value_cell)
	        {
	          concrete_uneval_value_bucket = set_concrete_uneval_expr_bucket(list_elem->value_bucket,
									         act_type_list,
									         par_list,
									         sim_measure_id);
	          if (concrete_uneval_value_bucket == NULL)
		    correct = FALSE;
	          else
	          {
	            if (concrete_uneval_value_list == NULL)
		      last_concrete_uneval_value_cell = concrete_uneval_value_list =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            else
		      last_concrete_uneval_value_cell = last_concrete_uneval_value_cell->next_value_cell =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            modified = (concrete_uneval_value_bucket != list_elem->value_bucket);
	          }
	        }
	        if (!correct)
	        {
	          concrete_uneval_expr = NULL;
	          free_value_list(concrete_uneval_value_list,
			          expr,
			          1);
	        }
	        else
	          if (!modified)
	          {
	            concrete_uneval_expr = symbolic_expr;
	            free_value_list(concrete_uneval_value_list,
			            expr,
			            1);
	          }
	          else
	            concrete_uneval_expr = set_expr_bucket(expr->op_index,
						           expr->opn1,
						           expr->opn2,
						           expr->opn3,
						           expr->value,
						           concrete_uneval_value_list,
						           (char)(expr->illegal_use_cumul));
	        break;
	      case ARRAY_CONS_OP:
	        for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
	             array_index = 0,
		     array_length = (int)(expr->value),
		     concrete_uneval_value_list = last_concrete_uneval_value_cell = NULL,
		     correct = TRUE,
		     modified = FALSE;
	             ((array_index < array_length) &&
		      correct);
	             array_elem++,
	             array_index++)
	        {
	          concrete_uneval_value_bucket = set_concrete_uneval_expr_bucket((*array_elem)->value_bucket,
									         act_type_list,
									         par_list,
									         sim_measure_id);
	          if (concrete_uneval_value_bucket == NULL)
		    correct = FALSE;
	          else
	          {
	            if (concrete_uneval_value_list == NULL)
		      last_concrete_uneval_value_cell = concrete_uneval_value_list =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            else
		      last_concrete_uneval_value_cell = last_concrete_uneval_value_cell->next_value_cell =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            modified = (concrete_uneval_value_bucket != (*array_elem)->value_bucket);
	          }
	        }
	        if (!correct)
	        {
	          concrete_uneval_expr = NULL;
	          free_value_list(concrete_uneval_value_list,
			          expr,
			          2);
	        }
	        else
	          if (!modified)
	          {
	            concrete_uneval_expr = symbolic_expr;
	            free_value_list(concrete_uneval_value_list,
			            expr,
			            2);
	          }
	          else
	            concrete_uneval_expr =
		      set_expr_bucket(expr->op_index,
				      expr->opn1,
				      expr->opn2,
				      expr->opn3,
				      expr->value,
				      transform_list_into_array(concrete_uneval_value_list,
							        expr->value),
				      (char)(expr->illegal_use_cumul));
	        break;
	      case RECORD_CONS_OP:
	        for (record_field = expr->struct_value,
		     concrete_uneval_value_list = last_concrete_uneval_value_cell = NULL,
		     correct = TRUE,
		     modified = FALSE;
	             ((record_field != NULL) &&
		      correct);
	             record_field = record_field->next_value_cell)
	        {
	          concrete_uneval_value_bucket = set_concrete_uneval_expr_bucket(record_field->value_bucket,
									         act_type_list,
									         par_list,
									         sim_measure_id);
	          if (concrete_uneval_value_bucket == NULL)
		    correct = FALSE;
	          else
	          {
	            if (concrete_uneval_value_list == NULL)
		      last_concrete_uneval_value_cell = concrete_uneval_value_list =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            else
		      last_concrete_uneval_value_cell = last_concrete_uneval_value_cell->next_value_cell =
		        alloc_value_cell(concrete_uneval_value_bucket,
				         0.0L,
				         NULL,
				         NULL);
	            modified = (concrete_uneval_value_bucket != record_field->value_bucket);
	          }
	        }
	        if (!correct)
	        {
	          concrete_uneval_expr = NULL;
	          free_value_list(concrete_uneval_value_list,
			          expr,
			          1);
	        }
	        else
	          if (!modified)
	          {
	            concrete_uneval_expr = symbolic_expr;
	            free_value_list(concrete_uneval_value_list,
			            expr,
			            1);
	          }
	          else
	            concrete_uneval_expr = set_expr_bucket(expr->op_index,
						           expr->opn1,
						           expr->opn2,
						           expr->opn3,
						           expr->value,
						           concrete_uneval_value_list,
						           (char)(expr->illegal_use_cumul));
                break;
	      case SAMPLE_OP:
	        concrete_reward_expr = set_concrete_uneval_expr_bucket(symbolic_expr->info.expr->opn3,
								       act_type_list,
								       par_list,
								       sim_measure_id);
	        act_type_cell = get_list4_cell(symbolic_expr->info.expr->opn2,
				               act_type_list,
				               FALSE);
	        if (act_type_cell == NULL)
	        {
	          /* the action type must be checked anyway, as it may have erroneously occurred without */
	          /* any index */
	          concrete_act_type = symbolic_expr->info.expr->opn2;
	          check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		           &concrete_act_type,
		           TRUE);
	          concrete_uneval_expr =
		    ((concrete_act_type == NULL) ||
		     (concrete_reward_expr == NULL))?
		      NULL:
		      set_sample_bucket(sim_measure_id,
				        concrete_act_type,
				        concrete_reward_expr,
				        (BOOLEAN)(symbolic_expr->info.expr->illegal_use_cumul));
	        }
	        else
	        {
	          concrete_aei = act_type_cell->st_bucket2;
	          eval_expr(act_type_cell->st_bucket3,
			    0);
	          concrete_index_expr = set_concrete_expr_bucket(act_type_cell->st_bucket3);
	          build_indexed_id(&concrete_aei,
			           concrete_index_expr);
	          concrete_act_type = act_type_cell->st_bucket4;
	          build_prefixed_id(concrete_aei,
			            &concrete_act_type);
	          check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		           &concrete_act_type,
		           TRUE);
	          concrete_uneval_expr =
		    ((concrete_act_type == NULL) ||
		     !check_rew_act_type(concrete_act_type,
				         TRUE) ||
		     (concrete_reward_expr == NULL))?
		      NULL:
		      set_sample_bucket(sim_measure_id,
				        concrete_act_type,
				        concrete_reward_expr,
				        (BOOLEAN)(symbolic_expr->info.expr->illegal_use_cumul));
	        }
                break;
	      default:
                break;
	    }
	  }
	return(concrete_uneval_expr);
}


ST_BUCKET	*set_nusmv_expr_bucket(ST_BUCKET *property_expr)
{
	EXPR		*expr;
	ST_BUCKET	*nusmv_expr,
			*act_type;
	ST_BUCKET_CELL	*dupl_act_type_cell;

	expr = property_expr->info.expr;
	switch (expr->op_index)
	{
	  case NE_OP:
	  case PROP_TRUE_OP:
	  case PROP_FALSE_OP:
	    nusmv_expr = property_expr;
	    break;
	  case EQ_OP:
	    act_type = expr->opn2;
	    if ((act_type->info.act_type->interaction_index == (char)NO_INTERACTION) ||
		act_type->info.act_type->architectural)
	      nusmv_expr =
		set_expr_bucket(EQ_OP,
				(ST_BUCKET *)search_lexeme_table("_action_type",
                                                                 SYT),
				(ST_BUCKET *)search_lexeme_table(
					       build_nusmv_act_type_lexeme(act_type->symbol_lexeme),
					       SYT),
				NULL,
				0.0L,
				NULL,
				FALSE);
	    else
	      if ((act_type->info.act_type->interaction_index != (char)INPUT_OR) &&
	          (act_type->info.act_type->interaction_index != (char)OUTPUT_OR))
	      {
		act_type = act_type->info.act_type->renamed;
	        nusmv_expr =
		  set_expr_bucket(EQ_OP,
				  (ST_BUCKET *)search_lexeme_table("_action_type",
                                                                   SYT),
				  (ST_BUCKET *)search_lexeme_table(
						 build_nusmv_act_type_lexeme(act_type->symbol_lexeme),
						 SYT),
				  NULL,
				  0.0L,
				  NULL,
				  FALSE);
	      }
	      else
	      {
		dupl_act_type_cell = act_type->info.act_type->duplicate_list;
		act_type = dupl_act_type_cell->st_bucket->info.act_type->renamed;
	        nusmv_expr =
		  set_expr_bucket(EQ_OP,
				  (ST_BUCKET *)search_lexeme_table("_action_type",
                                                                   SYT),
				  (ST_BUCKET *)search_lexeme_table(
						 build_nusmv_act_type_lexeme(act_type->symbol_lexeme),
						 SYT),
				  NULL,
				  0.0L,
				  NULL,
				  FALSE);
		for (dupl_act_type_cell = dupl_act_type_cell->next_st_bucket_cell;
		     (dupl_act_type_cell != NULL);
		     dupl_act_type_cell = dupl_act_type_cell->next_st_bucket_cell)
		{
		  act_type = dupl_act_type_cell->st_bucket->info.act_type->renamed;
	          nusmv_expr =
		    set_expr_bucket(PROP_OR_OP,
				    nusmv_expr,
				    set_expr_bucket(EQ_OP,
					            (ST_BUCKET *)search_lexeme_table("_action_type",
                                                                                     SYT),
					            (ST_BUCKET *)search_lexeme_table(
								   build_nusmv_act_type_lexeme(
								     act_type->symbol_lexeme),
								   SYT),
					            NULL,
					            0.0L,
					            NULL,
					            FALSE),
				    NULL,
				    0.0L,
				    NULL,
				    FALSE);
		}
	      }
	    break;
	  case PROP_AND_OP:
	  case PROP_OR_OP:
	  case PROP_XOR_OP:
	  case PROP_IMPL_OP:
	  case PROP_BI_IMPL_OP:
	  case LTL_UNTIL_OP:
	  case LTL_RELEASES_OP:
	  case LTL_SINCE_OP:
	  case LTL_TRIGGERED_OP:
	    nusmv_expr = set_expr_bucket(expr->op_index,
					 set_nusmv_expr_bucket(expr->opn1),
					 set_nusmv_expr_bucket(expr->opn2),
					 NULL,
					 0.0L,
					 NULL,
					 FALSE);
	    break;
	  case PROP_NOT_OP:
	  case LTL_NEXT_STATE_SAT_OP:
	  case LTL_ALL_FUTURE_STATES_SAT_OP:
	  case LTL_SOME_FUTURE_STATE_SAT_OP:
	  case LTL_PREV_STATE_SAT_OP:
	  case LTL_ALL_PAST_STATES_SAT_OP:
	  case LTL_SOME_PAST_STATE_SAT_OP:
	    nusmv_expr = set_expr_bucket(expr->op_index,
					 set_nusmv_expr_bucket(expr->opn1),
					 NULL,
					 NULL,
					 0.0L,
					 NULL,
					 FALSE);
	    break;
	  default:
	    nusmv_expr = NULL;
	    break;
	}
	return(nusmv_expr);
}


ST_BUCKET	*get_expr_bucket(EXPR_OP_INDEX op_index,
				 ST_BUCKET     *opn1,
				 ST_BUCKET     *opn2,
				 ST_BUCKET     *opn3,
				 long double   array_length,
				 VALUE_CELL    *struct_value,
				 BOOLEAN       cumulative)
{
	return((ST_BUCKET *)search_lexeme_table(build_expr_lexeme(op_index,
								  opn1,
								  opn2,
								  opn3,
								  array_length,
								  struct_value,
								  cumulative),
						SYT));
}


ST_BUCKET_CELL	*rewrite_aet_formal_const_par_list(ST_BUCKET_CELL *aet_formal_const_par_list,
						   ST_BUCKET      *aei)
{
	ST_BUCKET	*aei_formal_const_par;
	ST_BUCKET_CELL	*aei_formal_const_par_list;

	if (aet_formal_const_par_list == NULL)
	  aei_formal_const_par_list = NULL;
	else
	{
	  /* rewrite the formal constant parameter list in a posticipated way to preserve the parameter */
	  /* order */
	  aei_formal_const_par_list = rewrite_aet_formal_const_par_list(aet_formal_const_par_list->
									  next_st_bucket_cell,
									aei);
	  aei_formal_const_par =
	    (ST_BUCKET *)search_lexeme_table(strrchr(aet_formal_const_par_list->st_bucket->symbol_lexeme,
					             '.') + 1,
				             SYT);
	  build_prefixed_id(aei,
			    &aei_formal_const_par);
	  aei_formal_const_par->symbol_index = aet_formal_const_par_list->st_bucket->symbol_index;
	  aei_formal_const_par->defined = (char)TRUE;
	  aei_formal_const_par->used = (char)TRUE;
	  aei_formal_const_par->info.expr =
	    alloc_expr(aet_formal_const_par_list->st_bucket->info.expr->op_index,
		       (aet_formal_const_par_list->st_bucket->info.expr->opn1 != NULL)?
			 rewrite_expr_bucket(aet_formal_const_par_list->st_bucket->info.expr->opn1,
				             aei):
			 NULL,
		       (aet_formal_const_par_list->st_bucket->info.expr->opn2 != NULL)?
			 rewrite_expr_bucket(aet_formal_const_par_list->st_bucket->info.expr->opn2,
				             aei):
			 NULL,
		       aet_formal_const_par_list->st_bucket->info.expr->opn3,
		       aet_formal_const_par_list->st_bucket->info.expr->data_type,
		       aet_formal_const_par_list->st_bucket->info.expr->illegal_use_cumul,
		       aet_formal_const_par_list->st_bucket->info.expr->value,
		       duplicate_struct_expr(aet_formal_const_par_list->st_bucket->info.expr->struct_value,
					     aet_formal_const_par_list->st_bucket->info.expr->data_type->
					       data_type_lexeme[0]));
	  rewrite_var_lengths_bounds(aei_formal_const_par->info.expr,
				     aei);
	  aei_formal_const_par_list = alloc_st_bucket_cell(aei_formal_const_par,
							   aei_formal_const_par_list);
	}
	return(aei_formal_const_par_list);
}


ST_BUCKET_CELL	*rewrite_aet_behavior_list(ST_BUCKET_CELL *aet_behavior_list,
				           ST_BUCKET      *aei)
{
	ST_BUCKET	*aei_behavior;
	ST_BUCKET_CELL	*aei_behavior_list;

	if (aet_behavior_list == NULL)
	  aei_behavior_list = NULL;
	else
	{
	  /* rewrite the AET behavior list in a posticipated way to preserve the behavior order */
	  aei_behavior_list = rewrite_aet_behavior_list(aet_behavior_list->next_st_bucket_cell,
						        aei);
	  aei_behavior =
	    (ST_BUCKET *)search_lexeme_table(strrchr(aet_behavior_list->st_bucket->symbol_lexeme,
						     '.') + 1,
					     SYT);
	  build_prefixed_id(aei,
			    &aei_behavior);
	  aei_behavior->symbol_index = aet_behavior_list->st_bucket->symbol_index;
	  aei_behavior->defined = (char)TRUE;
	  aei_behavior->used = (char)TRUE;
	  aei_behavior->info.behavior =
	    alloc_behavior(rewrite_aet_behavior_par_list(aet_behavior_list->st_bucket->info.behavior->
							   formal_var_par_list,
							 aei_behavior,
							 aei),
			   rewrite_expr_bucket_list(aet_behavior_list->st_bucket->info.behavior->
					              actual_var_par_list,
					            aei),
			   rewrite_aet_behavior_par_list(aet_behavior_list->st_bucket->info.behavior->
						           local_var_list,
						         aei_behavior,
						         aei),
			   aet_behavior_list->st_bucket->info.behavior->init_behav,
			   aet_behavior_list->st_bucket->info.behavior->state_lexeme);
	  aei_behavior_list = alloc_st_bucket_cell(aei_behavior,
						   aei_behavior_list);
	}
	return(aei_behavior_list);
}


ST_BUCKET_CELL	*rewrite_aet_behavior_par_list(ST_BUCKET_CELL *aet_behavior_par_list,
					       ST_BUCKET      *aei_behavior,
					       ST_BUCKET      *aei)
{
	ST_BUCKET	*aet_behavior_par,
			*aei_behavior_par;
	ST_BUCKET_CELL	*aei_behavior_par_list;

	if (aet_behavior_par_list == NULL)
	  aei_behavior_par_list = NULL;
	else
	{
	  /* rewrite the AET behavior parameter list in a posticipated way to preserve the parameter order */
	  aei_behavior_par_list = rewrite_aet_behavior_par_list(aet_behavior_par_list->next_st_bucket_cell,
								aei_behavior,
								aei);
	  aet_behavior_par = aet_behavior_par_list->st_bucket;
	  aei_behavior_par =
	    (ST_BUCKET *)search_lexeme_table(strrchr(aet_behavior_par->symbol_lexeme,
					             '.') + 1,
				             SYT);
	  build_prefixed_id(aei_behavior,
			    &aei_behavior_par);
	  aei_behavior_par->symbol_index = aet_behavior_par->symbol_index;
	  aei_behavior_par->defined = (char)TRUE;
	  aei_behavior_par->used = (char)TRUE;
	  aei_behavior_par->info.expr =
	    alloc_expr(aet_behavior_par->info.expr->op_index,
		       (aet_behavior_par->info.expr->opn1 != NULL)?
			 rewrite_expr_bucket(aet_behavior_par->info.expr->opn1,
				             aei):
			 NULL,
		       (aet_behavior_par->info.expr->opn2 != NULL)?
			 rewrite_expr_bucket(aet_behavior_par->info.expr->opn2,
				             aei):
			 NULL,
		       aet_behavior_par->info.expr->opn3,
		       aet_behavior_par->info.expr->data_type,
		       FALSE,
		       aet_behavior_par->info.expr->value,
		       duplicate_struct_expr(aet_behavior_par->info.expr->struct_value,
					     aet_behavior_par->info.expr->data_type->data_type_lexeme[0]));
	  rewrite_var_lengths_bounds(aei_behavior_par->info.expr,
				     aei);
	  aei_behavior_par_list = alloc_st_bucket_cell(aei_behavior_par,
						       aei_behavior_par_list);
	}
	return(aei_behavior_par_list);
}


ST_BUCKET_CELL	*rewrite_aet_act_type_list(ST_BUCKET_CELL *aet_act_type_list,
					   ST_BUCKET      *aei)
{
	ST_BUCKET	*aei_act_type;
	ST_BUCKET_CELL	*aei_act_type_list;

	if (aet_act_type_list == NULL)
	  aei_act_type_list = NULL;
	else
	{
	  /* rewrite the AET action type list in a posticipated way to preserve the action type order */
	  aei_act_type_list = rewrite_aet_act_type_list(aet_act_type_list->next_st_bucket_cell,
						        aei);
	  aei_act_type =
	    (ST_BUCKET *)search_lexeme_table(strrchr(aet_act_type_list->st_bucket->symbol_lexeme,
						     '.') + 1,
					     SYT);
	  build_prefixed_id(aei,
			    &aei_act_type);
	  aei_act_type->symbol_index = aet_act_type_list->st_bucket->symbol_index;
	  aei_act_type->defined = (char)TRUE;
	  aei_act_type->used = (char)TRUE;
	  if (aet_act_type_list->st_bucket->info.act_type != NULL)
	  {
	    aei_act_type->info.act_type =
	      alloc_act_type(aet_act_type_list->st_bucket->info.act_type->act_type_index,
			     rewrite_expr_bucket_list(aet_act_type_list->st_bucket->info.act_type->par_list,
					              aei),
			     aei);
	    aei_act_type->info.act_type->rate_index =
	      aet_act_type_list->st_bucket->info.act_type->rate_index;
	    aei_act_type->info.act_type->priority = aet_act_type_list->st_bucket->info.act_type->priority;
	    aei_act_type->info.act_type->interaction_index =
	      aet_act_type_list->st_bucket->info.act_type->interaction_index;
	  }
	  else
	    aei_act_type->info.act_type = NULL;
	  aei_act_type_list = alloc_st_bucket_cell(aei_act_type,
						   aei_act_type_list);
	}
	return(aei_act_type_list);
}


ST_BUCKET_CELL  *rewrite_expr_bucket_list(ST_BUCKET_CELL *aet_expr_list,
		                          ST_BUCKET      *aei)
{
	ST_BUCKET       *expr;
	ST_BUCKET_CELL  *aei_expr_list;

	if (aet_expr_list == NULL)
	  aei_expr_list = NULL;
	else
	{
	  /* rewrite the expression list in a posticipated way to preserve the expression order */
	  aei_expr_list = rewrite_expr_bucket_list(aet_expr_list->next_st_bucket_cell,
	                                           aei);
	  expr = rewrite_expr_bucket(aet_expr_list->st_bucket,
	                             aei);
	  aei_expr_list = alloc_st_bucket_cell(expr,
	                                       aei_expr_list);
	}
	return(aei_expr_list);
}


ST_BUCKET       *rewrite_expr_bucket(ST_BUCKET *aet_expr,
                                     ST_BUCKET *aei)
{
                ST_BUCKET       *aei_expr,
                                *aei_sub_expr1,
                                *aei_sub_expr2,
                                *aei_sub_expr3;
                VALUE_CELL      *list_elem,
                                *expr_list,
                                *last_list_elem,
                                **old_array_elem,
                                *expr_array,
                                **new_array_elem,
                                *record_field,
                                *expr_record,
                                *last_record_field;
        static  char            *variable_name  =       NULL;
                int             length,
                                array_length,
                                array_index;
        static  int             max_length      =       0;

        switch (aet_expr->info.expr->op_index)
        {
          case VAR_OP:
            length = strlen(aei->symbol_lexeme) +
                     strlen(strchr(aet_expr->symbol_lexeme,
				   '.'));
            if (length > max_length)
            {
              if (variable_name != NULL)
                free(variable_name);
              variable_name = alloc_string(length);
              max_length = length;
            }
            sprintf(variable_name,
                    "%s%s",
                    aei->symbol_lexeme,
                    strchr(aet_expr->symbol_lexeme,
			   '.'));
            aei_expr = (ST_BUCKET *)search_lexeme_table(variable_name,
                                                        SYT);
            if (aei_expr->symbol_index == ID)
            {
              aei_expr->symbol_index = aet_expr->symbol_index;
              aei_expr->defined = aet_expr->defined;
              aei_expr->used = aet_expr->used;
              aei_expr->info.expr =
		alloc_expr(aet_expr->info.expr->op_index,
                           aet_expr->info.expr->opn1,
                           aet_expr->info.expr->opn2,
                           aet_expr->info.expr->opn3,
                           aet_expr->info.expr->data_type,
                           aet_expr->info.expr->illegal_use_cumul,
                           aet_expr->info.expr->value,
                           duplicate_struct_expr(aet_expr->info.expr->struct_value,
                                                 aet_expr->info.expr->data_type->data_type_lexeme[0]));
            }
            break;
          case NUMBER_OP:
          case TRUE_OP:
          case FALSE_OP:
            aei_expr = aet_expr;
            break;
          case ASSIGN_OP:
          case PLUS_OP:
          case MINUS_OP:
          case TIMES_OP:
          case DIV_OP:
          case MOD_OP:
          case MIN_OP:
          case MAX_OP:
          case POWER_OP:
          case C_UNIFORM_OP:
          case ERLANG_OP:
          case GAMMA_OP:
          case WEIBULL_OP:
          case BETA_OP:
          case NORMAL_OP:
          case D_UNIFORM_OP:
          case BINOMIAL_OP:
          case NEG_BINOMIAL_OP:
          case PASCAL_OP:
          case AND_OP:
          case OR_OP:
          case EQ_OP:
          case NE_OP:
          case LT_OP:
          case LE_OP:
          case GT_OP:
          case GE_OP:
          case CONCAT_OP:
          case INSERT_OP:
          case REMOVE_OP:
          case READ_OP:
          case PUT_OP:
            aei_sub_expr1 = (aet_expr->info.expr->op_index != PUT_OP)?
                              rewrite_expr_bucket(aet_expr->info.expr->opn1,
                                                  aei):
                              aet_expr->info.expr->opn1;
            aei_sub_expr2 = rewrite_expr_bucket(aet_expr->info.expr->opn2,
                                                aei);
            aei_sub_expr3 = (aet_expr->info.expr->op_index != PUT_OP)?
                              NULL:
                              rewrite_expr_bucket(aet_expr->info.expr->opn3,
                                                  aei);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       aei_sub_expr1,
                                       aei_sub_expr2,
                                       aei_sub_expr3,
                                       aet_expr->info.expr->value,
                                       aet_expr->info.expr->struct_value,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          case ABS_OP:
          case CEIL_OP:
          case FLOOR_OP:
          case EPOWER_OP:
          case LOGE_OP:
          case LOG10_OP:
          case SQRT_OP:
          case SIN_OP:
          case COS_OP:
          case EXPONENTIAL_OP:
          case PARETO_OP:
          case POISSON_OP:
          case GEOMETRIC_OP:
          case NOT_OP:
          case FIRST_OP:
          case TAIL_OP:
          case LENGTH_OP:
          case GET_OP:
            aei_sub_expr1 = (aet_expr->info.expr->op_index != GET_OP)?
                              rewrite_expr_bucket(aet_expr->info.expr->opn1,
                                                  aei):
                              aet_expr->info.expr->opn1;
            aei_sub_expr2 = (aet_expr->info.expr->op_index != GET_OP)?
                              NULL:
                              rewrite_expr_bucket(aet_expr->info.expr->opn2,
                                                  aei);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       aei_sub_expr1,
                                       aei_sub_expr2,
                                       NULL,
                                       aet_expr->info.expr->value,
                                       aet_expr->info.expr->struct_value,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          case B_PARETO_OP:
          case BERNOULLI_OP:
          case WRITE_OP:
            aei_sub_expr1 = rewrite_expr_bucket(aet_expr->info.expr->opn1,
                                                aei);
            aei_sub_expr2 = rewrite_expr_bucket(aet_expr->info.expr->opn2,
                                                aei);
            aei_sub_expr3 = rewrite_expr_bucket(aet_expr->info.expr->opn3,
                                                aei);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       aei_sub_expr1,
                                       aei_sub_expr2,
                                       aei_sub_expr3,
                                       aet_expr->info.expr->value,
                                       aet_expr->info.expr->struct_value,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          case LIST_CONS_OP:
            for (list_elem = (aet_expr->info.expr->struct_value->value != (long double)DBL_MAX)?
                               aet_expr->info.expr->struct_value:
                               NULL,
                 expr_list = last_list_elem = NULL;
                 (list_elem != NULL);
                 list_elem = list_elem->next_value_cell)
              if (expr_list == NULL)
                expr_list = last_list_elem =
                  alloc_value_cell(rewrite_expr_bucket(list_elem->value_bucket,
                                                       aei),
                                   0.0L,
                                   NULL,
                                   NULL);
              else
                last_list_elem = last_list_elem->next_value_cell =
                  alloc_value_cell(rewrite_expr_bucket(list_elem->value_bucket,
                                                       aei),
                                   0.0L,
                                   NULL,
                                   NULL);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       NULL,
                                       NULL,
                                       NULL,
                                       0.0,
                                       (expr_list != NULL)?
                                         expr_list:
                                         aet_expr->info.expr->struct_value,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          case ARRAY_CONS_OP:
            expr_array =
	      alloc_value_cell(rewrite_expr_bucket(aet_expr->info.expr->struct_value->value_bucket,
                                                   aei),
                               aet_expr->info.expr->value,
                               (VALUE_CELL *)new_calloc((int)(aet_expr->info.expr->value),
                                                        sizeof(VALUE_CELL *)),
                               NULL);
            for (old_array_elem = (VALUE_CELL **)(aet_expr->info.expr->struct_value->struct_value),
                 new_array_elem = (VALUE_CELL **)(expr_array->struct_value),
                 array_length = (int)(aet_expr->info.expr->value),
                 array_index = 0;
                 (array_index < array_length);
                 old_array_elem++,
                 new_array_elem++,
                 array_index++)
              *new_array_elem = alloc_value_cell(rewrite_expr_bucket((*old_array_elem)->value_bucket,
                                                                     aei),
                                                 0.0L,
                                                 NULL,
                                                 NULL);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       NULL,
                                       NULL,
                                       NULL,
                                       aet_expr->info.expr->value,
                                       expr_array,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          case RECORD_CONS_OP:
            for (record_field = aet_expr->info.expr->struct_value,
                 expr_record = last_record_field = NULL;
                 (record_field != NULL);
                 record_field = record_field->next_value_cell)
              if (expr_record == NULL)
                last_record_field = expr_record =
		  alloc_value_cell(rewrite_expr_bucket(record_field->value_bucket,
                                                       aei),
                                   0.0L,
                                   NULL,
                                   NULL);
              else
                last_record_field = last_record_field->next_value_cell =
                  alloc_value_cell(rewrite_expr_bucket(record_field->value_bucket,
                                                       aei),
                                   0.0L,
                                   NULL,
                                   NULL);
            aei_expr = set_expr_bucket(aet_expr->info.expr->op_index,
                                       NULL,
                                       NULL,
                                       NULL,
                                       0.0L,
                                       expr_record,
                                       aet_expr->info.expr->illegal_use_cumul);
            break;
          default:
            aei_expr = NULL;
            break;
        }
        return(aei_expr);
}


void		check_id(ID_CONTEXT_INDEX id_context,
			 ST_BUCKET        **id_bucket,
			 BOOLEAN          inside_iteration)
{
	switch (id_context)
	{
	  /* check definitions/declarations */
	  case ARCHI_TYPE_ID_DEF:
	    switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = ARCHI_TYPE_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case BEHAV_ID:
                signal_error(BEHAV_REDEF_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_REDECL_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case AET_ID_DEF:
	    switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = AET_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case BEHAV_ID:
                signal_error(BEHAV_REDEF_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_REDECL_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case AEI_ID_DECL:
	    switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = AEI_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
		if (!selector_enabled[0])
		{
		  if (!inside_iteration)
                    signal_error(AEI_REDECL,
			         NULL,
			         NULL);
		  else
                    signal_error(ONE_AEI_REDECL,
			         (*id_bucket)->symbol_lexeme,
			         NULL);
		  *id_bucket = NULL;
		}
		break;
	      case BEHAV_ID:
                signal_error(BEHAV_REDEF_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_REDECL_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case BEHAV_ID_DEF:
	    switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = BEHAV_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case BEHAV_ID:
                if ((*id_bucket)->defined)
                {
                  signal_error(BEHAV_REDEF,
                               NULL,
			       NULL);
                  *id_bucket = NULL;
                }
                else
                  (*id_bucket)->defined = TRUE;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_REDECL_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case VAR_ID_DECL:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = VAR_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case BEHAV_ID:
                signal_error(BEHAV_REDEF_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
		if (!inside_iteration)
		{
                  signal_error(VAR_REDECL,
			       NULL,
			       NULL);
		  *id_bucket = NULL;
		}
                break;
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
		signal_error(REC_FIELD_REDECL_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case REC_FIELD_ID_DECL:
	    switch ((*id_bucket)->symbol_index)
	    {
	      case ID:
	        (*id_bucket)->symbol_index = REC_FIELD_ID;
	        break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case BEHAV_ID:
	        signal_error(BEHAV_REDEF_REC_FIELD,
		             NULL,
			     NULL);
		*id_bucket = NULL;
	        break;
	      case ACT_TYPE_ID:
	        signal_error(ACT_TYPE_REDEF_REC_FIELD,
		             NULL,
			     NULL);
		*id_bucket = NULL;
	        break;
	      case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
	        signal_error(VAR_REDECL_REC_FIELD,
		             NULL,
			     NULL);
		*id_bucket = NULL;
	        break;
	      case REC_FIELD_ID:
		/* the check for record field redeclaration is postponed to handle_rec_field_list() */
	        break;
	      default:
                break;
	    }
	    break;
	  case PROPERTY_ID_DEF:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = PROPERTY_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case BEHAV_ID:
                signal_error(BEHAV_REDEF_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
		signal_error(REC_FIELD_REDECL_PROPERTY,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case PROPERTY_ID:
		if (!selector_enabled[0])
		{
		  if (!inside_iteration)
                    signal_error(PROPERTY_REDEF,
			         NULL,
			         NULL);
		  else
                    signal_error(ONE_PROPERTY_REDEF,
			         (*id_bucket)->symbol_lexeme,
			         NULL);
		  *id_bucket = NULL;
		}
                break;
	      default:
                break;
            }
	    break;
	  case MEASURE_ID_DEF:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = MEASURE_ID;
                (*id_bucket)->defined = TRUE;
                break;
	      case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_REDEF_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AET_ID:
                signal_error(AET_REDEF_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
	      case AEI_ID:
                signal_error(AEI_REDECL_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
		break;
              case BEHAV_ID:
                signal_error(BEHAV_REDEF_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_REDEF_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_REDECL_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
		signal_error(REC_FIELD_REDECL_MEASURE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case MEASURE_ID:
		if (!selector_enabled[0])
		{
		  if (!inside_iteration)
                    signal_error(MEASURE_REDEF,
			         NULL,
			         NULL);
		  else
                    signal_error(ONE_MEASURE_REDEF,
			         (*id_bucket)->symbol_lexeme,
			         NULL);
		  *id_bucket = NULL;
		}
                break;
	      default:
                break;
            }
	    break;
	  /* check uses */
	  case ARCHI_TYPE_ID_USE:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = ARCHI_TYPE_ID;
		(*id_bucket)->used = TRUE;
                signal_error(ARCHI_TYPE_UNDEF,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ARCHI_TYPE_ID:
                (*id_bucket)->used = TRUE;
                break;
              case AET_ID:
                signal_error(AET_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                signal_error(AEI_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      case BEHAV_ID:
                signal_error(BEHAV_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_ARCHI_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case AET_ID_USE:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = AET_ID;
		(*id_bucket)->used = TRUE;
                signal_error(AET_UNDEF,
			     NULL,
			     NULL);
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                (*id_bucket)->used = TRUE;
                break;
              case AEI_ID:
                signal_error(AEI_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      case BEHAV_ID:
                signal_error(BEHAV_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_AET,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case AEI_ID_USE:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
		(*id_bucket)->used = TRUE;
		if (!selector_enabled[0])
		{
		  if (!inside_iteration)
                    signal_error(AEI_UNDECL,
			         NULL,
			         NULL);
		  else
                    signal_error(ONE_AEI_UNDECL,
			         (*id_bucket)->symbol_lexeme,
			         NULL);
		  *id_bucket = NULL;
		}
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                signal_error(AET_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                (*id_bucket)->used = TRUE;
                break;
	      case BEHAV_ID:
                signal_error(BEHAV_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_AEI,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case BEHAV_ID_USE:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = BEHAV_ID;
                (*id_bucket)->used = TRUE;
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                signal_error(AET_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                signal_error(AEI_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      case BEHAV_ID:
                (*id_bucket)->used = TRUE;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_BEHAV,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case ACT_TYPE_ID_USE:
	  case ACT_TYPE_ID_USE_AUX_SPEC:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
		if (id_context == ACT_TYPE_ID_USE)
		{
                  (*id_bucket)->symbol_index = ACT_TYPE_ID;
                  (*id_bucket)->used = TRUE;
		}
		else
		  if (!selector_enabled[0] &&
		      !selector_enabled[1])
		  {
		    if (!inside_iteration)
                      signal_error(ACT_TYPE_UNDEF,
			           NULL,
			           NULL);
		    else
                      signal_error(ONE_ACT_TYPE_UNDEF,
			           (*id_bucket)->symbol_lexeme,
			           NULL);
		    *id_bucket = NULL;
		  }
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                signal_error(AET_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                signal_error(AEI_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case BEHAV_ID:
                signal_error(BEHAV_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                (*id_bucket)->used = TRUE;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_ACT_TYPE,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case VAR_ID_USE:
	  case VAR_ID_USE_AUX_SPEC:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
		if (id_context == VAR_ID_USE)
		{
		  /* the variable identifier refers to a formal parameter or local variable that has not */
		  /* been defined in the header of the behavior definition being parsed; the identifier is */
		  /* classified as FORMAL_VAR_PAR_ID in order to avoid checking that it has not been used */
		  /* before occurring in an input action */
		  (*id_bucket)->symbol_index = FORMAL_VAR_PAR_ID;
                  (*id_bucket)->used = TRUE;
                  (*id_bucket)->info.expr = alloc_expr(NO_EXPR_OP,
						       NULL,
						       NULL,
						       NULL,
						       NULL,
						       FALSE,
						       0.0L,
						       NULL);
                  signal_error(VAR_UNDECL,
			       NULL,
			       NULL);
		}
		else
		  if (!selector_enabled[0])
		  {
		    if (!inside_iteration)
                      signal_error(VAR_UNDECL,
			           NULL,
			           NULL);
		    else
                      signal_error(ONE_VAR_UNDECL,
			           (*id_bucket)->symbol_lexeme,
			           NULL);
		    *id_bucket = NULL;
		  }
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                signal_error(AET_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                signal_error(AEI_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case BEHAV_ID:
                signal_error(BEHAV_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
		if (id_context == VAR_ID_USE)
		{
		  /* the variable identifier refers to a formal variable parameter or local variable that */
		  /* has not been defined in the header of the behavior definition being parsed; the */
		  /* identifier is classified as FORMAL_VAR_PAR_ID in order to avoid checking that it has */
		  /* not been used before occurring in an input action */
		  (*id_bucket)->symbol_index = FORMAL_VAR_PAR_ID;
                  (*id_bucket)->used = TRUE;
                  (*id_bucket)->info.expr = alloc_expr(NO_EXPR_OP,
						       NULL,
						       NULL,
						       NULL,
						       NULL,
						       FALSE,
						       0.0L,
						       NULL);
                  signal_error(VAR_UNDECL,
			       NULL,
			       NULL);
		}
		else
		  if (!selector_enabled[0])
		  {
		    if (!inside_iteration)
                      signal_error(VAR_UNDECL,
			           NULL,
			           NULL);
		    else
                      signal_error(ONE_VAR_UNDECL,
			           (*id_bucket)->symbol_lexeme,
			           NULL);
		    *id_bucket = NULL;
		  }
                break;
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                (*id_bucket)->used = TRUE;
                break;
              case REC_FIELD_ID:
                signal_error(REC_FIELD_USED_VAR,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
	      default:
                break;
            }
	    break;
	  case REC_FIELD_ID_USE:
	  case REC_FIELD_ID_USE_AUX_SPEC:
            switch ((*id_bucket)->symbol_index)
            {
              case ID:
		if (id_context == REC_FIELD_ID_USE)
		{
                  (*id_bucket)->symbol_index = REC_FIELD_ID;
		  (*id_bucket)->used = TRUE;
		}
                signal_error(REC_FIELD_UNDECL,
			     NULL,
			     NULL);
                break;
              case ARCHI_TYPE_ID:
                signal_error(ARCHI_TYPE_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AET_ID:
                signal_error(AET_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case AEI_ID:
                signal_error(AEI_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case BEHAV_ID:
                signal_error(BEHAV_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case ACT_TYPE_ID:
                signal_error(ACT_TYPE_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case VAR_ID:
              case FORMAL_CONST_PAR_ID:
              case FORMAL_VAR_PAR_ID:
              case LOCAL_VAR_ID:
                signal_error(VAR_USED_REC_FIELD,
			     NULL,
			     NULL);
		*id_bucket = NULL;
                break;
              case REC_FIELD_ID:
		(*id_bucket)->used = TRUE;
                break;
	      default:
                break;
	    }
	    break;
	  case TRACE_FILE_ID_USE:
	    switch ((*id_bucket)->symbol_index)
            {
              case ID:
                (*id_bucket)->symbol_index = TRACE_FILE_ID;
                break;
              case TRACE_FILE_ID:
		if (!selector_enabled[0])
		{
		  if (!inside_iteration)
                    signal_error(TRACE_FILE_REUSED,
			         NULL,
			         NULL);
		  else
                    signal_error(ONE_TRACE_FILE_REUSED,
			         (*id_bucket)->symbol_lexeme,
			         NULL);
		  *id_bucket = NULL;
		}
                break;
	      default:
                break;
            }
	    break;
	  default:
	    break;
	}
}


BOOLEAN		check_rew_act_type(ST_BUCKET *act_type,
				   BOOLEAN   inside_iteration)
{
	BOOLEAN		can_be_attached;

	can_be_attached = TRUE;
	if (act_type->info.act_type->rate_index == (char)PASSIVE)
	{
	  if (!inside_iteration)
	    signal_error(PASSIVE_ACT_TYPE_WITH_REWARD,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_PASSIVE_ACT_TYPE_WITH_REWARD,
		         act_type->symbol_lexeme,
		         NULL);
	  can_be_attached = FALSE;
	}
	return(can_be_attached);
}


BOOLEAN		check_expr_undecl_const_par_free(ST_BUCKET *expr_bucket,
						 ST_BUCKET *selector1,
						 ST_BUCKET *selector2)
{
	BOOLEAN		free;
	EXPR		*expr;
	VALUE_CELL	*list_elem,
			**array_elem,
			*record_field;
	int		array_length,
			array_index;

	expr = expr_bucket->info.expr;
	switch (expr->op_index)
	{
	  case VAR_OP:
	    free = ((expr_bucket == selector1) ||
	            (expr_bucket == selector2) ||
		    ((expr_bucket->symbol_index == FORMAL_CONST_PAR_ID) &&
		     (BOOLEAN)(expr_bucket->defined)));
	    break;
	  case NUMBER_OP:
	  case TRUE_OP:
	  case FALSE_OP:
	    free = TRUE;
	    break;
	  case PLUS_OP:
	  case MINUS_OP:
	  case TIMES_OP:
	  case DIV_OP:
	  case MOD_OP:
	  case MIN_OP:
	  case MAX_OP:
	  case POWER_OP:
	  case C_UNIFORM_OP:
	  case ERLANG_OP:
	  case GAMMA_OP:
	  case WEIBULL_OP:
	  case BETA_OP:
	  case NORMAL_OP:
	  case D_UNIFORM_OP:
	  case BINOMIAL_OP:
	  case NEG_BINOMIAL_OP:
	  case PASCAL_OP:
	  case AND_OP:
	  case OR_OP:
	  case EQ_OP:
	  case NE_OP:
	  case LT_OP:
	  case LE_OP:
	  case GT_OP:
	  case GE_OP:
	  case CONCAT_OP:
	  case INSERT_OP:
	  case REMOVE_OP:
	  case READ_OP:
	  case PUT_OP:
	    free = check_expr_undecl_const_par_free((expr->op_index != PUT_OP)?
					              expr->opn1:
					              expr->opn2,
						    selector1,
						    selector2) &&
		   check_expr_undecl_const_par_free((expr->op_index != PUT_OP)?
					              expr->opn2:
					              expr->opn3,
						    selector1,
						    selector2);
	    break;
	  case ABS_OP:
	  case CEIL_OP:
	  case FLOOR_OP:
	  case EPOWER_OP:
	  case LOGE_OP:
	  case LOG10_OP:
	  case SQRT_OP:
	  case SIN_OP:
	  case COS_OP:
	  case EXPONENTIAL_OP:
	  case PARETO_OP:
	  case POISSON_OP:
	  case GEOMETRIC_OP:
	  case NOT_OP:
	  case FIRST_OP:
	  case TAIL_OP:
	  case LENGTH_OP:
	  case GET_OP:
	    free = check_expr_undecl_const_par_free((expr->op_index != GET_OP)?
					              expr->opn1:
					              expr->opn2,
	    					    selector1,
						    selector2);
	    break;
	  case B_PARETO_OP:
	  case BERNOULLI_OP:
	  case WRITE_OP:
	    free = check_expr_undecl_const_par_free(expr->opn1,
						    selector1,
						    selector2) &&
		   check_expr_undecl_const_par_free(expr->opn2,
						    selector1,
						    selector2) &&
		   check_expr_undecl_const_par_free(expr->opn3,
						    selector1,
						    selector2);
	    break;
	  case LIST_CONS_OP:
	    for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			       expr->struct_value:
			       NULL,
		 free = TRUE;
		 ((list_elem != NULL) &&
		  (free = check_expr_undecl_const_par_free(list_elem->value_bucket,
							   selector1,
							   selector2)));
		 list_elem = list_elem->next_value_cell);
	    break;
	  case ARRAY_CONS_OP:
	    for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
		 array_index = 0,
		 array_length = (int)(expr->value),
		 free = TRUE;
		 ((array_index < array_length) &&
		  (free = check_expr_undecl_const_par_free((*array_elem)->value_bucket,
							   selector1,
							   selector2)));
		 array_elem++,
		 array_index++);
	    break;
	  case RECORD_CONS_OP:
	    for (record_field = expr->struct_value,
		 free = TRUE;
		 ((record_field != NULL) &&
		  (free = check_expr_undecl_const_par_free(record_field->value_bucket,
							   selector1,
							   selector2)));
		 record_field = record_field->next_value_cell);
	    break;
	  default:
	    free = TRUE;
	    break;
	}
	return(free);
}


BOOLEAN		check_expr_randomness_free(ST_BUCKET *expr_bucket)
{
	BOOLEAN		free;
	EXPR		*expr;
	VALUE_CELL	*list_elem,
			**array_elem,
			*record_field;
	int		array_length,
			array_index;

	expr = expr_bucket->info.expr;
	switch (expr->op_index)
	{
	  case VAR_OP:
	  case NUMBER_OP:
	  case TRUE_OP:
	  case FALSE_OP:
	    free = TRUE;
	    break;
	  case EXPONENTIAL_OP:
	  case PARETO_OP:
	  case POISSON_OP:
	  case GEOMETRIC_OP:
	  case C_UNIFORM_OP:
	  case ERLANG_OP:
	  case GAMMA_OP:
	  case WEIBULL_OP:
	  case BETA_OP:
	  case NORMAL_OP:
	  case D_UNIFORM_OP:
	  case BINOMIAL_OP:
	  case NEG_BINOMIAL_OP:
	  case PASCAL_OP:
	  case B_PARETO_OP:
	  case BERNOULLI_OP:
	    free = FALSE;
	    break;
	  case PLUS_OP:
	  case MINUS_OP:
	  case TIMES_OP:
	  case DIV_OP:
	  case MOD_OP:
	  case MIN_OP:
	  case MAX_OP:
	  case POWER_OP:
	  case AND_OP:
	  case OR_OP:
	  case EQ_OP:
	  case NE_OP:
	  case LT_OP:
	  case LE_OP:
	  case GT_OP:
	  case GE_OP:
	  case CONCAT_OP:
	  case INSERT_OP:
	  case REMOVE_OP:
	  case READ_OP:
	  case PUT_OP:
	    free = check_expr_randomness_free((expr->op_index != PUT_OP)?
					        expr->opn1:
					        expr->opn2) &&
		   check_expr_randomness_free((expr->op_index != PUT_OP)?
					        expr->opn2:
					        expr->opn3);
	    break;
	  case ABS_OP:
	  case CEIL_OP:
	  case FLOOR_OP:
	  case EPOWER_OP:
	  case LOGE_OP:
	  case LOG10_OP:
	  case SQRT_OP:
	  case SIN_OP:
	  case COS_OP:
	  case NOT_OP:
	  case FIRST_OP:
	  case TAIL_OP:
	  case LENGTH_OP:
	  case GET_OP:
	    free = check_expr_randomness_free((expr->op_index != GET_OP)?
					        expr->opn1:
					        expr->opn2);
	    break;
	  case WRITE_OP:
	    free = check_expr_randomness_free(expr->opn1) &&
		   check_expr_randomness_free(expr->opn2) &&
		   check_expr_randomness_free(expr->opn3);
	    break;
	  case LIST_CONS_OP:
	    for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			       expr->struct_value:
			       NULL,
		 free = TRUE;
		 ((list_elem != NULL) &&
		  (free = check_expr_randomness_free(list_elem->value_bucket)));
		 list_elem = list_elem->next_value_cell);
	    break;
	  case ARRAY_CONS_OP:
	    for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
		 array_index = 0,
		 array_length = (int)(expr->value),
		 free = TRUE;
		 ((array_index < array_length) &&
		  (free = check_expr_randomness_free((*array_elem)->value_bucket)));
		 array_elem++,
		 array_index++);
	    break;
	  case RECORD_CONS_OP:
	    for (record_field = expr->struct_value,
		 free = TRUE;
		 ((record_field != NULL) &&
		  (free = check_expr_randomness_free(record_field->value_bucket)));
		 record_field = record_field->next_value_cell);
	    break;
	  default:
	    free = TRUE;
	    break;
	}
	return(free);
}


BOOLEAN         check_expr_list_types(ST_BUCKET_CELL *par_list1,
                                      ST_BUCKET_CELL *par_list2,
                                      BOOLEAN        signal_type_mismatch)
{
        BOOLEAN         well_typed;

        for (well_typed = TRUE;
             ((par_list1 != NULL) &&
              (par_list2 != NULL) &&
              well_typed);
             par_list1 = par_list1->next_st_bucket_cell,
             par_list2 = par_list2->next_st_bucket_cell)
          well_typed = check_expr_types(par_list1->st_bucket->info.expr,
                                        par_list2->st_bucket->info.expr,
                                        FALSE);
        if (signal_type_mismatch)
        {
          if (!well_typed)
            signal_error(ILL_TYPED_PARAMETERS,
                         NULL,
                         NULL);
          else
            if ((par_list1 == NULL) &&
                (par_list2 != NULL))
              signal_error(TOO_MANY_PARAMETERS,
                           NULL,
                           NULL);
            else
              if ((par_list2 == NULL) &&
                  (par_list1 != NULL))
                signal_error(TOO_FEW_PARAMETERS,
                             NULL,
                             NULL);
        }
        return(well_typed &&
               (par_list1 == NULL) &&
               (par_list2 == NULL));
}


BOOLEAN		check_expr_types(EXPR    *expr1,
                                 EXPR    *expr2,
                                 BOOLEAN int_real_compatibility)
{
        BOOLEAN         matched;
        VALUE_CELL      *record_field1,
                        *record_field2;
        char            *expr_type1,
                        *expr_type2;

        if ((expr1->data_type == expr2->data_type) ||
            (expr1->data_type == NULL) ||
            (expr2->data_type == NULL))
          matched = TRUE;
        else
        {
          expr_type1 = expr1->data_type->data_type_lexeme;
          expr_type2 = expr2->data_type->data_type_lexeme;
          if ((expr_type1[0] == expr_type2[0]) ||
              (((expr_type1[0] == 'i') ||
                (expr_type1[0] == 'P')) &&
               ((expr_type2[0] == 'i') ||
                (expr_type2[0] == 'P'))) ||
              (((expr_type1[0] == 'r') ||
                (expr_type1[0] == 'R') ||
                (expr_type1[0] == 'W')) &&
               ((expr_type2[0] == 'i') ||
                (expr_type2[0] == 'r') ||
                (expr_type2[0] == 'P') ||
                (expr_type2[0] == 'R') ||
                (expr_type2[0] == 'W'))) ||
              (int_real_compatibility &&
               ((expr_type1[0] == 'i') ||
                (expr_type1[0] == 'P')) &&
               ((expr_type2[0] == 'r') ||
                (expr_type2[0] == 'R') ||
                (expr_type2[0] == 'W'))))
            switch (expr_type1[0])
            {
              case 'i':
              case 'r':
              case 'b':
              case 'P':
              case 'R':
              case 'W':
                matched = TRUE;
                break;
              case 'l':
                /* init_table() of table-handler.c and set_expr_bucket() of symbol-handler.c set to l the */
                /* type of the empty list and the type of the expression lists containing type errors */
                if ((expr_type1[1] == EOS) ||
                    (expr_type2[1] == EOS))
                  matched = TRUE;
                else
                  matched = check_expr_types(expr1->struct_value->value_bucket->info.expr,
                                             expr2->struct_value->value_bucket->info.expr,
                                             int_real_compatibility);
                break;
              case 'a':
                /* handle_struct_data_type() of empa-parser.y sets to 0 the array lengths containing */
                /* errors, while set_expr_bucket() of symbol-handler.c sets to a the type of the */
                /* expression arrays containing type errors */
                if ((expr_type1[1] == EOS) ||
                    (expr_type2[1] == EOS))
                  matched = TRUE;
                else
                  matched = check_expr_types(expr1->struct_value->value_bucket->info.expr,
                                             expr2->struct_value->value_bucket->info.expr,
                                             int_real_compatibility);
                break;
              case 'p':
                /* handle_struct_data_type_decl() of empa-parser.y leaves the field names unspecified if */
                /* the related fields contain errors, while set_expr_bucket() of symbol-handler.c sets to */
                /* p the type of the expression records containing type errors and leaves the field names */
                /* unspecified in the type in the case of expression records */
                if ((expr_type1[1] == EOS) ||
                    (expr_type2[1] == EOS))
                  matched = TRUE;
                else
                {
                  for (record_field1 = expr1->struct_value,
                       record_field2 = expr2->struct_value,
		       matched = TRUE;
                       ((record_field1 != NULL) &&
                        (record_field2 != NULL) &&
                        matched);
                       record_field1 = record_field1->next_value_cell,
                       record_field2 = record_field2->next_value_cell)
                    matched = (((expr1->op_index == RECORD_CONS_OP) ||
                                (expr2->op_index == RECORD_CONS_OP) ||
                                (record_field1->value_bucket->symbol_lexeme == NULL) ||
                                (record_field2->value_bucket->symbol_lexeme == NULL) ||
                                (record_field1->value_bucket->symbol_lexeme ==
                                  record_field2->value_bucket->symbol_lexeme)) &&
                               check_expr_types(record_field1->value_bucket->info.expr,
                                                record_field2->value_bucket->info.expr,
                                                int_real_compatibility));
                  matched = (matched &&
                             (record_field1 == NULL) &&
                             (record_field2 == NULL));
                }
                break;
              default:
                matched = FALSE;
                break;
            }
          else
            matched = FALSE;
        }
        return(matched);
}


BOOLEAN		check_expr_all(ST_BUCKET   *expr,
			       ST_BUCKET   *selector1,
			       ST_BUCKET   *selector2,
			       ST_BUCKET   *data_type,
			       ERROR_INDEX error_index_not_id_free,
			       ERROR_INDEX error_index_not_randomness_free,
			       ERROR_INDEX error_index_ill_typed)
{
	BOOLEAN		correct;

	correct = TRUE;
	if ((error_index_not_id_free != NO_ERROR) &&
	    !check_expr_undecl_const_par_free(expr,
					      selector1,
					      selector2))
	{
	  signal_error(error_index_not_id_free,
		       NULL,
		       NULL);
	  correct = FALSE;
	}
	else
	  if ((error_index_not_randomness_free != NO_ERROR) &&
	      !check_expr_randomness_free(expr))
	  {
	    signal_error(error_index_not_randomness_free,
			 NULL,
			 NULL);
	    correct = FALSE;
	  }
	  else
	    if ((error_index_ill_typed != NO_ERROR) &&
		!check_expr_types(data_type->info.expr,
				  expr->info.expr,
				  (error_index_ill_typed == ILL_TYPED_ASSIGN)?
				    FALSE:
				    TRUE))
	    {
	      signal_error(error_index_ill_typed,
			   NULL,
			   NULL);
	      correct = FALSE;
	    }
	return(correct);
}


BOOLEAN		check_eval_var_lengths_bounds(EXPR    *var,
					      BOOLEAN const_par_or_first_behav_var_par,
					      BOOLEAN inside_par_list,
					      char    *var_lexeme)
{
	BOOLEAN		correct;
	ST_BUCKET	*bound1,
			*bound2,
			*array_length;
	VALUE_CELL	*record_field;

	switch (var->data_type->data_type_lexeme[0])
	{
	  case 'i':
	    bound1 = var->opn1;
	    bound2 = var->opn2;
	    if ((var->data_type->data_type_lexeme[1] != '(') ||
		(bound1 == NULL) ||
		(bound2 == NULL))
	      correct = TRUE;
	    else
	    {
	      eval_expr(bound1,
		        0);
	      eval_expr(bound2,
		        0);
	      if (bound1->info.expr->value > bound2->info.expr->value)
	      {
	        correct = FALSE;
		if (!const_par_or_first_behav_var_par)
		  signal_error(VAR_WITH_EMPTY_INTEGER_RANGE,
		               var_lexeme,
			       NULL);
		else
		  if (!inside_par_list)
	            signal_error(EMPTY_INTEGER_RANGE,
		                 NULL,
			         NULL);
		  else
	            signal_error(ONE_EMPTY_INTEGER_RANGE,
		                 var_lexeme,
			         NULL);
	      }
	      else
	        correct = TRUE;
	    }
	    break;
	  case 'r':
	  case 'b':
	  case 'P':
	  case 'R':
	  case 'W':
	    correct = TRUE;
	    break;
	  case 'l':
	    correct = check_eval_var_lengths_bounds(var->struct_value->value_bucket->info.expr,
						    const_par_or_first_behav_var_par,
						    inside_par_list,
						    var_lexeme);
	    break;
	  case 'a':
	    array_length = (ST_BUCKET *)(var->struct_value->next_value_cell);
	    eval_expr(array_length,
		      0);
	    if (array_length->info.expr->value <= 0.0)
	    {
	      correct = FALSE;
	      if (!const_par_or_first_behav_var_par)
		signal_error(VAR_WITH_ARRAY_LENGTH_NOT_POSITIVE,
		             var_lexeme,
			     NULL);
	      else
	        if (!inside_par_list)
	          signal_error(ARRAY_LENGTH_NOT_POSITIVE,
		               NULL,
			       NULL);
	        else
	          signal_error(ONE_ARRAY_LENGTH_NOT_POSITIVE,
		               var_lexeme,
			       NULL);
	    }
	    else
	    {
	      var->value = array_length->info.expr->value;
	      correct = check_eval_var_lengths_bounds(var->struct_value->value_bucket->info.expr,
						      const_par_or_first_behav_var_par,
						      inside_par_list,
						      var_lexeme);
	    }
	    break;
	  case 'p':
	    for (record_field = var->struct_value,
		 correct = TRUE;
		 ((record_field != NULL) &&
		  correct);
		 record_field = record_field->next_value_cell)
	      correct = check_eval_var_lengths_bounds(record_field->value_bucket->info.expr,
						      const_par_or_first_behav_var_par,
						      inside_par_list,
						      var_lexeme);
	    break;
	  default:
	    correct = FALSE;
	    break;
	}
	return(correct);
}


BOOLEAN		check_assign_lengths_bounds(EXPR    *var,
					    EXPR    *expr,
					    BOOLEAN inside_par_list,
					    char    *var_lexeme)
{
	BOOLEAN		correct;
	VALUE_CELL	*list_elem,
			**array_elem,
			*var_record_field,
			*expr_record_field;
	int		array_length,
			array_index;
	long double	bound1,
			bound2;

	switch (var->data_type->data_type_lexeme[0])
	{
	  case 'i':
	    if ((var->data_type->data_type_lexeme[1] != '(') ||
		(var->opn1 == NULL) ||
		(var->opn2 == NULL))
	      correct = TRUE;
	    else
	    {
	      bound1 = var->opn1->info.expr->value;
	      bound2 = var->opn2->info.expr->value;
	      if ((expr->value < bound1) ||
		  (expr->value > bound2))
	      {
	        correct = FALSE;
		if (!inside_par_list)
	          signal_error(ASSIGN_VALUE_OUT_OF_RANGE,
		               NULL,
			       NULL);
		else
	          signal_error(ONE_ASSIGN_VALUE_OUT_OF_RANGE,
		               var_lexeme,
			       NULL);
	      }
	      else
	        correct = TRUE;
	    }
	    break;
	  case 'r':
	  case 'b':
	    correct = TRUE;
	    break;
	  case 'P':
	    if ((expr->value <= 0.0L) ||
	        !check_int(expr->value))
	    {
	      correct = FALSE;
	      if (!inside_par_list)
	        signal_error(ACTUAL_PRIORITY_NOT_POSITIVE_INT_NUMBER,
		             NULL,
			     NULL);
	      else
	        signal_error(ONE_ACTUAL_PRIORITY_NOT_POSITIVE_INT_NUMBER,
		             var_lexeme,
			     NULL);
	    }
	    else
	      correct = TRUE;
	    break;
	  case 'R':
	    if (expr->value <= 0.0L)
	    {
	      correct = FALSE;
	      if (!inside_par_list)
	        signal_error(ACTUAL_RATE_NOT_POSITIVE_NUMBER,
		             NULL,
			     NULL);
	      else
	        signal_error(ONE_ACTUAL_RATE_NOT_POSITIVE_NUMBER,
		             var_lexeme,
			     NULL);
	    }
	    else
	      correct = TRUE;
	    break;
	  case 'W':
	    if (expr->value <= 0.0L)
	    {
	      correct = FALSE;
	      if (!inside_par_list)
	        signal_error(ACTUAL_WEIGHT_NOT_POSITIVE_NUMBER,
		             NULL,
			     NULL);
	      else
	        signal_error(ONE_ACTUAL_WEIGHT_NOT_POSITIVE_NUMBER,
		             var_lexeme,
			     NULL);
	    }
	    else
	      correct = TRUE;
	    break;
	  case 'l':
	    for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			       expr->struct_value:
			       NULL,
		 correct = TRUE;
	         ((list_elem != NULL) &&
		  correct);
	         list_elem = list_elem->next_value_cell)
	      correct = check_assign_lengths_bounds(var->struct_value->value_bucket->info.expr,
						    list_elem->value_bucket->info.expr,
						    inside_par_list,
						    var_lexeme);
	    break;
	  case 'a':
	    if (var->value != expr->value)
	    {
	      correct = FALSE;
	      if (!inside_par_list)
	        signal_error(ASSIGN_ARRAY_LENGTH_MISMATCH,
		             NULL,
			     NULL);
	      else
	        signal_error(ONE_ASSIGN_ARRAY_LENGTH_MISMATCH,
		             var_lexeme,
			     NULL);
	    }
	    else
	      for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
	           array_index = 0,
		   array_length = (int)(expr->value),
		   correct = TRUE;
	           ((array_index < array_length) &&
		    correct);
	           array_elem++,
	           array_index++)
	        correct = check_assign_lengths_bounds(var->struct_value->value_bucket->info.expr,
						      (*array_elem)->value_bucket->info.expr,
						      inside_par_list,
						      var_lexeme);
	    break;
	  case 'p':
	    for (var_record_field = var->struct_value,
	         expr_record_field = expr->struct_value,
		 correct = TRUE;
		 ((var_record_field != NULL) &&
		  correct);
		 var_record_field = var_record_field->next_value_cell,
		 expr_record_field = expr_record_field->next_value_cell)
	      correct = check_assign_lengths_bounds(var_record_field->value_bucket->info.expr,
						    expr_record_field->value_bucket->info.expr,
						    inside_par_list,
						    var_lexeme);
	    break;
	  default:
	    correct = FALSE;
	    break;
	}
	return(correct);
}


BOOLEAN		check_var_aet_aei_membership(ST_BUCKET    *var,
					     SYMBOL_INDEX aet_aei)
{
	static	char		*prefix		=	NULL;
		int		length;
	static	int		max_length	=	0;

	length = strchr(var->symbol_lexeme,
			'.') -
		 var->symbol_lexeme;
        if (length > max_length)
        {
          if (prefix != NULL)
            free(prefix);
          prefix = alloc_string(length);
          max_length = length;
        }
	strncpy(prefix,
		var->symbol_lexeme,
		length);
	prefix[length] = EOS;
	return(((ST_BUCKET *)search_lexeme_table(prefix,
						 SYT))->symbol_index == (short int)aet_aei);
}


void		handle_concretely_indexed_aei(ST_BUCKET       **aei,
					      EXPR_PARSE_INFO *index,
					      int             selector_index,
					      BOOLEAN         declaration,
					      BOOLEAN         inside_iterative_attachment_property_measure)
{
	if (!poss_aei_index_parsed ||
	    (index != NULL))
	{
	  if (inside_iterative_attachment_property_measure)
	    unindexed_aei[selector_index] = *aei;
	  if (index != NULL)
	  {
	    if (!inside_iterative_attachment_property_measure ||
		check_expr_undecl_const_par_free(index->expr,
						 NULL,
						 NULL))
	    {
	      eval_expr(index->expr,
		        0);
	      index->expr = set_concrete_expr_bucket(index->expr);
	      if (inside_iterative_attachment_property_measure)
	        index_expr[selector_index] = NULL;
	    }
	    else
	      index_expr[selector_index] = index->expr;
	    build_indexed_id(aei,
			     index->expr);
	  }
	  else
	    if (inside_iterative_attachment_property_measure)
	      index_expr[selector_index] = NULL;
	  check_id((declaration)?
		     AEI_ID_DECL:
		     AEI_ID_USE,
		   aei,
		   (!inside_iterative_attachment_property_measure)?
		     FALSE:
		     (index_expr[selector_index] != NULL));
	}
	else
	  *aei = NULL;
}


void		handle_symbolically_indexed_aei(ST_BUCKET       **aei,
					        EXPR_PARSE_INFO *symbolic_index,
					        int             selector_index,
					        BOOLEAN         declaration)
{
	if (symbolic_index != NULL)
	{
	  unindexed_aei[selector_index] = *aei;
	  index_expr[selector_index] = symbolic_index->expr;
	  build_indexed_id(aei,
			   symbolic_index->expr);
	  check_id((declaration)?
		     AEI_ID_DECL:
		     AEI_ID_USE,
		   aei,
		   TRUE);
	}
	else
	  *aei = NULL;
}


void		handle_prefixed_indexed_id(ST_BUCKET        *indexed_prefix,
					   ST_BUCKET        **id,
					   int              selector_index,
					   BOOLEAN          inside_iteration,
					   BOOLEAN          inside_iterative_attachment,
					   ID_CONTEXT_INDEX context)
{
	if (indexed_prefix != NULL)
	{
	  if (inside_iteration)
	    unindexed_id[selector_index] = *id;
	  build_prefixed_id(indexed_prefix,
			    id);
	  if (inside_iterative_attachment &&
	      (index_expr[selector_index] == NULL))
	    unindexed_id[selector_index] = *id;
	  check_id(context,
		   id,
		   inside_iteration);
	}
	else
	  *id = NULL;
}


void		handle_unprefixed_concretely_indexed_id(ST_BUCKET        **id,
						        EXPR_PARSE_INFO  *index,
						        ID_CONTEXT_INDEX id_context)
{
	if (!poss_aei_index_parsed ||
	    (index != NULL))
	{
	  if (index != NULL)
	  {
	    eval_expr(index->expr,
		      0);
	    index->expr = set_concrete_expr_bucket(index->expr);
	    build_indexed_id(id,
			     index->expr);
	  }
	  check_id(id_context,
		   id,
		   FALSE);
	}
	else
	  *id = NULL;
}


void		handle_unprefixed_symbolically_indexed_id(ST_BUCKET        **id,
						          EXPR_PARSE_INFO  *symbolic_index,
						          int              selector_index,
						          ID_CONTEXT_INDEX id_context)
{
	if (!poss_aei_index_parsed ||
	    (symbolic_index != NULL))
	{
	  if (poss_aei_index_parsed ||
	      (id_context == PROPERTY_ID_DEF) ||
	      (id_context == MEASURE_ID_DEF))
	  {
	    unindexed_id[selector_index] = *id;
	    index_expr[selector_index] = symbolic_index->expr;
	    build_indexed_id(id,
			     symbolic_index->expr);
	  }
	  else
	    index_expr[selector_index] = NULL;
	  check_id(id_context,
		   id,
		   TRUE);
	  if (!poss_aei_index_parsed &&
	      (id_context != PROPERTY_ID_DEF) &&
	      (id_context != MEASURE_ID_DEF))
	    unindexed_id[selector_index] = *id;
	}
	else
	  *id = NULL;
}


void		handle_iteration_1(ST_BUCKET **iteration_var)
{
	check_id(VAR_ID_DECL,
		 iteration_var,
		 TRUE);
	selector[selector_index] = *iteration_var;
	id_prefix_in_expr = NULL;
}


void		handle_iteration_2(EXPR_PARSE_INFO **bound1)
{
	if ((*bound1 != NULL) &&
	    !check_expr_all((*bound1)->expr,
			    NULL,
			    NULL,
			    (ST_BUCKET *)search_lexeme_table("integer",
							     SYT),
			    INTEGER_BOUND_NOT_UNDECL_ID_FREE,
			    INTEGER_BOUND_NOT_RANDOMNESS_FREE,
			    ILL_TYPED_INTEGER_BOUND))
	  *bound1 = NULL;
}


ST_BUCKET	*handle_iteration_3(ST_BUCKET       *iteration_var,
				    EXPR_PARSE_INFO *bound1,
				    EXPR_PARSE_INFO *bound2)
{
	if ((bound2 != NULL) &&
	    !check_expr_all(bound2->expr,
			    NULL,
			    NULL,
			    (ST_BUCKET *)search_lexeme_table("integer",
							     SYT),
			    INTEGER_BOUND_NOT_UNDECL_ID_FREE,
			    INTEGER_BOUND_NOT_RANDOMNESS_FREE,
			    ILL_TYPED_INTEGER_BOUND))
	  bound2 = NULL;
	if ((bound1 != NULL) &&
	    (bound2 != NULL))
	{
	  eval_expr(bound1->expr,
		    0);
	  eval_expr(bound2->expr,
		    0);
	  if (bound1->expr->info.expr->value > bound2->expr->info.expr->value)
	  {
	    signal_error(EMPTY_INDEX_RANGE,
			 NULL,
			 NULL);
	    bound1 = bound2 = NULL;
	  }
	}
	if (iteration_var != NULL)
	  iteration_var->info.expr = alloc_expr(VAR_OP,
				                ((bound1 != NULL) &&
				                 (bound2 != NULL))?
				                  bound1->expr:
				                  NULL,
				                ((bound1 != NULL) &&
				                 (bound2 != NULL))?
				                  bound2->expr:
				                  NULL,
				                NULL,
				                (DTT_BUCKET *)search_lexeme_table("i()",
								                  DTT),
				                FALSE,
				                0.0L,
				                NULL);
	selector_enabled[selector_index] = TRUE;
	return(((bound1 != NULL) &&
	        (bound2 != NULL))?
		 iteration_var:
		 NULL);
}


EXPR_PARSE_INFO	*handle_id_in_expr(ST_BUCKET      **id,
				   ST_BUCKET      *aet,
				   ST_BUCKET_CELL **local_var_actual_par_list,
				   BOOLEAN        parsing_behavior_term,
				   BOOLEAN        inside_aux_spec)
{
	ST_BUCKET	*aux_id;

	if ((*id != selector[0]) &&
	    (*id != selector[1]))
	{
	  aux_id = *id;
	  if (id_prefix_in_expr != NULL)
	    build_prefixed_id(id_prefix_in_expr,
			      id);
	  if (((*id)->symbol_index == ID) &&
	      (aet != NULL) &&
	      parsing_behavior_term &&
	      !inside_aux_spec)
	  {
	    *id = aux_id;
	    build_prefixed_id(aet,
			      id);
	  }
	  check_id((!inside_aux_spec)?
		     VAR_ID_USE:
		     VAR_ID_USE_AUX_SPEC,
		   id,
		   FALSE);
	  if ((*id != NULL) &&
	      ((*id)->symbol_index == LOCAL_VAR_ID) &&
	      !inside_aux_spec)
	    *local_var_actual_par_list = alloc_st_bucket_cell(*id,
							      *local_var_actual_par_list);
	}
	return((*id == NULL)?
	         NULL:
	         alloc_expr_parse_info(*id,
				       ((*id)->symbol_index == LOCAL_VAR_ID)?
				         alloc_st_bucket_cell(*id,
							      NULL):
				         NULL));
}


VALUE_CELL	*transform_list_into_array(VALUE_CELL *expr_list,
				           int        value_num)
{
	VALUE_CELL	*expr_array,
			*list_elem,
			**array_elem;

	expr_array = alloc_value_cell(expr_list->value_bucket,
				      value_num,
				      (VALUE_CELL *)new_calloc(value_num,
							       sizeof(VALUE_CELL *)),
				      NULL);
	for (array_elem = (VALUE_CELL **)(expr_array->struct_value),
	     list_elem = expr_list;
	     (list_elem != NULL);
	     array_elem++,
	     list_elem = list_elem->next_value_cell)
	  *array_elem = list_elem;
	return(expr_array);
}


void            encode_local_state_comp(ST_BUCKET *local_state_comp)
{
        static  char            *code_lexeme	=       NULL;
                int             length;
        static  int             max_length      =       0;
	static	unsigned	code		=	1;

        if (local_state_comp->code == NULL)
        {
          length = compute_digit_num((double)code);
          if (length > max_length)
          {
            if (code_lexeme != NULL)
              free(code_lexeme);
            code_lexeme = alloc_string(length);
            max_length = length;
          }
          sprintf(code_lexeme,
                  "%u",
                  code);
          local_state_comp->code = (CDT_BUCKET *)search_lexeme_table(code_lexeme,
								     CDT);
          local_state_comp->code->encoded_local_state_comp = local_state_comp;
          code++;
        }
}


ST_BUCKET	*decode_local_state_comp(char *local_state_lexeme,
					 int  *offset)
{
        static	char		*code_lexeme		=	NULL;
		int		length;
	static	int		max_length		=	0;
        	unsigned	number;

        for (number = 0;
             ((local_state_lexeme[*offset] != ' ') &&
              (local_state_lexeme[*offset] != EOS));
             (*offset)++)
          number = number * 10 + (local_state_lexeme[*offset] - '0');
        (*offset)--;
	length = compute_digit_num((double)number);
	if (length > max_length)
	{
	  if (code_lexeme != NULL)
	    free(code_lexeme);
          code_lexeme = alloc_string(length);
	  max_length = length;
	}
        sprintf(code_lexeme,
                "%u",
                number);
        return(((CDT_BUCKET *)search_lexeme_table(code_lexeme,
						  CDT))->encoded_local_state_comp);
}


void		eval_local_transition_guards(LST_BUCKET **local_state_vector)
{
	L_TRANS_CELL	*local_trans_cell;
	int		i;

	for (i = archi_type[spec_no]->info.archi_type->aei_num - 1;
	     (i >= 0);
	     i--)
	  for (local_trans_cell = local_state_vector[i]->local_trans_list;
	       (local_trans_cell != NULL);
	       local_trans_cell = local_trans_cell->next_l_trans_cell)
	    eval_expr(local_trans_cell->value_passing_info->guard,
		      0);
}


void		eval_global_transition_guards(G_TRANS_CELL *global_trans_list)
{
	G_TRANS_CELL	*global_trans_cell;

	for (global_trans_cell = global_trans_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  eval_expr(global_trans_cell->value_passing_info->guard,
		    0);
}


void            eval_assign_list(ST_BUCKET_CELL *assign_list,
                                 int            input_output_assign_num)
{
        ST_BUCKET_CELL  *assign_cell,
			*first_assign_cell,
			*eq_prefix_assign_cell;
        int             input_output_assign_no,
			prefix_length1,
			prefix_length2;

	/* evaluate the synchronization assignments first */
        for (input_output_assign_no = input_output_assign_num,
	     assign_cell = assign_list;
             (input_output_assign_no > 0);
             input_output_assign_no--,
             assign_cell = assign_cell->next_st_bucket_cell)
          if (assign_cell->st_bucket->info.expr->opn1 != assign_cell->st_bucket->info.expr->opn2)
          {
            eval_expr(assign_cell->st_bucket,
		      0);
	    assign_expr_eval(assign_cell->st_bucket->info.expr->opn1->info.expr,
			     assign_cell->st_bucket->info.expr,
			     ASSIGN_TO_LEFT,
			     assign_cell->st_bucket->symbol_lexeme);
          }

	/* evaluate the unfolding assignments by grouping those ones assigning expressions to variables */
	/* declared in the same behavior of the same AEI */
        for (first_assign_cell = NULL,
	     prefix_length1 = 0;
             (assign_cell != NULL);
             assign_cell = assign_cell->next_st_bucket_cell)
        {
          if (first_assign_cell == NULL)
          {
            first_assign_cell = assign_cell;
            prefix_length1 = strrchr(first_assign_cell->st_bucket->info.expr->opn1->symbol_lexeme,
				     '.') -
			     first_assign_cell->st_bucket->info.expr->opn1->symbol_lexeme +
			     1;
          }
          prefix_length2 = strrchr(assign_cell->st_bucket->info.expr->opn1->symbol_lexeme,
				   '.') -
			   assign_cell->st_bucket->info.expr->opn1->symbol_lexeme +
			   1;
	  if ((prefix_length2 != prefix_length1) ||
	      strncmp(assign_cell->st_bucket->info.expr->opn1->symbol_lexeme,
                      first_assign_cell->st_bucket->info.expr->opn1->symbol_lexeme,
                      prefix_length1))
          {
            for (eq_prefix_assign_cell = first_assign_cell;
                 (eq_prefix_assign_cell != assign_cell);
                 eq_prefix_assign_cell = eq_prefix_assign_cell->next_st_bucket_cell)
              if (eq_prefix_assign_cell->st_bucket->info.expr->opn1 !=
		    eq_prefix_assign_cell->st_bucket->info.expr->opn2)
	        assign_expr_eval(eq_prefix_assign_cell->st_bucket->info.expr->opn1->info.expr,
				 eq_prefix_assign_cell->st_bucket->info.expr,
				 ASSIGN_TO_LEFT,
				 eq_prefix_assign_cell->st_bucket->symbol_lexeme);
            first_assign_cell = assign_cell;
            prefix_length1 = strrchr(first_assign_cell->st_bucket->info.expr->opn1->symbol_lexeme,
                      		     '.') -
              		     first_assign_cell->st_bucket->info.expr->opn1->symbol_lexeme +
			     1;
          }
          eval_expr(assign_cell->st_bucket,
		    0);
        }
        for (eq_prefix_assign_cell = first_assign_cell;
             (eq_prefix_assign_cell != assign_cell);
             eq_prefix_assign_cell = eq_prefix_assign_cell->next_st_bucket_cell)
          if (eq_prefix_assign_cell->st_bucket->info.expr->opn1 !=
		eq_prefix_assign_cell->st_bucket->info.expr->opn2)
	    assign_expr_eval(eq_prefix_assign_cell->st_bucket->info.expr->opn1->info.expr,
			     eq_prefix_assign_cell->st_bucket->info.expr,
			     ASSIGN_TO_LEFT,
			     eq_prefix_assign_cell->st_bucket->symbol_lexeme);
	assign_list_eval_num++;
}


void		eval_expr(ST_BUCKET *expr_bucket,
			  int       run)
{
	BOOLEAN		position_found;
	EXPR		*expr,
			*sub_expr1,
			*sub_expr2,
			*sub_expr3;
	ST_BUCKET	*trace_file;
	VALUE_CELL	*list_elem,
			*last_list_elem,
			**array_elem,
			*record_field;
	int		array_length,
			array_index,
			list_index,
			read_outcome;

	expr = expr_bucket->info.expr;
        sub_expr1 = (expr->opn1 != NULL)?
		      expr->opn1->info.expr:
		      NULL;
        sub_expr2 = (expr->opn2 != NULL)?
		      expr->opn2->info.expr:
		      NULL;
        sub_expr3 = (expr->opn3 != NULL)?
		      expr->opn3->info.expr:
		      NULL;
	switch (expr->op_index)
	{
	  case ASSIGN_OP:
	    if ((sub_expr2->op_index != VAR_OP) &&
		((sub_expr2->sim_run_num != sim_run_num) ||
		 (sub_expr2->assign_list_eval_num != assign_list_eval_num)))
	    {
	      /* the right hand expression of the assignment has to be evaluated because it is not a */
	      /* variable and it is not the right hand expression of another assignment belonging to the */
	      /* currently considered assignment list */
	      sub_expr2->sim_run_num = sim_run_num;
	      sub_expr2->assign_list_eval_num = assign_list_eval_num;
              eval_expr(expr->opn2,
		        run);
	    }
	    if (check_expr_array_lengths(sub_expr1,
					 sub_expr2))
	      assign_expr_eval(expr,
			       sub_expr2,
			       RIGHT_TO_ASSIGN,
			       expr_bucket->symbol_lexeme);
	    else
	      signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			   expr_bucket->symbol_lexeme);
	    break;
	  case PLUS_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    expr->value = sub_expr1->value +
			  sub_expr2->value;
	    break;
	  case MINUS_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    expr->value = sub_expr1->value -
			  sub_expr2->value;
	    break;
	  case TIMES_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    expr->value = sub_expr1->value *
			  sub_expr2->value;
	    break;
	  case DIV_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    if (sub_expr2->value != 0.0L)
	      expr->value = sub_expr1->value /
			    sub_expr2->value;
	    else
	      signal_crash(DIV_OP_CRASH,
			   expr_bucket->symbol_lexeme);
	    break;
          case MOD_OP:
            eval_expr(expr->opn1,
		      run);
            eval_expr(expr->opn2,
		      run);
            if (sub_expr2->value >= 1.0L)
              expr->value = (int)(sub_expr1->value) %
                            (int)(sub_expr2->value);
            else
              signal_crash(MOD_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
	  case MIN_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    expr->value = (sub_expr1->value <= sub_expr2->value)?
			    sub_expr1->value:
			    sub_expr2->value;
	    break;
	  case MAX_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    expr->value = (sub_expr1->value >= sub_expr2->value)?
			    sub_expr1->value:
			    sub_expr2->value;
	    break;
	  case ABS_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)fabs(sub_expr1->value);
	    break;
	  case CEIL_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)ceil(sub_expr1->value);
	    break;
	  case FLOOR_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)floor(sub_expr1->value);
	    break;
          case POWER_OP:
            eval_expr(expr->opn1,
		      run);
            eval_expr(expr->opn2,
		      run);
            if (((sub_expr1->value != 0.0L) ||
		 (sub_expr2->value > 0.0L)) &&
		((sub_expr1->value >= 0.0L) ||
		 check_int(sub_expr2->value)))
              expr->value = (long double)pow(sub_expr1->value,
					     sub_expr2->value);
            else
              signal_crash(POWER_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
          case EPOWER_OP:
            eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)exp(sub_expr1->value);
            break;
          case LOGE_OP:
            eval_expr(expr->opn1,
		      run);
            if (sub_expr1->value > 0.0L)
              expr->value = (long double)log(sub_expr1->value);
            else
              signal_crash(LOG_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
          case LOG10_OP:
            eval_expr(expr->opn1,
		      run);
            if (sub_expr1->value > 0.0L)
              expr->value = (long double)log10(sub_expr1->value);
            else
              signal_crash(LOG_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
          case SQRT_OP:
            eval_expr(expr->opn1,
		      run);
            if (sub_expr1->value >= 0.0L)
              expr->value = (long double)sqrt(sub_expr1->value);
            else
              signal_crash(SQRT_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
          case SIN_OP:
            eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)sin(sub_expr1->value);
            break;
          case COS_OP:
            eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)cos(sub_expr1->value);
            break;
          case C_UNIFORM_OP:
          case ERLANG_OP:
          case GAMMA_OP:
          case EXPONENTIAL_OP:
          case WEIBULL_OP:
          case BETA_OP:
          case NORMAL_OP:
          case PARETO_OP:
          case B_PARETO_OP:
          case D_UNIFORM_OP:
	  case BERNOULLI_OP:
          case BINOMIAL_OP:
          case POISSON_OP:
          case NEG_BINOMIAL_OP:
          case GEOMETRIC_OP:
          case PASCAL_OP:
	    trace_file = (ST_BUCKET *)(expr->struct_value);
	    if (trace_file != NULL)
	    {
	      read_outcome = fscanf(trace_file->info.trace_file,
				    "%Lf\n",
				    &(expr->value));
	      if ((read_outcome == EOF) ||
		  (expr->value <= 0.0L))
		signal_crash(TRACE_READ_CRASH,
			     trace_file->symbol_lexeme);
	    }
	    else
	      switch (expr->op_index)
	      {
	  	case C_UNIFORM_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if (sub_expr1->value < sub_expr2->value)
	            expr->value = gen_c_uniform_num(sub_expr1->value,
					            sub_expr2->value);
	          else
	            signal_crash(C_UNIFORM_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case ERLANG_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((sub_expr1->value > 0.0L) &&
		      (sub_expr2->value >= 1.0L))
	            expr->value = gen_erlang_num(sub_expr1->value,
					         sub_expr2->value);
	          else
	            signal_crash(ERLANG_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case GAMMA_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((sub_expr1->value > 0.0L) &&
		      (sub_expr2->value > 0.0L))
	            expr->value = gen_gamma_num(sub_expr1->value,
					        sub_expr2->value);
	          else
	            signal_crash(GAMMA_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case EXPONENTIAL_OP:
	          eval_expr(expr->opn1,
		            run);
	          if (sub_expr1->value > 0.0L)
	            expr->value = gen_exponential_num(sub_expr1->value);
	          else
	            signal_crash(EXPONENTIAL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case WEIBULL_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((sub_expr1->value > 0.0L) &&
		      (sub_expr2->value > 0.0L))
	            expr->value = gen_weibull_num(sub_expr1->value,
					          sub_expr2->value);
	          else
	            signal_crash(WEIBULL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case BETA_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((sub_expr1->value > 0.0L) &&
		      (sub_expr2->value > 0.0L))
	            expr->value = gen_beta_num(sub_expr1->value,
					       sub_expr2->value);
	          else
	            signal_crash(BETA_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case NORMAL_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if (sub_expr2->value > 0.0L)
	            expr->value = gen_normal_num(sub_expr1->value,
					         sub_expr2->value);
	          else
	            signal_crash(NORMAL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case PARETO_OP:
	          eval_expr(expr->opn1,
		            run);
	          if (sub_expr1->value > 0.0L)
	            expr->value = gen_pareto_num(sub_expr1->value);
	          else
	            signal_crash(PARETO_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case B_PARETO_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          eval_expr(expr->opn3,
		            run);
	          if ((sub_expr1->value > 0.0L) &&
		      (sub_expr2->value >= 1.0L) &&
		      (sub_expr2->value < sub_expr3->value))
	            expr->value = gen_b_pareto_num(sub_expr1->value,
						   sub_expr2->value,
						   sub_expr3->value);
	          else
	            signal_crash(B_PARETO_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case D_UNIFORM_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if (sub_expr1->value < sub_expr2->value)
	            expr->value = gen_d_uniform_num(sub_expr1->value,
					            sub_expr2->value);
	          else
	            signal_crash(D_UNIFORM_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case BERNOULLI_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          eval_expr(expr->opn3,
		            run);
	          if ((0.0L < sub_expr3->value) &&
		      (sub_expr3->value < 1.0L))
	            expr->value = gen_bernoulli_num(sub_expr1->value,
					            sub_expr2->value,
						    sub_expr3->value);
	          else
	            signal_crash(BERNOULLI_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case BINOMIAL_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((0.0L < sub_expr1->value) &&
		      (sub_expr1->value < 1.0L) &&
		      (sub_expr2->value >= 1.0L))
	            expr->value = gen_binomial_num(sub_expr1->value,
					           sub_expr2->value);
	          else
	            signal_crash(BINOMIAL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case POISSON_OP:
	          eval_expr(expr->opn1,
		            run);
	          if (sub_expr1->value > 0.0L)
	            expr->value = gen_poisson_num(sub_expr1->value);
	          else
	            signal_crash(POISSON_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case NEG_BINOMIAL_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((0.0L < sub_expr1->value) &&
		      (sub_expr1->value < 1.0L) &&
		      (sub_expr2->value >= 1.0L))
	            expr->value = gen_neg_binomial_num(sub_expr1->value,
					               sub_expr2->value);
	          else
	            signal_crash(NEG_BINOMIAL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case GEOMETRIC_OP:
	          eval_expr(expr->opn1,
		            run);
	          if ((0.0L < sub_expr1->value) &&
		      (sub_expr1->value < 1.0L))
	            expr->value = gen_geometric_num(sub_expr1->value);
	          else
	            signal_crash(GEOMETRIC_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
	  	case PASCAL_OP:
	          eval_expr(expr->opn1,
		            run);
	          eval_expr(expr->opn2,
		            run);
	          if ((0.0L < sub_expr1->value) &&
		      (sub_expr1->value < 1.0L) &&
		      (sub_expr2->value >= 1.0L))
	            expr->value = gen_pascal_num(sub_expr1->value,
					         sub_expr2->value);
	          else
	            signal_crash(PASCAL_OP_CRASH,
			         expr_bucket->symbol_lexeme);
	    	  break;
		default:
	    	  break;
	      }
	    break;
	  case AND_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = sub_expr1->value;
	    if (expr->value)
	    {
	      eval_expr(expr->opn2,
		        run);
	      expr->value = (long double)(expr->value &&
					  sub_expr2->value);
	    }
	    break;
	  case OR_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = sub_expr1->value;
	    if (!expr->value)
	    {
	      eval_expr(expr->opn2,
		        run);
	      expr->value = (long double)(expr->value ||
					  sub_expr2->value);
	    }
	    break;
	  case NOT_OP:
	    eval_expr(expr->opn1,
		      run);
	    expr->value = (long double)(!sub_expr1->value);
	    break;
	  case EQ_OP:
	  case NE_OP:
	  case LT_OP:
	  case LE_OP:
	  case GT_OP:
	  case GE_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    switch (sub_expr1->data_type->data_type_lexeme[0])
	    {
	      case 'i':
	      case 'r':
	      case 'b':
	      case 'P':
	      case 'R':
	      case 'W':
		expr->value = compare_scalar_value(sub_expr1->value,
						   sub_expr2->value,
						   expr->op_index);
		break;
	      case 'l':
	      case 'a':
	      case 'p':
	        expr->value = compare_struct_value(sub_expr1->struct_value,
						   sub_expr2->struct_value,
						   expr->op_index,
						   sub_expr1,
						   1,
						   1);
		break;
	      default:
		break;
	    }
	    break;
	  case LIST_CONS_OP:
	    for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			       expr->struct_value:
			       NULL;
	         (list_elem != NULL);
	         list_elem = list_elem->next_value_cell)
	    {
	      eval_expr(list_elem->value_bucket,
		        0);
	      if (check_expr_array_lengths(list_elem->value_bucket->info.expr,
					   expr->struct_value->value_bucket->info.expr))
	        copy_value(&list_elem,
			   list_elem,
			   TRUE,
			   expr->struct_value->value_bucket->info.expr,
			   2,
			   2,
			   NULL);
	      else
	        signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			     expr_bucket->symbol_lexeme);
	    }
	    break;
	  case FIRST_OP:
	    eval_expr(expr->opn1,
		      run);
	    if (sub_expr1->struct_value->value != (long double)DBL_MAX)
	    {
	      expr->value = sub_expr1->struct_value->value;
	      if (sub_expr1->struct_value->struct_value != NULL)
	        copy_value(&(expr->struct_value),
			   sub_expr1->struct_value->struct_value,
			   FALSE,
			   expr,
			   1,
			   2,
			   NULL);
	    }
	    else
	      signal_crash(FIRST_OP_CRASH,
			   expr_bucket->symbol_lexeme);
	    break;
	  case TAIL_OP:
	    eval_expr(expr->opn1,
		      run);
	    if (sub_expr1->struct_value->value != (long double)DBL_MAX)
	    {
	      copy_value(&(expr->struct_value),
			 sub_expr1->struct_value->next_value_cell,
			 FALSE,
			 expr,
			 1,
			 1,
			 NULL);
	      if (expr->struct_value == NULL)
		expr->struct_value = alloc_value_cell(sub_expr1->struct_value->value_bucket,
						      (long double)DBL_MAX,
						      NULL,
						      NULL);
	    }
	    else
	    {
              free_value_list(expr->struct_value->next_value_cell,
			      expr,
			      1);
	      expr->struct_value->value = (long double)DBL_MAX;
	    }
	    break;
	  case CONCAT_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    if (!check_expr_array_lengths(sub_expr1,
					  sub_expr2))
	      signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			   expr_bucket->symbol_lexeme);
	    else
	      if (sub_expr1->struct_value->value != (long double)DBL_MAX)
	      {
	        copy_value(&(expr->struct_value),
			   sub_expr1->struct_value,
			   FALSE,
			   expr,
			   1,
			   1,
			   NULL);
	        if (sub_expr2->struct_value->value != (long double)DBL_MAX)
	        {
	          for (list_elem = last_list_elem = expr->struct_value;
		       (list_elem != NULL);
		       last_list_elem = list_elem,
		       list_elem = list_elem->next_value_cell);
	          copy_value(&(last_list_elem->next_value_cell),
			     sub_expr2->struct_value,
			     FALSE,
			     expr,
			     1,
			     1,
			     NULL);
	        }
	      }
	      else
	        copy_value(&(expr->struct_value),
			   sub_expr2->struct_value,
			   FALSE,
			   expr,
			   1,
			   1,
			   NULL);
	    break;
	  case INSERT_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    if (!check_expr_array_lengths(sub_expr1,
					  sub_expr2->struct_value->value_bucket->info.expr))
	      signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			   expr_bucket->symbol_lexeme);
	    else
	    {
	      for (list_elem = (sub_expr2->struct_value->value != (long double)DBL_MAX)?
			         sub_expr2->struct_value:
			         NULL,
		   expr->struct_value = last_list_elem = NULL,
		   position_found = FALSE;
		   ((list_elem != NULL) &&
		    !position_found);
		   list_elem = list_elem->next_value_cell)
	      {
	        /* compare the value of the new element with the value of the current element */
	        switch (sub_expr1->data_type->data_type_lexeme[0])
	        {
		  case 'i':
		  case 'r':
		  case 'b':
		  case 'P':
		  case 'R':
		  case 'W':
		    position_found = (sub_expr1->value < list_elem->value);
		    break;
		  case 'l':
		  case 'a':
		  case 'p':
		    position_found = (BOOLEAN)compare_struct_value(sub_expr1->struct_value,
								   list_elem->struct_value,
								   LT_OP,
								   sub_expr1,
								   1,
								   2);
		    break;
		  default:
		    break;
	        }
	        /* allocate a cell for the new element and copy its value */
	        if (position_found)
	        {
		  if (last_list_elem == NULL)
		    last_list_elem = expr->struct_value = alloc_value_cell(expr->opn1,
									   sub_expr1->value,
									   NULL,
									   NULL);
		  else
		    last_list_elem = last_list_elem->next_value_cell = alloc_value_cell(expr->opn1,
										        sub_expr1->value,
										        NULL,
										        NULL);
		  if (sub_expr1->struct_value != NULL)
	            copy_value(&(last_list_elem->struct_value),
			       sub_expr1->struct_value,
			       FALSE,
			       expr->struct_value->value_bucket->info.expr,
			       2,
			       1,
			       NULL);
	        }
	        /* copy the current element */
	        if (last_list_elem == NULL)
		  last_list_elem = expr->struct_value = alloc_value_cell(list_elem->value_bucket,
								         list_elem->value,
								         NULL,
								         NULL);
	        else
		  last_list_elem = last_list_elem->next_value_cell =
		    alloc_value_cell(list_elem->value_bucket,
				     list_elem->value,
				     NULL,
				     NULL);
	        if (list_elem->struct_value != NULL)
	          copy_value(&(last_list_elem->struct_value),
			     list_elem->struct_value,
			     FALSE,
			     expr->struct_value->value_bucket->info.expr,
			     2,
			     2,
			     NULL);
	      }
	      /* handle the rest of the list */
	      if (!position_found)
	      {
	        /* the end of the list has been reached and the new element has not been inserted */
	        if (last_list_elem == NULL)
		  last_list_elem = expr->struct_value = alloc_value_cell(expr->opn1,
								         sub_expr1->value,
								         NULL,
								         NULL);
	        else
		  last_list_elem = last_list_elem->next_value_cell = alloc_value_cell(expr->opn1,
										      sub_expr1->value,
										      NULL,
										      NULL);
	        if (sub_expr1->struct_value != NULL)
	          copy_value(&(last_list_elem->struct_value),
			     sub_expr1->struct_value,
			     FALSE,
			     expr->struct_value->value_bucket->info.expr,
			     2,
			     1,
			     NULL);
	      }
	      else
	        /* the rest of the list has to be copied */
	        if (list_elem != NULL)
	          copy_value(&(last_list_elem->next_value_cell),
			     list_elem,
			     FALSE,
			     expr,
			     1,
			     1,
			     NULL);
	    }
	    break;
	  case REMOVE_OP:
	    eval_expr(expr->opn1,
		      run);
	    eval_expr(expr->opn2,
		      run);
	    if (sub_expr1->value >= 1.0L)
	    {
	      for (list_elem = (sub_expr2->struct_value->value != (long double)DBL_MAX)?
				 sub_expr2->struct_value:
				 NULL,
		   last_list_elem = NULL,
		   list_index = 1;
		   ((list_elem != NULL) &&
		    (list_index < sub_expr1->value));
		   list_elem = list_elem->next_value_cell,
		   list_index++)
	      {
	        /* copy the current element */
		if (last_list_elem == NULL)
		  last_list_elem = expr->struct_value = alloc_value_cell(list_elem->value_bucket,
									 list_elem->value,
									 NULL,
									 NULL);
		else
		  last_list_elem = last_list_elem->next_value_cell =
		    alloc_value_cell(list_elem->value_bucket,
				     list_elem->value,
				     NULL,
				     NULL);
	        if (list_elem->struct_value != NULL)
	          copy_value(&(last_list_elem->struct_value),
			     list_elem->struct_value,
			     FALSE,
			     expr->struct_value->value_bucket->info.expr,
			     2,
			     2,
			     NULL);
	      }
	      if (list_elem != NULL)
	      {
		/* copy the rest of the list by skipping the element to be removed */
	        if (list_elem->next_value_cell != NULL)
	          copy_value((last_list_elem == NULL)?
			       &(expr->struct_value):
			       &(last_list_elem->next_value_cell),
			     list_elem->next_value_cell,
			     FALSE,
			     expr,
			     1,
			     1,
			     NULL);
	      }
	      else
		/* the list is too short */
		signal_crash(REMOVE_OP_CRASH,
			     expr_bucket->symbol_lexeme);
	    }
	    else
	      /* the position of the element to be removed is not correct */
	      signal_crash(REMOVE_OP_CRASH,
			   expr_bucket->symbol_lexeme);
	    break;
	  case LENGTH_OP:
	    eval_expr(expr->opn1,
		      run);
	    for (list_elem = (sub_expr1->struct_value->value != (long double)DBL_MAX)?
			       sub_expr1->struct_value:
			       NULL,
		 expr->value = 0.0L;
		 (list_elem != NULL);
		 list_elem = list_elem->next_value_cell,
		 expr->value++);
	    break;
	  case ARRAY_CONS_OP:
	    for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
	         array_index = 0,
		 array_length = (int)(expr->value);
	         (array_index < array_length);
	         array_elem++,
	         array_index++)
	    {
	      eval_expr((*array_elem)->value_bucket,
		        0);
	      if (check_expr_array_lengths((*array_elem)->value_bucket->info.expr,
					   (*((VALUE_CELL **)(expr->struct_value->struct_value)))->
					     value_bucket->info.expr))
	        copy_value(&(*array_elem),
			   *array_elem,
			   TRUE,
			   (*array_elem)->value_bucket->info.expr,
			   2,
			   2,
			   NULL);
	      else
	        signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			     expr_bucket->symbol_lexeme);
	    }
	    break;
          case READ_OP:
            eval_expr(expr->opn1,
		      run);
            eval_expr(expr->opn2,
		      run);
            if ((sub_expr1->value >= 0.0L) &&
		(sub_expr1->value < sub_expr2->value))
            {
	      array_elem = (VALUE_CELL **)(sub_expr2->struct_value->struct_value) +
			   (int)(sub_expr1->value);
	      expr->value = (*array_elem)->value;
	      if ((*array_elem)->struct_value != NULL)
	        copy_value(&(expr->struct_value),
			   (*array_elem)->struct_value,
			   FALSE,
			   expr,
			   1,
			   2,
			   NULL);
            }
            else
              signal_crash(READ_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
          case WRITE_OP:
            eval_expr(expr->opn1,
		      run);
            eval_expr(expr->opn2,
		      run);
            eval_expr(expr->opn3,
		      run);
            if ((sub_expr1->value >= 0.0L) &&
		(sub_expr1->value < sub_expr3->value))
            {
	      if (!check_expr_array_lengths(sub_expr2,
					    (*((VALUE_CELL **)(sub_expr3->struct_value->struct_value)))->
					      value_bucket->info.expr))
	        signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			     expr_bucket->symbol_lexeme);
	      else
	      {
	        copy_value(&(expr->struct_value),
			   sub_expr3->struct_value->struct_value,
			   FALSE,
			   expr,
			   1,
			   2,
			   NULL);
	        expr->value = expr->struct_value->value = sub_expr3->value;
	        array_elem = (VALUE_CELL **)(expr->struct_value->struct_value) +
			     (int)(sub_expr1->value);
	        (*array_elem)->value_bucket->info.expr->value = (*array_elem)->value = sub_expr2->value;
	        if (sub_expr2->struct_value != NULL)
	        {
	          copy_value(&((*array_elem)->struct_value),
			     sub_expr2->struct_value,
			     FALSE,
			     expr->struct_value->value_bucket->info.expr,
			     1,
			     1,
			     NULL);
	          (*array_elem)->value_bucket->info.expr->struct_value = (*array_elem)->struct_value;
	        }
	      }
            }
            else
              signal_crash(WRITE_OP_CRASH,
                           expr_bucket->symbol_lexeme);
            break;
	  case RECORD_CONS_OP:
	    for (record_field = expr->struct_value;
	         (record_field != NULL);
	         record_field = record_field->next_value_cell)
	    {
	      eval_expr(record_field->value_bucket,
		        0);
	      copy_value(&record_field,
			 record_field,
			 TRUE,
			 record_field->value_bucket->info.expr,
			 2,
			 2,
			 NULL);
	    }
            break;
	  case GET_OP:
            eval_expr(expr->opn2,
		      run);
	    for (record_field = sub_expr2->struct_value;
	         strcmp(strrchr(record_field->value_bucket->symbol_lexeme,
				';') + 1,
			expr->opn1->symbol_lexeme);
	         record_field = record_field->next_value_cell);
	    expr->value = record_field->value;
	    if (record_field->struct_value != NULL)
	      copy_value(&(expr->struct_value),
			 record_field->struct_value,
			 FALSE,
			 expr,
			 1,
			 2,
			 NULL);
            break;
	  case PUT_OP:
            eval_expr(expr->opn2,
		      run);
            eval_expr(expr->opn3,
		      run);
	    copy_value(&(expr->struct_value),
		       sub_expr3->struct_value,
		       FALSE,
		       expr,
		       1,
		       1,
		       NULL);
	    for (record_field = expr->struct_value;
	         strcmp(strrchr(record_field->value_bucket->symbol_lexeme,
				';') + 1,
			expr->opn1->symbol_lexeme);
	         record_field = record_field->next_value_cell);
	    if (!check_expr_array_lengths(record_field->value_bucket->info.expr,
					  sub_expr2))
	      signal_crash(ARRAY_LENGTH_MISMATCH_CRASH,
			   expr_bucket->symbol_lexeme);
	    else
	    {
	      record_field->value_bucket->info.expr->value = record_field->value = sub_expr2->value;
	      if (sub_expr2->struct_value != NULL)
	      {
	        copy_value(&(record_field->struct_value),
			   sub_expr2->struct_value,
			   FALSE,
			   expr->struct_value->value_bucket->info.expr,
			   2,
			   1,
			   NULL);
	        record_field->value_bucket->info.expr->struct_value = record_field->struct_value;
	      }
	    }
            break;
	  case SAMPLE_OP:
	    if (!expr->illegal_use_cumul)
	    {
	      expr->sample->cumul_reward[run] /= (expr->sample->obs_num[run] == 0)?
						   1.0L:
						   (long double)(expr->sample->obs_num[run]);
	      /* the following instruction is needed if the sample occurs more than once */
	      expr->sample->obs_num[run] = 1;
	    }
	    expr->value = expr->sample->cumul_reward[run];
            break;
	  default:
            break;
	}
}


void		assign_expr_eval(EXPR         *expr1,
				 EXPR         *expr2,
				 ASSIGN_INDEX assign_index,
				 char         *assign_lexeme)
{
		ST_BUCKET		*tmp_value_bucket;
		ST_BUCKET_CELL		*tmp_record_field,
					*prev_tmp_record_field;
	static	ST_BUCKET_CELL		*tmp_value_bucket_list	=	NULL;
		VALUE_CELL		*record_field;

	switch (assign_index)
	{
	  case RIGHT_TO_ASSIGN:
	    /* copy the value of the evaluated right hand expression into the value of the assignment */
	    /* expression before copying it into the value of the left hand variable, so that all the */
	    /* assignments in which that variable occurs in the right hand expression are correctly */
	    /* evaluated */
	    switch (expr1->data_type->data_type_lexeme[0])
	    {
	      case 'i':
	      case 'r':
	      case 'b':
	      case 'P':
	      case 'R':
	      case 'W':
	        expr1->value = expr2->value;
	        break;
	      case 'l':
	      case 'a':
	      case 'p':
		if ((expr1->data_type->data_type_lexeme[0] == 'l') ||
		    (expr1->data_type->data_type_lexeme[0] == 'a'))
		  /* value_bucket is temporarily buffered (see below why) */
		  tmp_value_bucket = expr1->struct_value->value_bucket;
		else
		{
		  tmp_value_bucket = NULL;
		  for (record_field = expr1->struct_value,
		       tmp_record_field = prev_tmp_record_field = tmp_value_bucket_list;
		       (record_field != NULL);
		       record_field = record_field->next_value_cell,
		       prev_tmp_record_field = tmp_record_field,
		       tmp_record_field = tmp_record_field->next_st_bucket_cell)
		  {
		    /* for each field value_bucket is temporarily buffered (see below why) */
		    if (tmp_record_field == NULL)
		    {
		      if (tmp_record_field == tmp_value_bucket_list)
			tmp_record_field = tmp_value_bucket_list = alloc_st_bucket_cell(NULL,
											NULL);
		      else
			prev_tmp_record_field->next_st_bucket_cell = tmp_record_field =
			  alloc_st_bucket_cell(NULL,
					       NULL);
		    }
		    tmp_record_field->st_bucket = record_field->value_bucket;
		  }
		}
		copy_value(&(expr1->struct_value),
			   expr2->struct_value,
			   FALSE,
			   expr1,
			   1,
			   1,
			   assign_lexeme);
		if ((expr1->data_type->data_type_lexeme[0] == 'l') ||
		    (expr1->data_type->data_type_lexeme[0] == 'a'))
		  /* value_bucket is copied from the temporarily buffered value_bucket in order to avoid */
		  /* losing the field names of possible record subtypes in the case of right hand */
		  /* expressions that are expression records */
		  expr1->struct_value->value_bucket = tmp_value_bucket;
		else
		  for (record_field = expr1->struct_value,
		       tmp_record_field = tmp_value_bucket_list;
		       (record_field != NULL);
		       record_field = record_field->next_value_cell,
		       tmp_record_field = tmp_record_field->next_st_bucket_cell)
		    /* value_bucket is copied from the temporarily buffered value_bucket in order to avoid */
		    /* losing the field names in case of right hand expressions that are expression */
		    /* records */
		    record_field->value_bucket = tmp_record_field->st_bucket;
	        break;
	      default:
	        break;
            }
	    break;
	  case ASSIGN_TO_LEFT:
	    /* copy the value of the evaluated assignment expression into the value of the left hand */
	    /* variable */
	    switch (expr1->data_type->data_type_lexeme[0])
	    {
	      case 'i':
		if ((expr1->data_type->data_type_lexeme[1] == '(') &&
		    ((expr2->value < expr1->opn1->info.expr->value) ||
		     (expr2->value > expr1->opn2->info.expr->value)))
		  signal_crash(OUT_OF_RANGE_BOUNDED_INTEGER_CRASH,
			       assign_lexeme);
		else
	          expr1->value = expr2->value;
	        break;
	      case 'r':
	      case 'b':
	      case 'P':
	      case 'R':
	      case 'W':
	        expr1->value = expr2->value;
	        break;
	      case 'l':
	      case 'a':
	      case 'p':
		copy_value(&(expr1->struct_value),
			   expr2->struct_value,
			   FALSE,
			   expr1,
			   1,
			   1,
			   assign_lexeme);
	        break;
	      default:
	        break;
            }
	    break;
        }
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

char		*build_par_behav_invoc_lexeme(ST_BUCKET      *behavior,
					      ST_BUCKET_CELL *actual_par_list)
{
		char		*actual_par_list_lexeme;
        static  char            *lexeme				=       NULL;
                int             length;
        static  int             max_length      		=       0;

	actual_par_list_lexeme = build_par_list_lexeme(actual_par_list);
        length = strlen(behavior->symbol_lexeme) +
		 strlen(actual_par_list_lexeme);
        if (length > max_length)
        {
          if (lexeme != NULL)
            free(lexeme);
          lexeme = alloc_string(length);
          max_length = length;
        }
	sprintf(lexeme,
		"%s%s",
		behavior->symbol_lexeme,
		actual_par_list_lexeme);
	return(lexeme);
}


char		*build_action_lexeme(ST_BUCKET      *type,
				     ACTION_INDEX   action_index,
				     ST_BUCKET_CELL *input_output_par_list,
				     RATE_INDEX     rate_index,
				     ST_BUCKET      *priority,
				     ST_BUCKET      *rate)
{
		char		*input_output_par_list_lexeme;
        static  char            *lexeme				=       NULL;
                int             length;
        static  int             max_length      		=       0;

	/* compute the length of the action lexeme */
	input_output_par_list_lexeme = (input_output_par_list != NULL)?
					 build_par_list_lexeme(input_output_par_list):
					 NULL;
	length = 1 +
		 strlen(type->symbol_lexeme) +
		 ((input_output_par_list != NULL)?
		    (1 +
		     strlen(input_output_par_list_lexeme)):
		    0) +
		 2 +
		 ((rate_index == EXP_TIMED)?
		    (4 +
		     strlen(rate->symbol_lexeme) +
		     1):
		    (((rate_index == IMMEDIATE)?
		       4:
		       2) +
		     strlen(priority->symbol_lexeme) +
		     2 +
		     strlen(rate->symbol_lexeme) +
		     1)) +
		 1;
        if (length > max_length)
        {
          if (lexeme != NULL)
            free(lexeme);
          lexeme = alloc_string(length);
          max_length = length;
        }

	/* build the action lexeme */
	if (rate_index == EXP_TIMED)
	  sprintf(lexeme,
		  "<%s%s%s, exp(%s)>",
		  type->symbol_lexeme,
		  (action_index == INPUT)?
		    "?":
		    ((action_index == OUTPUT)?
		       "!":
		       ""),
		  (input_output_par_list != NULL)?
		    input_output_par_list_lexeme:
		    "",
		  rate->symbol_lexeme);
	else
	  sprintf(lexeme,
		  "<%s%s%s, %s(%s, %s)>",
		  type->symbol_lexeme,
		  (action_index == INPUT)?
		    "?":
		    ((action_index == OUTPUT)?
		       "!":
		       ""),
		  (input_output_par_list != NULL)?
		    input_output_par_list_lexeme:
		    "",
		  (rate_index == IMMEDIATE)?
		    "inf":
		    "_",
		  priority->symbol_lexeme,
		  rate->symbol_lexeme);
	return(lexeme);
}


char		*build_par_list_lexeme(ST_BUCKET_CELL *par_list)
{
		ST_BUCKET_CELL	*par_cell;
        static  char            *lexeme		=       NULL;
                int             length;
        static  int             max_length	=       0;

	for (par_cell = par_list,
	     length = 1;
	     (par_cell != NULL);
	     par_cell = par_cell->next_st_bucket_cell)
	  length += strlen(par_cell->st_bucket->symbol_lexeme) +
		    ((par_cell->next_st_bucket_cell != NULL)?
		       2:
		       0);
	length++;
        if (length > max_length)
        {
          if (lexeme != NULL)
            free(lexeme);
          lexeme = alloc_string(length);
          max_length = length;
        }
	strcpy(lexeme,
	       "(");
	for (par_cell = par_list;
	     (par_cell != NULL);
	     par_cell = par_cell->next_st_bucket_cell)
	{
	  strcat(lexeme,
	         par_cell->st_bucket->symbol_lexeme);
	  if (par_cell->next_st_bucket_cell != NULL)
	    strcat(lexeme,
	           ", ");
	}
	strcat(lexeme,
	       ")");
	return(lexeme);
}


char		*build_expr_lexeme(EXPR_OP_INDEX op_index,
				   ST_BUCKET     *opn1,
				   ST_BUCKET     *opn2,
				   ST_BUCKET     *opn3,
				   long double   array_length,
				   VALUE_CELL    *struct_value,
				   BOOLEAN       cumulative)
{
		BOOLEAN		opn1_paren,
				opn2_paren;
		VALUE_CELL	*value_cell,
				*list_elem,
				**array_elem,
				*record_field;
		char		*aggr_rate_string;
        static  char            *lexeme			=       NULL,
				*expr_op[]		=
	{
		"",
		":=",
		"",
		"",
		"+",
		"-",
		"*",
		"/",
		"mod",
		"min",
		"max",
		"abs",
		"ceil",
		"floor",
		"power",
		"epower",
		"loge",
		"log10",
		"sqrt",
		"sin",
		"cos",
		"c_uniform",
		"erlang",
		"gamma",
		"exponential",
		"weibull",
		"beta",
		"normal",
		"pareto",
		"b_pareto",
		"d_uniform",
		"bernoulli",
		"binomial",
		"poisson",
		"neg_binomial",
		"geometric",
		"pascal",
		"true",
		"false",
		"&&",
		"||",
		"!",
		"=",
		"!=",
		"<",
		"<=",
		">",
		">=",
		"list_cons",
		"first",
		"tail",
		"concat",
		"insert",
		"remove",
		"length",
		"array_cons",
		"read",
		"write",
		"record_cons",
		"get",
		"put",
		"TRUE",
		"FALSE",
		"&",				/* complies with NuSMV: originally /\ */
		"|",				/* complies with NuSMV: originally \/ */
		"!",				/* complies with NuSMV: originally NOT */
		"xor",				/* complies with NuSMV: originally \_/ */
		"->",
		"<->",
		"EXISTS_TRANS",
		"EXISTS_WEAK_TRANS",
		"FOR_ALL_TRANS",
		"FOR_ALL_WEAK_TRANS",
		"EXISTS_TRANS_SET",
		"EXISTS_WEAK_TRANS_SET",
		"FOR_ALL_TRANS_SETS",
		"FOR_ALL_WEAK_TRANS_SETS",
		"MIN_FIXPOINT",
		"MAX_FIXPOINT",
		"AG",
		"AF",
		"",
		"",
		"EG",
		"EF",
		"",
		"",
		"X",				/* complies with NuSMV: originally NEXT_STATE_SAT */
		"G",				/* complies with NuSMV: originally ALL_FUTURE_STATES_SAT */
		"F",				/* complies with NuSMV: originally SOME_FUTURE_STATE_SAT */
		"U",				/* complies with NuSMV: originally UNTIL */
		"V",				/* complies with NuSMV: originally RELEASES */
		"Y",				/* complies with NuSMV: originally PREV_STATE_SAT */
		"H",				/* complies with NuSMV: originally ALL_PAST_STATES_SAT */
		"O",				/* complies with NuSMV: originally SOME_PAST_STATE_SAT */
		"S",				/* complies with NuSMV: originally SINCE */
		"T",				/* complies with NuSMV: originally TRIGGERED */
		""
	};
                int             length,
				array_index;
        static  int             max_length		=       0;

	opn1_paren = opn2_paren = FALSE;
	switch (op_index)
	{
	  case ASSIGN_OP:
	    length = strlen(opn1->symbol_lexeme) +
		     1 +
		     strlen(expr_op[op_index]) +
		     1 +
		     strlen(opn2->symbol_lexeme);
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s %s %s",
		    opn1->symbol_lexeme,
		    expr_op[op_index],
		    opn2->symbol_lexeme);
	    break;
	  case PLUS_OP:
	  case MINUS_OP:
	  case TIMES_OP:
	  case DIV_OP:
	  case AND_OP:
	  case OR_OP:
	  case EQ_OP:
	  case NE_OP:
	  case LT_OP:
	  case LE_OP:
	  case GT_OP:
	  case GE_OP:
	  case PROP_AND_OP:
	  case PROP_OR_OP:
	  case PROP_XOR_OP:
	  case PROP_IMPL_OP:
	  case PROP_BI_IMPL_OP:
	  case LTL_UNTIL_OP:
	  case LTL_RELEASES_OP:
	  case LTL_SINCE_OP:
	  case LTL_TRIGGERED_OP:
	    length = strlen(opn1->symbol_lexeme) +
		     1 +
		     (((op_index == PROP_AND_OP) &&
		       (option_index != CTL_MODEL_CHECK) &&
		       (option_index != LTL_MODEL_CHECK))?
		        2:
		        strlen(expr_op[op_index])) +
		     1 +
		     strlen(opn2->symbol_lexeme);
	    if (strcmp(opn1->symbol_lexeme,
		       "_action_type") &&
		(((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PLUS_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == MINUS_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == TIMES_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == DIV_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == AND_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == OR_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == EQ_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == NE_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LT_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LE_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == GT_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == GE_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PROP_AND_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PROP_OR_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PROP_XOR_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PROP_IMPL_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == PROP_BI_IMPL_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LTL_UNTIL_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LTL_RELEASES_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LTL_SINCE_OP) ||
	         ((EXPR_OP_INDEX)(opn1->info.expr->op_index) == LTL_TRIGGERED_OP)))
	    {
	      opn1_paren = TRUE;
	      length += 2;
	    }
	    if (strcmp(opn1->symbol_lexeme,
		       "_action_type") &&
		(((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PLUS_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == MINUS_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == TIMES_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == DIV_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == AND_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == OR_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == EQ_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == NE_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LT_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LE_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == GT_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == GE_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PROP_AND_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PROP_OR_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PROP_XOR_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PROP_IMPL_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == PROP_BI_IMPL_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LTL_UNTIL_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LTL_RELEASES_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LTL_SINCE_OP) ||
	         ((EXPR_OP_INDEX)(opn2->info.expr->op_index) == LTL_TRIGGERED_OP)))
	    {
	      opn2_paren = TRUE;
	      length += 2;
	    }
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    (!opn1_paren)?
		      ((!opn2_paren)?
		         "%s %s %s":
		         "%s %s (%s)"):
		      ((!opn2_paren)?
		         "(%s) %s %s":
		         "(%s) %s (%s)"),
		    opn1->symbol_lexeme,
		    ((op_index == PROP_AND_OP) &&
		     (option_index != CTL_MODEL_CHECK) &&
		     (option_index != LTL_MODEL_CHECK))?
		      "/\\":
		      expr_op[op_index],
		    opn2->symbol_lexeme);
	    break;
	  case MOD_OP:
	  case MIN_OP:
	  case MAX_OP:
	  case POWER_OP:
          case C_UNIFORM_OP:
          case ERLANG_OP:
          case GAMMA_OP:
          case WEIBULL_OP:
          case BETA_OP:
          case NORMAL_OP:
          case D_UNIFORM_OP:
          case BINOMIAL_OP:
          case NEG_BINOMIAL_OP:
          case PASCAL_OP:
	  case CONCAT_OP:
	  case INSERT_OP:
	  case REMOVE_OP:
	  case READ_OP:
	  case GET_OP:
	  case MODAL_MIN_FIXPOINT_OP:
	  case MODAL_MAX_FIXPOINT_OP:
	    length = strlen(expr_op[op_index]) +
		     1 +
		     (((op_index == GET_OP) &&
		       (opn1 == NULL))?
		        0:
		        strlen(opn1->symbol_lexeme)) +
		     2 +
		     strlen(opn2->symbol_lexeme) +
		     1;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s(%s, %s)",
		    expr_op[op_index],
		    (opn1 == NULL)?
		      "":
		      opn1->symbol_lexeme,
		    opn2->symbol_lexeme);
	    break;
	  case ABS_OP:
	  case CEIL_OP:
	  case FLOOR_OP:
	  case EPOWER_OP:
	  case LOGE_OP:
	  case LOG10_OP:
	  case SQRT_OP:
	  case SIN_OP:
	  case COS_OP:
          case EXPONENTIAL_OP:
          case PARETO_OP:
          case POISSON_OP:
          case GEOMETRIC_OP:
	  case NOT_OP:
	  case FIRST_OP:
	  case TAIL_OP:
	  case LENGTH_OP:
	  case PROP_NOT_OP:
	  case CTL_FOR_ALL_PATHS_ALL_STATES_SAT_OP:
	  case CTL_FOR_ALL_PATHS_SOME_STATE_SAT_OP:
	  case CTL_EXISTS_PATH_ALL_STATES_SAT_OP:
	  case CTL_EXISTS_PATH_SOME_STATE_SAT_OP:
	  case LTL_NEXT_STATE_SAT_OP:
	  case LTL_ALL_FUTURE_STATES_SAT_OP:
	  case LTL_SOME_FUTURE_STATE_SAT_OP:
	  case LTL_PREV_STATE_SAT_OP:
	  case LTL_ALL_PAST_STATES_SAT_OP:
	  case LTL_SOME_PAST_STATE_SAT_OP:
	    length = (((op_index == PROP_NOT_OP) &&
		       (option_index != CTL_MODEL_CHECK) &&
		       (option_index != LTL_MODEL_CHECK))?
		        3:
		        strlen(expr_op[op_index])) +
		     1 +
		     strlen(opn1->symbol_lexeme) +
		     1;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s(%s)",
		    ((op_index == PROP_NOT_OP) &&
		     (option_index != CTL_MODEL_CHECK) &&
		     (option_index != LTL_MODEL_CHECK))?
		      "NOT":
		      expr_op[op_index],
		    opn1->symbol_lexeme);
	    break;
          case B_PARETO_OP:
	  case BERNOULLI_OP:
	  case WRITE_OP:
	  case PUT_OP:
	    length = strlen(expr_op[op_index]) +
		     1 +
		     (((op_index == PUT_OP) &&
		       (opn1 == NULL))?
		        0:
		        strlen(opn1->symbol_lexeme)) +
		     2 +
		     strlen(opn2->symbol_lexeme) +
		     2 +
		     strlen(opn3->symbol_lexeme) +
		     1;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s(%s, %s, %s)",
		    expr_op[op_index],
		    (opn1 == NULL)?
		      "":
		      opn1->symbol_lexeme,
		    opn2->symbol_lexeme,
		    opn3->symbol_lexeme);
	    break;
	  case LIST_CONS_OP:
	    for (list_elem = (struct_value->value != (long double)DBL_MAX)?
			       struct_value:
			       NULL,
		 length = strlen(expr_op[op_index]) +
		 	  1;
		 (list_elem != NULL);
		 list_elem = list_elem->next_value_cell)
	      length += strlen(list_elem->value_bucket->symbol_lexeme) +
			((list_elem->next_value_cell != NULL)?
			   2:
			   0);
	    length++;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(lexeme,
		   expr_op[op_index]);
	    strcat(lexeme,
		   "(");
	    for (list_elem = (struct_value->value != (long double)DBL_MAX)?
			       struct_value:
			       NULL;
		 (list_elem != NULL);
		 list_elem = list_elem->next_value_cell)
	    {
	      strcat(lexeme,
		     list_elem->value_bucket->symbol_lexeme);
	      if (list_elem->next_value_cell != NULL)
	        strcat(lexeme,
		       ", ");
	    }
	    strcat(lexeme,
		   ")");
	    break;
	  case ARRAY_CONS_OP:
	    for (array_elem = (VALUE_CELL **)(struct_value->struct_value),
		 array_index = 0,
		 length = strlen(expr_op[op_index]) +
		 	  1;
		 (array_index < (int)array_length);
		 array_elem++,
		 array_index++)
	      length += strlen((*array_elem)->value_bucket->symbol_lexeme) +
			((array_index < (int)array_length - 1)?
			   2:
			   0);
	    length++;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(lexeme,
		   expr_op[op_index]);
	    strcat(lexeme,
		   "(");
	    for (array_elem = (VALUE_CELL **)(struct_value->struct_value),
		 array_index = 0;
		 (array_index < (int)array_length);
		 array_elem++,
		 array_index++)
	    {
	      strcat(lexeme,
		     (*array_elem)->value_bucket->symbol_lexeme);
	      if (array_index < (int)array_length - 1)
	        strcat(lexeme,
		       ", ");
	    }
	    strcat(lexeme,
		   ")");
	    break;
	  case RECORD_CONS_OP:
	    for (record_field = struct_value,
		 length = strlen(expr_op[op_index]) +
			  1;
		 (record_field != NULL);
		 record_field = record_field->next_value_cell)
	      length += strlen(record_field->value_bucket->symbol_lexeme) +
			((record_field->next_value_cell != NULL)?
			   2:
			   0);
	    length++;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(lexeme,
		   expr_op[op_index]);
	    strcat(lexeme,
		   "(");
	    for (record_field = struct_value;
		 (record_field != NULL);
		 record_field = record_field->next_value_cell)
	    {
	      strcat(lexeme,
		     record_field->value_bucket->symbol_lexeme);
	      if (record_field->next_value_cell != NULL)
	        strcat(lexeme,
		       ", ");
	    }
	    strcat(lexeme,
		   ")");
	    break;
	  case MODAL_CAN_OP:
	  case MODAL_WEAK_CAN_OP:
	  case MODAL_MUST_OP:
	  case MODAL_WEAK_MUST_OP:
	    for (value_cell = struct_value,
		 length = strlen(expr_op[op_index]) +
			  7;
		 (value_cell != NULL);
		 value_cell = value_cell->next_value_cell)
	      length += strlen(value_cell->value_bucket->symbol_lexeme) +
			((value_cell->next_value_cell != NULL)?
			   2:
			   0);
	    length += 21 +
		      strlen(opn1->symbol_lexeme) +
		      2;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(lexeme,
		   expr_op[op_index]);
	    strcat(lexeme,
		   "(LABEL(");
	    for (value_cell = struct_value;
		 (value_cell != NULL);
		 value_cell = value_cell->next_value_cell)
	    {
	      strcat(lexeme,
		     value_cell->value_bucket->symbol_lexeme);
	      if (value_cell->next_value_cell != NULL)
	        strcat(lexeme,
		       ", ");
	    }
	    strcat(lexeme,
		   "); REACHED_STATE_SAT(");
	    strcat(lexeme,
		   opn1->symbol_lexeme);
	    strcat(lexeme,
		   "))");
	    break;
	  case MODAL_CAN_PROB_OP:
	  case MODAL_WEAK_CAN_PROB_OP:
	  case MODAL_MUST_PROB_OP:
	  case MODAL_WEAK_MUST_PROB_OP:
	    for (value_cell = struct_value,
		 length = strlen(expr_op[op_index]) +
			  7;
		 (value_cell != NULL);
		 value_cell = value_cell->next_value_cell)
	      length += strlen(value_cell->value_bucket->symbol_lexeme) +
			((value_cell->next_value_cell != NULL)?
			   2:
			   0);
	    length += 21 +
		      compute_digit_num((double)array_length) +
		      F_FORMAT_PRECIS_LENGTH +
		      22 +
		      strlen(opn1->symbol_lexeme) +
		      2;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(lexeme,
		   expr_op[op_index]);
	    strcat(lexeme,
		   "(LABEL(");
	    for (value_cell = struct_value;
		 (value_cell != NULL);
		 value_cell = value_cell->next_value_cell)
	    {
	      strcat(lexeme,
		     value_cell->value_bucket->symbol_lexeme);
	      if (value_cell->next_value_cell != NULL)
	        strcat(lexeme,
		       ", ");
	    }
	    strcat(lexeme,
		   ((RATE_INDEX)(struct_value->value) == PASSIVE)?
		     "); MIN_AGGR_REA_PROB(":
		     (((RATE_INDEX)(struct_value->value) == EXP_TIMED)?
		        "); MIN_AGGR_EXP_RATE(":
		        "); MIN_AGGR_GEN_PROB("));
	    aggr_rate_string = alloc_string(compute_digit_num((double)array_length) +
					      F_FORMAT_PRECIS_LENGTH);
	    sprintf(aggr_rate_string,
		    "%f",
		    (double)array_length);
	    strcat(lexeme,
		   aggr_rate_string);
	    strcat(lexeme,
		   "); REACHED_STATES_SAT(");
	    strcat(lexeme,
		   opn1->symbol_lexeme);
	    strcat(lexeme,
		   "))");
	    break;
	  case CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP:
	  case CTL_FOR_ALL_PATHS_WEAK_UNTIL_OP:
	  case CTL_EXISTS_PATH_STRONG_UNTIL_OP:
	  case CTL_EXISTS_PATH_WEAK_UNTIL_OP:
	    length = 2 +
		     strlen(opn1->symbol_lexeme) +
		     3 +
		     strlen(opn2->symbol_lexeme) +
		     1;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s(%s %s %s)",
		    ((op_index == CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP) ||
		     (op_index == CTL_FOR_ALL_PATHS_WEAK_UNTIL_OP))?
		      "A":
		      "E",
		    opn1->symbol_lexeme,
		    ((op_index == CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP) ||
		     (op_index == CTL_EXISTS_PATH_STRONG_UNTIL_OP))?
		      "U":
		      "W",
		    opn2->symbol_lexeme);
	    break;
	  case SAMPLE_OP:
	    length = strlen(opn1->symbol_lexeme) +
		     1 +
		     strlen(opn2->symbol_lexeme) +
		     1 +
		     strlen(opn3->symbol_lexeme) +
		     2 +
		     1 +
		     1;
            if (length > max_length)
            {
              if (lexeme != NULL)
                free(lexeme);
              lexeme = alloc_string(length);
              max_length = length;
            }
	    sprintf(lexeme,
		    "%s.%s(%s, %c)",
		    opn1->symbol_lexeme,
		    opn2->symbol_lexeme,
		    opn3->symbol_lexeme,
		    (cumulative)?
		      'c':
		      ' ');
	    break;
	  default:
	    break;
	}
	return(lexeme);
}


char		*build_concrete_expr_lexeme(ST_BUCKET *symbolic_expr)
{
		VALUE_CELL	**array_elem,
				*record_field;
	static	char		*concrete_expr_lexeme	=	NULL;
		int		length,
				array_index;
	static	int		max_length		=	0;

	switch (symbolic_expr->info.expr->data_type->data_type_lexeme[0])
	{
	  case 'i':
	  case 'P':
	    length = compute_digit_num(symbolic_expr->info.expr->value) +
		     ((symbolic_expr->info.expr->value < 0)?
			1:
			0);
	    if (length > max_length)
            {
              if (concrete_expr_lexeme != NULL)
                free(concrete_expr_lexeme);
              concrete_expr_lexeme = alloc_string(length);
              max_length = length;
            }
            sprintf(concrete_expr_lexeme,
                    "%d",
                    (int)(symbolic_expr->info.expr->value));
	    break;
	  case 'r':
	  case 'R':
	  case 'W':
	    length = compute_digit_num(symbolic_expr->info.expr->value) +
		     F_FORMAT_PRECIS_LENGTH +
		     ((symbolic_expr->info.expr->value < 0)?
			1:
			0);
	    if (length > max_length)
            {
              if (concrete_expr_lexeme != NULL)
                free(concrete_expr_lexeme);
              concrete_expr_lexeme = alloc_string(length);
              max_length = length;
            }
            sprintf(concrete_expr_lexeme,
                    "%f",
                    (double)(symbolic_expr->info.expr->value));
	    break;
	  case 'b':
	    length = (symbolic_expr->info.expr->value)?
		       4:
		       5;
	    if (length > max_length)
            {
              if (concrete_expr_lexeme != NULL)
                free(concrete_expr_lexeme);
              concrete_expr_lexeme = alloc_string(length);
              max_length = length;
            }
            sprintf(concrete_expr_lexeme,
		    (symbolic_expr->info.expr->value)?
		      "true":
		      "false");
	    break;
	  case 'a':
	    for (array_elem = (VALUE_CELL **)(symbolic_expr->info.expr->struct_value->struct_value),
		 array_index = 0,
		 length = 11;
		 (array_index < (int)(symbolic_expr->info.expr->value));
		 array_elem++,
		 array_index++)
	    {
	      (*array_elem)->value_bucket->info.expr->sample =
		(SAMPLE *)dupl_string(build_concrete_expr_lexeme((*array_elem)->value_bucket));
	      length += strlen((char *)((*array_elem)->value_bucket->info.expr->sample)) +
			((array_index < (int)(symbolic_expr->info.expr->value) - 1)?
			   2:
			   0);
	    }
	    length++;
            if (length > max_length)
            {
              if (concrete_expr_lexeme != NULL)
                free(concrete_expr_lexeme);
              concrete_expr_lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(concrete_expr_lexeme,
		   "array_cons(");
	    for (array_elem = (VALUE_CELL **)(symbolic_expr->info.expr->struct_value->struct_value),
		 array_index = 0;
		 (array_index < (int)(symbolic_expr->info.expr->value));
		 array_elem++,
		 array_index++)
	    {
	      strcat(concrete_expr_lexeme,
		     (char *)((*array_elem)->value_bucket->info.expr->sample));
	      if (array_index < (int)(symbolic_expr->info.expr->value) - 1)
	        strcat(concrete_expr_lexeme,
		       ", ");
	    }
	    strcat(concrete_expr_lexeme,
		   ")");
	    break;
	  case 'p':
	    for (record_field = symbolic_expr->info.expr->struct_value,
		 length = 12;
		 (record_field != NULL);
		 record_field = record_field->next_value_cell)
	    {
	      record_field->value_bucket->info.expr->sample =
		(SAMPLE *)dupl_string(build_concrete_expr_lexeme(record_field->value_bucket));
	      length += strlen((char *)(record_field->value_bucket->info.expr->sample)) +
			((record_field->next_value_cell != NULL)?
			   2:
			   0);
	    }
	    length++;
            if (length > max_length)
            {
              if (concrete_expr_lexeme != NULL)
                free(concrete_expr_lexeme);
              concrete_expr_lexeme = alloc_string(length);
              max_length = length;
            }
	    strcpy(concrete_expr_lexeme,
		   "record_cons(");
	    for (record_field = symbolic_expr->info.expr->struct_value;
		 (record_field != NULL);
		 record_field = record_field->next_value_cell)
	    {
	      strcat(concrete_expr_lexeme,
		     (char *)(record_field->value_bucket->info.expr->sample));
	      if (record_field->next_value_cell != NULL)
	        strcat(concrete_expr_lexeme,
		       ", ");
	    }
	    strcat(concrete_expr_lexeme,
		   ")");
	    break;
	  default:
	    concrete_expr_lexeme = NULL;
	    break;
	}
	return(concrete_expr_lexeme);
}


char		*build_nusmv_act_type_lexeme(char *act_type_lexeme)
{
		char		*first_square_bracket,
				*last_square_bracket;
	static  char            *nusmv_act_type_lexeme	=       NULL;
                int             length,
				i,
				j;
        static  int             max_length		=       0;

	first_square_bracket = strchr(act_type_lexeme,
				      '[');
	last_square_bracket = strrchr(act_type_lexeme,
				      '[');
	length = strlen(act_type_lexeme) +
		 ((first_square_bracket == NULL)?
		    0:
		    ((first_square_bracket == last_square_bracket)?
		       2:
		       4));
	if (length > max_length)
        {
          if (nusmv_act_type_lexeme != NULL)
            free(nusmv_act_type_lexeme);
          nusmv_act_type_lexeme = alloc_string(length);
          max_length = length;
        }
	for (i = j = 0;
             (act_type_lexeme[i] != EOS);
             i++,
	     j++)
          if ((act_type_lexeme[i] == '[') ||
              (act_type_lexeme[i] == ']'))
	  {
	    nusmv_act_type_lexeme[j] = '-';
	    nusmv_act_type_lexeme[++j] = '-';
	  }
          else
            if (act_type_lexeme[i] == '.')
	      nusmv_act_type_lexeme[j] = '-';
	    else
	      nusmv_act_type_lexeme[j] = act_type_lexeme[i];
	nusmv_act_type_lexeme[j] = EOS;
	return(nusmv_act_type_lexeme);
}


void		rewrite_var_lengths_bounds(EXPR      *var_type,
				           ST_BUCKET *aei)
{
	ST_BUCKET	*array_length,
			*rewritten_array_length;
	VALUE_CELL	*record_field;

	switch (var_type->data_type->data_type_lexeme[0])
	{
	  case 'i':
	    if (var_type->opn1 != NULL)
	      var_type->opn1 = rewrite_expr_bucket(var_type->opn1,
						   aei);
	    if (var_type->opn2 != NULL)
	      var_type->opn2 = rewrite_expr_bucket(var_type->opn2,
						   aei);
	    break;
	  case 'l':
	    rewrite_var_lengths_bounds(var_type->struct_value->value_bucket->info.expr,
				       aei);
	    break;
	  case 'a':
	    array_length = (ST_BUCKET *)(var_type->struct_value->next_value_cell);
	    rewritten_array_length = rewrite_expr_bucket(array_length,
							 aei);
	    var_type->struct_value->next_value_cell = (VALUE_CELL *)rewritten_array_length;
	    rewrite_var_lengths_bounds(var_type->struct_value->value_bucket->info.expr,
				       aei);
	    break;
	  case 'p':
            for (record_field = var_type->struct_value;
                 (record_field != NULL);
                 record_field = record_field->next_value_cell)
	      rewrite_var_lengths_bounds(record_field->value_bucket->info.expr,
				         aei);
	    break;
	  default:
	    break;
	}
}


BOOLEAN		check_expr_array_lengths(EXPR *expr1,
					 EXPR *expr2)
{
	BOOLEAN		matched;
        VALUE_CELL      *record_field1,
                        *record_field2;

	switch (expr1->data_type->data_type_lexeme[0])
	{
          case 'i':
          case 'r':
          case 'b':
          case 'P':
          case 'R':
          case 'W':
            matched = TRUE;
            break;
          case 'l':
            matched = ((expr1->struct_value->value_bucket == NULL) ||
		       (expr2->struct_value->value_bucket == NULL) ||
		       check_expr_array_lengths(expr1->struct_value->value_bucket->info.expr,
                                                expr2->struct_value->value_bucket->info.expr));
            break;
          case 'a':
            matched = ((expr1->value == expr2->value) &&
		       check_expr_array_lengths(expr1->struct_value->value_bucket->info.expr,
                                                expr2->struct_value->value_bucket->info.expr));
            break;
          case 'p':
            for (record_field1 = expr1->struct_value,
                 record_field2 = expr2->struct_value,
		 matched = TRUE;
                 ((record_field1 != NULL) &&
                  (record_field2 != NULL) &&
		  matched);
                 record_field1 = record_field1->next_value_cell,
                 record_field2 = record_field2->next_value_cell)
              matched = check_expr_array_lengths(record_field1->value_bucket->info.expr,
                                                 record_field2->value_bucket->info.expr);
            break;
	  default:
            matched = FALSE;
            break;
	}
	return(matched);
}


void		copy_value(VALUE_CELL **dst_value,
			   VALUE_CELL *src_value,
			   BOOLEAN    struct_value_cons,
			   EXPR       *expr,
			   int        dst_depth,
			   int        src_depth,
			   char       *assign_lexeme)
{
	EXPR		*src_expr;
        VALUE_CELL      *dst_list_elem,
			*prev_dst_list_elem,
			*src_list_elem,
			**dst_array_elem,
			**src_array_elem,
			*dst_record_field,
			*prev_dst_record_field,
			*src_record_field;
	int		array_length,
			array_index;

	if (struct_value_cons)
	  src_expr = src_value->value_bucket->info.expr;
	else
	  src_expr = NULL;
	switch (expr->data_type->data_type_lexeme[0])
	{
	  case 'i':
	    /* apply only for the list, array and record construction operations */
	    if (((*dst_value)->value_bucket->info.expr->data_type->data_type_lexeme[1] == '(') &&
		((src_expr->value <
		    (*dst_value)->value_bucket->info.expr->opn1->info.expr->value) ||
		 (src_expr->value >
		    (*dst_value)->value_bucket->info.expr->opn2->info.expr->value)))
	      signal_crash(OUT_OF_RANGE_BOUNDED_INTEGER_CRASH,
			   assign_lexeme);
	    else
	      (*dst_value)->value = src_expr->value;
	    break;
	  case 'r':
	  case 'b':
	  case 'P':
	  case 'R':
	  case 'W':
	    /* apply only for the list, array and record construction operations */
	    (*dst_value)->value = src_expr->value;
	    break;
	  case 'l':
	    if ((struct_value_cons &&
		 (src_expr->struct_value == NULL)) ||
		(!struct_value_cons &&
		 (src_value == NULL)))
	    {
	      /* free the cells of the destination list because the source list is empty */
	      free_value_list((struct_value_cons)?
				(*dst_value)->struct_value:
				*dst_value,
			      expr,
			      (struct_value_cons)?
				(dst_depth + 1):
				dst_depth);
	      if (struct_value_cons)
		(*dst_value)->struct_value = NULL;
	      else
		*dst_value = NULL;
	    }
	    else
	    {
	      /* copy the source list into the destination list by reusing the cells of the destination */
	      /* list whenever available */
              for (dst_list_elem = prev_dst_list_elem = (struct_value_cons)?
							  (*dst_value)->struct_value:
							  *dst_value,
		   src_list_elem = (struct_value_cons)?
				     src_expr->struct_value:
				     src_value;
                   (src_list_elem != NULL);
		   dst_list_elem = dst_list_elem->next_value_cell,
                   src_list_elem = src_list_elem->next_value_cell)
	      {
                if (dst_list_elem == NULL)
	        {
		  /* allocate a new cell because there are no cells to be reused */
                  dst_list_elem = alloc_value_cell(src_list_elem->value_bucket,
						   src_list_elem->value,
						   NULL,
						   NULL);
		  if (struct_value_cons &&
		      ((*dst_value)->struct_value == NULL))
		    (*dst_value)->struct_value = prev_dst_list_elem = dst_list_elem;
		  else
		    if (!struct_value_cons &&
		        (*dst_value == NULL))
		      *dst_value = prev_dst_list_elem = dst_list_elem;
		    else
		    {
		      prev_dst_list_elem->next_value_cell = dst_list_elem;
		      prev_dst_list_elem = dst_list_elem;
		    }
	        }
                else
	        {
		  /* reuse the cell */
		  dst_list_elem->value_bucket = src_list_elem->value_bucket;
		  dst_list_elem->value = src_list_elem->value;
		  prev_dst_list_elem = dst_list_elem;
	        }
		/* copy recursively the structured value of the current element of the list */
		if (src_list_elem->struct_value != NULL)
	          copy_value(&(dst_list_elem->struct_value),
			     src_list_elem->struct_value,
			     FALSE,
			     expr->struct_value->value_bucket->info.expr,
			     dst_depth + 1,
			     src_depth + 1,
			     assign_lexeme);
	      }
	      /* free the remaining cells of the destination list */
	      free_value_list(prev_dst_list_elem->next_value_cell,
			      expr,
			      dst_depth);
	      prev_dst_list_elem->next_value_cell = NULL;
	    }
	    break;
	  case 'a':
	    /* extract the array length */
	    array_length = (int)(expr->value);
	    /* allocate pointer array if the destination array has not */
	    if (struct_value_cons)
	    {
	      if ((*dst_value)->struct_value == NULL)
		(*dst_value)->struct_value = (VALUE_CELL *)new_calloc(array_length,
								      sizeof(VALUE_CELL *));
	      dst_array_elem = (VALUE_CELL **)((*dst_value)->struct_value);
	      src_array_elem = (VALUE_CELL **)(src_expr->struct_value->struct_value);
	    }
	    else
	    {
	      if (dst_depth == 1)
	      {
		if ((*dst_value)->struct_value == NULL)
	          (*dst_value)->struct_value = (VALUE_CELL *)new_calloc(array_length,
								        sizeof(VALUE_CELL *));
		dst_array_elem = (VALUE_CELL **)((*dst_value)->struct_value);
	      }
	      else
	      {
	        if (*dst_value == NULL)
		  *dst_value = (VALUE_CELL *)new_calloc(array_length,
							sizeof(VALUE_CELL *));
		dst_array_elem = (VALUE_CELL **)(*dst_value);
	      }
	      src_array_elem = (src_depth == 1)?
				 (VALUE_CELL **)(src_value->struct_value):
				 (VALUE_CELL **)src_value;
	    }
	    /* perform the copy recursively */
	    for (array_index = 0;
		 (array_index < array_length);
		 dst_array_elem++,
		 src_array_elem++,
		 array_index++)
	    {
	      if (*dst_array_elem == NULL)
		/* allocate a new cell because there are no cells to be reused */
	        *dst_array_elem = alloc_value_cell((*src_array_elem)->value_bucket,
						   (*src_array_elem)->value,
						   NULL,
						   NULL);
	      else
	      {
		/* reuse the cell */
		(*dst_array_elem)->value_bucket = (*src_array_elem)->value_bucket;
		(*dst_array_elem)->value = (*src_array_elem)->value;
	      }
	      /* copy recursively the structured value of the current element of the array */
	      if ((*src_array_elem)->struct_value != NULL)
	        copy_value(&((*dst_array_elem)->struct_value),
			   (*src_array_elem)->struct_value,
			   FALSE,
			   expr->struct_value->value_bucket->info.expr,
			   dst_depth + 1,
			   src_depth + 1,
			   assign_lexeme);
	    }
	    break;
	  case 'p':
	    /* copy the source record into the destination record by reusing the cells of the destination */
	    /* record whenever available */
            for (dst_record_field = prev_dst_record_field = (struct_value_cons)?
							      (*dst_value)->struct_value:
							      *dst_value,
		 src_record_field = (struct_value_cons)?
				      src_expr->struct_value:
				      src_value;
                 (src_record_field != NULL);
		 dst_record_field = dst_record_field->next_value_cell,
                 src_record_field = src_record_field->next_value_cell)
	    {
              if (dst_record_field == NULL)
	      {
		/* allocate a new cell because there are no cells to be reused */
                dst_record_field = alloc_value_cell(src_record_field->value_bucket,
						    src_record_field->value,
						    NULL,
						    NULL);
		if (struct_value_cons &&
		    ((*dst_value)->struct_value == NULL))
		  (*dst_value)->struct_value = prev_dst_record_field = dst_record_field;
		else
		  if (!struct_value_cons &&
		      (*dst_value == NULL))
		    *dst_value = prev_dst_record_field = dst_record_field;
		  else
		  {
		    prev_dst_record_field->next_value_cell = dst_record_field;
		    prev_dst_record_field = dst_record_field;
		  }
	      }
              else
	      {
		/* reuse the cell */
		dst_record_field->value = src_record_field->value;
		prev_dst_record_field = dst_record_field;
	      }
	      /* copy recursively the structured value of the current field of the record */
	      if (src_record_field->struct_value != NULL)
	        copy_value(&(dst_record_field->struct_value),
			   src_record_field->struct_value,
			   FALSE,
			   expr->struct_value->value_bucket->info.expr,
			   dst_depth + 1,
			   src_depth + 1,
			   assign_lexeme);
	    }
	    break;
	  default:
	    break;
	}
}


long double	compare_scalar_value(long double   value1,
				     long double   value2,
				     EXPR_OP_INDEX comparison_op)
{
	long double	result;

	switch (comparison_op)
	{
	  case EQ_OP:
	    result = (long double)(value1 == value2);
	    break;
	  case NE_OP:
	    result = (long double)(value1 != value2);
	    break;
	  case LT_OP:
	    result = (long double)(value1 < value2);
	    break;
	  case LE_OP:
	    result = (long double)(value1 <= value2);
	    break;
	  case GT_OP:
	    result = (long double)(value1 > value2);
	    break;
	  case GE_OP:
	    result = (long double)(value1 >= value2);
	    break;
	  default:
	    result = 0.0L;
	    break;
	}
	return(result);
}


long double	compare_struct_value(VALUE_CELL    *struct_value1,
				     VALUE_CELL    *struct_value2,
				     EXPR_OP_INDEX comparison_op,
				     EXPR          *expr,
				     int           depth1,
				     int           depth2)
{
	EXPR		*sub_expr;
	VALUE_CELL	*list_elem1,
			*list_elem2,
			**array_elem1,
			**array_elem2,
			*record_field1,
			*record_field2;
	int		array_length,
			array_index;
	long double	result;

	switch (expr->data_type->data_type_lexeme[0])
	{
	  case 'l':
	    if ((struct_value1->value != (long double)DBL_MAX) &&
		(struct_value2->value != (long double)DBL_MAX))
	      sub_expr = expr->struct_value->value_bucket->info.expr;
	    else
	      sub_expr = NULL;
	    for (list_elem1 = (struct_value1->value != (long double)DBL_MAX)?
				struct_value1:
				NULL,
		 list_elem2 = (struct_value2->value != (long double)DBL_MAX)?
				struct_value2:
				NULL,
		 result = (long double)((comparison_op != NE_OP)?
					  TRUE:
					  FALSE);
	         ((list_elem1 != NULL) &&
	          (list_elem2 != NULL) &&
	          ((comparison_op != NE_OP)?
		     result:
		     (long double)(!result)));
	         list_elem1 = list_elem1->next_value_cell,
	         list_elem2 = list_elem2->next_value_cell)
	    {
	      switch (sub_expr->data_type->data_type_lexeme[0])
	      {
		case 'i':
		case 'r':
		case 'b':
		case 'P':
		case 'R':
		case 'W':
		  result = compare_scalar_value(list_elem1->value,
						list_elem2->value,
						comparison_op);
		  break;
		case 'l':
		case 'a':
		case 'p':
	          result = compare_struct_value(list_elem1->struct_value,
						list_elem2->struct_value,
						comparison_op,
						sub_expr,
						depth1 + 1,
						depth2 + 1);
		  break;
		default:
		  result = 0.0L;
		  break;
		}
	    }
	    switch (comparison_op)
	    {
	      case EQ_OP:
	        result = (long double)(result &&
				       (list_elem1 == NULL) &&
				       (list_elem2 == NULL));
	        break;
	      case NE_OP:
	        result = (long double)(result ||
				       !((list_elem1 == NULL) &&
					 (list_elem2 == NULL)));
	        break;
	      case LT_OP:
	      case LE_OP:
	        result = (long double)(result &&
				       ((list_elem1 == NULL) ||
				        (list_elem2 != NULL)));
	        break;
	      case GT_OP:
	      case GE_OP:
	        result = (long double)(result &&
				       ((list_elem1 != NULL) ||
				        (list_elem2 == NULL)));
	        break;
	      default:
		result = 0.0L;
	        break;
	    }
	    break;
	  case 'a':
	    sub_expr = expr->struct_value->value_bucket->info.expr;
	    for (array_elem1 = (depth1 == 1)?
				 (VALUE_CELL **)(struct_value1->struct_value):
				 (VALUE_CELL **)struct_value1,
	         array_elem2 = (depth2 == 1)?
				 (VALUE_CELL **)(struct_value2->struct_value):
				 (VALUE_CELL **)struct_value2,
		 array_length = (int)(expr->value),
		 array_index = 0,
	         result = (comparison_op != NE_OP)?
			    TRUE:
			    FALSE;
		 ((array_index < array_length) &&
	          ((comparison_op != NE_OP)?
		     result:
		     !result));
		 array_elem1++,
		 array_elem2++,
		 array_index++)
	    {
	      switch (sub_expr->data_type->data_type_lexeme[0])
	      {
		case 'i':
		case 'r':
		case 'b':
		case 'P':
		case 'R':
		case 'W':
		  result = compare_scalar_value((*array_elem1)->value,
						(*array_elem2)->value,
						comparison_op);
		  break;
		case 'l':
		case 'a':
		case 'p':
	          result = compare_struct_value((*array_elem1)->struct_value,
						(*array_elem2)->struct_value,
						comparison_op,
						sub_expr,
						depth1 + 1,
						depth2 + 1);
		  break;
		default:
		  result = 0.0L;
		  break;
	      }
	    }
	    break;
	  case 'p':
	    for (record_field1 = struct_value1,
		 record_field2 = struct_value2,
		 result = (long double)((comparison_op != NE_OP)?
					  TRUE:
					  FALSE);
	         ((record_field1 != NULL) &&
	          (record_field2 != NULL) &&
	          ((comparison_op != NE_OP)?
		     result:
		     (long double)(!result)));
	         record_field1 = record_field1->next_value_cell,
	         record_field2 = record_field2->next_value_cell)
	    {
	      sub_expr = record_field1->value_bucket->info.expr;
	      switch (sub_expr->data_type->data_type_lexeme[0])
	      {
		case 'i':
		case 'r':
		case 'b':
		case 'P':
		case 'R':
		case 'W':
		  result = compare_scalar_value(record_field1->value,
						record_field2->value,
						comparison_op);
		  break;
		case 'l':
		case 'a':
		case 'p':
	          result = compare_struct_value(record_field1->struct_value,
						record_field2->struct_value,
						comparison_op,
						sub_expr,
						depth1 + 1,
						depth2 + 1);
		  break;
		default:
		  result = 0.0L;
		  break;
		}
	    }
	    break;
	  default:
	    result = 0.0L;
	    break;
	}
	return(result);
}
