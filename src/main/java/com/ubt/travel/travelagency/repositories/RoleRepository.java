package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);

}
