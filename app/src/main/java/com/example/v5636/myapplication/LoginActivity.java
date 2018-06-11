package com.example.v5636.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    private String userUID;
    Button mRegister_button,mLogin_button,mForget_button,mGoogle_button;
    EditText mEmail,mPassword;
    GoogleApiClient mGoogleApiClient;
    final String TAG="MainActivity";
    private GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.loginEmail);
        mPassword=findViewById(R.id.loginPassword);

        mRegister_button=findViewById(R.id.Register);
        mForget_button=findViewById(R.id.Forgotpass);
        mLogin_button=findViewById(R.id.Login);
        mGoogle_button=findViewById(R.id.google_button);

        mRegister_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            //    finish();
            }
        });

        mLogin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mForget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPassWordActivity.class));
                //finish();
            }
        });


        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        auth = FirebaseAuth.getInstance();

        mGoogle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//firebaseAuthWithGoogle();
            }
        });
        // [END config_signin]




        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("onAuthStateChanged", "登入:"+
                            user.getUid());
                    userUID =  user.getUid();
                }
                else{
                    Log.d("onAuthStateChanged", "已登出");
                }
            }
        };

    }

    private void login() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        Log.d("AUTH", email + "/" + password);

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please fill e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please fill e-mail", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //     Intent signIntent = Auth.GoogleSignInApi.getSignInIntent()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        // Sign in success, update UI with the signed-in user's information
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Log.d(TAG, "signInWithEmail:success");
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


            //------------------------------

            //finish();

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
        //FirebaseUser currentUser = auth.getCurrentUser();
    }
    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
     //   showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user =auth.getCurrentUser();
                            //
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                         //   Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // [START_EXCLUDE]
                    //    hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]
}
