package com.example.werkstukennioderooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.werkstukennioderooms.database.AppDatabase;
import com.example.werkstukennioderooms.database.Dranken;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class DrankenlijstActivity extends AppCompatActivity {

    public static AppDatabase drankenDataBase;

    ListView listDranken;
    EditText search;
    customAdapter customAdapter;

    List<Dranken> getAlleDranken;
    List<Dranken> alleDranken;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drankenlijst);

        drankenDataBase = Room.databaseBuilder(this, AppDatabase.class,"Dranken").allowMainThreadQueries().build();
        drankThread runnable = new drankThread(drankenDataBase);
        new Thread(runnable).start();
        customAdapter = new customAdapter();


        //Als je klikt op 1 list item word je naar een ander scherm gebracht
        listDranken = findViewById(R.id.lstDranken);

        listDranken.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), detailActivity.class);
                intent.putExtra("drank_id",position);
                startActivity(intent);
            }
        });


        //Filteren in de zoekbalk
        search = findViewById(R.id.txtSearch);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    //Filter functie
    private void filter(String text){
        ArrayList<Dranken> gefilterdeLijst = new ArrayList<Dranken>();
        getAlleDranken = alleDranken;

        for (Dranken drank: getAlleDranken){
            if (drank.getDrankNaam().toLowerCase().contains(text.toLowerCase())){
                gefilterdeLijst.add(drank);
            }
        }
        listDranken.setAdapter(customAdapter);
        customAdapter.filterLijst(gefilterdeLijst);

    }

    class customAdapter extends BaseAdapter{

        @Override
        public int getCount(){
            return getAlleDranken.size();
        }

        void filterLijst(List<Dranken> filterLijst){
            getAlleDranken = filterLijst;
            notifyDataSetChanged();
            Log.d("filter", String.valueOf((getAlleDranken.size())));
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.single_listview_drank, null);
            TextView title = convertView.findViewById(R.id.lstTitle);

            title.setText(getAlleDranken.get(position).getDrankNaam());

            return convertView;

        }

    }




    //Data ophalen en in de listview steken
    class drankThread implements Runnable{
        AppDatabase drankenDataBase;

        drankThread(AppDatabase database){
            this.drankenDataBase = database;
        }


        @Override
        public void run() {
            getAlleDranken = drankenDataBase.drankenDao().getDranken();
            alleDranken = drankenDataBase.drankenDao().getDranken();

            ListView listDranken = findViewById(R.id.lstDranken);
            listDranken.setAdapter(customAdapter);
        }

    }

}
