/****************************************************************/
/*								*/
/*								*/
/*                  	   sim_parser.y				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module implements the parser for .sim spec files. */
/* This module contains the following function: */
/* - parse_sim(): It drives the parsing of a .sim file. */
/* and the following auxiliary functions: */
/* - handle_clock_act_type(): It contains the actions to be undertaken when parsing the action type on */
/*			      which the simulation clock will be based. */
/* - expand_iterative_sim_measure_def(): It expands a syntactically correct iterative measure definition */
/*					 into the corresponding non-iterative measure definitions. */
/* - handle_interval_begin(): It contains the actions to be undertaken when parsing a syntactically */
/*			      correct observation interval begin within a measure definition. */
/* - handle_interval_end(): It contains the actions to be undertaken when parsing a syntactically correct */
/*			    observation interval end within a measure definition. */
/* - handle_sub_interval_width(): It contains the actions to be undertaken when parsing a syntactically */
/*				  correct observation subinterval width within a measure definition. */
/* - check_interval_begin(): It checks whether the observation interval begin declared within a */
/*			     non-iterative measure definition is consistent. */
/* - check_interval_end(): It checks whether the observation interval begin declared within a */
/*			   non-iterative measure definition is consistent. */
/* - check_sub_interval_width(): It checks whether the observation subinterval width declared within a */
/*			         non-iterative measure definition is consistent. */
/* - expand_iterative_trace_def(): It expands a syntactically correct iterative trace definition into the */
/*				   corresponding non-iterative trace definitions. */
/* - check_trace_expr(): It checks whether a trace expression occurs in the .aem spec and has an */
/*			 invocation to a pseudo random number generator as outermost operator. */
/* - simyyerror(): It disables the homonymous library function, in order not to interfere with TTGUI, and */
/*                 reports an error message whenever a syntax error occurs. The error message pinpoints */
/*                 more accurately the place where the error occurs than it would do if the error message */
/*                 were issued only after the detection of a synchronizing token. This also allows the */
/*                 case in which no synchronizing token can be found to be treated easily. */
/* - handle_sync_sim(): It handles the detection of a synchronizing token. */
/****************************************************************/


%{

/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include        <float.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/sim_scanner.h"
#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/simulator.h"

#include	"../headers/file_handler.h"
#include	"../headers/list_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		parse_sim(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	ST_BUCKET	*sim_measure_id;
				/* pointer to the symbol table bucket for the measure being parsed; */
				/* set by ; */

PRIVATE	ST_BUCKET4_CELL	*act_type_list,
				/* list of the symbol table buckets for the symbolically indexed action */
				/* types occurring in the reward expression being parsed; */
				/* set by ; */
				/* used in */
			*par_list;
				/* list of the symbol table buckets for the indexed formal variable */
				/* parameters or local variables of a behavior occurring in the reward */
				/* expression being parsed or the indexed formal constant parameters of */
				/* an AEI occurring in the trace expression being parsed; */
				/* set by ; */
				/* used in */


PRIVATE	void		handle_clock_act_type(ST_BUCKET *);

PRIVATE	ST_BUCKET_CELL	*expand_iterative_sim_measure_def(ST_BUCKET *);

PRIVATE	void		handle_interval_begin(EXPR_PARSE_INFO **),
			handle_interval_end(EXPR_PARSE_INFO *,
					    EXPR_PARSE_INFO **),
			handle_sub_interval_width(EXPR_PARSE_INFO *,
						  EXPR_PARSE_INFO *,
						  EXPR_PARSE_INFO **);

PRIVATE	BOOLEAN		check_interval_begin(ST_BUCKET *,
					     BOOLEAN),
			check_interval_end(ST_BUCKET *,
					   ST_BUCKET *,
					   BOOLEAN),
			check_sub_interval_width(ST_BUCKET *,
					         ST_BUCKET *,
					         ST_BUCKET *,
					         BOOLEAN);

PRIVATE	ST_BUCKET_CELL	*expand_iterative_trace_def(ST_BUCKET *);

PRIVATE	BOOLEAN		check_trace_expr(ST_BUCKET *,
					 BOOLEAN,
				         BOOLEAN);

PRIVATE	int		simyyerror(char *);

PRIVATE void            handle_sync_sim(void);

%}


/****************************************************************/
/* 5. Definition of grammar symbol attributes.			*/
/****************************************************************/

