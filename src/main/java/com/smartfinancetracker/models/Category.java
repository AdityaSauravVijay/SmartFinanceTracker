package com.smartfinancetracker.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Column(name = "parentCategoryId", nullable = false)
    public int parentCategoryId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public int categoryId;
    @Column(name = "categoryName", nullable = false)
    public String categoryName;
    @Column(name = "userId", nullable = false)
    public int userId;
    @Column(name = "budget", nullable = false)
    public double budget;
    @Column(name="dateTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date dateTime;
    //@Column(name = "userId", nullable = false)
    @Transient
    public List<Category> subCategories;

    public Category() {
    }
    public Category(int categoryId, int parentCategoryId, int userId, String categoryName, double budget) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.budget = budget;
        this.dateTime = getDateTime();
        this.subCategories = new ArrayList<>();
    }

     //Method to add a subcategory
//    public void addSubCategory(Category subCategory) {
//        this.subCategories.add(subCategory);
//    }

    // Getters and Setters
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getParentId() { return parentCategoryId; }
    public void setParentId(int parentId) { this.parentCategoryId = parentId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String name) { this.categoryName = name; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public void setDateTime(){
        this.dateTime = getDateTime();
    }

    public Date getDateTime(){
        return this.dateTime;
    }

   // public List<Category> getSubCategories() { return subCategories; }

    // Method to find a category by ID (already provided)
    public Category findCategoryById(int searchCategoryId) {
        if (this.categoryId == searchCategoryId) {
            return this;
        }
//        for (Category subCategory : this.subCategories) {
//            Category foundCategory = subCategory.findCategoryById(searchCategoryId);
//            if (foundCategory != null) {
//                return foundCategory;
//            }
//        }
        return null; // Return null if the category is not found
    }

    // Method to remove a subcategory by ID
//    public boolean removeSubCategoryById(int subCategoryId) {
//        return subCategories.removeIf(subCategory -> subCategory.getCategoryId() == subCategoryId ||
//                subCategory.removeSubCategoryById(subCategoryId));
//    }

    // Method to calculate the total budget including all subcategories
    public double getTotalBudget() {
        double total = this.budget;
//        for (Category subCategory : this.subCategories) {
//            total += subCategory.getTotalBudget();
//        }
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
            //queue.addAll(current.getSubCategories());
        }
        return flatList;
    }
}


