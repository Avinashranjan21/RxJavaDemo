package com.devschool.chapter01;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Launcher {

    public static void main(String[] args) {
        Observable<String> myString = Observable.just("Alpha","Beta","Gamma","Delta","Epsilon");

        System.out.println("A simple just is used to emit data from observable");
        myString.subscribe(System.out::println);

        System.out.println("Using map to handle the data transmit from observable");
        myString.map(String::length).subscribe(System.out::println);

        System.out.println("Now using observable interval method to push object in fixed sequence");
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);

        secondIntervals.subscribe(System.out::println);
        // Hold main thread for 5 seconds so observable above has chance to fire

        sleep(5000);


    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
