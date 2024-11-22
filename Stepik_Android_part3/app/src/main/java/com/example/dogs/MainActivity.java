package com.example.dogs;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainViewModel.class);
        viewModel.loadDogImage();
        viewModel.getDogImage().observe(this, dogImage -> {
            if (dogImage != null) {
                Log.d(TAG, dogImage.toString());
            }
        });




    }




}