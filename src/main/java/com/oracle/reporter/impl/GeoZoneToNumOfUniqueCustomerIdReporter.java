package com.oracle.reporter.impl;

import com.oracle.model.GeoZoneData;
import com.oracle.reporter.Reporter;

import java.util.ArrayList;
import java.util.List;

public class GeoZoneToNumOfUniqueCustomerIdReporter implements Reporter {

    private final GeoZoneData geoZoneData;

    public GeoZoneToNumOfUniqueCustomerIdReporter(GeoZoneData geoZoneData) {
        this.geoZoneData = geoZoneData;
    }
    /**
     * Generates number of unique customer id for each Geo zone.
     * @return List of numbers of unique customer ids.
     */
    @Override
    public List<String> createReport() {
        List<String> output = new ArrayList<>();
        geoZoneData.getUniqueCustomeridsForEachGeoZone()
                .forEach((key, value) -> output.add("GeoZone: " + key + ", Num of unique CustomerId: " + value.size()));

        return output;
    }
}
