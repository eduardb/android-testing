package com.example.android.testing.notes;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Eduard on 28.12.2015.
 */
public class NotesApp extends Application {

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        mRefWatcher = LeakCanary.install(this);

        if (mRefWatcher == RefWatcher.DISABLED && "leak".equals(BuildConfig.FLAVOR)) {
            return;
        }
    }
}
