/****************************************************************/
/*                                                              */
/*                                                              */
/*                          driver.c                            */
/*                                                              */
/*                                          (by Marco Bernardo) */
/****************************************************************/
/* This module is the driver of the kernel of TwoTowers. It contains its entry point -- function main() -- */
/* as well as its two exit points -- functions main() and signal_crash(). It is invoked by TTGUI by */
/* issuing a TTKernel command together with the appropriate option as well as the necessary spec, */
/* auxiliary spec and report file names. */
/* This module contains the following functions: */
/* - main(): It extracts the parameters of the TTKernel command, then drives the execution. */
/* - signal_crash(): It signals the occurrence of a crash by writing a message to the report file, then */
/*		     exits. */
/* and the following auxiliary functions: */
/* - get_option_index(): It extracts the option. */
/* - get_spec_path(): It extracts the spec file names. */
/* - get_report_path(): It extracts the report file name. */
/* - drive(): It drives the execution according to the option. */
/* - open_report_file(): It opens the report file and initializes the variables used to communicate with */
/*			 TTGUI. */
/* - close_report_file(): It closes the report file after writing the outcome of the execution of */
/*			  TTKernel, including the running time. */
/****************************************************************/


/****************************************************************/
/* 1. Inclusion of libraries.					*/
/****************************************************************/

#include	<stdlib.h>
#include	<string.h>
#include        <time.h>

#include	"../headers/Library.h"


/****************************************************************/
/* 2. Inclusion of external gvariables and functions.		*/
/****************************************************************/

#include	"../headers/aemilia_parser.h"
#include	"../headers/ltl_parser.h"
#include	"../headers/sec_parser.h"
#include	"../headers/rew_parser.h"
#include	"../headers/sim_parser.h"
#include	"../headers/aemilia_compiler.h"

#include	"../headers/equivalence_verifier.h"

#include	"../headers/nusmv_connector.h"

#include	"../headers/security_analyzer.h"

#include	"../headers/markov_solver.h"
#include	"../headers/simulator.h"

#include        "../headers/file_handler.h"
#include        "../headers/table_handler.h"


/****************************************************************/
/* 3. Definition/declaration of exporting gvariables/functions. */
/****************************************************************/

OPTION_INDEX    option_index;
                        /* option specifying the task to carry out; */
			/* set by get_option_index() and drive() of driver.c; */
			/* used in get_spec_path(), drive(), and close_report() of driver.c, in */
			/* handle_formula_id_var() of mu-parser.y, in set_variable_bucket() of */
			/* symbol-handler.c, in open_sem_model(), handle_init_state(), gen_sem_model(), */
			/* gen_sem_model_trans(), close_sem_model(), print_psm(), and melt_move() of */
			/* aemilia-compiler.c, in verify_fun_formula() and verify_fun_eq_pre() of */
			/* cwb-nc-connector.c, and in compute_distr_yb_measure() and print_prob() of */
			/* markov-solver.c */

ST_BUCKET	*archi_type[2];
			/* pointer to the symbol table bucket for the architectural type defined in each */
			/* .aem spec file; */
			/* set by ; */
			/* used in */

