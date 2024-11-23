package com.example.dogs;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {


    // writing a url adress
    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_STATUS = "status";
    private static final String TAG = "MainViewModel";

    public MutableLiveData<DogImage> dogImage = new MutableLiveData<>();

    public MutableLiveData<Boolean> loadingImage = new MutableLiveData<>();
    public MutableLiveData<Boolean> imposibileloading = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<DogImage> getDogImage() {
        return dogImage;
    }

    public LiveData<Boolean> getLoadDogImage() {
        return loadingImage;
    }

    public LiveData<Boolean> getImposibileloading() {
        return imposibileloading;
    }

    public void loadDogImage() {
        Disposable disposable = loadDogImageRX().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Throwable {
                                loadingImage.setValue(Boolean.TRUE);
                                imposibileloading.setValue(Boolean.TRUE);
                            }
                        }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        loadingImage.setValue(Boolean.FALSE);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        imposibileloading.setValue(Boolean.FALSE);
                    }
                }).subscribe(image -> dogImage.setValue(image), throwable ->
                        Log.d(TAG, throwable.getMessage()));
        compositeDisposable.add(disposable);

    }

    private Single<DogImage> loadDogImageRX() {
        return Single.fromCallable(() -> {
            URL url = new URL(BASE_URL);
            HttpURLConnection uRLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = uRLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result;
            StringBuilder data = new StringBuilder();

            do {
                result = bufferedReader.readLine();
                data.append(result);
            } while (result != null);
            JSONObject jsonObject = new JSONObject(data.toString());
            String message = jsonObject.getString(KEY_MESSAGE);
            String status = jsonObject.getString(KEY_STATUS);
            return new DogImage(message, status);

        });


    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
