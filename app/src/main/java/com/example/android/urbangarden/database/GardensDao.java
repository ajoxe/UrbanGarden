package com.example.android.urbangarden.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.urbangarden.model.Garden;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by amirahoxendine on 3/3/18.
 */
@Dao
public interface GardensDao {
    @Query("SELECT * FROM gardens")
    List<Garden> getAll();

    @Query("UPDATE gardens SET status = 'saved' WHERE propid = :id")
    void saveGarden(String id);

    @Insert(onConflict = REPLACE)
    void insertGarden(Garden garden);

    @Insert(onConflict = REPLACE)
    void insertAllGardens(List<Garden> garden);

    @Query("SELECT * FROM gardens where propid LIKE  :id")
    Garden findGardenByID(String id);

    @Query("SELECT * FROM gardens where status LIKE  'saved'")
    List<Garden> getSaved();

    @Delete
    void deleteGarden(Garden garden);

    @Query("SELECT COUNT(*) from gardens")
    int countGardens();
}
