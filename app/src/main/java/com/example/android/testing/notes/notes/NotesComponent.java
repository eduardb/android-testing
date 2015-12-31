package com.example.android.testing.notes.notes;

import com.example.android.testing.notes.internal.di.ViewScope;

import dagger.Subcomponent;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Subcomponent(modules = NotesModule.class)
public interface NotesComponent {
    NotesContract.UserActionsListener getUserActionsListener();
}
