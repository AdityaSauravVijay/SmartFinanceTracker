package com.smartfinacetracker.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Category {
    public String parentCategoryId;
    public String categoryId;
    public String categoryName;
    public String userId;
    public double budget;
    public List<Category> subCategories;

    public Category(String categoryId, String parentCategoryId, String userId, String categoryName, double budget) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.budget = budget;
        this.subCategories = new ArrayList<>();
    }

    // Method to add a subcategory
    public void addSubCategory(Category subCategory) {
        this.subCategories.add(subCategory);
    }

    // Getters and Setters
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getParentId() { return parentCategoryId; }
    public void setParentId(String parentId) { this.parentCategoryId = parentId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String name) { this.categoryName = name; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public List<Category> getSubCategories() { return subCategories; }

    // Method to find a category by ID (already provided)
    public Category findCategoryById(String searchCategoryId) {
        if (this.categoryId.equals(searchCategoryId)) {
            return this;
        }
        for (Category subCategory : this.subCategories) {
            Category foundCategory = subCategory.findCategoryById(searchCategoryId);
            if (foundCategory != null) {
                return foundCategory;
            }
        }
        return null; // Return null if the category is not found
    }

    // Method to remove a subcategory by ID
    public boolean removeSubCategoryById(String subCategoryId) {
        return subCategories.removeIf(subCategory -> subCategory.getCategoryId().equals(subCategoryId) ||
                subCategory.removeSubCategoryById(subCategoryId));
    }

    // Method to calculate the total budget including all subcategories
    public double getTotalBudget() {
        double total = this.budget;
        for (Category subCategory : this.subCategories) {
            total += subCategory.getTotalBudget();
        }
        return total;
    }

    // Method to flatten the tree into a list
    public List<Category> flatten() {
        List<Category> flatList = new ArrayList<>();
        Queue<Category> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Category current = queue.poll();
            flatList.add(current);
            queue.addAll(current.getSubCategories());
        }
        return flatList;
    }
}


