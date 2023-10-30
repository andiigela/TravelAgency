package com.ubt.travel.travelagency.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservation {
    @EmbeddedId
    private HotelReservationPK id;
    @ManyToOne
    @MapsId("hotelId")
    private Hotel hotel;
    @ManyToOne
    @MapsId("userId")
    private AppUser user;
}
