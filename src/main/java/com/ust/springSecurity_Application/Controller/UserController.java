package com.ust.springSecurity_Application.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/home")
    @PreAuthorize("hasRole('USER')")
    public String getHome(){
    return "hi all welcome to UST..";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdmin(){
        return "welcome Admin to UST..";
    }

}
