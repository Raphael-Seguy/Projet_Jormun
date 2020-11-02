package com.example.jormun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LatLng ltlngCurrent;
    private Client cltCurrent;
    private Runnable rnblePositionServer;
    private Marker mrkrPlayer;
    private DBManager dbmgrDataCaptain;
    private String sResponse;
    private FragmentManager frgmtmgrFragmentControler;

    private Boolean bPosUpdated;
    private Boolean bRunning;

    private ExecutorService executor;
    private LayoutInflater lytinflLoader;
    private View vwPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Hey this the start of the app");
        lytinflLoader = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        executor = Executors.newCachedThreadPool();
        Location lcCurrent;
        System.out.println("Still need to clean these");
        setFrgmtmgrFragmentControler(getSupportFragmentManager());
        System.out.println("Let's grab the fragment managers");
        setContentView(R.layout.activity_maps);
        System.out.println("Heyo");
        setbPosUpdated(false);
        System.out.println("Let's grab the map");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        System.out.println("Error?");
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        System.out.println("Let's  call and setup the positions element");
        GetCoord();
        if(getLtlngCurrent()==null){
            setLtlngCurrent(new LatLng(0,0));
        }
        System.out.println("Let's check the connection to the DB");
        dbmgrDataCaptain = new DBManager("https://91.176.180.132",this);
        System.out.println("Let's start the launch procedure");
        SettingUp();
    }
    public void GetCoord(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LatLng ltlngCurrent;
        Location lcLocation;

        if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1000, this);
            }catch (SecurityException e) {
                Toast.makeText(MapsActivity.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
            if(Build.VERSION.SDK_INT >= 23) {
                if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MapsActivity.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
                }else{
                    lcLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    new LatLng(lcLocation.getLatitude(), lcLocation.getLongitude());
                }
            }else{
                lcLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                new LatLng(lcLocation.getLatitude(), lcLocation.getLongitude());
            }
        }else{
            Toast.makeText(MapsActivity.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
        }
    }
    public GoogleMap getmMap() {
        return mMap;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void ClearScreeen(){
        do{
            getSupportFragmentManager().popBackStack();
        }while(getSupportFragmentManager().getBackStackEntryCount()>0);
    }
    public void Message(LatLng ltlngCoords){
        System.out.println("Information : CoordsClicked("+ltlngCoords.latitude+","+ltlngCoords.longitude+")");
        System.out.println("Information : The user location is "+ltlngCurrent.latitude+";"+ltlngCurrent.longitude);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        GetCoord();


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLngClicked) {
                Message(latLngClicked);
            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {
        setbPosUpdated(true);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Toast.makeText(MapsActivity.this,"Your location has changed",Toast.LENGTH_LONG).show();
        ltlngCurrent = new LatLng(latitude, longitude);
        mrkrPlayer = mMap.addMarker(new MarkerOptions().position(ltlngCurrent).title(""));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ltlngCurrent));
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {

            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
        return;
    }

    @Override
    protected void onPause() {
        ClearScreeen();
        Toast.makeText(MapsActivity.this,"You are on pause",Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        ClearScreeen();
        Toast.makeText(MapsActivity.this,"You are on stop",Toast.LENGTH_LONG).show();
        bRunning=false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //cltCurrent.SendUserStatus(Client.USER_EXITING);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //SettingUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MapsActivity.this,"You are back, welcome back",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(MapsActivity.this,"We can't find the provvider",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        Toast.makeText(MapsActivity.this,"Provider enabled, thank you",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        //Toast.makeText(MapsActivity.this,"Your status has changed",Toast.LENGTH_LONG).show();
    }
    //////////////////////////
    ///Méthode de procédure///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////

    public void SettingUp() {
        System.out.println("Beginning procedure");
        Popup ppupCurrent;
        String sToken;
        ThreadLauncher thrdLauncher;
        FileManager flmgrToken;

        bRunning = true;
        thrdLauncher = new ThreadLauncher();
        System.out.println("Finished initializing the setup");
        /*Runnable rnbleDBCommServer = new Runnable() {
            @Override
            public void run() {
                dbmgrDataCaptain.AskDB("var=Maria");
            }
        };

        /*thrdLauncher.start();
        while(thrdLauncher.isAlive()){
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rnblePositionServer = new Runnable() {

            @Override
            public void run() {
                while(bRunning && getbPosUpdated()){
                    GetCoord();
                    cltCurrent.SendUserPosition(getLtlngCurrent());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executor.submit(rnblePositionServer);
        executor.submit(rnbleDBCommServer);*/
        System.out.println("Skip Over Pos Server Setup");
        flmgrToken = new FileManager("Token.txt");
        if(dbmgrDataCaptain.getbConCheck()){
            if(flmgrToken.CheckTokenExistence()){
                if(flmgrToken.CheckIfAlive()!=null){
                    ppupCurrent = new LauncherPopup();
                }else{
                    ppupCurrent = new ConnectionPopup();
                }
            }else{
                ppupCurrent = new InitialPopup();
            }
        }else{
            ppupCurrent = new ErrorPopup();
        }
        System.out.println("Finished looking for the correct popup");
        ppupCurrent.setDbmgrSecretary(this.dbmgrDataCaptain);
        ppupCurrent.setFlmgrArchivist(flmgrToken);
        ppupCurrent.setFragmgrCurrent(frgmtmgrFragmentControler);
        System.out.println("Open popup");
        FragmentTransaction fragtrans = getFrgmtmgrFragmentControler().beginTransaction();
        fragtrans.addToBackStack("Start");
        fragtrans.add(R.id.map,ppupCurrent,null);
        fragtrans.addToBackStack(null);
        fragtrans.commit();
        System.out.println("Set marker");
        if(mMap!=null){
            mrkrPlayer = mMap.addMarker(new MarkerOptions().position(getLtlngCurrent()));
        }

    }

    public void ReloadingContext(){

    }

    public String getsResponse() {
        return sResponse;
    }

    public void setsResponse(String sResponse) {
        this.sResponse = sResponse;
    }
    public LatLng getLtlngCurrent() {
        return ltlngCurrent;
    }

    public void setLtlngCurrent(LatLng ltlngCurrent) {
        this.ltlngCurrent = ltlngCurrent;
    }

    public View getVwPopup() {
        return vwPopup;
    }

    public void setVwPopup(View vwPopup) {
        this.vwPopup = vwPopup;
    }

    public Boolean getbPosUpdated() {
        return bPosUpdated;
    }

    public FragmentManager getFrgmtmgrFragmentControler() {
        return frgmtmgrFragmentControler;
    }

    public void setFrgmtmgrFragmentControler(FragmentManager frgmtmgrFragmentControler) {
        this.frgmtmgrFragmentControler = frgmtmgrFragmentControler;
    }

    public void setbPosUpdated(Boolean bPosUpdated) {
        this.bPosUpdated = bPosUpdated;
    }

    public class ThreadLauncher extends Thread{
        @Override
        public void run() {
            super.run();
            GetCoord();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cltCurrent = new Client("91.176.180.132",2501, 420);
        }
    }
    //Gestion Popup

    /*public void OpenPopup(){

        findViewById(R.id.map).post(new Runnable() {
            @Override
            public void run() {
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(getVwPopup(), width, height, focusable);
                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(findViewById(R.id.map), Gravity.CENTER, 0, 0);
                // dismiss the popup window when touched
                getVwPopup().setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });
    }
    public View LauchPopup(Popup ppupCurrent){
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(ppupCurrent.getiIDLayout(), null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        return popupView;

    }*/
}
