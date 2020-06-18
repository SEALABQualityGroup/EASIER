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
#define yyparse         secyyparse
#define yylex           secyylex
#define yyerror         secyyerror
#define yydebug         secyydebug
#define yynerrs         secyynerrs
#define yylval          secyylval
#define yychar          secyychar

/* First part of user prologue.  */
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


#line 147 "sec_parser.tab.c"

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
#ifndef YY_SECYY_SEC_PARSER_TAB_H_INCLUDED
# define YY_SECYY_SEC_PARSER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int secyydebug;
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

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 113 "sec_parser.y"

	BOOLEAN		boolean;
				/* flag indicating whether an iterative declaration is correct */
        ST_BUCKET       *st_bucket;
                                /* pointer to the symbol table bucket for an identifier or a number */
	EXPR_PARSE_INFO	*expr_parse_info;
				/* parse information about an expression */
	LAR_PARSE_INFO	*lar_parse_info;
				/* parse information about an expression list/array/record */

#line 272 "sec_parser.tab.c"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE secyylval;

int secyyparse (void);

#endif /* !YY_SECYY_SEC_PARSER_TAB_H_INCLUDED  */



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
#define YYFINAL  17
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   1173

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  77
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  26
/* YYNRULES -- Number of rules.  */
#define YYNRULES  99
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  318

#define YYUNDEFTOK  2
#define YYMAXUTOK   706


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
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_int16 yyrline[] =
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
  "T_READ", "T_WRITE", "T_RECORD_CONS", "T_GET", "T_PUT",
  "T_HIGH_SECURITY", "T_LOW_SECURITY", "T_OBS_NRESTR_INTERNALS",
  "T_OBS_NRESTR_INTERACTIONS", "T_ALL_OBS_NRESTR", "DOTDOT", "NE", "LE",
  "GE", "AND", "OR", "'!'", "'='", "'<'", "'>'", "'+'", "'-'", "'*'",
  "'/'", "';'", "'.'", "'['", "']'", "'('", "','", "')'", "$accept",
  "sec_level_list", "$@1", "$@2", "high_list", "low_list", "act_type_list",
  "$@3", "act_type_set", "s_act_type_set", "$@4", "i_act_type_set", "$@5",
  "act_type_set_hl", "iteration", "$@6", "$@7", "poss_aei_index",
  "aei_index", "expr", "$@8", "$@9", "expr_list", "expr_array",
  "expr_record", "struct_expr", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_int16 yytoknum[] =
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

#define YYPACT_NINF (-123)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-1)

#define yytable_value_is_error(Yyn) \
  ((Yyn) == YYTABLE_NINF)

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
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

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_int8 yydefact[] =
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

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -123,   208,  -123,  -123,  -123,   215,    21,  -123,  -123,  -123,
    -123,  -123,  -123,    49,  -123,  -123,  -123,  -123,   216,   -23,
    -123,  -123,  -123,  -123,  -123,  -122
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     3,    20,    21,     4,    19,    13,    33,    14,    15,
      83,    29,   147,   211,    16,    84,   257,    24,    25,   177,
     255,   256,   178,   186,   190,   179
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
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
static const yytype_int8 yystos[] =
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

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_int8 yyr1[] =
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

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_int8 yyr2[] =
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
#line 239 "sec_parser.y"
                            {
			    }
#line 1861 "sec_parser.tab.c"
    break;

  case 3:
#line 242 "sec_parser.y"
                            {
                              handle_sync_sec();
                              yyerrok;
                            }
#line 1870 "sec_parser.tab.c"
    break;

  case 4:
#line 247 "sec_parser.y"
                            {
                            }
#line 1877 "sec_parser.tab.c"
    break;

  case 5:
#line 250 "sec_parser.y"
                            {
			      variation = MADE_LOW;
                              handle_sync_sec();
                              yyerrok;
                            }
#line 1887 "sec_parser.tab.c"
    break;

  case 6:
#line 256 "sec_parser.y"
                            {
                            }
#line 1894 "sec_parser.tab.c"
    break;

  case 7:
#line 262 "sec_parser.y"
                            {
			      variation = MADE_LOW;
                            }
#line 1902 "sec_parser.tab.c"
    break;

  case 8:
#line 269 "sec_parser.y"
                            {
                            }
#line 1909 "sec_parser.tab.c"
    break;

  case 9:
#line 275 "sec_parser.y"
                            {
			    }
#line 1916 "sec_parser.tab.c"
    break;

  case 10:
