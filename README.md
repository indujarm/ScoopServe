🍨 ScoopServe


ScoopServe is a microservices-based Ice Cream Ordering System built using Spring Boot. The project is composed of independent services for handling ice cream inventory, order management, and coupon-based discounts. All services are registered with Eureka Server and routed through a centralized API Gateway.

Each service is fully documented using Swagger UI for easy API testing and exploration.

🚀 Tech Stack
Java 17

Spring Boot

Spring Web

Spring Data JPA

Eureka Discovery Client

OpenFeign (for service-to-service communication)

Spring Cloud Gateway

MySQL

Swagger (Springdoc OpenAPI)

Lombok

🧱 Microservices Overview
Service	Port	Description
IceCreamService	8082	Manages ice cream flavors and prices
OrderService	8088	Places, updates, and tracks orders
CouponService	8083	Manages and validates discount coupons
Eureka Server	8761	Service registry
API Gateway	8080	Routes all client requests to microservices

🔗 URLs
🧭 API Gateway
Base URL: http://localhost:8080

📘 Swagger UIs:
Service	Swagger URL
IceCreamService	http://localhost:8080/icecream-service/swagger-ui.html
OrderService	http://localhost:8080/order-service/swagger-ui.html
CouponService	http://localhost:8080/coupon-service/swagger-ui.html

🔍 Eureka Dashboard
http://localhost:8761

🔄 Sample API Flow
Create Ice Cream Flavor
POST /icecream-service/flavors

Create Coupon
POST /coupon-service/coupons

Place Order with Coupon
POST /order-service/orders

Check Order Status / View Orders
GET /order-service/orders/{id}

📦 Project Modules

ScoopServe/
│
├── eureka-server/
├── api-gateway/
├── icecream-service/
├── order-service/
├── coupon-service/
🧪 How to Run
Start Eureka Server first.

Run all microservices (they will register automatically).

Launch API Gateway to access endpoints.

Visit http://localhost:8080 for Gateway entry point.

🧾 Author
Induja R.M

Developed as part of hands-on learning with microservices architecture using Spring Boot, Eureka, and API Gateway.

