/****************************************************************/
/*								*/
/*								*/
/*                     aemilia_parser.y				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module implements the parser for .aem spec files. */
/* This module contains the following function: */
/* - parse_aemilia(): It drives the parsing of a .aem spec file. */
/* and the following auxiliary functions: */
/* - handle_archi_type_par_assign(): It contains the actions to be undertaken when parsing the assignment */
/*				     to a formal constant parameter of the architectural type. */
/* - handle_behavior_def_1(): It contains the actions to be undertaken when parsing the identifier of a */
/*			      behavior definition. */
/* - handle_behavior_def_2(): It contains the actions to be undertaken when parsing the formal variable */
/*			      parameters and the local variables of a behavior definition. */
/* - handle_behavior_def_3(): It contains the actions to be undertaken when parsing the EMPAgr term of a */
/*			      behavior definition. */
/* - handle_struct_data_type_decl(): It contains the actions to be undertaken when parsing a structured */
/*				     data type declaration. */
/* - handle_term(): It contains the actions to be undertaken when parsing a sequential EMPAgr term. */
/* - handle_input_act_type_par(): It contains the actions to be undertaken when parsing a parameter of an */
/*				  input action type. */
/* - handle_output_act_type_par(): It contains the actions to be undertaken when parsing a parameter of an */
/*				   output action type. */
/* - handle_interaction_decl(): It contains the actions to be undertaken when parsing an interaction */
/*				declaration. */
/* - expand_iterative_aei_decl(): It expands an iterative AEI declaration into the corresponding */
/*				  non-iterative AEI declarations. */
/* - handle_aei_decl(): It contains the actions to be undertaken when parsing a correct non-iterative AEI */
/*			declaration. */
/* - expand_iterative_ai_decl(): It expands an iterative architectural interaction declaration into the */
/*				 corresponding non-iterative architectural interaction declarations. */
/* - handle_architectural_interaction(): It contains the actions to be undertaken when parsing a correct */
/*					 non-iterative architectural interaction. */
/* - expand_iterative_attachment_decl(): It expands an iterative attachment declaration into the */
/*				         corresponding non-iterative attachment declarations. */
/* - attach_interactions(): It attaches to each other the two interactions of a correct non-iterative */
/*			    attachment. */
/* - check_active_attached(): It checks whether a passive and-interaction is attached to several active */
/*			      interactions. */
/* - check_output_interaction(): It checks for the occurrence of a correct output interaction in a */
/*				 non-iterative attachment. */
/* - check_input_interaction(): It checks for the occurrence of a correct input interaction in a */
/*				non-iterative attachment. */
/* - create_sync_act_types(): It creates the synchronization action types after parsing the attachments. */
/* - expand_iterative_hiding_decl(): It expands an iterative hiding declaration into the corresponding */
/*				     non-iterative hiding declarations. */
/* - handle_hiding(): It contains the actions to be undertaken when parsing a correct non-iterative */
/*		      hiding. */
/* - hide_internals(): It hides all the internal action types of an architectural type. */
/* - hide_aei_internals(): It hides all the internal action types of an AEI. */
/* - hide_interactions(): It hides all the interactions of an architectural type. */
/* - hide_aei_interactions(): It hides all the interactions of an AEI. */
/* - hide_all(): It hides all the action types of an architectural type. */
/* - hide_aei_all(): It hides all the action types of an AEI. */
/* - expand_iterative_restriction_decl(): It expands an iterative restriction declaration into the */
/*					  corresponding non-iterative restriction declarations. */
/* - handle_restriction(): It contains the actions to be undertaken when parsing a correct non-iterative */
/*			   restriction. */
/* - restrict_obs_internals(): It restricts all the observable internal action types of an architectural */
/*			       type. */
/* - restrict_aei_obs_internals(): It restricts all the observable internal action types of an AEI. */
/* - restrict_obs_interactions(): It restricts all the observable interactions of an architectural type. */
/* - restrict_aei_obs_interactions(): It restricts all the observable interactions of an AEI. */
/* - restrict_all_observables(): It restricts all the observable action types of an architectural type. */
/* - restrict_aei_all_observables(): It restricts all the observable action types of an AEI. */
/* - expand_iterative_renaming_decl(): It expands an iterative renaming declaration into the */
/*				       corresponding non-iterative renaming declarations. */
/* - handle_renaming_old(): It contains the actions to be undertaken when parsing an action type to be */
/*			    renamed within a correct non-iterative renaming. */
/* - handle_renaming_new(): It contains the actions to be undertaken when parsing an action type that */
/*			    renames another one within a correct non-iterative renaming. */
/* - check_nusmv_keywords(): It checks whether an identifier can occur in a NuSMV translation. */
/* - aemiliayyerror(): It disables the homonymous library function, in order not to interfere with TTGUI, */
/*		       and reports an error message whenever a syntax error occurs. The error message */
/*		       pinpoints more accurately the place where the error occurs than it would do if the */
/*		       error message were issued only after the detection of a synchronizing token. This */
/*		       also allows the case in which no synchronizing token can be found to be treated */
/*		       easily. */
/* - handle_sync_aemilia(): It handles the detection of a synchronizing token. */
/* - check_semantic_errors(): It checks for the presence of semantic errors. */
/* - check_behavior_reachability(): It checks whether a behavior defined within an AET is reachable from */
/*				    the initial behavior of the AET. */
/* - find_aei_lexeme(): It finds the lexeme of the AEI with a given serial number. */
/****************************************************************/


%{

/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<float.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/aemilia_scanner.h"
#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"
#include	"../headers/aemilia_compiler.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/string_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		parse_aemilia(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	ACTION_INDEX	action_index;
				/* sort of the action being parsed; */
				/* set by parse_aemilia() and at parsing time; */
				/* used in handle_term() and at parsing time */

PRIVATE	BOOLEAN		parsing_behavior_term,
				/* flag indicating whether the process term defining a behavior is being */
				/* parsed; this is needed to recognize the occurrences of the formal */
				/* constant parameters of an AET within the expressions occurring in the */
				/* behavior of the AET; */
				/* set by ; */
				/* used in */
			no_declarations;
				/* flag indicating whether some declarations are present in the */
				/* interaction section of an AET or in the behavioral variation section; */
				/* this is needed to detect the absence of the keyword void when no */
				/* declaration is present; */
				/* set by ; */
				/* used in */

PRIVATE	INTERACT_INDEX	interaction_index;
				/* sort of the interaction being parsed; */
				/* set by ; */
				/* used in */

PRIVATE	RATE_INDEX	rate_index;
				/* sort of the rate of the action being parsed; */
				/* set by ; */
				/* used in */

PRIVATE	ST_BUCKET	*aet,
				/* pointer to the symbol table bucket of the AET whose definition is */
				/* being parsed; this is needed to manage the dot notation for the names */
				/* of the variables declared in the AET header; */
				/* set by ; */
				/* used in */
			*behavior,
				/* pointer to the symbol table bucket of the behavior whose definition is */
				/* being parsed; this is needed to manage the dot notation for the names */
				/* of the variables declared in the behavior header; */
				/* set by parse_aemilia() and handle_behav_def1(); */
				/* used in check_id() */
			*priority,
				/* pointer to the symbol table bucket for the (possibly formal) priority */
				/* level of the action being parsed; */
				/* set by parse_aemilia() and at parsing time; */
				/* used at parsing time */
			*rate,
				/* pointer to the symbol table bucket for the (possibly formal) */
				/* rate/weight of the action being parsed; */
				/* set by parse_aemilia() and at parsing time; */
				/* used at parsing time */
			*aei;
				/* pointer to the symbol table bucket for the AEI whose hiding is being */
				/* parsed; this is needed to manage the dot notation for the name of the */
				/* action type to be hidden; */
				/* set by ; */
				/* used in */

PRIVATE	ST_BUCKET_CELL	*last_init_assign_cell,
				/* last cell for an initial assignment inserted into the list of initial */
				/* assignments of the architectural type; this is needed in order not to */
				/* reverse the order of the initial assignments; */
				/* set by parse_aemilia() and handle_archi_type_par_assign(); */
				/* used in handle_archi_type_par_assign() */
			*init_behav_actual_var_par_list,
				/* list of actual variable parameters of the initial behavior of the AEI */
				/* being parsed; */
				/* set by ; */
				/* used in */
			*last_init_behav_actual_var_par_cell,
				/* last cell for an actual variable parameter of the initial behavior of */
				/* the AEI being parsed inserted into init_behav_actual_var_par_list; this */
				/* is needed in order not to reverse the order of the actual variable */
				/* parameters; */
				/* set by ; */
				/* used in */
			*invoked_behavior_list,
				/* list of behaviors invoked within the term in the right hand side of the */
				/* defining equation for the behavior being parsed */
				/* set by ; */
				/* used in */
			*act_type_list,
				/* list of action types occurring in the AET behavior being parsed; this */
				/* is needed to check that the action types declared to be interactions */
				/* occur in the AET behavior; */
				/* set by ; */
				/* used in */
			*local_var_actual_par_list;
				/* list of the symbol table buckets for the local variables occurring in */
				/* the actual parameters of an output action or a behavior invocation; */
				/* this is needed to check that no local variables of a behavior are used */
				/* before it gets a value; */
				/* set by ; */
				/* used in */

PRIVATE	VP_INDEX	value_passing;
				/* sort of value passing determined by the formal variable parameter or */
				/* local variable being parsed; */
				/* set by parse_aemilia() and at parsing time; */
				/* used in handle_term() and at parsing time */


PRIVATE	void		handle_archi_type_par_assign(ST_BUCKET *,
						     EXPR_PARSE_INFO *),
			handle_behavior_def_1(ST_BUCKET **,
					      BOOLEAN),
			handle_behavior_def_2(ST_BUCKET *,
					      ST_BUCKET_CELL *,
					      ST_BUCKET_CELL *,
					      BOOLEAN),
			handle_behavior_def_3(ST_BUCKET *,
					      TERM_PARSE_INFO *),
			handle_struct_data_type_decl(EXPR_OP_INDEX,
					 	     EXPR **,
					 	     EXPR *,
					 	     EXPR_PARSE_INFO *,
					 	     ST_BUCKET_CELL *),
			handle_term(TERM_PARSE_INFO **,
				    EMPA_OP_INDEX,
				    TERM_PARSE_INFO *,
				    TERM_PARSE_INFO *,
				    ACT_PARSE_INFO *,
				    EXPR_PARSE_INFO *,
				    ST_BUCKET *,
				    ST_BUCKET_CELL *),
			handle_input_act_type_par(ST_BUCKET **,
						  ST_BUCKET_CELL **),
			handle_output_act_type_par(EXPR_PARSE_INFO *,
						   ST_BUCKET_CELL **),
			handle_interaction_decl(ST_BUCKET *,
					        INTERACT_INDEX);

PRIVATE	ST_BUCKET_CELL	*expand_iterative_aei_decl(ST_BUCKET *);

PRIVATE	void		handle_aei_decl(ST_BUCKET *,
				        ST_BUCKET *,
				        ST_BUCKET_CELL *);

PRIVATE	ST_BUCKET_CELL	*expand_iterative_ai_decl(void);

PRIVATE	void		handle_architectural_interaction(ST_BUCKET **,
							 BOOLEAN),
			expand_iterative_attachment_decl(ST_BUCKET *,
							 ST_BUCKET *),
			attach_interactions(ST_BUCKET *,
					    ST_BUCKET *,
					    BOOLEAN);

PRIVATE	BOOLEAN		check_active_attached(ST_BUCKET_CELL *);

PRIVATE	void		check_output_interaction(ST_BUCKET **,
						 BOOLEAN),
			check_input_interaction(ST_BUCKET **,
						BOOLEAN),
			create_sync_act_types(void),
			expand_iterative_hiding_decl(ST_BUCKET *),
			handle_hiding(ST_BUCKET *,
				      BOOLEAN),
			hide_internals(void),
			hide_aei_internals(ST_BUCKET *),
			hide_interactions(void),
			hide_aei_interactions(ST_BUCKET *),
			hide_all(void),
			hide_aei_all(ST_BUCKET *),
			expand_iterative_restriction_decl(ST_BUCKET *),
			handle_restriction(ST_BUCKET *,
					   BOOLEAN),
			restrict_obs_internals(void),
			restrict_aei_obs_internals(ST_BUCKET *),
			restrict_obs_interactions(void),
			restrict_aei_obs_interactions(ST_BUCKET *),
			restrict_all_observables(void),
			restrict_aei_all_observables(ST_BUCKET *),
			expand_iterative_renaming_decl(void),
			handle_renaming_old(ST_BUCKET **,
					    BOOLEAN),
			handle_renaming_new(ST_BUCKET *,
					    ST_BUCKET *,
					    BOOLEAN);

PRIVATE	BOOLEAN		check_nusmv_keywords(char *);

PRIVATE	int		aemiliayyerror(char *);

PRIVATE	void		handle_sync_aemilia(void),
			check_semantic_errors(void),
			check_behavior_reachability(ST_BUCKET *);

PRIVATE	char		*find_aei_lexeme(int);

%}


/****************************************************************/
/* 5. Definition of grammar symbol attributes.			*/
/****************************************************************/

%union
{
	BOOLEAN		boolean;
				/* flag indicating whether an iterative declaration is correct */
	ST_BUCKET_CELL	*st_bucket_cell;
				/* list of symbol table bucket cells for action types */
	ST_BUCKET2_CELL	*st_bucket2_cell;
				/* list of double symbol table bucket cells for attachments */
	ST_BUCKET	*st_bucket;
				/* pointer to the symbol table bucket for an identifier or a number */
	EXPR		*expr;
				/* information about a data type */
	TERM_PARSE_INFO	*term_parse_info;
				/* parse information about a term */
	ACT_PARSE_INFO	*action_parse_info;
				/* parse information about an action */
	EXPR_PARSE_INFO	*expr_parse_info;
				/* parse information about an expression */
	LAR_PARSE_INFO	*lar_parse_info;
				/* parse information about an expression list/array/record */
}


/****************************************************************/
/* 6. Definition of tokens.					*/
/****************************************************************/

%token	<st_bucket>		T_NUMBER		300	/* number */
%token	<st_bucket>		T_ID			301	/* identifier */
%token	<st_bucket>		T_ARCHITECTURAL_TYPE	400	/* keyword */
%token	<st_bucket>		T_ARCHI_ELEM_TYPES	401	/* keyword */
%token	<st_bucket>		T_ELEM_TYPE		402	/* keyword */
%token	<st_bucket>		T_BEHAVIOR		403	/* keyword */
%token	<st_bucket>		T_INPUT_INTERACTIONS	404	/* keyword */
%token	<st_bucket>		T_OUTPUT_INTERACTIONS	405	/* keyword */
%token	<st_bucket>		T_UNI			406	/* keyword */
%token	<st_bucket>		T_AND			407	/* keyword */
%token	<st_bucket>		T_OR			408	/* keyword */
%token	<st_bucket>		T_ARCHI_TOPOLOGY	409	/* keyword */
%token	<st_bucket>		T_ARCHI_ELEM_INSTANCES	410	/* keyword */
%token	<st_bucket>		T_ARCHI_INTERACTIONS	411	/* keyword */
%token	<st_bucket>		T_ARCHI_ATTACHMENTS	412	/* keyword */
%token	<st_bucket>		T_FROM			413	/* keyword */
%token	<st_bucket>		T_TO			414	/* keyword */
%token	<st_bucket>		T_BEHAV_VARIATIONS	415	/* keyword */
%token	<st_bucket>		T_BEHAV_HIDINGS		416	/* keyword */
%token	<st_bucket>		T_HIDE			417	/* keyword */
%token	<st_bucket>		T_INTERNALS		418	/* keyword */
%token	<st_bucket>		T_INTERACTIONS		419	/* keyword */
%token	<st_bucket>		T_ALL			420	/* keyword */
%token	<st_bucket>		T_BEHAV_RESTRICTIONS	421	/* keyword */
%token	<st_bucket>		T_RESTRICT		422	/* keyword */
%token	<st_bucket>		T_OBS_INTERNALS		423	/* keyword */
%token	<st_bucket>		T_OBS_INTERACTIONS	424	/* keyword */
%token	<st_bucket>		T_ALL_OBSERVABLES	425	/* keyword */
%token	<st_bucket>		T_BEHAV_RENAMINGS	426	/* keyword */
%token	<st_bucket>		T_RENAME		427	/* keyword */
%token	<st_bucket>		T_AS			428	/* keyword */
%token	<st_bucket>		T_FOR_ALL		429	/* keyword */
%token	<st_bucket>		T_IN			430	/* keyword */
%token	<st_bucket>		T_END			431	/* keyword */
%token	<st_bucket>		T_CONST			432	/* keyword */
%token	<st_bucket>		T_LOCAL			433	/* keyword */
%token	<st_bucket>		T_STOP			434	/* keyword */
%token	<st_bucket>		T_EXP			436	/* keyword */
%token	<st_bucket>		T_INF			437	/* keyword */
%token	<st_bucket>		T_CHOICE		438	/* keyword */
%token	<st_bucket>		T_COND			439	/* keyword */
%token	<st_bucket>		T_VOID			440	/* keyword */
%token	<st_bucket>		T_PRIO			441	/* keyword */
%token	<st_bucket>		T_RATE			442	/* keyword */
%token	<st_bucket>		T_WEIGHT		443	/* keyword */
%token	<st_bucket>		T_INTEGER		444	/* keyword */
%token	<st_bucket>		T_REAL			445	/* keyword */
%token	<st_bucket>		T_MOD			446	/* keyword */
%token	<st_bucket>		T_MIN			447	/* keyword */
%token	<st_bucket>		T_MAX			448	/* keyword */
%token	<st_bucket>		T_ABS			449	/* keyword */
%token	<st_bucket>		T_CEIL			450	/* keyword */
%token	<st_bucket>		T_FLOOR			451	/* keyword */
%token	<st_bucket>		T_POWER			452	/* keyword */
%token	<st_bucket>		T_EPOWER		453	/* keyword */
%token	<st_bucket>		T_LOGE			454	/* keyword */
%token	<st_bucket>		T_LOG10			455	/* keyword */
%token	<st_bucket>		T_SQRT			456	/* keyword */
%token	<st_bucket>		T_SIN			457	/* keyword */
%token	<st_bucket>		T_COS			458	/* keyword */
%token	<st_bucket>		T_C_UNIFORM		459	/* keyword */
%token	<st_bucket>		T_ERLANG		460	/* keyword */
%token	<st_bucket>		T_GAMMA			461	/* keyword */
%token	<st_bucket>		T_EXPONENTIAL		462	/* keyword */
%token	<st_bucket>		T_WEIBULL		463	/* keyword */
%token	<st_bucket>		T_BETA			464	/* keyword */
%token	<st_bucket>		T_NORMAL		465	/* keyword */
%token	<st_bucket>		T_PARETO		466	/* keyword */
%token	<st_bucket>		T_B_PARETO		467	/* keyword */
%token	<st_bucket>		T_D_UNIFORM		468	/* keyword */
%token	<st_bucket>		T_BERNOULLI		469	/* keyword */
%token	<st_bucket>		T_BINOMIAL		470	/* keyword */
%token	<st_bucket>		T_POISSON		471	/* keyword */
%token	<st_bucket>		T_NEG_BINOMIAL		472	/* keyword */
%token	<st_bucket>		T_GEOMETRIC		473	/* keyword */
%token	<st_bucket>		T_PASCAL		474	/* keyword */
%token	<st_bucket>		T_BOOLEAN		475	/* keyword */
%token	<st_bucket>		T_TRUE			476	/* keyword */
%token	<st_bucket>		T_FALSE			477	/* keyword */
%token	<st_bucket>		T_LIST			478	/* keyword */
%token	<st_bucket>		T_LIST_CONS		479	/* keyword */
%token	<st_bucket>		T_FIRST			480	/* keyword */
%token	<st_bucket>		T_TAIL			481	/* keyword */
%token	<st_bucket>		T_CONCAT		482	/* keyword */
%token	<st_bucket>		T_INSERT		483	/* keyword */
%token	<st_bucket>		T_REMOVE		484	/* keyword */
%token	<st_bucket>		T_LENGTH		485	/* keyword */
%token	<st_bucket>		T_ARRAY			486	/* keyword */
%token	<st_bucket>		T_ARRAY_CONS		487	/* keyword */
%token	<st_bucket>		T_READ			488	/* keyword */
%token	<st_bucket>		T_WRITE			489	/* keyword */
%token	<st_bucket>		T_RECORD		490	/* keyword */
%token	<st_bucket>		T_RECORD_CONS		491	/* keyword */
%token	<st_bucket>		T_GET			492	/* keyword */
%token	<st_bucket>		T_PUT			493	/* keyword */
%token				ASSIGN			700	/* symbol ":=" */
%token				DOTDOT			701	/* symbol ".." */
%token				NE			702	/* symbol "!=" */
%token				LE			703	/* symbol "<=" */
%token				GE			704	/* symbol ">=" */
%token				AND			705	/* symbol "&&" */
%token				OR			706	/* symbol "||" */
%token				IMPL			707	/* symbol "->" */


