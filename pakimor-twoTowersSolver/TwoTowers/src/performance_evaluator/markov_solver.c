/****************************************************************/
/*								*/
/*								*/
/*			 markov_solver.c			*/
/*								*/
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module analyzes the performance properties of a .aem spec file by solving the underlying */
/* performance model. */
/* This module contains the following function: */
/* - compute_distr_rew_measure(): It computes the stationary/transient distribution/measures of a .empa */
/*				 spec file, where the performance measures are expressed through yield and */
/*				 bonus rewards in a .rew spec file. */
/* and the following auxiliary functions: */
/* - build_matrix(): It builds the infinitesimal generator matrix and stores it in a packed way using */
/*		     three vectors: the vector of the nonzero rates, the vector of the destination state */
/*		     of each nonzero rate, and the vector of the positions of the first nonzero rate for */
/*		     each state. */
/* - build_vector(): It builds the vectors needed by the packed representation of the infinitesimal */
/*		     generator matrix. */
/* - build_diag_elem(): It builds a diagonal element of the packed infinitesimal generator matrix. */
/* - transpose_matrix(): It transposes the packed infinitesimal generator matrix for numerical stability */
/*			 and computational efficiency purposes. The operation is carried out directly on */
/*			 the three vectors and builds only one additional vector. */
/* - compute_absorbing_scc_num(): It computes the number of absorbing strongly connected components. */
/* - visit_depth_first1(): It visits in depth first order the performance semantic model. */
/* - visit_depth_first2(): It visits in depth first order the transpose performance semantic model by */
/*			   considering the states in order of decreasing end time determined by the */
/*			   previous depth first visit. */
/* - visit_depth_first3(): It visits in depth first order the forest determined by the previous depth */
/*			   first visit. */
/* - solve_gauss_elim(): It solves the performance model in the stationary case by applying the Gaussian */
/*			 elimination method. */
/* - solve_assor(): It solves the performance model in the stationary case by applying an adaptive version */
/*		    of the symmetric stochastic overrelaxation method. */
/* - solve_unif(): It solves the performance model in the transient case by applying the uniformization */
/*		   method. */
/* - perform_ssor_step(): It performs a step of the symmetric stochastic overrelaxation method. */
/* - normalize_vector(): It normalizes a vector so that its elements form a probability mass function */
/*			 (nonnegative reals summing up to 1). */
/* - print_distr(): It recursively prints onto a .dis file the stationary/transient probability */
/*		    distribution. */
/* - print_prob(): It prints onto a .dis file the stationary/transient probability of a state. */
/* - compute_hsmc_norm_factor(): It computes the normalizing factor for the state probabilities of a HSMC. */
/* - update_hsmc_norm_factor(): It updates the normalizing factor for the state probabilities of a HSMC by */
/*				adding the contribution of a state. */
/* - compute_rew_measures(): It recursively computes the value of the yield-bonus reward based */
/*			     stationary/transient measures. */
/* - update_rew_measures(): It updates the value of the yield-bonus reward based stationary/transient */
/*			    measures by adding the contribution of a state. */
/* - print_rew_measures(): It prints onto a .val file the value of the yield-bonus reward based */
/*			   stationary/transient measures. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<math.h>
#include	<stdlib.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/driver.h"

#include	"../headers/aemilia_compiler.h"

#include	"../headers/simulator.h"

#include	"../headers/file_handler.h"
#include	"../headers/memory_handler.h"
#include	"../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions.	*/
/****************************************************************/

ST_BUCKET_CELL	*rew_measure_list;
			/* list of yield-bonus reward based performance measures to be computed; */
			/* set by parse_rew() and append_absent_rew_measure() of rew-parser.y; */
			/* used in update_rew_measure() and print_rew_measure() of markov-solver.c and in */
			/* append_absent_rew_measure() of rew-parser.y */

long double     trans_time;
                        /* time at which transient distribution/measures must be evaluated; */
                        /* set by get_spec_path() of driver.c and solve_unif() of markov-solver.c; */
                        /* used in solve_unif() of markov-solver.c */


