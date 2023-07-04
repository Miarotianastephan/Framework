
javac -d . framework\src\Model.java
javac -d . framework\src\Dao.java
javac -d . framework\src\ParamValue.java
javac -d . framework\src\Url.java
javac -d . framework\src\field.java
javac -d . framework\src\Scopes.java
javac -d . framework\src\Mapping.java
javac -d . framework\src\ModelView.java
javac -d . framework\src\ManageForAnnotation.java
javac -d . framework\src\Utility.java
javac -d . framework\src\FrontServlet.java

@REM compile of java class
@REM javac -d . framework\src\*.java

@REM create jar
jar cf test_framework\src\WEB-INF\lib\framework_jar.jar etu1846
jar -tf test_framework\src\WEB-INF\lib\framework_jar.jar

@REM remove directory after create the jar file 
rmdir /S /Q etu1846