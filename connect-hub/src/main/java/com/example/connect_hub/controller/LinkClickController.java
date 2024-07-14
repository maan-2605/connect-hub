package com.example.connect_hub.controller;

import com.example.connect_hub.model.Link;
import com.example.connect_hub.service.AnalyticsService;
import com.example.connect_hub.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class LinkClickController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/l/{linkId}")
    public RedirectView handleLinkClick(@PathVariable String linkId, HttpServletRequest request) {
        Link link = linkService.getLinkById(linkId);
        analyticsService.recordLinkClick(link.getUserId(), linkId, request.getRemoteAddr(), request.getHeader("User-Agent"));
        return new RedirectView(link.getUrl());
    }
}