package com.svalero.toteco_app_aa2.view.dialog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.view.AddEstablishmentDTO;
import com.svalero.toteco_app_aa2.presenter.dialog.AddEstablishmentPresenter;
import com.svalero.toteco_app_aa2.view.AddPublicationFragment;

import java.util.List;
import java.util.Objects;

public class AddEstablishmentDialog extends DialogFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener, AddEstablishmentContract.View {

    private final AddPublicationFragment addPublicationFragment;
    private final AddEstablishmentPresenter presenter;
    private List<Establishment> establishments;
    private Establishment establishment;
    private GoogleMap map;
    private static View view;
    private SupportMapFragment supportMapFragment;

    public AddEstablishmentDialog(AddPublicationFragment fragment) {
        addPublicationFragment = fragment;
        presenter = new AddEstablishmentPresenter(this, fragment.getContext());
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.dialog_add_establishment, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        supportMapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.add_establishment_map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.add_product_submit, (dialog, id) -> {
                    onPressSubmit(view);
                })
                .setNegativeButton(R.string.add_product_cancel, (dialog, id) -> {
                    AddEstablishmentDialog.this.getDialog().cancel();
                });

        return builder.create();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .snippet("new")
                .title("new"));
        addEstablishment(latLng);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        EditText etEstablishmentName = view.findViewById(R.id.add_establishment_name);

        if (!Objects.equals(marker.getSnippet(), "new")) {
            // If the establishment does exists we print the name in the editor
            etEstablishmentName.setText(marker.getTitle());
            etEstablishmentName.setEnabled(false);
            establishment = presenter.findEstablishment(establishments, marker);
        } else {
            etEstablishmentName.setText("");
            etEstablishmentName.setEnabled(true);
        }

        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapClickListener(this);
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

        presenter.loadEstablishments();
    }

    @Override
    public void loadEstablishments(List<Establishment> establishments) {
        this.establishments = establishments;
        establishments.stream().forEach(p -> {
            LatLng latLng = new LatLng(p.getLocation().getLatitude(), p.getLocation().getLongitude());
            map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .snippet(getString(R.string.add_publication_establishment_punctuation_print, String.valueOf(p.getPunctuation())))
                    .title(p.getName()));
        });
    }

    private void addEstablishment(LatLng latLng) {
        EditText etName = view.findViewById(R.id.add_establishment_name);
        EditText etPunctuation = view.findViewById(R.id.add_establishment_punctuation);
        etName.setEnabled(true);
        String name = etName.getText().toString();
        float punctuation = 0;
        if (!etPunctuation.equals("")) {
            punctuation = Float.parseFloat(etPunctuation.getText().toString());
        }
        establishment = new Establishment();
        establishment.setName(name);
        establishment.setPunctuation(punctuation);
    }

    @Override
    public void onPressSubmit(View view) {
        EditText etEstablishmentName = view.findViewById(R.id.add_establishment_name);
        EditText etEstablishmentPunctuation = view.findViewById(R.id.add_establishment_punctuation);

        String establishmentName = etEstablishmentName.getText().toString();
        String sEstablishmentPunctuation = etEstablishmentPunctuation.getText().toString();

        AddEstablishmentDTO addEstablishmentDTO = new AddEstablishmentDTO(
                establishmentName,
                sEstablishmentPunctuation,
                establishment
        );

        presenter.onPressSubmit(addEstablishmentDTO);
    }

    @Override
    public void onSubmit(String error) {
        if (error.equals("")) {
            addPublicationFragment.setEstablishment();
        } else {
            addPublicationFragment.showToast(error);
        }

        addPublicationFragment.makeSummary();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != supportMapFragment) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .remove(supportMapFragment)
                    .commit();
        }
    }
}