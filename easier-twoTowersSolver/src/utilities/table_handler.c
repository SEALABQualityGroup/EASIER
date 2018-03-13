/****************************************************************/
/*                                                              */
/*                                                              */
/*                       table_handler.c                        */
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module handles the symbol table, the data type table, the code table, the local state table, and */
/* the global state table. These tables are hash tables whose buckets are collected into linked lists. */
/* Each list is self organizing, i.e. the first bucket of the list is associated with the last searched */
/* element. */
/* This module contains the following functions: */
/* - init_hash_tables(): It initializes the hash tables. */
/* - search_lexeme_table(): It searches the symbol table, the data type table, the code table, and the */
/*			    local state table for a lexeme and inserts the lexeme in the hash table if not */
/*			    present. */
/* - search_lst_table(): It searches the local state table for a local state and inserts the local state */
/*			 if not present. */
/* - search_gst_table(): It searches the global state table for a global state and inserts the global */
/*			 state if not present. */
/* - share_non_state_space_hash_tables(): It makes the hash tables not related to the state space */
/*					  coincide. */
/* and the following auxiliary functions: */
/* - hash_weinberger(): It computes the hash function proposed by Weinberger for the lexemes of the symbol */
/*			table, the data type table, the code table, and the local state table. */
/* - hash_additive(): It computes a division method based hash function for the global states of the */
/*		      global state table by taking into account the sum of the local states composing a */
/*		      global state. */
/* - check_local_states_equal(): It checks whether two local states are equal. */
/* - check_global_states_equal(): It checks whether two global states are equal. */
/* - move_bucket(): It moves a bucket at the beginning of a bucket list. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<float.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external variables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/memory_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

ST_BUCKET	*symbol_table[2][SYT_SIZE];
			/* symbol table; */
			/* set by init_table() and move_bucket() of table-handler.c; */
			/* used in check_sem_error() of empa-checker.c and in search_table() of */
			/* table-handler.c */

GST_BUCKET	*global_state_table[2][GST_SIZE];
                       	/* global state table; */
			/* set by init_table(), search_table(), and move_bucket(); */
			/* used in search_table() */


void		init_hash_tables(void),
		*search_lexeme_table(char *,
				     TABLE_INDEX);

LST_BUCKET	*search_lst_table(char *,
				  ST_BUCKET_CELL *);

GST_BUCKET	*search_gst_table(LST_BUCKET **);

void		share_non_state_space_hash_tables(void);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	DTT_BUCKET	*data_type_table[2][DTT_SIZE];
                        	/* data type table; */
				/* set by init_table(), search_table(), and move_bucket(); */
				/* used in search_table() */

PRIVATE	CDT_BUCKET	*code_table[2][CDT_SIZE];
				/* code table; */
				/* set by init_table() and move_bucket(); */
				/* used in search_table() */

PRIVATE	LST_BUCKET	*local_state_table[2][LST_SIZE];
                        	/* local state table; */
				/* set by init_table(), search_table(), and move_bucket(); */
				/* used in search_table() */


PRIVATE	unsigned	hash_weinberger(char *,
					TABLE_INDEX),
			hash_additive(LST_BUCKET **);

PRIVATE	BOOLEAN		check_local_states_equal(LST_BUCKET *,
						 char *,
						 ST_BUCKET_CELL *),
			check_global_states_equal(LST_BUCKET **,
						  LST_BUCKET **);

