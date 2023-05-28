package com.example.vussnkrs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegistroActivity extends AppCompatActivity {

    Button registro;
    EditText nombreRegistro, apellidosRegistro, tallaRegistro, direccionRegistro, movilRegistro,  emailRegistro, passwordRegistro;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        registro = findViewById(R.id.buttonRegistro);
        nombreRegistro = findViewById(R.id.nombreRegistro);
        apellidosRegistro = findViewById(R.id.apellidosRegistro);
        tallaRegistro = findViewById(R.id.tallaRegistro);
        direccionRegistro = findViewById(R.id.direccionRegistro);
        movilRegistro = findViewById(R.id.movilRegistro);
        emailRegistro = findViewById(R.id.emailRegistro);
        passwordRegistro = findViewById(R.id.passwordRegistro);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreRegistro.getText().toString().trim();
                String apellidos = apellidosRegistro.getText().toString().trim();
                String talla = tallaRegistro.getText().toString().trim();
                String direccion = direccionRegistro.getText().toString().trim();
                String movil = movilRegistro.getText().toString().trim();
                String email = emailRegistro.getText().toString().trim();
                String password = passwordRegistro.getText().toString().trim();
                if(nombre.isEmpty() && email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(nombre, apellidos, talla, direccion, movil, email, password);
                }
            }
        });

    }

    private void registerUser(String nombre, String  apellidos, String talla, String direccion, String movil, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("nombre", nombre);
                map.put("apellidos", apellidos);
                map.put("talla", talla);
                map.put("direccion", direccion);
                map.put("movil", movil);
                map.put("email", email);
                map.put("password", password);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                        Toast.makeText(RegistroActivity.this, "Usuario registrado completado con éxito", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistroActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistroActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed () {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}