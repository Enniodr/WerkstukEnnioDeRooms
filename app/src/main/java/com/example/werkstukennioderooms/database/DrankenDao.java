package com.example.werkstukennioderooms.database;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DrankenDao {

    @Insert
    public void insertDrank(Dranken dranken);

    @Query("select * from Dranken")
    public List<Dranken> getDranken();

    @Query("select * from Dranken where drank_id = :drank_id")
    public Dranken getDranken(int drank_id);

}
