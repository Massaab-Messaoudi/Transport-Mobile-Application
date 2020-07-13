package com.example.doc.bustracker;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Trucking_map extends FragmentActivity implements OnMapReadyCallback {
    boolean show_bus = false, show_station = false;
    double latitude = 0;
    double longitude = 0;
    private GoogleMap mMap;
    private int busid = 1;
    private boolean station = false;
    private boolean bus = false;
    double[] list_lat = new double[38];
    double[] list_log = new double[38];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucking_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle = getIntent().getExtras();
        station = bundle.getBoolean("station");
        bus = bundle.getBoolean("bus");
        busid = bundle.getInt("bus_id");
        BusStopsLocation bsl = new BusStopsLocation();
        list_lat = bsl.getlat(busid);
        list_log = bsl.getlong(busid);

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
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;


       /* if (show_bus == true) {
            // Add a marker in Sydney and move the camera
            for (int i = 0; i < Bus_Location_Activity.list_lat.length; i++) {
                //  try {
                latitude = Bus_Location_Activity.list_lat[i];
                longitude = Bus_Location_Activity.list_log[i];
                LatLng sydney = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Bus N° " + i + 1).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp)));

               // mMap.addMarker(new MarkerOptions().position(sydney).title("Bus id  " + Bus_Location_Activity.list_id[i]).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp)));

            }
        }*/

        if (show_station == true) {
            for (int i = 0; i < Bus_Location_Activity.station_lat.length; i++) {

                double s_latitude = Bus_Location_Activity.station_lat[i];
                double s_longitude = Bus_Location_Activity.station_log[i];
                LatLng sydney = new LatLng(s_latitude, s_longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Station N° " + i).icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)));


                // mMap.addMarker(new MarkerOptions().position(new LatLng(Bus_Location_Activity.station_lat[i], Bus_Location_Activity.station_lat[i])).title("02 stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)));
            }

        }

        LatLng sydney = new LatLng(35.531393, 6.172846);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12));



    }



}

