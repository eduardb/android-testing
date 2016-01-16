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

package com.example.android.testing.notes.ui.notedetail;

import com.example.android.testing.notes.base.RxPresenter;
import com.example.android.testing.notes.data.Note;
import com.example.android.testing.notes.data.NotesRepository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link NoteDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class NoteDetailPresenter extends RxPresenter<NoteDetailContract.View>
        implements NoteDetailContract.UserActionsListener {

    private final NotesRepository mNotesRepository;

    public NoteDetailPresenter(@NonNull NotesRepository notesRepository,
                               @NonNull NoteDetailContract.View noteDetailView) {
        super(noteDetailView);
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null!");
    }

    @Override
    public void openNote(@Nullable String noteId) {
        if (null == noteId || noteId.isEmpty()) {
            getView().showMissingNote();
            return;
        }

        getView().setProgressIndicator(true);
        mNotesRepository
                .getNote(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Note>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Note note) {
                        getView().setProgressIndicator(false);
                        if (null == note) {
                            getView().showMissingNote();
                        } else {
                            showNote(note);
                        }
                    }
                });
    }

    protected void showNote(Note note) {
        String title = note.getTitle();
        String description = note.getDescription();
        String imageUrl = note.getImageUrl();

        if (title != null && title.isEmpty()) {
            getView().hideTitle();
        } else {
            getView().showTitle(title);
        }

        if (description != null && description.isEmpty()) {
            getView().hideDescription();
        } else {
            getView().showDescription(description);
        }

        if (imageUrl != null) {
            getView().showImage(imageUrl);
        } else {
            getView().hideImage();
        }

    }
}
