/****************************************************************/
/*								*/
/*								*/
/*	              listing_generator.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	void		open_listing(char *),
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
