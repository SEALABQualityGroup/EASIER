/****************************************************************/
/*                                                              */
/*                                                              */
/*                      number_handler.c			*/
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module handles numbers. */
/* This module contains the following functions: */
/* - check_int(): It checks whether a number is integer. */
/* - compute_digit_num(): It computes the number of digits in the integral part of a positive number. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<math.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

BOOLEAN		check_int(long double);

int		compute_digit_num(double);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

BOOLEAN		check_int(long double number)
{
	double		int_part;

	return(modf(number,
		    &int_part) == 0.0);
}


int		compute_digit_num(double number)
{
	return((fabs(number) <= 1.0)?
		 1:
		 ((int)log10(fabs(number)) + 1));
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/
