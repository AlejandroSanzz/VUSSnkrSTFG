package com.example.vussnkrs.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.vussnkrs.R;import com.example.vussnkrs.productos.ProductosManage;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductoAdapterManage extends FirestoreRecyclerAdapter<ProductosManage, ProductoAdapterManage.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio;
        Button button_eliminar_manage, button_editar_manage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrarCesta);
            talla = itemView.findViewById(R.id.imagenMostrarCesta);
            precio = itemView.findViewById(R.id.precioMostrarCesta);
            button_eliminar_manage = itemView.findViewById(R.id.button_cesta_producto);
            button_editar_manage = itemView.findViewById(R.id.button_favorito_producto);
        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapterManage(@NonNull FirestoreRecyclerOptions<ProductosManage> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductosManage ProductosManage) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(ProductosManage.getNombre());
        holder.talla.setText(ProductosManage.getTalla());
        holder.precio.setText(ProductosManage.getPrecio());

        holder.button_eliminar_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setMessage("¿Desea eliminar este producto?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteProduct(id); //Elimina el producto
                            }
                        })
                        .setNegativeButton("No", null) // No hace nada, simplemente cierra el diálogo
                        .show();

            }
        });

        holder.button_editar_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, CreateProductActivity.class );
                i.putExtra("id_product", id);
                activity.startActivity(i);
            }
        });
    }

    private void deleteProduct(String id) {
        mFirestore.collection("product").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_sinle_manage, parent, false);
                return new ViewHolder(v);
    }


}
