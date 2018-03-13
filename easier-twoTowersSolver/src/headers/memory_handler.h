/****************************************************************/
/*								*/
/*								*/
/*	            	memory_handler.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	char		*alloc_string(int);

extern	ST_BUCKET_CELL	*alloc_st_bucket_cell(ST_BUCKET *,
					      ST_BUCKET_CELL *);

extern	ST_BUCKET2_CELL	*alloc_st_bucket2_cell(ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET2_CELL *);

extern	ST_BUCKET3_CELL	*alloc_st_bucket3_cell(ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET3_CELL *);

extern	ST_BUCKET4_CELL	*alloc_st_bucket4_cell(ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET *,
					       ST_BUCKET4_CELL *);

extern	ST_BUCKET	*alloc_st_bucket(char *),
			*alloc_dummy_st_bucket(EXPR *);

extern	ARCHI_TYPE	*alloc_archi_type(void);

extern	AET		*alloc_aet(ST_BUCKET_CELL *,
				   ST_BUCKET_CELL *,
				   ST_BUCKET_CELL *);

extern	AEI		*alloc_aei(ST_BUCKET *,
				   ST_BUCKET_CELL *,
				   ST_BUCKET_CELL *,
				   ST_BUCKET_CELL *,
				   BOOLEAN);

extern	BEHAVIOR	*alloc_behavior(ST_BUCKET_CELL *,
					ST_BUCKET_CELL *,
					ST_BUCKET_CELL *,
					BOOLEAN,
					char *);

extern	ACT_TYPE	*alloc_act_type(ACTION_INDEX,
					ST_BUCKET_CELL *,
					ST_BUCKET *);

extern	YB_CELL		*alloc_yb_cell(long double,
				       long double,
				       YB_CELL *);

extern	SAMPLE_CELL	*alloc_sample_cell(SAMPLE *,
					   SAMPLE_CELL *);

extern	SAMPLE		*alloc_sample(ST_BUCKET *);

extern	PAR_BEHAV_INVOC	*alloc_par_behav_invoc(ST_BUCKET *,
					       ST_BUCKET_CELL *);

extern	EXPR		*alloc_expr(EXPR_OP_INDEX,
				    ST_BUCKET *,
				    ST_BUCKET *,
				    ST_BUCKET *,
				    DTT_BUCKET *,
				    BOOLEAN,
				    long double,
				    VALUE_CELL *);

extern	VALUE_CELL	*alloc_value_cell(ST_BUCKET *,
					  long double,
					  VALUE_CELL *,
					  VALUE_CELL *);

extern	ACTION		*alloc_action(ST_BUCKET *,
				      ACTION_INDEX,
				      ST_BUCKET_CELL *,
				      RATE_INDEX,
				      ST_BUCKET *,
				      ST_BUCKET *);

extern	AGGR_G_TRANS	*alloc_aggr_g_trans(PART_BLOCK *,
					    ST_BUCKET_CELL *,
					    AGGR_RATE_CELL *,
			                    int);

extern	PART_BLOCK_CELL *alloc_part_block_cell(PART_BLOCK *,
		                               PART_BLOCK_CELL *);

extern	PART_BLOCK      *alloc_part_block(PART_BLOCK *);

extern	G_STATE_CELL    *alloc_g_state_cell(GST_BUCKET *,
		                            G_STATE_CELL *);

extern	AGGR_RATE_CELL  *alloc_aggr_rate_cell(long double,
					      RATE_INDEX,
		                              AGGR_RATE_CELL *);

extern	INEQUIV_GS_CELL *alloc_inequiv_gs_cell(GST_BUCKET *,
		                               GST_BUCKET *,
					       long double,
					       INEQUIV_GS_CELL *,
					       INEQUIV_GS_CELL *,
					       INEQUIV_GS_CELL *);

extern	REW_MEASURE	*alloc_rew_measure(ST_BUCKET3_CELL *);

extern	SIM_MEASURE	*alloc_sim_measure(MEASURE_INDEX,
					   ST_BUCKET *,
					   ST_BUCKET *,
					   ST_BUCKET *,
					   ST_BUCKET *);

extern	TERM_PARSE_INFO	*alloc_term_parse_info(char *,
					       ST_BUCKET_CELL *,
					       ST_BUCKET_CELL *);

extern	ACT_PARSE_INFO	*alloc_action_parse_info(ST_BUCKET *,
					         ST_BUCKET_CELL *);

extern	EXPR_PARSE_INFO	*alloc_expr_parse_info(ST_BUCKET *,
					       ST_BUCKET_CELL *);

extern	LAR_PARSE_INFO	*alloc_lar_parse_info(VALUE_CELL *,
					      int,
					      ST_BUCKET_CELL *);

extern	DTT_BUCKET	*alloc_dtt_bucket(char *);

extern	CDT_BUCKET	*alloc_cdt_bucket(char *);

extern	LST_BUCKET_CELL *alloc_lst_bucket_cell(LST_BUCKET *,
		                               LST_BUCKET_CELL *);

extern	LST_BUCKET	*alloc_lst_bucket(char *,
					  ST_BUCKET_CELL *);

extern	L_TRANS_CELL	*alloc_l_trans_cell(ST_BUCKET *,
					    VP_INFO *,
					    REWARD_INFO,
					    LST_BUCKET *,
					    INTERACT_INDEX,
					    L_TRANS_CELL *);

extern	L_ACT_TYPE_CELL *alloc_l_act_type_cell(ST_BUCKET *,
					       ST_BUCKET *,
					       int,
					       int,
					       BOOLEAN,
		                               L_ACT_TYPE_CELL *);

extern	L_NONDET_CELL   *alloc_l_nondet_cell(LST_BUCKET *,
					     LST_BUCKET_CELL *,
		                             L_NONDET_CELL *);

extern	GST_BUCKET_CELL	*alloc_gst_bucket_cell(GST_BUCKET *,
					       GST_BUCKET_CELL *);

extern	GST_BUCKT2_CELL *alloc_gst_bucket2_cell(GST_BUCKET *,
		                                GST_BUCKET *,
						GST_BUCKT2_CELL *);

extern	GST_BUCKET	*alloc_gst_bucket(LST_BUCKET **);

extern	G_TRANS_CELL	*alloc_g_trans_cell(ST_BUCKET *,
					    VP_INFO *,
					    REWARD_INFO,
					    GST_BUCKET *,
					    G_TRANS_CELL *);

extern	VP_INFO		*alloc_vp_info(ST_BUCKET *,
				       ST_BUCKET_CELL *,
				       int);

extern	RANDOM_CELL	*alloc_random_cell(G_TRANS_CELL *,
					   long double,
					   RANDOM_CELL *);

extern	ST_BUCKET_CELL	*duplicate_st_bucket_list(ST_BUCKET_CELL *);

extern	REWARD_INFO	duplicate_reward_info(REWARD_INFO);

extern	VALUE_CELL      *duplicate_struct_expr(VALUE_CELL *,
		                               char),
			*duplicate_record_expr(VALUE_CELL *);

extern	void		free_st_bucket_list(ST_BUCKET_CELL *),
			free_st_bucket3_list(ST_BUCKET3_CELL *),
			free_st_bucket4_list(ST_BUCKET4_CELL *),
			free_value_list(VALUE_CELL *,
					EXPR *,
					int),
			free_l_trans_cell(L_TRANS_CELL *),
			free_gst_bucket_list(GST_BUCKET_CELL *),
			free_g_trans_list(G_TRANS_CELL *),
			free_g_trans_cell(G_TRANS_CELL *),
			*new_calloc(int,
				    unsigned);
