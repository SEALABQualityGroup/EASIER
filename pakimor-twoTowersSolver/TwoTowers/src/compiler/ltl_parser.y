/****************************************************************
 *								*
 *								*
 *                  	   ltl_parser.y				*
 *								*
 *                                          (by Marco Bernardo) *
 ****************************************************************
 * This module implements the parser for .ltl spec files.
 * This module contains the following function:
 * - parse_ltl(): It drives the parsing of a .ltl spec file.
 * and the following auxiliary functions:
 * - expand_iterative_property_def(): It expands a syntactically correct iterative property definition into
 *				      the corresponding non-iterative property definitions.
 * - ltlyyerror(): It disables the homonymous library function, in order not to interfere with TTGUI, and
 *                 reports an error message whenever a syntax error occurs. The error message pinpoints more
 *		   accurately the place where the error occurs than it would do if the error message were
 *		   issued only after the detection of a synchronizing token. This also allows the case in
 *		   which no synchronizing token can be found to be treated easily.
 * - handle_sync_ltl(): It handles the detection of a synchronizing token.
 ****************************************************************/


%{

/****************************************************************
 * 1. Inclusion of libraries.					*
 ****************************************************************/

#include	<float.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************
 * 2. Inclusion of external gvariables and functions.		*
 ****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/ltl_scanner.h"
#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/nusmv_connector.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************
 * 3. Definition/declaration of exporting gvariables/functions.	*
 ****************************************************************/

void		parse_ltl(char *);


/****************************************************************
 * 4. Definition/declaration of private gvariables/functions.	*
 ****************************************************************/

PRIVATE ST_BUCKET4_CELL *act_type_list;
                                /* list of the symbol table buckets for the symbolically indexed action
				 * types occurring in the iterative property definition being parsed;
                                 * set by ;
                                 * used in */


PRIVATE	ST_BUCKET_CELL	*expand_iterative_property_def(ST_BUCKET *);

PRIVATE	int		ltlyyerror(char *);

PRIVATE	void		handle_sync_ltl(void);

%}


/****************************************************************
 * 5. Definition of grammar symbol attributes.			*
 ****************************************************************/

%union
{
	ST_BUCKET_CELL	*st_bucket_cell;
				/* list of symbol table bucket cells for properties */
        ST_BUCKET       *st_bucket;
                                /* pointer to the symbol table bucket for an identifier or a number */
	EXPR_PARSE_INFO	*expr_parse_info;
				/* parse information about an expression */
	LAR_PARSE_INFO	*lar_parse_info;
				/* parse information about an expression list/array/record */
}


/****************************************************************
 * 6. Definition of tokens.					*
 ****************************************************************/

