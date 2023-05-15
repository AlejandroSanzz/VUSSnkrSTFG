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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        FirebaseAuth mAuth;


        Button btnIniciarSesion = findViewById(R.id.btn_iniciar);
        Button btnSalir = findViewById(R.id.bttn_salir);
        EditText email = findViewById(R.id.txt_usuario);
        EditText password = findViewById(R.id.txt_contraseña);

    btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            String emailUSer = email.getText().toString().trim();
            String passUSer = password.getText().toString().trim();

            if (email == null && password == null) {
                Toast.makeText(AdminActivity.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(emailUSer, passUSer);
            }

        }
    });
    }

    private void loginUser(String emailUSer, String passUSer) {
        mAuth.signInWithEmailAndPassword(emailUSer, passUSer).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(AdminActivity.this, ManageActivity.class));
                    Toast.makeText(AdminActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}