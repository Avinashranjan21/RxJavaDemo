package com.devschool.chapter01;

import com.devschool.Utility;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ObservableType {

    public static void main(String[] args) {

//        rangeObservable();
//        intervalObservable();
//        intervalWith2Subscriber();
//        connectableWith2Subscriber();


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


}
