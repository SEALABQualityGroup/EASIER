/****************************************************************/
/*								*/
/*								*/
/*	            	  rew_scanner.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	FILE		*rewyyin;

extern	char		*rewyytext;

extern	int		rewyyleng;


extern	int		rewyylex(void);

extern	void		unread_rew_token(void);
