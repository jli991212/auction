package com.DBProject.auctionSystem.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.DBProject.auctionSystem.model.Bid;
import com.DBProject.auctionSystem.model.Member;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MemberDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<Member> getMembers() {
        String sql = "SELECT * FROM member";
        return new ResultMapper<Member>().queryForList(jdbc, sql);
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

    public List<Member> getMemberByEmail(String email) { // for login
        String sql = "SELECT * FROM member WHERE email=\"" + email + "\"";
        List<Member> member = null;

        try {
            member = jdbc.query(sql, (rs, rowNum) ->
                    new Member(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("memberType")
                    ));
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("find member by email error");
        }

        return member;
    }

    public String verifyMember(String email, String password){
        List<Member> mems = getMemberByEmail(email);
        if(mems.size() == 0) {
            System.out.println("No such member");
            return "Please register first!";
        }
        Member mem = mems.get(0);
        if(!mem.getPassword().equals(password)){
            System.out.println("input password: " + password + " DB password: " + mem.getPassword());
            System.out.println("password not match");
            return "Login information does not match!";
        }
        return mem.getMemberType();
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
