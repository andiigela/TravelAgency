package com.ubt.travel.travelagency.dto;

import com.ubt.travel.travelagency.models.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelWithBookmarkStatus {
    private Hotel hotel;
    private boolean bookmarked;
}
