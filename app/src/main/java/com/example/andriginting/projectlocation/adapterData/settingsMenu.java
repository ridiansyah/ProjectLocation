package com.example.andriginting.projectlocation.adapterData;

/**
 * Created by Andri Ginting on 2/15/2017.
 */

public class settingsMenu {
    private String nama;
    private int gambar;

    public settingsMenu(String nama, int gambar) {
        this.nama = nama;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
