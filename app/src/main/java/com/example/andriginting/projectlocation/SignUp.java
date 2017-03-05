package com.example.andriginting.projectlocation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtemail;
    private EditText txtKota;
    private EditText txtPassword;
    private EditText txtKonPass;

    private Button btn_daftar;
    private ProgressDialog mProg;

    private DatabaseReference mdatabase;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthlistener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);

        mProg = new ProgressDialog(this);
        txtUsername = (EditText) findViewById(R.id.editUserRegis);
        txtemail = (EditText) findViewById(R.id.editEmail);
        txtKota = (EditText) findViewById(R.id.editKota);
        txtPassword = (EditText) findViewById(R.id.editPassRegis);
        txtKonPass = (EditText) findViewById(R.id.editPassRegis2);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);

        //untuk firebase
        firebaseAuth = FirebaseAuth.getInstance();
        mAuthlistener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser!=null){
                    Log.d("User Telah login",firebaseUser.getUid());
                }else{
                    Log.d("User belum Login","");
                }
            }
        };

        initFirebase();

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
               // addEventListener();

            }
        });

    }

    private void initFirebase() {
        FirebaseApp.getApps(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabase = mFirebaseDatabase.getReference();

    }

    //menambah data user ke database firebase
    public void registerUser() {
        final String username = txtUsername.getText().toString();
        final String email = txtemail.getText().toString();
        final String kota = txtKota.getText().toString();
        String password = txtPassword.getText().toString();
        String konpass = txtKonPass.getText().toString();


        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Masukkan Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(kota)) {
            Toast.makeText(this, "Masukkan Kota", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(konpass)) {
            Toast.makeText(this, "Konfirmasi Password", Toast.LENGTH_SHORT).show();
            return;
        }// end of else if


        mProg.setMessage("Loading...");
        mProg.show();
        daftarUser(email, password);

        String userID =mdatabase.push().getKey();
        user regis_user = new user(txtUsername.getText().toString(), txtemail.getText().toString(), txtKota.getText().toString());
        mdatabase.child("User").push().setValue(regis_user);
        //updateUser(regis_user);

    }

    public void updateUser(user user) {

        mdatabase.child("User").child("nama").setValue(user.getname());
        mdatabase.child("User").child("email").setValue(user.getEmail());
        mdatabase.child("User").child("kota").setValue(user.getKota());
        clearEditText();
    }

    private void daftarUser(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUp.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUp.this, Login.class);
                    startActivity(i);
                }
            }
        });
    }

    private void clearEditText() {
        txtUsername.setText("");
        txtemail.setText("");
    }

    @Override
    public void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthlistener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthlistener!=null){
            firebaseAuth.removeAuthStateListener(mAuthlistener);
        }
    }
}


