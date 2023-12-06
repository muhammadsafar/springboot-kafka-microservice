package com.msbahrddin.orderservice.service;



import com.msbahrddin.basedomains.dto.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(String orderId);

    Order createOrder(Order order);

    Order updateOrder(String orderId, Order order);

    void deleteOrder(String orderId);

    List<Order> findOrdersByPriceGreaterThan(double price);
}

