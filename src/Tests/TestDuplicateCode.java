package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

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
	public void testAllChecks() throws Exception {
		 try
	     {
			 ProcessCmd("Processing Check_dupConstants.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml Check_dupConstants.java");
			 ProcessCmd("Processing CyclomaticComplexity.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml CyclomaticComplexity.java");
			 ProcessCmd("Processing CheckInterfaces.java...","java -classpath DuplicateCodeCheck.jar;checkstyle-10.8.0-all.jar com.puppycrawl.tools.checkstyle.Main -c configDuplicateCode.xml CheckInterfaces.java");		 
	     }
		 catch (Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
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
}
