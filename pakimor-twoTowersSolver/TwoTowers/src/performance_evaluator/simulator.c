/****************************************************************/
/*								*/
/*								*/
/*		         simulator.c				*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module analyzes the performance properties of a .empa spec file via possibly trace driven */
/* simulation based on the method of independent replications. */
/* This module contains the following functions: */
/* - simulate(): It perform the simulative analysis, where the termination condition, the performance */
/*		 measures, and possible trace file names are expressed in a .sim spec file. */
/* - gen_uniform_01_num(): It implements a multiplicative linear congruential generator with multiplier */
/*			   7^5 and modulus 2^31 - 1, using real arithmetic according to Schrage's method. */
/*			   The initial value of the seed is 1. The generated numbers are uniformly */
/*			   distributed between 0 and 1. */
/* - gen_c_uniform_num(): It generates random numbers following a continuous uniform distribution. */
/* - gen_erlang_num(): It generates random numbers following an Erlang distribution. */
/* - gen_gamma_num(): It generates random numbers following a gamma distribution. */
/* - gen_exponential_num(): It generates random numbers following an exponential distribution. */
/* - gen_weibull_num(): It generates random numbers following a Weibull distribution. */
/* - gen_beta_num(): It generates random numbers following a beta distribution. */
/* - gen_normal_num(): It generates random numbers following a normal distribution. */
/* - gen_pareto_num(): It generates random numbers following a Pareto distribution. */
/* - gen_b_pareto_num(): It generates random numbers following a bounded Pareto distribution. */
/* - gen_d_uniform_num(): It generates random numbers following a discrete uniform distribution. */
/* - gen_bernoulli_num(): It generates random numbers following a Bernoulli distribution. */
/* - gen_binomial_num(): It generates random numbers following a binomial distribution. */
/* - gen_poisson_num(): It generates random numbers following a Poisson distribution. */
/* - gen_neg_binomial_num(): It generates random numbers following a negative binomial distribution. */
/* - gen_geometric_num(): It generates random numbers following a geometric distribution. */
/* - gen_pascal_num(): It generates random numbers following a Pascal distribution. */
/* and the following auxiliary functions: */
/* - open_trace_files(): It opens the .trc spec files. */
/* - choose_next_global_state(): It randomly chooses the next global state among the derivative global */
/*				 states of the highest priority global transitions with true guards of the */
/*				 current global state. */
/* - choose_exp_timed_global_trans(): It randomly chooses one global transition among the exponentially */
/*				      timed global transitions of the current global state according to */
/*				      the race policy. */
/* - choose_immediate_global_trans(): It randomly chooses one global transition among the highest priority */
/*				      immediate transitions of the current global state according to the */
/*				      preselection policy. */
/* - update_sample_clock(): It updates the samples for the measures and the simulation clock. */
/* - reset_sample(): It resets the samples occurring in a given measure distribution. */
/* - reset_rew_var_list(): It resets the variables occurring in the reward expressions at the end of a */
/*			   simulation run. */
/* - infer_measure(): It infers a given performance measure together with the corresponding confidence */
/*		      intervals from the related samples. */
/* - compute_sample_mean_var(): It computes the sample mean and variance for a measure. */
/* - close_trace_files(): It closes the .trc spec files. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<float.h>
#include	<math.h>
#include	<stdlib.h>
#include	<string.h>
#include	<unistd.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/symbol_handler.h"
#include	"../headers/aemilia_compiler.h"

#include	"../headers/file_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/number_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

ST_BUCKET_CELL	*sim_measure_list,
			/* list of performance measures to be estimated; */
			/* set by parse_sim() and append_absent_sim_measure() of sim-parser.y; */
			/* used in append_absent_sim_measure() of sim-parser.y and in */
			/* update_sample_clock() and infer_measure() of simulator.c */
		*rew_var_list,
			/* list of variables occurring in reward expressions associated with actions */
			/* (value_passing = SYMBOLIC_VP); */
			/* set in sim-parser.y; */
			/* used in reset_rew_var_list() of simulator.c */
		*trace_file_list;
			/* list of .trc spec files; */
			/* set in sim-parser.y; */
			/* used in open_trace_files() and close_trace_files() of simulator.c */

