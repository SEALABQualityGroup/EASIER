/****************************************************************/
/*								*/
/*								*/
/*			 aemilia_compiler.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern  GST_BUCKET_CELL *inv_vanishing_global_state_list[2];

extern  long double     *init_prob_vector[2];

extern	void		open_sem_model(char *),
			generate_aei_state_spaces(void),
			generate_global_state_space(void),
			generate_global_transitions(GST_BUCKET *);

extern	BOOLEAN		check_vanishing(GST_BUCKET *);

extern	void		merge_global_transitions(G_TRANS_CELL *),
			close_sem_model(void);
