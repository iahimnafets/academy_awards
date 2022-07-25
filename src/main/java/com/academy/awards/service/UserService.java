package com.academy.awards.service;

import com.academy.awards.entitys.User;
import com.academy.awards.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
        User user  = userRepository.findByUsername(username);
        if(user == null){
            String messageError = "User not found in the data Base";
            log.error(messageError);
            throw new UsernameNotFoundException(messageError);
        }else{
            log.info( "User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    //  authorities.add( new SimpleGrantedAuthority("ROLE_ADMIN"));
    //  authorities.add( new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    public User saveUser(User user ){
        log.info( "saveUser-RUN {}", user.getUsername() );
        user.setPassword( passwordEncoder.encode(user.getPassword()) );
        return userRepository.save(user);
    }

}
