package com.example.jormun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private DirectoryManager dirmgrStorage;

    private Team tmCurrent;

    private GoogleMap mMap;

    private LatLng ltlngCurrent;
    private LatLng ltlngFarRightCorner;
    private LatLng ltlngFarLeftCorner;
    private LatLng ltlngNearRightCorner;
    private LatLng ltlngNearLeftCorner;

    private Client cltCurrent;

    private Runnable rnblePositionServer;

    private Marker mrkrPlayer;

    private DBManager dbmgrDataCaptain;

    private String sResponse;

    private FragmentManager frgmtmgrFragmentControler;

    private InternalDBManager interndbManager;

    private Boolean bPosUpdated;
    private Boolean bRunning;

    private ExecutorService executor;

    private LayoutInflater lytinflLoader;

    private View vwPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tmCurrent = new Team();
        dirmgrStorage = new DirectoryManager();
        //Hey this the start of the app
        lytinflLoader = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        executor = Executors.newCachedThreadPool();
        Location lcCurrent;
        //Still need to clean these
        setFrgmtmgrFragmentControler(getSupportFragmentManager());
        //Let's grab the fragment managers
        setContentView(R.layout.activity_maps);
        setbPosUpdated(false);
        //Let's grab the map
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        //Let's  call and setup the positions element
        if(getLtlngCurrent()==null){
            setLtlngCurrent(new LatLng(0,0));
        }
        //Let's check the connection to the DB
        dbmgrDataCaptain = new DBManager("https://91.176.180.132",this);
        //Let's start the launch procedure
        interndbManager = new InternalDBManager(this,dirmgrStorage);
        interndbManager.open();

        SettingUp();
    }
    public void GetCoord(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location lcLocation;

        if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1000, this);
            }catch (SecurityException e) {
                System.out.println("Can't get your position due to security error");
                e.printStackTrace();
            }

        }else if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
            if(Build.VERSION.SDK_INT >= 23) {
                if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Can't get your position due to missing permission");
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
    public void ClearScreen(){
        do{
            getSupportFragmentManager().popBackStack();
        }while(getSupportFragmentManager().getBackStackEntryCount()>0);
    }
    public void Closure(){
        bRunning=false;
        ClearScreen();
        if(getCltCurrent()!=null){
            getCltCurrent().CloseConnection();
        }
    }
    public void Message(LatLng ltlngCoords){
        System.out.println("Information : CoordsClicked("+ltlngCoords.latitude+","+ltlngCoords.longitude+")");
        System.out.println("Information : The user location is "+ltlngCurrent.latitude+";"+ltlngCurrent.longitude);

        setLtlngFarLeftCorner(mMap.getProjection().getVisibleRegion().farLeft);
        setLtlngFarRightCorner(mMap.getProjection().getVisibleRegion().farRight);
        setLtlngNearLeftCorner(mMap.getProjection().getVisibleRegion().nearLeft);
        setLtlngNearRightCorner(mMap.getProjection().getVisibleRegion().nearRight);

        System.out.println("FARLEFT = LAT("+getLtlngFarLeftCorner().latitude+"), LONG("+getLtlngFarLeftCorner().longitude+")");
        System.out.println("FARRIGHT = LAT("+getLtlngFarRightCorner().latitude+"), LONG("+getLtlngFarRightCorner().longitude+")");
        System.out.println("NEARLEFT = LAT("+getLtlngNearLeftCorner().latitude+"), LONG("+getLtlngNearLeftCorner().longitude+")");
        System.out.println("NEARRIGHT = LAT("+getLtlngNearRightCorner().latitude+"), LONG("+getLtlngNearRightCorner().longitude+")");

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        setLtlngFarLeftCorner(mMap.getProjection().getVisibleRegion().farLeft);
        setLtlngFarRightCorner(mMap.getProjection().getVisibleRegion().farRight);
        setLtlngNearLeftCorner(mMap.getProjection().getVisibleRegion().nearLeft);
        setLtlngNearRightCorner(mMap.getProjection().getVisibleRegion().nearRight);


        System.out.println("FARLEFT = LAT("+getLtlngFarLeftCorner().latitude+"), LONG("+getLtlngFarLeftCorner().longitude+")");
        System.out.println("FARRIGHT = LAT("+getLtlngFarRightCorner().latitude+"), LONG("+getLtlngFarRightCorner().longitude+")");
        System.out.println("NEARLEFT = LAT("+getLtlngNearLeftCorner().latitude+"), LONG("+getLtlngNearLeftCorner().longitude+")");
        System.out.println("NEARRIGHT = LAT("+getLtlngNearRightCorner().latitude+"), LONG("+getLtlngNearRightCorner().longitude+")");

        FetchCoords ftchcrdsCurrentPosition = new FetchCoords();
        ftchcrdsCurrentPosition.run();

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
        ModifyMap(location);
        if(getCltCurrent()!=null){
            getCltCurrent().UpdatePosition(getLtlngCurrent(),getLtlngFarLeftCorner(),getLtlngFarRightCorner(),getLtlngNearLeftCorner(),getLtlngNearRightCorner());
        }
    }

    public void ModifyMap(Location lcCurrent){
        double latitude = lcCurrent.getLatitude();
        double longitude = lcCurrent.getLongitude();

        Toast.makeText(MapsActivity.this,"Your location has changed",Toast.LENGTH_LONG).show();

        setLtlngCurrent(new LatLng(latitude, longitude));
        setLtlngNearRightCorner(mMap.getProjection().getVisibleRegion().nearRight);
        setLtlngNearLeftCorner(mMap.getProjection().getVisibleRegion().nearLeft);
        setLtlngFarRightCorner(mMap.getProjection().getVisibleRegion().farRight);
        setLtlngFarLeftCorner(mMap.getProjection().getVisibleRegion().farLeft);

        mrkrPlayer = mMap.addMarker(new MarkerOptions().position(ltlngCurrent).title("Player here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ltlngCurrent));
        mMap.setMinZoomPreference(15f);
        mMap.setMaxZoomPreference(16f);
        mMap.setLatLngBoundsForCameraTarget(new LatLngBounds(ltlngCurrent,ltlngCurrent));
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
        Closure();
        Toast.makeText(MapsActivity.this,"You are on pause",Toast.LENGTH_LONG).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        Closure();
        Toast.makeText(MapsActivity.this,"You are on stop",Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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

        bRunning = true;
        System.out.println("Finished initializing the setup");
        System.out.println("Skip Over Pos Server Setup");
        interndbManager.PrintTable(InternalDBBuilder.TABLE_infouser);
        if(dbmgrDataCaptain.getbConCheck()){
            if(interndbManager.CheckToken()>0){
                if(interndbManager.CheckAliveToken(dbmgrDataCaptain).contains("1#")){
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
        ppupCurrent.setMpactCurrent(this);
        ppupCurrent.setDbmgrSecretary(this.dbmgrDataCaptain);
        ppupCurrent.setInterndbSecretary(interndbManager);
        ppupCurrent.setFragmgrCurrent(frgmtmgrFragmentControler);
        ppupCurrent.setCurrTeam(tmCurrent);
        System.out.println("Open popup");
        FragmentTransaction fragtrans = getFrgmtmgrFragmentControler().beginTransaction();
        fragtrans.addToBackStack("Maps");
        fragtrans.add(R.id.map,ppupCurrent,null);
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


    public LatLng getLtlngFarRightCorner() {
        return ltlngFarRightCorner;
    }

    public void setLtlngFarRightCorner(LatLng ltlngFarRightCorner) {
        this.ltlngFarRightCorner = ltlngFarRightCorner;
    }

    public LatLng getLtlngFarLeftCorner() {
        return ltlngFarLeftCorner;
    }

    public void setLtlngFarLeftCorner(LatLng ltlngFarLeftCorner) {
        this.ltlngFarLeftCorner = ltlngFarLeftCorner;
    }

    public LatLng getLtlngNearRightCorner() {
        return ltlngNearRightCorner;
    }

    public void setLtlngNearRightCorner(LatLng ltlngNearRightCorner) {
        this.ltlngNearRightCorner = ltlngNearRightCorner;
    }

    public LatLng getLtlngNearLeftCorner() {
        return ltlngNearLeftCorner;
    }

    public void setLtlngNearLeftCorner(LatLng ltlngNearLeftCorner) {
        this.ltlngNearLeftCorner = ltlngNearLeftCorner;
    }

    public Client getCltCurrent() {
        return cltCurrent;
    }

    public void setCltCurrent(Client cltCurrent) {
        this.cltCurrent = cltCurrent;
    }
    private class FetchCoords implements Runnable {

        @Override
        public void run() {
            GetCoord();
        }
    }
}
