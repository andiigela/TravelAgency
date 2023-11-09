package com.ubt.travel.travelagency.controllers;
import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.models.HotelReservation;
//import com.ubt.travel.travelagency.models.HotelReservationPK;
import com.ubt.travel.travelagency.models.Order;
import com.ubt.travel.travelagency.services.HotelReservationService;
import com.ubt.travel.travelagency.services.HotelService;
import com.ubt.travel.travelagency.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class HotelsController {
    private final HotelService hotelService;
    private final HotelReservationService hotelReservationService;
    private final OrderService orderService;
    public HotelsController(HotelService hotelService,HotelReservationService hotelReservationService,OrderService orderService){
        this.hotelService=hotelService;
        this.hotelReservationService=hotelReservationService;
        this.orderService=orderService;
    }
    @GetMapping("/hotels")
    public String getHotelsView(Model model){
        List<Hotel> hotels = hotelService.getHotels();
        model.addAttribute("hotels",hotels);
        return "hotels";
    }
    @GetMapping("/hotels/create")
    public String getHotelsCreate(Model model){
        model.addAttribute("hotel",new Hotel());
        return "create-hotel";
    }
    @PostMapping("/hotels/create")
    public String createHotel(@ModelAttribute("hotel") Hotel hotel){
        hotelService.createHotel(hotel);
        return "redirect:/hotels";
    }
    @GetMapping("/hotels/edit/{id}")
    public String getEditHotelView(Model model, @PathVariable("id") Long id){
        Hotel hotel = hotelService.getHotel(id);
        model.addAttribute("editHotel",hotel);
        return "edit-hotel";
    }
    @PostMapping("/hotels/edit")
    public String editHotel(@ModelAttribute("editHotel") Hotel hotel){
        hotelService.editHotel(hotel);
        return "redirect:/hotels";
    }
    @PostMapping("/hotels/delete/{id}")
    public String deleteHotel(@PathVariable("id") Long id){
        hotelService.deleteHotel(id);
        return "redirect:/hotels";
    }
}
