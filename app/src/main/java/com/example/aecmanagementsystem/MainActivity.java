package com.example.aecmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private ImageView txtImage;
    private EditText txtPassword;
    private Button btnLogin,btnRegister;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtImage=(ImageView)findViewById(R.id.txtImage);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        firebaseAuth=FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoregisterActivity();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emails = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (TextUtils.isEmpty(emails)) {
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    firebaseAuth.signInWithEmailAndPassword(emails, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(MainActivity.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainAapActivity.class));

                                    } else {


                                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                                    }


                                }

                            });


                }
            }
        });
    }

    private void gotoregisterActivity() {
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }
}
