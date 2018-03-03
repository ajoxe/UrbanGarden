package com.example.android.urbangarden.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.urbangarden.model.Garden;

/**
 * Created by amirahoxendine on 3/3/18.
 */
@Database(entities = {Garden.class}, version = 1)
public abstract class GardensDatabase extends RoomDatabase{
    private static GardensDatabase INSTANCE;



    public static GardensDatabase getGardensDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), GardensDatabase.class, "gardens")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
