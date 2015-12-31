package com.example.android.testing.notes.internal.di.modules;

import com.example.android.testing.notes.data.FakeNotesServiceApiImpl;
import com.example.android.testing.notes.data.NoteRepositories;
import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.data.NotesServiceApi;
import com.example.android.testing.notes.util.FakeImageFileImpl;
import com.example.android.testing.notes.util.ImageFile;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eduard on 30.12.2015.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ImageFile provideImageFile() {
        return new FakeImageFileImpl();
    }

    @Provides
    @Singleton
    NotesServiceApi getNotesServiceApi() {
        return new FakeNotesServiceApiImpl();
    }

    @Provides
    @Singleton
    NotesRepository provideNotesRepository(NotesServiceApi notesServiceApi) {
        return NoteRepositories.getInMemoryRepoInstance(notesServiceApi);
    }
}
