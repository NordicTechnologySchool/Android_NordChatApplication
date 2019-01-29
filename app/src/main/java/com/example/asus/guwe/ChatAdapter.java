package com.example.asus.guwe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Pesan> daftarPesan;

    // Constructor
    public ChatAdapter(Context mContext, ArrayList<Pesan> daftarPesan) {
        this.mContext = mContext;
        this.daftarPesan = daftarPesan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 0 = kiri, 1 = kanan
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.bubble_left, parent, false);

            return new ChatAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.bubble_right, parent, false);

            return new ChatAdapter.ViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pesan pesan = daftarPesan.get(position);

        holder.pesanNama.setText(pesan.getNama());
        holder.pesanTeks.setText(pesan.getTeks());
        holder.pesanWaktu.setText(pesan.getWaktuFormat());
    }

    @Override
    public int getItemViewType(int position) {

        String myID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String userID = daftarPesan.get(position).getUserID();

        if (userID.equals(myID)){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return daftarPesan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pesanNama;
        public TextView pesanTeks;
        public TextView pesanWaktu;

        public ViewHolder(View itemView) {
            super(itemView);

            pesanNama = itemView.findViewById(R.id.chatNama);
            pesanTeks = itemView.findViewById(R.id.chatText);
            pesanWaktu = itemView.findViewById(R.id.chatWaktu);
        }
    }
}
