package com.example.vussnkrs.productos;

public class Productos {
    String nombre, talla, precio;
    public Productos(){}

    public Productos(String nombre, String talla, String precio){
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
