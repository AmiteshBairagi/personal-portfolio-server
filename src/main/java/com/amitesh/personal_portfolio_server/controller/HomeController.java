package com.amitesh.personal_portfolio_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String home(){
        return "Server is up and running on port : " + port ;
    }
}
