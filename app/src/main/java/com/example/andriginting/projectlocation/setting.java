package com.example.andriginting.projectlocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class setting extends AppCompatActivity {

    private Button logout;
    private TextView chg_pass;
    private TextView about_setting,editprofil,support;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        FirebaseUser User = null;
        logout=(Button)findViewById(R.id.btn_logout);
        firebaseAuth = FirebaseAuth.getInstance();
        chg_pass=(TextView)findViewById(R.id.chg_pass);
        support =(TextView)findViewById(R.id.support);
        about_setting=(TextView)findViewById(R.id.about);
        editprofil=(TextView)findViewById(R.id.editprofil);


        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(setting.this,Login.class));
            }
        });

        //edit profile
        editprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,editProfile.class));
            }
        });
        //chg password
        chg_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(setting.this);
                View mview =getLayoutInflater().inflate(R.layout.chg_password,null);
                EditText pass1 = (EditText)mview.findViewById(R.id.old_pass);
                EditText pass2 = (EditText)mview.findViewById(R.id.new_pass);
                Button ganti =(Button)mview.findViewById(R.id.btn_chgPass);
                ganti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        changePass(firebaseAuth.getCurrentUser().getEmail());
                    }
                });
                builder.setView(mview);
                AlertDialog dialog =builder.create();
                dialog.show();
            }

        });

        about_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(setting.this);
                View mview =getLayoutInflater().inflate(R.layout.about,null);

                builder.setView(mview);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,support.class));
            }
        });
    }

    private void changePass(String email) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(this,new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                Toast.makeText(setting.this,"Password berhasil diganti",Toast.LENGTH_SHORT).show();
            }
                else{
                Toast.makeText(setting.this,"Terjadi kesalahan,coba lagi",Toast.LENGTH_SHORT).show();
            }
            }//end if
        });
    }

}
