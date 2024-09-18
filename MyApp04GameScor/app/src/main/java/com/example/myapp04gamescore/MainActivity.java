package com.example.myapp04gamescore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int score1 = 0;
    private int score2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // here we transmit to the variables score1 and score2 parameters from bundle by their key
        // only if it is not first activation of activity - first using till rotation the screen
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("score1");
            score2 = savedInstanceState.getInt("score2");
        }
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        // here we transmit in object bundle values of variables score1 and score2 till rotating the screen
        outState.putInt("score1", score1);
        outState.putInt("score2", score2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}