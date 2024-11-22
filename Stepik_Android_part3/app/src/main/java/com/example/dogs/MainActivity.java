package com.example.dogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    // writing a url adress
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }


    private void loadDogImage() {
        // here  as  usual working  with  intrnet and all  operations in internet must  have their
        // own threads, so  for best  working is used new Thread
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL);
                HttpURLConnection uRLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = uRLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String result;
                StringBuilder data = new StringBuilder();

                do {
                    result = bufferedReader.readLine();
                    if (result != null) {
                        data.append(result);
                    }
                } while (result != null);
                Log.d("MainActivity", data.toString());

            } catch (Exception e) {
                Log.d("MainActivity", e.toString());
            }
        }).start();

    }

}