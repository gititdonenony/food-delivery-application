package com.food_delivery_app.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeliveryRequest {
    private String status;

    private LocalDateTime assignedDate;
}
