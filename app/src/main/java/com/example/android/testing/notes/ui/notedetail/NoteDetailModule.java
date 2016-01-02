package com.example.android.testing.notes.ui.notedetail;

import android.support.annotation.NonNull;

import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.ui.notedetail.NoteDetailContract.UserActionsListener;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduard on 30.12.2015.
 */
@Module
public class NoteDetailModule {

    private final NoteDetailContract.View mView;

    public NoteDetailModule(NoteDetailContract.View view) {
        mView = view;
    }

    @Provides
    @ViewScope
    UserActionsListener provideUserActionsListener(@NonNull NotesRepository notesRepository) {
        return new NoteDetailPresenter(notesRepository, mView);
    }
}
