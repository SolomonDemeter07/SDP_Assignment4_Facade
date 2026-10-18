# Assignment 4 — Facade Pattern (Online Order Processing)

## Overview
This repository contains a Java implementation of the **Facade** structural design pattern (Option B: Online Order Processing Facade).

The `OrderFacade` simplifies access to a complex e-commerce subsystem consisting of four independent services:
* `InventoryService` — manages product stock reservations and releases.
* `PaymentProcessor` — handles customer balance charges and refunds.
* `ShippingService` — schedules deliveries and cancels tracking numbers.
* `NotificationService` — dispatches confirmation, cancellation, and alert emails.

The Facade exposes two high-level methods (`placeOrder` and `cancelOrder`) and handles automatic state rollback (releasing reserved stock) when payment processing fails.

## Project Structure
* `src/OrderFacade.java` — The Facade class coordinating the subsystem.
* `src/InventoryService.java` — Subsystem class 1.
* `src/PaymentProcessor.java` — Subsystem class 2.
* `src/ShippingService.java` — Subsystem class 3.
* `src/NotificationService.java` — Subsystem class 4.
* `src/Customer.java`, `src/ShoppingCart.java`, `src/OrderRecord.java` — Domain data models.
* `src/OrderProcessingException.java` — Custom domain exception for coordination failures.
* `src/Main.java` — Client class that interacts exclusively with `OrderFacade`.

## Requirements & How to Run
* **JDK Version:** Java 17

### Run via Terminal:
1. Compile all source files:
   javac -d out src/*.java
2. Run the client:
   java -cp out Main