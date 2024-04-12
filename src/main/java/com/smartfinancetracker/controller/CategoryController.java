package com.smartfinancetracker.controller;

import com.smartfinancetracker.models.Category;
import com.smartfinancetracker.models.User;
import com.smartfinancetracker.service.CategoryService;
import com.smartfinancetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super();
        this.categoryService = categoryService;
    }

    //Register api user
    @PostMapping
    public ResponseEntity<List<Category>> registerCategory(@RequestBody Category category) {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories = categoryService.bindCategories(category);
        List<Category> savedCategories = categoryService.saveCategories(categories);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategories);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<Category> getCategory(@PathVariable long categoryID) {
        Optional<Category> categoryData = categoryService.getCategory(categoryID);
        return categoryData.map(category -> new ResponseEntity<>(category, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable int categoryId, @RequestBody Category updateRequest) throws Exception {
        updateRequest.setCategoryId(categoryId);
        Category updatedCategory = categoryService.updateCategory(updateRequest);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId, @RequestBody Category updateRequest) {
        updateRequest.setCategoryId(categoryId);
        categoryService.deleteCategory(updateRequest);
        return ResponseEntity.ok("Deleted");
    }
}
