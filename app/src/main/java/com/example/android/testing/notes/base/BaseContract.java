package com.example.android.testing.notes.base;

/**
 * Created by Eduard on 02.01.2016.
 */
public interface BaseContract {
    interface View {
    }

    interface UserActionsListener {
        void wakeUp();
        void sleep();
        void destroy();
    }
}