package com.ekcapaper.racingar.kit.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ekcapaper.racingar.kit.room.table.FavoriteEntity;
import com.ekcapaper.racingar.kit.room.table.NotificationEntity;

@Database(entities = {NotificationEntity.class, FavoriteEntity.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DAO getDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDb(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "materialx_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}