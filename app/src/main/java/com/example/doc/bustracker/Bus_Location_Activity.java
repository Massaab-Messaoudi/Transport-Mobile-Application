package com.example.doc.bustracker;


import android.content.DialogInterface;
import android.content.Intent;
import com.example.doc.bustracker.Mysql3.Downloader3;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.doc.bustracker.Mysql2.Downloader1;
import com.example.doc.bustracker.Mysql.Downloader;
import java.util.ArrayList;
public class Bus_Location_Activity extends AppCompatActivity {
    public boolean data_downloaded=false;
    public static boolean connection=false;
    public static double[] station_lat;
    public static double[] station_log;
    TextView tes;
    public static int[] list_size;
    public static int[] nb_bus;
    public static String[] list_mat;
    //public static double[] list_lat;
    //public static double[] list_log;
    boolean location_selected = false;
    ArrayList<String> bus = new ArrayList<>();
    Spinner sp_wilaya;
    Spinner sp_bus;
    private static int bus_id = 1;
    final static String urlAddress2 = "http://192.168.1.202/selection/Constants2.php";
    final static String url = "http://192.168.1.202/select_station/select_station.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__location_);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //calcul the number of station for each ligne // 15= number of ligne
        list_size=new int[15];
        new Downloader1(this, url, bus_id,1,"bus_location").execute();
        //calcul the number of bus for each ligne
        nb_bus=new int[15];
        new Downloader1(this, urlAddress2, bus_id,0,"bus_location").execute();
        ArrayList<String> wilaya = new ArrayList<>();
        wilaya.add("Batna");
        wilaya.add("Constantine");
        wilaya.add("Sétif");
        wilaya.add("Alger");
        wilaya.add("Biskra");
        ArrayAdapter<String> table_wilaya;
        table_wilaya = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wilaya);
        sp_wilaya = (Spinner) findViewById(R.id.spinner_wilaya3);
        sp_wilaya.setAdapter(table_wilaya);
        table_wilaya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tes=(TextView)findViewById(R.id.tvplan);

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
        sp_bus = (Spinner) findViewById(R.id.spinner_bus2);
        sp_bus.setAdapter(table_bus);
        table_bus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sp_bus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        bus_id = 1;
                        data_downloaded=false;
                        break;
                    case 1:
                        bus_id = 2;
                        data_downloaded=false;
                        break;
                    case 2:
                        bus_id = 3;
                        data_downloaded=false;
                        break;
                    case 3:
                        bus_id = 4;
                        data_downloaded=false;
                        break;
                    case 4:
                        bus_id = 5;
                        data_downloaded=false;
                        break;
                    case 5:
                        bus_id = 6;
                        data_downloaded=false;
                        break;
                    case 6:   //Hai Bouakal - Centre Ville
                        bus_id=7;
                        data_downloaded=false;
                        break;
                    case 7:// Hai 1200 logement - Arar
                        bus_id=8;
                        data_downloaded=false;
                        break;
                    case 8://Hai Lombarkia - Hai Riyad 02
                        bus_id=9;
                        data_downloaded=false;
                        break;
                    case 9://Hai Park Avourag - Hamla 3
                        bus_id=10;
                        data_downloaded=false;
                        break;
                    case 10://Hai Bouzourane - Hamla 3
                        bus_id=11;
                        data_downloaded=false;
                        break;
                    case 11: //le nord station sauvage  - Hai Tamchit
                        bus_id=12;
                        data_downloaded=false;
                        break;
                    case 12: //Hai Awlad bchina - Hai Salsabil
                        bus_id=13;
                        data_downloaded=false;
                        break;
                    case 13: //Batna - Tazoult
                        bus_id=14;
                        data_downloaded=false;
                        break;
                    case 14: //Hai Bouzourane - Hamla 3
                        bus_id=15;
                        data_downloaded=false;
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

    public void onButtonClickedFindLocation(View view) {
        if(connection==true) {
            AsyncTask<String, String, String> demotask = new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Done";
                }

                @Override
                protected void onPostExecute(String s) {
                    if (s.equals("Done")) {
                        //pd.dismiss();
                        if (connection == true) {//il ya une connection avec le server
                            Toast.makeText(Bus_Location_Activity.this,"Data Downloaded Successfully ",Toast.LENGTH_SHORT).show();
                            data_downloaded = true;
                        } else {
                            Toast.makeText(Bus_Location_Activity.this, "Connection failed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            };
            demotask.execute();
            station_lat = new double[list_size[bus_id - 1]];
            station_log = new double[list_size[bus_id - 1]];
            new Downloader3(this, url, bus_id, "bus_location").execute();
           // list_lat = new double[nb_bus[bus_id - 1]];
            //list_log = new double[nb_bus[bus_id - 1]];
            list_mat = new String[nb_bus[bus_id - 1]];
            new Downloader(this, urlAddress2, bus_id).execute();
            location_selected = true;
        }
        else
            {
                Toast.makeText(Bus_Location_Activity.this, "Connection down \n please try again later ", Toast.LENGTH_SHORT).show();
            }
    }
   /* public void onButtonClickedShowLocation(View view) {
        if(data_downloaded==true){
        Intent inten=new Intent(this,Trucking_map.class);
        inten.putExtra("station",false);
        inten.putExtra("bus",true);
        inten.putExtra("bus_id",bus_id);
        startActivity(inten);
       // data_downloaded=false;
        }
        else
            {
                Toast.makeText(this,"bus location is unknown ",Toast.LENGTH_SHORT).show();
            }
    }*/
    public void showmap(View v) {
        if(data_downloaded==true){
        boolean show_bus=false;
        //Intent inten=new Intent(this,Trucking_map.class);
            Intent inten=new Intent(this,MapsMQTT.class);
        if (location_selected==true){
            show_bus=true;
        }
        inten.putExtra("bus_id",bus_id);
        inten.putExtra("bus",show_bus);
        inten.putExtra("station",true);
        startActivity(inten);
          //  data_downloaded=false;
        }
        else
        {
            Toast.makeText(this,"bus location is unknown ",Toast.LENGTH_SHORT).show();
        }
    }

    public void showinfowindow(View v) {



        AlertDialog.Builder window=new AlertDialog.Builder(this);
        window.setMessage("Buses Locations have detected successfully \n "+"Now you can see Buses locations in map")
                .setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        }).show();
    }



}