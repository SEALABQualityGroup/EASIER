/****************************************************************/
/*								*/
/*								*/
/*                  	   sec_parser.y				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module implements the parser for .sec spec files. */
/* This module contains the following function: */
/* - parse_sec(): It drives the parsing of a .sec spec file. */
/* and the following auxiliary functions: */
/* - expand_iterative_act_type_decl(): It expands an iterative action type declaration into the */
/*				       corresponding non-iterative action types declarations. */
/* - associate_security_level(): It associates a security level with a correct non-iterative action type */
/*				 and its possible renamed versions and or-duplicates. */
/* - associate_obs_nrestr_internals(): It associates a security level with all the observable, */
/*				       non-restricted internal actions. */
/* - associate_aei_obs_nrestr_internals(): It associates a security level with all the observable, */
/*				           non-restricted internal actions of an AEI. */
/* - associate_obs_nrestr_interactions(): It associates a security level with all the observable, */
/*					  non-restricted interactions. */
/* - associate_aei_obs_nrestr_interactions(): It associates a security level with all the observable, */
/*					      non-restricted interactions of an AEI. */
/* - associate_all_obs_nrestr(): It associates a security level with all the observable, non-restricted */
/*				 actions. */
/* - associate_aei_all_obs_nrestr(): It associates a security level with all the observable, */
/*				     non-restricted actions of an AEI. */
/* - secyyerror(): It disables the homonymous library function, in order not to interfere with TTGUI, and */
/*                 reports an error message whenever a syntax error occurs. The error message pinpoints */
/*		   more accurately the place where the error occurs than it would do if the error message */
/*		   were issued only after the detection of a synchronizing token. This also allows the */
/*		   case in which no synchronizing token can be found to be treated easily. */
/* - handle_sync_sec(): It handles the detection of a synchronizing token. */
/****************************************************************/


%{

/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include        <float.h>
#include	<stdio.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/sec_scanner.h"
#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/file_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		parse_sec(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	ST_BUCKET	*aei;
				/* pointer to the symbol table bucket for the AEI whose action type is */
				/* being parsed; this is needed to manage the dot notation for the name */
				/* of the action type to be made high/low; */
				/* set by ; */
				/* used in */

PRIVATE	VARIATION_INDEX	variation;
				/* security level to be associated with the action type being parsed */
				/* set by ; */
				/* used in */


PRIVATE	void		expand_iterative_act_type_decl(void),
			associate_security_level(ST_BUCKET *,
						 BOOLEAN,
						 BOOLEAN),
			associate_obs_nrestr_internals(void),
			associate_aei_obs_nrestr_internals(ST_BUCKET *),
			associate_obs_nrestr_interactions(void),
			associate_aei_obs_nrestr_interactions(ST_BUCKET *),
			associate_all_obs_nrestr(void),
			associate_aei_all_obs_nrestr(ST_BUCKET *);

PRIVATE	int		secyyerror(char *);

PRIVATE	void		handle_sync_sec(void);

%}


/****************************************************************/
/* 5. Definition of grammar symbol attributes.			*/
/****************************************************************/

