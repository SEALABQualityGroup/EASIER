/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

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
/* Line 1529 of yacc.c.  */
#line 280 "aemilia_parser.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE aemiliayylval;

