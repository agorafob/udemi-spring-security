package com.alik.securityapp.controllers;

import com.alik.securityapp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getPrincipal());
        return "hello";
    }

//    @GetMapping("/showUserInfo")
//    public String showUserInfo(@AuthenticationPrincipal User user){
//        System.out.println(user);
//        return "hello";
//    }
}
