/****************************************************************
 *								*
 *								*
 *			   Library.h				*
 *								*
 *					    (by Marco Bernardo) *
 ****************************************************************
 * This header file has to be included by all the implementation files
 * because it contains the definition of all the symbolic constants,
 * the definition of all the data types, and the inclusion of stdio.h.
 ****************************************************************/


/****************************************************************
 * 1. Inclusion of libraries.					*
 ****************************************************************/

#include	<stdio.h>					/* see ST_BUCKET_INFO below (type FILE) */


/****************************************************************
 * 2. Definition of constants.					*
 ****************************************************************/

 /* utility: private */
#define		PRIVATE				static


 /* string related constants */
#define		EOS				'\0'
#define		F_FORMAT_PRECIS_LENGTH		7
#define		E_FORMAT_LENGTH			12


 /* hash table related constants */
#define		SYT_SIZE			211
#define		DTT_SIZE			23
#define		CDT_SIZE			211
#define		LST_SIZE			100003
#define		GST_SIZE			100003
#define         HASH1                   	4
#define         HASH2                   	0xf0000000
#define         HASH3                   	24


 /* parsing related constants */
#define		MAX_TAB_NUM			20
#define		FIRST_KEYWORD_TOKEN		400		/* see SYMBOL_INDEX below */
#define		KEYWORD_NUM			155		/* see SYMBOL_INDEX below */
#define		DATA_TYPE_NUM			10		/* see data_type[] in init_table() of
								 * table-handler.c */


 /* NuSMV related constants */
#define		NUSMV_KEYWORD_NUM		80		/* see nusmv_keywords[] in
								 * check_nusmv_keywords() of
								 * aemilia-parser.y */


 /* Markovian analysis related constants */
#define		ZERO_TOL			1.0e-100L
#define		ERROR_TOL			1.0e-6L
#define		INIT_ITERATION_NUM		20
#define		MAX_ITERATION_NUM		1000
#define		INIT_RELAXATION			1.0L
#define		RELAXATION_STEP			0.05L
#define		MIN_RELAXATION			0.1L
#define		MAX_RELAXATION			1.9L
#define		RELAXATION_ADJ_TOL		1.0e-4L
#define		MAX_EXPONENT			100.0L


 /* simulation related constants */
#define		MIN_SIM_RUN_NUM			1
#define		MAX_SIM_RUN_NUM			30
#define		INIT_SEED			1.0L
#define		LCG_MULTIPLIER			16807.0L
#define		LCG_MODULUS			2147483647.0L
#define		LCG_MO_DIV_MU			127773.0L
#define		LCG_MO_MOD_MU			2836.0L
#define		ALPHA				1.64L


/****************************************************************
 * 3. Definition of enumerated types.				*
 ****************************************************************
 * The enumerated types used within the structured types defined in Sect. 4 are implicitly converted to char
 * or short int (depending on their maximum value) in order to save space.
 ****************************************************************/

 /* utility: boolean */
typedef	enum
{
	FALSE,
	TRUE
}			BOOLEAN;


 /* option index (the values must coincide with the ones used in TTGUI) */
typedef	enum
{
	PARSING					=	'a',
	ISM_SIZE				=	'b',
	FSM_SIZE				=	'c',
	PSM_SIZE				=	'd',
	ISM					=	'e',
	FSM					=	'f',
	PSM					=	'g',
	STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK	=	'h',
	WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK	=	'i',
	STRONG_MARKOVIAN_BISIM_EQUIV_CHECK	=	'j',
	WEAK_MARKOVIAN_BISIM_EQUIV_CHECK	=	'k',
	CTL_MODEL_CHECK				=	'l',
	LTL_MODEL_CHECK				=	'm',
	NON_INTERFERENCE_ANALYSIS		=	'n',
	NON_DED_ON_COMP_ANALYSIS		=	'o',
	STAT_DISTR_GAUSS_ELIM			=	'p',
	STAT_DISTR_ASSOR			=	'q',
	TRANS_DISTR_UNIF			=	'r',
	STAT_MEASURE_GAUSS_ELIM			=	's',
	STAT_MEASURE_ASSOR			=	't',
	TRANS_MEASURE_UNIF			=	'u',
	SIMULATION				=	'v'
}			OPTION_INDEX;			/* see exhaustive use in drive() and
							 * close_report() of driver.c */


 /* table index */
typedef	enum
{
	SYT,
	DTT,
	CDT,
	LST,
	GST
}			TABLE_INDEX;			/* see exhaustive use in search_table(),
							 * hash_pjw(), and move_bucket() of
							 * table-handler.c */


 /* symbol index (the first value must be greater than the last ASCII code) */
typedef	enum
{
	NUMBER				=	300,
	ID,
	ARCHI_TYPE_ID,
	AET_ID,
	AEI_ID,
	BEHAV_ID,
	ACT_TYPE_ID,
	VAR_ID,
	FORMAL_CONST_PAR_ID,
	FORMAL_VAR_PAR_ID,
	LOCAL_VAR_ID,
	REC_FIELD_ID,
	PROPERTY_ID,
	PROPERTY_VAR_ID,
	MEASURE_ID,
	TRACE_FILE_ID,
	PAR_BEHAV_INVOC_STRING,
	EXPR_STRING,
	ACTION_STRING,
	AGGR_GLOBAL_TRANS_STRING,
	ARCHITECTURAL_TYPE		=	400,
	ARCHI_ELEM_TYPES,
	ELEM_TYPE,
	BEHAVIOR_DEF,
	INPUT_INTERACTIONS,
	OUTPUT_INTERACTIONS,
	ARCHI_TOPOLOGY,
	ARCHI_ELEM_INSTANCES,
	ARCHI_INTERACTIONS,
	UNI,
	K_AND,
	K_OR,
	ARCHI_ATTACHMENTS,
	FROM,
	TO,
	BEHAV_VARIATIONS,
	BEHAV_HIDINGS,
	HIDE,
	INTERNALS,
	INTERACTIONS,
	ALL,
	BEHAV_RESTRICTIONS,
	RESTRICT,
	OBS_INTERNALS,
	OBS_INTERACTIONS,
	ALL_OBSERVABLES,
	BEHAV_RENAMINGS,
	RENAME,
	AS,
	FOR_ALL,
	IN,
	END,
	CONST,
	LOCAL,
	STOP,
	INVISIBLE,
	EXP,
	INF,
	CHOICE,
	COND,
	VOID,
	PRIO,
	RATE,
	WEIGHT,
	INTEGER,
	REAL,
	MOD,
	MIN,
	MAX,
	ABS,
	CEIL,
	FLOOR,
	POWER,
	EPOWER,
	LOGE,
	LOG10,
	SQRT,
	SIN,
	COS,
	C_UNIFORM,
	ERLANG,
	GAMMA,
	EXPONENTIAL,
	WEIBULL,
	BETA,
	NORMAL,
	PARETO,
	B_PARETO,
	D_UNIFORM,
	BERNOULLI,
	BINOMIAL,
	POISSON,
	NEG_BINOMIAL,
	GEOMETRIC,
	PASCAL,
	K_BOOLEAN,
	K_TRUE,
	K_FALSE,
	LIST,
	LIST_CONS,
	FIRST,
	TAIL,
	CONCAT,
	INSERT,
	REMOVE,
	LENGTH,
	ARRAY,
	ARRAY_CONS,
	READ,
	WRITE,
	RECORD,
	RECORD_CONS,
	GET,
	PUT,
	PROPERTY,
	IS,
	PROP_TRUE,
	PROP_FALSE,
	PROP_NOT,
	MODAL_EXISTS_TRANS,
	MODAL_EXISTS_WEAK_TRANS,
	MODAL_FOR_ALL_TRANS,
	MODAL_FOR_ALL_WEAK_TRANS,
	MODAL_EXISTS_TRANS_SET,
	MODAL_EXISTS_WEAK_TRANS_SET,
	MODAL_FOR_ALL_TRANS_SETS,
	MODAL_FOR_ALL_WEAK_TRANS_SETS,
	MODAL_LABEL,
	MODAL_MIN_AGGR_REA_PROB,
	MODAL_MIN_AGGR_EXP_RATE,
	MODAL_MIN_AGGR_GEN_PROB,
	MODAL_REACHED_STATE_SAT,
	MODAL_REACHED_STATES_SAT,
	MODAL_MIN_FIXPOINT,
	MODAL_MAX_FIXPOINT,
	MODAL_DEADLOCK_FREE,
	CTL_FOR_ALL_PATHS,
	CTL_FOR_ALL_PATHS_ALL_STATES_SAT,
	CTL_FOR_ALL_PATHS_SOME_STATE_SAT,
	CTL_EXISTS_PATH,
	CTL_EXISTS_PATH_ALL_STATES_SAT,
	CTL_EXISTS_PATH_SOME_STATE_SAT,
	CTL_STRONG_UNTIL,
	CTL_WEAK_UNTIL,
	LTL_NEXT_STATE_SAT,
	LTL_ALL_FUTURE_STATES_SAT,
	LTL_SOME_FUTURE_STATE_SAT,
	LTL_UNTIL,
	LTL_RELEASES,
	LTL_PREV_STATE_SAT,
	LTL_ALL_PAST_STATES_SAT,
	LTL_SOME_PAST_STATE_SAT,
	LTL_SINCE,
	LTL_TRIGGERED,
	HIGH_SECURITY,
	LOW_SECURITY,
	OBS_NRESTR_INTERNALS,
	OBS_NRESTR_INTERACTIONS,
	ALL_OBS_NRESTR,
	MEASURE,
	ENABLED,
	STATE_REWARD,
	TRANS_REWARD,
	RUN_LENGTH_BASED_ON,
	RUN_LENGTH,
	RUN_NUMBER,
	MEAN,
	VARIANCE,
	DISTRIBUTION,
	REWARD,
	EXECUTED,
	CUMULATIVE,
	NON_CUMULATIVE,
	DRAW,
	TRC
}			SYMBOL_INDEX;			/* see FIRST_KEYWORD_TOKEN and KEYWORD_NUM above,
							 * token definition in aemilia-parser.y,
							 * mu-parser.y, gctl-parser.y, rew-parser.y, and
							 * sim-parser.y, keyword[] in init_table() of
							 * table-handler.c, and almost exhaustive use in
							 * check_id() of aemilia-parser.y and in
							 * check_sem_error() of aemilia-checker.c */


 /* identifier context index */
typedef	enum
{
	NO_ID_CONTEXT,
	ARCHI_TYPE_ID_DEF,
	AET_ID_DEF,
	AEI_ID_DECL,
	BEHAV_ID_DEF,
	VAR_ID_DECL,
	REC_FIELD_ID_DECL,
	PROPERTY_ID_DEF,
	MEASURE_ID_DEF,
	ARCHI_TYPE_ID_USE,
	AET_ID_USE,
	AEI_ID_USE,
	BEHAV_ID_USE,
	ACT_TYPE_ID_USE,
	ACT_TYPE_ID_USE_AUX_SPEC,
	VAR_ID_USE,
	VAR_ID_USE_AUX_SPEC,
	REC_FIELD_ID_USE,
	REC_FIELD_ID_USE_AUX_SPEC,
	TRACE_FILE_ID_USE,
}			ID_CONTEXT_INDEX;		/* see almost exhaustive use in check_id() and
							 * build_var_list() of aemilia-parser.y and in
							 * set_variable_bucket() of symbol-handler.c */


 /* EMPAgr dynamic operator index */
typedef enum
{
	STOP_OP,
	ACT_PREFIX_OP,
	COND_OP,
	ALT_COMP_OP,
	BEHAV_INVOC_OP
}			EMPA_OP_INDEX;			/* see exhaustive use in handle_term() of
							 * aemilia-parser.y */


 /* action index */
typedef	enum
{
	UNSTRUCTURED,
	INPUT,
	OUTPUT
}			ACTION_INDEX;			/* see exhaustive use while parsing actions
							 * in aemilia-parser.y */


 /* rate index */
typedef	enum
{
	NO_RATE,
	PASSIVE,
	EXP_TIMED,
	IMMEDIATE
}			RATE_INDEX;			/* see exhaustive use while parsing actions
							 * in aemilia-parser.y */


 /* value passing index */
typedef	enum
{
	NO_VP,
	CONCRETE_VP,
	SYMBOLIC_VP
}			VP_INDEX;			/* see exhaustive use ??? while parsing actions
							 * in aemilia-parser.y */


 /* data expression operator index */
