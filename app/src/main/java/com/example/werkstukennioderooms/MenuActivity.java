package com.example.werkstukennioderooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onGaNaarInfo(View view) {
     //   Intent intent = new Intent(this, InfoActivity.class);
     //   startActivity(intent);
    }

    public void onDranken(View view) {
      //  Intent intent = new Intent(this, LijstVanEvenementen.class);
      //  startActivity(intent);
    }

    public void onAfmelden(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}