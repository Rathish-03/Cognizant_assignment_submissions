package com.example.mockito1;

/**
 * Processes orders and interacts with a PaymentGateway.
 */
public class OrderProcessor {

    private PaymentGateway paymentGateway;

    // Constructor Injection: The dependency is passed through the constructor.
    public OrderProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * Places an order, processing payment if necessary.
     * @param orderId The ID of the order.
     * @param amount The amount of the order.
     * @return true if the order is successfully placed and payment processed, false otherwise.
     */
    public boolean placeOrder(String orderId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Order amount must be positive.");
        }
        System.out.println("OrderProcessor: Attempting to process payment for order " + orderId + " with amount " + amount);
        boolean paymentSuccess = paymentGateway.processPayment(amount);

        if (paymentSuccess) {
            System.out.println("OrderProcessor: Payment successful for order " + orderId);
            // Simulate other order processing logic (e.g., update database, send confirmation)
            return true;
        } else {
            System.out.println("OrderProcessor: Payment failed for order " + orderId);
            return false;
        }
    }

    /**
     * Attempts to place a premium order, getting a transaction ID.
     * @param orderId The ID of the order.
     * @param amount The amount of the order.
     * @return The transaction ID if successful, null otherwise.
     */
    public String placePremiumOrder(String orderId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Premium order amount must be positive.");
        }
        System.out.println("OrderProcessor: Attempting to process premium payment for order " + orderId + " with amount " + amount);
        String transactionId = paymentGateway.processPaymentWithId(orderId, amount);

        if (transactionId != null && !transactionId.isEmpty()) {
            System.out.println("OrderProcessor: Premium payment successful for order " + orderId + ". Transaction ID: " + transactionId);
            return transactionId;
        } else {
            System.out.println("OrderProcessor: Premium payment failed for order " + orderId);
            return null;
        }
    }
}
