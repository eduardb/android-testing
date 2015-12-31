package com.example.android.testing.notes;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.example.android.testing.notes.internal.di.components.AppComponent;
import com.example.android.testing.notes.internal.di.components.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Eduard on 28.12.2015.
 */
public class NotesApp extends Application {

    private RefWatcher mRefWatcher;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRefWatcher = LeakCanary.install(this);

        if (mRefWatcher == RefWatcher.DISABLED && "leak".equals(BuildConfig.FLAVOR)) {
            return;
        }

        mAppComponent = getAppComponentBuilder().build();
    }

    public static NotesApp get(Context context) {
        return (NotesApp) context.getApplicationContext();
    }

    public RefWatcher refWatcher() {
        return mRefWatcher;
    }

    public AppComponent appComponent() {
        return mAppComponent;
    }

    @NonNull
    @VisibleForTesting
    public DaggerAppComponent.Builder getAppComponentBuilder() {
        return DaggerAppComponent.builder();
    }
}
