/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2020 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

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

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.5.1"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1


/* Substitute the variable and function names.  */
#define yyparse         rewyyparse
#define yylex           rewyylex
#define yyerror         rewyyerror
#define yydebug         rewyydebug
#define yynerrs         rewyynerrs
#define yylval          rewyylval
#define yychar          rewyychar

/* First part of user prologue.  */
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


#line 156 "rew_parser.tab.c"

# ifndef YY_CAST
#  ifdef __cplusplus
#   define YY_CAST(Type, Val) static_cast<Type> (Val)
#   define YY_REINTERPRET_CAST(Type, Val) reinterpret_cast<Type> (Val)
#  else
#   define YY_CAST(Type, Val) ((Type) (Val))
#   define YY_REINTERPRET_CAST(Type, Val) ((Type) (Val))
#  endif
# endif
# ifndef YY_NULLPTR
#  if defined __cplusplus
#   if 201103L <= __cplusplus
#    define YY_NULLPTR nullptr
#   else
#    define YY_NULLPTR 0
#   endif
#  else
#   define YY_NULLPTR ((void*)0)
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Use api.header.include to #include this header
   instead of duplicating it here.  */
#ifndef YY_REWYY_REW_PARSER_TAB_H_INCLUDED
# define YY_REWYY_REW_PARSER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int rewyydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
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

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 110 "rew_parser.y"

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

#line 284 "rew_parser.tab.c"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE rewyylval;

int rewyyparse (void);

#endif /* !YY_REWYY_REW_PARSER_TAB_H_INCLUDED  */



#ifdef short
# undef short
#endif

/* On compilers that do not define __PTRDIFF_MAX__ etc., make sure
   <limits.h> and (if available) <stdint.h> are included
   so that the code can choose integer types of a good width.  */

#ifndef __PTRDIFF_MAX__
# include <limits.h> /* INFRINGES ON USER NAME SPACE */
# if defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stdint.h> /* INFRINGES ON USER NAME SPACE */
#  define YY_STDINT_H
# endif
#endif

/* Narrow types that promote to a signed type and that can represent a
   signed or unsigned integer of at least N bits.  In tables they can
   save space and decrease cache pressure.  Promoting to a signed type
   helps avoid bugs in integer arithmetic.  */

#ifdef __INT_LEAST8_MAX__
typedef __INT_LEAST8_TYPE__ yytype_int8;
#elif defined YY_STDINT_H
typedef int_least8_t yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef __INT_LEAST16_MAX__
typedef __INT_LEAST16_TYPE__ yytype_int16;
#elif defined YY_STDINT_H
typedef int_least16_t yytype_int16;
#else
typedef short yytype_int16;
#endif

#if defined __UINT_LEAST8_MAX__ && __UINT_LEAST8_MAX__ <= __INT_MAX__
typedef __UINT_LEAST8_TYPE__ yytype_uint8;
#elif (!defined __UINT_LEAST8_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST8_MAX <= INT_MAX)
typedef uint_least8_t yytype_uint8;
#elif !defined __UINT_LEAST8_MAX__ && UCHAR_MAX <= INT_MAX
typedef unsigned char yytype_uint8;
#else
typedef short yytype_uint8;
#endif

#if defined __UINT_LEAST16_MAX__ && __UINT_LEAST16_MAX__ <= __INT_MAX__
typedef __UINT_LEAST16_TYPE__ yytype_uint16;
#elif (!defined __UINT_LEAST16_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST16_MAX <= INT_MAX)
typedef uint_least16_t yytype_uint16;
#elif !defined __UINT_LEAST16_MAX__ && USHRT_MAX <= INT_MAX
typedef unsigned short yytype_uint16;
#else
typedef int yytype_uint16;
#endif

#ifndef YYPTRDIFF_T
# if defined __PTRDIFF_TYPE__ && defined __PTRDIFF_MAX__
#  define YYPTRDIFF_T __PTRDIFF_TYPE__
#  define YYPTRDIFF_MAXIMUM __PTRDIFF_MAX__
# elif defined PTRDIFF_MAX
#  ifndef ptrdiff_t
#   include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  endif
#  define YYPTRDIFF_T ptrdiff_t
#  define YYPTRDIFF_MAXIMUM PTRDIFF_MAX
# else
#  define YYPTRDIFF_T long
#  define YYPTRDIFF_MAXIMUM LONG_MAX
# endif
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned
# endif
#endif

