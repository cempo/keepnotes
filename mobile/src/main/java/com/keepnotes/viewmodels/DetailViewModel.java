package com.keepnotes.viewmodels;

import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;

public class DetailViewModel {
    private Note note;
    private IDataManager manager;

    public DetailViewModel(Note note, IDataManager manager) {
        this.note = note;
        this.manager = manager;
    }

    public Note getNote() {
        return note;
    }

    public String getTitle() {
        return note.getTitle();
    }

    public void removeNote() {
        manager.removeNote(note);
    }

    public void saveNote() {
        manager.saveNote(note);
    }

    public void changeStatus(boolean done) {
        manager.changeNoteStatus(note);
    }
}
