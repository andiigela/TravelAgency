package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository=hotelRepository;
    }
    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public void createHotel(Hotel hotel) {
        if(hotel == null) return;
        hotelRepository.save(hotel);
    }

    @Override
    public void editHotel(Hotel hotel) {
        if(hotel == null) return;
        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(Long id) {
        if( id == 0 || id == null) return;
        hotelRepository.deleteById(id);
    }
    @Override
    public Hotel getHotel(Long id) {
        if( id == 0 || id == null) return null;
        return hotelRepository.findById(id).get();
    }

    @Override
    public List<Hotel> getHotelsByName(String name) {
        return hotelRepository.searchByNameIgnoreCaseOrderById(name);
    }
}
