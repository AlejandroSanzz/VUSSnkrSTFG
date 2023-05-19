package com.example.vussnkrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vussnkrs.databinding.ActivityManageBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ManageActivity extends AppCompatActivity {

    Button btn_add_Prod;
    EditText sku, nombre, categoria, descripcion, talla, precio, stock, imagen, proveedor;
    private FirebaseFirestore mfirestore;
    private ActivityManageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_agregar, R.id.navigation_eliminar, R.id.navigation_actualizar, R.id.navigation_mostrar)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_manage);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        sku = findViewById(R.id.editTextSKU);
        nombre = findViewById(R.id.editTextNombre);
        categoria = findViewById(R.id.editTextCategoria);
        descripcion = findViewById(R.id.editTextDescripcion);
        talla = findViewById(R.id.editTextTalla);
        precio = findViewById(R.id.editTextPrecio);
        stock = findViewById(R.id.editTextStock);
        imagen = findViewById(R.id.editTextImagen);
        proveedor = findViewById(R.id.editTextProveedor);
        btn_add_Prod = findViewById(R.id.btn_agregar);
        mfirestore = FirebaseFirestore.getInstance();

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
                String imagenProduct = imagen.getText().toString().trim();
                String proveedorProduct = proveedor.getText().toString().trim();




                if (nombreProduct.isEmpty() && tallaProduct.isEmpty() && skuProduct.isEmpty() && nombreProduct.isEmpty() && categoriaProduct.isEmpty() && descripcionProduct.isEmpty() && tallaProduct.isEmpty() && precioProduct.isEmpty() && stockProduct.isEmpty() && imagenProduct.isEmpty() && proveedorProduct.isEmpty()) {
                    Toast.makeText(ManageActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postProduct(skuProduct, nombreProduct, categoriaProduct, descripcionProduct, tallaProduct, precioProduct, stockProduct, imagenProduct, proveedorProduct);
                }
            }
        });

    }

    private void postProduct(String skuProduct, String nombreProduct, String categoriaProduct, String descripcionProduct, String tallaProduct, String precioProduct, String stockProduct, String imagenProduct, String proveedorProduct ) {
        Map<String, Object> map = new HashMap<>();
        map.put("sku", nombreProduct);
        map.put("nombre", skuProduct);
        map.put("categoria", categoriaProduct);
        map.put("descripcion", descripcionProduct);
        map.put("talla", tallaProduct);
        map.put("precio", precioProduct);
        map.put("stock", stockProduct);
        map.put("imagen", imagenProduct);
        map.put("proveedor", proveedorProduct);

        mfirestore.collection("product").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                finish();
                Toast.makeText(ManageActivity.this, "Creado exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ManageActivity.this, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ManageActivity.this, "Error al ingresar ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}