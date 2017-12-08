/****************************************************************/
/*								*/
/*								*/
/*                  	   rew_parser.y				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module implements the parser for .rew spec files. */
/* This module contains the following function: */
/* - parse_rew(): It drives the parsing of a .rew spec file. */
/* and the following auxiliary functions: */
/* - expand_iterative_rew_measure_def(): It expands an iterative reward measure definition into the */
/*					 corresponding non-iterative reward measure definitions. */
/* - handle_type_yb(): It contains the actions to be undertaken when parsing a correct type-yield-bonus */
/*		       triple within a correct measure definition. */
/* - rewyyerror(): It disables the homonymous library function, in order not to interfere with TTGUI, and */
/*                 reports an error message whenever a syntax error occurs. The error message pinpoints */
/*		   more accurately the place where the error occurs than it would do if the error message */
/*		   were issued only after the detection of a synchronizing token. This also allows the */
/*		   case in which no synchronizing token can be found to be treated easily. */
/* - handle_sync_rew(): It handles the detection of a synchronizing token. */
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

#include	"../headers/rew_scanner.h"
#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/markov_solver.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		parse_rew(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	BOOLEAN		aux_enabled;
				/* auxiliary flag indicating whether the first selector has been parsed; */
				/* set by ; */
				/* used in */

PRIVATE	REWARD_INDEX	reward_index;
				/* kind of the reward being parsed; */
				/* set by ; */
				/* used in */

PRIVATE	ST_BUCKET_CELL	*aux_rew_measure_list;
				/* auxiliary list of the symbol table bucket cells for the measure being */
				/* parsed; */
				/* set by ; */
				/* used in */

PRIVATE	int		rew_measure_num,
				/* number of correct reward measures to be computed; */
				/* set by parse_rew() and append_absent_rew_measure() and at parsing time; */
				/* used in append_absent_yb_type() */
			init_rew_measure_no;
				/* serial number of the (first instance of the) correct reward measure */
				/* being parsed; */
				/* set by ; */
				/* used in */


PRIVATE	ST_BUCKET_CELL	*expand_iterative_rew_measure_def(ST_BUCKET *);

PRIVATE	void		handle_type_yb(ST_BUCKET3_CELL *);

PRIVATE	int		rewyyerror(char *);

PRIVATE	void		handle_sync_rew(void);

%}


/****************************************************************/
/* 5. Definition of grammar symbol attributes.			*/
/****************************************************************/

%union
{
	ST_BUCKET_CELL	*st_bucket_cell;
				/* list of symbol table bucket cells for reward measures */
	ST_BUCKET3_CELL	*st_bucket3_cell;
				/* list of triple symbol table buckets for type-yield-bonus triples */
        ST_BUCKET       *st_bucket;
                                /* pointer to the symbol table bucket for an identifier or a number */
	EXPR_PARSE_INFO	*expr_parse_info;
				/* parse information about an expression */
	LAR_PARSE_INFO	*lar_parse_info;
				/* parse information about an expression list/array/record */
}


/****************************************************************/
/* 6. Definition of tokens.					*/
/****************************************************************/

