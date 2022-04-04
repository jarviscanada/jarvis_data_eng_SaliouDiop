package ca.jrvs.apps.practice;

import java.util.LinkedList;

public class StackImp {

    private final LinkedList<Integer> queue;

    public StackImp() {
        queue = new LinkedList<>();
    }

    public void push(int data) {
        queue.addFirst(data);
    }

    public int pop() {
        return queue.removeFirst();
    }

    public int peek() {
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        StackImp stack = new StackImp();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }
}