void		compute_distr_rew_measure(char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE	FILE		*per_file;
				/* .dis/.val file containing the performance evaluation results; */
				/* set by compute_distr_rew_measure(); */
				/* used in print_prob() and print_rew_measure() */

PRIVATE	long double	*rate_vector,
				/* vector of the nonzero rates; */
				/* set by compute_distr_rew_measure(), build_vector(), build_diag_elem(), */
				/* transpose_matrix(), solve_assor(), and solve_unif(); */
				/* used in transpose_matrix(), solve_gauss_elim(), solve_assor(), */
				/* perform_ssor_step(), and solve_unif() */
			*prob_vector,
				/* vector of the stationary/transient state probabilities; */
				/* set by compute_distr_rew_measure(), solve_gauss_elim(), solve_gauss(), */
		       		/* solve_assor(), perform_ssor_step(), and solve_unif(); */
				/* used in solve_gauss_elim(), solve_assor(), perform_ssor_step(), */
				/* solve_unif(), print_prob(), update_hsmc_norm_factor(), and */
				/* update_rew_measure() */
			hsmc_norm_factor;
				/* normalizing factor for the state probabilities of a HSMC; */
				/* set by compute_distr_rew_measure() and update_hsmc_norm_factor(); */
				/* used in update_rew_measure() */

PRIVATE	int		*rate_dest_vector,
				/* vector of the destination states of each nonzero rate; */
				/* set by compute_distr_rew_measure(), build_vector(), build_diag_elem(), */
				/* and transpose_matrix(); */
				/* used in transpose_matrix(), compute_absorbing_scc_num(), */
				/* visit_depth_first2(), visit_depth_first3(), solve_gauss_elim(), */
				/* solve_assor(), perform_ssor_step(), and solve_unif() */
			*first_rate_pos_vector,
				/* vector of the positions of the first nonzero rate for each state; */
				/* set by compute_distr_rew_measure(), build_vector(), and */
				/* transpose_matrix(); */
				/* used in transpose_matrix() */
	  		*first_rate_pos_transp_vector;
				/* transpose vector of the positions of the first nonzero rate for each */
				/* state; */
				/* set by compute_distr_rew_measure() and transpose_matrix(); */
				/* used in compute_absorbing_scc_num(), visit_depth_first2(), */
				/* visit_depth_first3(), solve_gauss_elim(), solve_assor(), */
				/* perform_ssor_step(), and solve_unif() */


PRIVATE	void		build_matrix(int *,
				     int *),
			build_vector(GST_BUCKET *,
				     int *,
				     int *),
			build_diag_elem(GST_BUCKET *,
					long double,
					int *,
					BOOLEAN *),
			transpose_matrix(void);

PRIVATE	int		compute_absorbing_scc_num(void);

PRIVATE	void		visit_depth_first1(int *,
					   int *),
			visit_depth_first2(int,
					   int *,
					   int *),
			visit_depth_first3(int,
					   int,
					   int *);

PRIVATE	BOOLEAN		solve_gauss_elim(void);

PRIVATE	void		solve_assor(void),
			perform_ssor_step(long double),
			solve_unif(void),
			normalize_vector(long double *),
			print_distr(int *),
			print_prob(GST_BUCKET *,
				   int *),
			compute_hsmc_norm_factor(void),
			update_hsmc_norm_factor(GST_BUCKET *),
			compute_rew_measures(void),
			update_rew_measures(GST_BUCKET *),
			print_rew_measures(void);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

void		compute_distr_rew_measure(char *aem_path)
{
	BOOLEAN		pivot_crash;
	OPTION_INDEX	old_option_index;
	int		next_global_state_no,
			next_free_pos;

	/* allocate memory for the packed infinitesimal generator matrix, with the number of nonzero rates */
	/* given by the number of transitions plus the number of states to account for the diagonal */
	/* elements (position zero of each vector is never used) */
	rate_vector = (long double *)new_calloc(archi_type[spec_no]->info.archi_type->state_num +
						  archi_type[spec_no]->info.archi_type->trans_num +
						  1,
						sizeof(long double));
	rate_dest_vector = (int *)new_calloc(archi_type[spec_no]->info.archi_type->state_num +
					       archi_type[spec_no]->info.archi_type->trans_num +
					       1,
					     sizeof(int));
	first_rate_pos_vector = (int *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 2,
						  sizeof(int));
	first_rate_pos_transp_vector =
	  (int *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 2,
			    sizeof(int));
	prob_vector = (long double *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
						sizeof(long double));

	/* build and transpose the packed infinitesimal generator matrix */
	next_global_state_no = 1;
	next_free_pos = 1;
	build_matrix(&next_global_state_no,
		     &next_free_pos);
	first_rate_pos_vector[archi_type[spec_no]->info.archi_type->state_num + 1] = next_free_pos;
	transpose_matrix();

	/* solve the performance model */
	switch (option_index)
	{
	  case STAT_DISTR_GAUSS_ELIM:
	  case STAT_MEASURE_GAUSS_ELIM:
	    if ((archi_type[spec_no]->info.archi_type->absorbing_state_num <= 1) &&
		(compute_absorbing_scc_num() == 1))
	    {
	      pivot_crash = solve_gauss_elim();
	      if (pivot_crash)
	        solve_assor();
	    }
	    else
	    {
	      old_option_index = option_index;
	      option_index = (old_option_index == STAT_DISTR_GAUSS_ELIM)?
			       TRANS_DISTR_UNIF:
			       TRANS_MEASURE_UNIF;
	      trans_time = 1000.0L;
	      solve_unif();
	      option_index = old_option_index;
	    }
	    break;
	  case STAT_DISTR_ASSOR:
	  case STAT_MEASURE_ASSOR:
	    if ((archi_type[spec_no]->info.archi_type->absorbing_state_num <= 1) &&
		(compute_absorbing_scc_num() == 1))
	      solve_assor();
	    else
	    {
	      old_option_index = option_index;
	      option_index = (old_option_index == STAT_DISTR_ASSOR)?
			       TRANS_DISTR_UNIF:
			       TRANS_MEASURE_UNIF;
	      trans_time = 1000.0L;
	      solve_unif();
	      option_index = old_option_index;
	    }
	    break;
	  case TRANS_DISTR_UNIF:
	  case TRANS_MEASURE_UNIF:
	    solve_unif();
	    break;
	  default:
	    break;
	}

	/* print the performance evaluation results */
	per_file = new_fopen(aem_path,
			     ((option_index == STAT_DISTR_GAUSS_ELIM) ||
			      (option_index == STAT_DISTR_ASSOR) ||
			      (option_index == TRANS_DISTR_UNIF))?
			       ".dis":
			       ".val",
			     "w");
	if ((option_index == STAT_DISTR_GAUSS_ELIM) ||
	    (option_index == STAT_DISTR_ASSOR) ||
	    (option_index == TRANS_DISTR_UNIF))
	{
	  next_global_state_no = 1;
	  print_distr(&next_global_state_no);
	}
	else
	{
	  if (archi_type[spec_no]->info.archi_type->psm_index == HSMC)
	  {
	    hsmc_norm_factor = 0.0L;
	    compute_hsmc_norm_factor();
	    if (hsmc_norm_factor == 0.0L)
	      hsmc_norm_factor = 1.0L;
	  }
	  compute_rew_measures();
	  print_rew_measures();
	}
	fclose(per_file);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		build_matrix(int *next_global_state_no,
			     int *next_free_pos)
{
	GST_BUCKET	*global_state;
	GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
	G_TRANS_CELL	*global_trans_cell;

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
                                                   NULL);
	archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered = TRUE;
        while (global_state_stack != NULL)
	{
          /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
	  /* case the performance semantic model is continuous time (the vanishing global states from the */
	  /* initial global state to the first nonvanishing one must be overridden) */
	  if ((archi_type[spec_no]->info.archi_type->psm_index != HCTMC) ||
              !check_vanishing(global_state))
          {
            if (global_state->global_state_no == *next_global_state_no)
	      build_vector(global_state,
			   next_global_state_no,
			   next_free_pos);
            for (global_trans_cell = global_state->global_trans_list;
                 (global_trans_cell != NULL);
	         global_trans_cell = global_trans_cell->next_g_trans_cell)
              if (global_trans_cell->der_global_state->global_state_no == *next_global_state_no)
	        build_vector(global_trans_cell->der_global_state,
			     next_global_state_no,
			     next_free_pos);
	  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
          /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if (!global_trans_cell->der_global_state->global_trans_considered)
	    {
                global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                           global_state_stack);
		global_trans_cell->der_global_state->global_trans_considered = TRUE;
	    }
        }
}


void		build_vector(GST_BUCKET *global_state,
			     int        *next_global_state_no,
			     int        *next_free_pos)
{
	BOOLEAN		self_considered;
	G_TRANS_CELL	*global_trans_cell;
	GST_BUCKET	*der_global_state;
	int		global_state_no,
			curr_min_der_global_state_no,
			prev_min_der_global_state_no,
			global_trans_count,
			der_global_state_trans_count;
	long double	total_rate,
			der_global_state_aggr_rate;

	/* initialize variables and vectors */
	global_state_no = global_state->global_state_no;
        (*next_global_state_no)++;
	first_rate_pos_vector[global_state_no] = *next_free_pos;

	/* compute the total rate of the global state towards other global states */
	for (global_trans_cell = global_state->global_trans_list,
	     global_trans_count = 0,
	     total_rate = 0.0L,
	     der_global_state_aggr_rate = 0.0L;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell,
	     global_trans_count++)
	  if (global_trans_cell->der_global_state != global_state)
	    total_rate += global_trans_cell->action->info.action->rate->info.expr->value;

	/* update the vectors */
	for (self_considered = FALSE,
	     curr_min_der_global_state_no = 0,
	     der_global_state_trans_count = 0;
	     (global_trans_count > 0);
	     global_trans_count -= der_global_state_trans_count)
	{
	  /* determine the next derivative global state with the smallest serial number */
	  for (global_trans_cell = global_state->global_trans_list,
	       prev_min_der_global_state_no = curr_min_der_global_state_no,
	       curr_min_der_global_state_no = archi_type[spec_no]->info.archi_type->state_num + 1;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	  {
	    der_global_state = global_trans_cell->der_global_state;
	    if (der_global_state->global_state_no == curr_min_der_global_state_no)
	    {
	      der_global_state_aggr_rate += global_trans_cell->action->info.action->rate->info.expr->value;
	      der_global_state_trans_count++;
	    }
	    else
	      if ((der_global_state->global_state_no > prev_min_der_global_state_no) &&
	          (der_global_state->global_state_no < curr_min_der_global_state_no))
	      {
	        curr_min_der_global_state_no = der_global_state->global_state_no;
	        der_global_state_aggr_rate =
		  global_trans_cell->action->info.action->rate->info.expr->value;
	        der_global_state_trans_count = 1;
	      }
	  }
	  /* update the vectors for the next derivative global state with the smallest serial number */
	  if (curr_min_der_global_state_no == global_state_no)
	    build_diag_elem(global_state,
			    total_rate,
			    next_free_pos,
			    &self_considered);
	  else
	  {
	    if (!self_considered &&
		(global_state_no < curr_min_der_global_state_no))
	      build_diag_elem(global_state,
			      total_rate,
			      next_free_pos,
			      &self_considered);
	    switch (archi_type[spec_no]->info.archi_type->psm_index)
	    {
	      case HCTMC:
	      case HDTMC:
	        rate_vector[*next_free_pos] = der_global_state_aggr_rate;
	        break;
	      case HSMC:
	        if (check_vanishing(global_state))
	          rate_vector[*next_free_pos] = der_global_state_aggr_rate;
	        else
	          rate_vector[*next_free_pos] = der_global_state_aggr_rate / total_rate;
	        break;
	      default:
	        break;
	    }
	    rate_dest_vector[*next_free_pos] = curr_min_der_global_state_no;
            (*next_free_pos)++;
	  }
	}
	if (!self_considered)
	  build_diag_elem(global_state,
			  total_rate,
			  next_free_pos,
			  &self_considered);
}


void		build_diag_elem(GST_BUCKET  *global_state,
			        long double total_rate,
			        int         *next_free_pos,
			        BOOLEAN     *self_considered)
{
	switch (archi_type[spec_no]->info.archi_type->psm_index)
	{
	  case HCTMC:
	  case HDTMC:
	    rate_vector[*next_free_pos] = -total_rate;
	    break;
	  case HSMC:
	    if (check_vanishing(global_state))
	      rate_vector[*next_free_pos] = -total_rate;
	    else
	      rate_vector[*next_free_pos] = -1.0L;
	    break;
	  default:
	    break;
	}
	rate_dest_vector[*next_free_pos] = global_state->global_state_no;
        (*next_free_pos)++;
	*self_considered = TRUE;
}


void		transpose_matrix(void)
{
	BOOLEAN		found;
	int		der_global_state_no,
			global_state_no,
			next_free_pos,
			pos,
			last_pos,
			aux_global_state_no;
	long double	aux_rate;

	for (der_global_state_no = 1,
	     next_free_pos = 1;
	     (der_global_state_no <= archi_type[spec_no]->info.archi_type->state_num);
	     der_global_state_no++)
	{
	  first_rate_pos_transp_vector[der_global_state_no] = next_free_pos;
	  for (global_state_no = 1;
	       (global_state_no <= archi_type[spec_no]->info.archi_type->state_num);
	       global_state_no++)
	  {
	    for (pos = first_rate_pos_vector[global_state_no],
	         last_pos = first_rate_pos_vector[global_state_no + 1] - 1,
	         found = FALSE;
	         ((pos <= last_pos) &&
		  !found);
		 )
	      if (rate_dest_vector[pos] == der_global_state_no)
		found = TRUE;
	      else
		pos++;
	    if (found)
	    {
	      aux_rate = rate_vector[pos];
	      for (;
		   (pos > next_free_pos);
		   pos--)
	      {
		rate_vector[pos] = rate_vector[pos - 1];
		rate_dest_vector[pos] = rate_dest_vector[pos - 1];
	      }
	      rate_vector[next_free_pos] = aux_rate;
	      rate_dest_vector[next_free_pos] = global_state_no;
	      next_free_pos++;
	      for (aux_global_state_no = 1;
	           (aux_global_state_no <= global_state_no);
	           aux_global_state_no++)
		++first_rate_pos_vector[aux_global_state_no];
	    }
	  }
	}
	first_rate_pos_transp_vector[archi_type[spec_no]->info.archi_type->state_num + 1] = next_free_pos;
}


int		compute_absorbing_scc_num(void)
{
	char		*absorbing_vector;
	int		*scc_vector,
			visit_time,
			visited_global_state_num,
			max_end_time,
			global_state_no,
			scc_num,
			scc_no,
			absorbing_scc_num,
			row,
			pos,
			i;

	/* visit in depth first order the performance semantic model; at the end scc_vector stores the */
	/* end visit time for each global state */
	scc_vector = (int *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
				       sizeof(int));
	for (i = archi_type[spec_no]->info.archi_type->state_num;
	     (i >= 1);
	     i--)
	  scc_vector[i] = -2;
	visit_time = 0;
	visit_depth_first1(&visit_time,
			   scc_vector);

	/* visit in depth first order the transpose performance semantic model by considering the global */
	/* states in order of decreasing end time determined by the previous depth first visit; at the end */
	/* scc_vector stores the opposite of the serial number of the father of each global state (or 0 if */
	/* the global state is the root of a tree in the forest) */
	for (visited_global_state_num = 0;
	     (visited_global_state_num < archi_type[spec_no]->info.archi_type->state_num);
	     )
	{
	  for (i = archi_type[spec_no]->info.archi_type->state_num,
	       max_end_time = 0,
	       global_state_no = 0;
	       (i >= 1);
	       i--)
	    if (scc_vector[i] > max_end_time)
	    {
	      max_end_time = scc_vector[i];
	      global_state_no = i;
	    }
	  visit_depth_first2(global_state_no,
			     &visited_global_state_num,
			     scc_vector);
	}

	/* determine the number of absorbing strongly connected components */
	for (i = archi_type[spec_no]->info.archi_type->state_num,
	     scc_num = 0;
	     (i >= 1);
	     i--)
	  if (scc_vector[i] == 0)
	    scc_num++;
	if (scc_num == 1)
	  absorbing_scc_num = 1;
	else
	{
	  /* visit in depth first order the forest determined by the previous depth first visit; at the */
	  /* end scc_stores the same scc serial number for all the global states in the same scc */
	  for (global_state_no = archi_type[spec_no]->info.archi_type->state_num,
	       scc_no = 0;
	       (global_state_no >= 1);
	       global_state_no--)
	    if (scc_vector[global_state_no] == 0)
	    {
	      scc_vector[global_state_no] = ++scc_no;
	      visit_depth_first3(global_state_no,
			         scc_no,
			         scc_vector);
	    }
	  /* check for each scc whether it is absorbing or not */
	  absorbing_vector = (char *)new_calloc(scc_num + 1,
						sizeof(char));
	  for (i = scc_num;
	       (i >= 1);
	       i--)
	    absorbing_vector[i] = (char)TRUE;
	  for (global_state_no = archi_type[spec_no]->info.archi_type->state_num;
	       (global_state_no >= 1);
	       global_state_no--)
	    for (row = archi_type[spec_no]->info.archi_type->state_num;
	         (row >= 1);
	         row--)
	      for (pos = first_rate_pos_transp_vector[row];
	           (pos < first_rate_pos_transp_vector[row + 1]);
	           pos++)
		if ((rate_dest_vector[pos] == global_state_no) &&
		    (scc_vector[row] != scc_vector[global_state_no]))
		  absorbing_vector[scc_vector[global_state_no]] = (char)FALSE;
	  for (scc_no = 1,
	       absorbing_scc_num = 0;
	       (scc_no <= scc_num);
	       scc_no++)
	    if (absorbing_vector[scc_no])
	      absorbing_scc_num++;
	  free(absorbing_vector);
	}
	free(scc_vector);
	return(absorbing_scc_num);
}


void	visit_depth_first1(int *visit_time,
			   int *scc_vector)
{
	GST_BUCKET	*global_state;
	GST_BUCKET_CELL	*global_state_stack,
			*pending_global_state_stack,
			*global_state_cell;
	G_TRANS_CELL	*global_trans_cell;

	/* initialize the stack of the global states to be considered with the initial global state, */
	/* as well as the stack of the pending global states (not all of their descendants have been */
	/* considered yet), then process one global state at a time until the first stack is nonempty; */
	/* recursion is not employed to avoid run-time-support stack overflow in the case of a huge global */
	/* state space */
	global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
						   NULL);
	if (archi_type[spec_no]->info.archi_type->init_global_state->global_state_no >= 1)
	{
	  scc_vector[archi_type[spec_no]->info.archi_type->init_global_state->global_state_no] = -1;
	  pending_global_state_stack = alloc_gst_bucket_cell(NULL,
							     NULL);
	}
	else
	  pending_global_state_stack = NULL;
	while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
	  global_state_cell = global_state_stack;
	  global_state_stack = global_state_stack->next_gst_bucket_cell;
	  global_state = global_state_cell->gst_bucket;
	  free(global_state_cell);
	  /* assign the finish time to the last pending global states whose descendants have all been */
	  /* considered */
	  while ((pending_global_state_stack != NULL) &&
		 (pending_global_state_stack->gst_bucket != NULL))
	  {
	    scc_vector[pending_global_state_stack->gst_bucket->global_state_no] = ++(*visit_time);
	    global_state_cell = pending_global_state_stack;
	    pending_global_state_stack = pending_global_state_stack->next_gst_bucket_cell;
	    free(global_state_cell);
	  }
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
	  /* case the performance semantic model is continuous time (the vanishing global states from the */
	  /* initial global state to the first nonvanishing one must be overridden) */
	  if (global_state->global_state_no >= 1)
	    pending_global_state_stack->gst_bucket = global_state;
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
          /* of the global state */
	  for (global_trans_cell = global_state->global_trans_list;
	       (global_trans_cell != NULL);
	       global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if ((global_trans_cell->der_global_state->global_state_no <= 0) ||
	        (scc_vector[global_trans_cell->der_global_state->global_state_no] == -2))
	    {
	      global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
							 global_state_stack);
	      if (global_state->global_state_no >= 1)
	        scc_vector[global_state->global_state_no] = -1;
	      if (global_trans_cell->der_global_state->global_state_no >= 1)
	        pending_global_state_stack = alloc_gst_bucket_cell(NULL,
								   pending_global_state_stack);
	    }
	}

	/* assign the finish time to the remaining pending global states */
	while (pending_global_state_stack != NULL)
	{
	  scc_vector[pending_global_state_stack->gst_bucket->global_state_no] = ++(*visit_time);
	  global_state_cell = pending_global_state_stack;
	  pending_global_state_stack = pending_global_state_stack->next_gst_bucket_cell;
	  free(global_state_cell);
	}
}


