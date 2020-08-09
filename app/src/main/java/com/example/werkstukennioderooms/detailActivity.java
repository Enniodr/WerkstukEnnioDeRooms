package com.example.werkstukennioderooms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.werkstukennioderooms.database.AppDatabase;
import com.example.werkstukennioderooms.database.Dranken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class detailActivity extends AppCompatActivity {

    TextView drankNaam;
    TextView drankBeschrijving;
    TextView drankPercentage;
    ImageView drankFoto;


    public static AppDatabase drankenDataBase;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent vorigeActivity = getIntent();
        final int drank_id = vorigeActivity.getIntExtra("drank_id",0);
        final int lid_id = vorigeActivity.getIntExtra("lid_id",0);
        Log.d("lid_id", String.valueOf(lid_id));

        drankNaam = findViewById(R.id.txtDetailNaam);
        drankBeschrijving = findViewById(R.id.txtDetailBeschrijving);
        drankPercentage = findViewById(R.id.txtDetailPercentage);
        drankFoto = findViewById(R.id.imgDetail);

        drankenDataBase = Room.databaseBuilder(this,AppDatabase.class,"Dranken").addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();

        Dranken detailDrank = drankenDataBase.drankenDao().getDranken(drank_id+1);

        drankNaam.setText(detailDrank.getDrankNaam());
        drankBeschrijving.setText(detailDrank.getBeschrijving());
        drankPercentage.setText(detailDrank.getAlcoholpercentage());
        drankFoto.setImageResource(detailDrank.getFoto());



    }
}
