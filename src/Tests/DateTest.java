package Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.easymock.*;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import org.junit.Test;
import CodeSmell.Date;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Date.class)
public class DateTest {

	//@Test
	public void testIsLeapYear() {
		Date date = new Date(1, 1, 2000);
		boolean isLeap = Date.isLeap(2000);
		assertTrue(isLeap);		
	}
	//@Test
	public void testIsCommonYear() {
		Date date = new Date(1, 1, 2000);
		boolean isLeap = date.isLeap(2001);
		assertFalse(isLeap);		
	}
	//@Test
	public void testIsCenturyYear() {
		Date date = new Date(1, 1, 1000);
		boolean isLeap = date.isLeap(1000);
		assertFalse(isLeap);		
	}
	//@Test
	public void testToString() {
		// create a spy for out obj date
		Date spyDate = spy(new Date(30,8,2017));
		
		//simulate the beh for all methods that are call
		// i.e., tell those methods what to return:
		//doReturn("Wednesday").when(spyDate).getDayName();
	}
	//@Test
	public void testValidCombination() {
		PowerMock.mockStaticPartial(Date.class, "isLeap");
		EasyMock.expect(Date.isLeap(2012)).andReturn(true);
		PowerMock.replayAll();
		//assertTrue(Date.validCombination(29, 02, 2017));
		
		
	}

}