#define YYSIZE_MAXIMUM                                  \
  YY_CAST (YYPTRDIFF_T,                                 \
           (YYPTRDIFF_MAXIMUM < YY_CAST (YYSIZE_T, -1)  \
            ? YYPTRDIFF_MAXIMUM                         \
            : YY_CAST (YYSIZE_T, -1)))

#define YYSIZEOF(X) YY_CAST (YYPTRDIFF_T, sizeof (X))

/* Stored state numbers (used for stacks). */
typedef yytype_int16 yy_state_t;

/* State numbers in computations.  */
typedef int yy_state_fast_t;

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# if defined __GNUC__ && 2 < __GNUC__ + (96 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_PURE __attribute__ ((__pure__))
# else
#  define YY_ATTRIBUTE_PURE
# endif
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# if defined __GNUC__ && 2 < __GNUC__ + (7 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_UNUSED __attribute__ ((__unused__))
# else
#  define YY_ATTRIBUTE_UNUSED
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && ! defined __ICC && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                            \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")              \
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END      \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

#if defined __cplusplus && defined __GNUC__ && ! defined __ICC && 6 <= __GNUC__
# define YY_IGNORE_USELESS_CAST_BEGIN                          \
    _Pragma ("GCC diagnostic push")                            \
    _Pragma ("GCC diagnostic ignored \"-Wuseless-cast\"")
# define YY_IGNORE_USELESS_CAST_END            \
    _Pragma ("GCC diagnostic pop")
#endif
#ifndef YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_END
#endif


