package com.smartfinancetracker.service.serviceimpl;

import com.smartfinancetracker.dao.CategoryDao;
import com.smartfinancetracker.models.Category;
import com.smartfinancetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
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
        ArrayList<Category> savedCategories = new ArrayList<>();

        categories.forEach(category -> {
            Category savedMainCategory = categoryRepository.save(category);
            savedCategories.add(savedMainCategory);

            category.getSubCategories().forEach(subCategory -> {
                subCategory.setParentCategoryId(savedMainCategory.getCategoryId());
                Category savedSubCategory = categoryRepository.save(subCategory);
                //savedCategories.add(savedSubCategory);
            });
        });

        return savedCategories;
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

    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> buildCategoryTree(List<Category> categories) {
        Map<Integer, Category> categoryMap = new HashMap<>();
        List<Category> roots = new ArrayList<>();

        // First, map all categories by their ID for quick access.
        for (Category category : categories) {
            categoryMap.put(category.getCategoryId(), category);
            // Initialize subcategories list
            category.setSubCategories(new ArrayList<>());
        }

        // Set up the parent-child relationships.
        for (Category category : categories) {
            if (category.getParentCategoryId() == 0) {
                roots.add(category); // This category is one of the roots of the trees
            } else {
                Category parent = categoryMap.get(category.getParentCategoryId());
                if (parent != null) {
                    parent.getSubCategories().add(category);
                }
            }
        }

        return roots; // Return the list of root categories
    }

    public Category buildCategoryTreeUsingImplementedClass(List<Category> categories) {
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
    public List<Category> getAllACategories(){
        List<Category> allCategories = categoryRepository.findAll();
        List<Category> rootCategory = buildCategoryTree(allCategories);
        return rootCategory;
    }


    @Override
    public List<Category> fetchCategoriesOfUser(long userId){
        return buildCategoryTree(categoryRepository.findByuserId(userId));
    }
}
