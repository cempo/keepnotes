package com.keepnotes.storage;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.keepnotes.models.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class PreferenceDataManagerTest {
    private PreferenceDataManager manager;
    @Mock
    private SharedPreferences sharedPrefs;
    @Mock
    private SharedPreferences.Editor editor;
    //private Context context;
    private Gson gson;
    private ArrayList<Note> NOTE_LIST;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(sharedPrefs.edit()).thenReturn(editor);
        Mockito.when(editor.putString(anyString(), anyString())).thenReturn(editor);

        gson = GsonWrapper.INSTANCE.getGson();
        manager = new PreferenceDataManager(sharedPrefs);

        NOTE_LIST = new ArrayList<>();
        NOTE_LIST.add(new Note(1, "TITLE 1", "DESCRIPTION 1", false));
    }

    @Test
    public void getNotes() {
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        assertEquals(NOTE_LIST, manager.getNotes());
    }

    @Test
    public void Notes_GetNotesFromEmptyPref_EmptyList() {
        mockGetNotes("");

        assertEquals(new ArrayList<Note>(), manager.getNotes());
    }

    @Test
    public void setNotes() throws Exception {
        manager.setNotes(NOTE_LIST);

        checkThatSetNotes(NOTE_LIST);
        checkThatPrefsApplied();
    }

    @Test
    public void removeNote() {
        //Init
        ArrayList<Note> notesAfter = new ArrayList<>(NOTE_LIST);
        Note removeNote = NOTE_LIST.get(0);
        notesAfter.remove(removeNote);
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        //Action
        manager.removeNote(removeNote);

        //Assert
        checkThatGetNotes();
        checkThatSetNotes(notesAfter);
        checkThatPrefsApplied();
    }

    @Test
    public void Note_SaveNote_SavedNote() {
        //Init
        ArrayList<Note> notesAfter = new ArrayList<>(NOTE_LIST);
        Note newNote = new Note(2, "TITLE 2", "DESCRIPTION 2", false);
        notesAfter.add(newNote);
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        //Action
        manager.saveNote(newNote);

        //Assert
        checkThatGetNotes();
        checkThatSetNotes(notesAfter);
        checkThatPrefsApplied();
    }

    @Test
    public void Note_UpdateNote_UpdatedNote() {
        //Init
        ArrayList<Note> notesAfter = new ArrayList<>(NOTE_LIST);
        Note updatedNote = notesAfter.get(0);
        updatedNote.setTitle("TITLE 1 UPDATED");
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        //Action
        manager.saveNote(updatedNote);

        //Assert
        checkThatGetNotes();
        checkThatSetNotes(notesAfter);
        checkThatPrefsApplied();
    }

    @Test
    public void Note_ChangeNoteStatus_ChangedNoteStatus() {
        //Init
        ArrayList<Note> notesAfter = new ArrayList<>(NOTE_LIST);
        Note updatedNote = notesAfter.get(0);
        updatedNote.setDone(true);
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        //Action
        manager.changeNoteStatus(updatedNote);

        //Assert
        checkThatGetNotes();
        checkThatSetNotes(notesAfter);
        checkThatPrefsApplied();
    }

    @Test
    public void Note_ChangeNewNoteStatus_ChangedNoteStatus() {
        //Init
        Note newNote = new Note(3, "TITLE 3", "DESCRIPTION 3", false);
        mockGetNotes(getStringFromNotes(NOTE_LIST));

        //Action
        manager.changeNoteStatus(newNote);

        //Assert
        checkThatGetNotes();
        checkThatNeverSetNotes();
        checkThatPrefsNeverApplied();
    }

    private void mockGetNotes(String stringList) {
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.Companion.getNOTES(), eq(anyString())))
                .thenReturn(stringList);
    }

    private void checkThatSetNotes(ArrayList<Note> notes) {
        Mockito.verify(editor).putString(PreferenceDataManager.Companion.getNOTES(), getStringFromNotes(notes));
    }

    private void checkThatNeverSetNotes() {
        Mockito.verify(editor, Mockito.never()).putString(PreferenceDataManager.Companion.getNOTES(), eq(anyString()));
    }

    private void checkThatGetNotes() {
        Mockito.verify(sharedPrefs).getString(PreferenceDataManager.Companion.getNOTES(), eq(anyString()));
    }

    private void checkThatPrefsApplied() {
        Mockito.verify(editor).apply();
    }

    private void checkThatPrefsNeverApplied() {
        Mockito.verify(editor, Mockito.never()).apply();
    }

    private String getStringFromNotes(ArrayList<Note> notesAfter) {
        return gson.toJson(notesAfter);
    }
}