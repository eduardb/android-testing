/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.notes.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load notes from the a data source.
 */
public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    /**
     * This method has reduced visibility for testing and is only visible to tests in the same
     * package.
     */
    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(@NonNull NotesServiceApi notesServiceApi) {
        mNotesServiceApi = checkNotNull(notesServiceApi);
    }

    @Override
    public Observable<List<Note>> getNotes() {
        // Load from API only if needed.
        if (mCachedNotes == null) {
            return mNotesServiceApi.getAllNotes()
                    .doOnNext(new Action1<List<Note>>() {
                        @Override
                        public void call(List<Note> notes) {
                            mCachedNotes = ImmutableList.copyOf(notes);
                        }
                    });
        } else {
            return Observable.just(mCachedNotes);
        }
    }

    @Override
    public Observable<Note> saveNote(@NonNull Note note) {
        checkNotNull(note);
        return mNotesServiceApi.saveNote(note)
                .doOnNext(new Action1<Note>() {
                    @Override
                    public void call(Note note) {
                        refreshData();
                    }
                });
    }

    @Override
    public Observable<Note> getNote(@NonNull final String noteId) {
        checkNotNull(noteId);
        // Load notes matching the id always directly from the API.
        return mNotesServiceApi.getNote(noteId);
    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }

}
