// region values
package com.example.weatherapp.ui;


import static android.content.Context.LOCATION_SERVICE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.Manifest;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;


import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.databinding.FragmentSearchBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements OnMapReadyCallback, LocationListener {
    private GoogleMap googleMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected @NonNull
    FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void setupViews() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    protected void callRequests() {

    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
//        Bundle bundle = new Bundle();
//        LatLng currentLatLng = bundle.getParcelable("Location");
//        gMap.addMarker(new MarkerOptions().position(currentLatLng));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                googleMap.clear();
                googleMap.addMarker(options);
                googleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                                CameraPosition.builder()
                                        .zoom(10f)
                                        .target(latLng)
                                        //.bearing(100)
                                        // .tilt(30f)
                                        .build()

                        ));

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        navController.navigate(SearchFragmentDirections
                                .actionSearchFragmentToWeatherFragment(String.valueOf(latLng.latitude), String.valueOf(latLng.longitude)));
                        return false;
                    }
                });

            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}
//endregion
