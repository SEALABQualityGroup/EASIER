/****************************************************************/
/*								*/
/*								*/
/*	            	 aemilia_scanner.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	FILE		*aemiliayyin;

extern	char		*aemiliayytext;

extern	int		aemiliayyleng;


extern	int		aemiliayylex(void);

extern	void		unread_aemilia_token(void);