typedef enum
{
	NO_EXPR_OP,
	ASSIGN_OP,
	VAR_OP,
	NUMBER_OP,
	PLUS_OP,
	MINUS_OP,
	TIMES_OP,
	DIV_OP,
	MOD_OP,
	MIN_OP,
	MAX_OP,
	ABS_OP,
	CEIL_OP,
	FLOOR_OP,
	POWER_OP,
	EPOWER_OP,
	LOGE_OP,
	LOG10_OP,
	SQRT_OP,
	SIN_OP,
	COS_OP,
	C_UNIFORM_OP,
	ERLANG_OP,
	GAMMA_OP,
	EXPONENTIAL_OP,
	WEIBULL_OP,
	BETA_OP,
	NORMAL_OP,
	PARETO_OP,
	B_PARETO_OP,
	D_UNIFORM_OP,
	BERNOULLI_OP,
	BINOMIAL_OP,
	POISSON_OP,
	NEG_BINOMIAL_OP,
	GEOMETRIC_OP,
	PASCAL_OP,
	TRUE_OP,
	FALSE_OP,
	AND_OP,
	OR_OP,
	NOT_OP,
	EQ_OP,
	NE_OP,
	LT_OP,
	LE_OP,
	GT_OP,
	GE_OP,
	LIST_CONS_OP,
	FIRST_OP,
	TAIL_OP,
	CONCAT_OP,
	INSERT_OP,
	REMOVE_OP,
	LENGTH_OP,
	ARRAY_CONS_OP,
	READ_OP,
	WRITE_OP,
	RECORD_CONS_OP,
	GET_OP,
	PUT_OP,
	PROP_TRUE_OP,
	PROP_FALSE_OP,
	PROP_AND_OP,
	PROP_OR_OP,
	PROP_NOT_OP,
	PROP_XOR_OP,
	PROP_IMPL_OP,
	PROP_BI_IMPL_OP,
	MODAL_CAN_OP,
	MODAL_WEAK_CAN_OP,
	MODAL_MUST_OP,
	MODAL_WEAK_MUST_OP,
	MODAL_CAN_PROB_OP,
	MODAL_WEAK_CAN_PROB_OP,
	MODAL_MUST_PROB_OP,
	MODAL_WEAK_MUST_PROB_OP,
	MODAL_MIN_FIXPOINT_OP,
	MODAL_MAX_FIXPOINT_OP,
	CTL_FOR_ALL_PATHS_ALL_STATES_SAT_OP,
	CTL_FOR_ALL_PATHS_SOME_STATE_SAT_OP,
	CTL_FOR_ALL_PATHS_STRONG_UNTIL_OP,
	CTL_FOR_ALL_PATHS_WEAK_UNTIL_OP,
	CTL_EXISTS_PATH_ALL_STATES_SAT_OP,
	CTL_EXISTS_PATH_SOME_STATE_SAT_OP,
	CTL_EXISTS_PATH_STRONG_UNTIL_OP,
	CTL_EXISTS_PATH_WEAK_UNTIL_OP,
	LTL_NEXT_STATE_SAT_OP,
	LTL_ALL_FUTURE_STATES_SAT_OP,
	LTL_SOME_FUTURE_STATE_SAT_OP,
	LTL_UNTIL_OP,
	LTL_RELEASES_OP,
	LTL_PREV_STATE_SAT_OP,
	LTL_ALL_PAST_STATES_SAT_OP,
	LTL_SOME_PAST_STATE_SAT_OP,
	LTL_SINCE_OP,
	LTL_TRIGGERED_OP,
	SAMPLE_OP
}			EXPR_OP_INDEX;			/* see expr_op[] in build_expr_lexeme() of
							 * symbol-handler.c and almost exhaustive use in
							 * set_expr_bucket(), rewrite_expr(), and
							 * build_expr_lexeme() of symbol-handler.c and in
							 * eval_expr() of simulator.c */


 /* assignment evaluation index */
typedef	enum
{
	RIGHT_TO_ASSIGN,
	ASSIGN_TO_LEFT
}			ASSIGN_INDEX;			/* see exhaustive use in assign_expr_eval() of
							 * simulator.c */


 /* interaction index */
typedef	enum
{
	NO_INTERACTION,
	INPUT_UNI,
	INPUT_AND,
	INPUT_OR,
	OUTPUT_UNI,
	OUTPUT_AND,
	OUTPUT_OR,
	SYNC_OR,
	NO_OR,
	DUPLICATE_OR,
	HIGH_PRIORITY_PASSIVE_OR,
	LOW_PRIORITY_PASSIVE_OR
}			INTERACT_INDEX;			/* see almost exhaustive use while parsing
							 * interactions in aemilia-parser.y and in
							 * mark_passive_local_transitions() of
							 * aemilia-compiler.c */


 /* action type variation index */
typedef	enum
{
	UNVARIED,
	HIDDEN,
	RESTRICTED,
	MADE_HIGH,
	MADE_LOW
}			VARIATION_INDEX;		/* see almost exhaustive use while parsing
							 * variations in aemilia-parser.y */


 /* reward index */
typedef	enum
{
	YIELD,
	BONUS
}			REWARD_INDEX;			/* see exhaustive use in rew-parser.y */


 /* measure index */
typedef	enum
{
	M_EXPECTATION,
	M_VARIANCE,
	M_DISTRIBUTION
}			MEASURE_INDEX;			/* see exhaustive use in infer_measure() of
							 * simulator.c */


 /* action type security level index */
typedef	enum
{
	UNDEF_LEVEL,
	LOW_LEVEL,
	HIGH_LEVEL
}			SECURITY_INDEX;			/* see exhaustive use in sec-parser.y */


 /* performance semantic model index */
typedef	enum
{
	NO_PSM_INDEX,
	HSMC,
	HCTMC,
	HDTMC
}			PSM_INDEX;			/* see exhaustive use in gen_sem_model() of
							 * aemilia-compiler.c and in build_vector() and
							 * build_diag_elem() of markov-solver.c */


 /* lexical, syntax and semantical error index */
