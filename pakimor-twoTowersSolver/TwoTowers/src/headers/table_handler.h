/****************************************************************/
/*								*/
/*								*/
/*	            	 table_handler.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	ST_BUCKET	*symbol_table[2][SYT_SIZE];

extern	GST_BUCKET      *global_state_table[2][GST_SIZE];


extern	void		init_hash_tables(void),
			*search_lexeme_table(char *,
					     TABLE_INDEX);

extern	LST_BUCKET	*search_lst_table(char *,
					  ST_BUCKET_CELL *);

extern	GST_BUCKET	*search_gst_table(LST_BUCKET **);

extern	void		share_non_state_space_hash_tables(void);
