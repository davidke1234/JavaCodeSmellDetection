# 582Project CodeSmell Detection

Setup:
1. Right click on a jar file to add to build path.
1. Configure CheckStyle: Window: Preferences: CheckStyle.  Add custom xml file and set to default.

Technology: 
1. Eclipse: Version: 2022-09 (4.25.0)
1. JRE System Library: JavaSE-17
1. JUnit 4
1. CheckStyle plugin ver 10.8.0

References:
1. Documentation: [Extending CheckStyle](https://checkstyle.org/extending.html)
2. Run CheckStyle GUI:
	D:\WSU\582 Testing\Project\582Project>java -cp checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.gui.Main 

How to build a custom checker:
	
1. Check the AST (abstract syntax tree)
    - java -jar checkstyle-10.8.0-all.jar -t Check.java
    - Use the CheckStyle API: import com.puppycrawl.tools.checkstyle.api.*
	
How to test files:
	
1. Run checkStyle with default xml file:
	- java -jar checkstyle-10.8.0-all.jar -c /sun_checks.xml src/CodeSmell/Check.java 
           
1. Run checkstyle with custom module(s) (Root module, Checks, etc) in configuration file:
(https://checkstyle.org/cmdline.html)
	- Export your class project as a jar file.  CodeSmell.jar
	- Execute in a cmd line: 
	
		- D:\WSU\582 Testing\Project\582Project>java -classpath CodeSmell.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml Check.java

		Note: Custom modules should be specified with the class' full classpath in the configuration file and the compiled class be located in the custom JAR for Checkstyle to find. 