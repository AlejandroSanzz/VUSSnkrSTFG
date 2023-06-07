package com.example.vussnkrs.productos;

public class ProductosManage {
    String SKU, nombre, categoria, descripcion, talla, precio, stock, nproveedor, foto;
    public ProductosManage(){}

    public ProductosManage(String SKU, String nombre, String categoria, String descripcion, String talla, String precio, String stock, String nproveedor, String foto){
        this.SKU = SKU;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
        this.nproveedor = nproveedor;
        this.foto = foto;

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