#define YY_ASSERT(E) ((void) (0 && (E)))

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
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
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
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
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
  yy_state_t yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (YYSIZEOF (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (YYSIZEOF (yy_state_t) + YYSIZEOF (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYPTRDIFF_T yynewbytes;                                         \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * YYSIZEOF (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / YYSIZEOF (*yyptr);                        \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, YY_CAST (YYSIZE_T, (Count)) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYPTRDIFF_T yyi;                      \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

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
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  344

#define YYUNDEFTOK  2
#define YYMAXUTOK   707


/* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, with out-of-bounds checking.  */
#define YYTRANSLATE(YYX)                                                \
  (0 <= (YYX) && (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex.  */
static const yytype_int8 yytranslate[] =
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
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_int16 yyrline[] =
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

#if YYDEBUG || YYERROR_VERBOSE || 0
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
  "$accept", "measure_def_list", "$@1", "$@2", "measure_def",
  "s_measure_def", "$@3", "i_measure_def", "$@4", "type_yb_list",
  "type_yb", "s_type_yb", "$@5", "$@6", "i_type_yb", "$@7", "$@8",
  "reward", "$@9", "$@10", "iteration", "$@11", "$@12", "nested_iteration",
  "$@13", "poss_aei_index", "aei_index", "expr", "$@14", "$@15",
  "expr_list", "expr_array", "expr_record", "struct_expr", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_int16 yytoknum[] =
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

#define YYPACT_NINF (-154)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-33)

#define yytable_value_is_error(Yyn) \
  ((Yyn) == YYTABLE_NINF)

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
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

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_int8 yydefact[] =
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

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -154,    27,  -154,  -154,  -154,  -154,  -154,  -154,  -154,  -153,
    -154,  -154,  -154,  -154,  -154,  -154,  -154,    45,  -154,  -154,
     203,  -154,  -154,  -154,  -154,   139,   -22,   -19,  -154,  -154,
    -154,  -154,  -154,  -113
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     4,    17,    16,     5,     6,    76,    15,   137,   200,
     201,   202,   314,   325,   254,   321,   328,   333,   340,   341,
       7,    18,   138,   203,   204,    20,    21,   168,   248,   249,
     169,   177,   181,   170
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
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
static const yytype_int8 yystos[] =
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

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_int8 yyr1[] =
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

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_int8 yyr2[] =
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


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                    \
  do                                                              \
    if (yychar == YYEMPTY)                                        \
      {                                                           \
        yychar = (Token);                                         \
        yylval = (Value);                                         \
        YYPOPSTACK (yylen);                                       \
        yystate = *yyssp;                                         \
        goto yybackup;                                            \
      }                                                           \
    else                                                          \
      {                                                           \
        yyerror (YY_("syntax error: cannot back up")); \
        YYERROR;                                                  \
      }                                                           \
  while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*-----------------------------------.
| Print this symbol's value on YYO.  |
`-----------------------------------*/

static void
yy_symbol_value_print (FILE *yyo, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyoutput = yyo;
  YYUSE (yyoutput);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyo, yytoknum[yytype], *yyvaluep);
# endif
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/*---------------------------.
| Print this symbol on YYO.  |
`---------------------------*/

static void
yy_symbol_print (FILE *yyo, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyo, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyo, yytype, yyvaluep);
  YYFPRINTF (yyo, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yy_state_t *yybottom, yy_state_t *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yy_state_t *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %d):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[+yyssp[yyi + 1 - yynrhs]],
                       &yyvsp[(yyi + 1) - (yynrhs)]
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

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
#ifndef YYINITDEPTH
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
#   define yystrlen(S) (YY_CAST (YYPTRDIFF_T, strlen (S)))
#  else
/* Return the length of YYSTR.  */
static YYPTRDIFF_T
yystrlen (const char *yystr)
{
  YYPTRDIFF_T yylen;
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
static char *
yystpcpy (char *yydest, const char *yysrc)
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
static YYPTRDIFF_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYPTRDIFF_T yyn = 0;
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
            else
              goto append;

          append:
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

  if (yyres)
    return yystpcpy (yyres, yystr) - yyres;
  else
    return yystrlen (yystr);
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYPTRDIFF_T *yymsg_alloc, char **yymsg,
                yy_state_t *yyssp, int yytoken)
{
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat: reported tokens (one for the "unexpected",
     one per "expected"). */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Actual size of YYARG. */
  int yycount = 0;
  /* Cumulated lengths of YYARG.  */
  YYPTRDIFF_T yysize = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[+*yyssp];
      YYPTRDIFF_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
      yysize = yysize0;
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYPTRDIFF_T yysize1
                    = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
                    yysize = yysize1;
                  else
                    return 2;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
    default: /* Avoid compiler warnings. */
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    /* Don't count the "%s"s in the final size, but reserve room for
       the terminator.  */
    YYPTRDIFF_T yysize1 = yysize + (yystrlen (yyformat) - 2 * yycount) + 1;
    if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
      yysize = yysize1;
    else
      return 2;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          ++yyp;
          ++yyformat;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    yy_state_fast_t yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yy_state_t yyssa[YYINITDEPTH];
    yy_state_t *yyss;
    yy_state_t *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYPTRDIFF_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYPTRDIFF_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;


/*------------------------------------------------------------.
| yynewstate -- push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;


/*--------------------------------------------------------------------.
| yysetstate -- set current state (the top of the stack) to yystate.  |
`--------------------------------------------------------------------*/
yysetstate:
  YYDPRINTF ((stderr, "Entering state %d\n", yystate));
  YY_ASSERT (0 <= yystate && yystate < YYNSTATES);
  YY_IGNORE_USELESS_CAST_BEGIN
  *yyssp = YY_CAST (yy_state_t, yystate);
  YY_IGNORE_USELESS_CAST_END

  if (yyss + yystacksize - 1 <= yyssp)
#if !defined yyoverflow && !defined YYSTACK_RELOCATE
    goto yyexhaustedlab;
#else
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYPTRDIFF_T yysize = yyssp - yyss + 1;

# if defined yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        yy_state_t *yyss1 = yyss;
        YYSTYPE *yyvs1 = yyvs;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * YYSIZEOF (*yyssp),
                    &yyvs1, yysize * YYSIZEOF (*yyvsp),
                    &yystacksize);
        yyss = yyss1;
        yyvs = yyvs1;
      }
# else /* defined YYSTACK_RELOCATE */
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yy_state_t *yyss1 = yyss;
        union yyalloc *yyptr =
          YY_CAST (union yyalloc *,
                   YYSTACK_ALLOC (YY_CAST (YYSIZE_T, YYSTACK_BYTES (yystacksize))));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
# undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YY_IGNORE_USELESS_CAST_BEGIN
      YYDPRINTF ((stderr, "Stack size increased to %ld\n",
                  YY_CAST (long, yystacksize)));
      YY_IGNORE_USELESS_CAST_END

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }
#endif /* !defined yyoverflow && !defined YYSTACK_RELOCATE */

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;


/*-----------.
| yybackup.  |
`-----------*/
yybackup:
  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
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
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);
  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  /* Discard the shifted token.  */
  yychar = YYEMPTY;
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
| yyreduce -- do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

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
			      rew_measure_list = (yyvsp[0].st_bucket_cell);
			    }
#line 1924 "rew_parser.tab.c"
    break;

  case 3:
#line 248 "rew_parser.y"
                            {
			      rew_measure_list = append_list((yyvsp[-2].st_bucket_cell),
							     rew_measure_list);
			    }
#line 1933 "rew_parser.tab.c"
    break;

  case 4:
#line 253 "rew_parser.y"
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
#line 1942 "rew_parser.tab.c"
    break;

  case 5:
#line 258 "rew_parser.y"
                            {
                            }
#line 1949 "rew_parser.tab.c"
    break;

  case 6:
#line 261 "rew_parser.y"
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
#line 1958 "rew_parser.tab.c"
    break;

  case 7:
#line 266 "rew_parser.y"
                            {
                            }
#line 1965 "rew_parser.tab.c"
    break;

  case 8:
#line 272 "rew_parser.y"
                            {
			      (yyval.st_bucket_cell) = (yyvsp[0].st_bucket_cell);
                            }
#line 1973 "rew_parser.tab.c"
    break;

  case 9:
#line 276 "rew_parser.y"
                            {
			      selector_enabled[0] = FALSE;
			      selector[0] = NULL;
			      (yyval.st_bucket_cell) = (yyvsp[0].st_bucket_cell);
                            }
#line 1983 "rew_parser.tab.c"
    break;

  case 10:
#line 285 "rew_parser.y"
                            {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[-1].st_bucket),
						                      (yyvsp[0].expr_parse_info),
								      MEASURE_ID_DEF);
			      if ((yyvsp[-1].st_bucket) != NULL)
			      {
			        init_rew_measure_no = ++rew_measure_num;
				(yyvsp[-1].st_bucket)->info.rew_measure = alloc_rew_measure(NULL);
			        aux_rew_measure_list = alloc_st_bucket_cell((yyvsp[-1].st_bucket),
									    NULL);
			      }
			      else
			        aux_rew_measure_list = NULL;
			    }
#line 2002 "rew_parser.tab.c"
    break;

  case 11:
#line 300 "rew_parser.y"
                            {
			      (yyval.st_bucket_cell) = aux_rew_measure_list;
			    }
#line 2010 "rew_parser.tab.c"
    break;

  case 12:
#line 307 "rew_parser.y"
                            {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[-1].st_bucket),
						                        (yyvsp[0].expr_parse_info),
						                        0,
								        MEASURE_ID_DEF);
			      selector_enabled[0] = FALSE;
			      aux_rew_measure_list = ((selector[0] == NULL) ||
						      (selector[0]->info.expr->opn1 == NULL) ||
						      ((yyvsp[-1].st_bucket) == NULL))?
						       NULL:
						       expand_iterative_rew_measure_def((yyvsp[-1].st_bucket));
			      selector_enabled[0] = TRUE;
			    }
