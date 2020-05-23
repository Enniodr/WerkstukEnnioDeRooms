package com.example.werkstukennioderooms.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Leden.class, Dranken.class}, version = 3, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LedenDao ledenDao();

    public abstract DrankenDao drankenDao();
}
