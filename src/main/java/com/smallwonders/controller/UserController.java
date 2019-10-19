package com.smallwonders.controller;

import com.smallwonders.model.auth.User;
import com.smallwonders.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rucsac on 10/10/2018.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private SecurityContextService securityContextService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://lit-beach-29911.herokuapp.com"})
    @GetMapping("/me")
    public User getMe() {
        return securityContextService.currentUser().orElseThrow(RuntimeException::new);
    }




}
