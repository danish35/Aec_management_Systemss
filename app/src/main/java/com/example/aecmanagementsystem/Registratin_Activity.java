package com.example.aecmanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Registratin_Activity extends AppCompatActivity {

    private EditText editPdf;
    private Button btnUploads;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratin_);


        editPdf=(EditText)findViewById(R.id.editPdf);
        btnUploads=(Button)findViewById(R.id.btnUploads);
        storageReference=FirebaseStorage.getInstance().getReference();
        databaseReference=FirebaseDatabase.getInstance().getReference("Registration Form");

        btnUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectpdffile();
            }
        });

    }

    private void selectpdffile() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData()!=null)

        {
            uploadPDFFile(data.getData());
        }


    }

    private void uploadPDFFile(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading.....");
        progressDialog.show();

        StorageReference reference=storageReference.child("Registration/"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (! uri.isComplete());
                Uri url=uri.getResult();


                upload_pdf upload_pdf= new upload_pdf(editPdf.getText().toString(),url.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue("upload PDF");
                Toast.makeText(Registratin_Activity.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {


                double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded"+(int)progress+"%");

            }
        });
    }


}
