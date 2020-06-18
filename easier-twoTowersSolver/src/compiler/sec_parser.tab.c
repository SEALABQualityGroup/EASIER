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
#define yyparse secyyparse
#define yylex   secyylex
#define yyerror secyyerror
#define yylval  secyylval
#define yychar  secyychar
#define yydebug secyydebug
#define yynerrs secyynerrs


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
     T_HIGH_SECURITY = 534,
     T_LOW_SECURITY = 535,
     T_OBS_NRESTR_INTERNALS = 536,
     T_OBS_NRESTR_INTERACTIONS = 537,
     T_ALL_OBS_NRESTR = 538,
     DOTDOT = 700,
     NE = 701,
     LE = 702,
     GE = 703,
     AND = 704,
     OR = 705
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
#define T_HIGH_SECURITY 534
#define T_LOW_SECURITY 535
#define T_OBS_NRESTR_INTERNALS 536
#define T_OBS_NRESTR_INTERACTIONS 537
#define T_ALL_OBS_NRESTR 538
#define DOTDOT 700
#define NE 701
#define LE 702
#define GE 703
#define AND 704
#define OR 705




/* Copy the first part of user declarations.  */
#line 37 "sec_parser.y"


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
#line 113 "sec_parser.y"
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
/* Line 193 of yacc.c.  */
#line 307 "sec_parser.tab.c"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif



/* Copy the second part of user declarations.  */