%union
{
	BOOLEAN		boolean;
				/* flag indicating whether an iterative declaration is correct */
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

%token	<st_bucket>		T_NUMBER			300	/* number */
%token  <st_bucket>             T_ID            		301     /* identifier */
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
%token  <st_bucket>             T_HIGH_SECURITY			534     /* keyword */
%token  <st_bucket>             T_LOW_SECURITY			535     /* keyword */
%token  <st_bucket>             T_OBS_NRESTR_INTERNALS		536     /* keyword */
%token  <st_bucket>             T_OBS_NRESTR_INTERACTIONS	537     /* keyword */
%token  <st_bucket>             T_ALL_OBS_NRESTR		538     /* keyword */
%token				DOTDOT				700	/* symbol ".." */
%token				NE				701	/* symbol "!=" */
%token				LE				702	/* symbol "<=" */
%token				GE				703	/* symbol ">=" */
%token				AND				704	/* symbol "&&" */
%token				OR				705	/* symbol "||" */


/****************************************************************/
/* 7. Association of attributes with nonterminals.		*/
/****************************************************************/

%type	<boolean>		i_act_type_set	  /* body of an iterative action type set */
%type	<boolean>		act_type_set_hl	  /* action type set being made high/low */
%type	<st_bucket>		iteration	  /* iteration */
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

%start			sec_level_list


/****************************************************************/
/* 10. Definition of grammar rules.				*/
/****************************************************************/

%%

sec_level_list	:	high_list low_list
			    {
			    }
		|       error T_HIGH_SECURITY
                            {
                              handle_sync_sec();
                              yyerrok;
                            }
                        sec_level_list
                            {
                            }
		|       error T_LOW_SECURITY
                            {
			      variation = MADE_LOW;
                              handle_sync_sec();
                              yyerrok;
                            }
                        low_list
                            {
                            }
		;


high_list	:	T_HIGH_SECURITY act_type_list
                            {
			      variation = MADE_LOW;
                            }
		;


low_list	:	T_LOW_SECURITY act_type_list
                            {
                            }
		;


act_type_list	:	act_type_set
			    {
			    }
		|	act_type_set ';' act_type_list
			    {
			    }
		|	error ';'
                            {
                              handle_sync_sec();
                              yyerrok;
                            }
			act_type_list
			    {
			    }
		;


act_type_set	:	s_act_type_set
			    {
			    }
		|	iteration i_act_type_set
			    {
			      selector_enabled[0] = FALSE;
			      if (($1 != NULL) &&
				  $2)
				expand_iterative_act_type_decl();
			      selector[0] = NULL;
			    }
		;


s_act_type_set	:	T_ID poss_aei_index
                            {
			      handle_concretely_indexed_aei(&$1,
							    $2,
							    0,
							    FALSE,
							    FALSE);
			      aei = $1;
                            }
                        '.' act_type_set_hl
			    {
			    }
		|	T_OBS_NRESTR_INTERNALS
			    {
			      associate_obs_nrestr_internals();
			    }
		|	T_OBS_NRESTR_INTERACTIONS
			    {
			      associate_obs_nrestr_interactions();
			    }
		|	T_ALL_OBS_NRESTR
			    {
			      associate_all_obs_nrestr();
			    }
		;


i_act_type_set	:	T_ID aei_index
                            {
			      handle_symbolically_indexed_aei(&$1,
							      $2,
							      0,
							      FALSE);
			      aei = $1;
                            }
                        '.' act_type_set_hl
			    {
			      $$ = $5;
			    }
		;


act_type_set_hl	:	T_ID
			    {
			      handle_prefixed_indexed_id(aei,
						         &$1,
						         0,
						         selector_enabled[0],
						         FALSE,
							 ACT_TYPE_ID_USE_AUX_SPEC);
			      if (($1 != NULL) &&
				  !selector_enabled[0])
				associate_security_level($1,
							 FALSE,
							 FALSE);
			      $$ = ($1 != NULL);
			    }
		|	T_OBS_NRESTR_INTERNALS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_internals(aei);
			      $$ = (aei != NULL);
			    }
		|	T_OBS_NRESTR_INTERACTIONS
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_interactions(aei);
			      $$ = (aei != NULL);
			    }
		|	T_ALL_OBS_NRESTR
			    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_all_obs_nrestr(aei);
			      $$ = (aei != NULL);
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


/****************************************************************/
/* 11. Definition of exporting functions.			*/
/****************************************************************/

void		parse_sec(char *sec_path)
{
	int		secyyparse(void);

	/* open the .sec spec and the .lis file */
	secyyin = new_fopen(sec_path,
			    NULL,
			    "r");
	open_listing(sec_path);

	/* initialize the iteration-related global variables imported via symbol-handler.h */
	selector_enabled[0] = selector_enabled[1] = poss_aei_index_parsed = FALSE;
	id_prefix_in_expr = NULL;
	selector[0] = selector[1] = unindexed_aei[0] = unindexed_aei[1] = index_expr[0] = index_expr[1] =
	  unindexed_id[0] = unindexed_id[1] = NULL;
	selector_index = 0;

	/* initialize the private global variables */
	aei = NULL;
	variation = MADE_HIGH;

	/* parse the .sec spec */
	secyyparse();

	/* close the .sec spec and the .lis file */
	fclose(secyyin);
	close_listing();
}


/****************************************************************/
/* 12. Definition of private functions.				*/
/****************************************************************/

void		expand_iterative_act_type_decl(void)
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
	  {
	    concrete_act_type = unindexed_id[0];
	    build_prefixed_id(concrete_aei,
			      &concrete_act_type);
	    check_id(ACT_TYPE_ID_USE_AUX_SPEC,
		     &concrete_act_type,
		     TRUE);
	    if (concrete_act_type != NULL)
	      associate_security_level(concrete_act_type,
				       FALSE,
				       TRUE);
	  }
	}
}


