package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.dto.TopCategoryDto;
import com.DBProject.auctionSystem.model.Category;
import com.DBProject.auctionSystem.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
