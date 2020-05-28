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
#define yyparse         ltlyyparse
#define yylex           ltlyylex
#define yyerror         ltlyyerror
#define yydebug         ltlyydebug
#define yynerrs         ltlyynerrs
#define yylval          ltlyylval
#define yychar          ltlyychar

/* First part of user prologue.  */
#line 23 "ltl_parser.y"


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


#line 134 "ltl_parser.tab.c"

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
#ifndef YY_LTLYY_LTL_PARSER_TAB_H_INCLUDED
# define YY_LTLYY_LTL_PARSER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int ltlyydebug;
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
    T_PROPERTY = 494,
    T_IS = 495,
    T_PROP_TRUE = 496,
    T_PROP_FALSE = 497,
    T_PROP_NOT = 498,
    T_MODAL_DEADLOCK_FREE = 515,
    T_LTL_NEXT_STATE_SAT = 524,
    T_LTL_ALL_FUTURE_STATES_SAT = 525,
    T_LTL_SOME_FUTURE_STATE_SAT = 526,
    T_LTL_UNTIL = 527,
    T_LTL_RELEASES = 528,
    T_LTL_PREV_STATE_SAT = 529,
    T_LTL_ALL_PAST_STATES_SAT = 530,
    T_LTL_SOME_PAST_STATE_SAT = 531,
    T_LTL_SINCE = 532,
    T_LTL_TRIGGERED = 533,
    DOTDOT = 700,
    NE = 701,
    LE = 702,
    GE = 703,
    AND = 704,
    OR = 705,
    PROP_AND = 706,
    PROP_OR = 707,
    PROP_XOR = 708,
    PROP_IMPL = 709,
    PROP_BI_IMPL = 710
  };
#endif

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 86 "ltl_parser.y"

	ST_BUCKET_CELL	*st_bucket_cell;
				/* list of symbol table bucket cells for properties */
        ST_BUCKET       *st_bucket;
                                /* pointer to the symbol table bucket for an identifier or a number */
	EXPR_PARSE_INFO	*expr_parse_info;
				/* parse information about an expression */
	LAR_PARSE_INFO	*lar_parse_info;
				/* parse information about an expression list/array/record */

#line 275 "ltl_parser.tab.c"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE ltlyylval;

int ltlyyparse (void);

#endif /* !YY_LTLYY_LTL_PARSER_TAB_H_INCLUDED  */



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
#define YYLAST   1764

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  93
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  24
/* YYNRULES -- Number of rules.  */
#define YYNRULES  110
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  361

