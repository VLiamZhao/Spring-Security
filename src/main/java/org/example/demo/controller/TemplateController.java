package org.example.demo.controller;

import org.example.demo.auth.ApplicationUser;
import org.example.demo.auth.ApplicationUserDao;
import org.example.demo.auth.ApplicationUserService;
import org.example.demo.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @Autowired
    ApplicationUserService applicationUserService;

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("courses")
    public String getCoursesView() {
        return "courses";
    }


}