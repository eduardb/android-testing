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
import android.support.v4.util.ArrayMap;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Implementation of the Notes Service API that adds a latency simulating network.
 */
public class NotesServiceApiImpl implements NotesServiceApi {

    protected static final int SERVICE_LATENCY_IN_MILLIS = 2000;
    protected static final ArrayMap<String, Note> NOTES_SERVICE_DATA =
            NotesServiceApiEndpoint.loadPersistedNotes();

    @RxLogObservable
    @Override
    public Observable<List<Note>> getAllNotes() {
        // Simulate network by delaying the execution.
        return Observable.create(new Observable.OnSubscribe<List<Note>>() {
            @Override
            public void call(Subscriber<? super List<Note>> subscriber) {
                try {
                    Thread.sleep(SERVICE_LATENCY_IN_MILLIS);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(new ArrayList<>(NOTES_SERVICE_DATA.values()));
                subscriber.onCompleted();
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<Note> getNote(final String noteId) {
        return Observable.create(new Observable.OnSubscribe<Note>() {
            @Override
            public void call(Subscriber<? super Note> subscriber) {
                try {
                    Thread.sleep(SERVICE_LATENCY_IN_MILLIS);
                } catch (InterruptedException e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(NOTES_SERVICE_DATA.get(noteId));
                subscriber.onCompleted();
            }
        });
    }

    @RxLogObservable
    @Override
    public Observable<Note> saveNote(@NonNull final Note note) {
        return Observable.create(new Observable.OnSubscribe<Note>() {
            @Override
            public void call(Subscriber<? super Note> subscriber) {
                NOTES_SERVICE_DATA.put(note.getId(), note);
                subscriber.onNext(note);
                subscriber.onCompleted();
            }
        });
    }
}
