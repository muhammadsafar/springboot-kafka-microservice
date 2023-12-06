package com.msbahrddin.orderservice.service.impl;

import com.msbahrddin.basedomains.dto.Order;
import com.msbahrddin.orderservice.repo.OrderRepository;
import com.msbahrddin.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String orderId, Order order) {
        order.setOrderId(orderId);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> findOrdersByPriceGreaterThan(double price) {
        return orderRepository.findOrdersByPriceGreaterThan(price);
    }

}