%token  <st_bucket>		T_NUMBER			300	/* number */
%token  <st_bucket>		T_ID				301	/* identifier */
%token	<st_bucket>		T_FOR_ALL			429	/* keyword */
%token	<st_bucket>		T_IN				430	/* keyword */
%token	<st_bucket>		T_MOD				446	/* keyword */
%token	<st_bucket>		T_MIN				447	/* keyword */
%token	<st_bucket>		T_MAX				448	/* keyword */
%token	<st_bucket>		T_ABS				449	/* keyword */
%token	<st_bucket>		T_CEIL				450	/* keyword */
%token	<st_bucket>		T_FLOOR				451	/* keyword */
%token	<st_bucket>		T_POWER				452	/* keyword */
%token	<st_bucket>		T_EPOWER			453	/* keyword */
%token	<st_bucket>		T_LOGE				454	/* keyword */
%token	<st_bucket>		T_LOG10				455	/* keyword */
%token	<st_bucket>		T_SQRT				456	/* keyword */
%token	<st_bucket>		T_SIN				457	/* keyword */
%token	<st_bucket>		T_COS				458	/* keyword */
%token	<st_bucket>		T_C_UNIFORM			459	/* keyword */
%token	<st_bucket>		T_ERLANG			460	/* keyword */
%token	<st_bucket>		T_GAMMA				461	/* keyword */
%token	<st_bucket>		T_EXPONENTIAL			462	/* keyword */
%token	<st_bucket>		T_WEIBULL			463	/* keyword */
%token	<st_bucket>		T_BETA				464	/* keyword */
%token	<st_bucket>		T_NORMAL			465	/* keyword */
%token	<st_bucket>		T_PARETO			466	/* keyword */
%token	<st_bucket>		T_B_PARETO			467	/* keyword */
%token	<st_bucket>		T_D_UNIFORM			468	/* keyword */
%token	<st_bucket>		T_BERNOULLI			469	/* keyword */
%token	<st_bucket>		T_BINOMIAL			470	/* keyword */
%token	<st_bucket>		T_POISSON			471	/* keyword */
%token	<st_bucket>		T_NEG_BINOMIAL			472	/* keyword */
%token	<st_bucket>		T_GEOMETRIC			473	/* keyword */
%token	<st_bucket>		T_PASCAL			474	/* keyword */
%token	<st_bucket>		T_TRUE				476	/* keyword */
%token	<st_bucket>		T_FALSE				477	/* keyword */
%token	<st_bucket>		T_LIST_CONS			479	/* keyword */
%token	<st_bucket>		T_FIRST				480	/* keyword */
%token	<st_bucket>		T_TAIL				481	/* keyword */
%token	<st_bucket>		T_CONCAT			482	/* keyword */
%token	<st_bucket>		T_INSERT			483	/* keyword */
%token	<st_bucket>		T_REMOVE			484	/* keyword */
%token	<st_bucket>		T_LENGTH			485	/* keyword */
%token	<st_bucket>		T_ARRAY_CONS			487	/* keyword */
%token	<st_bucket>		T_READ				488	/* keyword */
%token	<st_bucket>		T_WRITE				489	/* keyword */
%token	<st_bucket>		T_RECORD_CONS			491	/* keyword */
%token	<st_bucket>		T_GET				492	/* keyword */
%token	<st_bucket>		T_PUT				493	/* keyword */
%token  <st_bucket>             T_PROPERTY        		494     /* keyword */
%token  <st_bucket>             T_IS				495     /* keyword */
%token  <st_bucket>  		T_PROP_TRUE            		496     /* keyword */
%token  <st_bucket>  		T_PROP_FALSE           		497     /* keyword */
%token  <st_bucket>  		T_PROP_NOT           		498     /* keyword */
%token  <st_bucket>             T_MODAL_DEADLOCK_FREE  		515     /* keyword */
%token  <st_bucket>  		T_LTL_NEXT_STATE_SAT   		524     /* keyword */
%token  <st_bucket>  		T_LTL_ALL_FUTURE_STATES_SAT	525     /* keyword */
%token  <st_bucket>  		T_LTL_SOME_FUTURE_STATE_SAT	526     /* keyword */
%token  <st_bucket>  		T_LTL_UNTIL        		527     /* keyword */
%token  <st_bucket>  		T_LTL_RELEASES         		528     /* keyword */
%token  <st_bucket>  		T_LTL_PREV_STATE_SAT 		529     /* keyword */
%token  <st_bucket>  		T_LTL_ALL_PAST_STATES_SAT	530     /* keyword */
%token  <st_bucket>  		T_LTL_SOME_PAST_STATE_SAT	531     /* keyword */
%token  <st_bucket>  		T_LTL_SINCE         		532     /* keyword */
%token  <st_bucket>  		T_LTL_TRIGGERED        		533     /* keyword */
%token				DOTDOT				700	/* symbol ".." */
%token				NE				701	/* symbol "!=" */
%token				LE				702	/* symbol "<=" */
%token				GE				703	/* symbol ">=" */
%token				AND				704	/* symbol "&&" */
%token				OR				705	/* symbol "||" */
%token                          PROP_AND          		706     /* symbol "/\" */
%token                          PROP_OR           		707     /* symbol "\/" */
%token                          PROP_XOR           		708     /* symbol "\_/" */
%token				PROP_IMPL			709	/* symbol "->" */
%token				PROP_BI_IMPL			710	/* symbol "<->" */


/****************************************************************
 * 7. Association of attributes with nonterminals.		*
 ****************************************************************/

%type	<st_bucket_cell>	property_def	  /* property definition */
%type	<st_bucket>		s_property_def	  /* simple property definition */
%type	<st_bucket>		i_property_def	  /* body of an iterative property definition */
%type	<st_bucket>		act_type	  /* action type */
%type	<st_bucket>		iteration	  /* iteration */
%type	<expr_parse_info>	property_expr	  /* property expression */
%type	<expr_parse_info>	poss_aei_index	  /* possible AEI index */
%type	<expr_parse_info>	aei_index	  /* AEI index */
%type	<expr_parse_info>	expr		  /* expression */
%type	<expr_parse_info>	expr_list	  /* expression list */
%type	<expr_parse_info>	expr_array	  /* expression array */
%type	<expr_parse_info>	expr_record	  /* expression record */
%type	<lar_parse_info>	struct_expr	  /* expression list/array/record */


