package com.example.dogs;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainViewModel extends AndroidViewModel {


    // writing a url adress
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_STATUS = "status";
    private static final String TAG = "MainViewModel";

    public MutableLiveData<DogImage> dogImage = new MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<DogImage> getDogImage() {
        return dogImage;
    }

    public void loadDogImage() {
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
                JSONObject jsonObject = new JSONObject(data.toString());
                String message = jsonObject.getString(KEY_MESSAGE);
                String status = jsonObject.getString(KEY_STATUS);
                DogImage dogImage = new DogImage(message, status);


                Log.d(TAG, dogImage.toString());

            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
        }).start();

    }


}
