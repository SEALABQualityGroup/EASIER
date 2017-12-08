/****************************************************************/
/*								*/
/*								*/
/*	            	  sec_scanner.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	FILE		*secyyin;

extern	char		*secyytext;

extern	int		secyyleng;


extern	int		secyylex(void);

extern	void		unread_sec_token(void);
