#################################################################
#                                                               #
#                                                               #
#                         Makefile                              #
#                                                               #
#                                           (by Marco Bernardo) #
#################################################################


ifeq ($(findstring Windows_, $(OS)), Windows_)
	WIN_EX_EXT = .exe
	WIN_FB_LIB = -L"$(WIN_FLEX_BISON)/lib"
endif

ttkernel: Makefile
	cd driver && make ttkernel 
	cd compiler && make ttkernel
	cd equivalence_verifier && make ttkernel
	cd model_checker && make ttkernel
	cd security_analyzer && make ttkernel
	cd performance_evaluator && make ttkernel
	cd utilities && make ttkernel
	gcc -ansi -Wall -g -O driver/driver.o \
	                      compiler/aemilia_parser.tab.o \
	                      compiler/lex.aemiliayy.o \
	                      compiler/ltl_parser.tab.o \
	                      compiler/lex.ltlyy.o \
	                      compiler/sec_parser.tab.o \
	                      compiler/lex.secyy.o \
	                      compiler/rew_parser.tab.o \
	                      compiler/lex.rewyy.o \
	                      compiler/sim_parser.tab.o \
	                      compiler/lex.simyy.o \
	                      compiler/listing_generator.o \
	                      compiler/symbol_handler.o \
	                      compiler/aemilia_compiler.o \
	                      equivalence_verifier/equivalence_verifier.o \
	                      model_checker/nusmv_connector.o \
	                      security_analyzer/security_analyzer.o \
	                      performance_evaluator/markov_solver.o \
	                      performance_evaluator/simulator.o \
	                      utilities/file_handler.o \
	                      utilities/list_handler.o \
	                      utilities/memory_handler.o \
	                      utilities/number_handler.o \
	                      utilities/string_handler.o \
	                      utilities/table_handler.o \
	  -o ../bin/TTKernel$(WIN_EX_EXT) $(WIN_FB_LIB) -ll -lm

clean:
	cd driver && make clean
	cd compiler && make clean
	cd equivalence_verifier && make clean
	cd model_checker && make clean
	cd security_analyzer && make clean
	cd performance_evaluator && make clean
	cd utilities && make clean

cleanall:
	make clean
	rm -f ../bin/TTKernel$(WIN_EX_EXT)
