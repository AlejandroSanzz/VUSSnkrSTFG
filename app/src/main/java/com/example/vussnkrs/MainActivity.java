package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    EditText text_nombre, text_talla;
    String miNombre, miTalla;
    TextView text_bienvenida;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //preft = getSharedPreferences(getString(R.string.talla), Context.MODE_PRIVATE);
       /* text_talla = findViewById(R.id.text_talla);
        SharedPreferences pref = getSharedPreferences(getString(R.string.pref_talla), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("talla", text_talla.getText().toString());
        editor.apply();
        txt_saludo.setText("Bienvenido a VUSSnkrS " + nombre + " tu talla es la " + talla);
        */pref = getPreferences(Context.MODE_PRIVATE);
       // text_nombre = findViewById(R.id.txt_nombre);
        miNombre = pref.getString("miNombre","stranger");
        /*text_talla = findViewById(R.id.text_talla);
        miTalla = pref.getString("miTalla","0");
        *///nVeces = Integer.parseInt(prefv.getString("nVeces","0"));
        //text_veces = findViewById(R.id.text_veces);
        text_bienvenida = findViewById(R.id.txt_bienvenida);
        text_bienvenida.setText("Bienvenido de nuev@ " + miNombre + "!!");
    }

    public void irTienda (View view) {
        Intent i = new Intent(this, PaginaActivity.class);
        startActivity(i);
        /*
        pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("miNombre", text_nombre.getText().toString());
        //editor.putString("miTalla", text_talla.getText().toString());
        editor.apply();
        text_nombre = findViewById(R.id.txt_nombre);
        String campo1 = text_nombre.getText().toString();
        if(!campo1.isEmpty()) {
            Intent i = new Intent(this, PaginaActivity.class);
            text_email = findViewById(R.id.txt_nombre);
            i.putExtra("nombre", text_nombre.getText().toString());
            startActivity(i);
        } else {
            text_nombre.setError("El campo nombre es obligatorio");
        }*/

    }

    public void admin (View view) {
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
    }
}