PRIVATE	void		move_bucket(TABLE_INDEX,
				    int,
				    void *,
				    BOOLEAN,
				    void *);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		init_hash_tables(void)
{
		ST_BUCKET	*st_bucket;
	static	char		*keywords[]	=
	{
		"ARCHI_TYPE",
		"ARCHI_ELEM_TYPES",
		"ELEM_TYPE",
		"BEHAVIOR",
		"INPUT_INTERACTIONS",
		"OUTPUT_INTERACTIONS",
		"UNI",
		"AND",
		"OR",
		"ARCHI_TOPOLOGY",
		"ARCHI_ELEM_INSTANCES",
		"ARCHI_INTERACTIONS",
		"ARCHI_ATTACHMENTS",
		"FROM",
		"TO",
		"BEHAV_VARIATIONS",
		"BEHAV_HIDINGS",
		"HIDE",
		"INTERNALS",
		"INTERACTIONS",
		"ALL",
		"BEHAV_RESTRICTIONS",
		"RESTRICT",
		"OBS_INTERNALS",
		"OBS_INTERACTIONS",
		"ALL_OBSERVABLES",
		"BEHAV_RENAMINGS",
		"RENAME",
		"AS",
		"FOR_ALL",
		"IN",
		"END",
		"const",
		"local",
		"stop",
		"invisible",
		"exp",
		"inf",
		"choice",
		"cond",
		"void",
		"prio",
		"rate",
		"weight",
		"integer",
		"real",
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
		"boolean",
		"true",
		"false",
		"list",
		"list_cons",
		"first",
		"tail",
		"concat",
		"insert",
		"remove",
		"length",
		"array",
		"array_cons",
		"read",
		"write",
		"record",
		"record_cons",
		"get",
		"put",
		"PROPERTY",
		"IS",
		"TRUE",
		"FALSE",
		"NOT",
		"EXISTS_TRANS",
		"EXISTS_WEAK_TRANS",
		"FOR_ALL_TRANS",
		"FOR_ALL_WEAK_TRANS",
		"EXISTS_TRANS_SET",
		"EXISTS_WEAK_TRANS_SET",
		"FOR_ALL_TRANS_SETS",
		"FOR_ALL_WEAK_TRANS_SETS",
		"LABEL",
		"MIN_AGGR_REA_PROB",
		"MIN_AGGR_EXP_RATE",
		"MIN_AGGR_GEN_PROB",
		"REACHED_STATE_SAT",
		"REACHED_STATES_SAT",
		"MIN_FIXPOINT",
		"MAX_FIXPOINT",
		"DEADLOCK_FREE",
		"FOR_ALL_PATHS",
		"FOR_ALL_PATHS_ALL_STATES_SAT",
		"FOR_ALL_PATHS_SOME_STATE_SAT",
		"EXISTS_PATH",
		"EXISTS_PATH_ALL_STATES_SAT",
		"EXISTS_PATH_SOME_STATE_SAT",
		"STRONG_UNTIL",
		"WEAK_UNTIL",
		"NEXT_STATE_SAT",
                "ALL_FUTURE_STATES_SAT",
                "SOME_FUTURE_STATE_SAT",
                "UNTIL",
                "RELEASES",
                "PREV_STATE_SAT",
                "ALL_PAST_STATES_SAT",
                "SOME_PAST_STATE_SAT",
                "SINCE",
                "TRIGGERED",
		"HIGH_SECURITY",
		"LOW_SECURITY",
		"OBS_NRESTR_INTERNALS",
		"OBS_NRESTR_INTERACTIONS",
		"ALL_OBS_NRESTR",
		"MEASURE",
		"ENABLED",
		"STATE_REWARD",
		"TRANS_REWARD",
		"RUN_LENGTH_ON_EXEC",
		"RUN_LENGTH",
		"RUN_NUMBER",
		"MEAN",
		"VARIANCE",
		"DISTRIBUTION",
		"REWARD",
		"EXECUTED",
		"CUMULATIVE",
		"NON_CUMULATIVE",
		"DRAW",
		"trc"
	},
				*data_types[]	=
	{
		"i",
		"i()",
		"P",
		"r",
		"R",
		"W",
		"b",
		"l",
		"a",
		"p"
	};
		int		j;

	for (table_no = 0;
	     (table_no < 2);
	     table_no++)
	{
	  /* initialize the data type table by inserting the data type shorthands; these are needed while */
	  /* initializing the symbol table */
          for (j = 0;
               (j < DTT_SIZE);
               j++)
            data_type_table[table_no][j] = NULL;
	  for (j = 0;
	       (j < DATA_TYPE_NUM);
	       j++)
	    search_lexeme_table(data_types[j],
				DTT);
	  /* initialize the symbol table by inserting the keywords */
	  for (j = 0;
	       (j < SYT_SIZE);
	       j++)
	    symbol_table[table_no][j] = NULL;
	  for (j = 0;
	       (j < KEYWORD_NUM);
	       j++)
	  {
	    st_bucket = (ST_BUCKET *)search_lexeme_table(keywords[j],
							 SYT);
	    st_bucket->symbol_index = FIRST_KEYWORD_TOKEN + j;
	    switch (st_bucket->symbol_index)
	    {
	      case INTEGER:
	      case PRIO:
	        st_bucket->info.expr =
		  alloc_expr(NO_EXPR_OP,
			     NULL,
			     NULL,
			     NULL,
			     (DTT_BUCKET *)search_lexeme_table((st_bucket->symbol_index == INTEGER)?
								 "i":
								 "P",
							       DTT),
			     FALSE,
			     0.0L,
			     NULL);
	        break;
	      case REAL:
	      case RATE:
	      case WEIGHT:
	        st_bucket->info.expr =
		  alloc_expr(NO_EXPR_OP,
			     NULL,
			     NULL,
			     NULL,
			     (DTT_BUCKET *)search_lexeme_table((st_bucket->symbol_index == REAL)?
								 "r":
								 ((st_bucket->symbol_index == RATE)?
								    "R":
								    "W"),
							       DTT),
			     FALSE,
			     0.0L,
			     NULL);
	        break;
	      case K_TRUE:
	      case PROP_TRUE:
	        st_bucket->info.expr = alloc_expr((st_bucket->symbol_index == K_TRUE)?
				                    TRUE_OP:
				                    PROP_TRUE_OP,
				                  NULL,
				                  NULL,
				                  NULL,
					          (DTT_BUCKET *)search_lexeme_table("b",
								                    DTT),
				                  FALSE,
				                  1.0L,
				                  NULL);
	        break;
	      case K_FALSE:
	      case PROP_FALSE:
	        st_bucket->info.expr = alloc_expr((st_bucket->symbol_index == K_FALSE)?
				                    FALSE_OP:
				                    PROP_FALSE_OP,
				                  NULL,
				                  NULL,
				                  NULL,
					          (DTT_BUCKET *)search_lexeme_table("b",
								                    DTT),
				                  FALSE,
				                  0.0L,
				                  NULL);
	        break;
	      default:
	        break;
	    }
	  }
	  /* initialize the code table */
	  for (j = 0;
	       (j < CDT_SIZE);
	       j++)
	    code_table[table_no][j] = NULL;
	  /* initialize the local state table */
          for (j = 0;
               (j < LST_SIZE);
               j++)
            local_state_table[table_no][j] = NULL;
	  /* initialize the global state table */
          for (j = 0;
               (j < GST_SIZE);
               j++)
            global_state_table[table_no][j] = NULL;
	}
}


