package com.example.asus.guwe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Daftar extends AppCompatActivity {

    private FirebaseAuth mAuth; // null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mAuth = FirebaseAuth.getInstance(); // set firebase autentikasi

    }

    @Override
    protected void onStart() {
        super.onStart();

        // update (user-interface) activity dan layout berdasarkan userinfo
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

  //      user.updateProfile(Name)


    }

    public void btnDaftar(View view) {

        // ambil objek widget dari layout
        EditText namaEditText = (EditText)  findViewById(R.id.Nameku);
        EditText emailEditText = (EditText)  findViewById(R.id.txtname);
        EditText passEditText = (EditText)  findViewById(R.id.txtPassword);

        // abmbil isi inputan dari widget
        final String nama = namaEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String pass = passEditText.getText().toString();



        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    final FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.
                                            Builder().setDisplayName(nama).build();
                                    user.updateProfile(profileUpdates).addOnCompleteListener(Daftar.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            updateUI(user);
                                        }
                                    });

                                } else {
                                    Log.e("TES_DAFTAR_GAGAL", task.getException().getMessage() );
                                    Toast.makeText(Daftar.this, "Daftar gagal",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        }
    )
        ;
    }



    // updateUI digunakan untuk mengubah user-interface / tampilan
    // pada layout / activity
    void updateUI(FirebaseUser user){
        if ( user != null){
            // kalau login
            Intent keProfil = new Intent(this, Profil.class);
            startActivity(keProfil);
        }else {
            // kalau tidak login

        }
    }

    public void kelogin(View view) {
        Intent gue = new Intent(this, Login.class);
        startActivity(gue);
    }
}
