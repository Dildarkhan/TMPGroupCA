package com.dildarkhan.tmpgroupca.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationUpdatesDao {
    @Query("SELECT * FROM locationupdates")
    List<LocationUpdates> getAllLocationUpdates();


    /*//"SELECT * FROM locationupdates WHERE pid = :pid LIMIT 1"
    @Query("SELECT * FROM locationupdates WHERE pid =:pid LIMIT 1")
    LocationUpdates getLocationUpdatesExists(int pid);*/

    @Insert
    void insert(LocationUpdates locationUpdates);

    @Delete
    void delete(LocationUpdates locationUpdates);

    @Update
    void update(LocationUpdates locationUpdates);

    @Query("DELETE FROM LocationUpdates")
    void nukeTable();
}
