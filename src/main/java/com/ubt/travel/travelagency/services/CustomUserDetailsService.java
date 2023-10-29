package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserService appUserService;
    @Autowired
    public CustomUserDetailsService(AppUserService appUserService){
        this.appUserService=appUserService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("User not found !!");
        User authUser = new User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getRoles().stream().map(
                                role -> new SimpleGrantedAuthority("ROLE_" + role.getName())
                        )
                        .collect(Collectors.toList())
                );
        return authUser;
    }
}
