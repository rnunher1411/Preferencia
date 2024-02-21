package com.example.proyectocamara;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CamaraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        FloatingActionButton boton = findViewById(R.id.openCameraButton);
        pedirPermisosGps();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedirPermisos();
            }
        });

    }

    private void pedirPermisosGps ()  {

        boolean tienePermisosGPS = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(!tienePermisosGPS) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }else {


            activarGPS();


        }

    }

    @SuppressWarnings("MissingPermission")
    private void activarGPS() {

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            LocationListener locationListener = new LocationListener() {

                @Override
                public void onLocationChanged(@NonNull Location location) {

                    Toast.makeText( CamaraActivity.this,
                            "Latitud: " + location.getLatitude() +
                                    "   Longitud: " + location.getLongitude(), Toast.LENGTH_LONG
                    ).show();

                }

            };

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

    }

    private void pedirPermisos() {

        boolean tenemosPermisosCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if(!tenemosPermisosCamera) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        }else {

            Intent intentParaCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intentParaCamara, 2);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == -1) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView image = findViewById(R.id.capturaCamara);
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bitmap);

        }


    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                activarGPS();

            }

        }

    }

}