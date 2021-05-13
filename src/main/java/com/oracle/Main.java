package com.oracle;

import com.oracle.dataprocesser.DataPreProcessor;
import com.oracle.model.Data;
import com.oracle.reader.DataReader;
import com.oracle.reader.impl.FileDataReader;
import com.oracle.report.ReportGenerator;
import com.oracle.reporter.Reporter;
import com.oracle.reporter.factory.ReporterFactory;
import com.oracle.reporter.type.ReporterType;

import java.util.List;

public class Main {
    private static final String fileName = "src/main/resources/input.txt";
    public static void main(String[] args) {

        DataReader dataReader = new FileDataReader();
        final List<Data> dataList = dataReader.readData(fileName);

        DataPreProcessor<Data> processor = new DataPreProcessor<>();
        List<Data> processedData = processor.with(dataList)
                .validate(Data::isValid)
                .encode(data -> data)
                .get();

        ReporterFactory reporterFactory = new ReporterFactory(processedData);
        Reporter contractIdToCustomerId = reporterFactory.getReporter(ReporterType.CONTRACT_ID_UNIQUE_CUSTOMER_ID_COUNT);
        Reporter geoZoneToNumOfUniqueCustomerId = reporterFactory.getReporter(ReporterType.GEO_ZONE_UNIQUE_CUSTOMER_ID_COUNT);
        Reporter avgBuildDurationForGeoZone = reporterFactory.getReporter(ReporterType.GEO_ZONE_AVG_BUILD_DURATION);
        Reporter geoZoneToUniqueCustomerId = reporterFactory.getReporter(ReporterType.GEO_ZONE_UNIQUE_CUSTOMER_IDS);

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.addReporter(contractIdToCustomerId);
        reportGenerator.addReporter(geoZoneToNumOfUniqueCustomerId);
        reportGenerator.addReporter(avgBuildDurationForGeoZone);
        reportGenerator.addReporter(geoZoneToUniqueCustomerId);
        reportGenerator.generateReport();
    }
}
