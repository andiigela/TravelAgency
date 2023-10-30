package com.ubt.travel.travelagency.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
    @EmbeddedId
    private BookmarkPK id;
    @ManyToOne
    @MapsId("hotelId")
    private Hotel hotel;
    @ManyToOne
    @MapsId("userId")
    private AppUser user;
}
