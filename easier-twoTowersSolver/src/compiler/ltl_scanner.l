 /***************************************************************
  *								*
  *								*
  *                  	   ltl_scanner.l			*
  *								*
  *                                         (by Marco Bernardo) *
  ***************************************************************
  * This module implements the lexical analyzer for .ltl spec files.
  * This module contains the following function:
  * - unread_ltl_token(): It writes the last read token back to the stream.
  ***************************************************************/
 

%{

 /***************************************************************
  * 1. Inclusion of libraries.					*
  ***************************************************************/

#include	"../headers/Library.h"
#include	"ltl_parser.tab.h"


 /***************************************************************
  * 2. Inclusion of external gvariables and functions.		*
  ***************************************************************/

#include	"../headers/listing_generator.h"
#include	"../headers/symbol_handler.h"

#include	"../headers/table_handler.h"


 /***************************************************************
  * 3. Definition/declaration of exporting gvariables/functions.*
  ***************************************************************/

void		unread_ltl_token(void);


 /***************************************************************
  * 4. Definition/declaration of private gvariables/functions.	*
  ***************************************************************/

%}


 /***************************************************************
  * 5. Definition of regular expressions.			*
  ***************************************************************/

id		[A-Za-z]([A-Za-z]|[0-9]|\_)*
number		((\-)?[0-9]+)|((\-)?[0-9]+(\.)[0-9]+)
blank		(" ")+
inside_C	[^\t\n]*
illegal_char	[^\,\;\.\!\_\(\)\[\]\+\-\*\/\=\<\>\\\t\n\%]


 /***************************************************************
  * 6. Definition of start conditions.				*
  ***************************************************************/

%start		C
			/* %-comment context */


 /***************************************************************
  * 7. Enforcement of standard termination.			*
  ***************************************************************/

%option		noyywrap


 /***************************************************************
  * 8. Association of rules with regular expressions.		*
  ***************************************************************/

%%

"%"		{
		  BEGIN C;
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		}


<C>{inside_C}\t	{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		}


<C>{inside_C}\n	{
		  ltlyytext[--ltlyyleng] = EOS;
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  print_newline(FALSE);
		  BEGIN 0;
		}


{id}		{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  ltlyylval.st_bucket = (ST_BUCKET *)search_lexeme_table(ltlyytext,
									 SYT);
		  if ((ltlyylval.st_bucket->symbol_index >= FIRST_KEYWORD_TOKEN) &&
		      (ltlyylval.st_bucket->symbol_index <= FIRST_KEYWORD_TOKEN + KEYWORD_NUM))
		    return(ltlyylval.st_bucket->symbol_index);
		  else
		    return(ID);
		}


{number}	{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  ltlyylval.st_bucket = set_number_bucket(ltlyytext);
		  return(NUMBER);
		}


","		|
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
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  return(ltlyytext[0]);
		}


".."		|
"!="		|
"<="		|
">="		|
"&&"		|
"||"		|
"/\\"		|
"\\/"		|
"->"		{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  switch(ltlyytext[0])
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
		    case '/':
		      return(PROP_AND);
		      break;
		    case '\\':
		      return(PROP_OR);
		      break;
		    case '-':
		      return(PROP_IMPL);
		      break;
		  }
		}


"\\_/"		|
"<->"		{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  switch(ltlyytext[0])
		  {
		    case '\\':
		      return(PROP_XOR);
		      break;
		    case '<':
		      return(PROP_BI_IMPL);
		      break;
		  }
		}


{blank}		{
		  record_blank(ltlyyleng);
		}


\t		{
		  record_tab();
		}


\n		{
		  print_newline(FALSE);
		}


{illegal_char}	{
		  print_lexeme(ltlyytext,
			       ltlyyleng);
		  signal_error(ILLEGAL_CHAR,
			       NULL,
			       NULL);
		}


%%


 /***************************************************************
  * 9. Definition of exporting functions.			*
  ***************************************************************/

void		unread_ltl_token(void)
{
	yyless(0);
}


 /***************************************************************
  * 10. Definition of private functions.			*
  ***************************************************************/
