@echo off
rem A simple batch program that locates every source file

rem Get all of the sources
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
javac @sources.txt -d bin

rem Remove the sources file
erase sources.txt