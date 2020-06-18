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
#define yyparse simyyparse
#define yylex   simyylex
#define yyerror simyyerror
#define yylval  simyylval
#define yychar  simyychar
#define yydebug simyydebug
#define yynerrs simyynerrs


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     T_NUMBER = 300,
     T_ID = 301,
     T_TRACE_FILE_ID = 315,
     T_FROM = 413,
     T_FOR_ALL = 429,
     T_IN = 430,
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
     T_TRUE = 476,
     T_FALSE = 477,
     T_LIST_CONS = 479,
     T_FIRST = 480,
     T_TAIL = 481,
     T_CONCAT = 482,
     T_INSERT = 483,
     T_REMOVE = 484,
     T_LENGTH = 485,
     T_ARRAY_CONS = 487,
     T_READ = 488,
     T_WRITE = 489,
     T_RECORD_CONS = 491,
     T_GET = 492,
     T_PUT = 493,
     T_IS = 495,
     T_MEASURE = 539,
     T_RUN_LENGTH_ON_EXEC = 543,
     T_RUN_LENGTH = 544,
     T_RUN_NUMBER = 545,
     T_MEAN = 546,
     T_VARIANCE = 547,
     T_DISTRIBUTION = 548,
     T_REWARD = 549,
     T_EXECUTED = 550,
     T_CUMULATIVE = 551,
     T_NON_CUMULATIVE = 552,
     T_DRAW = 553,
     T_TRC = 554,
     DOTDOT = 700,
     NE = 701,
     LE = 702,
     GE = 703,
     AND = 704,
     OR = 705,
     IMPL = 706
   };
#endif
/* Tokens.  */
#define T_NUMBER 300
#define T_ID 301
#define T_TRACE_FILE_ID 315
#define T_FROM 413
#define T_FOR_ALL 429
#define T_IN 430
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
#define T_TRUE 476
#define T_FALSE 477
#define T_LIST_CONS 479
#define T_FIRST 480
#define T_TAIL 481
#define T_CONCAT 482
#define T_INSERT 483
#define T_REMOVE 484
#define T_LENGTH 485
#define T_ARRAY_CONS 487
#define T_READ 488
#define T_WRITE 489
#define T_RECORD_CONS 491
#define T_GET 492
#define T_PUT 493
#define T_IS 495
#define T_MEASURE 539
#define T_RUN_LENGTH_ON_EXEC 543
#define T_RUN_LENGTH 544
#define T_RUN_NUMBER 545
#define T_MEAN 546
#define T_VARIANCE 547
#define T_DISTRIBUTION 548
#define T_REWARD 549
#define T_EXECUTED 550
#define T_CUMULATIVE 551
#define T_NON_CUMULATIVE 552
#define T_DRAW 553
#define T_TRC 554
#define DOTDOT 700
#define NE 701
#define LE 702
#define GE 703
#define AND 704
#define OR 705
#define IMPL 706




