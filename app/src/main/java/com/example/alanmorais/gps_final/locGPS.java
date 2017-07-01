package com.example.alanmorais.gps_final;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created by alanmorais on 30/06/17.
 */

public class locGPS implements LocationListener {

    private Context context;
    private LocationManager locationManager;
    private Location location;

    private static final int DINSTANCIA_MINIMA = 0;
    private static final int TEMPO_MINIMO = 0;

    public locGPS(Context context) {

        this.context = context;

        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

        //Verificando se o GPS está ligado
        boolean isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);

        if(!isGPSEnable && !isNetworkEnable) {
            throw new RuntimeException("favor ligar GPS ou prover uma conexão com a internet");
        }else{
            if(isNetworkEnable) {
                //Transformando essa classe em um listener de eventor relacionados ao GPS
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, TEMPO_MINIMO, DINSTANCIA_MINIMA, this);

                //Adquirindo o último location capturado pelo gps
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if(isGPSEnable) {
                //Transformando essa classe em um listener de eventor relacionados ao GPS
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TEMPO_MINIMO, DINSTANCIA_MINIMA, this);

                //Adquirindo o último location capturado pelo gps
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