#define YYUNDEFTOK  2
#define YYMAXUTOK   711


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
       2,     2,     2,    78,     2,     2,     2,     2,     2,     2,
      87,    88,    84,    82,    92,    83,    89,    85,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    86,
      80,    79,    81,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,    90,     2,    91,     2,     2,     2,     2,     2,     2,
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
       2,    48,    49,    50,    51,    52,    53,    54,    55,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,    56,     2,     2,     2,     2,
       2,     2,     2,     2,    57,    58,    59,    60,    61,    62,
      63,    64,    65,    66,     2,     2,     2,     2,     2,     2,
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
      67,    68,    69,    70,    71,    72,    73,    74,    75,    76,
      77,     2
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_int16 yyrline[] =
{
       0,   239,   239,   243,   249,   248,   257,   256,   267,   274,
     287,   286,   303,   302,   320,   325,   330,   344,   358,   371,
     385,   399,   413,   434,   449,   462,   475,   488,   502,   516,
     529,   542,   555,   569,   583,   591,   590,   623,   627,   622,
     640,   644,   652,   669,   677,   682,   696,   710,   724,   738,
     752,   765,   778,   791,   805,   819,   833,   846,   859,   872,
     885,   898,   911,   925,   939,   953,   966,   980,   994,  1008,
    1021,  1036,  1050,  1065,  1079,  1092,  1106,  1119,  1133,  1138,
    1143,  1157,  1171,  1184,  1198,  1212,  1226,  1240,  1254,  1268,
    1272,  1285,  1298,  1312,  1326,  1340,  1353,  1357,  1371,  1386,
    1391,  1390,  1411,  1410,  1431,  1439,  1452,  1468,  1486,  1502,
    1513
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
  "T_READ", "T_WRITE", "T_RECORD_CONS", "T_GET", "T_PUT", "T_PROPERTY",
  "T_IS", "T_PROP_TRUE", "T_PROP_FALSE", "T_PROP_NOT",
  "T_MODAL_DEADLOCK_FREE", "T_LTL_NEXT_STATE_SAT",
  "T_LTL_ALL_FUTURE_STATES_SAT", "T_LTL_SOME_FUTURE_STATE_SAT",
  "T_LTL_UNTIL", "T_LTL_RELEASES", "T_LTL_PREV_STATE_SAT",
  "T_LTL_ALL_PAST_STATES_SAT", "T_LTL_SOME_PAST_STATE_SAT", "T_LTL_SINCE",
  "T_LTL_TRIGGERED", "DOTDOT", "NE", "LE", "GE", "AND", "OR", "PROP_AND",
  "PROP_OR", "PROP_XOR", "PROP_IMPL", "PROP_BI_IMPL", "'!'", "'='", "'<'",
  "'>'", "'+'", "'-'", "'*'", "'/'", "';'", "'('", "')'", "'.'", "'['",
  "']'", "','", "$accept", "prop_def_list", "$@1", "$@2", "property_def",
  "s_property_def", "$@3", "i_property_def", "$@4", "property_expr",
  "act_type", "$@5", "iteration", "$@6", "$@7", "poss_aei_index",
  "aei_index", "expr", "$@8", "$@9", "expr_list", "expr_array",
  "expr_record", "struct_expr", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_int16 yytoknum[] =
{
       0,   256,   711,   300,   301,   429,   430,   446,   447,   448,
     449,   450,   451,   452,   453,   454,   455,   456,   457,   458,
     459,   460,   461,   462,   463,   464,   465,   466,   467,   468,
     469,   470,   471,   472,   473,   474,   476,   477,   479,   480,
     481,   482,   483,   484,   485,   487,   488,   489,   491,   492,
     493,   494,   495,   496,   497,   498,   515,   524,   525,   526,
     527,   528,   529,   530,   531,   532,   533,   700,   701,   702,
     703,   704,   705,   706,   707,   708,   709,   710,    33,    61,
      60,    62,    43,    45,    42,    47,    59,    40,    41,    46,
      91,    93,    44
};
# endif

#define YYPACT_NINF (-115)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-1)

#define yytable_value_is_error(Yyn) \
  ((Yyn) == YYTABLE_NINF)

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
       1,   -48,     8,    15,     5,   -66,  -115,   -21,  -115,  -115,
    -115,   -55,  -115,     1,    36,  -115,     1,     1,    31,   124,
    -115,  -115,  -115,   -55,  -115,  -115,   124,  -115,  -115,   -40,
     -39,   -36,   -30,   -29,   -28,     2,     9,    12,    13,    14,
      16,    29,    30,    32,    34,    35,    37,    38,    39,    43,
      88,    91,    92,    93,    96,    97,    98,   100,  -115,  -115,
     101,   111,   112,   118,   119,   120,   128,   133,   135,   137,
     138,   139,   140,   124,   124,   762,    50,  -115,  1620,   124,
     124,   124,   124,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,   124,   124,   124,   124,    49,
     114,  1627,   780,   124,   124,   124,   124,   124,   124,   124,
     124,   124,   124,   124,   124,  -115,   182,    68,    56,   294,
     312,   330,   801,   822,   843,   348,   864,   885,   906,   927,
     948,   969,   366,   384,   402,   990,   420,   438,   456,  1011,
     474,   492,   510,   528,  1032,   546,  1053,   564,   582,   143,
    -115,  1074,  1095,   600,   618,   636,  1116,   144,  -115,   654,
     672,   154,  -115,  -115,  -115,  -115,  1645,  1645,  1645,  1627,
    1627,  1645,  1645,  1645,   -67,   -67,  -115,  -115,   -55,  -115,
    -115,   156,  -115,   160,   161,   169,   179,   180,   181,   182,
    1671,  -115,   182,   124,   124,   124,   124,  -115,  -115,  -115,
     124,  -115,  -115,  -115,  -115,  -115,  -115,   124,   124,   124,
    -115,   124,   124,   124,  -115,   124,   124,   124,   124,  -115,
     124,  -115,   124,   124,  -115,  -115,  -115,   124,   124,   124,
    -115,  -115,   124,   124,  -115,   185,   186,  -115,   182,   182,
     182,   182,   182,   182,   182,   -52,   182,   182,   182,   182,
     182,   182,   182,   182,   182,  1671,  1620,  1137,  1158,  1179,
    1200,  1221,  1242,  1263,  1284,  1305,  1326,   690,  1347,   708,
    1368,  1389,  1410,  -115,  1431,  1452,  1473,  1494,   726,   124,
     124,   183,   -32,   116,   210,   228,   248,   266,   284,  -115,
    -115,  -115,  -115,  -115,   -50,   -34,   -34,  1671,  1689,  -115,
    -115,  -115,  -115,  -115,  -115,  -115,  -115,  -115,  -115,   124,
    -115,   124,  -115,  -115,  -115,  -115,  -115,  -115,  -115,   124,
    1515,   744,   269,  -115,  -115,  -115,  -115,  -115,  -115,  -115,
    1536,  1557,  1578,  -115,   124,  -115,  -115,  -115,  -115,  1599,
    -115
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_int8 yydefact[] =
{
       0,     0,     0,     0,     0,     2,     8,     0,     6,     4,
      37,    40,     1,     0,     0,     9,     0,     0,     0,     0,
      10,    41,     3,     0,     7,     5,     0,    44,    43,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,    78,    79,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,    12,    38,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   105,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,    82,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    42,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,   109,     0,
     106,     0,     0,     0,     0,     0,     0,     0,   107,     0,
       0,     0,   108,   100,   102,   104,    84,    86,    88,    80,
      81,    83,    85,    87,    45,    46,    47,    48,    40,    14,
      15,     0,    22,     0,     0,     0,     0,     0,     0,     0,
      11,    23,     0,     0,     0,     0,     0,    50,    51,    52,
       0,    56,    57,    58,    59,    60,    61,     0,     0,     0,
      65,     0,     0,     0,    69,     0,     0,     0,     0,    74,
       0,    76,     0,     0,    89,    90,    91,     0,     0,     0,
      95,    96,     0,     0,    99,     0,     0,    35,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    13,    39,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,   110,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    34,
      27,    28,    32,    33,    16,    17,    19,    20,    21,    49,
      53,    54,    55,    62,    63,    64,    66,    67,    68,     0,
      71,     0,    73,    75,    77,    92,    93,    94,    97,     0,
       0,     0,     0,    18,    24,    25,    26,    29,    30,    31,
       0,     0,     0,   101,     0,    36,    70,    72,    98,     0,
     103
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -115,    33,  -115,  -115,  -115,  -115,  -115,  -115,  -115,    -9,
    -115,  -115,  -115,  -115,  -115,    76,   256,   -19,  -115,  -115,
    -115,  -115,  -115,  -114
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     4,    17,    16,     5,     6,    76,    15,   137,   210,
     211,   301,     7,    18,   138,    20,    21,   168,   255,   256,
     169,   177,   181,   170
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_int16 yytable[] =
{
      75,   178,     1,     8,   182,    12,     2,    78,   266,   267,
     266,   267,    10,   268,   269,   268,   269,   133,   134,    11,
      13,   270,   271,   272,   273,   274,   266,   267,   266,   267,
      14,   268,   269,   268,   269,    19,   309,    26,     9,   270,
      23,   270,   271,   272,   273,   274,    22,    79,    80,    24,
      25,    81,     3,   183,   121,   122,   343,    82,    83,    84,
     139,   140,   141,   142,   143,   144,   145,   146,   147,   148,
     149,   150,   151,   152,   153,   154,   155,   156,   157,   158,
     159,   160,   161,   162,   163,   164,   165,   166,   167,    85,
     171,   172,   173,   174,   175,   176,    86,   179,   180,    87,
      88,    89,   136,    90,   186,   187,   188,   189,   190,   191,
     192,   193,   194,   195,   196,   197,    91,    92,   184,    93,
     212,    94,    95,   213,    96,    97,    98,    27,    28,   293,
      99,    29,    30,    31,    32,    33,    34,    35,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,    51,    52,    53,    54,    55,    56,    57,
      58,    59,    60,    61,    62,    63,    64,    65,    66,    67,
      68,    69,    70,    71,    72,   100,   266,   267,   101,   102,
     103,   268,   269,   104,   105,   106,   198,   107,   108,   270,
     271,   272,   273,   274,   276,   277,   278,   279,   109,   110,
     265,   280,    73,   275,   344,   111,   112,   113,   281,   282,
     283,    74,   284,   285,   286,   114,   287,   288,   289,   290,
     115,   291,   116,   292,   117,   118,   119,   120,   294,   295,
     296,   244,   251,   297,   298,   199,   200,   201,   202,   203,
     204,   205,   254,   258,   206,   207,   208,   259,   260,   302,
     303,   304,   305,   306,   307,   308,   261,   310,   311,   312,
     313,   314,   315,   316,   317,   318,   262,   263,   264,   209,
     266,   267,   342,   355,   257,   268,   269,   299,   300,    77,
     340,   341,     0,   270,   271,   272,   273,   274,   266,   267,
       0,     0,     0,   268,   269,     0,     0,     0,   345,     0,
       0,   270,   271,   272,   273,   274,     0,     0,   266,   267,
     350,     0,   351,   268,   269,     0,   346,     0,     0,     0,
     352,   270,   271,   272,   273,   274,   266,   267,     0,     0,
       0,   268,   269,     0,     0,   359,   347,     0,     0,   270,
     271,   272,   273,   274,   266,   267,     0,     0,     0,   268,
     269,     0,     0,     0,   348,     0,     0,   270,   271,   272,
     273,   274,   123,   124,   125,   126,   127,     0,     0,     0,
       0,     0,   349,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   214,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   215,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   216,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     220,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   227,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   228,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   229,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   231,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     232,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   233,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   235,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   236,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   237,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     238,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   240,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   242,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   243,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   247,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     248,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   249,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   252,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,     0,   253,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,   123,   124,   125,   126,
     127,     0,   329,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,   123,   124,   125,   126,   127,     0,
     331,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,   123,   124,   125,   126,   127,     0,   339,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
     123,   124,   125,   126,   127,     0,   354,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,   123,   124,
     125,   126,   127,   135,     0,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,   185,   123,
     124,   125,   126,   127,     0,     0,     0,     0,     0,     0,
     128,   129,   130,   131,   132,   133,   134,     0,     0,   217,
     123,   124,   125,   126,   127,     0,     0,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
     218,   123,   124,   125,   126,   127,     0,     0,     0,     0,
       0,     0,   128,   129,   130,   131,   132,   133,   134,     0,
       0,   219,   123,   124,   125,   126,   127,     0,     0,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,   221,   123,   124,   125,   126,   127,     0,     0,
       0,     0,     0,     0,   128,   129,   130,   131,   132,   133,
     134,     0,     0,   222,   123,   124,   125,   126,   127,     0,
       0,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,   223,   123,   124,   125,   126,   127,
       0,     0,     0,     0,     0,     0,   128,   129,   130,   131,
     132,   133,   134,     0,     0,   224,   123,   124,   125,   126,
     127,     0,     0,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,   225,   123,   124,   125,
     126,   127,     0,     0,     0,     0,     0,     0,   128,   129,
     130,   131,   132,   133,   134,     0,     0,   226,   123,   124,
     125,   126,   127,     0,     0,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,   230,   123,
     124,   125,   126,   127,     0,     0,     0,     0,     0,     0,
     128,   129,   130,   131,   132,   133,   134,     0,     0,   234,
     123,   124,   125,   126,   127,     0,     0,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
     239,   123,   124,   125,   126,   127,     0,     0,     0,     0,
       0,     0,   128,   129,   130,   131,   132,   133,   134,     0,
       0,   241,   123,   124,   125,   126,   127,     0,     0,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,   245,   123,   124,   125,   126,   127,     0,     0,
       0,     0,     0,     0,   128,   129,   130,   131,   132,   133,
     134,     0,     0,   246,   123,   124,   125,   126,   127,     0,
       0,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,   250,   123,   124,   125,   126,   127,
       0,     0,     0,     0,     0,     0,   128,   129,   130,   131,
     132,   133,   134,     0,     0,   319,   123,   124,   125,   126,
     127,     0,     0,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,   320,   123,   124,   125,
     126,   127,     0,     0,     0,     0,     0,     0,   128,   129,
     130,   131,   132,   133,   134,     0,     0,   321,   123,   124,
     125,   126,   127,     0,     0,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,   322,   123,
     124,   125,   126,   127,     0,     0,     0,     0,     0,     0,
     128,   129,   130,   131,   132,   133,   134,     0,     0,   323,
     123,   124,   125,   126,   127,     0,     0,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
     324,   123,   124,   125,   126,   127,     0,     0,     0,     0,
       0,     0,   128,   129,   130,   131,   132,   133,   134,     0,
       0,   325,   123,   124,   125,   126,   127,     0,     0,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,   326,   123,   124,   125,   126,   127,     0,     0,
       0,     0,     0,     0,   128,   129,   130,   131,   132,   133,
     134,     0,     0,   327,   123,   124,   125,   126,   127,     0,
       0,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,   328,   123,   124,   125,   126,   127,
       0,     0,     0,     0,     0,     0,   128,   129,   130,   131,
     132,   133,   134,     0,     0,   330,   123,   124,   125,   126,
     127,     0,     0,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,   332,   123,   124,   125,
     126,   127,     0,     0,     0,     0,     0,     0,   128,   129,
     130,   131,   132,   133,   134,     0,     0,   333,   123,   124,
     125,   126,   127,     0,     0,     0,     0,     0,     0,   128,
     129,   130,   131,   132,   133,   134,     0,     0,   334,   123,
     124,   125,   126,   127,     0,     0,     0,     0,     0,     0,
     128,   129,   130,   131,   132,   133,   134,     0,     0,   335,
     123,   124,   125,   126,   127,     0,     0,     0,     0,     0,
       0,   128,   129,   130,   131,   132,   133,   134,     0,     0,
     336,   123,   124,   125,   126,   127,     0,     0,     0,     0,
       0,     0,   128,   129,   130,   131,   132,   133,   134,     0,
       0,   337,   123,   124,   125,   126,   127,     0,     0,     0,
       0,     0,     0,   128,   129,   130,   131,   132,   133,   134,
       0,     0,   338,   123,   124,   125,   126,   127,     0,     0,
       0,     0,     0,     0,   128,   129,   130,   131,   132,   133,
     134,     0,     0,   353,   123,   124,   125,   126,   127,     0,
       0,     0,     0,     0,     0,   128,   129,   130,   131,   132,
     133,   134,     0,     0,   356,   123,   124,   125,   126,   127,
       0,     0,     0,     0,     0,     0,   128,   129,   130,   131,
     132,   133,   134,     0,     0,   357,   123,   124,   125,   126,
     127,     0,     0,     0,     0,     0,     0,   128,   129,   130,
     131,   132,   133,   134,     0,     0,   358,   123,   124,   125,
     126,   127,     0,     0,     0,     0,     0,     0,   128,   129,
     130,   131,   132,   133,   134,     0,     0,   360,   123,   124,
     125,   126,   127,     0,     0,   123,   124,   125,     0,   128,
     129,   130,   131,   132,   133,   134,   128,   129,   130,   131,
     132,   133,   134,    -1,    -1,    -1,     0,     0,     0,     0,
       0,     0,     0,     0,    -1,    -1,    -1,   131,   132,   133,
     134,   266,   267,     0,     0,     0,   268,   269,     0,     0,
       0,     0,     0,     0,   270,   271,   272,   273,   274,   266,
     267,     0,     0,     0,   268,   269,     0,     0,     0,     0,
       0,     0,   270,   271,   272
};

static const yytype_int16 yycheck[] =
{
      19,   115,     1,    51,   118,     0,     5,    26,    60,    61,
      60,    61,     4,    65,    66,    65,    66,    84,    85,     4,
      86,    73,    74,    75,    76,    77,    60,    61,    60,    61,
      51,    65,    66,    65,    66,    90,    88,     6,    86,    73,
       4,    73,    74,    75,    76,    77,    13,    87,    87,    16,
      17,    87,    51,     4,    73,    74,    88,    87,    87,    87,
      79,    80,    81,    82,    83,    84,    85,    86,    87,    88,
      89,    90,    91,    92,    93,    94,    95,    96,    97,    98,
      99,   100,   101,   102,   103,   104,   105,   106,   107,    87,
     109,   110,   111,   112,   113,   114,    87,   116,   117,    87,
      87,    87,    52,    87,   123,   124,   125,   126,   127,   128,
     129,   130,   131,   132,   133,   134,    87,    87,     4,    87,
      52,    87,    87,    67,    87,    87,    87,     3,     4,   243,
      87,     7,     8,     9,    10,    11,    12,    13,    14,    15,
      16,    17,    18,    19,    20,    21,    22,    23,    24,    25,
      26,    27,    28,    29,    30,    31,    32,    33,    34,    35,
      36,    37,    38,    39,    40,    41,    42,    43,    44,    45,
      46,    47,    48,    49,    50,    87,    60,    61,    87,    87,
      87,    65,    66,    87,    87,    87,     4,    87,    87,    73,
      74,    75,    76,    77,   213,   214,   215,   216,    87,    87,
     209,   220,    78,   212,    88,    87,    87,    87,   227,   228,
     229,    87,   231,   232,   233,    87,   235,   236,   237,   238,
      87,   240,    87,   242,    87,    87,    87,    87,   247,   248,
     249,    88,    88,   252,   253,    53,    54,    55,    56,    57,
      58,    59,    88,    87,    62,    63,    64,    87,    87,   258,
     259,   260,   261,   262,   263,   264,    87,   266,   267,   268,
     269,   270,   271,   272,   273,   274,    87,    87,    87,    87,
      60,    61,    89,     4,   198,    65,    66,    92,    92,    23,
     299,   300,    -1,    73,    74,    75,    76,    77,    60,    61,
      -1,    -1,    -1,    65,    66,    -1,    -1,    -1,    88,    -1,
      -1,    73,    74,    75,    76,    77,    -1,    -1,    60,    61,
     329,    -1,   331,    65,    66,    -1,    88,    -1,    -1,    -1,
     339,    73,    74,    75,    76,    77,    60,    61,    -1,    -1,
      -1,    65,    66,    -1,    -1,   354,    88,    -1,    -1,    73,
      74,    75,    76,    77,    60,    61,    -1,    -1,    -1,    65,
      66,    -1,    -1,    -1,    88,    -1,    -1,    73,    74,    75,
      76,    77,    68,    69,    70,    71,    72,    -1,    -1,    -1,
      -1,    -1,    88,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    -1,    92,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    68,    69,    70,    71,
      72,    -1,    92,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    68,    69,    70,    71,    72,    -1,
      92,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    68,    69,    70,    71,    72,    -1,    92,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    -1,    92,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    68,    69,    70,    71,
      72,    -1,    92,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    68,    69,    70,    71,    72,    -1,
      92,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    68,    69,    70,    71,    72,    -1,    92,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    -1,    92,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    68,    69,    70,    71,
      72,    -1,    92,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    68,    69,    70,    71,    72,    -1,
      92,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    68,    69,    70,    71,    72,    -1,    92,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    -1,    92,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    68,    69,    70,    71,
      72,    -1,    92,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    68,    69,    70,    71,    72,    -1,
      92,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    68,    69,    70,    71,    72,    -1,    92,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    -1,    92,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    68,    69,    70,    71,
      72,    -1,    92,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    68,    69,    70,    71,    72,    -1,
      92,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    68,    69,    70,    71,    72,    -1,    92,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      68,    69,    70,    71,    72,    -1,    92,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    68,    69,
      70,    71,    72,    91,    -1,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    -1,    -1,    88,    68,
      69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,
      79,    80,    81,    82,    83,    84,    85,    -1,    -1,    88,
      68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    -1,    -1,
      88,    68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,
      -1,    -1,    79,    80,    81,    82,    83,    84,    85,    -1,
      -1,    88,    68,    69,    70,    71,    72,    -1,    -1,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      -1,    -1,    88,    68,    69,    70,    71,    72,    -1,    -1,
      -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,    84,
      85,    -1,    -1,    88,    68,    69,    70,    71,    72,    -1,
      -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    -1,    -1,    88,    68,    69,    70,    71,    72,
      -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,
      83,    84,    85,    -1,    -1,    88,    68,    69,    70,    71,
      72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    -1,    -1,    88,    68,    69,    70,
      71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,
      81,    82,    83,    84,    85,    -1,    -1,    88,    68,    69,
      70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    -1,    -1,    88,    68,
      69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,
      79,    80,    81,    82,    83,    84,    85,    -1,    -1,    88,
      68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    -1,    -1,
      88,    68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,
      -1,    -1,    79,    80,    81,    82,    83,    84,    85,    -1,
      -1,    88,    68,    69,    70,    71,    72,    -1,    -1,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      -1,    -1,    88,    68,    69,    70,    71,    72,    -1,    -1,
      -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,    84,
      85,    -1,    -1,    88,    68,    69,    70,    71,    72,    -1,
      -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    -1,    -1,    88,    68,    69,    70,    71,    72,
      -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,
      83,    84,    85,    -1,    -1,    88,    68,    69,    70,    71,
      72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    -1,    -1,    88,    68,    69,    70,
      71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,
      81,    82,    83,    84,    85,    -1,    -1,    88,    68,    69,
      70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    -1,    -1,    88,    68,
      69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,
      79,    80,    81,    82,    83,    84,    85,    -1,    -1,    88,
      68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    -1,    -1,
      88,    68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,
      -1,    -1,    79,    80,    81,    82,    83,    84,    85,    -1,
      -1,    88,    68,    69,    70,    71,    72,    -1,    -1,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      -1,    -1,    88,    68,    69,    70,    71,    72,    -1,    -1,
      -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,    84,
      85,    -1,    -1,    88,    68,    69,    70,    71,    72,    -1,
      -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    -1,    -1,    88,    68,    69,    70,    71,    72,
      -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,
      83,    84,    85,    -1,    -1,    88,    68,    69,    70,    71,
      72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    -1,    -1,    88,    68,    69,    70,
      71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,
      81,    82,    83,    84,    85,    -1,    -1,    88,    68,    69,
      70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,
      80,    81,    82,    83,    84,    85,    -1,    -1,    88,    68,
      69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,    -1,
      79,    80,    81,    82,    83,    84,    85,    -1,    -1,    88,
      68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,    -1,
      -1,    79,    80,    81,    82,    83,    84,    85,    -1,    -1,
      88,    68,    69,    70,    71,    72,    -1,    -1,    -1,    -1,
      -1,    -1,    79,    80,    81,    82,    83,    84,    85,    -1,
      -1,    88,    68,    69,    70,    71,    72,    -1,    -1,    -1,
      -1,    -1,    -1,    79,    80,    81,    82,    83,    84,    85,
      -1,    -1,    88,    68,    69,    70,    71,    72,    -1,    -1,
      -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,    84,
      85,    -1,    -1,    88,    68,    69,    70,    71,    72,    -1,
      -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,
      84,    85,    -1,    -1,    88,    68,    69,    70,    71,    72,
      -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,    82,
      83,    84,    85,    -1,    -1,    88,    68,    69,    70,    71,
      72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,    81,
      82,    83,    84,    85,    -1,    -1,    88,    68,    69,    70,
      71,    72,    -1,    -1,    -1,    -1,    -1,    -1,    79,    80,
      81,    82,    83,    84,    85,    -1,    -1,    88,    68,    69,
      70,    71,    72,    -1,    -1,    68,    69,    70,    -1,    79,
      80,    81,    82,    83,    84,    85,    79,    80,    81,    82,
      83,    84,    85,    68,    69,    70,    -1,    -1,    -1,    -1,
      -1,    -1,    -1,    -1,    79,    80,    81,    82,    83,    84,
      85,    60,    61,    -1,    -1,    -1,    65,    66,    -1,    -1,
      -1,    -1,    -1,    -1,    73,    74,    75,    76,    77,    60,
      61,    -1,    -1,    -1,    65,    66,    -1,    -1,    -1,    -1,
      -1,    -1,    73,    74,    75
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_int8 yystos[] =
{
       0,     1,     5,    51,    94,    97,    98,   105,    51,    86,
       4,     4,     0,    86,    51,   100,    96,    95,   106,    90,
     108,   109,    94,     4,    94,    94,     6,     3,     4,     7,
       8,     9,    10,    11,    12,    13,    14,    15,    16,    17,
      18,    19,    20,    21,    22,    23,    24,    25,    26,    27,
      28,    29,    30,    31,    32,    33,    34,    35,    36,    37,
      38,    39,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    49,    50,    78,    87,   110,    99,   109,   110,    87,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      87,    87,    87,    87,    87,    87,    87,    87,    87,    87,
      87,   110,   110,    68,    69,    70,    71,    72,    79,    80,
      81,    82,    83,    84,    85,    91,    52,   101,   107,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   113,
     116,   110,   110,   110,   110,   110,   110,   114,   116,   110,
     110,   115,   116,     4,     4,    88,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,     4,    53,
      54,    55,    56,    57,    58,    59,    62,    63,    64,    87,
     102,   103,    52,    67,    92,    92,    92,    88,    88,    88,
      92,    88,    88,    88,    88,    88,    88,    92,    92,    92,
      88,    92,    92,    92,    88,    92,    92,    92,    92,    88,
      92,    88,    92,    92,    88,    88,    88,    92,    92,    92,
      88,    88,    92,    92,    88,   111,   112,   108,    87,    87,
      87,    87,    87,    87,    87,   102,    60,    61,    65,    66,
      73,    74,    75,    76,    77,   102,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   116,   110,   110,   110,   110,   110,    92,
      92,   104,   102,   102,   102,   102,   102,   102,   102,    88,
     102,   102,   102,   102,   102,   102,   102,   102,   102,    88,
      88,    88,    88,    88,    88,    88,    88,    88,    88,    92,
      88,    92,    88,    88,    88,    88,    88,    88,    88,    92,
     110,   110,    89,    88,    88,    88,    88,    88,    88,    88,
     110,   110,   110,    88,    92,     4,    88,    88,    88,   110,
      88
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_int8 yyr1[] =
{
       0,    93,    94,    94,    95,    94,    96,    94,    97,    97,
      99,    98,   101,   100,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   102,   102,   102,   102,   102,
     102,   102,   102,   102,   102,   104,   103,   106,   107,   105,
     108,   108,   109,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     110,   110,   110,   110,   110,   110,   110,   110,   110,   110,
     111,   110,   112,   110,   110,   113,   113,   114,   115,   116,
     116
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_int8 yyr2[] =
{
       0,     2,     1,     3,     0,     4,     0,     4,     1,     2,
       0,     6,     0,     6,     1,     1,     3,     3,     4,     3,
       3,     3,     1,     1,     4,     4,     4,     3,     3,     4,
       4,     4,     3,     3,     3,     0,     5,     0,     0,     8,
       0,     1,     3,     1,     1,     3,     3,     3,     3,     6,
       4,     4,     4,     6,     6,     6,     4,     4,     4,     4,
       4,     4,     6,     6,     6,     4,     6,     6,     6,     4,
       8,     6,     8,     6,     4,     6,     4,     6,     1,     1,
       3,     3,     2,     3,     3,     3,     3,     3,     3,     4,
       4,     4,     6,     6,     6,     4,     4,     6,     8,     4,
       0,     7,     0,     9,     3,     0,     1,     1,     1,     1,
       3
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
#line 240 "ltl_parser.y"
                            {
			      property_list = (yyvsp[0].st_bucket_cell);
			    }
#line 2011 "ltl_parser.tab.c"
    break;

  case 3:
#line 244 "ltl_parser.y"
                            {
			      property_list = append_list((yyvsp[-2].st_bucket_cell),
							  property_list);
			    }
#line 2020 "ltl_parser.tab.c"
    break;

  case 4:
#line 249 "ltl_parser.y"
                            {
                              handle_sync_ltl();
                              yyerrok;
                            }
#line 2029 "ltl_parser.tab.c"
    break;

  case 5:
#line 254 "ltl_parser.y"
                            {
                            }
#line 2036 "ltl_parser.tab.c"
    break;

  case 6:
#line 257 "ltl_parser.y"
                            {
			      handle_sync_ltl();
			      yyerrok;
			    }
#line 2045 "ltl_parser.tab.c"
    break;

  case 7:
#line 262 "ltl_parser.y"
                            {
			    }
#line 2052 "ltl_parser.tab.c"
    break;

  case 8:
#line 268 "ltl_parser.y"
                            {
			      (yyval.st_bucket_cell) = ((yyvsp[0].st_bucket) == NULL)?
                                     NULL:
                                     alloc_st_bucket_cell((yyvsp[0].st_bucket),
                                                          NULL);
                            }
#line 2063 "ltl_parser.tab.c"
    break;

  case 9:
#line 275 "ltl_parser.y"
                            {
			      selector_enabled[0] = FALSE;
			      (yyval.st_bucket_cell) = (((yyvsp[-1].st_bucket) == NULL) ||
                                    ((yyvsp[0].st_bucket) == NULL))?
                                     NULL:
                                     expand_iterative_property_def((yyvsp[0].st_bucket));
			      selector[0] = NULL;
                            }
#line 2076 "ltl_parser.tab.c"
    break;

  case 10:
#line 287 "ltl_parser.y"
                            {
			      handle_unprefixed_concretely_indexed_id(&(yyvsp[-1].st_bucket),
						                      (yyvsp[0].expr_parse_info),
								      PROPERTY_ID_DEF);
			    }
#line 2086 "ltl_parser.tab.c"
    break;

  case 11:
#line 293 "ltl_parser.y"
                            {
			      if (((yyvsp[-4].st_bucket) != NULL) &&
				  ((yyvsp[0].expr_parse_info) != NULL))
				(yyvsp[-4].st_bucket)->info.property_expr = set_nusmv_expr_bucket((yyvsp[0].expr_parse_info)->expr);
			      (yyval.st_bucket) = (yyvsp[-4].st_bucket);
			    }
#line 2097 "ltl_parser.tab.c"
    break;

  case 12:
#line 303 "ltl_parser.y"
                            {
			      handle_unprefixed_symbolically_indexed_id(&(yyvsp[-1].st_bucket),
						                        (yyvsp[0].expr_parse_info),
						                        0,
								        PROPERTY_ID_DEF);
			      act_type_list = NULL;
			    }
#line 2109 "ltl_parser.tab.c"
    break;

  case 13:
#line 311 "ltl_parser.y"
                            {
			      if (((yyvsp[-4].st_bucket) != NULL) &&
				  ((yyvsp[0].expr_parse_info) != NULL))
				(yyvsp[-4].st_bucket)->info.property_expr = (yyvsp[0].expr_parse_info)->expr;
			      (yyval.st_bucket) = (yyvsp[-4].st_bucket);
			    }
#line 2120 "ltl_parser.tab.c"
    break;

  case 14:
#line 321 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2129 "ltl_parser.tab.c"
    break;

  case 15:
#line 326 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2138 "ltl_parser.tab.c"
    break;

  case 16:
#line 331 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_AND_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2156 "ltl_parser.tab.c"
    break;

  case 17:
#line 345 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_OR_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2174 "ltl_parser.tab.c"
    break;

  case 18:
#line 359 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_NOT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2191 "ltl_parser.tab.c"
    break;

  case 19:
#line 372 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_XOR_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2209 "ltl_parser.tab.c"
    break;

  case 20:
#line 386 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_IMPL_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2227 "ltl_parser.tab.c"
    break;

  case 21:
#line 400 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(PROP_BI_IMPL_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2245 "ltl_parser.tab.c"
    break;

  case 22:
#line 414 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info(
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
#line 2270 "ltl_parser.tab.c"
    break;

  case 23:
#line 435 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[0].st_bucket) == NULL)?
				     NULL:
				     alloc_expr_parse_info(
                                       set_expr_bucket(EQ_OP,
						       (ST_BUCKET *)search_lexeme_table("_action_type",
				 						        SYT),
						       (yyvsp[0].st_bucket),
						       NULL,
						       0.0,
						       NULL,
						       FALSE),
				       NULL);
			    }
#line 2289 "ltl_parser.tab.c"
    break;

  case 24:
#line 450 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_NEXT_STATE_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2306 "ltl_parser.tab.c"
    break;

  case 25:
#line 463 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_ALL_FUTURE_STATES_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2323 "ltl_parser.tab.c"
    break;

  case 26:
#line 476 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SOME_FUTURE_STATE_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2340 "ltl_parser.tab.c"
    break;

  case 27:
#line 489 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_UNTIL_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2358 "ltl_parser.tab.c"
    break;

  case 28:
#line 503 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_RELEASES_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2376 "ltl_parser.tab.c"
    break;

  case 29:
#line 517 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_PREV_STATE_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2393 "ltl_parser.tab.c"
    break;

  case 30:
#line 530 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_ALL_PAST_STATES_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2410 "ltl_parser.tab.c"
    break;

  case 31:
#line 543 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = ((yyvsp[-1].expr_parse_info) == NULL)?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SOME_PAST_STATE_SAT_OP,
                                                                           (yyvsp[-1].expr_parse_info)->expr,
                                                                           NULL,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2427 "ltl_parser.tab.c"
    break;

  case 32:
#line 556 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_SINCE_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2445 "ltl_parser.tab.c"
    break;

  case 33:
#line 570 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (((yyvsp[-2].expr_parse_info) == NULL) ||
				    ((yyvsp[0].expr_parse_info) == NULL))?
				     NULL:
				     alloc_expr_parse_info(set_expr_bucket(LTL_TRIGGERED_OP,
                                                                           (yyvsp[-2].expr_parse_info)->expr,
                                                                           (yyvsp[0].expr_parse_info)->expr,
                                                                           NULL,
                                                                           0.0,
                                                                           NULL,
                                                                           FALSE),
                                                           NULL);
			    }
#line 2463 "ltl_parser.tab.c"
    break;

  case 34:
#line 584 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 2471 "ltl_parser.tab.c"
    break;

  case 35:
#line 591 "ltl_parser.y"
                            {
                              handle_concretely_indexed_aei(&(yyvsp[-1].st_bucket),
                                                            (yyvsp[0].expr_parse_info),
                                                            1,
                                                            FALSE,
                                                            selector_enabled[0]);
                            }
#line 2483 "ltl_parser.tab.c"
    break;

  case 36:
#line 599 "ltl_parser.y"
                            {
                              handle_prefixed_indexed_id((yyvsp[-4].st_bucket),
                                                         &(yyvsp[0].st_bucket),
                                                         1,
                                                         selector_enabled[0],
                                                         FALSE,
                                                         ACT_TYPE_ID_USE_AUX_SPEC);
                              if (((yyvsp[0].st_bucket) != NULL) &&
                                  selector_enabled[0] &&
                                  (index_expr[1] != NULL))
			      {
				(yyvsp[0].st_bucket)->symbol_index = ACT_TYPE_ID;
                                act_type_list = alloc_st_bucket4_cell((yyvsp[0].st_bucket),
                                                                      unindexed_aei[1],
                                                                      index_expr[1],
                                                                      unindexed_id[1],
                                                                      act_type_list);
			      }
                              (yyval.st_bucket) = (yyvsp[0].st_bucket);
                            }
#line 2508 "ltl_parser.tab.c"
    break;

  case 37:
#line 623 "ltl_parser.y"
                            {
			      handle_iteration_1(&(yyvsp[0].st_bucket));
			    }
#line 2516 "ltl_parser.tab.c"
    break;

  case 38:
#line 627 "ltl_parser.y"
                            {
			      handle_iteration_2(&(yyvsp[0].expr_parse_info));
			    }
#line 2524 "ltl_parser.tab.c"
    break;

  case 39:
#line 631 "ltl_parser.y"
                            {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[-6].st_bucket),
						      (yyvsp[-3].expr_parse_info),
						      (yyvsp[0].expr_parse_info));
			    }
#line 2534 "ltl_parser.tab.c"
    break;

  case 40:
#line 640 "ltl_parser.y"
                            {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    }
#line 2543 "ltl_parser.tab.c"
    break;

  case 41:
#line 645 "ltl_parser.y"
                            {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[0].expr_parse_info);
			    }
