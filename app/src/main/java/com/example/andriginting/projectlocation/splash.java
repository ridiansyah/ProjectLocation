package com.example.andriginting.projectlocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {
    private static int jedatampilan = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(splash.this,Login.class));
                this.finish();
            }
            private void finish(){
                //TODO Auto-generated method sub
            }
        },jedatampilan);
    }
}
