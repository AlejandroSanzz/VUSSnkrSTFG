package com.example.vussnkrs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vussnkrs.adapter.ProductoAdapterCesta;
import com.example.vussnkrs.productos.ProductosCesta;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class CestaActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PAYMENT = 1;
    private PayPalConfiguration config;

    RecyclerView mRecycler;
    ProductoAdapterCesta mAdapter;
    FirebaseFirestore mFirestore;

    //String precioTotal = String.valueOf(0.0);
    TextView txtPrecioTotal;

    private BigDecimal precioTotal = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta);

        // Configurar la AppBar
        Toolbar toolbar = findViewById(R.id.toolbarcesta);
        setSupportActionBar(toolbar);
        config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId("AUPEciDTB6EytXqKrfspsUJbY5o2RbLxZg0jimzMATfrPMApAsN4eVBp0xW62bALA5OOIiPyb8AlZvoy");

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Button paypalButton = findViewById(R.id.btn_pagar);
        txtPrecioTotal = findViewById(R.id.txt_preciototal);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerViewSingleCesta);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userID = currentUser.getUid();
        Query query = mFirestore.collection("user").document(userID).collection("productCesta");

        FirestoreRecyclerOptions<ProductosCesta> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<ProductosCesta>()
                        .setQuery(query, ProductosCesta.class)
                        .build();

        mAdapter = new ProductoAdapterCesta(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        paypalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorTexto = ((TextView) findViewById(R.id.txt_preciototal)).getText().toString();
                BigDecimal valor = new BigDecimal(valorTexto);

                PayPalPayment payment = new PayPalPayment(valor, "EUR", "Descripción del pago", PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(CestaActivity.this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });



    }

    private void calcularPrecioTotal() {
        precioTotal = BigDecimal.ZERO;

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            ProductosCesta producto = mAdapter.getItem(i);
            BigDecimal precioProducto = new BigDecimal(producto.getPrecio());
            precioTotal = precioTotal.add(precioProducto);
        }

        txtPrecioTotal.setText(precioTotal.toString());
    }

    public void onActualizarCestaClick(View view) {
        calcularPrecioTotal();
        Toast.makeText(this, "Cesta actualizada correctamente", Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutienda, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Comprar:
                Toast.makeText(this, "Vas a entrar en la tienda", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CestaActivity.this, TiendaActivity.class));
                return true;
            case R.id.Favoritos:
                Toast.makeText(this, "Vas a entrar en tus favoritos", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CestaActivity.this, FavoritosActivity.class));
                return true;
            case R.id.Cesta:
                Toast.makeText(this, "Vas a entrar en tu cesta", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CestaActivity.this, CestaActivity.class));
                return true;
            case R.id.Perfil:
                Toast.makeText(this,"Vas a entrar en tu perfil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CestaActivity.this, PerfilActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    String paymentId = confirm.getProofOfPayment().getPaymentId();
                    // Procesa el ID de pago según tus necesidades
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // El usuario canceló el proceso de pago
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // Datos de pago inválidos o faltantes
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
        calcularPrecioTotal();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onBackPressed () {
        System.exit(0); // Cierra la actividad actual
    }
}
