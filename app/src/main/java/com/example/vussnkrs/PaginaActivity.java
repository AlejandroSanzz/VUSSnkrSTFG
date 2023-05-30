package com.example.vussnkrs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vussnkrs.ui.Manage.adapter.ProductAdapter;
import com.example.vussnkrs.ui.Manage.modelo.Product;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.databinding.ActivityPaginaBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class PaginaActivity extends AppCompatActivity {

   /* RecyclerView mRecycler;
    ProductAdapter mAdapter;
    FirebaseFirestore mFirestore;
    private ActivityPaginaBinding binding;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina);
        /*mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingle);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        Query query = mFirestore.collection("product");

        FirestoreRecyclerOptions<Product> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Product>().setQuery(query, Product.class).build();
        mAdapter = new ProductAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        binding = ActivityPaginaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_comprar, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_pagina);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }
/*
    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }*/
}
/*

        ArrayAdapter<String> prefAdaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, preferenciasDeCompra);
        prefCompra.setAdapter(prefAdaptador);


        tallasDisponiblesHombre = new ArrayList<>();

        tallasDisponiblesHombre.add("EU 38.5");
        tallasDisponiblesHombre.add("EU 39");
        tallasDisponiblesHombre.add("EU 40");
        tallasDisponiblesHombre.add("EU 40.5");
        tallasDisponiblesHombre.add("EU 41");
        tallasDisponiblesHombre.add("EU 42");
        tallasDisponiblesHombre.add("EU 42.5");
        tallasDisponiblesHombre.add("EU 43");
        tallasDisponiblesHombre.add("EU 44");
        tallasDisponiblesHombre.add("EU 44.5");
        tallasDisponiblesHombre.add("EU 45");
        tallasDisponiblesHombre.add("EU 45.5");
        tallasDisponiblesHombre.add("EU 46");
        tallasDisponiblesHombre.add("EU 47");
        tallasDisponiblesHombre.add("EU 47.5");
        tallasDisponiblesHombre.add("EU 48");
        tallasDisponiblesHombre.add("EU 48.5");
        tallasDisponiblesHombre.add("EU 49.5");
        tallasDisponiblesHombre.add("EU 50.5");
        tallasDisponiblesHombre.add("EU 51.5");
        tallasDisponiblesHombre.add("EU 52.5");

        tallasDisponiblesMujer = new ArrayList<>();

        tallasDisponiblesMujer.add("EU 34.5");
        tallasDisponiblesMujer.add("EU 35");
        tallasDisponiblesMujer.add("EU 35.5");
        tallasDisponiblesMujer.add("EU 36");
        tallasDisponiblesMujer.add("EU 36.5");
        tallasDisponiblesMujer.add("EU 37.5");
        tallasDisponiblesMujer.add("EU 38");
        tallasDisponiblesMujer.add("EU 38.5");
        tallasDisponiblesMujer.add("EU 39");
        tallasDisponiblesMujer.add("EU 40");
        tallasDisponiblesMujer.add("EU 40.5");
        tallasDisponiblesMujer.add("EU 41");
        tallasDisponiblesMujer.add("EU 42");
        tallasDisponiblesMujer.add("EU 42.5");
        tallasDisponiblesMujer.add("EU 43");
        tallasDisponiblesMujer.add("EU 44");
        tallasDisponiblesMujer.add("EU 44.5");

        tallaSpinner = findViewById(R.id.nzapatillas_spinner);
        ArrayAdapter<String> tallaAdaptadorHombre = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tallasDisponiblesHombre);
        tallaSpinner.setAdapter(tallaAdaptadorHombre);

        tallaSpinner = findViewById(R.id.nzapatillas_spinner);
        ArrayAdapter<String> tallaAdaptadorMujer = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tallasDisponiblesMujer);
        tallaSpinner.setAdapter(tallaAdaptadorMujer);
    }

} */