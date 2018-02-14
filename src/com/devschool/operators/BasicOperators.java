package com.devschool.operators;

import com.devschool.Utility;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class BasicOperators {
    public static void main(String[] args) {
        filterOperator();
        takeOperator();
        skipOperator();
        takeWhileOperator();
        skipWhileOperator();

    }

    private static void filterOperator() {
        System.out.println("Filter Operator...................................");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").filter(s -> s.length() != 5).
                subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    private static void takeOperator() {
        System.out.println("Take Operator.....................................");
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println("Take Operator with time constraint................");
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        Utility.sleep(5000);
    }

    private static void skipOperator() {
        System.out.println("Skip Operator.....................................");

        Observable.range(1, 100)
                .skip(90)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void takeWhileOperator() {
        System.out.println("takeWhile Operator.....................................");
        Observable.range(1, 100)
                .takeWhile(i -> i < 5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void skipWhileOperator() {
        System.out.println("skipWhile Operator.....................................");
        Observable.range(1, 100)
                .skipWhile(i -> i <= 95)
                .subscribe(i -> System.out.println("skipWhile RECEIVED: " + i));
    }

}