#line 278 "sec_parser.y"
                            {
			    }
#line 1923 "sec_parser.tab.c"
    break;

  case 11:
#line 281 "sec_parser.y"
                            {
                              handle_sync_sec();
                              yyerrok;
                            }
#line 1932 "sec_parser.tab.c"
    break;

  case 12:
#line 286 "sec_parser.y"
                            {
			    }
#line 1939 "sec_parser.tab.c"
    break;

  case 13:
#line 292 "sec_parser.y"
                            {
			    }
#line 1946 "sec_parser.tab.c"
    break;

  case 14:
#line 295 "sec_parser.y"
                            {
			      selector_enabled[0] = FALSE;
			      if (((yyvsp[-1].st_bucket) != NULL) &&
				  (yyvsp[0].boolean))
				expand_iterative_act_type_decl();
			      selector[0] = NULL;
			    }
#line 1958 "sec_parser.tab.c"
    break;

  case 15:
#line 306 "sec_parser.y"
                            {
			      handle_concretely_indexed_aei(&(yyvsp[-1].st_bucket),
							    (yyvsp[0].expr_parse_info),
							    0,
							    FALSE,
							    FALSE);
			      aei = (yyvsp[-1].st_bucket);
                            }
#line 1971 "sec_parser.tab.c"
    break;

  case 16:
#line 315 "sec_parser.y"
                            {
			    }
#line 1978 "sec_parser.tab.c"
    break;

  case 17:
#line 318 "sec_parser.y"
                            {
			      associate_obs_nrestr_internals();
			    }
#line 1986 "sec_parser.tab.c"
    break;

  case 18:
#line 322 "sec_parser.y"
                            {
			      associate_obs_nrestr_interactions();
			    }
#line 1994 "sec_parser.tab.c"
    break;

  case 19:
#line 326 "sec_parser.y"
                            {
			      associate_all_obs_nrestr();
			    }
#line 2002 "sec_parser.tab.c"
    break;

  case 20:
#line 333 "sec_parser.y"
                            {
			      handle_symbolically_indexed_aei(&(yyvsp[-1].st_bucket),
							      (yyvsp[0].expr_parse_info),
							      0,
							      FALSE);
			      aei = (yyvsp[-1].st_bucket);
                            }
#line 2014 "sec_parser.tab.c"
    break;

  case 21:
#line 341 "sec_parser.y"
                            {
			      (yyval.boolean) = (yyvsp[0].boolean);
			    }
#line 2022 "sec_parser.tab.c"
    break;

  case 22:
#line 348 "sec_parser.y"
                            {
			      handle_prefixed_indexed_id(aei,
						         &(yyvsp[0].st_bucket),
						         0,
						         selector_enabled[0],
						         FALSE,
							 ACT_TYPE_ID_USE_AUX_SPEC);
			      if (((yyvsp[0].st_bucket) != NULL) &&
				  !selector_enabled[0])
				associate_security_level((yyvsp[0].st_bucket),
							 FALSE,
							 FALSE);
			      (yyval.boolean) = ((yyvsp[0].st_bucket) != NULL);
			    }
#line 2041 "sec_parser.tab.c"
    break;

  case 23:
#line 363 "sec_parser.y"
                            {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_internals(aei);
			      (yyval.boolean) = (aei != NULL);
			    }
#line 2052 "sec_parser.tab.c"
    break;

  case 24:
#line 370 "sec_parser.y"
                            {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_obs_nrestr_interactions(aei);
			      (yyval.boolean) = (aei != NULL);
			    }
#line 2063 "sec_parser.tab.c"
    break;

  case 25:
#line 377 "sec_parser.y"
                            {
			      if ((aei != NULL) &&
				  !selector_enabled[0])
			        associate_aei_all_obs_nrestr(aei);
			      (yyval.boolean) = (aei != NULL);
			    }
#line 2074 "sec_parser.tab.c"
    break;

  case 26:
#line 387 "sec_parser.y"
                            {
			      handle_iteration_1(&(yyvsp[0].st_bucket));
			    }
#line 2082 "sec_parser.tab.c"
    break;

  case 27:
#line 391 "sec_parser.y"
                            {
			      handle_iteration_2(&(yyvsp[0].expr_parse_info));
			    }
#line 2090 "sec_parser.tab.c"
    break;

  case 28:
#line 395 "sec_parser.y"
                            {
			      (yyval.st_bucket) = handle_iteration_3((yyvsp[-6].st_bucket),
						      (yyvsp[-3].expr_parse_info),
						      (yyvsp[0].expr_parse_info));
			    }
