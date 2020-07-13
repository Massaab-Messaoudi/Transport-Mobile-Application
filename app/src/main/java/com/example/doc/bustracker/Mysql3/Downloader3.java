package com.example.doc.bustracker.Mysql3;

/**
 * Created by Study on 3/25/2018.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader3 extends AsyncTask<Void,Void,String>{
    Context c;
    String urlAddess;
    String activity_id;
    int id;
    ProgressDialog pd;


    public  Downloader3(Context c, String urlAddess,int id,String activity_id) {
        this.c = c;
        this.urlAddess = urlAddess;
        this.id=id;
        this.activity_id=activity_id;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        pd=new ProgressDialog(c);
     /*   pd.setTitle("Retrieve");
        pd.setMessage("Retrieving..Please wait");
        pd.show();*/
    }

    @Override
    protected String doInBackground(Void... params)
    {

        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

     //   pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
          //  Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
            Toast.makeText(c,"Server connection failed",Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }else
        {

            new DataParser3(c,jsonData,id,activity_id);

        }

    }

    private String downloadData()
    {
        Object connection= Connector3.connect(urlAddess);
        if(connection.toString().startsWith("Error"))
        {
           /* Toast.makeText(c,"Connection failed ",Toast.LENGTH_SHORT).show();
            pd.dismiss();*/
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