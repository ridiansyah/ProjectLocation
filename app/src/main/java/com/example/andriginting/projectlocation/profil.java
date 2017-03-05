package com.example.andriginting.projectlocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class profil extends AppCompatActivity {

    private TextView namaprofil,namakota;
    private TextView txtemail;
    private TextView poin;
    private ImageView setting;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mData;
    private Firebase ref;

    private final static String TAG = user.class.getSimpleName();
    private String userId;

    private CircleProgressView mCircleProgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        namaprofil = (TextView) findViewById(R.id.id_namaProfil);
        txtemail = (TextView) findViewById(R.id.id_emailProfil);
        setting = (ImageView) findViewById(R.id.tmbl_setting);
        namakota=(TextView)findViewById(R.id.id_namakota);

        mCircleProgView = (CircleProgressView)findViewById(R.id.circle_progress_view);
        mCircleProgView.setTextEnabled(false);
        mCircleProgView.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleProgView.setStartAngle(90);
        mCircleProgView.setProgressWithAnimation(20,2000);
        mCircleProgView.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float v) {

            }

            @Override
            public void onAnimationEnd() {

            }
        });

        //coba firebase lagi
        mDatabase=FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        ref=new Firebase("https://roundme-366f1.firebaseio.com/");

        mData=FirebaseDatabase.getInstance();
        //masuk ke reference user
        mDatabase=mData.getReference("User");
        userId=mDatabase.push().getKey();
        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user dataUser = dataSnapshot.getValue(user.class);
                if  (dataUser==null){
                    Log.e(TAG,"Data user is null");
                    return;
                }
                Log.e(TAG,dataUser.name);
                namaprofil.setText(dataUser.name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue()!=null
                        && dataSnapshot.getKey().startsWith("-")){
                    Map<String,Object> value =(Map<String, Object>)dataSnapshot.getValue();
                    String Usersname=value.get("name").toString();
                    String kota = value.get("kota").toString();
                    namaprofil.setText(Usersname);
                    namakota.setText(kota);
                }
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser fbuser = firebaseAuth.getCurrentUser();
                if (fbuser != null) {
                    Log.d("Data User ", " " + fbuser.getUid());//uid auth user
                } else {
                    Log.d("Data User ", " LOGOUT");
                }
            }
        };

        //inisialisasi profil
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d("Data User ", " LOGIN");
            txtemail.setText(user.getEmail());

        } else {
            Log.d("Data User ", " LOGOUT");
            startActivity(new Intent(profil.this, Login.class));
            finish();
        }

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(profil.this, setting.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}