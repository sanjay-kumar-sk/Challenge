package com.oracle.model;

import java.util.Objects;

/**
 * This class represents the data model that needs to be read from each line of input.
 */
public class Data {
    private final int customerId;
    private final int contractId;
    private final String geoZone;
    private final String teamCode;
    private final String projectCode;
    private final int buildDuration;

    public Data(int customerId, int contractId, String geoZone, String teamCode, String projectCode, int buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geoZone = geoZone;
        this.teamCode = teamCode;
        this.projectCode = projectCode;
        this.buildDuration = buildDuration;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getContractId() {
        return contractId;
    }

    public String getGeoZone() {
        return geoZone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getBuildDuration() {
        return buildDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return customerId == data.customerId && contractId == data.contractId && buildDuration == data.buildDuration && geoZone.equals(data.geoZone) && teamCode.equals(data.teamCode) && projectCode.equals(data.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, contractId, geoZone, teamCode, projectCode, buildDuration);
    }

    public boolean isValid() {
        return this.customerId > 0 && this.contractId > 0 && !this.geoZone.isEmpty()
                && !this.projectCode.isEmpty() && !this.teamCode.isEmpty() && this.buildDuration > 0;
    }
}