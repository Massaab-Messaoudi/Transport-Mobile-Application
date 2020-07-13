package com.example.doc.bustracker.Mysql2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.doc.bustracker.Bus_Location_Activity;
import com.example.doc.bustracker.MainActivity;
import com.example.doc.bustracker.NearbyStopActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by DOC on 22/03/2018.
 */

public class Downloader1 extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddess;
    int type;
    int id;
    String activity_id;
      ProgressDialog pd;


    public  Downloader1(Context c, String urlAddess, int id ,int type,String activity_id) {
        this.c = c;
        this.urlAddess = urlAddess;
        this.id=id;
        this.type=type;
        this.activity_id=activity_id;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
      /*  pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving..Please wait");
        pd.show();*/
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

       //  pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
             // Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
            Toast.makeText(c,"Server connection failed",Toast.LENGTH_SHORT).show();
        }else
        {
               /* if(activity_id.equals("main"))
                {
                    MainActivity.connection=true;
                }
                if(activity_id.equals("nearby_stop"))
                {
                    NearbyStopActivity.connection=true;
                }*/
           /* if(activity_id.equals("bus_location"))
            {
                Bus_Location_Activity.connection=true;
            }*/
                new DataParser1(c, jsonData, id,type,activity_id);
             //    pd.dismiss();

        }

    }

    private String downloadData()
    {
        Object connection= connector1.connect(urlAddess);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try {
            HttpURLConnection con= (HttpURLConnection) connection;

            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData=new StringBuffer();

            while ((line=br.readLine()) != null)
            {
                jsonData.append(line+"n");

            }

            br.close();
            is.close();

            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }

    }
}

