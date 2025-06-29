package com.example.mockito1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; // This import is crucial for verify and argument matchers

/**
 * Test class for OrderProcessor, demonstrating Mocking, Stubbing, and VERIFICATION using Mockito.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("OrderProcessor Tests with Mockito Verification")
class OrderProcessorTest {

    @Mock
    private PaymentGateway mockPaymentGateway;

    @InjectMocks
    private OrderProcessor orderProcessor;

    // Existing test methods from Mocking & Stubbing Exercise 1 will be here...
    // testPlaceOrder_Success()
    // testPlaceOrder_Failure()
    // testPlaceOrder_InvalidAmount()
    // testPlacePremiumOrder_Success()
    // testPlacePremiumOrder_Failure()

    // --- New Test Methods for Verifying Interactions ---

    @Test
    @DisplayName("Should verify processPayment was called exactly once on successful order")
    void testVerifyPaymentCallCount_Success() {
        // Arrange: Stub the payment to succeed
        when(mockPaymentGateway.processPayment(anyDouble())).thenReturn(true);

        // Act: Place an order
        orderProcessor.placeOrder("VERIFY001", 200.0);

        // Assert/Verify:
        // Verify that processPayment was called exactly once with the specific amount 200.0
        verify(mockPaymentGateway, times(1)).processPayment(200.0);

        // You can also verify with argument matchers
        verify(mockPaymentGateway, times(1)).processPayment(eq(200.0)); // eq() for exact match
        verify(mockPaymentGateway, times(1)).processPayment(anyDouble()); // anyDouble() for any double value

        System.out.println("    Test: testVerifyPaymentCallCount_Success completed.");
    }

    @Test
    @DisplayName("Should verify processPayment was never called for invalid order amount")
    void testVerifyPaymentCall_Never() {
        // Arrange: No stubbing needed, as the method shouldn't be called.

        // Act: Try to place an order with an invalid amount (should throw exception)
        assertThrows(IllegalArgumentException.class, () -> {
            orderProcessor.placeOrder("INVALID001", -10.0);
        });

        // Assert/Verify:
        // Verify that no interactions happened on the mockPaymentGateway at all.
        // This is useful to ensure that internal validation happens before external calls.
        verifyNoInteractions(mockPaymentGateway);

        // Alternatively, verify a specific method was never called
        verify(mockPaymentGateway, never()).processPayment(anyDouble());
        System.out.println("    Test: testVerifyPaymentCall_Never completed.");
    }

    @Test
    @DisplayName("Should verify processPaymentWithId was called once with specific arguments")
    void testVerifyPremiumPaymentCallWithArguments() {
        // Arrange: Stub the premium payment to succeed
        String orderId = "PREM_VERIFY_001";
        double amount = 750.0;
        when(mockPaymentGateway.processPaymentWithId(orderId, amount)).thenReturn("TXN_PREM_XYZ");

        // Act: Place a premium order
        orderProcessor.placePremiumOrder(orderId, amount);

        // Assert/Verify:
        // Verify the exact call including both arguments
        verify(mockPaymentGateway, times(1)).processPaymentWithId(orderId, amount);

        // Or using argument matchers
        verify(mockPaymentGateway, times(1)).processPaymentWithId(eq(orderId), anyDouble());
        verify(mockPaymentGateway, times(1)).processPaymentWithId(anyString(), eq(amount));

        System.out.println("    Test: testVerifyPremiumPaymentCallWithArguments completed.");
    }

    @Test
    @DisplayName("Should verify multiple interactions for sequential calls")
    void testVerifyMultipleInteractions() {
        // Arrange: Stub payments for different scenarios
        when(mockPaymentGateway.processPayment(100.0)).thenReturn(true);
        when(mockPaymentGateway.processPayment(50.0)).thenReturn(false);

        // Act: Make multiple calls
        orderProcessor.placeOrder("ORDER_A", 100.0); // Should call processPayment(100.0)
        orderProcessor.placeOrder("ORDER_B", 50.0);  // Should call processPayment(50.0)
        orderProcessor.placeOrder("ORDER_C", 100.0); // Should call processPayment(100.0) again

        // Assert/Verify:
        verify(mockPaymentGateway, times(2)).processPayment(100.0); // Called twice
        verify(mockPaymentGateway, times(1)).processPayment(50.0);  // Called once

        // Verify that no other calls were made on the mock
        verifyNoMoreInteractions(mockPaymentGateway); // Verifies nothing else happened beyond the verified calls
        System.out.println("    Test: testVerifyMultipleInteractions completed.");
    }

    // You can also verify the order of interactions, but it's often more brittle
    // and generally less recommended unless the order is truly crucial to the logic.
    // Example (advanced):
    // @Test
    // void testOrderOfInteractions() {
    //     InOrder inOrder = inOrder(mockPaymentGateway);
    //
    //     orderProcessor.placeOrder("ORDER_X", 100.0);
    //     orderProcessor.placePremiumOrder("ORDER_Y", 200.0);
    //
    //     inOrder.verify(mockPaymentGateway).processPayment(100.0);
    //     inOrder.verify(mockPaymentGateway).processPaymentWithId("ORDER_Y", 200.0);
    // }
}
