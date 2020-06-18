/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Bison interface for Yacc-like parsers in C

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

/* Undocumented macros, especially those whose name start with YY_,
   are private implementation details.  Do not rely on them.  */

#ifndef YY_AEMILIAYY_AEMILIA_PARSER_TAB_H_INCLUDED
# define YY_AEMILIAYY_AEMILIA_PARSER_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int aemiliayydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
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

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
union YYSTYPE
{
#line 332 "aemilia_parser.y"

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

#line 184 "aemilia_parser.tab.h"

};
typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE aemiliayylval;

int aemiliayyparse (void);

#endif /* !YY_AEMILIAYY_AEMILIA_PARSER_TAB_H_INCLUDED  */
