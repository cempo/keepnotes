package com.keepnotes.viewmodels;

import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;

import java.util.ArrayList;

public class MainViewModel {
    private IDataManager manager;

    public MainViewModel(IDataManager manager) {
        this.manager = manager;
    }

    public ArrayList<Note> getNotes() {
        return manager.getNotes();
    }
}
