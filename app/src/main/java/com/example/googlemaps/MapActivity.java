package com.example.googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapBinding;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng home = new LatLng(23.223659, 88.363074);
        LatLng edu=new LatLng(23.073265, 76.859305);
        mMap.addMarker(new MarkerOptions().position(home).title("Marker in Home"));
        mMap.addMarker(new MarkerOptions().position(edu).title("VIT BHOPAL Boys Hostel 3"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(edu));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(home,16f));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(edu,16f));

    }
}