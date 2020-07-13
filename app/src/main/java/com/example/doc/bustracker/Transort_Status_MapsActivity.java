package com.example.doc.bustracker;

import android.Manifest;
import android.app.ProgressDialog;
import com.example.doc.bustracker.Direction.DirectionFinder;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.doc.bustracker.Direction.DirectionFinderListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Transort_Status_MapsActivity extends FragmentActivity implements OnMapReadyCallback ,RoutingListener, DirectionFinderListener{

    public static String[] list_lat;
    public static String[] list_log;
    private ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private int bus_id;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transort__status__maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        polylines = new ArrayList<>();
        Bundle bundle=getIntent().getExtras();
        bus_id=bundle.getInt("busid");

    }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng batna_centre = new LatLng(35.549571, 6.175214);
        //  mMap.addMarker(new MarkerOptions().position(batna_centre).title(" Batna centre"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(batna_centre, 13));

        if(bus_id==2) {   // 02 ligne
            list_lat=new String[16];
            list_log=new String[16];
            list_lat[0]="35.531320";
            list_lat[1]="35.540327";
            list_lat[2]="35.543671";
            list_log[0]="6.172985";
            list_log[1]="6.155557";
            list_log[2]=" 6.165242";
            list_lat[3]="35.542812";
            list_log[3]="6.166362";
            list_lat[4]="35.552466";
            list_log[4]="6.178746";
            list_lat[5]="35.556956";
            list_log[5]="6.173079";
            list_lat[6]="35.559234";
            list_log[6]="6.173416";
            list_lat[7]="35.554668";
            list_log[7]="6.179046";
            list_lat[8]="35.543871";
            list_log[8]="6.165577";
            list_lat[9]="35.543691";
            list_log[9]="6.164877";
            list_lat[10]="35.539990";
            list_log[10]="6.155045";
            list_lat[11]="35.536280";
            list_log[11]="6.150799";
            list_lat[12]="35.532915";
            list_log[12]="6.154835";
            list_lat[13]="35.527600";
            list_log[13]="6.167353";
            list_lat[14]="35.529214";
            list_log[14]="6.173944";
            list_lat[15]="35.531320";
            list_log[15]="6.172985";

       /*  String origin="35.531320,6.172985";
          String destination ="35.540327,6.155557";*/
            try {

                //  DirectionFinder df= new DirectionFinder(this,origin,destination,"35.543671, 6.165242");
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(bus_id==1) {
            list_lat=new String[18];
            list_log=new String[18];
            list_lat[0]="35.537586";
            list_log[0]="6.137879";
            list_lat[1]="35.538205";
            list_log[1]="6.135396";
            list_lat[2]="35.533976";
            list_log[2]="6.148384";
            list_lat[3]="35.542297";
            list_log[3]="6.157811";
            list_lat[4]="35.543711";
            list_log[4]="6.165458";
            list_lat[5]="35.542457";
            list_log[5]="6.166679";
            list_lat[6]="35.555128";
            list_log[6]="6.185006";
            list_lat[7]="35.556233";
            list_log[7]="6.183561";
            list_lat[8]="35.563173";
            list_log[8]="6.189168";
            list_lat[9]="35.559465";
            list_log[9]="6.193504";
            list_lat[10]="35.561194";
            list_log[10]="6.196756";
            list_lat[11]="35.563342";
            list_log[11]="6.189162";
            list_lat[12]="35.557259";
            list_log[12]="6.182474";
            list_lat[13]="35.554280";
            list_log[13]="6.178555";
            list_lat[14]="35.543877";
            list_log[14]="6.165603";
            list_lat[15]="35.539256";
            list_log[15]="6.154180";
            list_lat[16]="35.534252";
            list_log[16]="6.148405";
            list_lat[17]="35.537586";
            list_log[17]="6.137879";

            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(bus_id==7)
        {
            LatLng bus1 = new LatLng(35.551127, 6.177470);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();

            list_lat=new String[18];
            list_log=new String[18];
            list_lat[0]="35.551127";
            list_log[0]="6.177470";
            list_lat[1]="35.549588";
            list_log[1]="6.175146";
            list_lat[2]="35.545887";
            list_log[2]="6.170695";
            list_lat[3]="35.542933";
            list_log[3]="6.174065";
            list_lat[4]="35.539043";
            list_log[4]="6.170858";
            list_lat[5]="35.534264";
            list_log[5]="6.182664";
            list_lat[6]="35.532912";
            list_log[6]="6.184155";
            list_lat[7]="35.532780";
            list_log[7]="6.186932";
            list_lat[8]="35.533975";
            list_log[8]="6.187336";
            list_lat[9]="35.534276";
            list_log[9]="6.182676";
            list_lat[10]="35.534541";
            list_log[10]="6.180748";
            list_lat[11]="35.535513";
            list_log[11]="6.174465";
            list_lat[12]="35.539050";
            list_log[12]="6.170870";
            list_lat[13]="35.540071";
            list_log[13]="6.171164";
            list_lat[14]="35.543409";
            list_log[14]="6.173562";
            list_lat[15]="35.545032";
            list_log[15]="6.171852";
            list_lat[16]="35.549946";
            list_log[16]="6.178941";
            list_lat[17]="35.551127";
            list_log[17]="6.177470";

            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(bus_id==8)
        {
            LatLng bus1 = new LatLng(35.531618, 6.165091);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();
            list_lat=new String[21];
            list_log=new String[21];
            list_lat[0]="35.531618";
            list_log[0]="6.165091";
            list_lat[1]="35.537865";
            list_log[1]="6.156235";
            list_lat[2]="35.539772";
            list_log[2]="6.160680";
            list_lat[3]="35.542457";
            list_log[3]="6.166679";
            list_lat[4]="35.542812";
            list_log[4]="6.166363";
            list_lat[5]="35.552465";
            list_log[5]="6.178747";
            list_lat[6]="35.557404";
            list_log[6]="6.172737";
            list_lat[7]="35.581136";
            list_log[7]="6.193578";
            list_lat[8]="35.571064";
            list_log[8]="6.181923";
            list_lat[9]="35.562979";
            list_log[9]="6.181421";
            list_lat[10]="35.561221";
            list_log[10]="6.182318";
            list_lat[11]="35.557437";
            list_log[11]="6.182155";
            list_lat[12]="35.554668";
            list_log[12]="6.179049";
            list_lat[13]="35.553774";
            list_log[13]="6.180270";
            list_lat[14]="35.549350";
            list_log[14]="6.174849";
            list_lat[15]="35.542891";
            list_log[15]="6.166238";
            list_lat[16]="35.542465";
            list_log[16]="6.166673";
            list_lat[17]="35.540374";
            list_log[17]="6.161991";
            list_lat[18]="35.535853";
            list_log[18]="6.151503";
            list_lat[19]="35.531581";
            list_log[19]="6.157706";
            list_lat[20]="35.531618";
            list_log[20]="6.165091";
            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        if(bus_id==9)
        {
            LatLng bus1 = new LatLng(35.545597, 6.143572);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();
            list_lat=new String[21];
            list_log=new String[21];
            list_lat[0]="35.545597";
            list_log[0]="6.143572";
            list_lat[1]="35.549573";
            list_log[1]="6.148966";
            list_lat[2]="35.553068";
            list_log[2]="6.158121";
            list_lat[3]="35.548855";
            list_log[3]="6.164780";
            list_lat[4]="35.546801";
            list_log[4]="6.162574";
            list_lat[5]="35.542818";
            list_log[5]="6.166381";
            list_lat[6]="35.548378";
            list_log[6]="6.173829";
            list_lat[7]="35.547132";
            list_log[7]="6.175491";
            list_lat[8]="35.548579";
            list_log[8]="6.177194";
            list_lat[9]="35.543524";
            list_log[9]="6.184554";
            list_lat[10]="35.543651";
            list_log[10]="6.187903";
            list_lat[11]="35.539986";
            list_log[11]="6.190421";
            list_lat[12]="35.544569";
            list_log[12]="6.193635";
            list_lat[13]="35.547510";
            list_log[13]="6.184736";
            list_lat[14]="35.543257";
            list_log[14]="6.183936";
            list_lat[15]="35.558805";
            list_log[15]="6.164213";
            list_lat[16]="35.554462";
            list_log[16]="6.156686";
            list_lat[17]="35.553088";
            list_log[17]="6.158099";
            list_lat[18]="35.548784";
            list_log[18]="6.150621";
            list_lat[19]="35.544125";
            list_log[19]="6.144857";
            list_lat[20]="35.545597";
            list_log[20]="6.143572";
            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        if (bus_id == 10)
        {
            LatLng bus1 = new LatLng(35.540373, 6.190130);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();
            list_lat=new String[29];
            list_log=new String[29];
            list_lat[0]="35.539989";
            list_log[0]="6.190423";
            list_lat[1]="35.540730";
            list_log[1]="6.190967";
            list_lat[2]="35.544568";
            list_log[2]="6.193634";
            list_lat[3]="35.547456";
            list_log[3]="6.184862";
            list_lat[4]="35.543243";
            list_log[4]="6.183956";
            list_lat[5]="35.549701";
            list_log[5]="6.175274";
            list_lat[6]="35.545885";
            list_log[6]="6.170696";
            list_lat[7]="35.546567";
            list_log[7]="6.169561";
            list_lat[8]="35.553071";
            list_log[8]="6.158116";
            list_lat[9]="35.551041";
            list_log[9]="6.152583";
            list_lat[10]="35.554741";
            list_log[10]="6.150177";
            list_lat[11]="35.554107";
            list_log[11]="6.147382";
            list_lat[12]="35.548837";
            list_log[12]="6.106128";
            list_lat[13]="35.543259";
            list_log[13]="6.086942";
            list_lat[14]="35.531515";
            list_log[14]="6.093045";
            list_lat[15]="35.542807";
            list_log[15]="6.086600";
            list_lat[16]="35.548831";
            list_log[16]="6.106129";
            list_lat[17]="35.554072";
            list_log[17]="6.146921";
            list_lat[18]="35.554731";
            list_log[18]="6.150171";
            list_lat[19]="35.549804";
            list_log[19]="6.154125";
            list_lat[20]="35.553052";
            list_log[20]="6.158108";
            list_lat[21]="35.546813";
            list_log[21]="6.169001";
            list_lat[22]="35.545343";
            list_log[22]="6.168145";
            list_lat[23]="35.544484";
            list_log[23]="6.169200";
            list_lat[24]="35.549632";
            list_log[24]="6.175354";
            list_lat[25]="35.543015";
            list_log[25]="6.184195";
            list_lat[26]="35.543588";
            list_log[26]="6.184558";
            list_lat[27]="35.543651";
            list_log[27]="6.187906";
            list_lat[28]="35.539989";
            list_log[28]="6.190423";

            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        if(bus_id==11)
        {
            LatLng bus1 = new LatLng(35.559473, 6.193876);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();
            list_lat=new String[21];
            list_log=new String[21];
            list_lat[0]="35.559473";
            list_log[0]="6.193876";
            list_lat[1]="35.561188";
            list_log[1]="6.196760";
            list_lat[2]="35.565403";
            list_log[2]="6.191845";
            list_lat[3]="35.563248";
            list_log[3]="6.189287";
            list_lat[4]="35.557217";
            list_log[4]="6.182983";
            list_lat[5]="35.556827";
            list_log[5]="6.181548";
            list_lat[6]="35.543885";
            list_log[6]="6.165627";
            list_lat[7]="35.542004";
            list_log[7]="6.157337";
            list_lat[8]="35.534214";
            list_log[8]="6.148067";
            list_lat[9]="35.537854";
            list_log[9]="6.134528";
            list_lat[10]="35.527715";
            list_log[10]="6.095662";
            list_lat[11]="35.535401";
            list_log[11]="6.090896";
            list_lat[12]="35.535315";
            list_log[12]="6.090684";
            list_lat[13]="35.520965";
            list_log[13]="6.103992";
            list_lat[14]="35.537770";
            list_log[14]="6.134688";
            list_lat[15]="35.534014";
            list_log[15]="6.148302";
            list_lat[16]="35.542178";
            list_log[16]="6.157696";
            list_lat[17]="35.543715";
            list_log[17]="6.165429";
            list_lat[18]="35.542459";
            list_log[18]="6.166683";
            list_lat[19]="35.554193";
            list_log[19]="6.186246";
            list_lat[20]="35.559473";
            list_log[20]="6.193876";
            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (bus_id == 12) {
            LatLng bus1 = new LatLng(35.546071, 6.179555);
            mMap.addMarker(new MarkerOptions().position(bus1).title("bus start poin").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_24dp))).showInfoWindow();
            list_lat=new String[11];
            list_log=new String[11];
            list_lat[0]="35.546071";
            list_log[0]="6.179555";
            list_lat[1]="35.542053";
            list_log[1]="6.184691";
            list_lat[2]="35.538176";
            list_log[2]="6.185165";
            list_lat[3]="35.502080";
            list_log[3]="6.238728";
            list_lat[4]="35.483330";
            list_log[4]="6.259497";
            list_lat[5]="35.491057";
            list_log[5]="6.268149";
            list_lat[6]="35.491125";
            list_log[6]="6.264360";
            list_lat[7]="35.538821";
            list_log[7]="6.184883";
            list_lat[8]="35.541798";
            list_log[8]="6.184713";
            list_lat[9]="35.543808";
            list_log[9]="6.181991";
            list_lat[10]="35.546071";
            list_log[10]="6.179555";

            try {
                DirectionFinder df= new DirectionFinder(this);
                df.execute();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }




        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED)

        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


    }
    private void getRoute(LatLng point1,LatLng point2,LatLng point3,LatLng point4,LatLng point5,LatLng point6
            ,LatLng point7,LatLng point8,LatLng point9,LatLng point10,LatLng point11) {

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(false)
                .waypoints(point1, point2,point3,point4,point5,point6,point7,point8,point9,point10)
                // .wait(500)
                .build();
        routing.execute();


    }

    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark_material_light};
    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingCancelled() {

    }
    private void erasePolylines()
    {
        for(Polyline line:polylines)
        {
            line.remove();
        }
        polylines.clear();
    }

    @Override
    public void onDirectionFinderStart() {
      /*  final ProgressDialog  pd=new ProgressDialog(this);
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

                    Toast.makeText(Transort_Status_MapsActivity.this,"Data Downloaded Successfully ",Toast.LENGTH_SHORT).show();


                }
            }

        };
        demotask.execute();*/
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<com.example.doc.bustracker.Direction.Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (com.example.doc.bustracker.Direction.Route route : routes) {
          // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));


          /*  originMarkers.add(mMap.addMarker(new MarkerOptions()

                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()

                    .title(route.endAddress)
                    .position(route.endLocation)));*/

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
    }
    }
}