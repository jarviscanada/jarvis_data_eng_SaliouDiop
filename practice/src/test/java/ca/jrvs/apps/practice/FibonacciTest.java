package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {
    @Test
    void fibonacciTest() {
        int n = Fibonacci.fibonacci(10);
        assertEquals(55, n);
    }

    @Test
    void fibonacciTest2() {
        int n = Fibonacci.fibonacci(0);
        assertEquals(0, n);
    }

    @Test
    void fibonacciTest3() {
        int n = Fibonacci.fibonacci(2);
        assertEquals(1, n);
    }

}