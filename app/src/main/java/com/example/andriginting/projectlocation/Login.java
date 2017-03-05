package com.example.andriginting.projectlocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtPassLogin;
    private TextView signup_text;
    private Button btn_login;
    private ProgressBar mprog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        txtEmailLogin = (EditText) findViewById(R.id.editEmailLogin);
        txtPassLogin = (EditText) findViewById(R.id.editPassLogin);
        btn_login = (Button) findViewById(R.id.btn_login);
        signup_text = (TextView) findViewById(R.id.signup_text);
        mprog=(ProgressBar)findViewById(R.id.progressbar_login);

        //firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();

        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(Login.this,Menu1.class));
                }
            }
        };

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mulaiLogin();
                finish();
            }
        });

        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

    } //end on create

    //method untuk masuk ke firebase loginAuth
    public void mulaiLogin() {
        String email = txtEmailLogin.getText().toString();
        String pass = txtPassLogin.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Login.this, "Email belum terisi", Toast.LENGTH_LONG).show();
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(Login.this, "Password belum terisi", Toast.LENGTH_LONG).show();
        }
        mprog.setVisibility(View.VISIBLE);

        //auth user
        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mprog.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            Toast.makeText(Login.this,"There is some Error",Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i = new Intent(Login.this,Menu1.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });

    } //end of method mulaiLogin

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

}
