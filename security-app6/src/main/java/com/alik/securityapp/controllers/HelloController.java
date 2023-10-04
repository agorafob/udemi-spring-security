package com.alik.securityapp.controllers;

import com.alik.securityapp.security.PersonDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(@AuthenticationPrincipal PersonDetails personDetails){
        System.out.println(personDetails.person());
        return "hello";
    }

}
