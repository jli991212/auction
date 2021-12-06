package com.DBProject.auctionSystem.controller;

import java.util.List;

import com.DBProject.auctionSystem.model.Feedback;
import com.DBProject.auctionSystem.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping(path = "/received/{memberID}")
    public List<Feedback> getAllFeedbackReceived(@PathVariable int memberID) {
        return feedbackService.getAllFeedbackReceived(memberID);
    }

    @GetMapping("/{memberID}/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllFeedback(@PathVariable int memberID) {
        ModelAndView model = new ModelAndView();
        List<Feedback> feedbackList = this.getAllFeedbackReceived(memberID);
        
        if(feedbackList == null)
            return new ModelAndView("redirect:/");

        model.addObject("feedbackList", feedbackList);
        model.setViewName("feedback_list");

        return model;
    }
}
