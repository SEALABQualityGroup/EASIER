/****************************************************************/
/*								*/
/*								*/
/*			  list_handler.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	BOOLEAN		check_list_membership(ST_BUCKET *,
					      ST_BUCKET_CELL *,
					      BOOLEAN),
			check_lists_equality(ST_BUCKET_CELL *,
					     ST_BUCKET_CELL *),
			check_unordered_lists_equality(ST_BUCKET_CELL *,
					               ST_BUCKET_CELL *);

extern ST_BUCKET4_CELL	*get_list4_cell(ST_BUCKET *,
				        ST_BUCKET4_CELL *,
				        BOOLEAN);

extern	void		append_to_list_no_dupls(ST_BUCKET *,
						ST_BUCKET_CELL **);

extern	ST_BUCKET_CELL	*append_list(ST_BUCKET_CELL *,
				     ST_BUCKET_CELL *),
			*concat_lists_no_dupls(ST_BUCKET_CELL *,
					       ST_BUCKET_CELL *),
			*remove_list(ST_BUCKET_CELL *,
				     ST_BUCKET_CELL *);
