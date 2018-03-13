/****************************************************************
 *                                                              *
 *                                                              *
 *                        file_handler.c			*
 *                                                              *
 *                                          (by Marco Bernardo) *
 ****************************************************************
 * This module handles the files.
 * This module contains the following functions:
 * - new_fopen(): It opens a file, with a new suffix to be added, according to an access mode and checks
 *		  whether the operation has been successful.
 * - new_freopen(): It reopens a file, with a new suffix to be added, according to an access mode and checks
 *		    whether the operation has been successful.
 ****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<stdio.h>
#include	<stdlib.h>
#include	<string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/memory_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

FILE		*new_fopen(char *,
			   char *,
			   char *),
		*new_freopen(char *,
			     char *,
			     char *,
			     FILE *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

FILE		*new_fopen(char *old_file_name,
			   char *new_suffix,
			   char *access_mode)
{
	FILE		*new_file;
	char		*new_file_name;

	if (new_suffix != NULL)
	{
	  new_file_name = alloc_string(strlen(old_file_name) + strlen(new_suffix));
	  sprintf(new_file_name,
		  "%s%s",
		  old_file_name,
		  new_suffix);
	}
	else
	  new_file_name = old_file_name;
	new_file = fopen(new_file_name,
			 access_mode);
	if (new_file == NULL)
	  signal_crash(FILE_CRASH,
		       new_file_name);
	else
	  if (new_suffix != NULL)
	    free(new_file_name);
	return(new_file);
}


FILE		*new_freopen(char *old_file_name,
			     char *new_suffix,
			     char *access_mode,
			     FILE *old_file)
{
	FILE		*new_file;
	char		*new_file_name;

	if (new_suffix != NULL)
	{
	  new_file_name = alloc_string(strlen(old_file_name) + strlen(new_suffix));
	  sprintf(new_file_name,
		  "%s%s",
		  old_file_name,
		  new_suffix);
	}
	else
	  new_file_name = old_file_name;
	new_file = freopen(new_file_name,
			   access_mode,
			   old_file);
	if (new_file == NULL)
	  signal_crash(FILE_CRASH,
		       new_file_name);
	return(new_file);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/
