package com.example.myapptodolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    // here we return new thread and install bool variable  ShouldCloseScreen in true by the  method
    // postValue in difference to the method setValue because getter has modifier LiveData and not
    // MutableLiveData
    public void saveNote(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.add(note);
                ShouldCloseScreen.postValue(true);
            }
        });
        thread.start();

    }



}