%union
{
	BOOLEAN		boolean;
				/* flag indicating whether the sample associated with an action type */
				/* occurring in the specification of a measure is cumulative */
	ST_BUCKET_CELL  *st_bucket_cell;
                                /* list of symbol table bucket cells for simulation measures */
	ST_BUCKET       *st_bucket;
                                /* pointer to the symbol table bucket for an identifier or a number */
        SIM_MEASURE	*sim_measure;
                                /* information about a simulation measure */
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
%token  <st_bucket>             T_TRACE_FILE_ID      	315     /* .trc spec file name */
%token  <st_bucket>             T_FROM                  413     /* keyword */
%token	<st_bucket>		T_FOR_ALL		429	/* keyword */
%token	<st_bucket>		T_IN			430	/* keyword */
%token  <st_bucket>  		T_MOD           	446     /* keyword */
%token  <st_bucket>  		T_MIN           	447     /* keyword */
%token  <st_bucket>  		T_MAX           	448     /* keyword */
%token  <st_bucket>  		T_ABS           	449     /* keyword */
%token  <st_bucket>  		T_CEIL          	450     /* keyword */
%token  <st_bucket>  		T_FLOOR         	451     /* keyword */
%token  <st_bucket>             T_POWER         	452     /* keyword */
%token  <st_bucket>             T_EPOWER        	453     /* keyword */
%token  <st_bucket>             T_LOGE          	454     /* keyword */
%token  <st_bucket>             T_LOG10         	455     /* keyword */
%token  <st_bucket>             T_SQRT          	456     /* keyword */
%token  <st_bucket>             T_SIN           	457     /* keyword */
%token  <st_bucket>             T_COS           	458     /* keyword */
%token  <st_bucket>             T_C_UNIFORM     	459     /* keyword */
%token  <st_bucket>             T_ERLANG        	460     /* keyword */
%token  <st_bucket>             T_GAMMA         	461     /* keyword */
%token  <st_bucket>             T_EXPONENTIAL   	462     /* keyword */
%token  <st_bucket>             T_WEIBULL       	463     /* keyword */
%token  <st_bucket>             T_BETA          	464     /* keyword */
%token  <st_bucket>             T_NORMAL        	465     /* keyword */
%token  <st_bucket>             T_PARETO        	466     /* keyword */
%token  <st_bucket>             T_B_PARETO      	467     /* keyword */
%token  <st_bucket>             T_D_UNIFORM     	468     /* keyword */
%token  <st_bucket>             T_BERNOULLI     	469     /* keyword */
%token  <st_bucket>             T_BINOMIAL      	470     /* keyword */
%token  <st_bucket>             T_POISSON       	471     /* keyword */
%token  <st_bucket>             T_NEG_BINOMIAL  	472     /* keyword */
%token  <st_bucket>             T_GEOMETRIC     	473     /* keyword */
%token  <st_bucket>             T_PASCAL        	474     /* keyword */
%token	<st_bucket>		T_TRUE			476	/* keyword */
%token	<st_bucket>		T_FALSE			477	/* keyword */
%token	<st_bucket>		T_LIST_CONS		479	/* keyword */
%token  <st_bucket>  		T_FIRST         	480     /* keyword */
%token  <st_bucket>  		T_TAIL          	481     /* keyword */
%token	<st_bucket>		T_CONCAT		482	/* keyword */
%token	<st_bucket>		T_INSERT		483	/* keyword */
%token	<st_bucket>		T_REMOVE		484	/* keyword */
%token  <st_bucket>  		T_LENGTH        	485     /* keyword */
%token	<st_bucket>		T_ARRAY_CONS		487	/* keyword */
%token  <st_bucket>  		T_READ          	488     /* keyword */
%token	<st_bucket>		T_WRITE			489	/* keyword */
%token	<st_bucket>		T_RECORD_CONS		491	/* keyword */
%token  <st_bucket>  		T_GET           	492     /* keyword */
%token	<st_bucket>		T_PUT			493	/* keyword */
%token  <st_bucket>             T_IS                    495     /* keyword */
%token  <st_bucket>             T_MEASURE               539     /* keyword */
%token  <st_bucket>  		T_RUN_LENGTH_ON_EXEC    543     /* keyword */
%token  <st_bucket>  		T_RUN_LENGTH            544     /* keyword */
%token  <st_bucket>  		T_RUN_NUMBER            545     /* keyword */
%token  <st_bucket>  		T_MEAN             	546     /* keyword */
%token  <st_bucket>  		T_VARIANCE             	547     /* keyword */
%token  <st_bucket>  		T_DISTRIBUTION          548     /* keyword */
%token  <st_bucket>  		T_REWARD                549     /* keyword */
%token  <st_bucket>  		T_EXECUTED              550     /* keyword */
%token  <st_bucket>  		T_CUMULATIVE            551     /* keyword */
%token  <st_bucket>  		T_NON_CUMULATIVE        552     /* keyword */
%token  <st_bucket>  		T_DRAW		        553     /* keyword */
%token  <st_bucket>  		T_TRC		        554     /* keyword */
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

%type	<boolean>		cumulative	  /* cumulative flag */
%type	<st_bucket_cell>	measure_def	  /* measure definition */
%type	<st_bucket_cell>	trace_def_list1	  /* n.e. list of trace definitions */
%type	<st_bucket_cell>	trace_def	  /* trace definition */
%type	<st_bucket>		s_measure_def	  /* simple measure definition */
%type	<st_bucket>		i_measure_def	  /* body of an iterative measure definition */
%type	<st_bucket>		act_type	  /* action type */
%type	<st_bucket>		aei_behav_var	  /* behavior variable of an AEI */
%type	<st_bucket>		s_trace_def	  /* simple trace definition */
%type	<st_bucket>		i_trace_def	  /* body of an iterative trace definition */
%type	<st_bucket>		aei_const_par	  /* constant parameter of an AEI */
%type	<st_bucket>		iteration	  /* iteration */
%type	<sim_measure>		sim_measure_evd	  /* simulation measure kernel */
%type	<expr_parse_info>	sim_measure_expr  /* expression in a simulation measure */
%type	<expr_parse_info>	sim_expr_list	  /* expression list in a simulation measure */
%type	<expr_parse_info>	sim_expr_array	  /* expression array in a simulation measure */
%type	<expr_parse_info>	sim_expr_record	  /* expression record in a simulation measure */
%type	<expr_parse_info>	rew_expr	  /* expression in a reward */
%type	<expr_parse_info>	rew_expr_list	  /* expression list in a reward */
%type	<expr_parse_info>	rew_expr_array	  /* expression array in a reward */
%type	<expr_parse_info>	rew_expr_record	  /* expression record in a reward */
%type	<expr_parse_info>	trace_expr	  /* expression from a trace */
%type	<expr_parse_info>	trc_expr_list	  /* expression list from a trace */
%type	<expr_parse_info>	trc_expr_array	  /* expression array from a trace */
%type	<expr_parse_info>	trc_expr_record	  /* expression record from a trace */
%type	<expr_parse_info>	poss_aei_index	  /* possible AEI index */
%type	<expr_parse_info>	aei_index	  /* AEI index */
%type	<expr_parse_info>	expr		  /* expression */
%type	<expr_parse_info>	expr_list	  /* expression list */
%type	<expr_parse_info>	expr_array	  /* expression array */
%type	<expr_parse_info>	expr_record	  /* expression record */
%type	<lar_parse_info>	struct_expr	  /* expression list/array/record */
%type	<lar_parse_info>	sim_struct_expr	  /* expression list/array/record in a simulation measure */
%type	<lar_parse_info>	rew_struct_expr	  /* expression list/array/record in a reward */
%type	<lar_parse_info>	trc_struct_expr	  /* expression list/array/record from a trace */


/****************************************************************/
/* 8. Definition of operator precedence and associativity.	*/
/****************************************************************/

%left                   AND
                        OR
%right                  '!'
%nonassoc               '='
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

%start			simulation_def


/****************************************************************/
/* 10. Definition of grammar rules.				*/
/****************************************************************/

%%

simulation_def	:	clock_act_type sim_run_length sim_run_num measure_def_list trace_def_list
			    {
			    }
                |       error T_RUN_LENGTH_ON_EXEC
                            {
                              handle_sync_sim();
                              yyerrok;
                            }
                        simulation_def
			    {
			    }
                |       error T_RUN_LENGTH
                            {
                              handle_sync_sim();
                              yyerrok;
                            }
                        sim_run_length sim_run_num measure_def_list trace_def_list
			    {
			    }
                |       error T_RUN_NUMBER
                            {
                              handle_sync_sim();
                              yyerrok;
                            }
                        sim_run_num measure_def_list trace_def_list
			    {
			    }
                |       error T_MEASURE
                            {
                              handle_sync_sim();
                              yyerrok;
                            }
                        measure_def_list trace_def_list
			    {
			    }
                |       error T_DRAW
                            {
                              handle_sync_sim();
                              yyerrok;
                            }
                        trace_def_list
			    {
			    }
		;


clock_act_type	:	T_RUN_LENGTH_ON_EXEC act_type
			    {
			      if ($2 != NULL)
			        handle_clock_act_type($2);
			    }
		;


sim_run_length	:	T_RUN_LENGTH expr
			    {
			      if (($2 != NULL) &&
				  check_expr_all($2->expr,
						 NULL,
						 NULL,
						 (ST_BUCKET *)search_lexeme_table("integer",
										  SYT),
						 SIM_RUN_LENGTH_NOT_UNDECL_ID_FREE,
						 SIM_RUN_LENGTH_NOT_RANDOMNESS_FREE,
						 ILL_TYPED_SIM_RUN_LENGTH))
			      {
				eval_expr($2->expr,
					  0);
			        if ($2->expr->info.expr->value <= 0.0L)
				  signal_error(SIM_RUN_LENGTH_NOT_POSITIVE,
					       NULL,
					       NULL);
				else
			          sim_run_length = (int)($2->expr->info.expr->value);
			      }
			    }
		|	error T_RUN_NUMBER
			    {
                              handle_sync_sim();
                              yyerrok;
			    }
		;


sim_run_num	:	T_RUN_NUMBER expr
			    {
			      if (($2 != NULL) &&
				  check_expr_all($2->expr,
						 NULL,
						 NULL,
						 (ST_BUCKET *)search_lexeme_table("integer",
										  SYT),
						 SIM_RUN_NUM_NOT_UNDECL_ID_FREE,
						 SIM_RUN_NUM_NOT_RANDOMNESS_FREE,
						 ILL_TYPED_SIM_RUN_NUM))
			      {
				eval_expr($2->expr,
					  0);
			        if (($2->expr->info.expr->value < (long double)MIN_SIM_RUN_NUM) ||
				    ($2->expr->info.expr->value > (long double)MAX_SIM_RUN_NUM))
				  signal_error(SIM_RUN_NUM_OUT_OF_RANGE,
					       NULL,
					       NULL);
				else
			          sim_run_num = (int)($2->expr->info.expr->value);
			      }
			    }
		|	error T_MEASURE
			    {
                              handle_sync_sim();
                              yyerrok;
			    }
		;


measure_def_list:	measure_def
			    {
			      sim_measure_list = $1;
			    }
		|	measure_def ';' measure_def_list
			    {
			      sim_measure_list = append_list($1,
							     sim_measure_list);
			    }
		|	error ';'
			    {
                              handle_sync_sim();
                              yyerrok;
                            }
			measure_def_list
			    {
			    }
		|	error T_MEASURE
			    {
                              handle_sync_sim();
                              yyerrok;
                            }
			measure_def_list
			    {
			    }
		;


measure_def	:	s_measure_def
                            {
			      $$ = ($1 == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell($1,
                                                          NULL);
                            }
		|	iteration i_measure_def
                            {
			      selector_enabled[0] = FALSE;
                              $$ = (($1 == NULL) ||
                                    ($2 == NULL))?
                                     NULL:
                                     expand_iterative_sim_measure_def($2);
                              selector[0] = NULL;
                            }
		;


s_measure_def	:	T_MEASURE T_ID poss_aei_index
                            {
			      handle_unprefixed_concretely_indexed_id(&$2,
						                      $3,
								      MEASURE_ID_DEF);
			      sim_measure_id = $2;
                            }
			T_IS sim_measure_evd
			    {
                              if ($2 != NULL)
			      {
				if ($6 != NULL)
				  $2->info.sim_measure = $6;
				else
				  $2 = NULL;
			      }
			      $$ = $2;
			    }
		;


i_measure_def	:	T_MEASURE T_ID aei_index
                            {
			      handle_unprefixed_symbolically_indexed_id(&$2,
						                        $3,
						                        0,
								        MEASURE_ID_DEF);
			      sim_measure_id = $2;
			      act_type_list = par_list = NULL;
                            }
			T_IS sim_measure_evd
			    {
                              if ($2 != NULL)
			      {
				if ($6 != NULL)
				  $2->info.sim_measure = $6;
				else
				  $2 = NULL;
			      }
			      $$ = $2;
			    }
		;


sim_measure_evd	:	T_MEAN '{' sim_measure_expr ',' expr
			    {
			      if ($5 != NULL)
				handle_interval_begin(&$5);
			    }
			DOTDOT expr
			    {
			      if ($8 != NULL)
			        handle_interval_end($5,
						    &$8);
			    }
			'}'
			    {
			      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($8 == NULL))?
				     NULL:
				     alloc_sim_measure(M_EXPECTATION,
						       $3->expr,
						       $5->expr,
						       $8->expr,
						       NULL);
			    }
		|	T_VARIANCE '{' sim_measure_expr ',' expr
			    {
			      if ($5 != NULL)
				handle_interval_begin(&$5);
			    }
			DOTDOT expr
			    {
			      if ($8 != NULL)
			        handle_interval_end($5,
						    &$8);
			    }
			'}'
			    {
			      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($8 == NULL))?
				     NULL:
			             alloc_sim_measure(M_VARIANCE,
						       $3->expr,
						       $5->expr,
						       $8->expr,
						       NULL);
			    }
		|	T_DISTRIBUTION '{' sim_measure_expr ',' expr
			    {
			      if ($5 != NULL)
				handle_interval_begin(&$5);
			    }
			DOTDOT expr
			    {
			      if ($8 != NULL)
			        handle_interval_end($5,
						    &$8);
			    }
			',' expr
			    {
			      if ($11 != NULL)
			        handle_sub_interval_width($5,
						          $8,
						          &$11);
			    }
			'}'
			    {
			      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($8 == NULL) ||
				    ($11 == NULL))?
				     NULL:
			             alloc_sim_measure(M_DISTRIBUTION,
						       $3->expr,
						       $5->expr,
						       $8->expr,
						       $11->expr);
			    }
		;


