/****************************************************************/
/*								*/
/*								*/
/*	                    driver.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	OPTION_INDEX	option_index;

extern  ST_BUCKET	*archi_type[];

extern	int		spec_no,
			table_no,
			error_num[],
			warning_num[];


extern	void		signal_crash(CRASH_INDEX,
				     char *);
