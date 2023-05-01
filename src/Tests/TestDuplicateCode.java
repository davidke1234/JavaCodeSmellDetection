package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.puppycrawl.tools.checkstyle.DetailAstImpl;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.Violation;

import CodeSmell.DuplicateCodeCheck;
import CodeSmell.DuplicateCodeCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class TestDuplicateCode {

	@Test
	public void testDuplicateCodeCheck() {
		// update ast: replace ">" with "&gt;" and "<" with "&lt;"
		DuplicateCodeCheck dupes = new DuplicateCodeCheck();

		try {
			File file = new File("CheckJava_SampleAST1.txt");
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	@Test
    public void testVisitToken_ReturnsNoDuplicateCode() {
      final DuplicateCodeCheck checker = new DuplicateCodeCheck();
      
      DetailAstImpl rootAst = new DetailAstImpl();
      
      DetailAstImpl newType1 = new DetailAstImpl();
      newType1.setType(TokenTypes.CLASS_DEF);
      rootAst.addChild(newType1);
      
      DetailAstImpl newType2 = new DetailAstImpl();
      newType2.setType(TokenTypes.OBJBLOCK);
      rootAst.addChild(newType2);
           
      DetailAstImpl newType4 = new DetailAstImpl();
      newType4.setType(TokenTypes.METHOD_DEF);
      rootAst.addChild(newType4);
      
      checker.visitToken(rootAst);
      SortedSet<Violation> violation = checker.getViolations();
      
      assertTrue(violation.size() == 0);
    }

	@Test
	public void testHasDupes_ReturnsTrue() {

		DuplicateCodeCheck dupes = new DuplicateCodeCheck();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("String x = Hello World");
		lines.add("String x = Hello World");

		boolean result = dupes.hasDupes(lines);

		assertTrue(result);
	}

	@Test
	public void testHasDupes_ReturnFalse() {
		DuplicateCodeCheck dupes = new DuplicateCodeCheck();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("String x = Hello World");
		boolean result = dupes.hasDupes(lines);
		assertFalse(result);
	}
	
	@Test
	public void testHasDupes_ReturnFalseWithNoCode() {
		DuplicateCodeCheck dupes = new DuplicateCodeCheck();
		ArrayList<String> lines = new ArrayList<String>();
		boolean result = dupes.hasDupes(lines);
		assertFalse(result);
	}
	
	@Test
	public void testAllChecks() throws Exception {
		ArrayList<String> violations = new ArrayList<String>(); 
		 try
	     {
			 violations = ProcessCmd("Processing Check_dupConstants.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml Check_dupConstants.java");
			 //ProcessCmd("Processing CyclomaticComplexity.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml CyclomaticComplexity.java");
			 //ProcessCmd("Processing CheckInterfaces.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml CheckInterfaces.java");		 
	     }
		 catch (Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
		 
		 assertTrue(violations.size() >5 );
	}
	
	public ArrayList<String> ProcessCmd(String title, String command)  throws Exception {
		ArrayList<String> results = new ArrayList<String>();
		
		ProcessBuilder builder = new ProcessBuilder(
				 "cmd.exe", "/c", command);
		
		builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        int i = 0;
        //System.out.println("");
        results.add(title);
        
        while (i<100) {
        	i++;
            line = r.readLine();
            if (line == null || i>100) { break; }
            //System.out.println(line);
            results.add(line);
        }
        
        r.close();
        return results;
     }
}
