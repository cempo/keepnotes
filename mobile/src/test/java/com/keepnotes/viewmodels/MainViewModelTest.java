package com.keepnotes.viewmodels;

import android.app.Application;
import com.keepnotes.App;
import com.keepnotes.BuildConfig;
import com.keepnotes.models.Note;
import com.keepnotes.storage.IDataManager;
import com.keepnotes.storage.PreferenceDataManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainViewModelTest {

    private Application application;
    private IDataManager manager;

    public MainViewModelTest() {
    }

    @Before
    public void setUp() {
        application = RuntimeEnvironment.application;
        manager = new PreferenceDataManager(application);

    }

    @Test
    public void NoteList_GetNotes_GetNotesFromModel() {
        ArrayList<Note> notes = manager.getNotes();
        assertEquals(notes, new MainViewModel().getNotes());
    }

}