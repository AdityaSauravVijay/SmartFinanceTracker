package com.smartfinancetracker.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Column(name = "parentCategoryId", nullable = false)
    private int parentCategoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "categoryName", nullable = false)
    private String categoryName;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "budget", nullable = false)
    private double budget;

    @Column(name="dateTime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateTime;

    @Transient
    private List<Category> subCategories;

    public Category() {
        this.subCategories = new ArrayList<>();
        this.dateTime = new Date();
    }

    public Category(int categoryId, int parentCategoryId, int userId, String categoryName, double budget) {
        this();
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.budget = budget;
    }

    public void addSubCategory(Category subCategory) {
        if (this.subCategories == null) {
            this.subCategories = new ArrayList<>();
        }
        this.subCategories.add(subCategory);
    }

    // Un-comment and implement these methods to manage subcategories effectively
    public boolean removeSubCategoryById(int subCategoryId) {
        if (subCategories != null) {
            return subCategories.removeIf(subCategory ->
                    subCategory.getCategoryId() == subCategoryId || subCategory.removeSubCategoryById(subCategoryId));
        }
        return false;
    }

    public double getTotalBudget() {
        double total = this.budget;
        if (subCategories != null) {
            for (Category subCategory : subCategories) {
                total += subCategory.getTotalBudget();
            }
        }
        return total;
    }

    public List<Category> flatten() {
        List<Category> flatList = new ArrayList<>();
        Queue<Category> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            Category current = queue.poll();
            flatList.add(current);
            if (current.getSubCategories() != null) {
                queue.addAll(current.getSubCategories());
            }
        }
        return flatList;
    }
}
