package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.dto.AppUserDto;
import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.models.HotelReservation;
import com.ubt.travel.travelagency.models.HotelReservationPK;
import com.ubt.travel.travelagency.services.AppUserService;
import com.ubt.travel.travelagency.services.HotelReservationService;
import com.ubt.travel.travelagency.services.HotelService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelReservationsController {
    private final HotelReservationService hotelReservationService;
    private final AppUserService appUserService;
    private final HotelService hotelService;
    public HotelReservationsController(HotelReservationService hotelReservationService,AppUserService appUserService,
                                       HotelService hotelService){
        this.hotelReservationService=hotelReservationService;
        this.appUserService=appUserService;
        this.hotelService=hotelService;
    }
    @PostMapping("/hotelreservations/{hotelId}")
    public String reserveHotel(@PathVariable("hotelId") Long hotelId, @RequestParam("reservedNumber") String reservedNumber){
         Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
         if(authUser instanceof AnonymousAuthenticationToken){
             return "redirect:/login";
         }
         AppUser currentUser = appUserService.findUserByUsername(authUser.getName());
         Hotel hotel = hotelService.getHotel(hotelId);
         HotelReservation hotelReservation = new HotelReservation();

         HotelReservationPK hotelReservationPK = new HotelReservationPK();
         hotelReservationPK.setHotelId(hotel.getId());
         hotelReservationPK.setUserId(currentUser.getId());

         hotelReservation.setId(hotelReservationPK);
         hotelReservation.setHotel(hotel);
         hotelReservation.setUser(currentUser);
         hotel.getHotelReservations().add(hotelReservation);

         if(reservedNumber.trim().equals("")){
             return "redirect:/search?name=" + hotel.getName();
         }
         int myReservedNumber = Integer.parseInt(reservedNumber);
         if(myReservedNumber > 0 && myReservedNumber <= hotel.getNumriDhomave()){
             hotel.setNumriDhomave(hotel.getNumriDhomave()-myReservedNumber);
         }

         hotelService.createHotel(hotel);


         hotelReservationService.createHotelReservation(hotelReservation);
         return "redirect:/search?name=" + hotel.getName();
    }
}
