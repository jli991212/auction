package com.DBProject.auctionSystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jdbc.core.JdbcTemplate;

public class ResultMapper<T> {
    public List<T> queryForList(JdbcTemplate jdbc, String sql) {
        List<T> list = new ArrayList<>();

        try {
            ObjectMapper om = new ObjectMapper();

            List<Map<String, Object>> result = jdbc.queryForList(sql);

            list = om.convertValue(result, new TypeReference<List<T>>(){});
        } catch(Exception e) {}

        return list;
    }
}
