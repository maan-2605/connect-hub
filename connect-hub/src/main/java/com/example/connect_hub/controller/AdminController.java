package com.example.connect_hub.controller;

import com.example.connect_hub.service.AdminService;
import com.example.connect_hub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        if (!userService.isAdmin(authentication.getName())) {
            return "redirect:/";
        }

        model.addAttribute("totalUsers", adminService.getTotalUsers());
        model.addAttribute("totalLinks", adminService.getTotalLinks());
        model.addAttribute("totalPageViews", adminService.getTotalPageViews());
        model.addAttribute("totalLinkClicks", adminService.getTotalLinkClicks());
        model.addAttribute("users", adminService.getAllUsers());

        return "admin/dashboard";
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable String userId, Authentication authentication) {
        if (!userService.isAdmin(authentication.getName())) {
            return "redirect:/";
        }

        adminService.deleteUser(userId);
        return "redirect:/admin/dashboard";
    }
}