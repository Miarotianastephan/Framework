
@REM compile of java class
javac -d framework\src framework\src\*.java

@REM create jar 
jar cf test_framework\src\WEB-INF\lib\framework_jar.jar framework\src\etu1846

@REM compile the lib --into classes
@REM javac --classpath test_framework\src\WEB-INF\lib\framework_jar.jar -d test_framework\src\WEB-INF\classes 

@REM transform to war 
jar -cvf myapp.war -C test_framework\src .