/****************************************************************/
/* 7. Association of attributes with nonterminals.		*/
/****************************************************************/

%type	<boolean>		i_renaming	  /* body of an iterative renaming */
%type	<st_bucket_cell>	archi_par_list	  /* p.e. list of architectural type parameters */
%type	<st_bucket_cell>	archi_par_list1	  /* n.e. list of architectural type parameters */
%type	<st_bucket_cell>	aet_def_list	  /* list of AET definitions */
%type	<st_bucket_cell>	aet_par_list	  /* p.e. list of AET parameters */
%type	<st_bucket_cell>	aet_par_list1	  /* n.e. list of AET parameters */
%type	<st_bucket_cell>	behavior	  /* list of behavior definitions */
%type	<st_bucket_cell>	behav_def_list	  /* p.e. list of behavior definitions after the first one */
%type	<st_bucket_cell>	behav_def_list1	  /* n.e. list of behavior definitions after the first one */
%type	<st_bucket_cell>	f_beh_par_list	  /* p.e. list of formal parameters of the first behavior */
%type	<st_bucket_cell>	f_beh_par_list1	  /* n.e. list of formal parameters of the first behavior */
%type	<st_bucket_cell>	behav_par_list	  /* p.e. list of behavior formal parameters */
%type	<st_bucket_cell>	behav_par_list1	  /* n.e. list of behavior formal parameters */
%type	<st_bucket_cell>	behav_var_list	  /* p.e. list of behavior local variables */
%type	<st_bucket_cell>	behav_var_list1	  /* n.e. list of behavior local variables */
%type	<st_bucket_cell>	field_decl_list	  /* list of record field declarations */
%type	<st_bucket_cell>	behav_apar_list	  /* p.e. list of behavior actual parameters */
%type	<st_bucket_cell>	behav_apar_list1  /* n.e. list of behavior actual parameters */
%type	<st_bucket_cell>	act_input_output  /* p.e. list of action parameters */
%type	<st_bucket_cell>	act_input_list	  /* n.e. list of input action parameters */
%type	<st_bucket_cell>	act_output_list	  /* n.e. list of output action parameters */
%type	<st_bucket_cell>	aei_decl_list	  /* list of AEI declarations */
%type	<st_bucket_cell>	aei_decl	  /* AEI declaration */
%type	<st_bucket_cell>	aei_par_list	  /* p.e. list of AEI actual parameters */
%type	<st_bucket_cell>	aei_par_list1	  /* n.e. list of AEI actual parameters */
%type	<st_bucket_cell>	a_interact_list	  /* p.e. list of architectural interactions */
%type	<st_bucket_cell>	a_interact_list1  /* n.e. list of architectural interactions */
%type	<st_bucket_cell>	a_interact	  /* architectural interaction */
%type	<st_bucket2_cell>	i_attach	  /* body of an iterative attachment declaration */
%type	<st_bucket>		archi_par	  /* architectural type parameter */
%type	<st_bucket>		aet_def		  /* AET definition */
%type	<st_bucket>		aet_par		  /* AET formal parameter */
%type	<st_bucket>		first_behav_def	  /* definition of the first behavior */
%type	<st_bucket>		behav_def	  /* definition of a subsequent behavior */
%type	<st_bucket>		first_behav_par	  /* formal parameter of the first behavior */
%type	<st_bucket>		behav_par	  /* formal parameter of a subsequent behavior */
%type	<st_bucket>		behav_var	  /* behavior local variable */
%type	<st_bucket>		field_decl	  /* record field */
%type	<st_bucket>		s_aei_decl	  /* simple AEI declaration */
%type	<st_bucket>		i_aei_decl	  /* body of an iterative AEI declaration */
%type	<st_bucket>		s_a_interact	  /* simple architectural interaction */
%type	<st_bucket>		i_a_interact	  /* body of an iterative architectural interaction */
%type	<st_bucket>		i_hiding	  /* body of an iterative hiding */
%type	<st_bucket>		act_type_set_h	  /* action type set to be hidden */
%type	<st_bucket>		i_restriction	  /* body of an iterative restriction */
%type	<st_bucket>		act_type_set_r	  /* action type set to be restricted */
%type	<st_bucket>		iteration	  /* iteration in the topology section */
%type	<st_bucket>		add_iteration	  /* additional iteration in the topology section */
%type	<expr>			all_data_type	  /* arbitrary data type */
%type	<expr>			var_data_type	  /* data type for behavior parameters and variables */
%type	<expr>			num_data_type	  /* data type for priorities/rates/weights */
%type	<term_parse_info>	term		  /* term excluding behavior invocations */
%type	<term_parse_info>	term1		  /* term including behavior invocations */
%type	<term_parse_info>	term2		  /* term e.c.i. guarded by a boolean condition */
%type	<term_parse_info>	term2_list	  /* list of terms as above */
%type	<term_parse_info>	enclosed_term2	  /* term as above preceded by a comma */
%type	<expr_parse_info>	guard		  /* boolean condition of a term e.c.i. */
%type	<action_parse_info>	action		  /* action */
%type	<expr_parse_info>	poss_aei_index	  /* possible AEI index */
%type	<expr_parse_info>	aei_index	  /* AEI index */
%type	<expr_parse_info>	expr		  /* expression */
%type	<expr_parse_info>	expr_list	  /* expression list */
%type	<expr_parse_info>	expr_array	  /* expression array */
%type	<expr_parse_info>	expr_record	  /* expression record */
%type	<lar_parse_info>	struct_expr	  /* expression list/array/record */


/****************************************************************/
/* 8. Definition of operator precedence and associativity.	*/
/****************************************************************/

%nonassoc		ASSIGN
%left			AND
			OR
%right			'!'
%nonassoc		'='
			NE
			'<'
			LE
			'>'
			GE
%left			'+'
			'-'
%left			'*'
			'/'


/****************************************************************/
/* 9. Definition of the start symbol.				*/
/****************************************************************/

%start			archi_type


/****************************************************************/
/* 10. Definition of grammar rules.				*/
/****************************************************************/

%%

archi_type	:	archi_header archi_elem_types archi_topology behav_variations T_END
			    {
			    }
		|	error T_ARCHITECTURAL_TYPE
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_type
			    {
			    }
		|	error T_ARCHI_ELEM_TYPES
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_elem_types archi_topology behav_variations T_END
			    {
			    }
		|	error T_ARCHI_TOPOLOGY
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_topology behav_variations T_END
			    {
			    }
		|	error T_ARCHI_ELEM_INSTANCES
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_elem_insts archi_interacts archi_attachs behav_variations T_END
			    {
			    }
		|	error T_ARCHI_INTERACTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_interacts archi_attachs behav_variations T_END
			    {
			    }
		|	error T_ARCHI_ATTACHMENTS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			archi_attachs behav_variations T_END
			    {
			    }
		|	error T_BEHAV_VARIATIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			behav_variations T_END
			    {
			    }
		|	error T_BEHAV_HIDINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			behav_hidings behav_restricts behav_renamings T_END
			    {
			    }
		|	error T_BEHAV_RESTRICTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			behav_restricts behav_renamings T_END
			    {
			    }
		|	error T_BEHAV_RENAMINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			behav_renamings T_END
			    {
			    }
		|	error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			T_END
			    {
			    }
		;


archi_header	:	T_ARCHITECTURAL_TYPE T_ID
	     		    {
			      check_id(ARCHI_TYPE_ID_DEF,
				       &$2,
				       FALSE);
			      if ($2 != NULL)
			      {
			        $2->info = archi_type[spec_no]->info;
			        archi_type[spec_no] = $2;
			      }
	     		    }
	     		'(' archi_par_list ')'
			    {
			      $2->info.archi_type->formal_const_par_list = $5;
			    }
	     	;


archi_par_list	:	T_VOID
			    {
			      $$ = NULL;
			    }
		|	archi_par_list1
			    {
			      $$ = $1;
			    }
		;


archi_par_list1	:	archi_par
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	archi_par ',' archi_par_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1,
					                  $3);
			    }
		;


archi_par	:	T_CONST all_data_type T_ID
			    {
			      check_id(VAR_ID_DECL,
				       &$3,
				       FALSE);
			      store_var_decl($3,
					     FORMAL_CONST_PAR_ID,
					     $2,
					     value_passing);
			    }
			ASSIGN expr
			    {
			      handle_archi_type_par_assign($3,
							   $6);
			      $$ = $3;
			    }
	  	;


archi_elem_types:	T_ARCHI_ELEM_TYPES aet_def_list
			    {
			      archi_type[spec_no]->info.archi_type->aet_list = $2;
			    }
		|	error T_ELEM_TYPE
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			aet_def_list
			    {
			      archi_type[spec_no]->info.archi_type->aet_list = $4;
			    }
		;


aet_def_list	:	aet_def
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			      aet = NULL;
			    }
	 	|	aet_def aet_def_list
			    {
			      $$ = ($1 == NULL)?
				     $2:
				     alloc_st_bucket_cell($1,
					                  $2);
			      aet = NULL;
			    }
		|	error T_ELEM_TYPE
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			aet_def_list
			    {
			      $$ = $4;
			    }
		|	error T_ARCHI_TOPOLOGY
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = NULL;
			    }
		;


aet_def		:	T_ELEM_TYPE T_ID
			    {
			      check_id(AET_ID_DEF,
				       &$2,
				       FALSE);
			      aet = $2;
			      id_prefix_in_expr = aet;
			      act_type_list = NULL;
			    }
	 		'(' aet_par_list ')' behavior
			    {
			      interaction_index = INPUT_UNI;
			      no_declarations = TRUE;
			    }
	 		T_INPUT_INTERACTIONS interactions
			    {
			      interaction_index = OUTPUT_UNI;
			      no_declarations = TRUE;
			    }
			T_OUTPUT_INTERACTIONS interactions
			    {
			      $$ = $2;
			      if ($$ != NULL)
			        $$->info.aet = alloc_aet($5,
						         $7,
						         act_type_list);
			    }
		;


aet_par_list	:	T_VOID
			    {
			      $$ = NULL;
			    }
		|	aet_par_list1
			    {
			      $$ = $1;
			    }
		;


aet_par_list1	:	aet_par
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	aet_par ',' aet_par_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1,
						          $3);
			    }
		;


aet_par		:	T_CONST all_data_type T_ID
			    {
			      build_prefixed_id(aet,
						&$3);
			      check_id(VAR_ID_DECL,
				       &$3,
				       FALSE);
			      store_var_decl($3,
					     FORMAL_CONST_PAR_ID,
					     $2,
					     value_passing);
			      $$ = $3;
			    }
	  	;


behavior	:	T_BEHAVIOR first_behav_def behav_def_list
			    {
			      $$ = ($2 == NULL)?
				     $3:
				     alloc_st_bucket_cell($2,
							  $3);
			    }
		;


first_behav_def	:	T_ID
			    {
			      handle_behavior_def_1(&$1,
						    TRUE);
			    }
			'(' f_beh_par_list ';' behav_var_list ')'
			    {
			      handle_behavior_def_2($1,
						    $4,
						    $6,
						    TRUE);
			    }
			'=' term
			    {
			      handle_behavior_def_3($1,
						    $10);
			      $$ = $1;
			    }
		;


f_beh_par_list	:	T_VOID
			    {
			      $$ = NULL;
			    }
		|	f_beh_par_list1
			    {
			      $$ = $1;
			    }
		;


f_beh_par_list1	:	first_behav_par
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	first_behav_par ',' f_beh_par_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1,
					                  $3);
			    }
		;


first_behav_par	:	var_data_type T_ID
			    {
			      build_prefixed_id(behavior,
						&$2);
			      check_id(VAR_ID_DECL,
				       &$2,
				       FALSE);
			      store_var_decl($2,
					     FORMAL_VAR_PAR_ID,
					     $1,
					     value_passing);
			    }
			ASSIGN expr
			    {
			      if (($2 != NULL) &&
			          ($5 != NULL) &&
				  check_expr_all($5->expr,
						 NULL,
						 NULL,
						 $2,
						 ACTUAL_PAR_NOT_UNDECL_ID_FREE,
						 NO_ERROR,
						 ILL_TYPED_ASSIGN))
			      {
			        if (init_behav_actual_var_par_list == NULL)
			          last_init_behav_actual_var_par_cell = init_behav_actual_var_par_list =
				    alloc_st_bucket_cell($5->expr,
						         NULL);
			        else
			          last_init_behav_actual_var_par_cell =
				    last_init_behav_actual_var_par_cell->next_st_bucket_cell =
				      alloc_st_bucket_cell($5->expr,
						           NULL);
			      }
			      $$ = $2;
			    }
	  	;


behav_def_list	:
			    {
			      $$ = NULL;
			    }
		|	behav_def_list1
			    {
			      $$ = $1;
			    }
		;


behav_def_list1	:	behav_def
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	behav_def behav_def_list1
			    {
			      $$ = ($1 == NULL)?
				     $2:
				     alloc_st_bucket_cell($1,
					                  $2);
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			behav_def_list1
			    {
			      $$ = $4;
			    }
		|	error T_INPUT_INTERACTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = NULL;
			    }
		;


behav_def	:	';' T_ID
			    {
			      handle_behavior_def_1(&$2,
						    FALSE);
			    }
			'(' behav_par_list ';' behav_var_list ')'
			    {
			      handle_behavior_def_2($2,
						    $5,
						    $7,
						    FALSE);
			    }
			'=' term
			    {
			      handle_behavior_def_3($2,
						    $11);
			      $$ = $2;
			    }
		;


behav_par_list	:	T_VOID
			    {
			      $$ = NULL;
			    }
		|	behav_par_list1
			    {
			      $$ = $1;
			    }
		;


behav_par_list1	:	behav_par
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	behav_par ',' behav_par_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1,
					                  $3);
			    }
		;


behav_par	:	var_data_type T_ID
			    {
			      build_prefixed_id(behavior,
						&$2);
			      check_id(VAR_ID_DECL,
				       &$2,
				       FALSE);
			      store_var_decl($2,
					     FORMAL_VAR_PAR_ID,
					     $1,
					     value_passing);
			      $$ = $2;
			    }
	  	;


behav_var_list	:	T_VOID
			    {
			      $$ = NULL;
			    }
		|	behav_var_list1
			    {
			      $$ = $1;
			    }
		;


behav_var_list1	:	behav_var
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	behav_var ',' behav_var_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1,
					                  $3);
			    }
		;


behav_var	:	T_LOCAL var_data_type T_ID
			    {
			      build_prefixed_id(behavior,
						&$3);
			      check_id(VAR_ID_DECL,
				       &$3,
				       FALSE);
			      store_var_decl($3,
					     LOCAL_VAR_ID,
					     $2,
					     value_passing);
			      $$ = $3;
			    }
	  	;


all_data_type	:	var_data_type
			    {
			      $$ = $1;
			    }
		|	num_data_type
			    {
			      $$ = $1;
			    }
		;


var_data_type	:	T_INTEGER
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("i",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			      value_passing = SYMBOLIC_VP;
			    }
		|	T_INTEGER '(' expr
			    {
			      if (($3 != NULL) &&
			          !check_expr_all($3->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  INTEGER_BOUND_NOT_UNDECL_ID_FREE,
						  INTEGER_BOUND_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_INTEGER_BOUND))
				$3 = NULL;
			    }
			DOTDOT expr
			    {
			      if (($6 != NULL) &&
			          !check_expr_all($6->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  INTEGER_BOUND_NOT_UNDECL_ID_FREE,
						  INTEGER_BOUND_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_INTEGER_BOUND))
				$6 = NULL;
			    }
			')'
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      (($3 != NULL) &&
					       ($6 != NULL))?
					        $3->expr:
						NULL,
					      (($3 != NULL) &&
					       ($6 != NULL))?
					        $6->expr:
						NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("i()",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    }
		|	T_REAL
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("r",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			      value_passing = SYMBOLIC_VP;
			    }
		|	T_BOOLEAN
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("b",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    }
		|	T_LIST '(' var_data_type ')'
			    {
			      handle_struct_data_type_decl(LIST_CONS_OP,
					       		   &$$,
					       		   $3,
					       		   NULL,
					       		   NULL);
			      value_passing = SYMBOLIC_VP;
			    }
		|	T_ARRAY '(' expr
			    {
			      if (($3 != NULL) &&
			          !check_expr_all($3->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  ARRAY_LENGTH_NOT_UNDECL_ID_FREE,
						  ARRAY_LENGTH_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_ARRAY_LENGTH))
				$3 = NULL;
			    }
			',' var_data_type ')'
			    {
			      handle_struct_data_type_decl(ARRAY_CONS_OP,
					       		   &$$,
					       		   $6,
					       		   $3,
					       		   NULL);
			    }
		|	T_RECORD '(' field_decl_list ')'
			    {
			      handle_struct_data_type_decl(RECORD_CONS_OP,
					       		   &$$,
					       		   NULL,
					       		   NULL,
					       		   $3);
			    }
		;


