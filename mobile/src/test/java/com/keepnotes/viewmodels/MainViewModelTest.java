package com.keepnotes.viewmodels;

import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainViewModelTest {
    private IDataManager manager;
    private MainViewModel mainViewModel;
    private ArrayList<Note> notes;

    public MainViewModelTest() {
    }

    @Before
    public void setUp() {
        manager = Mockito.mock(IDataManager.class);
        mainViewModel = new MainViewModel(manager);

        notes = new ArrayList<>();
        notes.add(new Note(1, "TITLE 1", "DESCRIPTION 1", false));

        Mockito.when(manager.getNotes()).thenReturn(notes);
    }

    @Test
    public void NoteList_GetNotes_GetNotesFromModel() {
        ArrayList<Note> notes = manager.getNotes();
        assertEquals(notes, mainViewModel.getNotes());
    }

}