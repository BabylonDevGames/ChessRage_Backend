package com.ibabylon.chessrage.controller.api;


import com.ibabylon.chessrage.security.model.FirebaseUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @PostMapping("/info")
    public String testUser(){
        final FirebaseUserDetails auth =(FirebaseUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return auth.getId();
    }
}
