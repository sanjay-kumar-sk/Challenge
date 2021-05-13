package com.oracle.report;

import com.oracle.reader.DataReader;
import com.oracle.reader.impl.FileDataReader;
import com.oracle.reporter.Reporter;
import com.oracle.reporter.factory.ReporterFactory;
import com.oracle.reporter.type.ReporterType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReporterTest {

    private static ReporterFactory factory = null;

    @BeforeAll
    public static void setUp() {
        DataReader reader = new FileDataReader();
        factory = new ReporterFactory(reader.readData("src/main/resources/input.txt"));
    }

    @Test
    public void getNumberOfUniqueCustomerIdForEachGeoZone() {
        factory.getReporter(ReporterType.GEO_ZONE_UNIQUE_CUSTOMER_ID_COUNT).createReport();
        List<String> output = factory.getReporter(ReporterType.GEO_ZONE_UNIQUE_CUSTOMER_ID_COUNT).createReport();
        assertEquals(3, output.size());
    }

    @Test
    public void uniqueCustomerIdForEachGeoZone() {
        List<String> output = factory.getReporter(ReporterType.GEO_ZONE_UNIQUE_CUSTOMER_IDS).createReport();
        assertEquals(3, output.size());
    }

    @Test
    public void getNumberOfUniqueCustomerIdForEachContractId() {
        List<String> output = factory.getReporter(ReporterType.CONTRACT_ID_UNIQUE_CUSTOMER_ID_COUNT).createReport();
        assertEquals(2, output.size());
    }

    @Test
    public void getAvgBuildDurationOfEachGeoZone() {
        List<String> output = factory.getReporter(ReporterType.GEO_ZONE_AVG_BUILD_DURATION).createReport();
        assertEquals(3, output.size());
    }
}