package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Bookmark;
import com.ubt.travel.travelagency.models.BookmarkPK;

import java.util.List;

public interface BookmarkService {
    void createBookmark(Bookmark bookmark);
    List<Bookmark> getBookmarksByUser(AppUser user);
    void deleteBookmark(BookmarkPK id);

}
