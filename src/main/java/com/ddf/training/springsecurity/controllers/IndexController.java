package com.ddf.training.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping({"", "index", "home"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping({"login", "sign-in"})
    public String getLoginPage(){
        return "login";
    }

    @GetMapping({"courses"})
    public String getCourses(){
        return "courses";
    }

}