typedef	enum
{
	NO_ERROR,
	ILLEGAL_CHAR,
	NUMERIC_OVERFLOW,
	NUMERIC_UNDERFLOW,
	ARCHI_TYPE_REDEF,
	AET_REDEF,
	AEI_REDECL,
	ONE_AEI_REDECL,
	BEHAV_REDEF,
	VAR_REDECL,
	REC_FIELD_REDECL,
	PROPERTY_REDEF,
	ONE_PROPERTY_REDEF,
	MEASURE_REDEF,
	ONE_MEASURE_REDEF,
	ARCHI_TYPE_REDEF_AET,
	ARCHI_TYPE_REDEF_AEI,
	ARCHI_TYPE_REDEF_BEHAV,
	ARCHI_TYPE_REDEF_VAR,
	ARCHI_TYPE_REDEF_REC_FIELD,
	ARCHI_TYPE_REDEF_PROPERTY,
	ARCHI_TYPE_REDEF_MEASURE,
	AET_REDEF_ARCHI_TYPE,
	AET_REDEF_AEI,
	AET_REDEF_BEHAV,
	AET_REDEF_VAR,
	AET_REDEF_REC_FIELD,
	AET_REDEF_PROPERTY,
	AET_REDEF_MEASURE,
	AEI_REDECL_ARCHI_TYPE,
	AEI_REDECL_AET,
	AEI_REDECL_BEHAV,
	AEI_REDECL_VAR,
	AEI_REDECL_REC_FIELD,
	AEI_REDECL_PROPERTY,
	AEI_REDECL_MEASURE,
	BEHAV_REDEF_ARCHI_TYPE,
	BEHAV_REDEF_AET,
	BEHAV_REDEF_AEI,
	BEHAV_REDEF_VAR,
	BEHAV_REDEF_REC_FIELD,
	BEHAV_REDEF_PROPERTY,
	BEHAV_REDEF_MEASURE,
	ACT_TYPE_REDEF_ARCHI_TYPE,
	ACT_TYPE_REDEF_AET,
	ACT_TYPE_REDEF_AEI,
	ACT_TYPE_REDEF_BEHAV,
	ACT_TYPE_REDEF_VAR,
	ACT_TYPE_REDEF_REC_FIELD,
	ACT_TYPE_REDEF_PROPERTY,
	ACT_TYPE_REDEF_MEASURE,
	VAR_REDECL_ARCHI_TYPE,
	VAR_REDECL_AET,
	VAR_REDECL_AEI,
	VAR_REDECL_BEHAV,
	VAR_REDECL_REC_FIELD,
	VAR_REDECL_PROPERTY,
	VAR_REDECL_MEASURE,
	REC_FIELD_REDECL_ARCHI_TYPE,
	REC_FIELD_REDECL_AET,
	REC_FIELD_REDECL_AEI,
	REC_FIELD_REDECL_BEHAV,
	REC_FIELD_REDECL_VAR,
	REC_FIELD_REDECL_PROPERTY,
	REC_FIELD_REDECL_MEASURE,
	ARCHI_TYPE_UNDEF,
	AET_UNDEF,
	AEI_UNDECL,
	ONE_AEI_UNDECL,
	ACT_TYPE_UNDEF,
	ONE_ACT_TYPE_UNDEF,
	VAR_UNDECL,
	ONE_VAR_UNDECL,
	REC_FIELD_UNDECL,
	TRACE_FILE_REUSED,
	ONE_TRACE_FILE_REUSED,
	ARCHI_TYPE_USED_AET,
	ARCHI_TYPE_USED_AEI,
	ARCHI_TYPE_USED_BEHAV,
	ARCHI_TYPE_USED_ACT_TYPE,
	ARCHI_TYPE_USED_VAR,
	ARCHI_TYPE_USED_REC_FIELD,
	AET_USED_ARCHI_TYPE,
	AET_USED_AEI,
	AET_USED_BEHAV,
	AET_USED_ACT_TYPE,
	AET_USED_VAR,
	AET_USED_REC_FIELD,
	AEI_USED_ARCHI_TYPE,
	AEI_USED_AET,
	AEI_USED_BEHAV,
	AEI_USED_ACT_TYPE,
	AEI_USED_VAR,
	AEI_USED_REC_FIELD,
	BEHAV_USED_ARCHI_TYPE,
	BEHAV_USED_AET,
	BEHAV_USED_AEI,
	BEHAV_USED_ACT_TYPE,
	BEHAV_USED_VAR,
	BEHAV_USED_REC_FIELD,
	ACT_TYPE_USED_ARCHI_TYPE,
	ACT_TYPE_USED_AET,
	ACT_TYPE_USED_AEI,
	ACT_TYPE_USED_BEHAV,
	ACT_TYPE_USED_VAR,
	ACT_TYPE_USED_REC_FIELD,
	VAR_USED_ARCHI_TYPE,
	VAR_USED_AET,
	VAR_USED_AEI,
	VAR_USED_BEHAV,
	VAR_USED_ACT_TYPE,
	VAR_USED_REC_FIELD,
	REC_FIELD_USED_ARCHI_TYPE,
	REC_FIELD_USED_AET,
	REC_FIELD_USED_AEI,
	REC_FIELD_USED_BEHAV,
	REC_FIELD_USED_ACT_TYPE,
	REC_FIELD_USED_VAR,
	TOO_MANY_PARAMETERS,
	TOO_FEW_PARAMETERS,
	ILL_TYPED_ASSIGN,
	ILL_TYPED_PARAMETERS,
	ILL_TYPED_ARRAY_LENGTH,
	ILL_TYPED_INTEGER_BOUND,
	ILL_TYPED_EXP_RATE,
	ILL_TYPED_PRIORITY,
	ILL_TYPED_WEIGHT,
	ILL_TYPED_ATTACHMENT,
	ONE_ILL_TYPED_ATTACHMENT,
	ILL_TYPED_RENAMING,
	ONE_ILL_TYPED_RENAMING,
	ILL_TYPED_AEI_INDEX,
	ILL_TYPED_EXPR,
	ILL_TYPED_LIST,
	ILL_TYPED_ARRAY,
	ILL_TYPED_REWARD,
	ILL_TYPED_SIM_RUN_LENGTH,
	ILL_TYPED_SIM_RUN_NUM,
	ILL_TYPED_INTERVAL_BEGIN,
	ILL_TYPED_INTERVAL_END,
	ILL_TYPED_SUB_INTERVAL_WIDTH,
	ACTUAL_PAR_NOT_UNDECL_ID_FREE,
	ARRAY_LENGTH_NOT_UNDECL_ID_FREE,
	INTEGER_BOUND_NOT_UNDECL_ID_FREE,
	PRIORITY_NOT_UNDECL_ID_FREE,
	AEI_INDEX_EXPR_NOT_UNDECL_ID_FREE,
	REWARD_NOT_UNDECL_ID_FREE,
	SIM_RUN_LENGTH_NOT_UNDECL_ID_FREE,
	SIM_RUN_NUM_NOT_UNDECL_ID_FREE,
	INTERVAL_BEGIN_NOT_UNDECL_ID_FREE,
	INTERVAL_END_NOT_UNDECL_ID_FREE,
	SUB_INTERVAL_WIDTH_NOT_UNDECL_ID_FREE,
	ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
	ARRAY_LENGTH_NOT_RANDOMNESS_FREE,
	INTEGER_BOUND_NOT_RANDOMNESS_FREE,
	EXP_RATE_NOT_RANDOMNESS_FREE,
	PRIORITY_NOT_RANDOMNESS_FREE,
	WEIGHT_NOT_RANDOMNESS_FREE,
	AEI_INDEX_EXPR_NOT_RANDOMNESS_FREE,
	REWARD_NOT_RANDOMNESS_FREE,
	SIM_RUN_LENGTH_NOT_RANDOMNESS_FREE,
	SIM_RUN_NUM_NOT_RANDOMNESS_FREE,
	INTERVAL_BEGIN_NOT_RANDOMNESS_FREE,
	INTERVAL_END_NOT_RANDOMNESS_FREE,
	SUB_INTERVAL_WIDTH_NOT_RANDOMNESS_FREE,
	EMPTY_INTEGER_RANGE,
	ONE_EMPTY_INTEGER_RANGE,
	EMPTY_INDEX_RANGE,
	ARRAY_LENGTH_NOT_POSITIVE,
	ONE_ARRAY_LENGTH_NOT_POSITIVE,
	ASSIGN_VALUE_OUT_OF_RANGE,
	ONE_ASSIGN_VALUE_OUT_OF_RANGE,
	ASSIGN_ARRAY_LENGTH_MISMATCH,
	ONE_ASSIGN_ARRAY_LENGTH_MISMATCH,
	FIELD_NOT_IN_REC,
	INCONSISTENT_USE_ACT_TYPE,
	ACTUAL_PRIORITY_NOT_POSITIVE_INT_NUMBER,
	ONE_ACTUAL_PRIORITY_NOT_POSITIVE_INT_NUMBER,
	ACTUAL_RATE_NOT_POSITIVE_NUMBER,
	ONE_ACTUAL_RATE_NOT_POSITIVE_NUMBER,
	ACTUAL_WEIGHT_NOT_POSITIVE_NUMBER,
	ONE_ACTUAL_WEIGHT_NOT_POSITIVE_NUMBER,
	EXP_RATE_LE_ZERO,
	PRIORITY_LE_ZERO,
	WEIGHT_LE_ZERO,
	RATE_NOT_PASSIVE,
	ACT_TYPE_NOT_IN_BEHAVIOR,
	ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
	NO_DECLARED_INTERACTIONS,
	OUTPUT_ACT_TYPE_AS_INPUT_INTERACTION,
	INPUT_ACT_TYPE_AS_OUTPUT_INTERACTION,
	INPUT_ACT_TYPE_AS_INPUT_AND_INTERACTION,
	AMBIGUOUS_INTERACTION,
	ACT_TYPE_ALREADY_DECLARED_INTERACTION,
	NO_DECLARED_AEIS,
	ACT_TYPE_NOT_INTERACTION,
	ONE_ACT_TYPE_NOT_INTERACTION,
	INTERACTION_ALREADY_DECLARED_AI,
	ONE_INTERACTION_ALREADY_DECLARED_AI,
	AI_IN_ATTACHMENT,
	ONE_AI_IN_ATTACHMENT,
	ACT_TYPE_NOT_INPUT_INTERACTION,
	ONE_ACT_TYPE_NOT_INPUT_INTERACTION,
	ACT_TYPE_NOT_OUTPUT_INTERACTION,
	ONE_ACT_TYPE_NOT_OUTPUT_INTERACTION,
	UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
	ONE_UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
	AUTO_ATTACHMENT,
	ONE_AUTO_ATTACHMENT,
	TWO_NON_UNI_ATTACHED,
	ONE_TWO_NON_UNI_ATTACHED,
	AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
	ONE_AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
	TWO_NON_PASSIVE_ATTACHMENT,
	ONE_TWO_NON_PASSIVE_ATTACHMENT,
	TWO_NON_PASSIVE_AND_ATTACHMENT,
	ONE_TWO_NON_PASSIVE_AND_ATTACHMENT,
	ATTACHMENT_ALREADY_DECLARED,
	ONE_ATTACHMENT_ALREADY_DECLARED,
	NO_AEI_INDEXING_USED_IN_ATTACHMENT,
	NO_DECLARED_BEHAV_VARIATIONS,
	AI_IN_HIDING,
	ONE_AI_IN_HIDING,
	HIDING_ALREADY_DECLARED,
	ONE_HIDING_ALREADY_DECLARED,
	AI_IN_RESTRICTION,
	ONE_AI_IN_RESTRICTION,
	HIDDEN_ACT_TYPE_IN_RESTRICTION,
	ONE_HIDDEN_ACT_TYPE_IN_RESTRICTION,
	RESTRICTION_ALREADY_DECLARED,
	ONE_RESTRICTION_ALREADY_DECLARED,
	HIDDEN_ACT_TYPE_IN_RENAMING,
	ONE_HIDDEN_ACT_TYPE_IN_RENAMING,
	RESTRICTED_ACT_TYPE_IN_RENAMING,
	ONE_RESTRICTED_ACT_TYPE_IN_RENAMING,
	RENAMING_VIOLATING_UNIQUENESS,
	ONE_RENAMING_VIOLATING_UNIQUENESS,
	RENAMING_ALREADY_DECLARED,
	ONE_RENAMING_ALREADY_DECLARED,
	RENAMING_ACT_TYPE_NUSMV_KEYWORD,
	ONE_RENAMING_ACT_TYPE_NUSMV_KEYWORD,
	SAME_AEI_INDEX,
	ILLEGAL_SECTION,
	ILLEGAL_DEF_DECL,
	UNEXPECTED_CHAR,
	RESUME_ILLEGAL_SECTION,
	RESUME_ILLEGAL_DEF_DECL,
	RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION,
	RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
	ARCHI_TYPE_ID_NOT_DEF,
	AET_ID_NOT_DEF,
	AEI_ID_NOT_DECL,
	BEHAV_ID_NOT_DEF,
	FORMAL_PAR_ID_NOT_DECL,
	ARCHI_TYPE_ID_NOT_USED,
	AET_ID_NOT_USED,
	AEI_ID_NOT_USED,
	BEHAV_ID_NOT_USED,
	ACT_TYPE_ID_NOT_USED,
	FORMAL_PAR_ID_NOT_USED,
	LOCAL_VAR_ID_NOT_USED,
	REC_FIELD_ID_NOT_USED,
	VAR_WITH_EMPTY_INTEGER_RANGE,
	VAR_WITH_ARRAY_LENGTH_NOT_POSITIVE,
	FORMAL_PAR_IN_INPUT,
	LOCAL_VAR_BEFORE_INPUT,
	INTERACTION_NOT_ATTACHED,
	AEI_PAIR_NOT_CONNECTED,
	PASSIVE_ACT_TYPE_WITH_REWARD,
	ONE_PASSIVE_ACT_TYPE_WITH_REWARD,
	ACT_TYPE_ALREADY_REWARD,
	ONE_ACT_TYPE_ALREADY_REWARD,
	SIM_CLOCK_ACT_TYPE_PASSIVE,
	SIM_CLOCK_ACT_TYPE_HIDDEN,
	SIM_CLOCK_ACT_TYPE_RESTRICTED,
	SIM_RUN_LENGTH_NOT_POSITIVE,
	SIM_RUN_NUM_OUT_OF_RANGE,
	INTERVAL_BEGIN_NEGATIVE,
	ONE_INTERVAL_BEGIN_NEGATIVE,
	INTERVAL_BEGIN_GE_RUN_LENGTH,
	ONE_INTERVAL_BEGIN_GE_RUN_LENGTH,
	INTERVAL_END_NOT_POSITIVE,
	ONE_INTERVAL_END_NOT_POSITIVE,
	INTERVAL_END_LE_INTERVAL_BEGIN,
	ONE_INTERVAL_END_LE_INTERVAL_BEGIN,
	INTERVAL_END_GT_RUN_LENGTH,
	ONE_INTERVAL_END_GT_RUN_LENGTH,
	SUB_INTERVAL_WIDTH_NOT_POSITIVE,
	ONE_SUB_INTERVAL_WIDTH_NOT_POSITIVE,
	SUB_INTERVAL_WIDTH_NOT_REGULAR,
	ONE_SUB_INTERVAL_WIDTH_NOT_REGULAR,
	TRACE_EXPR_NOT_RANDOM,
	ONE_TRACE_EXPR_NOT_RANDOM,
	TRACE_EXPR_NOT_IN_AEMILIA_SPEC,
	ONE_TRACE_EXPR_NOT_IN_AEMILIA_SPEC,
	TRACE_EXPR_ALREADY_WITH_TRACE_FILE,
	ONE_TRACE_EXPR_ALREADY_WITH_TRACE_FILE,
	HIGH_LOW_ACT_TYPE_HIDDEN,
	ONE_HIGH_LOW_ACT_TYPE_HIDDEN,
	HIGH_LOW_ACT_TYPE_RESTRICTED,
	ONE_HIGH_LOW_ACT_TYPE_RESTRICTED,
	HIGH_ACT_TYPE_BECOMES_LOW,
	ONE_HIGH_ACT_TYPE_BECOMES_LOW,
	REDUNDANT_ACT_TYPE_HIGH,
	ONE_REDUNDANT_ACT_TYPE_HIGH,
	REDUNDANT_ACT_TYPE_LOW,
	ONE_REDUNDANT_ACT_TYPE_LOW
}			ERROR_INDEX;			/* see error_msg[] and exhaustive use in
							 * signal_error() of listing-generator.c */


 /* crash index */
typedef	enum
{
	MEM_CRASH,
	FILE_CRASH,
	EXP_RATE_LE_ZERO_CRASH,
	PRIORITY_LE_ZERO_CRASH,
	WEIGHT_LE_ZERO_CRASH,
	EXP_RATE_UNEVAL_CRASH,
	WEIGHT_UNEVAL_CRASH,
	ARRAY_LENGTH_MISMATCH_CRASH,
	OUT_OF_RANGE_BOUNDED_INTEGER_CRASH,
	DIV_OP_CRASH,
	MOD_OP_CRASH,
	POWER_OP_CRASH,
	LOG_OP_CRASH,
	SQRT_OP_CRASH,
	C_UNIFORM_OP_CRASH,
	ERLANG_OP_CRASH,
	GAMMA_OP_CRASH,
	EXPONENTIAL_OP_CRASH,
	WEIBULL_OP_CRASH,
	BETA_OP_CRASH,
	NORMAL_OP_CRASH,
	PARETO_OP_CRASH,
	B_PARETO_OP_CRASH,
	D_UNIFORM_OP_CRASH,
	BERNOULLI_OP_CRASH,
	BINOMIAL_OP_CRASH,
	POISSON_OP_CRASH,
	NEG_BINOMIAL_OP_CRASH,
	GEOMETRIC_OP_CRASH,
	PASCAL_OP_CRASH,
	FIRST_OP_CRASH,
	REMOVE_OP_CRASH,
	READ_OP_CRASH,
	WRITE_OP_CRASH,
	DEADLOCK_STATE_SIM_CRASH,
	OPEN_STATE_SIM_CRASH,
	TRACE_READ_CRASH
}			CRASH_INDEX;			/* see crash_msg[] and exhaustive use in
							 * signal_crash() of driver.c */


/****************************************************************
 * 4. Definition of structured types.				*
 ****************************************************************/

 /* symbol table: symbol table bucket cell */
typedef	struct	st_bucket_cell
{
	struct	st_bucket	*st_bucket;
					/* pointer to the symbol table bucket */
	struct	st_bucket_cell	*next_st_bucket_cell;
					/* pointer to the next symbol table bucket cell */
}			ST_BUCKET_CELL;


 /* symbol table: double symbol table bucket cell */
typedef	struct	st_bucket2_cell
{
	struct	st_bucket	*st_bucket1,
					/* pointer to the first symbol table bucket */
				*st_bucket2;
					/* pointer to the second symbol table bucket */
	struct	st_bucket2_cell	*next_st_bucket2_cell;
					/* pointer to the next double symbol table bucket cell */
}			ST_BUCKET2_CELL;


 /* symbol table: triple symbol table bucket cell */
