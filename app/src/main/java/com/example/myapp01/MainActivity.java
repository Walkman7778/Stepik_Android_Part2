package com.example.myapp01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.plainTextSampleAnswer);
        Button button = findViewById(R.id.button);
        TextView textViewCorrectAnsw = findViewById(R.id.correctView);
        TextView textViewInCorrectAnsw = findViewById(R.id.incorrectView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                int number = Integer.parseInt(text);
                if (number == 12){
                    textViewCorrectAnsw.setVisibility(View.VISIBLE);
                    textViewInCorrectAnsw.setVisibility(View.GONE);

                } else{
                    textViewInCorrectAnsw.setVisibility(View.VISIBLE);
                    textViewCorrectAnsw.setVisibility(View.GONE);
                }
            }
        });

    }
}
