package com.example.myapptodolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
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
        Disposable disposable = getNotesRx()
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
        Disposable disposable = removeRx(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("MainViewModel","remove note" + note.getId());
                        refreshList();
                    }
                });
        compositeDisposable.add(disposable);

    }


    /* creating new method getNotesRx which realize working with data in different threads as if it
    would be type Single in the room library only  it  is own mwthod we must subscribe on it
    otherwise it won't work */
    private Single<List<Note>> getNotesRx() {
        return Single.fromCallable(new Callable<List<Note>>() {
            @Override
            public List<Note> call() throws Exception {
                return notesDao.getNotes();
            }
        });
    };


    /* creating new method getNotesRx which realize working with data in different threads as if it
    would be type Completable in the room library only  it  is own mwthod we must subscribe on it
    otherwise it won't work */
    private Completable removeRx(Note note){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                notesDao.remove(note.getId());
            }
        });

    };

    /* the difference from ```Single.fromCallable``` and ``Compleatble.fromAction``` is - the firs
    function get as argument a list and return it as data but second don't transmit the list of data
    in the callable function*/





    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
