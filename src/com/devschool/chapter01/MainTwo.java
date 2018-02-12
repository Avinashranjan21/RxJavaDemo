package com.devschool.chapter01;

import io.reactivex.Observable;

public class MainTwo {

    public static void main(String[] args) {

        System.out.println("onError handled by observer ");
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onNext("Delta");
                emitter.onNext("Epsilon");
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });

        source.subscribe(s -> System.out.println("RECEIVED: " + s), Throwable::printStackTrace);

        System.out.println("Doing extra function to the above observable");

//        Below observable will  map the aobe observable data length to the lengths observable data
        Observable<Integer> lengths = source.map(String::length);


//        Below observable will filter the data transmitted from the above lengths observable
        Observable<Integer> filtered = lengths.filter(i -> i >= 5);

        filtered.subscribe(s -> System.out.println("RECEIVED " + s));

    }
}
