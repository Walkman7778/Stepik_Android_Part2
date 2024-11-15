package com.example.myapptodolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNoteViewModel extends AndroidViewModel {

    // here we subscribe to the notesDao directly.
    private NotesDao notesDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDatabase.getInstance(application).notesDao();
    }

    public void saveNote(Note note){
        Disposable disposable = notesDao.add(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("AddNoteViewModel", "subscribe");
                    }
                },
                      /* here I used new Consumer for throwing new exception */
                        new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("AddNoteViewModel", "save note error");
                    }
                });
        compositeDisposable.add(disposable);
    }



    public Completable addNote(Note note){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception{
                notesDao.add(note);

            }
        });

    }



    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
