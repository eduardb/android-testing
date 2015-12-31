package com.example.android.testing.notes.notedetail;

import com.example.android.testing.notes.internal.di.ViewScope;

import dagger.Subcomponent;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Subcomponent(modules = NoteDetailModule.class)
public interface NoteDetailComponent {
    NoteDetailContract.UserActionsListener getUserActionsListener();
}
