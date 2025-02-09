package com.ims.model;

import java.util.Objects;

public class SubCategory {
    private int subCategoryId;
    private String subCategoryName;
    private String description; // Short description of subcategory
    private Category parentCategory; // Reference to parent category

    // Constructor (Mandatory fields only)
    public SubCategory(int subCategoryId, String subCategoryName, Category parentCategory) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.parentCategory = parentCategory;
    }

    // Getters & Setters
    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return subCategoryId == that.subCategoryId && Objects.equals(subCategoryName, that.subCategoryName)
                && Objects.equals(parentCategory, that.parentCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategoryId, subCategoryName, parentCategory);
    }

    // toString method
    @Override
    public String toString() {
        return "SubCategory{ " +
                "subCategoryId=" + subCategoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", parentCategory=" + (parentCategory != null ? parentCategory.getCategoryName() : "N/A") +
                " }";
    }
}
