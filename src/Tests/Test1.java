package Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import CodeSmell.duplicateCodeCheck;
import org.easymock.*;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest()
public class Test1 {

	//public void testDuplicateCodeCheck() {
	//	duplicateCodeCheck dupes = new duplicateCodeCheck();
	//}
	
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
