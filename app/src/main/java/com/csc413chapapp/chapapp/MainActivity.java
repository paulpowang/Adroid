package com.csc413chapapp.chapapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;


    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //Action button

        fab.setOnClickListener(new View.OnClickListener()

        {

            @Override

            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input);
//Action button with onClick so the user can basically click it and it will give an input

                FirebaseDatabase.getInstance().getReference().push().setValue(new ChatMessage(input.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));

                input.setText("");
            }


            private void displayChatMessages() {

                ListView listOfMessages = (ListView) findViewById(R.id.list_of_messages);

                adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.message, FirebaseDatabase.getInstance().getReference()) {

                    @Override

                    protected void populateView(View v, ChatMessage model, int position) {

                        TextView messageText = (TextView) v.findViewById(R.id.message_text);
                        TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                        TextView messageTime = (TextView) v.findViewById(R.id.message_time);


                        messageText.setText(model.getMessageText());

                        messageUser.setText(model.getMessageUser());


                        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime())); //Date for the text message, shows timestamp
                    }
                };

                listOfMessages.setAdapter(adapter);

    }

        });
};

}