#line 2552 "ltl_parser.tab.c"
    break;

  case 42:
#line 653 "ltl_parser.y"
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
#line 2570 "ltl_parser.tab.c"
    break;

  case 43:
#line 670 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[0].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    }
#line 2582 "ltl_parser.tab.c"
    break;

  case 44:
#line 678 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2591 "ltl_parser.tab.c"
    break;

  case 45:
#line 683 "ltl_parser.y"
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
#line 2609 "ltl_parser.tab.c"
    break;

  case 46:
#line 697 "ltl_parser.y"
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
#line 2627 "ltl_parser.tab.c"
    break;

  case 47:
#line 711 "ltl_parser.y"
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
#line 2645 "ltl_parser.tab.c"
    break;

  case 48:
#line 725 "ltl_parser.y"
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
#line 2663 "ltl_parser.tab.c"
    break;

  case 49:
#line 739 "ltl_parser.y"
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
#line 2681 "ltl_parser.tab.c"
    break;

  case 50:
#line 753 "ltl_parser.y"
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
#line 2698 "ltl_parser.tab.c"
    break;

  case 51:
#line 766 "ltl_parser.y"
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
#line 2715 "ltl_parser.tab.c"
    break;

  case 52:
#line 779 "ltl_parser.y"
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
#line 2732 "ltl_parser.tab.c"
    break;

  case 53:
#line 792 "ltl_parser.y"
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
#line 2750 "ltl_parser.tab.c"
    break;

  case 54:
#line 806 "ltl_parser.y"
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
#line 2768 "ltl_parser.tab.c"
    break;

  case 55:
#line 820 "ltl_parser.y"
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
#line 2786 "ltl_parser.tab.c"
    break;

  case 56:
#line 834 "ltl_parser.y"
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
#line 2803 "ltl_parser.tab.c"
    break;

  case 57:
#line 847 "ltl_parser.y"
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
#line 2820 "ltl_parser.tab.c"
    break;

  case 58:
#line 860 "ltl_parser.y"
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
#line 2837 "ltl_parser.tab.c"
    break;

  case 59:
#line 873 "ltl_parser.y"
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
#line 2854 "ltl_parser.tab.c"
    break;

  case 60:
#line 886 "ltl_parser.y"
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
#line 2871 "ltl_parser.tab.c"
    break;

  case 61:
#line 899 "ltl_parser.y"
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
#line 2888 "ltl_parser.tab.c"
    break;

  case 62:
#line 912 "ltl_parser.y"
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
#line 2906 "ltl_parser.tab.c"
    break;

  case 63:
#line 926 "ltl_parser.y"
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
#line 2924 "ltl_parser.tab.c"
    break;

  case 64:
#line 940 "ltl_parser.y"
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
#line 2942 "ltl_parser.tab.c"
    break;

  case 65:
#line 954 "ltl_parser.y"
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
#line 2959 "ltl_parser.tab.c"
    break;

  case 66:
#line 967 "ltl_parser.y"
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
#line 2977 "ltl_parser.tab.c"
    break;

  case 67:
#line 981 "ltl_parser.y"
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
#line 2995 "ltl_parser.tab.c"
    break;

  case 68:
#line 995 "ltl_parser.y"
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
#line 3013 "ltl_parser.tab.c"
    break;

  case 69:
#line 1009 "ltl_parser.y"
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
#line 3030 "ltl_parser.tab.c"
    break;

  case 70:
#line 1022 "ltl_parser.y"
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
#line 3049 "ltl_parser.tab.c"
    break;

  case 71:
#line 1037 "ltl_parser.y"
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
#line 3067 "ltl_parser.tab.c"
    break;

  case 72:
#line 1051 "ltl_parser.y"
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
#line 3086 "ltl_parser.tab.c"
    break;

  case 73:
#line 1066 "ltl_parser.y"
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
#line 3104 "ltl_parser.tab.c"
    break;

  case 74:
#line 1080 "ltl_parser.y"
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
#line 3121 "ltl_parser.tab.c"
    break;

  case 75:
