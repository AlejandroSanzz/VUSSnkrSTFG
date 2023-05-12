package com.example.vussnkrs.ui.Manage.eliminar;

import androidx.lifecycle.ViewModelProvider;

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

public class EliminarFragment extends Fragment {

    private EliminarViewModel mViewModel;

    public static EliminarFragment newInstance() {
        return new EliminarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eliminar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EliminarViewModel.class);
        // TODO: Use the ViewModel
    }

    public BaseDeDatos basededatos;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        basededatos = new BaseDeDatos(requireContext());
        Button btn_eliminar = requireView().findViewById(R.id.btn_eliminar);
        btn_eliminar.setOnClickListener(this::eliminarProducto);
    }

    public void eliminarProducto(View view){
        EditText editTextID = requireView().findViewById(R.id.editTextID);
        String id = editTextID.getText().toString();

        eliminarProducto(id);
    }
    // Método para eliminar un producto de la tabla "productos"
    public void eliminarProducto(String id) {
        SQLiteDatabase db = basededatos.openWritable();
        db.delete(BaseDeDatos.TABLA_PRODUCTOS, BaseDeDatos.COLUMNA_ID + "=?", new String[]{String.valueOf(id)});
        basededatos.close(db);
        Toast.makeText(getContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
    }
}