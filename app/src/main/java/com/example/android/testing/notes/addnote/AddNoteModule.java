package com.example.android.testing.notes.addnote;

import android.support.annotation.NonNull;

import com.example.android.testing.notes.addnote.AddNoteContract.UserActionsListener;
import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.internal.di.ViewScope;
import com.example.android.testing.notes.util.ImageFile;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduard on 30.12.2015.
 */
@Module
public class AddNoteModule {

    private final AddNoteContract.View mView;

    public AddNoteModule(AddNoteContract.View view) {
        mView = view;
    }

    @Provides
    @ViewScope
    UserActionsListener provideUserActionsListener(@NonNull NotesRepository notesRepository,
                                                   @NonNull ImageFile imageFile) {
        return new AddNotePresenter(notesRepository, mView, imageFile);
    }
}