void		associate_security_level(ST_BUCKET *act_type,
					 BOOLEAN   skip_obs_nrestr_checks,
					 BOOLEAN   inside_iteration)
{
        ST_BUCKET_CELL	*duplicate_cell;

	if (!skip_obs_nrestr_checks &&
	    (((act_type->info.act_type->renamed == NULL) &&
              (act_type->info.act_type->variation == HIDDEN)) ||
             ((act_type->info.act_type->renamed != NULL) &&
              (act_type->info.act_type->renamed->info.act_type->variation == HIDDEN))))
	{
	  if (!inside_iteration)
	    signal_error(HIGH_LOW_ACT_TYPE_HIDDEN,
                         NULL,
                         NULL);
	  else
	    signal_error(ONE_HIGH_LOW_ACT_TYPE_HIDDEN,
                         act_type->symbol_lexeme,
                         NULL);
	}
	else
	  if (!skip_obs_nrestr_checks &&
	      (((act_type->info.act_type->renamed == NULL) &&
                (act_type->info.act_type->variation == RESTRICTED)) ||
               ((act_type->info.act_type->renamed != NULL) &&
                (act_type->info.act_type->renamed->info.act_type->variation == RESTRICTED))))
	  {
	    if (!inside_iteration)
	      signal_error(HIGH_LOW_ACT_TYPE_RESTRICTED,
                           NULL,
                           NULL);
	    else
	      signal_error(ONE_HIGH_LOW_ACT_TYPE_RESTRICTED,
                           act_type->symbol_lexeme,
                           NULL);
	  }
	  else
	    if ((variation == MADE_LOW) &&
		(((act_type->info.act_type->renamed == NULL) &&
                  (act_type->info.act_type->variation == MADE_HIGH)) ||
                 ((act_type->info.act_type->renamed != NULL) &&
                  (act_type->info.act_type->renamed->info.act_type->variation == MADE_HIGH))))
	    {
	      if (!inside_iteration)
	        signal_error(HIGH_ACT_TYPE_BECOMES_LOW,
                             NULL,
                             NULL);
	      else
	        signal_error(ONE_HIGH_ACT_TYPE_BECOMES_LOW,
                             act_type->symbol_lexeme,
                             NULL);
	    }
	    else
	      if ((variation == MADE_HIGH) &&
		  (((act_type->info.act_type->renamed == NULL) &&
                    (act_type->info.act_type->variation == MADE_HIGH)) ||
                   ((act_type->info.act_type->renamed != NULL) &&
                    (act_type->info.act_type->renamed->info.act_type->variation == MADE_HIGH))))
	      {
	        if (!inside_iteration)
	          signal_error(REDUNDANT_ACT_TYPE_HIGH,
                               NULL,
                               NULL);
	        else
	          signal_error(ONE_REDUNDANT_ACT_TYPE_HIGH,
                               act_type->symbol_lexeme,
                               NULL);
	      }
	      else
	        if ((variation == MADE_LOW) &&
		    (((act_type->info.act_type->renamed == NULL) &&
                      (act_type->info.act_type->variation == MADE_LOW)) ||
                     ((act_type->info.act_type->renamed != NULL) &&
                      (act_type->info.act_type->renamed->info.act_type->variation == MADE_LOW))))
	        {
	          if (!inside_iteration)
	            signal_error(REDUNDANT_ACT_TYPE_LOW,
                                 NULL,
                                 NULL);
	          else
	            signal_error(ONE_REDUNDANT_ACT_TYPE_LOW,
                                 act_type->symbol_lexeme,
                                 NULL);
		}
		else
		{
                  act_type->info.act_type->variation = (char)variation;
		  if (act_type->info.act_type->renamed == NULL)
                    for (duplicate_cell = act_type->info.act_type->duplicate_list;
                         (duplicate_cell != NULL);
                         duplicate_cell = duplicate_cell->next_st_bucket_cell)
                    {
                      duplicate_cell->st_bucket->info.act_type->variation = (char)variation;
                      duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->variation =
			(char)variation;
                    }
                  else
                    act_type->info.act_type->renamed->info.act_type->variation = (char)variation;
		}
}


void		associate_obs_nrestr_internals(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[0]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  associate_aei_obs_nrestr_internals(aei_cell->st_bucket);
}


void		associate_aei_obs_nrestr_internals(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if (act_type_cell->st_bucket->info.act_type->interaction_index == NO_INTERACTION)
	    associate_security_level(act_type_cell->st_bucket,
				     TRUE,
				     TRUE);
}


void		associate_obs_nrestr_interactions(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[0]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  associate_aei_obs_nrestr_interactions(aei_cell->st_bucket);
}


void		associate_aei_obs_nrestr_interactions(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  if (act_type_cell->st_bucket->info.act_type->interaction_index != NO_INTERACTION)
	    associate_security_level(act_type_cell->st_bucket,
				     TRUE,
				     TRUE);
}


void		associate_all_obs_nrestr(void)
{
	ST_BUCKET_CELL	*aei_cell;

	for (aei_cell = archi_type[0]->info.archi_type->aei_list;
	     (aei_cell != NULL);
	     aei_cell = aei_cell->next_st_bucket_cell)
	  associate_aei_all_obs_nrestr(aei_cell->st_bucket);
}


void		associate_aei_all_obs_nrestr(ST_BUCKET *aei)
{
	ST_BUCKET_CELL	*act_type_cell;

	for (act_type_cell = aei->info.aei->act_type_list;
	     (act_type_cell != NULL);
	     act_type_cell = act_type_cell->next_st_bucket_cell)
	  associate_security_level(act_type_cell->st_bucket,
				   TRUE,
				   TRUE);
}


int		secyyerror(char *msg)
{
        if (!strcmp(secyytext,
                    "HIGH_SECURITY") ||
            !strcmp(secyytext,
                    "LOW_SECURITY"))
        {
          remove_lexeme();
          unread_sec_token();
          signal_error(ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
          signal_error(RESUME_ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
        }
        else
	  if (secyytext[0] == ';')
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


void            handle_sync_sec(void)
{
        if (!strcmp(secyytext,
                    "HIGH_SECURITY") ||
            !strcmp(secyytext,
                    "LOW_SECURITY"))
        {
          remove_lexeme();
          unread_sec_token();
	}
        signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
                     NULL,
                     NULL);
}
