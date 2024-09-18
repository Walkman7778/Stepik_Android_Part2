package com.example.myapp04gamescore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int score1 = 0;
    private int score2 = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textTeam1 = findViewById(R.id.textCountTeam1);
        TextView textTeam2 = findViewById(R.id.textCountTeam2);

        textTeam1.setText(String.valueOf(score1));
        textTeam2.setText(String.valueOf(score2));

        textTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTeam1.setText(String.valueOf(++score1));
            }
        });

        textTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTeam2.setText(String.valueOf(++score2));
            }
        });



    }
}