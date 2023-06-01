package com.example.vussnkrs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vussnkrs.databinding.ActivityManageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class CreateProductActivity extends AppCompatActivity {

    ImageView foto_producto;
    Button btn_add_Prod, boton_anadir_foto, boton_eliminar_foto;
    EditText sku, nombre, categoria, descripcion, talla, precio, stock, proveedor;
    private FirebaseFirestore mfirestore;
    private FirebaseAuth mAuth;

    StorageReference storageReference;
    String storage_path = "product/*";

    private static final int COD_SEL_STORAGE = 200;
    private static final int COD_SEL_IMAGE = 300;

    private Uri image_url;
    String foto = "foto";
    String idd;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        sku = findViewById(R.id.editTextSKU);
        nombre = findViewById(R.id.editTextNombre);
        categoria = findViewById(R.id.editTextCategoria);
        descripcion = findViewById(R.id.editTextDescripcion);
        talla = findViewById(R.id.editTextTalla);
        precio = findViewById(R.id.editTextPrecio);
        stock = findViewById(R.id.editTextStock);
        proveedor = findViewById(R.id.editTextProveedor);
        btn_add_Prod = findViewById(R.id.btn_agregar);
        mfirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();
        foto_producto = findViewById(R.id.foto_producto);
        boton_anadir_foto = findViewById(R.id.boton_anadir_foto);
        boton_eliminar_foto = findViewById(R.id.boton_eliminar_foto);
        progressDialog = new ProgressDialog(this);

        String id = getIntent().getStringExtra("id_product");

        if (id == null || id == "") {
            btn_add_Prod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String skuProduct = sku.getText().toString().trim();
                    String nombreProduct = nombre.getText().toString().trim();
                    String categoriaProduct = categoria.getText().toString().trim();
                    String descripcionProduct = descripcion.getText().toString().trim();
                    String tallaProduct = talla.getText().toString().trim();
                    String precioProduct = precio.getText().toString().trim();
                    String stockProduct = stock.getText().toString().trim();
                    String proveedorProduct = proveedor.getText().toString().trim();




                    if (nombreProduct.isEmpty() && tallaProduct.isEmpty() && skuProduct.isEmpty() && nombreProduct.isEmpty() && categoriaProduct.isEmpty() && descripcionProduct.isEmpty() && tallaProduct.isEmpty() && precioProduct.isEmpty() && stockProduct.isEmpty() && proveedorProduct.isEmpty()) {
                        Toast.makeText(CreateProductActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        postProduct(skuProduct, nombreProduct, categoriaProduct, descripcionProduct, tallaProduct, precioProduct, stockProduct, proveedorProduct);
                    }
                }
            });
        } else {
            btn_add_Prod.setText("Actualizar producto");
            getProduct(id);

            btn_add_Prod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String skuProduct = sku.getText().toString().trim();
                    String nombreProduct = nombre.getText().toString().trim();
                    String categoriaProduct = categoria.getText().toString().trim();
                    String descripcionProduct = descripcion.getText().toString().trim();
                    String tallaProduct = talla.getText().toString().trim();
                    String precioProduct = precio.getText().toString().trim();
                    String stockProduct = stock.getText().toString().trim();
                    String proveedorProduct = proveedor.getText().toString().trim();

                    if (nombreProduct.isEmpty() && tallaProduct.isEmpty() && skuProduct.isEmpty() && nombreProduct.isEmpty() && categoriaProduct.isEmpty() && descripcionProduct.isEmpty() && tallaProduct.isEmpty() && precioProduct.isEmpty() && stockProduct.isEmpty() && proveedorProduct.isEmpty()) {
                        Toast.makeText(CreateProductActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    } else {
                        updateProduct(skuProduct, nombreProduct, categoriaProduct, descripcionProduct, tallaProduct, precioProduct, stockProduct, proveedorProduct, id);
                        startActivity(new Intent(CreateProductActivity.this, ManageActivityMostrar.class));
                    }
                }
            });
        }




        boton_anadir_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFoto();
            }
        });

    }

    private void updateProduct(String skuProduct, String nombreProduct, String categoriaProduct, String descripcionProduct, String tallaProduct, String precioProduct, String stockProduct, String proveedorProduct, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("sku", skuProduct);
        map.put("nombre", nombreProduct);
        map.put("categoria", categoriaProduct);
        map.put("descripcion", descripcionProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);
        map.put("stock", stockProduct);
        map.put("proveedor", proveedorProduct);

        mfirestore.collection("product").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(CreateProductActivity.this, "Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProductActivity.this, "Error al actualizar ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void postProduct(String skuProduct, String nombreProduct, String categoriaProduct, String descripcionProduct, String tallaProduct, String precioProduct, String stockProduct, String proveedorProduct ) {
        Map<String, Object> map = new HashMap<>();
        map.put("sku", skuProduct);
        map.put("nombre", nombreProduct);
        map.put("categoria", categoriaProduct);
        map.put("descripcion", descripcionProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);
        map.put("stock", stockProduct);
        map.put("proveedor", proveedorProduct);

        mfirestore.collection("product").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                finish();
                Toast.makeText(CreateProductActivity.this, "Creado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CreateProductActivity.this, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProductActivity.this, "Error al ingresar ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProduct (String id) {
        mfirestore.collection("product").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String skuProduct = documentSnapshot.getString("sku");
                String nombreProduct = documentSnapshot.getString("nombre");
                String categoriaProduct = documentSnapshot.getString("categoria");
                String descripcionProduct = documentSnapshot.getString("descripcion");
                String tallaProduct = documentSnapshot.getString("talla");
                String precioProduct = documentSnapshot.getString("precio");
                String stockProduct = documentSnapshot.getString("stock");
                String proveedorProduct = documentSnapshot.getString("proveedor");

                sku.setText(skuProduct);
                nombre.setText(nombreProduct);
                categoria.setText(categoriaProduct);
                descripcion.setText(descripcionProduct);
                talla.setText(tallaProduct);
                precio.setText(precioProduct);
                stock.setText(stockProduct);
                proveedor.setText(proveedorProduct);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateProductActivity.this, "Error al actualizar ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void uploadFoto() {
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");

        startActivityForResult(i, COD_SEL_IMAGE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == COD_SEL_IMAGE ) {
                image_url = data.getData();
                subirFoto(image_url);
            }
        }
    }

    private void subirFoto (Uri image_url) {
        progressDialog.setMessage("Actualizando foto");
        progressDialog.show();
        String rute_storage_foto = storage_path + "" + foto + "" + mAuth.getUid() + "" + idd;
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
                            mfirestore.collection("product").document(idd).update(map);
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

    @Override
    public void onBackPressed () {
        new AlertDialog.Builder(this)
                .setMessage("¿Desea salir de la pantalla?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(CreateProductActivity.this, MainActivity.class));
                    }
                })
                .setNegativeButton("No", null) // No hace nada, simplemente cierra el diálogo
                .show();
    }
}