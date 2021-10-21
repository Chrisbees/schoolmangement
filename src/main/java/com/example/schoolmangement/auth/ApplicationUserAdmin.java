package com.example.schoolmangement.auth;

import com.example.schoolmangement.model.Role;
import com.example.schoolmangement.model.StaffClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class ApplicationUserAdmin implements UserDetails {

    private final StaffClass staffClass;

    public ApplicationUserAdmin(StaffClass staffClass) {
        this.staffClass = staffClass;
    }


    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        List<Role> roles = Arrays.asList(staffClass.getRole());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return staffClass.getPassword();
    }

    @Override
    public String getUsername() {
        return staffClass.getUsername();
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
        return staffClass.isActive();
    }
}
