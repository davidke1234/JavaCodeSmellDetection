package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShotgunCheckTest {
	
	private ShotgunCheck shotgunCheck;
	
	void initialize() { //initialize the check, also test that object is created
		shotgunCheck  = new ShotgunCheck();
		assertNotNull(shotgunCheck.getDefaultTokens());
	}
	
	void TestDefaultTokens() {
		shotgunCheck  = new ShotgunCheck();
		assertNotNull(shotgunCheck.getDefaultTokens());
		assertArrayEquals(new int[] {TokenTypes.METHOD_DEF}, shotgunCheck.getDefaultTokens());
	}
	
	void TestRequiredTokens() {
		shotgunCheck  = new ShotgunCheck();
		assertNotNull(shotgunCheck.getRequiredTokens());
		assertArrayEquals(shotgunCheck.getDefaultTokens(), shotgunCheck.getDefaultTokens());
	}
	
	void TestCheckVisit() {
		
		shotgunCheck = new ShotgunCheck();
		
		
	}
	
	void TestGoodClass() {
		
	}
	
	void TestBadClass() {
		
	}
	

}