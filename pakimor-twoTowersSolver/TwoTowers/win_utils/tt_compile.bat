@echo off
setlocal

set WIN_FLEX_BISON=%ProgramFiles%\GnuWin32

rem %~dp0. is the path of this batch file
set path=%path%;%WIN_FLEX_BISON%\bin;%~dp0.;%~dp0.\win_include
cd %~dp0..\src
set message1=This shell adds "%WIN_FLEX_BISON%\bin" to the environment variable path
set message1=%message1% in order to find flex, bison, and other utilities during the compilation.
set message2=1) To compile: type "make" or "mingw32-make" and enter.
set message3=2) To remove the object files (*.o): type "make clean" and enter.
rem transforms '\' to '/' in WIN_FLEX_BISON path
set WIN_FLEX_BISON=%WIN_FLEX_BISON:\=/%
color 1f
cmd /K "echo %message1% && echo %message2% && echo %message3%"
color
endlocal
@echo on
