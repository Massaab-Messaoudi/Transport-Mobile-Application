package com.example.doc.bustracker;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.doc.bustracker.Mysql2.Downloader1;
import com.example.doc.bustracker.Mysql3.Downloader3;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class NearbyStopActivity extends AppCompatActivity {
    public static boolean data_downloaded=false;
    public static boolean connection=false;
    ArrayList<String> bus = new ArrayList<>();
  //  String bus_class = "Public";
  //  Spinner sp_class;
    public static double[] station_lat;
    public static double[] station_log;
    public static int[] list_size;
    Location userloc;
    int bus_id=1;
    Spinner sp_wilaya;
    Spinner sp_bus;
    private FusedLocationProviderClient user;
    double user_lat;
    double user_log;
    boolean user_location=false;
    final static String url = "http://192.168.1.202/select_station/select_station.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stop);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        list_size=new int[15];
        new Downloader1(this, url, bus_id,1,"nearby_stop").execute();
        // set up wilaya spinner
        ArrayList<String> wilaya = new ArrayList<>();
        wilaya.add("Batna");
        wilaya.add("Constantine");
        wilaya.add("Sétif");
        wilaya.add("Alger");
        wilaya.add("Biskra");
       /* ArrayAdapter<String> table_wilaya;
        table_wilaya = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wilaya);
        sp_wilaya = (Spinner)findViewById(R.id.nearby_wilaya);
        sp_wilaya.setAdapter(table_wilaya);
        table_wilaya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set up class spinner
        ArrayList<String> clas = new ArrayList<>();
        clas.add("Public");
        clas.add("Privé");
        ArrayAdapter<String> table_clas;
        table_clas = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clas);
        sp_class = (Spinner) findViewById(R.id.nearby_class);
        sp_class.setAdapter(table_clas);
        table_clas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        bus_class = "Public";
                        newspinner(bus, bus_class);
                        data_downloaded=false;
                        break;
                    case 1:
                        bus_class = "Privé";
                        newspinner(bus, bus_class);
                        data_downloaded=false;
                        break;
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/



        // set up bus spinner
        ArrayList<String> bus = new ArrayList<>();
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
        sp_bus = (Spinner)findViewById(R.id.nearby_bus);
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

        user = LocationServices.getFusedLocationProviderClient(this);
        requestPermission();



        if (ActivityCompat.checkSelfPermission(NearbyStopActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }
        user.getLastLocation().addOnSuccessListener(NearbyStopActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                    userloc=location;
                    user_lat=location.getLatitude();
                    user_log=location.getLongitude();
                    user_location=true;
                }
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

    public void download_stop_location(View v) {
        if(connection==true) {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Download Data");
        pd.setMessage("Please wait ...");
        pd.show();
        AsyncTask<String,String,String> demotask=new AsyncTask<String, String, String>() {
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
                if(s.equals("Done"))
                {
                    pd.dismiss();
                    if(connection==true){//il ya une connection avec le server
                       data_downloaded=true;
                        Toast.makeText(NearbyStopActivity.this,"the nearby stope finded ",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(NearbyStopActivity.this,"Connection failed ",Toast.LENGTH_SHORT).show();
                    }
                }
            }

        };
        demotask.execute();
        station_lat=new double[list_size[bus_id-1]];
        station_log=new double[list_size[bus_id-1]];
        new Downloader3(this, url, bus_id,"nearby_stop").execute();
        }
        else
        {
            Toast.makeText(this,"Connection down \n please try again later ",Toast.LENGTH_SHORT).show();
        }
    }
    public void show_nearby_stop(View v) {
        if(data_downloaded==true) {
            int index = 0;
            if (user_location == true) {
                Location stoploc = new Location("");
                stoploc.setLatitude(station_lat[0]);
                stoploc.setLongitude(station_log[0]);
                float dis = userloc.distanceTo(stoploc);
                float dis_min;
                for (int i = 1; i < station_lat.length; i++) {
                    stoploc.setLatitude(station_lat[i]);
                    stoploc.setLongitude(station_log[i]);
                    dis_min = userloc.distanceTo(stoploc);
                    if (dis_min < dis) {
                        dis = dis_min;
                        index = i;
                    }

                }
                Intent inte = new Intent(this, Nearby_stop_map.class);
                inte.putExtra("user_lat", user_lat);
                inte.putExtra("user_log", user_log);
                inte.putExtra("lat_stop", station_lat[index]);
                inte.putExtra("log_stop", station_log[index]);
                startActivity(inte);

            } else {
                  Toast.makeText(this, "Your GPS is not activated , please turn on you gps and restart the app before you try again", Toast.LENGTH_SHORT).show();
            }
           // data_downloaded=false;
        }else
            {
                Toast.makeText(this, "Stop location is unknown", Toast.LENGTH_SHORT).show();
            }
    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }



  /*  public void newspinner(ArrayList<String> bus, String clas_bus)
    {
        bus.clear();
        Spinner sp=(Spinner)findViewById(R.id.nearby_bus);
        if(clas_bus.equals("Privé")) {
            bus.add("Hai Bouakal - Centre Ville");
            bus.add("Hai 1200 logement - Arar ");
            bus.add("Hai Lombarkia - Hai Riyad 02 ");
            bus.add("Hai Park Avourag - Hamla 3 ");
            bus.add("Hai Bouzourane - Hamla 3 ");
            //bus.add("le nord station sauvage  - Hai Tamchit ");
            // bus.add("Hai Awlad bchina - Hai Salsabil ");
            bus.add("Batna - Tazoult ");
            // bus.add("Hai Bouzourane - Hamla 3 ");
            ArrayAdapter<String> table_bus;
            table_bus = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bus);
            sp.setAdapter(table_bus);
            table_bus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {

                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0:   //Hai Bouakal - Centre Ville
                            bus_id=7;
                            data_downloaded=false;
                            break;
                        case 1:// Hai 1200 logement - Arar
                            bus_id=8;
                            data_downloaded=false;
                            break;
                        case 2://Hai Lombarkia - Hai Riyad 02
                            bus_id=9;
                            data_downloaded=false;
                            break;
                        case 3://Hai Park Avourag - Hamla 3
                            bus_id=10;
                            data_downloaded=false;
                            break;
                        case 4://Hai Bouzourane - Hamla 3
                            bus_id=11;
                            data_downloaded=false;
                            break;
                        case 5: //le nord station sauvage  - Hai Tamchit
                            bus_id=12;
                            data_downloaded=false;
                            break;
                        case 6: //Hai Awlad bchina - Hai Salsabil
                            bus_id=13;
                            data_downloaded=false;
                            break;
                        case 7: //Batna - Tazoult
                            bus_id=14;
                            data_downloaded=false;
                            break;
                        case 8: //Hai Bouzourane - Hamla 3
                            bus_id=15;
                            data_downloaded=false;
                            break;

                    }

                }
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else{
            bus.add("01");
            bus.add("02");
            bus.add("03");
            bus.add("04");
            bus.add("05");
            ArrayAdapter<String> table_bus;
            table_bus = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bus);
            sp.setAdapter(table_bus);
            table_bus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {

                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0:
                            bus_id=1;
                            data_downloaded=false;
                            break;
                        case 1:
                            bus_id=2;
                            data_downloaded=false;
                            break;
                        case 2:
                            bus_id=3;
                            data_downloaded=false;
                            break;
                        case 3:
                            bus_id=4;
                            data_downloaded=false;
                            break;
                        case 4:
                            bus_id=5;
                            data_downloaded=false;
                            break;
                    }

                }
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }*/
}
