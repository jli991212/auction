package com.DBProject.auctionSystem.service;

import java.util.List;

import com.DBProject.auctionSystem.dao.CategoryDao;
import com.DBProject.auctionSystem.dto.TopCategoryDto;
import com.DBProject.auctionSystem.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    public List<TopCategoryDto> getTopCategories() {
        return categoryDao.getTopCategories();
    }
}