package com.example.myapp04gamescore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private int score1 = 0;
    private int score2 = 0;
    private TextView textTeam1;
    private TextView textTeam2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        // here we transmit to the variables score1 and score2 parameters from bundle by their key
        // only if it is not first activation of activity - first using till rotation the screen
        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("score1");
            score2 = savedInstanceState.getInt("score2");
        }

        textTeam1 = findViewById(R.id.textCountTeam1);
        textTeam2 = findViewById(R.id.textCountTeam2);
        score1();
        score2();

        textTeam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score1++;
                score1();

            }
        });

        textTeam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score2++;
                score2();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("MainActivity", "onSaveInstanceState");
        // here we transmit in object bundle values of variables score1 and score2 till rotating the screen
        outState.putInt("score1", score1);
        outState.putInt("score2", score2);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("MainActivity", "onPostResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }









    protected void score1(){
        textTeam1.setText(String.valueOf(score1));
    }


    protected void score2(){
        textTeam2.setText(String.valueOf(score2));
    }
}