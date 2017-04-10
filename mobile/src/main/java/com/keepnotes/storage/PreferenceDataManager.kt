package com.keepnotes.storage

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.keepnotes.App
import com.keepnotes.models.Note
import java.util.*


class PreferenceDataManager : IDataManager {
    companion object {
        val NOTES = "notes"
    }

    var manager: SharedPreferences

    constructor() {
        manager = PreferenceManager.getDefaultSharedPreferences(App.getInstance())
    }

    constructor(manager: SharedPreferences) {
        this.manager = manager
    }

    override fun getNotes(): ArrayList<Note> {
        var notes = ArrayList<Note>()
        val placesJson = manager.getString(NOTES, "")
        if (placesJson != "") {
            notes = GsonWrapper.getGson().fromJson<ArrayList<Note>>(placesJson, Note.listTokenType)
        }
        return notes
    }

    override fun setNotes(notes: ArrayList<Note>) {
        manager.edit().putString(NOTES, GsonWrapper.getGson().toJson(notes)).apply()
    }

    override fun removeNote(note: Note) {
        val notes = getNotes()
        notes.remove(note)
        setNotes(notes)
    }

    override fun saveNote(note: Note) {
        val notes = getNotes()
        val index = notes.indexOf(note)
        if (index != -1) {
            notes[index] = note
        } else {
            notes.add(note)
        }
        setNotes(notes)
    }

    override fun changeNoteStatus(note: Note) {
        val notes = getNotes()
        val index = notes.indexOf(note)
        if (index != -1) {
            notes[index] = note
            setNotes(notes)
        }
    }
}