typedef	struct	st_bucket3_cell
{
	struct	st_bucket	*st_bucket1,
					/* pointer to the first symbol table bucket */
				*st_bucket2,
					/* pointer to the second symbol table bucket */
				*st_bucket3;
					/* pointer to the third symbol table bucket */
	struct	st_bucket3_cell	*next_st_bucket3_cell;
					/* pointer to the next triple symbol table bucket cell */
}			ST_BUCKET3_CELL;


 /* symbol table: quadruple symbol table bucket cell */
typedef	struct	st_bucket4_cell
{
	struct	st_bucket	*st_bucket1,
					/* pointer to the first symbol table bucket */
				*st_bucket2,
					/* pointer to the second symbol table bucket */
				*st_bucket3,
					/* pointer to the third symbol table bucket */
				*st_bucket4;
					/* pointer to the fourth symbol table bucket */
	struct	st_bucket4_cell	*next_st_bucket4_cell;
					/* pointer to the next quadruple symbol table bucket cell */
}			ST_BUCKET4_CELL;


 /* symbol table: symbol table bucket variant information */
typedef	union	st_bucket_info
{
	struct	archi_type	*archi_type;
					/* pointer to the architectural type identified by the symbol */
					/* (symbol_index = ARCHI_TYPE_ID) */
	struct	aet		*aet;
					/* pointer to the AET identified by the symbol */
					/* (symbol_index = AET_ID) */
	struct	aei		*aei;
					/* pointer to the AEI identified by the symbol */
					/* (symbol_index = AEI_ID) */
	struct	behavior	*behavior;
					/* pointer to the behavior identified by the symbol */
					/* (symbol_index = BEHAV_ID) */
	struct	act_type	*act_type;
					/* pointer to the action type identified by the symbol */
					/* (symbol_index = ACT_TYPE_ID) */
	struct	par_behav_invoc	*par_behav_invoc;
					/* pointer to the parametrized behavior invocation identified by */
					/* the symbol */
					/* (symbol_index = PAR_BEHAV_INVOC_STRING) */
	struct	expr		*expr;
					/* pointer to the expression identified by the symbol */
					/* (symbol_index = VAR_FORMAL_PAR_ID, LOCAL_VAR_ID, TT, FF, */
					/* EXPR_STRING, NUMBER) */
	struct	action		*action;
					/* pointer to the action identified by the symbol */
					/* (symbol_index = ACTION_STRING) */
	struct	st_bucket	*property_expr;
					/* pointer to the symbol table bucket for the modal/temporal logic */
					/* expression identified by the symbol */
					/* (symbol_index = PROPERTY_ID) */
	struct	aggr_g_trans	*aggr_global_trans;
					/* pointer to the aggregated global transition identified by the */
					/* symbol */
					/* (symbol_index = AGGR_GLOBAL_TRANS_STRING) */
	struct	rew_measure	*rew_measure;
					/* pointer to the reward measure identified by the symbol */
					/* (symbol_index = MEASURE_ID and option_index = SM_GAUSS_ELIM, */
					/* SM_ASSOR, TM_UNIF) */
	struct	sim_measure	*sim_measure;
					/* pointer to the simulation measure identified by the symbol */
					/* (symbol_index = MEASURE_ID and option_index = SIM) */
	FILE			*trace_file;
					/* pointer to the trace file identified by the symbol */
					/* (symbol_index = TRACE_FILE_ID) */
}			ST_BUCKET_INFO;


 /* symbol table: symbol table bucket */
typedef	struct	st_bucket
{
	char			*symbol_lexeme;
					/* lexeme of the symbol; */
					/* set at scanning time and by build_par_behav_invoc_lexeme(), */
					/* build_expr_lexeme(), build_action_lexeme(), and */
					/* build_param_list_lexeme() of symbol-handler.c; */
					/* used in aemilia-parser.y, in search_unloc_var() of */
					/* sim-parser.y, */
					/* in rewrite_expr(), build_par_behav_invoc_lexeme(), */
					/* build_expr_lexeme(), build_action_lexeme(), and */
					/* build_param_list_lexeme() of symbol-handler.c, in */
					/* check_expr_type(), check_sem_error(), and check_rel_type() of */
					/* aemilia-checker.c, in open_sem_model(), handle_init_state(), */
					/* gen_sem_model(), build_unfolded_state_lexeme(), */
					/* print_assign_list(), sort_move(), compute_pot_move(), */
					/* norm_pot_move(), check_act_type_in_act_type_set(), and */
					/* apply_rel_fun() of aemilia-compiler.c, in verify_int_bisim_eq() */
					/* and compute_aggr_move() of i-analyzer.c, in */
					/* verify_fun_formula() and verify_fun_eq_pre() of */
					/* cwbnc-connector.c, in print_yb_measure() of markov-solver.c, */
					/* and in open_trace_file(), eval_assign_list(), eval_expr(), and */
					/* infer_measure() of simulator.c */
	short int		symbol_index;
					/* sort of the symbol; */
					/* set at scanning and parsing time; */
					/* used in set_variable_bucket(), set_par_behav_invoc_bucket(), */
					/* set_expr_bucket(), set_action_bucket(), set_number_bucket(), */
					/* and rewrite_expr() of symbol-handler.c, in check_sem_error() of */
					/* aemilia-checker.c, in build_unfolded_state_lexeme() of */
					/* aemilia-compiler.c, and in compute_aggr_move() of i-analyzer.c */
	char			defined,
                                        /* flag indicating whether the identifier given by the symbol has */
					/* been defined/declared */
                                        /* (symbol_index = ARCHI_TYPE_ID, AET_ID, AEI_ID, BEHAV_ID, */
                                        /* VAR_FORMAL_PAR_ID, LOCAL_VAR_ID); */
					/* set by check_id() of aemilia-parser.y; */
					/* used in check_sem_error() of aemilia-checker.c to verify the */
					/* existence of the identifier definition */
                                used;
                                        /* flag indicating whether the identifier given by the symbol */
					/* has been used */
                                        /* (symbol_index = AET_ID, AEI_ID, BEHAV_ID, ACT_TYPE_ID, */
					/* VAR_FORMAL_PAR_ID, LOCAL_VAR_ID, RECORD_FIELD_ID); */
					/* set by check_id() of aemilia-parser.y; */
					/* used in check_sem_error() of aemilia-checker.c to verify the */
					/* existence of an identifier use */
	union	st_bucket_info	info;
					/* information associated with the symbol; */
                                        /* (symbol_index = ARCHI_TYPE_ID, AET_ID, AEI_ID, BEHAV_ID, */
					/* ACT_TYPE_ID, VAR_FORMAL_PAR_ID, LOCAL_VAR_ID, RECORD_FIELD_ID, */
					/* MEASURE_ID, TRACE_FILE_ID, TT, FF, PAR_BEHAV_INVOC_STRING, */
					/* EXPR_STRING, ACTION_STRING, AGGR_GLOBAL_TRANS_STRING, NUMBER); */
					/* mostly set at parsing time; */
					/* used all over the software tool */
	struct	cdt_bucket	*code;
					/* pointer to the bucket for the code of the symbol used in the */
					/* concise representation of the local states; */
					/* set by encode_state_comp() of aemilia-compiler.c; */
					/* used in handle_term() of aemilia-parser.y and in */
					/* handle_init_state(), build_unfolded_state_lexeme(), */
					/* compute_pot_move(), and norm_pot_move() of aemilia-compiler.c */
	struct	st_bucket	*next_st_bucket;
					/* pointer to the next symbol table bucket */
}			ST_BUCKET;


 /* symbol table: architectural type */
typedef	struct	archi_type
{
	struct	st_bucket_cell	*formal_const_par_list,
					/* list of the formal constant parameters declared in the */
					/* architectural type header; */
					/* set by ; */
					/* used in */
				*init_assign_list,
					/* list of the initial assignments for the formal variable */
					/* parameters declared in the initial behavior header of the */
					/* various AEIs; */
					/* set by ; */
					/* used in */
				*aet_list,
					/* list of the AETs defined in the architectural type; */
					/* set by ; */
					/* used in */
				*aei_list,
					/* list of the AEIs declared in the architectural type; */
					/* set by ; */
					/* used in */
				*ai_list;
					/* list of the architectural interactions declared in the */
					/* architectural type; */
					/* set by ; */
					/* used in */
	int			aei_num;
					/* number of AEIs; */
					/* set by ; */
					/* used in */
	char			value_passing,
					/* flag indicating whether value passing occurs in the */
					/* architectural type; */
                        		/* set by ; */
                        		/* used in handle_init_state(), gen_sem_model(), */
					/* gen_sem_model_trans(), compute_repr_alias_state(), */
					/* build_unfolded_state_lexeme(), melt_move(), compute_pot_move(), */
					/* and norm_pot_move() of aemilia-compiler.c and in simulate() of */
					/* simulator.c */
				performance_closed,
					/* flag indicating whether the state space is performance closed; */
                        		/* set by ; */
                        		/* used in drive() and close_report() of driver.c and in */
					/* gen_sem_model() and close_sem_model() of aemilia-compiler.c */
				psm_index;
					/* type of the performance semantic model; */
                        		/* set by open_report() of driver.c and by gen_sem_model() of */
					/* aemilia-compiler.c; */
                        		/* used in gen_sem_model(), close_sem_model(), and print_psm() of */
					/* aemilia-compiler.c and in compute_distr_yb_measure(), */
					/* build_matrix(), build_vector(), build_diag_elem(), */
					/* visit_depth_first1(), print_distr(), compute_yb_measure(), and */
                        		/* update_yb_measure() of markov-solver.c */
	struct	gst_bucket	*init_global_state;
					/* pointer to the global state table bucket of the initial state; */
                        		/* set by ; */
                        		/* used in drive() of driver.c, in gen_sem_model() of */
					/* aemilia-compiler.c, in verify_int_bisim_eq() of i-analyzer.c, */
					/* in compute_distr_yb_measure() and compute_absorbing_scc_num() */
					/* of markov-solver.c, and in simulate() of simulator.c */
	int			state_num,
					/* number of states (it is initially negative in the case the */
					/* performance semantic model must be generated); */
                        		/* set by gen_sem_model(), remove_vanishing_state(), and */
					/* print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c, in gen_sem_model() and */
					/* close_sem_model() of aemilia-compiler.c, in */
					/* verify_int_bisim_eq() of i-analyzer.c, and in */
                        		/* compute_distr_yb_measure(), build_vector(), transpose_matrix(), */
                        		/* compute_absorbing_scc_num(), solve_gauss_elim(), solve_assor(), */
                        		/* perform_ssor_step(), solve_unif(), normalize_vector(), and */
					/* update_yb_measure() of markov-solver.c */
				vanishing_state_num,
					/* number of vanishing states; */
                        		/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c and in close_sem_model() of */
					/* aemilia-compiler.c */
				open_state_num,
					/* number of open states; */
                        		/* set by gen_sem_model() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c and in close_sem_model() of */
					/* aemilia-compiler.c */
				absorbing_state_num,
					/* number of absorbing states; it is intended to be the number of */
                        		/* states with no outgoing transitions in the case of the */
					/* integrated or functional semantic model, the number of states */
					/* with no outgoing transitions towards other states in the case */
					/* of the performance semantic model; */
                        		/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c, in close_sem_model() of */
					/* aemilia-compiler.c, and in compute_distr_yb_measure() of */
					/* markov-solver.c */
				trans_num,
					/* number of transitions; */
                        		/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c, in close_sem_model() of */
					/* aemilia-compiler.c, and in compute_distr_yb_measure() of */
					/* markov-solver.c */
				invisible_trans_num,
					/* number of invisible transitions; */
                        		/* set by gen_sem_model() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c and in close_sem_model() of */
					/* aemilia-compiler.c */
				immediate_trans_num,
					/* number of immediate transitions; */
                        		/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c and in close_sem_model() of */
					/* aemilia-compiler.c */
				passive_trans_num;
					/* number of passive transitions; */
                        		/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
                        		/* used in close_report() of driver.c and in close_sem_model() of */
					/* aemilia-compiler.c */
}			ARCHI_TYPE;


 /* symbol table: AET */
typedef	struct	aet
{
	struct	st_bucket_cell	*formal_const_par_list,
					/* list of the formal constant parameters declared in the AET */
					/* header; */
					/* set by ; */
					/* used in */
				*behavior_list,
					/* list of behaviors defined in the AET behavior; */
					/* set by ; */
					/* used in */
				*act_type_list;
					/* list of action types occurring in the AET behavior; */
					/* set by ; */
					/* used in */
}			AET;


 /* symbol table: AEI */
