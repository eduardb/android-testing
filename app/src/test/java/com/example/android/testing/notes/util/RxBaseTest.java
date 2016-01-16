package com.example.android.testing.notes.util;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

/**
 * Created by Eduard on 16.01.2016.
 */
@RunWith(RxJavaTestRunner.class)
public class RxBaseTest {

    @Before
    public void registerSchedulersHook() {

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After
    public void reset() {
        RxAndroidPlugins.getInstance().reset();
    }
}
