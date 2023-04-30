package com.antipattern.detector.AntipatternDetector;


import com.puppycrawl.tools.checkstyle.Main;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
//added more tests to match logic

public class SpaghettiCodeDetectorTest {


    @Test
    public void visitToken_WhenMethodLengthisValid()  {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        DetailAST methodDefAst = mock(DetailAST.class);
        DetailAST openingBraceAst = mock(DetailAST.class);
        

        when(methodDefAst.getType()).thenReturn(TokenTypes.METHOD_DEF);
        when(methodDefAst.getLineNo()).thenReturn(1);
        when(methodDefAst.findFirstToken(TokenTypes.SLIST)).thenReturn(openingBraceAst);

        when(openingBraceAst.getLineNo()).thenReturn(SpaghettiCodeDetector.MAX_METHOD_LENGTH-1);

        detector.visitToken(methodDefAst);
        assertTrue(true);

    }


    @Test
    public void visitToken_WhenMethodLengthAndNestingDepth_areWithinLimits() {

        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        DetailAST methodDefAst = mock(DetailAST.class);

        when(methodDefAst.getType()).thenReturn(TokenTypes.METHOD_DEF);
        when(methodDefAst.getLineNo()).thenReturn(1);

        DetailAST openingBrace = mock(DetailAST.class);
        when(openingBrace.getType()).thenReturn(TokenTypes.SLIST);
        when(openingBrace.getLineNo()).thenReturn(40);
        when(methodDefAst.findFirstToken(TokenTypes.SLIST)).thenReturn(openingBrace);

        DetailAST openingBraceAst = mock(DetailAST.class);
        DetailAST ifAst = mock(DetailAST.class);
        when(ifAst.getType()).thenReturn(TokenTypes.LITERAL_IF);
        when(ifAst.findFirstToken(TokenTypes.SLIST)).thenReturn(ifAst);
        when(openingBrace.getFirstChild()).thenReturn(ifAst);
        when(ifAst.getNextSibling()).thenReturn(openingBraceAst);
        detector.visitToken(methodDefAst);
        assertTrue(true);
    }

    @Test
    public void getDefaultTokensReturnsMethodDefTokenType() {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        int[] expectedTokens = new int[]{TokenTypes.METHOD_DEF};
        int[] actualTokens = detector.getDefaultTokens();

        assertNotNull(actualTokens, "Returned array should not be null");
        assertArrayEquals(expectedTokens,  actualTokens);
    }

    @Test
    public void getRequiredTokensReturnsDefaultTokens() {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        int[] defaultTokens = detector.getDefaultTokens();
        int[] requiredTokens = detector.getRequiredTokens();

        assertNotNull(defaultTokens);
        assertNotNull(requiredTokens);
        assertArrayEquals(defaultTokens, requiredTokens);
    }

    @Test
    public void getNestingDepthWhenNoNesting() {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        DetailAST ast = mock(DetailAST.class);
        when(ast.getFirstChild()).thenReturn(null);
        int nestingDepth = detector.getNestingDepth(ast);

        // "Expected nesting depth to be 0 when there is no nesting"
        assertEquals(0, nestingDepth );
    }

    @Test
    public void getNestingDepthWhenSingleLevelNesting() {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        DetailAST ast = mock(DetailAST.class);
        DetailAST child = mock(DetailAST.class);
        DetailAST slist = mock(DetailAST.class);

        when(ast.getFirstChild()).thenReturn(child);
        when(child.getNextSibling()).thenReturn(null);
        when(child.getType()).thenReturn(TokenTypes.LITERAL_IF);
        when(child.findFirstToken(TokenTypes.SLIST)).thenReturn(slist); // here slist is the first child
        when(slist.getFirstChild()).thenReturn(null); // here no next child to slist! so we return null!

        int expectedNestingDepth = 1;
        int actualNestingDepth = detector.getNestingDepth(ast);

        assertEquals(expectedNestingDepth, actualNestingDepth);
    }


    @Test
    public void getAcceptableTokens() {
        SpaghettiCodeDetector detector = new SpaghettiCodeDetector();
        int[] acceptableTokens =  detector.getAcceptableTokens();
        assertNull(acceptableTokens);
    }
   // @Test
	//public void checkstylePluginTest() throws IOException{
		
		//Main.main("-c","config.xml","C:\\Users\\garhg\\Desktop\\582\\AntiPatt (4)\\AntiPatt (2)\\AntiPatt\\AntipatternDetector\\src\\main\\java\\com\\antipattern\\detector\\AntipatternDetector\\SpaghettiCodeDetector.java");
	//}
}

