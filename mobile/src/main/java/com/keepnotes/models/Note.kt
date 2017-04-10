package com.keepnotes.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.reflect.TypeToken

import java.lang.reflect.Type

class Note : Parcelable {
    var id: Long = 0
    var title: String = ""
    var description: String? = null
    var isDone: Boolean = false

    constructor() {}

    constructor(id: Long, title: String, description: String, isDone: Boolean) {
        this.id = id
        this.title = title
        this.description = description
        this.isDone = isDone
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.id)
        dest.writeString(this.title)
        dest.writeString(this.description)
        dest.writeByte(if (isDone) 1.toByte() else 0.toByte())
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readLong()
        this.title = `in`.readString()
        this.description = `in`.readString()
        this.isDone = `in`.readByte().toInt() != 0
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val note = o as Note?

        return id == note!!.id

    }

    override fun hashCode(): Int {
        return (id xor id.ushr(32)).toInt()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Note> = object : Parcelable.Creator<Note> {
            override fun createFromParcel(source: Parcel): Note {
                return Note(source)
            }

            override fun newArray(size: Int): Array<Note?> {
                return arrayOfNulls(size)
            }
        }

        val listTokenType: Type
            get() = object : TypeToken<List<Note>>() {

            }.type
    }
}
