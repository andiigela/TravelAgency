package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;

public interface AppUserService {
    void createUser(AppUser appUser);
    AppUser findUserByEmail(String email);
    AppUser findUserByUsername(String username);
}