typedef	struct	aei
{
	struct	st_bucket	*aet;
					/* pointer to the symbol table bucket for the AET representing */
					/* the type of the AEI; */
					/* set by ; */
					/* used in */
	int			aei_no;
					/* serial number of the AEI within the architectural type (it is 0 */
					/* if the AEI identifier has been parsed within an iteration and */
					/* contains an index); */
					/* set by ; */
					/* used in */
	struct	st_bucket_cell	*formal_const_par_list,
					/* list of the (rewritten) formal constant parameters declared in */
					/* the header of the AET representing the type of the AEI; */
					/* set by ; */
					/* used in */
				*behavior_list,
					/* list of the (rewritten) behaviors defined in the behavior of */
					/* the AET representing the type of the AEI; */
					/* set by ; */
					/* used in */
				*act_type_list,
					/* list of the (rewritten) action types occurring in the behavior */
					/* of the AET representing the type of the AEI; */
					/* set by ; */
					/* used in */
				*sync_set,
					/* list of the (rewritten and #-composed) action types forming the */
					/* synchronization set that precedes the AEI in the translation */
					/* semantics of the architectural type; */
					/* set by ; */
					/* used in */
				*init_state_var_assign_list;
					/* list of the initial concrete assignments to the state variables */
					/* of the AEI */
					/* (value_passing = CONCRETE_VP); */
					/* set by ; */
					/* used in */
	struct	lst_bucket	*init_local_state;
					/* pointer to the local state table bucket for the initial state */
					/* of the AEI; */
					/* set by ; */
					/* used in */
	int			local_state_num;
					/* number of local states of the AEI; */
					/* set by ; */
					/* used in */
	struct	l_act_type_cell	*local_act_type_list,
					/* list of the action types labeling the local transitions of the */
					/* AEI; */
					/* set by ; */
					/* used in */
				*composed_act_type_list;
					/* list of the action types labeling the composed local */
					/* transitions of the AEIs up to this one; */
					/* set by ; */
					/* used in */
}			AEI;


 /* symbol table: behavior */
typedef	struct	behavior
{
	struct	st_bucket_cell	*formal_var_par_list,
					/* list of formal variable parameters declared in the behavior */
					/* header; */
					/* set in aemilia-parser.y; */
					/* used in check_par_list_type() of aemilia-checker.c to check for */
					/* parameter consistency in the case of parametrized behavior */
					/* invocation */
				*actual_var_par_list,
					/* list of actual variable parameters if the behavior is the */
					/* initial one; */
					/* set in aemilia-parser.y; */
					/* used in check_par_list_type() of aemilia-checker.c to check for */
					/* parameter consistency in the case of parametrized behavior */
					/* invocation */
				*local_var_list,
					/* list of local variables declared in the behavior header; */
					/* set in aemilia-parser.y; */
					/* used in insert_technical_assign() of aemilia-compiler.c to */
					/* generate technical assignments */
				*invoked_behavior_list;
					/* list of behaviors invoked within the term in the right hand */
					/* side of the defining equation for the behavior; */
					/* set by ; */
					/* used in */
	char			visited,
					/* flag indicating whether the behavior has been visited during */
					/* the check for unused behaviors; */
					/* set by ; */
					/* used in */
				init_behav,
					/* flag indicating whether the behavior is the initial one in the */
					/* AET/AEI behavior; */
					/* set by ; */
					/* used in */
				*state_lexeme;
					/* lexeme of the state associated with the term in the right hand */
					/* side of the defining equation for the behavior; this is shared */
					/* by all the AEIs of the AET in which the behavior is defined; */
					/* set by handle_term() of aemilia-parser.y; */
					/* used in compute_repr_alias_state() of aemilia-compiler.c */
}			BEHAVIOR;


 /* symbol table: reward information */
typedef	union	reward_info
{
	struct	yb_cell		*yb_list;
					/* list of yield-bonus reward pairs associated with the action */
					/* type enabling; if the action type is not involved in any */
					/* performance measure, then the list is NULL, otherwise it */
					/* contains as many cells (some of which possibly with zero */
					/* rewards) as the serial number of the last measure involving */
					/* the action type */
					/* (option_index = SM_GAUSS_ELIM, SM_ASSOR, TM_UNIF); */
					/* set by append_absent_yb() of rew-parser.y; */
					/* used in compute_pot_move() of aemilia-compiler.c */
	struct	sample_cell	*sample_list;
					/* list of samples associated with the action type execution */
					/* (option_index = SIM); */
					/* set in sim-parser.y; */
					/* used in update_sample_clock() of simulator.c */
}			REWARD_INFO;


 /* symbol table: action type */
typedef	struct	act_type
{
	char			act_type_index;
					/* sort of the action type; */
					/* set in aemilia-parser.y; */
					/* used in */
	struct	st_bucket_cell	*par_list;
					/* list of parameters of the action type */
					/* (value_passing = TRUE and act_type_index = INPUT, OUTPUT); */
					/* set in aemilia-parser.y; */
					/* used in check_par_list_type() of aemilia-checker.c */
	struct	st_bucket	*aei;
					/* pointer to the symbol table bucket for the AEI in which the */
					/* action type occurs; */
					/* set by ; */
					/* used in */
	char			rate_index;
					/* sort of the rate of the action; this is needed due to the fact */
					/* that the priority level can be symbolic, so using positive */
					/* integers for immediate actions and negative integers for */
					/* actions does not always work; */
					/* set by; */
					/* used in */
	struct	st_bucket	*priority;
					/* pointer to the symbol table bucket for the (possibly symbolic) */
					/* priority level of the action type; */
					/* set by ; */
					/* used in */
	char			interaction_index,
					/* sort of the action type in the case it is an interaction; */
					/* set by ; */
					/* used in */
				architectural;
					/* flag indicating whether the action type is an architectural */
					/* interaction */
					/* (interaction_index <> NO_INTERACTION); */
					/* set by ; */
					/* used in */
	struct	st_bucket_cell	*attached_interaction_list,
					/* list of attached interactions */
					/* (interaction_index <> NO_INTERACTION and architectural = */
					/* FALSE); */
					/* set by ; */
					/* used in */
				*duplicate_list;
					/* list of duplicates of the action type or list of correlated */
					/* action types to be taken into account when normalizing the */
					/* rates */
					/* (interaction_index = INPUT_OR, OUTPUT_OR and architectural = */
					/* FALSE); */
					/* set by ; */
					/* used in */
	char			variation;
					/* flag indicating whether the action type is hidden/restricted; */
					/* set by ; */
					/* used in */
	struct	st_bucket	*renamed;
					/* pointer to the symbol table bucket for the action type into */
       					/* which the action type is renamed; if the action type is */
					/* involved in an attachment, then it is renamed to the */
					/* synchronization action type (which may be renamed in turn); */
					/* set by ; */
					/* used in */
	union	reward_info	reward_info;
					/* list of yield-bonus reward pairs or samples associated with */
					/* the action type; */
					/* (option_index = SM_GAUSS_ELIM, SM_ASSOR, TM_UNIF, SIM); */
					/* set by ; */
					/* used in */
	char			sim_clock_act_type,
					/* flag indicating whether the execution of the action type makes */
					/* the simulation clock advance */
					/* (option_index = SIM); */
					/* set by ; */
					/* used in */
				security_level;
					/* flag indicating the security level of the action type */
					/* (option_index = NON_INTERF_ANALYSIS, NON_DED_ON_COMP_ANALYSIS); */
					/* set by ; */
					/* used in */
}			ACT_TYPE;


 /* symbol table: yield-bonus cell */
typedef	struct	yb_cell
{
	long double		yield,
				bonus;
					/* value of the yield-bonus reward pair; */
					/* set by append_absent_yb() of rew-parser.y and by */
					/* norm_pot_move() and remove_vanishing_state() of */
					/* aemilia-compiler.c; */
					/* used in update_yb_measure() of markov-solver.c */
	struct	yb_cell		*next_yb_cell;
					/* pointer to the next yield-bonus cell */
}			YB_CELL;


 /* symbol table: sample cell */
typedef	struct	sample_cell
{
	struct	sample		*sample;
					/* pointer to the sample */
	struct	sample_cell	*next_sample_cell;
					/* pointer to the next sample cell */
}			SAMPLE_CELL;


 /* symbol table: sample */
typedef	struct	sample
{
	struct	st_bucket	*reward_expr;
					/* pointer to the symbol table bucket for the reward associated */
					/* with the sample; the bucket is given op_index equal to */
					/* SAMPLE_OP and opn1 equal to the symbol table bucket for the */
					/* performance measure in which it occurs; */
					/* set in sim-parser.y; */
					/* used in update_sample_clock() of simulator.c; */
	long double		cumul_reward[MAX_SIM_RUN_NUM];
					/* cumulative reward at the end of each simulation run; */
					/* set by update_sample_clock() of simulator.c in the case of */
					/* execution of the associated action type; */
					/* used in infer_measure() of simulator.c */
	int			obs_num[MAX_SIM_RUN_NUM];
					/* number of observations of the associated action type for each */
					/* simulation run; */
					/* set by update_sample_clock() of simulator.c in the case of */
					/* execution of the associated action type; */
					/* used in infer_measure() of simulator.c if the measure in which */
					/* the sample occurs is not cumulative */
}			SAMPLE;


 /* symbol table: parametrized behavior invocation */
typedef	struct	par_behav_invoc
{
	struct	st_bucket	*behavior;
					/* pointer to the symbol table bucket of the behavior; */
					/* set by handle_term() of aemilia-parser.y; */
					/* used in build_unfolded_state_lexeme() of aemilia-compiler.c */
	struct	st_bucket_cell	*actual_par_list;
					/* list of actual parameters */
					/* (value_passing = TRUE); */
					/* set by handle_term() of aemilia-parser.y; */
					/* used in build_unfolded_state_lexeme() of aemilia-compiler.c */
}			PAR_BEHAV_INVOC;


 /* symbol table: expression */
typedef	struct	expr
{
	char			op_index;
					/* outermost operator of the expression; */
					/* set at parsing time by set_expr_bucket() of symbol-handler.c; */
					/* used in eval_expr() of simulator.c */
	struct	st_bucket	*opn1,
				*opn2,
				*opn3;
					/* expression operands (or bounds of a bounded integer typed */
					/* variable or record field); */
					/* set at parsing time by set_expr_bucket() of symbol-handler.c; */
					/* used in eval_expr() of simulator.c */
	struct	dtt_bucket	*data_type;
					/* pointer to the bucket for the data type table bucket of the */
					/* type of the expression; */
					/* this type representation is more compact than the type tree and */
					/* lets all the expressions with the same type to share the same */
					/* bucket, thereby reducing the memory usage and allowing strict */
					/* type checking to be conducted with a single comparison; in the */
					/* case of structured data type, information about its subtype is */
					/* contained also in the cell pointed to by struct_value for a */
					/* more efficient loose type checking; */
					/* set by init_table() of table-handler.c, by check_id() and */
					/* handle_struct_data_type_decl() of aemilia-parser.y, by */
					/* set_expr_bucket() and set_number_bucket() of */
					/* symbol-handler.c, and by handle_formula_id_var() and */
					/* handle_formula_var() of mu-parser.y */
					/* used in check_expr_type() of aemilia-checker.c */
	char			illegal_use_cumul;
					/* flag indicating whether the formal parameter/local variable */
					/* forming the expression is used in an input action/before */
					/* occurring in an input action */
					/* (value_passing = TRUE and symbol_index = FORMAL_PAR_ID, */
					/* LOCAL_VAR_ID) */
					/* or flag indicating whether the reward forming the expression */
					/* associated with the sample is cumulative or at the end of each */
					/* simulation run its cumulative value must be divided by the */
					/* number of observations */
					/* (symbol_index = EXPR_STRING and expr_op_index = SAMPLE_OP); */
					/* in the second case the flag is part of the lexeme; */
					/* set by handle_behav_def3() and handle_input_list() of */
					/* aemilia-parser.y and in sim-parser.y; */
					/* used in check_sem_error() of aemilia-checker.c and in */
					/* eval_expr() of simulator.c */
	long double		value;
					/* expression value for scalar data */
					/* (type = "i", "r", "b") */
					/* or array length for array data */
					/* (type = "a"); */
					/* set by init_table() of table-handler.c and set_number_bucket() */
					/* of symbol-handler.c in the case of boolean or numeric */
					/* constants, by set_expr_bucket() of symbol-handler.c in the case */
					/* of array length, and by eval_expr(), assign_expr_eval(), and */
					/* reset_loc_var_list of simulator.c; */
					/* used in check_expr_type() of aemilia-checker.c and in */
					/* eval_expr(), assign_expr_eval(), choose_exp_timed_trans(), and */
					/* choose_imm_trans() of simulator.c */
	struct	value_cell	*struct_value;
					/* expression value for structured data */
					/* (type = "l", "a", "p"), */
					/* pointer to the symbol table bucket for the trace file */
					/* from which values have to be taken at simulation time */
					/* (option_index = SIM and op_index related to probability */
					/* distributions), */
					/* or action type list */
					/* (op_index = MU_CAN_OP, MU_WEAK_CAN_OP, MU_MUST_OP, */
					/* MU_WEAK_MUST_OP); */
					/* to reduce memory usage in case of simulation, the allocated */
					/* cells of the two data structures are reused as much as possible */
					/* in eval_expr() of simulator.c */
					/* (left hand side of an assignment or right hand side of an */
					/* assignment with op_index <> VAR_OP); */
					/* set by handle_struct_data_type_decl() of aemilia-parser.y for */
					/* subtype related information, by set_expr_bucket() of */
					/* symbol-handler.c in the case of expression list, array or */
					/* record, and by eval_expr() and assign_expr_eval() of */
					/* simulator.c; */
					/* used in check_expr_type() of aemilia-checker.c and in */
					/* eval_expr() and assign_expr_eval() of simulator.c */
	char			sim_run_num;
					/* simulation run number; together with assign_list_eval_num, it */
					/* avoids recomputing at simulation time every nonvariable right */
					/* hand side shared by several assignments of the same list */
					/* (option_index = SIM, value_passing = TRUE, and right hand side */
					/* of an assignment with op_index <> VAR_OP); */
					/* set and used by eval_expr() of simulator.c */
	int			assign_list_eval_num;
					/* assignment list evaluation number; together with sim_run_num, */
					/* it avoids recomputing at simulation time every nonvariable */
					/* right hand side shared by several assignments of the same list */
					/* (option_index = SIM, value_passing = TRUE, and right hand side */
					/* of an assignment with op_index <> VAR_OP); */
					/* set and used by eval_expr() of simulator.c */
	struct	sample		*sample;
					/* pointer to the sample having this reward expression */
					/* (option_index = SIM and op_index = SAMPLE_OP) */
					/* or to the string representing the concrete value of the */
					/* expression; */
					/* set at parsing time in sim-parser.y and by reset_sample() and */
					/* eval_expr() of simulator.c; */
					/* used in eval_expr() and update_sample_clock() of simulator.c */
}			EXPR;


 /* symbol table: value cell */