int		sim_run_length,
			/* length of a simulation run; */
			/* set in sim-parser.y; */
			/* used in handle_sim_measure1() and handle_sim_measure2() of sim-parser.y and in */
			/* simulate() of simulator.c */
		sim_run_num,
			/* number of simulation runs; */
			/* set by sim-parser.y; */
			/* used in simulate(), infer_measure(), and compute_sample_mean_var() of */
			/* simulator.c */
		sim_run_no,
			/* serial number of the current simulation run; */
			/* set by simulate(); */
			/* used in eval_expr() and update_sample_clock() */
		assign_list_eval_num;
			/* number of the current assignment list evaluation within the current */
			/* simulation run */
			/* (value_passing = SYMBOLIC_VP) */
			/* set by simulate() and eval_assign_list(); */
			/* used in eval_expr() */


void		simulate(char *,
			 char *);

long double	gen_uniform_01_num(void),
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


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	FILE		*est_file;
				/* .est file containing the simulation results; */
				/* set by simulate(); */
				/* used in infer_measure() */

PRIVATE	int 		sim_clock;
				/* simulation clock; */
				/* set and used in simulate() and update_sample_clock() */


PRIVATE	void		open_trace_files(char *);

PRIVATE	GST_BUCKET	*choose_next_global_state(G_TRANS_CELL *);

PRIVATE	G_TRANS_CELL	*choose_exp_timed_global_trans(G_TRANS_CELL *),
			*choose_immediate_global_trans(G_TRANS_CELL *,
						       long double);

PRIVATE	void		update_sample_clock(G_TRANS_CELL *),
			reset_sample(ST_BUCKET *,
				     int),
			reset_rew_var_list(void),
			infer_measure(void),
			compute_sample_mean_var(ST_BUCKET *,
						long double *,
						long double *,
						int,
						long double *),
			close_trace_files(void);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		simulate(char *aem_path,
			 char *sim_path)
{
	GST_BUCKET	*global_state;

	/* open the files concerned with the simulation */
	est_file = new_fopen(aem_path,
			     ".est",
			     "w");
	open_trace_files(sim_path);

	/* perform the simulation runs */
	for (sim_run_no = 0,
	     assign_list_eval_num = 0;
	     (sim_run_no < sim_run_num);
	     sim_run_no++,
	     assign_list_eval_num = 0)
	{
	  /* start from the initial global state after evaluating the initial assignments */
	  if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	    eval_assign_list(archi_type[spec_no]->info.archi_type->init_assign_list,
			     0);
	  global_state = archi_type[spec_no]->info.archi_type->init_global_state;
	  /* perform a simulation run */
	  for (sim_clock = 0;
	       (sim_clock < sim_run_length);
	       )
	  {
	    /* generate the global transitions of the global state after evaluating the guards of the */
	    /* local transitions of the local states constituting the global state */
	    if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	      eval_local_transition_guards(global_state->local_state_vector);
	    generate_global_transitions(global_state);
	    /* evaluate the guards of the global transitions, choose the global transition to be executed */
	    /* among those with highest priority and true guard, evaluate the related assignments, and */
	    /* continue the simulation run with the related derivative global state */
	    if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	      eval_global_transition_guards(global_state->global_trans_list);
	    global_state = choose_next_global_state(global_state->global_trans_list);
	  }
	  /* reset the variables involved in the samples before the next simulation run starts */
	  if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	    reset_rew_var_list();
	}

	/* infer the performance measures from the samples collected during the simulation runs */
	infer_measure();

	/* close the files concerned with the simulation */
	close_trace_files();
	fclose(est_file);
}


long double	gen_uniform_01_num(void)
{
		long double	new_seed,
				seed_div_mo_div_mu,
				seed_mod_mo_div_mu;
	static	long double	seed			=	INIT_SEED;

	seed_div_mo_div_mu = (long double)floor(seed / LCG_MO_DIV_MU);
	seed_mod_mo_div_mu = seed -
			     LCG_MO_DIV_MU * seed_div_mo_div_mu;
	new_seed = LCG_MULTIPLIER * seed_mod_mo_div_mu -
		   LCG_MO_MOD_MU * seed_div_mo_div_mu;
	seed = new_seed +
	       ((new_seed > 0.0L)?
		  0.0L:
		  LCG_MODULUS);
	return(seed / LCG_MODULUS);
}


