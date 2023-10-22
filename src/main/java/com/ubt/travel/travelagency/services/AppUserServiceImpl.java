package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Role;
import com.ubt.travel.travelagency.repositories.AppUserRepository;
import com.ubt.travel.travelagency.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service

public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    public AppUserServiceImpl(AppUserRepository appUserRepository,RoleRepository roleRepository){
        this.appUserRepository=appUserRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public AppUser findUserByEmail(String email) {
        if(email.trim().equals("")) return null;
        return appUserRepository.findAppUserByEmail(email);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        if(username.trim().equals("")) return null;
        return appUserRepository.findAppUserByUsername(username);
    }

    @Override
    public void createUser(AppUser appUser) {
        if(appUser == null) return;
        Role userRole = roleRepository.findRoleByName("User");
        if(userRole == null) return;
        appUser.getRoles().add(userRole);
        appUserRepository.save(appUser);
    }
}
