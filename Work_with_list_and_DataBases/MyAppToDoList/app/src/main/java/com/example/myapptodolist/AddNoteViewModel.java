package com.example.myapptodolist;

import android.app.Application;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNoteViewModel extends AndroidViewModel {

    // here we subscribe to the notesDao directly.
    private NotesDao notesDao;
    private MutableLiveData<Boolean> ShouldCloseScreen = new MutableLiveData<>();
    public AddNoteViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDatabase.getInstance(application).notesDao();
    }

    public LiveData<Boolean> getShouldCloseScreen() {
        return ShouldCloseScreen;
    }
    /* here we return new thread and install bool variable  ShouldCloseScreen in true by the  method
         postValue in difference to the method setValue because getter has modifier LiveData and not
        MutableLiveData ------ this action is  made  in  ontoher way by using RxJava3 libraries
        just switching the threads  is  easier */
    public void saveNote(Note note){
        notesDao.add(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
            @Override
            public void run() throws Throwable {
                ShouldCloseScreen.postValue(true);
            }
        });



    }



}
