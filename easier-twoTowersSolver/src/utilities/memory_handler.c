/****************************************************************/
/*                                                              */
/*                                                              */
/*                      memory_handler.c			*/
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module handles the dynamic memory allocation and deallocation. */
/* This module contains the following functions: */
/* - alloc_string(): It allocates a memory block for a string. */
/* - alloc_st_bucket_cell(): It allocates a memory block for a symbol table bucket cell. */
/* - alloc_st_bucket2_cell(): It allocates a memory block for a double symbol table bucket cell. */
/* - alloc_st_bucket3_cell(): It allocates a memory block for a triple symbol table bucket cell. */
/* - alloc_st_bucket4_cell(): It allocates a memory block for a quadruple symbol table bucket cell. */
/* - alloc_st_bucket(): It allocates a symbol table bucket for a lexeme. */
/* - alloc_dummy_st_bucket(): It allocates a dummy symbol table bucket for the subtype of a structured */
/*			      data type. */
/* - alloc_archi_type(): It allocates a memory block for an architectural type. */
/* - alloc_aet(): It allocates a memory block for an AET. */
/* - alloc_aei(): It allocates a memory block for an AEI. */
/* - alloc_behavior(): It allocates a memory block for a behavior. */
/* - alloc_act_type(): It allocates a memory block for an action type. */
/* - alloc_yb_cell(): It allocates a memory block for a yield-bonus cell. */
/* - alloc_sample_cell(): It allocates a memory block for a sample cell. */
/* - alloc_sample(): It allocates a memory block for a sample. */
/* - alloc_par_behav_invoc(): It allocates a memory block for a parametrized behavior invocation. */
/* - alloc_expr(): It allocates a memory block for an expression. */
/* - alloc_value_cell(): It allocates a memory block for a value cell. */
/* - alloc_action(): It allocates a memory block for an action. */
/* - alloc_aggr_g_trans(): It allocates a memory block for an aggregated global transition. */
/* - alloc_part_block_cell(): It allocates a memory block for a partition block cell. */
/* - alloc_part_block(): It allocates a memory block for a partition block. */
/* - alloc_g_state_cell(): It allocates a memory block for a global state cell. */
/* - alloc_aggr_rate_cell(): It allocates a memory block for an aggregated rate cell. */
/* - alloc_inequiv_gs_cell(): It allocates a memory block for an inequivalent global state cell. */
/* - alloc_rew_measure(): It allocates a memory block for a reward measure. */
/* - alloc_sim_measure(): It allocates a memory block for a simulation measure. */
/* - alloc_term_parse_info(): It allocates a memory block for term parse information. */
/* - alloc_action_parse_info(): It allocates a memory block for action parse information. */
/* - alloc_expr_parse_info(): It allocates a memory block for expression parse information.  */
/* - alloc_lar_parse_info(): It allocates a memory block for expression list/array/record parse */
/*			     information. */
/* - alloc_dtt_bucket(): It allocates a memory block for a data type table bucket. */
/* - alloc_cdt_bucket(): It allocates a memory block for a code table bucket. */
/* - alloc_lst_bucket_cell(): It allocates a memeory block for a local state table bucket cell. */
/* - alloc_lst_bucket(): It allocates a memeory block for a local state table bucket. */
/* - alloc_l_trans_cell(): It allocates a memory block for a local transition cell. */
/* - alloc_l_act_type_cell(): It allocates a memory block for a local action type cell. */
/* - alloc_l_nondet_cell(): It allocates a memory block for a local nondeterminism cell. */
/* - alloc_gst_bucket_cell(): It allocates a memory block for a global state table bucket cell. */
/* - alloc_gst_bucket2_cell(): It allocates a memory block for a double global state table bucket cell. */
/* - alloc_gst_bucket(): It allocates a memeory block for a global state table bucket. */
/* - alloc_g_trans_cell(): It allocates a memory block for a global transition cell. */
/* - alloc_vp_info(): It allocates a memory block for value passing information concerning a move. */
/* - alloc_random_cell(): It allocates a memory block for a random cell. */
/* - duplicate_st_bucket_list(): It duplicates a list of symbol table buckets. */
/* - duplicate_reward_info(): It duplicates the reward information. */
/* - duplicate_struct_expr(): It duplicates a structured expression. */
/* - duplicate_record_expr(): It duplicates a structured expression of type record. */
/* - free_st_bucket_list(): It deallocates a symbol table bucket cell list. */
/* - free_st_bucket3_list(): It deallocates a triple symbol table bucket cell list. */
/* - free_st_bucket4_list(): It deallocates a quadruple symbol table bucket cell list. */
/* - free_value_list(): It deallocates a value cell list. */
/* - free_l_trans_cell(): It deallocates a local transition cell. */
/* - free_gst_bucket_list(): It deallocates a global state table bucket cell list. */
/* - free_g_trans_list(): It deallocates a global transition cell list. */
/* - free_g_trans_cell(): It deallocates a global transition cell. */
/* - new_calloc(): It allocates a number of contiguous memory blocks of a certain size. */
/* and the following auxiliary function: */
/* - new_malloc(): It allocates a memory block of a certain size. */
/* - free_yb_list(): It deallocates a yield-bonus cell list. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<math.h>
#include	<stdlib.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/string_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

char		*alloc_string(int);

ST_BUCKET_CELL	*alloc_st_bucket_cell(ST_BUCKET *,
				      ST_BUCKET_CELL *);

ST_BUCKET2_CELL	*alloc_st_bucket2_cell(ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET2_CELL *);

ST_BUCKET3_CELL	*alloc_st_bucket3_cell(ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET3_CELL *);

ST_BUCKET4_CELL	*alloc_st_bucket4_cell(ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET4_CELL *);

ST_BUCKET	*alloc_st_bucket(char *),
		*alloc_dummy_st_bucket(EXPR *);

ARCHI_TYPE	*alloc_archi_type(void);

AET		*alloc_aet(ST_BUCKET_CELL *,
			   ST_BUCKET_CELL *,
			   ST_BUCKET_CELL *);

AEI		*alloc_aei(ST_BUCKET *,
			   ST_BUCKET_CELL *,
			   ST_BUCKET_CELL *,
			   ST_BUCKET_CELL *,
			   BOOLEAN);

BEHAVIOR	*alloc_behavior(ST_BUCKET_CELL *,
				ST_BUCKET_CELL *,
				ST_BUCKET_CELL *,
				BOOLEAN,
				char *);

ACT_TYPE	*alloc_act_type(ACTION_INDEX,
				ST_BUCKET_CELL *,
				ST_BUCKET *);

YB_CELL		*alloc_yb_cell(long double,
			       long double,
			       YB_CELL *);

SAMPLE_CELL	*alloc_sample_cell(SAMPLE *,
				   SAMPLE_CELL *);

SAMPLE		*alloc_sample(ST_BUCKET *);

PAR_BEHAV_INVOC	*alloc_par_behav_invoc(ST_BUCKET *,
				       ST_BUCKET_CELL *);

EXPR		*alloc_expr(EXPR_OP_INDEX,
			    ST_BUCKET *,
			    ST_BUCKET *,
			    ST_BUCKET *,
			    DTT_BUCKET *,
			    BOOLEAN,
			    long double,
			    VALUE_CELL *);

VALUE_CELL	*alloc_value_cell(ST_BUCKET *,
				  long double,
				  VALUE_CELL *,
				  VALUE_CELL *);

ACTION		*alloc_action(ST_BUCKET *,
			      ACTION_INDEX,
			      ST_BUCKET_CELL *,
			      RATE_INDEX,
			      ST_BUCKET *,
			      ST_BUCKET *);

AGGR_G_TRANS	*alloc_aggr_g_trans(PART_BLOCK *,
				    ST_BUCKET_CELL *,
				    AGGR_RATE_CELL *,
				    int);

PART_BLOCK_CELL	*alloc_part_block_cell(PART_BLOCK *,
				       PART_BLOCK_CELL *);

PART_BLOCK	*alloc_part_block(PART_BLOCK *);

G_STATE_CELL	*alloc_g_state_cell(GST_BUCKET *,
				    G_STATE_CELL *);

AGGR_RATE_CELL	*alloc_aggr_rate_cell(long double,
				      RATE_INDEX,
				      AGGR_RATE_CELL *);

INEQUIV_GS_CELL *alloc_inequiv_gs_cell(GST_BUCKET *,
				       GST_BUCKET *,
				       long double,
				       INEQUIV_GS_CELL *,
				       INEQUIV_GS_CELL *,
				       INEQUIV_GS_CELL *);

REW_MEASURE	*alloc_rew_measure(ST_BUCKET3_CELL *);

SIM_MEASURE	*alloc_sim_measure(MEASURE_INDEX,
				   ST_BUCKET *,
				   ST_BUCKET *,
				   ST_BUCKET *,
				   ST_BUCKET *);

TERM_PARSE_INFO	*alloc_term_parse_info(char *,
				       ST_BUCKET_CELL *,
				       ST_BUCKET_CELL *);

ACT_PARSE_INFO	*alloc_action_parse_info(ST_BUCKET *,
				         ST_BUCKET_CELL *);

EXPR_PARSE_INFO	*alloc_expr_parse_info(ST_BUCKET *,
				       ST_BUCKET_CELL *);

LAR_PARSE_INFO	*alloc_lar_parse_info(VALUE_CELL *,
				      int,
				      ST_BUCKET_CELL *);

DTT_BUCKET	*alloc_dtt_bucket(char *);

CDT_BUCKET	*alloc_cdt_bucket(char *);

LST_BUCKET_CELL	*alloc_lst_bucket_cell(LST_BUCKET *,
				       LST_BUCKET_CELL *);

LST_BUCKET	*alloc_lst_bucket(char *,
				  ST_BUCKET_CELL *);

L_TRANS_CELL	*alloc_l_trans_cell(ST_BUCKET *,
				    VP_INFO *,
				    REWARD_INFO,
				    LST_BUCKET *,
				    INTERACT_INDEX,
				    L_TRANS_CELL *);

L_ACT_TYPE_CELL	*alloc_l_act_type_cell(ST_BUCKET *,
				       ST_BUCKET *,
				       int,
				       int,
				       BOOLEAN,
				       L_ACT_TYPE_CELL *);

L_NONDET_CELL	*alloc_l_nondet_cell(LST_BUCKET *,
				     LST_BUCKET_CELL *,
				     L_NONDET_CELL *);

GST_BUCKET_CELL	*alloc_gst_bucket_cell(GST_BUCKET *,
				       GST_BUCKET_CELL *);

GST_BUCKT2_CELL	*alloc_gst_bucket2_cell(GST_BUCKET *,
					GST_BUCKET *,
				        GST_BUCKT2_CELL *);

GST_BUCKET	*alloc_gst_bucket(LST_BUCKET **);

G_TRANS_CELL	*alloc_g_trans_cell(ST_BUCKET *,
				    VP_INFO *,
				    REWARD_INFO,
				    GST_BUCKET *,
				    G_TRANS_CELL *);

VP_INFO		*alloc_vp_info(ST_BUCKET *,
			       ST_BUCKET_CELL *,
			       int);

RANDOM_CELL	*alloc_random_cell(G_TRANS_CELL *,
				   long double,
				   RANDOM_CELL *);

ST_BUCKET_CELL	*duplicate_st_bucket_list(ST_BUCKET_CELL *);

REWARD_INFO	duplicate_reward_info(REWARD_INFO);

VALUE_CELL      *duplicate_struct_expr(VALUE_CELL *,
                                       char),
		*duplicate_record_expr(VALUE_CELL *);

void		free_st_bucket_list(ST_BUCKET_CELL *),
		free_st_bucket3_list(ST_BUCKET3_CELL *),
		free_st_bucket4_list(ST_BUCKET4_CELL *),
		free_value_list(VALUE_CELL *,
				EXPR *,
				int),
		free_l_trans_cell(L_TRANS_CELL *),
		free_gst_bucket_list(GST_BUCKET_CELL *),
		free_g_trans_list(G_TRANS_CELL *),
		free_g_trans_cell(G_TRANS_CELL *),
		*new_calloc(int,
			    unsigned);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	void		*new_malloc(unsigned),
			free_yb_list(YB_CELL *);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

char		*alloc_string(int length)
{
	return((char *)new_malloc(sizeof(char) * (length + 1)));
}


ST_BUCKET_CELL	*alloc_st_bucket_cell(ST_BUCKET *st_bucket,
				      ST_BUCKET_CELL *next_st_bucket_cell)
{
	ST_BUCKET_CELL	*st_bucket_cell;

	st_bucket_cell = (ST_BUCKET_CELL *)new_malloc(sizeof(ST_BUCKET_CELL));
	st_bucket_cell->st_bucket = st_bucket;
	st_bucket_cell->next_st_bucket_cell = next_st_bucket_cell;
	return(st_bucket_cell);
}


ST_BUCKET2_CELL	*alloc_st_bucket2_cell(ST_BUCKET       *st_bucket1,
				       ST_BUCKET       *st_bucket2,
				       ST_BUCKET2_CELL *next_st_bucket2_cell)
{
	ST_BUCKET2_CELL	*st_bucket2_cell;

	st_bucket2_cell = (ST_BUCKET2_CELL *)new_malloc(sizeof(ST_BUCKET2_CELL));
	st_bucket2_cell->st_bucket1 = st_bucket1;
	st_bucket2_cell->st_bucket2 = st_bucket2;
	st_bucket2_cell->next_st_bucket2_cell = next_st_bucket2_cell;
	return(st_bucket2_cell);
}


ST_BUCKET3_CELL	*alloc_st_bucket3_cell(ST_BUCKET       *st_bucket1,
				       ST_BUCKET       *st_bucket2,
				       ST_BUCKET       *st_bucket3,
				       ST_BUCKET3_CELL *next_st_bucket3_cell)
{
	ST_BUCKET3_CELL	*st_bucket3_cell;

	st_bucket3_cell = (ST_BUCKET3_CELL *)new_malloc(sizeof(ST_BUCKET3_CELL));
	st_bucket3_cell->st_bucket1 = st_bucket1;
	st_bucket3_cell->st_bucket2 = st_bucket2;
	st_bucket3_cell->st_bucket3 = st_bucket3;
	st_bucket3_cell->next_st_bucket3_cell = next_st_bucket3_cell;
	return(st_bucket3_cell);
}


ST_BUCKET4_CELL	*alloc_st_bucket4_cell(ST_BUCKET       *st_bucket1,
				       ST_BUCKET       *st_bucket2,
				       ST_BUCKET       *st_bucket3,
				       ST_BUCKET       *st_bucket4,
				       ST_BUCKET4_CELL *next_st_bucket4_cell)
{
	ST_BUCKET4_CELL	*st_bucket4_cell;

	st_bucket4_cell = (ST_BUCKET4_CELL *)new_malloc(sizeof(ST_BUCKET4_CELL));
	st_bucket4_cell->st_bucket1 = st_bucket1;
	st_bucket4_cell->st_bucket2 = st_bucket2;
	st_bucket4_cell->st_bucket3 = st_bucket3;
	st_bucket4_cell->st_bucket4 = st_bucket4;
	st_bucket4_cell->next_st_bucket4_cell = next_st_bucket4_cell;
	return(st_bucket4_cell);
}


ST_BUCKET	*alloc_st_bucket(char *symbol_lexeme)
{
	ST_BUCKET	*st_bucket;

	st_bucket = (ST_BUCKET *)new_malloc(sizeof(ST_BUCKET));
	st_bucket->symbol_lexeme = dupl_string(symbol_lexeme);
	st_bucket->symbol_index = ID;
	st_bucket->defined = FALSE;
	st_bucket->used = FALSE;
	st_bucket->info.archi_type = NULL;
	st_bucket->code = NULL;
	st_bucket->next_st_bucket = NULL;
	return(st_bucket);
}


ST_BUCKET	*alloc_dummy_st_bucket(EXPR *expr)
{
	ST_BUCKET	*st_bucket;

	st_bucket = (ST_BUCKET *)new_malloc(sizeof(ST_BUCKET));
	st_bucket->symbol_lexeme = NULL;
	st_bucket->symbol_index = ID;
	st_bucket->defined = FALSE;
	st_bucket->used = FALSE;
	st_bucket->info.expr = expr;
	st_bucket->next_st_bucket = NULL;
	return(st_bucket);
}


ARCHI_TYPE	*alloc_archi_type(void)
{
	ARCHI_TYPE	*archi_type;

	archi_type = (ARCHI_TYPE *)new_malloc(sizeof(ARCHI_TYPE));
	archi_type->formal_const_par_list = NULL;
	archi_type->init_assign_list = NULL;
	archi_type->aet_list = NULL;
	archi_type->aei_list = NULL;
	archi_type->ai_list = NULL;
	archi_type->aei_num = 0;
	archi_type->value_passing = (char)NO_VP;
	archi_type->performance_closed = (char)TRUE;
	archi_type->psm_index = (char)NO_PSM_INDEX;
	archi_type->init_global_state = NULL;
	archi_type->state_num = 0;
	archi_type->vanishing_state_num = 0;
	archi_type->open_state_num = 0;
	archi_type->absorbing_state_num = 0;
	archi_type->trans_num = 0;
	archi_type->invisible_trans_num = 0;
	archi_type->immediate_trans_num = 0;
	archi_type->passive_trans_num = 0;
	return(archi_type);
}


AET		*alloc_aet(ST_BUCKET_CELL *formal_const_par_list,
			   ST_BUCKET_CELL *behavior_list,
			   ST_BUCKET_CELL *act_type_list)
{
	AET		*aet;

	aet = (AET *)new_malloc(sizeof(AET));
	aet->formal_const_par_list = formal_const_par_list;
	aet->behavior_list = behavior_list;
	aet->act_type_list = act_type_list;
	return(aet);
}


AEI		*alloc_aei(ST_BUCKET      *aet,
			   ST_BUCKET_CELL *formal_const_par_list,
			   ST_BUCKET_CELL *behavior_list,
			   ST_BUCKET_CELL *act_type_list,
			   BOOLEAN        symbolic)
{
	AEI		*aei;

	aei = (AEI *)new_malloc(sizeof(AEI));
	aei->aet = aet;
	aei->aei_no = (symbolic)?
			0:
			++archi_type[spec_no]->info.archi_type->aei_num;
	aei->formal_const_par_list = formal_const_par_list;
	aei->behavior_list = behavior_list;
	aei->act_type_list = act_type_list;
	aei->sync_set = NULL;
	aei->init_state_var_assign_list = NULL;
	aei->init_local_state = NULL;
	aei->local_state_num = 0;
	aei->local_act_type_list = NULL;
	aei->composed_act_type_list = NULL;
	return(aei);
}


BEHAVIOR	*alloc_behavior(ST_BUCKET_CELL *formal_var_par_list,
				ST_BUCKET_CELL *actual_var_par_list,
				ST_BUCKET_CELL *local_var_list,
				BOOLEAN        init_behav,
				char           *state_lexeme)
{
	BEHAVIOR	*behavior;

	behavior = (BEHAVIOR *)new_malloc(sizeof(BEHAVIOR));
	behavior->formal_var_par_list = formal_var_par_list;
	behavior->actual_var_par_list = actual_var_par_list;
	behavior->local_var_list = local_var_list;
	behavior->invoked_behavior_list = NULL;
	behavior->visited = (char)FALSE;
	behavior->init_behav = (char)init_behav;
	behavior->state_lexeme = state_lexeme;
	return(behavior);
}


ACT_TYPE	*alloc_act_type(ACTION_INDEX   act_type_index,
				ST_BUCKET_CELL *par_list,
				ST_BUCKET      *aei)
{
	ACT_TYPE	*act_type;

	act_type = (ACT_TYPE *)new_malloc(sizeof(ACT_TYPE));
	act_type->act_type_index = (char)act_type_index;
	act_type->par_list = par_list;
	act_type->aei = aei;
	act_type->rate_index = NO_RATE;
	act_type->priority = NULL;
	act_type->interaction_index = (char)NO_INTERACTION;
	act_type->architectural = (char)FALSE;
	act_type->attached_interaction_list = NULL;
	act_type->duplicate_list = NULL;
	act_type->variation = (char)UNVARIED;
	act_type->renamed = NULL;
	act_type->reward_info.yb_list = NULL;
	act_type->sim_clock_act_type = (char)FALSE;
	act_type->security_level = (char)UNDEF_LEVEL;
	return(act_type);
}


YB_CELL		*alloc_yb_cell(long double yield,
			       long double bonus,
			       YB_CELL     *next_yb_cell)
{
	YB_CELL		*yb_cell;

	yb_cell = (YB_CELL *)new_malloc(sizeof(YB_CELL));
	yb_cell->yield = yield;
	yb_cell->bonus = bonus;
	yb_cell->next_yb_cell = next_yb_cell;
	return(yb_cell);
}


SAMPLE_CELL	*alloc_sample_cell(SAMPLE      *sample,
				   SAMPLE_CELL *next_sample_cell)
{
	SAMPLE_CELL	*sample_cell;

	sample_cell = (SAMPLE_CELL *)new_malloc(sizeof(SAMPLE_CELL));
	sample_cell->sample = sample;
	sample_cell->next_sample_cell = next_sample_cell;
	return(sample_cell);
}


SAMPLE		*alloc_sample(ST_BUCKET *reward_expr)
{
	SAMPLE		*sample;
	int		i;

	sample = (SAMPLE *)new_malloc(sizeof(SAMPLE));
	sample->reward_expr = reward_expr;
	for (i = 0;
	     (i < MAX_SIM_RUN_NUM);
	     i++)
	{
	  sample->cumul_reward[i] = 0.0L;
	  sample->obs_num[i] = 0;
	}
	return(sample);
}


PAR_BEHAV_INVOC	*alloc_par_behav_invoc(ST_BUCKET      *behavior,
				       ST_BUCKET_CELL *actual_par_list)
{
	PAR_BEHAV_INVOC	*par_behav_invoc;

	par_behav_invoc = (PAR_BEHAV_INVOC *)new_malloc(sizeof(PAR_BEHAV_INVOC));
	par_behav_invoc->behavior = behavior;
	par_behav_invoc->actual_par_list = actual_par_list;
	return(par_behav_invoc);
}


EXPR		*alloc_expr(EXPR_OP_INDEX op_index,
			    ST_BUCKET     *opn1,
			    ST_BUCKET     *opn2,
			    ST_BUCKET     *opn3,
			    DTT_BUCKET    *data_type,
			    BOOLEAN       illegal_use_cumul,
			    long double   value,
			    VALUE_CELL    *struct_value)
{
	EXPR		*expr;

	expr = (EXPR *)new_malloc(sizeof(EXPR));
	expr->op_index = (char)op_index;
	expr->opn1 = opn1;
	expr->opn2 = opn2;
	expr->opn3 = opn3;
	expr->data_type = data_type;
	expr->illegal_use_cumul = (char)illegal_use_cumul;
	expr->value = value;
	expr->struct_value = struct_value;
	expr->sim_run_num = -1;
	expr->assign_list_eval_num = 0;
	expr->sample = NULL;
	return(expr);
}


VALUE_CELL	*alloc_value_cell(ST_BUCKET   *value_bucket,
				  long double value,
				  VALUE_CELL  *struct_value,
				  VALUE_CELL  *next_value_cell)
{
	VALUE_CELL	*value_cell;

	value_cell = (VALUE_CELL *)new_malloc(sizeof(VALUE_CELL));
	value_cell->value_bucket = value_bucket;
	value_cell->value = value;
	value_cell->struct_value = struct_value;
	value_cell->next_value_cell = next_value_cell;
	return(value_cell);
}


ACTION		*alloc_action(ST_BUCKET      *type,
			      ACTION_INDEX   action_index,
			      ST_BUCKET_CELL *par_list,
			      RATE_INDEX     rate_index,
			      ST_BUCKET      *priority,
			      ST_BUCKET      *rate)
{
	ACTION		*action;

	action = (ACTION *)new_malloc(sizeof(ACTION));
	action->type = type;
	action->action_index = action_index;
	action->par_list = par_list;
	action->rate_index = rate_index;
	action->priority = priority;
	action->rate = rate;
	return(action);
}


AGGR_G_TRANS	*alloc_aggr_g_trans(PART_BLOCK     *part_block,
				    ST_BUCKET_CELL *act_type_list,
				    AGGR_RATE_CELL *aggr_rate_list,
				    int            refinement_phase)
{
	AGGR_G_TRANS	*aggr_global_trans;

	aggr_global_trans = (AGGR_G_TRANS *)new_malloc(sizeof(AGGR_G_TRANS));
	aggr_global_trans->part_block = part_block;
	aggr_global_trans->act_type_list = act_type_list;
	aggr_global_trans->aggr_rate_list = aggr_rate_list;
	aggr_global_trans->refinement_phase = refinement_phase;
	return(aggr_global_trans);
}


PART_BLOCK_CELL	*alloc_part_block_cell(PART_BLOCK      *part_block,
				       PART_BLOCK_CELL *next_part_block_cell)
{
	PART_BLOCK_CELL	*part_block_cell;

	part_block_cell = (PART_BLOCK_CELL *)new_malloc(sizeof(PART_BLOCK_CELL));
	part_block_cell->part_block = part_block;
	part_block_cell->next_part_block_cell = next_part_block_cell;
	return(part_block_cell);
}


PART_BLOCK	*alloc_part_block(PART_BLOCK *father_part_block)
{
	PART_BLOCK	*part_block;

	part_block = (PART_BLOCK *)new_malloc(sizeof(PART_BLOCK));
	part_block->global_state_list = NULL;
	part_block->father_part_block = father_part_block;
	part_block->refining_part_block = NULL;
	part_block->children_part_block_list = NULL;
	part_block->aggr_global_trans_list = NULL;
	return(part_block);
}


G_STATE_CELL	*alloc_g_state_cell(GST_BUCKET   *global_state,
				    G_STATE_CELL *next_g_state_cell)
{
	G_STATE_CELL	*global_state_cell;

	global_state_cell = (G_STATE_CELL *)new_malloc(sizeof(G_STATE_CELL));
	global_state_cell->global_state = global_state;
	global_state_cell->prev_g_state_cell = NULL;
	global_state_cell->next_g_state_cell = next_g_state_cell;
	if (next_g_state_cell != NULL)
	  next_g_state_cell->prev_g_state_cell = global_state_cell;
	return(global_state_cell);
}


AGGR_RATE_CELL	*alloc_aggr_rate_cell(long double    aggr_rate,
				      RATE_INDEX     rate_index,
				      AGGR_RATE_CELL *next_aggr_rate_cell)
{
	AGGR_RATE_CELL	*aggr_rate_cell;

	aggr_rate_cell = (AGGR_RATE_CELL *)new_malloc(sizeof(AGGR_RATE_CELL));
	aggr_rate_cell->aggr_rate = aggr_rate;
	aggr_rate_cell->rate_index = (char)rate_index;
	aggr_rate_cell->next_aggr_rate_cell = next_aggr_rate_cell;
	return(aggr_rate_cell);
}


INEQUIV_GS_CELL *alloc_inequiv_gs_cell(GST_BUCKET      *outer_global_state,
				       GST_BUCKET      *inner_global_state,
				       long double     outer_incoming_rate,
				       INEQUIV_GS_CELL *father_inequiv_gs_cell,
				       INEQUIV_GS_CELL *prev_inequiv_gs_cell,
				       INEQUIV_GS_CELL *next_inequiv_gs_cell)
{
	INEQUIV_GS_CELL	*inequiv_gs_cell;

	inequiv_gs_cell = (INEQUIV_GS_CELL *)new_malloc(sizeof(INEQUIV_GS_CELL));
	inequiv_gs_cell->outer_global_state = outer_global_state;
	inequiv_gs_cell->inner_global_state = inner_global_state;
	inequiv_gs_cell->disting_act_type = NULL;
	inequiv_gs_cell->disting_aggr_rate = 0.0L;
	inequiv_gs_cell->outer_incoming_rate = outer_incoming_rate;
	inequiv_gs_cell->disting_rate_index = (char)NO_RATE;
	inequiv_gs_cell->left = (char)TRUE;
	inequiv_gs_cell->curr_outer_reached_global_state = NULL;
	inequiv_gs_cell->disting_sub_formula_num = 0;
	inequiv_gs_cell->min_disting_sub_formula_num = -1;
	inequiv_gs_cell->disting_sub_formula_list = NULL;
	inequiv_gs_cell->min_disting_sub_formula_list = NULL;
	inequiv_gs_cell->father_inequiv_gs_cell = father_inequiv_gs_cell;
	inequiv_gs_cell->prev_inequiv_gs_cell = prev_inequiv_gs_cell;
	inequiv_gs_cell->next_inequiv_gs_cell = next_inequiv_gs_cell;
	return(inequiv_gs_cell);
}


REW_MEASURE	*alloc_rew_measure(ST_BUCKET3_CELL *type_yb_list)
{
	REW_MEASURE	*rew_measure;

	rew_measure = (REW_MEASURE *)new_malloc(sizeof(REW_MEASURE));
	rew_measure->type_yb_list = type_yb_list;
	rew_measure->value = 0.0L;
	return(rew_measure);
}


SIM_MEASURE	*alloc_sim_measure(MEASURE_INDEX measure_index,
				   ST_BUCKET     *measure_expr,
				   ST_BUCKET     *interval_begin,
				   ST_BUCKET     *interval_end,
				   ST_BUCKET     *sub_interval_width)
{
	SIM_MEASURE	*sim_measure;
	int		obs_num,
			i,
			j;
	long double	*value_ptr;

	sim_measure = (SIM_MEASURE *)new_malloc(sizeof(SIM_MEASURE));
	sim_measure->measure_index = measure_index;
	sim_measure->measure_expr = measure_expr;
	sim_measure->interval_begin = interval_begin;
	sim_measure->interval_end = interval_end;
	sim_measure->sub_interval_width = sub_interval_width;
	if ((measure_index == M_DISTRIBUTION) &&
	    (sub_interval_width->info.expr->value != 0.0L))
	{
	  obs_num = (int)ceil((double)((interval_end->info.expr->value - interval_begin->info.expr->value) /
					sub_interval_width->info.expr->value));
	  for (i = 0;
	       (i < MAX_SIM_RUN_NUM);
	       i++)
	  {
	    sim_measure->value[i] = (long double *)new_calloc(obs_num,
							      sizeof(long double));
	    for (value_ptr = sim_measure->value[i],
		 j = 0;
		 (j < obs_num);
		 value_ptr++,
		 j++)
	      *value_ptr = 0.0L;
	  }
	}
	return(sim_measure);
}


TERM_PARSE_INFO	*alloc_term_parse_info(char           *state_lexeme,
				       ST_BUCKET_CELL *actual_par_list,
				       ST_BUCKET_CELL *crit_local_var_list)
{
	TERM_PARSE_INFO	*term_parse_info;

	term_parse_info = (TERM_PARSE_INFO *)new_malloc(sizeof(TERM_PARSE_INFO));
	term_parse_info->state_lexeme = state_lexeme;
	term_parse_info->actual_par_list = actual_par_list;
	term_parse_info->crit_local_var_list = crit_local_var_list;
	return(term_parse_info);
}


ACT_PARSE_INFO	*alloc_action_parse_info(ST_BUCKET      *action,
				         ST_BUCKET_CELL *local_var_list)
{
	ACT_PARSE_INFO	*action_parse_info;

	action_parse_info = (ACT_PARSE_INFO *)new_malloc(sizeof(ACT_PARSE_INFO));
	action_parse_info->action = action;
	action_parse_info->local_var_list = local_var_list;
	return(action_parse_info);
}


EXPR_PARSE_INFO	*alloc_expr_parse_info(ST_BUCKET      *expr,
				       ST_BUCKET_CELL *local_var_list)
{
	EXPR_PARSE_INFO	*expr_parse_info;

	expr_parse_info = (EXPR_PARSE_INFO *)new_malloc(sizeof(EXPR_PARSE_INFO));
	expr_parse_info->expr = expr;
	expr_parse_info->local_var_list = local_var_list;
	return(expr_parse_info);
}


LAR_PARSE_INFO	*alloc_lar_parse_info(VALUE_CELL     *struct_value,
				      int            value_num,
				      ST_BUCKET_CELL *local_var_list)
{
	LAR_PARSE_INFO	*lar_parse_info;

	lar_parse_info = (LAR_PARSE_INFO *)new_malloc(sizeof(LAR_PARSE_INFO));
	lar_parse_info->struct_value = struct_value;
	lar_parse_info->value_num = value_num;
	lar_parse_info->local_var_list = local_var_list;
	return(lar_parse_info);
}


DTT_BUCKET	*alloc_dtt_bucket(char *data_type_lexeme)
{
	DTT_BUCKET	*dtt_bucket;

	dtt_bucket = (DTT_BUCKET *)new_malloc(sizeof(DTT_BUCKET));
	dtt_bucket->data_type_lexeme = dupl_string(data_type_lexeme);
	dtt_bucket->next_dtt_bucket = NULL;
	return(dtt_bucket);
}


CDT_BUCKET	*alloc_cdt_bucket(char *code_lexeme)
{
	CDT_BUCKET	*cdt_bucket;

	cdt_bucket = (CDT_BUCKET *)new_malloc(sizeof(CDT_BUCKET));
	cdt_bucket->code_lexeme = dupl_string(code_lexeme);
	cdt_bucket->encoded_local_state_comp = NULL;
	cdt_bucket->next_cdt_bucket = NULL;
	return(cdt_bucket);
}


LST_BUCKET_CELL	*alloc_lst_bucket_cell(LST_BUCKET      *lst_bucket,
				       LST_BUCKET_CELL *next_lst_bucket_cell)
{
	LST_BUCKET_CELL	*lst_bucket_cell;

	lst_bucket_cell = (LST_BUCKET_CELL *)new_malloc(sizeof(LST_BUCKET_CELL));
	lst_bucket_cell->lst_bucket = lst_bucket;
	lst_bucket_cell->next_lst_bucket_cell = next_lst_bucket_cell;
	return(lst_bucket_cell);
}


LST_BUCKET	*alloc_lst_bucket(char           *local_state_lexeme,
				  ST_BUCKET_CELL *state_var_assign_list)
{
	LST_BUCKET	*lst_bucket;

	lst_bucket = (LST_BUCKET *)new_malloc(sizeof(LST_BUCKET));
	lst_bucket->local_state_lexeme = dupl_string(local_state_lexeme);
	lst_bucket->local_state_no = 0;
	lst_bucket->state_var_assign_list = state_var_assign_list;
	lst_bucket->local_trans_list = NULL;
	lst_bucket->local_trans_considered = FALSE;
	lst_bucket->next_lst_bucket = NULL;
	return(lst_bucket);
}


L_TRANS_CELL	*alloc_l_trans_cell(ST_BUCKET      *action,
				    VP_INFO        *value_passing_info,
				    REWARD_INFO    reward_info,
				    LST_BUCKET     *der_local_state,
				    INTERACT_INDEX or_marked,
				    L_TRANS_CELL   *next_l_trans_cell)
{
	L_TRANS_CELL	*l_trans_cell;

	l_trans_cell = (L_TRANS_CELL *)new_malloc(sizeof(L_TRANS_CELL));
	l_trans_cell->action = action;
	l_trans_cell->value_passing_info = value_passing_info;
	l_trans_cell->reward_info = reward_info;
	l_trans_cell->der_local_state = der_local_state;
	l_trans_cell->or_marked = (char)or_marked;
	l_trans_cell->next_l_trans_cell = next_l_trans_cell;
	return(l_trans_cell);
}


L_ACT_TYPE_CELL	*alloc_l_act_type_cell(ST_BUCKET       *local_act_type,
				       ST_BUCKET       *basic_local_act_type,
				       int	       priority,
				       int	       passive_or_priority,
				       BOOLEAN	       synchronization,
				       L_ACT_TYPE_CELL *next_l_act_type_cell)
{
	L_ACT_TYPE_CELL	*l_act_type_cell;

	l_act_type_cell = (L_ACT_TYPE_CELL *)new_malloc(sizeof(L_ACT_TYPE_CELL));
	l_act_type_cell->local_act_type = local_act_type;
	l_act_type_cell->basic_local_act_type = basic_local_act_type;
	l_act_type_cell->priority = priority;
	l_act_type_cell->passive_or_priority = passive_or_priority;
	l_act_type_cell->synchronization = (char)synchronization;
	l_act_type_cell->enabling_local_state_list = NULL;
	l_act_type_cell->local_nondet_list = NULL;
	l_act_type_cell->next_l_act_type_cell = next_l_act_type_cell;
	return(l_act_type_cell);
}


L_NONDET_CELL	*alloc_l_nondet_cell(LST_BUCKET      *enabling_local_state,
				     LST_BUCKET_CELL *reached_local_state_list,
				     L_NONDET_CELL   *next_l_nondet_cell)
{
	L_NONDET_CELL	*l_nondet_cell;

	l_nondet_cell = (L_NONDET_CELL *)new_malloc(sizeof(L_NONDET_CELL));
	l_nondet_cell->enabling_local_state = enabling_local_state;
	l_nondet_cell->reached_local_state_list = reached_local_state_list;
	l_nondet_cell->next_l_nondet_cell = next_l_nondet_cell;
	return(l_nondet_cell);
}


GST_BUCKET_CELL	*alloc_gst_bucket_cell(GST_BUCKET      *gst_bucket,
				       GST_BUCKET_CELL *next_gst_bucket_cell)
{
	GST_BUCKET_CELL	*gst_bucket_cell;

	gst_bucket_cell = (GST_BUCKET_CELL *)new_malloc(sizeof(GST_BUCKET_CELL));
	gst_bucket_cell->gst_bucket = gst_bucket;
	gst_bucket_cell->next_gst_bucket_cell = next_gst_bucket_cell;
	return(gst_bucket_cell);
}


GST_BUCKT2_CELL	*alloc_gst_bucket2_cell(GST_BUCKET      *gst_bucket1,
					GST_BUCKET      *gst_bucket2,
				        GST_BUCKT2_CELL *next_gst_bucket2_cell)
{
	GST_BUCKT2_CELL	*gst_bucket2_cell;

	gst_bucket2_cell = (GST_BUCKT2_CELL *)new_malloc(sizeof(GST_BUCKT2_CELL));
	gst_bucket2_cell->gst_bucket1 = gst_bucket1;
	gst_bucket2_cell->gst_bucket2 = gst_bucket2;
	gst_bucket2_cell->next_gst_bucket2_cell = next_gst_bucket2_cell;
	return(gst_bucket2_cell);
}


GST_BUCKET	*alloc_gst_bucket(LST_BUCKET **local_state_vector)
{
	GST_BUCKET	*gst_bucket;

	gst_bucket = (GST_BUCKET *)new_malloc(sizeof(GST_BUCKET));
	gst_bucket->local_state_vector = local_state_vector;
	gst_bucket->global_state_no = 0;
	gst_bucket->table_no = table_no;
	gst_bucket->global_trans_list = NULL;
	gst_bucket->global_trans_considered = (char)FALSE;
	gst_bucket->global_trans_need_norm = (char)FALSE;
	gst_bucket->reaching_global_state_list = NULL;
	gst_bucket->next_gst_bucket = NULL;
	return(gst_bucket);
}


G_TRANS_CELL	*alloc_g_trans_cell(ST_BUCKET    *action,
				    VP_INFO      *value_passing_info,
				    REWARD_INFO  reward_info,
				    GST_BUCKET   *der_global_state,
				    G_TRANS_CELL *next_g_trans_cell)
{
	G_TRANS_CELL	*g_trans_cell;

	g_trans_cell = (G_TRANS_CELL *)new_malloc(sizeof(G_TRANS_CELL));
	g_trans_cell->action = action;
	g_trans_cell->value_passing_info = value_passing_info;
	g_trans_cell->reward_info = reward_info;
	g_trans_cell->der_global_state = der_global_state;
	g_trans_cell->next_g_trans_cell = next_g_trans_cell;
	return(g_trans_cell);
}


VP_INFO		*alloc_vp_info(ST_BUCKET      *guard,
			       ST_BUCKET_CELL *assign_list,
			       int            input_output_assign_num)
{
	VP_INFO		*vp_info;

	vp_info = (VP_INFO *)new_malloc(sizeof(VP_INFO));
	vp_info->guard = guard;
	vp_info->assign_list = assign_list;
	vp_info->input_output_assign_num = input_output_assign_num;
	return(vp_info);
}


RANDOM_CELL	*alloc_random_cell(G_TRANS_CELL *global_transition,
				   long double  duration,
				   RANDOM_CELL  *next_random_cell)
{
	RANDOM_CELL	*random_cell;

	random_cell = (RANDOM_CELL *)new_malloc(sizeof(RANDOM_CELL));
	random_cell->global_transition = global_transition;
	random_cell->duration = duration;
	random_cell->next_random_cell = next_random_cell;
	return(random_cell);
}


ST_BUCKET_CELL	*duplicate_st_bucket_list(ST_BUCKET_CELL *old_st_bucket_list)
{
	ST_BUCKET_CELL	*new_st_bucket_list,
			*new_st_bucket_cell,
			*old_st_bucket_cell;

	for (old_st_bucket_cell = old_st_bucket_list,
	     new_st_bucket_list = new_st_bucket_cell = NULL;
	     (old_st_bucket_cell != NULL);
	     old_st_bucket_cell = old_st_bucket_cell->next_st_bucket_cell)
	  if (new_st_bucket_cell == NULL)
	    new_st_bucket_cell = new_st_bucket_list = alloc_st_bucket_cell(old_st_bucket_cell->st_bucket,
									   NULL);
	  else
	    new_st_bucket_cell = new_st_bucket_cell->next_st_bucket_cell =
	      alloc_st_bucket_cell(old_st_bucket_cell->st_bucket,
				   NULL);
	return(new_st_bucket_list);
}


REWARD_INFO	duplicate_reward_info(REWARD_INFO old_reward_info)
{
	REWARD_INFO	new_reward_info;
	YB_CELL		*new_yb_list,
			*new_yb_cell,
			*old_yb_cell;

	if ((option_index == SIMULATION) ||
	    (old_reward_info.yb_list == NULL))
	  new_reward_info = old_reward_info;
	else
	{
	  for (old_yb_cell = old_reward_info.yb_list,
	       new_yb_list = new_yb_cell = NULL;
	       (old_yb_cell != NULL);
	       old_yb_cell = old_yb_cell->next_yb_cell)
	    if (new_yb_cell == NULL)
	      new_yb_cell = new_yb_list = alloc_yb_cell(old_yb_cell->yield,
						        old_yb_cell->bonus,
						        NULL);
	    else
	      new_yb_cell = new_yb_cell->next_yb_cell = alloc_yb_cell(old_yb_cell->yield,
								      old_yb_cell->bonus,
								      NULL);
	  new_reward_info.yb_list = new_yb_list;
	}
	return(new_reward_info);
}


VALUE_CELL      *duplicate_struct_expr(VALUE_CELL *struct_value,
                                       char       struct_data_type)
{
        return((struct_value == NULL)?
                 NULL:
                 (((struct_data_type == 'l') ||
                   (struct_data_type == 'a'))?
                     alloc_value_cell(struct_value->value_bucket,
                                      struct_value->value,
                                      struct_value->struct_value,
                                      struct_value->next_value_cell):
                     duplicate_record_expr(struct_value)));
}


VALUE_CELL      *duplicate_record_expr(VALUE_CELL *old_field_list)
{
        VALUE_CELL      *old_field,
                        *new_field_list,
                        *last_new_field;

        for (old_field = old_field_list,
             new_field_list = last_new_field = NULL;
             (old_field != NULL);
             old_field = old_field->next_value_cell)
          if (new_field_list == NULL)
            last_new_field = new_field_list =
              alloc_value_cell(
		old_field->value_bucket,
                0.0L,
                ((old_field->struct_value != NULL) &&
                 (old_field->value_bucket->info.expr->data_type->data_type_lexeme[0] == 'p'))?
                  duplicate_record_expr(old_field->struct_value):
                  NULL,
                NULL);
          else
            last_new_field = last_new_field->next_value_cell =
              alloc_value_cell(
		old_field->value_bucket,
                0.0L,
                ((old_field->struct_value != NULL) &&
                 (old_field->value_bucket->info.expr->data_type->data_type_lexeme[0] == 'p'))?
                  duplicate_record_expr(old_field->struct_value):
                  NULL,
                NULL);
        return(new_field_list);
}


void		free_st_bucket_list(ST_BUCKET_CELL *st_bucket_list)
{
	ST_BUCKET_CELL	*curr_st_bucket_cell,
			*next_st_bucket_cell;

	for (curr_st_bucket_cell = st_bucket_list,
	     next_st_bucket_cell = (st_bucket_list != NULL)?
				     st_bucket_list->next_st_bucket_cell:
				     NULL;
	     (curr_st_bucket_cell != NULL);
	     curr_st_bucket_cell = next_st_bucket_cell,
	     next_st_bucket_cell = (next_st_bucket_cell != NULL)?
				     next_st_bucket_cell->next_st_bucket_cell:
				     NULL)
	  free(curr_st_bucket_cell);
}


void		free_st_bucket3_list(ST_BUCKET3_CELL *st_bucket3_list)
{
	ST_BUCKET3_CELL	*curr_st_bucket3_cell,
			*next_st_bucket3_cell;

	for (curr_st_bucket3_cell = st_bucket3_list,
	     next_st_bucket3_cell = (st_bucket3_list != NULL)?
				      st_bucket3_list->next_st_bucket3_cell:
				      NULL;
	     (curr_st_bucket3_cell != NULL);
	     curr_st_bucket3_cell = next_st_bucket3_cell,
	     next_st_bucket3_cell = (next_st_bucket3_cell != NULL)?
				      next_st_bucket3_cell->next_st_bucket3_cell:
				      NULL)
	  free(curr_st_bucket3_cell);
}


void		free_st_bucket4_list(ST_BUCKET4_CELL *st_bucket4_list)
{
	ST_BUCKET4_CELL	*curr_st_bucket4_cell,
			*next_st_bucket4_cell;

	for (curr_st_bucket4_cell = st_bucket4_list,
	     next_st_bucket4_cell = (st_bucket4_list != NULL)?
				      st_bucket4_list->next_st_bucket4_cell:
				      NULL;
	     (curr_st_bucket4_cell != NULL);
	     curr_st_bucket4_cell = next_st_bucket4_cell,
	     next_st_bucket4_cell = (next_st_bucket4_cell != NULL)?
				      next_st_bucket4_cell->next_st_bucket4_cell:
				      NULL)
	  free(curr_st_bucket4_cell);
}


void		free_value_list(VALUE_CELL *struct_value,
				EXPR       *expr,
				int        depth)
{
	VALUE_CELL	*curr_list_elem,
			*next_list_elem,
			**array_elem,
			**aux_array_elem,
			*curr_record_field,
			*next_record_field;
	int		array_length,
			array_index;

	if (struct_value != NULL)
	  switch (expr->data_type->data_type_lexeme[0])
	  {
	    case 'l':
	      for (curr_list_elem = struct_value,
	           next_list_elem = (struct_value != NULL)?
			              struct_value->next_value_cell:
			              NULL;
	           (curr_list_elem != NULL);
	           curr_list_elem = next_list_elem,
	           next_list_elem = (next_list_elem != NULL)?
			              next_list_elem->next_value_cell:
			              NULL)
	      {
	        free_value_list(curr_list_elem->struct_value,
				expr->struct_value->value_bucket->info.expr,
				depth + 1);
	        free(curr_list_elem);
	      }
	      break;
	    case 'a':
	      for (array_elem = (depth == 1)?
				  (VALUE_CELL **)(struct_value->struct_value):
				  (VALUE_CELL **)struct_value,
		   array_index = 0,
		   array_length = expr->value;
		   (array_index < array_length);
		   array_elem++,
		   array_index++)
	      {
	        free_value_list((*array_elem)->struct_value,
				expr->struct_value->value_bucket->info.expr,
				depth + 1);
	        free((*array_elem));
	        aux_array_elem = array_elem;
	        free(aux_array_elem);
	      }
	      if (depth > 1)
		free(struct_value);
	      break;
	    case 'p':
	      for (curr_record_field = struct_value,
	           next_record_field = (struct_value != NULL)?
			                 struct_value->next_value_cell:
			                 NULL;
	           (curr_record_field != NULL);
	           curr_record_field = next_record_field,
	           next_record_field = (next_record_field != NULL)?
			                 next_record_field->next_value_cell:
			                 NULL)
	      {
	        free_value_list(curr_record_field->struct_value,
				expr->struct_value->value_bucket->info.expr,
				depth + 1);
	        free(curr_record_field);
	      }
	      break;
	    default:
	      break;
	  }
}


void		free_l_trans_cell(L_TRANS_CELL *local_trans_cell)
{
	if (local_trans_cell->value_passing_info != NULL)
	{
	  free_st_bucket_list(local_trans_cell->value_passing_info->assign_list);
	  free(local_trans_cell->value_passing_info);
	}
	if (option_index != SIMULATION)
	  free_yb_list(local_trans_cell->reward_info.yb_list);
	free(local_trans_cell);
}


void		free_gst_bucket_list(GST_BUCKET_CELL *gst_bucket_list)
{
	GST_BUCKET_CELL	*curr_gst_bucket_cell,
			*next_gst_bucket_cell;

	for (curr_gst_bucket_cell = gst_bucket_list,
	     next_gst_bucket_cell = (gst_bucket_list != NULL)?
				      gst_bucket_list->next_gst_bucket_cell:
				      NULL;
	     (curr_gst_bucket_cell != NULL);
	     curr_gst_bucket_cell = next_gst_bucket_cell,
	     next_gst_bucket_cell = (next_gst_bucket_cell != NULL)?
				      next_gst_bucket_cell->next_gst_bucket_cell:
				      NULL)
	  free(curr_gst_bucket_cell);
}


void		free_g_trans_list(G_TRANS_CELL *global_trans_list)
{
	G_TRANS_CELL	*curr_global_trans_cell,
			*next_global_trans_cell;

	for (curr_global_trans_cell = global_trans_list,
	     next_global_trans_cell = (global_trans_list != NULL)?
				        global_trans_list->next_g_trans_cell:
				        NULL;
	     (curr_global_trans_cell != NULL);
	     curr_global_trans_cell = next_global_trans_cell,
	     next_global_trans_cell = (next_global_trans_cell != NULL)?
				        next_global_trans_cell->next_g_trans_cell:
				        NULL)
	  free_g_trans_cell(curr_global_trans_cell);
}


void		free_g_trans_cell(G_TRANS_CELL *global_trans_cell)
{
	if (global_trans_cell->value_passing_info != NULL)
	{
	  free_st_bucket_list(global_trans_cell->value_passing_info->assign_list);
	  free(global_trans_cell->value_passing_info);
	}
	if (option_index != SIMULATION)
	  free_yb_list(global_trans_cell->reward_info.yb_list);
	free(global_trans_cell);
}


void		*new_calloc(int      object_num,
			    unsigned size)
{
	void		*pointer;

	pointer = (void *)calloc(object_num,
				 size);
	if (pointer == NULL)
	  signal_crash(MEM_CRASH,
		       NULL);
	return(pointer);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		*new_malloc(unsigned size)
{
	void		*pointer;

	pointer = (void *)malloc(size);
	if (pointer == NULL)
	  signal_crash(MEM_CRASH,
		       NULL);
	return(pointer);
}


void            free_yb_list(YB_CELL *yb_list)
{
        YB_CELL         *curr_yb_cell,
                        *next_yb_cell;

        for (curr_yb_cell = yb_list,
             next_yb_cell = (yb_list != NULL)?
                              yb_list->next_yb_cell:
                              NULL;
             (curr_yb_cell != NULL);
             curr_yb_cell = next_yb_cell,
             next_yb_cell = (next_yb_cell != NULL)?
                              next_yb_cell->next_yb_cell:
                              NULL)
          free(curr_yb_cell);
}
