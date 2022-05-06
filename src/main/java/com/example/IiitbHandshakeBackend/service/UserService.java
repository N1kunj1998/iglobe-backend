package com.example.IiitbHandshakeBackend.service;

import com.example.IiitbHandshakeBackend.entity.LoginRequest;
import com.example.IiitbHandshakeBackend.entity.User;
import com.example.IiitbHandshakeBackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User addUser(User user){
        try {
            if(user.getRole() == null) return null;
            user.setActive(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepo.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public User signinUser(LoginRequest loginRequest){
        User user = userRepo.findByUsername(loginRequest.getUsername());
//        loginRequest.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        System.out.println(loginRequest.getPassword());
        System.out.println(user.getPassword());
        System.out.println(loginRequest);
        System.out.println(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()));
        System.out.println(user.getRole());
        System.out.println(loginRequest.getRole());
        System.out.println(user.getRole().equals(loginRequest.getRole()));
        System.out.println(user.isActive());
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()) && user.getRole().equals(loginRequest.getRole()) && user.isActive()){
            return user;
        }
        return null;
    }

    public Boolean resetPassword(LoginRequest request){
        User user = userRepo.findByUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            userRepo.save(user);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
