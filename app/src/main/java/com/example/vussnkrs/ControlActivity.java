package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
    }

    public BaseDeDatos basededatos = new BaseDeDatos(this);

    public void agregarProducto(View view) {
        EditText editTextSKU = findViewById(R.id.editTextSKU);
        String sku = editTextSKU.getText().toString();

        EditText editTextNombre = findViewById(R.id.editTextNombre);
        String nombre = editTextNombre.getText().toString();

        EditText editTextCategoria = findViewById(R.id.editTextCategoria);
        String categoria = editTextCategoria.getText().toString();

        EditText editTextDescripcion = findViewById(R.id.editTextDescripcion);
        String descripcion = editTextDescripcion.getText().toString();

        EditText editTextTalla = findViewById(R.id.editTextTalla);
        String talla = editTextTalla.getText().toString();

        EditText editTextPrecio = findViewById(R.id.editTextPrecio);
        String precio = editTextPrecio.getText().toString();
        /*
        EditText editTextImagen = findViewById(R.id.editTextImagen);
        String imagen = editTextImagen.getText().toString();
        */
        EditText editTextStock = findViewById(R.id.editTextStock);
        String stock = editTextStock.getText().toString();

        EditText editTextProveedor = findViewById(R.id.editTextProveedor);
        String proveedor = editTextProveedor.getText().toString();

        agregarProducto(sku, nombre, categoria, descripcion, talla, precio, stock, proveedor);
    }
    // Método para agregar un producto a la tabla "productos"
    public void agregarProducto(String sku, String nombre, String categoria, String descripcion, String talla,
                                String precio, /*String imagen,*/ String stock, String proveedor) {
        SQLiteDatabase db = basededatos.openWritable();
        ContentValues values = new ContentValues();
        values.put(BaseDeDatos.COLUMNA_SKU, sku);
        values.put(BaseDeDatos.COLUMNA_NOMBRE, nombre);
        values.put(BaseDeDatos.COLUMNA_CATEGORIA, categoria);
        values.put(BaseDeDatos.COLUMNA_DESCRIPCION, descripcion);
        values.put(BaseDeDatos.COLUMNA_TALLA, talla);
        values.put(BaseDeDatos.COLUMNA_PRECIO, precio);
        //values.put(BaseDeDatos.COLUMNA_IMAGEN, imagen);
        values.put(BaseDeDatos.COLUMNA_STOCK, stock);
        values.put(BaseDeDatos.COLUMNA_PROVEEDOR, proveedor);
        db.insert(BaseDeDatos.TABLA_PRODUCTOS, null, values);
        basededatos.close(db);
        Toast.makeText(this, "Se ha agregado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void eliminarProducto(View view){
        EditText editTextID = findViewById(R.id.editTextID);
        String id = editTextID.getText().toString();

        eliminarProducto(id);
    }
    // Método para eliminar un producto de la tabla "productos"
    public void eliminarProducto(String id) {
        SQLiteDatabase db = basededatos.openWritable();
        db.delete(BaseDeDatos.TABLA_PRODUCTOS, BaseDeDatos.COLUMNA_ID + "=?", new String[]{String.valueOf(id)});
        basededatos.close(db);
        Toast.makeText(this, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
    }

    // Método para actualizar un producto de la tabla "productos"
    public void actualizarProducto(int id, int sku, String nombre, String categoria, String descripcion, int talla,
                                   double precio, String imagen, int stock, String proveedor) {
        SQLiteDatabase db = basededatos.openWritable();
        ContentValues values = new ContentValues();
        values.put(BaseDeDatos.COLUMNA_SKU, sku);
        values.put(BaseDeDatos.COLUMNA_NOMBRE, nombre);
        values.put(BaseDeDatos.COLUMNA_CATEGORIA, categoria);
        values.put(BaseDeDatos.COLUMNA_DESCRIPCION, descripcion);
        values.put(BaseDeDatos.COLUMNA_TALLA, talla);
        values.put(BaseDeDatos.COLUMNA_PRECIO, precio);
        values.put(BaseDeDatos.COLUMNA_IMAGEN, imagen);
        values.put(BaseDeDatos.COLUMNA_STOCK, stock);
        values.put(BaseDeDatos.COLUMNA_PROVEEDOR, proveedor);
        db.update(BaseDeDatos.TABLA_PRODUCTOS, values, BaseDeDatos.COLUMNA_ID + "=?", new String[]{String.valueOf(id)});
        basededatos.close(db);
    }





/*
    EditText text_modelo, text_ntalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        text_modelo = findViewById(R.id.txt_modelo);
        text_ntalla = findViewById(R.id.txt_ntalla);
    }

    // Obtener los valores ingresados por el usuario
    int sku = Integer.parseInt(editTextSku.getText().toString());
    String nombre = editTextNombre.getText().toString();
    String categoria = editTextCategoria.getText().toString();
    String descripcion = editTextDescripcion.getText().toString();
    int talla = Integer.parseInt(txt_ntalla.getText().toString());
    double precio = Double.parseDouble(editTextPrecio.getText().toString());
    String imagen = ""; // Aquí deberías obtener la ruta o URL de la imagen
    int stock = Integer.parseInt(editTextStock.getText().toString());
    String nombreProveedor = editTextNombreProveedor.getText().toString();

    // Crear un objeto ContentValues con los valores ingresados
    ContentValues valores = new ContentValues();
    valores.put("SKU", sku);
    valores.put("Nombre", nombre);
    valores.put("Categoria", categoria);
    valores.put("Descripcion", descripcion);
    valores.put("Talla", talla);
    valores.put("Precio", precio);
    valores.put("Imagen", imagen);
    valores.put("Stock", stock);
    valores.put("NombreProveedor", nombreProveedor);

    // Obtener la instancia de la base de datos
    SQLiteDatabase db = basededatos.getWritableDatabase();

    // Insertar los valores en la tabla "productos"
    long resultado = db.insert("productos", null, valores);

    // Verificar si la inserción fue exitosa
    if (resultado == -1) {
        Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(this, "Producto agregado exitosamente", Toast.LENGTH_SHORT).show();
    }

    // Cerrar la base de datos
    db.close();
/*
    public boolean agregar (View view) {
        BaseDeDatos db = new BaseDeDatos(this, "snkrs", null, 1);
        SQLiteDatabase basedatos = db.getWritableDatabase();
        String modelo = text_modelo.getText().toString();
        String talla = text_ntalla.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Modelo", modelo);
        contentValues.put("Talla", talla);
        long resultado = basedatos.insert("snkrs", null, contentValues);
        Toast.makeText(this, "Se ha agregado correctamente", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ControlActivity.class);
        startActivity(i);
        return resultado != -1;
    }*/ /*
    ListasActivity la = new ListasActivity();
    public void agregar(Modelos modelo) {
        boolean existe = false;
        for (int i = 0; i < la.modelosDisponibles.size(); i++) {
            if (modelo.getNombre().equals(text_modelo)) {
                existe = true;
            }
        }
        if (!existe) {
            la.modelosDisponibles.add(new Modelos(text_modelo, text_talla));
            Toast.makeText(this, "Sneaker " + text_modelo + " añadida!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Esas sneakers ya están añadidas", Toast.LENGTH_SHORT).show();
        }
    }
    */
    public void salir (View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}