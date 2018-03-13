#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: markov_solver.o \
	  simulator.o \
	  Makefile

markov_solver.o: markov_solver.c \
	         ../headers/Library.h \
	         ../headers/driver.h \
	         ../headers/aemilia_compiler.h \
	         ../headers/simulator.h \
	         ../headers/file_handler.h \
	         ../headers/memory_handler.h \
	         ../headers/table_handler.h \
		 Makefile
	gcc -ansi -Wall -c -g -O markov_solver.c

simulator.o: simulator.c \
	     ../headers/Library.h \
	     ../headers/driver.h \
	     ../headers/symbol_handler.h \
	     ../headers/aemilia_compiler.h \
	     ../headers/file_handler.h \
	     ../headers/memory_handler.h \
	     ../headers/number_handler.h \
	     ../headers/table_handler.h \
	     Makefile
	gcc -ansi -Wall -c -g -O simulator.c

clean:
	rm -f *.o
