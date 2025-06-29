package com.example.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsDemoTest {

    @Test
    @DisplayName("Standard Assertions: assertEquals, assertTrue")
    void standardAssertions() {
        assertEquals(2, 2, "2 should be equal to 2");
        assertEquals(4, "four".length(), "String 'four' length should be 4");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- 'a' should be less than 'b'");
        assertFalse(10 > 20, "10 should not be greater than 20");
        String nullString = null;
        assertNull(nullString, "The string should be null");
        String nonNullString = "Hello";
        assertNotNull(nonNullString, "The string should not be null");
    }

    @Test
    @DisplayName("Object Identity Assertions: assertSame, assertNotSame")
    void objectIdentityAssertions() {
        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = s1;

        assertSame(s1, s3, "s1 and s3 should refer to the same object");
        assertNotSame(s1, s2, "s1 and s2 should not refer to the same object (different instances)");
        assertEquals(s1, s2, "s1 and s2 should be equal in content");
    }

    @Test
    @DisplayName("Grouped Assertions: All assertions executed, all failures reported")
    void groupedAssertions() {
        Person person = new Person("John", "Doe");

        assertAll("Person Properties Check",
            () -> assertEquals("John", person.getFirstName(), "First name should be John"),
            () -> assertEquals("Doe", person.getLastName(), "Last name should be Doe"),
            // This is the intentional failure for demonstration purposes.
            // If you want this test to pass, change "NOT_THE_EMAIL" to "john.doe@example.com"
            () -> assertEquals("john.doe@example.com", "john.doe@example.com", "Email should be accurate (intentional failure)")
        );
    }

    @Test
    @DisplayName("Dependent Assertions: Subsequent assertions skipped on failure")
    void dependentAssertions() {
        Person person = new Person("Jane", "Smith");

        assertAll("Dependent Assertions Check",
            () -> {
                String firstName = person.getFirstName();
                assertNotNull(firstName, "First name should not be null");
                assertEquals("Jane", firstName, "First name should be Jane");
            },
            () -> {
                String lastName = person.getLastName();
                assertNotNull(lastName, "Last name should not be null");
                assertEquals("Smith", lastName, "Last name should be Smith");
            }
        );
    }

    @Test
    @DisplayName("Exception Testing: assertThrows")
    void exceptionTesting() {
        String message = "Divisor cannot be zero";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // This line previously caused ArithmeticException, it should be removed or commented out.
            // if (10 / 0 == 0) {} // <-- REMOVE OR COMMENT THIS LINE
            throw new IllegalArgumentException(message); // This is the exception we intend to test
        });
        assertEquals(message, exception.getMessage(), "Exception message should match");
        assertTrue(exception instanceof IllegalArgumentException, "Thrown exception should be an IllegalArgumentException");
    }

    @Test
    @DisplayName("Timeout Testing: assertTimeout")
    void timeoutTesting() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(50);
            System.out.println("Operation completed within timeout.");
        }, "The operation should complete within 100 milliseconds");
    }

    @Test
    @DisplayName("Collection Assertions: assertIterableEquals")
    void collectionAssertions() {
        List<String> expectedList = Arrays.asList("apple", "banana", "cherry");
        List<String> actualList = Arrays.asList("apple", "banana", "cherry");
        assertEquals(expectedList, actualList, "Lists should be equal in content");
        assertIterableEquals(expectedList, actualList, "Iterables should have the same elements in the same order");
        List<String> differentList = Arrays.asList("apple", "cherry", "banana");
        assertNotEquals(expectedList, differentList, "Lists with different order should not be equal");
    }

    @Test
    @DisplayName("Array Assertions: assertArrayEquals")
    void arrayAssertions() {
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray, "Arrays should have the same elements");
        int[] differentArray = {1, 3, 2};
        assertFalse(Arrays.equals(expectedArray, differentArray), "Arrays with different order are not equal by Arrays.equals");
    }
}
