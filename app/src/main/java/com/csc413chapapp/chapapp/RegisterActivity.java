package com.csc413chapapp.chapapp;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Paul on 12/5/2017.
 */

public class RegisterActivity extends AppCompatActivity{

    private Button submitBttn;
    private EditText txt_email;
    private EditText txt_password;
    private EditText txt_username;
    private CheckBox box;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    FirebaseUser user;

    String email;
    String password;
    String username;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitBttn = (Button) findViewById(R.id.reg_btn_submit);
        txt_email = (EditText) findViewById(R.id.reg_txt_email);
        txt_password = (EditText) findViewById(R.id.reg_txt_pw);
        txt_username = (EditText) findViewById(R.id.reg_username);

        mAuth = FirebaseAuth.getInstance();

        submitBttn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                email = txt_email.getText().toString().trim();
                password = txt_password.getText().toString().trim();
                username = txt_username.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password);
                uid = mAuth.getCurrentUser().getUid();

                // Write a message to the database
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("UserInfo").child(uid);

                myRef.child("email").setValue(email);
                myRef.child("username").setValue(username);


                //write userdata into database
                //writeUserData();

                //make Toast message when input username or password invalid
                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                //back to Login
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(i);
                finish();
            }

        });

        //following code is copied and pasted from paul
        //display pw check box function
        box = (CheckBox) findViewById(R.id.reg_box_pw);
        txt_password = (EditText) findViewById(R.id.reg_txt_pw);
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    //display password
                    txt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //hide password
                    txt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                //Keep the cursor at the end of the text
                txt_password.setSelection(txt_password.getText().length());
            }
        });

    }//end of onCreate

    public void writeUserData(){

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message").child(uid).child("text");

        myRef.setValue("Hello, World!");
    }


}
