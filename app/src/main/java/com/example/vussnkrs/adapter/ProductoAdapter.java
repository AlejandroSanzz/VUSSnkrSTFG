package com.example.vussnkrs.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Productos, ProductoAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrar);
            talla = itemView.findViewById(R.id.imagenMostrar);
            precio = itemView.findViewById(R.id.precioMostrar);
        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapter(@NonNull FirestoreRecyclerOptions<Productos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Productos Productos) {
        holder.nombre.setText(Productos.getNombre());
        holder.talla.setText(Productos.getTalla());
        holder.precio.setText(Productos.getPrecio());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle, parent, false);
                return new ViewHolder(v);
    }


}
