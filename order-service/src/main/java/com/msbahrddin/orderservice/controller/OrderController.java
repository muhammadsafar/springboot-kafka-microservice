package com.msbahrddin.orderservice.controller;

import com.msbahrddin.basedomains.dto.Order;
import com.msbahrddin.basedomains.dto.OrderEvent;
import com.msbahrddin.orderservice.kafka.OrderProducer;
import com.msbahrddin.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/kafka")
public class OrderController {

    private OrderProducer orderProducer;

    private OrderService orderService;


    @Autowired
    public OrderController(OrderProducer orderProducer, OrderService orderService) {
        this.orderProducer = orderProducer;
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderEvent> placeOrder(@RequestBody Order order) {

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        //save into db
        orderService.createOrder(order);

        //send info to broker
        orderProducer.sendMessage(orderEvent);

        return new ResponseEntity<>(orderEvent, HttpStatus.CREATED);

    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getById(@PathVariable String orderId){
        Order get =  orderService.getOrderById(orderId);
        return ResponseEntity.ok(get);
    }
}
