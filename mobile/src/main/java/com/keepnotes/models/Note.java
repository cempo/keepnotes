package com.keepnotes.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Note implements Parcelable {
    public long id;
    public String title;
    public String description;
    public boolean isDone;

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeByte(isDone ? (byte) 1 : (byte) 0);
    }

    protected Note(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        this.isDone = in.readByte() != 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public static Type getListTokenType() {
        return new TypeToken<List<Note>>() {
        }.getType();
    }
}