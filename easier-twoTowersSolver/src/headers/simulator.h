/****************************************************************/
/*								*/
/*								*/
/*	            	  simulator.h				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/

extern	ST_BUCKET_CELL	*sim_measure_list,
			*rew_var_list,
			*trace_file_list;

extern	int		sim_run_length,
			sim_run_num,
			sim_run_no,
			assign_list_eval_num;


extern	void		simulate(char *,
				 char *);

extern	long double	gen_uniform_01_num(void),
			gen_c_uniform_num(long double,
					  long double),
			gen_erlang_num(long double,
				       long double),
			gen_gamma_num(long double,
				      long double),
			gen_exponential_num(long double),
			gen_weibull_num(long double,
					long double),
			gen_beta_num(long double,
				     long double),
			gen_normal_num(long double,
				       long double),
			gen_pareto_num(long double),
			gen_b_pareto_num(long double,
					 long double,
					 long double),
			gen_d_uniform_num(long double,
					  long double),
			gen_bernoulli_num(long double,
					  long double,
					  long double),
			gen_binomial_num(long double,
					 long double),
			gen_poisson_num(long double),
			gen_neg_binomial_num(long double,
					     long double),
			gen_geometric_num(long double),
			gen_pascal_num(long double,
				       long double);
