package com.example.android.testing.notes.util;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by Eduard on 11.01.2016.
 */
public class TestUtils {

    public static <T> TestSubscriber<T> subscribeTest(Observable<T> observable) {
        TestSubscriber<T> subscriber = new TestSubscriber<>();
        observable.subscribe(subscriber);
        return subscriber;
    }
}