#line 1093 "ltl_parser.y"
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
#line 3139 "ltl_parser.tab.c"
    break;

  case 76:
#line 1107 "ltl_parser.y"
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
#line 3156 "ltl_parser.tab.c"
    break;

  case 77:
#line 1120 "ltl_parser.y"
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
#line 3174 "ltl_parser.tab.c"
    break;

  case 78:
#line 1134 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 3183 "ltl_parser.tab.c"
    break;

  case 79:
#line 1139 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 3192 "ltl_parser.tab.c"
    break;

  case 80:
#line 1144 "ltl_parser.y"
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
#line 3210 "ltl_parser.tab.c"
    break;

  case 81:
#line 1158 "ltl_parser.y"
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
#line 3228 "ltl_parser.tab.c"
    break;

  case 82:
#line 1172 "ltl_parser.y"
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
#line 3245 "ltl_parser.tab.c"
    break;

  case 83:
#line 1185 "ltl_parser.y"
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
#line 3263 "ltl_parser.tab.c"
    break;

  case 84:
#line 1199 "ltl_parser.y"
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
#line 3281 "ltl_parser.tab.c"
    break;

  case 85:
#line 1213 "ltl_parser.y"
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
#line 3299 "ltl_parser.tab.c"
    break;

  case 86:
#line 1227 "ltl_parser.y"
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
#line 3317 "ltl_parser.tab.c"
    break;

  case 87:
