package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findAppUserByEmail(String email);
    AppUser findAppUserByUsername(String username);
}
