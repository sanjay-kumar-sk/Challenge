package com.oracle.report;

import com.oracle.model.Data;
import com.oracle.utils.FileUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class generates the report based on the input data.
 */
public class Reporter {

    private static final String fileName = "src/main/resources/input.txt";

    List<Data> dataList;

    public Reporter() {
        dataList = readData();
    }

    /**
     * Read Data from input file and transfrom into model object.
     * @return List of Data objects.
     */
    private List<Data> readData() {
        List<Data> dataList = null;
        try {
            dataList = FileUtils.readFileAndTransform(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }

    /**
     * Generates the required report.
     *
     * This can also return a List or a Report object with all the data.
     */
    public void generateReport() {
        System.out.println("The number of unique customerId for each contractId.");
        getNumberOfUniqueCustomerIdForEachContractId().forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        System.out.println("The number of unique customerId for each geoZone.");
        getNumberOfUniqueCustomerIdForEachGeoZone().forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        System.out.println("The average buildDuration for each geoZone.");
        getAvgBuildDurationOfEachGeoZone().forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        System.out.println("The list of unique customerId for each geoZone.");
        getUniqueCustomerIdForEachGeoZone().forEach(System.out::println);
    }

    /**
     * Retrun number of unique customer ids for each contract id.
     * @return List of unique customer ids
     */
    List<String> getNumberOfUniqueCustomerIdForEachContractId() {
        Map<Integer, Set<Integer>> contractIdToCustomerIdMap = new HashMap<>();
        dataList.forEach(data -> {
            contractIdToCustomerIdMap.putIfAbsent(data.getContractId(), new HashSet<>());
            contractIdToCustomerIdMap.get(data.getContractId()).add(data.getCustomerId());
        });

        List<String> output = new ArrayList<>();
        contractIdToCustomerIdMap.forEach((key, value) -> output.add("ContractId: " + key + ", Num of unique CustomerId: " + value.size()));

        return output;
    }

    /**
     * Retrun list of average build duration for each Geo zone.
     * @return List of average build duration.
     */
    List<String> getAvgBuildDurationOfEachGeoZone() {
        Map<String, List<Data>> geoZoneToDataMap = getGeoZoneToDataMap();
        List<String> output = new ArrayList<>();
        geoZoneToDataMap.forEach((key, value) -> output.add("Geo Zone: " + key + ", Average build duration: "
                + value.stream().mapToInt(Data::getBuildDuration).average().orElse(0.0)));

        return output;
    }

    /**
     * Generates number of unique customer id for each Geo zone.
     * @return List of numbers of unique customer ids.
     */
    List<String> getNumberOfUniqueCustomerIdForEachGeoZone() {
        Map<String, List<Data>> geoZoneToDataMap = getGeoZoneToDataMap();
        List<String> output = new ArrayList<>();
        getUniqueCustomeridsForEachGeoZone(geoZoneToDataMap).forEach((key, value) -> output.add("GeoZone: " + key + ", Num of unique CustomerId: " + value.size()));

        return output;
    }

    /**
     * Return map of numbers of Unique customer ids set based on each gea zone.
     * @param geoZoneToDataMap Geo zone to data map.
     * @return
     */
    private Map<String, Set<Integer>> getUniqueCustomeridsForEachGeoZone(Map<String, List<Data>> geoZoneToDataMap) {
        Map<String, Set<Integer>> output = new HashMap<>();
        geoZoneToDataMap.forEach((key, value) -> {
            Set<Integer> uniqueCustomerIds = new HashSet<>();
            value.forEach(data -> uniqueCustomerIds.add(data.getCustomerId()));
            output.put(key, uniqueCustomerIds);
        });
        return output;
    }

    /**
     * Generate list of Unique customer ids for each zones.
     * @return
     */
    List<String> getUniqueCustomerIdForEachGeoZone() {
        Map<String, List<Data>> geoZoneToDataMap = getGeoZoneToDataMap();
        List<String> output = new ArrayList<>();

        getUniqueCustomeridsForEachGeoZone(geoZoneToDataMap).forEach((key, value) -> {
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

    /**
     * Map geo zone to data.
     * @return
     */
    private Map<String, List<Data>> getGeoZoneToDataMap() {
        Map<String, List<Data>> geoZoneToDataMap = new HashMap<>();
        dataList.forEach(data -> {
            geoZoneToDataMap.putIfAbsent(data.getGeoZone(), new ArrayList<>());
            geoZoneToDataMap.get(data.getGeoZone()).add(data);
        });
        return geoZoneToDataMap;
    }


}
