package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.dto.AppUserDto;
import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Role;
import com.ubt.travel.travelagency.repositories.AppUserRepository;
import com.ubt.travel.travelagency.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public AppUserServiceImpl(AppUserRepository appUserRepository,RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder){
        this.appUserRepository=appUserRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
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
    public void createUser(AppUserDto appUserDto) {
        if( (appUserDto.getFirstName().trim().isEmpty() || appUserDto.getFirstName() == null)
            || (appUserDto.getLastName().trim().isEmpty() || appUserDto.getLastName() == null)
                || (appUserDto.getUsername().trim().isEmpty() || appUserDto.getUsername() == null)
                || (appUserDto.getEmail().trim().isEmpty() || appUserDto.getEmail() == null)
        ){
            return;
        }
        AppUser appUser = mapToAppUser(appUserDto);
        if(appUser == null) return;
        Role userRole = roleRepository.findRoleByName("User");
        if(userRole == null) return;
        if(!appUser.getRoles().contains(userRole)){
            appUser.getRoles().add(userRole);
        }
        //appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        if(appUser.getPassword() == null || appUser.getPassword().trim().equals("")){
            AppUser appUserDb = appUserRepository.findById(appUser.getId()).get();
            if(appUserDb != null){
                appUser.setPassword(appUserDb.getPassword());
            }
        }
        appUser.setPassword(appUser.getPassword());
        appUserRepository.save(appUser);
    }
    public AppUser mapToAppUser(AppUserDto appUserDto){
        AppUser appUser = AppUser.builder()
                .id(appUserDto.getId())
                .firstName(appUserDto.getFirstName())
                .lastName(appUserDto.getLastName())
                .username(appUserDto.getUsername())
                .email(appUserDto.getEmail())
                .password(appUserDto.getPassword())
                .roles(new ArrayList<>())
                .build();
        return appUser;
    }
    @Override
    public AppUser findUserById(Long id) {
        if(id == 0 || id == null) return null;
        return appUserRepository.findById(id).get();
    }
}