void		visit_depth_first2(int starting_global_state_no,
				   int *visited_global_state_num,
				   int *scc_vector)
{
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell;
	int		global_state_no,
			pos;

	/* initialize the stack of the global states to be considered with the initial global state, then */
	/* process one global state at a time until the stack is nonempty; recursion is not employed to */
	/* avoid run-time-support stack overflow in the case of a huge global state space */
	global_state_stack = alloc_gst_bucket_cell((GST_BUCKET *)starting_global_state_no,
						   NULL);
	scc_vector[starting_global_state_no] = 0;
	while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
	  global_state_cell = global_state_stack;
	  global_state_stack = global_state_stack->next_gst_bucket_cell;
	  global_state_no = (int)(global_state_cell->gst_bucket);
	  free(global_state_cell);
	  (*visited_global_state_num)++;
	  /* push into the stack the not-yet-considered global states reaching the global state and assign */
	  /* them the serial number of the global state as scc serial number */
	  for (pos = first_rate_pos_transp_vector[global_state_no];
	       (pos < first_rate_pos_transp_vector[global_state_no + 1]);
	       pos++)
	    if (scc_vector[rate_dest_vector[pos]] > 0)
	    {
	      global_state_stack = alloc_gst_bucket_cell((GST_BUCKET *)rate_dest_vector[pos],
							 global_state_stack);
	      scc_vector[rate_dest_vector[pos]] = -global_state_no;
	    }
	}
}


