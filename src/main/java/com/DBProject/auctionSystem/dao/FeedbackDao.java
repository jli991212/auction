package com.DBProject.auctionSystem.dao;

import java.util.List;

import com.DBProject.auctionSystem.model.Feedback;
import com.DBProject.auctionSystem.util.ResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeedbackDao {
    @Autowired
    JdbcTemplate jdbc;

    public List<Feedback> getAllFeedbackReceived(int memberID) {
        String sql = "SELECT * FROM feedback WHERE receiverID=?";
        return new ResultMapper<Feedback>().queryForList(jdbc, sql, memberID);
    }

    public List<Feedback> getAllFeedbackSent(int memberID) {
        String sql = "SELECT * FROM feedback WHERE senderID=?";
        return new ResultMapper<Feedback>().queryForList(jdbc, sql, memberID);
    }

    public List<Feedback> getFeedbackFromMemberID(int receiverID, int senderID) {
        String sql = "SELECT * FROM feedback WHERE receiverID=? AND senderID=?";
        return new ResultMapper<Feedback>().queryForList(jdbc, sql, receiverID, senderID);
    }
}
