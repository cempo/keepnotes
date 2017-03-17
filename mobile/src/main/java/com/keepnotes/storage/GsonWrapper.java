package com.keepnotes.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonWrapper {
    private static Gson gson;

    synchronized public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }
}
