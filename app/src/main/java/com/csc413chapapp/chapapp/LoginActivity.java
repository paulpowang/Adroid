package com.csc413chapapp.chapapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Paul on 11/27/2017.
 */

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    private String userUID;
    FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(authListener);
    }//end of onStart
    @Override
    protected void onStop(){
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }//end of onStop


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        //success or error message after login
        authListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){//Login success
                    Log.d("onAuthStateChanged", "Login: " + user.getUid());
                    userUID = user.getUid();
                }else{//Logout or Login failed
                    Log.d("onAuthStateChanged", "Logout");
                }
            }
        };//end of authListener
    }//end of onCreate

    public void login(View v){
        //get the email entered
        final String email = ((EditText)findViewById(R.id.login_txt_email)).getText().toString();
        //get the password entered
        final String password = ((EditText)findViewById(R.id.login_txt_pw)).getText().toString();
        Log.d("AUTH", email+"/"+password);
        //link to firebase to sign in
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Login failed
                if(!task.isSuccessful()){
                    Log.d("onComplete", "Login failed");
                    //register new user
                    register(email,password);
                }
            }//end of onComplete
        });//end of SignIn
    }//end of login method

    private void register (final String email, final String password){
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Login Failed")
                .setMessage("Account doesn't exist, do you want to register as new user?")
                //Button to Register
                .setPositiveButton("Register",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                createUser(email, password);
                            }
                        })
                //Botton to Cancel register
                .setNeutralButton("Cancel", null)
                .show();
    }

    private void createUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String message;
                                        if(task.isSuccessful()){
                                            message = "Register Success!!";
                                        }else{
                                            message ="Register Failed!!";
                                        }
                                new AlertDialog.Builder(LoginActivity.this)
                                        .setMessage(message)
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        });
    }//end of Create New User



}//end of Login Activity
