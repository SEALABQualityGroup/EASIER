/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton implementation for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.3"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Using locations.  */
#define YYLSP_NEEDED 0

/* Substitute the variable and function names.  */
#define yyparse aemiliayyparse
#define yylex   aemiliayylex
#define yyerror aemiliayyerror
#define yylval  aemiliayylval
#define yychar  aemiliayychar
#define yydebug aemiliayydebug
#define yynerrs aemiliayynerrs


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     T_NUMBER = 300,
     T_ID = 301,
     T_ARCHITECTURAL_TYPE = 400,
     T_ARCHI_ELEM_TYPES = 401,
     T_ELEM_TYPE = 402,
     T_BEHAVIOR = 403,
     T_INPUT_INTERACTIONS = 404,
     T_OUTPUT_INTERACTIONS = 405,
     T_UNI = 406,
     T_AND = 407,
     T_OR = 408,
     T_ARCHI_TOPOLOGY = 409,
     T_ARCHI_ELEM_INSTANCES = 410,
     T_ARCHI_INTERACTIONS = 411,
     T_ARCHI_ATTACHMENTS = 412,
     T_FROM = 413,
     T_TO = 414,
     T_BEHAV_VARIATIONS = 415,
     T_BEHAV_HIDINGS = 416,
     T_HIDE = 417,
     T_INTERNALS = 418,
     T_INTERACTIONS = 419,
     T_ALL = 420,
     T_BEHAV_RESTRICTIONS = 421,
     T_RESTRICT = 422,
     T_OBS_INTERNALS = 423,
     T_OBS_INTERACTIONS = 424,
     T_ALL_OBSERVABLES = 425,
     T_BEHAV_RENAMINGS = 426,
     T_RENAME = 427,
     T_AS = 428,
     T_FOR_ALL = 429,
     T_IN = 430,
     T_END = 431,
     T_CONST = 432,
     T_LOCAL = 433,
     T_STOP = 434,
     T_EXP = 436,
     T_INF = 437,
     T_CHOICE = 438,
     T_COND = 439,
     T_VOID = 440,
     T_PRIO = 441,
     T_RATE = 442,
     T_WEIGHT = 443,
     T_INTEGER = 444,
     T_REAL = 445,
     T_MOD = 446,
     T_MIN = 447,
     T_MAX = 448,
     T_ABS = 449,
     T_CEIL = 450,
     T_FLOOR = 451,
     T_POWER = 452,
     T_EPOWER = 453,
     T_LOGE = 454,
     T_LOG10 = 455,
     T_SQRT = 456,
     T_SIN = 457,
     T_COS = 458,
     T_C_UNIFORM = 459,
     T_ERLANG = 460,
     T_GAMMA = 461,
     T_EXPONENTIAL = 462,
     T_WEIBULL = 463,
     T_BETA = 464,
     T_NORMAL = 465,
     T_PARETO = 466,
     T_B_PARETO = 467,
     T_D_UNIFORM = 468,
     T_BERNOULLI = 469,
     T_BINOMIAL = 470,
     T_POISSON = 471,
     T_NEG_BINOMIAL = 472,
     T_GEOMETRIC = 473,
     T_PASCAL = 474,
     T_BOOLEAN = 475,
     T_TRUE = 476,
     T_FALSE = 477,
     T_LIST = 478,
     T_LIST_CONS = 479,
     T_FIRST = 480,
     T_TAIL = 481,
     T_CONCAT = 482,
     T_INSERT = 483,
     T_REMOVE = 484,
     T_LENGTH = 485,
     T_ARRAY = 486,
     T_ARRAY_CONS = 487,
     T_READ = 488,
     T_WRITE = 489,
     T_RECORD = 490,
     T_RECORD_CONS = 491,
     T_GET = 492,
     T_PUT = 493,
     ASSIGN = 700,
     DOTDOT = 701,
     NE = 702,
     LE = 703,
     GE = 704,
     AND = 705,
     OR = 706,
     IMPL = 707
   };
#endif
/* Tokens.  */
#define T_NUMBER 300
#define T_ID 301
#define T_ARCHITECTURAL_TYPE 400
#define T_ARCHI_ELEM_TYPES 401
#define T_ELEM_TYPE 402
#define T_BEHAVIOR 403
#define T_INPUT_INTERACTIONS 404
#define T_OUTPUT_INTERACTIONS 405
#define T_UNI 406
#define T_AND 407
#define T_OR 408
#define T_ARCHI_TOPOLOGY 409
#define T_ARCHI_ELEM_INSTANCES 410
#define T_ARCHI_INTERACTIONS 411
#define T_ARCHI_ATTACHMENTS 412
#define T_FROM 413
#define T_TO 414
#define T_BEHAV_VARIATIONS 415
#define T_BEHAV_HIDINGS 416
#define T_HIDE 417
#define T_INTERNALS 418
#define T_INTERACTIONS 419
#define T_ALL 420
#define T_BEHAV_RESTRICTIONS 421
#define T_RESTRICT 422
#define T_OBS_INTERNALS 423
#define T_OBS_INTERACTIONS 424
#define T_ALL_OBSERVABLES 425
#define T_BEHAV_RENAMINGS 426
#define T_RENAME 427
#define T_AS 428
#define T_FOR_ALL 429
#define T_IN 430
#define T_END 431
#define T_CONST 432
#define T_LOCAL 433
#define T_STOP 434
#define T_EXP 436
#define T_INF 437
#define T_CHOICE 438
#define T_COND 439
#define T_VOID 440
#define T_PRIO 441
#define T_RATE 442
#define T_WEIGHT 443
#define T_INTEGER 444
#define T_REAL 445
#define T_MOD 446
#define T_MIN 447
#define T_MAX 448
#define T_ABS 449
#define T_CEIL 450
#define T_FLOOR 451
#define T_POWER 452
#define T_EPOWER 453
#define T_LOGE 454
#define T_LOG10 455
#define T_SQRT 456
#define T_SIN 457
#define T_COS 458
#define T_C_UNIFORM 459
#define T_ERLANG 460
#define T_GAMMA 461
#define T_EXPONENTIAL 462
#define T_WEIBULL 463
#define T_BETA 464
#define T_NORMAL 465
#define T_PARETO 466
#define T_B_PARETO 467
#define T_D_UNIFORM 468
#define T_BERNOULLI 469
#define T_BINOMIAL 470
#define T_POISSON 471
#define T_NEG_BINOMIAL 472
#define T_GEOMETRIC 473
#define T_PASCAL 474
#define T_BOOLEAN 475
#define T_TRUE 476
#define T_FALSE 477
#define T_LIST 478
#define T_LIST_CONS 479
#define T_FIRST 480
#define T_TAIL 481
#define T_CONCAT 482
#define T_INSERT 483
#define T_REMOVE 484
#define T_LENGTH 485
#define T_ARRAY 486
#define T_ARRAY_CONS 487
#define T_READ 488
#define T_WRITE 489
#define T_RECORD 490
#define T_RECORD_CONS 491
#define T_GET 492
#define T_PUT 493
#define ASSIGN 700
#define DOTDOT 701
#define NE 702
#define LE 703
#define GE 704
#define AND 705
#define OR 706
#define IMPL 707




/* Copy the first part of user declarations.  */
#line 90 "aemilia_parser.y"


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



/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif

#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 332 "aemilia_parser.y"
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
/* Line 193 of yacc.c.  */
#line 571 "aemilia_parser.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 584 "aemilia_parser.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int i)
#else
static int
YYID (i)
    int i;
