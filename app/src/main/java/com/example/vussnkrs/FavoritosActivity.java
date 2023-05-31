package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class FavoritosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        // Configurar la AppBar
        Toolbar toolbar = findViewById(R.id.toolbarfavoritos);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutienda, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Comprar:
                Toast.makeText(this, "Vas a entrar en la tienda", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FavoritosActivity.this, TiendaActivity.class));
                return true;
            case R.id.Favoritos:
                Toast.makeText(this, "Vas a entrar en tus favoritos", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FavoritosActivity.this, FavoritosActivity.class));
                return true;
            case R.id.Cesta:
                Toast.makeText(this, "Vas a entrar en tu cesta", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FavoritosActivity.this, CestaActivity.class));
                return true;
            case R.id.Perfil:
                Toast.makeText(this,"Vas a entrar en tu perfil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FavoritosActivity.this, PerfilActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}