package com.food_delivery_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    //@OneToOne
    //@JoinColumn(name = "order_id", nullable = false) // Enforces non-null at the database level
    //@NotNull(message = "Order cannot be null") // Validation: Order must not be null
    //private Order order;

    //@ManyToOne
    //@JoinColumn(name = "delivery_person_id", nullable = false) // Enforces non-null at the database level
    //@NotNull(message = "Delivery person must be assigned") // Validation: Delivery person must not be null
    //private DeliveryPerson deliveryPerson;


    private String status;

    @NotNull(message = "Assigned date cannot be null") // Validation: Must not be null
    private LocalDateTime assignedDate;
}