#endif
{
  return i;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef _STDLIB_H
#      define _STDLIB_H 1
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined _STDLIB_H \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef _STDLIB_H
#    define _STDLIB_H 1
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined _STDLIB_H && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss;
  YYSTYPE yyvs;
  };

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack)					\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack, Stack, yysize);				\
	Stack = &yyptr->Stack;						\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  17
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   1701

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  126
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  169
/* YYNRULES -- Number of rules.  */
#define YYNRULES  349
/* YYNRULES -- Number of states.  */
#define YYNSTATES  803

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   708

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,   106,     2,     2,     2,     2,     2,     2,
     114,   115,   112,   110,   116,   111,   118,   113,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,   123,   117,
     108,   107,   109,   121,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,   124,     2,   125,     2,   122,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,   119,     2,   120,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       3,     4,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,     2,    40,    41,    42,    43,
      44,    45,    46,    47,    48,    49,    50,    51,    52,    53,
      54,    55,    56,    57,    58,    59,    60,    61,    62,    63,
      64,    65,    66,    67,    68,    69,    70,    71,    72,    73,
      74,    75,    76,    77,    78,    79,    80,    81,    82,    83,
      84,    85,    86,    87,    88,    89,    90,    91,    92,    93,
      94,    95,    96,    97,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
      98,    99,   100,   101,   102,   103,   104,   105,     2
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     9,    10,    15,    16,    24,    25,    32,
      33,    42,    43,    51,    52,    59,    60,    66,    67,    75,
      76,    83,    84,    90,    91,    96,    97,   104,   106,   108,
     110,   114,   115,   122,   125,   126,   131,   133,   136,   137,
     142,   145,   146,   147,   148,   162,   164,   166,   168,   172,
     176,   180,   181,   182,   193,   195,   197,   199,   203,   204,
     210,   211,   213,   215,   218,   219,   224,   227,   228,   229,
     241,   243,   245,   247,   251,   254,   256,   258,   260,   264,
     268,   270,   272,   274,   275,   276,   285,   287,   289,   294,
     295,   303,   308,   310,   314,   317,   319,   321,   323,   325,
     329,   335,   337,   338,   344,   347,   348,   349,   356,   358,
     361,   364,   365,   366,   367,   377,   378,   383,   388,   390,
     391,   396,   398,   399,   404,   405,   411,   414,   417,   418,
     419,   420,   428,   429,   431,   433,   437,   439,   443,   444,
     447,   448,   451,   452,   455,   457,   458,   463,   464,   469,
     474,   477,   479,   483,   484,   489,   493,   496,   498,   501,
     502,   503,   513,   514,   515,   525,   526,   528,   530,   531,
     536,   539,   541,   543,   545,   549,   550,   555,   559,   562,
     564,   567,   568,   574,   575,   581,   584,   586,   588,   590,
     594,   595,   600,   604,   607,   611,   614,   616,   620,   621,
     622,   623,   637,   638,   639,   640,   654,   655,   656,   662,
     663,   666,   668,   672,   673,   678,   682,   685,   689,   692,
     696,   699,   701,   704,   705,   712,   715,   718,   721,   722,
     729,   731,   733,   735,   737,   738,   741,   743,   747,   748,
     753,   757,   760,   764,   767,   769,   772,   773,   780,   783,
     786,   789,   790,   797,   799,   801,   803,   805,   806,   809,
     811,   815,   816,   821,   825,   828,   830,   833,   834,   835,
     846,   847,   848,   859,   860,   861,   870,   871,   872,   876,
     877,   879,   883,   885,   887,   891,   895,   899,   903,   910,
     915,   920,   925,   932,   939,   946,   951,   956,   961,   966,
     971,   976,   983,   990,   997,  1002,  1009,  1016,  1023,  1028,
    1037,  1044,  1053,  1060,  1065,  1072,  1077,  1084,  1086,  1088,
    1092,  1096,  1099,  1103,  1107,  1111,  1115,  1119,  1123,  1128,
    1133,  1138,  1145,  1152,  1159,  1164,  1169,  1176,  1185,  1190,
    1191,  1199,  1200,  1210,  1214,  1215,  1217,  1219,  1221,  1223
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int16 yyrhs[] =
{
     127,     0,    -1,   139,   145,   215,   251,    36,    -1,    -1,
       1,     5,   128,   127,    -1,    -1,     1,     6,   129,   145,
     215,   251,    36,    -1,    -1,     1,    14,   130,   215,   251,
      36,    -1,    -1,     1,    15,   131,   216,   229,   238,   251,
      36,    -1,    -1,     1,    16,   132,   229,   238,   251,    36,
      -1,    -1,     1,    17,   133,   238,   251,    36,    -1,    -1,
       1,    20,   134,   251,    36,    -1,    -1,     1,    21,   135,
     253,   262,   271,    36,    -1,    -1,     1,    26,   136,   262,
     271,    36,    -1,    -1,     1,    31,   137,   271,    36,    -1,
      -1,     1,    36,   138,    36,    -1,    -1,     5,     4,   140,
     114,   141,   115,    -1,    44,    -1,   142,    -1,   143,    -1,
     143,   116,   142,    -1,    -1,    37,   176,     4,   144,    98,
     288,    -1,     6,   147,    -1,    -1,     1,     7,   146,   147,
      -1,   149,    -1,   149,   147,    -1,    -1,     1,     7,   148,
     147,    -1,     1,    14,    -1,    -1,    -1,    -1,     7,     4,
     150,   114,   153,   115,   156,   151,     9,   208,   152,    10,
     208,    -1,    44,    -1,   154,    -1,   155,    -1,   155,   116,
     154,    -1,    37,   176,     4,    -1,     8,   157,   164,    -1,
      -1,    -1,     4,   158,   114,   160,   117,   173,   115,   159,
     107,   184,    -1,    44,    -1,   161,    -1,   162,    -1,   162,
     116,   161,    -1,    -1,   177,     4,   163,    98,   288,    -1,
      -1,   165,    -1,   167,    -1,   167,   165,    -1,    -1,     1,
     117,   166,   165,    -1,     1,     9,    -1,    -1,    -1,   117,
       4,   168,   114,   170,   117,   173,   115,   169,   107,   184,
      -1,    44,    -1,   171,    -1,   172,    -1,   172,   116,   171,
      -1,   177,     4,    -1,    44,    -1,   174,    -1,   175,    -1,
     175,   116,   174,    -1,    38,   177,     4,    -1,   177,    -1,
     183,    -1,    48,    -1,    -1,    -1,    48,   114,   288,   178,
      99,   288,   179,   115,    -1,    49,    -1,    79,    -1,    82,
     114,   177,   115,    -1,    -1,    90,   114,   288,   180,   116,
     177,   115,    -1,    94,   114,   181,   115,    -1,   182,    -1,
     181,   116,   182,    -1,   177,     4,    -1,    45,    -1,    46,
      -1,    47,    -1,    39,    -1,   192,   118,   185,    -1,    42,
     119,   187,   190,   120,    -1,   184,    -1,    -1,     4,   186,
     114,   206,   115,    -1,   188,   184,    -1,    -1,    -1,    43,
     114,   288,   189,   115,   105,    -1,   191,    -1,   191,   190,
      -1,   116,   187,    -1,    -1,    -1,    -1,   108,     4,   193,
     196,   194,   116,   201,   195,   109,    -1,    -1,   121,   114,
     197,   115,    -1,   106,   114,   199,   115,    -1,     4,    -1,
      -1,     4,   198,   116,   197,    -1,   288,    -1,    -1,   288,
     200,   116,   199,    -1,    -1,    40,   114,   288,   202,   115,
      -1,    41,   203,    -1,   122,   203,    -1,    -1,    -1,    -1,
     114,   288,   204,   116,   288,   205,   115,    -1,    -1,   207,
      -1,   288,    -1,   288,   116,   207,    -1,    44,    -1,   209,
     210,   211,    -1,    -1,    11,   212,    -1,    -1,    12,   212,
      -1,    -1,    13,   212,    -1,     4,    -1,    -1,     4,   213,
     117,   212,    -1,    -1,     1,   117,   214,   212,    -1,    14,
     216,   229,   238,    -1,    15,   217,    -1,   219,    -1,   219,
     117,   217,    -1,    -1,     1,   117,   218,   217,    -1,   219,
       1,    16,    -1,     1,    16,    -1,   220,    -1,   281,   223,
      -1,    -1,    -1,     4,   286,   221,   123,     4,   222,   114,
     226,   115,    -1,    -1,    -1,     4,   287,   224,   123,     4,
     225,   114,   226,   115,    -1,    -1,   227,    -1,   288,    -1,
      -1,   288,   228,   116,   227,    -1,    16,   230,    -1,    44,
      -1,   231,    -1,   233,    -1,   233,   117,   231,    -1,    -1,
       1,   117,   232,   231,    -1,   233,     1,    17,    -1,     1,
      17,    -1,   234,    -1,   281,   236,    -1,    -1,     4,   286,
     235,   118,     4,    -1,    -1,     4,   287,   237,   118,     4,
      -1,    17,   239,    -1,    44,    -1,   240,    -1,   242,    -1,
     242,   117,   240,    -1,    -1,     1,   117,   241,   240,    -1,
     242,     1,    20,    -1,     1,    20,    -1,   242,     1,    36,
      -1,     1,    36,    -1,   243,    -1,   281,   284,   247,    -1,
      -1,    -1,    -1,    18,     4,   286,   244,   118,     4,   245,
      19,     4,   286,   246,   118,     4,    -1,    -1,    -1,    -1,
      18,     4,   286,   248,   118,     4,   249,    19,     4,   286,
     250,   118,     4,    -1,    -1,    -1,    20,   252,   253,   262,
     271,    -1,    -1,    21,   254,    -1,   256,    -1,   256,   117,
     254,    -1,    -1,     1,   117,   255,   254,    -1,   256,     1,
      26,    -1,     1,    26,    -1,   256,     1,    31,    -1,     1,
      31,    -1,   256,     1,    36,    -1,     1,    36,    -1,   257,
      -1,   281,   259,    -1,    -1,    22,     4,   286,   258,   118,
     261,    -1,    22,    23,    -1,    22,    24,    -1,    22,    25,
      -1,    -1,    22,     4,   287,   260,   118,   261,    -1,     4,
      -1,    23,    -1,    24,    -1,    25,    -1,    -1,    26,   263,
      -1,   265,    -1,   265,   117,   263,    -1,    -1,     1,   117,
     264,   263,    -1,   265,     1,    31,    -1,     1,    31,    -1,
     265,     1,    36,    -1,     1,    36,    -1,   266,    -1,   281,
     268,    -1,    -1,    27,     4,   286,   267,   118,   270,    -1,
      27,    28,    -1,    27,    29,    -1,    27,    30,    -1,    -1,
      27,     4,   287,   269,   118,   270,    -1,     4,    -1,    28,
      -1,    29,    -1,    30,    -1,    -1,    31,   272,    -1,   274,
      -1,   274,   117,   272,    -1,    -1,     1,   117,   273,   272,
      -1,   274,     1,    36,    -1,     1,    36,    -1,   275,    -1,
     281,   278,    -1,    -1,    -1,    32,     4,   286,   276,   118,
       4,   277,    33,     4,   286,    -1,    -1,    -1,    32,     4,
     287,   279,   118,     4,   280,    33,     4,   286,    -1,    -1,
      -1,    34,     4,   282,    35,   288,   283,    99,   288,    -1,
      -1,    -1,    12,   285,   281,    -1,    -1,   287,    -1,   124,
     288,   125,    -1,     4,    -1,     3,    -1,   288,   110,   288,
      -1,   288,   111,   288,    -1,   288,   112,   288,    -1,   288,
     113,   288,    -1,    50,   114,   288,   116,   288,   115,    -1,
      53,   114,   288,   115,    -1,    54,   114,   288,   115,    -1,
      55,   114,   288,   115,    -1,    51,   114,   288,   116,   288,
     115,    -1,    52,   114,   288,   116,   288,   115,    -1,    56,
     114,   288,   116,   288,   115,    -1,    57,   114,   288,   115,
      -1,    58,   114,   288,   115,    -1,    59,   114,   288,   115,
      -1,    60,   114,   288,   115,    -1,    61,   114,   288,   115,
      -1,    62,   114,   288,   115,    -1,    63,   114,   288,   116,
     288,   115,    -1,    64,   114,   288,   116,   288,   115,    -1,
      65,   114,   288,   116,   288,   115,    -1,    66,   114,   288,
     115,    -1,    67,   114,   288,   116,   288,   115,    -1,    68,
     114,   288,   116,   288,   115,    -1,    69,   114,   288,   116,
     288,   115,    -1,    70,   114,   288,   115,    -1,    71,   114,
     288,   116,   288,   116,   288,   115,    -1,    72,   114,   288,
     116,   288,   115,    -1,    73,   114,   288,   116,   288,   116,
     288,   115,    -1,    74,   114,   288,   116,   288,   115,    -1,
      75,   114,   288,   115,    -1,    76,   114,   288,   116,   288,
     115,    -1,    77,   114,   288,   115,    -1,    78,   114,   288,
     116,   288,   115,    -1,    80,    -1,    81,    -1,   288,   103,
     288,    -1,   288,   104,   288,    -1,   106,   288,    -1,   288,
     107,   288,    -1,   288,   100,   288,    -1,   288,   108,   288,
      -1,   288,   101,   288,    -1,   288,   109,   288,    -1,   288,
     102,   288,    -1,    83,   114,   291,   115,    -1,    84,   114,
     288,   115,    -1,    85,   114,   288,   115,    -1,    86,   114,
     288,   116,   288,   115,    -1,    87,   114,   288,   116,   288,
     115,    -1,    88,   114,   288,   116,   288,   115,    -1,    89,
     114,   288,   115,    -1,    91,   114,   292,   115,    -1,    92,
     114,   288,   116,   288,   115,    -1,    93,   114,   288,   116,
     288,   116,   288,   115,    -1,    95,   114,   293,   115,    -1,
      -1,    96,   114,     4,   289,   116,   288,   115,    -1,    -1,
      97,   114,     4,   290,   116,   288,   116,   288,   115,    -1,
     114,   288,   115,    -1,    -1,   294,    -1,   294,    -1,   294,
      -1,   288,    -1,   288,   116,   294,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   567,   567,   571,   570,   579,   578,   587,   586,   595,
     594,   603,   602,   611,   610,   619,   618,   627,   626,   635,
     634,   643,   642,   651,   650,   662,   661,   679,   683,   690,
     697,   708,   707,   726,   731,   730,   742,   750,   759,   758,
     767,   777,   786,   791,   776,   806,   810,   817,   824,   834,
     850,   861,   866,   860,   881,   885,   892,   899,   910,   909,
     949,   952,   959,   966,   974,   973,   982,   992,   997,   991,
    1012,  1016,  1023,  1030,  1040,  1056,  1060,  1067,  1074,  1084,
    1100,  1104,  1111,  1125,  1138,  1124,  1168,  1181,  1193,  1203,
    1202,  1223,  1234,  1241,  1261,  1285,  1297,  1309,  1324,  1335,
    1346,  1360,  1365,  1364,  1390,  1408,  1412,  1411,  1430,  1434,
    1448,  1456,  1471,  1496,  1455,  1550,  1554,  1559,  1567,  1577,
    1576,  1591,  1601,  1600,  1616,  1615,  1643,  1647,  1655,  1659,
    1680,  1658,  1705,  1708,  1715,  1722,  1732,  1735,  1746,  1751,
    1762,  1767,  1778,  1782,  1791,  1802,  1801,  1815,  1814,  1825,
    1831,  1842,  1846,  1852,  1851,  1860,  1866,  1875,  1882,  1895,
    1903,  1894,  1927,  1934,  1926,  1961,  1964,  1971,  1984,  1983,
    2001,  2008,  2012,  2019,  2023,  2029,  2028,  2037,  2043,  2052,
    2059,  2072,  2071,  2096,  2095,  2115,  2122,  2125,  2131,  2134,
    2138,  2137,  2145,  2150,  2155,  2160,  2168,  2171,  2187,  2195,
    2207,  2186,  2243,  2251,  2264,  2242,  2321,  2324,  2323,  2338,
    2340,  2347,  2350,  2354,  2353,  2361,  2366,  2371,  2376,  2381,
    2386,  2394,  2397,  2409,  2408,  2420,  2424,  2428,  2436,  2435,
    2452,  2466,  2473,  2480,  2491,  2493,  2500,  2503,  2507,  2506,
    2514,  2519,  2524,  2529,  2537,  2540,  2552,  2551,  2563,  2567,
    2571,  2579,  2578,  2595,  2609,  2616,  2623,  2634,  2636,  2643,
    2646,  2650,  2649,  2657,  2662,  2670,  2673,  2685,  2693,  2684,
    2719,  2726,  2718,  2748,  2752,  2747,  2765,  2769,  2768,  2788,
    2792,  2800,  2817,  2825,  2830,  2845,  2860,  2875,  2890,  2905,
    2918,  2931,  2944,  2959,  2974,  2989,  3002,  3015,  3028,  3041,
    3054,  3067,  3082,  3097,  3112,  3125,  3140,  3155,  3170,  3183,
    3201,  3216,  3234,  3249,  3262,  3277,  3290,  3305,  3310,  3315,
    3330,  3345,  3358,  3373,  3388,  3403,  3418,  3433,  3448,  3452,
    3465,  3478,  3493,  3508,  3523,  3536,  3540,  3555,  3573,  3578,
    3577,  3598,  3597,  3619,  3627,  3640,  3656,  3674,  3690,  3701
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "T_NUMBER", "T_ID",
  "T_ARCHITECTURAL_TYPE", "T_ARCHI_ELEM_TYPES", "T_ELEM_TYPE",
  "T_BEHAVIOR", "T_INPUT_INTERACTIONS", "T_OUTPUT_INTERACTIONS", "T_UNI",
  "T_AND", "T_OR", "T_ARCHI_TOPOLOGY", "T_ARCHI_ELEM_INSTANCES",
  "T_ARCHI_INTERACTIONS", "T_ARCHI_ATTACHMENTS", "T_FROM", "T_TO",
  "T_BEHAV_VARIATIONS", "T_BEHAV_HIDINGS", "T_HIDE", "T_INTERNALS",
  "T_INTERACTIONS", "T_ALL", "T_BEHAV_RESTRICTIONS", "T_RESTRICT",
  "T_OBS_INTERNALS", "T_OBS_INTERACTIONS", "T_ALL_OBSERVABLES",
  "T_BEHAV_RENAMINGS", "T_RENAME", "T_AS", "T_FOR_ALL", "T_IN", "T_END",
  "T_CONST", "T_LOCAL", "T_STOP", "T_EXP", "T_INF", "T_CHOICE", "T_COND",
  "T_VOID", "T_PRIO", "T_RATE", "T_WEIGHT", "T_INTEGER", "T_REAL", "T_MOD",
  "T_MIN", "T_MAX", "T_ABS", "T_CEIL", "T_FLOOR", "T_POWER", "T_EPOWER",
  "T_LOGE", "T_LOG10", "T_SQRT", "T_SIN", "T_COS", "T_C_UNIFORM",
  "T_ERLANG", "T_GAMMA", "T_EXPONENTIAL", "T_WEIBULL", "T_BETA",
  "T_NORMAL", "T_PARETO", "T_B_PARETO", "T_D_UNIFORM", "T_BERNOULLI",
  "T_BINOMIAL", "T_POISSON", "T_NEG_BINOMIAL", "T_GEOMETRIC", "T_PASCAL",
  "T_BOOLEAN", "T_TRUE", "T_FALSE", "T_LIST", "T_LIST_CONS", "T_FIRST",
  "T_TAIL", "T_CONCAT", "T_INSERT", "T_REMOVE", "T_LENGTH", "T_ARRAY",
  "T_ARRAY_CONS", "T_READ", "T_WRITE", "T_RECORD", "T_RECORD_CONS",
  "T_GET", "T_PUT", "ASSIGN", "DOTDOT", "NE", "LE", "GE", "AND", "OR",
  "IMPL", "'!'", "'='", "'<'", "'>'", "'+'", "'-'", "'*'", "'/'", "'('",
  "')'", "','", "';'", "'.'", "'{'", "'}'", "'?'", "'_'", "':'", "'['",
  "']'", "$accept", "archi_type", "@1", "@2", "@3", "@4", "@5", "@6", "@7",
  "@8", "@9", "@10", "@11", "archi_header", "@12", "archi_par_list",
  "archi_par_list1", "archi_par", "@13", "archi_elem_types", "@14",
  "aet_def_list", "@15", "aet_def", "@16", "@17", "@18", "aet_par_list",
  "aet_par_list1", "aet_par", "behavior", "first_behav_def", "@19", "@20",
  "f_beh_par_list", "f_beh_par_list1", "first_behav_par", "@21",
  "behav_def_list", "behav_def_list1", "@22", "behav_def", "@23", "@24",
  "behav_par_list", "behav_par_list1", "behav_par", "behav_var_list",
  "behav_var_list1", "behav_var", "all_data_type", "var_data_type", "@25",
  "@26", "@27", "field_decl_list", "field_decl", "num_data_type", "term",
  "term1", "@28", "term2", "guard", "@29", "term2_list", "enclosed_term2",
  "action", "@30", "@31", "@32", "act_input_output", "act_input_list",
  "@33", "act_output_list", "@34", "act_rate", "@35", "act_prio_weight",
  "@36", "@37", "behav_apar_list", "behav_apar_list1", "interactions",
  "uni_interacts", "and_interacts", "or_interacts", "act_type_list", "@38",
  "@39", "archi_topology", "archi_elem_insts", "aei_decl_list", "@40",
  "aei_decl", "s_aei_decl", "@41", "@42", "i_aei_decl", "@43", "@44",
  "aei_par_list", "aei_par_list1", "@45", "archi_interacts",
  "a_interact_list", "a_interact_list1", "@46", "a_interact",
  "s_a_interact", "@47", "i_a_interact", "@48", "archi_attachs",
  "attach_list", "attach_list1", "@49", "attach", "s_attach", "@50", "@51",
  "@52", "i_attach", "@53", "@54", "@55", "behav_variations", "@56",
  "behav_hidings", "hiding_list", "@57", "hiding", "s_hiding", "@58",
  "i_hiding", "@59", "act_type_set_h", "behav_restricts", "restrict_list",
  "@60", "restriction", "s_restriction", "@61", "i_restriction", "@62",
  "act_type_set_r", "behav_renamings", "renaming_list", "@63", "renaming",
  "s_renaming", "@64", "@65", "i_renaming", "@66", "@67", "iteration",
  "@68", "@69", "add_iteration", "@70", "poss_aei_index", "aei_index",
  "expr", "@71", "@72", "expr_list", "expr_array", "expr_record",
  "struct_expr", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   708,   300,   301,   400,   401,   402,   403,   404,
     405,   406,   407,   408,   409,   410,   411,   412,   413,   414,
     415,   416,   417,   418,   419,   420,   421,   422,   423,   424,
     425,   426,   427,   428,   429,   430,   431,   432,   433,   434,
     436,   437,   438,   439,   440,   441,   442,   443,   444,   445,
     446,   447,   448,   449,   450,   451,   452,   453,   454,   455,
     456,   457,   458,   459,   460,   461,   462,   463,   464,   465,
     466,   467,   468,   469,   470,   471,   472,   473,   474,   475,
     476,   477,   478,   479,   480,   481,   482,   483,   484,   485,
     486,   487,   488,   489,   490,   491,   492,   493,   700,   701,
     702,   703,   704,   705,   706,   707,    33,    61,    60,    62,
      43,    45,    42,    47,    40,    41,    44,    59,    46,   123,
     125,    63,    95,    58,    91,    93
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint16 yyr1[] =
{
       0,   126,   127,   128,   127,   129,   127,   130,   127,   131,
     127,   132,   127,   133,   127,   134,   127,   135,   127,   136,
     127,   137,   127,   138,   127,   140,   139,   141,   141,   142,
     142,   144,   143,   145,   146,   145,   147,   147,   148,   147,
     147,   150,   151,   152,   149,   153,   153,   154,   154,   155,
     156,   158,   159,   157,   160,   160,   161,   161,   163,   162,
     164,   164,   165,   165,   166,   165,   165,   168,   169,   167,
     170,   170,   171,   171,   172,   173,   173,   174,   174,   175,
     176,   176,   177,   178,   179,   177,   177,   177,   177,   180,
     177,   177,   181,   181,   182,   183,   183,   183,   184,   184,
     184,   185,   186,   185,   187,   188,   189,   188,   190,   190,
     191,   193,   194,   195,   192,   196,   196,   196,   197,   198,
     197,   199,   200,   199,   202,   201,   201,   201,   203,   204,
     205,   203,   206,   206,   207,   207,   208,   208,   209,   209,
     210,   210,   211,   211,   212,   213,   212,   214,   212,   215,
     216,   217,   217,   218,   217,   217,   217,   219,   219,   221,
     222,   220,   224,   225,   223,   226,   226,   227,   228,   227,
     229,   230,   230,   231,   231,   232,   231,   231,   231,   233,
     233,   235,   234,   237,   236,   238,   239,   239,   240,   240,
     241,   240,   240,   240,   240,   240,   242,   242,   244,   245,
     246,   243,   248,   249,   250,   247,   251,   252,   251,   253,
     253,   254,   254,   255,   254,   254,   254,   254,   254,   254,
     254,   256,   256,   258,   257,   257,   257,   257,   260,   259,
     261,   261,   261,   261,   262,   262,   263,   263,   264,   263,
     263,   263,   263,   263,   265,   265,   267,   266,   266,   266,
     266,   269,   268,   270,   270,   270,   270,   271,   271,   272,
     272,   273,   272,   272,   272,   274,   274,   276,   277,   275,
     279,   280,   278,   282,   283,   281,   284,   285,   284,   286,
     286,   287,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   289,
     288,   290,   288,   288,   291,   291,   292,   293,   294,   294
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     0,     4,     0,     7,     0,     6,     0,
       8,     0,     7,     0,     6,     0,     5,     0,     7,     0,
       6,     0,     5,     0,     4,     0,     6,     1,     1,     1,
       3,     0,     6,     2,     0,     4,     1,     2,     0,     4,
       2,     0,     0,     0,    13,     1,     1,     1,     3,     3,
       3,     0,     0,    10,     1,     1,     1,     3,     0,     5,
       0,     1,     1,     2,     0,     4,     2,     0,     0,    11,
       1,     1,     1,     3,     2,     1,     1,     1,     3,     3,
       1,     1,     1,     0,     0,     8,     1,     1,     4,     0,
       7,     4,     1,     3,     2,     1,     1,     1,     1,     3,
       5,     1,     0,     5,     2,     0,     0,     6,     1,     2,
       2,     0,     0,     0,     9,     0,     4,     4,     1,     0,
       4,     1,     0,     4,     0,     5,     2,     2,     0,     0,
       0,     7,     0,     1,     1,     3,     1,     3,     0,     2,
       0,     2,     0,     2,     1,     0,     4,     0,     4,     4,
       2,     1,     3,     0,     4,     3,     2,     1,     2,     0,
       0,     9,     0,     0,     9,     0,     1,     1,     0,     4,
       2,     1,     1,     1,     3,     0,     4,     3,     2,     1,
       2,     0,     5,     0,     5,     2,     1,     1,     1,     3,
       0,     4,     3,     2,     3,     2,     1,     3,     0,     0,
       0,    13,     0,     0,     0,    13,     0,     0,     5,     0,
       2,     1,     3,     0,     4,     3,     2,     3,     2,     3,
       2,     1,     2,     0,     6,     2,     2,     2,     0,     6,
       1,     1,     1,     1,     0,     2,     1,     3,     0,     4,
       3,     2,     3,     2,     1,     2,     0,     6,     2,     2,
       2,     0,     6,     1,     1,     1,     1,     0,     2,     1,
       3,     0,     4,     3,     2,     1,     2,     0,     0,    10,
       0,     0,    10,     0,     0,     8,     0,     0,     3,     0,
       1,     3,     1,     1,     3,     3,     3,     3,     6,     4,
       4,     4,     6,     6,     6,     4,     4,     4,     4,     4,
       4,     6,     6,     6,     4,     6,     6,     6,     4,     8,
       6,     8,     6,     4,     6,     4,     6,     1,     1,     3,
       3,     2,     3,     3,     3,     3,     3,     3,     4,     4,
       4,     6,     6,     6,     4,     4,     6,     8,     4,     0,
       7,     0,     9,     3,     0,     1,     1,     1,     1,     3
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint16 yydefact[] =
{
       0,     0,     0,     0,     0,     3,     5,     7,     9,    11,
      13,    15,    17,    19,    21,    23,    25,     1,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   206,   209,   234,
     257,     0,     0,    34,     0,     0,    33,     0,     0,   206,
       4,     0,   206,     0,     0,     0,     0,     0,   206,   207,
       0,     0,   234,     0,   257,     0,     0,    24,     0,     0,
      38,    40,    41,    37,     0,     0,   206,     0,     0,   279,
       0,   150,     0,   157,     0,     0,     0,   279,   171,   170,
     172,     0,   179,     0,   206,     0,     0,   186,   185,   187,
       0,   196,   276,     0,   209,    16,     0,     0,   210,     0,
     221,     0,   257,     0,     0,   235,     0,   244,     0,     0,
       0,     0,   258,     0,   265,     0,    22,     0,    27,     0,
      28,    29,    35,     0,     0,     0,     2,     0,     8,   156,
     153,     0,   159,   280,   273,     0,     0,     0,   158,   206,
     178,   175,   181,     0,     0,     0,   180,     0,   193,   195,
     190,   279,     0,     0,   277,     0,    14,   234,   216,   218,
     220,   213,   279,   225,   226,   227,     0,     0,     0,   222,
       0,   241,   243,   238,   279,   248,   249,   250,     0,     0,
       0,   245,    20,   264,   261,   279,     0,     0,     0,   266,
      95,    96,    97,    82,    86,    87,     0,     0,     0,     0,
      80,    81,    26,     0,    39,     0,   149,     6,     0,   283,
     282,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     317,   318,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     155,   152,   162,     0,     0,     0,   177,   174,   183,    12,
       0,   198,   192,   194,   189,     0,     0,   197,   257,     0,
     223,   215,   217,   219,   212,     0,    18,     0,   246,   240,
     242,   237,     0,     0,   267,   263,   260,     0,     0,     0,
       0,     0,    31,    30,     0,    45,     0,    46,    47,   154,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   344,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   321,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   281,     0,     0,     0,
      10,   176,     0,     0,   191,     0,   278,   279,   208,   214,
       0,   228,   239,     0,   251,   262,     0,   270,    83,     0,
      89,     0,     0,    92,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   348,     0,   345,
       0,     0,     0,     0,     0,     0,     0,   346,     0,     0,
       0,   347,   339,   341,   343,   323,   325,   327,   319,   320,
     322,   324,   326,   284,   285,   286,   287,   160,   274,     0,
     182,     0,     0,   202,     0,     0,     0,     0,     0,     0,
       0,    88,     0,    94,    91,     0,     0,    49,     0,    42,
      48,     0,     0,     0,   289,   290,   291,     0,   295,   296,
     297,   298,   299,   300,     0,     0,     0,   304,     0,     0,
       0,   308,     0,     0,     0,     0,   313,     0,   315,     0,
       0,   328,   329,   330,     0,     0,     0,   334,   335,     0,
       0,   338,     0,     0,     0,     0,   163,   184,   199,     0,
     230,   231,   232,   233,   224,     0,   253,   254,   255,   256,
     247,     0,   268,     0,     0,     0,    93,    32,    51,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,   349,     0,     0,
       0,     0,     0,     0,     0,   165,     0,     0,     0,     0,
     229,   252,     0,   271,    84,     0,     0,     0,     0,    50,
      61,     0,   138,   288,   292,   293,   294,   301,   302,   303,
     305,   306,   307,     0,   310,     0,   312,   314,   316,   331,
     332,   333,   336,     0,     0,     0,     0,   166,   167,   275,
     165,     0,   203,     0,     0,     0,    90,     0,    66,    64,
      67,    63,     0,   136,    43,   140,     0,     0,     0,   340,
       0,   161,     0,     0,   279,     0,   279,     0,    85,    54,
       0,    55,    56,     0,     0,     0,     0,   144,   139,     0,
       0,   142,   309,   311,   337,     0,     0,   164,   200,     0,
     269,   279,     0,     0,    58,    65,     0,   147,     0,   138,
     141,     0,   137,   342,   169,     0,   279,   272,     0,    75,
       0,    76,    77,    57,     0,    70,     0,    71,    72,     0,
       0,     0,    44,   143,     0,   204,     0,    52,     0,     0,
       0,     0,    74,   148,   146,   201,     0,    79,     0,    78,
      59,     0,    73,     0,     0,    68,   205,    98,     0,     0,
      53,     0,     0,   105,   111,     0,     0,     0,     0,     0,
     115,   102,   101,    99,    69,     0,   105,     0,   108,   104,
       0,     0,   112,     0,   106,   110,   100,   109,     0,     0,
       0,   132,     0,     0,   121,   118,     0,     0,     0,   133,
     134,     0,   117,     0,     0,   116,     0,   128,   128,   113,
     103,     0,   107,     0,     0,     0,     0,   126,   127,     0,
     135,   123,   120,   124,   129,   114,     0,     0,   125,     0,
     130,     0,   131
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     3,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,     4,    32,   119,   120,   121,   394,    20,
      59,    36,   123,    37,   124,   550,   659,   306,   307,   308,
     479,   549,   586,   718,   650,   651,   652,   694,   589,   590,
     654,   591,   655,   732,   696,   697,   698,   690,   691,   692,
     199,   200,   470,   625,   472,   392,   393,   201,   730,   743,
     753,   738,   739,   762,   747,   748,   731,   740,   760,   789,
     752,   766,   774,   763,   773,   779,   796,   787,   797,   801,
     768,   769,   634,   635,   661,   682,   658,   678,   700,    39,
      44,    71,   208,    72,    73,   258,   524,   138,   369,   577,
     616,   617,   642,    46,    79,    80,   264,    81,    82,   265,
     146,   373,    48,    88,    89,   270,    90,    91,   375,   578,
     685,   277,   529,   645,   716,    50,    94,    52,    98,   279,
      99,   100,   380,   169,   465,   534,    54,   105,   287,   106,
     107,   383,   181,   467,   540,    56,   112,   293,   113,   114,
     386,   582,   189,   469,   624,    74,   259,   525,   155,   275,
     132,   133,   427,   522,   523,   428,   436,   440,   429
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -589
static const yytype_int16 yypact[] =
{
      90,   289,    49,    64,   253,  -589,  -589,  -589,  -589,  -589,
    -589,  -589,  -589,  -589,  -589,  -589,  -589,  -589,    59,   155,
      60,    90,   253,    60,    84,    92,   103,   131,   139,   219,
     215,   221,   144,  -589,    89,   275,  -589,   284,    84,   131,
    -589,    60,   131,   174,    92,   110,   103,    53,   131,  -589,
     251,    83,   219,   136,   215,   218,   254,  -589,   128,   155,
    -589,  -589,  -589,  -589,    92,   264,   131,   266,    23,   184,
     308,  -589,    17,  -589,   309,   103,    21,   184,  -589,  -589,
    -589,    13,  -589,   310,   131,     4,   315,  -589,  -589,  -589,
      12,  -589,   312,   291,   139,  -589,    29,    77,  -589,     5,
    -589,   304,   215,    26,   288,  -589,    14,  -589,   306,   298,
     -13,   338,  -589,    15,  -589,   311,  -589,   217,  -589,   231,
    -589,   232,  -589,   155,   233,   103,  -589,   319,  -589,  -589,
    -589,   619,  -589,  -589,  -589,   341,   174,   184,  -589,   131,
    -589,  -589,  -589,   342,   238,   184,  -589,   322,  -589,  -589,
    -589,   184,   135,   250,  -589,   343,  -589,   219,  -589,  -589,
    -589,  -589,   184,  -589,  -589,  -589,   318,    83,   356,  -589,
     326,  -589,  -589,  -589,   184,  -589,  -589,  -589,   224,   136,
     362,  -589,  -589,  -589,  -589,   184,   339,   218,   373,  -589,
    -589,  -589,  -589,   265,  -589,  -589,   267,   268,   272,   376,
    -589,  -589,  -589,   350,  -589,   178,  -589,  -589,   174,  -589,
    -589,   281,   282,   294,   295,   296,   297,   300,   301,   303,
     307,   316,   332,   333,   349,   351,   352,   353,   357,   359,
     374,   384,   386,   387,   388,   394,   404,   411,   412,   428,
    -589,  -589,   429,   438,   445,   446,   457,   463,   473,   480,
     490,   492,   493,   494,   495,   619,   619,   228,   299,   369,
    -589,  -589,  -589,   371,   238,   287,  -589,  -589,  -589,  -589,
     250,  -589,  -589,  -589,  -589,   378,   414,  -589,   215,    83,
    -589,  -589,  -589,  -589,  -589,   184,  -589,   136,  -589,  -589,
    -589,  -589,   184,   218,  -589,  -589,  -589,   184,   619,    -2,
     619,    -2,  -589,  -589,   217,  -589,   305,  -589,   323,  -589,
     619,   619,   619,   619,   619,   619,   619,   619,   619,   619,
     619,   619,   619,   619,   619,   619,   619,   619,   619,   619,
     619,   619,   619,   619,   619,   619,   619,   619,   619,   619,
     619,   619,   619,   619,   619,   619,   619,   619,   619,   619,
     419,   436,   368,   454,   619,   619,   619,   619,   619,   619,
     619,   619,   619,   619,   619,   619,  -589,   449,   619,   488,
    -589,  -589,   458,   379,  -589,   399,  -589,   184,  -589,  -589,
     416,  -589,  -589,   433,  -589,  -589,   468,  -589,  1574,   497,
    1574,   599,    61,  -589,   515,   611,   608,   580,   290,   325,
     348,   950,   966,   982,   383,   998,  1014,  1030,  1046,  1062,
    1078,   403,   420,   437,  1094,   472,   489,   531,  1110,   627,
     644,   661,   678,  1126,   695,  1142,   712,   729,   503,  -589,
    1158,  1174,   746,   763,   780,  1190,   504,  -589,   797,   814,
     505,  -589,  -589,  -589,  -589,  1588,  1588,  1588,   368,   368,
    1588,  1588,  1588,   108,   108,  -589,  -589,  -589,  1574,   617,
    -589,   620,   621,  -589,   252,   508,   293,   510,   625,   518,
     538,  -589,   529,  -589,  -589,    -2,   619,  -589,   642,  -589,
    -589,   619,   619,   619,  -589,  -589,  -589,   619,  -589,  -589,
    -589,  -589,  -589,  -589,   619,   619,   619,  -589,   619,   619,
     619,  -589,   619,   619,   619,   619,  -589,   619,  -589,   619,
     619,  -589,  -589,  -589,   619,   619,   619,  -589,  -589,   619,
     619,  -589,   532,   533,   537,   554,  -589,  -589,  -589,   539,
    -589,  -589,  -589,  -589,  -589,   252,  -589,  -589,  -589,  -589,
    -589,   293,  -589,   652,   619,    -2,  -589,  1574,  -589,    19,
     415,  1206,  1222,  1238,  1254,  1270,  1286,  1302,  1318,  1334,
    1350,   831,  1366,   848,  1382,  1398,  1414,  -589,  1430,  1446,
    1462,  1478,   865,   619,   619,   619,   619,   544,   640,   656,
    -589,  -589,   628,  -589,  1574,   547,   549,    -1,   660,  -589,
    -589,    25,    45,  -589,  -589,  -589,  -589,  -589,  -589,  -589,
    -589,  -589,  -589,   619,  -589,   619,  -589,  -589,  -589,  -589,
    -589,  -589,  -589,   619,  1494,   882,   550,  -589,   899,  1574,
     619,   662,  -589,   663,   665,   586,  -589,   192,  -589,  -589,
    -589,  -589,   279,  -589,  -589,   697,  1510,  1526,  1542,  -589,
     619,  -589,   597,   602,   184,   699,   184,   715,  -589,  -589,
     603,  -589,   605,   718,    16,   609,   607,   615,  -589,   716,
     279,   728,  -589,  -589,  -589,  1558,   619,  -589,  -589,   738,
    -589,   184,   130,    -2,  -589,  -589,   199,  -589,   632,    45,
    -589,   279,  -589,  -589,  -589,   641,   184,  -589,    -2,  -589,
     635,  -589,   650,  -589,   669,  -589,   658,  -589,   667,   754,
     279,   279,  -589,  -589,   772,  -589,   788,  -589,   755,   619,
     130,    -2,  -589,  -589,  -589,  -589,   666,  -589,   693,  -589,
    1574,   686,  -589,   805,    40,  -589,  -589,  -589,   691,   813,
    -589,   700,   719,   784,  -589,    31,    40,   720,   727,    40,
      52,  -589,  -589,  -589,  -589,   619,   784,   724,   727,  -589,
     721,   737,  -589,   747,  1574,  -589,  -589,  -589,   619,   856,
     736,   619,   753,   762,   916,   769,   771,    28,   779,  -589,
     933,   764,  -589,   786,   787,  -589,   781,   798,   798,  -589,
    -589,   619,  -589,   619,   856,   619,   619,  -589,  -589,   802,
    -589,  -589,  -589,  1574,  1574,  -589,   804,   812,  -589,   619,
    1574,   821,  -589
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -589,   857,  -589,  -589,  -589,  -589,  -589,  -589,  -589,  -589,
    -589,  -589,  -589,  -589,  -589,  -589,   717,  -589,  -589,   907,
    -589,    24,  -589,  -589,  -589,  -589,  -589,  -589,   540,  -589,
    -589,  -589,  -589,  -589,  -589,   273,  -589,  -589,  -589,  -548,
    -589,  -589,  -589,  -589,  -589,   234,  -589,   243,   246,  -589,
     659,  -257,  -589,  -589,  -589,  -589,   487,  -589,  -466,  -589,
    -589,   225,  -589,  -589,   222,  -589,  -589,  -589,  -589,  -589,
    -589,   195,  -589,   197,  -589,  -589,  -589,   209,  -589,  -589,
    -589,   207,   317,  -589,  -589,  -589,  -588,  -589,  -589,    44,
     959,   -73,  -589,  -589,  -589,  -589,  -589,  -589,  -589,  -589,
     385,   347,  -589,    46,  -589,  -141,  -589,  -589,  -589,  -589,
    -589,  -589,    32,  -589,  -144,  -589,  -589,  -589,  -589,  -589,
    -589,  -589,  -589,  -589,  -589,    10,  -589,   910,  -160,  -589,
    -589,  -589,  -589,  -589,  -589,   479,   -42,  -142,  -589,  -589,
    -589,  -589,  -589,  -589,   481,   -43,  -165,  -589,  -589,  -589,
    -589,  -589,  -589,  -589,  -589,   -26,  -589,  -589,  -589,  -589,
     -76,  -133,  -131,  -589,  -589,  -589,  -589,  -589,  -344
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -260
static const yytype_int16 yytable[] =
{
     257,   142,   437,   267,   262,   441,   166,   284,   628,   274,
     102,   109,   268,   152,   143,   178,   186,   587,   135,    83,
     587,    92,   296,   183,   148,   101,   587,   108,   -60,   115,
    -173,  -211,  -188,  -151,   -62,   741,  -211,   291,   140,   129,
     149,  -211,   389,   631,   391,  -236,   193,   194,  -188,    65,
    -236,  -259,    67,    16,    85,   158,   632,   171,    93,   170,
     159,    63,   172,   261,    17,   160,    33,    42,   776,   777,
     727,    86,   680,   728,    38,   271,   127,   195,    84,   727,
     196,   162,   728,   122,    96,    66,   280,    70,   197,   633,
      75,     1,   198,   703,   147,     2,    60,    87,   288,    43,
     163,   164,   165,    61,   184,    97,   675,   139,    45,   294,
     125,    76,   713,   714,    77,   278,   629,    70,    83,   379,
      47,   150,   167,   371,   352,   353,   374,    92,   385,   153,
     144,   179,   187,   588,   136,   309,   588,   103,   141,   729,
     130,   101,   588,   173,    70,   382,   161,   204,   729,   263,
     778,    49,   381,   108,    78,   272,    34,   206,   750,   384,
      51,   115,    35,   104,   387,   117,   567,   388,   688,   390,
      70,   273,   118,   751,   689,    68,   474,   475,    69,   398,
     399,   400,   401,   402,   403,   404,   405,   406,   407,   408,
     409,   410,   411,   412,   413,   414,   415,   416,   417,   418,
     419,   420,   421,   422,   423,   424,   425,   426,    70,   430,
     431,   432,   433,   434,   435,   304,   438,   439,   391,   110,
     364,   365,   305,   445,   446,   447,   448,   449,   450,   451,
     452,   453,   454,   455,   456,   378,   649,   458,    83,    76,
     193,   194,    77,   695,    92,    53,    55,   193,   194,   376,
     111,    85,    70,   101,    18,   289,   530,    57,    58,    19,
     290,   108,   190,   191,   192,   193,   194,   115,    86,   742,
     744,   195,    70,   749,   196,   531,   532,   533,   195,    62,
     656,   196,   197,   657,    70,    34,   198,    95,   585,   197,
     116,    35,   174,   198,     5,     6,   195,   536,   -36,   196,
     126,   463,   128,     7,     8,     9,    10,   197,   131,    11,
      12,   198,   134,   137,   145,    13,   175,   176,   177,   151,
      14,   537,   538,   539,   154,    15,   168,   156,   354,   355,
     356,   357,   358,   180,   182,   359,   360,   361,   362,   363,
     364,   365,   185,   188,   281,   547,   202,   205,   203,   282,
     551,   552,   553,   366,   283,   207,   554,   260,   269,   266,
     285,   276,   286,   555,   556,   557,   292,   558,   559,   560,
     653,   561,   562,   563,   564,   295,   565,   297,   566,   298,
     302,   299,   300,   568,   569,   570,   301,   117,   571,   572,
     354,   355,   356,   357,   358,   310,   311,   359,   360,   361,
     362,   363,   364,   365,   368,   372,   481,   370,   312,   313,
     314,   315,    70,   584,   316,   317,   653,   318,   377,   699,
     396,   319,   367,   442,   592,   354,   355,   356,   357,   358,
     320,   706,   359,   360,   361,   362,   363,   364,   365,   397,
     443,   482,   614,   615,   618,   619,   321,   322,   354,   355,
     356,   357,   358,   457,   699,   359,   360,   361,   362,   363,
     364,   365,   460,   323,   483,   324,   325,   326,   354,   355,
     356,   327,   636,   328,   637,   359,   360,   361,   362,   363,
     364,   365,   638,   354,   355,   356,   357,   358,   329,   618,
     359,   360,   361,   362,   363,   364,   365,   461,   330,   487,
     331,   332,   333,   354,   355,   356,   357,   358,   334,   665,
     359,   360,   361,   362,   363,   364,   365,   462,   335,   494,
     354,   355,   356,   357,   358,   336,   337,   359,   360,   361,
     362,   363,   364,   365,   464,   618,   495,   354,   355,   356,
     357,   358,   338,   339,   359,   360,   361,   362,   363,   364,
     365,   466,   340,   496,   354,   355,   356,   357,   358,   341,
     342,   359,   360,   361,   362,   363,   364,   365,   668,   444,
     670,   343,   354,   355,   356,   357,   358,   344,   720,   359,
     360,   361,   362,   363,   364,   365,   468,   345,   498,   354,
     355,   356,   357,   358,   346,   687,   359,   360,   361,   362,
     363,   364,   365,   473,   347,   499,   348,   349,   350,   351,
     705,   459,   471,   476,   754,   477,   478,   304,   511,   518,
     521,   526,   209,   210,   527,   528,   535,   764,   541,   542,
     770,   354,   355,   356,   357,   358,   543,   544,   359,   360,
     361,   362,   363,   364,   365,   545,   548,   500,   573,   574,
     770,   575,   764,   576,   793,   794,   583,   579,   620,   621,
     622,   623,   626,   627,   630,   641,   644,   646,   800,   211,
     212,   213,   214,   215,   216,   217,   218,   219,   220,   221,
     222,   223,   224,   225,   226,   227,   228,   229,   230,   231,
     232,   233,   234,   235,   236,   237,   238,   239,   647,   240,
     241,   648,   242,   243,   244,   245,   246,   247,   248,   660,
     249,   250,   251,   666,   252,   253,   254,   667,   669,   671,
     672,   673,   674,   676,   677,   255,   679,   354,   355,   356,
     357,   358,  -145,   256,   359,   360,   361,   362,   363,   364,
     365,   681,   686,   502,   354,   355,   356,   357,   358,   701,
     707,   359,   360,   361,   362,   363,   364,   365,   712,   704,
     503,   354,   355,   356,   357,   358,   708,   709,   359,   360,
     361,   362,   363,   364,   365,   710,   715,   504,   354,   355,
     356,   357,   358,   711,   723,   359,   360,   361,   362,   363,
     364,   365,   717,   688,   505,   354,   355,   356,   357,   358,
     724,   725,   359,   360,   361,   362,   363,   364,   365,   726,
     733,   507,   354,   355,   356,   357,   358,   734,   735,   359,
     360,   361,   362,   363,   364,   365,   736,   737,   509,   354,
     355,   356,   357,   358,   745,   758,   359,   360,   361,   362,
     363,   364,   365,   746,   756,   510,   354,   355,   356,   357,
     358,   759,   767,   359,   360,   361,   362,   363,   364,   365,
     765,   761,   514,   354,   355,   356,   357,   358,   771,   782,
     359,   360,   361,   362,   363,   364,   365,   772,    40,   515,
     354,   355,   356,   357,   358,  -119,   775,   359,   360,   361,
     362,   363,   364,   365,   780,   785,   516,   354,   355,   356,
     357,   358,   783,   784,   359,   360,   361,   362,   363,   364,
     365,   795,   786,   519,   354,   355,   356,   357,   358,   798,
     303,   359,   360,   361,   362,   363,   364,   365,   799,    41,
     520,   354,   355,   356,   357,   358,   802,   480,   359,   360,
     361,   362,   363,   364,   365,   722,   693,   603,   354,   355,
     356,   357,   358,   721,   719,   359,   360,   361,   362,   363,
     364,   365,   546,   395,   605,   354,   355,   356,   357,   358,
     757,   755,   359,   360,   361,   362,   363,   364,   365,   792,
     791,   613,   354,   355,   356,   357,   358,   788,   790,   359,
     360,   361,   362,   363,   364,   365,   702,    64,   640,   354,
     355,   356,   357,   358,   157,   643,   359,   360,   361,   362,
     363,   364,   365,   684,   580,  -168,   354,   355,   356,   357,
     358,     0,   581,   359,   360,   361,   362,   363,   364,   365,
       0,     0,  -122,   354,   355,   356,   357,   358,     0,     0,
     359,   360,   361,   362,   363,   364,   365,     0,     0,   781,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   484,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   485,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   486,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   488,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   489,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   490,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   491,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   492,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   493,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   497,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   501,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   506,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   508,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   512,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   513,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   517,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   593,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   594,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   595,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   596,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   597,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   598,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   599,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   600,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   601,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   602,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   604,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   606,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   607,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   608,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   609,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   610,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   611,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   612,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,     0,   639,
     354,   355,   356,   357,   358,     0,     0,   359,   360,   361,
     362,   363,   364,   365,     0,   662,   354,   355,   356,   357,
     358,     0,     0,   359,   360,   361,   362,   363,   364,   365,
       0,   663,   354,   355,   356,   357,   358,     0,     0,   359,
     360,   361,   362,   363,   364,   365,     0,   664,   354,   355,
     356,   357,   358,     0,     0,   359,   360,   361,   362,   363,
     364,   365,     0,   683,   354,   355,   356,   357,   358,     0,
       0,   359,   360,   361,   362,   363,   364,   365,  -260,  -260,
    -260,     0,     0,     0,     0,  -260,  -260,  -260,   362,   363,
     364,   365
};

static const yytype_int16 yycheck[] =
{
     131,    77,   346,   144,   137,   349,     1,   167,     9,   153,
      52,    54,   145,     1,     1,     1,     1,     1,     1,    45,
       1,    47,   187,    36,    20,    51,     1,    53,     9,    55,
      17,    26,    20,    16,     9,     4,    31,   179,    17,    16,
      36,    36,   299,   591,   301,    31,    48,    49,    36,    39,
      36,    36,    42,     4,     1,    26,    11,    31,    48,   102,
      31,    37,    36,   136,     0,    36,     7,    23,    40,    41,
      39,    18,   660,    42,    14,   151,    66,    79,    46,    39,
      82,     4,    42,    59,     1,    41,   162,    34,    90,    44,
      44,     1,    94,   681,    84,     5,     7,    44,   174,    15,
      23,    24,    25,    14,   117,    22,   654,    75,    16,   185,
      64,     1,   700,   701,     4,   157,   117,    34,   144,   279,
      17,   117,   117,   264,   255,   256,   270,   153,   293,   117,
     117,   117,   117,   117,   117,   208,   117,     1,   117,   108,
     117,   167,   117,   117,    34,   287,   117,   123,   108,   139,
     122,    20,   285,   179,    44,    20,     1,   125,   106,   292,
      21,   187,     7,    27,   297,    37,   510,   298,    38,   300,
      34,    36,    44,   121,    44,     1,   115,   116,     4,   310,
     311,   312,   313,   314,   315,   316,   317,   318,   319,   320,
     321,   322,   323,   324,   325,   326,   327,   328,   329,   330,
     331,   332,   333,   334,   335,   336,   337,   338,    34,   340,
     341,   342,   343,   344,   345,    37,   347,   348,   475,     1,
     112,   113,    44,   354,   355,   356,   357,   358,   359,   360,
     361,   362,   363,   364,   365,   278,    44,   368,   264,     1,
      48,    49,     4,    44,   270,    26,    31,    48,    49,   275,
      32,     1,    34,   279,     1,    31,     4,    36,   114,     6,
      36,   287,    45,    46,    47,    48,    49,   293,    18,   735,
     736,    79,    34,   739,    82,    23,    24,    25,    79,     4,
       1,    82,    90,     4,    34,     1,    94,    36,   545,    90,
      36,     7,     4,    94,     5,     6,    79,     4,    14,    82,
      36,   377,    36,    14,    15,    16,    17,    90,   124,    20,
      21,    94,     4,     4,     4,    26,    28,    29,    30,     4,
      31,    28,    29,    30,    12,    36,    22,    36,   100,   101,
     102,   103,   104,    27,    36,   107,   108,   109,   110,   111,
     112,   113,     4,    32,    26,   476,   115,   114,   116,    31,
     481,   482,   483,   125,    36,    36,   487,    16,    36,    17,
       4,    18,    36,   494,   495,   496,     4,   498,   499,   500,
     627,   502,   503,   504,   505,    36,   507,     4,   509,   114,
       4,   114,   114,   514,   515,   516,   114,    37,   519,   520,
     100,   101,   102,   103,   104,   114,   114,   107,   108,   109,
     110,   111,   112,   113,    35,   118,   116,    36,   114,   114,
     114,   114,    34,   544,   114,   114,   673,   114,     4,   676,
     115,   114,   123,     4,     9,   100,   101,   102,   103,   104,
     114,   688,   107,   108,   109,   110,   111,   112,   113,   116,
       4,   116,   573,   574,   575,   576,   114,   114,   100,   101,
     102,   103,   104,     4,   711,   107,   108,   109,   110,   111,
     112,   113,     4,   114,   116,   114,   114,   114,   100,   101,
     102,   114,   603,   114,   605,   107,   108,   109,   110,   111,
     112,   113,   613,   100,   101,   102,   103,   104,   114,   620,
     107,   108,   109,   110,   111,   112,   113,   118,   114,   116,
     114,   114,   114,   100,   101,   102,   103,   104,   114,   640,
     107,   108,   109,   110,   111,   112,   113,   118,   114,   116,
     100,   101,   102,   103,   104,   114,   114,   107,   108,   109,
     110,   111,   112,   113,   118,   666,   116,   100,   101,   102,
     103,   104,   114,   114,   107,   108,   109,   110,   111,   112,
     113,   118,   114,   116,   100,   101,   102,   103,   104,   114,
     114,   107,   108,   109,   110,   111,   112,   113,   644,   115,
     646,   114,   100,   101,   102,   103,   104,   114,   709,   107,
     108,   109,   110,   111,   112,   113,   118,   114,   116,   100,
     101,   102,   103,   104,   114,   671,   107,   108,   109,   110,
     111,   112,   113,     4,   114,   116,   114,   114,   114,   114,
     686,   123,   115,    98,   745,     4,     8,    37,   115,   115,
     115,     4,     3,     4,     4,     4,   118,   758,   118,     4,
     761,   100,   101,   102,   103,   104,   118,    99,   107,   108,
     109,   110,   111,   112,   113,   116,     4,   116,   116,   116,
     781,   114,   783,    99,   785,   786,     4,   118,   114,    19,
       4,    33,   115,   114,     4,   115,     4,     4,   799,    50,
      51,    52,    53,    54,    55,    56,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    73,    74,    75,    76,    77,    78,    33,    80,
      81,   115,    83,    84,    85,    86,    87,    88,    89,    12,
      91,    92,    93,   116,    95,    96,    97,   115,    19,     4,
     117,   116,     4,   114,   117,   106,    10,   100,   101,   102,
     103,   104,   117,   114,   107,   108,   109,   110,   111,   112,
     113,    13,     4,   116,   100,   101,   102,   103,   104,   117,
     115,   107,   108,   109,   110,   111,   112,   113,     4,   118,
     116,   100,   101,   102,   103,   104,   116,    98,   107,   108,
     109,   110,   111,   112,   113,   117,     4,   116,   100,   101,
     102,   103,   104,   116,   118,   107,   108,   109,   110,   111,
     112,   113,     4,    38,   116,   100,   101,   102,   103,   104,
     107,   115,   107,   108,   109,   110,   111,   112,   113,     4,
     119,   116,   100,   101,   102,   103,   104,     4,   118,   107,
     108,   109,   110,   111,   112,   113,   107,    43,   116,   100,
     101,   102,   103,   104,   114,   114,   107,   108,   109,   110,
     111,   112,   113,   116,   120,   116,   100,   101,   102,   103,
     104,   114,   116,   107,   108,   109,   110,   111,   112,   113,
       4,   114,   116,   100,   101,   102,   103,   104,   115,   105,
     107,   108,   109,   110,   111,   112,   113,   115,    21,   116,
     100,   101,   102,   103,   104,   116,   115,   107,   108,   109,
     110,   111,   112,   113,   115,   114,   116,   100,   101,   102,
     103,   104,   116,   116,   107,   108,   109,   110,   111,   112,
     113,   109,   114,   116,   100,   101,   102,   103,   104,   115,
     203,   107,   108,   109,   110,   111,   112,   113,   116,    22,
     116,   100,   101,   102,   103,   104,   115,   397,   107,   108,
     109,   110,   111,   112,   113,   711,   673,   116,   100,   101,
     102,   103,   104,   710,   708,   107,   108,   109,   110,   111,
     112,   113,   475,   304,   116,   100,   101,   102,   103,   104,
     748,   746,   107,   108,   109,   110,   111,   112,   113,   784,
     783,   116,   100,   101,   102,   103,   104,   778,   781,   107,
     108,   109,   110,   111,   112,   113,   679,    38,   116,   100,
     101,   102,   103,   104,    94,   620,   107,   108,   109,   110,
     111,   112,   113,   666,   535,   116,   100,   101,   102,   103,
     104,    -1,   541,   107,   108,   109,   110,   111,   112,   113,
      -1,    -1,   116,   100,   101,   102,   103,   104,    -1,    -1,
     107,   108,   109,   110,   111,   112,   113,    -1,    -1,   116,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,    -1,   115,
     100,   101,   102,   103,   104,    -1,    -1,   107,   108,   109,
     110,   111,   112,   113,    -1,   115,   100,   101,   102,   103,
     104,    -1,    -1,   107,   108,   109,   110,   111,   112,   113,
      -1,   115,   100,   101,   102,   103,   104,    -1,    -1,   107,
     108,   109,   110,   111,   112,   113,    -1,   115,   100,   101,
     102,   103,   104,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113,    -1,   115,   100,   101,   102,   103,   104,    -1,
      -1,   107,   108,   109,   110,   111,   112,   113,   100,   101,
     102,    -1,    -1,    -1,    -1,   107,   108,   109,   110,   111,
     112,   113
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint16 yystos[] =
{
       0,     1,     5,   127,   139,     5,     6,    14,    15,    16,
      17,    20,    21,    26,    31,    36,     4,     0,     1,     6,
     145,   128,   129,   130,   131,   132,   133,   134,   135,   136,
     137,   138,   140,     7,     1,     7,   147,   149,    14,   215,
     127,   145,   215,    15,   216,    16,   229,    17,   238,    20,
     251,    21,   253,    26,   262,    31,   271,    36,   114,   146,
       7,    14,     4,   147,   216,   251,   215,   251,     1,     4,
      34,   217,   219,   220,   281,   229,     1,     4,    44,   230,
     231,   233,   234,   281,   238,     1,    18,    44,   239,   240,
     242,   243,   281,   251,   252,    36,     1,    22,   254,   256,
     257,   281,   262,     1,    27,   263,   265,   266,   281,   271,
       1,    32,   272,   274,   275,   281,    36,    37,    44,   141,
     142,   143,   147,   148,   150,   229,    36,   251,    36,    16,
     117,   124,   286,   287,     4,     1,   117,     4,   223,   238,
      17,   117,   286,     1,   117,     4,   236,   251,    20,    36,
     117,     4,     1,   117,    12,   284,    36,   253,    26,    31,
      36,   117,     4,    23,    24,    25,     1,   117,    22,   259,
     271,    31,    36,   117,     4,    28,    29,    30,     1,   117,
      27,   268,    36,    36,   117,     4,     1,   117,    32,   278,
      45,    46,    47,    48,    49,    79,    82,    90,    94,   176,
     177,   183,   115,   116,   147,   114,   238,    36,   218,     3,
       4,    50,    51,    52,    53,    54,    55,    56,    57,    58,
      59,    60,    61,    62,    63,    64,    65,    66,    67,    68,
      69,    70,    71,    72,    73,    74,    75,    76,    77,    78,
      80,    81,    83,    84,    85,    86,    87,    88,    89,    91,
      92,    93,    95,    96,    97,   106,   114,   288,   221,   282,
      16,   217,   287,   251,   232,   235,    17,   231,   287,    36,
     241,   286,    20,    36,   240,   285,    18,   247,   262,   255,
     286,    26,    31,    36,   254,     4,    36,   264,   286,    31,
      36,   263,     4,   273,   286,    36,   272,     4,   114,   114,
     114,   114,     4,   142,    37,    44,   153,   154,   155,   217,
     114,   114,   114,   114,   114,   114,   114,   114,   114,   114,
     114,   114,   114,   114,   114,   114,   114,   114,   114,   114,
     114,   114,   114,   114,   114,   114,   114,   114,   114,   114,
     114,   114,   114,   114,   114,   114,   114,   114,   114,   114,
     114,   114,   288,   288,   100,   101,   102,   103,   104,   107,
     108,   109,   110,   111,   112,   113,   125,   123,    35,   224,
      36,   231,   118,   237,   240,   244,   281,     4,   271,   254,
     258,   287,   263,   267,   287,   272,   276,   287,   288,   177,
     288,   177,   181,   182,   144,   176,   115,   116,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   288,   291,   294,
     288,   288,   288,   288,   288,   288,   292,   294,   288,   288,
     293,   294,     4,     4,   115,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,     4,   288,   123,
       4,   118,   118,   286,   118,   260,   118,   269,   118,   279,
     178,   115,   180,     4,   115,   116,    98,     4,     8,   156,
     154,   116,   116,   116,   115,   115,   115,   116,   115,   115,
     115,   115,   115,   115,   116,   116,   116,   115,   116,   116,
     116,   115,   116,   116,   116,   116,   115,   116,   115,   116,
     116,   115,   115,   115,   116,   116,   116,   115,   115,   116,
     116,   115,   289,   290,   222,   283,     4,     4,     4,   248,
       4,    23,    24,    25,   261,   118,     4,    28,    29,    30,
     270,   118,     4,   118,    99,   116,   182,   288,     4,   157,
     151,   288,   288,   288,   288,   288,   288,   288,   288,   288,
     288,   288,   288,   288,   288,   288,   288,   294,   288,   288,
     288,   288,   288,   116,   116,   114,    99,   225,   245,   118,
     261,   270,   277,     4,   288,   177,   158,     1,   117,   164,
     165,   167,     9,   115,   115,   115,   115,   115,   115,   115,
     115,   115,   115,   116,   115,   116,   115,   115,   115,   115,
     115,   115,   115,   116,   288,   288,   226,   227,   288,   288,
     114,    19,     4,    33,   280,   179,   115,   114,     9,   117,
       4,   165,    11,    44,   208,   209,   288,   288,   288,   115,
     116,   115,   228,   226,     4,   249,     4,    33,   115,    44,
     160,   161,   162,   177,   166,   168,     1,     4,   212,   152,
      12,   210,   115,   115,   115,   288,   116,   115,   286,    19,
     286,     4,   117,   116,     4,   165,   114,   117,   213,    10,
     212,    13,   211,   115,   227,   246,     4,   286,    38,    44,
     173,   174,   175,   161,   163,    44,   170,   171,   172,   177,
     214,   117,   208,   212,   118,   286,   177,   115,   116,    98,
     117,   116,     4,   212,   212,     4,   250,     4,   159,   174,
     288,   173,   171,   118,   107,   115,     4,    39,    42,   108,
     184,   192,   169,   119,     4,   118,   107,    43,   187,   188,
     193,     4,   184,   185,   184,   114,   116,   190,   191,   184,
     106,   121,   196,   186,   288,   187,   120,   190,   114,   114,
     194,   114,   189,   199,   288,     4,   197,   116,   206,   207,
     288,   115,   115,   200,   198,   115,    40,    41,   122,   201,
     115,   116,   105,   116,   116,   114,   114,   203,   203,   195,
     207,   199,   197,   288,   288,   109,   202,   204,   115,   116,
     288,   205,   115
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  */

#define YYFAIL		goto yyerrlab

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      yytoken = YYTRANSLATE (yychar);				\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
#  define YY_LOCATION_PRINT(File, Loc)			\
     fprintf (File, "%d.%d-%d.%d",			\
	      (Loc).first_line, (Loc).first_column,	\
	      (Loc).last_line,  (Loc).last_column)
# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *bottom, yytype_int16 *top)
#else
static void
yy_stack_print (bottom, top)
    yytype_int16 *bottom;
    yytype_int16 *top;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; bottom <= top; ++bottom)
    YYFPRINTF (stderr, " %d", *bottom);
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      fprintf (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      fprintf (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif



#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into YYRESULT an error message about the unexpected token
   YYCHAR while in state YYSTATE.  Return the number of bytes copied,
   including the terminating null byte.  If YYRESULT is null, do not
   copy anything; just return the number of bytes that would be
   copied.  As a special case, return 0 if an ordinary "syntax error"
   message will do.  Return YYSIZE_MAXIMUM if overflow occurs during
   size calculation.  */
static YYSIZE_T
yysyntax_error (char *yyresult, int yystate, int yychar)
{
  int yyn = yypact[yystate];

  if (! (YYPACT_NINF < yyn && yyn <= YYLAST))
    return 0;
  else
    {
      int yytype = YYTRANSLATE (yychar);
      YYSIZE_T yysize0 = yytnamerr (0, yytname[yytype]);
      YYSIZE_T yysize = yysize0;
      YYSIZE_T yysize1;
      int yysize_overflow = 0;
      enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
      char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
      int yyx;

# if 0
      /* This is so xgettext sees the translatable formats that are
	 constructed on the fly.  */
      YY_("syntax error, unexpected %s");
      YY_("syntax error, unexpected %s, expecting %s");
      YY_("syntax error, unexpected %s, expecting %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s");
      YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s");
# endif
      char *yyfmt;
      char const *yyf;
      static char const yyunexpected[] = "syntax error, unexpected %s";
      static char const yyexpecting[] = ", expecting %s";
      static char const yyor[] = " or %s";
      char yyformat[sizeof yyunexpected
		    + sizeof yyexpecting - 1
		    + ((YYERROR_VERBOSE_ARGS_MAXIMUM - 2)
		       * (sizeof yyor - 1))];
      char const *yyprefix = yyexpecting;

      /* Start YYX at -YYN if negative to avoid negative indexes in
	 YYCHECK.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;

      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yycount = 1;

      yyarg[0] = yytname[yytype];
      yyfmt = yystpcpy (yyformat, yyunexpected);

      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
	if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR)
	  {
	    if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
	      {
		yycount = 1;
		yysize = yysize0;
		yyformat[sizeof yyunexpected - 1] = '\0';
		break;
	      }
	    yyarg[yycount++] = yytname[yyx];
	    yysize1 = yysize + yytnamerr (0, yytname[yyx]);
	    yysize_overflow |= (yysize1 < yysize);
	    yysize = yysize1;
	    yyfmt = yystpcpy (yyfmt, yyprefix);
	    yyprefix = yyor;
	  }

      yyf = YY_(yyformat);
      yysize1 = yysize + yystrlen (yyf);
      yysize_overflow |= (yysize1 < yysize);
      yysize = yysize1;

      if (yysize_overflow)
	return YYSIZE_MAXIMUM;

      if (yyresult)
	{
	  /* Avoid sprintf, as that infringes on the user's name space.
	     Don't have undefined behavior even if the translation
	     produced a string with the wrong number of "%s"s.  */
	  char *yyp = yyresult;
	  int yyi = 0;
	  while ((*yyp = *yyf) != '\0')
	    {
	      if (*yyp == '%' && yyf[1] == 's' && yyi < yycount)
		{
		  yyp += yytnamerr (yyp, yyarg[yyi++]);
		  yyf += 2;
		}
	      else
		{
		  yyp++;
		  yyf++;
		}
	    }
	}
      return yysize;
    }
}
#endif /* YYERROR_VERBOSE */


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */

#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */



/* The look-ahead symbol.  */
int yychar;

/* The semantic value of the look-ahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;



/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
  
  int yystate;
  int yyn;
  int yyresult;
  /* Number of tokens to shift before error messages enabled.  */
  int yyerrstatus;
  /* Look-ahead token as an internal (translated) token number.  */
  int yytoken = 0;
#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

  /* Three stacks and their tools:
     `yyss': related to states,
     `yyvs': related to semantic values,
     `yyls': related to locations.

     Refer to the stacks thru separate pointers, to allow yyoverflow
     to reallocate them elsewhere.  */

  /* The state stack.  */
  yytype_int16 yyssa[YYINITDEPTH];
  yytype_int16 *yyss = yyssa;
  yytype_int16 *yyssp;

  /* The semantic value stack.  */
  YYSTYPE yyvsa[YYINITDEPTH];
  YYSTYPE *yyvs = yyvsa;
  YYSTYPE *yyvsp;



#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  YYSIZE_T yystacksize = YYINITDEPTH;

  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;


  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY;		/* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */

  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;


	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),

		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss);
	YYSTACK_RELOCATE (yyvs);

#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;


      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     look-ahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to look-ahead token.  */
  yyn = yypact[yystate];
  if (yyn == YYPACT_NINF)
    goto yydefault;

  /* Not known => get a look-ahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid look-ahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yyn == 0 || yyn == YYTABLE_NINF)
	goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the look-ahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token unless it is eof.  */
  if (yychar != YYEOF)
    yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 568 "aemilia_parser.y"
    {
			    ;}
    break;

  case 3:
#line 571 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 4:
#line 576 "aemilia_parser.y"
    {
			    ;}
    break;

  case 5:
#line 579 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 6:
#line 584 "aemilia_parser.y"
    {
			    ;}
    break;

  case 7:
#line 587 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 8:
#line 592 "aemilia_parser.y"
    {
			    ;}
    break;

  case 9:
#line 595 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 10:
#line 600 "aemilia_parser.y"
    {
			    ;}
    break;

  case 11:
#line 603 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 12:
#line 608 "aemilia_parser.y"
    {
			    ;}
    break;

  case 13:
#line 611 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 14:
#line 616 "aemilia_parser.y"
    {
			    ;}
    break;

  case 15:
#line 619 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 16:
#line 624 "aemilia_parser.y"
    {
			    ;}
    break;

  case 17:
#line 627 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 18:
#line 632 "aemilia_parser.y"
    {
			    ;}
    break;

  case 19:
#line 635 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 20:
#line 640 "aemilia_parser.y"
    {
			    ;}
    break;

  case 21:
#line 643 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 22:
#line 648 "aemilia_parser.y"
    {
			    ;}
    break;

  case 23:
#line 651 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 24:
#line 656 "aemilia_parser.y"
    {
			    ;}
    break;

  case 25:
#line 662 "aemilia_parser.y"
    {
			      check_id(ARCHI_TYPE_ID_DEF,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      if ((yyvsp[(2) - (2)].st_bucket) != NULL)
			      {
			        (yyvsp[(2) - (2)].st_bucket)->info = archi_type[spec_no]->info;
			        archi_type[spec_no] = (yyvsp[(2) - (2)].st_bucket);
			      }
	     		    ;}
    break;

  case 26:
#line 673 "aemilia_parser.y"
    {
			      (yyvsp[(2) - (6)].st_bucket)->info.archi_type->formal_const_par_list = (yyvsp[(5) - (6)].st_bucket_cell);
			    ;}
    break;

  case 27:
#line 680 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 28:
#line 684 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 29:
#line 691 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 30:
#line 698 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].st_bucket),
					                  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 31:
#line 708 "aemilia_parser.y"
    {
			      check_id(VAR_ID_DECL,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			      store_var_decl((yyvsp[(3) - (3)].st_bucket),
					     FORMAL_CONST_PAR_ID,
					     (yyvsp[(2) - (3)].expr),
					     value_passing);
			    ;}
    break;

  case 32:
#line 718 "aemilia_parser.y"
    {
			      handle_archi_type_par_assign((yyvsp[(3) - (6)].st_bucket),
							   (yyvsp[(6) - (6)].expr_parse_info));
			      (yyval.st_bucket) = (yyvsp[(3) - (6)].st_bucket);
			    ;}
    break;

  case 33:
#line 727 "aemilia_parser.y"
    {
			      archi_type[spec_no]->info.archi_type->aet_list = (yyvsp[(2) - (2)].st_bucket_cell);
			    ;}
    break;

  case 34:
#line 731 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 35:
#line 736 "aemilia_parser.y"
    {
			      archi_type[spec_no]->info.archi_type->aet_list = (yyvsp[(4) - (4)].st_bucket_cell);
			    ;}
    break;

  case 36:
#line 743 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			      aet = NULL;
			    ;}
    break;

  case 37:
#line 751 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (2)].st_bucket) == NULL)?
				     (yyvsp[(2) - (2)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (2)].st_bucket),
					                  (yyvsp[(2) - (2)].st_bucket_cell));
			      aet = NULL;
			    ;}
    break;

  case 38:
#line 759 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 39:
#line 764 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
			    ;}
    break;

  case 40:
#line 768 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 41:
#line 777 "aemilia_parser.y"
    {
			      check_id(AET_ID_DEF,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      aet = (yyvsp[(2) - (2)].st_bucket);
			      id_prefix_in_expr = aet;
			      act_type_list = NULL;
			    ;}
    break;

  case 42:
#line 786 "aemilia_parser.y"
    {
			      interaction_index = INPUT_UNI;
			      no_declarations = TRUE;
			    ;}
    break;

  case 43:
#line 791 "aemilia_parser.y"
    {
			      interaction_index = OUTPUT_UNI;
			      no_declarations = TRUE;
			    ;}
    break;

  case 44:
#line 796 "aemilia_parser.y"
    {
			      (yyval.st_bucket) = (yyvsp[(2) - (13)].st_bucket);
			      if ((yyval.st_bucket) != NULL)
			        (yyval.st_bucket)->info.aet = alloc_aet((yyvsp[(5) - (13)].st_bucket_cell),
						         (yyvsp[(7) - (13)].st_bucket_cell),
						         act_type_list);
			    ;}
    break;

  case 45:
#line 807 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 46:
#line 811 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 47:
#line 818 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 48:
#line 825 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].st_bucket),
						          (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 49:
#line 835 "aemilia_parser.y"
    {
			      build_prefixed_id(aet,
						&(yyvsp[(3) - (3)].st_bucket));
			      check_id(VAR_ID_DECL,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			      store_var_decl((yyvsp[(3) - (3)].st_bucket),
					     FORMAL_CONST_PAR_ID,
					     (yyvsp[(2) - (3)].expr),
					     value_passing);
			      (yyval.st_bucket) = (yyvsp[(3) - (3)].st_bucket);
			    ;}
    break;

  case 50:
#line 851 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(2) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(2) - (3)].st_bucket),
							  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 51:
#line 861 "aemilia_parser.y"
    {
			      handle_behavior_def_1(&(yyvsp[(1) - (1)].st_bucket),
						    TRUE);
			    ;}
    break;

  case 52:
#line 866 "aemilia_parser.y"
    {
			      handle_behavior_def_2((yyvsp[(1) - (7)].st_bucket),
						    (yyvsp[(4) - (7)].st_bucket_cell),
						    (yyvsp[(6) - (7)].st_bucket_cell),
						    TRUE);
			    ;}
    break;

  case 53:
#line 873 "aemilia_parser.y"
    {
			      handle_behavior_def_3((yyvsp[(1) - (10)].st_bucket),
						    (yyvsp[(10) - (10)].term_parse_info));
			      (yyval.st_bucket) = (yyvsp[(1) - (10)].st_bucket);
			    ;}
    break;

  case 54:
#line 882 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 55:
#line 886 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 56:
#line 893 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 57:
#line 900 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].st_bucket),
					                  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 58:
#line 910 "aemilia_parser.y"
    {
			      build_prefixed_id(behavior,
						&(yyvsp[(2) - (2)].st_bucket));
			      check_id(VAR_ID_DECL,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      store_var_decl((yyvsp[(2) - (2)].st_bucket),
					     FORMAL_VAR_PAR_ID,
					     (yyvsp[(1) - (2)].expr),
					     value_passing);
			    ;}
    break;

  case 59:
#line 922 "aemilia_parser.y"
    {
			      if (((yyvsp[(2) - (5)].st_bucket) != NULL) &&
			          ((yyvsp[(5) - (5)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(5) - (5)].expr_parse_info)->expr,
						 NULL,
						 NULL,
						 (yyvsp[(2) - (5)].st_bucket),
						 ACTUAL_PAR_NOT_UNDECL_ID_FREE,
						 NO_ERROR,
						 ILL_TYPED_ASSIGN))
			      {
			        if (init_behav_actual_var_par_list == NULL)
			          last_init_behav_actual_var_par_cell = init_behav_actual_var_par_list =
				    alloc_st_bucket_cell((yyvsp[(5) - (5)].expr_parse_info)->expr,
						         NULL);
			        else
			          last_init_behav_actual_var_par_cell =
				    last_init_behav_actual_var_par_cell->next_st_bucket_cell =
				      alloc_st_bucket_cell((yyvsp[(5) - (5)].expr_parse_info)->expr,
						           NULL);
			      }
			      (yyval.st_bucket) = (yyvsp[(2) - (5)].st_bucket);
			    ;}
    break;

  case 60:
#line 949 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 61:
#line 953 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 62:
#line 960 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 63:
#line 967 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (2)].st_bucket) == NULL)?
				     (yyvsp[(2) - (2)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (2)].st_bucket),
					                  (yyvsp[(2) - (2)].st_bucket_cell));
			    ;}
    break;

  case 64:
#line 974 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 65:
#line 979 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
			    ;}
    break;

  case 66:
#line 983 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 67:
#line 992 "aemilia_parser.y"
    {
			      handle_behavior_def_1(&(yyvsp[(2) - (2)].st_bucket),
						    FALSE);
			    ;}
    break;

  case 68:
#line 997 "aemilia_parser.y"
    {
			      handle_behavior_def_2((yyvsp[(2) - (8)].st_bucket),
						    (yyvsp[(5) - (8)].st_bucket_cell),
						    (yyvsp[(7) - (8)].st_bucket_cell),
						    FALSE);
			    ;}
    break;

  case 69:
#line 1004 "aemilia_parser.y"
    {
			      handle_behavior_def_3((yyvsp[(2) - (11)].st_bucket),
						    (yyvsp[(11) - (11)].term_parse_info));
			      (yyval.st_bucket) = (yyvsp[(2) - (11)].st_bucket);
			    ;}
    break;

  case 70:
#line 1013 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 71:
#line 1017 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 72:
#line 1024 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 73:
#line 1031 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].st_bucket),
					                  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 74:
#line 1041 "aemilia_parser.y"
    {
			      build_prefixed_id(behavior,
						&(yyvsp[(2) - (2)].st_bucket));
			      check_id(VAR_ID_DECL,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      store_var_decl((yyvsp[(2) - (2)].st_bucket),
					     FORMAL_VAR_PAR_ID,
					     (yyvsp[(1) - (2)].expr),
					     value_passing);
			      (yyval.st_bucket) = (yyvsp[(2) - (2)].st_bucket);
			    ;}
    break;

  case 75:
#line 1057 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 76:
#line 1061 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 77:
#line 1068 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 78:
#line 1075 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].st_bucket) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].st_bucket),
					                  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 79:
#line 1085 "aemilia_parser.y"
    {
			      build_prefixed_id(behavior,
						&(yyvsp[(3) - (3)].st_bucket));
			      check_id(VAR_ID_DECL,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			      store_var_decl((yyvsp[(3) - (3)].st_bucket),
					     LOCAL_VAR_ID,
					     (yyvsp[(2) - (3)].expr),
					     value_passing);
			      (yyval.st_bucket) = (yyvsp[(3) - (3)].st_bucket);
			    ;}
    break;

  case 80:
#line 1101 "aemilia_parser.y"
    {
			      (yyval.expr) = (yyvsp[(1) - (1)].expr);
			    ;}
    break;

  case 81:
#line 1105 "aemilia_parser.y"
    {
			      (yyval.expr) = (yyvsp[(1) - (1)].expr);
			    ;}
    break;

  case 82:
#line 1112 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("i",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			      value_passing = SYMBOLIC_VP;
			    ;}
    break;

  case 83:
#line 1125 "aemilia_parser.y"
    {
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
			          !check_expr_all((yyvsp[(3) - (3)].expr_parse_info)->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  INTEGER_BOUND_NOT_UNDECL_ID_FREE,
						  INTEGER_BOUND_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_INTEGER_BOUND))
				(yyvsp[(3) - (3)].expr_parse_info) = NULL;
			    ;}
    break;

  case 84:
#line 1138 "aemilia_parser.y"
    {
			      if (((yyvsp[(6) - (6)].expr_parse_info) != NULL) &&
			          !check_expr_all((yyvsp[(6) - (6)].expr_parse_info)->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  INTEGER_BOUND_NOT_UNDECL_ID_FREE,
						  INTEGER_BOUND_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_INTEGER_BOUND))
				(yyvsp[(6) - (6)].expr_parse_info) = NULL;
			    ;}
    break;

  case 85:
#line 1151 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      (((yyvsp[(3) - (8)].expr_parse_info) != NULL) &&
					       ((yyvsp[(6) - (8)].expr_parse_info) != NULL))?
					        (yyvsp[(3) - (8)].expr_parse_info)->expr:
						NULL,
					      (((yyvsp[(3) - (8)].expr_parse_info) != NULL) &&
					       ((yyvsp[(6) - (8)].expr_parse_info) != NULL))?
					        (yyvsp[(6) - (8)].expr_parse_info)->expr:
						NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("i()",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    ;}
    break;

  case 86:
#line 1169 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("r",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			      value_passing = SYMBOLIC_VP;
			    ;}
    break;

  case 87:
#line 1182 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("b",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    ;}
    break;

  case 88:
#line 1194 "aemilia_parser.y"
    {
			      handle_struct_data_type_decl(LIST_CONS_OP,
					       		   &(yyval.expr),
					       		   (yyvsp[(3) - (4)].expr),
					       		   NULL,
					       		   NULL);
			      value_passing = SYMBOLIC_VP;
			    ;}
    break;

  case 89:
#line 1203 "aemilia_parser.y"
    {
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
			          !check_expr_all((yyvsp[(3) - (3)].expr_parse_info)->expr,
						  NULL,
						  NULL,
						  (ST_BUCKET *)search_lexeme_table("integer",
										   SYT),
						  ARRAY_LENGTH_NOT_UNDECL_ID_FREE,
						  ARRAY_LENGTH_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_ARRAY_LENGTH))
				(yyvsp[(3) - (3)].expr_parse_info) = NULL;
			    ;}
    break;

  case 90:
