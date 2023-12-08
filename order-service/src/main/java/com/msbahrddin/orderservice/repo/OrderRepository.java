package com.msbahrddin.orderservice.repo;

import com.msbahrddin.basedomains.dto.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    @Query("{ 'price' : { $gt: ?0 } }")
    List<Order> findOrdersByPriceGreaterThan(double price);
}