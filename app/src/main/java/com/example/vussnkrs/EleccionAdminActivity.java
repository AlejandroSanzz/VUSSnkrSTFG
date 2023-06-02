package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EleccionAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_admin);
    }

    public void Admin(View view) {
        finish();
        startActivity(new Intent(EleccionAdminActivity.this, ManageActivityMostrar.class));
        Toast.makeText(EleccionAdminActivity.this, "Pantalla de Admin.", Toast.LENGTH_SHORT).show();
    }

    public void AdminAgregar(View view) {
        finish();
        startActivity(new Intent(EleccionAdminActivity.this, CreateProductActivity.class));
        Toast.makeText(EleccionAdminActivity.this, "Pantalla de Admin.", Toast.LENGTH_SHORT).show();
    }

    public void irTienda (View view){
        startActivity(new Intent(EleccionAdminActivity.this, TiendaActivity.class));
        Toast.makeText(EleccionAdminActivity.this, "Bienvenido a la tienda", Toast.LENGTH_SHORT).show();
    }
}