#line 1216 "aemilia_parser.y"
    {
			      handle_struct_data_type_decl(ARRAY_CONS_OP,
					       		   &(yyval.expr),
					       		   (yyvsp[(6) - (7)].expr),
					       		   (yyvsp[(3) - (7)].expr_parse_info),
					       		   NULL);
			    ;}
    break;

  case 91:
#line 1224 "aemilia_parser.y"
    {
			      handle_struct_data_type_decl(RECORD_CONS_OP,
					       		   &(yyval.expr),
					       		   NULL,
					       		   NULL,
					       		   (yyvsp[(3) - (4)].st_bucket_cell));
			    ;}
    break;

  case 92:
#line 1235 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 93:
#line 1242 "aemilia_parser.y"
    {
			      if (check_list_membership((yyvsp[(3) - (3)].st_bucket),
							(yyvsp[(1) - (3)].st_bucket_cell),
							TRUE))
			      {
				signal_error(REC_FIELD_REDECL,
					     NULL,
					     NULL);
				(yyvsp[(3) - (3)].st_bucket) = NULL;
			      }
			      (yyval.st_bucket_cell) = ((yyvsp[(3) - (3)].st_bucket) == NULL)?
				     (yyvsp[(1) - (3)].st_bucket_cell):
				     append_list((yyvsp[(1) - (3)].st_bucket_cell),
						 alloc_st_bucket_cell((yyvsp[(3) - (3)].st_bucket),
								      NULL));
			    ;}
    break;

  case 94:
