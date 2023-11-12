package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.dto.AppUserDto;
import com.ubt.travel.travelagency.models.*;
import com.ubt.travel.travelagency.services.AppUserService;
import com.ubt.travel.travelagency.services.HotelReservationService;
import com.ubt.travel.travelagency.services.HotelService;
import com.ubt.travel.travelagency.services.OrderService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HotelReservationsController {
    private final HotelReservationService hotelReservationService;
    private final AppUserService appUserService;
    private final HotelService hotelService;
    private final OrderService orderService;
    public HotelReservationsController(HotelReservationService hotelReservationService,AppUserService appUserService,
                                       HotelService hotelService,OrderService orderService){
        this.hotelReservationService=hotelReservationService;
        this.appUserService=appUserService;
        this.hotelService=hotelService;
        this.orderService=orderService;
    }
    @PostMapping("/hotelreservations/{hotelId}")
    public String reserveHotel(@PathVariable("hotelId") Long hotelId, @RequestParam("reservedNumber") String reservedNumber,Model model){
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if(authUser instanceof AnonymousAuthenticationToken){
            return "redirect:/login";
        }
        AppUser currentUser = appUserService.findUserByUsername(authUser.getName());
        Hotel hotel = hotelService.getHotel(hotelId);
        HotelReservation hotelReservation = new HotelReservation();

        hotelReservation.setHotel(hotel);
        hotelReservation.setUser(currentUser);
        hotel.getHotelReservations().add(hotelReservation);
        hotelReservationService.createHotelReservation(hotelReservation);

        int myReservedNumber=0;
        if(!reservedNumber.trim().equals("")){
            myReservedNumber = Integer.parseInt(reservedNumber);

        }
        if(reservedNumber.trim().equals("")){
            return "redirect:/search?name=" + hotel.getName();
        }
        if(myReservedNumber > 0 && myReservedNumber <= hotel.getNumriDhomave()){
            hotel.setNumriDhomave(hotel.getNumriDhomave()-myReservedNumber);
        } else {
            return "redirect:/search?name=" + hotel.getName();
        }

        LocalDate currentDate = LocalDate.now();

        hotelService.createHotel(hotel);

        Order order = new Order();
        order.setDate(currentDate);
        order.setHotelReservation(hotelReservation);
        order.setPrice(myReservedNumber*hotel.getRoomPrice());
        order.setQuantity(myReservedNumber);
        order.setItemPaid("Hotel " + hotel.getName() + " Room");

        orderService.createOrder(order);

        return "redirect:/order/" +order.getId();
    }
}
