package com.example.andriginting.projectlocation.adapterData;

/**
 * Created by Andri Ginting on 2/7/2017.
 */

public class Itemkategori {
    private String nama_menu;
    private int gambar;


    public Itemkategori(String nama_menu,int gambar){
        this.gambar=gambar;
        this.nama_menu=nama_menu;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
