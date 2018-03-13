/****************************************************************/
/*                                                              */
/*                                                              */
/*                      string_handler.c			*/
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module handles strings. */
/* This module contains the following functions: */
/* - dupl_string(): It duplicates a string. */
/* - concat_string(): It concatenates two strings. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/memory_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

char		*dupl_string(char *),
		*concat_string(char *,
			       char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

char            *dupl_string(char *s)
{
        char            *duplicate;

        duplicate = alloc_string(strlen(s));
        strcpy(duplicate,
               s);
        return(duplicate);
}


char		*concat_string(char *s1,
			       char *s2)
{
	char		*s;

	if (s1 == NULL)
	  s = s2;
	else
	  if (s2 == NULL)
	    s = s1;
	  else
	  {
	    s = alloc_string(strlen(s1) + strlen(s2));
	    sprintf(s,
		    "%s%s",
		    s1,
		    s2);
	  }
	return(s);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/
