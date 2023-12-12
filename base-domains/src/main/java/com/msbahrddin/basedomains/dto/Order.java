package com.msbahrddin.basedomains.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
@Builder
public class Order {

    @Id
    private String id;

    private String orderName;
    private int qty;
    private double price;
}
