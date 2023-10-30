package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Bookmark;
import com.ubt.travel.travelagency.models.BookmarkPK;
import com.ubt.travel.travelagency.models.Hotel;
import com.ubt.travel.travelagency.services.AppUserService;
import com.ubt.travel.travelagency.services.BookmarkService;
import com.ubt.travel.travelagency.services.HotelService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookmarksController {
    private final BookmarkService bookmarkService;
    private final HotelService hotelService;
    private final AppUserService appUserService;
    public BookmarksController(BookmarkService bookmarkService,HotelService hotelService,AppUserService appUserService){
        this.bookmarkService=bookmarkService;
        this.hotelService=hotelService;
        this.appUserService=appUserService;
    }
    @PostMapping("/bookmark/hotel/{id}")
    public String bookmarkHotel(@PathVariable("id") Long id){
        Hotel hotel = hotelService.getHotel(id);
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if(authUser instanceof AnonymousAuthenticationToken) return "redirect:/login";
        AppUser appUser = appUserService.findUserByUsername(authUser.getName());

        Bookmark bookmark = new Bookmark();

        BookmarkPK bookmarkPK = new BookmarkPK();
        bookmarkPK.setHotelId(hotel.getId());
        bookmarkPK.setUserId(appUser.getId());

        bookmark.setId(bookmarkPK);
        bookmark.setUser(appUser);
        bookmark.setHotel(hotel);

        bookmarkService.createBookmark(bookmark);
        return "redirect:/bookmarks";
    }
    @GetMapping("/bookmarks")
    public String getBookmarksView(){
        return "";
    }







}
