#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: file_handler.o \
	  list_handler.o \
	  memory_handler.o \
	  number_handler.o \
	  string_handler.o \
	  table_handler.o \
	  Makefile

file_handler.o: file_handler.c \
	        ../headers/Library.h \
	        ../headers/driver.h \
	        ../headers/memory_handler.h \
		Makefile
	gcc -ansi -Wall -c -g -O file_handler.c

list_handler.o: list_handler.c \
		../headers/Library.h \
	        ../headers/memory_handler.h \
		Makefile
	gcc -ansi -Wall -c -g -O list_handler.c

memory_handler.o: memory_handler.c \
		  ../headers/Library.h \
		  ../headers/driver.h \
		  ../headers/string_handler.h \
		  ../headers/table_handler.h \
		  Makefile
	gcc -ansi -Wall -c -g -O memory_handler.c

number_handler.o: number_handler.c \
	          ../headers/Library.h \
		  Makefile
	gcc -ansi -Wall -c -g -O number_handler.c

string_handler.o: string_handler.c \
	          ../headers/Library.h \
	          ../headers/memory_handler.h \
		  Makefile
	gcc -ansi -Wall -c -g -O string_handler.c

table_handler.o: table_handler.c \
	         ../headers/Library.h \
	         ../headers/driver.h \
	         ../headers/memory_handler.h \
		 Makefile
	gcc -ansi -Wall -c -g -O table_handler.c

clean:
	rm -f *.o
