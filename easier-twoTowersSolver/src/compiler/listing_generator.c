/****************************************************************/
/*                                                              */
/*                                                              */
/*                    listing_generator.c                       */
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module generates at parsing time the .lis file for a spec file, which is a copy of the spec file */
/* augmented with line numbers and possible messages pinpointing errors and warnings. */
/* This module contains the following functions: */
/* - open_listing(): It opens the .lis file. */
/* - print_lexeme(): It prints a lexeme to the .lis file. */
/* - record_blank(): It records the number of blanks scanned after the last scanned and written lexeme. */
/* - record_tab(): It records the position of a tab scanned after the last scanned and written lexeme. */
/* - print_newline(): It prints a newline to the .lis file. */
/* - remove_lexeme(): It simulates the removal of the last lexeme written to the .lis file in order to */
/*		      prevent it from being written twice. This is necessary for some of the lexemes */
/*		      acting as synchronizing tokens. */
/* - signal_error(): It signals the detection of an error by printing the corresponding message to the */
/*		     appropriate position of the .lis file. */
/* - close_listing(): It closes the .lis file after writing into it the number of errors and warnings. */
/* and the following auxiliary function: */
/* - print_blank_tab(): It prints a number of blanks and tabs to the .lis file in order for the next */
/*			lexeme to be correctly alligned. */ 
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/file_handler.h"
#include	"../headers/memory_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

void		open_listing(char *),
		print_lexeme(char *,
			     int),
		record_blank(int),
		record_tab(void),
		print_newline(BOOLEAN),
		remove_lexeme(void),
		signal_error(ERROR_INDEX,
			     char *,
			     char *),
		close_listing(void);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	BOOLEAN		resumed,
				/* flag indicating whether the parsing has been resumed after encountering */
				/* an unexpected character or an illegal definition; */
				/* set by open_listing(), print_lexeme(), and signal_error(); */
				/* used in signal_error() */
			skip_lexeme;
				/* flag indicating whether the next lexeme to be printed must be skipped; */
				/* this is necessary to simulate the removal of the last lexeme written to */
				/* the .lis file in the case it is a synchronizing token; */
				/* set by open_listing(), print_lexeme(), and remove_lexeme(); */
				/* used in print_lexeme() */

PRIVATE	FILE		*listing;
				/* .lis file; */
				/* set by open_listing(); */
				/* used in print_lexeme(), print_newline(), signal_error(), */
				/* close_listing(), and print_blank_tab() */

PRIVATE	int		line_num,
				/* number of scanned lines; */
				/* set by open_listing() and print_newline(); */
				/* used in print_lexeme() and print_newline() */
			curr_line_lexeme_num,
				/* number of scanned lexemes in the current line; */
				/* set by open_listing(), print_lexeme(), and print_newline(); */
				/* used in print_newline() */
			curr_line_char_num,
				/* number of scanned characters in the current line up to the last scanned */
				/* lexeme included; */
				/* set by open_listing(), print_lexeme(), and print_newline(); */
				/* used in print_lexeme(), record_tab(), and signal_error() */
			curr_line_tab_num,
				/* number of scanned tabs in the current line; */
				/* set by open_listing(), record_tab(), and print_newline(); */
				/* used in record_tab() and print_blank_tab() */
			tab_pos[MAX_TAB_NUM],
				/* positions of the scanned tabs in the current line; */
				/* set by record_tab(); */
				/* used in print_blank_tab() */
			curr_part_lexeme_num,
				/* number of scanned lexemes in the current part of the current line; */
				/* set by open_listing(), print_lexeme(), print_newline(), and */
				/* signal_error(); */
				/* used in print_lexeme(), print_newline(), and signal_error() */
			blank_tab_num;
				/* number of scanned blanks and tabs following the last scanned lexeme in */
				/* the current line; */
				/* set by open_listing(), print_lexeme(), record_blank(), record_tab(), */
				/* and print_newline(); */
				/* used in print_lexeme() and record_tab() */

PRIVATE	void		print_blank_tab(int,
					int);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		open_listing(char *spec_path)
{
        listing = new_fopen(spec_path,
			    ".lis",
			    "w");
	resumed = FALSE;
	skip_lexeme = FALSE;
	line_num = 1;
	curr_line_lexeme_num = 0;
	curr_line_char_num = 0;
	curr_line_tab_num = 0;
	curr_part_lexeme_num = 0;
	blank_tab_num = 0;
}


