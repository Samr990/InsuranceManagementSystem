package com.ims.model;

import java.time.LocalDate;
import java.util.Objects;

public class Policy {
    private final int policyId; // Immutable ID
    private int userId;
    private String policyName;
    private double premiumAmount;
    private double coverageAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Category category; // Insurance category
    private SubCategory subCategory; // Insurance sub-category
    private String status; // Policy status as a string (e.g., "Applied", "Activated", "Canceled")

    // Constructor (Mandatory fields)
    public Policy(int policyId, int userId, String policyName, double premiumAmount, double coverageAmount,
                  LocalDate startDate, LocalDate endDate, Category category, SubCategory subCategory) {
        this.policyId = policyId;
        this.userId = userId;
        this.policyName = policyName;
        this.premiumAmount = premiumAmount;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.subCategory = subCategory;
        this.status = "Applied"; // Default status is applied
    }

    // Getters and setters
    public int getPolicyId() {
        return policyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return policyId == policy.policyId && Double.compare(policy.premiumAmount, premiumAmount) == 0
                && Double.compare(policy.coverageAmount, coverageAmount) == 0 && Objects.equals(userId, policy.userId)
                && Objects.equals(policyName, policy.policyName) && Objects.equals(startDate, policy.startDate)
                && Objects.equals(endDate, policy.endDate) && Objects.equals(category, policy.category)
                && Objects.equals(subCategory, policy.subCategory) && Objects.equals(status, policy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId, userId, policyName, premiumAmount, coverageAmount, startDate, endDate, category, subCategory, status);
    }

    // Updated toString method
    @Override
    public String toString() {
        return "Policy{ " +
                "policyId=" + policyId +
                ", userId='" + userId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", premiumAmount=" + premiumAmount +
                ", coverageAmount=" + coverageAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", category=" + (category != null ? category.getCategoryName() : "N/A") +
                ", subCategory=" + (subCategory != null ? subCategory.getSubCategoryName() : "N/A") +
                ", status='" + status + '\'' +
                " }";
    }
}
