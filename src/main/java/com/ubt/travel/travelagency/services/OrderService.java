package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Order;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getOrders(AppUser user);
}
