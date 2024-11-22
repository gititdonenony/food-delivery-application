package com.food_delivery_app.controller;
import com.food_delivery_app.dto.DeliveryRequest;
import com.food_delivery_app.dto.DeliveryResponse;
import com.food_delivery_app.exception.DeliveryNotFoundException;
import com.food_delivery_app.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    /**
     * Create a new delivery
     *
     * @param deliveryRequest The delivery request data
     * @return The created delivery details
     */
    @PostMapping("/create")
    public ResponseEntity<DeliveryResponse> createDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        DeliveryResponse createdDelivery = deliveryService.createDelivery(deliveryRequest);
        return new ResponseEntity<>(createdDelivery, HttpStatus.CREATED);
    }

    /**
     * Get a delivery by ID
     *
     * @param deliveryId The ID of the delivery to retrieve
     * @return The delivery details if found, otherwise empty
     */
    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable("deliveryId") Long deliveryId) {
        Optional<DeliveryResponse> delivery = deliveryService.getDeliveryById(deliveryId);
        return delivery.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all deliveries
     *
     * @return List of all deliveries
     */
    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getAllDeliveries() {
        List<DeliveryResponse> deliveries = deliveryService.getAllDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    /**
     * Update the status of a delivery
     *
     * @param deliveryId The ID of the delivery to update
     * @param status The new status to set
     * @return The updated delivery details
     */
    @PatchMapping("/{deliveryId}/status")
    public ResponseEntity<DeliveryResponse> updateDeliveryStatus(
            @PathVariable Long deliveryId, @RequestBody DeliveryRequest deliveryRequest){

        try {
            DeliveryResponse updatedDelivery = deliveryService.updateDeliveryStatus(deliveryId, deliveryRequest.getStatus());
            return new ResponseEntity<>(updatedDelivery, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (DeliveryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Assign a delivery person to a delivery
     *
     * @param deliveryId The ID of the delivery
     * @param deliveryPersonId The ID of the delivery person to assign
     * @return The updated delivery details
     */
    @PatchMapping("/{deliveryId}/assign/{deliveryPersonId}")
    public ResponseEntity<DeliveryResponse> assignDeliveryPerson(
            @PathVariable("deliveryId") Long deliveryId,
            @PathVariable("deliveryPersonId") Long deliveryPersonId) {

        try {
            DeliveryResponse assignedDelivery = deliveryService.assignDeliveryPerson(deliveryId, deliveryPersonId);
            return new ResponseEntity<>(assignedDelivery, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (DeliveryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
