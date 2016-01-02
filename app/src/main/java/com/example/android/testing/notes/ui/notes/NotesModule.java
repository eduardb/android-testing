package com.example.android.testing.notes.ui.notes;

import android.support.annotation.NonNull;

import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.ui.notes.NotesContract.UserActionsListener;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduard on 30.12.2015.
 */
@Module
public class NotesModule {

    private final NotesContract.View mView;

    public NotesModule(NotesContract.View view) {
        mView = view;
    }

    @Provides
    @ViewScope
    UserActionsListener provideUserActionsListener(@NonNull NotesRepository notesRepository) {
        return new NotesPresenter(notesRepository, mView);
    }
}