sim_measure_expr:	T_REWARD '(' T_EXECUTED '(' act_type
			    {
			      if (($5 != NULL) &&
				  !selector_enabled[0] &&
				  !check_rew_act_type($5,
						      FALSE))
				$5 = NULL;
			    }
			')' IMPL rew_expr
			    {
			      if (($9 != NULL) &&
                                  !check_expr_all($9->expr,
                                                  selector[0],
                                                  NULL,
                                                  (ST_BUCKET *)search_lexeme_table("real",
                                                                                   SYT),
                                                  REWARD_NOT_UNDECL_ID_FREE,
                                                  REWARD_NOT_RANDOMNESS_FREE,
                                                  ILL_TYPED_REWARD))
                                $9 = NULL;
			    }
			',' cumulative ')'
			    {
			      $$ = ((sim_measure_id == NULL) ||
				    ($5 == NULL) ||
				    ($9 == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_sample_bucket(sim_measure_id,
									     $5,
									     $9->expr,
									     $12),
							   NULL);
			    }
		|	T_ID
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
		|	sim_measure_expr '+' sim_measure_expr
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
		|	sim_measure_expr '-' sim_measure_expr
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
		|	sim_measure_expr '*' sim_measure_expr
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
		|	sim_measure_expr '/' sim_measure_expr
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
		|	T_MOD '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_ABS '(' sim_measure_expr ')'
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
		|	T_CEIL '(' sim_measure_expr ')'
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
		|	T_FLOOR '(' sim_measure_expr ')'
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
		|	T_MIN '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_MAX '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_POWER '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_EPOWER '(' sim_measure_expr ')'
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
		|	T_LOGE '(' sim_measure_expr ')'
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
		|	T_LOG10 '(' sim_measure_expr ')'
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
		|	T_SQRT '(' sim_measure_expr ')'
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
		|	T_SIN '(' sim_measure_expr ')'
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
		|	T_COS '(' sim_measure_expr ')'
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
		|	T_C_UNIFORM '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_ERLANG '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_GAMMA '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_EXPONENTIAL '(' sim_measure_expr ')'
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
		|	T_WEIBULL '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_BETA '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_NORMAL '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_PARETO '(' sim_measure_expr ')'
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
		|	T_B_PARETO '(' sim_measure_expr ',' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_D_UNIFORM '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_BERNOULLI '(' sim_measure_expr ',' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_BINOMIAL '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_POISSON '(' sim_measure_expr ')'
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
		|	T_NEG_BINOMIAL '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_GEOMETRIC '(' sim_measure_expr ')'
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
		|	T_PASCAL '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	sim_measure_expr AND sim_measure_expr
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
		|	sim_measure_expr OR sim_measure_expr
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
		|	'!' sim_measure_expr
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
		|	sim_measure_expr '=' sim_measure_expr
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
		|	sim_measure_expr NE sim_measure_expr
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
		|	sim_measure_expr '<' sim_measure_expr
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
		|	sim_measure_expr LE sim_measure_expr
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
		|	sim_measure_expr '>' sim_measure_expr
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
		|	sim_measure_expr GE sim_measure_expr
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
		|	T_LIST_CONS '(' sim_expr_list ')'
			    {
			      $$ = $3;
			    }
		|	T_FIRST '(' sim_measure_expr ')'
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
		|	T_TAIL '(' sim_measure_expr ')'
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
		|	T_CONCAT '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_INSERT '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_REMOVE '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_LENGTH '(' sim_measure_expr ')'
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
		|	T_ARRAY_CONS '(' sim_expr_array ')'
			    {
			      $$ = $3;
			    }
		|	T_READ '(' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_WRITE '(' sim_measure_expr ',' sim_measure_expr ',' sim_measure_expr ')'
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
		|	T_RECORD_CONS '(' sim_expr_record ')'
			    {
			      $$ = $3;
			    }
		|	T_GET '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &$3,
				       FALSE);
			    }
			',' sim_measure_expr ')'
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
			',' sim_measure_expr ',' sim_measure_expr ')'
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
		|	'(' sim_measure_expr ')'
			    {
			      $$ = $2;
			    }
		;


