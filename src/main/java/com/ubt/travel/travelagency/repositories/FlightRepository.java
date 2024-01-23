package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