field_decl_list	:	field_decl
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	field_decl_list ',' field_decl
			    {
			      if (check_list_membership($3,
							$1,
							TRUE))
			      {
				signal_error(REC_FIELD_REDECL,
					     NULL,
					     NULL);
				$3 = NULL;
			      }
			      $$ = ($3 == NULL)?
				     $1:
				     append_list($1,
						 alloc_st_bucket_cell($3,
								      NULL));
			    }
		;


field_decl	:	var_data_type T_ID
	  		    {
			      /* the first check without prefixing the record field identifier is needed */
			      /* to find as declared the identifier whenever it is used (no usage occurs */
			      /* with the corresponding prefix) */
			      check_id(REC_FIELD_ID_DECL,
				       &$2,
				       FALSE);
			      if ($2 != NULL)
			      {
				$2->info.expr = $1;
			        build_typed_rec_field_id($1,
						         &$2);
			        check_id(REC_FIELD_ID_DECL,
				         &$2,
				         FALSE);
			        if ($2 != NULL)
				  $2->info.expr = $1;
			      }
			      $$ = $2;
	  		    }
	  	;


num_data_type	:	T_PRIO
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("P",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    }
		|	T_RATE
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("R",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    }
		|	T_WEIGHT
			    {
			      $$ = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("W",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    }
		;


term		:	T_STOP
			    {
			      handle_term(&$$,
					  STOP_OP,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    }
		|	action '.' term1
			    {
			      handle_term(&$$,
					  ACT_PREFIX_OP,
					  $3,
					  NULL,
					  $1,
					  NULL,
					  NULL,
					  NULL);
			    }
		|	T_CHOICE '{' term2 term2_list '}'
			    {
			      handle_term(&$$,
					  ALT_COMP_OP,
					  $3,
					  $4,
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    }
		;


term1		:	term
			    {
			      $$ = $1;
			    }
		|	T_ID
			    {
			      build_prefixed_id(aet,
						&$1);
			      check_id(BEHAV_ID_USE,
				       &$1,
				       FALSE);
			      local_var_actual_par_list = NULL;
			    }
			'(' behav_apar_list ')'
			    {
			      handle_term(&$$,
					  BEHAV_INVOC_OP,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  $1,
					  $4);
			      if ($1 != NULL)
				invoked_behavior_list = alloc_st_bucket_cell($1,
									     invoked_behavior_list);
			    }
		;


term2		:	guard term
			    {
			      if ($1 == NULL)
				$$ = $2;
			      else
			        handle_term(&$$,
					    COND_OP,
					    $2,
					    NULL,
					    NULL,
					    $1,
					    NULL,
					    NULL);
			    }
		;


guard		:
			    {
			      $$ = NULL;
			    }
		|	T_COND '(' expr
			    {
			      if (($3 != NULL) &&
				  ($3->expr->info.expr->data_type != NULL) &&
				  ($3->expr->info.expr->data_type->data_type_lexeme[0] != 'b'))
			      {
				signal_error(ILL_TYPED_EXPR,
					     NULL,
					     NULL);
				$3 = NULL;
			      }
			    }
			')' IMPL
			    {
			      $$ = $3;
			    }
		;


term2_list	:	enclosed_term2
			    {
			      $$ = $1;
			    }
	   	|	enclosed_term2 term2_list
			    {
			      handle_term(&$$,
					  ALT_COMP_OP,
					  $1,
					  $2,
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    }
		;


enclosed_term2	:	',' term2
			    {
			      $$ = $2;
			    }
		;


action		:	'<' T_ID
			    {
			      build_prefixed_id(aet,
						&$2);
			      check_id(ACT_TYPE_ID_USE,
				       &$2,
				       FALSE);
			      if (($2 != NULL) &&
				  !check_list_membership($2,
							 act_type_list,
							 FALSE))
				act_type_list = alloc_st_bucket_cell($2,
								     act_type_list);
			      local_var_actual_par_list = NULL;
			    }
			act_input_output
			    {
			      if ($2 != NULL)
			      {
				if ($2->info.act_type == NULL)
				  $2->info.act_type = alloc_act_type(action_index,
							             $4,
							             NULL);
				else
				  if (action_index != $2->info.act_type->act_type_index)
				  {
				    signal_error(INCONSISTENT_USE_ACT_TYPE,
						 NULL,
						 NULL);
				    $2 = NULL;
				  }
				  else
				    if (!check_expr_list_types($2->info.act_type->par_list,
							       $4,
							       TRUE))
				      $2 = NULL;
			      }
			      rate_index = NO_RATE;
			      priority = rate = NULL;
			    }
			',' act_rate 
			    {
			      if ($2 != NULL)
			      {
			        if (($2->info.act_type->rate_index == NO_RATE) &&
				    (rate_index != NO_RATE) &&
				    (priority != NULL))
			        {
				  $2->info.act_type->rate_index = rate_index;
				  $2->info.act_type->priority = priority;
			        }
			        if ((rate_index != NO_RATE) &&
				    (priority != NULL) &&
				    (($2->info.act_type->rate_index != rate_index) ||
				     (($2->info.act_type->priority->symbol_index == NUMBER) &&
				      ($2->info.act_type->priority->info.expr->value !=
					 priority->info.expr->value)) ||
				     (($2->info.act_type->priority->symbol_index != NUMBER) &&
				      ($2->info.act_type->priority != priority))))
			        {
				  signal_error(INCONSISTENT_USE_ACT_TYPE,
					       NULL,
					       NULL);
				  $2 = NULL;
			        }
			      }
			      if ((action_index == INPUT) &&
				  ((rate_index == EXP_TIMED) ||
				   (rate_index == IMMEDIATE)))
			      {
				signal_error(RATE_NOT_PASSIVE,
					     NULL,
					     NULL);
				rate_index = NO_RATE;
			      }
			    }
			'>'
			    {
			      $$ = alloc_action_parse_info((($2 == NULL) ||
							    (rate_index == NO_RATE) ||
							    (priority == NULL) ||
							    (rate == NULL))?
							     NULL:
							     set_action_bucket($2,
									       action_index,
									       $4,
									       rate_index,
									       priority,
									       rate),
							   local_var_actual_par_list);
			    }
		;


act_input_output:
			    {
			      $$ = NULL;
			      action_index = UNSTRUCTURED;
			    }
		|	'?' '(' act_input_list ')'
			    {
			      $$ = $3;
			      action_index = INPUT;
			    }
		|	'!' '(' act_output_list ')'
			    {
			      $$ = $3;
			      action_index = OUTPUT;
			    }
		;


act_input_list	:	T_ID
			    {
			      handle_input_act_type_par(&$1,
							&local_var_actual_par_list);
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	T_ID
			    {
			      handle_input_act_type_par(&$1,
							&local_var_actual_par_list);
			    }
			',' act_input_list
			    {
			      $$ = ($1 == NULL)?
				     $4:
				     alloc_st_bucket_cell($1,
					                  $4);
			    }
		;


act_output_list	:	expr
			    {
			      handle_output_act_type_par($1,
							 &local_var_actual_par_list);
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1->expr,
					                  NULL);
			    }
		|	expr
			    {
			      handle_output_act_type_par($1,
							 &local_var_actual_par_list);
			    }
			',' act_output_list
			    {
			      $$ = ($1 == NULL)?
				     $4:
				     alloc_st_bucket_cell($1->expr,
					                  $4);
			    }
		;


act_rate	:	T_EXP '(' expr
			    {
			      rate_index = EXP_TIMED;
			      if (($3 != NULL) &&
				  check_expr_all($3->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("rate",
                                                                                  SYT),
                                                 NO_ERROR,
                                                 EXP_RATE_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_EXP_RATE))
			      {
			        if (($3->expr->symbol_index == NUMBER) &&
				    ($3->expr->info.expr->value <= 0.0L))
				  signal_error(EXP_RATE_LE_ZERO,
					       NULL,
					       NULL);
			        else
				{
				  priority = set_number_bucket("0");
				  rate = $3->expr;
				}
			      }
			    }
			')'
			    {
			    }
		|	T_INF act_prio_weight
			    {
			      rate_index = IMMEDIATE;
			    }
		|	'_' act_prio_weight
			    {
			      rate_index = PASSIVE;
			    }
		;


act_prio_weight :
			    {
			      priority = rate = set_number_bucket("1");
			    }
		|	'(' expr
			    {
			      if (($2 != NULL) &&
				  check_expr_all($2->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("prio",
                                                                                  SYT),
                                                 PRIORITY_NOT_UNDECL_ID_FREE,
                                                 PRIORITY_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_PRIORITY))
			      {
			        if (($2->expr->symbol_index == NUMBER) &&
				    ($2->expr->info.expr->value <= 0.0L))
				  signal_error(PRIORITY_LE_ZERO,
					       NULL,
					       NULL);
			        else
				  priority = $2->expr;
			      }
			    }
			',' expr
			    {
			      if (($5 != NULL) &&
				  check_expr_all($5->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("weight",
                                                                                  SYT),
                                                 NO_ERROR,
                                                 WEIGHT_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_WEIGHT))
			      {
			        if (($5->expr->symbol_index == NUMBER) &&
				    ($5->expr->info.expr->value <= 0.0L))
				  signal_error(WEIGHT_LE_ZERO,
					       NULL,
					       NULL);
			        else
				  rate = $5->expr;
			      }
			    }
			')'
		;


behav_apar_list	:
			    {
			      $$ = NULL;
			    }
		|	behav_apar_list1
			    {
			      $$ = $1;
			    }
		;


behav_apar_list1:	expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1->expr,
					                  NULL);
			    }
		|	expr ',' behav_apar_list1
			    {
			      $$ = ($1 == NULL)?
				     $3:
				     alloc_st_bucket_cell($1->expr,
					                  $3);
			    }
		;


interactions	:	T_VOID
			    {
			    }
		|	uni_interacts and_interacts or_interacts
			    {
			      if (no_declarations)
				signal_error(NO_DECLARED_INTERACTIONS,
					     NULL,
					     NULL);
			    }
		;


uni_interacts	:
			    {
			      interaction_index = (interaction_index == INPUT_UNI)?
						    INPUT_AND:
						    OUTPUT_AND;
			    }
	      	|	T_UNI act_type_list
			    {
			      no_declarations = FALSE;
			      interaction_index = (interaction_index == INPUT_UNI)?
						    INPUT_AND:
						    OUTPUT_AND;
			    }
	     	;


and_interacts	:
	      		    {
			      interaction_index = (interaction_index == INPUT_AND)?
						    INPUT_OR:
						    OUTPUT_OR;
	      		    }
		|	T_AND act_type_list
			    {
			      no_declarations = FALSE;
			      interaction_index = (interaction_index == INPUT_AND)?
						    INPUT_OR:
						    OUTPUT_OR;
			    }
	     	;


or_interacts	:
	      		    {
			      if (interaction_index == INPUT_OR)
				interaction_index = OUTPUT_UNI;
	      		    }
		|	T_OR act_type_list
			    {
			      no_declarations = FALSE;
			      if (interaction_index == INPUT_OR)
				interaction_index = OUTPUT_UNI;
			    }
	     	;


act_type_list	:	T_ID
			    {
			      build_prefixed_id(aet,
						&$1);
			      check_id(ACT_TYPE_ID_USE,
				       &$1,
				       FALSE);
			      handle_interaction_decl($1,
						      interaction_index);
			    }
		|	T_ID
			    {
			      build_prefixed_id(aet,
						&$1);
			      check_id(ACT_TYPE_ID_USE,
				       &$1,
				       FALSE);
			      handle_interaction_decl($1,
						      interaction_index);
			    }
			';' act_type_list
			    {
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			act_type_list
			    {
			    }
		;


archi_topology	:	T_ARCHI_TOPOLOGY archi_elem_insts archi_interacts archi_attachs
	       		    {
	       		    }
	       	;


archi_elem_insts:	T_ARCHI_ELEM_INSTANCES aei_decl_list
			    {
			      archi_type[spec_no]->info.archi_type->aei_list = $2;
			      if ($2 == NULL)
				signal_error(NO_DECLARED_AEIS,
					     NULL,
					     NULL);
			    }
		;


aei_decl_list	:	aei_decl
			    {
			      $$ = $1;
			    }
	      	|	aei_decl ';' aei_decl_list
			    {
			      $$ = append_list($1,
					       $3);
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			aei_decl_list
			    {
			      $$ = $4;
			    }
		|	aei_decl error T_ARCHI_INTERACTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = $1;
			    }
		|	error T_ARCHI_INTERACTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = NULL;
			    }
		;


aei_decl	:	s_aei_decl
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	iteration i_aei_decl
			    {
			      selector_enabled[0] = FALSE;
			      $$ = (($1 == NULL) ||
				    ($2 == NULL))?
				     NULL:
				     expand_iterative_aei_decl($2);
			      selector[0] = NULL;
			    }
		;


s_aei_decl	:	T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$1,
							    $2,
							    0,
							    TRUE,
							    FALSE);
			    }
			':' T_ID
			    {
			      check_id(AET_ID_USE,
				       &$5,
				       FALSE);
			      id_prefix_in_expr = NULL;
			    }
			'(' aei_par_list ')'
			    {
			      if (($1 != NULL) &&
				  ($5 != NULL) &&
				  ($5->info.aet != NULL))
			      {
				handle_aei_decl($1,
					        $5,
					        $8);
			        $$ = $1;
			      }
			      else
			        $$ = NULL;
			    }
		;


i_aei_decl	:	T_ID aei_index
			    {
			      handle_symbolically_indexed_aei(&$1,
							      $2,
							      0,
							      TRUE);
			    }
			':' T_ID
			    {
			      check_id(AET_ID_USE,
				       &$5,
				       TRUE);
			    }
			'(' aei_par_list ')'
			    {
			      if (($1 != NULL) &&
				  ($5 != NULL) &&
				  ($5->info.aet != NULL))
			      {
			        $1->used = (char)TRUE;
			        $1->info.aei = alloc_aei($5,
						         $5->info.aet->formal_const_par_list,
						         $5->info.aet->behavior_list,
						         $5->info.aet->act_type_list,
						         TRUE);
			        $1->info.aei->init_state_var_assign_list = $8;
			        $$ = $1;
			      }
			      else
			        $$ = NULL;
			    }
		;


aei_par_list	:
			    {
			      $$ = NULL;
			    }
		|	aei_par_list1
			    {
			      $$ = $1;
			    }
		;


aei_par_list1	:	expr
			    {
			      if (($1 != NULL) &&
				  !check_expr_randomness_free($1->expr))
				signal_error(ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
					     NULL,
					     NULL);
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1->expr,
					                  NULL);
			    }
		|	expr
			    {
			      if (($1 != NULL) &&
				  !check_expr_randomness_free($1->expr))
				signal_error(ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
					     NULL,
					     NULL);
			    }
			',' aei_par_list1
			    {
			      $$ = ($1 == NULL)?
				     $4:
				     alloc_st_bucket_cell($1->expr,
					                  $4);
			    }
		;


archi_interacts	:	T_ARCHI_INTERACTIONS a_interact_list
			    {
			      archi_type[spec_no]->info.archi_type->ai_list = $2;
			    }
		;


a_interact_list	:	T_VOID
	      		    {
			      $$ = NULL;
	      		    }
		|	a_interact_list1
	      		    {
			      $$ = $1;
	      		    }
		;


a_interact_list1:	a_interact
			    {
			      $$ = $1;
			    }
		|	a_interact ';' a_interact_list1
			    {
			      $$ = append_list($1,
					       $3);
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			a_interact_list1
			    {
			      $$ = $4;
			    }
		|	a_interact error T_ARCHI_ATTACHMENTS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = $1;
			    }
		|	error T_ARCHI_ATTACHMENTS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			      $$ = NULL;
			    }
		;


a_interact	:	s_a_interact
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_st_bucket_cell($1,
							  NULL);
			    }
		|	iteration i_a_interact
			    {
			      selector_enabled[0] = FALSE;
			      $$ = (($1 == NULL) ||
				    ($2 == NULL))?
				     NULL:
				     expand_iterative_ai_decl();
			      selector[0] = NULL;
			    }
		;


s_a_interact	:	T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$1,
							    $2,
							    0,
							    FALSE,
							    FALSE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($1,
						         &$5,
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ($5 != NULL)
				handle_architectural_interaction(&$5,
								 FALSE);
			      $$ = $5;
			    }
		;


i_a_interact	:	T_ID aei_index
			    {
			      handle_symbolically_indexed_aei(&$1,
							      $2,
							      0,
							      FALSE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($1,
						         &$5,
						         0,
						         TRUE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      $$ = $5;
			    }
		;


archi_attachs	:	T_ARCHI_ATTACHMENTS attach_list
			    {
			      create_sync_act_types();
			    }
		;


attach_list	:	T_VOID
	      		    {
	      		    }
		|	attach_list1
	      		    {
	      		    }
		;


attach_list1	:	attach
			    {
			    }
		|	attach ';' attach_list1
			    {
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			attach_list1
			    {
			    }
		|	attach error T_BEHAV_VARIATIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_BEHAV_VARIATIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	attach error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		;


attach		:	s_attach
			    {
			    }
		|	iteration add_iteration i_attach
			    {
			      selector_enabled[0] = selector_enabled[1] = FALSE;
			      if (($1 != NULL) &&
				  ((selector_index == 0) ||
				   ($2 != NULL)) &&
				  ($3 != NULL))
				expand_iterative_attachment_decl($3->st_bucket1,
								 $3->st_bucket2);
			      selector[0] = selector[1] = NULL;
			      selector_index = 0;
			    }
		;


s_attach	:	T_FROM T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$2,
							    $3,
							    0,
							    FALSE,
							    FALSE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($2,
						         &$6,
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ($6 != NULL)
			        check_output_interaction(&$6,
							 FALSE);
			    }
			T_TO T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$9,
							    $10,
							    1,
							    FALSE,
							    FALSE);
			      if (($9 != NULL) &&
				  ($9 == $2))
			      {
				signal_error(AUTO_ATTACHMENT,
					     NULL,
					     NULL);
				$9 = NULL;
			      }
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($9,
						         &$13,
						         1,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ($13 != NULL)
			        check_input_interaction(&$13,
							FALSE);
			      if (($6 != NULL) &&
				  ($13 != NULL))
			        attach_interactions($6,
						    $13,
						    FALSE);
			    }
		;


i_attach	:	T_FROM T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$2,
							    $3,
							    0,
							    FALSE,
							    TRUE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($2,
						         &$6,
						         0,
						         (index_expr[0] != NULL),
						         TRUE,
						         ACT_TYPE_ID_USE);
			      if ((index_expr[0] == NULL) &&
				  ($6 != NULL))
			        check_output_interaction(&$6,
							 FALSE);
			    }
			T_TO T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$9,
							    $10,
							    1,
							    FALSE,
							    TRUE);
			      if ((index_expr[0] == NULL) &&
				  (index_expr[1] == NULL) &&
			          ($9 != NULL) &&
				  ($9 == $2))
			      {
				signal_error(AUTO_ATTACHMENT,
					     NULL,
					     NULL);
				$9 = NULL;
			      }
			      if ((index_expr[0] == NULL) &&
				  (index_expr[1] == NULL))
				signal_error(NO_AEI_INDEXING_USED_IN_ATTACHMENT,
					     NULL,
					     NULL);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($9,
						         &$13,
						         1,
						         (index_expr[1] != NULL),
						         TRUE,
						         ACT_TYPE_ID_USE);
			      if ((index_expr[1] == NULL) &&
				  ($13 != NULL))
			        check_input_interaction(&$13,
							FALSE);
			      if (($6 != NULL) &&
				  ($13 != NULL))
			      {
			        if ((index_expr[0] == NULL) &&
				    (index_expr[1] == NULL))
				{
			          attach_interactions($6,
						      $13,
						      FALSE);
				  $$ = NULL;
				}
				else
				  $$ = alloc_st_bucket2_cell($6,
							     $13,
							     NULL);
			      }
			      else
				$$ = NULL;
			    }
		;


