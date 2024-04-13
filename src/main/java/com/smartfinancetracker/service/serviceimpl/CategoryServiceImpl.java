package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.CategoryDao;
import com.smartfinancetracker.models.Category;
import com.smartfinancetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        category.getSubCategories().forEach(categoryList -> {
            finalCategories.add(categoryList);
        });
        return finalCategories;
    }

    public Category updateCategory(Category updateRequest) throws Exception {
        Category category = categoryRepository.findById((long)updateRequest.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found"));

        // Update fields
        category.setCategoryName(updateRequest.getCategoryName());
        category.setParentCategoryId(updateRequest.getParentCategoryId());
        category.setBudget(updateRequest.getBudget());
        category.setUserId(updateRequest.getUserId());
        // Update other fields as needed

        return categoryRepository.save(category);
    }

    public void deleteCategory(Category categoryToBeDeleted){
        categoryRepository.deleteById((long) categoryToBeDeleted.getCategoryId());
    }

    public Category buildCategoryTree(List<Category> categories) {
        Map<Integer, Category> categoryMap = new HashMap<>();
        Category root = null;


        for (Category category : categories) {
            categoryMap.put(category.getCategoryId(), category);
            category.setSubCategories(new ArrayList<>());
        }

        for (Category category : categories) {
            if (category.getParentCategoryId() == 0) {
                root = category;
            } else {
                Category parent = categoryMap.get(category.getParentCategoryId());
                if (parent != null) {
                    parent.getSubCategories().add(category);
                }
            }
        }

        return root; // Return the root of the tree
    }
    @Override
    public Category getAllACategories(){
        List<Category> allCategories = categoryRepository.findAll();
        Category rootCategory = buildCategoryTree(allCategories);
        return rootCategory;
    }
}