/* Copy the first part of user declarations.  */
#line 41 "sim_parser.y"


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
#line 141 "sim_parser.y"
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
/* Line 193 of yacc.c.  */
#line 360 "sim_parser.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 373 "sim_parser.tab.c"

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
#define YYFINAL  12
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   5103

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  91
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  78
/* YYNRULES -- Number of rules.  */
#define YYNRULES  344
/* YYNRULES -- Number of states.  */
#define YYNSTATES  1225

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   707

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    74,     2,     2,     2,     2,     2,     2,
      86,    87,    80,    78,    84,    79,    88,    81,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    82,
      76,    75,    77,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    89,     2,    90,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    83,     2,    85,     2,     2,     2,     2,
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
       2,     2,     2,     2,     2,     5,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     6,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     7,
       8,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     9,    10,    11,    12,
      13,    14,    15,    16,    17,    18,    19,    20,    21,    22,
      23,    24,    25,    26,    27,    28,    29,    30,    31,    32,
      33,    34,    35,    36,    37,     2,    38,    39,     2,    40,
      41,    42,    43,    44,    45,    46,     2,    47,    48,    49,
       2,    50,    51,    52,     2,    53,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    54,
       2,     2,     2,    55,    56,    57,    58,    59,    60,    61,
      62,    63,    64,    65,    66,     2,     2,     2,     2,     2,
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
      67,    68,    69,    70,    71,    72,    73,     2
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     9,    10,    15,    16,    24,    25,    32,
      33,    39,    40,    45,    48,    51,    54,    57,    60,    62,
      66,    67,    72,    73,    78,    80,    83,    84,    91,    92,
      99,   100,   101,   112,   113,   114,   125,   126,   127,   128,
     142,   143,   144,   158,   160,   162,   166,   170,   174,   178,
     185,   190,   195,   200,   207,   214,   221,   226,   231,   236,
     241,   246,   251,   258,   265,   272,   277,   284,   291,   298,
     303,   312,   319,   328,   335,   340,   347,   352,   359,   361,
     363,   367,   371,   374,   378,   382,   386,   390,   394,   398,
     403,   408,   413,   420,   427,   434,   439,   444,   451,   460,
     465,   466,   474,   475,   485,   489,   490,   492,   494,   496,
     498,   502,   503,   509,   511,   513,   515,   519,   523,   527,
     531,   538,   543,   548,   553,   560,   567,   574,   579,   584,
     589,   594,   599,   604,   611,   618,   625,   630,   637,   644,
     651,   656,   665,   672,   681,   688,   693,   700,   705,   712,
     714,   716,   720,   724,   727,   731,   735,   739,   743,   747,
     751,   756,   761,   766,   773,   780,   787,   792,   797,   804,
     813,   818,   819,   827,   828,   838,   842,   843,   845,   847,
     849,   851,   855,   856,   864,   866,   868,   869,   871,   873,
     877,   878,   883,   884,   889,   891,   892,   896,   897,   906,
     907,   916,   918,   920,   924,   928,   932,   936,   943,   948,
     953,   958,   965,   972,   979,   984,   989,   994,   999,  1004,
    1009,  1016,  1023,  1030,  1035,  1042,  1049,  1056,  1061,  1070,
    1077,  1086,  1093,  1098,  1105,  1110,  1117,  1119,  1121,  1125,
    1129,  1132,  1136,  1140,  1144,  1148,  1152,  1156,  1161,  1166,
    1171,  1178,  1185,  1192,  1197,  1202,  1209,  1218,  1223,  1224,
    1232,  1233,  1243,  1247,  1248,  1250,  1252,  1254,  1256,  1260,
    1261,  1267,  1268,  1269,  1278,  1279,  1281,  1285,  1287,  1289,
    1293,  1297,  1301,  1305,  1312,  1317,  1322,  1327,  1334,  1341,
    1348,  1353,  1358,  1363,  1368,  1373,  1378,  1385,  1392,  1399,
    1404,  1411,  1418,  1425,  1430,  1439,  1446,  1455,  1462,  1467,
    1474,  1479,  1486,  1488,  1490,  1494,  1498,  1501,  1505,  1509,
    1513,  1517,  1521,  1525,  1530,  1535,  1540,  1547,  1554,  1561,
    1566,  1571,  1578,  1587,  1592,  1593,  1601,  1602,  1612,  1616,
    1617,  1619,  1621,  1623,  1625
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int16 yyrhs[] =
{
      92,     0,    -1,    98,    99,   100,   101,   138,    -1,    -1,
       1,    55,    93,    92,    -1,    -1,     1,    56,    94,    99,
     100,   101,   138,    -1,    -1,     1,    57,    95,   100,   101,
     138,    -1,    -1,     1,    54,    96,   101,   138,    -1,    -1,
       1,    65,    97,   138,    -1,    55,   126,    -1,    56,   162,
      -1,     1,    57,    -1,    57,   162,    -1,     1,    54,    -1,
     104,    -1,   104,    82,   101,    -1,    -1,     1,    82,   102,
     101,    -1,    -1,     1,    54,   103,   101,    -1,   105,    -1,
     157,   107,    -1,    -1,    54,     4,   160,   106,    53,   109,
      -1,    -1,    54,     4,   161,   108,    53,   109,    -1,    -1,
      -1,    58,    83,   117,    84,   162,   110,    67,   162,   111,
      85,    -1,    -1,    -1,    59,    83,   117,    84,   162,   112,
      67,   162,   113,    85,    -1,    -1,    -1,    -1,    60,    83,
     117,    84,   162,   114,    67,   162,   115,    84,   162,   116,
      85,    -1,    -1,    -1,    61,    86,    62,    86,   126,   118,
      87,    73,   128,   119,    84,   137,    87,    -1,     4,    -1,
       3,    -1,   117,    78,   117,    -1,   117,    79,   117,    -1,
     117,    80,   117,    -1,   117,    81,   117,    -1,     9,    86,
     117,    84,   117,    87,    -1,    12,    86,   117,    87,    -1,
      13,    86,   117,    87,    -1,    14,    86,   117,    87,    -1,
      10,    86,   117,    84,   117,    87,    -1,    11,    86,   117,
      84,   117,    87,    -1,    15,    86,   117,    84,   117,    87,
      -1,    16,    86,   117,    87,    -1,    17,    86,   117,    87,
      -1,    18,    86,   117,    87,    -1,    19,    86,   117,    87,
      -1,    20,    86,   117,    87,    -1,    21,    86,   117,    87,
      -1,    22,    86,   117,    84,   117,    87,    -1,    23,    86,
     117,    84,   117,    87,    -1,    24,    86,   117,    84,   117,
      87,    -1,    25,    86,   117,    87,    -1,    26,    86,   117,
      84,   117,    87,    -1,    27,    86,   117,    84,   117,    87,
      -1,    28,    86,   117,    84,   117,    87,    -1,    29,    86,
     117,    87,    -1,    30,    86,   117,    84,   117,    84,   117,
      87,    -1,    31,    86,   117,    84,   117,    87,    -1,    32,
      86,   117,    84,   117,    84,   117,    87,    -1,    33,    86,
     117,    84,   117,    87,    -1,    34,    86,   117,    87,    -1,
      35,    86,   117,    84,   117,    87,    -1,    36,    86,   117,
      87,    -1,    37,    86,   117,    84,   117,    87,    -1,    38,
      -1,    39,    -1,   117,    71,   117,    -1,   117,    72,   117,
      -1,    74,   117,    -1,   117,    75,   117,    -1,   117,    68,
     117,    -1,   117,    76,   117,    -1,   117,    69,   117,    -1,
     117,    77,   117,    -1,   117,    70,   117,    -1,    40,    86,
     122,    87,    -1,    41,    86,   117,    87,    -1,    42,    86,
     117,    87,    -1,    43,    86,   117,    84,   117,    87,    -1,
      44,    86,   117,    84,   117,    87,    -1,    45,    86,   117,
      84,   117,    87,    -1,    46,    86,   117,    87,    -1,    47,
      86,   123,    87,    -1,    48,    86,   117,    84,   117,    87,
      -1,    49,    86,   117,    84,   117,    84,   117,    87,    -1,
      50,    86,   124,    87,    -1,    -1,    51,    86,     4,   120,
      84,   117,    87,    -1,    -1,    52,    86,     4,   121,    84,
     117,    84,   117,    87,    -1,    86,   117,    87,    -1,    -1,
     125,    -1,   125,    -1,   125,    -1,   117,    -1,   117,    84,
     125,    -1,    -1,     4,   160,   127,    88,     4,    -1,   135,
      -1,     4,    -1,     3,    -1,   128,    78,   128,    -1,   128,
      79,   128,    -1,   128,    80,   128,    -1,   128,    81,   128,
      -1,     9,    86,   128,    84,   128,    87,    -1,    12,    86,
     128,    87,    -1,    13,    86,   128,    87,    -1,    14,    86,
     128,    87,    -1,    10,    86,   128,    84,   128,    87,    -1,
      11,    86,   128,    84,   128,    87,    -1,    15,    86,   128,
      84,   128,    87,    -1,    16,    86,   128,    87,    -1,    17,
      86,   128,    87,    -1,    18,    86,   128,    87,    -1,    19,
      86,   128,    87,    -1,    20,    86,   128,    87,    -1,    21,
      86,   128,    87,    -1,    22,    86,   128,    84,   128,    87,
      -1,    23,    86,   128,    84,   128,    87,    -1,    24,    86,
     128,    84,   128,    87,    -1,    25,    86,   128,    87,    -1,
      26,    86,   128,    84,   128,    87,    -1,    27,    86,   128,
      84,   128,    87,    -1,    28,    86,   128,    84,   128,    87,
      -1,    29,    86,   128,    87,    -1,    30,    86,   128,    84,
     128,    84,   128,    87,    -1,    31,    86,   128,    84,   128,
      87,    -1,    32,    86,   128,    84,   128,    84,   128,    87,
      -1,    33,    86,   128,    84,   128,    87,    -1,    34,    86,
     128,    87,    -1,    35,    86,   128,    84,   128,    87,    -1,
      36,    86,   128,    87,    -1,    37,    86,   128,    84,   128,
      87,    -1,    38,    -1,    39,    -1,   128,    71,   128,    -1,
     128,    72,   128,    -1,    74,   128,    -1,   128,    75,   128,
      -1,   128,    68,   128,    -1,   128,    76,   128,    -1,   128,
      69,   128,    -1,   128,    77,   128,    -1,   128,    70,   128,
      -1,    40,    86,   131,    87,    -1,    41,    86,   128,    87,
      -1,    42,    86,   128,    87,    -1,    43,    86,   128,    84,
     128,    87,    -1,    44,    86,   128,    84,   128,    87,    -1,
      45,    86,   128,    84,   128,    87,    -1,    46,    86,   128,
      87,    -1,    47,    86,   132,    87,    -1,    48,    86,   128,
      84,   128,    87,    -1,    49,    86,   128,    84,   128,    84,
     128,    87,    -1,    50,    86,   133,    87,    -1,    -1,    51,
      86,     4,   129,    84,   128,    87,    -1,    -1,    52,    86,
       4,   130,    84,   128,    84,   128,    87,    -1,    86,   128,
      87,    -1,    -1,   134,    -1,   134,    -1,   134,    -1,   128,
      -1,   128,    84,   134,    -1,    -1,     4,   160,   136,    88,
       4,    88,     4,    -1,    63,    -1,    64,    -1,    -1,   139,
      -1,   142,    -1,   142,    82,   139,    -1,    -1,     1,    82,
     140,   139,    -1,    -1,     1,    65,   141,   139,    -1,   144,
      -1,    -1,   157,   143,   146,    -1,    -1,    65,   148,   145,
       6,     5,   160,    88,    66,    -1,    -1,    65,   148,   147,
       6,     5,   161,    88,    66,    -1,   155,    -1,     3,    -1,
     148,    78,   148,    -1,   148,    79,   148,    -1,   148,    80,
     148,    -1,   148,    81,   148,    -1,     9,    86,   148,    84,
     148,    87,    -1,    12,    86,   148,    87,    -1,    13,    86,
     148,    87,    -1,    14,    86,   148,    87,    -1,    10,    86,
     148,    84,   148,    87,    -1,    11,    86,   148,    84,   148,
      87,    -1,    15,    86,   148,    84,   148,    87,    -1,    16,
      86,   148,    87,    -1,    17,    86,   148,    87,    -1,    18,
      86,   148,    87,    -1,    19,    86,   148,    87,    -1,    20,
      86,   148,    87,    -1,    21,    86,   148,    87,    -1,    22,
      86,   148,    84,   148,    87,    -1,    23,    86,   148,    84,
     148,    87,    -1,    24,    86,   148,    84,   148,    87,    -1,
      25,    86,   148,    87,    -1,    26,    86,   148,    84,   148,
      87,    -1,    27,    86,   148,    84,   148,    87,    -1,    28,
      86,   148,    84,   148,    87,    -1,    29,    86,   148,    87,
      -1,    30,    86,   148,    84,   148,    84,   148,    87,    -1,
      31,    86,   148,    84,   148,    87,    -1,    32,    86,   148,
      84,   148,    84,   148,    87,    -1,    33,    86,   148,    84,
     148,    87,    -1,    34,    86,   148,    87,    -1,    35,    86,
     148,    84,   148,    87,    -1,    36,    86,   148,    87,    -1,
      37,    86,   148,    84,   148,    87,    -1,    38,    -1,    39,
      -1,   148,    71,   148,    -1,   148,    72,   148,    -1,    74,
     148,    -1,   148,    75,   148,    -1,   148,    68,   148,    -1,
     148,    76,   148,    -1,   148,    69,   148,    -1,   148,    77,
     148,    -1,   148,    70,   148,    -1,    40,    86,   151,    87,
      -1,    41,    86,   148,    87,    -1,    42,    86,   148,    87,
      -1,    43,    86,   148,    84,   148,    87,    -1,    44,    86,
     148,    84,   148,    87,    -1,    45,    86,   148,    84,   148,
      87,    -1,    46,    86,   148,    87,    -1,    47,    86,   152,
      87,    -1,    48,    86,   148,    84,   148,    87,    -1,    49,
      86,   148,    84,   148,    84,   148,    87,    -1,    50,    86,
     153,    87,    -1,    -1,    51,    86,     4,   149,    84,   148,
      87,    -1,    -1,    52,    86,     4,   150,    84,   148,    84,
     148,    87,    -1,    86,   148,    87,    -1,    -1,   154,    -1,
     154,    -1,   154,    -1,   148,    -1,   148,    84,   154,    -1,
      -1,     4,   160,   156,    88,     4,    -1,    -1,    -1,     7,
       4,   158,     8,   162,   159,    67,   162,    -1,    -1,   161,
      -1,    89,   162,    90,    -1,     4,    -1,     3,    -1,   162,
      78,   162,    -1,   162,    79,   162,    -1,   162,    80,   162,
      -1,   162,    81,   162,    -1,     9,    86,   162,    84,   162,
      87,    -1,    12,    86,   162,    87,    -1,    13,    86,   162,
      87,    -1,    14,    86,   162,    87,    -1,    10,    86,   162,
      84,   162,    87,    -1,    11,    86,   162,    84,   162,    87,
      -1,    15,    86,   162,    84,   162,    87,    -1,    16,    86,
     162,    87,    -1,    17,    86,   162,    87,    -1,    18,    86,
     162,    87,    -1,    19,    86,   162,    87,    -1,    20,    86,
     162,    87,    -1,    21,    86,   162,    87,    -1,    22,    86,
     162,    84,   162,    87,    -1,    23,    86,   162,    84,   162,
      87,    -1,    24,    86,   162,    84,   162,    87,    -1,    25,
      86,   162,    87,    -1,    26,    86,   162,    84,   162,    87,
      -1,    27,    86,   162,    84,   162,    87,    -1,    28,    86,
     162,    84,   162,    87,    -1,    29,    86,   162,    87,    -1,
      30,    86,   162,    84,   162,    84,   162,    87,    -1,    31,
      86,   162,    84,   162,    87,    -1,    32,    86,   162,    84,
     162,    84,   162,    87,    -1,    33,    86,   162,    84,   162,
      87,    -1,    34,    86,   162,    87,    -1,    35,    86,   162,
      84,   162,    87,    -1,    36,    86,   162,    87,    -1,    37,
      86,   162,    84,   162,    87,    -1,    38,    -1,    39,    -1,
     162,    71,   162,    -1,   162,    72,   162,    -1,    74,   162,
      -1,   162,    75,   162,    -1,   162,    68,   162,    -1,   162,
      76,   162,    -1,   162,    69,   162,    -1,   162,    77,   162,
      -1,   162,    70,   162,    -1,    40,    86,   165,    87,    -1,
      41,    86,   162,    87,    -1,    42,    86,   162,    87,    -1,
      43,    86,   162,    84,   162,    87,    -1,    44,    86,   162,
      84,   162,    87,    -1,    45,    86,   162,    84,   162,    87,
      -1,    46,    86,   162,    87,    -1,    47,    86,   166,    87,
      -1,    48,    86,   162,    84,   162,    87,    -1,    49,    86,
     162,    84,   162,    84,   162,    87,    -1,    50,    86,   167,
      87,    -1,    -1,    51,    86,     4,   163,    84,   162,    87,
      -1,    -1,    52,    86,     4,   164,    84,   162,    84,   162,
      87,    -1,    86,   162,    87,    -1,    -1,   168,    -1,   168,
      -1,   168,    -1,   162,    -1,   162,    84,   168,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   308,   308,   312,   311,   320,   319,   328,   327,   336,
     335,   344,   343,   354,   362,   384,   392,   415,   423,   427,
     433,   432,   441,   440,   451,   458,   471,   470,   492,   491,
     515,   520,   514,   538,   543,   537,   561,   566,   572,   560,
     595,   603,   594,   627,   635,   640,   654,   668,   682,   696,
     710,   723,   736,   749,   763,   777,   791,   804,   817,   830,
     843,   856,   869,   883,   897,   911,   924,   938,   952,   966,
     979,   994,  1008,  1023,  1037,  1050,  1064,  1077,  1091,  1096,
    1101,  1115,  1129,  1142,  1156,  1170,  1184,  1198,  1212,  1226,
    1230,  1243,  1256,  1270,  1284,  1298,  1311,  1315,  1329,  1344,
    1349,  1348,  1369,  1368,  1389,  1397,  1410,  1426,  1444,  1460,
    1471,  1487,  1486,  1515,  1527,  1535,  1540,  1554,  1568,  1582,
    1596,  1610,  1623,  1636,  1649,  1663,  1677,  1691,  1704,  1717,
    1730,  1743,  1756,  1769,  1783,  1797,  1811,  1824,  1838,  1852,
    1866,  1879,  1894,  1908,  1923,  1937,  1950,  1964,  1977,  1991,
    1996,  2001,  2015,  2029,  2042,  2056,  2070,  2084,  2098,  2112,
    2126,  2130,  2143,  2156,  2170,  2184,  2198,  2211,  2215,  2229,
    2244,  2249,  2248,  2269,  2268,  2289,  2297,  2310,  2326,  2344,
    2360,  2371,  2387,  2386,  2417,  2421,  2429,  2432,  2439,  2443,
    2449,  2448,  2458,  2457,  2469,  2477,  2476,  2493,  2492,  2526,
    2525,  2559,  2566,  2571,  2585,  2599,  2613,  2627,  2641,  2654,
    2667,  2680,  2694,  2708,  2722,  2735,  2748,  2761,  2774,  2787,
    2800,  2814,  2828,  2842,  2855,  2869,  2883,  2897,  2910,  2925,
    2939,  2954,  2968,  2981,  2995,  3008,  3022,  3027,  3032,  3046,
    3060,  3073,  3087,  3101,  3115,  3129,  3143,  3157,  3161,  3174,
    3187,  3201,  3215,  3229,  3242,  3246,  3260,  3275,  3280,  3279,
    3300,  3299,  3320,  3328,  3341,  3357,  3375,  3391,  3402,  3418,
    3417,  3447,  3451,  3446,  3464,  3468,  3476,  3493,  3501,  3506,
    3520,  3534,  3548,  3562,  3576,  3589,  3602,  3615,  3629,  3643,
    3657,  3670,  3683,  3696,  3709,  3722,  3735,  3749,  3763,  3777,
    3790,  3804,  3818,  3832,  3845,  3860,  3874,  3889,  3903,  3916,
    3930,  3943,  3957,  3962,  3967,  3981,  3995,  4008,  4022,  4036,
    4050,  4064,  4078,  4092,  4096,  4109,  4122,  4136,  4150,  4164,
    4177,  4181,  4195,  4210,  4215,  4214,  4235,  4234,  4255,  4263,
    4276,  4292,  4310,  4326,  4337
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "T_NUMBER", "T_ID", "T_TRACE_FILE_ID",
  "T_FROM", "T_FOR_ALL", "T_IN", "T_MOD", "T_MIN", "T_MAX", "T_ABS",
  "T_CEIL", "T_FLOOR", "T_POWER", "T_EPOWER", "T_LOGE", "T_LOG10",
  "T_SQRT", "T_SIN", "T_COS", "T_C_UNIFORM", "T_ERLANG", "T_GAMMA",
  "T_EXPONENTIAL", "T_WEIBULL", "T_BETA", "T_NORMAL", "T_PARETO",
  "T_B_PARETO", "T_D_UNIFORM", "T_BERNOULLI", "T_BINOMIAL", "T_POISSON",
  "T_NEG_BINOMIAL", "T_GEOMETRIC", "T_PASCAL", "T_TRUE", "T_FALSE",
  "T_LIST_CONS", "T_FIRST", "T_TAIL", "T_CONCAT", "T_INSERT", "T_REMOVE",
  "T_LENGTH", "T_ARRAY_CONS", "T_READ", "T_WRITE", "T_RECORD_CONS",
  "T_GET", "T_PUT", "T_IS", "T_MEASURE", "T_RUN_LENGTH_ON_EXEC",
  "T_RUN_LENGTH", "T_RUN_NUMBER", "T_MEAN", "T_VARIANCE", "T_DISTRIBUTION",
  "T_REWARD", "T_EXECUTED", "T_CUMULATIVE", "T_NON_CUMULATIVE", "T_DRAW",
  "T_TRC", "DOTDOT", "NE", "LE", "GE", "AND", "OR", "IMPL", "'!'", "'='",
  "'<'", "'>'", "'+'", "'-'", "'*'", "'/'", "';'", "'{'", "','", "'}'",
  "'('", "')'", "'.'", "'['", "']'", "$accept", "simulation_def", "@1",
  "@2", "@3", "@4", "@5", "clock_act_type", "sim_run_length",
  "sim_run_num", "measure_def_list", "@6", "@7", "measure_def",
  "s_measure_def", "@8", "i_measure_def", "@9", "sim_measure_evd", "@10",
  "@11", "@12", "@13", "@14", "@15", "@16", "sim_measure_expr", "@17",
  "@18", "@19", "@20", "sim_expr_list", "sim_expr_array",
  "sim_expr_record", "sim_struct_expr", "act_type", "@21", "rew_expr",
  "@22", "@23", "rew_expr_list", "rew_expr_array", "rew_expr_record",
  "rew_struct_expr", "aei_behav_var", "@24", "cumulative",
  "trace_def_list", "trace_def_list1", "@25", "@26", "trace_def", "@27",
  "s_trace_def", "@28", "i_trace_def", "@29", "trace_expr", "@30", "@31",
  "trc_expr_list", "trc_expr_array", "trc_expr_record", "trc_struct_expr",
  "aei_const_par", "@32", "iteration", "@33", "@34", "poss_aei_index",
  "aei_index", "expr", "@35", "@36", "expr_list", "expr_array",
  "expr_record", "struct_expr", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   707,   300,   301,   315,   413,   429,   430,   446,
     447,   448,   449,   450,   451,   452,   453,   454,   455,   456,
     457,   458,   459,   460,   461,   462,   463,   464,   465,   466,
     467,   468,   469,   470,   471,   472,   473,   474,   476,   477,
     479,   480,   481,   482,   483,   484,   485,   487,   488,   489,
     491,   492,   493,   495,   539,   543,   544,   545,   546,   547,
     548,   549,   550,   551,   552,   553,   554,   700,   701,   702,
     703,   704,   705,   706,    33,    61,    60,    62,    43,    45,
      42,    47,    59,   123,    44,   125,    40,    41,    46,    91,
      93
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    91,    92,    93,    92,    94,    92,    95,    92,    96,
      92,    97,    92,    98,    99,    99,   100,   100,   101,   101,
     102,   101,   103,   101,   104,   104,   106,   105,   108,   107,
     110,   111,   109,   112,   113,   109,   114,   115,   116,   109,
     118,   119,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     120,   117,   121,   117,   117,   122,   122,   123,   124,   125,
     125,   127,   126,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   129,   128,   130,   128,   128,   131,   131,   132,   133,
     134,   134,   136,   135,   137,   137,   138,   138,   139,   139,
     140,   139,   141,   139,   142,   143,   142,   145,   144,   147,
     146,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   149,   148,
     150,   148,   148,   151,   151,   152,   153,   154,   154,   156,
     155,   158,   159,   157,   160,   160,   161,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   163,   162,   164,   162,   162,   165,
     165,   166,   167,   168,   168
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     0,     4,     0,     7,     0,     6,     0,
       5,     0,     4,     2,     2,     2,     2,     2,     1,     3,
       0,     4,     0,     4,     1,     2,     0,     6,     0,     6,
       0,     0,    10,     0,     0,    10,     0,     0,     0,    13,
       0,     0,    13,     1,     1,     3,     3,     3,     3,     6,
       4,     4,     4,     6,     6,     6,     4,     4,     4,     4,
       4,     4,     6,     6,     6,     4,     6,     6,     6,     4,
       8,     6,     8,     6,     4,     6,     4,     6,     1,     1,
       3,     3,     2,     3,     3,     3,     3,     3,     3,     4,
       4,     4,     6,     6,     6,     4,     4,     6,     8,     4,
       0,     7,     0,     9,     3,     0,     1,     1,     1,     1,
       3,     0,     5,     1,     1,     1,     3,     3,     3,     3,
       6,     4,     4,     4,     6,     6,     6,     4,     4,     4,
       4,     4,     4,     6,     6,     6,     4,     6,     6,     6,
       4,     8,     6,     8,     6,     4,     6,     4,     6,     1,
       1,     3,     3,     2,     3,     3,     3,     3,     3,     3,
       4,     4,     4,     6,     6,     6,     4,     4,     6,     8,
       4,     0,     7,     0,     9,     3,     0,     1,     1,     1,
       1,     3,     0,     7,     1,     1,     0,     1,     1,     3,
       0,     4,     0,     4,     1,     0,     3,     0,     8,     0,
       8,     1,     1,     3,     3,     3,     3,     6,     4,     4,
       4,     6,     6,     6,     4,     4,     4,     4,     4,     4,
       6,     6,     6,     4,     6,     6,     6,     4,     8,     6,
       8,     6,     4,     6,     4,     6,     1,     1,     3,     3,
       2,     3,     3,     3,     3,     3,     3,     4,     4,     4,
       6,     6,     6,     4,     4,     6,     8,     4,     0,     7,
       0,     9,     3,     0,     1,     1,     1,     1,     3,     0,
       5,     0,     0,     8,     0,     1,     3,     1,     1,     3,
       3,     3,     3,     6,     4,     4,     4,     6,     6,     6,
       4,     4,     4,     4,     4,     4,     6,     6,     6,     4,
       6,     6,     6,     4,     8,     6,     8,     6,     4,     6,
       4,     6,     1,     1,     3,     3,     2,     3,     3,     3,
       3,     3,     3,     4,     4,     4,     6,     6,     6,     4,
       4,     6,     8,     4,     0,     7,     0,     9,     3,     0,
       1,     1,     1,     1,     3
};