int             spec_no,
                        /* number of the spec file currently being examined; */
			/* set by drive() and open_report() of driver.c; */
			/* used in drive() and close_report() of driver.c, in handle_const_def2(), */
			/* handle_const_def3(), and handle_f_par_list2() of aemilia-parser.y, in */
			/* signal_error() and close_listing() of listing-generator.c, in check_sem_error() */
			/* of aemilia-checker.c, in open_sem_model(), handle_init_state(), */
			/* gen_sem_model(), */
			/* gen_sem_model_trans(), close_sem_model(), compute_repr_alias_state(), */
			/* build_unfolded_state_lexeme(), remove_vanishing_state(), print_psm(), */
			/* melt_move(), compute_pot_move(), and norm_pot_move() of aemilia-compiler.c, in */
			/* compute_distr_yb_measure(), build_matrix(), build_vector(), build_diag_elem(), */
			/* transpose_matrix(), compute_absorbing_scc_num(), solve_gauss_elim(), */
			/* solve_assor(), perform_ssor_step(), solve_unif(), and normalize_vector() of */
			/* markov-solver.c, and in simulate() of simulator.c */
                table_no,
                        /* number of the hash table currently being used; this is needed to permit name */
			/* clashes whenever two spec files are considered; */
			/* set by drive() and open_report() of driver.c and by verify_int_bisim_eq() and */
			/* compute_aggr_move() of i-analyzer.c; */
			/* used in drive() of driver.c, in check_sem_error() of aemilia-checker.c, in */
			/* handle_init_state() of aemilia-compiler.c, in verify_int_bisim_eq(), */
			/* scan_state_space(), and compute_aggr_move() of i-analyzer.c, and in */
			/* init_table(), search_table(), and move_bucket() of table-handler.c */
                error_num[2],
                        /* number of lexical, syntax, type and semantic errors for each spec file; */
			/* set by open_report() of driver.c and by signal_error() of listing-generator.c; */
			/* used in drive() and close_report() of driver.c and in close_listing() of */
			/* listing-generator.c */
                warning_num[2];
                        /* number of warnings for each spec file; */
			/* set by open_report() of driver.c and by signal_error() of listing-generator.c; */
			/* used in close_report() of driver.c and in close_listing() of */
			/* listing-generator.c */


void		signal_crash(CRASH_INDEX,
			     char *);


/****************************************************************/
/* 4. Definition/declaration of private gvariables/functions.	*/
/****************************************************************/

PRIVATE FILE            *report_file;
                                /* report file; */
				/* set by open_report(); */
				/* used in signal_crash() and close_report() */

PRIVATE	char		*spec_path[2],
				/* name of the spec files; */
				/* set by get_spec_path(); */
				/* used in drive() */
			*report_path;
				/* name of the report file; */
				/* set by get_report_path(); */
				/* used in open_report() */


PRIVATE void		get_option_index(char **),
			get_spec_path(int,
				      char **),
			get_report_path(int,
				        char **),
			drive(void),
			open_report_file(void),
			close_report_file(void);


/****************************************************************/
/* 5. Definition of exporting functions.			*/
/****************************************************************/

int		main(int  argc,
		     char **argv)
{
	get_option_index(argv);
	get_spec_path(argc,
		      argv);
	get_report_path(argc,
		        argv);
	drive();
	return(0);
}


