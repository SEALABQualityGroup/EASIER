 /***************************************************************/
 /*								*/
 /*								*/
 /*                  	   sim_scanner.l			*/
 /*								*/
 /*                                         (by Marco Bernardo) */
 /***************************************************************/
 /* This module implements the lexical analyzer for .sim spec files. */
 /* This module contains the following function: */
 /* - unread_sim_token(): It writes the last read token back to the stream. */
 /***************************************************************/
 

%{

 /***************************************************************/
 /* 1. Inclusion of libraries.					*/
 /***************************************************************/

#include	"../headers/Library.h"
#include	"sim_parser.tab.h"


 /***************************************************************/
 /* 2. Inclusion of external gvariables and functions.		*/
 /***************************************************************/

#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/table_handler.h"


 /***************************************************************/
 /* 3. Definition/declaration of exporting gvariables/functions.*/
 /***************************************************************/

void		unread_sim_token(void);


 /***************************************************************/
 /* 4. Definition/declaration of private gvariables/functions.	*/
 /***************************************************************/

%}


 /***************************************************************/
 /* 5. Definition of regular expressions.			*/
 /***************************************************************/

id		[A-Za-z]([A-Za-z]|[0-9]|\_)*
trace_file_id	(\.)(\/\.\.)*(\/{id})+
number		((\-)?[0-9]+)|((\-)?[0-9]+(\.)[0-9]+)
blank		(" ")+
inside_C	[^\t\n]*
illegal_char	[^\,\;\.\!\(\)\{\}\[\]\+\-\*\/\=\<\>\t\n\%]


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
		  print_lexeme(simyytext,
			       simyyleng);
		}


<C>{inside_C}\t	{
		  print_lexeme(simyytext,
			       simyyleng);
		}


<C>{inside_C}\n	{
		  simyytext[--simyyleng] = EOS;
		  print_lexeme(simyytext,
			       simyyleng);
		  print_newline(FALSE);
		  BEGIN 0;
		}


{id}		{
		  print_lexeme(simyytext,
			       simyyleng);
		  simyylval.st_bucket = (ST_BUCKET *)search_lexeme_table(simyytext,
									 SYT);
		  if ((simyylval.st_bucket->symbol_index >= FIRST_KEYWORD_TOKEN) &&
		      (simyylval.st_bucket->symbol_index <= FIRST_KEYWORD_TOKEN + KEYWORD_NUM))
		    return(simyylval.st_bucket->symbol_index);
		  else
		    return(ID);
		}


{trace_file_id}	{
		  print_lexeme(simyytext,
			       simyyleng);
		  simyylval.st_bucket = (ST_BUCKET *)search_lexeme_table(simyytext,
									 SYT);
		  simyylval.st_bucket->symbol_index = TRACE_FILE_ID;
		  return(TRACE_FILE_ID);
		}


{number}	{
		  print_lexeme(simyytext,
			       simyyleng);
		  simyylval.st_bucket = set_number_bucket(simyytext);
		  return(NUMBER);
		}


","		|
";"		|
"."		|
"!"		|
"("		|
")"		|
"{"		|
"}"		|
"["		|
"]"		|
"+"		|
"-"		|
"*"		|
"/"		|
"="		|
"<"		|
">"		{
		  print_lexeme(simyytext,
			       simyyleng);
		  return(simyytext[0]);
		}


".."		|
"!="		|
"<="		|
">="		|
"&&"		|
"||"		|
"->"		{
		  print_lexeme(simyytext,
			       simyyleng);
		  switch(simyytext[0])
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
		  record_blank(simyyleng);
		}


\t		{
		  record_tab();
		}


\n		{
		  print_newline(FALSE);
		}


{illegal_char}	{
		  print_lexeme(simyytext,
			       simyyleng);
		  signal_error(ILLEGAL_CHAR,
			       NULL,
			       NULL);
		}


%%


 /***************************************************************/
 /* 9. Definition of exporting functions.			*/
 /***************************************************************/

void		unread_sim_token(void)
{
	yyless(0);
}


 /***************************************************************/
 /* 10. Definition of private functions.			*/
 /***************************************************************/
