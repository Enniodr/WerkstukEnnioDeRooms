package com.example.werkstukennioderooms.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LedenDao {
    @Insert
    public void insertLeden(Leden leden);

    @Query("select * from Leden")
    public List<Leden> getLeden();
}
