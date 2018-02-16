package com.devschool.combine_observables;


import io.reactivex.Observable;

/**
 * This class is sample code type of combining observable and methods.
 */
public class CombineObservableType {

    public static void main(String[] args) {
        mergeMethod();
    }

    private static void mergeMethod() {
        System.out.println("Observable.merge() operator is called............ this is used to merge two observable into one subscription");

        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");

        Observable.merge(source1, source2).subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