void		*search_lexeme_table(char        *lexeme,
				     TABLE_INDEX table_index)
{
	unsigned	list_index;
	void		*prev_bucket,
			*curr_bucket;

	/* search the hash table for the lexeme bucket */
	list_index = hash_weinberger(lexeme,
				     table_index);
	for (prev_bucket = curr_bucket = (table_index == SYT)?
					   (void *)(symbol_table[table_no][list_index]):
					   ((table_index == DTT)?
					      (void *)(data_type_table[table_no][list_index]):
					      (void *)(code_table[table_no][list_index]));
	     ((curr_bucket != NULL) &&
	       strcmp(lexeme,
		      (table_index == SYT)?
		        ((ST_BUCKET *)curr_bucket)->symbol_lexeme:
			((table_index == DTT)?
		           ((DTT_BUCKET *)curr_bucket)->data_type_lexeme:
		           ((CDT_BUCKET *)curr_bucket)->code_lexeme)));
	     prev_bucket = curr_bucket,
	     curr_bucket = (table_index == SYT)?
			     (void *)(((ST_BUCKET *)curr_bucket)->next_st_bucket):
			     ((table_index == DTT)?
			     	(void *)(((DTT_BUCKET *)curr_bucket)->next_dtt_bucket):
			     	(void *)(((CDT_BUCKET *)curr_bucket)->next_cdt_bucket)));

	/* process the lexeme bucket */
	if (curr_bucket == NULL)
	{
	  curr_bucket = (table_index == SYT)?
			      (void *)alloc_st_bucket(lexeme):
			      ((table_index == DTT)?
			         (void *)alloc_dtt_bucket(lexeme):
			         (void *)alloc_cdt_bucket(lexeme));
	  move_bucket(table_index,
		      list_index,
		      curr_bucket,
		      FALSE,
		      NULL);
	}
	else
	  if (prev_bucket != curr_bucket)
	    move_bucket(table_index,
			list_index,
			curr_bucket,
			TRUE,
			prev_bucket);
	return(curr_bucket);
}


