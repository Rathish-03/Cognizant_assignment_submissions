package com.example.ex4;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Account, demonstrating AAA pattern, test fixtures,
 * and setup/teardown methods in JUnit 5.
 */
@DisplayName("Account Operations Tests") // Class-level display name
class AccountTest {

    private Account account; // This is a test fixture: an object used by multiple tests
    private static final String TEST_ACCOUNT_NUMBER = "ACC123"; // Constant for all tests in this class
    private static final BigDecimal INITIAL_BALANCE = new BigDecimal("100.00");

    // --- Setup Methods ---

    /**
     * Runs once before all test methods in this class.
     * Used for heavy setup that can be shared across all tests (e.g., database connection pool).
     * Must be static.
     */
    @BeforeAll
    static void setupAll() {
        System.out.println(">>> @BeforeAll: Executed once before all tests in AccountTest class.");
        // Example: Initialize a static resource, like a shared database connection or a logger.
    }

    /**
     * Runs before each test method.
     * Used for initializing or resetting test fixtures to a known state for each individual test.
     */
    @BeforeEach
    void setup() {
        // Arrange: Initialize a fresh Account object for each test
        account = new Account(TEST_ACCOUNT_NUMBER, INITIAL_BALANCE);
        System.out.println("    >>> @BeforeEach: Account initialized for test: " + account.getAccountNumber() + " with balance " + account.getBalance());
    }

    // --- Test Methods (demonstrating AAA) ---

    @Test
    @DisplayName("Should correctly deposit positive amount")
    void testDepositPositiveAmount() {
        // Arrange: (Done in @BeforeEach)
        BigDecimal depositAmount = new BigDecimal("50.00");
        BigDecimal expectedBalance = new BigDecimal("150.00"); // 100.00 (initial) + 50.00

        // Act: Perform the action under test
        account.deposit(depositAmount);

        // Assert: Verify the outcome
        assertNotNull(account, "Account should not be null after deposit");
        assertEquals(expectedBalance, account.getBalance(), "Balance should be updated after deposit");
        System.out.println("        Test: testDepositPositiveAmount completed.");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for depositing negative or zero amount")
    void testDepositInvalidAmount() {
        // Arrange: (Done in @BeforeEach)
        BigDecimal negativeAmount = new BigDecimal("-10.00");
        BigDecimal zeroAmount = BigDecimal.ZERO;

        // Act & Assert for negative amount
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(negativeAmount);
        }, "Should throw IllegalArgumentException for negative deposit");

        // Act & Assert for zero amount
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(zeroAmount);
        }, "Should throw IllegalArgumentException for zero deposit");

        // Assert that the balance remains unchanged
        assertEquals(INITIAL_BALANCE, account.getBalance(), "Balance should remain unchanged after failed deposit attempts");
        System.out.println("        Test: testDepositInvalidAmount completed.");
    }

    @Test
    @DisplayName("Should correctly withdraw positive amount")
    void testWithdrawPositiveAmount() {
        // Arrange: (Done in @BeforeEach)
        BigDecimal withdrawAmount = new BigDecimal("30.00");
        BigDecimal expectedBalance = new BigDecimal("70.00"); // 100.00 (initial) - 30.00

        // Act:
        account.withdraw(withdrawAmount);

        // Assert:
        assertNotNull(account, "Account should not be null after withdrawal");
        assertEquals(expectedBalance, account.getBalance(), "Balance should be updated after withdrawal");
        System.out.println("        Test: testWithdrawPositiveAmount completed.");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for withdrawing more than balance")
    void testWithdrawInsufficientFunds() {
        // Arrange: (Done in @BeforeEach)
        BigDecimal withdrawAmount = new BigDecimal("150.00"); // More than initial 100.00

        // Act & Assert:
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(withdrawAmount);
        }, "Should throw IllegalArgumentException for insufficient funds");

        // Assert that the balance remains unchanged
        assertEquals(INITIAL_BALANCE, account.getBalance(), "Balance should remain unchanged after failed withdrawal attempt");
        System.out.println("        Test: testWithdrawInsufficientFunds completed.");
    }

    // --- Teardown Methods ---

    /**
     * Runs after each test method.
     * Used for cleaning up resources that were set up for the individual test.
     */
    @AfterEach
    void teardown() {
        // Example: Nullify objects, close streams, reset mocks if not handled by extensions.
        account = null; // Good practice to nullify to avoid accidental use or memory leaks if not GC'd
        System.out.println("    >>> @AfterEach: Account object nulled.");
    }

    /**
     * Runs once after all test methods in this class.
     * Used for heavy cleanup that was shared across all tests (e.g., closing database connection pool).
     * Must be static.
     */
    @AfterAll
    static void teardownAll() {
        System.out.println(">>> @AfterAll: Executed once after all tests in AccountTest class.");
        // Example: Close static resources initialized in @BeforeAll.
    }
}
