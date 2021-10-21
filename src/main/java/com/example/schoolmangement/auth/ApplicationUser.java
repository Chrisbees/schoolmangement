package com.example.schoolmangement.auth;

import com.example.schoolmangement.dto.ParentsDto;
import com.example.schoolmangement.model.Parents;
import com.example.schoolmangement.model.Role;
import com.example.schoolmangement.model.StaffClass;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationUser implements UserDetails{

    private final Parents parents;
    public ApplicationUser(Parents parents) {
        this.parents = parents;
    }
//
//    public ApplicationUser(StaffClass parents) {
//        this.grantedAuthorities = Arrays.stream(parents.getRole().split(","))
//                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        this.password = parents.getPassword();
//        this.username = parents.getUsername();
//        this.isAccountNonExpired = true;
//        this.isAccountNonLocked = true;
//        this.isCredentialsNonExpired = true;
//        this.isEnabled = true;
//    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        List<Role> roles = Arrays.asList(parents.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return parents.getPassword();
    }

    @Override
    public String getUsername() {
        return parents.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return parents.isActive();
    }
}
