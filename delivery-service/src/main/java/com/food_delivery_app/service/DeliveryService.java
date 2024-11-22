package com.food_delivery_app.service;

import com.food_delivery_app.dto.DeliveryRequest;
import com.food_delivery_app.dto.DeliveryResponse;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {

    /**
     * Create a new delivery
     *
     * @param deliveryRequest The delivery request data
     * @return The created delivery details
     */
    DeliveryResponse createDelivery(DeliveryRequest deliveryRequest);

    /**
     * Get a delivery by ID
     *
     * @param deliveryId The ID of the delivery to retrieve
     * @return The delivery details if found, otherwise empty
     */
    Optional<DeliveryResponse> getDeliveryById(Long deliveryId);

    /**
     * Get all deliveries
     *
     * @return List of all deliveries
     */
    List<DeliveryResponse> getAllDeliveries();

    /**
     * Update the status of a delivery
     *
     * @param deliveryId The ID of the delivery to update
     * @param status The new status to set
     * @return The updated delivery details
     */
    DeliveryResponse updateDeliveryStatus(Long deliveryId, String status);

    /**
     * Assign a delivery person to a delivery
     *
     * @param deliveryId The ID of the delivery
     * @param deliveryPersonId The ID of the delivery person to assign
     * @return The updated delivery details
     */
    DeliveryResponse assignDeliveryPerson(Long deliveryId, Long deliveryPersonId);
}
