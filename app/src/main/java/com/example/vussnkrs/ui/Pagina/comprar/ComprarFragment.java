package com.example.vussnkrs.ui.Pagina.comprar;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vussnkrs.R;
import com.example.vussnkrs.adapter.ProductoAdapter;
import com.example.vussnkrs.productos.Productos;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ComprarFragment extends Fragment {

    private ComprarViewModel mViewModel;

    public static ComprarFragment newInstance() {
        return new ComprarFragment();
    }

    RecyclerView mRecycler;
    ProductoAdapter mAdapter;
    FirebaseFirestore mFirestore;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comprar, container, false);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = view.findViewById(R.id.recyclerViewSingle);
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
        mViewModel = new ViewModelProvider(requireActivity()).get(ComprarViewModel.class);
        // TODO: Use the ViewModel
    }
}