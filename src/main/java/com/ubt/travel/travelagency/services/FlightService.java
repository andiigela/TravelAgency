package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getFlights();
    Flight getFlightById(Long id);
    void createFlight(Flight flight);
    void deleteFlightById(Long id);
}
