package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import CodeSmell.SwissArmyKnifeCheck;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.utils.TokenUtil;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

import java.io.File;
import java.util.List;

public class TestSwissArmyKnife {

	// Black box testing

//	@Test
//	public void testAddBeforeExecutionFileFilter() throws Exception {
//	  final Checker checker = new Checker();
//	  
//	  final TestBeforeExecutionFileFilter filter = new TestBeforeExecutionFileFilter();
//	  checker.addBeforeExecutionFileFilter(filter);
//	  filter.resetFilter();
//	  checker.process(Collections.singletonList(new File("dummy.java")));
//	  assertTrue("Checker.acceptFileStarted() doesn't call filter", filter.wasCalled());
//	}

//	@Test
//	public void testDestroy() throws Exception {
//	  final Checker checker = new Checker();
//	  final DebugAuditAdapter auditAdapter = new DebugAuditAdapter();
//	  checker.addListener(auditAdapter);
//	  final TestFileSetCheck fileSet = new TestFileSetCheck();
//	  checker.addFileSetCheck(fileSet);
//	  final DebugFilter filter = new DebugFilter();
//	  checker.addFilter(filter);
//	  final TestBeforeExecutionFileFilter fileFilter = new TestBeforeExecutionFileFilter();
//	  checker.addBeforeExecutionFileFilter(fileFilter);
//	  // should remove all listeners, file sets, and filters
//	  checker.destroy();
//	  checker.process(Collections.singletonList(temporaryFolder.newFile()));
//	  final SortedSet<LocalizedMessage> messages = new TreeSet<>();
//	  messages.add(new LocalizedMessage(1, 0, "a Bundle", "message.key",
//	      new Object[] {"arg"}, null, getClass(), null));
//	  checker.fireErrors("Some File Name", messages);
//	  assertFalse("Checker.destroy() doesn't remove listeners.", auditAdapter.wasCalled());
//	  assertFalse("Checker.destroy() doesn't remove file sets.", fileSet.wasCalled());
//	  assertFalse("Checker.destroy() doesn't remove filters.", filter.wasCalled());
//	  assertFalse("Checker.destroy() doesn't remove file filters.", fileFilter.wasCalled());
//	}

	@Test
	public void testCheckerSwissArmyKnife() throws Exception {
		Checker checker = new Checker();
		final SwissArmyKnifeCheck fileSet = new SwissArmyKnifeCheck();
		File file = new File("cyclomaticComplexity3.java");
		
//        Scanner myReader = new Scanner(file);
//	    while (myReader.hasNextLine()) {
//	        String data = myReader.nextLine();
//	        System.out.println(data);
//	      }
//	      myReader.close();
		
		
		//System.out.print(file..getMessage());
		checker.addFileSetCheck(fileSet);
		int result = 0;
		SortedSet<Violation> violations = null;

		try {
			violations = fileSet.process(file);
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}

		// TODO: check for violations
		assertTrue(violations.size() > 0);

	}
	
	@Test
	public void testCheckerSwissArmyKnifeViaCMD() throws Exception {
		// Execute command
		String command = "cmd /c start cmd.exe";
		Process child = Runtime.getRuntime().exec(command);
	
		// Get output stream to write from it
		OutputStream out = child.getOutputStream();
	
		System.out.print("cd C:/ /r/n".getBytes());
		out.flush();
		System.out.print("dir /r/n".getBytes());
		out.close();
	}
	
	@Test
	public void testExecute() throws Exception {
	 try
     {
         // Command to create an external process
         String command = "D:\\WSU\\582 Testing\\Project\\582Project\\blackBoxTests.cmd";

         // Running the above command
         Runtime run  = Runtime.getRuntime();
         Process proc = run.exec(command);
     }

     catch (IOException e)
     {
         e.printStackTrace();
     }
 }

//	@Test
//	public void testWholeShebang() throws CheckstyleException { 
//		//Creates a new Checker instance. The instance needs to be contextualized and configured. 
//		com.puppycrawl.tools.checkstyle.Checker checker = new com.puppycrawl.tools.checkstyle.Checker();
//		

	// checker.addFileSetCheck;

//		File file = new File("CyclomaticComplexity.java");
//		FileSetCheck fileSetCheck;
//		SortedSet<Violation> violations = fileSetCheck.process(file, null);
//		System.out.println(violations.size());

//	}

	// *** White box testing ***

	// Unit Tests

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
			// Assertions.assertEquals("Found unsupported token: OBJBLOCK",
			// ex.getMessage());
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
			// Assertions.assertEquals("Found unsupported token: OBJBLOCK",
			// ex.getMessage());
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

//	@Test
//	public void testLogMe_returnsError() {
//		SwissArmyKnifeCheck swiss = new SwissArmyKnifeCheck();
//		String msg = swiss.logMe(1, "Hi there");
//		assertTrue(msg.contains("Failed to log"));
//	}

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
