package com.example.android.testing.notes.internal.di.components;

import com.example.android.testing.notes.data.NotesRepository;
import com.example.android.testing.notes.internal.di.modules.AppModule;
import com.example.android.testing.notes.util.ImageFile;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eduard on 28.12.2015.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    //Exposed to sub-graphs.
    ImageFile imageFile();
    NotesRepository notesRepository();
}
