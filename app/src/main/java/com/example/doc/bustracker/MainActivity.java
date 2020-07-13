package com.example.doc.bustracker;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.doc.bustracker.Mysql2.Downloader1;
import com.example.doc.bustracker.Mysql3.Downloader3;
import  com.example.doc.bustracker.assist.cite_names;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;



import static android.Manifest.permission.ACCESS_FINE_LOCATION;
public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener {
    public static TextView tv;
    public static int[] list_recom=new int [7];
    public static boolean data_downloaded=false;
    public static boolean connection=false;
    public static double[] station_lat1;
    public static double[] station_log1;
    public static double[] station_lat2;
    public static double[] station_log2;
    public static double[] station_lat3;
    public static double[] station_log3;
    public static double[] station_lat4;
    public static double[] station_log4;
    public static double[] station_lat5;
    public static double[] station_log5;
    public static double[] station_lat6;
    public static double[] station_log6;
    public static double[] station_lat7;
    public static double[] station_log7;
    public static double[] station_lat8;
    public static double[] station_log8;
    public static double[] station_lat9;
    public static double[] station_log9;
    public static double[] station_lat10;
    public static double[] station_log10;
    public static double[] station_lat11;
    public static double[] station_log11;
    public static int[] list_size;
    Location userloc;
    boolean user_location=false;
    private FusedLocationProviderClient user;
    double user_lat;
    double user_log;

    String[] list_carites = new String[32];
    private Spinner list;
    int [] bus_list=new int [7];
    final static String url = "http://192.168.1.202/select_station/select_station.php";
    int bus_id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.home_text);
        list_size=new int[15];
        new Downloader1(this, url, bus_id,1,"main").execute();
        final cite_names la_cité = new cite_names();
        list_carites = la_cité.getListNames();
        ArrayList<String> list_cité = new ArrayList<>();
        for (int i = 0; i < list_carites.length; i++) {
            list_cité.add(list_carites[i]);
        }

        ArrayAdapter<String> table;
        table = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_cité);
        list = (Spinner) findViewById(R.id.home_desdtination_spinner);
        list.setAdapter(table);
        table.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bus_list=la_cité.getnewlist(position);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // select user location

        user = LocationServices.getFusedLocationProviderClient(this);
        requestPermission();
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }
        user.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment frag=null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.transport_status) {
            Intent inte=new Intent(this,TransoprtMapActivity.class);
            startActivity(inte);


        } else if (id == R.id.bus_selection) {

            Intent inte=new Intent(this,Bus_Location_Activity.class);
            startActivity(inte);
        }  else if (id == R.id.nearby_stops) {
            Intent inte=new Intent(this,NearbyStopActivity.class);
            startActivity(inte);

        }
        else if (id == R.id.close) {
            finish();

        }
        if(frag!=null){
            FragmentManager fm=getSupportFragmentManager();

            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.screen_area,frag);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void showbuswindow(View v) {
        String listbus="";

        int n=0;
        StringBuilder s=new StringBuilder();
        if(data_downloaded==true) {
            if (user_location == true) {
                float dis_minimal = nearby_stop(bus_list[0]);
                float distance;
                int index=bus_list[0];
                for (int i = 0; i < bus_list.length; i++) {
                    n=0;
                    if(bus_list[i]!=0) {

                        distance = nearby_stop(bus_list[i]);
                        if (distance <= dis_minimal) {
                            dis_minimal = distance;
                            index = bus_list[i];
                            n=index;
                            list_recom[i]=bus_list[i] ;
                        }

                    }

                }
                for(int i =0;i<list_recom.length;i++)
                {
                    if(list_recom[i]!=0)
                    {
                        if(list_recom[i]>6)
                        {
                            switch (list_recom[i]) {
                                case 7:
                                    listbus = listbus + "Hai Bouakal - Centre Ville \n";
                                    break;
                                case 8:
                                    listbus = listbus +  "Hai 1200 logement - Arar \n ";
                                    break;
                                case 9:
                                    listbus = listbus +  "Hai Lombarkia - Hai Riyad 02 \n";
                                    break;
                                case 10:
                                    listbus = listbus + "Hai Park Avourag - Hamla 3 \n";
                                    break;
                                case 11:
                                    listbus = listbus +  "Hai Bouzourane - Hamla 3 \n";
                                    break;
                                case 12:
                                    listbus = listbus +  "Batna - Tazoult \n";
                                    break;
                            }
                        }else {
                            listbus = listbus + String.valueOf(list_recom[i])+"\n";
                        }
                    }
                }
                AlertDialog.Builder window = new AlertDialog.Builder(this);
                //  if (index <= 6) {
                window.setMessage("To reach your destination you should take this bus \n  " + listbus + " if you want to check bus location go to Menu -> Find bus location")
                        .setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }).show();
              /*  } else {
                    String bus_name = "Hai Bouakal - Centre Ville";
                    switch (index) {
                        case 7:
                            bus_name = "Hai Bouakal - Centre Ville";
                            break;
                        case 8:
                            bus_name = "Hai 1200 logement - Arar ";
                            break;
                        case 9:
                            bus_name = "Hai Lombarkia - Hai Riyad 02";
                            break;
                        case 10:
                            bus_name = "Hai Park Avourag - Hamla 3";
                            break;
                        case 11:
                            bus_name = "Hai Bouzourane - Hamla 3";
                            break;
                        case 12:
                            bus_name = "Batna - Tazoult";
                            break;
                    }
                    window.setMessage("To reach the destination that you selected you should take this Bus ' \n " + bus_name + " '  to check any bus location go to Menu -> Find bus location")
                            .setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
                }*/
                // data_downloaded=false;

            }else
            {
                Toast.makeText(MainActivity.this,"you should to download bus info first ",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getstations(View v) {
        if(connection==true) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Download Data");
            pd.setMessage("Please wait ...");
            pd.show();
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
                        pd.dismiss();
                        if (connection == true) {//il ya une connection avec le server
                            Toast.makeText(MainActivity.this, "Data Downloaded Successfully ", Toast.LENGTH_SHORT).show();
                            data_downloaded = true;
                        } else {
                            Toast.makeText(MainActivity.this, "Connection failed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            };
            demotask.execute();

            for (int i = 1; i <= 11; i++) {

                switch (i) {
                    case 1:
                        station_lat1 = new double[list_size[i - 1]];
                        station_log1 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 2:
                        station_lat2 = new double[list_size[i - 1]];
                        station_log2 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 3:
                        station_lat3 = new double[list_size[i - 1]];
                        station_log3 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 4:
                        station_lat4 = new double[list_size[i - 1]];
                        station_log4 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 5:
                        station_lat5 = new double[list_size[i - 1]];
                        station_log5 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 6:
                        station_lat6 = new double[list_size[i - 1]];
                        station_log6 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 7:
                        station_lat7 = new double[list_size[i - 1]];
                        station_log7 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 8:
                        station_lat8 = new double[list_size[i - 1]];
                        station_log8 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 9:
                        station_lat9 = new double[list_size[i - 1]];
                        station_log9 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 10:
                        station_lat10 = new double[list_size[i - 1]];
                        station_log10 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;
                    case 11:
                        station_lat11 = new double[list_size[i - 1]];
                        station_log11 = new double[list_size[i - 1]];
                        new Downloader3(this, url, i, "main").execute();
                        break;

                }
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "Connection down \n please try again later ", Toast.LENGTH_SHORT).show();
        }


    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    public float nearby_stop(int id) {
        Location stoploc;
        float dis_min=1000000000;
        float dis;
        switch (id) {

            case 1:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat1[0]);
                    stoploc.setLongitude(station_log1[0]);
                    dis_min = userloc.distanceTo(stoploc);
                    for (int i = 0; i < station_log1.length; i++) {
                        stoploc.setLatitude(station_lat1[i]);
                        stoploc.setLongitude(station_log1[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 2:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat2[0]);
                    stoploc.setLongitude(station_log2[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log2.length; i++) {
                        stoploc.setLatitude(station_lat2[i]);
                        stoploc.setLongitude(station_log2[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 3:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat3[0]);
                    stoploc.setLongitude(station_log3[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log3.length; i++) {
                        stoploc.setLatitude(station_lat3[i]);
                        stoploc.setLongitude(station_log3[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 4:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat4[0]);
                    stoploc.setLongitude(station_log4[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log4.length; i++) {
                        stoploc.setLatitude(station_lat4[i]);
                        stoploc.setLongitude(station_log4[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 5:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat5[0]);
                    stoploc.setLongitude(station_log5[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log5.length; i++) {
                        stoploc.setLatitude(station_lat5[i]);
                        stoploc.setLongitude(station_log5[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 6:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat6[0]);
                    stoploc.setLongitude(station_log6[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log6.length; i++) {
                        stoploc.setLatitude(station_lat6[i]);
                        stoploc.setLongitude(station_log6[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 7:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat7[0]);
                    stoploc.setLongitude(station_log7[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log7.length; i++) {
                        stoploc.setLatitude(station_lat7[i]);
                        stoploc.setLongitude(station_log7[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 8:
                stoploc = new Location("");
                try {
                    stoploc.setLatitude(station_lat8[0]);
                    stoploc.setLongitude(station_log8[0]);
                    dis_min = userloc.distanceTo(stoploc);
                    for (int i = 0; i < station_log8.length; i++) {
                        stoploc.setLatitude(station_lat8[i]);
                        stoploc.setLongitude(station_log8[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 9:
                stoploc = new Location("");
                try {
                    stoploc.setLatitude(station_lat9[0]);
                    stoploc.setLongitude(station_log9[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log9.length; i++) {
                        stoploc.setLatitude(station_lat9[i]);
                        stoploc.setLongitude(station_log9[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 10:
                stoploc = new Location("");
                try {
                    stoploc.setLatitude(station_lat10[0]);
                    stoploc.setLongitude(station_log10[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log10.length; i++) {
                        stoploc.setLatitude(station_lat10[i]);
                        stoploc.setLongitude(station_log10[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
            case 11:
                stoploc = new Location("");
                try{
                    stoploc.setLatitude(station_lat11[0]);
                    stoploc.setLongitude(station_log11[0]);
                    dis_min = userloc.distanceTo(stoploc);

                    for (int i = 0; i < station_log11.length; i++) {
                        stoploc.setLatitude(station_lat11[i]);
                        stoploc.setLongitude(station_log11[i]);
                        dis = userloc.distanceTo(stoploc);
                        if (dis < dis_min) {
                            dis_min = dis;

                        }
                    }
                }catch (Exception e){}
                return dis_min;
        }
        return 1000000000;
    }




}