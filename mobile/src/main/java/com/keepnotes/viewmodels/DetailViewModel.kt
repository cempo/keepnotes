package com.keepnotes.viewmodels

import com.keepnotes.models.Note
import com.keepnotes.storage.IDataManager

class DetailViewModel(val note: Note, private val manager: IDataManager) {

    val title: String
        get() = note.title

    fun removeNote() {
        manager.removeNote(note)
    }

    fun saveNote() {
        manager.saveNote(note)
    }

    fun changeStatus(done: Boolean) {
        manager.changeNoteStatus(note)
    }
}
