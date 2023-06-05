package com.example.vussnkrs.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.CreateProductActivity;
import com.example.vussnkrs.MainActivity;
import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Productos, ProductoAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio;

        Button button_cesta_producto, button_favorito_producto;

        FirebaseFirestore mfirestore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrar);
            talla = itemView.findViewById(R.id.imagenMostrar);
            precio = itemView.findViewById(R.id.precioMostrar);
            button_cesta_producto = itemView.findViewById(R.id.button_cesta_producto);

            mfirestore = FirebaseFirestore.getInstance();

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

        holder.button_cesta_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProduct = holder.nombre.getText().toString().trim();
                String tallaProduct = holder.talla.getText().toString().trim();
                String precioProduct = holder.precio.getText().toString().trim();

                postProduct(nombreProduct, tallaProduct, precioProduct);
            }
        });
    }

    private void postProduct(String nombreProduct,String tallaProduct, String precioProduct) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);

        FirebaseFirestore mfirestore;
        mfirestore = FirebaseFirestore.getInstance();
        mfirestore.collection("productCesta").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle, parent, false);
                return new ViewHolder(v);
    }


}
