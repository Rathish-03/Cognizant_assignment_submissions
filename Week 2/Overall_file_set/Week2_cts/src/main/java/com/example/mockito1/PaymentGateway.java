package com.example.mockito1;

/**
 * An interface representing an external payment processing service.
 * This is a dependency that will be mocked.
 */
public interface PaymentGateway {

    /**
     * Processes a payment for a given amount.
     * @param amount The amount to process.
     * @return true if payment is successful, false otherwise.
     */
    boolean processPayment(double amount);

    /**
     * Processes a payment with an order ID.
     * @param orderId The ID of the order.
     * @param amount The amount to process.
     * @return A transaction ID if successful, null otherwise.
     */
    String processPaymentWithId(String orderId, double amount);
}
