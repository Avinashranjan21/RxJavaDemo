package com.devschool.chapter01;


import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Cold and hot Observables
 */

public class ColdHotObservables {

    public static void main(String[] args) {

        coldObservable();
        connectableObservable();

    }

    private static void coldObservable() {
        //        Cold observable........

        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
//first observer
        source.subscribe(s -> System.out.println("Observer 1 Received: " + s));
//second observer
        source.subscribe(s -> System.out.println("Observer 2 Received: " + s));
    }
//        Hot observable .........

    public static void connectableObservable() {
        ConnectableObservable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon").publish();
//Set up observer 1
        source.subscribe(s -> System.out.println("Observer 1: " + s));
//Set up observer 2
        source.map(String::length).subscribe(i -> System.out.println("Observer 2: " + i));
//Fire!
        source.connect();
    }
}
