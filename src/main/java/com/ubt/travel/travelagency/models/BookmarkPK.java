package com.ubt.travel.travelagency.models;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Embeddable
@Data
public class BookmarkPK implements Serializable {
    private Long userId;
    private Long hotelId;
}
