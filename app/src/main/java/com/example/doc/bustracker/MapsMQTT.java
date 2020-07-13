package com.example.doc.bustracker;

import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapsMQTT extends FragmentActivity implements OnMapReadyCallback {
    private final Map<String, MarkerOptions> mMarkers = new ConcurrentHashMap<String, MarkerOptions>();
    //public static MarkerOptions[]markers;

    boolean show_bus = false, show_station = false;
    private int busid = 1;
    private boolean station = false;
    private boolean bus = false;
    int i=0;
    MqttAndroidClient client;
    private Button LocationUpdate;
    // private Button btclose;
    private GoogleMap mMap;
   double latitude=0.0;
    double longitude=0.0;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_mqtt);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle = getIntent().getExtras();
        station = bundle.getBoolean("station");
        bus = bundle.getBoolean("bus");
        busid = bundle.getInt("bus_id");
        BusStopsLocation bsl = new BusStopsLocation();
        // list_lat = bsl.getlat(busid);
        // list_log = bsl.getlong(busid);

        if (bus == true) {
            show_bus = true;
            if (station == false) {
            } else {
                show_station = true;


            }
        } else {

            if (station == true) {
                show_station = true;
            }

        }



      /*  btclose=(Button)findViewById(R.id.exit);
        btclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });*/
        LocationUpdate=(Button)findViewById(R.id.sub);
        String clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.1.9:1883",
                        clientId);

            //"tcp://192.168.1.101:1883"
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    //  Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //  Log.d(TAG, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        LocationUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//02/+
                String topic = "arduino";
                int qos = 1;
                try {
                    IMqttToken subToken = client.subscribe(topic, qos);
                    subToken.setActionCallback(new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken,
                                              Throwable exception) {

                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
        client.setCallback(new MqttCallback() {
            boolean id_finded=false;
            boolean lat_finded = false;

            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(MapsMQTT.this, "disconnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

                String msg = new String(message.getPayload()) ;  //id,lat,long

                char [] log_char= msg.toCharArray();
                StringBuffer id_string=new StringBuffer();
                StringBuffer lat_string=new StringBuffer();
                StringBuffer log_string=new StringBuffer();
                for(int k =0;k<log_char.length;k++) {
                    if(id_finded==true) {
                        if (log_char[k] == ',') {
                            lat_finded = true;
                        } else {
                            if (lat_finded == false) {
                                lat_string.append(log_char[k]);
                            } else {
                                log_string.append(log_char[k]);
                            }
                        }
                    }
                    else
                    {
                        if(log_char[k] == ',')
                        {
                            id_finded=true;
                        }else
                        {
                            id_string.append(log_char[k]);
                        }
                    }
                }
                if(bus_belong(id_string.toString())) {
                    remove(id_string.toString());
                    latitude = Double.parseDouble(lat_string.toString());
                    longitude = Double.parseDouble(log_string.toString());
                    Toast.makeText(MapsMQTT.this, latitude + "/" + longitude, Toast.LENGTH_SHORT).show();
                    LatLng sydney = new LatLng(latitude, longitude);
                    MarkerOptions m = new MarkerOptions();
                    m.position(sydney).title("Marker in Sydney");
                    add(id_string.toString(), sydney, "bus");
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    Toast.makeText(MapsMQTT.this, new String(message.getPayload()), Toast.LENGTH_SHORT).show();

                }
                lat_finded = false;
                id_finded = false;

            }
            public void deliveryComplete(IMqttDeliveryToken token) {
            }

        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;



     /*   if (show_bus == true) {
            // Add a marker in Sydney and move the camera
            for (int i = 0; i < Bus_Location_Activity.list_id.length; i++) {
                //  try {
              //  latitude = Bus_Location_Activity.list_lat[i];
              //  longitude = Bus_Location_Activity.list_log[i];
                LatLng sydney = new LatLng(latitude, longitude);
                add(Bus_Location_Activity.list_id[i],sydney,"bus");
                // mMap.addMarker(new MarkerOptions().position(sydney).title("Bus id  " + Bus_Location_Activity.list_id[i]).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp)));

            }
        }*/

        if (show_station == true) {
            for (int i = 0; i < Bus_Location_Activity.station_lat.length; i++) {

                double s_latitude = Bus_Location_Activity.station_lat[i];
                double s_longitude = Bus_Location_Activity.station_log[i];
                LatLng sydney = new LatLng(s_latitude, s_longitude);
                add(String.valueOf(100+i),sydney,"station");
                // mMap.addMarker(new MarkerOptions().position(sydney).title("Station N° " + i).icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)));


                // mMap.addMarker(new MarkerOptions().position(new LatLng(Bus_Location_Activity.station_lat[i], Bus_Location_Activity.station_lat[i])).title("02 stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)));
            }

        }

        LatLng sydney = new LatLng(35.531393, 6.172846);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12));
    }
    private void add(String name, LatLng ll,String type) {
        final MarkerOptions marker;
        if(type.equals("station")){
            marker = new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)).title("Station n =" +String.valueOf(Integer.parseInt(name)-100));
        }else {
            marker = new MarkerOptions().position(ll).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp)).title(String.valueOf(name));
        }
        mMarkers.put(name, marker);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMap.addMarker(marker);
            }
        });
    }

    private void remove(String name) {
        mMarkers.remove(name);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMap.clear();

                for (MarkerOptions item : mMarkers.values()) {
                    mMap.addMarker(item);
                }
            }
        });
    }
    private boolean bus_belong(String busmat){
        for(int i=0;i<Bus_Location_Activity.list_mat.length;i++)
        {
            if(Bus_Location_Activity.list_mat[i].equals(busmat)){
                return true;
            }
        }
        return false;
    }
}

