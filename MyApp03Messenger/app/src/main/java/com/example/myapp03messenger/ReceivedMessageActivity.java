package com.example.myapp03messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceivedMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_message);
        // getting intent from  first activity with its content
        Intent intent = getIntent();
        // creating a string variable in which we put value of intent variable with key "message"
        String message = intent.getStringExtra("message");
        TextView textView = findViewById(R.id.textV2ReceivedMessage);
        textView.setText(message);
    }
}