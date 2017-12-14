package com.csc413chapapp.chapapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Paul on 12/5/2017.
 */

public class RegisterActivity extends AppCompatActivity{

    private Button submitBttn;
    private EditText txt_email;
    private EditText txt_password;

    private FirebaseAuth mAuth;

    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitBttn = (Button) findViewById(R.id.reg_btn_submit);
        txt_email = (EditText) findViewById(R.id.reg_txt_email);
        txt_password = (EditText) findViewById(R.id.reg_txt_pw);

        mAuth = FirebaseAuth.getInstance();

        submitBttn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                email = txt_email.getText().toString().trim();
                password = txt_password.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password);

            }

        });

    }


}
