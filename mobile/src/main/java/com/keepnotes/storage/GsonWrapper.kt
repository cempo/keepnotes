package com.keepnotes.storage

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonWrapper {
    private var gson: Gson? = null

    @Synchronized fun getGson(): Gson {
        if (gson == null) {
            gson = GsonBuilder().create()
        }
        return gson ?: throw AssertionError("Set to null by another thread")
    }

}
