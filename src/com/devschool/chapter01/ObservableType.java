package com.devschool.chapter01;

import com.devschool.Utility;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ObservableType {

    public static void main(String[] args) {

//        rangeObservable();
//        intervalObservable();
//        intervalWith2Subscriber();
//        connectableWith2Subscriber();
        emptyObservable();
        neverObservable();
//        errorObservable();
        deferObservable();


    }




    private static void connectableWith2Subscriber() {
        //    Now using interval observer using connectable Observer

        ConnectableObservable<Long> secondsConnectable = Observable.interval(1, TimeUnit.SECONDS).publish();

        secondsConnectable.subscribe(l -> System.out.println("Avinash Pandey 1-"+l));
        secondsConnectable.connect();

        Utility.sleep(5000);

        secondsConnectable.subscribe(l-> System.out.println("Avinash Pandey 2-"+l));
        secondsConnectable.connect();

        Utility.sleep(5000);
    }

    private static void intervalWith2Subscriber() {
        //        Now testing with two subscriber and using two sleep method on Main thread

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        //Observer 1
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        //sleep 5 seconds
        Utility.sleep(5000);
        //Observer 2
        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        //sleep 5 seconds
        Utility.sleep(5000);
    }

    private static void intervalObservable() {
        System.out.println("Observable.interval sample code");
        /**
         * Observable.range
         * */
        Observable.interval(1, TimeUnit.SECONDS).subscribe(s -> System.out.println(s + " Avinash Pandey"));
        Utility.sleep(5000);
    }

    private static void rangeObservable() {
        System.out.println("Observable.range sample code");
        /**
         * Observable.range
         * @Param1 start is the stating point
         * @Param2 count is total count of emission
         * */
        Observable.range(1, 10).subscribe(s -> System.out.println("Avinash Pandey: " + s));
    }

    private static void futureObservable(){
        Future<String> futureValue = null;  // initialize this with any future observable
        Observable.fromFuture(futureValue)
                .map(String::length)
                .subscribe(System.out::println);
    }

    private static void emptyObservable(){
        System.out.println("Empty observable calls below");
        Observable<String> empty = Observable.empty();
        empty.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
    }

    private static void neverObservable(){
        System.out.println("Never observable calls below : it will never calls onComplete()");
        Observable<String> empty = Observable.never();
        empty.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
        Utility.sleep(5000);
    }

    private static void errorObservable(){
        System.out.println("This error observable will immediately call onError() with specified exception");
        Observable.error(new Exception("Crash and burn!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!"));
    }

    private static void deferObservable(){
        System.out.println(" Defer Observable : it creates separate state for each observer");
        int start = 1;
        int count = 5;

        Observable<Integer> source = Observable.defer(() -> Observable.range(start,count));
        Observable<Integer> source2 = Observable.range(start,count);
        source.subscribe(i -> System.out.println("Observer 1: " + i));
//modify count
//        count = 10; //todo check this error
        source.subscribe(i -> System.out.println("Observer 2: " + i));

    }


}
