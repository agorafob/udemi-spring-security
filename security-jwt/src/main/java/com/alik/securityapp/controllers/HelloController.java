package com.alik.securityapp.controllers;

import com.alik.securityapp.security.PersonDetails;
import com.alik.securityapp.services.AdminService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final AdminService adminService;

    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo(@AuthenticationPrincipal PersonDetails personDetails){
        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStuff();
        return "admin";
    }

}
