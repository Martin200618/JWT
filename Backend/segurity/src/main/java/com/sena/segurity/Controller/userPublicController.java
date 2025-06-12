package com.sena.segurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.segurity.DTO.ResponseLogin;
import com.sena.segurity.DTO.ResponsesDTO;
import com.sena.segurity.DTO.requestLoginDTO;
import com.sena.segurity.DTO.requestRegisterUserDTO;
import com.sena.segurity.Service.userService;
@RestController
@RequestMapping("api/v1/public/users")
public class userPublicController {
    @Autowired
    private userService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody requestRegisterUserDTO user) {
        ResponsesDTO response = userService.save(user);
        // ResponsesDTO response =null;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login") //falta desarrollar
    public ResponseEntity<ResponseLogin> login(@RequestBody requestLoginDTO userDTO) {
        ResponseLogin response = userService.login(userDTO);
        // ResponseLogin response = null;
        return new ResponseEntity<ResponseLogin>(response, HttpStatus.OK);
    }

    //  @PostMapping("/forgot") //falta desarrollar
    // public ResponseEntity<Object> forgot(@RequestBody UserDTO userDTO) {
    //     // ResponsesDTO response = userService.save(userDTO);
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }   
}