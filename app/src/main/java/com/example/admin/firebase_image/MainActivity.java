package com.example.admin.firebase_image;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private ImageView imageView;

    private EditText et_dob;
    private EditText et_height;
    private EditText et_country;
    private EditText et_education;
    private EditText et_work;
    private EditText et_income;
    private EditText et_marital;
    private EditText et_mother;
    private EditText et_religion;



    private EditText et_name;
    private EditText et_email;
    private EditText et_password;
    private EditText et_confirmpassword;
    private EditText et_mobile;
    private EditText et_message;

    private Uri imgUri;


    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";
    public static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        imageView = (ImageView) findViewById(R.id.imageView);


        et_dob = (EditText) findViewById(R.id.et_dob);
        et_height = (EditText) findViewById(R.id.et_height);
        et_country = (EditText) findViewById(R.id.et_country);
        et_education = (EditText) findViewById(R.id.et_education);
        et_work = (EditText) findViewById(R.id.et_work);
        et_income = (EditText) findViewById(R.id.et_income);
        et_marital = (EditText) findViewById(R.id.et_marital);
        et_mother = (EditText) findViewById(R.id.et_mother);
        et_religion = (EditText) findViewById(R.id.et_religion);

        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmpassword = (EditText) findViewById(R.id.et_confirmpassword);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_message = (EditText) findViewById(R.id.et_message);
    }

    public void btnChoose(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @SuppressWarnings("VisibleForTests")
    public void reg_btn(View v) {
        if (imgUri != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registered Successfully");
            dialog.show();

            //Get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(imgUri));

            //Add file to reference

            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    String dob = et_dob.getText().toString().trim();
                    String height = et_height.getText().toString().trim();
                    String country = et_country.getText().toString().trim();
                    String education = et_education.getText().toString().trim();
                    String work = et_work.getText().toString().trim();
                    String income = et_income.getText().toString().trim();
                    String marital = et_marital.getText().toString().trim();
                    String mother = et_mother.getText().toString().trim();
                    String religion = et_religion.getText().toString().trim();


                    String name = et_name.getText().toString().trim();
                   String email = et_email.getText().toString().trim();
                   String password = et_password.getText().toString().trim();
                 String confirmpassword = et_confirmpassword.getText().toString().trim();
                 String mobile = et_mobile.getText().toString().trim();
                 String message = et_message.getText().toString().trim();

                    dialog.dismiss();
                    //Display success toast msg
                    Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                    Users imageUpload = new Users(dob, height, country, education, work,
                            income, marital, mother, religion, name ,email, password, confirmpassword, mobile, message, taskSnapshot.getDownloadUrl().toString());
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(imageUpload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //Dimiss dialog when error
                            dialog.dismiss();
                            //Display err toast msg
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(getApplicationContext(), "Please select image", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnShowListImage_Click(View v) {
        Intent i = new Intent(MainActivity.this, UsersListActivity.class);
        startActivity(i);
    }
}
