package com.example.aecmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Show_ProfileActivity extends AppCompatActivity {
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__profile);
        mref= FirebaseDatabase.getInstance().getReference("Student");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                student post = dataSnapshot.getValue(student.class);
                System.out.println(post);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });


    }
}
