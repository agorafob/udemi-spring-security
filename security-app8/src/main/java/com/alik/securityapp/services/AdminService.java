package com.alik.securityapp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ADMIN')")
    public void doAdminStuff(){
        System.out.println("Only admin here");
    }
}
