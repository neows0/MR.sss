@echo off
rem A simple batch program that locates every source file

rem If a parameter was not put in, then call make to put it in for us
if [%1]==[] (
	make
	exit /b 0
)

rem Get all of the sources in a text file
dir /b /s *.java > sources.txt 

setlocal enableextensions disabledelayedexpansion

rem Make the source paths contain the local path instead of the full path
set "search=%cd%\"
set "replace="
set "textFile=sources.txt"
for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
    set "line=%%i"
    setlocal enabledelayedexpansion
    set "line=!line:%search%=%replace%!"
    >>"%textFile%" echo(!line!
    endlocal
)

rem Compile the java sources
echo javac @sources.txt -d %1%
javac @sources.txt -d %1%

rem Store the exit code to return it later
set exitcode=%errorlevel%

rem Delete the sources file
erase sources.txt

rem Return the stored exit code
exit /b %exitcode%