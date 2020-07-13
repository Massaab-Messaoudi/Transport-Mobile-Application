package com.example.doc.bustracker.Mysql;

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
import com.example.doc.bustracker.Trucking_map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class DataParser  {
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

    // StringBuffer spacecrafts= new StringBuffer();

    public DataParser(Context c, String jsonData,int id) {
        this.c = c;
        this.jsonData = jsonData;
        this.id=id;
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
                int line_id = jo.getInt("line");
                if (line_id==id) {
                    Double latitude = jo.getDouble("lat");
                    Double longitude = jo.getDouble("lng");
                    String id_Bus=jo.getString("mat");
                    // lat_list.add(latitude);
                    //log_list.add(longitude);

                  /*  char [] lat_char= latitude.toCharArray();
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
                  /*  Double lat_double=Double.parseDouble(lat_string.toString());
                    Double log_double=Double.parseDouble(log_string.toString());*/


                   // Bus_Location_Activity.list_lat[compt]=latitude;
                   // Bus_Location_Activity.list_log[compt]=longitude;
                    Bus_Location_Activity.list_mat[compt]=id_Bus;
                    compt++;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}