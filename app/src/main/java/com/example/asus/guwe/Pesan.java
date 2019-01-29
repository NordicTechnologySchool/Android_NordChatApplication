package com.example.asus.guwe;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pesan {
    private String userID;
    private String nama;
    private String teks;
    private Date waktu;

    // Contructor
    public Pesan( String userID, String nama, String teks) {
        this.userID = userID;
        this.nama = nama;
        this.teks = teks;
        this.waktu = new Date();
    }

    public Pesan(){

    }

    // get
    public String getUserID() {
        return userID;
    }

    public String getNama() {
        return nama;
    }

    public String getTeks() {
        return teks;
    }

    public Date getWaktu(){
        return waktu;
    }

    public long getWaktuUnix() {
        return waktu.getTime();
    }

    public String getWaktuFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        return simpleDateFormat.format(waktu.getTime());
    }

    // set
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTeks(String teks) {
        this.teks = teks;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
}
