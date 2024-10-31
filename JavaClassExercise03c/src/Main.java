import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---- Part A: Individual Threads ----");
        partA();

        System.out.println("---- Part B: Thread Pool ----");
        partB();
    }

    // Part A - Using individual threads
    public static void partA() {
        Thread primeThread = new Thread(new PrimeNumbersTask(), "PrimeThread");
        Thread fibonacciThread = new Thread(new FibonacciTask(), "FibonacciThread");
        Thread factorialThread = new Thread(new FactorialTask(), "FactorialThread");

        primeThread.start();
        fibonacciThread.start();
        factorialThread.start();

        try {
            primeThread.join();
            fibonacciThread.join();
            factorialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Part B - Using a Thread Pool with 5 threads
    public static void partB() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 25; i++) {
            final int num = i;
            executor.submit(() -> {
                System.out.printf("%s: Prime %d = %d at %d%n",
                        Thread.currentThread().getName(), num, calculateNthPrime(num), System.currentTimeMillis());
                randomDelay();
            });
        }

        for (int i = 1; i <= 50; i++) {
            final int num = i;
            executor.submit(() -> {
                System.out.printf("%s: Fibonacci %d = %d at %d%n",
                        Thread.currentThread().getName(), num, calculateNthFibonacci(num), System.currentTimeMillis());
                randomDelay();
            });
        }

        for (int i = 1; i <= 50; i++) {
            final int num = i;
            executor.submit(() -> {
                System.out.printf("%s: Factorial %d = %s at %d%n",
                        Thread.currentThread().getName(), num, calculateFactorial(num), System.currentTimeMillis());
                randomDelay();
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // Helper method for a random delay
    public static void randomDelay() {
        try {
            Thread.sleep(new Random().nextInt(401) + 100); // Random delay between 100-500ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Prime Numbers Calculation Task
    static class PrimeNumbersTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 25; i++) {
                System.out.printf("%s: Prime %d = %d at %d%n",
                        Thread.currentThread().getName(), i, calculateNthPrime(i), System.currentTimeMillis());
                randomDelay();
            }
        }
    }

    // Fibonacci Sequence Calculation Task
    static class FibonacciTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                System.out.printf("%s: Fibonacci %d = %d at %d%n",
                        Thread.currentThread().getName(), i, calculateNthFibonacci(i), System.currentTimeMillis());
                randomDelay();
            }
        }
    }

    // Factorial Calculation Task
    static class FactorialTask implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 50; i++) {
                System.out.printf("%s: Factorial %d = %s at %d%n",
                        Thread.currentThread().getName(), i, calculateFactorial(i), System.currentTimeMillis());
                randomDelay();
            }
        }
    }

    // Method to calculate the nth prime number
    public static int calculateNthPrime(int n) {
        int count = 0;
        int num = 1;
        while (count < n) {
            num++;
            if (isPrime(num)) {
                count++;
            }
        }
        return num;
    }

    // Helper to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Method to calculate the nth Fibonacci number
    public static long calculateNthFibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1, c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    // Method to calculate the factorial of a given number
    public static BigInteger calculateFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
