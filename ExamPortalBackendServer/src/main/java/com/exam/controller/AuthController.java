package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.entities.JwtRequest;
import com.exam.entities.JwtResponse;
import com.exam.entities.User;
import com.exam.repo.UserRepository;
import com.exam.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthController {

	@Autowired
	private UserDetailsServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
    	
    	System.out.println("inside login Handler");
    	System.out.println(request.getUserName() + ">>>>>" + request.getPassword());
        this.doAuthenticate(request.getUserName(), request.getPassword());

        System.out.println("Authentication done");
        System.out.println("Username : " + request.getUserName());
        UserDetails userDetails = userServiceImpl.loadUserByUsername(request.getUserName());
        String token = this.jwtUtil.generateToken(userDetails.getUsername());
        
        //retrieving user
        User user = userRepository.findByUserName(userDetails.getUsername());
        
        JwtResponse response = new JwtResponse(token, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	private void doAuthenticate(String username, String password) {

		System.out.println(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
	
	@ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
