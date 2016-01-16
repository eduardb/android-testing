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

import com.example.android.testing.notes.util.TestUtils;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of the in-memory repository with cache.
 */
public class InMemoryNotesRepositoryTest {

    private final static String NOTE_TITLE = "title";

    private static List<Note> NOTES = Lists.newArrayList(new Note("Title1", "Description1"),
            new Note("Title2", "Description2"));

    private InMemoryNotesRepository mNotesRepository;

    @Mock
    private NotesServiceApiImpl mServiceApi;

    @Before
    public void setupNotesRepository() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mNotesRepository = new InMemoryNotesRepository(mServiceApi);
    }

    @Test
    public void getNotes_repositoryCachesAfterFirstApiCall() {
        // When two calls are issued to the notes repository
        twoLoadCallsToRepository();

        // Then notes where only requested once from Service API
        verify(mServiceApi).getAllNotes();
    }

    @Test
    public void invalidateCache_DoesNotCallTheServiceApi() {
        twoLoadCallsToRepository();

        // When data refresh is requested
        mNotesRepository.refreshData();
        final TestSubscriber<List<Note>> testSubscriber =
                TestUtils.subscribeTest(mNotesRepository.getNotes()); // Third call to API

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(NOTES);
        testSubscriber.assertCompleted();

        // The notes where requested twice from the Service API (Caching on first and third call)
        verify(mServiceApi, times(2)).getAllNotes();
    }

    @Test
    public void getNotes_requestsAllNotesFromServiceApi() {
        when(mServiceApi.getAllNotes()).thenReturn(Observable.just(NOTES));

        // When notes are requested from the notes repository
        final TestSubscriber<List<Note>> testSubscriber =
                TestUtils.subscribeTest(mNotesRepository.getNotes());

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(NOTES);
        testSubscriber.assertCompleted();

        // Then notes are loaded from the service API
        verify(mServiceApi).getAllNotes();
    }

    @Test
    public void saveNote_savesNoteToServiceAPIAndInvalidatesCache() {
        // Given a stub note with title and description
        Note newNote = new Note(NOTE_TITLE, "Some Note Description");

        when(mServiceApi.saveNote(newNote)).thenReturn(Observable.just(newNote));

        // When a note is saved to the notes repository
        final TestSubscriber<Note> testSubscriber =
                TestUtils.subscribeTest(mNotesRepository.saveNote(newNote));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(newNote);
        testSubscriber.assertCompleted();

        // Then the notes cache is cleared
        assertThat(mNotesRepository.mCachedNotes, is(nullValue()));
    }

    @Test
    public void getNote_requestsSingleNoteFromServiceApi() {
        when(mServiceApi.getNote(anyString())).thenReturn(Observable.<Note>just(null));

        // When a note is requested from the notes repository
        final TestSubscriber<Note> testSubscriber =
                TestUtils.subscribeTest(mNotesRepository.getNote(NOTE_TITLE));

        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(null);
        testSubscriber.assertCompleted();

        // Then the note is loaded from the service API
        verify(mServiceApi).getNote(eq(NOTE_TITLE));
    }

    /**
     * Convenience method that issues two calls to the notes repository
     */
    private void twoLoadCallsToRepository() {
        when(mServiceApi.getAllNotes()).thenReturn(Observable.just(NOTES));

        // When notes are requested from repository
        final TestSubscriber<List<Note>> testSubscriber1 =
                TestUtils.subscribeTest(mNotesRepository.getNotes()); // First call to API

        // Verify that the notes were requested from Service API
        verify(mServiceApi).getAllNotes();

        testSubscriber1.assertNoErrors();
        testSubscriber1.assertValueCount(1);
        testSubscriber1.assertValue(NOTES);
        testSubscriber1.assertCompleted();

        final TestSubscriber<List<Note>> testSubscriber2 =
                TestUtils.subscribeTest(mNotesRepository.getNotes()); // Second call to API

        // Verify that the notes were not longer requested from Service API
        verify(mServiceApi).getAllNotes();

        testSubscriber2.assertNoErrors();
        testSubscriber2.assertValueCount(1);
        testSubscriber2.assertValue(NOTES);
        testSubscriber2.assertCompleted();
    }
}