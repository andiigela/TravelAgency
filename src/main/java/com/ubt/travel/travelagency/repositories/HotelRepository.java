package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    List<Hotel> searchByNameIgnoreCaseOrderById(String name);
}
