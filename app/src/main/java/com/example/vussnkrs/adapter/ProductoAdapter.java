package com.example.vussnkrs.adapter;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vussnkrs.CreateProductActivity;
import com.example.vussnkrs.R;
import com.example.vussnkrs.productos.Productos;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ProductoAdapter extends FirestoreRecyclerAdapter<Productos, ProductoAdapter.ViewHolder> {

    private Context context;
    FirebaseFirestore mfirestore;
    ProgressDialog progressDialog;
    String storage_path = "user/*";

    String foto = "foto";
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, talla, precio, imagen;
        ImageView imageview_cesta, imageview_favoritos, foto;
        FirebaseFirestore mfirestore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreMostrar);
            talla = itemView.findViewById(R.id.tallaMostrar);
            precio = itemView.findViewById(R.id.precioMostrar);
            foto = itemView.findViewById(R.id.fotoMostrar);

            imagen = itemView.findViewById(R.id.imagenMostrar);
            imageview_cesta = itemView.findViewById(R.id.imageview_cesta);
            imageview_favoritos = itemView.findViewById(R.id.imageview_favoritos);
            mfirestore = FirebaseFirestore.getInstance();

        }
    }

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductoAdapter(@NonNull FirestoreRecyclerOptions<Productos> options){//, Context context) {
        super(options);
        //this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Productos Productos) {
        holder.nombre.setText(Productos.getNombre());
        holder.talla.setText(Productos.getTalla());
        holder.precio.setText(Productos.getPrecio());
        holder.imagen.setText(Productos.getFoto());
        // Obtén la URL de descarga de la imagen desde productosManage
        String imageUrl = Productos.getFoto();

        // Utiliza Glide para cargar y mostrar la imagen desde la URL de descarga
        Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.foto);

        holder.imageview_cesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //uploadFoto();
                String nombreProduct = holder.nombre.getText().toString().trim();
                String tallaProduct = holder.talla.getText().toString().trim();
                String precioProduct = holder.precio.getText().toString().trim();
                String imageUrl = Productos.getFoto();
                String imagenProduct = holder.imagen.getText().toString().trim();
                postProductCesta(nombreProduct, tallaProduct, precioProduct, imagenProduct);
                Toast.makeText(view.getContext(), "Producto añadido a la cesta", Toast.LENGTH_SHORT).show();
            }

        });

        holder.imageview_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProduct = holder.nombre.getText().toString().trim();
                String tallaProduct = holder.talla.getText().toString().trim();
                String precioProduct = holder.precio.getText().toString().trim();
                String imagenProduct = holder.imagen.getText().toString().trim();

                postProductFavoritos(nombreProduct, tallaProduct, precioProduct, imagenProduct);
                Toast.makeText(view.getContext(), "Producto añadido a favoritos", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void uploadFoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");

        // Aquí debes proporcionar el contexto adecuado para iniciar la actividad
        // Puedes obtener el contexto en el constructor del adaptador y almacenarlo como una variable de instancia para usarlo aquí.
        //startActivityForResult(i, COD_SEL_IMAGE);
    }
/*
    private void subirFoto (Uri image_url) {
        progressDialog.setMessage("Actualizando foto");
        progressDialog.show();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();

        String productId = getSnapshots().getSnapshot(getAdapterPosition()).getId();

        String rute_storage_foto = storage_path  + "" + mAuth.getUid() + "" + productId + foto;
        StorageReference reference = storageReference.child(rute_storage_foto);
        reference.putFile(image_url).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                if (uriTask.isSuccessful()) {
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downolad_uri = uri.toString();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("foto", downolad_uri);
                            //mfirestore.collection("product").document(productId).update(map);
                            mfirestore.collection("user").document(userId).collection("productFavoritos").document(productId).update(map);
                            //mfirestore.collection("user").document(userId).collection("productCesta").document(productId).update(map);
                            Toast.makeText(CreateProductActivity.this, "Foto actualizada", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProductActivity.this, "Error al cargar la foto", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/
    private void postProductCesta(String nombreProduct,String tallaProduct, String precioProduct, String imagenProduct) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);
        map.put("foto", imagenProduct);

        FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userID = currentUser.getUid();

        mfirestore.collection("user").document(userID).collection("productCesta").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // El producto se agregó exitosamente a la colección "productFavoritos"
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Ocurrió un error al guardar los favoritos del usuario
                    }
                });
    }

    private void postProductFavoritos(String nombreProduct,String tallaProduct, String precioProduct, String imagenProduct) {

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);
        map.put("foto", imagenProduct);

        FirebaseFirestore mfirestore = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();

        mfirestore.collection("user").document(userId).collection("productFavoritos").add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // El producto se agregó exitosamente a la colección "productFavoritos"
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Ocurrió un error al guardar los favoritos del usuario
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
