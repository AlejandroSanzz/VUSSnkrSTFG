package com.example.vussnkrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vussnkrs.adapter.ProductoAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;



public class PerfilActivity extends AppCompatActivity {

    EditText txt_nombre, txt_apellidos, txt_direccion, txt_numero, txt_correo, txt_contraseña;

    Button btn_actualizar_perfil;
    FirebaseFirestore mFirestore;
    private Spinner prefCompraSpinner, nZapatillasSpinner, idiomaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Configurar la AppBar
        Toolbar toolbar = findViewById(R.id.toolbarperfil);
        setSupportActionBar(toolbar);

        prefCompraSpinner = findViewById(R.id.pref_spinner);
        nZapatillasSpinner = findViewById(R.id.nzapatillas_spinner);

        txt_nombre = findViewById(R.id.txt_nombre2);
        txt_apellidos = findViewById(R.id.txt_apellidos);
        txt_direccion = findViewById(R.id.txt_direccion);
        txt_numero = findViewById(R.id.txt_numero);
        txt_correo = findViewById(R.id.txt_correo);
        txt_contraseña = findViewById(R.id.txt_contraseña);
        btn_actualizar_perfil = findViewById(R.id.btn_actualizar_datos);
        mFirestore = FirebaseFirestore.getInstance();


        // Obtener el ID del usuario y llamar a getProduct()
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String userId = firebaseAuth.getCurrentUser().getUid();
            getUser(userId);
        }


        btn_actualizar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getProduct("xUCUKDxG1GfNRIvZzOEiKMXdiqg2");
            }
        });


        /*
        idiomaSpinner = findViewById(R.id.idioma_spinner);

        Switch switchIdioma = findViewById(R.id.switch_idioma);

        switchIdioma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Cambiar al idioma seleccionado (por ejemplo, inglés)
                    cambiarIdioma("en");
                } else {
                    // Cambiar al idioma predeterminado (por ejemplo, español)
                    cambiarIdioma("es");
                }
            }
        });

        private void cambiarIdioma(String codigoIdioma) {
            Locale locale = new Locale(codigoIdioma);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.setLocale(locale);
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());

            // Reiniciar la actividad actual para refrescar los recursos de idioma
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        ArrayAdapter<CharSequence> idiomaAdaptador = ArrayAdapter.createFromResource(
                this,
                R.array.Idiomas,
                android.R.layout.simple_spinner_item
        );
        idiomaAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idiomaSpinner.setAdapter(idiomaAdaptador);
        idiomaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String idiomaSeleccionado = parent.getItemAtPosition(position).toString();

                // Cambiar el idioma de la aplicación
                Locale locale = new Locale(idiomaSeleccionado);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.setLocale(locale);
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());

                // Reiniciar la actividad actual para refrescar los recursos de idioma
                //Intent intent = getIntent();
                //finish();
                //startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Acción a realizar cuando no se selecciona ningún idioma
            }
        });
        */

        ArrayAdapter<CharSequence> prefAdaptador = ArrayAdapter.createFromResource(
                this,
                R.array.preferenciasDeCompra,
                android.R.layout.simple_spinner_item
        );
        prefAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignamos el adaptador al primer spinner
        prefCompraSpinner.setAdapter(prefAdaptador);
        // Creamos un listener para detectar los cambios en el primer spinner
        prefCompraSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos la opción seleccionada en el primer spinner
                String opcionSeleccionada = parent.getItemAtPosition(position).toString();

                // Actualizamos el segundo spinner en función de la opción seleccionada
                if (opcionSeleccionada.equals("Hombre")) {
                    ArrayAdapter<CharSequence> tallasHombreAdaptador = ArrayAdapter.createFromResource(
                            PerfilActivity.this,
                            R.array.tallasHombre,
                            android.R.layout.simple_spinner_item
                    );
                    tallasHombreAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(tallasHombreAdaptador);
                } else if (opcionSeleccionada.equals("Mujer")) {
                    ArrayAdapter<CharSequence> tallasMujerAdaptador = ArrayAdapter.createFromResource(
                            PerfilActivity.this,
                            R.array.tallasMujer,
                            android.R.layout.simple_spinner_item
                    );
                    tallasMujerAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(tallasMujerAdaptador);
                } else if (opcionSeleccionada.equals("")) {
                    ArrayAdapter<CharSequence> todasAdapatador = ArrayAdapter.createFromResource(
                            PerfilActivity.this,
                            R.array.todasTallas,
                            android.R.layout.simple_spinner_item);
                    todasAdapatador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    nZapatillasSpinner.setAdapter(todasAdapatador);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);

        int nZapatillasPosition = sharedPreferences.getInt("nZapatillasPosition", 0);

        nZapatillasSpinner.setSelection(nZapatillasPosition);

        nZapatillasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("nZapatillasPosition", position);

                editor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });


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
                startActivity(new Intent(PerfilActivity.this, TiendaActivity.class));
                return true;
            case R.id.Favoritos:
                Toast.makeText(this, "Vas a entrar en tus favoritos", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PerfilActivity.this, FavoritosActivity.class));
                return true;
            case R.id.Cesta:
                Toast.makeText(this, "Vas a entrar en tu cesta", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PerfilActivity.this, CestaActivity.class));
                return true;
            case R.id.Perfil:
                Toast.makeText(this,"Vas a entrar en tu perfil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PerfilActivity.this, PerfilActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getUser (String id) {
        mFirestore.collection("user").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombreUser = documentSnapshot.getString("nombre");
                String apellidosUser = documentSnapshot.getString("apellidos");
                String direccionUser = documentSnapshot.getString("direccion");
                String movilUser = documentSnapshot.getString("movil");
                String emailUser = documentSnapshot.getString("email");
                String passwordUser = documentSnapshot.getString("password");

                txt_nombre.setText(nombreUser);
                txt_apellidos.setText(apellidosUser);
                txt_direccion.setText(direccionUser);
                txt_numero.setText(movilUser);
                txt_correo.setText(emailUser);
                txt_contraseña.setText(passwordUser);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PerfilActivity.this, "Error al actualizar ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed () {
        System.exit(0); // Cierra la actividad actual
    }
}

