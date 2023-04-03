@REM compile the java class
@REM LOG_PATH get the current path of the batch file  
@REM echo The logs path is %LOGS_PATH%

set LOGS_PATH=%~dp0test_framework\src\WEB-INF\lib\framework_jar.jar
javac -cp %LOGS_PATH% -d test_framework\src\WEB-INF\classes test_framework\src\WEB-INF\lib\*.java

@REM @REM transform to war 
jar -cvf app_context.war -C test_framework\src .

@REM @REM checking the content of the war file 
jar tvf app_context.war

