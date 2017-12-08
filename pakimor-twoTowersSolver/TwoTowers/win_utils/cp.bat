@rem $ cp2copy dos utility - Edoardo Bonta' - 11 Dec 2005 $
@echo off
set recursive=no
:next
if '%1'=='-r' goto sh
if '%1'=='' goto exit
if '%2'=='' goto exit
set p1=%1
set p1=%p1:/=\%
set p2=%2
set p2=%p2:/=\%
if %recursive%==yes if exist %p1% if not exist %p2% mkdir %p2%
if exist %p1% copy %p1% %p2%
goto exit
:sh
shift
set recursive=yes
goto next
:exit
