package com.ubt.travel.travelagency.dto;

import com.ubt.travel.travelagency.models.Hotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HotelWithReservationStatus {
    private Hotel hotel;
    private boolean isReserved;

}