LST_BUCKET	*search_lst_table(char           *local_state_lexeme,
				  ST_BUCKET_CELL *state_var_assign_list)
{
	LST_BUCKET	*prev_lst_bucket,
			*curr_lst_bucket;
	unsigned	list_index;

	/* search the local state table for the local state bucket */
	list_index = hash_weinberger(local_state_lexeme,
				     LST);
	for (prev_lst_bucket = curr_lst_bucket = local_state_table[table_no][list_index];
	     ((curr_lst_bucket != NULL) &&
	      !check_local_states_equal(curr_lst_bucket,
		      		        local_state_lexeme,
				        state_var_assign_list));
	     prev_lst_bucket = curr_lst_bucket,
	     curr_lst_bucket = curr_lst_bucket->next_lst_bucket);

	/* process the local state bucket */
	if (curr_lst_bucket == NULL)
	{
	  curr_lst_bucket = alloc_lst_bucket(local_state_lexeme,
					     state_var_assign_list);
	  move_bucket(LST,
		      list_index,
		      curr_lst_bucket,
		      FALSE,
		      NULL);
	}
	else
	  if (prev_lst_bucket != curr_lst_bucket)
	    move_bucket(LST,
			list_index,
			curr_lst_bucket,
			TRUE,
			prev_lst_bucket);
	return(curr_lst_bucket);
}


GST_BUCKET	*search_gst_table(LST_BUCKET **local_state_vector)
{
	GST_BUCKET	*prev_gst_bucket,
			*curr_gst_bucket;
	unsigned	list_index;

	/* search the global state table for the global state bucket */
	list_index = hash_additive(local_state_vector);
	for (prev_gst_bucket = curr_gst_bucket = global_state_table[table_no][list_index];
	     ((curr_gst_bucket != NULL) &&
	      !check_global_states_equal(curr_gst_bucket->local_state_vector,
		      			 local_state_vector));
	     prev_gst_bucket = curr_gst_bucket,
	     curr_gst_bucket = curr_gst_bucket->next_gst_bucket);

	/* process the global state bucket */
	if (curr_gst_bucket == NULL)
	{
	  curr_gst_bucket = alloc_gst_bucket(local_state_vector);
	  move_bucket(GST,
		      list_index,
		      curr_gst_bucket,
		      FALSE,
		      NULL);
	}
	else
	{
	  free(local_state_vector);
	  if (prev_gst_bucket != curr_gst_bucket)
	    move_bucket(GST,
			list_index,
			curr_gst_bucket,
			TRUE,
			prev_gst_bucket);
	}
	return(curr_gst_bucket);
}


