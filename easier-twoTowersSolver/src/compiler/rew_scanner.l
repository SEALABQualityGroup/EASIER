 /***************************************************************/
 /*								*/
 /*								*/
 /*                  	   rew_scanner.l			*/
 /*								*/
 /*                                         (by Marco Bernardo) */
 /***************************************************************/
 /* This module implements the lexical analyzer for .rew spec files. */
 /* This module contains the following function: */
 /* - unread_rew_token(): It writes the last read token back to the stream. */
 /***************************************************************/
 

%{

 /***************************************************************/
 /* 1. Inclusion of libraries.					*/
 /***************************************************************/

#include	"../headers/Library.h"
#include	"rew_parser.tab.h"


 /***************************************************************/
 /* 2. Inclusion of external gvariables and functions.		*/
 /***************************************************************/

#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/table_handler.h"


 /***************************************************************/
 /* 3. Definition/declaration of exporting gvariables/functions.*/
 /***************************************************************/

void		unread_rew_token(void);


 /***************************************************************/
 /* 4. Definition/declaration of private gvariables/functions.	*/
 /***************************************************************/

%}


 /***************************************************************/
 /* 5. Definition of regular expressions.			*/
 /***************************************************************/

id		[A-Za-z]([A-Za-z]|[0-9]|\_)*
number		((\-)?[0-9]+)|((\-)?[0-9]+(\.)[0-9]+)
blank		(" ")+
inside_C	[^\t\n]*
illegal_char	[^\;\.\!\(\)\[\]\+\-\*\/\=\<\>\t\n\%]


 /***************************************************************/
 /* 6. Definition of start conditions.				*/
 /***************************************************************/

%start		C
			/* %-comment context */


 /***************************************************************/
 /* 7. Enforcement of standard termination.			*/
 /***************************************************************/

%option		noyywrap


 /***************************************************************/
 /* 8. Association of rules with regular expressions.		*/
 /***************************************************************/

%%

"%"		{
		  BEGIN C;
		  print_lexeme(rewyytext,
			       rewyyleng);
		}


<C>{inside_C}\t	{
		  print_lexeme(rewyytext,
			       rewyyleng);
		}


<C>{inside_C}\n	{
		  rewyytext[--rewyyleng] = EOS;
		  print_lexeme(rewyytext,
			       rewyyleng);
		  print_newline(FALSE);
		  BEGIN 0;
		}


{id}		{
		  print_lexeme(rewyytext,
			       rewyyleng);
		  rewyylval.st_bucket = (ST_BUCKET *)search_lexeme_table(rewyytext,
									 SYT);
		  if ((rewyylval.st_bucket->symbol_index >= FIRST_KEYWORD_TOKEN) &&
		      (rewyylval.st_bucket->symbol_index <= FIRST_KEYWORD_TOKEN + KEYWORD_NUM))
		    return(rewyylval.st_bucket->symbol_index);
		  else
		    return(ID);
		}


{number}	{
		  print_lexeme(rewyytext,
			       rewyyleng);
		  rewyylval.st_bucket = set_number_bucket(rewyytext);
		  return(NUMBER);
		}


";"		|
"."		|
"!"		|
"("		|
")"		|
"["		|
"]"		|
"+"		|
"-"		|
"*"		|
"/"		|
"="		|
"<"		|
">"		{
		  print_lexeme(rewyytext,
			       rewyyleng);
		  return(rewyytext[0]);
		}


".."		|
"!="		|
"<="		|
">="		|
"&&"		|
"||"		|
"->"		{
		  print_lexeme(rewyytext,
			       rewyyleng);
		  switch(rewyytext[0])
		  {
		    case '.':
		      return(DOTDOT);
		      break;
		    case '!':
		      return(NE);
		      break;
		    case '<':
		      return(LE);
		      break;
		    case '>':
		      return(GE);
		      break;
		    case '&':
		      return(AND);
		      break;
		    case '|':
		      return(OR);
		      break;
		    case '-':
		      return(IMPL);
		      break;
		  }
		}


{blank}		{
		  record_blank(rewyyleng);
		}


\t		{
		  record_tab();
		}


\n		{
		  print_newline(FALSE);
		}


{illegal_char}	{
		  print_lexeme(rewyytext,
			       rewyyleng);
		  signal_error(ILLEGAL_CHAR,
			       NULL,
			       NULL);
		}


%%


 /***************************************************************/
 /* 9. Definition of exporting functions.			*/
 /***************************************************************/

void		unread_rew_token(void)
{
	yyless(0);
}


 /***************************************************************/
 /* 10. Definition of private functions.			*/
 /***************************************************************/
