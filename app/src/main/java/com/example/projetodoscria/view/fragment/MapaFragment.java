package com.example.projetodoscria.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.projetodoscria.R;
import com.example.projetodoscria.modelo.Monitores;
import com.example.projetodoscria.view.tabs.TabMonitores;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Intent.getIntent;
import static java.security.AccessController.getContext;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager locationManager;
    ArrayList<Monitores> monits = new ArrayList<Monitores>();
    private String provider;

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monits.add(new Monitores("Ponto 0", -22.824371412016053, -43.30047223716974, 6.93, "disponivel"));
        monits.add(new Monitores("Ponto 1", -22.9, -43.1, 7.35, "disponivel"));
        monits.add(new Monitores("Ponto 2", -22.822915903955465, -43.29979196190834, 6.93, "indisponivel"));

        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);//utilizar recurso com cuidado. Consome muita bateria.  primeiro 0 = tempo mínimo em ms. Segundo 0 = distância em metros para atualização
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        try {
            Location locationAtual = new Location("");//locationManager.getLastKnownLocation(provider);//new Location("");
            /*for(String provider : locationManager.getAllProviders()){
                Toast.makeText(getActivity(), provider, Toast.LENGTH_LONG).show();
            }

            /*locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria,true);*/

            //Location locationAtual = locationManager.getLastKnownLocation(provider);

            googleMap = map;
            googleMap.setOnMapClickListener(this);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setMyLocationEnabled(true);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(locationAtual.getLatitude(), locationAtual.getLongitude()));
            markerOptions.title("Localização Atual");

        } catch (SecurityException exception) {
            Log.e("XAMPSON", exception.getMessage());
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        googleMap.clear();

        Location location = new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        Toast.makeText(getContext(), "Coordenadas: " + latLng.toString(), Toast.LENGTH_SHORT).show();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Você está observando aqui!");

        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        for (int i = 0; i < monits.size(); i++) {
            double latitude = monits.get(i).getLatitude();
            double longitude = monits.get(i).getLongitude();
            // if(addProximityAlert(latitude, longitude)){
            LatLng loc = new LatLng(latitude, longitude);
            Location locDestino = new Location("");
            locDestino.setLatitude(latitude);
            locDestino.setLongitude(longitude);
            if (location.distanceTo(locDestino) <= 5000) {

                markerOptions.position(loc);
                markerOptions.title(monits.get(i).getNome());

                googleMap.addMarker(markerOptions);
            } else {
                //Toast.makeText(getActivity(), "Não está perto!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        googleMap.clear();

        LatLng novaLocalizacao = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(novaLocalizacao);
        markerOptions.title("Você está aqui!");

        googleMap.addMarker(markerOptions);

        for (int i = 0; i < monits.size(); i++){
            double latitude = monits.get(i).getLatitude();
            double longitude = monits.get(i).getLongitude();
            // if(addProximityAlert(latitude, longitude)){
            LatLng loc = new LatLng(latitude, longitude);
            Location locDestino = new Location("");
            locDestino.setLatitude(latitude);
            locDestino.setLongitude(longitude);
            if(location.distanceTo(locDestino) <= 5000){

                markerOptions.position(loc);
                markerOptions.title(monits.get(i).getNome());

                googleMap.addMarker(markerOptions);
            } else {
                //Toast.makeText(getActivity(), "Não está perto!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //Toast.makeText(getActivity(), "O Status foi Alterado!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(getActivity(), "GPS Habilitado!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(getActivity(), "GPS Desabilitado!", Toast.LENGTH_LONG).show();
    }
}