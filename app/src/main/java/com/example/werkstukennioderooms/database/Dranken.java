package com.example.werkstukennioderooms.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dranken {
    @PrimaryKey(autoGenerate = true)
    private int drank_id;
    private int foto;
    private String drankNaam;
    private String beschrijving;
    private int alcoholpercentage;

    public int getDrank_id() {
        return drank_id;
    }

    public void setDrank_id(int drank_id) {
        this.drank_id = drank_id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getDrankNaam() {
        return drankNaam;
    }

    public void setDrankNaam(String drankNaam) {
        this.drankNaam = drankNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getAlcoholpercentage() {
        return alcoholpercentage;
    }

    public void setAlcoholpercentage(int alcoholpercentage) {
        this.alcoholpercentage = alcoholpercentage;
    }
}
