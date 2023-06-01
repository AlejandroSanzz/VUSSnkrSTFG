package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.vussnkrs.adapter.ProductoAdapterManage;

import com.example.vussnkrs.productos.ProductosManage;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class ManageActivityMostrar extends AppCompatActivity {

    RecyclerView mRecycler;
    ProductoAdapterManage mAdapter;
    FirebaseFirestore mFirestore;

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
}