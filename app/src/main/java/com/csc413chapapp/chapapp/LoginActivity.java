package com.csc413chapapp.chapapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Paul on 12/5/2017.
 */

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    private String userUID;

    //check pw chechbox
    private EditText pwInput;
    private CheckBox box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        //authStateListener to listen when login or logout
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //get User
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("onAuthStateChange", "Login: " + user.getUid());
                    userUID = user.getUid();
                } else {
                    Log.d("onAuthStateChange", "Logout");
                }
            }
        };


        //display pw check box function
        box = (CheckBox) findViewById(R.id.box_pw);
        pwInput = (EditText) findViewById(R.id.login_txt_pw);
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //display password
                    pwInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //hide password
                    pwInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //Keep the cursor at the end of the text
                pwInput.setSelection(pwInput.getText().length());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }

    public void login(View v) {
        String email = ((EditText) findViewById(R.id.login_txt_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_txt_pw)).getText().toString();
        Log.d("login method", email + "/" + password);
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent i = new Intent(LoginActivity.this, AfterLoginActivity.class);
                    LoginActivity.this.startActivity(i);

                } else {
                    //make Toast message when input username or password invalid
                    Toast.makeText(LoginActivity.this, "invalid user or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }//end of login method

    public void toRegister(View v) {

        Intent i = new Intent(this, RegisterActivity.class);
        this.startActivity(i);
    }
}//end of main class
