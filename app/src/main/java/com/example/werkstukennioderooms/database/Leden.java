package com.example.werkstukennioderooms.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Leden {
    @PrimaryKey(autoGenerate = true)
    private  int lid_id;
    private  String naam;
    private  String email;
    private  String paswoord;

    public int getLid_id() {
        return lid_id;
    }

    public void setLid_id(int lid_id) {
        this.lid_id = lid_id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

}
