package com.wdc.web.wdc.services;

import com.wdc.web.wdc.entities.User;
import com.wdc.web.wdc.repositories.UserRepository;
import com.wdc.web.wdc.security.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {


    @Autowired
    private  UserRepository userRepository;

    public UserPrincipalService() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserApp(userRepository.findByNom(username));
    }

    public User findUserByName(String username) throws UsernameNotFoundException {
        return userRepository.findByNom(username);
    }

}