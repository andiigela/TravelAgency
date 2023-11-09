package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.HotelReservation;

import java.util.List;

public interface HotelReservationService {
    List<HotelReservation> getHotelReservations();
    void createHotelReservation(HotelReservation hotelReservation);
    void deleteHotelReservationById(Long id);
}
