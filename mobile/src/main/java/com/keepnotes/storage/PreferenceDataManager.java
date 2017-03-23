package com.keepnotes.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.keepnotes.App;
import com.keepnotes.models.Note;

import java.util.ArrayList;


public class PreferenceDataManager implements IDataManager {
    public static final String NOTES = "notes";

    private SharedPreferences manager;

    /*public PreferenceDataManager() {
        this(PreferenceManager.getDefaultSharedPreferences(App.getInstance()));
    }*/

    public PreferenceDataManager(SharedPreferences sharedPreferences) {
        manager = sharedPreferences;
    }

    @Override
    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        String placesJson = manager.getString(NOTES, "");
        if (!placesJson.equals("")) {
            notes = GsonWrapper.getGson().fromJson(placesJson, Note.getListTokenType());
        }
        return notes;
    }

    @Override
    public void setNotes(ArrayList<Note> notes) {
        manager.edit().putString(NOTES, GsonWrapper.getGson().toJson(notes)).apply();
    }

    @Override
    public void removeNote(Note note) {
        ArrayList<Note> notes = getNotes();
        notes.remove(note);
        setNotes(notes);
    }

    @Override
    public void saveNote(Note note) {
        ArrayList<Note> notes = getNotes();
        int index = notes.indexOf(note);
        if (index != -1) {
            notes.set(index, note);
        } else {
            notes.add(note);
        }
        setNotes(notes);
    }

    @Override
    public void changeNoteStatus(Note note) {
        ArrayList<Note> notes = getNotes();
        int index = notes.indexOf(note);
        if (index != -1) {
            notes.set(index, note);
            setNotes(notes);
        }

    }
}