#line 2028 "rew_parser.tab.c"
    break;

  case 13:
#line 321 "rew_parser.y"
                            {
			      (yyval.st_bucket_cell) = aux_rew_measure_list;
			    }
#line 2036 "rew_parser.tab.c"
    break;

  case 14:
#line 328 "rew_parser.y"
                            {
			    }
#line 2043 "rew_parser.tab.c"
    break;

  case 15:
#line 331 "rew_parser.y"
                            {
			    }
#line 2050 "rew_parser.tab.c"
    break;

  case 16:
#line 334 "rew_parser.y"
                            {
                              handle_sync_rew();
                              yyerrok;
                            }
#line 2059 "rew_parser.tab.c"
    break;

  case 17:
#line 342 "rew_parser.y"
                            {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ((yyvsp[0].st_bucket3_cell) != NULL))
				handle_type_yb((yyvsp[0].st_bucket3_cell));
			      selector_enabled[0] = aux_enabled;
                            }
#line 2072 "rew_parser.tab.c"
    break;

  case 18:
#line 351 "rew_parser.y"
                            {
			      aux_enabled = selector_enabled[0];
			      selector_enabled[0] = selector_enabled[1] = FALSE;
			      if ((aux_rew_measure_list != NULL) &&
				  ((yyvsp[-1].st_bucket) != NULL) &&
				  ((yyvsp[0].st_bucket3_cell) != NULL))
				handle_type_yb((yyvsp[0].st_bucket3_cell));
			      selector[1] = NULL;
			      selector_enabled[0] = aux_enabled;
			      selector_index = 0;
                            }
