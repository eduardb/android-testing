package com.example.android.testing.notes.internal.di.modules;

import com.example.android.testing.notes.data.NoteRepositories;
import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.data.NotesServiceApi;
import com.example.android.testing.notes.data.NotesServiceApiImpl;
import com.example.android.testing.notes.util.ImageFile;
import com.example.android.testing.notes.util.ImageFileImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduard on 28.12.2015.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ImageFile provideImageFile() {
        return new ImageFileImpl();
    }

    @Provides
    @Singleton
    NotesServiceApi getNotesServiceApi() {
        return new NotesServiceApiImpl();
    }

    @Provides
    @Singleton
    NotesRepository provideNotesRepository(NotesServiceApi notesServiceApi) {
        return NoteRepositories.getInMemoryRepoInstance(notesServiceApi);
    }
}
