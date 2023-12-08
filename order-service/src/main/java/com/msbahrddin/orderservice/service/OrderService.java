package com.msbahrddin.orderservice.service;



import com.msbahrddin.basedomains.dto.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long orderId);

    Order createOrder(Order order);

    Order updateOrder(Long orderId, Order order);

    void deleteOrder(Long orderId);

    List<Order> findOrdersByPriceGreaterThan(double price);
}