#line 2088 "rew_parser.tab.c"
    break;

  case 19:
#line 366 "rew_parser.y"
                            {
			      handle_concretely_indexed_aei(&(yyvsp[-1].st_bucket),
                                                            (yyvsp[0].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            }
#line 2100 "rew_parser.tab.c"
    break;

  case 20:
#line 374 "rew_parser.y"
                            {
			      handle_prefixed_indexed_id((yyvsp[-4].st_bucket),
                                                         &(yyvsp[0].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    }
#line 2113 "rew_parser.tab.c"
    break;

  case 21:
#line 383 "rew_parser.y"
                            {
			      (yyval.st_bucket3_cell) = (((yyvsp[-4].st_bucket) == NULL) ||
				    ((yyvsp[0].st_bucket) == NULL))?
				     NULL:
				     alloc_st_bucket3_cell((yyvsp[-4].st_bucket),
							   (reward_index == YIELD)?
							     (yyvsp[0].st_bucket):
							     NULL,
							   (reward_index == BONUS)?
							     (yyvsp[0].st_bucket):
							     NULL,
							   NULL);
			    }
#line 2131 "rew_parser.tab.c"
    break;

  case 22:
#line 400 "rew_parser.y"
                            {
			      handle_symbolically_indexed_aei(&(yyvsp[-1].st_bucket),
                                                              (yyvsp[0].expr_parse_info),
                                                              1,
                                                              FALSE);
                            }
#line 2142 "rew_parser.tab.c"
    break;

  case 23:
#line 407 "rew_parser.y"
                            {
			      handle_prefixed_indexed_id((yyvsp[-4].st_bucket),
                                                         &(yyvsp[0].st_bucket),
                                                         1,
                                                         TRUE,
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
			    }
#line 2155 "rew_parser.tab.c"
    break;

  case 24:
#line 416 "rew_parser.y"
                            {
			      (yyval.st_bucket3_cell) = (((yyvsp[-4].st_bucket) == NULL) ||
				    ((yyvsp[0].st_bucket) == NULL))?
				     NULL:
				     alloc_st_bucket3_cell((yyvsp[-4].st_bucket),
							   (reward_index == YIELD)?
							     (yyvsp[0].st_bucket):
							     NULL,
							   (reward_index == BONUS)?
							     (yyvsp[0].st_bucket):
							     NULL,
							   NULL);
			    }
#line 2173 "rew_parser.tab.c"
    break;

  case 25:
#line 433 "rew_parser.y"
                            {
			      if (((yyvsp[0].expr_parse_info) != NULL) &&
				  !check_expr_all((yyvsp[0].expr_parse_info)->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				(yyvsp[0].expr_parse_info) = NULL;
			    }
#line 2190 "rew_parser.tab.c"
    break;

  case 26:
#line 446 "rew_parser.y"
                            {
			      (yyval.st_bucket) = ((yyvsp[-2].expr_parse_info) != NULL)?
				     (yyvsp[-2].expr_parse_info)->expr:
				     NULL;
			      reward_index = YIELD;
			    }
#line 2201 "rew_parser.tab.c"
    break;

  case 27:
#line 453 "rew_parser.y"
                            {
			      if (((yyvsp[0].expr_parse_info) != NULL) &&
				  !check_expr_all((yyvsp[0].expr_parse_info)->expr,
						  selector[0],
						  selector[1],
						  (ST_BUCKET *)search_lexeme_table("real",
										   SYT),
						  REWARD_NOT_UNDECL_ID_FREE,
						  REWARD_NOT_RANDOMNESS_FREE,
						  ILL_TYPED_REWARD))
				(yyvsp[0].expr_parse_info) = NULL;
			    }
#line 2218 "rew_parser.tab.c"
    break;

  case 28:
#line 466 "rew_parser.y"
                            {
			      (yyval.st_bucket) = ((yyvsp[-2].expr_parse_info) != NULL)?
				     (yyvsp[-2].expr_parse_info)->expr:
				     NULL;
			      reward_index = BONUS;
			    }
#line 2229 "rew_parser.tab.c"
    break;

  case 29:
#line 476 "rew_parser.y"
                            {
			      handle_iteration_1(&(yyvsp[0].st_bucket));
			    }
#line 2237 "rew_parser.tab.c"
    break;

  case 30:
#line 480 "rew_parser.y"
                            {
			      handle_iteration_2(&(yyvsp[0].expr_parse_info));
			    }
#line 2245 "rew_parser.tab.c"
    break;

  case 31:
#line 484 "rew_parser.y"
                            {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[-6].st_bucket),
						      (yyvsp[-3].expr_parse_info),
						      (yyvsp[0].expr_parse_info));
			    }
#line 2255 "rew_parser.tab.c"
    break;

  case 32:
#line 493 "rew_parser.y"
                            {
			      selector_index = 1;
			    }
#line 2263 "rew_parser.tab.c"
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
			        (yyval.st_bucket) = (yyvsp[0].st_bucket);
			    }
#line 2279 "rew_parser.tab.c"
    break;

  case 34:
#line 512 "rew_parser.y"
                            {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    }
#line 2288 "rew_parser.tab.c"
    break;

  case 35:
#line 517 "rew_parser.y"
                            {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[0].expr_parse_info);
			    }
#line 2297 "rew_parser.tab.c"
    break;

  case 36:
#line 525 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-1].expr_parse_info) != NULL) &&
				    !check_expr_all((yyvsp[-1].expr_parse_info)->expr,
						    selector[0],
						    selector[1],
						    (ST_BUCKET *)search_lexeme_table("integer",
										     SYT),
						    AEI_INDEX_EXPR_NOT_UNDECL_ID_FREE,
						    AEI_INDEX_EXPR_NOT_RANDOMNESS_FREE,
						    ILL_TYPED_AEI_INDEX))?
				     NULL:
				     (yyvsp[-1].expr_parse_info);
			    }
#line 2315 "rew_parser.tab.c"
    break;

  case 37:
#line 542 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[0].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    }
#line 2327 "rew_parser.tab.c"
    break;

  case 38:
#line 550 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2336 "rew_parser.tab.c"
    break;

  case 39:
#line 555 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PLUS_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2354 "rew_parser.tab.c"
    break;

  case 40:
#line 569 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MINUS_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2372 "rew_parser.tab.c"
    break;

  case 41:
#line 583 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TIMES_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2390 "rew_parser.tab.c"
    break;

  case 42:
#line 597 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(DIV_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2408 "rew_parser.tab.c"
    break;

  case 43:
#line 611 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MOD_OP,
                                                                          (yyvsp[-3].expr_parse_info)->expr,
                                                                          (yyvsp[-1].expr_parse_info)->expr,
                                                                          NULL,
                                                                          0.0,
                                                                          NULL,
                                                                          FALSE),
                                                           NULL);
			    }
