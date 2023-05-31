package com.example.vussnkrs.ui.Manage.mostrar;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vussnkrs.R;
import com.example.vussnkrs.adapter.ProductoAdapter;
import com.example.vussnkrs.productos.Productos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class MostrarFragment extends Fragment {

    private MostrarViewModel mViewModel;

    RecyclerView mRecycler;
    ProductoAdapter mAdapter;
    FirebaseFirestore mFirestore;

    public static MostrarFragment newInstance() {
        return new MostrarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar, container, false);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = view.findViewById(R.id.recyclerViewSingle2);
        mRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        Query query = mFirestore.collection("product");

        FirestoreRecyclerOptions<Productos> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Productos>()
                        .setQuery(query, Productos.class)
                        .build();

        mAdapter = new ProductoAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MostrarViewModel.class);
        // TODO: Use the ViewModel
    }

}