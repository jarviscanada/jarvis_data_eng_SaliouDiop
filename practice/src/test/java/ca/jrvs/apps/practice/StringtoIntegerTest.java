package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringtoIntegerTest {
    @Test
    void atoi() {
        assertEquals(-42, StringtoInteger.atoi("     -42"));
        assertEquals(4193, StringtoInteger.atoi("4193 with words"));
    }

}