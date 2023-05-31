package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EleccionAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_admin);
    }

    public void irAdmin (View view){
        startActivity(new Intent(EleccionAdminActivity.this, ManageActivity.class));
    }

    public void irTienda (View view){
        startActivity(new Intent(EleccionAdminActivity.this, TiendaActivity.class));
    }
}