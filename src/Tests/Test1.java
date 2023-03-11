package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import CodeSmell.DuplicateCodeCheck;
import org.easymock.*;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.io.File;
import java.io.FileNotFoundException;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest()
public class Test1 {

	@Test
	public void testDuplicateCodeCheck() {
	DuplicateCodeCheck dupes = new DuplicateCodeCheck();
	
	try {
		File file = new File("CheckJava_SampleAST.txt");
		  Scanner myReader = new Scanner(file);
		  while (myReader.hasNextLine()) {
		    String data = myReader.nextLine();
		    System.out.println(data);
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
}
