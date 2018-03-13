@rem $ mv2move dos utility - Edoardo Bonta' - 11 Dec 2005 $
@echo off
if '%1'=='' goto exit
if '%2'=='' goto exit
set p1=%1
set p1=%p1:/=\%
set p2=%2
set p2=%p2:/=\%
if exist %p1% move %p1% %p2%
:exit
