package com.keepnotes;

import com.keepnotes.storage.IDataManager;
import com.keepnotes.storage.PreferenceDataManager;

public class App extends android.app.Application {


    private static App instance;
    private static IDataManager dataManager;

    public static IDataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new PreferenceDataManager();
        }
        return dataManager;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }
    }
}
