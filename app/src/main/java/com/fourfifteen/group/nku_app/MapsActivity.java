package com.fourfifteen.group.nku_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private class MarkerProperties{
        String Title;
        int Id;
        MarkerProperties(String newTitle, int newId){ Title = newTitle; Id = newId; }
    }

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

        LatLng kentonGarage = new LatLng(39.030487, -84.467727);
        LatLng universityGarage = new LatLng(39.030220, -84.461172);
        LatLng welcomeCenterGarage = new LatLng(39.0313646, -84.4615218);
        LatLng nkuCenter = new LatLng(39.030726, -84.463710);
        LatLng scienceCenter = new LatLng(39.032517, -84.466219);
        LatLng fineArtsCenter = new LatLng(39.031125,-84.463832);
        LatLng collegeOfInformatics = new LatLng(39.030921,-84.466616);
        LatLng foundersHall = new LatLng(39.031783, -84.465044);
        LatLng studentUnion = new LatLng(39.030217, -84.465253);
        LatLng campusRecCenter = new LatLng(39.029361, -84.466529);
        LatLng chaseLawBuilding = new LatLng(39.030839, -84.464791);
        LatLng steelyLibrary = new LatLng(39.032086, -84.463827);
        LatLng landrumAcademicCenter = new LatLng(39.032589, -84.464419);
        LatLng mathematicsAndSocialScience = new LatLng(39.030035, -84.462390);
        LatLng collegeOfBusiness = new LatLng(39.031093, -84.461597);
        LatLng parkingServices = new LatLng(39.032445, -84.460978);
        LatLng bbtArena = new LatLng(39.032065, -84.459264);


        Marker kentonGarageMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(kentonGarage));
        kentonGarageMarker.setTag(new MarkerProperties("Kenton Garage", 2));

        Marker universityGarageMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(universityGarage));
        universityGarageMarker.setTag(new MarkerProperties("University Garage", 1));

        Marker welcomeCenterGarageMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(welcomeCenterGarage));
        welcomeCenterGarageMarker.setTag(new MarkerProperties("Welcome Center Garage", 0));

        Marker scienceCenterMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(scienceCenter));
        scienceCenterMarker.setTag(new MarkerProperties("Dorthy Westerman Herrmann Science Center", 4));

        Marker fineArtsCentermarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(fineArtsCenter));
        fineArtsCentermarker.setTag(new MarkerProperties("Fine Arts Center",5));

        Marker collegeOfInformaticsMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(collegeOfInformatics));
        collegeOfInformaticsMarker.setTag(new MarkerProperties("College of Informatics",6));

        Marker foundersHallMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(foundersHall));
        foundersHallMarker.setTag(new MarkerProperties("Founders Hall",7));

        Marker studentUnionMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(studentUnion));
        studentUnionMarker.setTag(new MarkerProperties("Student Union",8));

        Marker campusRecCenterMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).position(campusRecCenter));
        campusRecCenterMarker.setTag(new MarkerProperties("Campus Recreation Center",9));

        Marker chaseLawBuildingMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(chaseLawBuilding));
        chaseLawBuildingMarker.setTag(new MarkerProperties("Salmon P. Chase College of Law Building",10));

        Marker steelyLibraryMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(steelyLibrary));
        steelyLibraryMarker.setTag(new MarkerProperties("Steely Library",11));

        Marker landrumAcademicCenterMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(landrumAcademicCenter));
        landrumAcademicCenterMarker.setTag(new MarkerProperties("Landrum Academic Center",12));

        Marker mathSocialScienceMarker= mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(mathematicsAndSocialScience));
        mathSocialScienceMarker.setTag(new MarkerProperties("Math Education Psychology Center",13));

        Marker collegeOfBusinessMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(collegeOfBusiness));
        collegeOfBusinessMarker.setTag(new MarkerProperties("Haile U.S. Bank College Of Business",14));

        Marker parkingServicesMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(parkingServices));
        parkingServicesMarker.setTag(new MarkerProperties("Parking Services",15));

        Marker bbtArenaMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(bbtArena));
        bbtArenaMarker.setTag(new MarkerProperties("BB&T Arena",16));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(nkuCenter));
        mMap.setMinZoomPreference(16);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Parking parkingGarage = new Parking();
                MarkerProperties markerProperties = (MarkerProperties)marker.getTag();
                marker.setTitle(markerProperties.Title);

                if(markerProperties.Id <=2) {
                    marker.setSnippet(getString(R.string.MarkerParkingAvailability) + parkingGarage.getGarageSpots(markerProperties.Id));
                }

                return false;
            }
        });

    }

}
