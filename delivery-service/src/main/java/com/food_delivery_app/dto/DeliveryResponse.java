package com.food_delivery_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeliveryResponse {
    private Long deliveryId;
    private String status;
    private LocalDateTime assignedDate;
}