void		visit_depth_first3(int starting_global_state_no,
				   int scc_no,
				   int *scc_vector)
{
	GST_BUCKET_CELL	*global_state_stack,
			*global_state_cell;
	int		global_state_no,
			pos;

	/* initialize the stack of the global states to be considered with the initial global state, then */
	/* process one global state at a time until the stack is nonempty; recursion is not employed to */
	/* avoid run-time-support stack overflow in the case of a huge global state space */
	global_state_stack = alloc_gst_bucket_cell((GST_BUCKET *)starting_global_state_no,
						   NULL);
	while (global_state_stack != NULL)
	{
	  /* extract the global state at the top of the stack */
	  global_state_cell = global_state_stack;
	  global_state_stack = global_state_stack->next_gst_bucket_cell;
	  global_state_no = (int)(global_state_cell->gst_bucket);
	  free(global_state_cell);
	  /* push into the stack the not-yet-considered global states reaching the global state and assign */
	  /* them the scc serial number of the global state as scc serial number */
	  for (pos = first_rate_pos_transp_vector[global_state_no];
	       (pos < first_rate_pos_transp_vector[global_state_no + 1]);
	       pos++)
	    if (scc_vector[rate_dest_vector[pos]] == -global_state_no)
	    {
	      global_state_stack = alloc_gst_bucket_cell((GST_BUCKET *)rate_dest_vector[pos],
							 global_state_stack);
	      scc_vector[rate_dest_vector[pos]] = scc_no;
	    }
	}
}


