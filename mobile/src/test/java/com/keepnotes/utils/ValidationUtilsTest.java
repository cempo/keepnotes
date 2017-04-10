package com.keepnotes.utils;

import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class ValidationUtilsTest {
    private Note note;

    @Before
    public void setUp() throws Exception {
        note = new Note();
        note.setTitle("The Note");
        note.setDescription("D");
    }

    @Test
    public void NoteTitle_ValidateLengthNoteTitle_ValidLengthNoteTitle() {
        String title = note.getTitle();
        assertTrue(ValidationUtils.INSTANCE.isValidTitleLength(title));
    }

    @Test
    public void NoteTitle_ValidateSymbolsNoteTitle_ValidSymbolsNoteTitle() {
        String title = note.getTitle();
        assertTrue(ValidationUtils.INSTANCE.isValidTitleSymbols(title));
    }

    @Test
    public void NoteDescription_ValidateLengthNoteDescription_ValidLengthNoteDescription() {
        String description = note.getDescription();
        assertTrue(ValidationUtils.INSTANCE.isValidDescriptionLength(description));
    }
}