package com.svalero.toteco_app_aa2.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.LocationsContract;
import com.svalero.toteco_app_aa2.databinding.FragmentLocationsBinding;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.presenter.LocationsPresenter;

import java.util.List;

public class LocationActivity extends AppCompatActivity implements LocationsContract.View,
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap map;
    private LocationsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.locations_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        presenter = new LocationsPresenter(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMarkerClickListener(this);

        // give the permissions to access to users device
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        googleMap.setMyLocationEnabled(true);
        presenter.loadEstablishments();
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void loadEstablishments(List<Establishment> establishments) {
        establishments.stream().forEach(e -> {
            LatLng latLng = new LatLng(e.getLocation().getLatitude(), e.getLocation().getLongitude());
            map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .snippet(getString(R.string.add_publication_establishment_punctuation_print, String.valueOf(e.getPunctuation())))
                    .title(e.getName()));
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}