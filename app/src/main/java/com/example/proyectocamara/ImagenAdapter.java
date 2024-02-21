package com.example.proyectocamara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ImagenAdapter extends ArrayAdapter {

    private List<Imagen> imagenes;
    public ImagenAdapter(@NonNull Context context, List<Imagen> list) {

        super(context, 0, list);

        this.imagenes = list;

    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        return initView(position, convertView, parent);

    };

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);

    }

    private View initView(byte[] position, View convertView, ViewGroup parent) {

        final View vistaPersonal = LayoutInflater.from(getContext()).inflate(R.layout.galeria, parent, false);

        ImageView image = vistaPersonal.findViewById(R.id.captura);
        Imagen currentItem = image.setImageBitmap(position);

        if (currentItem != null) {

            //textViewName.setText(currentItem.getNombre());

        }

        return vistaPersonal;

    }




}