behav_variations:
			    {
			    }
		|	T_BEHAV_VARIATIONS
			    {
			      no_declarations = TRUE;
			    }
			behav_hidings behav_restricts behav_renamings
			    {
			      if (no_declarations)
				signal_error(NO_DECLARED_BEHAV_VARIATIONS,
					     NULL,
					     NULL);
			    }
	      	;


behav_hidings	:
			    {
			    }
		|	T_BEHAV_HIDINGS hiding_list
			    {
			      no_declarations = FALSE;
			    }
	      	;


hiding_list	:	hiding
			    {
			    }
		|	hiding ';' hiding_list
			    {
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			hiding_list
			    {
			    }
		|	hiding error T_BEHAV_RESTRICTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_BEHAV_RESTRICTIONS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	hiding error T_BEHAV_RENAMINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_BEHAV_RENAMINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	hiding error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		;


hiding		:	s_hiding
			    {
			    }
		|	iteration i_hiding
			    {
			      selector_enabled[0] = FALSE;
			      if (($1 != NULL) &&
				  ($2 != NULL))
				expand_iterative_hiding_decl($2);
			      selector[0] = NULL;
			    }
		;


s_hiding	:	T_HIDE T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$2,
							    $3,
							    0,
							    FALSE,
							    FALSE);
			      aei = $2;
			    }
	       		'.' act_type_set_h
			    {
			    }
		|	T_HIDE T_INTERNALS
			    {
			      hide_internals();
			    }
		|	T_HIDE T_INTERACTIONS
			    {
			      hide_interactions();
			    }
		|	T_HIDE T_ALL
			    {
			      hide_all();
			    }
		;


i_hiding	:	T_HIDE T_ID aei_index
			    {
			      handle_symbolically_indexed_aei(&$2,
							      $3,
							      0,
							      FALSE);
			      aei = $2;
			    }
	       		'.' act_type_set_h
			    {
			      $$ = ($2 == NULL)?
				     NULL:
				     $6;
			    }
		;


act_type_set_h	:	T_ID
			    {
			      handle_prefixed_indexed_id(aei,
						         &$1,
						         0,
						         selector_enabled[0],
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if (($1 != NULL) &&
				  !selector_enabled[0])
			        handle_hiding($1,
					      FALSE);
			      $$ = $1;
			    }
	     	|	T_INTERNALS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_internals(aei);
			      $$ = $1;
			    }
		|	T_INTERACTIONS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_interactions(aei);
			      $$ = $1;
			    }
		|	T_ALL
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_all(aei);
			      $$ = $1;
			    }
		;


behav_restricts	:
			    {
			    }
		|	T_BEHAV_RESTRICTIONS restrict_list
			    {
			      no_declarations = FALSE;
			    }
	      	;


restrict_list	:	restriction
			    {
			    }
		|	restriction ';' restrict_list
			    {
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			restrict_list
			    {
			    }
		|	restriction error T_BEHAV_RENAMINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_BEHAV_RENAMINGS
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	restriction error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		;


restriction	:	s_restriction
			    {
			    }
		|	iteration i_restriction
			    {
			      selector_enabled[0] = FALSE;
			      if (($1 != NULL) &&
				  ($2 != NULL))
				expand_iterative_restriction_decl($2);
			      selector[0] = NULL;
			    }
		;


s_restriction	:	T_RESTRICT T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$2,
							    $3,
							    0,
							    FALSE,
							    FALSE);
			      aei = $2;
			    }
	       		'.' act_type_set_r
			    {
			    }
		|	T_RESTRICT T_OBS_INTERNALS
			    {
			      restrict_obs_internals();
			    }
		|	T_RESTRICT T_OBS_INTERACTIONS
			    {
			      restrict_obs_interactions();
			    }
		|	T_RESTRICT T_ALL_OBSERVABLES
			    {
			      restrict_all_observables();
			    }
		;


i_restriction	:	T_RESTRICT T_ID aei_index
			    {
			      handle_symbolically_indexed_aei(&$2,
							      $3,
							      0,
							      FALSE);
			      aei = $2;
			    }
	       		'.' act_type_set_r
			    {
			      $$ = ($2 == NULL)?
				     NULL:
				     $6;
			    }
		;


act_type_set_r	:	T_ID
			    {
			      handle_prefixed_indexed_id(aei,
						         &$1,
						         0,
						         selector_enabled[0],
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if (($1 != NULL) &&
				  !selector_enabled[0])
			        handle_restriction($1,
						   FALSE);
			      $$ = $1;
			    }
	     	|	T_OBS_INTERNALS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_obs_internals(aei);
			      $$ = $1;
			    }
		|	T_OBS_INTERACTIONS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_obs_interactions(aei);
			      $$ = $1;
			    }
		|	T_ALL_OBSERVABLES
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_all_observables(aei);
			      $$ = $1;
			    }
		;


behav_renamings	:
			    {
			    }
		|	T_BEHAV_RENAMINGS renaming_list
			    {
			      no_declarations = FALSE;
			    }
	      	;


renaming_list	:	renaming
	      		    {
	      		    }
		|	renaming ';' renaming_list
			    {
			    }
		|	error ';'
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
			renaming_list
			    {
			    }
		|	renaming error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		|	error T_END
			    {
			      handle_sync_aemilia();
			      yyerrok;
			    }
		;


renaming	:	s_renaming
			    {
			    }
		|	iteration i_renaming
			    {
			      selector_enabled[0] = FALSE;
			      if (($1 != NULL) &&
				  $2)
				expand_iterative_renaming_decl();
			      selector[0] = NULL;
			    }
		;


s_renaming	:	T_RENAME T_ID poss_aei_index
			    {
			      handle_concretely_indexed_aei(&$2,
							    $3,
							    0,
							    FALSE,
							    FALSE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($2,
						         &$6,
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ($6 != NULL)
			        handle_renaming_old(&$6,
						    FALSE);
			    }
			T_AS T_ID poss_aei_index
			    {
			      handle_unprefixed_concretely_indexed_id(&$9,
						                      $10,
								      ACT_TYPE_ID_USE);
			      if (($6 != NULL) &&
			          ($9 != NULL))
			        handle_renaming_new($6,
					            $9,
						    FALSE);
			    }
		;


i_renaming	:	T_RENAME T_ID aei_index
			    {
			      handle_symbolically_indexed_aei(&$2,
							      $3,
							      0,
							      FALSE);
			    }
	       		'.' T_ID
			    {
			      handle_prefixed_indexed_id($2,
						         &$6,
						         0,
						         TRUE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			    }
			T_AS T_ID poss_aei_index
			    {
			      handle_unprefixed_symbolically_indexed_id(&$9,
						                        $10,
						                        1,
								        ACT_TYPE_ID_USE);
			      $$ = (($2 != NULL) &&
				    ($6 != NULL) &&
				    ($9 != NULL));
			    }
		;


iteration	:	T_FOR_ALL T_ID
			    {
			      handle_iteration_1(&$2);
			    }
			T_IN expr
			    {
			      handle_iteration_2(&$5);
			    }
			DOTDOT expr
			    {
			      $$ = handle_iteration_3($2,
						      $5,
						      $8);
			    }
		;


add_iteration	:
			    {
			      $$ = NULL;
			    }
		|	T_AND
			    {
			      selector_index = 1;
			    }
			iteration
			    {
			      if (selector[1] == selector[0])
			      {
				signal_error(SAME_AEI_INDEX,
					     NULL,
					     NULL);
			        $$ = NULL;
			      }
			      else
			        $$ = $3;
			    }
		;


poss_aei_index	:
			    {
			      poss_aei_index_parsed = FALSE;
			      $$ = NULL;
			    }
		|	aei_index
			    {
			      poss_aei_index_parsed = TRUE;
			      $$ = $1;
			    }
		;


aei_index	:	'[' expr ']'
			    {
			      $$ = (($2 != NULL) &&
				    !check_expr_all($2->expr,
						    selector[0],
						    selector[1],
						    (ST_BUCKET *)search_lexeme_table("integer",
										     SYT),
						    AEI_INDEX_EXPR_NOT_UNDECL_ID_FREE,
						    AEI_INDEX_EXPR_NOT_RANDOMNESS_FREE,
						    ILL_TYPED_AEI_INDEX))?
				     NULL:
				     $2;
			    }
		;


expr		:	T_ID
			    {
			      $$ = handle_id_in_expr(&$1,
						     aet,
						     &local_var_actual_par_list,
						     parsing_behavior_term,
						     FALSE);
			    }
		|	T_NUMBER
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	expr '+' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PLUS_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr '-' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MINUS_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr '*' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TIMES_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr '/' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(DIV_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	T_MOD '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MOD_OP,
                                                                          $3->expr,
                                                                          $5->expr,
                                                                          NULL,
                                                                          0.0,
                                                                          NULL,
                                                                          FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_ABS '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ABS_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_CEIL '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CEIL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_FLOOR '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FLOOR_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_MIN '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MIN_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_MAX '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MAX_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_POWER '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POWER_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_EPOWER '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EPOWER_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_LOGE '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOGE_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_LOG10 '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOG10_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_SQRT '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SQRT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_SIN '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SIN_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_COS '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(COS_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_C_UNIFORM '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(C_UNIFORM_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_ERLANG '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ERLANG_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_GAMMA '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GAMMA_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_EXPONENTIAL '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EXPONENTIAL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_WEIBULL '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WEIBULL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_BETA '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BETA_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_NORMAL '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NORMAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_PARETO '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PARETO_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_B_PARETO '(' expr ',' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(B_PARETO_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 concat_lists_no_dupls(
										   $5->local_var_list,
									           $7->local_var_list)));
			    }
		|	T_D_UNIFORM '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(D_UNIFORM_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_BERNOULLI '(' expr ',' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BERNOULLI_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 concat_lists_no_dupls(
										   $5->local_var_list,
									           $7->local_var_list)));
			    }
		|	T_BINOMIAL '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BINOMIAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_POISSON '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POISSON_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_NEG_BINOMIAL '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NEG_BINOMIAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_GEOMETRIC '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GEOMETRIC_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_PASCAL '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PASCAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_TRUE
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	T_FALSE
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	expr AND expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(AND_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr OR expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(OR_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	'!' expr
			    {
        		      $$ = ($2 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NOT_OP,
                                                                           $2->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $2->local_var_list);
			    }
		|	expr '=' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EQ_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr NE expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr '<' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LT_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr LE expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr '>' expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GT_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	expr GE expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($1->local_var_list,
								                 $3->local_var_list));
			    }
		|	T_LIST_CONS '(' expr_list ')'
			    {
			      $$ = $3;
			    }
		|	T_FIRST '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FIRST_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_TAIL '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TAIL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_CONCAT '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CONCAT_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_INSERT '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(INSERT_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_REMOVE '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(REMOVE_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_LENGTH '(' expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LENGTH_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $3->local_var_list);
			    }
		|	T_ARRAY_CONS '(' expr_array ')'
			    {
			      $$ = $3;
			    }
		|	T_READ '(' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(READ_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 $5->local_var_list));
			    }
		|	T_WRITE '(' expr ',' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WRITE_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($3->local_var_list,
								                 concat_lists_no_dupls(
										   $5->local_var_list,
									           $7->local_var_list)));
			    }
		|	T_RECORD_CONS '(' expr_record ')'
			    {
			      $$ = $3;
			    }
		|	T_GET '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE,
				       &$3,
				       FALSE);
			    }
			',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($6 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GET_OP,
                                                                           $3,
                                                                           $6->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   $6->local_var_list);
			    }
		|	T_PUT '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE,
				       &$3,
				       FALSE);
			    }
			',' expr ',' expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($6 == NULL) ||
				    ($8 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PUT_OP,
                                                                           $3,
                                                                           $6->expr,
                                                                           $8->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls($6->local_var_list,
								                 $8->local_var_list));
			    }
		|	'(' expr ')'
			    {
			      $$ = $2;
			    }
		;


expr_list	:
			    {
			      $$ = alloc_expr_parse_info(set_expr_bucket(LIST_CONS_OP,
						     			 NULL,
									 NULL,
									 NULL,
									 0.0L,
						     			 alloc_value_cell(NULL,
											  DBL_MAX,
											  NULL,
											  NULL),
									 FALSE),
							 NULL);
			    }
		|	struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LIST_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   $1->struct_value,
									   FALSE),
							   $1->local_var_list);
			    }
		;


expr_array	:	struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ARRAY_CONS_OP,
								           NULL,
								           NULL,
								           NULL,
								           $1->value_num,
								           transform_list_into_array(
									     $1->struct_value,
									     $1->value_num),
								           FALSE),
						           $1->local_var_list);
			    }
		;


expr_record	:	struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(RECORD_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   $1->struct_value,
									   FALSE),
							   $1->local_var_list);
			    }
		;


struct_expr	:	expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell($1->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
						          $1->local_var_list);
			    }
		|	expr ',' struct_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell($1->expr,
						      			   0.0L,
						      			   NULL,
						      			   $3->struct_value),
						          $3->value_num + 1,
						          concat_lists_no_dupls($1->local_var_list,
								                $3->local_var_list));
			    }
		;

%%


/****************************************************************/
/* 11. Definition of exporting functions.			*/
/****************************************************************/

void		parse_aemilia(char *aemilia_path)
{
	int		aemiliayyparse(void);

	/* open the .aem spec and the .lis file */
	aemiliayyin = new_fopen(aemilia_path,
			        NULL,
			        "r");
	open_listing(aemilia_path);

	/* create a dummy bucket for the architectural type to be parsed, so that archi_type[spec_no] is */
	/* never NULL in the case of syntax errors occurring in the header of the architectural type */
	archi_type[spec_no] = (ST_BUCKET *)search_lexeme_table("@",
							       SYT);
	check_id(ARCHI_TYPE_ID_DEF,
		 &(archi_type[spec_no]),
		 FALSE);
	archi_type[spec_no]->info.archi_type = alloc_archi_type();

	/* initialize the iteration-related global variables imported via symbol-handler.h */
	selector_enabled[0] = selector_enabled[1] = poss_aei_index_parsed = FALSE;
	id_prefix_in_expr = selector[0] = selector[1] = unindexed_aei[0] = unindexed_aei[1] =
	  index_expr[0] = index_expr[1] = unindexed_id[0] = unindexed_id[1] = NULL;
	selector_index = 0;

	/* initialize the private global variables */
	action_index = UNSTRUCTURED;
	parsing_behavior_term = FALSE;
	aet = behavior = priority = rate = NULL;
	local_var_actual_par_list = NULL;
	rate_index = NO_RATE;

	/* parse and check the .aem spec */
	aemiliayyparse();
	check_semantic_errors();

	/* close the .aem spec and the .lis file */
	fclose(aemiliayyin);
	close_listing();
}


/****************************************************************/
/* 12. Definition of private functions.				*/
/****************************************************************/

void		handle_archi_type_par_assign(ST_BUCKET       *archi_type_par,
					     EXPR_PARSE_INFO *expr)
{
	if ((archi_type_par != NULL) &&
	    (expr != NULL) &&
	    check_expr_all(expr->expr,
			   NULL,
			   NULL,
			   archi_type_par,
			   ACTUAL_PAR_NOT_UNDECL_ID_FREE,
			   ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
			   ILL_TYPED_ASSIGN) &&
	    check_eval_var_lengths_bounds(archi_type_par->info.expr,
				          TRUE,
				          FALSE,
				          NULL))
	{
	  eval_expr(expr->expr,
		    0);
	  if (check_assign_lengths_bounds(archi_type_par->info.expr,
					  expr->expr->info.expr,
					  FALSE,
	 				  archi_type_par->symbol_lexeme))
	    assign_expr_eval(archi_type_par->info.expr,
			     expr->expr->info.expr,
			     ASSIGN_TO_LEFT,
			     NULL);
	}
}


void		handle_behavior_def_1(ST_BUCKET **behavior_bucket,
				      BOOLEAN   first)
{
	build_prefixed_id(aet,
			  behavior_bucket);
	check_id(BEHAV_ID_DEF,
		 behavior_bucket,
		 FALSE);
	behavior = *behavior_bucket;
	id_prefix_in_expr = aet;
	value_passing = CONCRETE_VP;
	if (first)
	  init_behav_actual_var_par_list = last_init_behav_actual_var_par_cell = NULL;
}


void		handle_behavior_def_2(ST_BUCKET      *behavior_bucket,
				      ST_BUCKET_CELL *var_par_list,
				      ST_BUCKET_CELL *local_var_list,
				      BOOLEAN        first)
{
	if (behavior_bucket != NULL)
	{
	  if (behavior_bucket->info.behavior != NULL)
	  {
	    check_expr_list_types(behavior_bucket->info.behavior->formal_var_par_list,
			          var_par_list,
				  TRUE);
	    behavior_bucket->info.behavior->formal_var_par_list = var_par_list;
	    behavior_bucket->info.behavior->local_var_list = local_var_list;
	  }
	  else
	    behavior_bucket->info.behavior = alloc_behavior(var_par_list,
						            (first)?
						              init_behav_actual_var_par_list:
						              NULL,
						            local_var_list,
						            first,
						            NULL);
	}
	invoked_behavior_list = NULL;
	id_prefix_in_expr = behavior;
	parsing_behavior_term = TRUE;
}


void		handle_behavior_def_3(ST_BUCKET       *behavior_bucket,
				      TERM_PARSE_INFO *term)
{
	parsing_behavior_term = FALSE;
	if ((behavior_bucket != NULL) &&
	    (term->state_lexeme != NULL))
	{
	  behavior_bucket->info.behavior->invoked_behavior_list = invoked_behavior_list;
	  behavior_bucket->info.behavior->state_lexeme = term->state_lexeme;
	  for (;
	       (term->crit_local_var_list != NULL);
	       term->crit_local_var_list = term->crit_local_var_list->next_st_bucket_cell)
	    term->crit_local_var_list->st_bucket->info.expr->illegal_use_cumul = TRUE;
	}
}