%token	<st_bucket>		T_NUMBER		300	/* number */
%token  <st_bucket>             T_ID            	301     /* identifier */
%token	<st_bucket>		T_FOR_ALL		429	/* keyword */
%token	<st_bucket>		T_IN			430	/* keyword */
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
%token	<st_bucket>		T_TRUE			476	/* keyword */
%token	<st_bucket>		T_FALSE			477	/* keyword */
%token	<st_bucket>		T_LIST_CONS		479	/* keyword */
%token	<st_bucket>		T_FIRST			480	/* keyword */
%token	<st_bucket>		T_TAIL			481	/* keyword */
%token	<st_bucket>		T_CONCAT		482	/* keyword */
%token	<st_bucket>		T_INSERT		483	/* keyword */
%token	<st_bucket>		T_REMOVE		484	/* keyword */
%token	<st_bucket>		T_LENGTH		485	/* keyword */
%token	<st_bucket>		T_ARRAY_CONS		487	/* keyword */
%token	<st_bucket>		T_READ			488	/* keyword */
%token	<st_bucket>		T_WRITE			489	/* keyword */
%token	<st_bucket>		T_RECORD_CONS		491	/* keyword */
%token	<st_bucket>		T_GET			492	/* keyword */
%token	<st_bucket>		T_PUT			493	/* keyword */
%token  <st_bucket>             T_IS                    495     /* keyword */
%token  <st_bucket>             T_MEASURE               539     /* keyword */
%token  <st_bucket>             T_ENABLED               540     /* keyword */
%token  <st_bucket>             T_STATE_REWARD          541     /* keyword */
%token  <st_bucket>             T_TRANS_REWARD          542     /* keyword */
%token				DOTDOT			700	/* symbol ".." */
%token				NE			701	/* symbol "!=" */
%token				LE			702	/* symbol "<=" */
%token				GE			703	/* symbol ">=" */
%token				AND			704	/* symbol "&&" */
%token				OR			705	/* symbol "||" */
%token				IMPL			706	/* symbol "->" */


/****************************************************************/
/* 7. Association of attributes with nonterminals.		*/
/****************************************************************/

%type	<st_bucket_cell>	measure_def	  /* measure definition */
%type	<st_bucket_cell>	s_measure_def	  /* simple measure definition */
%type	<st_bucket_cell>	i_measure_def	  /* body of an iterative measure definition */
%type	<st_bucket3_cell>	s_type_yb	  /* simple type-yield-bonus triple */
%type	<st_bucket3_cell>	i_type_yb	  /* body of an iterative type-yield-bonus triple */
%type	<st_bucket>		reward		  /* reward */
%type	<st_bucket>		iteration	  /* iteration */
%type	<st_bucket>		nested_iteration  /* nested iteration */
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

%start			measure_def_list


/****************************************************************/
/* 10. Definition of grammar rules.				*/
/****************************************************************/

%%

measure_def_list:	measure_def
			    {
			      rew_measure_list = $1;
			    }
		|	measure_def ';' measure_def_list
			    {
			      rew_measure_list = append_list($1,
							     rew_measure_list);
			    }
		|       error ';'
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
                        measure_def_list
                            {
                            }
		|       error T_MEASURE
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
                        measure_def_list
                            {
                            }
		;


measure_def	:	s_measure_def
                            {
			      $$ = $1;
                            }
		|	iteration i_measure_def
                            {
			      selector_enabled[0] = FALSE;
			      selector[0] = NULL;
			      $$ = $2;
                            }
		;


s_measure_def	:	T_MEASURE T_ID poss_aei_index
			    {
			      handle_unprefixed_concretely_indexed_id(&$2,
						                      $3,
								      MEASURE_ID_DEF);
			      if ($2 != NULL)
			      {
			        init_rew_measure_no = ++rew_measure_num;
				$2->info.rew_measure = alloc_rew_measure(NULL);
			        aux_rew_measure_list = alloc_st_bucket_cell($2,
									    NULL);
			      }
			      else
			        aux_rew_measure_list = NULL;
			    }
			T_IS type_yb_list
			    {
			      $$ = aux_rew_measure_list;
			    }
		;


i_measure_def	:	T_MEASURE T_ID aei_index
			    {
			      handle_unprefixed_symbolically_indexed_id(&$2,
						                        $3,
						                        0,
								        MEASURE_ID_DEF);
			      selector_enabled[0] = FALSE;
			      aux_rew_measure_list = ((selector[0] == NULL) ||
						      (selector[0]->info.expr->opn1 == NULL) ||
						      ($2 == NULL))?
						       NULL:
						       expand_iterative_rew_measure_def($2);
			      selector_enabled[0] = TRUE;
			    }
			T_IS type_yb_list
			    {
			      $$ = aux_rew_measure_list;
			    }
		;


type_yb_list	:	type_yb
			    {
			    }
		|	type_yb type_yb_list
			    {
			    }
		|       error T_MEASURE
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
		;


