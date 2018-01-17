package com.example.projetodoscria.fragment;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projetodoscria.Models.Monitores;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    private GoogleMap googleMap;
    private LocationManager locationManager;
    ArrayList<Monitores> monits = new ArrayList<Monitores>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Ativa o GPS
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);//utilizar recurso com cuidado. Consome muita bateria.  primeiro 0 = tempo mínimo em ms. Segundo 0 = distância em metros para atualização
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        Location locationAtual = new Location("");

        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            googleMap = map;
            googleMap.setOnMapClickListener(this);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setMyLocationEnabled(true);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(locationAtual.getLatitude(), locationAtual.getLongitude()));
            markerOptions.title("Localização Atual");

            for (int i = 0; i < 4; i++) {
                monits.add(new Monitores("Ponto 0", -22.824371412016053, -43.30047223716974));
                monits.add(new Monitores("Ponto 1", -22.9, -43.1));
                monits.add(new Monitores("Ponto 2", -22.822915903955465, -43.29979196190834));
            }

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
        Toast.makeText(getActivity(), "O Status foi Alterado!", Toast.LENGTH_LONG).show();
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