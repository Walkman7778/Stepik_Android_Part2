package com.example.dogs;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;

import java.time.Duration;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private ImageView imageView;
    private ProgressBar progressBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        MainViewModel viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainViewModel.class);
        viewModel.loadDogImage();
        viewModel.imposibileloading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean notLoading) {
                if(notLoading){
                    Toast.makeText(MainActivity.this,
                            R.string.toasterror, Toast.LENGTH_LONG).show();
                }
            }
        });
        viewModel.loadingImage.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if(loading){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        viewModel.getDogImage().observe(this, new Observer<DogImage>() {
            @Override
            public void onChanged(DogImage dogImage) {
                if (dogImage != null) {
                    Glide.with(MainActivity.this).load(dogImage.getName()).into(imageView);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadDogImage();
            }
        });
    }

    private void initViews() {
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.button);

    }


}