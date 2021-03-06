package com.example.xdiaz.uf2activitat3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Auxili extends AppCompatActivity implements LocationListener {

    Double latitud;
    Double longitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxili);

        LocationManager gestorLloc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gestorLloc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


    }

    @Override
    public void onLocationChanged(Location location) {
        latitud=location.getLatitude();
        longitud=location.getLongitude();

        String text="Posicio actual:\n" +
                "Latitud: "+location.getLatitude()+"\n"+
                "Longitud: "+location.getLongitude();
        //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String missatge = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                missatge = "GPS status: Out of service";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                missatge = "GPS status: Temporarily unavailable";
                break;
            case LocationProvider.AVAILABLE:
                missatge = "GPS status: Available";
                break;
        }
        Toast.makeText(getApplicationContext(),
                missatge,
                Toast.LENGTH_LONG).show();

    }



    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getApplicationContext(),
                "GPS habilitat per l'usuari",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getApplicationContext(),
                "GPS desactivat per l'usuari",
                Toast.LENGTH_LONG ).show();

    }

    public void onClicAuxilio(View v){

        String text="Posicio actual:\n" +
                "Latitud: "+latitud+"\n"+
                "Longitud: "+longitud;
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();



    }


}
