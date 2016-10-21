@echo off
rem A simple batch script that runs the client

rem If mrSSS.jar does not exist then (re)compile it
if not exist mrSSS.jar (
	echo make mrSSS.jar
	make mrSSS.jar
	
	rem If the file failed to build then exit prematurely
	if not errorlevel == 0 (
		exit /b %errorlevel%
	)
)

rem Run the client
@echo off
cd bin
java -jar ../mrSSS.jar
exit /b %errorlevel%