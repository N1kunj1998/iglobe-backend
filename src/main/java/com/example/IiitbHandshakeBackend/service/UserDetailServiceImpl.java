package com.example.IiitbHandshakeBackend.service;

import com.example.IiitbHandshakeBackend.entity.User;
import com.example.IiitbHandshakeBackend.entity.UserDetailsImpl;
import com.example.IiitbHandshakeBackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Could not found user !!");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        return userDetails;
    }
}

