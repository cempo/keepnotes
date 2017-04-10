package com.keepnotes.storage

import com.keepnotes.models.Note
import java.util.*

interface IDataManager {
    fun getNotes(): ArrayList<Note>

    fun setNotes(notes: ArrayList<Note>)

    fun removeNote(note: Note)

    fun saveNote(note: Note)

    fun changeNoteStatus(note: Note)
}
