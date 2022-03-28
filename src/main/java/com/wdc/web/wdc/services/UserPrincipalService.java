package com.wdc.web.wdc.services;

import com.wdc.web.wdc.entities.User;
import com.wdc.web.wdc.repositories.UserRepository;
import com.wdc.web.wdc.security.UserApp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNom(username);
        UserApp userApp = new UserApp(user);
        return userApp;
    }
}