package com.example.myapp03messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        EditText editTextMessage = findViewById(R.id.editTextTextPersonName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                // in the buttonClickListener we call function lauchNextScreen which has as parameter
                // string variable containing text from first layout editText container
                launchNextScreen(message);
            }
        });
    }
    private void launchNextScreen(String message){
        // here intent is using for opening new activity which has as parameters
        // context and activity which must be opened with resolution class -it is an object
        Intent intent = new Intent(this, ReceivedMessageActivity.class);
        // we put in intent variable message with key "message" and transmit it to the second activity
        intent.putExtra("message", message);
        // starting second activity
        startActivity(intent);


    }
}