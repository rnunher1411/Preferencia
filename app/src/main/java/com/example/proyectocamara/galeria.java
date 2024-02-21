package com.example.proyectocamara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class galeria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        final String extra = getIntent().getStringExtra("lista");
        final ListView listaBit = findViewById(R.id.listaBit);
        listaBit.setAdapter(extra);

    }
}