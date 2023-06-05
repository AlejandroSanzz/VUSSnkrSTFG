package com.example.vussnkrs.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.example.vussnkrs.productos.ProductosCesta;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ProductoAdapterCesta extends FirestoreRecyclerAdapter<ProductosCesta, ProductoAdapterCesta.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio, stock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrarCesta);
            talla = itemView.findViewById(R.id.imagenMostrarCesta);
            precio = itemView.findViewById(R.id.precioMostrarCesta);
            stock = itemView.findViewById(R.id.stockMostrarCesta);

        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapterCesta(@NonNull FirestoreRecyclerOptions<ProductosCesta> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductosCesta ProductosCesta) {
        holder.nombre.setText(ProductosCesta.getNombre());
        holder.talla.setText(ProductosCesta.getTalla());
        holder.precio.setText(ProductosCesta.getPrecio());
        holder.stock.setText(ProductosCesta.getStock());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle_cesta, parent, false);
                return new ViewHolder(v);
    }


}
