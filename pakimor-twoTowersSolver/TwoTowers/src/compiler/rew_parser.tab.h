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
/* Line 1529 of yacc.c.  */
#line 186 "rew_parser.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE rewyylval;

