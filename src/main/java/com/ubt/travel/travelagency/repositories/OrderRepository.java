package com.ubt.travel.travelagency.repositories;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrdersByHotelReservationUser(AppUser user);
}
