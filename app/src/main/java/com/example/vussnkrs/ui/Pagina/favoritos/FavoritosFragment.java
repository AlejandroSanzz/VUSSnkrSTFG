package com.example.vussnkrs.ui.Pagina.favoritos;

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
import android.widget.Toast;

import com.example.vussnkrs.BaseDeDatos;
import com.example.vussnkrs.R;

public class FavoritosFragment extends Fragment {

    private FavoritosViewModel mViewModel;

    public static FavoritosFragment newInstance() {
        return new FavoritosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavoritosViewModel.class);
        // TODO: Use the ViewModel
    }

    public void agregarAFavoritos(View view) {
        // Obtener el ID del producto actual
        //int productoId = obtenerIdProductoActual();

        // Agregar el ID del producto a la tabla de favoritos
        BaseDeDatos db = new BaseDeDatos(getActivity());
        SQLiteDatabase sqLiteDatabase = db.openWritable();

        ContentValues valores = new ContentValues();
        //valores.put("producto_id", productoId);

        sqLiteDatabase.insert("favoritos", null, valores);

        db.close(sqLiteDatabase);

        // Mostrar un mensaje de confirmación
        Toast.makeText(getActivity(), "Producto agregado a favoritos", Toast.LENGTH_SHORT).show();
    }


}