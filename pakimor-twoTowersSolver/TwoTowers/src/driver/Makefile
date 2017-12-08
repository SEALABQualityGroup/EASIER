#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: driver.o \
	  Makefile

driver.o: driver.c \
	  ../headers/Library.h \
	  ../headers/aemilia_parser.h \
	  ../headers/ltl_parser.h \
	  ../headers/sec_parser.h \
	  ../headers/rew_parser.h \
	  ../headers/sim_parser.h \
	  ../headers/aemilia_compiler.h \
	  ../headers/equivalence_verifier.h \
	  ../headers/nusmv_connector.h \
	  ../headers/security_analyzer.h \
	  ../headers/markov_solver.h \
	  ../headers/simulator.h \
	  ../headers/file_handler.h \
	  ../headers/table_handler.h \
	  Makefile
	gcc -ansi -Wall -c -g -O driver.c

clean:
	rm -f *.o
