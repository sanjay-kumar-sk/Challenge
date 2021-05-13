package com.oracle.model;

import java.util.*;

public class GeoZoneData {
    Map<String, List<Data>> keyToDataMap;

    private GeoZoneData(Map<String, List<Data>> keyToDataMap) {
        this.keyToDataMap = keyToDataMap;
    }

    public static GeoZoneData from(List<Data> dataList) {
        Map<String, List<Data>> contractIdToDataMap = new HashMap<>();
        dataList.forEach(data -> {
            contractIdToDataMap.putIfAbsent(data.getGeoZone(), new ArrayList<>());
            contractIdToDataMap.get(data.getGeoZone()).add(data);
        });

        return new GeoZoneData(contractIdToDataMap);
    }

    public Map<String, List<Data>> getGeoZoneData() {
        return keyToDataMap;
    }


    /**
     * Return map of numbers of Unique customer ids set based on each gea zone.
     *
     * @return
     */
    public Map<String, Set<Integer>> getUniqueCustomeridsForEachGeoZone() {
        Map<String, Set<Integer>> output = new HashMap<>();
        keyToDataMap.forEach((key, value) -> {
            Set<Integer> uniqueCustomerIds = new HashSet<>();
            value.forEach(data -> uniqueCustomerIds.add(data.getCustomerId()));
            output.put(key, uniqueCustomerIds);
        });
        return output;
    }

}