void		handle_struct_data_type_decl(EXPR_OP_INDEX   data_type_index,
				 	     EXPR            **new_expr,
				 	     EXPR            *old_expr,
				 	     EXPR_PARSE_INFO *array_length,
				 	     ST_BUCKET_CELL  *rec_field_list)
{
		DTT_BUCKET	*data_type_bucket;
		ST_BUCKET_CELL	*rec_field_cell;
		VALUE_CELL	*last_new_rec_field_cell;
	static	char		*data_type			=	NULL;
		int		length;
	static	int		max_length			=	0;

	/* allocate memory for the string representing the data type */
	switch (data_type_index)
	{
	  case LIST_CONS_OP:
	  case ARRAY_CONS_OP:
	    length = 2 +
		     strlen(old_expr->data_type->data_type_lexeme) +
		     1;
	    break;
	  case RECORD_CONS_OP:
	    for (rec_field_cell = rec_field_list,
		 length = 2;
		 (rec_field_cell != NULL);
		 rec_field_cell = rec_field_cell->next_st_bucket_cell)
	      length += strlen(rec_field_cell->st_bucket->symbol_lexeme) +
			((rec_field_cell->next_st_bucket_cell != NULL)?
			   1:
			   0);
	    length++;
	    break;
	  default:
	    length = 0;
	    break;
	}
	if (length > max_length)
	{
	  if (data_type != NULL)
	    free(data_type);
	  data_type = alloc_string(length);
	  max_length = length;
	}

	/* write the string representing the data type */
	switch (data_type_index)
	{
	  case LIST_CONS_OP:
	    sprintf(data_type,
	            "l(%s)",
	            old_expr->data_type->data_type_lexeme);
	    break;
	  case ARRAY_CONS_OP:
	    sprintf(data_type,
	            "a(%s)",
	            old_expr->data_type->data_type_lexeme);
	    break;
	  case RECORD_CONS_OP:
	    strcpy(data_type,
		   "p(");
	    for (rec_field_cell = rec_field_list;
		 (rec_field_cell != NULL);
		 rec_field_cell = rec_field_cell->next_st_bucket_cell)
	    {
	      strcat(data_type,
		     rec_field_cell->st_bucket->symbol_lexeme);
	      if (rec_field_cell->next_st_bucket_cell != NULL)
	        strcat(data_type,
		       ",");
	    }
	    strcat(data_type,
		   ")");
	    break;
	  default:
	    data_type = NULL;
	    break;
	}

	/* search the lexeme table for the string representing the data type and store the related */
	/* information */
	data_type_bucket = (DTT_BUCKET *)search_lexeme_table(data_type,
						             DTT);
	*new_expr = alloc_expr(NO_EXPR_OP,
			       NULL,
			       NULL,
			       NULL,
			       data_type_bucket,
			       FALSE,
			       0.0L,
			       NULL);
	switch (data_type_index)
	{
	  case LIST_CONS_OP:
	    (*new_expr)->struct_value = alloc_value_cell(alloc_dummy_st_bucket(old_expr),
							 (long double)DBL_MAX,
							 NULL,
							 NULL);
	    break;
	  case ARRAY_CONS_OP:
	    (*new_expr)->struct_value = alloc_value_cell(alloc_dummy_st_bucket(old_expr),
							 0.0L,
							 NULL,
							 (array_length == NULL)?
							   NULL:
							   (VALUE_CELL *)(array_length->expr));
	    break;
	  case RECORD_CONS_OP:
	    for (rec_field_cell = rec_field_list,
		 last_new_rec_field_cell = NULL;
		 (rec_field_cell != NULL);
		 rec_field_cell = rec_field_cell->next_st_bucket_cell)
	      if (last_new_rec_field_cell == NULL)
	        last_new_rec_field_cell = (*new_expr)->struct_value =
		  alloc_value_cell(rec_field_cell->st_bucket,
				   0.0L,
				   NULL,
				   NULL);
	      else
	        last_new_rec_field_cell = last_new_rec_field_cell->next_value_cell =
		  alloc_value_cell(rec_field_cell->st_bucket,
				   0.0L,
				   NULL,
				   NULL);
	    free_st_bucket_list(rec_field_list);
	    break;
	  default:
	    break;
	}
}


void		handle_term(TERM_PARSE_INFO **term,
			    EMPA_OP_INDEX   op_index,
			    TERM_PARSE_INFO *term1,
			    TERM_PARSE_INFO *term2,
			    ACT_PARSE_INFO  *action,
			    EXPR_PARSE_INFO *guard,
			    ST_BUCKET       *behavior,
			    ST_BUCKET_CELL  *behavior_actual_par_list)
{
	ST_BUCKET	*par_behav_invoc;
	char		*state_lexeme;

	switch (op_index)
	{
	  case STOP_OP:
	    *term = alloc_term_parse_info("_",
					  NULL,
					  NULL);
	    break;
	  case ACT_PREFIX_OP:
	    if ((action->action != NULL) &&
		(term1->state_lexeme != NULL))
	    {
	      encode_local_state_comp(action->action);
	      state_lexeme = alloc_string(1 + strlen(action->action->code->code_lexeme) +
					    1 + strlen(term1->state_lexeme));
	      sprintf(state_lexeme,
		      ".%s %s",
		      action->action->code->code_lexeme,
		      term1->state_lexeme);
	      if (term1->state_lexeme[0] != '_')
	        free(term1->state_lexeme);
	      *term = alloc_term_parse_info(state_lexeme,
				            NULL,
				            (action->action->info.action->action_index == OUTPUT)?
					      concat_lists_no_dupls(term1->crit_local_var_list,
					   	                    action->local_var_list):
					      ((action->action->info.action->action_index == INPUT)?
					         remove_list(term1->crit_local_var_list,
					      	             action->local_var_list):
					         term1->crit_local_var_list));
	    }
	    else
	      *term = alloc_term_parse_info(NULL,
				            NULL,
				            ((action->action != NULL) &&
				             (action->action->info.action->action_index == OUTPUT))?
				              concat_lists_no_dupls(term1->crit_local_var_list,
					   	                    action->local_var_list):
					      (((action->action != NULL) &&
					        (action->action->info.action->action_index == INPUT))?
					         remove_list(term1->crit_local_var_list,
						             action->local_var_list):
					         term1->crit_local_var_list));
	    break;
	  case COND_OP:
	    if ((guard->expr->info.expr->data_type != NULL) &&
		(term1->state_lexeme != NULL))
	    {
	      encode_local_state_comp(guard->expr);
	      state_lexeme = alloc_string(1 + strlen(guard->expr->code->code_lexeme) +
					    1 + strlen(term1->state_lexeme));
	      sprintf(state_lexeme,
		      ":%s %s",
		      guard->expr->code->code_lexeme,
		      term1->state_lexeme);
	      if (term1->state_lexeme[0] != '_')
	        free(term1->state_lexeme);
	      *term = alloc_term_parse_info(state_lexeme,
                                            NULL,
                                            concat_lists_no_dupls(guard->local_var_list,
					                          term1->crit_local_var_list));
	    }
	    else
	      *term = alloc_term_parse_info(NULL,
                                            NULL,
                                            concat_lists_no_dupls(guard->local_var_list,
					                          term1->crit_local_var_list));
	    break;
	  case ALT_COMP_OP:
	    if ((term1->state_lexeme != NULL) &&
		(term2->state_lexeme != NULL))
	    {
	      state_lexeme = alloc_string(1 + strlen(term1->state_lexeme) +
					    1 + strlen(term2->state_lexeme));
	      sprintf(state_lexeme,
		      "+%s %s",
		      term1->state_lexeme,
		      term2->state_lexeme);
	      if (term1->state_lexeme[0] != '_')
	        free(term1->state_lexeme);
	      if (term2->state_lexeme[0] != '_')
	        free(term2->state_lexeme);
	      *term = alloc_term_parse_info(state_lexeme,
					    NULL,
					    concat_lists_no_dupls(term1->crit_local_var_list,
							          term2->crit_local_var_list));
	    }
	    else
	      *term = alloc_term_parse_info(NULL,
					    NULL,
					    concat_lists_no_dupls(term1->crit_local_var_list,
							          term2->crit_local_var_list));
	    break;
	  case BEHAV_INVOC_OP:
	    if (behavior != NULL)
	    {
	      if (behavior->info.behavior == NULL)
		/* the invoked behavior has not been defined yet */
		behavior->info.behavior = alloc_behavior(behavior_actual_par_list,
							 NULL,
							 NULL,
							 FALSE,
							 NULL);
	      if (check_expr_list_types(behavior->info.behavior->formal_var_par_list,
				        behavior_actual_par_list,
				        TRUE))
	      {
		encode_local_state_comp(behavior);
		if (behavior_actual_par_list != NULL)
		{
		  par_behav_invoc = set_par_behav_invoc_bucket(behavior,
							       behavior_actual_par_list);
		  encode_local_state_comp(par_behav_invoc);
		  state_lexeme = dupl_string(par_behav_invoc->code->code_lexeme);
		}
		else
		  state_lexeme = dupl_string(behavior->code->code_lexeme);
		*term = alloc_term_parse_info(state_lexeme,
					      behavior_actual_par_list,
					      local_var_actual_par_list);
	      }
	      else
		*term = alloc_term_parse_info(NULL,
					      behavior_actual_par_list,
					      local_var_actual_par_list);
	    }
	    else
	      *term = alloc_term_parse_info(NULL,
					    behavior_actual_par_list,
					    local_var_actual_par_list);
	    break;
	}
}


void		handle_input_act_type_par(ST_BUCKET      **input_act_type_par,
					  ST_BUCKET_CELL **local_var_actual_par_list)
{
	build_prefixed_id(behavior,
			  input_act_type_par);
	check_id(VAR_ID_USE,
		 input_act_type_par,
		 FALSE);
	if (*input_act_type_par != NULL)
	  switch ((*input_act_type_par)->symbol_index)
	  {
	    case FORMAL_CONST_PAR_ID:
	    case FORMAL_VAR_PAR_ID:
	      (*input_act_type_par)->info.expr->illegal_use_cumul = TRUE;
	      break;
	    case LOCAL_VAR_ID:
	      *local_var_actual_par_list = alloc_st_bucket_cell(*input_act_type_par,
								*local_var_actual_par_list);
	      break;
	    default:
              break;
	  }
}


void		handle_output_act_type_par(EXPR_PARSE_INFO *act_type_par,
					   ST_BUCKET_CELL  **local_var_actual_par_list)
{
	if ((act_type_par != NULL) &&
	    (act_type_par->expr->symbol_index == LOCAL_VAR_ID))
	  *local_var_actual_par_list = alloc_st_bucket_cell(act_type_par->expr,
							    *local_var_actual_par_list);
}


void		handle_interaction_decl(ST_BUCKET      *act_type,
				        INTERACT_INDEX interaction_index)
{
	if ((act_type != NULL) &&
	    (act_type->info.act_type != NULL))
	{
	  if (!check_list_membership(act_type,
				     act_type_list,
				     FALSE))
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
			 NULL,
			 NULL);
	  else
	  {
	    if (((interaction_index == INPUT_UNI) ||
		 (interaction_index == INPUT_AND) ||
		 (interaction_index == INPUT_OR)) &&
		(act_type->info.act_type->act_type_index == OUTPUT))
	      signal_error(OUTPUT_ACT_TYPE_AS_INPUT_INTERACTION,
			   NULL,
			   NULL);
	    else
	      if (((interaction_index == OUTPUT_UNI) ||
		   (interaction_index == OUTPUT_AND) ||
		   (interaction_index == OUTPUT_OR)) &&
		  (act_type->info.act_type->act_type_index == INPUT))
		signal_error(INPUT_ACT_TYPE_AS_OUTPUT_INTERACTION,
			     NULL,
			     NULL);
	      else
	        if ((interaction_index == INPUT_AND) &&
		    (act_type->info.act_type->act_type_index == INPUT))
		  signal_error(INPUT_ACT_TYPE_AS_INPUT_AND_INTERACTION,
			       NULL,
			       NULL);
	    if ((act_type->info.act_type->interaction_index != NO_INTERACTION) &&
	        (act_type->info.act_type->interaction_index != interaction_index))
	      signal_error(AMBIGUOUS_INTERACTION,
			   NULL,
			   NULL);
	    else
	      if ((act_type->info.act_type->interaction_index != NO_INTERACTION) &&
	          (act_type->info.act_type->interaction_index == interaction_index))
	        signal_error(ACT_TYPE_ALREADY_DECLARED_INTERACTION,
			     NULL,
			     NULL);
	      else
	        act_type->info.act_type->interaction_index = interaction_index;
	  }
	}
}


ST_BUCKET_CELL	*expand_iterative_aei_decl(ST_BUCKET *symbolic_aei)
{
	ST_BUCKET	*concrete_aei,
			*concrete_index_expr;
	ST_BUCKET_CELL	*concrete_aei_list,
			*last_concrete_aei_cell,
			*actual_aei_par_list;
	int		min_value,
			max_value,
			value;

	concrete_aei_list = last_concrete_aei_cell = NULL;
	actual_aei_par_list = symbolic_aei->info.aei->init_state_var_assign_list;
	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value;
	     (value <= max_value);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_index_expr = set_concrete_expr_bucket(index_expr[0]);
	  concrete_aei = unindexed_aei[0];
	  build_indexed_id(&concrete_aei,
			   concrete_index_expr);
	  check_id(AEI_ID_DECL,
	           &concrete_aei,
		   TRUE);
	  if (concrete_aei != NULL)
	  {
	    handle_aei_decl(concrete_aei,
			    symbolic_aei->info.aei->aet,
			    actual_aei_par_list);
	    if (concrete_aei_list == NULL)
	      last_concrete_aei_cell = concrete_aei_list = alloc_st_bucket_cell(concrete_aei,
										NULL);
	    else
	      last_concrete_aei_cell = last_concrete_aei_cell->next_st_bucket_cell =
		alloc_st_bucket_cell(concrete_aei,
				     NULL);
	  }
	}
	return(concrete_aei_list);
}


void		handle_aei_decl(ST_BUCKET      *aei,
			        ST_BUCKET      *aet,
			        ST_BUCKET_CELL *actual_aei_par_list)
{
	BOOLEAN		aei_par_consistency;
	ST_BUCKET	*assignment;
	ST_BUCKET_CELL	*formal_par_cell,
			*actual_par_cell,
			*last_init_state_var_assign_cell;

	/* allocate the information area in the symbol table bucket for the AEI and initialize it by */
	/* rewriting the information about the AET representing the type of the AEI */
	aei_par_consistency = check_expr_list_types(aet->info.aet->formal_const_par_list,
						    actual_aei_par_list,
						    TRUE);
	aei->info.aei = alloc_aei(aet,
			          (aei_par_consistency)?
			            rewrite_aet_formal_const_par_list(aet->info.aet->formal_const_par_list,
								      aei):
				    NULL,
			          rewrite_aet_behavior_list(aet->info.aet->behavior_list,
							    aei),
			          rewrite_aet_act_type_list(aet->info.aet->act_type_list,
							    aei),
			          FALSE);

	/* generate the assignments for the formal parameters of the AEI and its initial behavior on the */
	/* basis of the actual parameters of the AEI */
	if (aei_par_consistency)
	{
	  /* assign the values of the actual constant parameters to the corresponding formal constant */
	  /* parameters of the AEI */
	  for (formal_par_cell = aei->info.aei->formal_const_par_list,
	       actual_par_cell = actual_aei_par_list;
	       (formal_par_cell != NULL);
	       formal_par_cell = formal_par_cell->next_st_bucket_cell,
	       actual_par_cell = actual_par_cell->next_st_bucket_cell)
	    if (check_eval_var_lengths_bounds(formal_par_cell->st_bucket->info.expr,
					      TRUE,
					      TRUE,
					      formal_par_cell->st_bucket->symbol_lexeme))
	    {
	      eval_expr(actual_par_cell->st_bucket,
		        0);
	      if (check_assign_lengths_bounds(formal_par_cell->st_bucket->info.expr,
					      actual_par_cell->st_bucket->info.expr,
					      TRUE,
					      formal_par_cell->st_bucket->symbol_lexeme))
		assign_expr_eval(formal_par_cell->st_bucket->info.expr,
				 actual_par_cell->st_bucket->info.expr,
				 ASSIGN_TO_LEFT,
				 NULL);
	    }
	  /* generate the initial assignments for the formal variable parameters of the initial behavior */
	  /* of the AEI */
	  if (aei->info.aei->behavior_list != NULL)
	    for (formal_par_cell =
		   aei->info.aei->behavior_list->st_bucket->info.behavior->formal_var_par_list,
	         actual_par_cell =
		   aei->info.aei->behavior_list->st_bucket->info.behavior->actual_var_par_list,
	         last_init_state_var_assign_cell = NULL;
	         (formal_par_cell != NULL);
	         formal_par_cell = formal_par_cell->next_st_bucket_cell,
	         actual_par_cell = actual_par_cell->next_st_bucket_cell)
	      if (check_expr_randomness_free(actual_par_cell->st_bucket) &&
		  check_eval_var_lengths_bounds(formal_par_cell->st_bucket->info.expr,
					        TRUE,
					        TRUE,
					        formal_par_cell->st_bucket->symbol_lexeme))
	      {
	        eval_expr(actual_par_cell->st_bucket,
			  0);
	        if (check_assign_lengths_bounds(formal_par_cell->st_bucket->info.expr,
					        actual_par_cell->st_bucket->info.expr,
					        TRUE,
					        formal_par_cell->st_bucket->symbol_lexeme))
		{
	          if (archi_type[spec_no]->info.archi_type->value_passing == (char)CONCRETE_VP)
	          {
	            /* generate a concrete assignment after transforming the right-hand side of the */
		    /* corresponding symbolic assignment into a concrete expression */
		    assignment = set_expr_bucket(ASSIGN_OP,
					         formal_par_cell->st_bucket,
					         set_concrete_expr_bucket(actual_par_cell->st_bucket),
					         NULL,
					         0.0L,
					         NULL,
					         FALSE);
	            if (aei->info.aei->init_state_var_assign_list == NULL)
	              last_init_state_var_assign_cell = aei->info.aei->init_state_var_assign_list =
	                alloc_st_bucket_cell(assignment,
				             NULL);
	            else
	              last_init_state_var_assign_cell =
			last_init_state_var_assign_cell->next_st_bucket_cell =
	                  alloc_st_bucket_cell(assignment,
				               NULL);
	          }
	          else
	          {
	            /* generate a symbolic assignment */
		    assignment = set_expr_bucket(ASSIGN_OP,
					         formal_par_cell->st_bucket,
					         actual_par_cell->st_bucket,
					         NULL,
					         0.0L,
					         NULL,
					         FALSE);
	            if (archi_type[spec_no]->info.archi_type->init_assign_list == NULL)
	              last_init_assign_cell = archi_type[spec_no]->info.archi_type->init_assign_list =
	                alloc_st_bucket_cell(assignment,
				             NULL);
	            else
	              last_init_assign_cell = last_init_assign_cell->next_st_bucket_cell =
	                alloc_st_bucket_cell(assignment,
				             NULL);
	          }
	        }
	      }
	}
}


