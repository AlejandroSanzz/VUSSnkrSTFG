package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListasActivity extends AppCompatActivity {

    TextView txt_saludo;
    SharedPreferences pref;
    String talla;
    //String gtalla;
    private ListView listaProductos;
    private ArrayList<BaseDeDatos> productos;
    //private AdaptadorProductos adaptadorProductos;
    private BaseDeDatos baseDeDatos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        //listaProductos = findViewById(R.id.listaProductos);
        productos = new ArrayList<>();
        baseDeDatos = new BaseDeDatos(this);

        //mostrarProductos();

        pref = getSharedPreferences(getString(R.string.pref_talla), Context.MODE_PRIVATE);
        talla = pref.getString("talla", "0");
        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre");
        txt_saludo = findViewById(R.id.txt_saludo);
        txt_saludo.setText("Bienvenido a VUSSnkrS " + nombre + " selecciona tu talla");

        // Obtener la lista de productos de la base de datos
        ArrayList<Producto> productos = obtenerProductosDesdeBD();

        // Mostrar la lista de productos en la vista
        ListView listView = findViewById(R.id.listas);
        ArrayAdapter<Producto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);
        listView.setAdapter(adapter);

    }

    public void inicio (View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    private ArrayList<Producto> obtenerProductosDesdeBD() {
        ArrayList<Producto> productos = new ArrayList<>();

        // Crear una instancia de la base de datos
        BaseDeDatos db = new BaseDeDatos(this);

        // Abrir la base de datos con permisos de escritura
        SQLiteDatabase sqLiteDatabase = db.openWritable();

        // Definir las columnas que se desean obtener
        String[] columnas = {BaseDeDatos.COLUMNA_ID, BaseDeDatos.COLUMNA_SKU, BaseDeDatos.COLUMNA_NOMBRE, BaseDeDatos.COLUMNA_CATEGORIA, BaseDeDatos.COLUMNA_DESCRIPCION, BaseDeDatos.COLUMNA_TALLA, BaseDeDatos.COLUMNA_PRECIO, BaseDeDatos.COLUMNA_IMAGEN, BaseDeDatos.COLUMNA_STOCK, BaseDeDatos.COLUMNA_PROVEEDOR};

        // Obtener los datos de la tabla "productos"
        Cursor cursor = sqLiteDatabase.query(BaseDeDatos.TABLA_PRODUCTOS, columnas, null, null, null, null, null);

        // Iterar a través de los datos del cursor y crear objetos Producto
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(BaseDeDatos.COLUMNA_ID));
            @SuppressLint("Range") int sku = cursor.getInt(cursor.getColumnIndex(BaseDeDatos.COLUMNA_SKU));
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_NOMBRE));
            @SuppressLint("Range") String categoria = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_CATEGORIA));
            @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_DESCRIPCION));
            @SuppressLint("Range") int talla = cursor.getInt(cursor.getColumnIndex(BaseDeDatos.COLUMNA_TALLA));
            @SuppressLint("Range") double precio = cursor.getDouble(cursor.getColumnIndex(BaseDeDatos.COLUMNA_PRECIO));
            @SuppressLint("Range") String imagen = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_IMAGEN));
            @SuppressLint("Range") int stock = cursor.getInt(cursor.getColumnIndex(BaseDeDatos.COLUMNA_STOCK));
            @SuppressLint("Range") String proveedor = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_PROVEEDOR));

            Producto producto = new Producto(id, sku, nombre, categoria, descripcion, talla, precio, imagen, stock, proveedor);
            productos.add(producto);
        }

        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close(sqLiteDatabase);

        return productos;
    }

    /*
    private void mostrarProductos() {
        SQLiteDatabase db = baseDeDatos.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BaseDeDatos.TABLA_PRODUCTOS, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String sku = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_SKU));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_NOMBRE));
                @SuppressLint("Range") String categoria = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_CATEGORIA));
                @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_DESCRIPCION));
                @SuppressLint("Range") String talla = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_TALLA));
                @SuppressLint("Range") String precio = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_PRECIO));
                @SuppressLint("Range") String imagen = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_IMAGEN));
                @SuppressLint("Range") String stock = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_STOCK));
                @SuppressLint("Range") String proveedor = cursor.getString(cursor.getColumnIndex(BaseDeDatos.COLUMNA_PROVEEDOR));

                //BaseDeDatos producto = new BaseDeDatos(sku, nombre, categoria, descripcion, talla, precio, imagen, stock, proveedor);
                //productos.add(producto);
            } while (cursor.moveToNext());
        }

        //adaptadorProductos = new AdaptadorProductos(this, productos);
        //listaProductos.setAdapter(adaptadorProductos);

        cursor.close();
        db.close();
    }
    public void mostrarProductos(ListView listView) {
        // Obtener una referencia a la base de datos
        SQLiteDatabase db = getReadableDatabase();

        // Definir una lista para almacenar los datos
        List<String> productos = new ArrayList<>();

        // Definir la consulta SQL
        String consulta = "SELECT * FROM " + TABLA_PRODUCTOS;

        // Ejecutar la consulta y obtener un cursor con los resultados
        Cursor cursor = db.rawQuery(consulta, null);

        // Recorrer el cursor y agregar los datos a la lista
        if (cursor.moveToFirst()) {
            do {
                String producto = "ID: " + cursor.getInt(0) + ", SKU: " + cursor.getInt(1) + ", Nombre: " + cursor.getString(2)
                        + ", Categoría: " + cursor.getString(3) + ", Descripción: " + cursor.getString(4) + ", Talla: " + cursor.getInt(5)
                        + ", Precio: " + cursor.getFloat(6) + ", Imagen: " + cursor.getString(7) + ", Stock: " + cursor.getInt(8)
                        + ", Proveedor: " + cursor.getString(9);
                productos.add(producto);
            } while (cursor.moveToNext());
        }

        // Cerrar el cursor y la base de datos
        cursor.close();
        db.close();

        // Mostrar los datos en la aplicación (por ejemplo, en un ListView)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);
        listView.setAdapter(adapter);
    }*/


}