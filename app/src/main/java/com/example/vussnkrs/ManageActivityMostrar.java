package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.vussnkrs.adapter.ProductoAdapter;
import com.example.vussnkrs.adapter.ProductoAdapterManage;

import com.example.vussnkrs.productos.ProductosManage;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ManageActivityMostrar extends AppCompatActivity {

    RecyclerView mRecycler;
    ProductoAdapterManage mAdapter;
    FirebaseFirestore mFirestore;

    SearchView search_view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_mostrar);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle3);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("product");

        FirestoreRecyclerOptions<ProductosManage> firestoreRecyclerOptionsManage =
                new FirestoreRecyclerOptions.Builder<ProductosManage>()
                        .setQuery(query, ProductosManage.class)
                        .build();

        mAdapter = new ProductoAdapterManage(firestoreRecyclerOptionsManage, this);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        search_view = findViewById(R.id.search);
        search_view();
    }

    private void search_view () {
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }

    public void textSearch(String s) {
        Query query = mFirestore.collection("product");
        FirestoreRecyclerOptions<ProductosManage> firestoreRecyclerOptionsManage =
                new FirestoreRecyclerOptions.Builder<ProductosManage>()
                        .setQuery(query.orderBy("nombre").startAt(s).endAt(s + "-"), ProductosManage.class).build();

        mAdapter = new ProductoAdapterManage(firestoreRecyclerOptionsManage, this);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
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
        new AlertDialog.Builder(this)
                .setMessage("¿Desea salir de la pantalla?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ManageActivityMostrar.this, EleccionAdminActivity.class));
                    }
                })
                .setNegativeButton("No", null) // No hace nada, simplemente cierra el diálogo
                .show();
    }
}