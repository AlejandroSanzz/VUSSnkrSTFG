package com.example.vussnkrs.ui.Manage.mostrar;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vussnkrs.BaseDeDatos;
import com.example.vussnkrs.Producto;
import com.example.vussnkrs.R;

import java.util.ArrayList;

public class MostrarFragment extends Fragment {

    private MostrarViewModel mViewModel;

    private ListView listaBBDD;
    private ArrayAdapter<Producto> pAdapter;

    public static MostrarFragment newInstance() {
        return new MostrarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar, container, false);

        listaBBDD = view.findViewById(R.id.listaBBDD);
        pAdapter = new ArrayAdapter<Producto>(getActivity(), android.R.layout.simple_list_item_1, obtenerProductosDesdeBD());
        listaBBDD.setAdapter(pAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MostrarViewModel.class);
        // TODO: Use the ViewModel
    }

    private ArrayList<Producto> obtenerProductosDesdeBD() {
        ArrayList<Producto> productos = new ArrayList<>();

        // Crear una instancia de la base de datos
        BaseDeDatos db = new BaseDeDatos(getActivity());

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
}