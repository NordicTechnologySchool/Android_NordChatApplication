package com.example.asus.guwe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // update (user-interface) activity dan layout berdasarkan userinfo
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    public void btnLogin(View view) {

        // ambil objek widget dari layout
        //  EditText namaEditText = (EditText)  findViewById(R.id.);
        EditText emailEditText = (EditText) findViewById(R.id.txtname);
        EditText passEditText = (EditText) findViewById(R.id.passedittxt);

        // abmbil isi inputan dari widget
        //   String nama = namaEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String pass = passEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    Log.e("TES_DAFTAR_GAGAL", task.getException().getMessage());
                                    Toast.makeText(Login.this, "Login gagal",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        }
                )
        ;
    }


    void updateUI(FirebaseUser user){
        if ( user != null){
            // kalau login
            Intent keProfil = new Intent(this, Profil.class);
            startActivity(keProfil);
        }else {
            // kalau tidak login

        }
    }

    public void keHalamanDaftar(View view) {
        Intent halamandaftar = new Intent(this, Daftar.class);
        startActivity(halamandaftar);
    }
}



