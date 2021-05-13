package com.oracle.report;

import com.oracle.reporter.Reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class generates the report using given reporters.
 */
public class ReportGenerator {
    List<Reporter> reporters = new ArrayList<>();

    /**
     * Add a reporter which will generate the report.
     * @param reporter Reporter implementation
     */
    public void addReporter(Reporter reporter) {
        reporters.add(reporter);
    }

    /**
     * Generates the required report.
     */
    public void generateReport() {
        for (Reporter reporter : reporters) {
            reporter.createReport().forEach(System.out::println);
        }
    }

}
