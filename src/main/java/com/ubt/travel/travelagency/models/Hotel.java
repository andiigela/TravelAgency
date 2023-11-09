package com.ubt.travel.travelagency.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int numriDhomave;
    @OneToMany(mappedBy = "hotel",cascade = CascadeType.REMOVE)
    private List<HotelReservation> hotelReservations = new ArrayList<>();
    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();
}
