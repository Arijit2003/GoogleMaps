package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapBinding;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(home,14f));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(edu,16f));
        mMap.addCircle(new CircleOptions().
                center(home)
                .radius(800)
                .fillColor(Color.parseColor("#E2D0FC"))
                .strokeColor(Color.parseColor("#000000"))
                .strokeWidth(1));


        mMap.addPolygon(new PolygonOptions().add(
                new LatLng(23.223659, 88.363074),
                new LatLng(23.073265, 76.859305)
        )
                .fillColor(Color.parseColor("#FFF8D6"))
                .strokeColor(Color.parseColor("#212A3E")));

        //GroundOverlay
        mMap.addGroundOverlay(new GroundOverlayOptions()
                .position(home,350f,350f)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.android))
                .clickable(true));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked here"));
                Geocoder geocoder = new Geocoder(MapActivity.this);
                try {
                    ArrayList<Address> arrAdd=(ArrayList<Address>) geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    Toast.makeText(MapActivity.this, arrAdd.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });



    }
}