/****************************************************************
 * 8. Definition of operator precedence and associativity.	*
 ****************************************************************/

%right			PROP_IMPL
%left			PROP_BI_IMPL
%left			PROP_OR
			PROP_XOR
%left			PROP_AND
%left			T_LTL_UNTIL
			T_LTL_RELEASES
			T_LTL_SINCE
			T_LTL_TRIGGERED
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


/****************************************************************
 * 9. Definition of the start symbol.				*
 ****************************************************************/

%start			prop_def_list


/****************************************************************
 * 10. Definition of grammar rules.				*
 ****************************************************************/

%%

prop_def_list	:	property_def
			    {
			      property_list = $1;
			    }
		|	property_def ';' prop_def_list
			    {
			      property_list = append_list($1,
							  property_list);
			    }
		|       error ';'
                            {
                              handle_sync_ltl();
                              yyerrok;
                            }
                        prop_def_list
                            {
                            }
		|	error T_PROPERTY
			    {
			      handle_sync_ltl();
			      yyerrok;
			    }
			prop_def_list
			    {
			    }
		;


property_def	:	s_property_def
                            {
			      $$ = ($1 == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell($1,
                                                          NULL);
                            }
		|	iteration i_property_def
                            {
			      selector_enabled[0] = FALSE;
			      $$ = (($1 == NULL) ||
                                    ($2 == NULL))?
                                     NULL:
                                     expand_iterative_property_def($2);
			      selector[0] = NULL;
                            }
		;


s_property_def	:	T_PROPERTY T_ID poss_aei_index
			    {
			      handle_unprefixed_concretely_indexed_id(&$2,
						                      $3,
								      PROPERTY_ID_DEF);
			    }
			T_IS property_expr
			    {
			      if (($2 != NULL) &&
				  ($6 != NULL))
				$2->info.property_expr = set_nusmv_expr_bucket($6->expr);
			      $$ = $2;
			    }
		;


i_property_def	:	T_PROPERTY T_ID aei_index
			    {
			      handle_unprefixed_symbolically_indexed_id(&$2,
						                        $3,
						                        0,
								        PROPERTY_ID_DEF);
			      act_type_list = NULL;
			    }
			T_IS property_expr
			    {
			      if (($2 != NULL) &&
				  ($6 != NULL))
				$2->info.property_expr = $6->expr;
			      $$ = $2;
			    }
		;


property_expr	:	T_PROP_TRUE
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	T_PROP_FALSE
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	property_expr PROP_AND property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_AND_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr PROP_OR property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_OR_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_PROP_NOT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_NOT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr PROP_XOR property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_XOR_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr PROP_IMPL property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_IMPL_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr PROP_BI_IMPL property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_BI_IMPL_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_MODAL_DEADLOCK_FREE
			    {
			      $$ = alloc_expr_parse_info(
				     set_expr_bucket(
				       LTL_ALL_FUTURE_STATES_SAT_OP,
                                       set_expr_bucket(NE_OP,
						       (ST_BUCKET *)search_lexeme_table("_action_type",
				 						        SYT),
						       (ST_BUCKET *)search_lexeme_table("_STUTTER",
				 						        SYT),
						       NULL,
						       0.0,
						       NULL,
						       FALSE),
                                       NULL,
                                       NULL,
                                       0.0,
                                       NULL,
                                       FALSE),
                                     NULL);
			    }
		|	act_type
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(
                                       set_expr_bucket(EQ_OP,
						       (ST_BUCKET *)search_lexeme_table("_action_type",
				 						        SYT),
						       $1,
						       NULL,
						       0.0,
						       NULL,
						       FALSE),
				       NULL);
			    }
		|	T_LTL_NEXT_STATE_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_NEXT_STATE_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LTL_ALL_FUTURE_STATES_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_ALL_FUTURE_STATES_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LTL_SOME_FUTURE_STATE_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SOME_FUTURE_STATE_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr T_LTL_UNTIL property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_UNTIL_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr T_LTL_RELEASES property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_RELEASES_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LTL_PREV_STATE_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_PREV_STATE_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LTL_ALL_PAST_STATES_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_ALL_PAST_STATES_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LTL_SOME_PAST_STATE_SAT '(' property_expr ')'
			    {
			      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SOME_PAST_STATE_SAT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr T_LTL_SINCE property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SINCE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	property_expr T_LTL_TRIGGERED property_expr
			    {
			      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_TRIGGERED_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	'(' property_expr ')'
			    {
			      $$ = $2;
			    }
		;


act_type	:	T_ID poss_aei_index
			    {
                              handle_concretely_indexed_aei(&$1,
                                                            $2,
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            }
                        '.' T_ID
                            {
                              handle_prefixed_indexed_id($1,
                                                         &$5,
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
                              if (($5 != NULL) &&
                                  selector_enabled[0] &&
                                  (index_expr[1] != NULL))
			      {
				$5->symbol_index = ACT_TYPE_ID;
                                act_type_list = alloc_st_bucket4_cell($5,
                                                                      unindexed_aei[1],
                                                                      index_expr[1],
                                                                      unindexed_id[1],
                                                                      act_type_list);
			      }
                              $$ = $5;
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
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
			    }
		|	T_RECORD_CONS '(' expr_record ')'
			    {
			      $$ = $3;
			    }
		|	T_GET '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
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
                                                           NULL);
			    }
		|	T_PUT '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                           NULL);
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
                                                          NULL);
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
                                                          NULL);
			    }
		;


