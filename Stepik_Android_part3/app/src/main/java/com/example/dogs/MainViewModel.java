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


    private static final String TAG = "MainViewModel";

    public MutableLiveData<DogImage> dogImage = new MutableLiveData<>();

    public MutableLiveData<Boolean> loadingImage = new MutableLiveData<>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<DogImage> getDogImage() {
        return dogImage;
    }

    public LiveData<Boolean> getloadingImage() {
        return loadingImage;
    }

    public LiveData<Boolean> getisError() {
        return isError;
    }

    public void loadDogImage() {
        Disposable disposable = loadDogImageRX().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Throwable {
                                loadingImage.setValue(true);
                                isError.setValue(false);

                            }
                        }).doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        loadingImage.setValue(false);
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        isError.setValue(true);
                    }
                }).subscribe(new Consumer<DogImage>() {
                    @Override
                    public void accept(DogImage image) throws Throwable {
                        dogImage.setValue(image);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, "Error: " + throwable.getMessage());
                    }
                });

        compositeDisposable.add(disposable);

    }

    private Single<DogImage> loadDogImageRX() { return ApiFactory.getApiService().loadDogImage();}
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
