package com.example.andriginting.projectlocation;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Andri Ginting on 1/15/2017.
 */

@IgnoreExtraProperties
public class user {
    public String name, email, kota;

    public user() {
    }

    public user(String name, String email, String kota) {
        this.name = name;
        this.email = email;
        this.kota = kota;
    }

    public String getKota() {
        return kota ;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getname() {
        return name ;
    }

    public void setname(String name) {
    }

    public String getEmail() {
        return email ;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
