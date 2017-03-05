package com.example.andriginting.projectlocation.adapterData;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Andri Ginting on 1/28/2017.
 */

public class Config extends Application{
    public static final String FIREBASE_URL = "https://roundme-366f1.firebaseio.com/";
    public static final String TAG ="Check Error";
    @Override
    public void onCreate() {
        super.onCreate();
        if (!com.google.firebase.FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
