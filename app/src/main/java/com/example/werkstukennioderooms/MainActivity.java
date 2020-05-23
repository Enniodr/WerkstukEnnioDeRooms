package com.example.werkstukennioderooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.werkstukennioderooms.database.AppDatabase;
import com.example.werkstukennioderooms.database.Dranken;
import com.example.werkstukennioderooms.database.Leden;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private EditText txtNaam;
    private EditText txtPaswoord;

    public static AppDatabase ledenDB;
    public static AppDatabase drankenDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNaam = findViewById(R.id.txtNaam);
        txtPaswoord = findViewById(R.id.txtPaswoord);

        ledenDB = Room.databaseBuilder(this, AppDatabase.class,"Leden").allowMainThreadQueries().build();
        drankenDB = Room.databaseBuilder(this, AppDatabase.class,"Dranken").allowMainThreadQueries().build();

        //Standaardgegevens nog inladen

        final Button login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });



    }

    private void logIn() {
        String naamString = txtNaam.getText().toString();
        String paswoordString = txtPaswoord.getText().toString();

        List<Leden> leden = ledenDB.ledenDao().getLeden();
        for (Leden lid : leden) {
            String lidNaam = lid.getNaam();
            String lidPaswoord = lid.getPaswoord();
            if (!lidNaam.equals(naamString) && !lidPaswoord.equals(paswoordString)) {
                Toast toast = Toast.makeText(this, "Foute combinatie. Probeer opnieuw.", Toast.LENGTH_SHORT);
                toast.show();

            } else {
                //// HIER NAAR MENU ////
                Intent intent = new Intent(this, MenuActivity.class);
                intent.putExtra("lidId", lid.getLid_id());
                Toast toast = Toast.makeText(this, "Goede combinatie", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(intent);
            }
        }
    }

    public void onRegistreer(View view) {

        Intent intent = new Intent(this, RegistreerActivity.class);
        startActivity(intent);
    }




    // Standaardgegevens inladen
    private void standaardGegevensInDatabase(){
        List<Leden> leden = ledenDB.ledenDao().getLeden();

        if (leden.size() <= 0){
            Leden lid = new Leden();
            lid.setNaam("Admin");
            lid.setEmail("Admin@admin.be");
            lid.setPaswoord("admin");
            ledenDB.ledenDao().insertLeden(lid);
        }

        List<Dranken> dranken = drankenDB.drankenDao().getDranken();

        if (dranken.size() <= 0){
            Dranken drank = new Dranken();
            drank.setFoto(R.drawable.fantalogo);
            drank.setDrankNaam("Fanta");
            drank.setBeschrijving("Een drankje van de Coca Cola groep");
            drank.setAlcoholpercentage(0);
            drankenDB.drankenDao().insertDrank(drank);
        }




    }
}
