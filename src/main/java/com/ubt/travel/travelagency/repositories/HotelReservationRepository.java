package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.HotelReservation;
import com.ubt.travel.travelagency.models.HotelReservationPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelReservationRepository extends JpaRepository<HotelReservation, HotelReservationPK> {
}