#line 2426 "rew_parser.tab.c"
    break;

  case 44:
#line 625 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ABS_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2443 "rew_parser.tab.c"
    break;

  case 45:
#line 638 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CEIL_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2460 "rew_parser.tab.c"
    break;

  case 46:
#line 651 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FLOOR_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2477 "rew_parser.tab.c"
    break;

  case 47:
#line 664 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MIN_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2495 "rew_parser.tab.c"
    break;

  case 48:
#line 678 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(MAX_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2513 "rew_parser.tab.c"
    break;

  case 49:
#line 692 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POWER_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2531 "rew_parser.tab.c"
    break;

  case 50:
#line 706 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EPOWER_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2548 "rew_parser.tab.c"
    break;

  case 51:
#line 719 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOGE_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2565 "rew_parser.tab.c"
    break;

  case 52:
#line 732 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LOG10_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2582 "rew_parser.tab.c"
    break;

  case 53:
#line 745 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SQRT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2599 "rew_parser.tab.c"
    break;

  case 54:
#line 758 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(SIN_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2616 "rew_parser.tab.c"
    break;

  case 55:
#line 771 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(COS_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2633 "rew_parser.tab.c"
    break;

  case 56:
#line 784 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(C_UNIFORM_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2651 "rew_parser.tab.c"
    break;

  case 57:
#line 798 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ERLANG_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2669 "rew_parser.tab.c"
    break;

  case 58:
#line 812 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GAMMA_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2687 "rew_parser.tab.c"
    break;

  case 59:
#line 826 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EXPONENTIAL_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2704 "rew_parser.tab.c"
    break;

  case 60:
#line 839 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WEIBULL_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2722 "rew_parser.tab.c"
    break;

  case 61:
#line 853 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BETA_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2740 "rew_parser.tab.c"
    break;

  case 62:
#line 867 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NORMAL_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2758 "rew_parser.tab.c"
    break;

  case 63:
#line 881 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PARETO_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2775 "rew_parser.tab.c"
    break;

  case 64:
#line 894 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-5].expr_parse_info) == NULL) ||
				    ((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(B_PARETO_OP,
                                                                           (yyvsp[-5].expr_parse_info)->expr,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2794 "rew_parser.tab.c"
    break;

  case 65:
#line 909 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(D_UNIFORM_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2812 "rew_parser.tab.c"
    break;

  case 66:
#line 923 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-5].expr_parse_info) == NULL) ||
				    ((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BERNOULLI_OP,
                                                                           (yyvsp[-5].expr_parse_info)->expr,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2831 "rew_parser.tab.c"
    break;

  case 67:
#line 938 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(BINOMIAL_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2849 "rew_parser.tab.c"
    break;

  case 68:
#line 952 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(POISSON_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2866 "rew_parser.tab.c"
    break;

  case 69:
#line 965 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NEG_BINOMIAL_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2884 "rew_parser.tab.c"
    break;

  case 70:
#line 979 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GEOMETRIC_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2901 "rew_parser.tab.c"
    break;

  case 71:
#line 992 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PASCAL_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2919 "rew_parser.tab.c"
    break;

  case 72:
#line 1006 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2928 "rew_parser.tab.c"
    break;

  case 73:
#line 1011 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2937 "rew_parser.tab.c"
    break;

  case 74:
#line 1016 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(AND_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2955 "rew_parser.tab.c"
    break;

  case 75:
#line 1030 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(OR_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2973 "rew_parser.tab.c"
    break;

  case 76:
#line 1044 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[0].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NOT_OP,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2990 "rew_parser.tab.c"
    break;

  case 77:
#line 1057 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(EQ_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3008 "rew_parser.tab.c"
    break;

  case 78:
#line 1071 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(NE_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3026 "rew_parser.tab.c"
    break;

  case 79:
#line 1085 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LT_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3044 "rew_parser.tab.c"
    break;

  case 80:
#line 1099 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LE_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3062 "rew_parser.tab.c"
    break;

  case 81:
#line 1113 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GT_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3080 "rew_parser.tab.c"
    break;

  case 82:
#line 1127 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GE_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3098 "rew_parser.tab.c"
    break;

  case 83:
#line 1141 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3106 "rew_parser.tab.c"
    break;

  case 84:
#line 1145 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(FIRST_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3123 "rew_parser.tab.c"
    break;

  case 85:
#line 1158 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(TAIL_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3140 "rew_parser.tab.c"
    break;

  case 86:
#line 1171 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(CONCAT_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3158 "rew_parser.tab.c"
    break;

  case 87:
#line 1185 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(INSERT_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3176 "rew_parser.tab.c"
    break;

  case 88:
#line 1199 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(REMOVE_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3194 "rew_parser.tab.c"
    break;

  case 89:
#line 1213 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LENGTH_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3211 "rew_parser.tab.c"
    break;

  case 90:
#line 1226 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3219 "rew_parser.tab.c"
    break;

  case 91:
#line 1230 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(READ_OP,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3237 "rew_parser.tab.c"
    break;

  case 92:
#line 1244 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-5].expr_parse_info) == NULL) ||
				    ((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(WRITE_OP,
                                                                           (yyvsp[-5].expr_parse_info)->expr,
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3256 "rew_parser.tab.c"
    break;

  case 93:
#line 1259 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3264 "rew_parser.tab.c"
    break;

  case 94:
#line 1263 "rew_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3274 "rew_parser.tab.c"
    break;

  case 95:
#line 1269 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-4].st_bucket) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(GET_OP,
                                                                           (yyvsp[-4].st_bucket),
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3292 "rew_parser.tab.c"
    break;

  case 96:
#line 1283 "rew_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3302 "rew_parser.tab.c"
    break;

  case 97:
#line 1289 "rew_parser.y"
                            {
        		      (yyval.expr_parse_info) = (((yyvsp[-6].st_bucket) == NULL) ||
				    ((yyvsp[-3].expr_parse_info) == NULL) ||
				    ((yyvsp[-1].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PUT_OP,
                                                                           (yyvsp[-6].st_bucket),
                                                                           (yyvsp[-3].expr_parse_info)->expr,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 3321 "rew_parser.tab.c"
    break;

  case 98:
#line 1304 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3329 "rew_parser.tab.c"
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
			    }
#line 3347 "rew_parser.tab.c"
    break;

  case 100:
#line 1325 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[0].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LIST_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[0].lar_parse_info)->struct_value,
									   FALSE),
                                                           NULL);
			    }
#line 3364 "rew_parser.tab.c"
    break;

  case 101:
#line 1341 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[0].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(ARRAY_CONS_OP,
								           NULL,
								           NULL,
								           NULL,
								           (yyvsp[0].lar_parse_info)->value_num,
								           transform_list_into_array(
									     (yyvsp[0].lar_parse_info)->struct_value,
									     (yyvsp[0].lar_parse_info)->value_num),
								           FALSE),
                                                           NULL);
			    }
#line 3383 "rew_parser.tab.c"
    break;

  case 102:
#line 1359 "rew_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[0].lar_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(RECORD_CONS_OP,
						     			   NULL,
									   NULL,
									   NULL,
									   0.0L,
						     			   (yyvsp[0].lar_parse_info)->struct_value,
									   FALSE),
                                                           NULL);
			    }
#line 3400 "rew_parser.tab.c"
    break;

  case 103:
#line 1375 "rew_parser.y"
                            {
			      (yyval.lar_parse_info) = ((yyvsp[0].expr_parse_info) == NULL)?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[0].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   NULL),
						          1,
                                                          NULL);
			    }
