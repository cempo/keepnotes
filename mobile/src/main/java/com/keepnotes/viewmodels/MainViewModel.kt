package com.keepnotes.viewmodels

import com.keepnotes.models.Note
import com.keepnotes.storage.IDataManager

import java.util.ArrayList

class MainViewModel(private val manager: IDataManager) {

    val notes: ArrayList<Note>
        get() = manager.getNotes()
}