void            signal_crash(CRASH_INDEX crash_index,
                             char        *info)
{
	static	char		*crash_msg[] =
	{
        	"Not enough memory!",
        	"Unable to open file %s!",
        	"Exponential rate not greater than zero: %s!",
        	"Priority not greater than zero: %s!",
        	"Weight not greater than zero: %s!",
        	"Exponential rate cannot be evaluated: %s!",
        	"Weight cannot be evaluated: %s!",
        	"Array length mismatch: %s!",
        	"Out-of-range bounded integer: %s!",
        	"Division by zero: %s!",
        	"Modulus by non positive integer: %s!",
        	"Invalid parameter of power operation: %s!",
        	"Logarithm of non positive number: %s!",
        	"Square root of negative number: %s!",
        	"Invalid parameter of continuous uniform distribution: %s!",
        	"Invalid parameter of Erlang distribution: %s!",
        	"Invalid parameter of gamma distribution: %s!",
        	"Invalid parameter of exponential distribution: %s!",
        	"Invalid parameter of Weibull distribution: %s!",
        	"Invalid parameter of beta distribution: %s!",
        	"Invalid parameter of normal distribution: %s!",
        	"Invalid parameter of Pareto distribution: %s!",
        	"Invalid parameter of bounded Pareto distribution: %s!",
        	"Invalid parameter of discrete uniform distribution: %s!",
        	"Invalid parameter of Bernoulli distribution: %s!",
        	"Invalid parameter of binomial distribution: %s!",
        	"Invalid parameter of Poisson distribution: %s!",
        	"Invalid parameter of negative binomial distribution: %s!",
        	"Invalid parameter of geometric distribution: %s!",
        	"Invalid parameter of Pascal distribution: %s!",
        	"Attempt to access the first element of an empty list: %s!",
        	"Attempt to remove an element not present in a given list: %s!",
        	"Attempt to read an element not present in a given array: %s!",
        	"Attempt to write an element not present in a given array: %s!",
        	"Deadlock state encountered during simulation!",
        	"Open state encountered during simulation!",
        	"Unable to read trace file %s during simulation!"
	};

        switch (crash_index)
        {
          case MEM_CRASH:
          case DEADLOCK_STATE_SIM_CRASH:
          case OPEN_STATE_SIM_CRASH:
            fprintf(report_file,
                    crash_msg[crash_index]);
            break;
          case FILE_CRASH:
          case EXP_RATE_LE_ZERO_CRASH:
          case PRIORITY_LE_ZERO_CRASH:
          case WEIGHT_LE_ZERO_CRASH:
          case EXP_RATE_UNEVAL_CRASH:
          case WEIGHT_UNEVAL_CRASH:
          case ARRAY_LENGTH_MISMATCH_CRASH:
          case OUT_OF_RANGE_BOUNDED_INTEGER_CRASH:
          case DIV_OP_CRASH:
          case MOD_OP_CRASH:
          case POWER_OP_CRASH:
          case LOG_OP_CRASH:
          case SQRT_OP_CRASH:
          case C_UNIFORM_OP_CRASH:
          case ERLANG_OP_CRASH:
          case GAMMA_OP_CRASH:
          case EXPONENTIAL_OP_CRASH:
          case WEIBULL_OP_CRASH:
          case BETA_OP_CRASH:
          case NORMAL_OP_CRASH:
          case PARETO_OP_CRASH:
          case B_PARETO_OP_CRASH:
          case D_UNIFORM_OP_CRASH:
          case BERNOULLI_OP_CRASH:
          case BINOMIAL_OP_CRASH:
          case POISSON_OP_CRASH:
          case NEG_BINOMIAL_OP_CRASH:
          case GEOMETRIC_OP_CRASH:
          case PASCAL_OP_CRASH:
          case FIRST_OP_CRASH:
          case REMOVE_OP_CRASH:
          case READ_OP_CRASH:
          case WRITE_OP_CRASH:
          case TRACE_READ_CRASH:
            fprintf(report_file,
                    crash_msg[crash_index],
                    info);
            break;
        }
        fclose(report_file);
        exit(0);
}


/****************************************************************/
/* 6. Definition of private functions.				*/
/****************************************************************/

void		get_option_index(char **argv)
{
	option_index = argv[1][1];
}


void		get_spec_path(int  argc,
			      char **argv)
{
	spec_path[0] = argv[2];
	if ((option_index == TRANS_DISTR_UNIF) ||
	    (option_index == TRANS_MEASURE_UNIF))
	  trans_time = (long double)atof(argv[3]);
	if ((argc >= 5) &&
	    (option_index != TRANS_DISTR_UNIF))
	  spec_path[1] = argv[argc - 2];
}


void		get_report_path(int  argc,
			        char **argv)
{
	report_path = argv[argc - 1];
}