type_yb		:	s_type_yb
                            {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ($1 != NULL))
				handle_type_yb($1);
			      selector_enabled[0] = aux_enabled;
                            }
		|	nested_iteration i_type_yb
                            {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = selector_enabled[1] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ($1 != NULL) &&
				  ($2 != NULL))
				handle_type_yb($2);
			      selector[1] = NULL;
			      selector_enabled[0] = aux_enabled;
			      selector_index = 0;
                            }
		;


s_type_yb	:	T_ENABLED '(' T_ID poss_aei_index
                            {
			      handle_concretely_indexed_aei(&$3,
                                                            $4,
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            }
                        '.' T_ID
			    {
			      handle_prefixed_indexed_id($3,
                                                         &$7,
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    }
			')' IMPL reward
			    {
			      $$ = (($7 == NULL) ||
				    ($11 == NULL))?
				     NULL:
				     alloc_st_bucket3_cell($7,
							   (reward_index == YIELD)?
							     $11:
							     NULL,
							   (reward_index == BONUS)?
							     $11:
							     NULL,
							   NULL);
			    }
		;


i_type_yb	:	T_ENABLED '(' T_ID aei_index
                            {
			      handle_symbolically_indexed_aei(&$3,
                                                              $4,
                                                              1,
                                                              FALSE);
                            }
                        '.' T_ID
			    {
			      handle_prefixed_indexed_id($3,
                                                         &$7,
                                                         1,
                                                         TRUE,
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    }
			')' IMPL reward
			    {
			      $$ = (($7 == NULL) ||
				    ($11 == NULL))?
				     NULL:
				     alloc_st_bucket3_cell($7,
							   (reward_index == YIELD)?
							     $11:
							     NULL,
							   (reward_index == BONUS)?
							     $11:
							     NULL,
							   NULL);
			    }
		;


reward		:	T_STATE_REWARD '(' expr
			    {
			      if (($3 != NULL) &&
				  !check_expr_all($3->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				$3 = NULL;
			    }
			')'
			    {
			      $$ = ($3 != NULL)?
				     $3->expr:
				     NULL;
			      reward_index = YIELD;
			    }
		|	T_TRANS_REWARD '(' expr
			    {
			      if (($3 != NULL) &&
				  !check_expr_all($3->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				$3 = NULL;
			    }
			')'
			    {
			      $$ = ($3 != NULL)?
				     $3->expr:
				     NULL;
			      reward_index = BONUS;
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


nested_iteration:
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
			        $$ = $2;
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


/****************************************************************/
/* 11. Definition of exporting functions.			*/
/****************************************************************/

void		parse_rew(char *rew_path)
{
	int		rewyyparse(void);

	/* open the .rew spec and the .lis file */
	rewyyin = new_fopen(rew_path,
			    NULL,
			    "r");
	open_listing(rew_path);

	/* initialize the iteration-related global variables imported via symbol-handler.h */
	selector_enabled[0] = selector_enabled[1] = poss_aei_index_parsed = FALSE;
	id_prefix_in_expr = NULL;
	selector[0] = selector[1] = unindexed_aei[0] = unindexed_aei[1] = index_expr[0] = index_expr[1] =
	  unindexed_id[0] = unindexed_id[1] = NULL;
	selector_index = 0;

	/* initialize other imported global variables and the private global variables */
	rew_measure_list = NULL;
	rew_measure_num = init_rew_measure_no = 0;

	/* parse the .rew spec */
	rewyyparse();

	/* close the .rew spec and the .lis file */
	fclose(rewyyin);
	close_listing();
}


/****************************************************************/
/* 12. Definition of private functions.				*/
/****************************************************************/

ST_BUCKET_CELL	*expand_iterative_rew_measure_def(ST_BUCKET *symbolic_measure)
{
	BOOLEAN		error_found;
	ST_BUCKET	*concrete_measure,
			*concrete_index_expr;
	ST_BUCKET_CELL	*concrete_measure_list,
			*last_concrete_measure_cell;
	int		aux_rew_measure_num,
			min_value,
			max_value,
			value;

	concrete_measure_list = last_concrete_measure_cell = NULL;
	aux_rew_measure_num = rew_measure_num;
	init_rew_measure_no = rew_measure_num + 1;
	min_value = (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value = (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value = min_value,
	     error_found = FALSE;
	     ((value <= max_value) &&
	      !error_found);
	     value++)
	{
	  selector[0]->info.expr->value = (long double)value;
	  eval_expr(index_expr[0],
		    0);
	  concrete_index_expr = set_concrete_expr_bucket(index_expr[0]);
	  concrete_measure = unindexed_id[0];
	  build_indexed_id(&concrete_measure,
			   concrete_index_expr);
	  check_id(MEASURE_ID_DEF,
	           &concrete_measure,
		   TRUE);
	  if (concrete_measure == NULL)
	  {
	    error_found = TRUE;
	    rew_measure_num = aux_rew_measure_num;
	  }
	  else
	  {
	    rew_measure_num++;
	    concrete_measure->info.rew_measure = alloc_rew_measure(NULL);
	    if (concrete_measure_list == NULL)
	      last_concrete_measure_cell = concrete_measure_list = alloc_st_bucket_cell(concrete_measure,
										        NULL);
	    else
	      last_concrete_measure_cell = last_concrete_measure_cell->next_st_bucket_cell =
	        alloc_st_bucket_cell(concrete_measure,
				     NULL);
	  }
	}
	return((error_found)?
		 NULL:
		 concrete_measure_list);
}


void		handle_type_yb(ST_BUCKET3_CELL *type_yb_cell)
{
	BOOLEAN		found;
	ST_BUCKET	*concrete_aei,
			*concrete_act_type,
			*concrete_reward;
	ST_BUCKET_CELL	*concrete_rew_measure_cell;
	ST_BUCKET3_CELL	*concrete_type_yb,
			*concrete_type_yb_cell,
			*prev_concrete_type_yb_cell;
	YB_CELL		*concrete_yb_cell,
			*prev_concrete_yb_cell;
	int		min_value[2],
			max_value[2],
			value[2],
			rew_measure_no,
			yb_num;

	min_value[0] = (selector[0] == NULL)?
			 0:
			 (int)(selector[0]->info.expr->opn1->info.expr->value);
	max_value[0] = (selector[0] == NULL)?
			 0:
			 (int)(selector[0]->info.expr->opn2->info.expr->value);
	for (value[0] = min_value[0],
	     concrete_rew_measure_cell = aux_rew_measure_list,
	     rew_measure_no = init_rew_measure_no;
	     (value[0] <= max_value[0]);
	     value[0]++,
	     concrete_rew_measure_cell = concrete_rew_measure_cell->next_st_bucket_cell,
	     rew_measure_no++)
	{
	  if (selector[0] != NULL)
	    selector[0]->info.expr->value = (long double)(value[0]);
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
	    if (selector[1] != NULL)
	      selector[1]->info.expr->value = (long double)(value[1]);
	    if (index_expr[1] == NULL)
	    {
	      /* the action type must be checked anyway, as it may have erroneously occurred without any */
	      /* index */
	      concrete_act_type = type_yb_cell->st_bucket1;
	      check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		       &concrete_act_type,
		       TRUE);
	    }
	    else
	    {
	      eval_expr(index_expr[1],
			0);
	      concrete_aei = unindexed_aei[1];
	      build_indexed_id(&concrete_aei,
			       set_concrete_expr_bucket(index_expr[1]));
	      check_id(AEI_ID_USE,
		       &concrete_aei,
		       TRUE);
	      if (concrete_aei != NULL)
	      {
	        concrete_act_type = unindexed_id[1];
	        build_prefixed_id(concrete_aei,
				  &concrete_act_type);
	        check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		         &concrete_act_type,
			 TRUE);
	      }
	      else
		concrete_act_type = NULL;
	    }
	    if ((concrete_act_type != NULL) &&
	        check_rew_act_type(concrete_act_type,
				   ((selector[0] != NULL) ||
				    (selector[1] != NULL))))
	    {
	      for (prev_concrete_type_yb_cell = concrete_type_yb_cell =
		     concrete_rew_measure_cell->st_bucket->info.rew_measure->type_yb_list,
		   found = FALSE;
		   ((concrete_type_yb_cell != NULL) &&
		    !found);
		   prev_concrete_type_yb_cell = concrete_type_yb_cell,
		   concrete_type_yb_cell = concrete_type_yb_cell->next_st_bucket3_cell)
		found = (concrete_type_yb_cell->st_bucket1 == concrete_act_type);
	      if (found)
	      {
		if ((selector[0] == NULL) &&
		    (selector[1] == NULL))
	          signal_error(ACT_TYPE_ALREADY_REWARD,
		               NULL,
		               NULL);
		else
	          signal_error(ONE_ACT_TYPE_ALREADY_REWARD,
		               concrete_act_type->symbol_lexeme,
		               concrete_rew_measure_cell->st_bucket->symbol_lexeme);
	      }
	      else
	      {
	        /* append the type-yield-bonus triple to the triple list of the measure */
	        eval_expr((reward_index == YIELD)?
			    type_yb_cell->st_bucket2:
			    type_yb_cell->st_bucket3,
			  0);
	        concrete_reward = set_concrete_expr_bucket((reward_index == YIELD)?
							     type_yb_cell->st_bucket2:
							     type_yb_cell->st_bucket3);
		concrete_type_yb = alloc_st_bucket3_cell(concrete_act_type,
							 (reward_index == YIELD)?
							   concrete_reward:
							   NULL,
							 (reward_index == BONUS)?
							   concrete_reward:
							   NULL,
							 NULL);
		if (concrete_type_yb_cell ==
		      concrete_rew_measure_cell->st_bucket->info.rew_measure->type_yb_list)
		  concrete_rew_measure_cell->st_bucket->info.rew_measure->type_yb_list = concrete_type_yb;
		else
		  prev_concrete_type_yb_cell->next_st_bucket3_cell = concrete_type_yb;
		/* append the yield-bonus pair to the pair list of the action type (functions */
		/* generate_local_transitions() and compute_act_prefix_der_local_states() of */
		/* aemilia-compiler.c will take care of exporting the yield-bonus pair to possible */
		/* renamed versions and or-duplicates of the action type) */
                for (prev_concrete_yb_cell = concrete_yb_cell =
		       concrete_act_type->info.act_type->reward_info.yb_list,
	             yb_num = 0;
                     (concrete_yb_cell != NULL);
	             prev_concrete_yb_cell = concrete_yb_cell,
                     concrete_yb_cell = concrete_yb_cell->next_yb_cell,
                     yb_num++);
	        for (concrete_yb_cell = prev_concrete_yb_cell;
	             (yb_num < rew_measure_no);
	             yb_num++)
	          if (concrete_yb_cell == NULL)
	            concrete_yb_cell = concrete_act_type->info.act_type->reward_info.yb_list =
		      alloc_yb_cell(0.0L,
				    0.0L,
				    NULL);
	          else
	            concrete_yb_cell = concrete_yb_cell->next_yb_cell = alloc_yb_cell(0.0L,
										      0.0L,
										      NULL);
		if (reward_index == YIELD)
		  concrete_yb_cell->yield = concrete_reward->info.expr->value;
		else
		  concrete_yb_cell->bonus = concrete_reward->info.expr->value;
	      }
	    }
	  }
	}
}


int		rewyyerror(char *msg)
{
        if (!strcmp(rewyytext,
                    "MEASURE"))
        {
          remove_lexeme();
          unread_rew_token();
          signal_error(ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
          signal_error(RESUME_ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
        }
        else
	  if (rewyytext[0] == ';')
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


void            handle_sync_rew(void)
{
        if (!strcmp(rewyytext,
                    "MEASURE"))
        {
          remove_lexeme();
          unread_rew_token();
	}
        signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
                     NULL,
                     NULL);
}
