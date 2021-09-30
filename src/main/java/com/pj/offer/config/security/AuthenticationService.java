package com.pj.offer.config.security;

import com.pj.offer.domain.model.User;
import com.pj.offer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        log.info("First, {} will be search for!", username);
        log.warn("If {} does not exist, will throw exception!", username);
        if(user.isPresent()){
            return user.get();
        } log.error("An exception was throw!");
        throw new UsernameNotFoundException("Invalid data!");

    }
}
