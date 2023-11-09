package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.HotelReservation;
import com.ubt.travel.travelagency.repositories.HotelReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelReservationImpl implements HotelReservationService {
    private final HotelReservationRepository hotelReservationRepository;
    public HotelReservationImpl(HotelReservationRepository hotelReservationRepository){
        this.hotelReservationRepository=hotelReservationRepository;
    }
    @Override
    public List<HotelReservation> getHotelReservations() {
        return hotelReservationRepository.findAll();
    }

    @Override
    public void createHotelReservation(HotelReservation hotelReservation) {
        if(hotelReservation == null) return;
        hotelReservationRepository.save(hotelReservation);
    }

    @Transactional
    @Override
    public void deleteHotelReservationById(Long id) {
        if(id == 0 || id == null) return;
        hotelReservationRepository.deleteById(id);
    }
}
