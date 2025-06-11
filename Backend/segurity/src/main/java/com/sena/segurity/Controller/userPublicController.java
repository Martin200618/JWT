package com.sena.segurity.Controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/public/user")
@CrossOrigin
@RequiredArgsConstructor
public class userPublicController {
    @PostMapping("/login/")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("End-point publico login",HttpStatus.OK);
    }
    
    @PostMapping("/register/")
    public ResponseEntity<String> register(){
        return new ResponseEntity<>("End-point publico register", HttpStatus.OK);
    }
}