void		drive(void)
{
	BOOLEAN		positive_outcome;

	init_hash_tables();
	open_report_file();
	switch (option_index)
	{
	  case PARSING:
	    parse_aemilia(spec_path[0]);
	    break;
	  case ISM_SIZE:
	  case FSM_SIZE:
	  case PSM_SIZE:
	  case ISM:
	  case FSM:
	  case PSM:
	    parse_aemilia(spec_path[0]);
	    if (error_num[0] == 0)
	    {
	      open_sem_model(spec_path[0]);
	      generate_aei_state_spaces();
	      generate_global_state_space();
	      close_sem_model();
	    }
	    break;
	  case CTL_MODEL_CHECK:
	  case LTL_MODEL_CHECK:
	    parse_aemilia(spec_path[0]);
	    if ((error_num[0] == 0) &&
		(archi_type[0]->info.archi_type->value_passing != (char)SYMBOLIC_VP))
	    {
	      spec_no = 1;
	      parse_ltl(spec_path[1]);
	      if (error_num[1] == 0)
	      {
	        spec_no = 0;
	        generate_aei_state_spaces();
		check_property_list(spec_path[0]);
	      }
	    }
	    break;
	  case STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK:
	  case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
	  case STRONG_MARKOVIAN_BISIM_EQUIV_CHECK:
	  case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	    parse_aemilia(spec_path[0]);
	    if ((error_num[0] == 0) &&
		(archi_type[0]->info.archi_type->value_passing != (char)SYMBOLIC_VP))
	    {
	      spec_no = 1;
	      table_no = 1;
	      parse_aemilia(spec_path[1]);
	      if ((error_num[1] == 0) &&
		  (archi_type[1]->info.archi_type->value_passing != (char)SYMBOLIC_VP))
	      {
	        open_equiv_check_result(spec_path[0]);
	        for (spec_no = 0,
		     table_no = 0;
		     (spec_no <= 1);
		     spec_no++,
		     table_no++)
		{
	          generate_aei_state_spaces();
		  generate_global_state_space();
		  switch (option_index)
		  {
		    case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
		      generate_weak_functional_global_trans_rel();
		      break;
		    case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
		      if (archi_type[spec_no]->info.archi_type->psm_index == HCTMC)
		        generate_weak_markovian_global_trans_rel();
		      break;
		    default:
		      break;
		  }
		}
		spec_no = 0;
		table_no = 0;
		positive_outcome = check_bisim_equiv();
	        close_equiv_check_result(positive_outcome);
	      }
	    }
	    break;
	  case NON_INTERFERENCE_ANALYSIS:
	  case NON_DED_ON_COMP_ANALYSIS:
	    parse_aemilia(spec_path[0]);
	    if ((error_num[0] == 0) &&
		(archi_type[0]->info.archi_type->value_passing != (char)SYMBOLIC_VP))
	    {
	      spec_no = 1;
	      parse_sec(spec_path[1]);
	      if (error_num[1] == 0)
		switch (option_index)
		{
		  case NON_INTERFERENCE_ANALYSIS:
		    analyze_non_interference(spec_path[0]);
		    break;
		  case NON_DED_ON_COMP_ANALYSIS:
		    analyze_non_ded_on_comp(spec_path[0]);
		    break;
		  default:
		    break;
		}
	    }
	    break;
	  case STAT_DISTR_GAUSS_ELIM:
	  case STAT_DISTR_ASSOR:
	  case TRANS_DISTR_UNIF:
	  case STAT_MEASURE_GAUSS_ELIM:
	  case STAT_MEASURE_ASSOR:
	  case TRANS_MEASURE_UNIF:
	    parse_aemilia(spec_path[0]);
	    if (error_num[0] == 0)
	    {
	      if ((option_index == STAT_MEASURE_GAUSS_ELIM) ||
		  (option_index == STAT_MEASURE_ASSOR) ||
		  (option_index == TRANS_MEASURE_UNIF))
	      {
	        spec_no = 1;
	        parse_rew(spec_path[1]);
	        if (error_num[1] == 0)
	          spec_no = 0;
	      }
	      if (error_num[spec_no] == 0)
	      {
	        open_sem_model(spec_path[0]);
	        generate_aei_state_spaces();
	        generate_global_state_space();
	        close_sem_model();
	        if (archi_type[0]->info.archi_type->performance_closed)
	          compute_distr_rew_measure(spec_path[0]);
	      }
	    }
	    break;
	  case SIMULATION:
	    parse_aemilia(spec_path[0]);
	    if (error_num[0] == 0)
	    {
	      spec_no = 1;
	      parse_sim(spec_path[1]);
	      if (error_num[1] == 0)
	      {
	        spec_no = 0;
	        generate_aei_state_spaces();
	        simulate(spec_path[0],
			 spec_path[1]);
	      }
	    }
	    break;
	}
	close_report_file();
}


