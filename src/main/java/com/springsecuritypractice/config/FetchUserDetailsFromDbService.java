package com.springsecuritypractice.config;

import com.springsecuritypractice.model.User;
import com.springsecuritypractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class FetchUserDetailsFromDbService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existingUser = userRepository.findByUsername(username);
        return existingUser.map(FetchUserDetailsFromDb::new)
                .orElseThrow();
    }
}
