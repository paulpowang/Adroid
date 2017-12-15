package com.csc413chapapp.chapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button start;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.start);
       // text = (TextView)findViewById(R.id.old)
         start.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent neo = new Intent(MainActivity.this, button2.class);
            startActivity(neo);
             }
         });

    }
}
