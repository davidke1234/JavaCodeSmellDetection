package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import CodeSmell.DuplicateCodeCheck;
import java.io.File;
import java.io.FileNotFoundException;

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
