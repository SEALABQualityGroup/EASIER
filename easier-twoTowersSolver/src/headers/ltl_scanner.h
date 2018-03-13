/****************************************************************/
/*								*/
/*								*/
/*	            	  ltl_scanner.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	FILE		*ltlyyin;

extern	char		*ltlyytext;

extern	int		ltlyyleng;


extern	int		ltlyylex(void);

extern	void		unread_ltl_token(void);