BOOLEAN		solve_gauss_elim(void)
{
	BOOLEAN		pivot_crash;
	int		**triang_rate_dest_matrix,
			*triang_length_matrix,
			row,
			col,
			pos,
			row_below,
			absorbing_global_state_no,
			new_length;
	long double	pivot,
			*filled_rate_vector,
			**triang_rate_matrix;

	/* allocate memory for the auxiliary rate vector and the upper triangular matrix */
	filled_rate_vector = (long double *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
						       sizeof(long double));
	triang_rate_matrix = (long double **)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
							sizeof(long double *));
	triang_rate_dest_matrix = (int **)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
						     sizeof(int *));
	triang_length_matrix = (int *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
						 sizeof(int));

	/* copy the rows of the packed infinitesimal generator matrix to the upper triangular matrix */
	for (row = archi_type[spec_no]->info.archi_type->state_num,
	     absorbing_global_state_no = 0;
	     (row >= 1);
	     row--)
	{
	  triang_length_matrix[row] = first_rate_pos_transp_vector[row + 1] -
					first_rate_pos_transp_vector[row];
	  triang_rate_matrix[row] = (long double *)new_calloc(triang_length_matrix[row],
							      sizeof(long double));
	  triang_rate_dest_matrix[row] = (int *)new_calloc(triang_length_matrix[row],
						           sizeof(int));
	  for (pos = first_rate_pos_transp_vector[row],
	       col = 0;
	       (pos < first_rate_pos_transp_vector[row + 1]);
	       pos++,
	       col++)
	  {
	    triang_rate_matrix[row][col] = rate_vector[pos];
	    triang_rate_dest_matrix[row][col] = rate_dest_vector[pos];
	    if (triang_rate_matrix[row][col] == 0.0L)
	      absorbing_global_state_no = row;
	  }
	}
	if (absorbing_global_state_no == 0)
	  absorbing_global_state_no = archi_type[spec_no]->info.archi_type->state_num;

	/* compute the upper triangular matrix (the row indicated by absorbing_state_no is ignored due to */
	/* singularity) */
	for (row = 1,
	     pivot_crash = FALSE;
	     ((row <= archi_type[spec_no]->info.archi_type->state_num) &&
	      !pivot_crash);
	     row++)
	  if (row != absorbing_global_state_no)
	  {
	    pivot = triang_rate_matrix[row][0];
	    if ((long double)fabs(pivot) <= ZERO_TOL)
	      pivot_crash = TRUE;
	    else
	    {
	      /* divide every element of the row by the pivot (the first element becomes 1) */
	      for (pos = 0;
	           (pos < triang_length_matrix[row]);
	           pos++)
	        triang_rate_matrix[row][pos] /= pivot;
	      /* reduce the rows below the one under consideration (except for the row indicated by */
	      /* absorbing_state_no) if the element in the same column as the pivot is not zero */
	      for (row_below = row + 1;
	           (row_below <= archi_type[spec_no]->info.archi_type->state_num);
	           row_below++)
	        if ((row_below != absorbing_global_state_no) &&
		    (triang_rate_dest_matrix[row_below][0] == row))
	        {
	          for (col = archi_type[spec_no]->info.archi_type->state_num;
		       (col >= 1);
		       col--)
	            filled_rate_vector[col] = 0.0L;
	          for (pos = 0;
	               (pos < triang_length_matrix[row_below]);
	               pos++)
	            filled_rate_vector[triang_rate_dest_matrix[row_below][pos]] =
		      triang_rate_matrix[row_below][pos];
	          for (pos = 0;
	               (pos < triang_length_matrix[row]);
	               pos++)
		    filled_rate_vector[triang_rate_dest_matrix[row][pos]] -=
		      triang_rate_matrix[row_below][0] * triang_rate_matrix[row][pos];
	          for (col = archi_type[spec_no]->info.archi_type->state_num,
		       new_length = 0;
		       (col >= 1);
		       col--)
	            if ((filled_rate_vector[col] != 0.0L) ||
		        (col == row_below))
		      new_length++;
	          if (new_length > triang_length_matrix[row_below])
	          {
	            free(triang_rate_matrix[row_below]);
	            free(triang_rate_dest_matrix[row_below]);
	            triang_rate_matrix[row_below] = (long double *)new_calloc(new_length,
									      sizeof(long double));
	            triang_rate_dest_matrix[row_below] = (int *)new_calloc(new_length,
								           sizeof(int));
	          }
	          triang_length_matrix[row_below] = new_length;
	          for (pos = 0,
		       col = 1;
		       (pos < triang_length_matrix[row_below]);
		       col++)
	            if ((filled_rate_vector[col] != 0.0L) ||
		        (col == row_below))
		    {
	              triang_rate_matrix[row_below][pos] = filled_rate_vector[col];
	              triang_rate_dest_matrix[row_below][pos] = col;
		      pos++;
		    }
	        }
	    }
	  }

	/* compute the solution via backward substitutions and normalization */
	if (!pivot_crash)
	{
	  prob_vector[absorbing_global_state_no] = 1.0L;
	  for (row = archi_type[spec_no]->info.archi_type->state_num;
	       (row >= 1);
	       row--)
	    if (row != absorbing_global_state_no)
	    {
	      prob_vector[row] = 0.0L;
	      for (pos = 1;
	           (pos < triang_length_matrix[row]);
	           pos++)
	        prob_vector[row] -=
	          triang_rate_matrix[row][pos] * prob_vector[triang_rate_dest_matrix[row][pos]];
	    }
	  normalize_vector(prob_vector);
	}
	return(pivot_crash);
}


