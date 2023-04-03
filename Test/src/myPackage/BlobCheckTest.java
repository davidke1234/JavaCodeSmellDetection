package myPackage;

import com.puppycrawl.tools.checkstyle.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import

public class BlobCheckTest {
	
	private BlobCheck blobCheck;
	
	void initialize() { //initialize the check, also test that object is created
		blobCheck  = new BlobCheck();
		assertNotNull(blobCheck.getDefaultTokens());
	}
	
	void TestDefaultTokens() {
		blobCheck  = new BlobCheck();
		assertNotNull(blobCheck.getDefaultTokens());
		assertArrayEquals(new int[] {TokenTypes.METHOD_DEF}, blobCheck.getDefaultTokens());
	}
	
	void TestRequiredTokens() {
		blobCheck  = new BlobCheck();
		assertNotNull(blobCheck.getRequiredTokens());
		assertArrayEquals(blobCheck.getDefaultTokens(), blobCheck.getDefaultTokens());
	}
	
	

}
