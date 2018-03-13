#################################################################
#                                                               #
#                                                               #
#			    Makefile				#
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: equivalence_verifier.o \
	  Makefile

equivalence_verifier.o: equivalence_verifier.c \
			../headers/Library.h \
			../headers/driver.h \
			../headers/aemilia_compiler.h \
			../headers/symbol_handler.h \
			../headers/file_handler.h \
			../headers/list_handler.h \
			../headers/memory_handler.h \
			../headers/number_handler.h \
			../headers/table_handler.h \
			Makefile
	gcc -ansi -Wall -c -g -O equivalence_verifier.c

clean:
	rm -f *.o
