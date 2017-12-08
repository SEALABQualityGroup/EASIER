#################################################################
#                                                               #
#                                                               #
#			    Makefile				#
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: security_analyzer.o \
	  Makefile

security_analyzer.o: security_analyzer.c \
		     ../headers/Library.h \
		     ../headers/driver.h \
		     ../headers/aemilia_compiler.h \
		     ../headers/equivalence_verifier.h \
		     ../headers/file_handler.h \
		     ../headers/memory_handler.h \
		     ../headers/table_handler.h \
		     Makefile
	gcc -ansi -Wall -c -g -O security_analyzer.c

clean:
	rm -f *.o