void		print_lexeme(char *lexeme,
			     int  length)
{
	if (!skip_lexeme)
	{
	  if (curr_part_lexeme_num == 0)
	  {
	    fprintf(listing,
		    "(%d)\t",
		    line_num);
	    print_blank_tab(1,
			    curr_line_char_num + blank_tab_num);
	  }
	  else
	    print_blank_tab(curr_line_char_num + 1,
			    curr_line_char_num + blank_tab_num);
	  fprintf(listing,
		  "%s",
		  lexeme);
	  curr_line_lexeme_num++;
	  curr_line_char_num += blank_tab_num + length;
	  curr_part_lexeme_num++;
	  blank_tab_num = 0;
	  resumed = FALSE;
	}
	else
	  skip_lexeme = FALSE;
}


void		record_blank(int length)
{
	blank_tab_num += length;
}


void		record_tab(void)
{
	tab_pos[curr_line_tab_num++] = curr_line_char_num + (++blank_tab_num);
}


void		print_newline(BOOLEAN forced)
{
	if (forced)
	  fprintf(listing,
		  "\n");
	else
	{
	  if (curr_line_lexeme_num == 0)
	    fprintf(listing,
		    "(%d)\n",
		    line_num);
	  else
	    if (curr_part_lexeme_num != 0)
	      fprintf(listing,
		      "\n");
	  line_num++;
	  curr_line_lexeme_num = 0;
	  curr_line_char_num = 0;
	  curr_line_tab_num = 0;
	  curr_part_lexeme_num = 0;
	  blank_tab_num = 0;
	}
}


void		remove_lexeme(void)
{
	skip_lexeme = TRUE;
}


