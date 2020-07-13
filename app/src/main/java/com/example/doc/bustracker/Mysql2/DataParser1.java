package com.example.doc.bustracker.Mysql2;
import com.example.doc.bustracker.Bus_Location_Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.AdapterView;

import com.example.doc.bustracker.Bus_Location_Activity;
import com.example.doc.bustracker.MainActivity;
import com.example.doc.bustracker.NearbyStopActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DOC on 22/03/2018.
 */

public class DataParser1 {
    String activity_id;
    JSONArray ja;
    JSONObject jo;
    Context c;
    String jsonData;
    int type;
    int id;
    public DataParser1(Context c, String jsonData, int id,int type,String activity_id) {
        this.c = c;
        this.jsonData = jsonData;
        this.id=id;
        this.type=type;
        this.activity_id=activity_id;
        parseData();
    }

    private void parseData()
    {
        if(activity_id.equals("main"))
        {
            MainActivity.connection=true;
        }
        if(activity_id.equals("nearby_stop"))
        {
            NearbyStopActivity.connection=true;
        }
        if(activity_id.equals("bus_location"))
        {
            Bus_Location_Activity.connection=true;
        }
        int[] sizes=new int[15];

        try
        {
            ja = new JSONArray(jsonData);
            int bus_id;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                if(type==1) {

                    switch (activity_id) {
                        case "bus_location":
                             bus_id = jo.getInt("line_id");
                            Bus_Location_Activity.list_size[bus_id - 1]++;
                            break;
                        case "main":
                            bus_id = jo.getInt("line_id");
                            MainActivity.list_size[bus_id - 1]++;
                            break;
                        case "nearby_stop":
                            bus_id = jo.getInt("line_id");
                            NearbyStopActivity.list_size[bus_id - 1]++;
                            break;

                    }
                }
                else {
                    if(activity_id.equals("bus_location")) {
                         bus_id = jo.getInt("line");
                        Bus_Location_Activity.nb_bus[bus_id - 1]++;
                    }
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}