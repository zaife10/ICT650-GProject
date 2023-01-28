package com.example.hospitalfinder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    String[] perms = {"android.permission.ACCESS_FINE_LOCATION",
            "android.permission. ACCESS_COARSE_LOCATION",
            "android.permission.INTERNET",
            "android.permission.ACCESS_NETWORK_STATE"};

    private FusedLocationProviderClient fusedLocationClient;

    FirebaseAuth auth;
    FirebaseUser user;
    TextView profileText, tvcoords, tvaddress;
    private LocationCallback locationCallback;
    LocationRequest locationRequest;



    Button button5, button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvcoords = (TextView) findViewById(R.id.tvcoords);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //check permission / permission request
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, perms, 200);
            return;
        }


        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();

                tvcoords.setText("" + lat + "," + lon);
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addressList = null;
                try {
                    addressList = geocoder.getFromLocation(lat,lon,1);

                    Address address = addressList.get(0);

                    String line = address.getAddressLine(0);
                    String area = address.getAdminArea();
                    String locality = address.getLocality();
                    String country = address.getCountryName();
                    String postcode = address.getPostalCode();

                    tvaddress.setText(line+"\n"+area+"\n"+locality+"\n"+postcode+"\n"+country);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        auth = FirebaseAuth.getInstance();
        profileText = (TextView) findViewById(R.id.textView);

        user = auth.getCurrentUser();
        profileText.setText(user.getEmail());

    }

    public void signout(View v) {
        auth.signOut();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void maps(View v) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

    public void aboutus(View v) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), about.class);
        startActivity(intent);
    }




}