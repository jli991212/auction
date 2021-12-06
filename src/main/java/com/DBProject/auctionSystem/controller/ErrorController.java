package com.DBProject.auctionSystem.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(Throwable.class)
    public ModelAndView exception(final Throwable throwable, final Model model) {
        String error = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("error", error);
        return new ModelAndView("error");
    }
}