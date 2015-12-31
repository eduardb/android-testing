package com.example.android.testing.notes.notes;

import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.internal.di.components.AppComponent;

import dagger.Component;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Component(modules = NotesModule.class, dependencies = AppComponent.class)
public interface NotesComponent {
    NotesContract.UserActionsListener getUserActionsListener();
}
