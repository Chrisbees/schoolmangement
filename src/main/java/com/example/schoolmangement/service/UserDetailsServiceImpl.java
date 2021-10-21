package com.example.schoolmangement.service;

import com.example.schoolmangement.auth.UserDetailsClass;
import com.example.schoolmangement.model.Users;
import com.example.schoolmangement.repository.DtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DtoRepository dtoRepository;


    @Autowired
    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userClass = dtoRepository.findByUsername(username);
        return userClass.map(UserDetailsClass::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
         }

}
