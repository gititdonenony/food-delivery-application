package com.food_delivery_app.service;

import com.food_delivery_app.dto.DeliveryRequest;
import com.food_delivery_app.dto.DeliveryResponse;
import com.food_delivery_app.exception.DeliveryNotFoundException;
import com.food_delivery_app.model.Delivery;
import com.food_delivery_app.repository.DeliveryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService{
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DeliveryResponse createDelivery(DeliveryRequest deliveryRequest) {
        // Convert DeliveryRequest DTO to Delivery entity
        Delivery delivery = modelMapper.map(deliveryRequest, Delivery.class);

        // Save the delivery to the database
        Delivery savedDelivery = deliveryRepository.save(delivery);

        // Convert saved entity back to DeliveryResponse DTO
        return modelMapper.map(savedDelivery, DeliveryResponse.class);
    }

    @Override
    public Optional<DeliveryResponse> getDeliveryById(Long deliveryId) {
        // Validate the deliveryId (business validation)
        if (deliveryId == null || deliveryId <= 0) {
            throw new IllegalArgumentException("Invalid delivery ID: " + deliveryId);
        }

        // Find the delivery by ID
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);

        // If delivery is not found, return an appropriate response or throw an exception
        if (delivery.isEmpty()) {
            throw new DeliveryNotFoundException("Delivery with ID " + deliveryId + " not found");
        }

        // Map the found delivery entity to DeliveryResponse DTO
        return Optional.of(modelMapper.map(delivery.get(), DeliveryResponse.class));
    }

    @Override
    public List<DeliveryResponse> getAllDeliveries() {
        // Get all deliveries and map them to DeliveryResponse DTOs
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream()
                .map(delivery -> modelMapper.map(delivery, DeliveryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryResponse updateDeliveryStatus(Long deliveryId, String status) {
        // Validate the deliveryId (business validation)
        if (deliveryId == null || deliveryId <= 0) {
            throw new IllegalArgumentException("Invalid delivery ID: " + deliveryId);
        }

        // Find the delivery by ID
        Optional<Delivery> deliveryOpt = deliveryRepository.findById(deliveryId);

        // If delivery exists, update the status
        if (deliveryOpt.isPresent()) {
            Delivery delivery = deliveryOpt.get();
            delivery.setStatus(status); // Update the status

            // Save the updated delivery
            Delivery updatedDelivery = deliveryRepository.save(delivery);

            // Return updated delivery as DeliveryResponse DTO
            return modelMapper.map(updatedDelivery, DeliveryResponse.class);
        }

        // If not found, throw an exception (optional based on your business logic)
        throw new DeliveryNotFoundException("Delivery with ID " + deliveryId + " not found");
    }

    @Override
    public DeliveryResponse assignDeliveryPerson(Long deliveryId, Long deliveryPersonId) {
        // Find the delivery by ID
        Optional<Delivery> deliveryOpt = deliveryRepository.findById(deliveryId);

        // If delivery exists, assign a delivery person
        if (deliveryOpt.isPresent()) {
            Delivery delivery = deliveryOpt.get();
            // Here, you would interact with the DeliveryPerson service to fetch and assign the delivery person

            // Example: Assign a delivery person (you would ideally have a service for DeliveryPerson)
            // delivery.setDeliveryPerson(deliveryPerson);

            // Save the updated delivery
            Delivery updatedDelivery = deliveryRepository.save(delivery);

            // Return the updated delivery as DeliveryResponse DTO
            return modelMapper.map(updatedDelivery, DeliveryResponse.class);
        }

        // Return null or throw an exception if not found (optional based on your business logic)
        return null;
    }
}