/* Line 216 of yacc.c.  */
#line 320 "sec_parser.tab.c"

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
#define YYLAST   1173

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  77
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  26
/* YYNRULES -- Number of rules.  */
#define YYNRULES  99
/* YYNRULES -- Number of states.  */
#define YYNSTATES  318

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   706

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    62,     2,     2,     2,     2,     2,     2,
      74,    76,    68,    66,    75,    67,    71,    69,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    70,
      64,    63,    65,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    72,     2,    73,     2,     2,     2,     2,     2,     2,
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
       2,    48,    49,    50,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,    51,    52,    53,    54,    55,     2,
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
      56,    57,    58,    59,    60,    61,     2
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     6,     7,    12,    13,    18,    21,    24,
      26,    30,    31,    36,    38,    41,    42,    48,    50,    52,
      54,    55,    61,    63,    65,    67,    69,    70,    71,    80,
      81,    83,    87,    89,    91,    95,    99,   103,   107,   114,
     119,   124,   129,   136,   143,   150,   155,   160,   165,   170,
     175,   180,   187,   194,   201,   206,   213,   220,   227,   232,
     241,   248,   257,   264,   269,   276,   281,   288,   290,   292,
     296,   300,   303,   307,   311,   315,   319,   323,   327,   332,
     337,   342,   349,   356,   363,   368,   373,   380,   389,   394,
     395,   403,   404,   414,   418,   419,   421,   423,   425,   427
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      78,     0,    -1,    81,    82,    -1,    -1,     1,    51,    79,
      78,    -1,    -1,     1,    52,    80,    82,    -1,    51,    83,
      -1,    52,    83,    -1,    85,    -1,    85,    70,    83,    -1,
      -1,     1,    70,    84,    83,    -1,    86,    -1,    91,    88,
      -1,    -1,     4,    94,    87,    71,    90,    -1,    53,    -1,
      54,    -1,    55,    -1,    -1,     4,    95,    89,    71,    90,
      -1,     4,    -1,    53,    -1,    54,    -1,    55,    -1,    -1,
      -1,     5,     4,    92,     6,    96,    93,    56,    96,    -1,
      -1,    95,    -1,    72,    96,    73,    -1,     4,    -1,     3,
      -1,    96,    66,    96,    -1,    96,    67,    96,    -1,    96,
      68,    96,    -1,    96,    69,    96,    -1,     7,    74,    96,
      75,    96,    76,    -1,    10,    74,    96,    76,    -1,    11,
      74,    96,    76,    -1,    12,    74,    96,    76,    -1,     8,
      74,    96,    75,    96,    76,    -1,     9,    74,    96,    75,
      96,    76,    -1,    13,    74,    96,    75,    96,    76,    -1,
      14,    74,    96,    76,    -1,    15,    74,    96,    76,    -1,
      16,    74,    96,    76,    -1,    17,    74,    96,    76,    -1,
      18,    74,    96,    76,    -1,    19,    74,    96,    76,    -1,
      20,    74,    96,    75,    96,    76,    -1,    21,    74,    96,
      75,    96,    76,    -1,    22,    74,    96,    75,    96,    76,
      -1,    23,    74,    96,    76,    -1,    24,    74,    96,    75,
      96,    76,    -1,    25,    74,    96,    75,    96,    76,    -1,
      26,    74,    96,    75,    96,    76,    -1,    27,    74,    96,
      76,    -1,    28,    74,    96,    75,    96,    75,    96,    76,
      -1,    29,    74,    96,    75,    96,    76,    -1,    30,    74,
      96,    75,    96,    75,    96,    76,    -1,    31,    74,    96,
      75,    96,    76,    -1,    32,    74,    96,    76,    -1,    33,
      74,    96,    75,    96,    76,    -1,    34,    74,    96,    76,
      -1,    35,    74,    96,    75,    96,    76,    -1,    36,    -1,
      37,    -1,    96,    60,    96,    -1,    96,    61,    96,    -1,
      62,    96,    -1,    96,    63,    96,    -1,    96,    57,    96,
      -1,    96,    64,    96,    -1,    96,    58,    96,    -1,    96,
      65,    96,    -1,    96,    59,    96,    -1,    38,    74,    99,
      76,    -1,    39,    74,    96,    76,    -1,    40,    74,    96,
      76,    -1,    41,    74,    96,    75,    96,    76,    -1,    42,
      74,    96,    75,    96,    76,    -1,    43,    74,    96,    75,
      96,    76,    -1,    44,    74,    96,    76,    -1,    45,    74,
     100,    76,    -1,    46,    74,    96,    75,    96,    76,    -1,
      47,    74,    96,    75,    96,    75,    96,    76,    -1,    48,
      74,   101,    76,    -1,    -1,    49,    74,     4,    97,    75,
      96,    76,    -1,    -1,    50,    74,     4,    98,    75,    96,
      75,    96,    76,    -1,    74,    96,    76,    -1,    -1,   102,
      -1,   102,    -1,   102,    -1,    96,    -1,    96,    75,   102,
      -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   238,   238,   242,   241,   250,   249,   261,   268,   274,
     277,   281,   280,   291,   294,   306,   305,   317,   321,   325,
     333,   332,   347,   362,   369,   376,   387,   391,   386,   404,
     408,   416,   433,   441,   446,   460,   474,   488,   502,   516,
     529,   542,   555,   569,   583,   597,   610,   623,   636,   649,
     662,   675,   689,   703,   717,   730,   744,   758,   772,   785,
     800,   814,   829,   843,   856,   870,   883,   897,   902,   907,
     921,   935,   948,   962,   976,   990,  1004,  1018,  1032,  1036,
    1049,  1062,  1076,  1090,  1104,  1117,  1121,  1135,  1150,  1155,
    1154,  1175,  1174,  1195,  1203,  1216,  1232,  1250,  1266,  1277
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
  "T_READ", "T_WRITE", "T_RECORD_CONS", "T_GET", "T_PUT",
  "T_HIGH_SECURITY", "T_LOW_SECURITY", "T_OBS_NRESTR_INTERNALS",
  "T_OBS_NRESTR_INTERACTIONS", "T_ALL_OBS_NRESTR", "DOTDOT", "NE", "LE",
  "GE", "AND", "OR", "'!'", "'='", "'<'", "'>'", "'+'", "'-'", "'*'",
  "'/'", "';'", "'.'", "'['", "']'", "'('", "','", "')'", "$accept",
  "sec_level_list", "@1", "@2", "high_list", "low_list", "act_type_list",
  "@3", "act_type_set", "s_act_type_set", "@4", "i_act_type_set", "@5",
  "act_type_set_hl", "iteration", "@6", "@7", "poss_aei_index",
  "aei_index", "expr", "@8", "@9", "expr_list", "expr_array",
  "expr_record", "struct_expr", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   706,   300,   301,   429,   430,   446,   447,   448,
     449,   450,   451,   452,   453,   454,   455,   456,   457,   458,
     459,   460,   461,   462,   463,   464,   465,   466,   467,   468,
     469,   470,   471,   472,   473,   474,   476,   477,   479,   480,
     481,   482,   483,   484,   485,   487,   488,   489,   491,   492,
     493,   534,   535,   536,   537,   538,   700,   701,   702,   703,
     704,   705,    33,    61,    60,    62,    43,    45,    42,    47,
      59,    46,    91,    93,    40,    44,    41
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    77,    78,    79,    78,    80,    78,    81,    82,    83,
      83,    84,    83,    85,    85,    87,    86,    86,    86,    86,
      89,    88,    90,    90,    90,    90,    92,    93,    91,    94,
      94,    95,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    97,
      96,    98,    96,    96,    99,    99,   100,   101,   102,   102
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     2,     0,     4,     0,     4,     2,     2,     1,
       3,     0,     4,     1,     2,     0,     5,     1,     1,     1,
       0,     5,     1,     1,     1,     1,     0,     0,     8,     0,
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
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     0,     3,     5,     0,    29,     0,
      17,    18,    19,     7,     9,    13,     0,     1,     0,     2,
       0,     0,    11,     0,    15,    30,    26,     0,     0,    14,
       8,     4,     6,     0,    33,    32,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    67,    68,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    10,    20,    12,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    94,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
      71,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,    31,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    98,     0,    95,
       0,     0,     0,     0,     0,     0,     0,    96,     0,     0,
       0,    97,    89,    91,    93,    73,    75,    77,    69,    70,
      72,    74,    76,    34,    35,    36,    37,    22,    23,    24,
      25,    16,    27,     0,     0,     0,     0,    39,    40,    41,
       0,    45,    46,    47,    48,    49,    50,     0,     0,     0,
      54,     0,     0,     0,    58,     0,     0,     0,     0,    63,
       0,    65,     0,     0,    78,    79,    80,     0,     0,     0,
      84,    85,     0,     0,    88,     0,     0,     0,    21,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    99,     0,     0,     0,     0,
       0,     0,     0,     0,    38,    42,    43,    44,    51,    52,
      53,    55,    56,    57,     0,    60,     0,    62,    64,    66,
      81,    82,    83,    86,     0,     0,     0,    28,     0,     0,
       0,    90,     0,    59,    61,    87,     0,    92
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     3,    20,    21,     4,    19,    13,    33,    14,    15,
      83,    29,   147,   211,    16,    84,   257,    24,    25,   177,
     255,   256,   178,   186,   190,   179
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -123
static const yytype_int16 yypact[] =
{
       2,   -43,     6,     4,   -46,  -123,  -123,   -53,   -47,    38,
    -123,  -123,  -123,  -123,   -27,  -123,    40,  -123,     6,  -123,
       2,   -46,  -123,   121,  -123,  -123,  -123,     6,   -47,  -123,
    -123,  -123,  -123,     6,  -123,  -123,   -25,   -19,   -18,   -12,
     -11,   -10,    20,    27,    30,    31,    32,    33,    34,    48,
      52,    53,    98,    99,   100,   101,   102,   103,   104,   105,
     106,   107,   108,   110,   111,  -123,  -123,   112,   113,   114,
     115,   116,   120,   122,   124,   125,   126,   127,   128,   129,
     121,   121,  1061,   -24,   201,  -123,  -123,  -123,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,   121,   121,   121,   121,    42,   207,
    1091,   -45,   121,   121,   121,   121,   121,   121,   121,   121,
     121,   121,   121,   121,  -123,    -3,   121,   145,   723,   736,
     749,   -31,   174,   188,   762,   219,   233,   247,   261,   275,
     289,   775,   788,   801,   303,   814,   827,   840,   317,   853,
     866,   879,   892,   331,   905,   345,   918,   931,   142,  -123,
     359,   373,   944,   957,   970,   387,   144,  -123,   983,   996,
     146,  -123,  -123,  -123,  -123,  1104,  1104,  1104,  1091,  1091,
    1104,  1104,  1104,   -28,   -28,  -123,  -123,  -123,  -123,  -123,
    -123,  -123,  1078,    -3,   121,   121,   121,  -123,  -123,  -123,
     121,  -123,  -123,  -123,  -123,  -123,  -123,   121,   121,   121,
    -123,   121,   121,   121,  -123,   121,   121,   121,   121,  -123,
     121,  -123,   121,   121,  -123,  -123,  -123,   121,   121,   121,
    -123,  -123,   121,   121,  -123,   148,   152,   165,  -123,   401,
     415,   429,   443,   457,   471,   485,   499,   513,   527,  1009,
     541,  1022,   555,   569,   583,  -123,   597,   611,   625,   639,
    1035,   121,   121,   121,  -123,  -123,  -123,  -123,  -123,  -123,
    -123,  -123,  -123,  -123,   121,  -123,   121,  -123,  -123,  -123,
    -123,  -123,  -123,  -123,   121,   653,  1048,  1078,   667,   681,
     695,  -123,   121,  -123,  -123,  -123,   709,  -123
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -123,   208,  -123,  -123,  -123,   215,    21,  -123,  -123,  -123,
    -123,  -123,  -123,    49,  -123,  -123,  -123,  -123,   216,   -23,
    -123,  -123,  -123,  -123,  -123,  -122
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If zero, do what YYDEFACT says.
   If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_int16 yytable[] =
{
      82,   207,   187,     1,    17,   191,    18,     7,     5,     6,
       8,     9,   132,   133,   134,   135,   136,    22,   137,   138,
     139,   140,   141,   142,   143,    23,   132,   133,   134,   135,
     136,   194,   137,   138,   139,   140,   141,   142,   143,    30,
     142,   143,    26,    27,    28,   217,   192,   145,    85,    88,
     208,   209,   210,     2,    87,    89,    90,   130,   131,    10,
      11,    12,    91,    92,    93,   148,   149,   150,   151,   152,
     153,   154,   155,   156,   157,   158,   159,   160,   161,   162,
     163,   164,   165,   166,   167,   168,   169,   170,   171,   172,
     173,   174,   175,   176,    94,   180,   181,   182,   183,   184,
     185,    95,   188,   189,    96,    97,    98,    99,   100,   195,
     196,   197,   198,   199,   200,   201,   202,   203,   204,   205,
     206,   275,   101,   212,    34,    35,   102,   103,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,    54,    55,    56,    57,
      58,    59,    60,    61,    62,    63,    64,    65,    66,    67,
      68,    69,    70,    71,    72,    73,    74,    75,    76,    77,
      78,    79,   104,   105,   106,   107,   108,   109,   110,   111,
     112,   113,   114,    80,   115,   116,   117,   118,   119,   120,
     121,   259,   260,   261,   122,    81,   123,   262,   124,   125,
     126,   127,   128,   129,   263,   264,   265,   146,   266,   267,
     268,   193,   269,   270,   271,   272,   213,   273,   244,   274,
     251,   283,   254,   281,   276,   277,   278,   282,    31,   279,
     280,   132,   133,   134,   135,   136,    32,   137,   138,   139,
     140,   141,   142,   143,    86,   132,   133,   134,   135,   136,
     218,   137,   138,   139,   140,   141,   142,   143,   305,   306,
     307,     0,   258,     0,   219,     0,     0,     0,     0,     0,
       0,   308,     0,   309,     0,     0,   132,   133,   134,   135,
     136,   310,   137,   138,   139,   140,   141,   142,   143,   316,
     132,   133,   134,   135,   136,   221,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   222,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   223,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   224,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   225,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   226,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   230,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   234,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   239,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   241,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   245,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   246,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   250,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   284,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   285,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   286,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   287,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   288,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   289,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   290,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   291,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   292,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   293,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   295,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   297,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   298,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   299,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   300,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   301,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   302,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   303,   137,   138,   139,   140,
     141,   142,   143,     0,   132,   133,   134,   135,   136,   311,
     137,   138,   139,   140,   141,   142,   143,     0,   132,   133,
     134,   135,   136,   313,   137,   138,   139,   140,   141,   142,
     143,     0,   132,   133,   134,   135,   136,   314,   137,   138,
     139,   140,   141,   142,   143,     0,   132,   133,   134,   135,
     136,   315,   137,   138,   139,   140,   141,   142,   143,     0,
     132,   133,   134,   135,   136,   317,   137,   138,   139,   140,
     141,   142,   143,   132,   133,   134,   135,   136,   214,   137,
     138,   139,   140,   141,   142,   143,   132,   133,   134,   135,
     136,   215,   137,   138,   139,   140,   141,   142,   143,   132,
     133,   134,   135,   136,   216,   137,   138,   139,   140,   141,
     142,   143,   132,   133,   134,   135,   136,   220,   137,   138,
     139,   140,   141,   142,   143,   132,   133,   134,   135,   136,
     227,   137,   138,   139,   140,   141,   142,   143,   132,   133,
     134,   135,   136,   228,   137,   138,   139,   140,   141,   142,
     143,   132,   133,   134,   135,   136,   229,   137,   138,   139,
     140,   141,   142,   143,   132,   133,   134,   135,   136,   231,
     137,   138,   139,   140,   141,   142,   143,   132,   133,   134,
     135,   136,   232,   137,   138,   139,   140,   141,   142,   143,
     132,   133,   134,   135,   136,   233,   137,   138,   139,   140,
     141,   142,   143,   132,   133,   134,   135,   136,   235,   137,
     138,   139,   140,   141,   142,   143,   132,   133,   134,   135,
     136,   236,   137,   138,   139,   140,   141,   142,   143,   132,
     133,   134,   135,   136,   237,   137,   138,   139,   140,   141,
     142,   143,   132,   133,   134,   135,   136,   238,   137,   138,
     139,   140,   141,   142,   143,   132,   133,   134,   135,   136,
     240,   137,   138,   139,   140,   141,   142,   143,   132,   133,
     134,   135,   136,   242,   137,   138,   139,   140,   141,   142,
     143,   132,   133,   134,   135,   136,   243,   137,   138,   139,
     140,   141,   142,   143,   132,   133,   134,   135,   136,   247,
     137,   138,   139,   140,   141,   142,   143,   132,   133,   134,
     135,   136,   248,   137,   138,   139,   140,   141,   142,   143,
     132,   133,   134,   135,   136,   249,   137,   138,   139,   140,
     141,   142,   143,   132,   133,   134,   135,   136,   252,   137,
     138,   139,   140,   141,   142,   143,   132,   133,   134,   135,
     136,   253,   137,   138,   139,   140,   141,   142,   143,   132,
     133,   134,   135,   136,   294,   137,   138,   139,   140,   141,
     142,   143,   132,   133,   134,   135,   136,   296,   137,   138,
     139,   140,   141,   142,   143,   132,   133,   134,   135,   136,
     304,   137,   138,   139,   140,   141,   142,   143,   132,   133,
     134,   135,   136,   312,   137,   138,   139,   140,   141,   142,
     143,     0,     0,     0,   144,   132,   133,   134,   135,   136,
       0,   137,   138,   139,   140,   141,   142,   143,   132,   133,
     134,     0,     0,     0,   137,   138,   139,   140,   141,   142,
     143,    -1,    -1,    -1,     0,     0,     0,    -1,    -1,    -1,
     140,   141,   142,   143
};

static const yytype_int16 yycheck[] =
{
      23,     4,   124,     1,     0,   127,    52,     1,    51,    52,
       4,     5,    57,    58,    59,    60,    61,    70,    63,    64,
      65,    66,    67,    68,    69,    72,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    18,
      68,    69,     4,    70,     4,    76,     4,    71,    27,    74,
      53,    54,    55,    51,    33,    74,    74,    80,    81,    53,
      54,    55,    74,    74,    74,    88,    89,    90,    91,    92,
      93,    94,    95,    96,    97,    98,    99,   100,   101,   102,
     103,   104,   105,   106,   107,   108,   109,   110,   111,   112,
     113,   114,   115,   116,    74,   118,   119,   120,   121,   122,
     123,    74,   125,   126,    74,    74,    74,    74,    74,   132,
     133,   134,   135,   136,   137,   138,   139,   140,   141,   142,
     143,   243,    74,   146,     3,     4,    74,    74,     7,     8,
       9,    10,    11,    12,    13,    14,    15,    16,    17,    18,
      19,    20,    21,    22,    23,    24,    25,    26,    27,    28,
      29,    30,    31,    32,    33,    34,    35,    36,    37,    38,
      39,    40,    41,    42,    43,    44,    45,    46,    47,    48,
      49,    50,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    62,    74,    74,    74,    74,    74,    74,
      74,   214,   215,   216,    74,    74,    74,   220,    74,    74,
      74,    74,    74,    74,   227,   228,   229,     6,   231,   232,
     233,     4,   235,   236,   237,   238,    71,   240,    76,   242,
      76,    56,    76,    75,   247,   248,   249,    75,    20,   252,
     253,    57,    58,    59,    60,    61,    21,    63,    64,    65,
      66,    67,    68,    69,    28,    57,    58,    59,    60,    61,
      76,    63,    64,    65,    66,    67,    68,    69,   281,   282,
     283,    -1,   213,    -1,    76,    -1,    -1,    -1,    -1,    -1,
      -1,   294,    -1,   296,    -1,    -1,    57,    58,    59,    60,
      61,   304,    63,    64,    65,    66,    67,    68,    69,   312,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    -1,    57,    58,    59,    60,    61,    76,
      63,    64,    65,    66,    67,    68,    69,    -1,    57,    58,
      59,    60,    61,    76,    63,    64,    65,    66,    67,    68,
      69,    -1,    57,    58,    59,    60,    61,    76,    63,    64,
      65,    66,    67,    68,    69,    -1,    57,    58,    59,    60,
      61,    76,    63,    64,    65,    66,    67,    68,    69,    -1,
      57,    58,    59,    60,    61,    76,    63,    64,    65,    66,
      67,    68,    69,    57,    58,    59,    60,    61,    75,    63,
      64,    65,    66,    67,    68,    69,    57,    58,    59,    60,
      61,    75,    63,    64,    65,    66,    67,    68,    69,    57,
      58,    59,    60,    61,    75,    63,    64,    65,    66,    67,
      68,    69,    57,    58,    59,    60,    61,    75,    63,    64,
      65,    66,    67,    68,    69,    57,    58,    59,    60,    61,
      75,    63,    64,    65,    66,    67,    68,    69,    57,    58,
      59,    60,    61,    75,    63,    64,    65,    66,    67,    68,
      69,    57,    58,    59,    60,    61,    75,    63,    64,    65,
      66,    67,    68,    69,    57,    58,    59,    60,    61,    75,
      63,    64,    65,    66,    67,    68,    69,    57,    58,    59,
      60,    61,    75,    63,    64,    65,    66,    67,    68,    69,
      57,    58,    59,    60,    61,    75,    63,    64,    65,    66,
      67,    68,    69,    57,    58,    59,    60,    61,    75,    63,
      64,    65,    66,    67,    68,    69,    57,    58,    59,    60,
      61,    75,    63,    64,    65,    66,    67,    68,    69,    57,
      58,    59,    60,    61,    75,    63,    64,    65,    66,    67,
      68,    69,    57,    58,    59,    60,    61,    75,    63,    64,
      65,    66,    67,    68,    69,    57,    58,    59,    60,    61,
      75,    63,    64,    65,    66,    67,    68,    69,    57,    58,
      59,    60,    61,    75,    63,    64,    65,    66,    67,    68,
      69,    57,    58,    59,    60,    61,    75,    63,    64,    65,
      66,    67,    68,    69,    57,    58,    59,    60,    61,    75,
      63,    64,    65,    66,    67,    68,    69,    57,    58,    59,
      60,    61,    75,    63,    64,    65,    66,    67,    68,    69,
      57,    58,    59,    60,    61,    75,    63,    64,    65,    66,
      67,    68,    69,    57,    58,    59,    60,    61,    75,    63,
      64,    65,    66,    67,    68,    69,    57,    58,    59,    60,
      61,    75,    63,    64,    65,    66,    67,    68,    69,    57,
      58,    59,    60,    61,    75,    63,    64,    65,    66,    67,
      68,    69,    57,    58,    59,    60,    61,    75,    63,    64,
      65,    66,    67,    68,    69,    57,    58,    59,    60,    61,
      75,    63,    64,    65,    66,    67,    68,    69,    57,    58,
      59,    60,    61,    75,    63,    64,    65,    66,    67,    68,
      69,    -1,    -1,    -1,    73,    57,    58,    59,    60,    61,
      -1,    63,    64,    65,    66,    67,    68,    69,    57,    58,
      59,    -1,    -1,    -1,    63,    64,    65,    66,    67,    68,
      69,    57,    58,    59,    -1,    -1,    -1,    63,    64,    65,
      66,    67,    68,    69
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     1,    51,    78,    81,    51,    52,     1,     4,     5,
      53,    54,    55,    83,    85,    86,    91,     0,    52,    82,
      79,    80,    70,    72,    94,    95,     4,    70,     4,    88,
      83,    78,    82,    84,     3,     4,     7,     8,     9,    10,
      11,    12,    13,    14,    15,    16,    17,    18,    19,    20,
      21,    22,    23,    24,    25,    26,    27,    28,    29,    30,
      31,    32,    33,    34,    35,    36,    37,    38,    39,    40,
      41,    42,    43,    44,    45,    46,    47,    48,    49,    50,
      62,    74,    96,    87,    92,    83,    95,    83,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      74,    74,    74,    74,    74,    74,    74,    74,    74,    74,
      96,    96,    57,    58,    59,    60,    61,    63,    64,    65,
      66,    67,    68,    69,    73,    71,     6,    89,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    99,   102,
      96,    96,    96,    96,    96,    96,   100,   102,    96,    96,
     101,   102,     4,     4,    76,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,    96,    96,     4,    53,    54,
      55,    90,    96,    71,    75,    75,    75,    76,    76,    76,
      75,    76,    76,    76,    76,    76,    76,    75,    75,    75,
      76,    75,    75,    75,    76,    75,    75,    75,    75,    76,
      75,    76,    75,    75,    76,    76,    76,    75,    75,    75,
      76,    76,    75,    75,    76,    97,    98,    93,    90,    96,
      96,    96,    96,    96,    96,    96,    96,    96,    96,    96,
      96,    96,    96,    96,    96,   102,    96,    96,    96,    96,
      96,    75,    75,    56,    76,    76,    76,    76,    76,    76,
      76,    76,    76,    76,    75,    76,    75,    76,    76,    76,
      76,    76,    76,    76,    75,    96,    96,    96,    96,    96,
      96,    76,    75,    76,    76,    76,    96,    76
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
#line 239 "sec_parser.y"
    {
			    ;}
    break;

  case 3:
#line 242 "sec_parser.y"
    {
                              handle_sync_sec();
                              yyerrok;
                            ;}
    break;

  case 4:
#line 247 "sec_parser.y"
    {
                            ;}
    break;

  case 5:
#line 250 "sec_parser.y"
    {
			      variation = MADE_LOW;
                              handle_sync_sec();
                              yyerrok;
                            ;}
    break;

  case 6:
#line 256 "sec_parser.y"
    {
                            ;}
    break;

  case 7:
#line 262 "sec_parser.y"
    {
			      variation = MADE_LOW;
                            ;}
    break;

  case 8:
#line 269 "sec_parser.y"
    {
                            ;}
    break;

  case 9:
#line 275 "sec_parser.y"
    {
			    ;}
    break;

  case 10:
#line 278 "sec_parser.y"
    {
			    ;}
    break;

  case 11:
#line 281 "sec_parser.y"
    {
                              handle_sync_sec();
                              yyerrok;
                            ;}
    break;

  case 12:
#line 286 "sec_parser.y"
    {
			    ;}
    break;

  case 13:
#line 292 "sec_parser.y"
    {
			    ;}
    break;

  case 14:
#line 295 "sec_parser.y"
    {
			      selector_enabled[0] = FALSE;
			      if (((yyvsp[(1) - (2)].st_bucket) != NULL) &&
				  (yyvsp[(2) - (2)].boolean))
				expand_iterative_act_type_decl();
			      selector[0] = NULL;
			    ;}
    break;

  case 15:
#line 306 "sec_parser.y"
    {
			      handle_concretely_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							    (yyvsp[(2) - (2)].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			      aei = (yyvsp[(1) - (2)].st_bucket);
                            ;}
    break;

  case 16:
#line 315 "sec_parser.y"
    {
			    ;}
    break;

  case 17:
#line 318 "sec_parser.y"
    {
			      associate_obs_nrestr_internals();
			    ;}
    break;

  case 18:
#line 322 "sec_parser.y"
    {
			      associate_obs_nrestr_interactions();
			    ;}
    break;

  case 19:
#line 326 "sec_parser.y"
    {
			      associate_all_obs_nrestr();
			    ;}
    break;

  case 20:
#line 333 "sec_parser.y"
    {
			      handle_symbolically_indexed_aei(&(yyvsp[(1) - (2)].st_bucket),
							      (yyvsp[(2) - (2)].expr_parse_info),
							      0,
							      FALSE);
			      aei = (yyvsp[(1) - (2)].st_bucket);
                            ;}
    break;

  case 21:
#line 341 "sec_parser.y"
    {
			      (yyval.boolean) = (yyvsp[(5) - (5)].boolean);
			    ;}
    break;

  case 22:
#line 348 "sec_parser.y"
    {
			      handle_prefixed_indexed_id(aei,
						         &(yyvsp[(1) - (1)].st_bucket),
						         0,
						         selector_enabled[0],
						         FALSE,
							 ACT_TYPE_ID_USE_AUX_SPEC);
			      if (((yyvsp[(1) - (1)].st_bucket) != NULL) &&
				  !selector_enabled[0])
				associate_security_level((yyvsp[(1) - (1)].st_bucket),
							 FALSE,
							 FALSE);
			      (yyval.boolean) = ((yyvsp[(1) - (1)].st_bucket) != NULL);
			    ;}
    break;

  case 23:
#line 363 "sec_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_internals(aei);
			      (yyval.boolean) = (aei != NULL);
			    ;}
    break;

  case 24:
#line 370 "sec_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_interactions(aei);
			      (yyval.boolean) = (aei != NULL);
			    ;}
    break;

  case 25:
#line 377 "sec_parser.y"
    {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_all_obs_nrestr(aei);
			      (yyval.boolean) = (aei != NULL);
			    ;}
    break;

  case 26:
#line 387 "sec_parser.y"
    {
			      handle_iteration_1(&(yyvsp[(2) - (2)].st_bucket));
			    ;}
    break;

  case 27:
#line 391 "sec_parser.y"
    {
			      handle_iteration_2(&(yyvsp[(5) - (5)].expr_parse_info));
			    ;}
    break;

  case 28:
#line 395 "sec_parser.y"
    {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[(2) - (8)].st_bucket),
						      (yyvsp[(5) - (8)].expr_parse_info),
						      (yyvsp[(8) - (8)].expr_parse_info));
			    ;}
    break;

  case 29:
#line 404 "sec_parser.y"
    {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    ;}
    break;

  case 30:
#line 409 "sec_parser.y"
    {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[(1) - (1)].expr_parse_info);
			    ;}
    break;

  case 31:
#line 417 "sec_parser.y"
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

  case 32:
#line 434 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[(1) - (1)].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    ;}
    break;

  case 33:
#line 442 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 34:
#line 447 "sec_parser.y"
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

  case 35:
#line 461 "sec_parser.y"
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

  case 36:
#line 475 "sec_parser.y"
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

  case 37:
#line 489 "sec_parser.y"
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

  case 38:
#line 503 "sec_parser.y"
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

  case 39:
#line 517 "sec_parser.y"
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

  case 40:
#line 530 "sec_parser.y"
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

  case 41:
#line 543 "sec_parser.y"
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

  case 42:
#line 556 "sec_parser.y"
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

  case 43:
#line 570 "sec_parser.y"
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

  case 44:
#line 584 "sec_parser.y"
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

  case 45:
#line 598 "sec_parser.y"
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

  case 46:
#line 611 "sec_parser.y"
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

  case 47:
#line 624 "sec_parser.y"
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

  case 48:
#line 637 "sec_parser.y"
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

  case 49:
#line 650 "sec_parser.y"
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

  case 50:
#line 663 "sec_parser.y"
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

  case 51:
#line 676 "sec_parser.y"
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

  case 52:
#line 690 "sec_parser.y"
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

  case 53:
#line 704 "sec_parser.y"
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

  case 54:
#line 718 "sec_parser.y"
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

  case 55:
#line 731 "sec_parser.y"
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

  case 56:
#line 745 "sec_parser.y"
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

  case 57:
#line 759 "sec_parser.y"
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

  case 58:
#line 773 "sec_parser.y"
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

  case 59:
#line 786 "sec_parser.y"
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

  case 60:
#line 801 "sec_parser.y"
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

  case 61:
#line 815 "sec_parser.y"
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

  case 62:
#line 830 "sec_parser.y"
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

  case 63:
#line 844 "sec_parser.y"
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

  case 64:
#line 857 "sec_parser.y"
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

  case 65:
#line 871 "sec_parser.y"
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

  case 66:
#line 884 "sec_parser.y"
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

  case 67:
#line 898 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 68:
#line 903 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[(1) - (1)].st_bucket),
							 NULL);
			    ;}
    break;

  case 69:
#line 908 "sec_parser.y"
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

  case 70:
#line 922 "sec_parser.y"
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

  case 71:
#line 936 "sec_parser.y"
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

  case 72:
