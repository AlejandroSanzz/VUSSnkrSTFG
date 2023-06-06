package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vussnkrs.adapter.ProductoAdapterCesta;
import com.example.vussnkrs.adapter.ProductoAdapterFavoritos;
import com.example.vussnkrs.productos.ProductosCesta;
import com.example.vussnkrs.productos.ProductosFavoritos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FavoritosActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    ProductoAdapterFavoritos mAdapter;
    FirebaseFirestore mFirestore;

    // Obtén el ID de usuario guardado en SharedPreferences
    //SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
    //String userID = sharedPreferences.getString("userID", "");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        // Configurar la AppBar
        Toolbar toolbar = findViewById(R.id.toolbarfavoritos);
        setSupportActionBar(toolbar);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingleFavoritos);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();

        Query query = mFirestore.collection("user").document(userId).collection("productFavoritos");

        FirestoreRecyclerOptions<ProductosFavoritos> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<ProductosFavoritos>()
                        .setQuery(query, ProductosFavoritos.class)
                        .build();

        mAdapter = new ProductoAdapterFavoritos(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
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
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onBackPressed () {
        System.exit(0); // Cierra la actividad actual
    }
}