long double	gen_c_uniform_num(long double lower,
				  long double upper)
{
	return(lower + (upper - lower) * gen_uniform_01_num());
}


long double	gen_erlang_num(long double rate,
			       long double stage_num)
{
	int		stage_index;
	long double	product;

	for (stage_index = 0,
	     product = 1.0L;
	     (stage_index < (int)stage_num);
	     stage_index++)
	  product *= gen_uniform_01_num();
	return((-1.0L / rate) * (long double)log(product));
}


long double	gen_gamma_num(long double rate,
			      long double stage_num)
{
	int		int_stage_num,
			stage_index;
	long double	fract_stage_num,
			product1,
			product2;

	int_stage_num = (int)floor(stage_num);
	fract_stage_num = stage_num - (long double)int_stage_num;
	if (int_stage_num >= 1)
	{
	  for (stage_index = 0,
	       product1 = 1.0L;
	       (stage_index < (int)stage_num);
	       stage_index++)
	    product1 *= gen_uniform_01_num();
	  product1 = (-1.0L / rate) * (long double)log(product1);
	}
	else
	  product1 = 0.0L;
	if (fract_stage_num > 0.0L)
	  product2 = (-1.0L / rate) *
		     gen_beta_num(fract_stage_num,
				  1.0L - fract_stage_num) *
		     gen_exponential_num(1.0L);
	else
	  product2 = 0.0L;
	return(product1 + product2);
}


long double	gen_exponential_num(long double rate)
{
	return((-1.0L / rate) * (long double)log(gen_uniform_01_num()));
}


long double	gen_weibull_num(long double rate,
				long double shape)
{
        return((1.0L / rate) * (long double)pow(-log(gen_uniform_01_num()),
						1.0L / shape));
}


long double	gen_beta_num(long double shape1,
			     long double shape2)
{
	long double	power1,
			power2;

	do
	{
	  power1 = (long double)pow(gen_uniform_01_num(),
				    1.0L / shape1);
	  power2 = (long double)pow(gen_uniform_01_num(),
				    1.0L / shape2);
	}
	while (power1 + power2 > 1.0L);
	return(power1 / (power1 + power2));
}


long double	gen_normal_num(long double mean,
			       long double standard_dev)
{
	long double	v1,
			v2,
			s,
			num;

	do
	{
	  v1 = 2.0L * gen_uniform_01_num() - 1.0L;
	  v2 = 2.0L * gen_uniform_01_num() - 1.0L;
	}
	while (((s = (v1 * v1) + (v2 * v2))
		 >= 1.0L) ||
	       ((num = v1 * (long double)sqrt(-2.0L * (long double)log(s) / s) * standard_dev + mean)
		 <= 0.0L));
	return(num);
}


long double	gen_pareto_num(long double shape)
{
	return((long double)pow(1.0L / gen_uniform_01_num(),
				1.0L / shape));
}


long double	gen_b_pareto_num(long double shape,
                                 long double lower,
                                 long double upper)
{
        long double	u01;

        u01 = gen_uniform_01_num();
        return(lower / (long double)pow(1.0L - u01 + u01 * (long double)pow(lower / upper,
									    shape),
					1.0L / shape));
}


long double	gen_d_uniform_num(long double lower,
				  long double upper)
{
	return((long double)floor(lower + (upper - lower + 1.0L) * gen_uniform_01_num()));
}


long double	gen_bernoulli_num(long double value1,
				  long double value2,
				  long double prob)
{
	return((gen_uniform_01_num() <= prob)?
		 value1:
		 value2);
}


long double	gen_binomial_num(long double prob,
				 long double trial_num)
{
	int		success_num,
			trial_index;

	for (trial_index = 0,
	     success_num = 0;
	     (trial_index < (int)trial_num);
	     trial_index++)
	  success_num += (int)gen_bernoulli_num(1.0L,
						0.0L,
						prob);
	return((long double)success_num);
}


