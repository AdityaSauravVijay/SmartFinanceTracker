package com.smartfinancetracker.service;

import com.smartfinancetracker.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    ArrayList<Category> saveCategories(ArrayList<Category> category);

    ArrayList<Category> bindCategories(Category category);
    Optional<Category> getCategory(long categoryID);

    public Category updateCategory(Category updateRequest) throws Exception;

    public void deleteCategory(Long categoryId);

    public List<Category> getAllACategories();

    List<Category> fetchCategoriesOfUser(long userId);
}
