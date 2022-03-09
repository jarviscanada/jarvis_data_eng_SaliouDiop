package ca.jrvs.apps.learning;

public class Learn {

    public int fib(int n) {
        if(n <=1) return n;
        return fib(n-1) + fib(n-2);
    }
    public static void main(String[] args) {
        int n = 10;
        System.out.println(new Learn().fib(n));
    }
}
