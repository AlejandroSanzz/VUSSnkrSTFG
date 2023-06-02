package com.example.vussnkrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    EditText edittext_email_inicio_sesion, edittext_contrasena_inicio_sesion;
    String myEmail, myPassword;
    FirebaseAuth mAuth;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Button btInicioSesion = findViewById(R.id.btn_iniciar_sesion);
        Button btRegistro = findViewById(R.id.btn_registrarse);
        EditText emailInicioSesion = findViewById(R.id.txt_email_inicio_sesion);
        EditText passwordInicioSesion = findViewById(R.id.txt_contrasena_inicio_sesion);

        btInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUSer = emailInicioSesion.getText().toString().trim();
                String passUSer = passwordInicioSesion.getText().toString().trim();

                if (emailUSer == null && passUSer == null) {
                    Toast.makeText(MainActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUSer, passUSer);
                }

            }
        });

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, RegistroActivity.class));
                Toast.makeText(MainActivity.this, "Registrarse", Toast.LENGTH_SHORT).show();
            }
        });

        pref = getPreferences(Context.MODE_PRIVATE);
        edittext_email_inicio_sesion = findViewById(R.id.txt_email_inicio_sesion);
        myEmail = pref.getString("myEmail","");
        edittext_contrasena_inicio_sesion = findViewById(R.id.txt_contrasena_inicio_sesion);
        myPassword = pref.getString("myPassword","");
        edittext_email_inicio_sesion.setText(myEmail);
        edittext_contrasena_inicio_sesion.setText(myPassword);
    }

    private void loginUser(String emailUSer, String passUSer) {
        mAuth.signInWithEmailAndPassword(emailUSer, passUSer).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userId = user.getUid();


                    if (userId.equals("OGVt9hq3KtR1vsxfHyj7qGshyLx1j")) {
                        // Usuario específico, redirigir a otra actividad
                        startActivity(new Intent(MainActivity.this, EleccionAdminActivity.class));
                    } else {
                        // Otros usuarios, redirigir a la actividad predeterminada
                        startActivity(new Intent(MainActivity.this, TiendaActivity.class));
                    }

                    finish();

                   // startActivity(new Intent(MainActivity.this, TiendaActivity.class));
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });

        pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("myEmail", edittext_email_inicio_sesion.getText().toString());
        editor.putString("myPassword", edittext_contrasena_inicio_sesion.getText().toString());
        editor.apply();

    }

    @Override
    public void onBackPressed () {
        new AlertDialog.Builder(this)
                .setMessage("¿Desea salir de la aplicación?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0); // Cierra la actividad actual
                    }
                })
                .setNegativeButton("No", null) // No hace nada, simplemente cierra el diálogo
                .show();
    }

    /*public void irTienda (View view) {
        Intent i = new Intent(this, PaginaActivity.class);
        startActivity(i);
        /*
        pref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("miNombre", text_nombre.getText().toString());
        //editor.putString("miTalla", text_talla.getText().toString());
        editor.apply();
        text_nombre = findViewById(R.id.txt_nombre);
        String campo1 = text_nombre.getText().toString();
        if(!campo1.isEmpty()) {
            Intent i = new Intent(this, PaginaActivity.class);
            text_email = findViewById(R.id.txt_nombre);
            i.putExtra("nombre", text_nombre.getText().toString());
            startActivity(i);
        } else {
            text_nombre.setError("El campo nombre es obligatorio");
        }

    } */

    /*public void admin (View view) {
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
    }*/
}