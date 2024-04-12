package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.CategoryDao;
import com.smartfinancetracker.models.Category;
import com.smartfinancetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ArrayList<Category> saveCategories(ArrayList<Category> categories) {
//        ArrayList<Category> savedCategories = new ArrayList<>();
//       categories.forEach(category -> {
//           savedCategories.add(categoryRepository.save(category));
//       });
//
       List<Category> savedCategories = categoryRepository.saveAll(categories);
       return new ArrayList<>(savedCategories);
    }

    @Override
    public Optional<Category> getCategory(long categoryID){
        return categoryRepository.findById(categoryID);
    }

    @Override
    public ArrayList<Category> bindCategories(Category category){
        ArrayList<Category> finalCategories = new ArrayList<>();
        finalCategories.add(category);
        category.subCategories.forEach(categoryList -> {
            finalCategories.add(categoryList);
        });
        return finalCategories;
    }

    public Category updateCategory(Category updateRequest) throws Exception {
        Category category = categoryRepository.findById((long)updateRequest.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        // Update fields
        category.setCategoryName(updateRequest.categoryName);
        category.setParentCategoryId(updateRequest.parentCategoryId);
        category.setBudget(updateRequest.budget);
        category.setUserId(updateRequest.userId);
        // Update other fields as needed

        return categoryRepository.save(category);
    }

    public void deleteCategory(Category categoryToBeDeleted){
        categoryRepository.deleteById((long)categoryToBeDeleted.categoryId);
    }
}
