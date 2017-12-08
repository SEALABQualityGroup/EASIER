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
#define yyparse rewyyparse
#define yylex   rewyylex
#define yyerror rewyyerror
#define yylval  rewyylval
#define yychar  rewyychar
#define yydebug rewyydebug
#define yynerrs rewyynerrs


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     T_NUMBER = 300,
     T_ID = 301,
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
     T_ENABLED = 540,
     T_STATE_REWARD = 541,
     T_TRANS_REWARD = 542,
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
#define T_ENABLED 540
#define T_STATE_REWARD 541
#define T_TRANS_REWARD 542
#define DOTDOT 700
#define NE 701
#define LE 702
#define GE 703
#define AND 704
#define OR 705
#define IMPL 706




/* Copy the first part of user declarations.  */
#line 25 "rew_parser.y"


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
#line 110 "rew_parser.y"
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
/* Line 193 of yacc.c.  */
#line 320 "rew_parser.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 333 "rew_parser.tab.c"

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
#define YYLAST   1359

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  78
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  34
/* YYNRULES -- Number of rules.  */
#define YYNRULES  104
/* YYNRULES -- Number of states.  */
#define YYNSTATES  344

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
       2,     2,     2,    63,     2,     2,     2,     2,     2,     2,
      72,    74,    69,    67,    77,    68,    73,    70,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    71,
      65,    64,    66,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    75,     2,    76,     2,     2,     2,     2,     2,     2,
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
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     5,
       6,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     7,     8,     9,    10,
      11,    12,    13,    14,    15,    16,    17,    18,    19,    20,
      21,    22,    23,    24,    25,    26,    27,    28,    29,    30,
      31,    32,    33,    34,    35,     2,    36,    37,     2,    38,
      39,    40,    41,    42,    43,    44,     2,    45,    46,    47,
       2,    48,    49,    50,     2,    51,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    52,
      53,    54,    55,     2,     2,     2,     2,     2,     2,     2,
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
      56,    57,    58,    59,    60,    61,    62,     2
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     5,     9,    10,    15,    16,    21,    23,
      26,    27,    34,    35,    42,    44,    47,    50,    52,    55,
      56,    57,    69,    70,    71,    83,    84,    90,    91,    97,
      98,    99,   108,   109,   112,   113,   115,   119,   121,   123,
     127,   131,   135,   139,   146,   151,   156,   161,   168,   175,
     182,   187,   192,   197,   202,   207,   212,   219,   226,   233,
     238,   245,   252,   259,   264,   273,   280,   289,   296,   301,
     308,   313,   320,   322,   324,   328,   332,   335,   339,   343,
     347,   351,   355,   359,   364,   369,   374,   381,   388,   395,
     400,   405,   412,   421,   426,   427,   435,   436,   446,   450,
     451,   453,   455,   457,   459
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      79,     0,    -1,    82,    -1,    82,    71,    79,    -1,    -1,
       1,    71,    80,    79,    -1,    -1,     1,    52,    81,    79,
      -1,    83,    -1,    98,    85,    -1,    -1,    52,     4,   103,
      84,    51,    87,    -1,    -1,    52,     4,   104,    86,    51,
      87,    -1,    88,    -1,    88,    87,    -1,     1,    52,    -1,
      89,    -1,   101,    92,    -1,    -1,    -1,    53,    72,     4,
     103,    90,    73,     4,    91,    74,    62,    95,    -1,    -1,
      -1,    53,    72,     4,   104,    93,    73,     4,    94,    74,
      62,    95,    -1,    -1,    54,    72,   105,    96,    74,    -1,
      -1,    55,    72,   105,    97,    74,    -1,    -1,    -1,     5,
       4,    99,     6,   105,   100,    56,   105,    -1,    -1,   102,
      98,    -1,    -1,   104,    -1,    75,   105,    76,    -1,     4,
      -1,     3,    -1,   105,    67,   105,    -1,   105,    68,   105,
      -1,   105,    69,   105,    -1,   105,    70,   105,    -1,     7,
      72,   105,    77,   105,    74,    -1,    10,    72,   105,    74,
      -1,    11,    72,   105,    74,    -1,    12,    72,   105,    74,
      -1,     8,    72,   105,    77,   105,    74,    -1,     9,    72,
     105,    77,   105,    74,    -1,    13,    72,   105,    77,   105,
      74,    -1,    14,    72,   105,    74,    -1,    15,    72,   105,
      74,    -1,    16,    72,   105,    74,    -1,    17,    72,   105,
      74,    -1,    18,    72,   105,    74,    -1,    19,    72,   105,
      74,    -1,    20,    72,   105,    77,   105,    74,    -1,    21,
      72,   105,    77,   105,    74,    -1,    22,    72,   105,    77,
     105,    74,    -1,    23,    72,   105,    74,    -1,    24,    72,
     105,    77,   105,    74,    -1,    25,    72,   105,    77,   105,
      74,    -1,    26,    72,   105,    77,   105,    74,    -1,    27,
      72,   105,    74,    -1,    28,    72,   105,    77,   105,    77,
     105,    74,    -1,    29,    72,   105,    77,   105,    74,    -1,
      30,    72,   105,    77,   105,    77,   105,    74,    -1,    31,
      72,   105,    77,   105,    74,    -1,    32,    72,   105,    74,
      -1,    33,    72,   105,    77,   105,    74,    -1,    34,    72,
     105,    74,    -1,    35,    72,   105,    77,   105,    74,    -1,
      36,    -1,    37,    -1,   105,    60,   105,    -1,   105,    61,
     105,    -1,    63,   105,    -1,   105,    64,   105,    -1,   105,
      57,   105,    -1,   105,    65,   105,    -1,   105,    58,   105,
      -1,   105,    66,   105,    -1,   105,    59,   105,    -1,    38,
      72,   108,    74,    -1,    39,    72,   105,    74,    -1,    40,
      72,   105,    74,    -1,    41,    72,   105,    77,   105,    74,
      -1,    42,    72,   105,    77,   105,    74,    -1,    43,    72,
     105,    77,   105,    74,    -1,    44,    72,   105,    74,    -1,
      45,    72,   109,    74,    -1,    46,    72,   105,    77,   105,
      74,    -1,    47,    72,   105,    77,   105,    77,   105,    74,
      -1,    48,    72,   110,    74,    -1,    -1,    49,    72,     4,
     106,    77,   105,    74,    -1,    -1,    50,    72,     4,   107,
      77,   105,    77,   105,    74,    -1,    72,   105,    74,    -1,
      -1,   111,    -1,   111,    -1,   111,    -1,   105,    -1,   105,
      77,   111,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   243,   243,   247,   253,   252,   261,   260,   271,   275,
     285,   284,   307,   306,   327,   330,   333,   341,   350,   366,
     374,   365,   400,   407,   399,   433,   432,   453,   452,   476,
     480,   475,   493,   493,   512,   516,   524,   541,   549,   554,
     568,   582,   596,   610,   624,   637,   650,   663,   677,   691,
     705,   718,   731,   744,   757,   770,   783,   797,   811,   825,
     838,   852,   866,   880,   893,   908,   922,   937,   951,   964,
     978,   991,  1005,  1010,  1015,  1029,  1043,  1056,  1070,  1084,
    1098,  1112,  1126,  1140,  1144,  1157,  1170,  1184,  1198,  1212,
    1225,  1229,  1243,  1258,  1263,  1262,  1283,  1282,  1303,  1311,
    1324,  1340,  1358,  1374,  1385
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "T_NUMBER", "T_ID", "T_FOR_ALL", "T_IN",
  "T_MOD", "T_MIN", "T_MAX", "T_ABS", "T_CEIL", "T_FLOOR", "T_POWER",
  "T_EPOWER", "T_LOGE", "T_LOG10", "T_SQRT", "T_SIN", "T_COS",
  "T_C_UNIFORM", "T_ERLANG", "T_GAMMA", "T_EXPONENTIAL", "T_WEIBULL",
  "T_BETA", "T_NORMAL", "T_PARETO", "T_B_PARETO", "T_D_UNIFORM",
  "T_BERNOULLI", "T_BINOMIAL", "T_POISSON", "T_NEG_BINOMIAL",
  "T_GEOMETRIC", "T_PASCAL", "T_TRUE", "T_FALSE", "T_LIST_CONS", "T_FIRST",
  "T_TAIL", "T_CONCAT", "T_INSERT", "T_REMOVE", "T_LENGTH", "T_ARRAY_CONS",
  "T_READ", "T_WRITE", "T_RECORD_CONS", "T_GET", "T_PUT", "T_IS",
  "T_MEASURE", "T_ENABLED", "T_STATE_REWARD", "T_TRANS_REWARD", "DOTDOT",
  "NE", "LE", "GE", "AND", "OR", "IMPL", "'!'", "'='", "'<'", "'>'", "'+'",
  "'-'", "'*'", "'/'", "';'", "'('", "'.'", "')'", "'['", "']'", "','",
  "$accept", "measure_def_list", "@1", "@2", "measure_def",
  "s_measure_def", "@3", "i_measure_def", "@4", "type_yb_list", "type_yb",
  "s_type_yb", "@5", "@6", "i_type_yb", "@7", "@8", "reward", "@9", "@10",
  "iteration", "@11", "@12", "nested_iteration", "@13", "poss_aei_index",
  "aei_index", "expr", "@14", "@15", "expr_list", "expr_array",
  "expr_record", "struct_expr", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   707,   300,   301,   429,   430,   446,   447,   448,
     449,   450,   451,   452,   453,   454,   455,   456,   457,   458,
     459,   460,   461,   462,   463,   464,   465,   466,   467,   468,
     469,   470,   471,   472,   473,   474,   476,   477,   479,   480,
     481,   482,   483,   484,   485,   487,   488,   489,   491,   492,
     493,   495,   539,   540,   541,   542,   700,   701,   702,   703,
     704,   705,   706,    33,    61,    60,    62,    43,    45,    42,
      47,    59,    40,    46,    41,    91,    93,    44
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    78,    79,    79,    80,    79,    81,    79,    82,    82,
      84,    83,    86,    85,    87,    87,    87,    88,    88,    90,
      91,    89,    93,    94,    92,    96,    95,    97,    95,    99,
     100,    98,   102,   101,   103,   103,   104,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   106,   105,   107,   105,   105,   108,
     108,   109,   110,   111,   111
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     3,     0,     4,     0,     4,     1,     2,
       0,     6,     0,     6,     1,     2,     2,     1,     2,     0,
       0,    11,     0,     0,    11,     0,     5,     0,     5,     0,
       0,     8,     0,     2,     0,     1,     3,     1,     1,     3,
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
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     2,     8,     0,     6,     4,
      29,    34,     1,     0,     0,     9,     0,     0,     0,     0,
      10,    35,     3,     0,     7,     5,     0,    38,    37,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    72,    73,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    12,    30,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    99,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    76,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    36,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   103,     0,
     100,     0,     0,     0,     0,     0,     0,     0,   101,     0,
       0,     0,   102,    94,    96,    98,    78,    80,    82,    74,
      75,    77,    79,    81,    39,    40,    41,    42,     0,     0,
      11,     0,    17,     0,     0,     0,     0,     0,     0,     0,
      44,    45,    46,     0,    50,    51,    52,    53,    54,    55,
       0,     0,     0,    59,     0,     0,     0,    63,     0,     0,
       0,     0,    68,     0,    70,     0,     0,    83,    84,    85,
       0,     0,     0,    89,    90,     0,     0,    93,     0,     0,
      16,     0,    15,     0,    18,    33,    13,    31,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,   104,     0,     0,     0,     0,     0,
       0,     0,    34,     0,    43,    47,    48,    49,    56,    57,
      58,    60,    61,    62,     0,    65,     0,    67,    69,    71,
      86,    87,    88,    91,     0,     0,     0,    19,     0,     0,
       0,     0,    95,     0,     0,    22,    64,    66,    92,     0,
       0,     0,    97,    20,     0,     0,    23,     0,     0,     0,
       0,     0,     0,    21,     0,     0,     0,    24,    25,    27,
       0,     0,    26,    28
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     4,    17,    16,     5,     6,    76,    15,   137,   200,
     201,   202,   314,   325,   254,   321,   328,   333,   340,   341,
       7,    18,   138,   203,   204,    20,    21,   168,   248,   249,
     169,   177,   181,   170
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -154
static const yytype_int16 yypact[] =
{
       5,   -13,    -1,    26,     9,   -30,  -154,   -10,  -154,  -154,
    -154,   -16,  -154,     5,    49,  -154,     5,     5,    83,   121,
    -154,  -154,  -154,   -16,  -154,  -154,   121,  -154,  -154,    24,
      28,    29,    30,    31,    44,    47,    48,    50,    54,    55,
     100,   101,   102,   103,   104,   105,   106,   107,   108,   109,
     110,   111,   113,   114,   119,   120,   123,   124,  -154,  -154,
     125,   126,   127,   128,   132,   136,   141,   143,   145,   146,
     147,   148,   152,   121,   121,   541,    67,  -154,  1275,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   117,
     221,   200,   555,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,  -154,     3,   182,   178,   -46,
     -32,   171,   573,   591,   609,   186,   627,   645,   663,   681,
     699,   717,   223,   238,   261,   735,   275,   289,   303,   753,
     317,   331,   345,   359,   771,   373,   789,   387,   401,   168,
    -154,   807,   825,   415,   429,   443,   843,   175,  -154,   457,
     471,   197,  -154,  -154,  -154,  -154,  1289,  1289,  1289,   200,
     200,  1289,  1289,  1289,   -53,   -53,  -154,  -154,   208,   201,
    -154,    46,  -154,   219,   269,     3,   121,   121,   121,   121,
    -154,  -154,  -154,   121,  -154,  -154,  -154,  -154,  -154,  -154,
     121,   121,   121,  -154,   121,   121,   121,  -154,   121,   121,
     121,   121,  -154,   121,  -154,   121,   121,  -154,  -154,  -154,
     121,   121,   121,  -154,  -154,   121,   121,  -154,   199,   202,
    -154,   274,  -154,   229,  -154,  -154,  -154,  1275,   861,   879,
     897,   915,   933,   951,   969,   987,  1005,  1023,   485,  1041,
     499,  1059,  1077,  1095,  -154,  1113,  1131,  1149,  1167,   513,
     121,   121,   -16,   305,  -154,  -154,  -154,  -154,  -154,  -154,
    -154,  -154,  -154,  -154,   121,  -154,   121,  -154,  -154,  -154,
    -154,  -154,  -154,  -154,   121,  1185,   527,  -154,   -16,  1203,
    1221,  1239,  -154,   121,   237,  -154,  -154,  -154,  -154,  1257,
     307,   239,  -154,  -154,   309,   240,  -154,   262,   249,    -5,
     556,   265,   279,  -154,    -5,   121,   121,  -154,  1275,  1275,
     291,   319,  -154,  -154
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -154,    27,  -154,  -154,  -154,  -154,  -154,  -154,  -154,  -153,
    -154,  -154,  -154,  -154,  -154,  -154,  -154,    45,  -154,  -154,
     203,  -154,  -154,  -154,  -154,   139,   -22,   -19,  -154,  -154,
    -154,  -154,  -154,  -113
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -33
static const yytype_int16 yytable[] =
{
      75,    77,   178,    10,   198,   182,     1,    78,   -32,    12,
       2,   123,   124,   125,   126,   127,   133,   134,   128,   129,
     130,   131,   132,   133,   134,   123,   124,   125,   126,   127,
      11,   207,   128,   129,   130,   131,   132,   133,   134,     8,
      22,    13,    14,    24,    25,   208,   -14,   198,   252,   331,
     332,   -32,   256,    23,   121,   122,   199,     3,     9,    19,
     139,   140,   141,   142,   143,   144,   145,   146,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   160,   161,   162,   163,   164,   165,   166,   167,    26,
     171,   172,   173,   174,   175,   176,    79,   179,   180,   199,
      80,    81,    82,    83,   186,   187,   188,   189,   190,   191,
     192,   193,   194,   195,   196,   197,    84,   -14,   136,    85,
      86,   183,    87,   274,    27,    28,    88,    89,    29,    30,
      31,    32,    33,    34,    35,    36,    37,    38,    39,    40,
      41,    42,    43,    44,    45,    46,    47,    48,    49,    50,
      51,    52,    53,    54,    55,    56,    57,    58,    59,    60,
      61,    62,    63,    64,    65,    66,    67,    68,    69,    70,
      71,    72,    90,    91,    92,    93,    94,    95,    96,    97,
      98,    99,   100,   101,    73,   102,   103,   257,   258,   259,
     260,   104,   105,    74,   261,   106,   107,   108,   109,   110,
     111,   262,   263,   264,   112,   265,   266,   267,   113,   268,
     269,   270,   271,   114,   272,   115,   273,   116,   117,   118,
     119,   275,   276,   277,   120,   184,   278,   279,   123,   124,
     125,   126,   127,   205,   206,   128,   129,   130,   131,   132,
     133,   134,   237,   123,   124,   125,   126,   127,   209,   244,
     128,   129,   130,   131,   132,   133,   134,   123,   124,   125,
     250,   305,   306,   213,   128,   129,   130,   131,   132,   133,
     134,   247,   253,   251,     2,   309,   280,   310,   282,   281,
     123,   124,   125,   126,   127,   311,   315,   128,   129,   130,
     131,   132,   133,   134,   319,   123,   124,   125,   126,   127,
     220,   283,   128,   129,   130,   131,   132,   133,   134,   308,
     320,   323,   324,   326,   327,   221,   338,   339,   123,   124,
     125,   126,   127,   330,   329,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,   335,   222,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,   336,   224,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,   342,   225,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,   337,
     226,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,   343,   228,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,   255,   229,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,   307,   230,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   231,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     233,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   235,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   236,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   240,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   241,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     242,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   245,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   246,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   294,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   296,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     304,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   313,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,   135,   334,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   185,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   210,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   211,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   212,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   214,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   215,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   216,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   217,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   218,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   219,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   223,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   227,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   232,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   234,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   238,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   239,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   243,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   284,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   285,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   286,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   287,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   288,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   289,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   290,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   291,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   292,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   293,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   295,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   297,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   298,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   299,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   300,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   301,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   302,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   303,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,     0,   312,
     123,   124,   125,   126,   127,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,     0,   316,   123,   124,
     125,   126,   127,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,     0,   317,   123,   124,   125,   126,
     127,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,     0,   318,   123,   124,   125,   126,   127,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
       0,   322,   123,   124,   125,   126,   127,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   -33,   -33,   -33,     0,
       0,     0,     0,   -33,   -33,   -33,   131,   132,   133,   134
};

static const yytype_int16 yycheck[] =
{
      19,    23,   115,     4,     1,   118,     1,    26,     5,     0,
       5,    57,    58,    59,    60,    61,    69,    70,    64,    65,
      66,    67,    68,    69,    70,    57,    58,    59,    60,    61,
       4,    77,    64,    65,    66,    67,    68,    69,    70,    52,
      13,    71,    52,    16,    17,    77,     0,     1,   201,    54,
      55,     5,   205,     4,    73,    74,    53,    52,    71,    75,
      79,    80,    81,    82,    83,    84,    85,    86,    87,    88,
      89,    90,    91,    92,    93,    94,    95,    96,    97,    98,
      99,   100,   101,   102,   103,   104,   105,   106,   107,     6,
     109,   110,   111,   112,   113,   114,    72,   116,   117,    53,
      72,    72,    72,    72,   123,   124,   125,   126,   127,   128,
     129,   130,   131,   132,   133,   134,    72,    71,    51,    72,
      72,     4,    72,   236,     3,     4,    72,    72,     7,     8,
       9,    10,    11,    12,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
      49,    50,    72,    72,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    63,    72,    72,   206,   207,   208,
     209,    72,    72,    72,   213,    72,    72,    72,    72,    72,
      72,   220,   221,   222,    72,   224,   225,   226,    72,   228,
     229,   230,   231,    72,   233,    72,   235,    72,    72,    72,
      72,   240,   241,   242,    72,     4,   245,   246,    57,    58,
      59,    60,    61,    51,    56,    64,    65,    66,    67,    68,
      69,    70,    74,    57,    58,    59,    60,    61,    77,    74,
      64,    65,    66,    67,    68,    69,    70,    57,    58,    59,
      52,   280,   281,    77,    64,    65,    66,    67,    68,    69,
      70,    74,    53,    72,     5,   294,    77,   296,     4,    77,
      57,    58,    59,    60,    61,   304,   308,    64,    65,    66,
      67,    68,    69,    70,   313,    57,    58,    59,    60,    61,
      77,    72,    64,    65,    66,    67,    68,    69,    70,     4,
      73,     4,    73,     4,    74,    77,   335,   336,    57,    58,
      59,    60,    61,    74,    62,    64,    65,    66,    67,    68,
      69,    70,    57,    58,    59,    60,    61,    72,    77,    64,
      65,    66,    67,    68,    69,    70,    57,    58,    59,    60,
      61,    72,    77,    64,    65,    66,    67,    68,    69,    70,
      57,    58,    59,    60,    61,    74,    77,    64,    65,    66,
      67,    68,    69,    70,    57,    58,    59,    60,    61,   334,
      77,    64,    65,    66,    67,    68,    69,    70,    57,    58,
      59,    60,    61,    74,    77,    64,    65,    66,    67,    68,
      69,    70,    57,    58,    59,    60,    61,   204,    77,    64,
      65,    66,    67,    68,    69,    70,    57,    58,    59,    60,
      61,   282,    77,    64,    65,    66,    67,    68,    69,    70,
      57,    58,    59,    60,    61,    -1,    77,    64,    65,    66,
      67,    68,    69,    70,    57,    58,    59,    60,    61,    -1,
      77,    64,    65,    66,    67,    68,    69,    70,    57,    58,
      59,    60,    61,    -1,    77,    64,    65,    66,    67,    68,
      69,    70,    57,    58,    59,    60,    61,    -1,    77,    64,
      65,    66,    67,    68,    69,    70,    57,    58,    59,    60,
      61,    -1,    77,    64,    65,    66,    67,    68,    69,    70,
      57,    58,    59,    60,    61,    -1,    77,    64,    65,    66,
      67,    68,    69,    70,    57,    58,    59,    60,    61,    -1,
      77,    64,    65,    66,    67,    68,    69,    70,    57,    58,
      59,    60,    61,    -1,    77,    64,    65,    66,    67,    68,
      69,    70,    57,    58,    59,    60,    61,    -1,    77,    64,
      65,    66,    67,    68,    69,    70,    57,    58,    59,    60,
      61,    -1,    77,    64,    65,    66,    67,    68,    69,    70,
      57,    58,    59,    60,    61,    -1,    77,    64,    65,    66,
      67,    68,    69,    70,    57,    58,    59,    60,    61,    -1,
      77,    64,    65,    66,    67,    68,    69,    70,    57,    58,
      59,    60,    61,    -1,    77,    64,    65,    66,    67,    68,
      69,    70,    57,    58,    59,    60,    61,    76,    62,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    -1,    -1,    -1,    74,
      57,    58,    59,    60,    61,    -1,    -1,    64,    65,    66,
      67,    68,    69,    70,    -1,    -1,    -1,    74,    57,    58,
      59,    60,    61,    -1,    -1,    64,    65,    66,    67,    68,
      69,    70,    -1,    -1,    -1,    74,    57,    58,    59,    60,
      61,    -1,    -1,    64,    65,    66,    67,    68,    69,    70,
      -1,    -1,    -1,    74,    57,    58,    59,    60,    61,    -1,
      -1,    64,    65,    66,    67,    68,    69,    70,    -1,    -1,
      -1,    74,    57,    58,    59,    60,    61,    -1,    -1,    64,
      65,    66,    67,    68,    69,    70,    57,    58,    59,    -1,
      -1,    -1,    -1,    64,    65,    66,    67,    68,    69,    70
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     1,     5,    52,    79,    82,    83,    98,    52,    71,
       4,     4,     0,    71,    52,    85,    81,    80,    99,    75,
     103,   104,    79,     4,    79,    79,     6,     3,     4,     7,
       8,     9,    10,    11,    12,    13,    14,    15,    16,    17,
      18,    19,    20,    21,    22,    23,    24,    25,    26,    27,
      28,    29,    30,    31,    32,    33,    34,    35,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,    63,    72,   105,    84,   104,   105,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    72,
      72,    72,    72,    72,    72,    72,    72,    72,    72,    72,
      72,   105,   105,    57,    58,    59,    60,    61,    64,    65,
      66,    67,    68,    69,    70,    76,    51,    86,   100,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   108,
     111,   105,   105,   105,   105,   105,   105,   109,   111,   105,
     105,   110,   111,     4,     4,    74,   105,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,     1,    53,
      87,    88,    89,   101,   102,    51,    56,    77,    77,    77,
      74,    74,    74,    77,    74,    74,    74,    74,    74,    74,
      77,    77,    77,    74,    77,    77,    77,    74,    77,    77,
      77,    77,    74,    77,    74,    77,    77,    74,    74,    74,
      77,    77,    77,    74,    74,    77,    77,    74,   106,   107,
      52,    72,    87,    53,    92,    98,    87,   105,   105,   105,
     105,   105,   105,   105,   105,   105,   105,   105,   105,   105,
     105,   105,   105,   105,   111,   105,   105,   105,   105,   105,
      77,    77,     4,    72,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    74,    77,    74,    77,    74,    74,    74,
      74,    74,    74,    74,    77,   105,   105,   103,     4,   105,
     105,   105,    74,    77,    90,   104,    74,    74,    74,   105,
      73,    93,    74,     4,    73,    91,     4,    74,    94,    62,
      74,    54,    55,    95,    62,    72,    72,    95,   105,   105,
      96,    97,    74,    74
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
#line 244 "rew_parser.y"
    {
			      rew_measure_list = (yyvsp[(1) - (1)].st_bucket_cell);
			    ;}
    break;

  case 3:
#line 248 "rew_parser.y"
    {
			      rew_measure_list = append_list((yyvsp[(1) - (3)].st_bucket_cell),
							     rew_measure_list);
			    ;}
    break;

  case 4:
#line 253 "rew_parser.y"
    {
                              handle_sync_rew();
                              yyerrok;
                            ;}
    break;

  case 5:
#line 258 "rew_parser.y"
    {
                            ;}
    break;

  case 6:
#line 261 "rew_parser.y"
    {
                              handle_sync_rew();
                              yyerrok;
                            ;}
    break;

  case 7:
#line 266 "rew_parser.y"
    {
                            ;}
    break;

  case 8:
#line 272 "rew_parser.y"
    {
			      (yyval.st_bucket_cell) = (yyvsp[(1) - (1)].st_bucket_cell);
                            ;}
    break;

  case 9:
#line 276 "rew_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      selector[0] = NULL;
			      (yyval.st_bucket_cell) = (yyvsp[(2) - (2)].st_bucket_cell);
                            ;}
    break;

  case 10:
#line 285 "rew_parser.y"
    {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[(2) - (3)].st_bucket),
						                      (yyvsp[(3) - (3)].expr_parse_info),
								      MEASURE_ID_DEF);
			      if ((yyvsp[(2) - (3)].st_bucket) != NULL)
			      {
			        init_rew_measure_no = ++rew_measure_num;
				(yyvsp[(2) - (3)].st_bucket)->info.rew_measure = alloc_rew_measure(NULL);
			        aux_rew_measure_list = alloc_st_bucket_cell((yyvsp[(2) - (3)].st_bucket),
									    NULL);
			      }
			      else
			        aux_rew_measure_list = NULL;
			    ;}
    break;

  case 11:
#line 300 "rew_parser.y"
    {
			      (yyval.st_bucket_cell) = aux_rew_measure_list;
			    ;}
    break;

  case 12:
#line 307 "rew_parser.y"
    {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[(2) - (3)].st_bucket),
						                        (yyvsp[(3) - (3)].expr_parse_info),
						                        0,
								        MEASURE_ID_DEF);
			      selector_enabled[0] = FALSE;
			      aux_rew_measure_list = ((selector[0] == NULL) ||
						      (selector[0]->info.expr->opn1 == NULL) ||
						      ((yyvsp[(2) - (3)].st_bucket) == NULL))?
						       NULL:
						       expand_iterative_rew_measure_def((yyvsp[(2) - (3)].st_bucket));
			      selector_enabled[0] = TRUE;
			    ;}
    break;

  case 13:
#line 321 "rew_parser.y"
    {
			      (yyval.st_bucket_cell) = aux_rew_measure_list;
			    ;}
    break;

  case 14:
#line 328 "rew_parser.y"
    {
			    ;}
    break;

  case 15:
#line 331 "rew_parser.y"
    {
			    ;}
    break;

  case 16:
#line 334 "rew_parser.y"
    {
                              handle_sync_rew();
                              yyerrok;
                            ;}
    break;

  case 17:
#line 342 "rew_parser.y"
    {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ((yyvsp[(1) - (1)].st_bucket3_cell) != NULL))
				handle_type_yb((yyvsp[(1) - (1)].st_bucket3_cell));
			      selector_enabled[0] = aux_enabled;
                            ;}
    break;

  case 18:
#line 351 "rew_parser.y"
    {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = selector_enabled[1] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ((yyvsp[(1) - (2)].st_bucket) != NULL) &&
				  ((yyvsp[(2) - (2)].st_bucket3_cell) != NULL))
				handle_type_yb((yyvsp[(2) - (2)].st_bucket3_cell));
			      selector[1] = NULL;
			      selector_enabled[0] = aux_enabled;
			      selector_index = 0;
                            ;}
    break;

  case 19:
#line 366 "rew_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(3) - (4)].st_bucket),
                                                            (yyvsp[(4) - (4)].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            ;}
    break;

  case 20:
#line 374 "rew_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(3) - (7)].st_bucket),
                                                         &(yyvsp[(7) - (7)].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    ;}
    break;

  case 21:
#line 383 "rew_parser.y"
    {
			      (yyval.st_bucket3_cell) = (((yyvsp[(7) - (11)].st_bucket) == NULL) ||
				    ((yyvsp[(11) - (11)].st_bucket) == NULL))?
				     NULL:
				     alloc_st_bucket3_cell((yyvsp[(7) - (11)].st_bucket),
							   (reward_index == YIELD)?
							     (yyvsp[(11) - (11)].st_bucket):
							     NULL,
							   (reward_index == BONUS)?
							     (yyvsp[(11) - (11)].st_bucket):
							     NULL,
							   NULL);
			    ;}
    break;

  case 22:
#line 400 "rew_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(3) - (4)].st_bucket),
                                                              (yyvsp[(4) - (4)].expr_parse_info),
                                                              1,
                                                              FALSE);
                            ;}
    break;

  case 23:
#line 407 "rew_parser.y"
    {
			      handle_prefixed_indexed_id((yyvsp[(3) - (7)].st_bucket),
                                                         &(yyvsp[(7) - (7)].st_bucket),
                                                         1,
                                                         TRUE,
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    ;}
    break;

  case 24:
#line 416 "rew_parser.y"
    {
			      (yyval.st_bucket3_cell) = (((yyvsp[(7) - (11)].st_bucket) == NULL) ||
				    ((yyvsp[(11) - (11)].st_bucket) == NULL))?
				     NULL:
				     alloc_st_bucket3_cell((yyvsp[(7) - (11)].st_bucket),
							   (reward_index == YIELD)?
							     (yyvsp[(11) - (11)].st_bucket):
							     NULL,
							   (reward_index == BONUS)?
							     (yyvsp[(11) - (11)].st_bucket):
							     NULL,
							   NULL);
			    ;}
    break;

  case 25:
#line 433 "rew_parser.y"
    {
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
				  !check_expr_all((yyvsp[(3) - (3)].expr_parse_info)->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				(yyvsp[(3) - (3)].expr_parse_info) = NULL;
			    ;}
    break;

  case 26:
#line 446 "rew_parser.y"
    {
			      (yyval.st_bucket) = ((yyvsp[(3) - (5)].expr_parse_info) != NULL)?
				     (yyvsp[(3) - (5)].expr_parse_info)->expr:
				     NULL;
			      reward_index = YIELD;
			    ;}
    break;

  case 27:
#line 453 "rew_parser.y"
    {
			      if (((yyvsp[(3) - (3)].expr_parse_info) != NULL) &&
				  !check_expr_all((yyvsp[(3) - (3)].expr_parse_info)->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				(yyvsp[(3) - (3)].expr_parse_info) = NULL;
			    ;}
    break;

  case 28:
#line 466 "rew_parser.y"
    {
			      (yyval.st_bucket) = ((yyvsp[(3) - (5)].expr_parse_info) != NULL)?
				     (yyvsp[(3) - (5)].expr_parse_info)->expr:
				     NULL;
			      reward_index = BONUS;
			    ;}
    break;

  case 29:
#line 476 "rew_parser.y"
    {
			      handle_iteration_1(&(yyvsp[(2) - (2)].st_bucket));
			    ;}
    break;

  case 30:
#line 480 "rew_parser.y"
    {
			      handle_iteration_2(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 31:
#line 484 "rew_parser.y"
    {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[(2) - (8)].st_bucket),
						      (yyvsp[(5) - (8)].expr_parse_info),
						      (yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 32:
#line 493 "rew_parser.y"
    {
			      selector_index = 1;
			    ;}
    break;

  case 33:
#line 497 "rew_parser.y"
    {
			      if (selector[1] == selector[0])
			      {
				signal_error(SAME_AEI_INDEX,
					     NULL,
					     NULL);
			        (yyval.st_bucket) = NULL;
			      }
			      else
			        (yyval.st_bucket) = (yyvsp[(2) - (2)].st_bucket);
			    ;}
    break;

  case 34:
#line 512 "rew_parser.y"
    {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    ;}
    break;

  case 35:
#line 517 "rew_parser.y"
    {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[(1) - (1)].expr_parse_info);
			    ;}
    break;

  case 36:
#line 525 "rew_parser.y"
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

  case 37:
#line 542 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    ;}
    break;

  case 38:
#line 550 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 39:
#line 555 "rew_parser.y"
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

  case 40:
#line 569 "rew_parser.y"
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

  case 41:
#line 583 "rew_parser.y"
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

  case 42:
#line 597 "rew_parser.y"
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

  case 43:
#line 611 "rew_parser.y"
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

  case 44:
#line 625 "rew_parser.y"
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

  case 45:
#line 638 "rew_parser.y"
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

  case 46:
#line 651 "rew_parser.y"
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

  case 47:
#line 664 "rew_parser.y"
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

  case 48:
#line 678 "rew_parser.y"
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

  case 49:
#line 692 "rew_parser.y"
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

  case 50:
#line 706 "rew_parser.y"
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

  case 51:
#line 719 "rew_parser.y"
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

  case 52:
#line 732 "rew_parser.y"
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

  case 53:
#line 745 "rew_parser.y"
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

  case 54:
#line 758 "rew_parser.y"
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

  case 55:
#line 771 "rew_parser.y"
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

  case 56:
#line 784 "rew_parser.y"
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

  case 57:
#line 798 "rew_parser.y"
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

  case 58:
#line 812 "rew_parser.y"
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

  case 59:
#line 826 "rew_parser.y"
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

  case 60:
#line 839 "rew_parser.y"
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

  case 61:
#line 853 "rew_parser.y"
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

  case 62:
#line 867 "rew_parser.y"
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

  case 63:
#line 881 "rew_parser.y"
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

  case 64:
#line 894 "rew_parser.y"
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

  case 65:
#line 909 "rew_parser.y"
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

  case 66:
#line 923 "rew_parser.y"
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

  case 67:
#line 938 "rew_parser.y"
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

  case 68:
#line 952 "rew_parser.y"
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

  case 69:
#line 965 "rew_parser.y"
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

  case 70:
#line 979 "rew_parser.y"
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

  case 71:
#line 992 "rew_parser.y"
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

  case 72:
#line 1006 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 73:
#line 1011 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 74:
#line 1016 "rew_parser.y"
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

  case 75:
#line 1030 "rew_parser.y"
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

  case 76:
#line 1044 "rew_parser.y"
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

  case 77:
#line 1057 "rew_parser.y"
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

  case 78:
#line 1071 "rew_parser.y"
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

  case 79:
#line 1085 "rew_parser.y"
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

  case 80:
#line 1099 "rew_parser.y"
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

  case 81:
#line 1113 "rew_parser.y"
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

  case 82:
#line 1127 "rew_parser.y"
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

  case 83:
#line 1141 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 84:
#line 1145 "rew_parser.y"
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

  case 85:
#line 1158 "rew_parser.y"
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

  case 86:
#line 1171 "rew_parser.y"
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

  case 87:
#line 1185 "rew_parser.y"
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

  case 88:
#line 1199 "rew_parser.y"
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

  case 89:
#line 1213 "rew_parser.y"
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

  case 90:
#line 1226 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 91:
#line 1230 "rew_parser.y"
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

  case 92:
#line 1244 "rew_parser.y"
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

  case 93:
#line 1259 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 94:
#line 1263 "rew_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 95:
#line 1269 "rew_parser.y"
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

  case 96:
#line 1283 "rew_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 97:
#line 1289 "rew_parser.y"
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

  case 98:
#line 1304 "rew_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 99:
#line 1311 "rew_parser.y"
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

  case 100:
#line 1325 "rew_parser.y"
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

  case 101:
#line 1341 "rew_parser.y"
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

  case 102:
#line 1359 "rew_parser.y"
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

  case 103:
#line 1375 "rew_parser.y"
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

  case 104:
#line 1386 "rew_parser.y"
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
#line 3456 "rew_parser.tab.c"
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


#line 1400 "rew_parser.y"



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

