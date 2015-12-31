package com.example.android.testing.notes.addnote;

import com.example.android.testing.notes.internal.di.ViewScope;

import dagger.Subcomponent;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Subcomponent(modules = AddNoteModule.class)
public interface AddNoteComponent {
    AddNoteContract.UserActionsListener getUserActionsListener();
}
