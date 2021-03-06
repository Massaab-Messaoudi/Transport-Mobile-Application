package com.example.doc.bustracker.Mysql;

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

public class Downloader extends AsyncTask<Void,Void,String>{
    Context c;
    String urlAddess;

    int id;
    ProgressDialog pd;


    public  Downloader(Context c, String urlAddess,int id) {
        this.c = c;
        this.urlAddess = urlAddess;
        this.id=id;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving..Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
            //Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
            Toast.makeText(c,"Server connection failed",Toast.LENGTH_SHORT).show();
        }else
        {
            //PARSE
            // new DataParser(c,jsonData,tv).execute();

            new DataParser(c,jsonData,id);
            //tv.setText(jsonData);
        }

    }

    private String downloadData()
    {
        Object connection=Connector.connect(urlAddess);
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