package com.example.werkstukennioderooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.werkstukennioderooms.database.AppDatabase;
import com.example.werkstukennioderooms.database.Leden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class RegistreerActivity extends AppCompatActivity {

    private EditText naam;
    private  EditText email;
    private  EditText paswoord;
    private  EditText checkPaswoord;
    private CheckBox nieuwsbrief;

    public static AppDatabase ledenDataBase;


    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreer);

        naam = findViewById(R.id.txtRegistreerNaam);
        email = findViewById(R.id.txtRegistreerEmail);
        paswoord = findViewById(R.id.txtRegistreerPassword);
        checkPaswoord = findViewById(R.id.txtRegistreerCheckPassword);
        nieuwsbrief = findViewById(R.id.chkNieuwsbrief);
        Button registreer = findViewById(R.id.btnRegistreerButton);

        ledenDataBase = Room.databaseBuilder(this, AppDatabase.class,"Leden").addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();

        registreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistreerLid();
            }
        });
    }

    private void RegistreerLid() {
        String rNaam = naam.getText().toString();
        String rEmail = email.getText().toString();
        String rPaswoord = paswoord.getText().toString();
        String rCheckPaswoord = checkPaswoord.getText().toString();
        boolean rNieuwsbrief = nieuwsbrief.isChecked();


        if (!rNaam.isEmpty() && !rEmail.isEmpty() && !rPaswoord.isEmpty() && rPaswoord.equals(rCheckPaswoord) && rNieuwsbrief) {
            Leden leden = new Leden();
            leden.setNaam(rNaam);
            leden.setEmail(rEmail);
            leden.setPaswoord(rPaswoord);

            ledenDataBase.ledenDao().insertLeden(leden);
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Vul alle velden in en controlleer of je wachtwoord 2x juist is", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
