package com.example.proyectocamara;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceView;

public class activity_visor_camara extends AppCompatActivity {

    Camera camara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_camara);

        SurfaceView view = findViewById(R.id.visorCamara);
        camara = Camera.open();


    }

    @Override
    protected void onPause() {

        super.onPause();

    }

    @Override
    protected void onResume() {

        super.onResume();

    }

}