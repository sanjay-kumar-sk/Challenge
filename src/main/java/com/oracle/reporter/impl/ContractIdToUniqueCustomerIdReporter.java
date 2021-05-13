package com.oracle.reporter.impl;

import com.oracle.model.ContractData;
import com.oracle.reporter.Reporter;

import java.util.*;

public class ContractIdToUniqueCustomerIdReporter implements Reporter {
    private final ContractData contractData;

    public ContractIdToUniqueCustomerIdReporter(ContractData contractData) {
        this.contractData = contractData;
    }

    /**
     * Retrun number of unique customer ids for each contract id.
     * @return List of unique customer ids
     */
    @Override
    public List<String> createReport() {
        List<String> output = new ArrayList<>();
        contractData.getContractIdToUniqueCustomerIds().forEach((key, value) -> output.add("ContractId: " + key + ", Num of unique CustomerId: " + value.size()));

        return output;
    }
}
