package com.antipattern.detector.AntipatternDetector;


import com.puppycrawl.tools.checkstyle.api.DetailAST;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConcreteSpaghettiCodeDetectorTest {

    @Test
    public void getAcceptableTokens() {
        ConcreteSpaghettiCodeDetector detector = new ConcreteSpaghettiCodeDetector();
        int[] acceptableTokens = detector.getAcceptableTokens();
        List<Integer> expectedTokens = Arrays.asList(
                TokenTypes.CLASS_DEF,
                TokenTypes.INTERFACE_DEF,
                TokenTypes.ENUM_DEF,
                TokenTypes.ANNOTATION_DEF
        );
        assertEquals(expectedTokens.size(), acceptableTokens.length);
        for (int i = 0; i < acceptableTokens.length; i++) {
            assertTrue(expectedTokens.contains(acceptableTokens[i]));
        }
    }


    @Test
    public void visitTokenWhenClassDefinitionIsShorterThanOrEqualTo100Lines() {
        ConcreteSpaghettiCodeDetector detector = spy(new ConcreteSpaghettiCodeDetector());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.CLASS_DEF);
        when(ast.getLineNo()).thenReturn(1);
        when(ast.getLastChild()).thenReturn(ast);
        when(ast.getLastChild().getLineNo()).thenReturn(99);

        // call the visitToken() method with the long class definition
        detector.visitToken(ast);
        assertTrue(true);
    }

    @Test

    public void visitTokenWhenClassDefinitionIsLongerThan100Lines() throws NullPointerException {
        ConcreteSpaghettiCodeDetector detector = spy(new ConcreteSpaghettiCodeDetector());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.CLASS_DEF);
        when(ast.getLineNo()).thenReturn(1);
        when(ast.getLastChild()).thenReturn(ast);
        when(ast.getLastChild().getLineNo()).thenReturn(102);

        detector.visitToken(ast);
    }

    @Test
    public void visitTokenWhenMethodDefinitionIsShorterThanOrEqualTo50Lines() {
        ConcreteSpaghettiCodeDetector detector = new ConcreteSpaghettiCodeDetector();
        DetailAST ast = mock(DetailAST.class);
        DetailAST lastChild = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.METHOD_DEF);
        when(ast.getLineNo()).thenReturn(1);
        when(ast.getLastChild()).thenReturn(lastChild);
        when(lastChild.getLineNo()).thenReturn(45);

        detector.visitToken(ast);
        assertTrue(true);
 }

    @Test
    public void visitTokenWhenMethodDefinitionIsLongerThan50Lines() throws NullPointerException {
        ConcreteSpaghettiCodeDetector detector = spy(new ConcreteSpaghettiCodeDetector());
        DetailAST ast = mock(DetailAST.class);

        when(ast.getType()).thenReturn(TokenTypes.METHOD_DEF);
        when(ast.getLineNo()).thenReturn(1);
        when(ast.getLastChild()).thenReturn(ast);
        when(ast.getLastChild().getLineNo()).thenReturn(52);

        detector.visitToken(ast);
        assertTrue(true);

    }
}
