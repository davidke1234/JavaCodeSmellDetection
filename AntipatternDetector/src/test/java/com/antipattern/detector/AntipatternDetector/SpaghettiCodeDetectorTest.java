package com.antipattern.detector.AntipatternDetector;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SpaghettiCodeDetectorTest {

    @Test
    void getDefaultTokens() {
        SpaghettiCodeDetector detector = new ConcreteSpaghettiCodeDetector();
        assertNotNull(detector.getDefaultTokens());
        assertArrayEquals(new int[]{TokenTypes.METHOD_DEF}, detector.getDefaultTokens());
    }

    @Test
    void getRequiredTokens() {
        SpaghettiCodeDetector detector = new ConcreteSpaghettiCodeDetector();
        assertNotNull(detector.getRequiredTokens());
        assertArrayEquals(detector.getDefaultTokens(), detector.getRequiredTokens());
    }

    @Test
    void visitToken() {
        // TODO: write test case for visitToken method
    }
}