sim_expr_list	:
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
		|	sim_struct_expr
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


sim_expr_array	:	sim_struct_expr
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


sim_expr_record	:	sim_struct_expr
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


sim_struct_expr	:	sim_measure_expr
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
		|	sim_measure_expr ',' sim_struct_expr
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


act_type	:       T_ID poss_aei_index
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
				act_type_list = alloc_st_bucket4_cell($5,
								      unindexed_aei[1],
								      index_expr[1],
								      unindexed_id[1],
								      act_type_list);
                              $$ = $5;
                            }
                ;


rew_expr	:	aei_behav_var
			    {
			      if ($1 == NULL)
			        $$ = NULL;
			      else
			      {
			        $$ = alloc_expr_parse_info($1,
							   NULL);
				append_to_list_no_dupls($1,
                                                        &rew_var_list);
			      }
			    }
		|	T_ID
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
		|	rew_expr '+' rew_expr
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
		|	rew_expr '-' rew_expr
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
		|	rew_expr '*' rew_expr
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
		|	rew_expr '/' rew_expr
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
		|	T_MOD '(' rew_expr ',' rew_expr ')'
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
		|	T_ABS '(' rew_expr ')'
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
		|	T_CEIL '(' rew_expr ')'
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
		|	T_FLOOR '(' rew_expr ')'
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
		|	T_MIN '(' rew_expr ',' rew_expr ')'
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
		|	T_MAX '(' rew_expr ',' rew_expr ')'
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
		|	T_POWER '(' rew_expr ',' rew_expr ')'
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
		|	T_EPOWER '(' rew_expr ')'
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
		|	T_LOGE '(' rew_expr ')'
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
		|	T_LOG10 '(' rew_expr ')'
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
		|	T_SQRT '(' rew_expr ')'
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
		|	T_SIN '(' rew_expr ')'
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
		|	T_COS '(' rew_expr ')'
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
		|	T_C_UNIFORM '(' rew_expr ',' rew_expr ')'
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
		|	T_ERLANG '(' rew_expr ',' rew_expr ')'
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
		|	T_GAMMA '(' rew_expr ',' rew_expr ')'
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
		|	T_EXPONENTIAL '(' rew_expr ')'
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
		|	T_WEIBULL '(' rew_expr ',' rew_expr ')'
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
		|	T_BETA '(' rew_expr ',' rew_expr ')'
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
		|	T_NORMAL '(' rew_expr ',' rew_expr ')'
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
		|	T_PARETO '(' rew_expr ')'
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
		|	T_B_PARETO '(' rew_expr ',' rew_expr ',' rew_expr ')'
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
		|	T_D_UNIFORM '(' rew_expr ',' rew_expr ')'
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
		|	T_BERNOULLI '(' rew_expr ',' rew_expr ',' rew_expr ')'
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
		|	T_BINOMIAL '(' rew_expr ',' rew_expr ')'
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
		|	T_POISSON '(' rew_expr ')'
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
		|	T_NEG_BINOMIAL '(' rew_expr ',' rew_expr ')'
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
		|	T_GEOMETRIC '(' rew_expr ')'
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
		|	T_PASCAL '(' rew_expr ',' rew_expr ')'
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
		|	rew_expr AND rew_expr
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
		|	rew_expr OR rew_expr
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
		|	'!' rew_expr
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
		|	rew_expr '=' rew_expr
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
		|	rew_expr NE rew_expr
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
		|	rew_expr '<' rew_expr
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
		|	rew_expr LE rew_expr
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
		|	rew_expr '>' rew_expr
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
		|	rew_expr GE rew_expr
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
		|	T_LIST_CONS '(' rew_expr_list ')'
			    {
			      $$ = $3;
			    }
		|	T_FIRST '(' rew_expr ')'
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
		|	T_TAIL '(' rew_expr ')'
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
		|	T_CONCAT '(' rew_expr ',' rew_expr ')'
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
		|	T_INSERT '(' rew_expr ',' rew_expr ')'
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
		|	T_REMOVE '(' rew_expr ',' rew_expr ')'
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
		|	T_LENGTH '(' rew_expr ')'
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
		|	T_ARRAY_CONS '(' rew_expr_array ')'
			    {
			      $$ = $3;
			    }
		|	T_READ '(' rew_expr ',' rew_expr ')'
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
		|	T_WRITE '(' rew_expr ',' rew_expr ',' rew_expr ')'
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
		|	T_RECORD_CONS '(' rew_expr_record ')'
			    {
			      $$ = $3;
			    }
		|	T_GET '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &$3,
				       FALSE);
			    }
			',' rew_expr ')'
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
			',' rew_expr ',' rew_expr ')'
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
		|	'(' rew_expr ')'
			    {
			      $$ = $2;
			    }
		;


