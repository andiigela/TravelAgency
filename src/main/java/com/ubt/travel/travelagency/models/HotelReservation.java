package com.ubt.travel.travelagency.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Hotel hotel;
    @ManyToOne
    private AppUser user;
    @OneToMany(mappedBy = "hotelReservation",cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
}
/*
package com.ubt.travel.travelagency.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "hotelReservation",cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
}
*/