void		open_report_file(void)
{
        report_file = new_fopen(report_path,
				NULL,
				"w");
	error_num[0] = 0;
	error_num[1] = 0;
	warning_num[0] = 0;
	warning_num[1] = 0;
	spec_no = 0;
	table_no = 0;
}


void		close_report_file(void)
{
	switch (option_index)
	{
	  case PARSING:
	    fprintf(report_file,
		    "%d %s, %d %s.    [%ld sec]",
		    error_num[spec_no],
		    (error_num[spec_no] == 1)?
		      "error":
		      "errors",
		    warning_num[spec_no],
		    (warning_num[spec_no] == 1)?
		      "warning":
		      "warnings",
		    clock() / CLOCKS_PER_SEC);
	    break;
	  case ISM_SIZE:
	  case FSM_SIZE:
	    if (error_num[spec_no] == 0)
	    {
	      if (warning_num[spec_no] == 0)
	        fprintf(report_file,
		        "Compilation completed.    [%ld sec]\n%d",
			clock() / CLOCKS_PER_SEC,
		        spec_no);
	      else
	        fprintf(report_file,
		        "Compilation completed (%d %s).    [%ld sec]\n%d",
			warning_num[spec_no],
			(warning_num[spec_no] == 1)?
			  "warning":
			  "warnings",
			clock() / CLOCKS_PER_SEC,
		        spec_no);
	    }
	    else
	      fprintf(report_file,
		      "Compilation not carried out: the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    break;
	  case PSM_SIZE:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[spec_no]->info.archi_type->performance_closed)
	      {
	        if (warning_num[spec_no] == 0)
	          fprintf(report_file,
		          "Compilation completed.    [%ld sec]\n%d",
			  clock() / CLOCKS_PER_SEC,
			  spec_no);
		else
	          fprintf(report_file,
		          "Compilation completed (%d %s).    [%ld sec]\n%d",
			  warning_num[spec_no],
			  (warning_num[spec_no] == 1)?
			    "warning":
			    "warnings",
			  clock() / CLOCKS_PER_SEC,
			  spec_no);
	      }
	      else
		fprintf(report_file,
			"Compilation not carried out: the specification is not performance closed!");
	    }
	    else
	      fprintf(report_file,
		      "Compilation not carried out: the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    break;
	  case ISM:
	    if (error_num[spec_no] == 0)
	    {
	      if (warning_num[spec_no] == 0)
	      {
	        fprintf(report_file,
		        "%d %s (%d tang., %d vanish., %d open, %d deadl.), ",
		        archi_type[spec_no]->info.archi_type->state_num,
		        (archi_type[spec_no]->info.archi_type->state_num == 1)?
			  "state":
			  "states",
		        archi_type[spec_no]->info.archi_type->state_num -
			  archi_type[spec_no]->info.archi_type->vanishing_state_num -
			  archi_type[spec_no]->info.archi_type->open_state_num -
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->vanishing_state_num,
		        archi_type[spec_no]->info.archi_type->open_state_num,
		        archi_type[spec_no]->info.archi_type->absorbing_state_num);
	        fprintf(report_file,
		        "%d %s (%d obser., %d invis.; %d exp. timed, %d immed., %d pass.).    ",
		        archi_type[spec_no]->info.archi_type->trans_num,
		        (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			  "transition":
			  "transitions",
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->immediate_trans_num -
			  archi_type[spec_no]->info.archi_type->passive_trans_num,
		        archi_type[spec_no]->info.archi_type->immediate_trans_num,
		        archi_type[spec_no]->info.archi_type->passive_trans_num);
	      }
	      else
	      {
	        fprintf(report_file,
		        "%d %s (%d tang., %d vanish., %d open, %d deadl.), ",
		        archi_type[spec_no]->info.archi_type->state_num,
		        (archi_type[spec_no]->info.archi_type->state_num == 1)?
			  "state":
			  "states",
		        archi_type[spec_no]->info.archi_type->state_num -
			  archi_type[spec_no]->info.archi_type->vanishing_state_num -
			  archi_type[spec_no]->info.archi_type->open_state_num -
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->vanishing_state_num,
		        archi_type[spec_no]->info.archi_type->open_state_num,
		        archi_type[spec_no]->info.archi_type->absorbing_state_num);
	        fprintf(report_file,
		        "%d %s (%d obser., %d invis.; %d exp. timed, %d immed., %d pass.)  -  (%d %s).    ",
		        archi_type[spec_no]->info.archi_type->trans_num,
		        (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			  "transition":
			  "transitions",
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->immediate_trans_num -
			  archi_type[spec_no]->info.archi_type->passive_trans_num,
		        archi_type[spec_no]->info.archi_type->immediate_trans_num,
		        archi_type[spec_no]->info.archi_type->passive_trans_num,
			warning_num[spec_no],
			(warning_num[spec_no] == 1)?
			  "warning":
			  "warnings");
	      }
	      fprintf(report_file,
		      "[%ld sec]",
		      clock() / CLOCKS_PER_SEC);
	    }
	    else
	      fprintf(report_file,
		      "Compilation not carried out: the specification contains %d %s and %d %s!",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings");
	    break;
	  case FSM:
	    if (error_num[spec_no] == 0)
	    {
	      if (warning_num[spec_no] == 0)
	        fprintf(report_file,
		        "%d %s (%d nondeadlocked, %d deadlocked), %d %s (%d observable, %d invisible).    ",
		        archi_type[spec_no]->info.archi_type->state_num,
		        (archi_type[spec_no]->info.archi_type->state_num == 1)?
			  "state":
			  "states",
		        archi_type[spec_no]->info.archi_type->state_num -
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->trans_num,
		        (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			  "transition":
			  "transitions",
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->invisible_trans_num);
	      else
	      {
	        fprintf(report_file,
		        "%d %s (%d nondeadlocked, %d deadlocked), %d %s (%d observable, %d invisible)  ",
		        archi_type[spec_no]->info.archi_type->state_num,
		        (archi_type[spec_no]->info.archi_type->state_num == 1)?
			  "state":
			  "states",
		        archi_type[spec_no]->info.archi_type->state_num -
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->absorbing_state_num,
		        archi_type[spec_no]->info.archi_type->trans_num,
		        (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			  "transition":
			  "transitions",
		        archi_type[spec_no]->info.archi_type->trans_num -
			  archi_type[spec_no]->info.archi_type->invisible_trans_num,
		        archi_type[spec_no]->info.archi_type->invisible_trans_num);
	        fprintf(report_file,
		        "-  (%d %s).    ",
			warning_num[spec_no],
			(warning_num[spec_no] == 1)?
			  "warning":
			  "warnings");
	      }
	      fprintf(report_file,
		      "[%ld sec]",
		      clock() / CLOCKS_PER_SEC);
	    }
	    else
	      fprintf(report_file,
		      "Compilation not carried out: the specification contains %d %s and %d %s!",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings");
	    break;
	  case PSM:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[spec_no]->info.archi_type->performance_closed)
	      {
		if (warning_num[spec_no] == 0)
		  fprintf(report_file,
			  "%d %s (%d nonabsorbing, %d absorbing), %d %s.    ",
			  archi_type[spec_no]->info.archi_type->state_num,
			  (archi_type[spec_no]->info.archi_type->state_num == 1)?
			    "state":
			    "states",
			  archi_type[spec_no]->info.archi_type->state_num -
			    archi_type[spec_no]->info.archi_type->absorbing_state_num,
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
			  archi_type[spec_no]->info.archi_type->trans_num,
			  (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			    "transition":
			    "transitions");
		else
		  fprintf(report_file,
			  "%d %s (%d nonabsorbing, %d absorbing), %d %s  -  (%d %s).    ",
			  archi_type[spec_no]->info.archi_type->state_num,
			  (archi_type[spec_no]->info.archi_type->state_num == 1)?
			    "state":
			    "states",
			  archi_type[spec_no]->info.archi_type->state_num -
			    archi_type[spec_no]->info.archi_type->absorbing_state_num,
			  archi_type[spec_no]->info.archi_type->absorbing_state_num,
			  archi_type[spec_no]->info.archi_type->trans_num,
			  (archi_type[spec_no]->info.archi_type->trans_num == 1)?
			    "transition":
			    "transitions",
			  warning_num[spec_no],
			  (warning_num[spec_no] == 1)?
			    "warning":
			    "warnings");
	        fprintf(report_file,
		        "[%ld sec]",
		        clock() / CLOCKS_PER_SEC);
	      }
	      else
		fprintf(report_file,
			"Compilation not carried out: the specification is not performance closed!");
	    }
	    else
	      fprintf(report_file,
		      "Compilation not carried out: the specification contains %d %s and %d %s!",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"transition":
			"transitions");
	    break;
	  case STRONG_FUNCTIONAL_BISIM_EQUIV_CHECK:
	  case WEAK_FUNCTIONAL_BISIM_EQUIV_CHECK:
	  case STRONG_MARKOVIAN_BISIM_EQUIV_CHECK:
	  case WEAK_MARKOVIAN_BISIM_EQUIV_CHECK:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[spec_no]->info.archi_type->value_passing != (char)SYMBOLIC_VP)
	      {
	        if (warning_num[0] + warning_num[1] == 0)
	          fprintf(report_file,
		          "Equivalence verification completed.    [%ld sec]\n%d",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	        else
	          fprintf(report_file,
		          "Equivalence checking completed (%d %s).    [%ld sec]\n%d",
			  warning_num[0] + warning_num[1],
			  (warning_num[0] + warning_num[1] == 1)?
			    "warning":
			    "warnings",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	      }
	      else
		fprintf(report_file,
			"Equivalence checking not carried out: symbolic handling of value passing!");
	    }
	    else
	    {
	      fprintf(report_file,
		      "Equivalence checking not carried out: ");
	      fprintf(report_file,
		      "the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    }
	    break;
	  case CTL_MODEL_CHECK:
	  case LTL_MODEL_CHECK:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[0]->info.archi_type->value_passing != (char)SYMBOLIC_VP)
	      {
	        if (warning_num[0] + warning_num[1] == 0)
	          fprintf(report_file,
		          "Model checking completed.    [%ld sec]\n%d",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	        else
	          fprintf(report_file,
		          "Model checking completed (%d %s).    [%ld sec]\n%d",
			  warning_num[0] + warning_num[1],
			  (warning_num[0] + warning_num[1] == 1)?
			    "warning":
			    "warnings",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	      }
	      else
		fprintf(report_file,
			"Model checking not carried out: symbolic handling of value passing!");
	    }
	    else
	      fprintf(report_file,
		      "Model checking not carried out: the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    break;
	  case NON_INTERFERENCE_ANALYSIS:
	  case NON_DED_ON_COMP_ANALYSIS:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[0]->info.archi_type->value_passing != (char)SYMBOLIC_VP)
	      {
	        if (warning_num[0] + warning_num[1] == 0)
	          fprintf(report_file,
		          "Security analysis completed.    [%ld sec]\n%d",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	        else
	          fprintf(report_file,
		          "Security analysis completed (%d %s).    [%ld sec]\n%d",
			  warning_num[0] + warning_num[1],
			  (warning_num[0] + warning_num[1] == 1)?
			    "warning":
			    "warnings",
			  clock() / CLOCKS_PER_SEC,
		          spec_no);
	      }
	      else
		fprintf(report_file,
			"Security analysis not carried out: symbolic handling of value passing!");
	    }
	    else
	      fprintf(report_file,
		      "Security analysis not carried out: the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    break;
	  case STAT_DISTR_GAUSS_ELIM:
	  case STAT_DISTR_ASSOR:
	  case TRANS_DISTR_UNIF:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[spec_no]->info.archi_type->performance_closed)
	      {
		if (warning_num[0] + warning_num[1] == 0)
	          fprintf(report_file,
		          "State probability distribution calculation completed.    ");
		else
	          fprintf(report_file,
		          "State probability distribution calculation completed (%d %s).    ",
			  warning_num[0] + warning_num[1],
			  (warning_num[0] + warning_num[1] == 1)?
			    "warning":
			    "warnings");
	        fprintf(report_file,
		        "[%ld sec]\n%d",
			clock() / CLOCKS_PER_SEC,
			spec_no);
	      }
	      else
	      {
		fprintf(report_file,
			"State probability distribution calculation not carried out: ");
		fprintf(report_file,
			"the specification is not performance closed!");
	      }
	    }
	    else
	    {
	      fprintf(report_file,
		      "State probability distribution calculation not carried out: ");
	      fprintf(report_file,
		      "the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    }
	    break;
	  case STAT_MEASURE_GAUSS_ELIM:
	  case STAT_MEASURE_ASSOR:
	  case TRANS_MEASURE_UNIF:
	    if (error_num[spec_no] == 0)
	    {
	      if (archi_type[spec_no]->info.archi_type->performance_closed)
	      {
		if (warning_num[0] + warning_num[1] == 0)
	          fprintf(report_file,
		          "Performance evaluation completed.    ");
		else
	          fprintf(report_file,
		          "Performance evaluation completed (%d %s).    ",
			  warning_num[0] + warning_num[1],
			  (warning_num[0] + warning_num[1] == 1)?
			    "warning":
			    "warnings");
	        fprintf(report_file,
		        "[%ld sec]\n%d",
			clock() / CLOCKS_PER_SEC,
			spec_no);
	      }
	      else
	      {
		fprintf(report_file,
			"Performance evaluation not carried out: ");
		fprintf(report_file,
			"the specification is not performance closed!");
	      }
	    }
	    else
	    {
	      fprintf(report_file,
		      "Performance evaluation not carried out: ");
	      fprintf(report_file,
		      "the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    }
	    break;
	  case SIMULATION:
	    if (error_num[spec_no] == 0)
	    {
	      if (warning_num[0] + warning_num[1] == 0)
	        fprintf(report_file,
		        "Simulation completed.    [%ld sec]\n%d",
			clock() / CLOCKS_PER_SEC,
		        spec_no);
	      else
	        fprintf(report_file,
		        "Simulation completed (%d %s).    [%ld sec]\n%d",
			warning_num[0] + warning_num[1],
			(warning_num[0] + warning_num[1] == 1)?
			  "warning":
			  "warnings",
			clock() / CLOCKS_PER_SEC,
		        spec_no);
	    }
	    else
	      fprintf(report_file,
		      "Simulation not carried out: the specification contains %d %s and %d %s!\n%d",
		      error_num[spec_no],
		      (error_num[spec_no] == 1)?
			"error":
			"errors",
		      warning_num[spec_no],
		      (warning_num[spec_no] == 1)?
			"warning":
			"warnings",
		      spec_no);
	    break;
	}
	fclose(report_file);
}
