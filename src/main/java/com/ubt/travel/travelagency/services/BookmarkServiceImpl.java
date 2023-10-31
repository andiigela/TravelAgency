package com.ubt.travel.travelagency.services;
import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Bookmark;
import com.ubt.travel.travelagency.repositories.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository){
        this.bookmarkRepository=bookmarkRepository;
    }
    @Override
    public void createBookmark(Bookmark bookmark) {
        if(bookmark == null) return;
        bookmarkRepository.save(bookmark);
    }

    @Override
    public List<Bookmark> getBookmarksByUser(AppUser user) {
        if(user == null) return null;
        return bookmarkRepository.getBookmarksByUser(user);
    }
}