ST_BUCKET_CELL	*expand_iterative_ai_decl(void)
{
	ST_BUCKET	*concrete_ai,
			*concrete_aei;
	ST_BUCKET_CELL	*concrete_ai_list,
			*last_concrete_ai_cell;
	int		min_value,
			max_value,
			value;

	concrete_ai_list = last_concrete_ai_cell = NULL;
	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value;
	     (value <= max_value);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_aei = unindexed_aei[0];
	  build_indexed_id(&concrete_aei,
			   set_concrete_expr_bucket(index_expr[0]));
	  check_id(AEI_ID_USE,
		   &concrete_aei,
		   TRUE);
	  if (concrete_aei != NULL)
	  {
	    concrete_ai = unindexed_id[0];
	    build_prefixed_id(concrete_aei,
			      &concrete_ai);
	    check_id(ACT_TYPE_ID_USE,
		     &concrete_ai,
		     TRUE);
	    if (concrete_ai != NULL)
	    {
	      handle_architectural_interaction(&concrete_ai,
					       TRUE);
	      if (concrete_ai != NULL)
	      {
	        if (concrete_ai_list == NULL)
	          last_concrete_ai_cell = concrete_ai_list = alloc_st_bucket_cell(concrete_ai,
										  NULL);
	        else
	          last_concrete_ai_cell = last_concrete_ai_cell->next_st_bucket_cell =
		    alloc_st_bucket_cell(concrete_ai,
				         NULL);
	      }
	    }
	  }
	}
	return(concrete_ai_list);
}


void		handle_architectural_interaction(ST_BUCKET **interaction,
						 BOOLEAN   inside_iteration)
{
	if ((*interaction)->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
		         (*interaction)->symbol_lexeme,
		         NULL);
	  *interaction = NULL;
	}
	else
	  if ((*interaction)->info.act_type->interaction_index == NO_INTERACTION)
	  {
	    if (!inside_iteration)
	      signal_error(ACT_TYPE_NOT_INTERACTION,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_ACT_TYPE_NOT_INTERACTION,
		           (*interaction)->symbol_lexeme,
		           NULL);
	    *interaction = NULL;
	  }
	  else
	    if ((*interaction)->info.act_type->architectural)
	    {
	      if (!inside_iteration)
	        signal_error(INTERACTION_ALREADY_DECLARED_AI,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_INTERACTION_ALREADY_DECLARED_AI,
		             (*interaction)->symbol_lexeme,
		             NULL);
	      *interaction = NULL;
	    }
	    else
	      (*interaction)->info.act_type->architectural = TRUE;
}


void		expand_iterative_attachment_decl(ST_BUCKET *output_interaction,
						 ST_BUCKET *input_interaction)
{
	ST_BUCKET	*concrete_aei[2],
			*concrete_interaction[2];
	int		min_value[2],
			max_value[2],
			value[2],
			i;

	min_value[0] = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value[0] = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value[0] = min_value[0];
	     (value[0] <= max_value[0]);
	     value[0]++)
	{
	  min_value[1] = (selector[1] == NULL)?
			   0:
			   (int)(selector[1]->info.expr->opn1->info.expr->value);
	  max_value[1] = (selector[1] == NULL)?
			   0:
			   (int)(selector[1]->info.expr->opn2->info.expr->value);
	  for (value[1] = min_value[1];
	       (value[1] <= max_value[1]);
	       value[1]++)
	  {
	    selector[0]->info.expr->value = (long double)(value[0]);
	    if (selector[1] != NULL)
	      selector[1]->info.expr->value = (long double)(value[1]);
	    for (i = 0;
		 (i <= 1);
		 i++)
	    {
	      if (index_expr[i] == NULL)
		concrete_interaction[i] = unindexed_id[i];
	      else
	      {
	        eval_expr(index_expr[i],
			  0);
	        concrete_aei[i] = unindexed_aei[i];
	        build_indexed_id(&(concrete_aei[i]),
			         set_concrete_expr_bucket(index_expr[i]));
		check_id(AEI_ID_USE,
			 &(concrete_aei[i]),
			 TRUE);
		if (concrete_aei[i] != NULL)
		{
	          concrete_interaction[i] = unindexed_id[i];
	          build_prefixed_id(concrete_aei[i],
				    &(concrete_interaction[i]));
		}
		else
		  concrete_interaction[i] = NULL;
	      }
	      if (concrete_interaction[i] != NULL)
	      {
		/* the action type must be checked anyway, as it may have erroneously occurred without */
	        /* any index */
	        check_id(ACT_TYPE_ID_USE,
		         &(concrete_interaction[i]),
			 TRUE);
	        if (concrete_interaction[i] != NULL)
	        {
		  if (i == 0)
		    check_output_interaction(&concrete_interaction[i],
					     TRUE);
		  else
		    check_input_interaction(&concrete_interaction[i],
					    TRUE);
	        }
	      }
	    }
	    if ((concrete_interaction[0] != NULL) &&
	        (concrete_interaction[1] != NULL) &&
		(concrete_aei[0] == concrete_aei[1]))
	    {
	      signal_error(ONE_AUTO_ATTACHMENT,
			   concrete_interaction[0]->symbol_lexeme,
			   concrete_interaction[1]->symbol_lexeme);
	      concrete_interaction[0] = NULL;
	    }
	    if ((concrete_interaction[0] != NULL) &&
	        (concrete_interaction[1] != NULL))
	      attach_interactions(concrete_interaction[0],
				  concrete_interaction[1],
				  TRUE);
	  }
	}
}


void		attach_interactions(ST_BUCKET *output_interaction,
				    ST_BUCKET *input_interaction,
				    BOOLEAN   inside_iteration)
{
	BOOLEAN		found;
	ST_BUCKET_CELL	*attached_interaction_cell;

	for (attached_interaction_cell = input_interaction->info.act_type->attached_interaction_list,
	     found = FALSE;
	     ((attached_interaction_cell != NULL) &&
	      !found);
	     attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell)
	  found = (attached_interaction_cell->st_bucket == output_interaction);
	if (found)
	{
	  if (!inside_iteration)
	    signal_error(ATTACHMENT_ALREADY_DECLARED,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_ATTACHMENT_ALREADY_DECLARED,
		         output_interaction->symbol_lexeme,
		         input_interaction->symbol_lexeme);
	}
	else
	  if ((((input_interaction->info.act_type->interaction_index == INPUT_AND) ||
		(input_interaction->info.act_type->interaction_index == INPUT_OR)) &&
	       (output_interaction->info.act_type->interaction_index != OUTPUT_UNI)) ||
	      (((output_interaction->info.act_type->interaction_index == OUTPUT_AND) ||
		(output_interaction->info.act_type->interaction_index == OUTPUT_OR)) &&
	        (input_interaction->info.act_type->interaction_index != INPUT_UNI)))
	  {
	    if (!inside_iteration)
	      signal_error(TWO_NON_UNI_ATTACHED,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_TWO_NON_UNI_ATTACHED,
		           output_interaction->symbol_lexeme,
		           input_interaction->symbol_lexeme);
	  }
	  else
	  {
	    found = FALSE;
	    if ((output_interaction->info.act_type->interaction_index == OUTPUT_AND) ||
		(output_interaction->info.act_type->interaction_index == OUTPUT_OR))
	    {
	      for (attached_interaction_cell =
		     output_interaction->info.act_type->attached_interaction_list;
	           ((attached_interaction_cell != NULL) &&
		    !found);
	           attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell)
	        found = (attached_interaction_cell->st_bucket->info.act_type->aei ==
			   input_interaction->info.act_type->aei);
	      if (found)
	      {
	        if (!inside_iteration)
	          signal_error(AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
			       NULL,
			       NULL);
		else
	          signal_error(ONE_AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
		               output_interaction->symbol_lexeme,
		               input_interaction->symbol_lexeme);
	      }
	    }
	    if (!found)
	    {
	      if ((input_interaction->info.act_type->interaction_index == INPUT_AND) ||
		  (input_interaction->info.act_type->interaction_index == INPUT_OR))
	      {
	        for (attached_interaction_cell =
		       input_interaction->info.act_type->attached_interaction_list;
	             ((attached_interaction_cell != NULL) &&
		      !found);
	             attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell)
	          found = (attached_interaction_cell->st_bucket->info.act_type->aei ==
			     output_interaction->info.act_type->aei);
	        if (found)
	        {
	          if (!inside_iteration)
	            signal_error(AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
			         NULL,
			         NULL);
		  else
	            signal_error(ONE_AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES,
		                 output_interaction->symbol_lexeme,
		                 input_interaction->symbol_lexeme);
	        }
	      }
	      if (!found)
	      {
	        if (!check_expr_list_types(input_interaction->info.act_type->par_list,
				           output_interaction->info.act_type->par_list,
				           FALSE))
	        {
	          if (!inside_iteration)
	            signal_error(ILL_TYPED_ATTACHMENT,
			         NULL,
			         NULL);
		  else
	            signal_error(ONE_ILL_TYPED_ATTACHMENT,
		                 output_interaction->symbol_lexeme,
		                 input_interaction->symbol_lexeme);
	        }
	        else
	          if ((input_interaction->info.act_type->rate_index != PASSIVE) &&
	              (output_interaction->info.act_type->rate_index != PASSIVE))
	          {
	            if (!inside_iteration)
	              signal_error(TWO_NON_PASSIVE_ATTACHMENT,
			           NULL,
			           NULL);
	            else
	              signal_error(ONE_TWO_NON_PASSIVE_ATTACHMENT,
		                   output_interaction->symbol_lexeme,
		                   input_interaction->symbol_lexeme);
	          }
	          else
		    if (((input_interaction->info.act_type->interaction_index == INPUT_AND) &&
		         (output_interaction->info.act_type->rate_index != PASSIVE) &&
		         check_active_attached(input_interaction->info.act_type->
						 attached_interaction_list)) ||
		        ((output_interaction->info.act_type->interaction_index == OUTPUT_AND) &&
		         (input_interaction->info.act_type->rate_index != PASSIVE) &&
		         check_active_attached(output_interaction->info.act_type->
						 attached_interaction_list)))
	            {
	              if (!inside_iteration)
	                signal_error(TWO_NON_PASSIVE_AND_ATTACHMENT,
			             NULL,
			             NULL);
		      else
	                signal_error(ONE_TWO_NON_PASSIVE_AND_ATTACHMENT,
		                     output_interaction->symbol_lexeme,
		                     input_interaction->symbol_lexeme);
	            }
		    else
	            {
	              if (input_interaction->info.act_type->attached_interaction_list == NULL)
	                input_interaction->info.act_type->attached_interaction_list =
		          alloc_st_bucket_cell(output_interaction,
				               NULL);
	              else
	              {
	                for (attached_interaction_cell =
		               input_interaction->info.act_type->attached_interaction_list;
		             (attached_interaction_cell->next_st_bucket_cell != NULL);
		             attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell);
	                attached_interaction_cell->next_st_bucket_cell =
			  alloc_st_bucket_cell(output_interaction,
					       NULL);
	              }
	              if (output_interaction->info.act_type->attached_interaction_list == NULL)
	                output_interaction->info.act_type->attached_interaction_list =
		          alloc_st_bucket_cell(input_interaction,
				               NULL);
	              else
	              {
	                for (attached_interaction_cell =
		               output_interaction->info.act_type->attached_interaction_list;
		             (attached_interaction_cell->next_st_bucket_cell != NULL);
		             attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell);
	                attached_interaction_cell->next_st_bucket_cell =
			  alloc_st_bucket_cell(input_interaction,
					       NULL);
	              }
	            }
	      }
	    }
	  }
}


BOOLEAN		check_active_attached(ST_BUCKET_CELL *attached_interaction_list)
{
	BOOLEAN		found;
	ST_BUCKET_CELL	*attached_interaction_cell;

	for (attached_interaction_cell = attached_interaction_list,
	     found = FALSE;
	     ((attached_interaction_cell != NULL) &&
	      !found);
	     attached_interaction_cell = attached_interaction_cell->next_st_bucket_cell)
	  found = (attached_interaction_cell->st_bucket->info.act_type->rate_index != PASSIVE);
	return(found);
}


void		check_output_interaction(ST_BUCKET **act_type,
					 BOOLEAN   inside_iteration)
{
	if ((*act_type)->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
			 NULL,
			 NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
			 (*act_type)->symbol_lexeme,
			 NULL);
	  *act_type = NULL;
	}
	else
	  if (((*act_type)->info.act_type->interaction_index != OUTPUT_UNI) &&
	      ((*act_type)->info.act_type->interaction_index != OUTPUT_AND) &&
	      ((*act_type)->info.act_type->interaction_index != OUTPUT_OR))
	  {
	    if (!inside_iteration)
	      signal_error(ACT_TYPE_NOT_OUTPUT_INTERACTION,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_ACT_TYPE_NOT_OUTPUT_INTERACTION,
			   (*act_type)->symbol_lexeme,
			   NULL);
	    *act_type = NULL;
	  }
	  else
	    if ((*act_type)->info.act_type->architectural)
	    {
	      if (!inside_iteration)
	        signal_error(AI_IN_ATTACHMENT,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_AI_IN_ATTACHMENT,
			     (*act_type)->symbol_lexeme,
			     NULL);
	      *act_type = NULL;
	    }
	    else
	      if (((*act_type)->info.act_type->interaction_index == OUTPUT_UNI) &&
		  ((*act_type)->info.act_type->attached_interaction_list != NULL))
	      {
	        if (!inside_iteration)
	          signal_error(UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
			       NULL,
			       NULL);
		else
	          signal_error(ONE_UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
			       (*act_type)->symbol_lexeme,
			       NULL);
	        *act_type = NULL;
	      }
}


void		check_input_interaction(ST_BUCKET **act_type,
					BOOLEAN   inside_iteration)
{
	if ((*act_type)->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
			 NULL,
			 NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
			 (*act_type)->symbol_lexeme,
			 NULL);
	  *act_type = NULL;
	}
	else
	  if (((*act_type)->info.act_type->interaction_index != INPUT_UNI) &&
	      ((*act_type)->info.act_type->interaction_index != INPUT_AND) &&
	      ((*act_type)->info.act_type->interaction_index != INPUT_OR))
	  {
	    if (!inside_iteration)
	      signal_error(ACT_TYPE_NOT_INPUT_INTERACTION,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_ACT_TYPE_NOT_INPUT_INTERACTION,
			   (*act_type)->symbol_lexeme,
			   NULL);
	    *act_type = NULL;
	  }
	  else
	    if ((*act_type)->info.act_type->architectural)
	    {
	      if (!inside_iteration)
	        signal_error(AI_IN_ATTACHMENT,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_AI_IN_ATTACHMENT,
			     (*act_type)->symbol_lexeme,
			     NULL);
	      *act_type = NULL;
	    }
	    else
	      if (((*act_type)->info.act_type->interaction_index == INPUT_UNI) &&
		  ((*act_type)->info.act_type->attached_interaction_list != NULL))
	      {
	        if (!inside_iteration)
	          signal_error(UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
			       NULL,
			       NULL);
		else
	          signal_error(ONE_UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS,
			       NULL,
			       NULL);
	        *act_type = NULL;
	      }
}


