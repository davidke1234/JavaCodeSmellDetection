package com.antipattern.detector.AntipatternDetector;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

//import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

//BlackBox testing making instances
public class CheckstylePluginTest {

	 
	@Test
    public void testSpaghettiCodeDetector() throws CheckstyleException {

//      instance
        Checker checker = new Checker();

        ClassLoader classLoader = getClass().getClassLoader();
//        File configFile = new File(classLoader.getResource("config.xml").getFile());
        File configFile = new File("C:\\Users\\garhg\\Desktop\\582\\AntiPatt (4)\\AntiPatt (2)\\AntiPatt\\AntipatternDetector\\src\\main\\resources\\config.xml");
        
        Configuration config = ConfigurationLoader.loadConfiguration(configFile.getPath(), new PropertiesExpander(new Properties()));
        checker.setModuleClassLoader(getClass().getClassLoader());
        checker.configure(config);

        // run Checkstyle on the source code
        List<CheckstyleViolation> violations = new ArrayList<>();
        checker.addListener(new CheckstyleListener(violations));
        List<File> files = Arrays.asList(new File("C:\\Users\\garhg\\Desktop\\582\\AntiPatt (4)\\AntiPatt (2)\\AntiPatt\\AntipatternDetector\\src\\main\\java\\com\\antipattern\\detector\\AntipatternDetector"));
        int numErrors = checker.process(files);
        assertNotNull(numErrors);
        checker.destroy();

        // loop through the list of violations to find the specific one you are looking for
        for (CheckstyleViolation violation : violations) {
            if (violation.getRuleName().equals("SpaghettiCode")) {
                // handle the SpaghettiCode violation here
                System.out.println("Found a SpaghettiCode violation in " + violation.getFilename() + " on line " + violation.getLine() + ".");
            }
            else
                System.out.println("Hello");

        }
    }

    // Checkstyle listener to collect violations
    private class CheckstyleListener implements AuditListener {
        private final List<CheckstyleViolation> violations;

        public CheckstyleListener(List<CheckstyleViolation> violations) {
            this.violations = violations;
        }


        @Override
        public void auditFinished(AuditEvent event) {}

        @Override
        public void fileStarted(AuditEvent event) {}

        @Override
        public void fileFinished(AuditEvent event) {}

        @Override
        public void addError(AuditEvent event) {
            CheckstyleViolation violation = new CheckstyleViolation(
                    event.getFileName(),
                    event.getLine(),
                    event.getColumn(),
                    event.getSource().toString(),
                    event.getMessage(),
                    event.getSeverityLevel().getName(),
                    event.getViolation().toString()     );
            violations.add(violation);
        }

        @Override
        public void addException(AuditEvent auditEvent, Throwable throwable) {

        }

        @Override
        public void auditStarted(AuditEvent auditEvent) {

        }
    }

    // Checkstyle violation class to store violation details
    private class CheckstyleViolation {
        private final String filename;
        private final int line;
        private final int column;
        private final String source;
        private final String message;
        private final String severity;
        private final String ruleName;

        public CheckstyleViolation(String filename, int line, int column, String source, String message, String severity, String ruleName) {
            this.filename = filename;
            this.line = line;
            this.column = column;
            this.source = source;
            this.message = message;
            this.severity = severity;
            this.ruleName = ruleName;
        }

        public String getFilename() {
            return filename;
        }

        public int getLine() {
            return line;
        }

        public int getColumn() {
            return column;
        }

        public String getSource() {
            return source;
        }

        public String getMessage() {
            return message;
        }

        public String getSeverity() {
            return severity;
        }

        public String getRuleName() {
            return ruleName;
        }
    }




}
