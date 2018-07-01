package com.enigmasoftltd.android.prod.journalapp.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.enigmasoftltd.android.prod.journalapp.data.DateConverter;
import com.enigmasoftltd.android.prod.journalapp.data.local.db.dao.PostDao;
import com.enigmasoftltd.android.prod.journalapp.data.model.db.Post;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase{

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "journalentries";
    private static AppDatabase sInstance;


    public static AppDatabase getsInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new Database Instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database Instance");
        return sInstance;
    }

    public abstract PostDao postDao();
}
