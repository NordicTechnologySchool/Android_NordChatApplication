package com.example.asus.guwe;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Currency;

public class Chat extends AppCompatActivity {

    ChatAdapter mChatAdapter;
    ArrayList<Pesan> daftarPesan = new ArrayList<Pesan>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // settingan recyclerview
        final RecyclerView listPesanXml = (RecyclerView) findViewById(R.id.chatList);
        listPesanXml.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        listPesanXml.setLayoutManager(linearLayoutManager);

        // di trigger ketika ada message baru
        FirebaseDatabase.getInstance().getReference("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // mereset arraylist
                daftarPesan.clear();

                // mengisi arraylist
                for (DataSnapshot ss : dataSnapshot.getChildren()){
                    Pesan pesan = ss.getValue(Pesan.class);
                    daftarPesan.add(pesan);
                }

                // set arraylist ke recyclerview
                mChatAdapter = new ChatAdapter(Chat.this, daftarPesan);
                listPesanXml.setAdapter(mChatAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void kirimkan(View view) {
        EditText tekspesan = (EditText)  findViewById(R.id.tulispesan);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String namaUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String teksUser = tekspesan.getText().toString();

        FirebaseDatabase.getInstance().getReference("message").push().setValue(new Pesan(userID, namaUser, teksUser));

        tekspesan.setText("");
    }
}
