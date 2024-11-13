package com.example.myapptodolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private NotesDao notesDao;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    // creating variable MutableLiveData notes for possible changing it in DB NotesDao
    private MutableLiveData<List<Note>>  notes = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDao = NoteDatabase.getInstance(application).notesDao();

    }


    public LiveData<List<Note>> getNotes(){
        return notes;
    }


    /* In method refreshList we use all  the  same types of disposable as in previous commit and
     threads have the  same structure ,  the main difference is that we use Single type of RxJava3
     library in NotesDao and consecutive in the refreshList method. We subscribe not to the new
     Action but tu the new Consumer it helps for returning data not only fact of operating with
     data*/
    public void refreshList(){
        Disposable disposable = notesDao.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> notesFromDb) throws Throwable {
                        notes.setValue(notesFromDb);
                    }
                });
        compositeDisposable.add(disposable);
    }




    public void remove(Note note){
        Disposable disposable = notesDao.remove(note.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("MainViewModel","subscribe");
                        refreshList();
                    }
                });
        compositeDisposable.add(disposable);

    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     * <p>
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
