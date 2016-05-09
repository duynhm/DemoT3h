package com.example;

import rx.Observable;
import rx.Subscriber;

public class main {
    public static void main(String[] arg){
        Observable.just("hello")
                .map(s -> potentialException(s))
                .map(s->anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Ouch!");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
    }

    public static String potentialException(String s){
        System.out.println("potentialException : " +s);
        return s.substring(10,15);
    }

    public static String anotherPotentialException(String s){
        System.out.println("anotherPotentialException: " + s);
        return s.toUpperCase();
    }


}
