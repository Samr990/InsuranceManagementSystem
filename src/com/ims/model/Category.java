package com.ims.model;

import java.util.List;
import java.util.Objects;

public class Category {
    private int categoryId;
    private String categoryName;
    private String description; // Short description of the category
    private List<SubCategory> subCategories; // List of insurance subcategories under this category

    // Constructor (Mandatory fields only)
    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    public Category(int categoryId, String categoryName, List<SubCategory> subList) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategories = subList;
    }

    // Getters & Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return categoryId == category.categoryId && Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName);
    }

    // toString method
    @Override
    public String toString() {
        return "Category{ " +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                " }";
    }
}
