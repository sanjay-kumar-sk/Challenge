package com.oracle.reporter.factory;

import com.oracle.model.ContractData;
import com.oracle.model.Data;
import com.oracle.model.GeoZoneData;
import com.oracle.reporter.Reporter;
import com.oracle.reporter.impl.AvgBuildDurationForGeoZone;
import com.oracle.reporter.impl.ContractIdToUniqueCustomerIdReporter;
import com.oracle.reporter.impl.GeoZoneToNumOfUniqueCustomerIdReporter;
import com.oracle.reporter.impl.GeoZoneToUniqueCustomerIdReporter;
import com.oracle.reporter.type.ReporterType;

import java.util.List;

public class ReporterFactory {
    private List<Data> data;

    public ReporterFactory(List<Data> data) {
        this.data = data;
    }


    public Reporter getReporter(ReporterType type) {
        GeoZoneData geoZoneData = GeoZoneData.from(data);
        ContractData contractData = ContractData.from(data);

        switch (type) {
            case GEO_ZONE_AVG_BUILD_DURATION:
                return new AvgBuildDurationForGeoZone(geoZoneData);
            case GEO_ZONE_UNIQUE_CUSTOMER_IDS:
                return new GeoZoneToUniqueCustomerIdReporter(geoZoneData);
            case GEO_ZONE_UNIQUE_CUSTOMER_ID_COUNT:
                return new GeoZoneToNumOfUniqueCustomerIdReporter(geoZoneData);
            case CONTRACT_ID_UNIQUE_CUSTOMER_ID_COUNT:
                return new ContractIdToUniqueCustomerIdReporter(contractData);
        }
        return null;
    }


}
