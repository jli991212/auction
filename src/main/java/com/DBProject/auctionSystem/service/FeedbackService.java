package com.DBProject.auctionSystem.service;

import java.util.List;

import com.DBProject.auctionSystem.dao.FeedbackDao;
import com.DBProject.auctionSystem.model.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    @Autowired
    FeedbackDao feedbackDao;

    public List<Feedback> getAllFeedbackReceived(int memberID) {
        return feedbackDao.getAllFeedbackReceived(memberID);
    }
}
