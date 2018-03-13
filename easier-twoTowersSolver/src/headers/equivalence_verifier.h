/****************************************************************/
/*								*/
/*								*/
/*		     equivalence_verifier.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	void		open_equiv_check_result(char *),
			generate_weak_functional_global_trans_rel(void),
			generate_weak_markovian_global_trans_rel(void);

extern	BOOLEAN		check_bisim_equiv(void),
			check_global_state_equiv(GST_BUCKET *,
						 GST_BUCKET *);

extern	void		minimize_bisim_equiv(void),
			close_equiv_check_result(BOOLEAN);

extern	ST_BUCKET	*compute_disting_formula(GST_BUCKET *,
						 GST_BUCKET *);

extern	void		print_disting_formula(FILE *,
					      char *,
					      int *,
					      int,
					      BOOLEAN,
					      BOOLEAN *);
