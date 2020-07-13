package com.example.doc.bustracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
public class TransoprtMapActivity extends AppCompatActivity {
    ArrayList<String> bus = new ArrayList<>();
    Spinner sp_wilaya;
    Spinner sp_bus;
    private static int bus_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transoprt_map);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> wilaya = new ArrayList<>();
        wilaya.add("Batna");
        wilaya.add("Constantine");
        wilaya.add("Sétif");
        wilaya.add("Alger");
        wilaya.add("Biskra");
        ArrayAdapter<String> table_wilaya;
        table_wilaya = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wilaya);
        sp_wilaya = (Spinner)findViewById(R.id.transport_wilaya);
        sp_wilaya.setAdapter(table_wilaya);
        table_wilaya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set up list of bus

        bus.add("01");
        bus.add("02");
        bus.add("03");
        bus.add("04");
        bus.add("05");
        bus.add("06");
        bus.add("Hai Bouakal - Centre Ville");
        bus.add("Hai 1200 logement - Arar ");
        bus.add("Hai Lombarkia - Hai Riyad 02 ");
        bus.add("Hai Park Avourag - Hamla 3 ");
        bus.add("Hai Bouzourane - Hamla 3 ");
        bus.add("Batna - Tazoult ");
        ArrayAdapter<String> table_bus;
        table_bus = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bus);
        sp_bus = (Spinner)findViewById(R.id.transport_bus);
        sp_bus.setAdapter(table_bus);
        table_bus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sp_bus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        bus_id = 1;
                        break;
                    case 1:
                        bus_id = 2;
                        break;
                    case 2:
                        bus_id = 3;
                        break;
                    case 3:
                        bus_id = 4;
                        break;
                    case 4:
                        bus_id = 5;
                        break;
                    case 5:
                        bus_id = 6;
                        break;
                    case 6:   //Hai Bouakal - Centre Ville
                        bus_id=7;
                        break;
                    case 7:// Hai 1200 logement - Arar
                        bus_id=8;
                        break;
                    case 8://Hai Lombarkia - Hai Riyad 02
                        bus_id=9;
                        break;
                    case 9://Hai Park Avourag - Hamla 3
                        bus_id=10;
                        break;
                    case 10://Hai Bouzourane - Hamla 3
                        bus_id=11;
                        break;
                    case 11: //le nord station sauvage  - Hai Tamchit
                        bus_id=12;
                        break;
                    case 12: //Hai Awlad bchina - Hai Salsabil
                        bus_id=13;
                        break;
                    case 13: //Batna - Tazoult
                        bus_id=14;
                        break;
                    case 14: //Hai Bouzourane - Hamla 3
                        bus_id=15;
                        break;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void show_bus_ligne(View view) {

        Intent inte=new Intent(this,Transort_Status_MapsActivity.class);
        inte.putExtra("busid",bus_id);
        startActivity(inte);
    }

}
