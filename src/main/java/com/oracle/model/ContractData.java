package com.oracle.model;

import java.util.*;
import java.util.stream.Collectors;

public class ContractData {
    Map<Integer, List<Data>> keyToDataMap;

    private ContractData(Map<Integer, List<Data>> keyToDataMap) {
        this.keyToDataMap = keyToDataMap;
    }

    public static ContractData from(List<Data> dataList) {
        Map<Integer, List<Data>> contractIdToDataMap = new HashMap<>();
        dataList.forEach(data -> {
            contractIdToDataMap.putIfAbsent(data.getContractId(), new ArrayList<>());
            contractIdToDataMap.get(data.getContractId()).add(data);
        });

        return new ContractData(contractIdToDataMap);
    }


    public Map<Integer, Set<Integer>> getContractIdToUniqueCustomerIds() {
        Map<Integer, Set<Integer>> contractIdToCustomerIdMap = new HashMap<>();
        keyToDataMap.forEach((contractId, data) -> {
            contractIdToCustomerIdMap.putIfAbsent(contractId, new HashSet<>());
            Set<Integer> customerIds = data.stream().map(Data::getCustomerId).collect(Collectors.toSet());
            contractIdToCustomerIdMap.get(contractId).addAll(customerIds);
        });

        return contractIdToCustomerIdMap;
    }
}
