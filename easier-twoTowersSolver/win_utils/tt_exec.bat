@echo off
setlocal
rem %~dp0 is the extended path of this batch file

set WISH_PATH=%ProgramFiles%\Tcl\bin
set NUSMV_BIN_PATH=%ProgramFiles%\NuSMV-2.2.5\bin

set path=%path%;%~dp0;%~dp0..\bin;%WISH_PATH%;%NUSMV_BIN_PATH%
start wish80.exe "%~dp0..\gui\TTGUI"
endlocal
@echo on
