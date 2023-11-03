package com.ubt.travel.travelagency.controllers;
import com.ubt.travel.travelagency.dto.HotelWithBookmarkStatus;
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

        List<HotelWithBookmarkStatus> bookmarkStatusList = new ArrayList<>();
        for(Hotel hotel : hotelService.getHotelsByName(name)){
            boolean isBookmarkedByCurrentUser = hotel.getBookmarks().stream()
                    .anyMatch(bookmark -> bookmark.getUser().getUsername().equals(authUser.getName()));
            bookmarkStatusList.add(new HotelWithBookmarkStatus(hotel,isBookmarkedByCurrentUser));
        }
        model.addAttribute("hotelsWithStatus",bookmarkStatusList);
        return "search-hotels";
    }
}