void		solve_assor(void)
{
	int		row,
			col,
			iteration;
	long double	diag_elem,
			relaxation,
			prev_max_error,
			max_error,
			error;

	/* scale the packed infinitesimal generator matrix so that all the diagonal elements become 1 */
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	{
	  for (col = first_rate_pos_transp_vector[row];
	       (rate_dest_vector[col] != row);
	       col++);
	  diag_elem = rate_vector[col];
	  if ((long double)fabs(diag_elem) > ZERO_TOL)
	    for (col = first_rate_pos_transp_vector[row];
	         (col <= first_rate_pos_transp_vector[row + 1] - 1);
	         col++)
	      rate_vector[col] /= diag_elem;
	}

	/* generate randomly the first probability vector */
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	  prob_vector[row] = init_prob_vector[0][row] = gen_uniform_01_num();
	normalize_vector(prob_vector);
	normalize_vector(init_prob_vector[0]);

	/* after carrying out a certain number of initial ssor steps without testing for convergence, */
	/* perform an additional ssor step followed by the test for convergence and a possible adjustment */
	/* of the relaxation until the result has been obtained or the maximum number of iterations has */
	/* been performed */
	for (iteration = 1,
	     relaxation = INIT_RELAXATION,
	     prev_max_error = 0.0L,
	     max_error = 1.0L;
	     ((max_error > ERROR_TOL) &&
	      (iteration <= MAX_ITERATION_NUM));
	     iteration++)
	{
	  perform_ssor_step(relaxation);
	  if (iteration >= INIT_ITERATION_NUM)
	  {
	    /* compute the maximum error */
	    for (row = archi_type[spec_no]->info.archi_type->state_num,
		 max_error = 0.0L;
	         (row >= 1);
	         row--)
	    {
	      error = (long double)fabs(prob_vector[row] - init_prob_vector[0][row]);
	      if (error > max_error)
		max_error = error;
	    }
	    /* store the current probability vector and maximum relative error and adjust the relaxation */
	    if (max_error > ERROR_TOL)
	    {
	      for (row = archi_type[spec_no]->info.archi_type->state_num;
	           (row >= 1);
	           row--)
	        init_prob_vector[0][row] = prob_vector[row];
	      if ((long double)fabs(max_error - prev_max_error) > RELAXATION_ADJ_TOL)
	      {
	        if ((max_error > prev_max_error) &&
		    (relaxation < MAX_RELAXATION))
		  relaxation += RELAXATION_STEP;
	        else
	          if ((max_error < prev_max_error) &&
		      (relaxation > MIN_RELAXATION))
		    relaxation -= RELAXATION_STEP;
	      }
	      prev_max_error = max_error;
	    }
	  }
	}
}


void		perform_ssor_step(long double relaxation)
{
	int		row,
			col;
	long double	value;

	/* forward step */
	for (row = 1;
	     (row <= archi_type[spec_no]->info.archi_type->state_num);
	     row++)
	{
	  for (col = first_rate_pos_transp_vector[row],
	       value = 0.0L;
	       (col <= first_rate_pos_transp_vector[row + 1] - 1);
	       col++)
	    value += rate_vector[col] * prob_vector[rate_dest_vector[col]];
	  prob_vector[row] -= relaxation * value;
	}
	normalize_vector(prob_vector);

	/* backward step */
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	{
	  for (col = first_rate_pos_transp_vector[row],
	       value = 0.0L;
	       (col <= first_rate_pos_transp_vector[row + 1] - 1);
	       col++)
	    value += rate_vector[col] * prob_vector[rate_dest_vector[col]];
	  prob_vector[row] -= relaxation * value;
	}
	normalize_vector(prob_vector);
}


