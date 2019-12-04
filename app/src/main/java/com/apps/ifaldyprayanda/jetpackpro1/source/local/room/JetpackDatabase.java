package com.apps.ifaldyprayanda.jetpackpro1.source.local.room;

import android.content.Context;

import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.MovieEntity;
import com.apps.ifaldyprayanda.jetpackpro1.source.local.entity.TvShowEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class, TvShowEntity.class},
            version = 1, exportSchema = false)

// for build database in app
public abstract class JetpackDatabase extends RoomDatabase {

    public static JetpackDatabase INSTANCE;

    public abstract JetpackDao jetpackDao();

    private static final Object sLock = new Object();

    public static  JetpackDatabase getInstance(final Context context)
    {
        synchronized (sLock)
        {
            if (INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        JetpackDatabase.class, "Jetpack.db")
                .build();
            }
        }
        return  INSTANCE;
    }
}