#line 1241 "ltl_parser.y"
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
#line 3335 "ltl_parser.tab.c"
    break;

  case 88:
#line 1255 "ltl_parser.y"
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
#line 3353 "ltl_parser.tab.c"
    break;

  case 89:
#line 1269 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3361 "ltl_parser.tab.c"
    break;

  case 90:
#line 1273 "ltl_parser.y"
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
#line 3378 "ltl_parser.tab.c"
    break;

  case 91:
#line 1286 "ltl_parser.y"
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
#line 3395 "ltl_parser.tab.c"
    break;

  case 92:
#line 1299 "ltl_parser.y"
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
#line 3413 "ltl_parser.tab.c"
    break;

  case 93:
#line 1313 "ltl_parser.y"
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
#line 3431 "ltl_parser.tab.c"
    break;

  case 94:
#line 1327 "ltl_parser.y"
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
#line 3449 "ltl_parser.tab.c"
    break;

  case 95:
#line 1341 "ltl_parser.y"
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
#line 3466 "ltl_parser.tab.c"
    break;

  case 96:
#line 1354 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3474 "ltl_parser.tab.c"
    break;

  case 97:
#line 1358 "ltl_parser.y"
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
#line 3492 "ltl_parser.tab.c"
    break;

  case 98:
#line 1372 "ltl_parser.y"
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
#line 3511 "ltl_parser.tab.c"
    break;

  case 99:
