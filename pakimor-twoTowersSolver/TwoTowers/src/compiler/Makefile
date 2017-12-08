#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################

ttkernel: aemilia_parser.tab.o \
	  lex.aemiliayy.o \
	  ltl_parser.tab.o \
	  lex.ltlyy.o \
	  sec_parser.tab.o \
	  lex.secyy.o \
	  rew_parser.tab.o \
	  lex.rewyy.o \
	  sim_parser.tab.o \
	  lex.simyy.o \
	  listing_generator.o \
	  symbol_handler.o \
	  aemilia_compiler.o \
	  Makefile

aemilia_parser.tab.o: aemilia_parser.y \
		      ../headers/Library.h \
		      ../headers/driver.h \
		      ../headers/aemilia_scanner.h \
		      ../headers/listing_generator.h \
		      ../headers/symbol_handler.h \
		      ../headers/aemilia_compiler.h \
		      ../headers/file_handler.h \
		      ../headers/list_handler.h \
		      ../headers/memory_handler.h \
		      ../headers/number_handler.h \
		      ../headers/string_handler.h \
		      ../headers/table_handler.h \
		      Makefile
	bison -d -paemiliayy aemilia_parser.y
	gcc -ansi -Wall -c -g -O aemilia_parser.tab.c

lex.aemiliayy.o: aemilia_scanner.l \
	         ../headers/Library.h \
	         aemilia_parser.tab.h \
	         ../headers/listing_generator.h \
	         ../headers/symbol_handler.h \
	         ../headers/memory_handler.h \
	         ../headers/table_handler.h \
		 Makefile
	flex -Paemiliayy aemilia_scanner.l
	gcc -ansi -c -g -O lex.aemiliayy.c

ltl_parser.tab.o: ltl_parser.y \
		  ../headers/Library.h \
		  ../headers/driver.h \
		  ../headers/ltl_scanner.h \
		  ../headers/listing_generator.h \
		  ../headers/symbol_handler.h \
		  ../headers/nusmv_connector.h \
		  ../headers/file_handler.h \
		  ../headers/list_handler.h \
		  ../headers/memory_handler.h \
		  ../headers/table_handler.h \
		  Makefile
	bison -d -pltlyy ltl_parser.y
	gcc -ansi -Wall -c -g -O ltl_parser.tab.c

lex.ltlyy.o: ltl_scanner.l \
	     ../headers/Library.h \
	     ltl_parser.tab.h \
	     ../headers/listing_generator.h \
	     ../headers/symbol_handler.h \
	     ../headers/table_handler.h \
	     Makefile
	flex -Pltlyy ltl_scanner.l
	gcc -ansi -c -g -O lex.ltlyy.c

sec_parser.tab.o: sec_parser.y \
		  ../headers/Library.h \
		  ../headers/driver.h \
		  ../headers/sec_scanner.h \
		  ../headers/listing_generator.h \
		  ../headers/symbol_handler.h \
		  ../headers/file_handler.h \
		  ../headers/memory_handler.h \
		  ../headers/table_handler.h \
		  Makefile
	bison -d -psecyy sec_parser.y
	gcc -ansi -Wall -c -g -O sec_parser.tab.c

lex.secyy.o: sec_scanner.l \
	     ../headers/Library.h \
	     sec_parser.tab.h \
	     ../headers/listing_generator.h \
	     ../headers/symbol_handler.h \
	     ../headers/table_handler.h \
	     Makefile
	flex -Psecyy sec_scanner.l
	gcc -ansi -c -g -O lex.secyy.c

rew_parser.tab.o: rew_parser.y \
		  ../headers/Library.h \
		  ../headers/driver.h \
		  ../headers/rew_scanner.h \
		  ../headers/listing_generator.h \
		  ../headers/symbol_handler.h \
		  ../headers/markov_solver.h \
		  ../headers/file_handler.h \
		  ../headers/list_handler.h \
		  ../headers/memory_handler.h \
		  ../headers/table_handler.h \
		  Makefile
	bison -d -prewyy rew_parser.y
	gcc -ansi -Wall -c -g -O rew_parser.tab.c

lex.rewyy.o: rew_scanner.l \
	     ../headers/Library.h \
	     rew_parser.tab.h \
	     ../headers/listing_generator.h \
	     ../headers/symbol_handler.h \
	     ../headers/table_handler.h \
	     Makefile
	flex -Prewyy rew_scanner.l
	gcc -ansi -c -g -O lex.rewyy.c

sim_parser.tab.o: sim_parser.y \
		  ../headers/Library.h \
		  ../headers/driver.h \
		  ../headers/sim_scanner.h \
		  ../headers/listing_generator.h \
		  ../headers/symbol_handler.h \
		  ../headers/simulator.h \
		  ../headers/file_handler.h \
		  ../headers/list_handler.h \
		  ../headers/memory_handler.h \
		  ../headers/number_handler.h \
		  ../headers/table_handler.h \
		  Makefile
	bison -d -psimyy sim_parser.y
	gcc -ansi -Wall -c -g -O sim_parser.tab.c

lex.simyy.o: sim_scanner.l \
	     ../headers/Library.h \
	     sim_parser.tab.h \
	     ../headers/listing_generator.h \
	     ../headers/symbol_handler.h \
	     ../headers/table_handler.h \
	     Makefile
	flex -Psimyy sim_scanner.l
	gcc -ansi -c -g -O lex.simyy.c

listing_generator.o: listing_generator.c \
		     ../headers/Library.h \
		     ../headers/driver.h \
		     ../headers/file_handler.h \
		     ../headers/memory_handler.h \
		     Makefile
	gcc -ansi -Wall -c -g -O listing_generator.c

symbol_handler.o: symbol_handler.c \
	          ../headers/Library.h \
	          ../headers/driver.h \
		  ../headers/listing_generator.h \
		  ../headers/simulator.h \
	          ../headers/list_handler.h \
	          ../headers/memory_handler.h \
	          ../headers/number_handler.h \
	          ../headers/string_handler.h \
	          ../headers/table_handler.h \
		  Makefile
	gcc -ansi -Wall -c -g -O symbol_handler.c

aemilia_compiler.o: aemilia_compiler.c \
		    ../headers/Library.h \
		    ../headers/driver.h \
		    ../headers/symbol_handler.h \
		    ../headers/markov_solver.h \
		    ../headers/file_handler.h \
		    ../headers/list_handler.h \
		    ../headers/memory_handler.h \
		    ../headers/number_handler.h \
		    ../headers/string_handler.h \
		    ../headers/table_handler.h \
		    Makefile
	gcc -ansi -Wall -c -g -O aemilia_compiler.c

clean:
	rm -f *.o \
	      *.output \
	      aemilia_parser.tab.c \
	      aemilia_parser.tab.h \
	      lex.aemiliayy.c \
	      ltl_parser.tab.c \
	      ltl_parser.tab.h \
	      lex.ltlyy.c \
	      sec_parser.tab.c \
	      sec_parser.tab.h \
	      lex.secyy.c \
	      rew_parser.tab.c \
	      rew_parser.tab.h \
	      lex.rewyy.c \
	      sim_parser.tab.c \
	      sim_parser.tab.h \
	      lex.simyy.c
