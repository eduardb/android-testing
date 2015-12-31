package com.example.android.testing.notes.base;

import android.support.v4.app.Fragment;

import com.example.android.testing.notes.NotesApp;

/**
 * Created by Eduard on 31.12.2015.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotesApp.get(getContext()).refWatcher().watch(this);
    }
}
