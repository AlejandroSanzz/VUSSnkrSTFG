package com.example.vussnkrs.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.example.vussnkrs.productos.ProductosFavoritos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductoAdapterFavoritos extends FirestoreRecyclerAdapter<ProductosFavoritos, ProductoAdapterFavoritos.ViewHolder> {
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio, stock;

        ImageView imageview_eliminar_favoritos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrarFavoritos);
            talla = itemView.findViewById(R.id.imagenMostrarFavoritos);
            precio = itemView.findViewById(R.id.precioMostrarFavoritos);
           // stock = itemView.findViewById(R.id.stockMostrarFavoritos);
            imageview_eliminar_favoritos = itemView.findViewById(R.id.imageview_eliminar_favoritos);
        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapterFavoritos(@NonNull FirestoreRecyclerOptions<ProductosFavoritos> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductosFavoritos ProductosFavoritos) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(ProductosFavoritos.getNombre());
        holder.talla.setText(ProductosFavoritos.getTalla());
        holder.precio.setText(ProductosFavoritos.getPrecio());
        //holder.stock.setText(ProductosFavoritos.getStock());

        holder.imageview_eliminar_favoritos.setOnClickListener(new View.OnClickListener() {
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
        mFirestore.collection("productFavoritos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle_favoritos, parent, false);
        return new ViewHolder(v);
    }


}