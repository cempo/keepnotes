package com.keepnotes.storage;

import com.keepnotes.models.Note;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface IDataManager {
    ArrayList<Note> getNotes();

    void setNotes(ArrayList<Note> notes);

    void removeNote(Note note);

    void saveNote(Note note);

    void changeNoteStatus(Note note);
}
