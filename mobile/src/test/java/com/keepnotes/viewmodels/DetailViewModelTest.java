package com.keepnotes.viewmodels;


import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;


public class DetailViewModelTest {
    @Mock
    private IDataManager manager;
    private Note note;
    private DetailViewModel detailViewModel;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        note = new Note();
        note.setTitle("The Note");
        note.setDescription("D");

        detailViewModel = new DetailViewModel(note, manager);
    }

    @Test
    public void Note_GetNote_HasNote() {
        assertThat(detailViewModel.getNote(), is(note));
    }

    @Test
    public void NoteTitle_GetTitle_HasNoteTitle() {
        String title = detailViewModel.getTitle();
        assertThat(title, is(note.getTitle()));
    }

    @Test
    public void Note_DeleteNote_DeletedNoteFromStorage() {
        detailViewModel.removeNote();
        Mockito.verify(manager).removeNote(note);
    }

    @Test
    public void Note_SaveNote_SavedNoteToStorage() {
        detailViewModel.saveNote();
        Mockito.verify(manager).saveNote(note);
    }

    @Test
    public void Note_DoneNote_NoteBecameDone() {
        detailViewModel.changeStatus(note.isDone());
        Mockito.verify(manager).changeNoteStatus(note);
    }
}