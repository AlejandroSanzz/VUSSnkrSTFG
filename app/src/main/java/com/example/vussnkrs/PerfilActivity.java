package com.example.vussnkrs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    private Spinner prefCompraSpinner, nZapatillasSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Configurar la AppBar
        Toolbar toolbar = findViewById(R.id.toolbarperfil);
        setSupportActionBar(toolbar);

        prefCompraSpinner = findViewById(R.id.pref_spinner);
        nZapatillasSpinner = findViewById(R.id.nzapatillas_spinner);

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
}

