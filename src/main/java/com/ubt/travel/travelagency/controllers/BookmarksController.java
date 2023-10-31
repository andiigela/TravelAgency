package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.dto.HotelWithBookmarkStatus;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
        Hotel hoteldb = hotelService.getHotel(id);
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if(authUser instanceof AnonymousAuthenticationToken) return "redirect:/login";
        AppUser appUser = appUserService.findUserByUsername(authUser.getName());

        Bookmark bookmark = new Bookmark();

        BookmarkPK bookmarkPK = new BookmarkPK();
        bookmarkPK.setHotelId(hoteldb.getId());
        bookmarkPK.setUserId(appUser.getId());

        bookmark.setId(bookmarkPK);
        bookmark.setUser(appUser);
        bookmark.setHotel(hoteldb);

        //hoteldb.getBookmarks().add(bookmark);
        //hotelService.createHotel(hoteldb);

        bookmarkService.createBookmark(bookmark);
        return "redirect:/search?name=" + hoteldb.getName();
    }
    @GetMapping("/bookmarks")
    public String getBookmarksView(Model model){
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        if(authUser instanceof AnonymousAuthenticationToken) return "redirect:/login";
        AppUser appUser = appUserService.findUserByUsername(authUser.getName());
        List<Bookmark> bookmarksByUser = bookmarkService.getBookmarksByUser(appUser);
        model.addAttribute("bookmarks",bookmarksByUser);

        return "bookmarks";
    }







}