#line 949 "sec_parser.y"
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

  case 73:
#line 963 "sec_parser.y"
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

  case 74:
#line 977 "sec_parser.y"
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

  case 75:
#line 991 "sec_parser.y"
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

  case 76:
#line 1005 "sec_parser.y"
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

  case 77:
#line 1019 "sec_parser.y"
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

  case 78:
#line 1033 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 79:
#line 1037 "sec_parser.y"
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

  case 80:
#line 1050 "sec_parser.y"
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

  case 81:
#line 1063 "sec_parser.y"
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

  case 82:
#line 1077 "sec_parser.y"
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

  case 83:
#line 1091 "sec_parser.y"
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

  case 84:
#line 1105 "sec_parser.y"
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

  case 85:
#line 1118 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 86:
#line 1122 "sec_parser.y"
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

  case 87:
#line 1136 "sec_parser.y"
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

  case 88:
#line 1151 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(3) - (4)].expr_parse_info);
			    ;}
    break;

  case 89:
#line 1155 "sec_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 90:
#line 1161 "sec_parser.y"
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

  case 91:
#line 1175 "sec_parser.y"
    {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[(3) - (3)].st_bucket),
				       FALSE);
			    ;}
    break;

  case 92:
#line 1181 "sec_parser.y"
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

  case 93:
#line 1196 "sec_parser.y"
    {
			      (yyval.expr_parse_info) = (yyvsp[(2) - (3)].expr_parse_info);
			    ;}
    break;

  case 94:
#line 1203 "sec_parser.y"
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

  case 95:
#line 1217 "sec_parser.y"
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

  case 96:
#line 1233 "sec_parser.y"
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

  case 97:
#line 1251 "sec_parser.y"
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

  case 98:
#line 1267 "sec_parser.y"
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

  case 99:
#line 1278 "sec_parser.y"
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
#line 3277 "sec_parser.tab.c"
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


#line 1292 "sec_parser.y"



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

