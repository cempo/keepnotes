package com.keepnotes.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.keepnotes.models.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class PreferenceDataManagerTest {
    private PreferenceDataManager manager;
    private SharedPreferences sharedPrefs;
    //private Context context;
    private Gson gson;
    private ArrayList<Object> notes;

    @Before
    public void setUp() throws Exception {
        //context = Mockito.mock(Context.class);
        sharedPrefs = Mockito.mock(SharedPreferences.class);
        //Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPrefs);

        gson = GsonWrapper.getGson();
        manager = new PreferenceDataManager(sharedPrefs);

        notes = new ArrayList<>();
        notes.add(new Note(1, "TITLE 1", "DESCRIPTION 1", false));
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.NOTES, eq(anyString()))).thenReturn(gson.toJson
                (notes));
    }

    @Test
    public void getNotes() throws Exception {
       assertEquals(notes, manager.getNotes());
    }

    @Test
    public void Notes_GetNotesFromEmptyPref_EmptyList() {
        Mockito.when(sharedPrefs.getString(PreferenceDataManager.NOTES, eq(anyString()))).thenReturn("");
        assertEquals(new ArrayList<Note>(), manager.getNotes());
    }

    @Test
    public void setNotes() throws Exception {

    }

    @Test
    public void removeNote() throws Exception {

    }

    @Test
    public void saveNote() throws Exception {

    }

    @Test
    public void changeNoteStatus() throws Exception {

    }

}