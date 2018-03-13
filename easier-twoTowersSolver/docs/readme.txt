======================================================================


    This document explains how to install and run TwoTowers 5.1 on
         a computer with a Linux or Windows operating system.


======================================================================


                     HOW TO INSTALL TwoTowers 5.1


1) Create a new directory for TwoTowers 5.1, move to this directory
   the file TwoTowers.tar.gz you downloaded, then expand the file
   (symbol > denotes the prompt of the operating system shell):

     > gunzip TwoTowers.tar.gz
     > tar -xvf TwoTowers.tar


2) Make sure that the following directory structure has been created:

     . bin
     |_. TTKernel.exe
     . docs
     |_. license.txt
       . manual.pdf
       . readme.txt
     . gui
     |_. TTGUI
     . src
     |_. Makefile
       . compiler
       |_. Makefile
         . aemilia_compiler.c
         . aemilia_parser.y
         . aemilia_scanner.l
         . listing_generator.c
         . ltl_parser.y
         . ltl_scanner.l
         . rew_parser.y
         . rew_scanner.l
         . sec_parser.y
         . sec_scanner.l
         . sim_parser.y
         . sim_scanner.l
         . symbol_handler.c
       . driver
       |_. Makefile
         . driver.c
       . equivalence_verifier
       |_. Makefile
         . equivalence_verifier.c
       . headers
       |_. Library.h
         . aemilia_compiler.h
         . aemilia_parser.h
         . aemilia_scanner.h
         . driver.h
         . equivalence_verifier.h
         . file_handler.h
         . list_handler.h
         . listing_generator.h
         . ltl_parser.h
         . ltl_scanner.h
         . markov_solver.h
         . memory_handler.h
         . number_handler.h
         . nusmv_connector.h
         . rew_parser.h
         . rew_scanner.h
         . sec_parser.h
         . sec_scanner.h
         . security_analyzer.h
         . sim_parser.h
         . sim_scanner.h
         . simulator.h
         . string_handler.h
         . symbol_handler.h
         . table_handler.h
       . model_checker
       |_. Makefile
         . nusmv_connector.c
       . performance_evaluator
       |_. Makefile
         . markov_solver.c
         . simulator.c
       . security_analyzer
       |_. Makefile
         . security_analyzer.c
       . utilities
       |_. Makefile
         . file_handler.c
         . list_handler.c
         . memory_handler.c
         . number_handler.c
         . string_handler.c
         . table_handler.c
     . win_utils
     |_. cp.bat
       . mv.bat
       . rm.bat
       . tt_compile.bat
       . tt_exec.bat


3.a) On a Linux machine, make sure that the following packages
     are available:

       flex     (lexical analyzer generator,
                 http://www.gnu.org/software/flex/flex.html)
       bison    (parser generator,
                 http://www.gnu.org/software/bison/bison.html)
       make     (program maintainance utility,
                 http://www.gnu.org/software/make/make.html)
       gcc      (C compiler,
                 http://www.gnu.org/software/gcc/gcc.html)

     Then compile the C source files through the following commands:

       > cd <TwoTowers 5.1 directory>/src/
       > make
       > make clean

     which should create the following executable file:

       <TwoTowers 5.1 directory>/bin/TTKernel

     and define the following symbolic link to the above executable
     file:

       > ln -s <TwoTowers 5.1 directory>/bin/TTKernel TTKernel

     in a directory whose pathname occurs in the shell variable path.


3.b) The executable file for Windows is already available at:
       <TwoTowers 5.1 directory>\bin\TTKernel.exe

     Should you need to generate it again, make sure that
     the following packages are available in \Program Files\GnuWin32:

       flex     (lexical analyzer generator,
                 http://gnuwin32.sourceforge.net/packages/flex.htm)
       bison    (parser generator,
                 http://gnuwin32.sourceforge.net/packages/bison.htm)

     and that the following packages are available as well:

       make     (program maintainance utility,
                 http://www.mingw.org/)
       gcc      (C compiler,
                 http://www.mingw.org/)

     Then compile the C source files through the following commands:

       <double click> <TwoTowers 5.1 directory>\win_utils\tt_compile
       > make
       > make clean

     which should create the following executable file:

       <TwoTowers 5.1 directory>\bin\TTKernel.exe


======================================================================


                       HOW TO RUN TwoTowers 5.1


1.a) On a Linux machine, make sure that the following packages
     are available:

       wish           (windowing shell for Tcl/Tk 8.0 or higher,
                       http://www.tcl.tk/software/tcltk/8.0.tml)
       NuSMV 2.2.5    (symbolic model checker,
                       http://nusmv.irst.itc.it/)

     Then type the following command to run the tool:

       > wish <TwoTowers 5.1 directory>/gui/TTGUI &

     To simplify this, define an alias like the following:

       alias tt 'wish <TwoTowers 5.1 directory>/gui/TTGUI &'

     so that the command to run the tool simply becomes:

       > tt

     In order to be able to use to model checker, make sure that
     the following symbolic links:

       > ln -s <NuSMV 2.2.5 directory>/NuSMV NuSMV
       > ln -s <NuSMV 2.2.5 directory>/src/ltl/ltl2smv/ltl2smv ltl2smv

     have been created in a directory whose pathname occurs in the
     shell variable path.


1.b) On a Windows machine, make sure that the following package
     is available in \Program Files\Tcl:

       wish           (windowing shell for Tcl/Tk 8.0 or higher,
                       http://www.tcl.tk/software/tcltk/8.0.tml)

     and that the following package is available in
     \Program Files\NuSMV-2.2.5:

       NuSMV 2.2.5    (symbolic model checker,
                       http://nusmv.irst.itc.it/)

     Then give the following command to run the tool:

       <double click> <TwoTowers 5.1 directory>\win_utils\tt_exec


======================================================================