void		signal_error(ERROR_INDEX error_index,
			     char        *id_lexeme1,
			     char        *id_lexeme2)
{
	static	BOOLEAN		unexpected_char_illegal_def	=	FALSE;
	static	char		*error_msg[]			=
	{
		"",
		"^Error: illegal character (ignored)\n",
		"^Error: numeric overflow\n",
		"^Error: numeric underflow\n",
		"^Error: redefined architectural type\n",
		"^Error: redefined architectural element type\n",
		"^Error: redeclared architectural element instance\n",
		"^Error: the architectural element instance %s is being redeclared\n",
		"^Error: redefined behavior\n",
		"^Error: redeclared variable\n",
		"^Error: redeclared record field\n",
		"^Error: redefined property\n",
		"^Error: the property %s is being redefined\n",
		"^Error: redefined measure\n",
		"^Error: the measure %s is being redefined\n",
		"^Error: architectural type redefined as architectural element type\n",
		"^Error: architectural type redefined as architectural element instance\n",
		"^Error: architectural type redefined as behavior\n",
		"^Error: architectural type redefined as variable\n",
		"^Error: architectural type redefined as record field\n",
		"^Error: architectural type redefined as property\n",
		"^Error: architectural type redefined as measure\n",
		"^Error: architectural element type redefined as architectural type\n",
		"^Error: architectural element type redefined as architectural element instance\n",
		"^Error: architectural element type redefined as behavior\n",
		"^Error: architectural element type redefined as variable\n",
		"^Error: architectural element type redefined as record field\n",
		"^Error: architectural element type redefined as property\n",
		"^Error: architectural element type redefined as measure\n",
		"^Error: architectural element instance redeclared as architectural type\n",
		"^Error: architectural element instance redeclared as architectural element type\n",
		"^Error: architectural element instance redeclared as behavior\n",
		"^Error: architectural element instance redeclared as variable\n",
		"^Error: architectural element instance redeclared as record field\n",
		"^Error: architectural element instance redeclared as property\n",
		"^Error: architectural element instance redeclared as measure\n",
		"^Error: behavior redefined as architectural type\n",
		"^Error: behavior redefined as architectural element type\n",
		"^Error: behavior redefined as architectural element instance\n",
		"^Error: behavior redefined as variable\n",
		"^Error: behavior redefined as record field\n",
		"^Error: behavior redefined as property\n",
		"^Error: behavior redefined as measure\n",
		"^Error: action type redefined as architectural type\n",
		"^Error: action type redefined as architectural element type\n",
		"^Error: action type redefined as architectural element instance\n",
		"^Error: action type redefined as behavior\n",
		"^Error: action type redefined as variable\n",
		"^Error: action type redefined as record field\n",
		"^Error: action type redefined as property\n",
		"^Error: action type redefined as measure\n",
		"^Error: variable redeclared as architectural type\n",
		"^Error: variable redeclared as architectural element type\n",
		"^Error: variable redeclared as architectural element instance\n",
		"^Error: variable redeclared as behavior\n",
		"^Error: variable redeclared as record field\n",
		"^Error: variable redeclared as property\n",
		"^Error: variable redeclared as measure\n",
		"^Error: record field redeclared as architectural type\n",
		"^Error: record field redeclared as architectural element type\n",
		"^Error: record field redeclared as architectural element instance\n",
		"^Error: record field redeclared as behavior\n",
		"^Error: record field redeclared as variable\n",
		"^Error: record field redeclared as property\n",
		"^Error: record field redeclared as measure\n",
		"^Error: undefined architectural type\n",
		"^Error: undefined architectural element type\n",
		"^Error: undeclared architectural element instance\n",
		"^Error: the architectural element instance %s has not been declared\n",
		"^Error: undefined action type\n",
		"^Error: the action type %s has not been defined\n",
		"^Error: undeclared variable\n",
		"^Error: the variable %s has not been declared\n",
		"^Error: undeclared record field\n",
		"^Error: reused trace file\n",
		"^Error: the trace file %s has already been used\n",
		"^Error: architectural type used as architectural element type\n",
		"^Error: architectural type used as architectural element instance\n",
		"^Error: architectural type used as behavior\n",
		"^Error: architectural type used as action type\n",
		"^Error: architectural type used as variable\n",
		"^Error: architectural type used as record field\n",
		"^Error: architectural element type used as architectural type\n",
		"^Error: architectural element type used as architectural element instance\n",
		"^Error: architectural element type used as behavior\n",
		"^Error: architectural element type used as action type\n",
		"^Error: architectural element type used as variable\n",
		"^Error: architectural element type used as record field\n",
		"^Error: architectural element instance used as architectural type\n",
		"^Error: architectural element instance used as architectural element type\n",
		"^Error: architectural element instance used as behavior\n",
		"^Error: architectural element instance used as action type\n",
		"^Error: architectural element instance used as variable\n",
		"^Error: architectural element instance used as record field\n",
		"^Error: behavior used as architectural type\n",
		"^Error: behavior used as architectural element type\n",
		"^Error: behavior used as architectural element instance\n",
		"^Error: behavior used as action type\n",
		"^Error: behavior used as variable\n",
		"^Error: behavior used as record field\n",
		"^Error: action type used as architectural type\n",
		"^Error: action type used as architectural element type\n",
		"^Error: action type used as architectural element instance\n",
		"^Error: action type used as behavior\n",
		"^Error: action type used as variable\n",
		"^Error: action type used as record field\n",
		"^Error: variable used as architectural type\n",
		"^Error: variable used as architectural element type\n",
		"^Error: variable used as architectural element instance\n",
		"^Error: variable used as behavior\n",
		"^Error: variable used as action type\n",
		"^Error: variable used as record field\n",
		"^Error: record field used as architectural type\n",
		"^Error: record field used as architectural element type\n",
		"^Error: record field used as architectural element instance\n",
		"^Error: record field used as behavior\n",
		"^Error: record field used as action type\n",
		"^Error: record field used as variable\n",
		"^Error: too many parameters\n",
		"^Error: too few parameters\n",
		"^Error: ill typed assignment\n",
		"^Error: ill typed parameters\n",
		"^Error: ill typed array length\n",
		"^Error: ill typed integer bound\n",
		"^Error: ill typed exponential rate\n",
		"^Error: ill typed priority\n",
		"^Error: ill typed weight\n",
		"^Error: ill typed attachment\n",
		"^Error: the attachment from %s to %s is ill typed\n",
		"^Error: ill typed renaming\n",
		"^Error: the renaming of %s as %s is ill typed\n",
		"^Error: ill typed index expression\n",
		"^Error: ill typed expression\n",
		"^Error: ill typed expression list\n",
		"^Error: ill typed expression array\n",
		"^Error: ill typed reward\n",
		"^Error: ill typed simulation run length\n",
		"^Error: ill typed simulation run number\n",
		"^Error: ill typed observation interval begin\n",
		"^Error: ill typed observation interval end\n",
		"^Error: ill typed observation interval width\n",
		"^Error: the expression for the actual parameter cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the array length cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the integer bound cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the priority cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the index cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the reward cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the simulation run length cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the simulation run number cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the observation interval begin cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the observation interval end cannot contain undeclared constant identifiers\n",
		"^Error: the expression for the observation interval width cannot contain undeclared constant identifiers\n",
		"^Error: the value of the actual constant parameter cannot be random\n",
		"^Error: the value of the array length cannot be random\n",
		"^Error: the value of the integer bound cannot be random\n",
		"^Error: the value of the exponential rate cannot be random\n",
		"^Error: the value of the priority cannot be random\n",
		"^Error: the value of the weight cannot be random\n",
		"^Error: the value of the index cannot be random\n",
		"^Error: the value of the reward cannot be random\n",
		"^Error: the value of the simulation run length cannot be random\n",
		"^Error: the value of the simulation run number cannot be random\n",
		"^Error: the value of the observation interval begin cannot be random\n",
		"^Error: the value of the observation interval end cannot be random\n",
		"^Error: the value of the observation interval width cannot be random\n",
		"^Error: empty integer range\n",
		"^Error: the integer range of %s is empty\n",
		"^Warning: empty index range\n",
		"^Error: the array length is not positive\n",
		"^Error: the array length of %s is not positive\n",
		"^Error: the assigned expression contains a value out of range\n",
		"^Error: the expression assigned to %s contains a value out of range\n",
		"^Error: the assigned expression contains an array of different length\n",
		"^Error: the expression assigned to %s contains an array of different length\n",
		"^Error: the field does not belong to the record\n",
		"^Error: level-inconsistent use of the action type\n",
		"^Error: the actual priority must be an integer number greater than zero\n",
		"^Error: the actual priority %s is not an integer number greater than zero\n",
		"^Error: the actual rate must be a number greater than zero\n",
		"^Error: the actual rate %s is not a number greater than zero\n",
		"^Error: the actual weight must be a number greater than zero\n",
		"^Error: the actual weight %s is not a number greater than zero\n",
		"^Error: the rate of the exponentially timed action must be greater than zero\n",
		"^Error: the priority of the immediate/passive action must be greater than zero\n",
		"^Error: the weight of the immediate/passive action must be greater than zero\n",
		"^Error: the rate of the input action must be passive\n",
		"^Error: the action type does not occur in the behavior\n",
		"^Error: the action type %s does not occur in the behavior\n",
		"^Error: no interactions have been declared (missing void in the previous section)\n",
		"^Error: output action type declared as input interaction\n",
		"^Error: input action type declared as output interaction\n",
		"^Error: input action type declared as input and-interaction\n",
		"^Error: action type declared as interaction of different kinds\n",
		"^Warning: action type already declared as interaction of this kind\n",
		"^Error: no architectural element instances have been declared (indexing misuse)\n",
		"^Error: the action type is not an interaction\n",
		"^Error: the action type %s is not an interaction\n",
		"^Warning: interaction already declared as architectural\n",
		"^Warning: the interaction %s has already been declared as architectural\n",
		"^Error: the architectural interaction cannot occur in an attachment\n",
		"^Error: the architectural interaction %s cannot occur in an attachment\n",
		"^Error: the action type is not an input interaction\n",
		"^Error: the action type %s is not an input interaction\n",
		"^Error: the action type is not an output interaction\n",
		"^Error: the action type %s is not an output interaction\n",
		"^Error: the uni-interaction occurs in several attachments\n",
		"^Error: the uni-interaction %s occurs in several attachments\n",
		"^Error: the attachment cannot involve interactions of the same architectural element instances\n",
		"^Error: the attachment from %s to %s involves interactions of the same architectural element instance\n",
		"^Error: the attachment cannot involve two non uni-interactions\n",
		"^Error: the attachment from %s to %s involves two non-uni-interactions\n",
		"^Error: and-/or-interaction attached to several uni-interactions of the same architectural element instance\n",
		"^Error: the attachment from %s to %s involves an and-/or-interaction attached to several uni-interactions of the same architectural element instance\n",
		"^Error: the attachment cannot involve two nonpassive interactions\n",
		"^Error: the attachment from %s to %s involves two nonpassive interactions\n",
		"^Error: passive and-interaction attached to several nonpassive interactions\n",
		"^Error: the attachment from %s to %s involves a passive and-interaction attached to several nonpassive interactions\n",
		"^Warning: attachment already declared\n",
		"^Warning: the attachment from %s to %s has already been declared\n",
		"^Warning: no iteration indices used in the attachment\n",
		"^Error: no behavioral variations have been declared\n",
		"^Error: the architectural interaction cannot occur in a hiding\n",
		"^Error: the architectural interaction %s cannot occur in a hiding\n",
		"^Warning: hiding already declared\n",
		"^Warning: the action type %s has already been hidden\n",
		"^Error: the architectural interaction cannot occur in a restriction\n",
		"^Error: the architectural %s interaction cannot occur in a restriction\n",
		"^Error: the hidden action type cannot occur in a restriction\n",
		"^Error: the hidden action type %s cannot occur in a restriction\n",
		"^Warning: restriction already declared\n",
		"^Warning: the action type %s has already been restricted\n",
		"^Error: the hidden action type cannot occur in a renaming\n",
		"^Error: the hidden action type %s cannot occur in a renaming\n",
		"^Error: the restricted action type cannot occur in a renaming\n",
		"^Error: the restricted action type %s cannot occur in a renaming\n",
		"^Error: the renaming violates uniqueness\n",
		"^Error: the renaming of %s as %s violates uniqueness\n",
		"^Warning: renaming already declared\n",
		"^Warning: the action type %s has already been renamed as %s\n",
		"^Error: the renaming action type may cause a clash in NuSMV\n",
		"^Error: the renaming action type %s may cause a clash in NuSMV\n",
		"^Error: the iteration index cannot be used twice\n",
		"^Error: illegal section\n",
		"^Error: illegal definition/declaration\n",
		"^Error: unexpected character\n",
		"^Resuming after: illegal section\n",
		"^Resuming after: illegal definition/declaration\n",
		"^Resuming after: unexpected character (the above section is illegal)\n",
		"^Resuming after: unexpected character (the above definition/declaration is illegal)\n",
		"\n\nError: the architectural type identifier %s is not defined",
		"\n\nError: the architectural element type identifier %s is not defined",
		"\n\nError: the architectural element instance identifier %s is not declared",
		"\n\nError: the behavior identifier %s is not defined",
		"\n\nError: the formal parameter identifier %s is not declared",
		"\n\nWarning: the architectural type identifier %s is not used",
		"\n\nWarning: the architectural element type identifier %s is not used",
		"\n\nWarning: the architectural element instance identifier %s is not used",
		"\n\nWarning: the behavior identifier %s is not used",
		"\n\nWarning: the action type identifier %s is not used",
		"\n\nWarning: the formal parameter identifier %s is not used",
		"\n\nWarning: the local variable identifier %s is not used",
		"\n\nWarning: the record field identifier %s is not used",
		"\n\nError: the data type for %s contains an empty integer range",
		"\n\nError: the data type for %s contains an array length not positive",
		"\n\nError: the formal parameter %s is used in an input action",
		"\n\nWarning: the local variable %s is used before occurring in an input action",
		"\n\nWarning: the interaction %s does not occur in any attachment",
		"\n\nWarning: no connectivity between %s and %s",
		"^Error: the reward cannot be attached to a passive action type\n",
		"^Error: the reward cannot be attached to the passive action type %s\n",
		"^Error: the action type has already been attached a reward in the measure\n",
		"^Error: the action type %s has already been attached a reward in the measure %s\n",
		"^Error: the action type on which the simulation clock is based cannot be passive\n",
		"^Error: the action type on which the simulation clock is based cannot be hidden\n",
		"^Error: the action type on which the simulation clock is based cannot be restricted\n",
		"^Error: the length of the simulation run must be positive\n",
		"^Error: the number of simulation runs is out of range\n",
		"^Error: the begin of the observation interval must be nonnegative\n",
		"^Error: the begin %s of the observation interval must be nonnegative\n",
		"^Error: the begin of the observation interval must be less than run length\n",
		"^Error: the begin %s of the observation interval must be less than run length\n",
		"^Error: the end of the observation interval must be positive\n",
		"^Error: the end %s of the observation interval must be positive\n",
		"^Error: the end of the observation interval must be greater than the begin\n",
		"^Error: the end %s of the observation interval must be greater than the begin\n",
		"^Error: the end of the observation interval must be not greater than the run length\n",
		"^Error: the end %s of the observation interval must be not greater than the run length\n",
		"^Error: the width of the observation subintervals must be positive\n",
		"^Error: the width %s of the observation subintervals must be positive\n",
		"^Error: the width of the observation subintervals must be regular\n",
		"^Error: the width %s of the observation subintervals must be regular\n",
		"^Error: the trace expression does not have a pseudo random number generator as outermost operator\n",
		"^Error: the trace expression %s does not have a pseudo random number generator as outermost operator\n",
		"^Error: the trace expression does not occur in the AEmilia spec\n",
		"^Error: the trace expression %s does not occur in the AEmilia spec\n",
		"^Error: the trace expression already has an associated trace file\n",
		"^Error: the trace expression %s already has an associated trace file\n",
		"^Error: the high/low security action type is hidden\n",
		"^Error: the high/low security action type %s is hidden\n",
		"^Error: the high/low security action type is restricted\n",
		"^Error: the high/low security action type %s is restricted\n",
		"^Error: high security action type redeclared as low security\n",
		"^Error: the low security action type %s has previously been declared as high security\n",
		"^Warning: redundant high security declaration\n",
		"^Warning: the action type %s has already been declared as high security\n",
		"^Warning: redundant low security declaration\n",
		"^Warning: the action type %s has already been declared as low security\n"
	};

	switch (error_index)
	{
	  case NO_ERROR:
	    break;
	  case ARCHI_TYPE_ID_NOT_DEF:
	  case AET_ID_NOT_DEF:
	  case AEI_ID_NOT_DECL:
	  case BEHAV_ID_NOT_DEF:
	  case FORMAL_PAR_ID_NOT_DECL:
	  case VAR_WITH_EMPTY_INTEGER_RANGE:
	  case VAR_WITH_ARRAY_LENGTH_NOT_POSITIVE:
	  case FORMAL_PAR_IN_INPUT:
	    fprintf(listing,
		    error_msg[error_index],
		    id_lexeme1);
	    error_num[spec_no]++;
	    resumed = FALSE;
	    break;
	  case ARCHI_TYPE_ID_NOT_USED:
	  case AET_ID_NOT_USED:
	  case AEI_ID_NOT_USED:
	  case BEHAV_ID_NOT_USED:
	  case ACT_TYPE_ID_NOT_USED:
	  case FORMAL_PAR_ID_NOT_USED:
	  case LOCAL_VAR_ID_NOT_USED:
	  case REC_FIELD_ID_NOT_USED:
	  case LOCAL_VAR_BEFORE_INPUT:
	  case INTERACTION_NOT_ATTACHED:
	    fprintf(listing,
		    error_msg[error_index],
		    id_lexeme1);
	    warning_num[spec_no]++;
	    resumed = FALSE;
	    break;
	  case AEI_PAIR_NOT_CONNECTED:
	    fprintf(listing,
		    error_msg[error_index],
		    id_lexeme1,
		    id_lexeme2);
	    warning_num[spec_no]++;
	    resumed = FALSE;
	    break;
	  default:
	    if (((error_index != ILLEGAL_SECTION) &&
		 (error_index != ILLEGAL_DEF_DECL) &&
		 (error_index != UNEXPECTED_CHAR) &&
		 (error_index != RESUME_ILLEGAL_SECTION) &&
	         (error_index != RESUME_ILLEGAL_DEF_DECL) &&
	         (error_index != RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION) &&
	         (error_index != RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL)) ||
	        ((error_index == UNEXPECTED_CHAR) &&
	         !resumed) ||
	        (((error_index == ILLEGAL_SECTION) ||
		  (error_index == ILLEGAL_DEF_DECL)) &&
	         !resumed) ||
	        (((error_index == RESUME_ILLEGAL_SECTION) ||
		  (error_index == RESUME_ILLEGAL_DEF_DECL)) &&
	         unexpected_char_illegal_def &&
		 !resumed) ||
	        (((error_index == RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION) ||
	          (error_index == RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL)) &&
	         unexpected_char_illegal_def &&
		 !resumed))
	    {
	      fprintf(listing,
		      (curr_part_lexeme_num == 0)?
		        "--->\t":
		        "\n--->\t");
	      print_blank_tab(1,
			      curr_line_char_num - 1);
	      switch (error_index)
	      {
		case ONE_AEI_REDECL:
		case ONE_MEASURE_REDEF:
		case ONE_AEI_UNDECL:
		case ONE_ACT_TYPE_UNDEF:
		case ONE_VAR_UNDECL:
		case ONE_TRACE_FILE_REUSED:
		case ONE_EMPTY_INTEGER_RANGE:
		case ONE_ARRAY_LENGTH_NOT_POSITIVE:
		case ONE_ASSIGN_VALUE_OUT_OF_RANGE:
		case ONE_ASSIGN_ARRAY_LENGTH_MISMATCH:
		case ONE_ACTUAL_PRIORITY_NOT_POSITIVE_INT_NUMBER:
		case ONE_ACTUAL_RATE_NOT_POSITIVE_NUMBER:
		case ONE_ACTUAL_WEIGHT_NOT_POSITIVE_NUMBER:
		case ONE_ACT_TYPE_NOT_IN_BEHAVIOR:
		case ONE_ACT_TYPE_NOT_INTERACTION:
		case ONE_INTERACTION_ALREADY_DECLARED_AI:
		case ONE_AI_IN_ATTACHMENT:
		case ONE_ACT_TYPE_NOT_INPUT_INTERACTION:
		case ONE_ACT_TYPE_NOT_OUTPUT_INTERACTION:
		case ONE_UNI_INTERACTION_IN_SEVERAL_ATTACHMENTS:
		case ONE_AI_IN_HIDING:
		case ONE_HIDING_ALREADY_DECLARED:
		case ONE_AI_IN_RESTRICTION:
		case ONE_HIDDEN_ACT_TYPE_IN_RESTRICTION:
		case ONE_RESTRICTION_ALREADY_DECLARED:
		case ONE_HIDDEN_ACT_TYPE_IN_RENAMING:
		case ONE_RESTRICTED_ACT_TYPE_IN_RENAMING:
		case ONE_RENAMING_ACT_TYPE_NUSMV_KEYWORD:
		case ONE_PASSIVE_ACT_TYPE_WITH_REWARD:
		case ONE_INTERVAL_BEGIN_NEGATIVE:
		case ONE_INTERVAL_BEGIN_GE_RUN_LENGTH:
		case ONE_INTERVAL_END_NOT_POSITIVE:
		case ONE_INTERVAL_END_LE_INTERVAL_BEGIN:
		case ONE_INTERVAL_END_GT_RUN_LENGTH:
		case ONE_SUB_INTERVAL_WIDTH_NOT_POSITIVE:
		case ONE_SUB_INTERVAL_WIDTH_NOT_REGULAR:
		case ONE_TRACE_EXPR_NOT_RANDOM:
		case ONE_TRACE_EXPR_NOT_IN_AEMILIA_SPEC:
		case ONE_TRACE_EXPR_ALREADY_WITH_TRACE_FILE:
		case ONE_HIGH_LOW_ACT_TYPE_HIDDEN:
		case ONE_HIGH_LOW_ACT_TYPE_RESTRICTED:
		case ONE_HIGH_ACT_TYPE_BECOMES_LOW:
		case ONE_REDUNDANT_ACT_TYPE_HIGH:
		case ONE_REDUNDANT_ACT_TYPE_LOW:
	          fprintf(listing,
		          error_msg[error_index],
		          id_lexeme1);
		  break;
		case ONE_ILL_TYPED_ATTACHMENT:
		case ONE_ILL_TYPED_RENAMING:
		case ONE_AUTO_ATTACHMENT:
		case ONE_TWO_NON_UNI_ATTACHED:
		case ONE_AND_OR_ATTACHED_TO_SAME_AEI_SEVERAL_TIMES:
		case ONE_TWO_NON_PASSIVE_ATTACHMENT:
		case ONE_TWO_NON_PASSIVE_AND_ATTACHMENT:
		case ONE_ATTACHMENT_ALREADY_DECLARED:
		case ONE_RENAMING_VIOLATING_UNIQUENESS:
		case ONE_RENAMING_ALREADY_DECLARED:
		case ONE_ACT_TYPE_ALREADY_REWARD:
	          fprintf(listing,
		          error_msg[error_index],
		          id_lexeme1,
		          id_lexeme2);
		  break;
		default:
	          fprintf(listing,
		          error_msg[error_index]);
		  break;
	      }
	      curr_part_lexeme_num = 0;
	      switch (error_index)
	      {
		case EMPTY_INDEX_RANGE:
		case ACT_TYPE_ALREADY_DECLARED_INTERACTION:
		case INTERACTION_ALREADY_DECLARED_AI:
		case ONE_INTERACTION_ALREADY_DECLARED_AI:
		case ATTACHMENT_ALREADY_DECLARED:
		case ONE_ATTACHMENT_ALREADY_DECLARED:
		case NO_AEI_INDEXING_USED_IN_ATTACHMENT:
		case HIDING_ALREADY_DECLARED:
		case ONE_HIDING_ALREADY_DECLARED:
		case RESTRICTION_ALREADY_DECLARED:
		case ONE_RESTRICTION_ALREADY_DECLARED:
		case RENAMING_ALREADY_DECLARED:
		case ONE_RENAMING_ALREADY_DECLARED:
		case REDUNDANT_ACT_TYPE_HIGH:
		case ONE_REDUNDANT_ACT_TYPE_HIGH:
		case REDUNDANT_ACT_TYPE_LOW:
		case ONE_REDUNDANT_ACT_TYPE_LOW:
		  warning_num[spec_no]++;
		  break;
		default:
		  if ((error_index != RESUME_ILLEGAL_SECTION) &&
		      (error_index != RESUME_ILLEGAL_DEF_DECL) &&
		      (error_index != RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION) &&
		      (error_index != RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL))
	            error_num[spec_no]++;
		  break;
	      }
	      if ((error_index == ILLEGAL_SECTION) ||
		  (error_index == ILLEGAL_DEF_DECL) ||
		  (error_index == UNEXPECTED_CHAR))
	        unexpected_char_illegal_def = TRUE;
	      if ((error_index == RESUME_ILLEGAL_SECTION) ||
		  (error_index == RESUME_ILLEGAL_DEF_DECL) ||
		  (error_index == RESUME_UNEXPECTED_CHAR_ILLEGAL_SECTION) ||
		  (error_index == RESUME_UNEXPECTED_CHAR_ILLEGAL_DEF_DECL))
	      {
	        unexpected_char_illegal_def = FALSE;
		resumed = TRUE;
	      }
	      else
		resumed = FALSE;
	    }
	}
}


void		close_listing(void)
{
	fprintf(listing,
		"\n\n>>>> %d %s\n>>>> %d %s",
		error_num[spec_no],
		(error_num[spec_no] == 1)?
		  "error":
		  "errors",
		warning_num[spec_no],
		(warning_num[spec_no] == 1)?
		  "warning":
		  "warnings");
	fclose(listing);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		print_blank_tab(int start_pos,
				int end_pos)
{
	int		printed_tab_num,
			curr_pos;

	for (printed_tab_num = 0;
	     ((printed_tab_num < curr_line_tab_num) &&
	      (tab_pos[printed_tab_num] < start_pos));
	     printed_tab_num++);
	for (curr_pos = start_pos;
	     (curr_pos <= end_pos);
	     curr_pos++)
	  if ((printed_tab_num < curr_line_tab_num) &&
	      (curr_pos == tab_pos[printed_tab_num]))
	  {
	    fprintf(listing,
		    "\t");
	    printed_tab_num++;
	  }
	  else
	    fprintf(listing,
		    " ");
}