long double	gen_poisson_num(long double mean)
{
	int		arrival_num;
	long double	product;

	for (arrival_num = 0,
	     product = 1.0L;
	     (product >= (long double)exp(mean));
	     arrival_num++,
	     product *= gen_uniform_01_num());
	return((long double)arrival_num);
}


long double	gen_neg_binomial_num(long double prob,
				     long double success_num)
{
	int		sum,
			success_index;

	for (success_index = 0,
	     sum = 0;
	     (success_index < (int)success_num);
	     success_index++,
	     sum += (int)gen_geometric_num(prob));
	return((long double)(sum - success_num));
}


long double	gen_geometric_num(long double prob)
{
	return((long double)ceil((long double)log(gen_uniform_01_num()) / (long double)log(1.0L - prob)));
}


long double	gen_pascal_num(long double prob,
			       long double success_num)
{
	int		sum,
			success_index;

	for (success_index = 0,
	     sum = 0;
	     (success_index < (int)success_num);
	     success_index++,
	     sum += (int)gen_geometric_num(prob));
	return((long double)sum);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		open_trace_files(char *sim_path)
{
	ST_BUCKET_CELL	*trace_file_cell;
	char            *last_slash,
                        *sim_dir;
	int		length;

	/* change the current directory to the one containing the .sim file (necessary because the path
	 * names of the .trc files specified in the .sim file are relative to the directory containing the
	 * .sim file itself) */
        last_slash = strrchr(sim_path,
                             '/');
        if ((last_slash != NULL) &&
	    (trace_file_list != NULL))
        {
          length = last_slash - sim_path + 1;
          sim_dir = alloc_string(length);
          strncpy(sim_dir,
                  sim_path,
                  length);
          sim_dir[length] = EOS;
          chdir(sim_dir);
          free(sim_dir);
        }

	/* open all the .trc files specified in the .sim file */
	for (trace_file_cell = trace_file_list;
	     (trace_file_cell != NULL);
	     trace_file_cell = trace_file_cell->next_st_bucket_cell)
	  trace_file_cell->st_bucket->info.trace_file = new_fopen(trace_file_cell->st_bucket->symbol_lexeme,
								  NULL,
								  "r");
}


GST_BUCKET	*choose_next_global_state(G_TRANS_CELL *global_trans_list)
{
	GST_BUCKET	*next_global_state;
	G_TRANS_CELL	*global_trans_cell,
			*chosen_global_trans_cell;
	long double	max_priority;

	for (global_trans_cell = global_trans_list,
	     max_priority = -1.0L;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  if ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
	      global_trans_cell->value_passing_info->guard->info.expr->value)
	  {
	    if (global_trans_cell->action->info.action->rate_index == PASSIVE)
	      signal_crash(OPEN_STATE_SIM_CRASH,
			   NULL);
	    else
	      if (global_trans_cell->action->info.action->priority->info.expr->value > max_priority)
		max_priority = global_trans_cell->action->info.action->priority->info.expr->value;
	  }
	if (max_priority == -1.0L)
	{
	  next_global_state = NULL;
	  signal_crash(DEADLOCK_STATE_SIM_CRASH,
		       NULL);
	}
	else
	{
	  chosen_global_trans_cell = (max_priority == 0.0L)?
				       choose_exp_timed_global_trans(global_trans_list):
				       choose_immediate_global_trans(global_trans_list,
								     max_priority);
	  update_sample_clock(chosen_global_trans_cell);
	  if (archi_type[spec_no]->info.archi_type->value_passing == (char)SYMBOLIC_VP)
	    eval_assign_list(chosen_global_trans_cell->value_passing_info->assign_list,
			     chosen_global_trans_cell->value_passing_info->input_output_assign_num);
	  next_global_state = chosen_global_trans_cell->der_global_state;
	}
	return(next_global_state);
}


G_TRANS_CELL	*choose_exp_timed_global_trans(G_TRANS_CELL *global_trans_list)
{
		G_TRANS_CELL	*global_trans_cell;
		RANDOM_CELL	*duration_cell,
				*prev_duration_cell,
				*min_duration_cell;
	static	RANDOM_CELL	*duration_list			=	NULL;
		int		duration_list_length,
				i;
	static	int		max_duration_list_length	=	0;

	/* select the executable moves and generate a duration for each of them */
	for (global_trans_cell = global_trans_list,
	     duration_list_length = 0,
	     duration_cell = prev_duration_cell = duration_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	{
	  if ((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
	      global_trans_cell->value_passing_info->guard->info.expr->value)
	  {
	    duration_list_length++;
	    if (duration_list_length <= max_duration_list_length)
	    {
	      duration_cell->global_transition = global_trans_cell;
	      duration_cell->duration =
	        gen_exponential_num(global_trans_cell->action->info.action->rate->info.expr->value);
	      prev_duration_cell = duration_cell;
	      duration_cell = duration_cell->next_random_cell;
	    }
	    else
	      if (duration_cell == duration_list)
	        prev_duration_cell = duration_list =
		  alloc_random_cell(global_trans_cell,
				    gen_exponential_num(
				      global_trans_cell->action->info.action->rate->info.expr->value),
				    NULL);
	      else
	        prev_duration_cell = prev_duration_cell->next_random_cell =
		  alloc_random_cell(global_trans_cell,
				    gen_exponential_num(
				      global_trans_cell->action->info.action->rate->info.expr->value),
				    NULL);
	  }
	}
	if (duration_list_length > max_duration_list_length)
	  max_duration_list_length = duration_list_length;

	/* choose the move to be executed according to the race policy */
	for (min_duration_cell = duration_list,
	     duration_cell = duration_list->next_random_cell,
	     i = 2;
	     (i <= duration_list_length);
	     duration_cell = duration_cell->next_random_cell,
	     i++)
	  if (duration_cell->duration < min_duration_cell->duration)
	    min_duration_cell = duration_cell;
	return(min_duration_cell->global_transition);
}


G_TRANS_CELL	*choose_immediate_global_trans(G_TRANS_CELL *global_trans_list,
					       long double  priority)
{
		BOOLEAN		found;
		G_TRANS_CELL	*global_trans_cell;
		RANDOM_CELL	*weight_cell,
				*prev_weight_cell;
	static	RANDOM_CELL	*weight_list		=	NULL;
		int		weight_list_length;
	static	int		max_weight_list_length	=	0;
		long double	weight,
				random_num;

	/* select the executable moves */
	for (global_trans_cell = global_trans_list,
	     weight = 0.0L,
	     weight_list_length = 0,
	     weight_cell = prev_weight_cell = weight_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	{
	  if (((archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP) ||
	       global_trans_cell->value_passing_info->guard->info.expr->value) &&
	      (global_trans_cell->action->info.action->priority->info.expr->value == priority))
	  {
	    weight += global_trans_cell->action->info.action->rate->info.expr->value;
	    weight_list_length++;
	    if (weight_list_length <= max_weight_list_length)
	    {
	      weight_cell->global_transition = global_trans_cell;
	      weight_cell->duration = 0.0L;
	      prev_weight_cell = weight_cell;
	      weight_cell = weight_cell->next_random_cell;
	    }
	    else
	      if (weight_cell == weight_list)
	        prev_weight_cell = weight_list = alloc_random_cell(global_trans_cell,
								   0.0L,
								   NULL);
	      else
	        prev_weight_cell = prev_weight_cell->next_random_cell = alloc_random_cell(global_trans_cell,
											  0.0L,
											  NULL);
	  }
	}
	if (weight_list_length > max_weight_list_length)
	  max_weight_list_length = weight_list_length;

	/* choose the move to be executed according to the preselection policy */
	random_num = gen_uniform_01_num() * weight;
	for (weight_cell = weight_list,
	     weight = 0.0L,
	     found = FALSE;
	     (!found);
	     )
	{
	  weight += weight_cell->global_transition->action->info.action->rate->info.expr->value;
	  if (weight >= random_num)
	    found = TRUE;
	  else
	    weight_cell = weight_cell->next_random_cell;
	}
	return(weight_cell->global_transition);
}


void		update_sample_clock(G_TRANS_CELL *global_trans_cell)
{
	SAMPLE_CELL	*sample_cell;
	SIM_MEASURE	*sim_measure;
	ST_BUCKET	*measure_id,
			*reward_expr;
	ST_BUCKET_CELL	*measure;
	long double	*distr_measure_ptr;

	/* update the samples for the currently executed action type */
	for (sample_cell = global_trans_cell->reward_info.sample_list;
	     (sample_cell != NULL);
	     sample_cell = sample_cell->next_sample_cell)
	{
	  measure_id = sample_cell->sample->reward_expr->info.expr->opn1;
	  if ((measure_id->info.sim_measure->interval_begin->info.expr->value <= sim_clock) &&
	      (measure_id->info.sim_measure->interval_end->info.expr->value > sim_clock))
	  {
	    reward_expr = sample_cell->sample->reward_expr->info.expr->opn3;
	    eval_expr(reward_expr,
		      0);
	    sample_cell->sample->cumul_reward[sim_run_no] += reward_expr->info.expr->value;
	    (sample_cell->sample->obs_num[sim_run_no])++;
	  }
	}

	/* update the simulation clock and the observations for the distribution measures */
	if (global_trans_cell->action->info.action->type->info.act_type->sim_clock_act_type)
	{
	  sim_clock++;
	  for (measure = sim_measure_list;
	       (measure != NULL);
	       measure = measure->next_st_bucket_cell)
	  {
	    sim_measure = measure->st_bucket->info.sim_measure;
	    if ((sim_measure->measure_index == M_DISTRIBUTION) &&
		(((int)(sim_clock - sim_measure->interval_begin->info.expr->value) %
		  (int)(sim_measure->sub_interval_width->info.expr->value)) == 0))
	    {
	      eval_expr(sim_measure->measure_expr,
			sim_run_no);
	      distr_measure_ptr = sim_measure->value[sim_run_no] +
		    		  ((int)((sim_clock - sim_measure->interval_begin->info.expr->value) /
					 sim_measure->sub_interval_width->info.expr->value) - 1);
	      *distr_measure_ptr = sim_measure->measure_expr->info.expr->value;
	      reset_sample(sim_measure->measure_expr,
			   sim_run_no);
	    }
	  }
	}
}


void		reset_sample(ST_BUCKET *measure_expr,
			     int       run)
{
	EXPR		*expr;
	VALUE_CELL	*list_elem,
			**array_elem,
			*record_field;
	int		array_length,
			array_index;

	expr = measure_expr->info.expr;
	switch (expr->op_index)
	{
	  case SAMPLE_OP:
	    expr->sample->cumul_reward[run] = 0.0L;
	    expr->sample->obs_num[run] = 0;
	    break;
	  case ABS_OP:
	  case CEIL_OP:
	  case FLOOR_OP:
	  case EPOWER_OP:
	  case LOGE_OP:
	  case LOG10_OP:
	  case SQRT_OP:
	  case SIN_OP:
	  case COS_OP:
	  case EXPONENTIAL_OP:
	  case PARETO_OP:
	  case POISSON_OP:
	  case GEOMETRIC_OP:
	  case NOT_OP:
	  case FIRST_OP:
	  case TAIL_OP:
	  case LENGTH_OP:
	  case GET_OP:
	    reset_sample((expr->op_index != GET_OP)?
			   expr->opn1:
			   expr->opn2,
			 run);
	    break;
	  case PLUS_OP:
	  case MINUS_OP:
	  case TIMES_OP:
	  case DIV_OP:
	  case MOD_OP:
	  case MIN_OP:
	  case MAX_OP:
	  case POWER_OP:
	  case C_UNIFORM_OP:
	  case ERLANG_OP:
	  case GAMMA_OP:
	  case WEIBULL_OP:
	  case BETA_OP:
	  case NORMAL_OP:
	  case D_UNIFORM_OP:
	  case BINOMIAL_OP:
	  case NEG_BINOMIAL_OP:
	  case PASCAL_OP:
	  case AND_OP:
	  case OR_OP:
	  case EQ_OP:
	  case NE_OP:
	  case LT_OP:
	  case LE_OP:
	  case GT_OP:
	  case GE_OP:
	  case CONCAT_OP:
	  case INSERT_OP:
	  case REMOVE_OP:
	  case READ_OP:
	  case PUT_OP:
	    reset_sample((expr->op_index != PUT_OP)?
			   expr->opn1:
			   expr->opn2,
			 run);
	    reset_sample((expr->op_index != PUT_OP)?
			   expr->opn2:
			   expr->opn3,
			 run);
	    break;
	  case B_PARETO_OP:
	  case BERNOULLI_OP:
	  case WRITE_OP:
	    reset_sample(expr->opn1,
			 run);
	    reset_sample(expr->opn2,
			 run);
	    reset_sample(expr->opn3,
			 run);
	    break;
	  case LIST_CONS_OP:
	    for (list_elem = (expr->struct_value->value != (long double)DBL_MAX)?
			       expr->struct_value:
			       NULL;
		 (list_elem != NULL);
		 list_elem = list_elem->next_value_cell)
	      reset_sample(list_elem->value_bucket,
			   run);
	    break;
	  case ARRAY_CONS_OP:
	    for (array_elem = (VALUE_CELL **)(expr->struct_value->struct_value),
		 array_index = 0,
		 array_length = (int)(expr->value);
		 (array_index < array_length);
		 array_elem++,
		 array_index++)
	      reset_sample((*array_elem)->value_bucket,
			   run);
	    break;
	  case RECORD_CONS_OP:
	    for (record_field = expr->struct_value;
		 (record_field != NULL);
		 record_field = record_field->next_value_cell)
	      reset_sample(record_field->value_bucket,
			   run);
	    break;
	  default:
	    break;
	}
}


void		reset_rew_var_list(void)
{
	ST_BUCKET_CELL	*rew_var_cell;

	for (rew_var_cell = rew_var_list;
	     (rew_var_cell != NULL);
	     rew_var_cell = rew_var_cell->next_st_bucket_cell)
	  rew_var_cell->st_bucket->info.expr->value = 0.0L;
}


void		infer_measure(void)
{
	SIM_MEASURE	*sim_measure;
	ST_BUCKET_CELL	*measure;
	int		i,
			j,
			obs_num;
	long double	sample_mean,
			sample_var,
			sample_standard_dev,
			j_sample_mean[MAX_SIM_RUN_NUM],
			j_sample_var[MAX_SIM_RUN_NUM],
			value[MAX_SIM_RUN_NUM];

	/* print the .est file header */
	fprintf(est_file,
		(sim_run_num > 1)?
		  "90%% confidence estimate of the performance measures for %s:":
		  "Estimate of the performance measures for %s:",
		archi_type[spec_no]->symbol_lexeme);

	/* print the estimate of the performance measures */
	for (measure = sim_measure_list;
	     (measure != NULL);
	     measure = measure->next_st_bucket_cell)
	{
	  sim_measure = measure->st_bucket->info.sim_measure;
	  switch (sim_measure->measure_index)
	  {
	    case M_EXPECTATION:
	      compute_sample_mean_var(sim_measure->measure_expr,
				      &sample_mean,
				      &sample_var,
				      -1,
				      NULL);
	      if (sim_run_num > 1)
	      {
	        sample_standard_dev = (long double)sqrt(sample_var);
	        fprintf(est_file,
		        "\n\n- Estimate of measure \"%s\":\n\t%g\n\t\t[%g, %g]",
		        measure->st_bucket->symbol_lexeme,
		        (double)sample_mean,
		        (double)(sample_mean -
				   ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)),
		        (double)(sample_mean +
				   ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)));
	      }
	      else
	        fprintf(est_file,
		        "\n\n- Estimate of measure \"%s\":\n\t%g",
		        measure->st_bucket->symbol_lexeme,
		        (double)sample_mean);
	      break;
	    case M_VARIANCE:
	      compute_sample_mean_var(sim_measure->measure_expr,
				      &sample_mean,
				      &sample_var,
				      -1,
				      NULL);
	      if (sim_run_num > 1)
	      {
		for (j = 0;
		     (j < sim_run_num);
		     j++)
		{
		  compute_sample_mean_var(sim_measure->measure_expr,
					  j_sample_mean + j,
					  j_sample_var + j,
					  j,
					  NULL);
		  value[j] = (long double)sim_run_num * sample_var -
			     (long double)(sim_run_num - 1) * j_sample_var[j];
		}
	        compute_sample_mean_var(NULL,
				        &sample_mean,
				        &sample_var,
				        -1,
					value);
	        sample_standard_dev = (long double)sqrt(sample_var);
	        fprintf(est_file,
		        "\n\n- Estimate of measure \"%s\":\n\t%g\n\t\t[%g, %g]",
		        measure->st_bucket->symbol_lexeme,
		        (double)sample_mean,
		        (double)(sample_mean -
				   ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)),
		        (double)(sample_mean +
				   ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)));
	      }
	      else
	        fprintf(est_file,
		        "\n\n- Estimate of measure \"%s\":\n\t%g",
		        measure->st_bucket->symbol_lexeme,
		        (double)sample_var);
	      break;
	    case M_DISTRIBUTION:
	      fprintf(est_file,
		      "\n\n- Estimate of measure \"%s\":",
		      measure->st_bucket->symbol_lexeme);
	      obs_num = (int)ceil((double)((sim_measure->interval_end->info.expr->value -
					    sim_measure->interval_begin->info.expr->value) /
                                           sim_measure->sub_interval_width->info.expr->value));
	      for (j = 0;
		   (j < obs_num);
		   j++)
	      {
	        for (i = 0;
	             (i < sim_run_num);
	             i++)
		  value[i] = sim_measure->value[i][j];
	        compute_sample_mean_var(NULL,
				        &sample_mean,
				        &sample_var,
				        -1,
					value);
	        if (sim_run_num > 1)
	        {
	          sample_standard_dev = (long double)sqrt(sample_var);
	          fprintf(est_file,
			  "\n\t%g\n\t\t[%g, %g]",
		          (double)sample_mean,
		          (double)(sample_mean -
				     ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)),
		          (double)(sample_mean +
				     ALPHA * sample_standard_dev / (long double)sqrt((double)sim_run_num)));
	        }
	        else
	          fprintf(est_file,
		          "\n\t%g",
		          (double)sample_mean);
	      }
	      break;
	  }
	}
}


