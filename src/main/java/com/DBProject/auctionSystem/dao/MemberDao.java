package com.DBProject.auctionSystem.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.DBProject.auctionSystem.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MemberDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<String> getMembers() {
        String sql = "SELECT name FROM member";
        return jdbc.queryForList(sql, String.class);
    }

    public Member getMember(int memberID) {
        String sql = "SELECT name FROM member WHERE memberID=?";
        Member member = null;

        try {
            member = jdbc.queryForObject(sql, (ResultSet rs, int rowNum) -> {
                Member m = new Member();

                m.setName(rs.getString("name"));
                
                return m;
            }, memberID);
        } catch(Exception e) {}

        return member;
    }

    public List<String> getQuery(String query){
        try {
            DataSource ds = jdbc.getDataSource();
            Connection myConn = DataSourceUtils.getConnection(ds);
            Statement stmt = myConn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            // column names and count
            ResultSetMetaData rsMetaData = res.getMetaData();
            //Retrieving the list of column names
            int count = rsMetaData.getColumnCount();
            List<String> colNames = new ArrayList<>();
            for(int i = 1; i<=count; i++) {
                colNames.add(rsMetaData.getColumnName(i));
            }
            // get result
            List<String> result = new ArrayList<>();
            while(res.next()){ // each record
                StringBuilder ret = new StringBuilder();
                for(String s : colNames){
                    ret.append(s + ": " + res.getString(s)+", ");
                }
                result.add(ret.toString());
            }
            return result;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Member addMember(Member member) {
        String sql = "INSERT INTO member (`name`, `email`, `password`, `memberType`) VALUES (?, ?, ?, ?)";

        int result = jdbc.update(
            sql,
            member.getName(),
            member.getEmail(),
            member.getPassword(),
            member.getMemberType()
        );

        return result > 0 ? member : null;
    }
}