#line 3415 "rew_parser.tab.c"
    break;

  case 104:
#line 1386 "rew_parser.y"
                            {
			      (yyval.lar_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].lar_parse_info) == NULL))?
				     NULL:
				     alloc_lar_parse_info(alloc_value_cell((yyvsp[-2].expr_parse_info)->expr,
						      			   0.0L,
						      			   NULL,
						      			   (yyvsp[0].lar_parse_info)->struct_value),
						          (yyvsp[0].lar_parse_info)->value_num + 1,
                                                          NULL);
			    }
#line 3431 "rew_parser.tab.c"
    break;


#line 3435 "rew_parser.tab.c"

      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */
  {
    const int yylhs = yyr1[yyn] - YYNTOKENS;
    const int yyi = yypgoto[yylhs] + *yyssp;
    yystate = (0 <= yyi && yyi <= YYLAST && yycheck[yyi] == *yyssp
               ? yytable[yyi]
               : yydefgoto[yylhs]);
  }

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = YY_CAST (char *, YYSTACK_ALLOC (YY_CAST (YYSIZE_T, yymsg_alloc)));
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
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

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:
  /* Pacify compilers when the user code never invokes YYERROR and the
     label yyerrorlab therefore never appears in user code.  */
  if (0)
    YYERROR;

  /* Do not reclaim the symbols of the rule whose action triggered
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
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
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

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


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


#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif


/*-----------------------------------------------------.
| yyreturn -- parsing is finished, return the result.  |
`-----------------------------------------------------*/
yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[+*yyssp], yyvsp);
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
  return yyresult;
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
