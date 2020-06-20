package com.gaurav.june2020.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Note.class, exportSchema = false, version = 1)
public abstract class NoteDB extends RoomDatabase {

    private static NoteDB instance;
    public abstract DAO noteDao();

    public static synchronized NoteDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDB.class,"note_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
