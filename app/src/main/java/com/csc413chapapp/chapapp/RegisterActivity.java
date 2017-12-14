package com.csc413chapapp.chapapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Paul on 12/5/2017.
 */

public class RegisterActivity extends AppCompatActivity{

    private Button submitBttn;
    private EditText txt_email;
    private EditText txt_password;

    //check pw chechbox
    private EditText pwInput;
    private CheckBox box;

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

                //make Toast message when input username or password invalid
                Toast.makeText(RegisterActivity.this, "Register Success, Please Return to Login Page to Login", Toast.LENGTH_LONG).show();

            }

        });

        //display pw check box function
        box = (CheckBox) findViewById(R.id.reg_box_pw);
        pwInput = (EditText) findViewById(R.id.reg_txt_pw);
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


}
