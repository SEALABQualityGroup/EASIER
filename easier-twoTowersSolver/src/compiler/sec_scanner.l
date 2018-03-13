 /***************************************************************/
 /*								*/
 /*								*/
 /*                  	   sec_scanner.l			*/
 /*								*/
 /*                                         (by Marco Bernardo) */
 /***************************************************************/
 /* This module implements the lexical analyzer for .sec spec files. */
 /* This module contains the following function: */
 /* - unread_sec_token(): It writes the last read token back to the stream. */
 /***************************************************************/
 

%{

 /***************************************************************/
 /* 1. Inclusion of libraries.					*/
 /***************************************************************/

#include	"../headers/Library.h"
#include	"sec_parser.tab.h"


 /***************************************************************/
 /* 2. Inclusion of external gvariables and functions.		*/
 /***************************************************************/

#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/table_handler.h"


 /***************************************************************/
 /* 3. Definition/declaration of exporting gvariables/functions.*/
 /***************************************************************/

void		unread_sec_token(void);


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
		  print_lexeme(secyytext,
			       secyyleng);
		}


<C>{inside_C}\t	{
		  print_lexeme(secyytext,
			       secyyleng);
		}


<C>{inside_C}\n	{
		  secyytext[--secyyleng] = EOS;
		  print_lexeme(secyytext,
			       secyyleng);
		  print_newline(FALSE);
		  BEGIN 0;
		}


{id}		{
		  print_lexeme(secyytext,
			       secyyleng);
		  secyylval.st_bucket = (ST_BUCKET *)search_lexeme_table(secyytext,
									 SYT);
		  if ((secyylval.st_bucket->symbol_index >= FIRST_KEYWORD_TOKEN) &&
		      (secyylval.st_bucket->symbol_index <= FIRST_KEYWORD_TOKEN + KEYWORD_NUM))
		    return(secyylval.st_bucket->symbol_index);
		  else
		    return(ID);
		}


{number}	{
		  print_lexeme(secyytext,
			       secyyleng);
		  secyylval.st_bucket = set_number_bucket(secyytext);
		  return(NUMBER);
		}


";"		|
"."		|
"!"             |
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
		  print_lexeme(secyytext,
			       secyyleng);
		  return(secyytext[0]);
		}


".."		|
"!="		|
"<="		|
">="		|
"&&"		|
"||"		{
		  print_lexeme(secyytext,
			       secyyleng);
		  switch(secyytext[0])
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
		  }
		}


{blank}		{
		  record_blank(secyyleng);
		}


\t		{
		  record_tab();
		}


\n		{
		  print_newline(FALSE);
		}


{illegal_char}	{
		  print_lexeme(secyytext,
			       secyyleng);
		  signal_error(ILLEGAL_CHAR,
			       NULL,
			       NULL);
		}


%%


 /***************************************************************/
 /* 9. Definition of exporting functions.			*/
 /***************************************************************/

void		unread_sec_token(void)
{
	yyless(0);
}


 /***************************************************************/
 /* 10. Definition of private functions.			*/
 /***************************************************************/
