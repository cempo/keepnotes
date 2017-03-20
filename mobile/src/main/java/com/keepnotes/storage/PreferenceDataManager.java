package com.keepnotes.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.keepnotes.App;
import com.keepnotes.models.Note;

import java.util.ArrayList;


public class PreferenceDataManager implements IDataManager {
    public static final String NOTES = "notes";

    private final SharedPreferences manager;

    public PreferenceDataManager() {
        manager = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public PreferenceDataManager(Context context) {
        manager = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        String placesJson = manager.getString(NOTES, "");
        if (!TextUtils.isEmpty(placesJson)) {
            notes = GsonWrapper.getGson().fromJson(placesJson, Note.getListTokenType());
        }
        return notes;
    }

    @Override
    public void setNotes(ArrayList<Note> notes) {
        manager.edit().putString(NOTES, GsonWrapper.getGson().toJson(notes)).apply();
    }

    @Override
    public void removeNote() {
        // TODO
    }
}
