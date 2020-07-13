package com.example.doc.bustracker.Mysql3;

import android.app.ProgressDialog;
import com.example.doc.bustracker.Bus_Location_Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.doc.bustracker.MainActivity;
import com.example.doc.bustracker.NearbyStopActivity;
import com.example.doc.bustracker.Trucking_map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class DataParser3  {
    String activity_id;
    public double lat_final;
    public double log_final;
    JSONArray ja;
    JSONObject jo;
    Context c;
    String jsonData;
    int id;
    boolean add=false;
    ProgressDialog pd;
    ArrayList<String> lat_list=new ArrayList<>();
    ArrayList<String> log_list=new ArrayList<>();

    public DataParser3(Context c, String jsonData,int id,String activity_id) {
        this.c = c;
        this.jsonData = jsonData;
        this.id=id;
        this.activity_id=activity_id;
        parseData();
    }



    private void parseData()
    {
        try
        {
            ja = new JSONArray(jsonData);


            lat_list.clear();
            log_list.clear();

            int compt=0;
            for (int i = 0; i < ja.length(); i++) {

                jo = ja.getJSONObject(i);
                int ligne_id = jo.getInt("line_id");
                if (ligne_id==id) {
                    Double latitude = jo.getDouble("lat");
                    Double longitude = jo.getDouble("lng");
                   /* char [] lat_char= latitude.toCharArray();
                    StringBuffer lat_string=new StringBuffer();
                    for(int j =0;j<lat_char.length;j++)
                    {
                        if(lat_char[j]=='[' || lat_char[j]==']')
                        {

                        }else
                        {
                            lat_string.append(lat_char[j]);
                        }
                    }
                    char [] log_char= longitude.toCharArray();
                    StringBuffer log_string=new StringBuffer();
                    for(int k =0;k<log_char.length;k++)
                    {
                        if(log_char[k]=='[' || log_char[k]==']')
                        {

                        }else
                        {
                            log_string.append(log_char[k]);
                        }
                    }*/
                   // Double lat_double=Double.parseDouble(lat_string.toString());
                   // Double log_double=Double.parseDouble(log_string.toString());
                    switch (activity_id) {
                        case "bus_location":
                        Bus_Location_Activity.station_lat[compt] = latitude;
                        Bus_Location_Activity.station_log[compt] = longitude;
                        break;
                        case "nearby_stop":
                            NearbyStopActivity.station_lat[compt] = latitude;
                            NearbyStopActivity.station_log[compt] = longitude;
                            break;
                        case "main":
                            switch (id)
                            {
                                case 1:
                                    MainActivity.station_lat1[compt]=latitude;
                                    MainActivity.station_log1[compt]=longitude;
                                    break;
                                case 2:
                                    MainActivity.station_lat2[compt]=latitude;
                                    MainActivity.station_log2[compt]=longitude;
                                    break;
                                case 3:
                                    MainActivity.station_lat3[compt]=latitude;
                                    MainActivity.station_log3[compt]=longitude;
                                    break;
                                case 4:
                                    MainActivity.station_lat4[compt]=latitude;
                                    MainActivity.station_log4[compt]=longitude;
                                    break;
                                case 5:
                                    MainActivity.station_lat5[compt]=latitude;
                                    MainActivity.station_log5[compt]=longitude;
                                    break;
                                case 6:
                                    MainActivity.station_lat6[compt]=latitude;
                                    MainActivity.station_log6[compt]=longitude;
                                    break;
                                case 7:
                                    MainActivity.station_lat7[compt]=latitude;
                                    MainActivity.station_log7[compt]=longitude;
                                    break;
                                case 8:
                                    MainActivity.station_lat8[compt]=latitude;
                                    MainActivity.station_log8[compt]=longitude;
                                    break;
                                case 9:
                                    MainActivity.station_lat9[compt]=latitude;
                                    MainActivity.station_log9[compt]=longitude;;
                                    break;
                                case 10:
                                    MainActivity.station_lat10[compt]=latitude;
                                    MainActivity.station_log10[compt]=longitude;
                                    break;
                                case 11:
                                    MainActivity.station_lat11[compt]=latitude;
                                    MainActivity.station_log11[compt]=longitude;
                                    break;
                            }
                    }

                    compt++;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}