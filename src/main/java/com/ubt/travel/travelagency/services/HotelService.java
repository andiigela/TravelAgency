package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotels();
    Hotel getHotel(Long id);
    void createHotel(Hotel hotel);
    void editHotel(Hotel hotel);
    void deleteHotel(Long id);
    List<Hotel> getHotelsByName(String name);
}
