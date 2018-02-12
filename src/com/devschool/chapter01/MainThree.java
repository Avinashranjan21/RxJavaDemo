package com.devschool.chapter01;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

/**
 * This class gives sample for Observable.just()
 * It will invoke the onNext() call for each one and then invoke onComplete() when they all have been pushed:
 */
public class MainThree {

    public static void main(String[] args) {


        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.map(String::length).filter(i -> i >= 5).subscribe(s -> System.out.println("RECEIVED: " + s));

        /**
         * Observable.fromIterable() to emit any item from any iterable type such as "List"
         */

        List<String> items = Arrays.asList("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        Observable<String> sourceIterable = Observable.fromIterable(items);

        sourceIterable.map(String::length).filter(i -> i >= 5).subscribe(s -> System.out.println("RECEIVED: " + s));

        /**
         * Implementing and subscribing to an Observer
         */

        System.out.println("Using observer interface ****************************");

        Observer<Integer> myObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("RECEIVED: "+integer);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done!");
            }
        };

        source.map(String::length).filter(i -> i >= 5)
                .subscribe(myObserver);


        /**
         * Consuming using lambda expression
         */

        System.out.println("Using observer interface with lambda expression ****************************");

        source.map(String::length).filter(i-> i>=5).subscribe(
                                                                integer -> System.out.println("RECEIVED: "+integer),
                                                                Throwable::printStackTrace,
                                                                ()-> System.out.println("Done!"));

    }
}