void		share_non_state_space_hash_tables(void)
{
	int		i;

	for (i = 0;
	     (i < SYT_SIZE);
	     i++)
	  symbol_table[1][i] = symbol_table[0][i];
	for (i = 0;
	     (i < DTT_SIZE);
	     i++)
	  data_type_table[1][i] = data_type_table[0][i];
	for (i = 0;
	     (i < CDT_SIZE);
	     i++)
	  code_table[1][i] = code_table[0][i];
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

unsigned	hash_weinberger(char        *lexeme,
				TABLE_INDEX table_index)
{
        unsigned        i,
			h,
                        g;

        for (i = 0,
	     h = 0;
	     (lexeme[i] != EOS);
	     i++)
        {
          h = (h << HASH1) + lexeme[i];
          if ((g = h & HASH2))
            h = h ^ (g >> HASH3) ^ g;
        }
        return((table_index == SYT)?
		 (h % SYT_SIZE):
		 ((table_index == DTT)?
		    (h % DTT_SIZE):
		    ((table_index == CDT)?
		       (h % CDT_SIZE):
		       (h % LST_SIZE))));
}


unsigned	hash_additive(LST_BUCKET **global_state)
{
	unsigned	i,
			sum;

	for (i = archi_type[spec_no]->info.archi_type->aei_num,
	     sum = 0;
	     (i >= 1);
	     i--)
	  sum += (unsigned)(global_state[i - 1]);
	return(sum % GST_SIZE);
}


BOOLEAN		check_local_states_equal(LST_BUCKET     *local_state,
					 char           *local_state_lexeme,
					 ST_BUCKET_CELL *state_var_assign_list)
{
	BOOLEAN		equal;
	ST_BUCKET_CELL	*state_var_assign_cell1,
			*state_var_assign_cell2;

	for (state_var_assign_cell1 = local_state->state_var_assign_list,
	     state_var_assign_cell2 = state_var_assign_list,
	     equal = !strcmp(local_state->local_state_lexeme,
			     local_state_lexeme);
	     ((state_var_assign_cell1 != NULL) &&
	      (state_var_assign_cell2 != NULL) &&
	      equal);
	     state_var_assign_cell1 = state_var_assign_cell1->next_st_bucket_cell,
	     state_var_assign_cell2 = state_var_assign_cell2->next_st_bucket_cell)
	  equal = (state_var_assign_cell1->st_bucket == state_var_assign_cell2->st_bucket);
	return(equal &&
	       (state_var_assign_cell1 == NULL) &&
	       (state_var_assign_cell2 == NULL));
}


BOOLEAN		check_global_states_equal(LST_BUCKET **global_state1,
					  LST_BUCKET **global_state2)
{
	BOOLEAN		equal;
	int		i;

	for (i = archi_type[spec_no]->info.archi_type->aei_num,
	     equal = TRUE;
	     ((i >= 1) &&
	      equal);
	     i--)
	  equal = (global_state1[i - 1] == global_state2[i - 1]);
	return(equal);
}


void		move_bucket(TABLE_INDEX table_index,
			    int         list_index,
			    void        *curr_bucket,
			    BOOLEAN     adjust_prev,
			    void        *prev_bucket)
{
	switch (table_index)
	{
	  case SYT:
	    if (adjust_prev)
	      ((ST_BUCKET *)prev_bucket)->next_st_bucket = ((ST_BUCKET *)curr_bucket)->next_st_bucket;
	    ((ST_BUCKET *)curr_bucket)->next_st_bucket = symbol_table[table_no][list_index];
	    symbol_table[table_no][list_index] = (ST_BUCKET *)curr_bucket;
	    break;
	  case DTT:
	    if (adjust_prev)
	      ((DTT_BUCKET *)prev_bucket)->next_dtt_bucket = ((DTT_BUCKET *)curr_bucket)->next_dtt_bucket;
	    ((DTT_BUCKET *)curr_bucket)->next_dtt_bucket = data_type_table[table_no][list_index];
	    data_type_table[table_no][list_index] = (DTT_BUCKET *)curr_bucket;
	    break;
	  case CDT:
	    if (adjust_prev)
	      ((CDT_BUCKET *)prev_bucket)->next_cdt_bucket = ((CDT_BUCKET *)curr_bucket)->next_cdt_bucket;
	    ((CDT_BUCKET *)curr_bucket)->next_cdt_bucket = code_table[table_no][list_index];
	    code_table[table_no][list_index] = (CDT_BUCKET *)curr_bucket;
	    break;
	  case LST:
	    if (adjust_prev)
	      ((LST_BUCKET *)prev_bucket)->next_lst_bucket = ((LST_BUCKET *)curr_bucket)->next_lst_bucket;
	    ((LST_BUCKET *)curr_bucket)->next_lst_bucket = local_state_table[table_no][list_index];
	    local_state_table[table_no][list_index] = (LST_BUCKET *)curr_bucket;
	    break;
	  case GST:
	    if (adjust_prev)
	      ((GST_BUCKET *)prev_bucket)->next_gst_bucket = ((GST_BUCKET *)curr_bucket)->next_gst_bucket;
	    ((GST_BUCKET *)curr_bucket)->next_gst_bucket = global_state_table[table_no][list_index];
	    global_state_table[table_no][list_index] = (GST_BUCKET *)curr_bucket;
	    break;
	}
}
