package com.ubt.travel.travelagency.services;
import com.ubt.travel.travelagency.models.Flight;
import com.ubt.travel.travelagency.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }
    @Override
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }
    @Override
    public Flight getFlightById(Long id) {
        if(id == 0 || id == null) return null;
        return flightRepository.findById(id).get();
    }
    @Override
    public void createFlight(Flight flight) {
        if(flight == null) return;
        flightRepository.save(flight);
    }
    @Override
    public void deleteFlightById(Long id) {
        if(id == 0 || id == null) return;
        flightRepository.deleteById(id);
    }
}
