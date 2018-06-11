package com.example.v5636.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassWordActivity extends AppCompatActivity {
    EditText mEmail;
    Button mSubmit;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word);
        mEmail=findViewById(R.id.forgetEmail);
        mSubmit=findViewById(R.id.Submit);
        firebaseAuth = FirebaseAuth.getInstance();


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=mEmail.getText().toString();
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(getApplicationContext(),"Please fill e-mail",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Password reset link was sent your email address",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Mail sending error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });


    }
}
