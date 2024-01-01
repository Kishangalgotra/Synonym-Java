package com.synonys.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "charge_point_operator")
public class ChargePointOperator {

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public BigDecimal getStationMinuteCost() {
        return stationMinuteCost;
    }

    public void setStationMinuteCost(BigDecimal stationMinuteCost) {
        this.stationMinuteCost = stationMinuteCost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyId;

    private String companyName;

    private String stationName;

    private BigDecimal stationMinuteCost;

}
