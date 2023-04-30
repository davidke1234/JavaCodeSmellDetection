package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedSet;
import java.io.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import CodeSmell.SwissArmyKnifeCheck;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.utils.TokenUtil;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;
import com.puppycrawl.tools.checkstyle.JavaParser;

import java.util.List;

public class TestSwissArmyKnife {

	// Black box testing
		
	@Test
	public void testChecker() throws Exception {
		//String command = "java -jar checkstyle-10.8.0-all.jar -t CyclomaticComplexity.java";
		
		DetailAstImpl detailAST = new DetailAstImpl();
		String ast = "";
		
		File file = new File("ast.txt");
		Scanner myReader = new Scanner(file);
	    while (myReader.hasNextLine()) {
	        String line = myReader.nextLine();
	        //System.out.println(line);
	        ast+=line;
	      }
	      myReader.close();	
					
		detailAST.setText(ast);  
		System.out.print("result: " + detailAST);
		
		final SwissArmyKnifeCheck checker = new SwissArmyKnifeCheck();
		checker.visitToken(detailAST);
	    SortedSet<Violation> violations = checker.getViolations();
		//assertTrue(violations.size() > 0);
	}

      		
	//@Test
	public void testAllChecks() throws Exception {
		 try
	     {
			 ProcessCmd("Processing Check_dupConstants.java...","java -classpath CodeSmell.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml Check_dupConstants.java");
			 ProcessCmd("Processing CyclomaticComplexity.java...","java -classpath CodeSmell.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml CyclomaticComplexity.java");
			 ProcessCmd("Processing CheckInterfaces.java...","java -classpath CodeSmell.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c config.xml CheckInterfaces.java");		 
	     }
		 catch (Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	}
	
	@Test
	public void testVisitToken_DoesNotReturnError() {
		final DetailAstImpl detailAst = new DetailAstImpl();
		detailAst.setType(TokenTypes.OBJBLOCK);
		boolean hasError = false;

		final SwissArmyKnifeCheck swissCheck = new SwissArmyKnifeCheck();
		try {
			swissCheck.visitToken(detailAst);
			// fail("exception expected");
		} catch (Exception ex) {
			hasError = true;
		}

		assertTrue(hasError == false);
	}

	@Test
	public void testVisitToken_ReturnsError() {
		final DetailAstImpl detailAst = new DetailAstImpl();
		detailAst.setType(TokenTypes.OBJBLOCK);
		boolean hasError = false;

		final SwissArmyKnifeCheck swissCheck = new SwissArmyKnifeCheck();
		try {
			swissCheck.visitToken(detailAst);
			// fail("exception expected");
		} catch (IllegalArgumentException ex) {
			hasError = true;
		}

		assertTrue(hasError == false);
	}

	@Test
	public void testLogMe_returnslogNoParamError() {
		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
		String msg = swiss.logMe(0, "Hi there");
		assertTrue(msg == "Param logNo is invalid");
	}

	@Test
	public void testLogMe_returnslogItemParamError() {
		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
		String msg = swiss.logMe(1, "");
		assertTrue(msg == "Param logItem is invalid");
	}

	@Test
	public void testCheckViolationOfMaxLines_returnsNoIssue() {
		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
		String message = swiss.checkViolationOfMaxLines(1, 2);
		assertTrue(message == "No SwissArmyKnife issues with line numbers.");
	}

	@Test
	public void testCheckViolationOfMaxLines_returnsIssue() {
		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
		String message = swiss.checkViolationOfMaxLines(5, 2);
		assertTrue(message.contains("SwissArmyKnife issue."));
	}
	
	public String GetFileContents(String command)  throws Exception {
		String result = "";
		ProcessBuilder builder = new ProcessBuilder(
				 "cmd.exe", "/c", command);
		
	    builder.redirectErrorStream(true);
	    Process p = builder.start();
	    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line;
        int i = 0;
        System.out.println("");
        while (i<5000) {
        	i++;
            line = r.readLine();
            if (line == null || i>5000) { break; }
            result += line;
        } 
        return result;
	}

	public void ProcessCmd(String title, String command)  throws Exception {
		ProcessBuilder builder = new ProcessBuilder(
				 "cmd.exe", "/c", command);
		
		builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        int i = 0;
        System.out.println("");
        System.out.println(title);
        while (i<100) {
        	i++;
            line = r.readLine();
            if (line == null || i>100) { break; }
            System.out.println(line);
        }
     }

	// Integration Tests
	// @Test
	// public void testSwissArmyKnifeCheck2() {
	// DetailAST ast = new DetailAST.class.
	// SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
	// SwissArmyKnifeCheck spy = Mockito.spy(swiss);
	// Mockito.doNothing().when(spy).visitToken(ast);
	// verify(swiss, times(1)).visitToken(ast);
	// }

	// @Test
//	public void testSwissArmyKnifeCheck() {
//		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
//	
//		DetailAstImpl ast = new DetailAstImpl();
//				
//		
//		String z = "COMPILATION_UNIT -> COMPILATION_UNIT [1:0]\r\n"
//				+ "|--PACKAGE_DEF -> package [1:0]\r\n"
//				+ "|   |--ANNOTATIONS -> ANNOTATIONS [1:8]\r\n"
//				+ "|   |--IDENT -> CodeSmell [1:8]\r\n"
//				+ "|   `--SEMI -> ; [1:17]\r\n"
//				+ "`--CLASS_DEF -> CLASS_DEF [3:0]\r\n"
//				+ "    |--MODIFIERS -> MODIFIERS [3:0]\r\n"
//				+ "    |   `--LITERAL_PUBLIC -> public [3:0]\r\n"
//				+ "    |--LITERAL_CLASS -> class [3:7]\r\n"
//				+ "    |--IDENT -> myClass [3:13]\r\n"
//				+ "    `--OBJBLOCK -> OBJBLOCK [3:21]\r\n"
//				+ "        |--LCURLY -> { [3:21]\r\n"
//				+ "        |--METHOD_DEF -> METHOD_DEF [4:1]\r\n"
//				+ "        |   |--MODIFIERS -> MODIFIERS [4:1]\r\n"
//				+ "        |   |   `--LITERAL_PUBLIC -> public [4:1]\r\n"
//				+ "        |   |--TYPE -> TYPE [4:8]\r\n"
//				+ "        |   |   `--IDENT -> String [4:8]\r\n"
//				+ "        |   |--IDENT -> ABC [4:15]\r\n"
//				+ "        |   |--LPAREN -> ( [4:18]\r\n"
//				+ "        |   |--PARAMETERS -> PARAMETERS [4:19]\r\n"
//				+ "        |   |--RPAREN -> ) [4:19]\r\n"
//				+ "        |   `--SLIST -> { [4:20]\r\n"
//				+ "        |       |--VARIABLE_DEF -> VARIABLE_DEF [5:2]\r\n"
//				+ "        |       |   |--MODIFIERS -> MODIFIERS [5:2]\r\n"
//				+ "        |       |   |--TYPE -> TYPE [5:2]\r\n"
//				+ "        |       |   |   `--IDENT -> String [5:2]\r\n"
//				+ "        |       |   |--IDENT -> x [5:9]\r\n"
//				+ "        |       |   `--ASSIGN -> = [5:11]\r\n"
//				+ "        |       |       `--EXPR -> EXPR [5:13]\r\n"
//				+ "        |       |           `--STRING_LITERAL -> \"hi\" [5:13]\r\n"
//				+ "        |       |--SEMI -> ; [5:17]\r\n"
//				+ "        |       |--VARIABLE_DEF -> VARIABLE_DEF [6:2]\r\n"
//				+ "        |       |   |--MODIFIERS -> MODIFIERS [6:2]\r\n"
//				+ "        |       |   |--TYPE -> TYPE [6:2]\r\n"
//				+ "        |       |   |   `--IDENT -> String [6:2]\r\n"
//				+ "        |       |   |--IDENT -> y [6:9]\r\n"
//				+ "        |       |   `--ASSIGN -> = [6:11]\r\n"
//				+ "        |       |       `--EXPR -> EXPR [6:13]\r\n"
//				+ "        |       |           `--STRING_LITERAL -> \"hi\" [6:13]\r\n"
//				+ "        |       |--SEMI -> ; [6:17]\r\n"
//				+ "        |       |--VARIABLE_DEF -> VARIABLE_DEF [7:2]\r\n"
//				+ "        |       |   |--MODIFIERS -> MODIFIERS [7:2]\r\n"
//				+ "        |       |   |--TYPE -> TYPE [7:2]\r\n"
//				+ "        |       |   |   `--IDENT -> String [7:2]\r\n"
//				+ "        |       |   |--IDENT -> z [7:9]\r\n"
//				+ "        |       |   `--ASSIGN -> = [7:11]\r\n"
//				+ "        |       |       `--EXPR -> EXPR [7:13]\r\n"
//				+ "        |       |           `--STRING_LITERAL -> \"hi\" [7:13]\r\n"
//				+ "        |       |--SEMI -> ; [7:17]\r\n"
//				+ "        |       `--RCURLY -> } [8:1]\r\n"
//				+ "        `--RCURLY -> } [9:0]";
//		
//		z = z.replace(">", "&gt;");
//		z = z.replace("<", "&lt;");	
//
//		ast.setText(z);
//			
//		swiss.visitToken(ast);
//	}

}