typedef	struct	value_cell
{
	struct	st_bucket	*value_bucket;
					/* pointer to the symbol table bucket for this element; */
					/* set by handle_struct_data_type_decl() of aemilia-parser.y (to */
					/* store subtype related information) and when parsing an */
					/* expression list, array or record in aemilia-parser.y; */
					/* used in check_expr_type() of checker.c, in set_expr_bucket() of */
					/* symbolhandler.c, and in eval_expr() of simulator.c */
	long double		value;
					/* value of this element if scalar */
					/* (type = "l" with depth 1, "p" with depth 1); */
					/* to avoid using wrong values at simulation time, the value */
					/* stored in the symbol table bucket for the element is moved here */
					/* as soon as it is computed (as a consequence of an assignment */
					/* labeling the current transition) because that value may change */
					/* due to an assignment labeling the same transition; since the */
					/* subtype related information must be available at simulation */
					/* time even in the case of a list that becomes empty, value is */
					/* set to DBL_MAX in the case of empty list; */
					/* set and used by eval_expr() of simulator.c; */
	struct	value_cell	*struct_value,
					/* pointer to the value of this element if structured */
					/* (type = "l" with depth >= 2, "a", "p" with depth >= 2); */
					/* the same note as above applies; in the case of an array, it is */
					/* a pointer to a set of contiguous pointers to the cells of the */
					/* values; */
					/* set by transform_list_array() of aemilia-parser.y, */
					/* init_struct_local_var() of symbol-handler.c, and eval_expr() of */
					/* simulator.c; */
					/* used in eval_expr() of simulator.c */
				*next_value_cell;
					/* pointer to the next value cell */
}			VALUE_CELL;


 /* symbol table: action */
typedef	struct	action
{
	struct	st_bucket	*type;
					/* pointer to the symbol table bucket of the type of the action; */
					/* set in aemilia-parser.y and by compute_pot_move() of */
					/* aemilia-compiler.c; */
					/* used in gen_sem_model(), melt_move(), sort_move(), */
					/* compute_pot_move(), select_passive_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c, in compute_aggr_move() */
					/* of i-analyzer.c, and in update_sample_clock() of simulator.c */
	char			action_index;
					/* sort of the action; */
					/* set in aemilia-parser.y; */
					/* used in norm_pot_move() of aemilia-compiler.c */
	struct	st_bucket_cell	*par_list;
					/* list of parameters of the action */
					/* (value_passing = TRUE and action_index = INPUT, OUTPUT); */
					/* set in aemilia-parser.y; */
					/* used in norm_pot_move() of aemilia-compiler.c */
	char			rate_index;
					/* sort of the rate of the action; this is needed due to the fact */
					/* that the priority level can be symbolic, so using positive */
					/* integers for immediate actions and negative integers for */
					/* actions does not always work; */
					/* set by; */
					/* used in */
	struct	st_bucket	*priority,
					/* pointer to the symbol table bucket for the (possibly symbolic) */
					/* priority level of the action; */
					/* set in aemilia-parser.y and by compute_pot_move() and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), melt_move(), select_move(), */
					/* sort_move(), select_passive_pot_move(), and norm_pot_move() of */
					/* aemilia-compiler.c, in compute_aggr_move() of i-analyzer.c, and */
					/* in simulate() and check_open() of simulator.c */
				*rate;
					/* pointer to the symbol table bucket for the (possibly symbolic) */
					/* rate/generative-weight/reactive-weight of the exponentially- */
					/* timed/immediate/passive action; in the case of an immediate */
					/* action, it is transformed into the corresponding execution */
					/* probability before proceeding with the Markovian analysis; */
					/* set in aemilia-parser.y and by remove_vanishing_state(), */
					/* norm_weight(), melt_move(), and norm_pot_move() of */
					/* aemilia-compiler.c; */
					/* used in remove_vanishing_state(), print_psm(), and */
					/* compute_pot_move() of aemilia-compiler.c, in */
					/* compute_aggr_move() */
					/* of i-analyzer.c, in build_vector(), update_hsmp_norm_factor(), */
					/* and update_yb_measure() of markov-solver.c, and in */
					/* choose_exp_timed_trans() and choose_imm_trans() of simulator.c */
}			ACTION;


 /* symbol table: aggregated global transition */
typedef	struct	aggr_g_trans
{
	struct	part_block	*part_block;
					/* pointer to the partition block identified by the aggregated */
					/* global transition, i.e. the set of global states sharing this */
					/* aggregated global transition towards a given partition block; */
					/* set by compute_aggr_move() of i-analyzer.c; */
					/* used in scan_state_space() of i-analyzer.c */
	struct	st_bucket_cell	*act_type_list;
					/* list of symbol table bucket cells for the action types */
					/* occurring in the aggregated global transition; */
					/* set by ; */
					/* used in */
	struct	aggr_rate_cell	*aggr_rate_list;
					/* list of cells for the aggregated rates of the action types */
					/* occurring in the aggregated global transition */
					/* (option_index = STRONG_MARKOVIAN_BISIM_EQUIV_CHECK, */
					/* WEAK_MARKOVIAN_BISIM_EQUIV_CHECK); */
					/* set by ; */
					/* used in */
	int			refinement_phase;
					/* refinement phase to which the aggregated global transition */
					/* is related; this is needed to avoid confusion with equal */
					/* aggregated global transitions related to previous partitions; */
					/* set and used in compute_aggr_move() of i-analyzer.c */
}			AGGR_G_TRANS;


 /* symbol table: partition block cell */
typedef	struct	part_block_cell
{
	struct	part_block	*part_block;
					/* pointer to the partition block; */
					/* set by ; */
					/* used in */
	struct	part_block_cell	*next_part_block_cell;
					/* pointer to the next partition block cell */
}			PART_BLOCK_CELL;


 /* symbol table: partition block */
typedef	struct	part_block
{
	struct	g_state_cell	*global_state_list;
					/* list of global states composing the partition block; */
					/* set by verify_int_bisim_eq(), scan_state_space(), and */
					/* compute_aggr_move() of i-analyzer.c; */
					/* used in verify_int_bisim_eq() of i-analyzer.c */
	struct	part_block	*father_part_block,
					/* pointer to the father partition block in the partition block */
					/* tree; */
					/* set by ; */
					/* used in */
				*refining_part_block;
					/* pointer to the refining partition block that originates the */
					/* children partition blocks in the partition block tree; */
					/* set by ; */
					/* used in */
	struct	part_block_cell	*children_part_block_list;
					/* list of cells for the children partition blocks in the */
					/* partition block tree; */
					/* set by ; */
					/* used in */
	struct	st_bucket_cell	*aggr_global_trans_list;
					/* list of symbol table bucket cells for the aggregated global */
					/* transitions labeling the branches in the partition block tree */
					/* towards the children partition blocks; */
					/* set by ; */
					/* used in */
}			PART_BLOCK;


 /* symbol table: global state cell */
typedef	struct	g_state_cell
{
	struct	gst_bucket	*global_state;
					/* pointer to the global state space table bucket of the global */
					/* state; */
					/* set by scan_state_space() of i-analyzer.c; */
					/* used in verify_int_bisim_eq() of i-analyzer.c */
	struct	g_state_cell	*prev_g_state_cell,
				*next_g_state_cell;
					/* pointers to the previous and the next global state cells */
}			G_STATE_CELL;


 /* symbol table: aggregated rate cell */
typedef	struct	aggr_rate_cell
{
	long double		aggr_rate;
					/* value of the aggregated rate; */
					/* set by ; */
					/* used in */
	char			rate_index;
					/* index of the aggregated rate; */
					/* set by ; */
					/* used in */
	struct	aggr_rate_cell	*next_aggr_rate_cell;
					/* pointer to the next aggregated rate cell */
}			AGGR_RATE_CELL;


 /* symbol table: inequivalent global state cell */
typedef	struct	inequiv_gs_cell
{
	struct	gst_bucket	*outer_global_state,
				*inner_global_state;
					/* pointers to the global state space table buckets for the two */
					/* inequivalent global states; */
					/* set by ; */
					/* used in */
	struct	st_bucket	*disting_act_type;
					/* pointer to the symbol table bucket for the action type */
					/* distinguishing the two inequivalent global states */
					/* (NULL if both inequivalent global states belong to the same */
					/* partition block); */
					/* set by ; */
					/* used in */
	long double		disting_aggr_rate,
					/* aggregated rate distinguishing the two inequivalent global */
					/* states with respect to the distinguishing action type; */
					/* set by ; */
					/* used in */
				outer_incoming_rate;
					/* rate with which the outer global state is reached from its */
					/* father; */
					/* set by ; */
					/* used in */
	char			disting_rate_index,
					/* index of the distinguishing aggregated rate (and the incoming */
					/* rate); */
					/* set by ; */
					/* used in */
				left;
					/* flag indicating whether the global states reached by the outer */
					/* inequivalent global state have to be considered in the outer */
					/* loop, hence the negation has not to be applied to the */
					/* distinguishing formula for the two inequivalent global states; */
					/* set by ; */
					/* used in */
	struct	gst_bucket	*curr_outer_reached_global_state;
					/* pointer to the global state space table bucket for the reached */
					/* global state in the outer loop currently considered for the */
					/* computation of the subformulas forming the distinguishing */
					/* formula for the two inequivalent global states; */
					/* set by ; */
					/* used in */
	long double		curr_outer_reached_global_state_incoming_rate;
					/* rate with which the reached global state in the outer loop */
					/* currently considered for the computation of the subformulas */
					/* forming the distinguishing formula for the two inequivalent */
					/* global states is reached from its father; */
					/* set by ; */
					/* used in */
	int			disting_sub_formula_num,
					/* number of subformulas forming the distinguishing formula for */
					/* the two inequivalent global states w.r.t. the currently */
					/* considered reached global state in the outer loop; */
					/* set by ; */
					/* used in */
				min_disting_sub_formula_num;
					/* minimum number of subformulas forming the distinguishing */
					/* formula for the two inequivalent global states w.r.t. all the */
					/* reached global states in the outer loop; */
					/* set by ; */
					/* used in */
	struct	st_bucket_cell	*disting_sub_formula_list,
					/* list of pointers to the symbol table buckets for the */
					/* subformulas forming the distinguishing formula for the two */
					/* inequivalent global states w.r.t. the currently considered */
					/* reached global state in the outer loop; */
					/* set by ; */
					/* used in */
				*min_disting_sub_formula_list;
					/* minimum list of pointers to the symbol table buckets for the */
					/* subformulas forming the distinguishing formula for the two */
					/* inequivalent global states w.r.t. all the reached global states */
					/* in the outer loop; */
					/* set by ; */
					/* used in */
	struct	inequiv_gs_cell	*father_inequiv_gs_cell,
					/* pointer to the cell for the two inequivalent global states */
					/* reaching these two inequivalent global states; */
					/* set by ; */
					/* used in */
				*prev_inequiv_gs_cell,
				*next_inequiv_gs_cell;
					/* pointers to the previous and the next inequivalent global state */
					/* cells */
}			INEQUIV_GS_CELL;


 /* symbol table: reward measure */
