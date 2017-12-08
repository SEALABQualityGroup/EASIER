#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: nusmv_connector.o \
	  Makefile

nusmv_connector.o: nusmv_connector.c \
	           ../headers/Library.h \
	           ../headers/driver.h \
	           ../headers/file_handler.h \
	           ../headers/list_handler.h \
	           ../headers/memory_handler.h \
	           ../headers/number_handler.h \
	           ../headers/string_handler.h \
	           ../headers/table_handler.h \
		   Makefile
	gcc -ansi -Wall -c -g -O nusmv_connector.c

clean:
	rm -f *.o