#line 1262 "aemilia_parser.y"
    {
			      /* the first check without prefixing the record field identifier is needed */
			      /* to find as declared the identifier whenever it is used (no usage occurs */
			      /* with the corresponding prefix) */
			      check_id(REC_FIELD_ID_DECL,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      if ((yyvsp[(2) - (2)].st_bucket) != NULL)
			      {
				(yyvsp[(2) - (2)].st_bucket)->info.expr = (yyvsp[(1) - (2)].expr);
			        build_typed_rec_field_id((yyvsp[(1) - (2)].expr),
						         &(yyvsp[(2) - (2)].st_bucket));
			        check_id(REC_FIELD_ID_DECL,
				         &(yyvsp[(2) - (2)].st_bucket),
				         FALSE);
			        if ((yyvsp[(2) - (2)].st_bucket) != NULL)
				  (yyvsp[(2) - (2)].st_bucket)->info.expr = (yyvsp[(1) - (2)].expr);
			      }
			      (yyval.st_bucket) = (yyvsp[(2) - (2)].st_bucket);
	  		    ;}
    break;

  case 95:
#line 1286 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("P",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    ;}
    break;

  case 96:
#line 1298 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("R",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    ;}
    break;

  case 97:
#line 1310 "aemilia_parser.y"
    {
			      (yyval.expr) = alloc_expr(NO_EXPR_OP,
					      NULL,
					      NULL,
					      NULL,
					      (DTT_BUCKET *)search_lexeme_table("W",
									        DTT),
					      FALSE,
					      0.0L,
					      NULL);
			    ;}
    break;

  case 98:
#line 1325 "aemilia_parser.y"
    {
			      handle_term(&(yyval.term_parse_info),
					  STOP_OP,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    ;}
    break;

  case 99:
#line 1336 "aemilia_parser.y"
    {
			      handle_term(&(yyval.term_parse_info),
					  ACT_PREFIX_OP,
					  (yyvsp[(3) - (3)].term_parse_info),
					  NULL,
					  (yyvsp[(1) - (3)].action_parse_info),
					  NULL,
					  NULL,
					  NULL);
			    ;}
    break;

  case 100:
#line 1347 "aemilia_parser.y"
    {
			      handle_term(&(yyval.term_parse_info),
					  ALT_COMP_OP,
					  (yyvsp[(3) - (5)].term_parse_info),
					  (yyvsp[(4) - (5)].term_parse_info),
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    ;}
    break;

  case 101:
#line 1361 "aemilia_parser.y"
    {
			      (yyval.term_parse_info) = (yyvsp[(1) - (1)].term_parse_info);
			    ;}
    break;

  case 102:
#line 1365 "aemilia_parser.y"
    {
			      build_prefixed_id(aet,
						&(yyvsp[(1) - (1)].st_bucket));
			      check_id(BEHAV_ID_USE,
				       &(yyvsp[(1) - (1)].st_bucket),
				       FALSE);
			      local_var_actual_par_list = NULL;
			    ;}
    break;

  case 103:
#line 1374 "aemilia_parser.y"
    {
			      handle_term(&(yyval.term_parse_info),
					  BEHAV_INVOC_OP,
					  NULL,
					  NULL,
					  NULL,
					  NULL,
					  (yyvsp[(1) - (5)].st_bucket),
					  (yyvsp[(4) - (5)].st_bucket_cell));
			      if ((yyvsp[(1) - (5)].st_bucket) != NULL)
				invoked_behavior_list = alloc_st_bucket_cell((yyvsp[(1) - (5)].st_bucket),
									     invoked_behavior_list);
			    ;}
    break;

  case 104:
#line 1391 "aemilia_parser.y"
    {
			      if ((yyvsp[(1) - (2)].expr_parse_info) == NULL)
				(yyval.term_parse_info) = (yyvsp[(2) - (2)].term_parse_info);
			      else
			        handle_term(&(yyval.term_parse_info),
					    COND_OP,
					    (yyvsp[(2) - (2)].term_parse_info),
					    NULL,
					    NULL,
					    (yyvsp[(1) - (2)].expr_parse_info),
					    NULL,
					    NULL);
			    ;}
    break;

  case 105:
#line 1408 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = NULL;
			    ;}
    break;

  case 106:
#line 1412 "aemilia_parser.y"
    {
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
				  ((yyvsp[(3) - (3)].expr_parse_info)->expr->info.expr->data_type != NULL) &&
				  ((yyvsp[(3) - (3)].expr_parse_info)->expr->info.expr->data_type->data_type_lexeme[0] != 'b'))
			      {
				signal_error(ILL_TYPED_EXPR,
					     NULL,
					     NULL);
				(yyvsp[(3) - (3)].expr_parse_info) = NULL;
			      }
			    ;}
    break;

  case 107:
#line 1424 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (6)].expr_parse_info);
			    ;}
    break;

  case 108:
#line 1431 "aemilia_parser.y"
    {
			      (yyval.term_parse_info) = (yyvsp[(1) - (1)].term_parse_info);
			    ;}
    break;

  case 109:
#line 1435 "aemilia_parser.y"
    {
			      handle_term(&(yyval.term_parse_info),
					  ALT_COMP_OP,
					  (yyvsp[(1) - (2)].term_parse_info),
					  (yyvsp[(2) - (2)].term_parse_info),
					  NULL,
					  NULL,
					  NULL,
					  NULL);
			    ;}
    break;

  case 110:
#line 1449 "aemilia_parser.y"
    {
			      (yyval.term_parse_info) = (yyvsp[(2) - (2)].term_parse_info);
			    ;}
    break;

  case 111:
#line 1456 "aemilia_parser.y"
    {
			      build_prefixed_id(aet,
						&(yyvsp[(2) - (2)].st_bucket));
			      check_id(ACT_TYPE_ID_USE,
				       &(yyvsp[(2) - (2)].st_bucket),
				       FALSE);
			      if (((yyvsp[(2) - (2)].st_bucket) != NULL) &&
				  !check_list_membership((yyvsp[(2) - (2)].st_bucket),
							 act_type_list,
							 FALSE))
				act_type_list = alloc_st_bucket_cell((yyvsp[(2) - (2)].st_bucket),
								     act_type_list);
			      local_var_actual_par_list = NULL;
			    ;}
    break;

  case 112:
#line 1471 "aemilia_parser.y"
    {
			      if ((yyvsp[(2) - (4)].st_bucket) != NULL)
			      {
				if ((yyvsp[(2) - (4)].st_bucket)->info.act_type == NULL)
				  (yyvsp[(2) - (4)].st_bucket)->info.act_type = alloc_act_type(action_index,
							             (yyvsp[(4) - (4)].st_bucket_cell),
							             NULL);
				else
				  if (action_index != (yyvsp[(2) - (4)].st_bucket)->info.act_type->act_type_index)
				  {
				    signal_error(INCONSISTENT_USE_ACT_TYPE,
						 NULL,
						 NULL);
				    (yyvsp[(2) - (4)].st_bucket) = NULL;
				  }
				  else
				    if (!check_expr_list_types((yyvsp[(2) - (4)].st_bucket)->info.act_type->par_list,
							       (yyvsp[(4) - (4)].st_bucket_cell),
							       TRUE))
				      (yyvsp[(2) - (4)].st_bucket) = NULL;
			      }
			      rate_index = NO_RATE;
			      priority = rate = NULL;
			    ;}
    break;

  case 113:
#line 1496 "aemilia_parser.y"
    {
			      if ((yyvsp[(2) - (7)].st_bucket) != NULL)
			      {
			        if (((yyvsp[(2) - (7)].st_bucket)->info.act_type->rate_index == NO_RATE) &&
				    (rate_index != NO_RATE) &&
				    (priority != NULL))
			        {
				  (yyvsp[(2) - (7)].st_bucket)->info.act_type->rate_index = rate_index;
				  (yyvsp[(2) - (7)].st_bucket)->info.act_type->priority = priority;
			        }
			        if ((rate_index != NO_RATE) &&
				    (priority != NULL) &&
				    (((yyvsp[(2) - (7)].st_bucket)->info.act_type->rate_index != rate_index) ||
				     (((yyvsp[(2) - (7)].st_bucket)->info.act_type->priority->symbol_index == NUMBER) &&
				      ((yyvsp[(2) - (7)].st_bucket)->info.act_type->priority->info.expr->value !=
					 priority->info.expr->value)) ||
				     (((yyvsp[(2) - (7)].st_bucket)->info.act_type->priority->symbol_index != NUMBER) &&
				      ((yyvsp[(2) - (7)].st_bucket)->info.act_type->priority != priority))))
			        {
				  signal_error(INCONSISTENT_USE_ACT_TYPE,
					       NULL,
					       NULL);
				  (yyvsp[(2) - (7)].st_bucket) = NULL;
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
			    ;}
    break;

  case 114:
#line 1532 "aemilia_parser.y"
    {
			      (yyval.action_parse_info) = alloc_action_parse_info((((yyvsp[(2) - (9)].st_bucket) == NULL) ||
							    (rate_index == NO_RATE) ||
							    (priority == NULL) ||
							    (rate == NULL))?
							     NULL:
							     set_action_bucket((yyvsp[(2) - (9)].st_bucket),
									       action_index,
									       (yyvsp[(4) - (9)].st_bucket_cell),
									       rate_index,
									       priority,
									       rate),
							   local_var_actual_par_list);
			    ;}
    break;

  case 115:
#line 1550 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			      action_index = UNSTRUCTURED;
			    ;}
    break;

  case 116:
#line 1555 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(3) - (4)].st_bucket_cell);
			      action_index = INPUT;
			    ;}
    break;

  case 117:
#line 1560 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(3) - (4)].st_bucket_cell);
			      action_index = OUTPUT;
			    ;}
    break;

  case 118:
#line 1568 "aemilia_parser.y"
    {
			      handle_input_act_type_par(&(yyvsp[(1) - (1)].st_bucket),
							&local_var_actual_par_list);
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 119:
#line 1577 "aemilia_parser.y"
    {
			      handle_input_act_type_par(&(yyvsp[(1) - (1)].st_bucket),
							&local_var_actual_par_list);
			    ;}
    break;

  case 120:
#line 1582 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (4)].st_bucket) == NULL)?
				     (yyvsp[(4) - (4)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (4)].st_bucket),
					                  (yyvsp[(4) - (4)].st_bucket_cell));
			    ;}
    break;

  case 121:
#line 1592 "aemilia_parser.y"
    {
			      handle_output_act_type_par((yyvsp[(1) - (1)].expr_parse_info),
							 &local_var_actual_par_list);
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
					                  NULL);
			    ;}
    break;

  case 122:
#line 1601 "aemilia_parser.y"
    {
			      handle_output_act_type_par((yyvsp[(1) - (1)].expr_parse_info),
							 &local_var_actual_par_list);
			    ;}
    break;

  case 123:
#line 1606 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (4)].expr_parse_info) == NULL)?
				     (yyvsp[(4) - (4)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (4)].expr_parse_info)->expr,
					                  (yyvsp[(4) - (4)].st_bucket_cell));
			    ;}
    break;

  case 124:
#line 1616 "aemilia_parser.y"
    {
			      rate_index = EXP_TIMED;
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("rate",
                                                                                  SYT),
                                                 NO_ERROR,
                                                 EXP_RATE_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_EXP_RATE))
			      {
			        if (((yyvsp[(3) - (3)].expr_parse_info)->expr->symbol_index == NUMBER) &&
				    ((yyvsp[(3) - (3)].expr_parse_info)->expr->info.expr->value <= 0.0L))
				  signal_error(EXP_RATE_LE_ZERO,
					       NULL,
					       NULL);
			        else
				{
				  priority = set_number_bucket("0");
				  rate = (yyvsp[(3) - (3)].expr_parse_info)->expr;
				}
			      }
			    ;}
    break;

  case 125:
#line 1641 "aemilia_parser.y"
    {
			    ;}
    break;

  case 126:
#line 1644 "aemilia_parser.y"
    {
			      rate_index = IMMEDIATE;
			    ;}
    break;

  case 127:
#line 1648 "aemilia_parser.y"
    {
			      rate_index = PASSIVE;
			    ;}
    break;

  case 128:
#line 1655 "aemilia_parser.y"
    {
			      priority = rate = set_number_bucket("1");
			    ;}
    break;

  case 129:
