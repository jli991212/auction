package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.model.Feedback;
import com.DBProject.auctionSystem.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping(path = "/received/{memberID}")
    public List<Feedback> getAllFeedbackReceived(@PathVariable int memberID) {
        return feedbackService.getAllFeedbackReceived(memberID);
    }
}
