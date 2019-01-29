package com.example.asus.guwe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();

        TextView profilNama = (TextView) findViewById(R.id.textVieww);
        TextView profilEmail = (TextView) findViewById(R.id.textView4);

        String textNama = "Hi " + user.getDisplayName() + "!";
        String textEmail = "Neh Email " + user.getEmail() + " ...";

        profilNama.setText(textNama);
        profilEmail.setText(textEmail);

    }

    public void btnKeluar(View view) { FirebaseAuth.getInstance().signOut();

        Intent keDaftar = new Intent(this, Daftar.class);
        startActivity(keDaftar);
    }

    public void kechat(View view) {
        Intent kechates = new Intent(this, Chat.class);
        startActivity(kechates);
    }
}
