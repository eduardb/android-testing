package com.example.android.testing.notes.ui.addnote;

import com.example.android.testing.notes.base.BaseComponent;
import com.example.android.testing.notes.internal.di.ViewScope;

import dagger.Subcomponent;

/**
 * Created by Eduard on 30.12.2015.
 */
@ViewScope
@Subcomponent(modules = AddNoteModule.class)
public interface AddNoteComponent extends BaseComponent {
    @Override
    AddNoteContract.UserActionsListener getUserActionsListener();
}
