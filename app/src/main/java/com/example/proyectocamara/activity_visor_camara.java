package com.example.proyectocamara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class activity_visor_camara extends AppCompatActivity {

    Camera camara;
    ArrayList<Imagen> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_camara);

        SurfaceView view = findViewById(R.id.visorCamara);
        camara = Camera.open();
        camara.setDisplayOrientation(90);



        Button botonCaptura = findViewById(R.id.botonCaptura);
        botonCaptura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                camara.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {

                        lista.add(new Imagen(data));
                        camara.startPreview();

                    }
                });

            }
        });

        Button botonGaleria = findViewById(R.id.botonGaleria);
        botonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(activity_visor_camara.this, galeria.class);
                intent.putExtra("listaBit", lista);
                startActivity(intent);

            }
        });

        SurfaceHolder holder = view.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {

                try {

                    camara.setPreviewDisplay(holder);
                    camara.startPreview();

                } catch (IOException e) {

                    throw new RuntimeException(e);

                }

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        this.lista =new ArrayList<>();

    }

    @Override
    protected void onResume() {

        super.onResume();

    }

}