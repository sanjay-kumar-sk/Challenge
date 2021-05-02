package com.oracle.report;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReporterTest {

    private static Reporter reporter = null;

    @BeforeAll
    public static void setUp() {
        reporter = new Reporter();
    }

    @Test
    public void getNumberOfUniqueCustomerIdForEachContractId() {
        List<String> numberOfUniqueCustomerIdForEachContractId = reporter.getNumberOfUniqueCustomerIdForEachContractId();
        assertEquals(2, numberOfUniqueCustomerIdForEachContractId.size());
    }

    @Test
    public void getAvgBuildDurationOfEachGeoZone() {
        List<String> avgBuildDurationOfEachGeoZone = reporter.getAvgBuildDurationOfEachGeoZone();
        assertEquals(3, avgBuildDurationOfEachGeoZone.size());
    }

    @Test
    public void getNumberOfUniqueCustomerIdForEachGeoZone() {
        List<String> numberOfUniqueCustomerIdForEachGeoZone = reporter.getNumberOfUniqueCustomerIdForEachGeoZone();
        assertEquals(3, numberOfUniqueCustomerIdForEachGeoZone.size());
    }

    @Test
    public void uniqueCustomerIdForEachGeoZone() {
        List<String> uniqueCustomerIdsForEachGeoZone = reporter.getUniqueCustomerIdForEachGeoZone();
        assertEquals(3, uniqueCustomerIdsForEachGeoZone.size());
    }
}