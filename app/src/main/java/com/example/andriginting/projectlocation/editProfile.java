package com.example.andriginting.projectlocation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.andriginting.projectlocation.adapterData.Config;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Map;

public class editProfile extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_CODE = 234;
    private Uri filepath;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;

    private TextView edit_gambarPropil;
    private TextView edit_nama, edit_kota;
    private TextView edit_email;
    private Button tombol_save;
    private Firebase ref;
    private static final int GALERY_INTENT = 2;
    private static final String TAG = "user.class.getname()";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edit_nama = (TextView) findViewById(R.id.edit_namaProfil2);
        edit_kota = (TextView) findViewById(R.id.edit_kota2);
        edit_email = (TextView) findViewById(R.id.edit_email2);
        tombol_save = (Button) findViewById(R.id.tombol_savepropil);
        edit_gambarPropil = (TextView) findViewById(R.id.edit_gambarProfil);
        edit_gambarPropil.setOnClickListener(this);

        Firebase.setAndroidContext(this);
        ref=new Firebase(Config.FIREBASE_URL);
        firebaseAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("User");
       edit_email.setText(firebaseAuth.getCurrentUser().getEmail());

        tombol_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp saveProfile=null;
                user mUser = new user(edit_nama.getText().toString(),edit_kota.getText().toString(),edit_email.getText().toString());

                saveProfile.updateUser(mUser);
                finish();
                startActivity(new Intent(editProfile.this,setting.class));
            }
        });

        ref.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue()!=null
                        && dataSnapshot.getKey().startsWith("-")){
                    Map<String,Object> value =(Map<String, Object>)dataSnapshot.getValue();
                    String username = value.get("name").toString();
                    String kota = value.get("kota").toString();
                    edit_nama.setText(username);
                    edit_kota.setText(kota);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void showFile() {
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(in.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(in, "Pilih Foto... "), PICK_IMAGE_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CODE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadfoto() {
        if (filepath != null) {
            final ProgressDialog mprog = new ProgressDialog(this);
            mprog.setTitle("Mohon Bersabar...");
            StorageReference stref = mStorage.child("images/ profile.jpg");
            stref.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progres =(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    mprog.setMessage((int)progres+"%");
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        if (v == edit_gambarPropil) {
            showFile();
            uploadfoto();
        }
    }
}