#line 2100 "sec_parser.tab.c"
    break;

  case 29:
#line 404 "sec_parser.y"
                            {
			      poss_aei_index_parsed = FALSE;
			      (yyval.expr_parse_info) = NULL;
			    }
#line 2109 "sec_parser.tab.c"
    break;

  case 30:
#line 409 "sec_parser.y"
                            {
			      poss_aei_index_parsed = TRUE;
			      (yyval.expr_parse_info) = (yyvsp[0].expr_parse_info);
			    }
#line 2118 "sec_parser.tab.c"
    break;

  case 31:
#line 417 "sec_parser.y"
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
#line 2136 "sec_parser.tab.c"
    break;

  case 32:
#line 434 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = handle_id_in_expr(&(yyvsp[0].st_bucket),
						     NULL,
						     NULL,
						     FALSE,
						     TRUE);
			    }
#line 2148 "sec_parser.tab.c"
    break;

  case 33:
#line 442 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2157 "sec_parser.tab.c"
    break;

  case 34:
#line 447 "sec_parser.y"
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
#line 2175 "sec_parser.tab.c"
    break;

  case 35:
#line 461 "sec_parser.y"
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
#line 2193 "sec_parser.tab.c"
    break;

  case 36:
#line 475 "sec_parser.y"
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
#line 2211 "sec_parser.tab.c"
    break;

  case 37:
#line 489 "sec_parser.y"
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
#line 2229 "sec_parser.tab.c"
    break;

  case 38:
#line 503 "sec_parser.y"
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
#line 2247 "sec_parser.tab.c"
    break;

  case 39:
#line 517 "sec_parser.y"
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
#line 2264 "sec_parser.tab.c"
    break;

  case 40:
#line 530 "sec_parser.y"
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
#line 2281 "sec_parser.tab.c"
    break;

  case 41:
#line 543 "sec_parser.y"
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
#line 2298 "sec_parser.tab.c"
    break;

  case 42:
#line 556 "sec_parser.y"
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
#line 2316 "sec_parser.tab.c"
    break;

  case 43:
#line 570 "sec_parser.y"
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
#line 2334 "sec_parser.tab.c"
    break;

  case 44:
#line 584 "sec_parser.y"
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
#line 2352 "sec_parser.tab.c"
    break;

  case 45:
#line 598 "sec_parser.y"
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
#line 2369 "sec_parser.tab.c"
    break;

  case 46:
#line 611 "sec_parser.y"
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
#line 2386 "sec_parser.tab.c"
    break;

  case 47:
#line 624 "sec_parser.y"
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
#line 2403 "sec_parser.tab.c"
    break;

  case 48:
#line 637 "sec_parser.y"
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
#line 2420 "sec_parser.tab.c"
    break;

  case 49:
#line 650 "sec_parser.y"
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
#line 2437 "sec_parser.tab.c"
    break;

  case 50:
#line 663 "sec_parser.y"
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
#line 2454 "sec_parser.tab.c"
    break;

  case 51:
#line 676 "sec_parser.y"
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
#line 2472 "sec_parser.tab.c"
    break;

  case 52:
#line 690 "sec_parser.y"
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
#line 2490 "sec_parser.tab.c"
    break;

  case 53:
#line 704 "sec_parser.y"
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
#line 2508 "sec_parser.tab.c"
    break;

  case 54:
#line 718 "sec_parser.y"
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
#line 2525 "sec_parser.tab.c"
    break;

  case 55:
#line 731 "sec_parser.y"
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
#line 2543 "sec_parser.tab.c"
    break;

  case 56:
#line 745 "sec_parser.y"
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
#line 2561 "sec_parser.tab.c"
    break;

  case 57:
#line 759 "sec_parser.y"
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
#line 2579 "sec_parser.tab.c"
    break;

  case 58:
#line 773 "sec_parser.y"
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
#line 2596 "sec_parser.tab.c"
    break;

  case 59:
#line 786 "sec_parser.y"
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
#line 2615 "sec_parser.tab.c"
    break;

  case 60:
#line 801 "sec_parser.y"
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
#line 2633 "sec_parser.tab.c"
    break;

  case 61:
#line 815 "sec_parser.y"
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
#line 2652 "sec_parser.tab.c"
    break;

  case 62:
#line 830 "sec_parser.y"
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
#line 2670 "sec_parser.tab.c"
    break;

  case 63:
