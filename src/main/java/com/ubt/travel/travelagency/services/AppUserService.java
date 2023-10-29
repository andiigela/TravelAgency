package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.dto.AppUserDto;
import com.ubt.travel.travelagency.models.AppUser;

public interface AppUserService {
    void createUser(AppUserDto appUserDto);
    AppUser findUserByEmail(String email);
    AppUser findUserByUsername(String username);
    AppUser findUserById(Long id);
}
