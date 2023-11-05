package com.ubt.travel.travelagency.services;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Order;
import com.ubt.travel.travelagency.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    @Override
    public void createOrder(Order order) {
        if(order == null) return;
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        if(id == 0 || id == null) return null;
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getOrders(AppUser user) {
        if(user == null) return null;
        return orderRepository.getOrdersByHotelReservationUser(user);
    }
}
