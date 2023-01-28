package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hospitalfinder.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.0, 101);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Hospital Shah Alam")
                .position(new LatLng(3.0713, 101.4900))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Pakar Hospital Shah Alam")
                .position(new LatLng(3.0707, 101.4885))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("SALAM Shah Alam Specialist Hospital")
                .position(new LatLng(3.0492, 101.5353))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Umra")
                .position(new LatLng(3.0829, 101.5400))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Avisena Specialist Hospital")
                .position(new LatLng(3.0718, 101.5237))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("MSU Medical Centre")
                .position(new LatLng(3.0768, 101.5528))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Ajwa")
                .position(new LatLng(3.0747, 101.4863))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("POLIKLINIK JOHAN")
                .position(new LatLng(3.0638, 101.5269))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Columbia Asia Extended Care Hospital")
                .position(new LatLng(3.0481964966883743, 101.50688974989416))
                .snippet("Open 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("KPJ Selangor Specialist Hospital")
                .position(new LatLng(3.0572, 101.5416))
                .snippet("Open 24 Hour")
        );

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark : markerOptions) {

            mMap.addMarker(mark);
        }

        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 8));

    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            return;
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};

            // 2. Otherwise, request location permissions from the user.
            ActivityCompat.requestPermissions(this, perms, 200);
        }
    }
}