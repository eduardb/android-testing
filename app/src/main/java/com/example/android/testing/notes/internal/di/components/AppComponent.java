package com.example.android.testing.notes.internal.di.components;

import com.example.android.testing.notes.addnote.AddNoteComponent;
import com.example.android.testing.notes.addnote.AddNoteModule;
import com.example.android.testing.notes.internal.di.modules.AppModule;
import com.example.android.testing.notes.notedetail.NoteDetailComponent;
import com.example.android.testing.notes.notedetail.NoteDetailModule;
import com.example.android.testing.notes.notes.NotesComponent;
import com.example.android.testing.notes.notes.NotesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eduard on 28.12.2015.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    AddNoteComponent plus(AddNoteModule addNoteModule);
    NoteDetailComponent plus(NoteDetailModule noteDetailModule);
    NotesComponent plus(NotesModule notesModule);
}