#line 1387 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3519 "ltl_parser.tab.c"
    break;

  case 100:
#line 1391 "ltl_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3529 "ltl_parser.tab.c"
    break;

  case 101:
#line 1397 "ltl_parser.y"
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
#line 3547 "ltl_parser.tab.c"
    break;

  case 102:
#line 1411 "ltl_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3557 "ltl_parser.tab.c"
    break;

  case 103:
#line 1417 "ltl_parser.y"
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
#line 3576 "ltl_parser.tab.c"
    break;

  case 104:
#line 1432 "ltl_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3584 "ltl_parser.tab.c"
    break;

  case 105:
#line 1439 "ltl_parser.y"
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
#line 3602 "ltl_parser.tab.c"
    break;

  case 106:
#line 1453 "ltl_parser.y"
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
#line 3619 "ltl_parser.tab.c"
    break;

  case 107:
#line 1469 "ltl_parser.y"
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
#line 3638 "ltl_parser.tab.c"
    break;

  case 108:
#line 1487 "ltl_parser.y"
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
#line 3655 "ltl_parser.tab.c"
    break;

  case 109:
#line 1503 "ltl_parser.y"
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
#line 3670 "ltl_parser.tab.c"
    break;

  case 110:
#line 1514 "ltl_parser.y"
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
#line 3686 "ltl_parser.tab.c"
    break;


#line 3690 "ltl_parser.tab.c"

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
#line 1528 "ltl_parser.y"



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
