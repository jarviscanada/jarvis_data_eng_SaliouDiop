package ca.jrvs.apps.learning;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Learn {

    public int fib(int n) {
        if(n <=1) return n;
        return fib(n-1) + fib(n-2);
    }
    public static void main(String[] args) {
        LinkedList<Person> person = new LinkedList<>();

        person.add(new Person("Saliou", 27));
        person.add(new Person("Arame", 20));

//        System.out.println(person.);
    }

    // LinkedList
    public static void linkedList() {
        LinkedList<Person> person = new LinkedList<>();
        person.add(new Person("Saliou", 27));
        person.add(new Person("Arame", 20));

        System.out.println(person.peek());


    }
    // stack
    public void LearnStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Peek");
        System.out.println(stack.peek());
        System.out.println("size = " + stack.size());
        System.out.println("Pop");
        System.out.println(stack.pop());
        System.out.println("size = " + stack.size());
    }

    // queue

    public void LearnQueue() {
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person("John", 20));
        queue.add(new Person("Mary", 30));
        queue.add(new Person("Bob", 40));
        queue.add(new Person("Jane", 50));
        queue.add(new Person("Jack", 60));

        System.out.println("Peek");
        System.out.println(queue.peek());
        System.out.println("size = " + queue.size());
        System.out.println("Poll");
        System.out.println(queue.poll());
        System.out.println("size = " + queue.size());


    }
}

class Person {
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
