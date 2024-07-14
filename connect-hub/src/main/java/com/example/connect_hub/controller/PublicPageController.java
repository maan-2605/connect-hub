package com.example.connect_hub.controller;


import com.example.connect_hub.model.User;
import com.example.connect_hub.service.AnalyticsService;
import com.example.connect_hub.service.LinkService;
import com.example.connect_hub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private LinkService linkService;

    @GetMapping("/{username}")
    public String showPublicPage(@PathVariable String username, Model model, HttpServletRequest request) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("links", linkService.getLinksByUserId(user.getId()));

        // Record page view
        analyticsService.recordPageView(user.getId(), request.getRemoteAddr(), request.getHeader("User-Agent"));

        return "public-page";
    }
}