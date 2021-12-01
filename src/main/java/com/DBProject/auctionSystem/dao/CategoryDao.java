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

    public Category getCategoryByCategoryID(int categoryID) {
        String sql = "SELECT * FROM category WHERE categoryID = ?";
        return new ResultMapper<Category>().queryForObject(Category.class, jdbc, sql, categoryID);
    }

    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM category";
        return new ResultMapper<Category>().queryForList(jdbc, sql);
    }

    public List<TopCategoryDto> getTopCategories() {
        String sql = "SELECT * FROM top_categories";
        return new ResultMapper<TopCategoryDto>().queryForList(jdbc, sql);
    }

    public Category addCategory(Category category) {
        String sql = "INSERT INTO category (`name`) VALUES (?)";

        int result = jdbc.update(sql, category.getName());

        return result > 0 ? category : null;
    }

    public boolean updateCategory(Category category, int categoryID) {
        String sql = "UPDATE category SET name = ? WHERE categoryID = ?";

        int result = jdbc.update(
            sql,
            category.getName(),
            categoryID
        );

        return result == 1;
    }

    public boolean deleteCategory(int categoryID) {
        String sql = "DELETE FROM category WHERE categoryID = ?";
        int result = jdbc.update(sql, categoryID);

        return result == 1;
    }
}
