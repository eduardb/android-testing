package com.example.android.testing.notes.notedetail;

import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.internal.di.components.AppComponent;

import dagger.Component;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Component(modules = NoteDetailModule.class, dependencies = AppComponent.class)
public interface NoteDetailComponent {
    NoteDetailContract.UserActionsListener getUserActionsListener();
}
