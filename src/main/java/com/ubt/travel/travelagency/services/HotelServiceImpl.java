package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.models.HotelReservation;
import com.ubt.travel.travelagency.models.Order;
import com.ubt.travel.travelagency.repositories.HotelRepository;
import com.ubt.travel.travelagency.repositories.HotelReservationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelReservationRepository hotelReservationRepository;
    public HotelServiceImpl(HotelRepository hotelRepository,HotelReservationRepository hotelReservationRepository){
        this.hotelRepository=hotelRepository;
        this.hotelReservationRepository=hotelReservationRepository;
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
        Hotel hotel = hotelRepository.findById(id).get();
        if(hotel != null){
            for(HotelReservation reservation : hotel.getHotelReservations()){
                reservation.setHotel(null);
                hotelReservationRepository.save(reservation);
            }
            hotel.getHotelReservations().clear();
            hotelRepository.save(hotel);
        }
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
