package com.example.obforum.controller;

import com.example.obforum.config.TokenProvider;
import com.example.obforum.dto.AuthToken;
import com.example.obforum.dto.LoginUser;
import com.example.obforum.model.User;
import com.example.obforum.dto.UserDto;
import com.example.obforum.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;
    private TokenProvider jwtTokenUtil;
    private UserService userService;

    public UserController(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }


    @PostMapping("/register")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/hello-admin")
//    public String adminPing(){
//        return "Only Admins Can Read This";
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/hello-admin-user")
//    public String adminUser(){
//        return "Only Admins and Users Can Read This";
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/hello-user")
//    public String userPing(){
//        return "Any User Can Read This";
//    }

}
