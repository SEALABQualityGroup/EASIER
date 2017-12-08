/****************************************************************/
/*								*/
/*								*/
/*	            	  sim_scanner.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	FILE		*simyyin;

extern	char		*simyytext;

extern	int		simyyleng;


extern	int		simyylex(void);

extern	void		unread_sim_token(void);
