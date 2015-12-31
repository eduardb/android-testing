package com.example.android.testing.notes.addnote;

import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.internal.di.components.AppComponent;

import dagger.Component;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Component(modules = AddNoteModule.class, dependencies = AppComponent.class)
public interface AddNoteComponent {
    AddNoteContract.UserActionsListener getUserActionsListener();
}
