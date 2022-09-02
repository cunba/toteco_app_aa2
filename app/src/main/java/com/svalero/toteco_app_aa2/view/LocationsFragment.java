package com.svalero.toteco_app_aa2.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

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
import com.svalero.toteco_app_aa2.presenter.LocationsPresenter;

import java.util.List;

public class LocationsFragment extends Fragment implements LocationsContract.View,
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private FragmentLocationsBinding binding;
    private List<Establishment> establishments;
    private GoogleMap map;
    private LocationsPresenter presenter;
    private SupportMapFragment supportMapFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLocationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        supportMapFragment = (SupportMapFragment) getParentFragmentManager()
                .findFragmentById(R.id.locations_map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }

        presenter = new LocationsPresenter(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (null != supportMapFragment) {
            getParentFragmentManager().beginTransaction()
                    .remove(supportMapFragment)
                    .commit();
        }
    }

    @Override
    public void loadEstablishments() {
        establishments = presenter.loadEstablishments();
        establishments.stream().forEach(p -> {
            LatLng latLng = new LatLng(p.getLatitude(), p.getLongitude());
            map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .snippet(getString(R.string.add_publication_establishment_punctuation_print, String.valueOf(p.getPunctuation())))
                    .title(p.getName()));
        });
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        System.out.println("entra");
        map = googleMap;
        googleMap.setOnMarkerClickListener(this);

        // give the permissions to access to users device
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        googleMap.setMyLocationEnabled(true);

        loadEstablishments();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (null != supportMapFragment) {
//            getParenstFragmentActivity().beginTransaction()
//                    .remove(supportMapFragment)
//                    .commit();
//        }
//    }
}