rew_expr_list	:
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
		|	rew_struct_expr
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


rew_expr_array	:	rew_struct_expr
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


rew_expr_record	:	rew_struct_expr
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


rew_struct_expr	:	rew_expr
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
		|	rew_expr ',' rew_struct_expr
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


aei_behav_var	:       T_ID poss_aei_index
                            {
			      handle_concretely_indexed_aei(&$1,
                                                            $2,
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            }
                        '.' T_ID '.' T_ID
                            {
                              build_prefixed_id($5,
                                                &$7);
			      handle_prefixed_indexed_id($1,
                                                         &$7,
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         VAR_ID_USE_AUX_SPEC);
			      if (($7 != NULL) &&
				  selector_enabled[0] &&
				  (index_expr[1] != NULL))
				par_list = alloc_st_bucket4_cell($7,
								 unindexed_aei[1],
								 index_expr[1],
								 unindexed_id[1],
								 par_list);
                              $$ = $7;
                            }
		;


cumulative	:	T_CUMULATIVE
			    {
			      $$ = TRUE;
			    }
		|	T_NON_CUMULATIVE
			    {
			      $$ = FALSE;
			    }
		;


trace_def_list	:
			    {
			      trace_file_list = NULL;
			    }
		|	trace_def_list1
			    {
			      trace_file_list = $1;
			    }
		;


trace_def_list1:	trace_def
			    {
			      $$ = $1;
			    }
		|	trace_def ';' trace_def_list1
			    {
			      $$ = append_list($1,
					       $3);
			    }
		|	error ';'
			    {
                              handle_sync_sim();
                              yyerrok;
			    }
                        trace_def_list1
                            {
			      $$ = $4;
                            }
		|	error T_DRAW
			    {
                              handle_sync_sim();
                              yyerrok;
			    }
                        trace_def_list1
                            {
			      $$ = $4;
                            }
		;


trace_def	:	s_trace_def
                            {
			      $$ = ($1 == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell($1,
                                                          NULL);
                            }
		|	iteration
                            {
			      par_list = NULL;
                            }
			i_trace_def
                            {
			      selector_enabled[0] = FALSE;
                              $$ = (($1 == NULL) ||
                                    ($3 == NULL))?
                                     NULL:
                                     expand_iterative_trace_def($3);
                              selector[0] = NULL;
                            }
		;


s_trace_def	:	T_DRAW trace_expr
			    {
			      if (($2 != NULL) &&
				  !check_trace_expr($2->expr,
						    TRUE,
						    FALSE))
				$2 = NULL;
			    }
			T_FROM T_TRACE_FILE_ID poss_aei_index '.' T_TRC
			    {
			      handle_unprefixed_concretely_indexed_id(&$5,
						                      $6,
								      NO_ID_CONTEXT);
			      if ($5 != NULL)
			      {
			        build_suffixed_id(&$5,
						  $8);
			        check_id(TRACE_FILE_ID_USE,
					 &$5,
				         FALSE);
			      }
			      if (($2 != NULL) &&
				  ($5 != NULL))
			      {
			        $2->expr->info.expr->struct_value = (VALUE_CELL *)$5;
			        $$ = $5;
			      }
			      else
				$$ = NULL;
			    }
		;


i_trace_def	:	T_DRAW trace_expr
			    {
			      if (($2 != NULL) &&
				  !check_trace_expr($2->expr,
						    FALSE,
						    FALSE))
				$2 = NULL;
			    }
			T_FROM T_TRACE_FILE_ID aei_index '.' T_TRC
			    {
			      handle_unprefixed_symbolically_indexed_id(&$5,
						                        $6,
						                        0,
								        NO_ID_CONTEXT);
			      if ($5 != NULL)
			      {
			        build_suffixed_id(&$5,
						  $8);
			        check_id(TRACE_FILE_ID_USE,
					 &$5,
				         TRUE);
			      }
			      if (($2 != NULL) &&
				  ($5 != NULL))
			      {
			        $2->expr->info.expr->struct_value = (VALUE_CELL *)$5;
			        $$ = $2->expr;
			      }
			      else
				$$ = NULL;
			    }
		;


trace_expr	:	aei_const_par
			    {
			      $$ = ($1 == NULL)?
				     NULL:
			             alloc_expr_parse_info($1,
						           NULL);
			    }
		|	T_NUMBER
			    {
			      $$ = alloc_expr_parse_info($1,
							 NULL);
			    }
		|	trace_expr '+' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PLUS_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '-' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MINUS_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '*' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(TIMES_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '/' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(DIV_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_MOD '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MOD_OP,
                                                                          $3->expr,
                                                                          $5->expr,
                                                                          NULL,
                                                                          0.0,
                                                                          NULL,
                                                                          FALSE),
                                                           NULL);
			    }
		|	T_ABS '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ABS_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_CEIL '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(CEIL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_FLOOR '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(FLOOR_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_MIN '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MIN_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_MAX '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MAX_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_POWER '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(POWER_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_EPOWER '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EPOWER_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LOGE '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LOGE_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LOG10 '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LOG10_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_SQRT '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(SQRT_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_SIN '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(SIN_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_COS '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(COS_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_C_UNIFORM '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(C_UNIFORM_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_ERLANG '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ERLANG_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_GAMMA '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GAMMA_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_EXPONENTIAL '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EXPONENTIAL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_WEIBULL '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(WEIBULL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_BETA '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BETA_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_NORMAL '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NORMAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_PARETO '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PARETO_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_B_PARETO '(' trace_expr ',' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(B_PARETO_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_D_UNIFORM '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(D_UNIFORM_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_BERNOULLI '(' trace_expr ',' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BERNOULLI_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_BINOMIAL '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BINOMIAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_POISSON '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(POISSON_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_NEG_BINOMIAL '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NEG_BINOMIAL_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_GEOMETRIC '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GEOMETRIC_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_PASCAL '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PASCAL_OP,
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
		|	trace_expr AND trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(AND_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr OR trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(OR_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	'!' trace_expr
			    {
        		      $$ = ($2 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NOT_OP,
                                                                           $2->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '=' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EQ_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr NE trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '<' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LT_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr LE trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr '>' trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GT_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	trace_expr GE trace_expr
			    {
        		      $$ = (($1 == NULL) ||
				    ($3 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GE_OP,
                                                                           $1->expr,
                                                                           $3->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LIST_CONS '(' trc_expr_list ')'
			    {
			      $$ = $3;
			    }
		|	T_FIRST '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(FIRST_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_TAIL '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(TAIL_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_CONCAT '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(CONCAT_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_INSERT '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(INSERT_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_REMOVE '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(REMOVE_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_LENGTH '(' trace_expr ')'
			    {
        		      $$ = ($3 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LENGTH_OP,
                                                                           $3->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_ARRAY_CONS '(' trc_expr_array ')'
			    {
			      $$ = $3;
			    }
		|	T_READ '(' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(READ_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_WRITE '(' trace_expr ',' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($5 == NULL) ||
				    ($7 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(WRITE_OP,
                                                                           $3->expr,
                                                                           $5->expr,
                                                                           $7->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	T_RECORD_CONS '(' trc_expr_record ')'
			    {
			      $$ = $3;
			    }
		|	T_GET '(' T_ID
			    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &$3,
				       FALSE);
			    }
			',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($6 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GET_OP,
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
			',' trace_expr ',' trace_expr ')'
			    {
        		      $$ = (($3 == NULL) ||
				    ($6 == NULL) ||
				    ($8 == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PUT_OP,
                                                                           $3,
                                                                           $6->expr,
                                                                           $8->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
		|	'(' trace_expr ')'
			    {
			      $$ = $2;
			    }
		;


trc_expr_list	:
			    {
			      $$ = alloc_expr_parse_info(get_expr_bucket(LIST_CONS_OP,
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
		|	trc_struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LIST_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   $1->struct_value,
									   FALSE),
                                                           NULL);
			    }
		;


trc_expr_array	:	trc_struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ARRAY_CONS_OP,
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


trc_expr_record	:	trc_struct_expr
			    {
			      $$ = ($1 == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(RECORD_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   $1->struct_value,
									   FALSE),
                                                           NULL);
			    }
		;


trc_struct_expr	:	trace_expr
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
		|	trace_expr ',' trc_struct_expr
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


aei_const_par	:       T_ID poss_aei_index
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
                                                         VAR_ID_USE_AUX_SPEC);
			      if (($5 != NULL) &&
				  selector_enabled[0] &&
				  (index_expr[1] != NULL))
				par_list = alloc_st_bucket4_cell($5,
								 unindexed_aei[1],
								 index_expr[1],
								 unindexed_id[1],
								 par_list);
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


/****************************************************************/
/* 11. Definition of exporting functions.			*/
/****************************************************************/

void		parse_sim(char *sim_path)
{
	int		simyyparse(void);

	/* open the .sim spec and the .lis file */
	simyyin = new_fopen(sim_path,
			    NULL,
			    "r");
	open_listing(sim_path);

	/* initialize the iteration-related global variables imported via symbol-handler.h */
	selector_enabled[0] = selector_enabled[1] = poss_aei_index_parsed = FALSE;
	id_prefix_in_expr = NULL;
	selector[0] = selector[1] = unindexed_aei[0] = unindexed_aei[1] = index_expr[0] = index_expr[1] =
	  unindexed_id[0] = unindexed_id[1] = NULL;
	selector_index = 0;

	/* initialize other imported global variables and the private global variables */
	sim_measure_list = NULL;
	rew_var_list = NULL;
	sim_run_length = 0;
	sim_run_num = 0;

	/* parse the .sim spec */
	simyyparse();

	/* close the .sim spec and the .lis file */
	fclose(simyyin);
	close_listing();
}


/****************************************************************/
/* 12. Definition of private functions.				*/
/****************************************************************/

void		handle_clock_act_type(ST_BUCKET *act_type)
{
        ST_BUCKET_CELL  *duplicate_cell;

        if (act_type->info.act_type->rate_index == (char)PASSIVE)
          signal_error(SIM_CLOCK_ACT_TYPE_PASSIVE,
                       NULL,
                       NULL);
	else
          if (((act_type->info.act_type->renamed == NULL) &&
               (act_type->info.act_type->variation == HIDDEN)) ||
              ((act_type->info.act_type->renamed != NULL) &&
               (act_type->info.act_type->renamed->info.act_type->variation == HIDDEN)))
            signal_error(SIM_CLOCK_ACT_TYPE_HIDDEN,
                         NULL,
                         NULL);
          else
            if (((act_type->info.act_type->renamed == NULL) &&
                 (act_type->info.act_type->variation == RESTRICTED)) ||
                ((act_type->info.act_type->renamed != NULL) &&
                 (act_type->info.act_type->renamed->info.act_type->variation == RESTRICTED)))
              signal_error(SIM_CLOCK_ACT_TYPE_RESTRICTED,
                           NULL,
                           NULL);
            else
            {
              act_type->info.act_type->sim_clock_act_type = (char)TRUE;
              if (act_type->info.act_type->renamed == NULL)
                for (duplicate_cell = act_type->info.act_type->duplicate_list;
                     (duplicate_cell != NULL);
                     duplicate_cell = duplicate_cell->next_st_bucket_cell)
                {
                  duplicate_cell->st_bucket->info.act_type->sim_clock_act_type = (char)TRUE;
                  duplicate_cell->st_bucket->info.act_type->renamed->info.act_type->sim_clock_act_type =
                    (char)TRUE;
                }
              else
                act_type->info.act_type->renamed->info.act_type->sim_clock_act_type = (char)TRUE;
            }
}


ST_BUCKET_CELL	*expand_iterative_sim_measure_def(ST_BUCKET *symbolic_measure)
{
	BOOLEAN		sub_interval_width_undefined;
	ST_BUCKET	*concrete_measure,
                        *concrete_index_expr,
                        *concrete_measure_expr,
			*concrete_interval_begin,
			*concrete_interval_end,
			*concrete_sub_interval_width;
	ST_BUCKET_CELL  *concrete_measure_list,
                        *last_concrete_measure_cell;
	int             min_value,
                        max_value,
                        value;

	concrete_measure_list = last_concrete_measure_cell = NULL;
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
          concrete_measure = unindexed_id[0];
          build_indexed_id(&concrete_measure,
                           concrete_index_expr);
	  check_id(MEASURE_ID_DEF,
		   &concrete_measure,
		   TRUE);
	  if (concrete_measure != NULL)
	  {
	    concrete_measure_expr =
	      set_concrete_uneval_expr_bucket(symbolic_measure->info.sim_measure->measure_expr,
					      act_type_list,
					      par_list,
					      concrete_measure);
	    concrete_interval_begin = symbolic_measure->info.sim_measure->interval_begin;
	    concrete_interval_begin = (check_interval_begin(concrete_interval_begin,
							    TRUE))?
					set_concrete_expr_bucket(concrete_interval_begin):
					NULL;
	    concrete_interval_end = symbolic_measure->info.sim_measure->interval_end;
	    concrete_interval_end = (check_interval_end(concrete_interval_begin,
							concrete_interval_end,
							TRUE))?
				      set_concrete_expr_bucket(concrete_interval_end):
				      NULL;
	    concrete_sub_interval_width = symbolic_measure->info.sim_measure->sub_interval_width;
	    if (concrete_sub_interval_width == NULL)
	      sub_interval_width_undefined = TRUE;
	    else
	    {
	      sub_interval_width_undefined = FALSE;
	      concrete_sub_interval_width = (check_sub_interval_width(concrete_interval_begin,
								      concrete_interval_end,
								      concrete_sub_interval_width,
								      TRUE))?
					      set_concrete_expr_bucket(concrete_sub_interval_width):
					      NULL;
	    }
	    if ((concrete_measure_expr != NULL) &&
	        (concrete_interval_begin != NULL) &&
	        (concrete_interval_end != NULL) &&
		(sub_interval_width_undefined ||
	         (concrete_sub_interval_width != NULL)))
	    {
	      concrete_measure->info.sim_measure =
		alloc_sim_measure((MEASURE_INDEX)(symbolic_measure->info.sim_measure->measure_index),
				  concrete_measure_expr,
				  concrete_interval_begin,
				  concrete_interval_end,
				  concrete_sub_interval_width);
	      if (concrete_measure_list == NULL)
                last_concrete_measure_cell = concrete_measure_list =
		  alloc_st_bucket_cell(concrete_measure,
                                       NULL);
              else
                last_concrete_measure_cell = last_concrete_measure_cell->next_st_bucket_cell =
                  alloc_st_bucket_cell(concrete_measure,
                                       NULL);
	    }
          }
	}
	free_st_bucket4_list(act_type_list);
	free_st_bucket4_list(par_list);
	return(concrete_measure_list);
}


void		handle_interval_begin(EXPR_PARSE_INFO **interval_begin)
{
	if (!check_expr_all((*interval_begin)->expr,
			    selector[0],
			    NULL,
			    (ST_BUCKET *)search_lexeme_table("integer",
							     SYT),
			    INTERVAL_BEGIN_NOT_UNDECL_ID_FREE,
			    INTERVAL_BEGIN_NOT_RANDOMNESS_FREE,
			    ILL_TYPED_INTERVAL_BEGIN))
	  *interval_begin = NULL;
	else
	  if (!selector_enabled[0] &&
	      !check_interval_begin((*interval_begin)->expr,
				    FALSE))
	    *interval_begin = NULL;
}


void		handle_interval_end(EXPR_PARSE_INFO *interval_begin,
				    EXPR_PARSE_INFO **interval_end)
{
	if (!check_expr_all((*interval_end)->expr,
	                    selector[0],
			    NULL,
			    (ST_BUCKET *)search_lexeme_table("integer",
							     SYT),
			    INTERVAL_END_NOT_UNDECL_ID_FREE,
			    INTERVAL_END_NOT_RANDOMNESS_FREE,
			    ILL_TYPED_INTERVAL_END))
	  *interval_end = NULL;
	else
	  if (!selector_enabled[0] &&
	      !check_interval_end((interval_begin != NULL)?
				    interval_begin->expr:
				    NULL,
			          (*interval_end)->expr,
			          FALSE))
	    *interval_end = NULL;
}


void		handle_sub_interval_width(EXPR_PARSE_INFO *interval_begin,
					  EXPR_PARSE_INFO *interval_end,
					  EXPR_PARSE_INFO **sub_interval_width)
{
	if (!check_expr_all((*sub_interval_width)->expr,
			    selector[0],
			    NULL,
			    (ST_BUCKET *)search_lexeme_table("integer",
							     SYT),
			    SUB_INTERVAL_WIDTH_NOT_UNDECL_ID_FREE,
			    SUB_INTERVAL_WIDTH_NOT_RANDOMNESS_FREE,
			    ILL_TYPED_SUB_INTERVAL_WIDTH))
	  *sub_interval_width = NULL;
	else
	  if (!selector_enabled[0] &&
	      !check_sub_interval_width((interval_begin != NULL)?
				          interval_begin->expr:
				          NULL,
				        (interval_end != NULL)?
				          interval_end->expr:
				          NULL,
			                (*sub_interval_width)->expr,
			                FALSE))
	    *sub_interval_width = NULL;
}


BOOLEAN		check_interval_begin(ST_BUCKET *interval_begin,
				     BOOLEAN   inside_iteration)
{
	BOOLEAN		consistent;

	consistent = TRUE;
	eval_expr(interval_begin,
		  0);
	if (interval_begin->info.expr->value < 0.0L)
	{
	  if (!inside_iteration)
	    signal_error(INTERVAL_BEGIN_NEGATIVE,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_INTERVAL_BEGIN_NEGATIVE,
		         interval_begin->symbol_lexeme,
		         NULL);
	  consistent = FALSE;
	}
	else
	  if ((sim_run_length > 0) &&
	      (interval_begin->info.expr->value >= (long double)sim_run_length))
	  {
	    if (!inside_iteration)
	      signal_error(INTERVAL_BEGIN_GE_RUN_LENGTH,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_INTERVAL_BEGIN_GE_RUN_LENGTH,
		           interval_begin->symbol_lexeme,
			   NULL);
	    consistent = FALSE;
	  }
	return(consistent);
}


BOOLEAN		check_interval_end(ST_BUCKET *interval_begin,
				   ST_BUCKET *interval_end,
				   BOOLEAN   inside_iteration)
{
	BOOLEAN		consistent;

	consistent = TRUE;
	eval_expr(interval_end,
		  0);
	if (interval_end->info.expr->value <= 0.0L)
	{
	  if (!inside_iteration)
	    signal_error(INTERVAL_END_NOT_POSITIVE,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_INTERVAL_END_NOT_POSITIVE,
		         interval_end->symbol_lexeme,
		         NULL);
	  consistent = FALSE;
	}
	else
	  if ((interval_begin != NULL) &&
	      (interval_end->info.expr->value <= interval_begin->info.expr->value))
	  {
	    if (!inside_iteration)
	      signal_error(INTERVAL_END_LE_INTERVAL_BEGIN,
			   NULL,
			   NULL);
	    else
	      signal_error(ONE_INTERVAL_END_LE_INTERVAL_BEGIN,
		           interval_end->symbol_lexeme,
			   NULL);
	    consistent = FALSE;
	  }
	  else
	    if ((sim_run_length > 0) &&
	        (interval_end->info.expr->value > (long double)sim_run_length))
	    {
	      if (!inside_iteration)
	        signal_error(INTERVAL_END_GT_RUN_LENGTH,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_INTERVAL_END_GT_RUN_LENGTH,
		             interval_end->symbol_lexeme,
			     NULL);
	      consistent = FALSE;
	    }
	return(consistent);
}


BOOLEAN		check_sub_interval_width(ST_BUCKET *interval_begin,
					 ST_BUCKET *interval_end,
					 ST_BUCKET *sub_interval_width,
					 BOOLEAN   inside_iteration)
{
	BOOLEAN		consistent;

	consistent = TRUE;
	eval_expr(sub_interval_width,
		  0);
	if (sub_interval_width->info.expr->value <= 0.0L)
	{
	  if (!inside_iteration)
	    signal_error(SUB_INTERVAL_WIDTH_NOT_POSITIVE,
		         NULL,
		         NULL);
	  else
	    signal_error(ONE_SUB_INTERVAL_WIDTH_NOT_POSITIVE,
		         sub_interval_width->symbol_lexeme,
		         NULL);
	  consistent = FALSE;
	}
	else
	  if ((interval_begin != NULL) &&
	      (interval_end != NULL) &&
	      (((int)(interval_end->info.expr->value - interval_begin->info.expr->value) %
		(int)(sub_interval_width->info.expr->value)) != 0))
	  {
	    if (!inside_iteration)
	      signal_error(SUB_INTERVAL_WIDTH_NOT_REGULAR,
		           NULL,
		           NULL);
	    else
	      signal_error(ONE_SUB_INTERVAL_WIDTH_NOT_REGULAR,
		           sub_interval_width->symbol_lexeme,
		           NULL);
	    consistent = FALSE;
	  }
	return(consistent);
}


ST_BUCKET_CELL	*expand_iterative_trace_def(ST_BUCKET *symbolic_trace_expr)
{
	ST_BUCKET	*symbolic_trace_file,
			*concrete_trace_file,
			*concrete_trace_expr,
                        *concrete_index_expr;
	ST_BUCKET_CELL  *concrete_trace_file_list,
                        *last_concrete_trace_file_cell;
	int             min_value,
                        max_value,
                        value;

	concrete_trace_file_list = last_concrete_trace_file_cell = NULL;
	symbolic_trace_file = (ST_BUCKET *)(symbolic_trace_expr->info.expr->struct_value);
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
          concrete_trace_file = unindexed_id[0];
          build_indexed_id(&concrete_trace_file,
                           concrete_index_expr);
	  build_suffixed_id(&concrete_trace_file,
			    (ST_BUCKET *)search_lexeme_table(".trc",
							     SYT));
	  check_id(TRACE_FILE_ID_USE,
		   &concrete_trace_file,
		   TRUE);
	  if (concrete_trace_file != NULL)
	  {
	    concrete_trace_expr = set_concrete_uneval_expr_bucket(symbolic_trace_expr,
								  NULL,
								  par_list,
								  NULL);
	    if ((concrete_trace_expr != NULL) &&
		check_trace_expr(concrete_trace_expr,
				 TRUE,
				 TRUE))
	    {
	      concrete_trace_expr->info.expr->struct_value = (VALUE_CELL *)concrete_trace_file;
	      if (concrete_trace_file_list == NULL)
                last_concrete_trace_file_cell = concrete_trace_file_list =
		  alloc_st_bucket_cell(concrete_trace_file,
                                       NULL);
              else
                last_concrete_trace_file_cell = last_concrete_trace_file_cell->next_st_bucket_cell =
                  alloc_st_bucket_cell(concrete_trace_file,
                                       NULL);
	    }
          }
	}
	free_st_bucket4_list(par_list);
	return(concrete_trace_file_list);
}


BOOLEAN		check_trace_expr(ST_BUCKET *expr,
				 BOOLEAN   apply_second_check,
			         BOOLEAN   inside_iteration)
{
	BOOLEAN		correct;

	correct = TRUE;
	if ((expr->info.expr->op_index < C_UNIFORM_OP) ||
	    (expr->info.expr->op_index > PASCAL_OP))
	{
	  if (!inside_iteration)
	    signal_error(TRACE_EXPR_NOT_RANDOM,
			 NULL,
			 NULL);
	  else
	    signal_error(ONE_TRACE_EXPR_NOT_RANDOM,
			 expr->symbol_lexeme,
			 NULL);
	  correct = FALSE;
	}
	else
	  if (apply_second_check)
	  {
	    if (expr->symbol_index == ID)
	    {
	      if (!inside_iteration)
	        signal_error(TRACE_EXPR_NOT_IN_AEMILIA_SPEC,
			     NULL,
			     NULL);
	      else
	        signal_error(ONE_TRACE_EXPR_NOT_IN_AEMILIA_SPEC,
			     expr->symbol_lexeme,
			     NULL);
	      correct = FALSE;
	    }
	    else
	      if (expr->info.expr->struct_value != NULL)
	      {
	        if (!inside_iteration)
	          signal_error(TRACE_EXPR_ALREADY_WITH_TRACE_FILE,
			       NULL,
			       NULL);
	        else
	          signal_error(ONE_TRACE_EXPR_ALREADY_WITH_TRACE_FILE,
			       expr->symbol_lexeme,
			       NULL);
	        correct = FALSE;
	      }
	  }
	return(correct);
}


int		simyyerror(char *msg)
{
        if (!strcmp(simyytext,
                    "RUN_LENGTH_ON_EXEC") ||
            !strcmp(simyytext,
                    "RUN_LENGTH") ||
            !strcmp(simyytext,
                    "RUN_NUMBER") ||
            !strcmp(simyytext,
                    "MEASURE") ||
            !strcmp(simyytext,
                    "DRAW"))
        {
          remove_lexeme();
          unread_sim_token();
          signal_error(ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
          signal_error(RESUME_ILLEGAL_DEF_DECL,
                       NULL,
                       NULL);
        }
        else
	  if (simyytext[0] == ';')
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


void            handle_sync_sim(void)
{
        if (!strcmp(simyytext,
                    "RUN_LENGTH_ON_EXEC") ||
            !strcmp(simyytext,
                    "RUN_LENGTH") ||
            !strcmp(simyytext,
                    "RUN_NUMBER") ||
            !strcmp(simyytext,
                    "MEASURE") ||
            !strcmp(simyytext,
                    "DRAW"))
        {
          remove_lexeme();
          unread_sim_token();
	}
        signal_error(RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL,
                     NULL,
                     NULL);
}
