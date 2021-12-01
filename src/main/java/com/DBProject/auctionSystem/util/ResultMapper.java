package com.DBProject.auctionSystem.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;

public class ResultMapper<T> {
    ObjectMapper om;

    public ResultMapper() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }
    
    public List<T> queryForList(JdbcTemplate jdbc, String sql, @Nullable Object ...args) {
        List<T> list = new ArrayList<>();

        try {
            List<Map<String, Object>> result = jdbc.queryForList(sql, args);
            list = om.convertValue(result, new TypeReference<List<T>>(){});
        } catch(Exception e) { }

        return list;
    }

    public T queryForObject(Class<T> tClass, JdbcTemplate jdbc, String sql, @Nullable Object ...args) {
        T object = null;

        try {
            Map<String, Object> result = jdbc.queryForMap(sql, args);
            object = om.convertValue(result, tClass);
        } catch(Exception e) { }

        return object;
    }
}
