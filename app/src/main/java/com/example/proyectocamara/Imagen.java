package com.example.proyectocamara;

import java.io.Serializable;

public class Imagen implements Serializable {

    private byte[] imagen;

    public Imagen(byte[] imagen) {

        this.imagen = imagen;

    }

    public byte[] getImagen() {

        return imagen;

    }

}