#line 1659 "aemilia_parser.y"
    {
			      if (((yyvsp[(2) - (2)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(2) - (2)].expr_parse_info)->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("prio",
                                                                                  SYT),
                                                 PRIORITY_NOT_UNDECL_ID_FREE,
                                                 PRIORITY_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_PRIORITY))
			      {
			        if (((yyvsp[(2) - (2)].expr_parse_info)->expr->symbol_index == NUMBER) &&
				    ((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value <= 0.0L))
				  signal_error(PRIORITY_LE_ZERO,
					       NULL,
					       NULL);
			        else
				  priority = (yyvsp[(2) - (2)].expr_parse_info)->expr;
			      }
			    ;}
    break;

  case 130:
#line 1680 "aemilia_parser.y"
    {
			      if (((yyvsp[(5) - (5)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(5) - (5)].expr_parse_info)->expr,
                                                 NULL,
                                                 NULL,
                                                 (ST_BUCKET *)search_lexeme_table("weight",
                                                                                  SYT),
                                                 NO_ERROR,
                                                 WEIGHT_NOT_RANDOMNESS_FREE,
                                                 ILL_TYPED_WEIGHT))
			      {
			        if (((yyvsp[(5) - (5)].expr_parse_info)->expr->symbol_index == NUMBER) &&
				    ((yyvsp[(5) - (5)].expr_parse_info)->expr->info.expr->value <= 0.0L))
				  signal_error(WEIGHT_LE_ZERO,
					       NULL,
					       NULL);
			        else
				  rate = (yyvsp[(5) - (5)].expr_parse_info)->expr;
			      }
			    ;}
    break;

  case 132:
#line 1705 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 133:
#line 1709 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 134:
#line 1716 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
					                  NULL);
			    ;}
    break;

  case 135:
#line 1723 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (3)].expr_parse_info) == NULL)?
				     (yyvsp[(3) - (3)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
					                  (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 136:
#line 1733 "aemilia_parser.y"
    {
			    ;}
    break;

  case 137:
#line 1736 "aemilia_parser.y"
    {
			      if (no_declarations)
				signal_error(NO_DECLARED_INTERACTIONS,
					     NULL,
					     NULL);
			    ;}
    break;

  case 138:
#line 1746 "aemilia_parser.y"
    {
			      interaction_index = (interaction_index == INPUT_UNI)?
						    INPUT_AND:
						    OUTPUT_AND;
			    ;}
    break;

  case 139:
#line 1752 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			      interaction_index = (interaction_index == INPUT_UNI)?
						    INPUT_AND:
						    OUTPUT_AND;
			    ;}
    break;

  case 140:
#line 1762 "aemilia_parser.y"
    {
			      interaction_index = (interaction_index == INPUT_AND)?
						    INPUT_OR:
						    OUTPUT_OR;
	      		    ;}
    break;

  case 141:
#line 1768 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			      interaction_index = (interaction_index == INPUT_AND)?
						    INPUT_OR:
						    OUTPUT_OR;
			    ;}
    break;

  case 142:
#line 1778 "aemilia_parser.y"
    {
			      if (interaction_index == INPUT_OR)
				interaction_index = OUTPUT_UNI;
	      		    ;}
    break;

  case 143:
#line 1783 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			      if (interaction_index == INPUT_OR)
				interaction_index = OUTPUT_UNI;
			    ;}
    break;

  case 144:
#line 1792 "aemilia_parser.y"
    {
			      build_prefixed_id(aet,
						&(yyvsp[(1) - (1)].st_bucket));
			      check_id(ACT_TYPE_ID_USE,
				       &(yyvsp[(1) - (1)].st_bucket),
				       FALSE);
			      handle_interaction_decl((yyvsp[(1) - (1)].st_bucket),
						      interaction_index);
			    ;}
    break;

  case 145:
#line 1802 "aemilia_parser.y"
    {
			      build_prefixed_id(aet,
						&(yyvsp[(1) - (1)].st_bucket));
			      check_id(ACT_TYPE_ID_USE,
				       &(yyvsp[(1) - (1)].st_bucket),
				       FALSE);
			      handle_interaction_decl((yyvsp[(1) - (1)].st_bucket),
						      interaction_index);
			    ;}
    break;

  case 146:
#line 1812 "aemilia_parser.y"
    {
			    ;}
    break;

  case 147:
#line 1815 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 148:
#line 1820 "aemilia_parser.y"
    {
			    ;}
    break;

  case 149:
#line 1826 "aemilia_parser.y"
    {
	       		    ;}
    break;

  case 150:
#line 1832 "aemilia_parser.y"
    {
			      archi_type[spec_no]->info.archi_type->aei_list = (yyvsp[(2) - (2)].st_bucket_cell);
			      if ((yyvsp[(2) - (2)].st_bucket_cell) == NULL)
				signal_error(NO_DECLARED_AEIS,
					     NULL,
					     NULL);
			    ;}
    break;

  case 151:
#line 1843 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 152:
#line 1847 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = append_list((yyvsp[(1) - (3)].st_bucket_cell),
					       (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 153:
#line 1852 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 154:
#line 1857 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
			    ;}
    break;

  case 155:
#line 1861 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (3)].st_bucket_cell);
			    ;}
    break;

  case 156:
#line 1867 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 157:
#line 1876 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 158:
#line 1883 "aemilia_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      (yyval.st_bucket_cell) = (((yyvsp[(1) - (2)].st_bucket) == NULL) ||
				    ((yyvsp[(2) - (2)].st_bucket) == NULL))?
				     NULL:
				     expand_iterative_aei_decl((yyvsp[(2) - (2)].st_bucket));
			      selector[0] = NULL;
			    ;}
    break;

  case 159:
#line 1895 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							    (yyvsp[(2) - (2)].expr_parse_info),
							    0,
							    TRUE,
							    FALSE);
			    ;}
    break;

  case 160:
#line 1903 "aemilia_parser.y"
    {
			      check_id(AET_ID_USE,
				       &(yyvsp[(5) - (5)].st_bucket),
				       FALSE);
			      id_prefix_in_expr = NULL;
			    ;}
    break;

  case 161:
#line 1910 "aemilia_parser.y"
    {
			      if (((yyvsp[(1) - (9)].st_bucket) != NULL) &&
				  ((yyvsp[(5) - (9)].st_bucket) != NULL) &&
				  ((yyvsp[(5) - (9)].st_bucket)->info.aet != NULL))
			      {
				handle_aei_decl((yyvsp[(1) - (9)].st_bucket),
					        (yyvsp[(5) - (9)].st_bucket),
					        (yyvsp[(8) - (9)].st_bucket_cell));
			        (yyval.st_bucket) = (yyvsp[(1) - (9)].st_bucket);
			      }
			      else
			        (yyval.st_bucket) = NULL;
			    ;}
    break;

  case 162:
#line 1927 "aemilia_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							      (yyvsp[(2) - (2)].expr_parse_info),
							      0,
							      TRUE);
			    ;}
    break;

  case 163:
#line 1934 "aemilia_parser.y"
    {
			      check_id(AET_ID_USE,
				       &(yyvsp[(5) - (5)].st_bucket),
				       TRUE);
			    ;}
    break;

  case 164:
#line 1940 "aemilia_parser.y"
    {
			      if (((yyvsp[(1) - (9)].st_bucket) != NULL) &&
				  ((yyvsp[(5) - (9)].st_bucket) != NULL) &&
				  ((yyvsp[(5) - (9)].st_bucket)->info.aet != NULL))
			      {
			        (yyvsp[(1) - (9)].st_bucket)->used = (char)TRUE;
			        (yyvsp[(1) - (9)].st_bucket)->info.aei = alloc_aei((yyvsp[(5) - (9)].st_bucket),
						         (yyvsp[(5) - (9)].st_bucket)->info.aet->formal_const_par_list,
						         (yyvsp[(5) - (9)].st_bucket)->info.aet->behavior_list,
						         (yyvsp[(5) - (9)].st_bucket)->info.aet->act_type_list,
						         TRUE);
			        (yyvsp[(1) - (9)].st_bucket)->info.aei->init_state_var_assign_list = (yyvsp[(8) - (9)].st_bucket_cell);
			        (yyval.st_bucket) = (yyvsp[(1) - (9)].st_bucket);
			      }
			      else
			        (yyval.st_bucket) = NULL;
			    ;}
    break;

  case 165:
#line 1961 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 166:
#line 1965 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 167:
#line 1972 "aemilia_parser.y"
    {
			      if (((yyvsp[(1) - (1)].expr_parse_info) != NULL) &&
				  !check_expr_randomness_free((yyvsp[(1) - (1)].expr_parse_info)->expr))
				signal_error(ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
					     NULL,
					     NULL);
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
					                  NULL);
			    ;}
    break;

  case 168:
#line 1984 "aemilia_parser.y"
    {
			      if (((yyvsp[(1) - (1)].expr_parse_info) != NULL) &&
				  !check_expr_randomness_free((yyvsp[(1) - (1)].expr_parse_info)->expr))
				signal_error(ACTUAL_CONST_PAR_NOT_RANDOMNESS_FREE,
					     NULL,
					     NULL);
			    ;}
    break;

  case 169:
#line 1992 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (4)].expr_parse_info) == NULL)?
				     (yyvsp[(4) - (4)].st_bucket_cell):
				     alloc_st_bucket_cell((yyvsp[(1) - (4)].expr_parse_info)->expr,
					                  (yyvsp[(4) - (4)].st_bucket_cell));
			    ;}
    break;

  case 170:
#line 2002 "aemilia_parser.y"
    {
			      archi_type[spec_no]->info.archi_type->ai_list = (yyvsp[(2) - (2)].st_bucket_cell);
			    ;}
    break;

  case 171:
#line 2009 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = NULL;
	      		    ;}
    break;

  case 172:
#line 2013 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
	      		    ;}
    break;

  case 173:
#line 2020 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 174:
#line 2024 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = append_list((yyvsp[(1) - (3)].st_bucket_cell),
					       (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 175:
#line 2029 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 176:
#line 2034 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
			    ;}
    break;

  case 177:
#line 2038 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (3)].st_bucket_cell);
			    ;}
    break;

  case 178:
#line 2044 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			      (yyval.st_bucket_cell) = NULL;
			    ;}
    break;

  case 179:
#line 2053 "aemilia_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
				     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
							  NULL);
			    ;}
    break;

  case 180:
#line 2060 "aemilia_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      (yyval.st_bucket_cell) = (((yyvsp[(1) - (2)].st_bucket) == NULL) ||
				    ((yyvsp[(2) - (2)].st_bucket) == NULL))?
				     NULL:
				     expand_iterative_ai_decl();
			      selector[0] = NULL;
			    ;}
    break;

  case 181:
#line 2072 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							    (yyvsp[(2) - (2)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			    ;}
    break;

  case 182:
#line 2080 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(1) - (5)].st_bucket),
						         &(yyvsp[(5) - (5)].st_bucket),
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ((yyvsp[(5) - (5)].st_bucket) != NULL)
				handle_architectural_interaction(&(yyvsp[(5) - (5)].st_bucket),
								 FALSE);
			      (yyval.st_bucket) = (yyvsp[(5) - (5)].st_bucket);
			    ;}
    break;

  case 183:
#line 2096 "aemilia_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							      (yyvsp[(2) - (2)].expr_parse_info),
							      0,
							      FALSE);
			    ;}
    break;

  case 184:
#line 2103 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(1) - (5)].st_bucket),
						         &(yyvsp[(5) - (5)].st_bucket),
						         0,
						         TRUE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      (yyval.st_bucket) = (yyvsp[(5) - (5)].st_bucket);
			    ;}
    break;

  case 185:
#line 2116 "aemilia_parser.y"
    {
			      create_sync_act_types();
			    ;}
    break;

  case 186:
#line 2123 "aemilia_parser.y"
    {
	      		    ;}
    break;

  case 187:
#line 2126 "aemilia_parser.y"
    {
	      		    ;}
    break;

  case 188:
#line 2132 "aemilia_parser.y"
    {
			    ;}
    break;

  case 189:
#line 2135 "aemilia_parser.y"
    {
			    ;}
    break;

  case 190:
#line 2138 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 191:
#line 2143 "aemilia_parser.y"
    {
			    ;}
    break;

  case 192:
#line 2146 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 193:
#line 2151 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 194:
#line 2156 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 195:
#line 2161 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 196:
#line 2169 "aemilia_parser.y"
    {
			    ;}
    break;

  case 197:
#line 2172 "aemilia_parser.y"
    {
			      selector_enabled[0] = selector_enabled[1] = FALSE;
			      if (((yyvsp[(1) - (3)].st_bucket) != NULL) &&
				  ((selector_index == 0) ||
				   ((yyvsp[(2) - (3)].st_bucket) != NULL)) &&
				  ((yyvsp[(3) - (3)].st_bucket2_cell) != NULL))
				expand_iterative_attachment_decl((yyvsp[(3) - (3)].st_bucket2_cell)->st_bucket1,
								 (yyvsp[(3) - (3)].st_bucket2_cell)->st_bucket2);
			      selector[0] = selector[1] = NULL;
			      selector_index = 0;
			    ;}
    break;

  case 198:
#line 2187 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							    (yyvsp[(3) - (3)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			    ;}
    break;

  case 199:
#line 2195 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(2) - (6)].st_bucket),
						         &(yyvsp[(6) - (6)].st_bucket),
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ((yyvsp[(6) - (6)].st_bucket) != NULL)
			        check_output_interaction(&(yyvsp[(6) - (6)].st_bucket),
							 FALSE);
			    ;}
    break;

  case 200:
#line 2207 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(9) - (10)].st_bucket),
							    (yyvsp[(10) - (10)].expr_parse_info),
							    1,
							    FALSE,
							    FALSE);
			      if (((yyvsp[(9) - (10)].st_bucket) != NULL) &&
				  ((yyvsp[(9) - (10)].st_bucket) == (yyvsp[(2) - (10)].st_bucket)))
			      {
				signal_error(AUTO_ATTACHMENT,
					     NULL,
					     NULL);
				(yyvsp[(9) - (10)].st_bucket) = NULL;
			      }
			    ;}
    break;

  case 201:
#line 2223 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(9) - (13)].st_bucket),
						         &(yyvsp[(13) - (13)].st_bucket),
						         1,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ((yyvsp[(13) - (13)].st_bucket) != NULL)
			        check_input_interaction(&(yyvsp[(13) - (13)].st_bucket),
							FALSE);
			      if (((yyvsp[(6) - (13)].st_bucket) != NULL) &&
				  ((yyvsp[(13) - (13)].st_bucket) != NULL))
			        attach_interactions((yyvsp[(6) - (13)].st_bucket),
						    (yyvsp[(13) - (13)].st_bucket),
						    FALSE);
			    ;}
    break;

  case 202:
#line 2243 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							    (yyvsp[(3) - (3)].expr_parse_info),
							    0,
							    FALSE,
							    TRUE);
			    ;}
    break;

  case 203:
#line 2251 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(2) - (6)].st_bucket),
						         &(yyvsp[(6) - (6)].st_bucket),
						         0,
						         (index_expr[0] != NULL),
						         TRUE,
						         ACT_TYPE_ID_USE);
			      if ((index_expr[0] == NULL) &&
				  ((yyvsp[(6) - (6)].st_bucket) != NULL))
			        check_output_interaction(&(yyvsp[(6) - (6)].st_bucket),
							 FALSE);
			    ;}
    break;

  case 204:
#line 2264 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(9) - (10)].st_bucket),
							    (yyvsp[(10) - (10)].expr_parse_info),
							    1,
							    FALSE,
							    TRUE);
			      if ((index_expr[0] == NULL) &&
				  (index_expr[1] == NULL) &&
			          ((yyvsp[(9) - (10)].st_bucket) != NULL) &&
				  ((yyvsp[(9) - (10)].st_bucket) == (yyvsp[(2) - (10)].st_bucket)))
			      {
				signal_error(AUTO_ATTACHMENT,
					     NULL,
					     NULL);
				(yyvsp[(9) - (10)].st_bucket) = NULL;
			      }
			      if ((index_expr[0] == NULL) &&
				  (index_expr[1] == NULL))
				signal_error(NO_AEI_INDEXING_USED_IN_ATTACHMENT,
					     NULL,
					     NULL);
			    ;}
    break;

  case 205:
#line 2287 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(9) - (13)].st_bucket),
						         &(yyvsp[(13) - (13)].st_bucket),
						         1,
						         (index_expr[1] != NULL),
						         TRUE,
						         ACT_TYPE_ID_USE);
			      if ((index_expr[1] == NULL) &&
				  ((yyvsp[(13) - (13)].st_bucket) != NULL))
			        check_input_interaction(&(yyvsp[(13) - (13)].st_bucket),
							FALSE);
			      if (((yyvsp[(6) - (13)].st_bucket) != NULL) &&
				  ((yyvsp[(13) - (13)].st_bucket) != NULL))
			      {
			        if ((index_expr[0] == NULL) &&
				    (index_expr[1] == NULL))
				{
			          attach_interactions((yyvsp[(6) - (13)].st_bucket),
						      (yyvsp[(13) - (13)].st_bucket),
						      FALSE);
				  (yyval.st_bucket2_cell) = NULL;
				}
				else
				  (yyval.st_bucket2_cell) = alloc_st_bucket2_cell((yyvsp[(6) - (13)].st_bucket),
							     (yyvsp[(13) - (13)].st_bucket),
							     NULL);
			      }
			      else
				(yyval.st_bucket2_cell) = NULL;
			    ;}
    break;

  case 206:
#line 2321 "aemilia_parser.y"
    {
			    ;}
    break;

  case 207:
#line 2324 "aemilia_parser.y"
    {
			      no_declarations = TRUE;
			    ;}
    break;

  case 208:
#line 2328 "aemilia_parser.y"
    {
			      if (no_declarations)
				signal_error(NO_DECLARED_BEHAV_VARIATIONS,
					     NULL,
					     NULL);
			    ;}
    break;

  case 209:
#line 2338 "aemilia_parser.y"
    {
			    ;}
    break;

  case 210:
#line 2341 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			    ;}
    break;

  case 211:
#line 2348 "aemilia_parser.y"
    {
			    ;}
    break;

  case 212:
#line 2351 "aemilia_parser.y"
    {
			    ;}
    break;

  case 213:
