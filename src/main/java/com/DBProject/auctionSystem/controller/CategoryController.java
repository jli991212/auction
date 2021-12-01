package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.dto.TopCategoryDto;
import com.DBProject.auctionSystem.model.Category;
import com.DBProject.auctionSystem.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> index() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/top")
    public List<TopCategoryDto> getTop() {
        return categoryService.getTopCategories();
    }

    @GetMapping(path = "/{categoryID}")
    public Category getByID(@PathVariable int categoryID) {
        return categoryService.getCategoryByCategoryID(categoryID);
    }

    @PostMapping(path = "/add")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping(path = "/update/{categoryID}")
    public boolean updateCategory(@RequestBody Category category, @PathVariable int categoryID) {
        return categoryService.updateCategory(category, categoryID);
    }

    @DeleteMapping(path = "/delete/{categoryID}")
    public boolean deleteCategory(@PathVariable int categoryID) {
        return categoryService.deleteCategory(categoryID);
    }
}
