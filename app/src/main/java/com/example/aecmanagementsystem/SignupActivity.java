package com.example.aecmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private EditText txtFullName;
    private EditText txtEmails;
    private EditText txtBranch;
    private EditText txtSemester;
    private EditText txtRoll;
    private EditText txtPassword;
    private Button btn_Register;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtFullName=(EditText)findViewById(R.id.txtFullName);
        txtEmails=(EditText)findViewById(R.id.txtEmails);
        txtSemester=(EditText)findViewById(R.id.txtSemester);
        txtBranch=(EditText)findViewById(R.id.txtBranch);
        txtRoll=(EditText)findViewById(R.id.txtRoll);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        btn_Register=(Button)findViewById(R.id.btn_Register);

        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        firebaseAuth=FirebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName=txtFullName.getText().toString();
                final String emails=txtEmails.getText().toString();
                final String branch=txtBranch.getText().toString();
                final String semester=txtSemester.getText().toString();
                final String roll=txtRoll.getText().toString();
                final String pass=txtPassword.getText().toString();

                if (TextUtils.isEmpty(fullName)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(emails)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(branch)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Branch", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(semester)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Semester", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(roll)) {
                    Toast.makeText(SignupActivity.this, "Please Enter Roll", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(SignupActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseAuth.createUserWithEmailAndPassword(emails, pass)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        student information = new student(
                                                fullName,emails,branch,semester, roll, pass


                                        );

                                        FirebaseDatabase.getInstance().getReference("Student").child(FirebaseAuth.getInstance().getUid()).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(SignupActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                            }
                                        });


                                    } else {

                                        Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                                    }

                                }
                            });

                }


            }
        });


    }
}