void		compute_sample_mean_var(ST_BUCKET   *measure_expr,
					long double *sample_mean,
					long double *sample_var,
					int         j,
					long double *value)
{
	int		i;
	long double	measure_sample[MAX_SIM_RUN_NUM];

	for (i = 0,
	     *sample_mean = 0.0L;
	     (i < sim_run_num);
	     i++)
	  if (i != j)
	  {
	    if (measure_expr != NULL)
	    {
	      eval_expr(measure_expr,
		        i);
	      measure_sample[i] = measure_expr->info.expr->value;
	    }
	    else
	      measure_sample[i] = value[i];
	    *sample_mean += measure_sample[i];
	  }
	*sample_mean /= (j == -1)?
			  ((long double)sim_run_num):
			  ((long double)(sim_run_num - 1));
	for (i = 0,
	     *sample_var = 0.0L;
	     (i < sim_run_num);
	     i++)
	  if (i != j)
	    *sample_var += (measure_sample[i] - *sample_mean) *
			   (measure_sample[i] - *sample_mean);
	if (*sample_var != 0.0L)
	  *sample_var /= (j == -1)?
			   ((long double)(sim_run_num - 1)):
			   ((long double)(sim_run_num - 2));
}


void		close_trace_files(void)
{
	ST_BUCKET_CELL	*trace_file_cell;

	for (trace_file_cell = trace_file_list;
	     (trace_file_cell != NULL);
	     trace_file_cell = trace_file_cell->next_st_bucket_cell)
	  fclose(trace_file_cell->st_bucket->info.trace_file);
}
