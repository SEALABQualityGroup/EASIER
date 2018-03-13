@rem $ rm2erase dos utility - Edoardo Bonta' - 11 Dec 2005 $
@echo off
:next
if '%1'=='-f' goto sh
if '%1'=='' goto exit
set p=%1
set p=%p:/=\%
if exist %p% erase %p%
:sh
shift
goto next
:exit
