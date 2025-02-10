package com.ims.model;

import java.time.LocalDate;
import java.util.Objects;

public class Policy {
    private final int policyId; // Immutable ID
    private String policyName;
    private double premiumAmount;
    private double coverageAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // Using String for status

    // Constructor
    public Policy(int policyId, String policyName, double premiumAmount, double coverageAmount,
                  LocalDate startDate, LocalDate endDate) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Inactive"; // Default status
    }

    // Getters
    public int getPolicyId() {
        return policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Equals & HashCode (Based on policyId only)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return policyId == policy.policyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId);
    }

    // ToString
    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", policyName='" + policyName + '\'' +
                ", premiumAmount=" + premiumAmount +
                ", coverageAmount=" + coverageAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
