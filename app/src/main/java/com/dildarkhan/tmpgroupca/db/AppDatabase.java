package com.dildarkhan.tmpgroupca.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {LocationUpdates.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LocationUpdatesDao locationUpdatesDao();

}