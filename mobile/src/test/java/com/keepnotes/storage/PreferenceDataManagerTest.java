package com.keepnotes.storage;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.keepnotes.models.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class PreferenceDataManagerTest {
    private PreferenceDataManager manager;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    //private Context context;
    private Gson gson;
    private ArrayList<Note> NOTE_LIST;

    @Before
    public void setUp() throws Exception {
        //context = Mockito.mock(Context.class);
        sharedPrefs = Mockito.mock(SharedPreferences.class);
        editor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(sharedPrefs.edit()).thenReturn(editor);
        Mockito.when(editor.putString(anyString(), anyString())).thenReturn(editor);

        gson = GsonWrapper.getGson();
        manager = new PreferenceDataManager(sharedPrefs);

        NOTE_LIST = new ArrayList<>();
        NOTE_LIST.add(new Note(1, "TITLE 1", "DESCRIPTION 1", false));
    }

    @Test
    public void getNotes() {
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.NOTES, eq(anyString()))).thenReturn(getStringFromNotes(NOTE_LIST));

       assertEquals(NOTE_LIST, manager.getNotes());
    }

    @Test
    public void Notes_GetNotesFromEmptyPref_EmptyList() {
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.NOTES, eq(anyString()))).thenReturn("");

        assertEquals(new ArrayList<Note>(), manager.getNotes());
    }

    @Test
    public void setNotes() throws Exception {
        manager.setNotes(NOTE_LIST);

        checkThatSetNotes(NOTE_LIST);
        checkThatPrefsApplied();
    }

    private void checkThatSetNotes(ArrayList<Note> notes) {
        Mockito.verify(editor).putString(PreferenceDataManager.NOTES, getStringFromNotes(notes));
    }

    @Test
    public void removeNote() throws Exception {
        //Init
        ArrayList<Note> notesAfter = new ArrayList<>(NOTE_LIST);
        Note removeNote = NOTE_LIST.get(0);
        notesAfter.remove(removeNote);
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.NOTES, eq(anyString()))).thenReturn(getStringFromNotes(NOTE_LIST));

        //Action
        manager.removeNote(removeNote);

        //Assert
        checkThatGetNotes();
        checkThatSetNotes(notesAfter);
        checkThatPrefsApplied();
    }

    private void checkThatGetNotes() {
        Mockito.verify(sharedPrefs).getString(PreferenceDataManager.NOTES, eq(anyString()));
    }

    private void checkThatPrefsApplied() {
        Mockito.verify(editor).apply();
    }

    private String getStringFromNotes(ArrayList<Note> notesAfter) {
        return gson.toJson(notesAfter);
    }

    @Test
    public void saveNote() throws Exception {

    }

    @Test
    public void changeNoteStatus() throws Exception {

    }

}