%%


/****************************************************************
 * 11. Definition of exporting functions.			*
 ****************************************************************/

void		parse_ltl(char *ltl_path)
{
	int		ltlyyparse(void);

	/* open the .ltl spec and the .lis file */
	ltlyyin = new_fopen(ltl_path,
			    NULL,
			    "r");
	open_listing(ltl_path);

	/* initialize the iteration-related global variables imported via symbol-handler.h */
	selector_enabled[0] = selector_enabled[1] = poss_aei_index_parsed = FALSE;
	id_prefix_in_expr = NULL;
	selector[0] = selector[1] = unindexed_aei[0] = unindexed_aei[1] = index_expr[0] = index_expr[1] =
	  unindexed_id[0] = unindexed_id[1] = NULL;
	selector_index = 0;

	/* initialize other imported global variables and the private global variables */
	property_list = NULL;

	/* parse the .ltl spec */
	ltlyyparse();

	/* close the .ltl spec and the .lis file */
	fclose(ltlyyin);
	close_listing();
}


/****************************************************************
 * 12. Definition of private functions.				*
 ****************************************************************/

ST_BUCKET_CELL	*expand_iterative_property_def(ST_BUCKET *symbolic_property)
{
	ST_BUCKET	*concrete_index_expr,
			*concrete_property,
			*concrete_property_expr;
	ST_BUCKET_CELL  *concrete_property_list,
                        *last_concrete_property_cell;
	int             min_value,
                        max_value,
                        value;

	concrete_property_list = last_concrete_property_cell = NULL;
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
          concrete_property = unindexed_id[0];
          build_indexed_id(&concrete_property,
                           concrete_index_expr);
	  check_id(PROPERTY_ID_DEF,
		   &concrete_property,
		   TRUE);
	  if (concrete_property != NULL)
	  {
	    concrete_property_expr =
	      set_concrete_uneval_expr_bucket(symbolic_property->info.property_expr,
					      act_type_list,
					      NULL,
					      NULL);
	    if (concrete_property_expr != NULL)
	    {
	      concrete_property->info.property_expr = set_nusmv_expr_bucket(concrete_property_expr);
	      if (concrete_property_list == NULL)
                last_concrete_property_cell = concrete_property_list =
		  alloc_st_bucket_cell(concrete_property,
                                       NULL);
              else
                last_concrete_property_cell = last_concrete_property_cell->next_st_bucket_cell =
                  alloc_st_bucket_cell(concrete_property,
                                       NULL);
	    }
          }
	}
	free_st_bucket4_list(act_type_list);
	return(concrete_property_list);
}


int		ltlyyerror(char *msg)
{
	if (!strcmp(ltlyytext,
		    "PROPERTY"))
	{
          remove_lexeme();
          unread_ltl_token();
	  signal_error(ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
          signal_error(RESUME_ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
        }
        else
	  if (ltlyytext[0] == ';')
          {
            signal_error(ILLEGAL_DEF_DECL,
                         NULL,
                         NULL);
            signal_error(RESUME_ILLEGAL_DEF_DECL,
                         NULL,
                         NULL);
          }
          else
            signal_error(UNEXPECTED_CHAR,
                         NULL,
                         NULL);
        return(0);
}


void		handle_sync_ltl(void)
{
	if (!strcmp(ltlyytext,
                    "PROPERTY"))
        {
          remove_lexeme();
          unread_ltl_token();
	}
        signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
                     NULL,
                     NULL);
}