typedef	struct	rew_measure
{
	struct	st_bucket3_cell	*type_yb_list;
					/* list of triple symbol table bucket cells for the */
					/* type-yield-bonus triples defining the reward measure; */
					/* set by append_absent_yb_measure() of rew-parser.y; */
					/* used in check_yb_measure_uni_viol() of rew-parser.y */
	long double		value;
					/* value of the reward measure; */
					/* set by update_yb_measure() of markov-solver.c; */
					/* used in print_yb_measure() of markov-solver.c */
}			REW_MEASURE;


 /* symbol table: simulation measure */
typedef	struct	sim_measure
{
	char			measure_index;
					/* sort of simulation measure; */
					/* set in sim-parser.y; */
					/* used in append_absent_sim_measure() of sim-parser.y and in */
					/* update_sample_clock() and infer_measure() of simulator.c */
	struct	st_bucket	*measure_expr,
					/* pointer to the symbol table bucket for the expression forming */
					/* the simulation measure; */
					/* set at parsing time by set_expr_bucket() of symbol-handler.c; */
					/* used in append_absent_sim_measure() of sim-parser.y and in */
					/* update_sample_clock() and infer_measure() of simulator.c */
				*interval_begin,
				*interval_end,
					/* pointers to the symbol table buckets for the expressions */
					/* forming the begin and the end of the observation interval for */
					/* the simulation measure; */
					/* set in sim-parser.y; */
					/* used in append_absent_sim_measure() of sim-parser.y and in */
					/* update_sample_clock() and infer_measure() of simulator.c */
				*sub_interval_width;
					/* pointer to the symbol table bucket for the expression forming */
					/* the width of the observation subintervals for the simulation */
					/* measure */
					/* (measure_index = DISTR); */
					/* set in sim-parser.y; */
					/* used in append_absent_sim_measure() of sim-parser.y and in */
					/* update_sample_clock() and infer_measure() of simulator.c */
	long double		*value[MAX_SIM_RUN_NUM];
					/* matrix of simulation measure values at the end of each */
					/* observation subinterval */
					/* (measure_index = DISTRIBUTION); */
					/* allocated by alloc_sim_measure() of memory-handler.c; */
					/* set by update_sample_clock() of simulator.c; */
					/* used in infer_measure() of simulator.c */
}			SIM_MEASURE;


 /* symbol table: term parse information */
typedef	struct	term_parse_info
{
	char			*state_lexeme;
					/* lexeme of the state associated with the term; */
					/* set in aemilia-parser.y; */
					/* used in handle_behav_def3() and handle_term() of */
					/* aemilia-parser.y */
	struct	st_bucket_cell	*actual_par_list,
					/* list of actual parameters of the term if it is a behavior */
					/* invocation */
					/* (value_passing = TRUE); */
					/* set in aemilia-parser.y; */
					/* used in handle_term() of aemilia-parser.y */
				*crit_local_var_list;
					/* list of critical local variables occurring in the term */
					/* (value_passing = TRUE); */
					/* set in aemilia-parser.y; */
					/* used in handle_behav_def3() and handle_term() of */
					/* aemilia-parser.y */
}			TERM_PARSE_INFO;


 /* symbol table: action parse information */
typedef	struct	act_parse_info
{
	struct	st_bucket	*action;
					/* pointer to the symbol table bucket of the action; */
					/* set in aemilia-parser.y; */
					/* used in handle_term() of aemilia-parser.y */
	struct	st_bucket_cell	*local_var_list;
					/* list of local variables occurring in the action */
					/* (value_passing = TRUE); */
					/* set in aemilia-parser.y; */
					/* used in handle_term() of aemilia-parser.y */
}			ACT_PARSE_INFO;


 /* symbol table: expression parse information */
typedef	struct	expr_parse_info
{
	struct	st_bucket	*expr;
					/* pointer to the symbol table bucket of the expression; */
					/* set in aemilia-parser.y; */
					/* used in handle_f_par_list2(), handle_term(), and */
					/* handle_output_list() of aemilia-parser.y */
	struct	st_bucket_cell	*local_var_list;
					/* list of local variables occurring in the expression */
					/* (value_passing = TRUE); */
					/* set in aemilia-parser.y; */
					/* used in handle_term() of aemilia-parser.y */
}			EXPR_PARSE_INFO;


 /* symbol table: list/array/record expression parse information */
typedef	struct	lar_parse_info
{
	struct	value_cell	*struct_value;
					/* pointer to the elements of the list/array/record expression; */
					/* set and used in aemilia-parser.y */
	int			value_num;
					/* number of elements in the list/array/record expression; */
					/* set and used in aemilia-parser.y */
	struct	st_bucket_cell	*local_var_list;
					/* list of local variables occurring in the list/array/record */
					/* expression; */
					/* set and used in aemilia-parser.y */
}			LAR_PARSE_INFO;


 /* data type table: data type table bucket */
typedef	struct	dtt_bucket
{
	char			*data_type_lexeme;
					/* lexeme of the data type in concise functional notation; this */
					/* information is not stored in the symbol table bucket to avoid */
					/* clashes with the symbols in the specs being parsed; */
					/* set by compute_pot_move() and norm_pot_move() of */
					/* aemilia-compiler.c, by handle_behav_def3(), */
					/* handle_struct_data_type_decl(), and handle_term() of */
					/* aemilia-parser.y, and by set_expr_bucket(), */
					/* build_variable_lexeme(), build_par_behav_invoc_lexeme(), */
					/* build_expr_lexeme(), build_action_lexeme(), */
					/* build_par_param_list_lexeme(), and build_param_list_lexeme() of */
					/* symbol-handler.c; */
					/* used in check_expr_type(), check_sem_error(), and */
					/* check_rel_type() of aemilia-checker.c, in open_sem_model(), */
					/* gen_sem_model(), gen_sem_model_trans(), */
					/* build_ext_state_lexeme(), compute_repr_alias_state(), */
					/* print_assign_list(), remove_vanishing_state(), print_psm(), */
					/* sort_move(), compute_pot_move(), norm_pot_move(), */
					/* check_act_type_in_act_type_set(), and apply_rel_fun() of */
					/* aemilia-compiler.c, in handle_behav_def3(), */
					/* handle_struct_data_type_decl(), handle_term(), check_id(), */
					/* build_var_list(), and check_var_unique() of aemilia-parser.y, */
					/* in set_variable_bucket(), set_expr_bucket(), rewrite_expr(), */
					/* build_par_behav_invoc_lexeme(), build_expr_lexeme(), */
					/* build_action_lexeme(), build_param_list_lexeme(), */
					/* duplicate_record_expr(), and init_struct_var() of */
					/* symbol-handler.c, in search_unloc_var() of sim-parser.y, in */
					/* verify_int_bisim_eq() and compute_aggr_move() of i-analyzer.c, */
					/* in verify_fun_formula() and verify_fun_eq_pre() of */
					/* cwb-nc-connector.c, in print_prob() and print_yb_measure() of */
					/* markov-solver.c, and in open_trace_file(), eval_assign_list(), */
					/* eval_expr(), assign_expr_eval(), copy_value(), */
					/* compare_struct_value(), and infer_measure() of simulator.c */
	struct	dtt_bucket	*next_dtt_bucket;
					/* pointer to the next data type table bucket */
}			DTT_BUCKET;


 /* code table: code table bucket */
typedef	struct	cdt_bucket
{
	char			*code_lexeme;
					/* lexeme of the code of the symbol used in the concise */
					/* representation of the local states; */
					/* set by encode_state_comp() of aemilia-compiler.c; */
					/* used in handle_term() of aemilia-parser.y and in */
					/* handle_init_state(), build_unfolded_state_lexeme(), */
					/* compute_pot_move(), and norm_pot_move() of aemilia-compiler.c */
	struct	st_bucket	*encoded_local_state_comp;
					/* pointer to the symbol table bucket for the encoded symbol; */
					/* this is needed when decoding; */
					/* set by encode_state_comp() of aemilia-compiler.c; */
					/* used in decode_state_comp() of aemilia-compiler.c */
	struct	cdt_bucket	*next_cdt_bucket;
					/* pointer to the next code table bucket */
}			CDT_BUCKET;


 /* local state table: local state table bucket cell */
typedef	struct	lst_bucket_cell
{
	struct	lst_bucket	*lst_bucket;
					/* pointer to the local state table bucket */
	struct	lst_bucket_cell	*next_lst_bucket_cell;
					/* pointer to the next local state table bucket cell */
}			LST_BUCKET_CELL;


 /* local state table: local state table bucket */
typedef	struct	lst_bucket
{
	char			*local_state_lexeme;
					/* lexeme of the local states in concise prefix notation; */
					/* set by handle_behav_def3() and handle_term() of */
					/* aemilia-parser.y and by compute_repr_alias_state() and */
					/* build_unfolded_state_lexeme() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), and print_psm() */
					/* of aemilia-compiler.c and in print_prob() of markov-solver.c */
	int			local_state_no;
					/* serial number of the local state; */
					/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
					/* used in gen_sem_model() and print_psm() of aemilia-compiler.c */
					/* and in build_vector(), build_diag_elem(), print_distr(), */
					/* print_prob(), compute_hsmc_norm_factor(), and */
					/* compute_yb_measure() of markov-solver.c */
	struct	st_bucket_cell	*state_var_assign_list;
					/* list of concrete assignments to the variables of the local */
					/* state */
					/* (value_passing = CONCRETE_VP); */
					/* set by ; */
					/* used in */
	struct	l_trans_cell	*local_trans_list;
					/* pointer to the root of the semantic model of the term */
					/* corresponding to the state, with transitions lexicographically */
					/* sorted according to action types and priorities in the case of */
					/* application of the Paige-Tarjan algorithm; */
					/* set by select_move(), remove_vanishing_state(), and */
					/* remove_immediate_selfloop() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), print_psm(), */
					/* sort_move(), and invert_ism() of aemilia-compiler.c, in */
					/* verify_int_bisim_eq(), scan_state_space(), and */
					/* compute_aggr_move() of i-analyzer.c, in build_matrix(), */
					/* build_vector(), print_distr(), compute_hsmc_norm_factor(), */
					/* update_hsmc_norm_factor(), compute_yb_measure(), and */
					/* update_yb_measure() of markov-solver.c, and in simulate() of */
					/* simulator.c */
	char			local_trans_considered;
					/* flag indicating whether information about the transitions of */
					/* the state has been generated, printed, or scanned; */
					/* set by gen_sem_model_trans() and print_psm() of */
					/* aemilia-compiler.c, by scan_state_space() of i-analyzer.c, and */
					/* by build_matrix(), print_distr(), compute_hsmc_norm_factor(), */
					/* and compute_yb_measure() of markov-solver.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), and print_psm() */
					/* of aemilia-compiler.c, in scan_state_space() of i-analyzer.c, */
					/* and in build_matrix(), print_distr(), */
					/* compute_hsmc_norm_factor(), and compute_yb_measure() of */
					/* markov-solver.c */
	struct	lst_bucket	*next_lst_bucket;
					/* pointer to the next local state table bucket */
}			LST_BUCKET;


 /* local state table: local transition cell */
