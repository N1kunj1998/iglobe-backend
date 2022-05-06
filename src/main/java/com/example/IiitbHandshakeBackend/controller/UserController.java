package com.example.IiitbHandshakeBackend.controller;

import com.example.IiitbHandshakeBackend.Helper.JwtUtil;
import com.example.IiitbHandshakeBackend.entity.JwtRequest;
import com.example.IiitbHandshakeBackend.entity.JwtResponse;
import com.example.IiitbHandshakeBackend.entity.LoginRequest;
import com.example.IiitbHandshakeBackend.entity.User;
import com.example.IiitbHandshakeBackend.service.UserDetailServiceImpl;
import com.example.IiitbHandshakeBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user = userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> signinUser(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest);
        return ResponseEntity.ok(userService.signinUser(loginRequest));
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.resetPassword(loginRequest));
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        // fine area..

        UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);

        // {"token" : "value"}
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/test")
    public String test(){
        return "<h1>test</h1>";
    }
}
