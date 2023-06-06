package com.example.vussnkrs.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.CestaActivity;
import com.example.vussnkrs.EleccionAdminActivity;
import com.example.vussnkrs.MainActivity;
import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.example.vussnkrs.productos.ProductosCesta;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductoAdapterCesta extends FirestoreRecyclerAdapter<ProductosCesta, ProductoAdapterCesta.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio, stock;//precioTotal;

        ImageView imageview_eliminar_cesta;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrarCesta);
            talla = itemView.findViewById(R.id.imagenMostrarCesta);
            precio = itemView.findViewById(R.id.precioMostrarCesta);
            stock = itemView.findViewById(R.id.stockMostrarCesta);
            imageview_eliminar_cesta = itemView.findViewById(R.id.imageview_eliminar_cesta);
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
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductosCesta ProductosCesta) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(ProductosCesta.getNombre());
        holder.talla.setText(ProductosCesta.getTalla());
        holder.precio.setText(ProductosCesta.getPrecio());
        holder.stock.setText(ProductosCesta.getStock());

        holder.imageview_eliminar_cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("¿Desea eliminar este producto?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteProduct(id); //Elimina el producto de favoritos

                            }
                        })
                        .setNegativeButton("No", null) // No hace nada, simplemente cierra el diálogo
                        .show();

            }
        });
    }

    private void deleteProduct(String id) {
        FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userID = currentUser.getUid();

        mfirestore.collection("user").document(userID).collection("productCesta").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle_cesta, parent, false);
                return new ViewHolder(v);
    }


}