void		solve_unif(void)
{
	int		row,
			pos,
			interval_num,
			interval,
			term_num,
			term;
	long double	max_diag_elem,
			exponent,
			error_tol,
			prod,
			sum,
			bound,
			*aux_prob_vector;

	/* determine the diagonal element with the maximum absolute value */
	for (row = archi_type[spec_no]->info.archi_type->state_num,
	     max_diag_elem = 0.0L;
	     (row >= 1);
	     row--)
	{
	  for (pos = first_rate_pos_transp_vector[row];
	       (rate_dest_vector[pos] != row);
	       pos++);
	  if ((long double)fabs(rate_vector[pos]) > max_diag_elem)
	    max_diag_elem = (long double)fabs(rate_vector[pos]);
	}

	/* scale and discretize the packed infinitesimal generator matrix */
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	  for (pos = first_rate_pos_transp_vector[row];
	       (pos <= first_rate_pos_transp_vector[row + 1] - 1);
	       pos++)
	  {
	    rate_vector[pos] /= max_diag_elem;
	    if (rate_dest_vector[pos] == row)
	      rate_vector[pos]++;
	  }

	/* compute the number of intervals and the number of terms in the approximating summation of each */
	/* interval */
	exponent = max_diag_elem * trans_time;
	interval_num = 1 + (int)(exponent / MAX_EXPONENT);
	exponent /= (long double)interval_num;
	error_tol = ERROR_TOL / interval_num;
	for (term_num = 0,
	     prod = 1.0L,
	     sum = prod,
	     bound = (1 - error_tol) / (long double)exp(-exponent);
	     (sum < bound);
	     term_num++,
	     prod *= exponent / (long double)term_num,
	     sum += prod);

	/* compute the transient state probability distribution interval by interval */
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	  prob_vector[row] = init_prob_vector[0][row];
	aux_prob_vector = (long double *)new_calloc(archi_type[spec_no]->info.archi_type->state_num + 1,
						    sizeof(long double));
	for (interval = 1;
	     (interval <= interval_num);
	     interval++)
	{
	  for (row = archi_type[spec_no]->info.archi_type->state_num;
	       (row >= 1);
	       row--)
	    init_prob_vector[0][row] = prob_vector[row];
	  for (term = 1;
	       (term <= term_num);
	       term++)
	  {
	    for (row = archi_type[spec_no]->info.archi_type->state_num;
	         (row >= 1);
	         row--)
	    {
	      aux_prob_vector[row] = 0.0L;
	      for (pos = first_rate_pos_transp_vector[row];
	           (pos <= first_rate_pos_transp_vector[row + 1] - 1);
	           pos++)
		aux_prob_vector[row] += init_prob_vector[0][rate_dest_vector[pos]] * rate_vector[pos];
	      aux_prob_vector[row] *= exponent / term;
	      prob_vector[row] += aux_prob_vector[row];
	    }
	    for (row = archi_type[spec_no]->info.archi_type->state_num;
	         (row >= 1);
	         row--)
	      init_prob_vector[0][row] = aux_prob_vector[row];
	  }
	  for (row = archi_type[spec_no]->info.archi_type->state_num;
	       (row >= 1);
	       row--)
	    prob_vector[row] *= (long double)exp(-exponent);
	  normalize_vector(prob_vector);
	}
}


void		normalize_vector(long double *vector)
{
	int		row;
	long double	elem_sum;

	for (row = archi_type[spec_no]->info.archi_type->state_num,
	     elem_sum = 0.0L;
	     (row >= 1);
	     row--)
	{
	  vector[row] = fabs(vector[row]);
	  elem_sum += vector[row];
	}
	for (row = archi_type[spec_no]->info.archi_type->state_num;
	     (row >= 1);
	     row--)
	  vector[row] /= elem_sum;
}


void		print_distr(int *next_global_state_no)
{
        GST_BUCKET      *global_state;
        GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
	G_TRANS_CELL    *global_trans_cell;

	/* print the .dis file header */
	if (option_index == TRANS_DISTR_UNIF)
	  fprintf(per_file,
		  "Transient probability distribution at time %g ",
		  (double)trans_time);
	else
	  fprintf(per_file,
		  "Stationary probability distribution ");
	fprintf(per_file,
		"of the homogeneous %s-time Markov chain\nunderlying %s:",
		(archi_type[spec_no]->info.archi_type->psm_index == HCTMC)?
		  "continuous":
		  "discrete",
		archi_type[spec_no]->symbol_lexeme);

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered = FALSE;
        while (global_state_stack != NULL)
        {
          /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
          /* case the performance semantic model is continuous time (the vanishing global states from the */
          /* initial global state to the first nonvanishing one must be overridden) */
          if ((archi_type[spec_no]->info.archi_type->psm_index != HCTMC) ||
              !check_vanishing(global_state))
          {
	    if (global_state->global_state_no == *next_global_state_no)
	      print_prob(global_state,
			 next_global_state_no);
	    for (global_trans_cell = global_state->global_trans_list;
                 (global_trans_cell != NULL);
                 global_trans_cell = global_trans_cell->next_g_trans_cell)
	      if (global_trans_cell->der_global_state->global_state_no == *next_global_state_no)
	        print_prob(global_trans_cell->der_global_state,
			   next_global_state_no);
	  }
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (global_trans_cell->der_global_state->global_trans_considered)
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered = FALSE;
            }
	}
}


void		print_prob(GST_BUCKET *global_state,
			   int        *next_global_state_no)
{
	fprintf(per_file,
		"\n\n- Global state %d:\t%g",
		global_state->global_state_no,
		(double)(prob_vector[global_state->global_state_no]));
	(*next_global_state_no)++;
}


void		compute_hsmc_norm_factor(void)
{
        GST_BUCKET      *global_state;
        GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
	G_TRANS_CELL    *global_trans_cell;

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered = FALSE;
        while (global_state_stack != NULL)
        {
          /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack */
	  update_hsmc_norm_factor(global_state);
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (global_trans_cell->der_global_state->global_trans_considered)
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered = FALSE;
            }
	}
}