void		create_sync_act_types(void)
{
		ST_BUCKET	*act_type,
				*attached_act_type,
				*sync_act_type,
				*dupl_or_act_type;
		ST_BUCKET_CELL	*aei_cell,
				*act_type_cell,
				*attached_act_type_cell,
				*last_duplicate_cell,
				*correlate_list,
				*correlate_cell;
	static	char		*sync_act_type_name		=	NULL;
		int		length,
				min_aei_no,
				attachment_no;
	static	int		max_length			=	0;


	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  if (aei_cell->st_bucket->info.aei != NULL)
	    for (act_type_cell = aei_cell->st_bucket->info.aei->act_type_list;
	         (act_type_cell != NULL);
	         act_type_cell = act_type_cell->next_st_bucket_cell)
	    {
	      act_type = act_type_cell->st_bucket;
	      if ((act_type->info.act_type != NULL) &&
		  (act_type->info.act_type->attached_interaction_list != NULL) &&
	          (act_type->info.act_type->renamed == NULL))
	        switch (act_type->info.act_type->interaction_index)
	        {
		  case INPUT_UNI:
		  case OUTPUT_UNI:
		    attached_act_type = act_type->info.act_type->attached_interaction_list->st_bucket;
		    if ((attached_act_type->info.act_type->interaction_index == OUTPUT_UNI) ||
		        (attached_act_type->info.act_type->interaction_index == INPUT_UNI))
		    {
		      length = strlen(act_type->symbol_lexeme) +
			       1 +
			       strlen(attached_act_type->symbol_lexeme);
		      if (length > max_length)
        	      {
          	        if (sync_act_type_name != NULL)
            	          free(sync_act_type_name);
          	        sync_act_type_name = alloc_string(length);
          	        max_length = length;
        	      }
		      sprintf(sync_act_type_name,
			      "%s#%s",
			      act_type->symbol_lexeme,
			      attached_act_type->symbol_lexeme);
		      sync_act_type = (ST_BUCKET *)search_lexeme_table(sync_act_type_name,
							               SYT);
		      check_id(ACT_TYPE_ID_USE,
			       &sync_act_type,
			       FALSE);
		      sync_act_type->info.act_type = alloc_act_type(UNSTRUCTURED,
							            act_type->info.act_type->par_list,
							            NULL);
		      act_type->info.act_type->renamed = sync_act_type;
		      attached_act_type->info.act_type->renamed = sync_act_type;
		      if (aei_cell->st_bucket->info.aei->aei_no <
			    attached_act_type->info.act_type->aei->info.aei->aei_no)
		        attached_act_type->info.act_type->aei->info.aei->sync_set =
			  alloc_st_bucket_cell(sync_act_type,
					       attached_act_type->info.act_type->aei->info.aei->sync_set);
		      else
		        aei_cell->st_bucket->info.aei->sync_set =
			  alloc_st_bucket_cell(sync_act_type,
					       aei_cell->st_bucket->info.aei->sync_set);
		    }
		    break;
		  case INPUT_AND:
		  case OUTPUT_AND:
		    for (length = strlen(act_type->symbol_lexeme),
		         attached_act_type_cell = act_type->info.act_type->attached_interaction_list;
		         (attached_act_type_cell != NULL);
		         length += 1 + strlen(attached_act_type_cell->st_bucket->symbol_lexeme),
		         attached_act_type_cell = attached_act_type_cell->next_st_bucket_cell);
		    if (length > max_length)
        	    {
          	      if (sync_act_type_name != NULL)
            	        free(sync_act_type_name);
          	      sync_act_type_name = alloc_string(length);
          	      max_length = length;
        	    }
		    strcpy(sync_act_type_name,
			   act_type->symbol_lexeme);
		    for (attached_act_type_cell = act_type->info.act_type->attached_interaction_list;
		         (attached_act_type_cell != NULL);
		         attached_act_type_cell = attached_act_type_cell->next_st_bucket_cell)
        	    {
		      strcat(sync_act_type_name,
			     "#");
		      strcat(sync_act_type_name,
			     attached_act_type_cell->st_bucket->symbol_lexeme);
        	    }
		    sync_act_type = (ST_BUCKET *)search_lexeme_table(sync_act_type_name,
							             SYT);
		    check_id(ACT_TYPE_ID_USE,
			     &sync_act_type,
			     FALSE);
		    sync_act_type->info.act_type = alloc_act_type(UNSTRUCTURED,
						                  act_type->info.act_type->par_list,
						                  NULL);
		    act_type->info.act_type->renamed = sync_act_type;
		    for (attached_act_type_cell = act_type->info.act_type->attached_interaction_list,
		         min_aei_no = aei_cell->st_bucket->info.aei->aei_no;
		         (attached_act_type_cell != NULL);
		         attached_act_type_cell = attached_act_type_cell->next_st_bucket_cell)
		    {
		      attached_act_type_cell->st_bucket->info.act_type->renamed = sync_act_type;
		      if (attached_act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no <
			    min_aei_no)
		        min_aei_no =
			  attached_act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no;
		    }
		    if (aei_cell->st_bucket->info.aei->aei_no > min_aei_no)
		      aei_cell->st_bucket->info.aei->sync_set =
		        alloc_st_bucket_cell(sync_act_type,
					     aei_cell->st_bucket->info.aei->sync_set);
		    for (attached_act_type_cell = act_type->info.act_type->attached_interaction_list;
		         (attached_act_type_cell != NULL);
		         attached_act_type_cell = attached_act_type_cell->next_st_bucket_cell)
		      if (attached_act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no >
			    min_aei_no)
		        attached_act_type_cell->st_bucket->info.act_type->aei->info.aei->sync_set =
			  alloc_st_bucket_cell(
			    sync_act_type,
			    attached_act_type_cell->st_bucket->info.act_type->aei->info.aei->sync_set);
		    break;
		  case INPUT_OR:
		  case OUTPUT_OR:
		    for (attached_act_type_cell = act_type->info.act_type->attached_interaction_list,
		         attachment_no = 1,
		         last_duplicate_cell = act_type->info.act_type->duplicate_list,
			 correlate_list = NULL;
		         (attached_act_type_cell != NULL);
		         attached_act_type_cell = attached_act_type_cell->next_st_bucket_cell,
			 attachment_no++)
		    {
		      /* create a new duplicate of the or action type */
		      length = strlen(act_type->symbol_lexeme) +
			       1 +
			       compute_digit_num((double)attachment_no);
		      if (length > max_length)
        	      {
          	        if (sync_act_type_name != NULL)
            	          free(sync_act_type_name);
          	        sync_act_type_name = alloc_string(length);
          	        max_length = length;
        	      }
		      sprintf(sync_act_type_name,
			      "%s.%d",
			      act_type->symbol_lexeme,
			      attachment_no);
		      dupl_or_act_type = (ST_BUCKET *)search_lexeme_table(sync_act_type_name,
							                  SYT);
		      check_id(ACT_TYPE_ID_USE,
			       &dupl_or_act_type,
			       FALSE);
		      dupl_or_act_type->info.act_type =
			alloc_act_type(act_type->info.act_type->act_type_index,
				       act_type->info.act_type->par_list,
				       act_type->info.act_type->aei);
		      dupl_or_act_type->info.act_type->priority = act_type->info.act_type->priority;
		      dupl_or_act_type->info.act_type->interaction_index =
		        (act_type->info.act_type->interaction_index == INPUT_OR)?
			  INPUT_UNI:
			  OUTPUT_UNI;
		      dupl_or_act_type->info.act_type->architectural =
		        act_type->info.act_type->architectural;
		      dupl_or_act_type->info.act_type->attached_interaction_list =
		        alloc_st_bucket_cell(attached_act_type_cell->st_bucket,
					     NULL);
		      if (last_duplicate_cell == NULL)
		        last_duplicate_cell = act_type->info.act_type->duplicate_list =
			  alloc_st_bucket_cell(dupl_or_act_type,
					       NULL);
		      else
		        last_duplicate_cell = last_duplicate_cell->next_st_bucket_cell =
			  alloc_st_bucket_cell(dupl_or_act_type,
					       NULL);
		      /* create the synchronization action type */
		      attached_act_type = attached_act_type_cell->st_bucket;
		      length += 1 +
			        strlen(attached_act_type->symbol_lexeme);
		      if (length > max_length)
        	      {
          	        if (sync_act_type_name != NULL)
            	          free(sync_act_type_name);
          	        sync_act_type_name = alloc_string(length);
          	        max_length = length;
        	      }
		      sprintf(sync_act_type_name,
			      "%s#%s",
			      dupl_or_act_type->symbol_lexeme,
			      attached_act_type->symbol_lexeme);
		      sync_act_type = (ST_BUCKET *)search_lexeme_table(sync_act_type_name,
							               SYT);
		      check_id(ACT_TYPE_ID_USE,
			       &sync_act_type,
			       FALSE);
		      sync_act_type->info.act_type = alloc_act_type(UNSTRUCTURED,
						                    act_type->info.act_type->par_list,
							            NULL);
		      sync_act_type->info.act_type->interaction_index = SYNC_OR;
		      dupl_or_act_type->info.act_type->renamed = sync_act_type;
		      attached_act_type->info.act_type->renamed = sync_act_type;
		      if (aei_cell->st_bucket->info.aei->aei_no <
			    attached_act_type->info.act_type->aei->info.aei->aei_no)
		        attached_act_type->info.act_type->aei->info.aei->sync_set =
			  alloc_st_bucket_cell(sync_act_type,
					       attached_act_type->info.act_type->aei->info.aei->sync_set);
		      else
		        aei_cell->st_bucket->info.aei->sync_set =
			  alloc_st_bucket_cell(sync_act_type,
					       aei_cell->st_bucket->info.aei->sync_set);
		      correlate_list = alloc_st_bucket_cell(sync_act_type,
							    correlate_list);
		    }
		    /* associate the list of correlate action types with each newly created */
		    /* synchronization action type */
		    for (correlate_cell = correlate_list;
			 (correlate_cell != NULL);
			 correlate_cell = correlate_cell->next_st_bucket_cell)
		      correlate_cell->st_bucket->info.act_type->duplicate_list = correlate_list;
		    break;
		  default:
		    break;
	        }
	    }
}


void		expand_iterative_hiding_decl(ST_BUCKET *symbolic_act_type)
{
	ST_BUCKET	*concrete_act_type,
			*concrete_aei;
	int		min_value,
			max_value,
			value;

	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value;
	     (value <= max_value);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_aei = unindexed_aei[0];
	  build_indexed_id(&concrete_aei,
			   set_concrete_expr_bucket(index_expr[0]));
	  check_id(AEI_ID_USE,
		   &concrete_aei,
		   TRUE);
	  if (concrete_aei != NULL)
	    switch (symbolic_act_type->symbol_index)
	    {
	      case ACT_TYPE_ID:
	        concrete_act_type = unindexed_id[0];
	        build_prefixed_id(concrete_aei,
			          &concrete_act_type);
	        check_id(ACT_TYPE_ID_USE,
		         &concrete_act_type,
		         TRUE);
	        if (concrete_act_type != NULL)
	          handle_hiding(concrete_act_type,
				TRUE);
		break;
	      case INTERNALS:
		hide_aei_internals(concrete_aei);
		break;
	      case INTERACTIONS:
		hide_aei_interactions(concrete_aei);
		break;
	      case ALL:
		hide_aei_all(concrete_aei);
		break;
	      default:
		break;
	    }
	}
}


void		handle_hiding(ST_BUCKET *act_type,
			      BOOLEAN   inside_iteration)
{
	ST_BUCKET_CELL	*duplicate_cell;

	if (act_type->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
		         act_type->symbol_lexeme,
		         NULL);
	}
	else
	  if (act_type->info.act_type->architectural)
	  {
	    if (!inside_iteration)
	      signal_error(AI_IN_HIDING,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_AI_IN_HIDING,
		           act_type->symbol_lexeme,
			   NULL);
	  }
	  else
	    if (((act_type->info.act_type->renamed == NULL) &&
		 (act_type->info.act_type->variation == HIDDEN)) ||
		((act_type->info.act_type->renamed != NULL) &&
		 (act_type->info.act_type->renamed->info.act_type->variation == HIDDEN)))
	    {
	      if (!inside_iteration)
	        signal_error(HIDING_ALREADY_DECLARED,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_HIDING_ALREADY_DECLARED,
		             act_type->symbol_lexeme,
			     NULL);
	    }
	    else
	    {
	      act_type->info.act_type->variation = HIDDEN;
	      if (act_type->info.act_type->renamed == NULL)
		for (duplicate_cell = act_type->info.act_type->duplicate_list;
		     (duplicate_cell != NULL);
		     duplicate_cell = duplicate_cell->next_st_bucket_cell)
		{
		  duplicate_cell->st_bucket->info.act_type->variation = HIDDEN;
		  duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation = HIDDEN;
		}
	      else
		act_type->info.act_type->renamed->info.act_type->variation = HIDDEN;
	    }
}


void		hide_internals(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  hide_aei_internals(aei_cell->st_bucket);
}


void		hide_aei_internals(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if (act_type_cell->st_bucket->info.act_type->interaction_index == NO_INTERACTION)
	    act_type_cell->st_bucket->info.act_type->variation = HIDDEN;
}


void		hide_interactions(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  hide_aei_interactions(aei_cell->st_bucket);
}


void		hide_aei_interactions(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell,
			*duplicate_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if ((act_type_cell->st_bucket->info.act_type->interaction_index != NO_INTERACTION) &&
	      !act_type_cell->st_bucket->info.act_type->architectural)
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


void		hide_all(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  hide_aei_all(aei_cell->st_bucket);
}


void		hide_aei_all(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell,
			*duplicate_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if (!act_type_cell->st_bucket->info.act_type->architectural)
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


void		expand_iterative_restriction_decl(ST_BUCKET *symbolic_act_type)
{
	ST_BUCKET	*concrete_act_type,
			*concrete_aei;
	int		min_value,
			max_value,
			value;

	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value;
	     (value <= max_value);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_aei = unindexed_aei[0];
	  build_indexed_id(&concrete_aei,
			   set_concrete_expr_bucket(index_expr[0]));
	  check_id(AEI_ID_USE,
		   &concrete_aei,
		   TRUE);
	  if (concrete_aei != NULL)
	    switch (symbolic_act_type->symbol_index)
	    {
	      case ACT_TYPE_ID:
	        concrete_act_type = unindexed_id[0];
	        build_prefixed_id(concrete_aei,
			          &concrete_act_type);
	        check_id(ACT_TYPE_ID_USE,
		         &concrete_act_type,
		         TRUE);
	        if (concrete_act_type != NULL)
	          handle_restriction(concrete_act_type,
				     TRUE);
		break;
	      case OBS_INTERNALS:
		restrict_aei_obs_internals(concrete_aei);
		break;
	      case OBS_INTERACTIONS:
		restrict_aei_obs_interactions(concrete_aei);
		break;
	      case ALL_OBSERVABLES:
		restrict_aei_all_observables(concrete_aei);
		break;
	      default:
		break;
	    }
	}
}


void		handle_restriction(ST_BUCKET *act_type,
			           BOOLEAN   inside_iteration)
{
	ST_BUCKET_CELL	*duplicate_cell;

	if (act_type->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
			 NULL,
			 NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
			 act_type->symbol_lexeme,
			 NULL);
	}
	else
	  if (act_type->info.act_type->architectural)
	  {
	    if (!inside_iteration)
	      signal_error(AI_IN_RESTRICTION,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_AI_IN_RESTRICTION,
			   act_type->symbol_lexeme,
			   NULL);
	  }
	  else
	    if (((act_type->info.act_type->renamed == NULL) &&
		 (act_type->info.act_type->variation == HIDDEN)) ||
		((act_type->info.act_type->renamed != NULL) &&
		 (act_type->info.act_type->renamed->info.act_type->variation == HIDDEN)))
	    {
	      if (!inside_iteration)
	        signal_error(HIDDEN_ACT_TYPE_IN_RESTRICTION,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_HIDDEN_ACT_TYPE_IN_RESTRICTION,
			     act_type->symbol_lexeme,
			     NULL);
	    }
	    else
	      if (((act_type->info.act_type->renamed == NULL) &&
		   (act_type->info.act_type->variation == RESTRICTED)) ||
		  ((act_type->info.act_type->renamed != NULL) &&
		   (act_type->info.act_type->renamed->info.act_type->variation == RESTRICTED)))
	      {
	        if (!inside_iteration)
	          signal_error(RESTRICTION_ALREADY_DECLARED,
			       NULL,
			       NULL);
	        else
	          signal_error(ONE_RESTRICTION_ALREADY_DECLARED,
			       act_type->symbol_lexeme,
			       NULL);
	      }
	      else
	      {
		act_type->info.act_type->variation = RESTRICTED;
		if (act_type->info.act_type->renamed == NULL)
		  for (duplicate_cell = act_type->info.act_type->duplicate_list;
		       (duplicate_cell != NULL);
		       duplicate_cell = duplicate_cell->next_st_bucket_cell)
		  {
		    duplicate_cell->st_bucket->info.act_type->variation = RESTRICTED;
		    duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation =
		      RESTRICTED;
		  }
		else
		  act_type->info.act_type->renamed->info.act_type->variation = RESTRICTED;
	      }
}


void		restrict_obs_internals(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  restrict_aei_obs_internals(aei_cell->st_bucket);
}


void		restrict_aei_obs_internals(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if ((act_type_cell->st_bucket->info.act_type->interaction_index == NO_INTERACTION) &&
	      (act_type_cell->st_bucket->info.act_type->variation == UNVARIED))
	    act_type_cell->st_bucket->info.act_type->variation = RESTRICTED;
}


void		restrict_obs_interactions(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  restrict_aei_obs_interactions(aei_cell->st_bucket);
}


void		restrict_aei_obs_interactions(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell,
			*duplicate_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if ((act_type_cell->st_bucket->info.act_type->interaction_index != NO_INTERACTION) &&
	      !act_type_cell->st_bucket->info.act_type->architectural &&
	      (act_type_cell->st_bucket->info.act_type->variation == UNVARIED))
	  {
	    act_type_cell->st_bucket->info.act_type->variation = RESTRICTED;
	    if (act_type_cell->st_bucket->info.act_type->renamed == NULL)
	      for (duplicate_cell = act_type_cell->st_bucket->info.act_type->duplicate_list;
		   (duplicate_cell != NULL);
		   duplicate_cell = duplicate_cell->next_st_bucket_cell)
	      {
		duplicate_cell->st_bucket->info.act_type->variation = RESTRICTED;
		duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation = RESTRICTED;
	      }
	    else
	      act_type_cell->st_bucket->info.act_type->renamed->info.act_type->variation = RESTRICTED;
	  }
}


void		restrict_all_observables(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  restrict_aei_all_observables(aei_cell->st_bucket);
}


void		restrict_aei_all_observables(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell,
			*duplicate_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if (!act_type_cell->st_bucket->info.act_type->architectural &&
	      (act_type_cell->st_bucket->info.act_type->variation == UNVARIED))
	  {
	    act_type_cell->st_bucket->info.act_type->variation = RESTRICTED;
	    if (act_type_cell->st_bucket->info.act_type->renamed == NULL)
	      for (duplicate_cell = act_type_cell->st_bucket->info.act_type->duplicate_list;
		   (duplicate_cell != NULL);
		   duplicate_cell = duplicate_cell->next_st_bucket_cell)
	      {
		duplicate_cell->st_bucket->info.act_type->variation = RESTRICTED;
		duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation = RESTRICTED;
	      }
	    else
	      act_type_cell->st_bucket->info.act_type->renamed->info.act_type->variation = RESTRICTED;
	  }
}


void		expand_iterative_renaming_decl(void)
{
	ST_BUCKET	*concrete_old_act_type,
			*concrete_new_act_type,
			*concrete_aei;
	int		min_value,
			max_value,
			value;

	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value;
	     (value <= max_value);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_aei = unindexed_aei[0];
	  build_indexed_id(&concrete_aei,
			   set_concrete_expr_bucket(index_expr[0]));
	  check_id(AEI_ID_USE,
		   &concrete_aei,
		   TRUE);
	  if (concrete_aei != NULL)
	  {
	    concrete_old_act_type = unindexed_id[0];
	    build_prefixed_id(concrete_aei,
			      &concrete_old_act_type);
	    check_id(ACT_TYPE_ID_USE,
		     &concrete_old_act_type,
		     TRUE);
	    if (concrete_old_act_type != NULL)
	      handle_renaming_old(&concrete_old_act_type,
				  TRUE);
	  }
	  else
	    concrete_old_act_type = NULL;
	  concrete_new_act_type = unindexed_id[1];
	  if (index_expr[1] != NULL)
	  {
	    eval_expr(index_expr[1],
		      0);
	    build_indexed_id(&concrete_new_act_type,
			     set_concrete_expr_bucket(index_expr[1]));
	  }
	  check_id(ACT_TYPE_ID_USE,
		   &concrete_new_act_type,
		   TRUE);
	  if ((concrete_old_act_type != NULL) &&
	      (concrete_new_act_type != NULL))
	    handle_renaming_new(concrete_old_act_type,
			        concrete_new_act_type,
			        TRUE);
	}
}


void		handle_renaming_old(ST_BUCKET **act_type,
			            BOOLEAN   inside_iteration)
{
	if ((*act_type)->info.act_type == NULL)
	{
	  if (!inside_iteration)
	    signal_error(ACT_TYPE_NOT_IN_BEHAVIOR,
			 NULL,
			 NULL);
	  else
	    signal_error(ONE_ACT_TYPE_NOT_IN_BEHAVIOR,
			 (*act_type)->symbol_lexeme,
			 NULL);
	  *act_type = NULL;
	}
	else
	  if ((*act_type)->info.act_type->variation == HIDDEN)
	  {
	    if (!inside_iteration)
	      signal_error(HIDDEN_ACT_TYPE_IN_RENAMING,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_HIDDEN_ACT_TYPE_IN_RENAMING,
			   (*act_type)->symbol_lexeme,
			   NULL);
	    *act_type = NULL;
	  }
	  else
	    if ((*act_type)->info.act_type->variation == RESTRICTED)
	    {
	      if (!inside_iteration)
	        signal_error(RESTRICTED_ACT_TYPE_IN_RENAMING,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_RESTRICTED_ACT_TYPE_IN_RENAMING,
			     (*act_type)->symbol_lexeme,
			     NULL);
	      *act_type = NULL;
	    }
}


