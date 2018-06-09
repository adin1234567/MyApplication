package com.example.v5636.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    public EditText email, password, repeat_password;
    Button registerButton, already_loginButton;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authListener;
    private String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //region UI
        email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        repeat_password = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.RegisterButton);
       already_loginButton = findViewById(R.id.already);
       already_loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
               }
       });
        //endregion
        firebaseAuth = FirebaseAuth.getInstance();
        //Set Listener on Firebase
        authListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Log.d("onAuthStateChanged", "登入:" + user.getUid());
                    userUID = user.getUid();
                }
            }
        };


        //Register Button
        registerButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String re_password = repeat_password.getText().toString();

                if (TextUtils.isEmpty(user_email)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(user_password)) {
                    Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (user_password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!user_password.equals(re_password)) {
                    return;

                }

                firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Complete Register", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email or password incorrect", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}