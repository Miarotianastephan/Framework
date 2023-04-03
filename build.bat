
@REM compile of java class
javac -d . framework\src\*.java

@REM create jar
jar cf test_framework\src\WEB-INF\lib\framework_jar.jar etu1846
jar -tf test_framework\src\WEB-INF\lib\framework_jar.jar

@REM remove directory after create the jar file 
rmdir /S /Q etu1846