void		handle_renaming_new(ST_BUCKET *old_act_type,
				    ST_BUCKET *new_act_type,
			            BOOLEAN   inside_iteration)
{
	ST_BUCKET_CELL	*duplicate_cell;

	if (((old_act_type->info.act_type->renamed == NULL) &&
	     (old_act_type->info.act_type->duplicate_list != NULL) &&
	     (old_act_type->info.act_type->duplicate_list->st_bucket->info.act_type->renamed->
		info.act_type->renamed != NULL)) ||
	    ((old_act_type->info.act_type->renamed != NULL) &&
	     (old_act_type->info.act_type->attached_interaction_list == NULL)) ||
	    ((old_act_type->info.act_type->renamed != NULL) &&
	     (old_act_type->info.act_type->attached_interaction_list != NULL) &&
	     (old_act_type->info.act_type->renamed->info.act_type->renamed != NULL)))
	{
	  if (((old_act_type->info.act_type->renamed == NULL) &&
	       (old_act_type->info.act_type->duplicate_list->st_bucket->info.act_type->renamed->
		  info.act_type->renamed != new_act_type)) ||
	      ((old_act_type->info.act_type->renamed != new_act_type) &&
	       (old_act_type->info.act_type->attached_interaction_list == NULL)) ||
	      ((old_act_type->info.act_type->renamed != NULL) &&
	       (old_act_type->info.act_type->attached_interaction_list != NULL) &&
	       (old_act_type->info.act_type->renamed->info.act_type->renamed != new_act_type)))
	  {
	    if (!inside_iteration)
	      signal_error(RENAMING_VIOLATING_UNIQUENESS,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_RENAMING_VIOLATING_UNIQUENESS,
			   old_act_type->symbol_lexeme,
			   new_act_type->symbol_lexeme);
	  }
	  else
	  {
	    if (!inside_iteration)
	      signal_error(RENAMING_ALREADY_DECLARED,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_RENAMING_ALREADY_DECLARED,
			   old_act_type->symbol_lexeme,
			   new_act_type->symbol_lexeme);
	  }
	}
	else
	  if ((new_act_type->info.act_type != NULL) &&
	      !check_expr_list_types(old_act_type->info.act_type->par_list,
				     new_act_type->info.act_type->par_list,
				     FALSE))
	  {
	    if (!inside_iteration)
	      signal_error(ILL_TYPED_RENAMING,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_ILL_TYPED_RENAMING,
			   old_act_type->symbol_lexeme,
			   new_act_type->symbol_lexeme);
	  }
	  else
	    if (((option_index == CTL_MODEL_CHECK) ||
	         (option_index == LTL_MODEL_CHECK)) &&
		!check_nusmv_keywords(new_act_type->symbol_lexeme))
	    {
	      if (!inside_iteration)
	        signal_error(RENAMING_ACT_TYPE_NUSMV_KEYWORD,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_RENAMING_ACT_TYPE_NUSMV_KEYWORD,
			     new_act_type->symbol_lexeme,
			     NULL);
	    }
	    else
	    {
	      if (old_act_type->info.act_type->renamed == NULL)
	      {
	        if (old_act_type->info.act_type->duplicate_list == NULL)
	          old_act_type->info.act_type->renamed = new_act_type;
	        else
		  for (duplicate_cell = old_act_type->info.act_type->duplicate_list;
		       (duplicate_cell != NULL);
		       duplicate_cell = duplicate_cell->next_st_bucket_cell)
		    duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->renamed = new_act_type;
	      }
	      else
	        old_act_type->info.act_type->renamed->info.act_type->renamed = new_act_type;
	      if (new_act_type->info.act_type == NULL)
	        new_act_type->info.act_type = alloc_act_type(old_act_type->info.act_type->act_type_index,
						             old_act_type->info.act_type->par_list,
						             NULL);
	    }
}


BOOLEAN		check_nusmv_keywords(char *id)
{
		BOOLEAN		found;
	static  char            *nusmv_keywords[] =
	{
		"ASYNC",
		"MODULE",
		"process",
		"DEFINE",
		"VAR",
		"IVAR",
		"CONSTANT",
		"INIT",
		"TRANS",
		"INVAR",
		"FORMAT",
		"SPEC",
		"LTLSPEC",
		"COMPUTE",
		"INVARSPEC",
		"CONSTRAINT",
		"SIMPWFF",
		"CTLWFF",
		"LTLWFF",
		"COMPWFF",
		"IN",
		"FAIRNESS",
		"JUSTICE",
		"COMPASSION",
		"ISA",
		"ASSIGN",
		"INPUT",
		"OUTPUT",
		"IMPLEMENTS",
		"GOTO",
		"LET",
		"STEP",
		"EVAL",
		"RESET",
		"array",
		"of",
		"boolean",
		"EX",
		"AX",
		"EF",
		"AF",
		"EG",
		"AG",
		"E",
		"F",
		"O",
		"G",
		"H",
		"X",
		"Y",
		"Z",
		"A",
		"U",
		"S",
		"V",
		"T",
		"BU",
		"EBF",
		"ABF",
		"EBG",
		"ABG",
		"MIN",
		"MAX",
		"FALSE",
		"TRUE",
		"apropos",
		"case",
		"esac",
		"if",
		"then",
		"else",
		"mod",
		"next",
		"init",
		"sigma",
		"self",
		"union",
		"in",
		"xor",
		"xnor"
	};
		int		i;

	for (i = NUSMV_KEYWORD_NUM - 1,
	     found = FALSE;
	     ((i >= 0) &&
	      !found);
	     i--)
	  found = !strcmp(id,
			  nusmv_keywords[i]);
	return(!found);
}


int		aemiliayyerror(char *msg)
{
	if (!strcmp(aemiliayytext,
		    "ARCHI_TYPE") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ELEM_TYPES") ||
	    !strcmp(aemiliayytext,
		    "ELEM_TYPE") ||
	    !strcmp(aemiliayytext,
		    "INPUT_INTERACTIONS") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_TOPOLOGY") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ELEM_INSTANCES") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_INTERACTIONS") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ATTACHMENTS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_VARIATIONS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_HIDINGS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_RESTRICTIONS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_RENAMINGS") ||
	    !strcmp(aemiliayytext,
		    "END"))
	{
	  remove_lexeme();
	  unread_aemilia_token();
	  signal_error(ILLEGAL_SECTION,
		       NULL,
		       NULL);
	  signal_error(RESUME_ILLEGAL_SECTION,
		       NULL,
		       NULL);
	}
	else
	  if (aemiliayytext[0] == ';')
	  {
	    signal_error(ILLEGAL_DEF_DECL,
		         NULL,
		         NULL);
	    signal_error(RESUME_ILLEGAL_DEF_DECL,
		         NULL,
		         NULL);
	  }
	  else
	  {
	    remove_lexeme();
	    unread_aemilia_token();
	    signal_error(UNEXPECTED_CHAR,
		         NULL,
		         NULL);
	  }
	return(0);
}


void		handle_sync_aemilia(void)
{
	if (!strcmp(aemiliayytext,
		    "ARCHI_TYPE") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ELEM_TYPES") ||
	    !strcmp(aemiliayytext,
		    "ELEM_TYPE") ||
	    !strcmp(aemiliayytext,
		    "INPUT_INTERACTIONS") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_TOPOLOGY") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ELEM_INSTANCES") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_INTERACTIONS") ||
	    !strcmp(aemiliayytext,
		    "ARCHI_ATTACHMENTS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_VARIATIONS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_HIDINGS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_RESTRICTIONS") ||
	    !strcmp(aemiliayytext,
		    "BEHAV_RENAMINGS") ||
	    !strcmp(aemiliayytext,
		    "END"))
	{
	  remove_lexeme();
	  unread_aemilia_token();
	  signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION,
		       NULL,
		       NULL);
	}
	else
	  signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
		       NULL,
		       NULL);
}


void            check_semantic_errors(void)
{
	BOOLEAN		found_error;
        ST_BUCKET       *id;
	ST_BUCKET_CELL	*aet_cell,
			*behavior_cell,
			*aei_cell,
			*act_type_cell,
			*dest_act_type_cell;
	char		*last_semicolon,
			**topology;
        int             i,
			j,
			k;

	/* determine the reachability of the behaviors from the initial behaviors of the AETs in which */
	/* they are defined */
	found_error = FALSE;
	for (aet_cell = archi_type[spec_no]->info.archi_type->aet_list;
	     (aet_cell != NULL);
	     aet_cell = aet_cell->next_st_bucket_cell)
	  if (aet_cell->st_bucket->info.aet->behavior_list != NULL)
	  {
	    check_behavior_reachability(aet_cell->st_bucket->info.aet->behavior_list->st_bucket);
	    for (behavior_cell = aet_cell->st_bucket->info.aet->behavior_list;
		 (behavior_cell != NULL);
		 behavior_cell = behavior_cell->next_st_bucket_cell)
	      behavior_cell->st_bucket->used = behavior_cell->st_bucket->info.behavior->visited;
	  }

        /* check the identifiers for correct definition/use */
        for (i = 0;
             (i < SYT_SIZE);
             i++)
          for (id = symbol_table[table_no][i];
               (id != NULL);
               id = id->next_st_bucket)
          {
            if ((id->symbol_lexeme[0] != '@') &&
		(id->info.archi_type != NULL))
	    {
              /* check the identifier for correct definition */
              if (!id->defined)
                switch (id->symbol_index)
                {
                  case ARCHI_TYPE_ID:
                    signal_error(ARCHI_TYPE_ID_NOT_DEF,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case AET_ID:
                    signal_error(AET_ID_NOT_DEF,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case AEI_ID:
                    signal_error(AEI_ID_NOT_DECL,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case BEHAV_ID:
                    signal_error(BEHAV_ID_NOT_DEF,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case FORMAL_CONST_PAR_ID:
                    signal_error(FORMAL_PAR_ID_NOT_DECL,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case FORMAL_VAR_PAR_ID:
		    if ((strchr(id->symbol_lexeme,
			        '.') != NULL) &&
			 check_var_aet_aei_membership(id,
						      AET_ID))
		    {
                      signal_error(FORMAL_PAR_ID_NOT_DECL,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  default:
                    break;
                }
              /* check the identifier for correct use */
              if (!id->used)
                switch (id->symbol_index)
                {
                  case ARCHI_TYPE_ID:
                    if (id != archi_type[spec_no])
		    {
                      signal_error(ARCHI_TYPE_ID_NOT_USED,
                                   id->symbol_lexeme,
				   NULL);
		      found_error = TRUE;
		    }
                    break;
                  case AET_ID:
                    signal_error(AET_ID_NOT_USED,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case AEI_ID:
		    if ((archi_type[spec_no]->info.archi_type->aei_list != NULL) &&
		        (archi_type[spec_no]->info.archi_type->aei_list->next_st_bucket_cell != NULL))
		    {
                      signal_error(AEI_ID_NOT_USED,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  case BEHAV_ID:
                    if (!id->info.behavior->init_behav)
		    {
                      signal_error(BEHAV_ID_NOT_USED,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  case ACT_TYPE_ID:
		    if (id->info.act_type->interaction_index != NO_INTERACTION)
		    {
                      signal_error(ACT_TYPE_ID_NOT_USED,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  case FORMAL_CONST_PAR_ID:
                    signal_error(FORMAL_PAR_ID_NOT_USED,
                                 id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  case FORMAL_VAR_PAR_ID:
		    if (check_var_aet_aei_membership(id,
						     AET_ID))
		    {
                      signal_error(FORMAL_PAR_ID_NOT_USED,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  case LOCAL_VAR_ID:
		    if (check_var_aet_aei_membership(id,
						     AET_ID))
		    {
                      signal_error(LOCAL_VAR_ID_NOT_USED,
                                   id->symbol_lexeme,
			           NULL);
		      found_error = TRUE;
		    }
                    break;
                  case REC_FIELD_ID:
		    last_semicolon = strrchr(id->symbol_lexeme,
					     ';');
                    signal_error(REC_FIELD_ID_NOT_USED,
				 (last_semicolon != NULL)?
				   ++last_semicolon:
				   id->symbol_lexeme,
			         NULL);
		    found_error = TRUE;
                    break;
                  default:
                    break;
              }
              /* perform other checks related to the correct definition/use of the identifier */
              switch (id->symbol_index)
              {
                case AEI_ID:
                  /* check the AEI interactions for presence in the attachments */
		  if (id->info.aei->aei_no > 0)
		    for (act_type_cell = id->info.aei->act_type_list;
		         (act_type_cell != NULL);
		         act_type_cell = act_type_cell->next_st_bucket_cell)
		      if ((act_type_cell->st_bucket->info.act_type != NULL) &&
			  (act_type_cell->st_bucket->info.act_type->interaction_index != NO_INTERACTION) &&
		          !act_type_cell->st_bucket->info.act_type->architectural &&
		          (act_type_cell->st_bucket->info.act_type->attached_interaction_list == NULL))
		      {
                        signal_error(INTERACTION_NOT_ATTACHED,
                                     act_type_cell->st_bucket->symbol_lexeme,
				     NULL);
		        found_error = TRUE;
		      }
                  break;
                case FORMAL_CONST_PAR_ID:
                  /* check the formal constant parameter for not being used in input actions */
                  if (id->info.expr->illegal_use_cumul)
		  {
                    signal_error(FORMAL_PAR_IN_INPUT,
                                 id->symbol_lexeme,
				 NULL);
		    found_error = TRUE;
		  }
                  break;
                case FORMAL_VAR_PAR_ID:
                  /* check the type of the formal variable parameter for correct array lengths and integer */
		  /* bounds, then initialize it if necessary whenever it is of type array or record (this */
		  /* does not conflict with the initializing assignment built in handle_aei_decl() for the */
		  /* formal variable parameters of the initial behaviors of the AEIs, as this assignment */
		  /* has not been applied yet) */
		  if (strchr(id->symbol_lexeme,
			     '.') != NULL)
		  {
		    if(check_var_aet_aei_membership(id,
						    AEI_ID) &&
		       check_eval_var_lengths_bounds(id->info.expr,
						     FALSE,
						     FALSE,
						     id->symbol_lexeme))
		    {
		      if (((id->info.expr->data_type->data_type_lexeme[0] == 'a') ||
                           (id->info.expr->data_type->data_type_lexeme[0] == 'p')) &&
	                  ((option_index == SIMULATION) ||
                           (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP)))
                        init_struct_var(&(id->info.expr->struct_value),
                                        id->info.expr,
                                        1);
		      else
		        found_error = TRUE;
		    }
                    /* check the formal variable parameter for not being used in input actions */
		    if (check_var_aet_aei_membership(id,
						     AET_ID) &&
                        id->info.expr->illegal_use_cumul)
		    {
                      signal_error(FORMAL_PAR_IN_INPUT,
                                   id->symbol_lexeme,
				   NULL);
		      found_error = TRUE;
		    }
		  }
                  break;
                case LOCAL_VAR_ID:
                  /* check the type of the local variable for correct array lengths and integer bounds, */
		  /* then initialize it if necessary whenever it is of type array or record */
		  if (check_var_aet_aei_membership(id,
						   AEI_ID) &&
		      check_eval_var_lengths_bounds(id->info.expr,
						    FALSE,
						    FALSE,
						    id->symbol_lexeme))
		  {
		    if (((id->info.expr->data_type->data_type_lexeme[0] == 'a') ||
                         (id->info.expr->data_type->data_type_lexeme[0] == 'p')) &&
	                ((option_index == SIMULATION) ||
                         (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP)))
                      init_struct_var(&(id->info.expr->struct_value),
                                      id->info.expr,
                                      1);
		    else
		      found_error = TRUE;
		  }
                  /* check the local variable for not being used before occurring in input actions */
		  if (check_var_aet_aei_membership(id,
						   AET_ID) &&
                      id->info.expr->illegal_use_cumul)
	          {
                    signal_error(LOCAL_VAR_BEFORE_INPUT,
                                 id->symbol_lexeme,
				 NULL);
		    found_error = TRUE;
		  }
                  break;
                default:
                  break;
              }
	    }
          }

        /* check the architectural topology for connectivity */
	if (archi_type[spec_no]->info.archi_type->aei_num > 1)
	{
	  /* build the matrix representation of the indirect reduced graph */
	  topology = (char **)new_calloc(sizeof(char *),
					 archi_type[spec_no]->info.archi_type->aei_num + 1);
	  for (i = archi_type[spec_no]->info.archi_type->aei_num;
	       (i >= 1);
	       i--)
	  {
	    topology[i] = (char *)new_calloc(sizeof(char),
					     archi_type[spec_no]->info.archi_type->aei_num + 1);
	    for (j = archi_type[spec_no]->info.archi_type->aei_num;
	         (j >= 1);
	         j--)
	      topology[i][j] = (char)FALSE;
	  }
	  for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	       (aei_cell != NULL);
	       aei_cell = aei_cell->next_st_bucket_cell)
	    for (act_type_cell = aei_cell->st_bucket->info.aei->act_type_list;
	         (act_type_cell != NULL);
	         act_type_cell = act_type_cell->next_st_bucket_cell)
	      if (act_type_cell->st_bucket->info.act_type != NULL)
	        for (dest_act_type_cell =
		       act_type_cell->st_bucket->info.act_type->attached_interaction_list;
	             (dest_act_type_cell != NULL);
	             dest_act_type_cell = dest_act_type_cell->next_st_bucket_cell)
		  topology[act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no]
			  [dest_act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no] =
		    topology[dest_act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no]
			    [act_type_cell->st_bucket->info.act_type->aei->info.aei->aei_no] = (char)TRUE;
	  /* compute the transitive closure */
	  for (k = archi_type[spec_no]->info.archi_type->aei_num;
	       (k >= 1);
	       k--)
	    for (i = archi_type[spec_no]->info.archi_type->aei_num;
	         (i >= 1);
	         i--)
	      for (j = archi_type[spec_no]->info.archi_type->aei_num;
	           (j >= 1);
	           j--)
	        topology[i][j] = topology[i][j] || (topology[i][k] && topology[k][j]);
	  /* check every pair of AEIs for connectivity */
	  for (i = 1;
	       (i <= archi_type[spec_no]->info.archi_type->aei_num);
	       i++)
	    for (j = i + 1;
	         (j <= archi_type[spec_no]->info.archi_type->aei_num);
	         j++)
	      if (!topology[i][j])
	      {
		signal_error(AEI_PAIR_NOT_CONNECTED,
			     find_aei_lexeme(i),
			     find_aei_lexeme(j));
		found_error = TRUE;
	      }
	  /* deallocate the matrix */
	  for (i = archi_type[spec_no]->info.archi_type->aei_num;
	       (i >= 1);
	       i--)
	    free(topology[i]);
	  free(topology);
	}
	if (found_error)
	  print_newline(TRUE);
}


void		check_behavior_reachability(ST_BUCKET *behavior)
{
	ST_BUCKET_CELL	*behavior_cell;

	if (!behavior->info.behavior->visited)
	{
	  behavior->info.behavior->visited = TRUE;
	  for (behavior_cell = behavior->info.behavior->invoked_behavior_list;
	       (behavior_cell != NULL);
	       behavior_cell = behavior_cell->next_st_bucket_cell)
	    check_behavior_reachability(behavior_cell->st_bucket);
	}
}


char		*find_aei_lexeme(int aei_no)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[spec_no]->info.archi_type->aei_list;
	     (aei_cell->st_bucket->info.aei->aei_no != aei_no);
	     aei_cell = aei_cell->next_st_bucket_cell);
	return(aei_cell->st_bucket->symbol_lexeme);
}
