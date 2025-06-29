package com.example.ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;

/**
 * Test class for the Calculator.
 * Demonstrates basic JUnit 5 test setup and simple assertions.
 */
class CalculatorTest { // <--- Class declaration starts here

    /**
     * Tests the add method of the Calculator class.
     * This is a basic test to ensure JUnit is set up and running.
     * AAA Pattern:
     * Arrange: Create Calculator object and define expected/actual values.
     * Act: Call the method under test.
     * Assert: Verify the result.
     */
    @Test
    @DisplayName("Should correctly add two positive numbers")
    void testAddPositiveNumbers() {
        // Arrange
        Calculator calculator = new Calculator();
        int num1 = 5;
        int num2 = 3;
        int expectedSum = 8;

        // Act
        int actualSum = calculator.add(num1, num2);

        // Assert
        assertEquals(expectedSum, actualSum, "The sum of 5 and 3 should be 8");
    }

    /**
     * Tests the add method with negative numbers.
     */
    @Test
    @DisplayName("Should correctly add a positive and a negative number")
    void testAddPositiveAndNegativeNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(5, -3), "5 + (-3) should be 2");
    }

    /**
     * Tests the subtract method.
     */
    @Test
    @DisplayName("Should correctly subtract two numbers")
    void testSubtractNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 should be 2");
    }

    /**
     * Tests the multiply method.
     */
    @Test
    @DisplayName("Should correctly multiply two numbers")
    void testMultiplyNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(15, calculator.multiply(5, 3), "5 * 3 should be 15");
    }

    /**
     * Tests the divide method with a non-zero divisor.
     */
    @Test
    @DisplayName("Should correctly divide two numbers")
    void testDivideNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2.5, calculator.divide(5, 2), 0.001, "5 / 2 should be 2.5"); // Using delta for double comparison
    }

    /**
     * Tests that the divide method throws an exception when dividing by zero.
     */
    @Test
    @DisplayName("Should throw IllegalArgumentException when dividing by zero")
    void testDivideByZeroThrowsException() { // <--- This method starts around line 84 in my example
        // Arrange
        Calculator calculator = new Calculator();

        // Act & Assert
        // Use assertThrows to verify that a specific exception is thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0); // This code block is expected to throw the exception
        });

        // Optionally, assert on the exception message
        assertEquals("Divisor cannot be zero", exception.getMessage(), "Exception message should match");
    } // <--- This closing brace is around line 92 in my example
} // <--- This is the final closing brace for the class, around line 94 in my example
