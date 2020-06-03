package com.example.aecmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainAapActivity extends AppCompatActivity {

    private Button buttonEducation;
    private Button buttonReg;
    private Button buttonProfile;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aap);


        buttonEducation=(Button) findViewById(R.id.buttonEducation);
        buttonReg=(Button)findViewById(R.id.buttonReg);
        buttonProfile=(Button)findViewById(R.id.buttonProfile);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        firebaseDatabase = FirebaseDatabase.getInstance();

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAapActivity.this,Show_ProfileActivity.class));

            }
        });










        buttonEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAapActivity.this,Assignment_Activity.class));
            }
        });




        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAapActivity.this,Registratin_Activity.class));
            }
        });


    }


}