/* YYDEFACT[STATE-NAME] -- Default rule to reduce with in state
   STATE-NUM when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint16 yydefact[] =
{
       0,     0,     0,     0,     0,     9,     3,     5,     7,    11,
     274,    13,     1,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   111,   275,    15,   278,   277,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   312,   313,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    14,     0,     0,     0,     0,     0,     0,
       0,    18,    24,     0,     4,     0,     0,     0,     0,    12,
     187,   188,   194,   195,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   339,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   316,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    17,    16,     0,    22,    20,   271,   274,    10,
       0,     0,    25,     0,     0,   192,   190,   202,   274,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   236,   237,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   197,   201,     0,     0,   276,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     343,     0,   340,     0,     0,     0,     0,     0,     0,     0,
     341,     0,     0,     0,   342,   334,   336,   338,   318,   320,
     322,   314,   315,   317,   319,   321,   279,   280,   281,   282,
       2,     0,     0,     0,    26,    19,     0,     0,     8,     0,
       0,   269,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   263,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   240,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   189,
       0,   196,   112,     0,     0,     0,   284,   285,   286,     0,
     290,   291,   292,   293,   294,   295,     0,     0,     0,   299,
       0,     0,     0,   303,     0,     0,     0,     0,   308,     0,
     310,     0,     0,   323,   324,   325,     0,     0,     0,   329,
     330,     0,     0,   333,     0,     0,    23,    21,     0,     0,
      28,     6,   193,   191,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   267,     0,   264,     0,     0,     0,
       0,     0,     0,     0,   265,     0,     0,     0,   266,   258,
     260,   262,   242,   244,   246,   238,   239,   241,   243,   245,
     203,   204,   205,   206,     0,   199,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,   344,     0,     0,     0,     0,     0,     0,     0,
     272,     0,     0,     0,     0,     0,     0,   208,   209,   210,
       0,   214,   215,   216,   217,   218,   219,     0,     0,     0,
     223,     0,     0,     0,   227,     0,     0,     0,     0,   232,
       0,   234,     0,     0,   247,   248,   249,     0,     0,     0,
     253,   254,     0,     0,   257,     0,     0,   274,     0,   283,
     287,   288,   289,   296,   297,   298,   300,   301,   302,     0,
     305,     0,   307,   309,   311,   326,   327,   328,   331,     0,
       0,     0,     0,     0,     0,     0,    27,     0,   270,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   268,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   335,     0,
       0,     0,     0,     0,    29,   207,   211,   212,   213,   220,
     221,   222,   224,   225,   226,     0,   229,     0,   231,   233,
     235,   250,   251,   252,   255,     0,     0,     0,     0,     0,
     304,   306,   332,     0,   273,    44,    43,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    78,    79,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     259,     0,   198,     0,   337,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   105,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    82,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   228,   230,   256,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   109,
       0,   106,     0,     0,     0,     0,     0,     0,     0,   107,
       0,     0,     0,   108,   100,   102,     0,   104,    84,    86,
      88,    80,    81,    83,    85,    87,    45,    46,    47,    48,
      30,    33,    36,   261,   200,     0,     0,     0,    50,    51,
      52,     0,    56,    57,    58,    59,    60,    61,     0,     0,
       0,    65,     0,     0,     0,    69,     0,     0,     0,     0,
      74,     0,    76,     0,     0,    89,    90,    91,     0,     0,
       0,    95,    96,     0,     0,    99,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   110,     0,
       0,     0,     0,     0,     0,     0,    40,     0,     0,     0,
      49,    53,    54,    55,    62,    63,    64,    66,    67,    68,
       0,    71,     0,    73,    75,    77,    92,    93,    94,    97,
       0,     0,     0,     0,    31,    34,    37,     0,     0,     0,
     101,     0,     0,     0,     0,     0,    70,    72,    98,     0,
       0,    32,    35,     0,   103,   115,   114,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   149,   150,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,    41,   113,    38,   182,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,   176,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,   153,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,   180,     0,   177,     0,     0,
       0,     0,     0,     0,     0,   178,     0,     0,     0,   179,
     171,   173,   175,   155,   157,   159,   151,   152,   154,   156,
     158,   116,   117,   118,   119,     0,    39,     0,     0,     0,
       0,   121,   122,   123,     0,   127,   128,   129,   130,   131,
     132,     0,     0,     0,   136,     0,     0,     0,   140,     0,
       0,     0,     0,   145,     0,   147,     0,     0,   160,   161,
     162,     0,     0,     0,   166,   167,     0,     0,   170,     0,
       0,   184,   185,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,   181,     0,     0,     0,     0,     0,     0,     0,    42,
       0,   120,   124,   125,   126,   133,   134,   135,   137,   138,
     139,     0,   142,     0,   144,   146,   148,   163,   164,   165,
     168,     0,     0,     0,   183,     0,     0,     0,   172,     0,
     141,   143,   169,     0,   174
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     3,    17,    18,    19,    16,    20,     4,    15,    76,
      80,   282,   281,    81,    82,   399,   162,   492,   566,   869,
     933,   870,   934,   871,   935,  1054,   789,   923,  1053,   866,
     867,   790,   798,   802,   791,    11,    95,  1085,  1159,  1160,
    1086,  1094,  1098,  1087,   994,  1055,  1163,    89,    90,   290,
     289,    91,   218,    92,   348,   351,   538,   434,   535,   536,
     435,   443,   447,   436,   216,   404,    93,   283,   562,    22,
      23,   250,   394,   395,   251,   259,   263,   252
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -1007
static const yytype_int16 yypact[] =
{
      17,     8,    19,    45,    11, -1007, -1007, -1007, -1007, -1007,
     -68, -1007, -1007,     2,   941,    14,    21,    17,    11,    14,
      53,   941, -1007, -1007, -1007, -1007, -1007,   -20,   -16,    -7,
      56,    58,    59,    60,    62,    63,    64,    65,    68,    75,
      76,    84,    85,    91,   100,   101,   102,   103,   106,   107,
     108,   110,   118,   119,   127,   128, -1007, -1007,   130,   160,
     167,   171,   172,   173,   174,   196,   197,   211,   219,   220,
     221,   941,   941,  4924,    27,   941,    21,   -35,   143,   186,
      53,   226, -1007,   255, -1007,    14,    21,   -36,  1100, -1007,
   -1007,   249, -1007, -1007,    88,   254,   941,   941,   941,   941,
     941,   941,   941,   941,   941,   941,   941,   941,   941,   941,
     941,   941,   941,   941,   941,   941,   941,   941,   941,   941,
     941,   941,   941,   941,   941,   941,   941,   941,   941,   941,
     941,   941,   941,   941,   941,   941,   339,   343,   451,   -37,
     941,   941,   941,   941,   941,   941,   941,   941,   941,   941,
     941,   941, -1007,  4924,    53, -1007, -1007, -1007,   -68, -1007,
      21,   344, -1007,    21,    53, -1007, -1007, -1007,   -68,   263,
     264,   265,   269,   273,   278,   280,   282,   283,   284,   285,
     289,   290,   293,   295,   296,   297,   304,   305,   319,   327,
     328,   329,   330,   336,   337,   338,   340,   341, -1007, -1007,
     342,   345,   349,   353,   358,   360,   362,   363,   364,   365,
     369,   370,   378,  1100,  1100,  4938, -1007,     9,   352, -1007,
     426,   131,  3122,  3139,   104,   209,   223,  3156,   243,   257,
     317,   331,   391,   411,  3173,  3190,  3207,   425,  3224,  3241,
    3258,   483,  3275,  3292,  3309,  3326,   497,  3343,   519,  3360,
    3377,   386, -1007,   722,   743,  3394,  3411,  3428,   768,   389,
   -1007,  3445,  3462,   390, -1007, -1007, -1007, -1007,   533,   533,
     533,   451,   451,   533,   533,   533,   -44,   -44, -1007, -1007,
   -1007,    21,    21,   421, -1007, -1007,   -68,    53, -1007,     9,
       9, -1007,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,
    1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,
    1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,
    1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,
    1100,  1100,   461,   480,  1018,   788,  1100,  1100,  1100,  1100,
    1100,  1100,  1100,  1100,  1100,  1100,  1100,  1100,   479, -1007,
    1100, -1007, -1007,   941,   941,   941, -1007, -1007, -1007,   941,
   -1007, -1007, -1007, -1007, -1007, -1007,   941,   941,   941, -1007,
     941,   941,   941, -1007,   941,   941,   941,   941, -1007,   941,
   -1007,   941,   941, -1007, -1007, -1007,   941,   941,   941, -1007,
   -1007,   941,   941, -1007,   415,   423, -1007, -1007,   941,   455,
   -1007, -1007, -1007, -1007,   422,  3479,  3496,  3513,   979,   993,
    1085,  3530,  1163,  1190,  1204,  1218,  1232,  1246,  3547,  3564,
    3581,  1260,  3598,  3615,  3632,  1274,  3649,  3666,  3683,  3700,
    1288,  3717,  1302,  3734,  3751,   424, -1007,  1316,  1330,  3768,
    3785,  3802,  1344,   427, -1007,  3819,  3836,   428, -1007, -1007,
   -1007, -1007,  4966,  4966,  4966,  1018,  1018,  4966,  4966,  4966,
     -32,   -32, -1007, -1007,   504,  4938,  1358,  1372,  1386,  1400,
    1414,  1428,  1442,  1456,  1470,  1484,  3853,  1498,  3870,  1512,
    1526,  1540, -1007,  1554,  1568,  1582,  1596,  3887,   941,   941,
    4924,   -34,   460,   514,  1100,  1100,  1100, -1007, -1007, -1007,
    1100, -1007, -1007, -1007, -1007, -1007, -1007,  1100,  1100,  1100,
   -1007,  1100,  1100,  1100, -1007,  1100,  1100,  1100,  1100, -1007,
    1100, -1007,  1100,  1100, -1007, -1007, -1007,  1100,  1100,  1100,
   -1007, -1007,  1100,  1100, -1007,   438,   449,   -68,   528, -1007,
   -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007,   941,
   -1007,   941, -1007, -1007, -1007, -1007, -1007, -1007, -1007,   941,
    1610,  3904,   469,   456,   458,   463, -1007,   -34, -1007,  1624,
    1638,  1652,  1666,  1680,  1694,  1708,  1722,  1736,  1750,  3921,
    1764,  3938,  1778,  1792,  1806, -1007,  1820,  1834,  1848,  1862,
    3955,  1100,  1100,   450,   538,  1876,  1890,  1904, -1007,   941,
     941,   671,   671,   671, -1007, -1007, -1007, -1007, -1007, -1007,
   -1007, -1007, -1007, -1007, -1007,  1100, -1007,  1100, -1007, -1007,
   -1007, -1007, -1007, -1007, -1007,  1100,  1918,  3972,   478,   -68,
   -1007, -1007, -1007,  1932,  4924, -1007, -1007,   462,   470,   471,
     485,   493,   494,   495,   496,   506,   507,   521,   529,   560,
     567,   570,   571,   572,   573,   574,   575,   590,   591,   592,
     593,   638,   639,   640,   641,   642, -1007, -1007,   643,   644,
     645,   647,   648,   649,   650,   651,   655,   656,   657,   658,
     660,   664,   671,   671,  3989,  4006,  4023,  1946,  1960,  1974,
   -1007,  1100, -1007,   459, -1007,   671,   671,   671,   671,   671,
     671,   671,   671,   671,   671,   671,   671,   671,   671,   671,
     671,   671,   671,   671,   671,   671,   671,   671,   671,   671,
     671,   671,   671,   671,   671,   671,   671,   671,   671,   671,
     671,   671,   671,   671,   671,   545,   579,   689,  4980,  1988,
     671,   671,   671,   671,   671,   671,   671,   671,   671,   671,
     671,   671,   941,   941,   941, -1007, -1007, -1007,  2002,   686,
    4040,  4057,  4074,  2016,  2030,  2044,  4091,  2058,  2072,  2086,
    2100,  2114,  2128,  4108,  4125,  4142,  2142,  4159,  4176,  4193,
    2156,  4210,  4227,  4244,  4261,  2170,  4278,  2184,  4295,  4312,
     667, -1007,  2198,  2212,  4329,  4346,  4363,  2226,   668, -1007,
    4380,  4397,   669, -1007, -1007, -1007,   672, -1007,  4994,  4994,
    4994,  4980,  4980,  4994,  4994,  4994,   -29,   -29, -1007, -1007,
    4924,  4924,  4924, -1007, -1007,   671,   671,   671, -1007, -1007,
   -1007,   671, -1007, -1007, -1007, -1007, -1007, -1007,   671,   671,
     671, -1007,   671,   671,   671, -1007,   671,   671,   671,   671,
   -1007,   671, -1007,   671,   671, -1007, -1007, -1007,   671,   671,
     671, -1007, -1007,   671,   671, -1007,   675,   679,    19,   700,
     705,   707,  2240,  2254,  2268,  2282,  2296,  2310,  2324,  2338,
    2352,  2366,  4414,  2380,  4431,  2394,  2408,  2422, -1007,  2436,
    2450,  2464,  2478,  4448,   671,   671, -1007,   941,   941,   941,
   -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007,
     671, -1007,   671, -1007, -1007, -1007, -1007, -1007, -1007, -1007,
     671,  2492,  4465,   690,  4924,  4924,  4924,  2506,  2520,  2534,
   -1007,   671,   703,   693,   694,   699, -1007, -1007, -1007,  2548,
    1178, -1007, -1007,   941, -1007, -1007,   -33,   698,   701,   702,
     709,   710,   718,   719,   720,   721,   724,   739,   740,   741,
     742,   745,   747,   749,   755,   764,   765,   766,   775,   776,
     784,   785,   786,   787,   790,   791, -1007, -1007,   792,   793,
     796,   829,   836,   839,   840,   841,   842,   856,   857,   860,
     861,  1178,  1178,  4952, -1007,  4924, -1007,  1178,  1178,  1178,
    1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,
    1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,
    1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,
    1178,  1178,  1178,  1178,  1178,  1178,  1178,   804,   850,  5008,
    2562,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,  1178,
    1178,  1178,  1178,   864,   789,   906,  4482,  4499,  4516,  2576,
    2590,  2604,  4533,  2618,  2632,  2646,  2660,  2674,  2688,  4550,
    4567,  4584,  2702,  4601,  4618,  4635,  2716,  4652,  4669,  4686,
    4703,  2730,  4720,  2744,  4737,  4754,   862, -1007,  2758,  2772,
    4771,  4788,  4805,  2786,   908, -1007,  4822,  4839,   909, -1007,
   -1007, -1007, -1007,  5022,  5022,  5022,  5008,  5008,  5022,  5022,
    5022,    42,    42, -1007, -1007,    61, -1007,   994,  1178,  1178,
    1178, -1007, -1007, -1007,  1178, -1007, -1007, -1007, -1007, -1007,
   -1007,  1178,  1178,  1178, -1007,  1178,  1178,  1178, -1007,  1178,
    1178,  1178,  1178, -1007,  1178, -1007,  1178,  1178, -1007, -1007,
   -1007,  1178,  1178,  1178, -1007, -1007,  1178,  1178, -1007,   913,
     915, -1007, -1007,   914,   912,  2800,  2814,  2828,  2842,  2856,
    2870,  2884,  2898,  2912,  2926,  4856,  2940,  4873,  2954,  2968,
    2982, -1007,  2996,  3010,  3024,  3038,  4890,  1178,  1178, -1007,
     998, -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007, -1007,
   -1007,  1178, -1007,  1178, -1007, -1007, -1007, -1007, -1007, -1007,
   -1007,  1178,  3052,  4907, -1007,  3066,  3080,  3094, -1007,  1178,
   -1007, -1007, -1007,  3108, -1007
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
   -1007,   986, -1007, -1007, -1007, -1007, -1007, -1007,   987,    -5,
      35, -1007, -1007, -1007, -1007, -1007, -1007, -1007,   437, -1007,
   -1007, -1007, -1007, -1007, -1007, -1007,   -78, -1007, -1007, -1007,
   -1007, -1007, -1007, -1007,  -714,   138, -1007,  -111, -1007, -1007,
   -1007, -1007, -1007, -1006, -1007, -1007, -1007,   -11,  -213, -1007,
   -1007, -1007, -1007, -1007, -1007, -1007, -1007,   -75, -1007, -1007,
   -1007, -1007, -1007,  -326, -1007, -1007,    -8, -1007, -1007,  -157,
    -283,   -14, -1007, -1007, -1007, -1007, -1007,  -126
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -275
static const yytype_int16 yytable[] =
{
      73,   284,   444,   400,   349,   448,   260,    94,    83,   264,
      87,   291,    13,   215,    86,    74,    78,   799,     1,   155,
     803,    21,    77,    10,   563,   564,   565,  1095,    78,   165,
    1099,   140,   141,   142,   143,   144,   150,   151,   145,   146,
     147,   148,   149,   150,   151,    12,   166,   156,   346,   347,
     267,   750,   751,  -186,    87,  -274,    21,   138,   139,    24,
      78,   153,     5,     6,     7,     8,    96,    14,    83,   159,
      97,    75,     2,     9,    88,    79,   402,   403,    83,    98,
     163,   152,   221,   222,   223,   224,   225,   226,   227,   228,
     229,   230,   231,   232,   233,   234,   235,   236,   237,   238,
     239,   240,   241,   242,   243,   244,   245,   246,   247,   248,
     249,   154,   253,   254,   255,   256,   257,   258,    88,   261,
     262,   164,  1051,  1052,  1161,  1162,   268,   269,   270,   271,
     272,   273,   274,   275,   276,   277,   278,   279,   334,   335,
     888,  1181,    99,   280,   100,   101,   102,   157,   103,   104,
     105,   106,    83,   288,   107,    83,   140,   141,   142,   143,
     144,   108,   109,   145,   146,   147,   148,   149,   150,   151,
     110,   111,   140,   141,   142,   143,   144,   112,   219,   145,
     146,   147,   148,   149,   150,   151,   113,   114,   115,   116,
     158,   356,   117,   118,   119,   285,   120,   585,   287,   140,
     141,   142,   143,   144,   121,   122,   145,   146,   147,   148,
     149,   150,   151,   123,   124,   353,   125,   405,   406,   407,
     408,   409,   410,   411,   412,   413,   414,   415,   416,   417,
     418,   419,   420,   421,   422,   423,   424,   425,   426,   427,
     428,   429,   430,   431,   432,   433,   126,   437,   438,   439,
     440,   441,   442,   127,   445,   446,   482,   128,   129,   130,
     131,   452,   453,   454,   455,   456,   457,   458,   459,   460,
     461,   462,   463,    83,    83,   465,   401,   140,   141,   142,
     143,   144,   132,   133,   145,   146,   147,   148,   149,   150,
     151,   140,   141,   142,   143,   144,   357,   134,   145,   146,
     147,   148,   149,   150,   151,   135,   136,   137,   160,   161,
     358,   140,   141,   142,   143,   144,   396,   397,   145,   146,
     147,   148,   149,   150,   151,   140,   141,   142,   143,   144,
     360,   217,   145,   146,   147,   148,   149,   150,   151,   466,
     467,   468,   220,   265,   361,   469,   693,   266,   286,   292,
     293,   294,   470,   471,   472,   295,   473,   474,   475,   296,
     476,   477,   478,   479,   297,   480,   298,   481,   299,   300,
     301,   302,   483,   484,   485,   303,   304,   486,   487,   305,
     593,   306,   307,   308,   490,   140,   141,   142,   143,   144,
     309,   310,   145,   146,   147,   148,   149,   150,   151,   140,
     141,   142,   143,   144,   362,   311,   145,   146,   147,   148,
     149,   150,   151,   312,   313,   314,   315,   350,   363,   569,
     570,   571,   316,   317,   318,   572,   319,   320,   321,   398,
     352,   322,   573,   574,   575,   323,   576,   577,   578,   324,
     579,   580,   581,   582,   325,   583,   326,   584,   327,   328,
     329,   330,   586,   587,   588,   331,   332,   589,   590,   140,
     141,   142,   143,   144,   333,   449,   145,   146,   147,   148,
     149,   150,   151,   383,   560,   561,   390,   393,   364,   140,
     141,   142,   143,   144,   450,   464,   145,   146,   147,   148,
     149,   150,   151,   140,   141,   142,   143,   144,   365,   488,
     145,   146,   147,   148,   149,   150,   151,   489,   491,   537,
     493,   524,   369,   567,   531,   534,   626,   627,   568,   140,
     141,   142,   591,   684,   685,   686,   145,   146,   147,   148,
     149,   150,   151,   592,   594,   595,   600,   596,   628,   601,
     687,   602,   688,   629,   692,   597,   603,   759,   695,   804,
     689,   140,   141,   142,   143,   144,   696,   697,   145,   146,
     147,   148,   149,   150,   151,   140,   141,   142,   143,   144,
     373,   698,   145,   146,   147,   148,   149,   150,   151,   699,
     700,   701,   702,   805,   378,   633,   634,   140,   141,   142,
     143,   144,   703,   704,   145,   146,   147,   148,   149,   150,
     151,  -275,  -275,  -275,   738,   739,   380,   705,  -275,  -275,
    -275,   148,   149,   150,   151,   706,   758,   760,   761,   762,
     763,   764,   765,   766,   767,   768,   769,   770,   771,   772,
     773,   774,   775,   776,   777,   778,   779,   780,   781,   782,
     783,   784,   785,   786,   787,   788,   707,   792,   793,   794,
     795,   796,   797,   708,   800,   801,   709,   710,   711,   712,
     713,   714,   808,   809,   810,   811,   812,   813,   814,   815,
     816,   817,   818,   819,   635,   636,   715,   716,   717,   718,
     637,   638,   639,   640,   641,   642,   643,   644,   645,   646,
     647,   648,   649,   650,   651,   652,   653,   654,   655,   656,
     657,   658,   659,   660,   661,   662,   663,   664,   665,   666,
     667,   668,   669,   670,   671,   672,   673,   674,   675,   676,
     677,   678,   679,   680,   719,   720,   721,   722,   723,   724,
     725,   726,   681,   727,   728,   729,   730,   731,   820,   821,
     822,   732,   733,   734,   735,   682,   736,   872,   873,   874,
     737,   806,   824,   875,   855,   862,   865,   683,   868,   894,
     876,   877,   878,   895,   879,   880,   881,   897,   882,   883,
     884,   885,   898,   886,   899,   887,   940,   932,   941,   942,
     889,   890,   891,   943,   997,   892,   893,   998,   999,   996,
     140,   141,   142,   143,   144,  1000,  1001,   145,   146,   147,
     148,   149,   150,   151,  1002,  1003,  1004,  1005,  1100,   384,
    1006,   140,   141,   142,   143,   144,   921,   922,   145,   146,
     147,   148,   149,   150,   151,  1007,  1008,  1009,  1010,   993,
     385,  1011,   927,  1012,   928,  1013,   140,   141,   142,   143,
     144,  1014,   929,   145,   146,   147,   148,   149,   150,   151,
    1015,  1016,  1017,   939,  1101,   389,   336,   337,   338,   339,
     340,  1018,  1019,   341,   342,   343,   344,   345,   346,   347,
    1020,  1021,  1022,  1023,  1116,   451,  1024,  1025,  1026,  1027,
    1039,  1040,  1028,   924,   925,   926,  1056,  1057,  1058,  1059,
    1060,  1061,  1062,  1063,  1064,  1065,  1066,  1067,  1068,  1069,
    1070,  1071,  1072,  1073,  1074,  1075,  1076,  1077,  1078,  1079,
    1080,  1081,  1082,  1083,  1084,  1029,  1088,  1089,  1090,  1091,
    1092,  1093,  1030,  1096,  1097,  1031,  1032,  1033,  1034,   995,
    1103,  1104,  1105,  1106,  1107,  1108,  1109,  1110,  1111,  1112,
    1113,  1114,  1035,  1036,    25,    26,  1037,  1038,  1115,  1148,
      27,    28,    29,    30,    31,    32,    33,    34,    35,    36,
      37,    38,    39,    40,    41,    42,    43,    44,    45,    46,
      47,    48,    49,    50,    51,    52,    53,    54,    55,    56,
      57,    58,    59,    60,    61,    62,    63,    64,    65,    66,
      67,    68,    69,    70,  1117,  1155,  1158,  1187,  1164,  1188,
    1190,  1189,  1214,    84,   604,    85,   896,  1165,  1166,  1167,
       0,     0,     0,  1168,     0,    71,     0,     0,     0,     0,
    1169,  1170,  1171,     0,  1172,  1173,  1174,    72,  1175,  1176,
    1177,  1178,     0,  1179,     0,  1180,     0,     0,     0,     0,
    1182,  1183,  1184,     0,     0,  1185,  1186,   336,   337,   338,
     339,   340,     0,     0,   341,   342,   343,   344,   345,   346,
     347,   336,   337,   338,   339,   340,   497,     0,   341,   342,
     343,   344,   345,   346,   347,     0,  1212,  1213,     0,     0,
     498,     0,     0,     0,     0,     0,   336,   337,   338,     0,
    1215,     0,  1216,   341,   342,   343,   344,   345,   346,   347,
    1217,     0,     0,   167,   168,     0,     0,     0,  1223,   169,
     170,   171,   172,   173,   174,   175,   176,   177,   178,   179,
     180,   181,   182,   183,   184,   185,   186,   187,   188,   189,
     190,   191,   192,   193,   194,   195,   196,   197,   198,   199,
     200,   201,   202,   203,   204,   205,   206,   207,   208,   209,
     210,   211,   212,   336,   337,   338,   339,   340,     0,     0,
     341,   342,   343,   344,   345,   346,   347,     0,     0,     0,
       0,     0,   499,     0,   213,     0,     0,     0,     0,     0,
       0,   945,   946,     0,     0,     0,   214,   947,   948,   949,
     950,   951,   952,   953,   954,   955,   956,   957,   958,   959,
     960,   961,   962,   963,   964,   965,   966,   967,   968,   969,
     970,   971,   972,   973,   974,   975,   976,   977,   978,   979,
     980,   981,   982,   983,   984,   985,   986,   987,   988,   989,
     990,   336,   337,   338,   339,   340,     0,     0,   341,   342,
     343,   344,   345,   346,   347,     0,     0,     0,     0,     0,
     501,     0,   991,     0,     0,     0,     0,     0,   336,   337,
     338,   339,   340,     0,   992,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   502,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   503,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   504,     0,   341,   342,   343,
     344,   345,   346,   347,   336,   337,   338,   339,   340,   505,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   506,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   510,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   514,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   519,     0,   341,   342,   343,
     344,   345,   346,   347,   336,   337,   338,   339,   340,   521,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   525,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   526,     0,   341,
     342,   343,   344,   345,   346,   347,   140,   141,   142,   143,
     144,   530,     0,   145,   146,   147,   148,   149,   150,   151,
     140,   141,   142,   143,   144,   539,     0,   145,   146,   147,
     148,   149,   150,   151,   140,   141,   142,   143,   144,   540,
       0,   145,   146,   147,   148,   149,   150,   151,   140,   141,
     142,   143,   144,   541,     0,   145,   146,   147,   148,   149,
     150,   151,   140,   141,   142,   143,   144,   542,     0,   145,
     146,   147,   148,   149,   150,   151,   140,   141,   142,   143,
     144,   543,     0,   145,   146,   147,   148,   149,   150,   151,
     140,   141,   142,   143,   144,   544,     0,   145,   146,   147,
     148,   149,   150,   151,   140,   141,   142,   143,   144,   545,
       0,   145,   146,   147,   148,   149,   150,   151,   140,   141,
     142,   143,   144,   546,     0,   145,   146,   147,   148,   149,
     150,   151,   140,   141,   142,   143,   144,   547,     0,   145,
     146,   147,   148,   149,   150,   151,   140,   141,   142,   143,
     144,   548,     0,   145,   146,   147,   148,   149,   150,   151,
     140,   141,   142,   143,   144,   550,     0,   145,   146,   147,
     148,   149,   150,   151,   140,   141,   142,   143,   144,   552,
       0,   145,   146,   147,   148,   149,   150,   151,   140,   141,
     142,   143,   144,   553,     0,   145,   146,   147,   148,   149,
     150,   151,   140,   141,   142,   143,   144,   554,     0,   145,
     146,   147,   148,   149,   150,   151,   140,   141,   142,   143,
     144,   555,     0,   145,   146,   147,   148,   149,   150,   151,
     140,   141,   142,   143,   144,   556,     0,   145,   146,   147,
     148,   149,   150,   151,   140,   141,   142,   143,   144,   557,
       0,   145,   146,   147,   148,   149,   150,   151,   140,   141,
     142,   143,   144,   558,     0,   145,   146,   147,   148,   149,
     150,   151,   336,   337,   338,   339,   340,   598,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   605,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   606,     0,   341,   342,   343,
     344,   345,   346,   347,   336,   337,   338,   339,   340,   607,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   608,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   609,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   610,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   611,     0,   341,   342,   343,
     344,   345,   346,   347,   336,   337,   338,   339,   340,   612,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   613,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   614,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   616,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   618,     0,   341,   342,   343,
     344,   345,   346,   347,   336,   337,   338,   339,   340,   619,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   620,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   621,     0,   341,
     342,   343,   344,   345,   346,   347,   336,   337,   338,   339,
     340,   622,     0,   341,   342,   343,   344,   345,   346,   347,
     336,   337,   338,   339,   340,   623,     0,   341,   342,   343,
     344,   345,   346,   347,   140,   141,   142,   143,   144,   624,
       0,   145,   146,   147,   148,   149,   150,   151,   140,   141,
     142,   143,   144,   630,     0,   145,   146,   147,   148,   149,
     150,   151,   140,   141,   142,   143,   144,   631,     0,   145,
     146,   147,   148,   149,   150,   151,   336,   337,   338,   339,
     340,   632,     0,   341,   342,   343,   344,   345,   346,   347,
     140,   141,   142,   143,   144,   690,     0,   145,   146,   147,
     148,   149,   150,   151,   336,   337,   338,   339,   340,   694,
       0,   341,   342,   343,   344,   345,   346,   347,   336,   337,
     338,   339,   340,   755,     0,   341,   342,   343,   344,   345,
     346,   347,   336,   337,   338,   339,   340,   756,     0,   341,
     342,   343,   344,   345,   346,   347,   740,   741,   742,   743,
     744,   757,     0,   745,   746,   747,   748,   749,   750,   751,
     336,   337,   338,   339,   340,   807,     0,   341,   342,   343,
     344,   345,   346,   347,   740,   741,   742,   743,   744,   823,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   828,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   829,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   830,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   832,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   833,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   834,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   835,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   836,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   837,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   841,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   845,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   850,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   852,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   856,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   857,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   861,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   900,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   901,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   902,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   903,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   904,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   905,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   906,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   907,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   908,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   909,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   911,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   913,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   914,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   915,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   916,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   917,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   918,     0,   745,   746,   747,   748,   749,   750,   751,
     740,   741,   742,   743,   744,   919,     0,   745,   746,   747,
     748,   749,   750,   751,   740,   741,   742,   743,   744,   930,
       0,   745,   746,   747,   748,   749,   750,   751,   740,   741,
     742,   743,   744,   936,     0,   745,   746,   747,   748,   749,
     750,   751,   740,   741,   742,   743,   744,   937,     0,   745,
     746,   747,   748,   749,   750,   751,   740,   741,   742,   743,
     744,   938,     0,   745,   746,   747,   748,   749,   750,   751,
    1041,  1042,  1043,  1044,  1045,   944,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1102,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1121,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1122,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1123,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1125,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1126,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1127,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1128,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1129,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1130,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1134,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1138,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1143,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1145,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1149,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1150,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1154,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1191,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1192,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1193,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1194,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1195,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1196,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1197,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1198,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1199,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1200,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1202,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1204,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1205,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1206,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1207,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1208,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1209,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    1041,  1042,  1043,  1044,  1045,  1210,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,  1045,  1218,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,
    1043,  1044,  1045,  1220,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,  1041,  1042,  1043,  1044,  1045,  1221,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,  1041,  1042,  1043,  1044,
    1045,  1222,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
     140,   141,   142,   143,   144,  1224,     0,   145,   146,   147,
     148,   149,   150,   151,     0,     0,   354,   140,   141,   142,
     143,   144,     0,     0,   145,   146,   147,   148,   149,   150,
     151,     0,     0,   355,   140,   141,   142,   143,   144,     0,
       0,   145,   146,   147,   148,   149,   150,   151,     0,     0,
     359,   140,   141,   142,   143,   144,     0,     0,   145,   146,
     147,   148,   149,   150,   151,     0,     0,   366,   140,   141,
     142,   143,   144,     0,     0,   145,   146,   147,   148,   149,
     150,   151,     0,     0,   367,   140,   141,   142,   143,   144,
       0,     0,   145,   146,   147,   148,   149,   150,   151,     0,
       0,   368,   140,   141,   142,   143,   144,     0,     0,   145,
     146,   147,   148,   149,   150,   151,     0,     0,   370,   140,
     141,   142,   143,   144,     0,     0,   145,   146,   147,   148,
     149,   150,   151,     0,     0,   371,   140,   141,   142,   143,
     144,     0,     0,   145,   146,   147,   148,   149,   150,   151,
       0,     0,   372,   140,   141,   142,   143,   144,     0,     0,
     145,   146,   147,   148,   149,   150,   151,     0,     0,   374,
     140,   141,   142,   143,   144,     0,     0,   145,   146,   147,
     148,   149,   150,   151,     0,     0,   375,   140,   141,   142,
     143,   144,     0,     0,   145,   146,   147,   148,   149,   150,
     151,     0,     0,   376,   140,   141,   142,   143,   144,     0,
       0,   145,   146,   147,   148,   149,   150,   151,     0,     0,
     377,   140,   141,   142,   143,   144,     0,     0,   145,   146,
     147,   148,   149,   150,   151,     0,     0,   379,   140,   141,
     142,   143,   144,     0,     0,   145,   146,   147,   148,   149,
     150,   151,     0,     0,   381,   140,   141,   142,   143,   144,
       0,     0,   145,   146,   147,   148,   149,   150,   151,     0,
       0,   382,   140,   141,   142,   143,   144,     0,     0,   145,
     146,   147,   148,   149,   150,   151,     0,     0,   386,   140,
     141,   142,   143,   144,     0,     0,   145,   146,   147,   148,
     149,   150,   151,     0,     0,   387,   140,   141,   142,   143,
     144,     0,     0,   145,   146,   147,   148,   149,   150,   151,
       0,     0,   388,   140,   141,   142,   143,   144,     0,     0,
     145,   146,   147,   148,   149,   150,   151,     0,     0,   391,
     140,   141,   142,   143,   144,     0,     0,   145,   146,   147,
     148,   149,   150,   151,     0,     0,   392,   336,   337,   338,
     339,   340,     0,     0,   341,   342,   343,   344,   345,   346,
     347,     0,     0,   494,   336,   337,   338,   339,   340,     0,
       0,   341,   342,   343,   344,   345,   346,   347,     0,     0,
     495,   336,   337,   338,   339,   340,     0,     0,   341,   342,
     343,   344,   345,   346,   347,     0,     0,   496,   336,   337,
     338,   339,   340,     0,     0,   341,   342,   343,   344,   345,
     346,   347,     0,     0,   500,   336,   337,   338,   339,   340,
       0,     0,   341,   342,   343,   344,   345,   346,   347,     0,
       0,   507,   336,   337,   338,   339,   340,     0,     0,   341,
     342,   343,   344,   345,   346,   347,     0,     0,   508,   336,
     337,   338,   339,   340,     0,     0,   341,   342,   343,   344,
     345,   346,   347,     0,     0,   509,   336,   337,   338,   339,
     340,     0,     0,   341,   342,   343,   344,   345,   346,   347,
       0,     0,   511,   336,   337,   338,   339,   340,     0,     0,
     341,   342,   343,   344,   345,   346,   347,     0,     0,   512,
     336,   337,   338,   339,   340,     0,     0,   341,   342,   343,
     344,   345,   346,   347,     0,     0,   513,   336,   337,   338,
     339,   340,     0,     0,   341,   342,   343,   344,   345,   346,
     347,     0,     0,   515,   336,   337,   338,   339,   340,     0,
       0,   341,   342,   343,   344,   345,   346,   347,     0,     0,
     516,   336,   337,   338,   339,   340,     0,     0,   341,   342,
     343,   344,   345,   346,   347,     0,     0,   517,   336,   337,
     338,   339,   340,     0,     0,   341,   342,   343,   344,   345,
     346,   347,     0,     0,   518,   336,   337,   338,   339,   340,
       0,     0,   341,   342,   343,   344,   345,   346,   347,     0,
       0,   520,   336,   337,   338,   339,   340,     0,     0,   341,
     342,   343,   344,   345,   346,   347,     0,     0,   522,   336,
     337,   338,   339,   340,     0,     0,   341,   342,   343,   344,
     345,   346,   347,     0,     0,   523,   336,   337,   338,   339,
     340,     0,     0,   341,   342,   343,   344,   345,   346,   347,
       0,     0,   527,   336,   337,   338,   339,   340,     0,     0,
     341,   342,   343,   344,   345,   346,   347,     0,     0,   528,
     336,   337,   338,   339,   340,     0,     0,   341,   342,   343,
     344,   345,   346,   347,     0,     0,   529,   336,   337,   338,
     339,   340,     0,     0,   341,   342,   343,   344,   345,   346,
     347,     0,     0,   532,   336,   337,   338,   339,   340,     0,
       0,   341,   342,   343,   344,   345,   346,   347,     0,     0,
     533,   140,   141,   142,   143,   144,     0,     0,   145,   146,
     147,   148,   149,   150,   151,     0,     0,   549,   140,   141,
     142,   143,   144,     0,     0,   145,   146,   147,   148,   149,
     150,   151,     0,     0,   551,   140,   141,   142,   143,   144,
       0,     0,   145,   146,   147,   148,   149,   150,   151,     0,
       0,   559,   140,   141,   142,   143,   144,     0,     0,   145,
     146,   147,   148,   149,   150,   151,     0,     0,   599,   336,
     337,   338,   339,   340,     0,     0,   341,   342,   343,   344,
     345,   346,   347,     0,     0,   615,   336,   337,   338,   339,
     340,     0,     0,   341,   342,   343,   344,   345,   346,   347,
       0,     0,   617,   336,   337,   338,   339,   340,     0,     0,
     341,   342,   343,   344,   345,   346,   347,     0,     0,   625,
     336,   337,   338,   339,   340,     0,     0,   341,   342,   343,
     344,   345,   346,   347,     0,     0,   691,   740,   741,   742,
     743,   744,     0,     0,   745,   746,   747,   748,   749,   750,
     751,     0,     0,   752,   740,   741,   742,   743,   744,     0,
       0,   745,   746,   747,   748,   749,   750,   751,     0,     0,
     753,   740,   741,   742,   743,   744,     0,     0,   745,   746,
     747,   748,   749,   750,   751,     0,     0,   754,   740,   741,
     742,   743,   744,     0,     0,   745,   746,   747,   748,   749,
     750,   751,     0,     0,   825,   740,   741,   742,   743,   744,
       0,     0,   745,   746,   747,   748,   749,   750,   751,     0,
       0,   826,   740,   741,   742,   743,   744,     0,     0,   745,
     746,   747,   748,   749,   750,   751,     0,     0,   827,   740,
     741,   742,   743,   744,     0,     0,   745,   746,   747,   748,
     749,   750,   751,     0,     0,   831,   740,   741,   742,   743,
     744,     0,     0,   745,   746,   747,   748,   749,   750,   751,
       0,     0,   838,   740,   741,   742,   743,   744,     0,     0,
     745,   746,   747,   748,   749,   750,   751,     0,     0,   839,
     740,   741,   742,   743,   744,     0,     0,   745,   746,   747,
     748,   749,   750,   751,     0,     0,   840,   740,   741,   742,
     743,   744,     0,     0,   745,   746,   747,   748,   749,   750,
     751,     0,     0,   842,   740,   741,   742,   743,   744,     0,
       0,   745,   746,   747,   748,   749,   750,   751,     0,     0,
     843,   740,   741,   742,   743,   744,     0,     0,   745,   746,
     747,   748,   749,   750,   751,     0,     0,   844,   740,   741,
     742,   743,   744,     0,     0,   745,   746,   747,   748,   749,
     750,   751,     0,     0,   846,   740,   741,   742,   743,   744,
       0,     0,   745,   746,   747,   748,   749,   750,   751,     0,
       0,   847,   740,   741,   742,   743,   744,     0,     0,   745,
     746,   747,   748,   749,   750,   751,     0,     0,   848,   740,
     741,   742,   743,   744,     0,     0,   745,   746,   747,   748,
     749,   750,   751,     0,     0,   849,   740,   741,   742,   743,
     744,     0,     0,   745,   746,   747,   748,   749,   750,   751,
       0,     0,   851,   740,   741,   742,   743,   744,     0,     0,
     745,   746,   747,   748,   749,   750,   751,     0,     0,   853,
     740,   741,   742,   743,   744,     0,     0,   745,   746,   747,
     748,   749,   750,   751,     0,     0,   854,   740,   741,   742,
     743,   744,     0,     0,   745,   746,   747,   748,   749,   750,
     751,     0,     0,   858,   740,   741,   742,   743,   744,     0,
       0,   745,   746,   747,   748,   749,   750,   751,     0,     0,
     859,   740,   741,   742,   743,   744,     0,     0,   745,   746,
     747,   748,   749,   750,   751,     0,     0,   860,   740,   741,
     742,   743,   744,     0,     0,   745,   746,   747,   748,   749,
     750,   751,     0,     0,   863,   740,   741,   742,   743,   744,
       0,     0,   745,   746,   747,   748,   749,   750,   751,     0,
       0,   864,   740,   741,   742,   743,   744,     0,     0,   745,
     746,   747,   748,   749,   750,   751,     0,     0,   910,   740,
     741,   742,   743,   744,     0,     0,   745,   746,   747,   748,
     749,   750,   751,     0,     0,   912,   740,   741,   742,   743,
     744,     0,     0,   745,   746,   747,   748,   749,   750,   751,
       0,     0,   920,   740,   741,   742,   743,   744,     0,     0,
     745,   746,   747,   748,   749,   750,   751,     0,     0,   931,
    1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,     0,     0,  1118,  1041,  1042,  1043,
    1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,
    1052,     0,     0,  1119,  1041,  1042,  1043,  1044,  1045,     0,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,     0,
    1120,  1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,
    1048,  1049,  1050,  1051,  1052,     0,     0,  1124,  1041,  1042,
    1043,  1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,     0,     0,  1131,  1041,  1042,  1043,  1044,  1045,
       0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,
       0,  1132,  1041,  1042,  1043,  1044,  1045,     0,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,     0,     0,  1133,  1041,
    1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,  1049,
    1050,  1051,  1052,     0,     0,  1135,  1041,  1042,  1043,  1044,
    1045,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
       0,     0,  1136,  1041,  1042,  1043,  1044,  1045,     0,     0,
    1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,     0,  1137,
    1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,     0,     0,  1139,  1041,  1042,  1043,
    1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,
    1052,     0,     0,  1140,  1041,  1042,  1043,  1044,  1045,     0,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,     0,
    1141,  1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,
    1048,  1049,  1050,  1051,  1052,     0,     0,  1142,  1041,  1042,
    1043,  1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,     0,     0,  1144,  1041,  1042,  1043,  1044,  1045,
       0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,
       0,  1146,  1041,  1042,  1043,  1044,  1045,     0,     0,  1046,
    1047,  1048,  1049,  1050,  1051,  1052,     0,     0,  1147,  1041,
    1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,  1049,
    1050,  1051,  1052,     0,     0,  1151,  1041,  1042,  1043,  1044,
    1045,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
       0,     0,  1152,  1041,  1042,  1043,  1044,  1045,     0,     0,
    1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,     0,  1153,
    1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,     0,     0,  1156,  1041,  1042,  1043,
    1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,
    1052,     0,     0,  1157,  1041,  1042,  1043,  1044,  1045,     0,
       0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,     0,
    1201,  1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,
    1048,  1049,  1050,  1051,  1052,     0,     0,  1203,  1041,  1042,
    1043,  1044,  1045,     0,     0,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,     0,     0,  1211,  1041,  1042,  1043,  1044,  1045,
       0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,     0,
       0,  1219,   140,   141,   142,   143,   144,     0,     0,   145,
     146,   147,   148,   149,   150,   151,   336,   337,   338,   339,
     340,     0,     0,   341,   342,   343,   344,   345,   346,   347,
    1041,  1042,  1043,  1044,  1045,     0,     0,  1046,  1047,  1048,
    1049,  1050,  1051,  1052,  -275,  -275,  -275,     0,     0,     0,
       0,  -275,  -275,  -275,   344,   345,   346,   347,   740,   741,
     742,     0,     0,     0,     0,   745,   746,   747,   748,   749,
     750,   751,  -275,  -275,  -275,     0,     0,     0,     0,  -275,
    -275,  -275,   748,   749,   750,   751,  1041,  1042,  1043,     0,
       0,     0,     0,  1046,  1047,  1048,  1049,  1050,  1051,  1052,
    -275,  -275,  -275,     0,     0,     0,     0,  -275,  -275,  -275,
    1049,  1050,  1051,  1052
};

static const yytype_int16 yycheck[] =
{
      14,   158,   328,   286,   217,   331,   132,    21,    16,   135,
       1,   168,     1,    88,    19,     1,     7,   731,     1,    54,
     734,    89,     1,     4,    58,    59,    60,  1033,     7,    65,
    1036,    68,    69,    70,    71,    72,    80,    81,    75,    76,
      77,    78,    79,    80,    81,     0,    82,    82,    80,    81,
      87,    80,    81,     0,     1,    88,    89,    71,    72,    57,
       7,    75,    54,    55,    56,    57,    86,    56,    76,    80,
      86,    57,    55,    65,    65,    54,   289,   290,    86,    86,
      85,    54,    96,    97,    98,    99,   100,   101,   102,   103,
     104,   105,   106,   107,   108,   109,   110,   111,   112,   113,
     114,   115,   116,   117,   118,   119,   120,   121,   122,   123,
     124,    76,   126,   127,   128,   129,   130,   131,    65,   133,
     134,    86,    80,    81,    63,    64,   140,   141,   142,   143,
     144,   145,   146,   147,   148,   149,   150,   151,   213,   214,
     854,  1147,    86,   154,    86,    86,    86,     4,    86,    86,
      86,    86,   160,   164,    86,   163,    68,    69,    70,    71,
      72,    86,    86,    75,    76,    77,    78,    79,    80,    81,
      86,    86,    68,    69,    70,    71,    72,    86,    90,    75,
      76,    77,    78,    79,    80,    81,    86,    86,    86,    86,
       4,    87,    86,    86,    86,   160,    86,   523,   163,    68,
      69,    70,    71,    72,    86,    86,    75,    76,    77,    78,
      79,    80,    81,    86,    86,    84,    86,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308,   309,   310,   311,   312,   313,   314,
     315,   316,   317,   318,   319,   320,    86,   322,   323,   324,
     325,   326,   327,    86,   329,   330,   382,    86,    86,    86,
      86,   336,   337,   338,   339,   340,   341,   342,   343,   344,
     345,   346,   347,   281,   282,   350,   287,    68,    69,    70,
      71,    72,    86,    86,    75,    76,    77,    78,    79,    80,
      81,    68,    69,    70,    71,    72,    87,    86,    75,    76,
      77,    78,    79,    80,    81,    86,    86,    86,    82,    54,
      87,    68,    69,    70,    71,    72,   281,   282,    75,    76,
      77,    78,    79,    80,    81,    68,    69,    70,    71,    72,
      87,    82,    75,    76,    77,    78,    79,    80,    81,   353,
     354,   355,    88,     4,    87,   359,   629,     4,     4,    86,
      86,    86,   366,   367,   368,    86,   370,   371,   372,    86,
     374,   375,   376,   377,    86,   379,    86,   381,    86,    86,
      86,    86,   386,   387,   388,    86,    86,   391,   392,    86,
     537,    86,    86,    86,   398,    68,    69,    70,    71,    72,
      86,    86,    75,    76,    77,    78,    79,    80,    81,    68,
      69,    70,    71,    72,    87,    86,    75,    76,    77,    78,
      79,    80,    81,    86,    86,    86,    86,    65,    87,   494,
     495,   496,    86,    86,    86,   500,    86,    86,    86,     8,
       4,    86,   507,   508,   509,    86,   511,   512,   513,    86,
     515,   516,   517,   518,    86,   520,    86,   522,    86,    86,
      86,    86,   527,   528,   529,    86,    86,   532,   533,    68,
      69,    70,    71,    72,    86,     4,    75,    76,    77,    78,
      79,    80,    81,    87,   488,   489,    87,    87,    87,    68,
      69,    70,    71,    72,     4,     6,    75,    76,    77,    78,
      79,    80,    81,    68,    69,    70,    71,    72,    87,    84,
      75,    76,    77,    78,    79,    80,    81,    84,    53,     5,
      88,    87,    87,    53,    87,    87,   591,   592,     4,    68,
      69,    70,    84,   601,   602,   603,    75,    76,    77,    78,
      79,    80,    81,    84,     6,   549,    67,   551,    88,    83,
     615,    83,   617,     5,    66,   559,    83,    88,    86,     4,
     625,    68,    69,    70,    71,    72,    86,    86,    75,    76,
      77,    78,    79,    80,    81,    68,    69,    70,    71,    72,
      87,    86,    75,    76,    77,    78,    79,    80,    81,    86,
      86,    86,    86,     4,    87,   599,   600,    68,    69,    70,
      71,    72,    86,    86,    75,    76,    77,    78,    79,    80,
      81,    68,    69,    70,   682,   683,    87,    86,    75,    76,
      77,    78,    79,    80,    81,    86,   691,   695,   696,   697,
     698,   699,   700,   701,   702,   703,   704,   705,   706,   707,
     708,   709,   710,   711,   712,   713,   714,   715,   716,   717,
     718,   719,   720,   721,   722,   723,    86,   725,   726,   727,
     728,   729,   730,    86,   732,   733,    86,    86,    86,    86,
      86,    86,   740,   741,   742,   743,   744,   745,   746,   747,
     748,   749,   750,   751,     3,     4,    86,    86,    86,    86,
       9,    10,    11,    12,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
      49,    50,    51,    52,    86,    86,    86,    86,    86,    86,
      86,    86,    61,    86,    86,    86,    86,    86,   752,   753,
     754,    86,    86,    86,    86,    74,    86,   825,   826,   827,
      86,    62,    66,   831,    87,    87,    87,    86,    86,    84,
     838,   839,   840,    84,   842,   843,   844,    67,   846,   847,
     848,   849,    67,   851,    67,   853,    73,    87,    85,    85,
     858,   859,   860,    84,    86,   863,   864,    86,    86,   946,
      68,    69,    70,    71,    72,    86,    86,    75,    76,    77,
      78,    79,    80,    81,    86,    86,    86,    86,     4,    87,
      86,    68,    69,    70,    71,    72,   894,   895,    75,    76,
      77,    78,    79,    80,    81,    86,    86,    86,    86,   940,
      87,    86,   910,    86,   912,    86,    68,    69,    70,    71,
      72,    86,   920,    75,    76,    77,    78,    79,    80,    81,
      86,    86,    86,   931,     4,    87,    68,    69,    70,    71,
      72,    86,    86,    75,    76,    77,    78,    79,    80,    81,
      86,    86,    86,    86,    85,    87,    86,    86,    86,    86,
     991,   992,    86,   897,   898,   899,   997,   998,   999,  1000,
    1001,  1002,  1003,  1004,  1005,  1006,  1007,  1008,  1009,  1010,
    1011,  1012,  1013,  1014,  1015,  1016,  1017,  1018,  1019,  1020,
    1021,  1022,  1023,  1024,  1025,    86,  1027,  1028,  1029,  1030,
    1031,  1032,    86,  1034,  1035,    86,    86,    86,    86,   943,
    1041,  1042,  1043,  1044,  1045,  1046,  1047,  1048,  1049,  1050,
    1051,  1052,    86,    86,     3,     4,    86,    86,    84,    87,
       9,    10,    11,    12,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
      49,    50,    51,    52,    88,    87,    87,    84,     4,    84,
      88,    87,     4,    17,   567,    18,   868,  1118,  1119,  1120,
      -1,    -1,    -1,  1124,    -1,    74,    -1,    -1,    -1,    -1,
    1131,  1132,  1133,    -1,  1135,  1136,  1137,    86,  1139,  1140,
    1141,  1142,    -1,  1144,    -1,  1146,    -1,    -1,    -1,    -1,
    1151,  1152,  1153,    -1,    -1,  1156,  1157,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    68,    69,    70,    71,    72,    87,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,  1187,  1188,    -1,    -1,
      87,    -1,    -1,    -1,    -1,    -1,    68,    69,    70,    -1,
    1201,    -1,  1203,    75,    76,    77,    78,    79,    80,    81,
    1211,    -1,    -1,     3,     4,    -1,    -1,    -1,  1219,     9,
      10,    11,    12,    13,    14,    15,    16,    17,    18,    19,
      20,    21,    22,    23,    24,    25,    26,    27,    28,    29,
      30,    31,    32,    33,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    48,    49,
      50,    51,    52,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    -1,
      -1,    -1,    87,    -1,    74,    -1,    -1,    -1,    -1,    -1,
      -1,     3,     4,    -1,    -1,    -1,    86,     9,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    48,    49,    50,    51,
      52,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    -1,    -1,    -1,
      87,    -1,    74,    -1,    -1,    -1,    -1,    -1,    68,    69,
      70,    71,    72,    -1,    86,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    71,    72,    87,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    71,    72,    87,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    71,    72,    87,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    87,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    87,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    -1,    -1,    84,    68,
      69,    70,    71,    72,    -1,    -1,    75,    76,    77,    78,
      79,    80,    81,    -1,    -1,    84,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      -1,    -1,    84,    68,    69,    70,    71,    72,    -1,    -1,
      75,    76,    77,    78,    79,    80,    81,    -1,    -1,    84,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    -1,    -1,    84,    68,    69,    70,
      71,    72,    -1,    -1,    75,    76,    77,    78,    79,    80,
      81,    -1,    -1,    84,    68,    69,    70,    71,    72,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    -1,    -1,
      84,    68,    69,    70,    71,    72,    -1,    -1,    75,    76,
      77,    78,    79,    80,    81,    -1,    -1,    84,    68,    69,
      70,    71,    72,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    -1,    -1,    84,    68,    69,    70,    71,    72,
      -1,    -1,    75,    76,    77,    78,    79,    80,    81,    -1,
      -1,    84,    68,    69,    70,    71,    72,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    71,
      72,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    71,    72,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81,    68,    69,    70,    -1,    -1,    -1,
      -1,    75,    76,    77,    78,    79,    80,    81,    68,    69,
      70,    -1,    -1,    -1,    -1,    75,    76,    77,    78,    79,
      80,    81,    68,    69,    70,    -1,    -1,    -1,    -1,    75,
      76,    77,    78,    79,    80,    81,    68,    69,    70,    -1,
      -1,    -1,    -1,    75,    76,    77,    78,    79,    80,    81,
      68,    69,    70,    -1,    -1,    -1,    -1,    75,    76,    77,
      78,    79,    80,    81
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     1,    55,    92,    98,    54,    55,    56,    57,    65,
       4,   126,     0,     1,    56,    99,    96,    93,    94,    95,
      97,    89,   160,   161,    57,     3,     4,     9,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    48,    49,    50,    51,
      52,    74,    86,   162,     1,    57,   100,     1,     7,    54,
     101,   104,   105,   157,    92,    99,   100,     1,    65,   138,
     139,   142,   144,   157,   162,   127,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,   162,   162,
      68,    69,    70,    71,    72,    75,    76,    77,    78,    79,
      80,    81,    54,   162,   101,    54,    82,     4,     4,   138,
      82,    54,   107,   100,   101,    65,    82,     3,     4,     9,
      10,    11,    12,    13,    14,    15,    16,    17,    18,    19,
      20,    21,    22,    23,    24,    25,    26,    27,    28,    29,
      30,    31,    32,    33,    34,    35,    36,    37,    38,    39,
      40,    41,    42,    43,    44,    45,    46,    47,    48,    49,
      50,    51,    52,    74,    86,   148,   155,    82,   143,    90,
      88,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   165,   168,   162,   162,   162,   162,   162,   162,   166,
     168,   162,   162,   167,   168,     4,     4,    87,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     138,   103,   102,   158,   160,   101,     4,   101,   138,   141,
     140,   160,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,   148,   148,    68,    69,    70,    71,
      72,    75,    76,    77,    78,    79,    80,    81,   145,   139,
      65,   146,     4,    84,    84,    84,    87,    87,    87,    84,
      87,    87,    87,    87,    87,    87,    84,    84,    84,    87,
      84,    84,    84,    87,    84,    84,    84,    84,    87,    84,
      87,    84,    84,    87,    87,    87,    84,    84,    84,    87,
      87,    84,    84,    87,   163,   164,   101,   101,     8,   106,
     161,   138,   139,   139,   156,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   151,   154,   148,   148,   148,
     148,   148,   148,   152,   154,   148,   148,   153,   154,     4,
       4,    87,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,     6,   148,   162,   162,   162,   162,
     162,   162,   162,   162,   162,   162,   162,   162,   162,   162,
     162,   162,   168,   162,   162,   162,   162,   162,    84,    84,
     162,    53,   108,    88,    84,    84,    84,    87,    87,    87,
      84,    87,    87,    87,    87,    87,    87,    84,    84,    84,
      87,    84,    84,    84,    87,    84,    84,    84,    84,    87,
      84,    87,    84,    84,    87,    87,    87,    84,    84,    84,
      87,    87,    84,    84,    87,   149,   150,     5,   147,    87,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    84,
      87,    84,    87,    87,    87,    87,    87,    87,    87,    84,
     162,   162,   159,    58,    59,    60,   109,    53,     4,   148,
     148,   148,   148,   148,   148,   148,   148,   148,   148,   148,
     148,   148,   148,   148,   148,   154,   148,   148,   148,   148,
     148,    84,    84,   160,     6,   162,   162,   162,    87,    84,
      67,    83,    83,    83,   109,    87,    87,    87,    87,    87,
      87,    87,    87,    87,    87,    84,    87,    84,    87,    87,
      87,    87,    87,    87,    87,    84,   148,   148,    88,     5,
      87,    87,    87,   162,   162,     3,     4,     9,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    48,    49,    50,    51,
      52,    61,    74,    86,   117,   117,   117,   148,   148,   148,
      87,    84,    66,   161,    87,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,   117,   117,
      68,    69,    70,    71,    72,    75,    76,    77,    78,    79,
      80,    81,    84,    84,    84,    87,    87,    87,   148,    88,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     122,   125,   117,   117,   117,   117,   117,   117,   123,   125,
     117,   117,   124,   125,     4,     4,    62,    87,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   117,   117,
     162,   162,   162,    87,    66,    84,    84,    84,    87,    87,
      87,    84,    87,    87,    87,    87,    87,    87,    84,    84,
      84,    87,    84,    84,    84,    87,    84,    84,    84,    84,
      87,    84,    87,    84,    84,    87,    87,    87,    84,    84,
      84,    87,    87,    84,    84,    87,   120,   121,    86,   110,
     112,   114,   117,   117,   117,   117,   117,   117,   117,   117,
     117,   117,   117,   117,   117,   117,   117,   117,   125,   117,
     117,   117,   117,   117,    84,    84,   126,    67,    67,    67,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      84,    87,    84,    87,    87,    87,    87,    87,    87,    87,
      84,   117,   117,   118,   162,   162,   162,   117,   117,   117,
      87,    84,    87,   111,   113,   115,    87,    87,    87,   117,
      73,    85,    85,    84,    87,     3,     4,     9,    10,    11,
      12,    13,    14,    15,    16,    17,    18,    19,    20,    21,
      22,    23,    24,    25,    26,    27,    28,    29,    30,    31,
      32,    33,    34,    35,    36,    37,    38,    39,    40,    41,
      42,    43,    44,    45,    46,    47,    48,    49,    50,    51,
      52,    74,    86,   128,   135,   162,   160,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,    86,
      86,    86,    86,    86,    86,    86,    86,    86,    86,   128,
     128,    68,    69,    70,    71,    72,    75,    76,    77,    78,
      79,    80,    81,   119,   116,   136,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   131,   134,   128,   128,
     128,   128,   128,   128,   132,   134,   128,   128,   133,   134,
       4,     4,    87,   128,   128,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,    84,    85,    88,    84,    84,
      84,    87,    87,    87,    84,    87,    87,    87,    87,    87,
      87,    84,    84,    84,    87,    84,    84,    84,    87,    84,
      84,    84,    84,    87,    84,    87,    84,    84,    87,    87,
      87,    84,    84,    84,    87,    87,    84,    84,    87,   129,
     130,    63,    64,   137,     4,   128,   128,   128,   128,   128,
     128,   128,   128,   128,   128,   128,   128,   128,   128,   128,
     128,   134,   128,   128,   128,   128,   128,    84,    84,    87,
      88,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      87,    84,    87,    84,    87,    87,    87,    87,    87,    87,
      87,    84,   128,   128,     4,   128,   128,   128,    87,    84,
      87,    87,    87,   128,    87
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
#line 309 "sim_parser.y"
    {
			    ;}
    break;

  case 3:
#line 312 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 4:
#line 317 "sim_parser.y"
    {
			    ;}
    break;

  case 5:
#line 320 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 6:
#line 325 "sim_parser.y"
    {
			    ;}
    break;

  case 7:
#line 328 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 8:
#line 333 "sim_parser.y"
    {
			    ;}
    break;

  case 9:
#line 336 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 10:
#line 341 "sim_parser.y"
    {
			    ;}
    break;

  case 11:
#line 344 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 12:
#line 349 "sim_parser.y"
    {
			    ;}
    break;

  case 13:
#line 355 "sim_parser.y"
    {
			      if ((yyvsp[(2) - (2)].st_bucket) != NULL)
			        handle_clock_act_type((yyvsp[(2) - (2)].st_bucket));
			    ;}
    break;

  case 14:
#line 363 "sim_parser.y"
    {
			      if (((yyvsp[(2) - (2)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(2) - (2)].expr_parse_info)->expr,
						 NULL,
						 NULL,
						 (ST_BUCKET *)search_lexeme_table("integer",
										  SYT),
						 SIM_RUN_LENGTH_NOT_UNDECL_ID_FREE,
						 SIM_RUN_LENGTH_NOT_RANDOMNESS_FREE,
						 ILL_TYPED_SIM_RUN_LENGTH))
			      {
				eval_expr((yyvsp[(2) - (2)].expr_parse_info)->expr,
					  0);
			        if ((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value <= 0.0L)
				  signal_error(SIM_RUN_LENGTH_NOT_POSITIVE,
					       NULL,
					       NULL);
				else
			          sim_run_length = (int)((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value);
			      }
			    ;}
    break;

  case 15:
#line 385 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
			    ;}
    break;

  case 16:
#line 393 "sim_parser.y"
    {
			      if (((yyvsp[(2) - (2)].expr_parse_info) != NULL) &&
				  check_expr_all((yyvsp[(2) - (2)].expr_parse_info)->expr,
						 NULL,
						 NULL,
						 (ST_BUCKET *)search_lexeme_table("integer",
										  SYT),
						 SIM_RUN_NUM_NOT_UNDECL_ID_FREE,
						 SIM_RUN_NUM_NOT_RANDOMNESS_FREE,
						 ILL_TYPED_SIM_RUN_NUM))
			      {
				eval_expr((yyvsp[(2) - (2)].expr_parse_info)->expr,
					  0);
			        if (((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value < (long double)MIN_SIM_RUN_NUM) ||
				    ((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value > (long double)MAX_SIM_RUN_NUM))
				  signal_error(SIM_RUN_NUM_OUT_OF_RANGE,
					       NULL,
					       NULL);
				else
			          sim_run_num = (int)((yyvsp[(2) - (2)].expr_parse_info)->expr->info.expr->value);
			      }
			    ;}
    break;

  case 17:
#line 416 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
			    ;}
    break;

  case 18:
#line 424 "sim_parser.y"
    {
			      sim_measure_list = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 19:
#line 428 "sim_parser.y"
    {
			      sim_measure_list = append_list((yyvsp[(1) - (3)].st_bucket_cell),
							     sim_measure_list);
			    ;}
    break;

  case 20:
#line 433 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 21:
#line 438 "sim_parser.y"
    {
			    ;}
    break;

  case 22:
#line 441 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
                            ;}
    break;

  case 23:
#line 446 "sim_parser.y"
    {
			    ;}
    break;

  case 24:
#line 452 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
                                                          NULL);
                            ;}
    break;

  case 25:
#line 459 "sim_parser.y"
    {
			      selector_enabled[0] = FALSE;
                              (yyval.st_bucket_cell) = (((yyvsp[(1) - (2)].st_bucket) == NULL) ||
                                    ((yyvsp[(2) - (2)].st_bucket) == NULL))?
                                     NULL:
                                     expand_iterative_sim_measure_def((yyvsp[(2) - (2)].st_bucket));
                              selector[0] = NULL;
                            ;}
    break;

  case 26:
#line 471 "sim_parser.y"
    {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[(2) - (3)].st_bucket),
						                      (yyvsp[(3) - (3)].expr_parse_info),
								      MEASURE_ID_DEF);
			      sim_measure_id = (yyvsp[(2) - (3)].st_bucket);
                            ;}
    break;

  case 27:
#line 478 "sim_parser.y"
    {
                              if ((yyvsp[(2) - (6)].st_bucket) != NULL)
			      {
				if ((yyvsp[(6) - (6)].sim_measure) != NULL)
				  (yyvsp[(2) - (6)].st_bucket)->info.sim_measure = (yyvsp[(6) - (6)].sim_measure);
				else
				  (yyvsp[(2) - (6)].st_bucket) = NULL;
			      }
			      (yyval.st_bucket) = (yyvsp[(2) - (6)].st_bucket);
			    ;}
    break;

  case 28:
#line 492 "sim_parser.y"
    {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[(2) - (3)].st_bucket),
						                        (yyvsp[(3) - (3)].expr_parse_info),
						                        0,
								        MEASURE_ID_DEF);
			      sim_measure_id = (yyvsp[(2) - (3)].st_bucket);
			      act_type_list = par_list = NULL;
                            ;}
    break;

  case 29:
#line 501 "sim_parser.y"
    {
                              if ((yyvsp[(2) - (6)].st_bucket) != NULL)
			      {
				if ((yyvsp[(6) - (6)].sim_measure) != NULL)
				  (yyvsp[(2) - (6)].st_bucket)->info.sim_measure = (yyvsp[(6) - (6)].sim_measure);
				else
				  (yyvsp[(2) - (6)].st_bucket) = NULL;
			      }
			      (yyval.st_bucket) = (yyvsp[(2) - (6)].st_bucket);
			    ;}
    break;

  case 30:
#line 515 "sim_parser.y"
    {
			      if ((yyvsp[(5) - (5)].expr_parse_info) != NULL)
				handle_interval_begin(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 31:
#line 520 "sim_parser.y"
    {
			      if ((yyvsp[(8) - (8)].expr_parse_info) != NULL)
			        handle_interval_end((yyvsp[(5) - (8)].expr_parse_info),
						    &(yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 32:
#line 526 "sim_parser.y"
    {
			      (yyval.sim_measure) = (((yyvsp[(3) - (10)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (10)].expr_parse_info) == NULL) ||
				    ((yyvsp[(8) - (10)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_sim_measure(M_EXPECTATION,
						       (yyvsp[(3) - (10)].expr_parse_info)->expr,
						       (yyvsp[(5) - (10)].expr_parse_info)->expr,
						       (yyvsp[(8) - (10)].expr_parse_info)->expr,
						       NULL);
			    ;}
    break;

  case 33:
#line 538 "sim_parser.y"
    {
			      if ((yyvsp[(5) - (5)].expr_parse_info) != NULL)
				handle_interval_begin(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 34:
#line 543 "sim_parser.y"
    {
			      if ((yyvsp[(8) - (8)].expr_parse_info) != NULL)
			        handle_interval_end((yyvsp[(5) - (8)].expr_parse_info),
						    &(yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 35:
#line 549 "sim_parser.y"
    {
			      (yyval.sim_measure) = (((yyvsp[(3) - (10)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (10)].expr_parse_info) == NULL) ||
				    ((yyvsp[(8) - (10)].expr_parse_info) == NULL))?
				     NULL:
			             alloc_sim_measure(M_VARIANCE,
						       (yyvsp[(3) - (10)].expr_parse_info)->expr,
						       (yyvsp[(5) - (10)].expr_parse_info)->expr,
						       (yyvsp[(8) - (10)].expr_parse_info)->expr,
						       NULL);
			    ;}
    break;

  case 36:
#line 561 "sim_parser.y"
    {
			      if ((yyvsp[(5) - (5)].expr_parse_info) != NULL)
				handle_interval_begin(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 37:
#line 566 "sim_parser.y"
    {
			      if ((yyvsp[(8) - (8)].expr_parse_info) != NULL)
			        handle_interval_end((yyvsp[(5) - (8)].expr_parse_info),
						    &(yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 38:
#line 572 "sim_parser.y"
    {
			      if ((yyvsp[(11) - (11)].expr_parse_info) != NULL)
			        handle_sub_interval_width((yyvsp[(5) - (11)].expr_parse_info),
						          (yyvsp[(8) - (11)].expr_parse_info),
						          &(yyvsp[(11) - (11)].expr_parse_info));
			    ;}
    break;

  case 39:
#line 579 "sim_parser.y"
    {
			      (yyval.sim_measure) = (((yyvsp[(3) - (13)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (13)].expr_parse_info) == NULL) ||
				    ((yyvsp[(8) - (13)].expr_parse_info) == NULL) ||
				    ((yyvsp[(11) - (13)].expr_parse_info) == NULL))?
				     NULL:
			             alloc_sim_measure(M_DISTRIBUTION,
						       (yyvsp[(3) - (13)].expr_parse_info)->expr,
						       (yyvsp[(5) - (13)].expr_parse_info)->expr,
						       (yyvsp[(8) - (13)].expr_parse_info)->expr,
						       (yyvsp[(11) - (13)].expr_parse_info)->expr);
			    ;}
    break;

  case 40:
#line 595 "sim_parser.y"
    {
			      if (((yyvsp[(5) - (5)].st_bucket) != NULL) &&
				  !selector_enabled[0] &&
				  !check_rew_act_type((yyvsp[(5) - (5)].st_bucket),
						      FALSE))
				(yyvsp[(5) - (5)].st_bucket) = NULL;
			    ;}
    break;

  case 41:
#line 603 "sim_parser.y"
    {
			      if (((yyvsp[(9) - (9)].expr_parse_info) != NULL) &&
                                  !check_expr_all((yyvsp[(9) - (9)].expr_parse_info)->expr,
                                                  selector[0],
                                                  NULL,
                                                  (ST_BUCKET *)search_lexeme_table("real",
                                                                                   SYT),
                                                  REWARD_NOT_UNDECL_ID_FREE,
                                                  REWARD_NOT_RANDOMNESS_FREE,
                                                  ILL_TYPED_REWARD))
                                (yyvsp[(9) - (9)].expr_parse_info) = NULL;
			    ;}
    break;

  case 42:
#line 616 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = ((sim_measure_id == NULL) ||
				    ((yyvsp[(5) - (13)].st_bucket) == NULL) ||
				    ((yyvsp[(9) - (13)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_sample_bucket(sim_measure_id,
									     (yyvsp[(5) - (13)].st_bucket),
									     (yyvsp[(9) - (13)].expr_parse_info)->expr,
									     (yyvsp[(12) - (13)].boolean)),
							   NULL);
			    ;}
    break;

  case 43:
#line 628 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    ;}
    break;

  case 44:
#line 636 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 45:
#line 641 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 46:
#line 655 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 47:
#line 669 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 48:
#line 683 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 49:
#line 697 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 50:
#line 711 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 51:
#line 724 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 52:
#line 737 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 53:
#line 750 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 54:
#line 764 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 55:
#line 778 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 56:
#line 792 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 57:
#line 805 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 58:
#line 818 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 59:
#line 831 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 60:
#line 844 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 61:
#line 857 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 62:
#line 870 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 63:
#line 884 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 64:
#line 898 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 65:
#line 912 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 66:
#line 925 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 67:
#line 939 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 68:
#line 953 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 69:
#line 967 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 70:
#line 980 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 71:
#line 995 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 72:
#line 1009 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 73:
#line 1024 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 74:
#line 1038 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 75:
#line 1051 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 76:
#line 1065 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 77:
#line 1078 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 78:
#line 1092 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 79:
#line 1097 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 80:
#line 1102 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 81:
#line 1116 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 82:
#line 1130 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 83:
#line 1143 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 84:
#line 1157 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 85:
#line 1171 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 86:
#line 1185 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 87:
#line 1199 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 88:
#line 1213 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 89:
#line 1227 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 90:
#line 1231 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 91:
#line 1244 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 92:
#line 1257 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 93:
#line 1271 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 94:
#line 1285 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 95:
#line 1299 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 96:
#line 1312 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 97:
#line 1316 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 98:
#line 1330 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 99:
#line 1345 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 100:
#line 1349 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 101:
#line 1355 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 102:
#line 1369 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 103:
#line 1375 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 104:
#line 1390 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 105:
#line 1397 "sim_parser.y"
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

  case 106:
#line 1411 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 107:
#line 1427 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 108:
#line 1445 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 109:
#line 1461 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
                                                          NULL);
			    ;}
    break;

  case 110:
#line 1472 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[(3) - (3)].lar_parse_info)->struct_value),
						          (yyvsp[(3) - (3)].lar_parse_info)->value_num + 1,
                                                          NULL);
			    ;}
    break;

  case 111:
#line 1487 "sim_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
                                                            (yyvsp[(2) - (2)].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            ;}
    break;

  case 112:
#line 1495 "sim_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(1) - (5)].st_bucket),
                                                         &(yyvsp[(5) - (5)].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			      if (((yyvsp[(5) - (5)].st_bucket) != NULL) &&
				  selector_enabled[0] &&
				  (index_expr[1] != NULL))
				act_type_list = alloc_st_bucket4_cell((yyvsp[(5) - (5)].st_bucket),
								      unindexed_aei[1],
								      index_expr[1],
								      unindexed_id[1],
								      act_type_list);
                              (yyval.st_bucket) = (yyvsp[(5) - (5)].st_bucket);
                            ;}
    break;

  case 113:
#line 1516 "sim_parser.y"
    {
			      if ((yyvsp[(1) - (1)].st_bucket) == NULL)
			        (yyval.expr_parse_info) = NULL;
			      else
			      {
			        (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							   NULL);
				append_to_list_no_dupls((yyvsp[(1) - (1)].st_bucket),
                                                        &rew_var_list);
			      }
			    ;}
    break;

  case 114:
#line 1528 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    ;}
    break;

  case 115:
#line 1536 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 116:
#line 1541 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 117:
#line 1555 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 118:
#line 1569 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 119:
#line 1583 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 120:
#line 1597 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 121:
#line 1611 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 122:
#line 1624 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 123:
#line 1637 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 124:
#line 1650 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 125:
#line 1664 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 126:
#line 1678 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 127:
#line 1692 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 128:
#line 1705 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 129:
#line 1718 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 130:
#line 1731 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 131:
#line 1744 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 132:
#line 1757 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 133:
#line 1770 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 134:
#line 1784 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 135:
#line 1798 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 136:
#line 1812 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 137:
#line 1825 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 138:
#line 1839 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 139:
#line 1853 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 140:
#line 1867 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 141:
#line 1880 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 142:
#line 1895 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 143:
#line 1909 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 144:
#line 1924 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 145:
#line 1938 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 146:
#line 1951 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 147:
#line 1965 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 148:
#line 1978 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 149:
#line 1992 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 150:
#line 1997 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 151:
#line 2002 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 152:
#line 2016 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 153:
#line 2030 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 154:
#line 2043 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 155:
#line 2057 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 156:
#line 2071 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 157:
#line 2085 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 158:
#line 2099 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 159:
#line 2113 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 160:
#line 2127 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 161:
#line 2131 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 162:
#line 2144 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 163:
#line 2157 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 164:
#line 2171 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 165:
#line 2185 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 166:
#line 2199 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 167:
#line 2212 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 168:
#line 2216 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 169:
#line 2230 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 170:
#line 2245 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 171:
#line 2249 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 172:
#line 2255 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 173:
#line 2269 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 174:
#line 2275 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 175:
#line 2290 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 176:
#line 2297 "sim_parser.y"
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

  case 177:
#line 2311 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 178:
#line 2327 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 179:
#line 2345 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 180:
#line 2361 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
                                                          NULL);
			    ;}
    break;

  case 181:
#line 2372 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[(3) - (3)].lar_parse_info)->struct_value),
						          (yyvsp[(3) - (3)].lar_parse_info)->value_num + 1,
                                                          NULL);
			    ;}
    break;

  case 182:
#line 2387 "sim_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
                                                            (yyvsp[(2) - (2)].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            ;}
    break;

  case 183:
#line 2395 "sim_parser.y"
    {
                              build_prefixed_id((yyvsp[(5) - (7)].st_bucket),
                                                &(yyvsp[(7) - (7)].st_bucket));
			      handle_prefixed_indexed_id((yyvsp[(1) - (7)].st_bucket),
                                                         &(yyvsp[(7) - (7)].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         VAR_ID_USE_AUX_SPEC);
			      if (((yyvsp[(7) - (7)].st_bucket) != NULL) &&
				  selector_enabled[0] &&
				  (index_expr[1] != NULL))
				par_list = alloc_st_bucket4_cell((yyvsp[(7) - (7)].st_bucket),
								 unindexed_aei[1],
								 index_expr[1],
								 unindexed_id[1],
								 par_list);
                              (yyval.st_bucket) = (yyvsp[(7) - (7)].st_bucket);
                            ;}
    break;

  case 184:
#line 2418 "sim_parser.y"
    {
			      (yyval.boolean) = TRUE;
			    ;}
    break;

  case 185:
#line 2422 "sim_parser.y"
    {
			      (yyval.boolean) = FALSE;
			    ;}
    break;

  case 186:
#line 2429 "sim_parser.y"
    {
			      trace_file_list = NULL;
			    ;}
    break;

  case 187:
#line 2433 "sim_parser.y"
    {
			      trace_file_list = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 188:
#line 2440 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 189:
#line 2444 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = append_list((yyvsp[(1) - (3)].st_bucket_cell),
					       (yyvsp[(3) - (3)].st_bucket_cell));
			    ;}
    break;

  case 190:
#line 2449 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
			    ;}
    break;

  case 191:
#line 2454 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
                            ;}
    break;

  case 192:
#line 2458 "sim_parser.y"
    {
                              handle_sync_sim();
                              yyerrok;
			    ;}
    break;

  case 193:
#line 2463 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(4) - (4)].st_bucket_cell);
                            ;}
    break;

  case 194:
#line 2470 "sim_parser.y"
    {
			      (yyval.st_bucket_cell) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell((yyvsp[(1) - (1)].st_bucket),
                                                          NULL);
                            ;}
    break;

  case 195:
#line 2477 "sim_parser.y"
    {
			      par_list = NULL;
                            ;}
    break;

  case 196:
#line 2481 "sim_parser.y"
    {
			      selector_enabled[0] = FALSE;
                              (yyval.st_bucket_cell) = (((yyvsp[(1) - (3)].st_bucket) == NULL) ||
                                    ((yyvsp[(3) - (3)].st_bucket) == NULL))?
                                     NULL:
                                     expand_iterative_trace_def((yyvsp[(3) - (3)].st_bucket));
                              selector[0] = NULL;
                            ;}
    break;

  case 197:
#line 2493 "sim_parser.y"
    {
			      if (((yyvsp[(2) - (2)].expr_parse_info) != NULL) &&
				  !check_trace_expr((yyvsp[(2) - (2)].expr_parse_info)->expr,
						    TRUE,
						    FALSE))
				(yyvsp[(2) - (2)].expr_parse_info) = NULL;
			    ;}
    break;

  case 198:
#line 2501 "sim_parser.y"
    {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[(5) - (8)].st_bucket),
						                      (yyvsp[(6) - (8)].expr_parse_info),
								      NO_ID_CONTEXT);
			      if ((yyvsp[(5) - (8)].st_bucket) != NULL)
			      {
			        build_suffixed_id(&(yyvsp[(5) - (8)].st_bucket),
						  (yyvsp[(8) - (8)].st_bucket));
			        check_id(TRACE_FILE_ID_USE,
					 &(yyvsp[(5) - (8)].st_bucket),
				         FALSE);
			      }
			      if (((yyvsp[(2) - (8)].expr_parse_info) != NULL) &&
				  ((yyvsp[(5) - (8)].st_bucket) != NULL))
			      {
			        (yyvsp[(2) - (8)].expr_parse_info)->expr->info.expr->struct_value = (VALUE_CELL *)(yyvsp[(5) - (8)].st_bucket);
			        (yyval.st_bucket) = (yyvsp[(5) - (8)].st_bucket);
			      }
			      else
				(yyval.st_bucket) = NULL;
			    ;}
    break;

  case 199:
#line 2526 "sim_parser.y"
    {
			      if (((yyvsp[(2) - (2)].expr_parse_info) != NULL) &&
				  !check_trace_expr((yyvsp[(2) - (2)].expr_parse_info)->expr,
						    FALSE,
						    FALSE))
				(yyvsp[(2) - (2)].expr_parse_info) = NULL;
			    ;}
    break;

  case 200:
#line 2534 "sim_parser.y"
    {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[(5) - (8)].st_bucket),
						                        (yyvsp[(6) - (8)].expr_parse_info),
						                        0,
								        NO_ID_CONTEXT);
			      if ((yyvsp[(5) - (8)].st_bucket) != NULL)
			      {
			        build_suffixed_id(&(yyvsp[(5) - (8)].st_bucket),
						  (yyvsp[(8) - (8)].st_bucket));
			        check_id(TRACE_FILE_ID_USE,
					 &(yyvsp[(5) - (8)].st_bucket),
				         TRUE);
			      }
			      if (((yyvsp[(2) - (8)].expr_parse_info) != NULL) &&
				  ((yyvsp[(5) - (8)].st_bucket) != NULL))
			      {
			        (yyvsp[(2) - (8)].expr_parse_info)->expr->info.expr->struct_value = (VALUE_CELL *)(yyvsp[(5) - (8)].st_bucket);
			        (yyval.st_bucket) = (yyvsp[(2) - (8)].expr_parse_info)->expr;
			      }
			      else
				(yyval.st_bucket) = NULL;
			    ;}
    break;

  case 201:
#line 2560 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].st_bucket) == NULL)?
				     NULL:
			             alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
						           NULL);
			    ;}
    break;

  case 202:
#line 2567 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 203:
#line 2572 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PLUS_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 204:
#line 2586 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MINUS_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 205:
#line 2600 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(TIMES_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 206:
#line 2614 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(DIV_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 207:
#line 2628 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MOD_OP,
                                                                          (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                          (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                          NULL,
                                                                          0.0,
                                                                          NULL,
                                                                          FALSE),
                                                           NULL);
			    ;}
    break;

  case 208:
#line 2642 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ABS_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 209:
#line 2655 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(CEIL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 210:
#line 2668 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(FLOOR_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 211:
#line 2681 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MIN_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 212:
#line 2695 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(MAX_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 213:
#line 2709 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(POWER_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 214:
#line 2723 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EPOWER_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 215:
#line 2736 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LOGE_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 216:
#line 2749 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LOG10_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 217:
#line 2762 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(SQRT_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 218:
#line 2775 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(SIN_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 219:
#line 2788 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(COS_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 220:
#line 2801 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(C_UNIFORM_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 221:
#line 2815 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ERLANG_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 222:
#line 2829 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GAMMA_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 223:
#line 2843 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EXPONENTIAL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 224:
#line 2856 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(WEIBULL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 225:
#line 2870 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BETA_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 226:
#line 2884 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NORMAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 227:
#line 2898 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PARETO_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 228:
#line 2911 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(B_PARETO_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 229:
#line 2926 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(D_UNIFORM_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 230:
#line 2940 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BERNOULLI_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 231:
#line 2955 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(BINOMIAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 232:
#line 2969 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(POISSON_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 233:
#line 2982 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NEG_BINOMIAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 234:
#line 2996 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GEOMETRIC_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 235:
#line 3009 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PASCAL_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 236:
#line 3023 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 237:
#line 3028 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 238:
#line 3033 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(AND_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 239:
#line 3047 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(OR_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 240:
#line 3061 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(2) - (2)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NOT_OP,
                                                                           (yyvsp[(2) - (2)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 241:
#line 3074 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(EQ_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 242:
#line 3088 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(NE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 243:
#line 3102 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LT_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 244:
#line 3116 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 245:
#line 3130 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GT_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 246:
#line 3144 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GE_OP,
                                                                           (yyvsp[(1) - (3)].expr_parse_info)->expr,
                                                                           (yyvsp[(3) - (3)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 247:
#line 3158 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 248:
#line 3162 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(FIRST_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 249:
#line 3175 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(TAIL_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 250:
#line 3188 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(CONCAT_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 251:
#line 3202 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(INSERT_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 252:
#line 3216 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(REMOVE_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 253:
#line 3230 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = ((yyvsp[(3) - (4)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LENGTH_OP,
                                                                           (yyvsp[(3) - (4)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 254:
#line 3243 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 255:
#line 3247 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (6)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (6)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(READ_OP,
                                                                           (yyvsp[(3) - (6)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (6)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 256:
#line 3261 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(5) - (8)].expr_parse_info) == NULL) ||
				    ((yyvsp[(7) - (8)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(WRITE_OP,
                                                                           (yyvsp[(3) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(5) - (8)].expr_parse_info)->expr,
                                                                           (yyvsp[(7) - (8)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 257:
#line 3276 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 258:
#line 3280 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 259:
#line 3286 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (7)].st_bucket) == NULL) ||
				    ((yyvsp[(6) - (7)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(GET_OP,
                                                                           (yyvsp[(3) - (7)].st_bucket),
                                                                           (yyvsp[(6) - (7)].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 260:
#line 3300 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 261:
#line 3306 "sim_parser.y"
    {
        		      (yyval.expr_parse_info) = (((yyvsp[(3) - (9)].st_bucket) == NULL) ||
				    ((yyvsp[(6) - (9)].expr_parse_info) == NULL) ||
				    ((yyvsp[(8) - (9)].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(PUT_OP,
                                                                           (yyvsp[(3) - (9)].st_bucket),
                                                                           (yyvsp[(6) - (9)].expr_parse_info)->expr,
                                                                           (yyvsp[(8) - (9)].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    ;}
    break;

  case 262:
#line 3321 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 263:
#line 3328 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info(get_expr_bucket(LIST_CONS_OP,
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

  case 264:
#line 3342 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(LIST_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									   FALSE),
                                                           NULL);
			    ;}
    break;

  case 265:
#line 3358 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(ARRAY_CONS_OP,
								           NULL,
								           NULL,
								           NULL,
								           (yyvsp[(1) - (1)].lar_parse_info)->value_num,
								           transform_list_into_array(
									     (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									     (yyvsp[(1) - (1)].lar_parse_info)->value_num),
								           FALSE),
                                                           NULL);
			    ;}
    break;

  case 266:
#line 3376 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = ((yyvsp[(1) - (1)].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(get_expr_bucket(RECORD_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[(1) - (1)].lar_parse_info)->struct_value,
									   FALSE),
                                                           NULL);
			    ;}
    break;

  case 267:
#line 3392 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
                                                          NULL);
			    ;}
    break;

  case 268:
#line 3403 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[(3) - (3)].lar_parse_info)->struct_value),
						          (yyvsp[(3) - (3)].lar_parse_info)->value_num + 1,
                                                          NULL);
			    ;}
    break;

  case 269:
#line 3418 "sim_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
                                                            (yyvsp[(2) - (2)].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            ;}
    break;

  case 270:
#line 3426 "sim_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(1) - (5)].st_bucket),
                                                         &(yyvsp[(5) - (5)].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         VAR_ID_USE_AUX_SPEC);
			      if (((yyvsp[(5) - (5)].st_bucket) != NULL) &&
				  selector_enabled[0] &&
				  (index_expr[1] != NULL))
				par_list = alloc_st_bucket4_cell((yyvsp[(5) - (5)].st_bucket),
								 unindexed_aei[1],
								 index_expr[1],
								 unindexed_id[1],
								 par_list);
                              (yyval.st_bucket) = (yyvsp[(5) - (5)].st_bucket);
                            ;}
    break;

  case 271:
#line 3447 "sim_parser.y"
    {
			      handle_iteration_1(&(yyvsp[(2) - (2)].st_bucket));
			    ;}
    break;

  case 272:
#line 3451 "sim_parser.y"
    {
			      handle_iteration_2(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 273:
#line 3455 "sim_parser.y"
    {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[(2) - (8)].st_bucket),
						      (yyvsp[(5) - (8)].expr_parse_info),
						      (yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 274:
#line 3464 "sim_parser.y"
    {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    ;}
    break;

  case 275:
#line 3469 "sim_parser.y"
    {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[(1) - (1)].expr_parse_info);
			    ;}
    break;

  case 276:
#line 3477 "sim_parser.y"
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

  case 277:
#line 3494 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    ;}
    break;

  case 278:
#line 3502 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 279:
#line 3507 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 280:
#line 3521 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 281:
#line 3535 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 282:
#line 3549 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 283:
#line 3563 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 284:
#line 3577 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 285:
#line 3590 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 286:
#line 3603 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 287:
#line 3616 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 288:
#line 3630 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 289:
#line 3644 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 290:
#line 3658 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 291:
#line 3671 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 292:
#line 3684 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 293:
#line 3697 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 294:
#line 3710 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 295:
#line 3723 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 296:
#line 3736 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 297:
#line 3750 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 298:
#line 3764 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 299:
#line 3778 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 300:
#line 3791 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 301:
#line 3805 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 302:
#line 3819 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 303:
#line 3833 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 304:
#line 3846 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 305:
#line 3861 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 306:
#line 3875 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 307:
#line 3890 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 308:
#line 3904 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 309:
#line 3917 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 310:
#line 3931 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 311:
#line 3944 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 312:
#line 3958 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 313:
#line 3963 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 314:
#line 3968 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 315:
#line 3982 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 316:
#line 3996 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 317:
#line 4009 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 318:
#line 4023 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 319:
#line 4037 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 320:
#line 4051 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 321:
#line 4065 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 322:
#line 4079 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 323:
#line 4093 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 324:
#line 4097 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 325:
#line 4110 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 326:
#line 4123 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 327:
#line 4137 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 328:
#line 4151 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 329:
#line 4165 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 330:
#line 4178 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 331:
#line 4182 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 332:
#line 4196 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 333:
#line 4211 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 334:
#line 4215 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 335:
#line 4221 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 336:
#line 4235 "sim_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 337:
#line 4241 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 338:
#line 4256 "sim_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 339:
#line 4263 "sim_parser.y"
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

  case 340:
#line 4277 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 341:
#line 4293 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 342:
#line 4311 "sim_parser.y"
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
                                                           NULL);
			    ;}
    break;

  case 343:
#line 4327 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = ((yyvsp[(1) - (1)].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (1)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
                                                          NULL);
			    ;}
    break;

  case 344:
#line 4338 "sim_parser.y"
    {
			      (yyval.lar_parse_info) = (((yyvsp[(1) - (3)].expr_parse_info) == NULL) ||
				    ((yyvsp[(3) - (3)].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[(1) - (3)].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[(3) - (3)].lar_parse_info)->struct_value),
						          (yyvsp[(3) - (3)].lar_parse_info)->value_num + 1,
                                                          NULL);
			    ;}
    break;


/* Line 1267 of yacc.c.  */
#line 8281 "sim_parser.tab.c"
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


#line 4352 "sim_parser.y"



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

