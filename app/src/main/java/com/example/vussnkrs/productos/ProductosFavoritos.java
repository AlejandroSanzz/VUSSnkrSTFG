package com.example.vussnkrs.productos;

public class ProductosFavoritos {
    String SKU, nombre, categoria, descripcion, talla, precio, stock, nproveedor;
    public ProductosFavoritos(){}

    public ProductosFavoritos(String SKU, String nombre, String categoria, String descripcion, String talla, String precio, String stock, String nproveedor){
        this.SKU = SKU;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
        this.nproveedor = nproveedor;

    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getNproveedor() {
        return nproveedor;
    }

    public void setNproveedor(String nproveedor) {
        this.nproveedor = nproveedor;
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