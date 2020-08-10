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

public class DetailActivity extends AppCompatActivity {

    TextView drankNaam;
    TextView drankBeschrijving;
    TextView drankPercentage;
    ImageView drankFoto;


    public static AppDatabase drankenDataBase;

    //https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        drankenDataBase = Room.databaseBuilder(this,AppDatabase.class,"Dranken").addMigrations(MIGRATION_1_2).allowMainThreadQueries().build();

        Intent vorigeActivity = getIntent();
        final int drank_id = vorigeActivity.getIntExtra("drank_id",0);
        //final int lid_id = vorigeActivity.getIntExtra("lid_id",0);
        Log.d("drank_id", String.valueOf(drank_id));

        drankNaam = findViewById(R.id.txtDetailNaam);
        drankBeschrijving = findViewById(R.id.txtDetailBeschrijving);
        drankPercentage = findViewById(R.id.txtDetailPercentage);
        drankFoto = findViewById(R.id.imgDetail);



        Dranken detailDrank = drankenDataBase.drankenDao().getDranken((drank_id+1));

        drankNaam.setText(detailDrank.getDrankNaam());
        drankBeschrijving.setText(detailDrank.getBeschrijving());
        drankPercentage.setText(detailDrank.getAlcoholpercentage());
        drankFoto.setImageResource(detailDrank.getFoto());



    }
}
