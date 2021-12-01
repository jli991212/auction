package com.DBProject.auctionSystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

public class ResultMapper<T> {
    public List<T> queryForList(JdbcTemplate jdbc, String sql, @Nullable Object ...args) {
        List<T> list = new ArrayList<>();

        try {
            ObjectMapper om = new ObjectMapper();
            List<Map<String, Object>> result = jdbc.queryForList(sql, args);

            list = om.convertValue(result, new TypeReference<List<T>>(){});
        } catch(Exception e) {}

        return list;
    }

    public T queryForObject(Class<T> tClass, JdbcTemplate jdbc, String sql, @Nullable Object ...args) {
        T object = null;

        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, Object> result = jdbc.queryForMap(sql, args);

            object = om.convertValue(result, tClass);
        } catch(Exception e) {}

        return object;
    }
}
