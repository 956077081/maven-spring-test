package com.pht;

import java.util.HashMap;
import java.util.concurrent.*;

public class TestMain2 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("asda");
        }, "name").start();
        Callable<Object> ca = () -> {
            System.out.println("asda");
            Thread.sleep(3000);
            return 1;
        };
        FutureTask futureTask = new FutureTask<Object>(ca);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            futureTask.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("asdasda");
    }
}
