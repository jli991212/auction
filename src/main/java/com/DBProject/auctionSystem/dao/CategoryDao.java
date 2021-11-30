package com.DBProject.auctionSystem.dao;

import java.util.List;

import com.DBProject.auctionSystem.dto.TopCategoryDto;
import com.DBProject.auctionSystem.model.Category;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        return new ResultMapper<Category>().queryForList(jdbc, sql);
    }

    public List<TopCategoryDto> getTopCategories() {
        String sql = "SELECT * FROM top_categories";
        return new ResultMapper<TopCategoryDto>().queryForList(jdbc, sql);
    }

    public Category addCategory(Category category) {
        return category;
    }

    public Category updateCategory() {
        return null;
    }

    public boolean deleteCategory() {
        return true;
    }

}