typedef	struct	l_trans_cell
{
	struct	st_bucket	*action;
					/* pointer to the symbol table bucket for the action; */
					/* set by remove_vanishing_state(), norm_weight(), melt_move(), */
					/* compute_pot_move(), and norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), remove_vanishing_state(), print_psm(), */
					/* norm_weight(), melt_move(), select_move(), sort_move(), */
					/* compute_pot_move(), select_passive_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c, in compute_aggr_move() */
					/* of i-analyzer.c, in update_hsmc_norm_factor() and */
					/* update_yb_measure() of markov-solver.c, and in simulate(), */
					/* check_open(), choose_exp_timed_trans(), and choose_imm_trans() */
					/* of simulator.c */
	struct	vp_info		*value_passing_info;
					/* value passing information concerning the move; */
					/* set by gen_sem_model_trans(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), */
					/* remove_vanishing_state(), melt_move(), select_move(), */
					/* sort_move(), compute_pot_move(), and norm_pot_move() of */
					/* aemilia-compiler.c and in choose_exp_timed_trans() and */
					/* choose_imm_trans() of simulator.c */
	union	reward_info	reward_info;
					/* list of yield-bonus reward pairs or samples associated with */
					/* the local transition */
					/* (option_index = SM_GAUSS_ELIM, SM_ASSOR, TM_UNIF, SIM); */
					/* set by ; */
					/* used in */
	struct	lst_bucket	*der_local_state;
					/* pointer to the local state table bucket of the derivative */
					/* local state; */
					/* set by gen_sem_model_trans(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), */
					/* remove_vanishing_state(), print_psm(), */
					/* remove_immediate_selfloop(), melt_move(), select_move(), */
					/* sort_move(), invert_ism(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c, in */
					/* verify_int_bisim_eq(), scan_state_space(), and */
					/* compute_aggr_move() of i-analyzer.c, in */
					/* build_matrix(), build_vector(), print_distr(), */
					/* compute_hsmc_norm_factor(), update_hsmc_norm_factor(), */
					/* compute_yb_measure(), and update_yb_measure() of */
					/* markov-solver.c, and in choose_exp_timed_trans() and */
					/* choose_imm_trans() of simulator.c */
	char			or_marked;
					/* flag related to the normalization of the rates of the global */
					/* transitions sharing the same or-interaction, which indicates */
					/* whether the local transition is a duplicate of the */
					/* or-interaction or it is a lower priority passive local */
					/* transition synchronizing with a duplicate of the or-interaction */
					/* set by ; */
					/* used in */
	struct	l_trans_cell	*next_l_trans_cell;
					/* pointer to the next local transition cell */
}			L_TRANS_CELL;


 /* local state table: local action type cell */
typedef	struct	l_act_type_cell
{
	struct	st_bucket	*local_act_type,
					/* pointer to the symbol table bucket for the local action type; */
					/* set by ; */
					/* used in */
				*basic_local_act_type;
					/* pointer to the symbol table bucket for the local action type */
					/* without the priority level in its lexeme (NULL if the local */
					/* action type is not a renaming one); */
					/* set by ; */
					/* used in */
	int			priority,
					/* priority level of the local action type (negative if the local */
					/* action type is passive); */
					/* set by ; */
					/* used in */
				passive_or_priority;
					/* priority level of the passive local uni-interaction type that */
					/* is this local action type or synchronizes with this local */
			       		/* action type in case it is a duplicate of an or-interaction type */
					/* (0 if it does not apply); */
					/* set by ; */
					/* used in */
	char			synchronization;
					/* flag indicating whether the local action type stems from a */
					/* synchronization; */
					/* set by ; */
					/* used in */
	struct	lst_bucket_cell	*enabling_local_state_list;
					/* pointer to the list of local states enabling the local action */
					/* type; */
					/* set by ; */
					/* used in */
	struct	l_nondet_cell	*local_nondet_list;
					/* pointer to the list of local states nondeterministically */
					/* reached by executing the local action from the same enabling */
					/* local state; */
					/* set by ; */
					/* used in */
	struct	l_act_type_cell	*next_l_act_type_cell;
					/* pointer to the next local action type cell */
}			L_ACT_TYPE_CELL;


 /* local state table: local nondeterminism cell */
typedef	struct	l_nondet_cell
{
	struct	lst_bucket	*enabling_local_state;
					/* pointer to the local state space table bucket for the local */
					/* state at which the local nondeterminism w.r.t. a local action */
					/* type takes place; */
					/* set by ; */
					/* used in */
	struct	lst_bucket_cell	*reached_local_state_list;
					/* pointer to the list of local states reached when executing the */
					/* local action type in the enabling local state; */
					/* set by ; */
					/* used in */
	struct	l_nondet_cell	*next_l_nondet_cell;
					/* pointer to the next local nondeterminism cell */
}			L_NONDET_CELL;


 /* global state table: global state table bucket cell */
typedef	struct	gst_bucket_cell
{
	struct	gst_bucket	*gst_bucket;
					/* pointer to the global state table bucket */
	struct	gst_bucket_cell	*next_gst_bucket_cell;
					/* pointer to the next global state table bucket cell */
}			GST_BUCKET_CELL;


 /* global state table: double global state table bucket cell */
typedef	struct	gst_buckt2_cell
{
	struct	gst_bucket	*gst_bucket1,
					/* pointer to the first global state table bucket */
				*gst_bucket2;
					/* pointer to the second global state table bucket */
	struct	gst_buckt2_cell	*next_gst_bucket2_cell;
					/* pointer to the next double global state table bucket cell */
}			GST_BUCKT2_CELL;


 /* global state table: global state table bucket */
typedef	struct	gst_bucket
{
	struct	lst_bucket	**local_state_vector;
					/* vector of pointers to the local state buckets for the local */
					/* states forming the global state; */
					/* set by handle_behav_def3() and handle_term() of */
					/* aemilia-parser.y */
					/* and by compute_repr_alias_state() and */
					/* build_unfolded_state_lexeme() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), and print_psm() */
					/* of aemilia-compiler.c and in print_prob() of markov-solver.c */
	int			global_state_no;
					/* serial number of the global state; */
					/* set by gen_sem_model() and print_psm() of aemilia-compiler.c; */
					/* used in gen_sem_model() and print_psm() of aemilia-compiler.c */
					/* and in build_vector(), build_diag_elem(), print_distr(), */
					/* print_prob(), compute_hsmc_norm_factor(), and */
					/* compute_yb_measure() of markov-solver.c */
	char			table_no;
					/* number of the state space table to which the state belongs; */
					/* this is needed by the Paige-Tarjan algorithm; */
					/* set by alloc_sst_bucket() of memory-handler.c; */
					/* used in verify_int_bisim_eq() of i-analyzer.c */
	struct	g_trans_cell	*global_trans_list;
					/* pointer to the root of the semantic model of the term */
					/* corresponding to the state, with transitions lexicographically */
					/* sorted according to action types and priorities in the case of */
					/* application of the Paige-Tarjan algorithm; */
					/* set by select_move(), remove_vanishing_state(), and */
					/* remove_immediate_selfloop() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), print_psm(), */
					/* sort_move(), and invert_ism() of aemilia-compiler.c, in */
					/* verify_int_bisim_eq(), scan_state_space(), and */
					/* compute_aggr_move() of i-analyzer.c, in build_matrix(), */
					/* build_vector(), print_distr(), compute_hsmc_norm_factor(), */
					/* update_hsmc_norm_factor(), compute_yb_measure(), and */
					/* update_yb_measure() of markov-solver.c, and in simulate() of */
					/* simulator.c */
	char			global_trans_considered,
					/* flag indicating whether information about the transitions of */
					/* the state has been generated, printed, or scanned; */
					/* set by gen_sem_model_trans() and print_psm() of */
					/* aemilia-compiler.c, by scan_state_space() of i-analyzer.c, and */
					/* by build_matrix(), print_distr(), compute_hsmc_norm_factor(), */
					/* and compute_yb_measure() of markov-solver.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), and print_psm() */
					/* of aemilia-compiler.c, in scan_state_space() of i-analyzer.c, */
					/* and in build_matrix(), print_distr(), */
					/* compute_hsmc_norm_factor(), */
					/* and compute_yb_measure() of markov-solver.c */
				global_trans_need_norm;
					/* flag indicating whether the global transitions of the global */
					/* state need to be renormalized after being generated */
					/* (option_index == SIM); */
					/* set by ; */
					/* used in ; */
	struct	gst_bucket_cell	*reaching_global_state_list;
					/* list of states reaching the state; this is needed to apply the */
					/* Paige-Tarjan algorithm and to carry out the vanishing state */
					/* elimination before proceeding with the Markovian analysis (in */
					/* which case the selfloops are not recorded here); */
					/* set by gen_sem_model(), remove_vanishing_state(), and */
					/* invert_ism() of aemilia-compiler.c; */
					/* used in gen_sem_model() and remove_vanishing_state() of */
					/* aemilia-compiler.c and in verify_int_bisim_eq() of i-analyzer.c */
	struct	gst_bucket	*next_gst_bucket;
					/* pointer to the next global state table bucket */
}			GST_BUCKET;


 /* global state table: global transition cell */
typedef	struct	g_trans_cell
{
	struct	st_bucket	*action;
					/* pointer to the symbol table bucket for the action; */
					/* set by remove_vanishing_state(), norm_weight(), melt_move(), */
					/* compute_pot_move(), and norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), remove_vanishing_state(), print_psm(), */
					/* norm_weight(), melt_move(), select_move(), sort_move(), */
					/* compute_pot_move(), select_passive_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c, in compute_aggr_move() */
					/* of i-analyzer.c, in update_hsmc_norm_factor() and */
					/* update_yb_measure() of markov-solver.c, and in simulate(), */
					/* check_open(), choose_exp_timed_trans(), and choose_imm_trans() */
					/* of simulator.c */
	struct	vp_info		*value_passing_info;
					/* value passing information concerning the move; */
					/* set by gen_sem_model_trans(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), */
					/* remove_vanishing_state(), melt_move(), select_move(), */
					/* sort_move(), compute_pot_move(), and norm_pot_move() of */
					/* aemilia-compiler.c and in choose_exp_timed_trans() and */
					/* choose_imm_trans() of simulator.c */
	union	reward_info	reward_info;
					/* list of yield-bonus reward pairs or samples associated with */
					/* the local transition */
					/* (option_index = SM_GAUSS_ELIM, SM_ASSOR, TM_UNIF, SIM); */
					/* set by ; */
					/* used in */
	struct	gst_bucket	*der_global_state;
					/* pointer to the global state table bucket of the derivative */
					/* global state; */
					/* set by gen_sem_model_trans(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), */
					/* remove_vanishing_state(), print_psm(), */
					/* remove_immediate_selfloop(), melt_move(), select_move(), */
					/* sort_move(), invert_ism(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c, in */
					/* verify_int_bisim_eq(), */
					/* scan_state_space(), and compute_aggr_move() of i-analyzer.c, in */
					/* build_matrix(), build_vector(), print_distr(), */
					/* compute_hsmc_norm_factor(), update_hsmc_norm_factor(), */
					/* compute_yb_measure(), and update_yb_measure() of */
					/* markov-solver.c, and in choose_exp_timed_trans() and */
					/* choose_imm_trans() of simulator.c */
	struct	g_trans_cell	*next_g_trans_cell;
					/* pointer to the next global transition cell */
}			G_TRANS_CELL;



 /* local and global state table: value passing information */
typedef	struct	vp_info
{
	struct	st_bucket	*guard;
					/* pointer to the symbol table bucket for the expression */
					/* representing the boolean guard; */
					/* set by compute_pot_move() and norm_pot_move() of */
					/* aemilia-compiler.c; */
					/* used in gen_sem_model(), melt_move(), and norm_pot_move() of */
					/* aemilia-compiler.c and in choose_exp_timed_trans() and */
					/* choose_imm_trans() of simulator.c */
	struct	st_bucket_cell	*assign_list;
					/* pointer to the list of assignments; */
					/* set by gen_sem_model_trans(), compute_pot_move(), and */
					/* norm_pot_move() of aemilia-compiler.c; */
					/* used in gen_sem_model(), gen_sem_model_trans(), melt_move(), */
					/* and norm_pot_move() of aemilia-compiler.c and in */
					/* choose_exp_timed_trans() and choose_imm_trans() of simulator.c */
	int			input_output_assign_num;
					/* number of input output assignments at the beginning of */
					/* assign_list; */
					/* set by compute_pot_move () and norm_pot_move() of */
					/* aemilia-compiler.c; */
					/* used in norm_pot_move() of aemilia-compiler.c and in */
					/* choose_exp_timed_trans() and choose_imm_trans() of simulator.c */
}			VP_INFO;


 /* global state table: random cell */
typedef	struct	random_cell
{
	struct	g_trans_cell	*global_transition;
					/* transition under consideration at simulation time; */
					/* set and used by choose_exp_timed_trans() and choose_imm_trans() */
					/* of simulator.c */
	long double		duration;
					/* random duration of the transition if exponentially timed; */
					/* set and used by choose_exp_timed_trans() of simulator.c */
	struct	random_cell	*next_random_cell;
					/* pointer to the next random cell */
}			RANDOM_CELL;
