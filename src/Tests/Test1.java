package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import CodeSmell.duplicateCodeCheck;

public class Test1 {

	@Test
	public void testHasDupes_ReturnsTrue() {
		duplicateCodeCheck dupes = new duplicateCodeCheck();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("String x = Hello World");
		lines.add("String x = Hello World");
		boolean result = dupes.hasDupes(lines);
		assertTrue(result);
	}

	@Test
	public void testHasDupes_ReturnFalse() {
		duplicateCodeCheck dupes = new duplicateCodeCheck();
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("String x = Hello World");
		boolean result = dupes.hasDupes(lines);
		assertFalse(result);
	}
}
