/****************************************************************/
/*								*/
/*								*/
/*	            	symbol_handler.h			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	BOOLEAN		selector_enabled[2],
			poss_aei_index_parsed;

extern	ST_BUCKET	*id_prefix_in_expr,
			*selector[2],
			*unindexed_aei[2],
			*index_expr[2],
			*unindexed_id[2];

extern	int		selector_index;


extern	void		build_prefixed_id(ST_BUCKET *,
					  ST_BUCKET **),
			build_suffixed_id(ST_BUCKET **,
					  ST_BUCKET *),
			build_indexed_id(ST_BUCKET **,
					 ST_BUCKET *),
			build_typed_rec_field_id(EXPR *,
						 ST_BUCKET **),
			store_var_decl(ST_BUCKET *,
				       SYMBOL_INDEX,
				       EXPR *,
				       VP_INDEX),
			init_struct_var(VALUE_CELL **,
					EXPR *,
					int);

extern	ST_BUCKET	*set_par_behav_invoc_bucket(ST_BUCKET *,
						    ST_BUCKET_CELL *),
			*set_action_bucket(ST_BUCKET *,
					   ACTION_INDEX,
					   ST_BUCKET_CELL *,
					   RATE_INDEX,
					   ST_BUCKET *,
					   ST_BUCKET *),
			*set_number_bucket(char *),
			*set_sample_bucket(ST_BUCKET *,
					   ST_BUCKET *,
					   ST_BUCKET *,
					   BOOLEAN),
			*set_expr_bucket(EXPR_OP_INDEX,
					 ST_BUCKET *,
					 ST_BUCKET *,
					 ST_BUCKET *,
					 long double,
					 VALUE_CELL *,
					 BOOLEAN),
			*set_concrete_expr_bucket(ST_BUCKET *),
			*set_concrete_uneval_expr_bucket(ST_BUCKET *,
							 ST_BUCKET4_CELL *,
							 ST_BUCKET4_CELL *,
							 ST_BUCKET *),
			*set_nusmv_expr_bucket(ST_BUCKET *),
			*get_expr_bucket(EXPR_OP_INDEX,
					 ST_BUCKET *,
					 ST_BUCKET *,
					 ST_BUCKET *,
					 long double,
					 VALUE_CELL *,
					 BOOLEAN);

extern	ST_BUCKET_CELL	*rewrite_aet_formal_const_par_list(ST_BUCKET_CELL *,
							   ST_BUCKET *),
			*rewrite_aet_behavior_list(ST_BUCKET_CELL *,
						   ST_BUCKET *),
			*rewrite_aet_behavior_par_list(ST_BUCKET_CELL *,
						       ST_BUCKET *,
						       ST_BUCKET *),
			*rewrite_aet_act_type_list(ST_BUCKET_CELL *,
						   ST_BUCKET *),
			*rewrite_expr_bucket_list(ST_BUCKET_CELL *,
					          ST_BUCKET *);

extern	ST_BUCKET	*rewrite_expr_bucket(ST_BUCKET *,
				             ST_BUCKET *);

extern	void		check_id(ID_CONTEXT_INDEX,
				 ST_BUCKET **,
				 BOOLEAN);

extern	BOOLEAN		check_rew_act_type(ST_BUCKET *,
					   BOOLEAN),
			check_expr_undecl_const_par_free(ST_BUCKET *,
							 ST_BUCKET *,
							 ST_BUCKET *),
			check_expr_randomness_free(ST_BUCKET *),
			check_expr_list_types(ST_BUCKET_CELL *,
		                              ST_BUCKET_CELL *,
			                      BOOLEAN),
			check_expr_types(EXPR *,
			                 EXPR *,
			                 BOOLEAN),
			check_expr_all(ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET *,
				       ST_BUCKET *,
				       ERROR_INDEX,
				       ERROR_INDEX,
				       ERROR_INDEX),
			check_eval_var_lengths_bounds(EXPR *,
						      BOOLEAN,
						      BOOLEAN,
						      char *),
			check_assign_lengths_bounds(EXPR *,
						    EXPR *,
						    BOOLEAN,
						    char *),
			check_var_aet_aei_membership(ST_BUCKET *,
						     SYMBOL_INDEX);

extern	void		handle_concretely_indexed_aei(ST_BUCKET **,
						      EXPR_PARSE_INFO *,
						      int,
						      BOOLEAN,
						      BOOLEAN),
			handle_symbolically_indexed_aei(ST_BUCKET **,
						        EXPR_PARSE_INFO *,
							int,
						        BOOLEAN),
			handle_prefixed_indexed_id(ST_BUCKET *,
						   ST_BUCKET **,
						   int,
						   BOOLEAN,
						   BOOLEAN,
						   ID_CONTEXT_INDEX),
			handle_unprefixed_concretely_indexed_id(ST_BUCKET **,
							        EXPR_PARSE_INFO *,
							        ID_CONTEXT_INDEX),
			handle_unprefixed_symbolically_indexed_id(ST_BUCKET **,
							          EXPR_PARSE_INFO *,
							          int,
							          ID_CONTEXT_INDEX),
			handle_iteration_1(ST_BUCKET **),
			handle_iteration_2(EXPR_PARSE_INFO **);

extern	ST_BUCKET	*handle_iteration_3(ST_BUCKET *,
					    EXPR_PARSE_INFO *,
	    				    EXPR_PARSE_INFO *);

extern	EXPR_PARSE_INFO	*handle_id_in_expr(ST_BUCKET **,
					   ST_BUCKET *,
					   ST_BUCKET_CELL **,
					   BOOLEAN,
					   BOOLEAN);

extern	VALUE_CELL	*transform_list_into_array(VALUE_CELL *,
						   int);

extern	void		encode_local_state_comp(ST_BUCKET *);

extern	ST_BUCKET	*decode_local_state_comp(char *,
						 int *);

extern	void		eval_local_transition_guards(LST_BUCKET **),
			eval_global_transition_guards(G_TRANS_CELL *),
			eval_assign_list(ST_BUCKET_CELL *,
					 int),
			eval_expr(ST_BUCKET *,
				  int),
			assign_expr_eval(EXPR *,
					 EXPR *,
					 ASSIGN_INDEX,
					 char *);