#line 2354 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 214:
#line 2359 "aemilia_parser.y"
    {
			    ;}
    break;

  case 215:
#line 2362 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 216:
#line 2367 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 217:
#line 2372 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 218:
#line 2377 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 219:
#line 2382 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 220:
#line 2387 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 221:
#line 2395 "aemilia_parser.y"
    {
			    ;}
    break;

  case 222:
#line 2398 "aemilia_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      if (((yyvsp[(1) - (2)].st_bucket) != NULL) &&
				  ((yyvsp[(2) - (2)].st_bucket) != NULL))
				expand_iterative_hiding_decl((yyvsp[(2) - (2)].st_bucket));
			      selector[0] = NULL;
			    ;}
    break;

  case 223:
#line 2409 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							    (yyvsp[(3) - (3)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			      aei = (yyvsp[(2) - (3)].st_bucket);
			    ;}
    break;

  case 224:
#line 2418 "aemilia_parser.y"
    {
			    ;}
    break;

  case 225:
#line 2421 "aemilia_parser.y"
    {
			      hide_internals();
			    ;}
    break;

  case 226:
#line 2425 "aemilia_parser.y"
    {
			      hide_interactions();
			    ;}
    break;

  case 227:
#line 2429 "aemilia_parser.y"
    {
			      hide_all();
			    ;}
    break;

  case 228:
#line 2436 "aemilia_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							      (yyvsp[(3) - (3)].expr_parse_info),
							      0,
							      FALSE);
			      aei = (yyvsp[(2) - (3)].st_bucket);
			    ;}
    break;

  case 229:
#line 2444 "aemilia_parser.y"
    {
			      (yyval.st_bucket) = ((yyvsp[(2) - (6)].st_bucket) == NULL)?
				     NULL:
				     (yyvsp[(6) - (6)].st_bucket);
			    ;}
    break;

  case 230:
#line 2453 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id(aei,
						         &(yyvsp[(1) - (1)].st_bucket),
						         0,
						         selector_enabled[0],
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if (((yyvsp[(1) - (1)].st_bucket) != NULL) &&
				  !selector_enabled[0])
			        handle_hiding((yyvsp[(1) - (1)].st_bucket),
					      FALSE);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 231:
#line 2467 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_internals(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 232:
#line 2474 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_interactions(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 233:
#line 2481 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        hide_aei_all(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 234:
#line 2491 "aemilia_parser.y"
    {
			    ;}
    break;

  case 235:
#line 2494 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			    ;}
    break;

  case 236:
#line 2501 "aemilia_parser.y"
    {
			    ;}
    break;

  case 237:
#line 2504 "aemilia_parser.y"
    {
			    ;}
    break;

  case 238:
#line 2507 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 239:
#line 2512 "aemilia_parser.y"
    {
			    ;}
    break;

  case 240:
#line 2515 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 241:
#line 2520 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 242:
#line 2525 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 243:
#line 2530 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 244:
#line 2538 "aemilia_parser.y"
    {
			    ;}
    break;

  case 245:
#line 2541 "aemilia_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      if (((yyvsp[(1) - (2)].st_bucket) != NULL) &&
				  ((yyvsp[(2) - (2)].st_bucket) != NULL))
				expand_iterative_restriction_decl((yyvsp[(2) - (2)].st_bucket));
			      selector[0] = NULL;
			    ;}
    break;

  case 246:
#line 2552 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							    (yyvsp[(3) - (3)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			      aei = (yyvsp[(2) - (3)].st_bucket);
			    ;}
    break;

  case 247:
#line 2561 "aemilia_parser.y"
    {
			    ;}
    break;

  case 248:
#line 2564 "aemilia_parser.y"
    {
			      restrict_obs_internals();
			    ;}
    break;

  case 249:
#line 2568 "aemilia_parser.y"
    {
			      restrict_obs_interactions();
			    ;}
    break;

  case 250:
#line 2572 "aemilia_parser.y"
    {
			      restrict_all_observables();
			    ;}
    break;

  case 251:
#line 2579 "aemilia_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							      (yyvsp[(3) - (3)].expr_parse_info),
							      0,
							      FALSE);
			      aei = (yyvsp[(2) - (3)].st_bucket);
			    ;}
    break;

  case 252:
#line 2587 "aemilia_parser.y"
    {
			      (yyval.st_bucket) = ((yyvsp[(2) - (6)].st_bucket) == NULL)?
				     NULL:
				     (yyvsp[(6) - (6)].st_bucket);
			    ;}
    break;

  case 253:
#line 2596 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id(aei,
						         &(yyvsp[(1) - (1)].st_bucket),
						         0,
						         selector_enabled[0],
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if (((yyvsp[(1) - (1)].st_bucket) != NULL) &&
				  !selector_enabled[0])
			        handle_restriction((yyvsp[(1) - (1)].st_bucket),
						   FALSE);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 254:
#line 2610 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_obs_internals(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 255:
#line 2617 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_obs_interactions(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 256:
#line 2624 "aemilia_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        restrict_aei_all_observables(aei);
			      (yyval.st_bucket) = (yyvsp[(1) - (1)].st_bucket);
			    ;}
    break;

  case 257:
#line 2634 "aemilia_parser.y"
    {
			    ;}
    break;

  case 258:
#line 2637 "aemilia_parser.y"
    {
			      no_declarations = FALSE;
			    ;}
    break;

  case 259:
#line 2644 "aemilia_parser.y"
    {
	      		    ;}
    break;

  case 260:
#line 2647 "aemilia_parser.y"
    {
			    ;}
    break;

  case 261:
#line 2650 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 262:
#line 2655 "aemilia_parser.y"
    {
			    ;}
    break;

  case 263:
#line 2658 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 264:
#line 2663 "aemilia_parser.y"
    {
			      handle_sync_aemilia();
			      yyerrok;
			    ;}
    break;

  case 265:
#line 2671 "aemilia_parser.y"
    {
			    ;}
    break;

  case 266:
#line 2674 "aemilia_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      if (((yyvsp[(1) - (2)].st_bucket) != NULL) &&
				  (yyvsp[(2) - (2)].boolean))
				expand_iterative_renaming_decl();
			      selector[0] = NULL;
			    ;}
    break;

  case 267:
#line 2685 "aemilia_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							    (yyvsp[(3) - (3)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			    ;}
    break;

  case 268:
#line 2693 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(2) - (6)].st_bucket),
						         &(yyvsp[(6) - (6)].st_bucket),
						         0,
						         FALSE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			      if ((yyvsp[(6) - (6)].st_bucket) != NULL)
			        handle_renaming_old(&(yyvsp[(6) - (6)].st_bucket),
						    FALSE);
			    ;}
    break;

  case 269:
#line 2705 "aemilia_parser.y"
    {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[(9) - (10)].st_bucket),
						                      (yyvsp[(10) - (10)].expr_parse_info),
								      ACT_TYPE_ID_USE);
			      if (((yyvsp[(6) - (10)].st_bucket) != NULL) &&
			          ((yyvsp[(9) - (10)].st_bucket) != NULL))
			        handle_renaming_new((yyvsp[(6) - (10)].st_bucket),
					            (yyvsp[(9) - (10)].st_bucket),
						    FALSE);
			    ;}
    break;

  case 270:
#line 2719 "aemilia_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(2) - (3)].st_bucket),
							      (yyvsp[(3) - (3)].expr_parse_info),
							      0,
							      FALSE);
			    ;}
    break;

  case 271:
#line 2726 "aemilia_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(2) - (6)].st_bucket),
						         &(yyvsp[(6) - (6)].st_bucket),
						         0,
						         TRUE,
						         FALSE,
						         ACT_TYPE_ID_USE);
			    ;}
    break;

  case 272:
#line 2735 "aemilia_parser.y"
    {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[(9) - (10)].st_bucket),
						                        (yyvsp[(10) - (10)].expr_parse_info),
						                        1,
								        ACT_TYPE_ID_USE);
			      (yyval.boolean) = (((yyvsp[(2) - (10)].st_bucket) != NULL) &&
				    ((yyvsp[(6) - (10)].st_bucket) != NULL) &&
				    ((yyvsp[(9) - (10)].st_bucket) != NULL));
			    ;}
    break;

  case 273:
#line 2748 "aemilia_parser.y"
    {
			      handle_iteration_1(&(yyvsp[(2) - (2)].st_bucket));
			    ;}
    break;

  case 274:
#line 2752 "aemilia_parser.y"
    {
			      handle_iteration_2(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 275:
#line 2756 "aemilia_parser.y"
    {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[(2) - (8)].st_bucket),
						      (yyvsp[(5) - (8)].expr_parse_info),
						      (yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 276:
#line 2765 "aemilia_parser.y"
    {
			      (yyval.st_bucket) = NULL;
			    ;}
    break;

  case 277:
#line 2769 "aemilia_parser.y"
    {
			      selector_index = 1;
			    ;}
    break;

  case 278:
#line 2773 "aemilia_parser.y"
    {
			      if (selector[1] == selector[0])
			      {
				signal_error(SAME_AEI_INDEX,
					     NULL,
					     NULL);
			        (yyval.st_bucket) = NULL;
			      }
			      else
			        (yyval.st_bucket) = (yyvsp[(3) - (3)].st_bucket);
			    ;}
    break;

  case 279:
#line 2788 "aemilia_parser.y"
    {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    ;}
    break;

  case 280:
#line 2793 "aemilia_parser.y"
    {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[(1) - (1)].expr_parse_info);
			    ;}
    break;

  case 281:
#line 2801 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (((yyvsp[(2) - (3)].expr_parse_info) != NULL) &&
				    !check_expr_all((yyvsp[(2) - (3)].expr_parse_info)->expr,
						    selector[0],
						    selector[1],
						    (ST_BUCKET *)search_lexeme_table("integer",
										     SYT),
						    AEI_INDEX_EXPR_NOT_UNDECL_ID_FREE,
						    AEI_INDEX_EXPR_NOT_RANDOMNESS_FREE,
						    ILL_TYPED_AEI_INDEX))?
				     NULL:
				     (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 282:
#line 2818 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     aet,
						     &local_var_actual_par_list,
						     parsing_behavior_term,
						     FALSE);
			    ;}
    break;

  case 283:
#line 2826 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 284:
#line 2831 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PLUS_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 285:
#line 2846 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MINUS_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 286:
#line 2861 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TIMES_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 287:
#line 2876 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(DIV_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 288:
#line 2891 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MOD_OP,
                                                                          (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                          (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                          NULL,
                                                                          0.0,
                                                                          NULL,
                                                                          FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 289:
#line 2906 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ABS_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 290:
#line 2919 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CEIL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 291:
#line 2932 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FLOOR_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 292:
#line 2945 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MIN_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 293:
#line 2960 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MAX_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 294:
#line 2975 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POWER_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 295:
#line 2990 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EPOWER_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 296:
#line 3003 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOGE_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 297:
#line 3016 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOG10_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 298:
#line 3029 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SQRT_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 299:
#line 3042 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SIN_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 300:
#line 3055 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(COS_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 301:
#line 3068 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(C_UNIFORM_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 302:
#line 3083 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ERLANG_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 303:
#line 3098 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GAMMA_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 304:
#line 3113 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EXPONENTIAL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 305:
#line 3126 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WEIBULL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 306:
#line 3141 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BETA_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 307:
#line 3156 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NORMAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 308:
#line 3171 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PARETO_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 309:
#line 3184 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(B_PARETO_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (8)].expr_parse_info)->local_var_list,
								                 concat_lists_no_dupls(
										   (yyvsp[(5) - (8)].expr_parse_info)->local_var_list,
									           (yyvsp[(7) - (8)].expr_parse_info)->local_var_list)));
			    ;}
    break;

  case 310:
#line 3202 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(D_UNIFORM_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 311:
#line 3217 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BERNOULLI_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (8)].expr_parse_info)->local_var_list,
								                 concat_lists_no_dupls(
										   (yyvsp[(5) - (8)].expr_parse_info)->local_var_list,
									           (yyvsp[(7) - (8)].expr_parse_info)->local_var_list)));
			    ;}
    break;

  case 312:
#line 3235 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BINOMIAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 313:
#line 3250 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POISSON_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 314:
#line 3263 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NEG_BINOMIAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 315:
#line 3278 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GEOMETRIC_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 316:
#line 3291 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PASCAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 317:
#line 3306 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 318:
#line 3311 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 319:
#line 3316 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(AND_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 320:
#line 3331 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(OR_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 321:
#line 3346 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(2) - (2)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NOT_OP,
                                                                           (yyvsp[(2) - (2)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(2) - (2)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 322:
#line 3359 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EQ_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 323:
#line 3374 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 324:
#line 3389 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LT_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 325:
#line 3404 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 326:
#line 3419 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GT_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 327:
#line 3434 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                 (yyvsp[(3) - (3)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 328:
#line 3449 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 329:
#line 3453 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FIRST_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 330:
#line 3466 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TAIL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 331:
#line 3479 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CONCAT_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 332:
#line 3494 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(INSERT_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 333:
#line 3509 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(REMOVE_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 334:
#line 3524 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LENGTH_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(3) - (4)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 335:
#line 3537 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 336:
#line 3541 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(READ_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (6)].expr_parse_info)->local_var_list,
								                 (yyvsp[(5) - (6)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 337:
#line 3556 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WRITE_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(3) - (8)].expr_parse_info)->local_var_list,
								                 concat_lists_no_dupls(
										   (yyvsp[(5) - (8)].expr_parse_info)->local_var_list,
									           (yyvsp[(7) - (8)].expr_parse_info)->local_var_list)));
			    ;}
    break;

  case 338:
#line 3574 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 339:
#line 3578 "aemilia_parser.y"
    {
			      check_id(REC_FIELD_ID_USE,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 340:
#line 3584 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (7)].st_bucket) == NULL) ||
				    ((yyvsp[(6) - (7)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GET_OP,
                                                                           (yyvsp[(3) - (7)].st_bucket),
                                                                           (yyvsp[(6) - (7)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
							   (yyvsp[(6) - (7)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 341:
#line 3598 "aemilia_parser.y"
    {
			      check_id(REC_FIELD_ID_USE,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 342:
#line 3604 "aemilia_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (9)].st_bucket) == NULL) ||
				    ((yyvsp[(6) - (9)].expr_parse_info) == NULL) ||
				    ((yyvsp[(8) - (9)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PUT_OP,
                                                                           (yyvsp[(3) - (9)].st_bucket),
                                                                           (yyvsp[(6) - (9)].expr_parse_info)->expr,
                                                                           (yyvsp[(8) - (9)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           concat_lists_no_dupls((yyvsp[(6) - (9)].expr_parse_info)->local_var_list,
								                 (yyvsp[(8) - (9)].expr_parse_info)->local_var_list));
			    ;}
    break;

  case 343:
#line 3620 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 344:
#line 3627 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info(set_expr_bucket(LIST_CONS_OP,
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
			    ;}
    break;

  case 345:
#line 3641 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LIST_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									   FALSE),
							   (yyvsp[(1) - (1)].lar_parse_info)->local_var_list);
			    ;}
    break;

  case 346:
#line 3657 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ARRAY_CONS_OP,
								           NULL,
								           NULL,
								           NULL,
								           (yyvsp[(1) - (1)].lar_parse_info)->value_num,
								           transform_list_into_array(
									     (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									     (yyvsp[(1) - (1)].lar_parse_info)->value_num),
								           FALSE),
						           (yyvsp[(1) - (1)].lar_parse_info)->local_var_list);
			    ;}
    break;

  case 347:
#line 3675 "aemilia_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(RECORD_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									   FALSE),
							   (yyvsp[(1) - (1)].lar_parse_info)->local_var_list);
			    ;}
    break;

  case 348:
#line 3691 "aemilia_parser.y"
    {
			      (yyval.lar_parse_info) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
						          (yyvsp[(1) - (1)].expr_parse_info)->local_var_list);
			    ;}
    break;

  case 349:
#line 3702 "aemilia_parser.y"
    {
			      (yyval.lar_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[(3) - (3)].lar_parse_info)->struct_value),
						          (yyvsp[(3) - (3)].lar_parse_info)->value_num + 1,
						          concat_lists_no_dupls((yyvsp[(1) - (3)].expr_parse_info)->local_var_list,
								                (yyvsp[(3) - (3)].lar_parse_info)->local_var_list));
			    ;}
    break;


/* Line 1267 of yacc.c.  */
#line 6642 "aemilia_parser.tab.c"
      default: break;
    }
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;


  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
      {
	YYSIZE_T yysize = yysyntax_error (0, yystate, yychar);
	if (yymsg_alloc < yysize && yymsg_alloc < YYSTACK_ALLOC_MAXIMUM)
	  {
	    YYSIZE_T yyalloc = 2 * yysize;
	    if (! (yysize <= yyalloc && yyalloc <= YYSTACK_ALLOC_MAXIMUM))
	      yyalloc = YYSTACK_ALLOC_MAXIMUM;
	    if (yymsg != yymsgbuf)
	      YYSTACK_FREE (yymsg);
	    yymsg = (char *) YYSTACK_ALLOC (yyalloc);
	    if (yymsg)
	      yymsg_alloc = yyalloc;
	    else
	      {
		yymsg = yymsgbuf;
		yymsg_alloc = sizeof yymsgbuf;
	      }
	  }

	if (0 < yysize && yysize <= yymsg_alloc)
	  {
	    (void) yysyntax_error (yymsg, yystate, yychar);
	    yyerror (yymsg);
	  }
	else
	  {
	    yyerror (YY_("syntax error"));
	    if (yysize != 0)
	      goto yyexhaustedlab;
	  }
      }
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse look-ahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse look-ahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (yyn != YYPACT_NINF)
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  if (yyn == YYFINAL)
    YYACCEPT;

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#ifndef yyoverflow
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEOF && yychar != YYEMPTY)
     yydestruct ("Cleanup: discarding lookahead",
		 yytoken, &yylval);
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


#line 3716 "aemilia_parser.y"



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

