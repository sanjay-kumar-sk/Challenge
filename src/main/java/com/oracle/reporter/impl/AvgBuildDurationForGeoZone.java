package com.oracle.reporter.impl;

import com.oracle.model.Data;
import com.oracle.model.GeoZoneData;
import com.oracle.reporter.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvgBuildDurationForGeoZone implements Reporter {
    private final GeoZoneData geoZoneData;

    public AvgBuildDurationForGeoZone(GeoZoneData geoZoneData) {
        this.geoZoneData = geoZoneData;
    }

    /**
     * Return list of average build duration for each Geo zone.
     * @return List of average build duration.
     */
    @Override
    public List<String> createReport() {
        Map<String, List<Data>> geoZoneToDataMap = geoZoneData.getGeoZoneData();
        List<String> output = new ArrayList<>();
        geoZoneToDataMap.forEach((key, value) -> output.add("Geo Zone: " + key + ", Average build duration: "
                + value.stream().mapToInt(Data::getBuildDuration).average().orElse(0.0)));

        return output;
    }
}
