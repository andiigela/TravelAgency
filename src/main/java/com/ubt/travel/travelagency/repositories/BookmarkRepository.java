package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.Bookmark;
import com.ubt.travel.travelagency.models.BookmarkPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkPK> {
}
