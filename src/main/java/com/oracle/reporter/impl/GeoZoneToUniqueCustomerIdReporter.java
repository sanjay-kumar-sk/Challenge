package com.oracle.reporter.impl;

import com.oracle.model.GeoZoneData;
import com.oracle.reporter.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoZoneToUniqueCustomerIdReporter implements Reporter {
    private final GeoZoneData geoZoneData;

    public GeoZoneToUniqueCustomerIdReporter(GeoZoneData geoZoneData) {
        this.geoZoneData = geoZoneData;
    }


    /**
     * Generate list of Unique customer ids for each zones.
     *
     * @return
     */
    @Override
    public List<String> createReport() {
        List<String> output = new ArrayList<>();
        geoZoneData.getUniqueCustomeridsForEachGeoZone().forEach((key, value) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GeoZone: ");
            stringBuilder.append(key);
            stringBuilder.append(", Customer Ids: [");
            stringBuilder.append(value.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            stringBuilder.append("]");
            output.add(stringBuilder.toString());
        });
        return output;
    }
}
