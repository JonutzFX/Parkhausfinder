package com.example.thomas.mapdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final int REQUEST_CODE = 42;
    public boolean myLocationEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


            // permission check für die Standortabfrage per GPS
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                return;
            }

            // ermöglicht Button "Mein Standort", funktioniert nur nach permission check
            mMap.setMyLocationEnabled(true);

        // Startmaker für Fulda TODO Startposition der App auf eigenen Standort?
        LatLng Fulda = new LatLng(50.555336, 9.680949);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Fulda, 15.0f));

        // Marker für die Parkhäuser/-plätze
        LatLng richthalle = new LatLng(50.556135, 9.685201);
        mMap.addMarker(new MarkerOptions().position(richthalle).title("Richthalle Fulda"));

        LatLng ochsenwiese = new LatLng(50.558298, 9.686989);
        mMap.addMarker(new MarkerOptions().position(ochsenwiese).title("Ochsenwiese"));
    }


    // Callback Methode des permission checks
    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            myLocationEnabled = true;
        } else {
            Log.d("Dateien", "Adresse kann nicht gespeichert werden.");
        }
    }
}
