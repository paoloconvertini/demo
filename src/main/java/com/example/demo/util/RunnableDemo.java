package com.example.demo.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunnableDemo implements Runnable {
    private String threadName;
    private Object o;

    public RunnableDemo(String name, Object o) {
        threadName = name;
        this.o = o;
    }

    public void run() {
        System.out.println("Thread " + threadName);
        try {
            System.out.println(o.toString());
        } catch (Exception e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
    }

//    public void start() {
//        if (t == null) {
//            t = new Thread(this, threadName);
//            t.start();
//        }
//    }
}