#line 844 "sec_parser.y"
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
#line 2687 "sec_parser.tab.c"
    break;

  case 64:
#line 857 "sec_parser.y"
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
#line 2705 "sec_parser.tab.c"
    break;

  case 65:
#line 871 "sec_parser.y"
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
#line 2722 "sec_parser.tab.c"
    break;

  case 66:
#line 884 "sec_parser.y"
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
#line 2740 "sec_parser.tab.c"
    break;

  case 67:
#line 898 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2749 "sec_parser.tab.c"
    break;

  case 68:
#line 903 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = alloc_expr_parse_info((yyvsp[0].st_bucket),
							 NULL);
			    }
#line 2758 "sec_parser.tab.c"
    break;

  case 69:
#line 908 "sec_parser.y"
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
#line 2776 "sec_parser.tab.c"
    break;

  case 70:
#line 922 "sec_parser.y"
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
#line 2794 "sec_parser.tab.c"
    break;

  case 71:
#line 936 "sec_parser.y"
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
#line 2811 "sec_parser.tab.c"
    break;

  case 72:
#line 949 "sec_parser.y"
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
#line 2829 "sec_parser.tab.c"
    break;

  case 73:
#line 963 "sec_parser.y"
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
#line 2847 "sec_parser.tab.c"
    break;

  case 74:
#line 977 "sec_parser.y"
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
#line 2865 "sec_parser.tab.c"
    break;

  case 75:
#line 991 "sec_parser.y"
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
#line 2883 "sec_parser.tab.c"
    break;

  case 76:
#line 1005 "sec_parser.y"
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
#line 2901 "sec_parser.tab.c"
    break;

  case 77:
#line 1019 "sec_parser.y"
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
#line 2919 "sec_parser.tab.c"
    break;

  case 78:
#line 1033 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 2927 "sec_parser.tab.c"
    break;

  case 79:
#line 1037 "sec_parser.y"
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
#line 2944 "sec_parser.tab.c"
    break;

  case 80:
#line 1050 "sec_parser.y"
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
#line 2961 "sec_parser.tab.c"
    break;

  case 81:
#line 1063 "sec_parser.y"
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
#line 2979 "sec_parser.tab.c"
    break;

  case 82:
#line 1077 "sec_parser.y"
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
#line 2997 "sec_parser.tab.c"
    break;

  case 83:
#line 1091 "sec_parser.y"
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
#line 3015 "sec_parser.tab.c"
    break;

  case 84:
#line 1105 "sec_parser.y"
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
#line 3032 "sec_parser.tab.c"
    break;

  case 85:
#line 1118 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3040 "sec_parser.tab.c"
    break;

  case 86:
#line 1122 "sec_parser.y"
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
#line 3058 "sec_parser.tab.c"
    break;

  case 87:
#line 1136 "sec_parser.y"
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
#line 3077 "sec_parser.tab.c"
    break;

  case 88:
#line 1151 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3085 "sec_parser.tab.c"
    break;

  case 89:
#line 1155 "sec_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3095 "sec_parser.tab.c"
    break;

  case 90:
#line 1161 "sec_parser.y"
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
#line 3113 "sec_parser.tab.c"
    break;

  case 91:
#line 1175 "sec_parser.y"
                            {
			      check_id(REC_FIELD_ID_USE_AUX_SPEC,
				       &(yyvsp[0].st_bucket),
				       FALSE);
			    }
#line 3123 "sec_parser.tab.c"
    break;

  case 92:
#line 1181 "sec_parser.y"
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
#line 3142 "sec_parser.tab.c"
    break;

  case 93:
#line 1196 "sec_parser.y"
                            {
			      (yyval.expr_parse_info) = (yyvsp[-1].expr_parse_info);
			    }
#line 3150 "sec_parser.tab.c"
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
			    }
#line 3168 "sec_parser.tab.c"
    break;

  case 95:
#line 1217 "sec_parser.y"
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
#line 3185 "sec_parser.tab.c"
    break;

  case 96:
#line 1233 "sec_parser.y"
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
#line 3204 "sec_parser.tab.c"
    break;

  case 97:
#line 1251 "sec_parser.y"
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
#line 3221 "sec_parser.tab.c"
    break;

  case 98:
#line 1267 "sec_parser.y"
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
#line 3236 "sec_parser.tab.c"
    break;

  case 99:
#line 1278 "sec_parser.y"
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
#line 3252 "sec_parser.tab.c"
    break;


#line 3256 "sec_parser.tab.c"

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
