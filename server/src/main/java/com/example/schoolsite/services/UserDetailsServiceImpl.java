package com.example.schoolsite.services;

import com.example.schoolsite.entity.User;
import com.example.schoolsite.workWithDatabase.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with  username: " + username));
        return UserDetailsImpl.build(user);
    }
}
