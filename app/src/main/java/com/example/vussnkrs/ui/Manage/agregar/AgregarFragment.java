package com.example.vussnkrs.ui.Manage.agregar;

import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vussnkrs.BaseDeDatos;
import com.example.vussnkrs.R;

public class AgregarFragment extends Fragment {

    private AgregarViewModel mViewModel;

    public static AgregarFragment newInstance() {
        return new AgregarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agregar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgregarViewModel.class);
        // TODO: Use the ViewModel
    }

    public BaseDeDatos basededatos;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        basededatos = new BaseDeDatos(requireContext());
        Button btn_agregar = requireView().findViewById(R.id.btn_agregar);
        btn_agregar.setOnClickListener(this::agregarProducto);
    }

    public void agregarProducto(View view) {
        EditText editTextSKU = requireView().findViewById(R.id.editTextSKU);
        String sku = editTextSKU.getText().toString();

        EditText editTextNombre = requireView().findViewById(R.id.editTextNombre);
        String nombre = editTextNombre.getText().toString();

        EditText editTextCategoria = requireView().findViewById(R.id.editTextCategoria);
        String categoria = editTextCategoria.getText().toString();

        EditText editTextDescripcion = requireView().findViewById(R.id.editTextDescripcion);
        String descripcion = editTextDescripcion.getText().toString();

        EditText editTextTalla = requireView().findViewById(R.id.editTextTalla);
        String talla = editTextTalla.getText().toString();

        EditText editTextPrecio = requireView().findViewById(R.id.editTextPrecio);
        String precio = editTextPrecio.getText().toString();
        /*
        EditText editTextImagen = requireView().findViewById(R.id.editTextImagen);
        String imagen = editTextImagen.getText().toString();
        */
        EditText editTextStock = requireView().findViewById(R.id.editTextStock);
        String stock = editTextStock.getText().toString();

        EditText editTextProveedor = requireView().findViewById(R.id.editTextProveedor);
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
        Toast.makeText(getContext(), "Se ha agregado correctamente", Toast.LENGTH_SHORT).show();
    }


}