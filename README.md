# Food Delivery App

## Overview
The Food Delivery App is a microservices-based application that enables customers to browse restaurants, place orders, and track deliveries. The system is designed to be scalable, resilient, and maintainable, following best practices in microservices architecture.

## Features
- Customer registration and profile management.
- Restaurant management, including menus and availability.
- Order placement and management.
- Real-time delivery tracking.
- Notifications for order and delivery updates.
- Secure payment processing.

## Technologies Used
- **Backend**: Spring Boot, Spring Cloud, Spring Data JPA, Hibernate
- **Database**: MySQL
- **Messaging**: RabbitMQ (for asynchronous notifications)
- **Security**: Spring Security with JWT
- **API Gateway**: Spring Cloud Gateway
- **Service Discovery**: Eureka Server
- **Configuration Management**: Spring Cloud Config
- **Containerization**: Docker and Docker Compose
- **Monitoring**: Actuator, Prometheus, Grafana
- **Testing**: JUnit, Mockito

## Microservices Architecture
The application is built using the following services:

1. **Customer Service**  
   - Manages customer profiles and their orders.

2. **Restaurant Service**  
   - Handles restaurant details, menus, and availability.

3. **Order Service**  
   - Processes customer orders and tracks their status.

4. **Delivery Service**  
   - Manages delivery personnel and delivery tracking.

5. **Notification Service**  
   - Sends email and SMS notifications.
   - Integration: RabbitMQ for messaging.

6. **Payment Service**  
   - Handles payment processing and order payment status.


## Key Features of the Architecture
- **Loose Coupling**: Services communicate via REST APIs and RabbitMQ.
- **Scalability**: Independent scaling of microservices as needed.
- **Fault Tolerance**: Circuit breakers and fallback mechanisms using Resilience4j.
- **Centralized Configuration**: Managed via Spring Cloud Config.
- **Service Discovery**: Eureka Server enables dynamic service registration and discovery.

## Setup and Installation

### Prerequisites
1. Java 17
2. Maven
3. Docker and Docker Compose
4. MySQL installed (or use Docker images)

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/food-delivery-app.git
   cd food-delivery-app

2. Start Config Server and Eureka Server:
   ```bash
   cd config-server
   mvn spring-boot:run
   cd ../eureka-server
   mvn spring-boot:run

3. Start all microservices
   ```bash
   cd customer-service
   mvn spring-boot:run
   cd restaurant-service
   mvn spring-boot:run

4. Use Docker Compose to start the databases:
   ```bash
   docker-compose up -d
5. Access the API Gateway at:
  http://localhost:8080

## Endpoints
Here are some of the key API endpoints:

### Customer Service
- `POST /api/customers` - Register a new customer.
- `GET /api/customers/{id}` - Get customer by ID.

### Restaurant Service
- `POST /api/restaurants` - Add a new restaurant.
- `GET /api/restaurants` - List all restaurants.

### Order Service
- `POST /api/orders` - Place a new order.
- `GET /api/orders/{id}` - Get order by ID.

### Delivery Service
- `GET /api/deliveries` - List all deliveries.
- `PATCH /api/deliveries/{id}/status` - Update delivery status.

### Payment Service
- `POST /api/payments` - Process a payment.
- `GET /api/payments/{id}` - Get payment details.

## Future Enhancements
- Add AI-based delivery time predictions.
- Implement dynamic pricing for peak hours.
- Introduce customer loyalty programs and rewards.
- Add real-time tracking for delivery personnel.




