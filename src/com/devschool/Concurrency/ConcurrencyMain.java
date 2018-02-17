package com.devschool.Concurrency;


import com.devschool.Utility;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Concurrency and Parallelization
 */
public class ConcurrencyMain {

    public static void main(String[] args) {

        Observable<String> source1 = Observable.just("Avinash", "Pandey", "Ranjan", "Bokaro", "Steel", "City")
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation((s)));
//                .subscribe(System.out::println);

        Observable<Integer> source2 = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(s -> intenseCalculation((s)));
//                .subscribe(System.out::println);


        System.out.println("Both subscriber are working on separate observable");


        System.out.println("Now merging two observable into one observable using ZIP method");

        Observable.zip(source1, source2, (s,i) -> s + "-" + i)
                .subscribe(System.out::println);

        Utility.sleep(20000);

        blockingSubscribe();
        ExecutorServiceMethod();
    }

    private static <T> T intenseCalculation(T value) {
//        Utility.sleep(ThreadLocalRandom.current().nextInt(3000)); //todo Making thread sleep for random time
        Utility.sleep(1000); // TODO: 16/02/18 Making thread to sleep for 1 sec fixed
        return value;
    }

    private static void blockingSubscribe(){
        System.out.println("Calling blockingSubscribe() to block main thread");
        Observable.just("Avinash", "Pandey", "Ranjan", "Bokaro", "Steel", "City")
                .subscribeOn(Schedulers.computation())
                .map(ConcurrencyMain::intenseCalculation)
                .blockingSubscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!"));
    }

    private static void ExecutorServiceMethod(){
        System.out.println("ExecutorService method is being called");

        int numberOfThreads = 20;
        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);
        Scheduler scheduler = Schedulers.from(executor);
        Observable.just("Avinash", "Pandey", "Ranjan", "Bokaro", "Steel", "City")
                .subscribeOn(scheduler)
                .doFinally(executor::shutdown)
                .subscribe(System.out::println);
    }

}
