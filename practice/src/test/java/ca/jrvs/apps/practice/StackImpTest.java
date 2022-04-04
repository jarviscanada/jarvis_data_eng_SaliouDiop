package ca.jrvs.apps.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackImpTest {
    @Test
    void push() {
        StackImp stack = new StackImp();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, stack.peek());
    }
    @Test
    void pop() {
        StackImp stack = new StackImp();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.size());
        assertEquals(2, stack.peek());
    }
    @Test
    void peek() {
        StackImp stack = new StackImp();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());
        assertEquals(3, stack.size());
    }
    @Test
    void isEmpty() {
        StackImp stack = new StackImp();
        assertTrue(stack.isEmpty());
    }

}