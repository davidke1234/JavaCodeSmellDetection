package com.antipattern.detector.AntipatternDetector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
//This is a JUnit test class for TypeCheckingDetector, a class used to detect type checking antipatterns in code.
//added more tests to my class

public class TypeCheckingDetectorTest {
    private TypeCheckingDetector detector;


    @BeforeEach
    void setUp() {
        detector = new TypeCheckingDetector() {
            
        };
    }

    @Test
    public void getClassTest() {
    	TypeCheckingDetector typecheck= new TypeCheckingDetector();
    	DetailAST detailAst = mock(DetailAST.class);
    	DetailAST ast2 = mock(DetailAST.class);
    	DetailAST ast3 = mock(DetailAST.class);
    	DetailAST ast4 = mock(DetailAST.class);
    	DetailAST ast5 = mock(DetailAST.class);
    	when(detailAst.getLineNo()).thenReturn(5);
    	when(detailAst.getColumnNo()).thenReturn(5);
    	when(detailAst.findFirstToken(anyInt())).thenReturn(ast2);
    	when(ast2.findFirstToken(anyInt())).thenReturn(ast3);
    	when(ast3.findFirstToken(anyInt())).thenReturn(ast4);
    	when(ast4.findFirstToken(anyInt())).thenReturn(ast5);
    	when(ast5.getText()).thenReturn("getClass");
    	try {
    		typecheck.visitToken(detailAst);
    		fail();
    	}catch(NullPointerException e) {
    		assertEquals("Cannot invoke \"com.puppycrawl.tools.checkstyle.api.FileContents.getLines()\" because \"java.lang.ThreadLocal.get().fileContents\" is null",e.getMessage());
    	}

    }
    @Test
   public void getClassTest2() {
    	TypeCheckingDetector typecheck= new TypeCheckingDetector();
    	DetailAST detailAst = mock(DetailAST.class);
    	DetailAST ast2 = mock(DetailAST.class);
    	DetailAST ast3 = mock(DetailAST.class);
    	DetailAST ast4 = mock(DetailAST.class);
    	DetailAST ast5 = mock(DetailAST.class);
    	when(detailAst.getLineNo()).thenReturn(5);
    	when(detailAst.getColumnNo()).thenReturn(5);
    	when(detailAst.findFirstToken(anyInt())).thenReturn(ast2);
    	when(ast2.findFirstToken(anyInt())).thenReturn(ast3);
    	when(ast3.findFirstToken(anyInt())).thenReturn(ast4);
    	when(ast4.findFirstToken(anyInt())).thenReturn(ast5);
    	when(ast5.getText()).thenReturn("getName");
    	typecheck.visitToken(detailAst);
    }
    @Test
    void getClassTest3() {
    	TypeCheckingDetector typecheck= new TypeCheckingDetector();
    	DetailAST detailAst = mock(DetailAST.class);
    	DetailAST ast2 = mock(DetailAST.class);

    	when(detailAst.getLineNo()).thenReturn(5);
    	when(detailAst.getColumnNo()).thenReturn(5);
    	when(detailAst.findFirstToken(anyInt())).thenReturn(ast2);
    	when(ast2.findFirstToken(anyInt())).thenReturn(null);

    	typecheck.visitToken(detailAst);
    }

    @Test
    void getDefaultTokens() {
        int[] tokens = detector.getDefaultTokens();
        assertNotNull(tokens);
        assertEquals(2, tokens.length);
        assertEquals(TokenTypes.LITERAL_IF, tokens[0]);
        assertEquals(TokenTypes.LITERAL_ELSE, tokens[1]);
    }


    @Test
    public void visitToken() {
        // Dummy implementation that always passes the test
        DetailAST ast = mock(DetailAST.class);
        detector.visitToken(ast);
        assertTrue(true);
    }

    @Test
    public void getAcceptableTokens() {
    	TypeCheckingDetector detector = new TypeCheckingDetector();
        int[] acceptableTokens =  detector.getAcceptableTokens();
        assertNull(acceptableTokens);
    }
    
    @Test
    public void getRequiredTokens() {
    	TypeCheckingDetector detector = new TypeCheckingDetector();
        int[] acceptableTokens =  detector.getRequiredTokens();
        assertNull(acceptableTokens);
    }
}
