package com.omaressam.hangin;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.omaressam.hangin.Utites.Unities;
import com.omaressam.hangin.network.api.ApiClient;
import com.omaressam.hangin.network.services.PlacesService;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_nearby extends Fragment implements OnMapReadyCallback {

    private Context context;

    private GoogleMap map;

    private SupportMapFragment mapFragment;

    private Location location;

    private FusedLocationProviderClient fusedLocationProviderClient;

    public fragment_nearby() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        setUpMaps();
        checkLocationPermission();
        return view;
    }


    private void checkLocationPermission() {
        int permission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) requireContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION} ,1);
        }
    }

    public void onRequestPermissionsResult (int requestCode, @NotNull String []permissions, @NotNull int[]grantResults) {
        if (requestCode == 1) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "permission_denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        mapFragment.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapFragment.onDestroy();
    }

    private void setUpMaps() {
        context = Objects.requireNonNull(requireActivity().getApplicationContext());
        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        getMyLocation();
        getPlaces();
    }

    private void initMarker(double lat, double lng, String title) {
        LatLng latLng = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(latLng).title(title));

    }

    private void moveCamera (double lat,double lng) {
        LatLng latLng = new LatLng(lat, lng);
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    private void getMyLocation () {
        if (Unities.checkLocationPermission(context)){
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
            Task location2 = fusedLocationProviderClient.getLastLocation();
            location2.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        map.setMyLocationEnabled(true);
                        location = (Location) task.getResult();
                        if (location !=null){
                            initMarker(location.getLatitude(),location.getLongitude(),"My location");
                            moveCamera(location.getLatitude(),location.getLongitude());
                        }else {
                            Toast.makeText(context, "Open location", Toast.LENGTH_SHORT).show();
                            map.setMyLocationEnabled(false);
                        }
                    }else {
                        Toast.makeText(context, "Open location", Toast.LENGTH_SHORT).show();
                        map.setMyLocationEnabled(false);
                    }

                }
            });
        }
    }

    private void getPlaces() {

        PlacesService placesService = ApiClient.getClient().create(PlacesService.class);
        placesService.getPlaces().enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(@NotNull Call<List<Place>> call, @NotNull Response<List<Place>> response) {
                if (response.isSuccessful()) {
                    List<Place> places = response.body();

                    if (places != null && !places.isEmpty()) {

                        for (Place place : places) {
                            initMarker(place.getLat(), place.getLng(), place.getName());
                        }

                    }

                }

            }

            @Override
            public void onFailure(@NotNull Call<List<Place>> call, @NotNull Throwable t) {

            }
        });
        // TODO: LinearLayoutManager

    }


}