void		update_hsmc_norm_factor(GST_BUCKET *global_state)
{
	G_TRANS_CELL    *global_trans_cell;
	long double	aggr_rate;

	if (!check_vanishing(global_state))
	{
          for (global_trans_cell = global_state->global_trans_list,
               aggr_rate = 0.0L;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
            if (global_trans_cell->der_global_state != global_state)
              aggr_rate += global_trans_cell->action->info.action->rate->info.expr->value;
	  if (aggr_rate == 0.0L)
	    aggr_rate = 1.0L;
	  hsmc_norm_factor += prob_vector[global_state->global_state_no] * (1.0L / aggr_rate);
	}
}


void		compute_rew_measures(void)
{
        GST_BUCKET      *global_state;
        GST_BUCKET_CELL *global_state_stack,
                        *global_state_cell;
	G_TRANS_CELL    *global_trans_cell;

	/* initialize the stack of the global states to be considered with the initial global state, then */
        /* process one global state at a time until the stack is nonempty; recursion is not employed to */
        /* avoid run-time-support stack overflow in the case of a huge global state space */
        global_state_stack = alloc_gst_bucket_cell(archi_type[spec_no]->info.archi_type->init_global_state,
                                                   NULL);
        archi_type[spec_no]->info.archi_type->init_global_state->global_trans_considered =
	  (archi_type[spec_no]->info.archi_type->psm_index == HSMC);
        while (global_state_stack != NULL)
        {
          /* extract the global state at the top of the stack */
          global_state_cell = global_state_stack;
          global_state_stack = global_state_stack->next_gst_bucket_cell;
          global_state = global_state_cell->gst_bucket;
          free(global_state_cell);
	  /* process the global state at the top of the stack, provided that it is not vanishing in the */
          /* case the performance semantic model is continuous time (the vanishing global states from the */
          /* initial global state to the first nonvanishing one must be overridden) */
          if ((archi_type[spec_no]->info.archi_type->psm_index != HCTMC) ||
              !check_vanishing(global_state))
	    update_rew_measures(global_state);
	  /* push into the stack the not-yet-considered derivative global states of the global transitions */
	  /* of the global state */
          for (global_trans_cell = global_state->global_trans_list;
               (global_trans_cell != NULL);
               global_trans_cell = global_trans_cell->next_g_trans_cell)
	    if ((global_trans_cell->der_global_state->global_trans_considered &&
	         (archi_type[spec_no]->info.archi_type->psm_index != HSMC)) ||
	        (!global_trans_cell->der_global_state->global_trans_considered &&
	         (archi_type[spec_no]->info.archi_type->psm_index == HSMC)))
            {
              global_state_stack = alloc_gst_bucket_cell(global_trans_cell->der_global_state,
                                                         global_state_stack);
              global_trans_cell->der_global_state->global_trans_considered =
		(archi_type[spec_no]->info.archi_type->psm_index == HSMC);
            }
	}
}


void		update_rew_measures(GST_BUCKET *global_state)
{
	G_TRANS_CELL	*global_trans_cell;
	ST_BUCKET_CELL	*rew_measure_cell;
	YB_CELL		*yb_cell;
	long double	yield_global_state_prob,
			bonus_global_state_prob,
			aggr_rate;

	/* determine the yield- and bonus-related global state probabilities */
	if (archi_type[spec_no]->info.archi_type->psm_index != HSMC)
	  yield_global_state_prob = bonus_global_state_prob = prob_vector[global_state->global_state_no];
	else
	{
	  if (check_vanishing(global_state))
	  {
	    yield_global_state_prob = 0.0L;
	    bonus_global_state_prob = prob_vector[global_state->global_state_no] /
				      hsmc_norm_factor;
	  }
	  else
	  {
            for (global_trans_cell = global_state->global_trans_list,
                 aggr_rate = 0.0L;
                 (global_trans_cell != NULL);
                 global_trans_cell = global_trans_cell->next_g_trans_cell)
              if (global_trans_cell->der_global_state != global_state)
                aggr_rate += global_trans_cell->action->info.action->rate->info.expr->value;
	    if (aggr_rate == 0.0L)
	      aggr_rate = 1.0L;
	    yield_global_state_prob = bonus_global_state_prob = prob_vector[global_state->global_state_no] *
								(1.0L / aggr_rate) /
								hsmc_norm_factor;
	  }
	}

	/* update the reward measures */
	for (global_trans_cell = global_state->global_trans_list;
	     (global_trans_cell != NULL);
	     global_trans_cell = global_trans_cell->next_g_trans_cell)
	  for (yb_cell = global_trans_cell->reward_info.yb_list,
	       rew_measure_cell = rew_measure_list;
	       (yb_cell != NULL);
	       yb_cell = yb_cell->next_yb_cell,
	       rew_measure_cell = rew_measure_cell->next_st_bucket_cell)
	    rew_measure_cell->st_bucket->info.rew_measure->value +=
	      yb_cell->yield *
		yield_global_state_prob +
	      yb_cell->bonus *
		bonus_global_state_prob *
		global_trans_cell->action->info.action->rate->info.expr->value;
}


void		print_rew_measures(void)
{
	ST_BUCKET_CELL	*rew_measure_cell;

	/* print the .val file header */
	if (option_index == TRANS_MEASURE_UNIF)
	  fprintf(per_file,
		  "Transient value at time %g ",
		  (double)trans_time);
	else
	  fprintf(per_file,
		  "Stationary value ");
	fprintf(per_file,
		"of the performance measures for %s:",
		archi_type[spec_no]->symbol_lexeme);

	/* print the value of the performance measures */
	for (rew_measure_cell = rew_measure_list;
	     (rew_measure_cell != NULL);
	     rew_measure_cell = rew_measure_cell->next_st_bucket_cell)
	  fprintf(per_file,
		  "\n\n- Value of measure \"%s\":\n\t%g",
		  rew_measure_cell->st_bucket->symbol_lexeme,
		  (double)(rew_measure_cell->st_bucket->info.rew_measure->value));
}
