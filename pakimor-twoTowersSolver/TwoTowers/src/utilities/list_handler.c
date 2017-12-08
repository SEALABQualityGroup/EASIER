/****************************************************************/
/*                                                              */
/*                                                              */
/*			   list_handler.c			*/
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module handles lists of symbol table bucket cells. */
/* This module contains the following functions: */
/* - check_list_membership(): It checks whether an element is present in a list. */
/* - check_lists_equality(): It checks two lists for equality. */
/* - check_unordered_lists_equality(): It checks two unordered lists of the same length for equality. */
/* - get_list4_cell(): It checks whether an element is present in a list of quadruple cells and gets the */
/*		       quadruple cell in which the element is stored. */
/* - append_to_list_no_dupls(): It appends an element to a list if not present. */
/* - append_list(): It appends a list to the end of another list. */
/* - concat_lists_no_dupls(): It concatenates two lists avoiding duplicates. */
/* - remove_list(): It removes the elements of a list from another list. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include        <string.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/memory_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

BOOLEAN		check_list_membership(ST_BUCKET *,
				      ST_BUCKET_CELL *,
				      BOOLEAN),
		check_lists_equality(ST_BUCKET_CELL *,
				     ST_BUCKET_CELL *),
		check_unordered_lists_equality(ST_BUCKET_CELL *,
					       ST_BUCKET_CELL *);

ST_BUCKET4_CELL *get_list4_cell(ST_BUCKET *,
				ST_BUCKET4_CELL *,
				BOOLEAN);

void		append_to_list_no_dupls(ST_BUCKET *,
					ST_BUCKET_CELL **);

ST_BUCKET_CELL	*append_list(ST_BUCKET_CELL *,
			     ST_BUCKET_CELL *),
		*concat_lists_no_dupls(ST_BUCKET_CELL *,
				       ST_BUCKET_CELL *),
		*remove_list(ST_BUCKET_CELL *,
			     ST_BUCKET_CELL *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

BOOLEAN		check_list_membership(ST_BUCKET      *elem,
			              ST_BUCKET_CELL *list,
				      BOOLEAN        is_record)
{
        BOOLEAN         found;
	ST_BUCKET_CELL	*cell;

        for (cell = list,
	     found = FALSE;
             ((cell != NULL) &&
              !found);
             cell = cell->next_st_bucket_cell)
          found = ((cell->st_bucket == elem) ||
		   (is_record &&
		    !strcmp(strchr(cell->st_bucket->symbol_lexeme,
				   ';'),
			    strchr(elem->symbol_lexeme,
				   ';'))));
        return(found);
}


BOOLEAN		check_lists_equality(ST_BUCKET_CELL *list1,
				     ST_BUCKET_CELL *list2)
{
	BOOLEAN		equal;
	ST_BUCKET_CELL	*cell1,
			*cell2;

	for (cell1 = list1,
	     cell2 = list2,
	     equal = TRUE;
	     ((cell1 != NULL) &&
	      (cell2 != NULL) &&
	      equal);
	     cell1 = cell1->next_st_bucket_cell,
	     cell2 = cell2->next_st_bucket_cell)
	  equal = (cell1->st_bucket == cell2->st_bucket);
	return(equal &&
	       (cell1 == NULL) &&
	       (cell2 == NULL));
}


BOOLEAN		check_unordered_lists_equality(ST_BUCKET_CELL *list1,
					       ST_BUCKET_CELL *list2)
{
	BOOLEAN		equal;
	ST_BUCKET_CELL	*cell;

	for (cell = list1,
	     equal = TRUE;
	     ((cell != NULL) &&
	      equal);
	     cell = cell->next_st_bucket_cell)
	  equal = check_list_membership(cell->st_bucket,
					list2,
					FALSE);
	for (cell = list2;
	     ((cell != NULL) &&
	      equal);
	     cell = cell->next_st_bucket_cell)
	  equal = check_list_membership(cell->st_bucket,
					list1,
					FALSE);
	return(equal);
}


ST_BUCKET4_CELL	*get_list4_cell(ST_BUCKET       *elem,
			        ST_BUCKET4_CELL *list,
				BOOLEAN         is_record)
{
        BOOLEAN         found;
	ST_BUCKET4_CELL	*cell;

        for (cell = list,
	     found = FALSE;
             ((cell != NULL) &&
              !found);
             cell = (!found)?
		      cell->next_st_bucket4_cell:
		      cell)
          found = ((cell->st_bucket1 == elem) ||
		   (is_record &&
		    !strcmp(strchr(cell->st_bucket1->symbol_lexeme,
				   ';'),
			    strchr(elem->symbol_lexeme,
				   ';'))));
        return(cell);
}


void		append_to_list_no_dupls(ST_BUCKET      *elem,
					ST_BUCKET_CELL **list)
{
	BOOLEAN		found;
	ST_BUCKET_CELL	*curr_cell,
			*prev_cell;

	for (curr_cell = prev_cell = *list,
	     found = FALSE;
	     ((curr_cell != NULL) &&
	      !found);
	     prev_cell = curr_cell,
	     curr_cell = curr_cell->next_st_bucket_cell)
	  found = (curr_cell->st_bucket == elem);
	if (!found)
	{
	  if (curr_cell == *list)
	    *list = alloc_st_bucket_cell(elem,
					 NULL);
	  else
	    prev_cell->next_st_bucket_cell = alloc_st_bucket_cell(elem,
								  NULL);
	}
}


ST_BUCKET_CELL	*append_list(ST_BUCKET_CELL *list1,
			     ST_BUCKET_CELL *list2)
{
	ST_BUCKET_CELL	*list,
			*cell;

	if (list1 == NULL)
	  list = list2;
	else
	  if (list2 == NULL)
	    list = list1;
	  else
	  {
	    list = list1;
	    for (cell = list1;
		 (cell->next_st_bucket_cell != NULL);
		 cell = cell->next_st_bucket_cell);
	    cell->next_st_bucket_cell = list2;
	  }
	return(list);
}


ST_BUCKET_CELL	*concat_lists_no_dupls(ST_BUCKET_CELL *list1,
				       ST_BUCKET_CELL *list2)
{
	BOOLEAN		found;
	ST_BUCKET_CELL	*list,
			*cell1,
			*cell2;

	if (list1 == NULL)
	  list = list2;
	else
	  if (list2 == NULL)
	    list = list1;
	  else
	  {
	    list = NULL;
	    for (cell1 = list1;
		 (cell1 != NULL);
		 cell1 = cell1->next_st_bucket_cell)
	      list = alloc_st_bucket_cell(cell1->st_bucket,
					  list);
	    for (cell2 = list2;
		 (cell2 != NULL);
		 cell2 = cell2->next_st_bucket_cell)
	    {
	      for (cell1 = list1,
		   found = FALSE;
		   ((cell1 != NULL) &&
		    !found);
		   cell1 = cell1->next_st_bucket_cell)
		found = (cell2->st_bucket == cell1->st_bucket);
	      if (!found)
		list = alloc_st_bucket_cell(cell2->st_bucket,
					    list);
	    }
	  }
	return(list);
}


ST_BUCKET_CELL	*remove_list(ST_BUCKET_CELL *list1,
			     ST_BUCKET_CELL *list2)
{
	BOOLEAN		found;
	ST_BUCKET_CELL	*list,
			*cell1,
			*cell2;

	if ((list1 == NULL) ||
	    (list2 == NULL))
	  list = list1;
	else
	  for (list = NULL,
	       cell1 = list1;
	       (cell1 != NULL);
	       cell1 = cell1->next_st_bucket_cell)
	  {
	    for (cell2 = list2,
		 found = FALSE;
		 ((cell2 != NULL) &&
		  !found);
		 cell2 = cell2->next_st_bucket_cell)
	      found = (cell1->st_bucket == cell2->st_bucket);
	    if (!found)
	      list = alloc_st_bucket_cell(cell1->st_bucket,
					  list);
	  }
	return(list);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/
