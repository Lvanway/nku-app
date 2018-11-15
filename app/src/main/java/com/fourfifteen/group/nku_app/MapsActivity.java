package com.fourfifteen.group.nku_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        LatLng lotF = new LatLng(39.032613, -84.4640379);
        LatLng lotL = new LatLng(39.0328913, -84.470315);
        LatLng lotK = new LatLng(39.0315922, -84.470315);
        final LatLng kentonGarage = new LatLng(39.0302385, -84.469908);
        LatLng lotS = new LatLng(39.028421, -84.4685112);
        LatLng universityGarage = new LatLng(39.0304453, -84.4623207);
        LatLng welcomeCenterGarage = new LatLng(39.0313646, -84.4615218);
        LatLng lotD = new LatLng(39.0321893, -84.4620309);
        LatLng nkuCenter = new LatLng(39.0319543, -84.4644747);


        final Marker kentonGarageMarker = mMap.addMarker(new MarkerOptions().position(kentonGarage).title("Kenton Drive Garage"));
        mMap.addMarker(new MarkerOptions().position(universityGarage).title("University Garage"));
        mMap.addMarker(new MarkerOptions().position(welcomeCenterGarage).title("Welcome Center Garage"));
        mMap.addMarker(new MarkerOptions().position(nkuCenter).title("NKU Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nkuCenter));
        mMap.setMinZoomPreference(16);
        mMap.setTrafficEnabled(true);

    }
}
