package com.ubt.travel.travelagency.controllers;
import com.ubt.travel.travelagency.dto.HotelWithReservationStatus;
import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.models.HotelReservation;
import com.ubt.travel.travelagency.models.HotelReservationPK;
import com.ubt.travel.travelagency.services.AppUserService;
import com.ubt.travel.travelagency.services.HotelReservationService;
import com.ubt.travel.travelagency.services.HotelService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private final HotelService hotelService;
    public SearchController(HotelService hotelService){
        this.hotelService=hotelService;
    }
    @GetMapping("/search")
    public String getSearchValues(@RequestParam("name") String name, Model model){
        List<Hotel> hotels = hotelService.getHotelsByName(name);
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();

        List<HotelWithReservationStatus> hotelsWithStatus = new ArrayList<>();

        for (Hotel hotel : hotels) {
            boolean isReservedByCurrentUser = hotel.getHotelReservations().stream()
                    .anyMatch(reservation -> reservation.getUser().getUsername().equals(authUser.getName()));

            hotelsWithStatus.add(new HotelWithReservationStatus(hotel, isReservedByCurrentUser));
        }
        model.addAttribute("hotelsWithStatus",hotelsWithStatus);
        return "search-hotels";
    }
}
