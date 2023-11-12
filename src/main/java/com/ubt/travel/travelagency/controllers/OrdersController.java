package com.ubt.travel.travelagency.controllers;

import com.ubt.travel.travelagency.models.AppUser;
import com.ubt.travel.travelagency.models.Order;
import com.ubt.travel.travelagency.services.AppUserService;
import com.ubt.travel.travelagency.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrdersController {
    private final OrderService orderService;
    private final AppUserService appUserService;
    public OrdersController(OrderService orderService,AppUserService appUserService){
        this.orderService=orderService;
        this.appUserService=appUserService;
    }
    @GetMapping("/orders")
    public String getOrders(Model model){
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserService.findUserByUsername(authUser.getName());
        List<Order> ordersByUser = orderService.getOrders(user);
        model.addAttribute("orders",ordersByUser);
        return "orders";
    }
    @GetMapping("/order/{orderId}")
    public String getOrderView(Model model, @PathVariable("orderId") Long orderId){
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order",order);
        return "order-details";
    }
}
