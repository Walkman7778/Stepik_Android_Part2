package com.example.myapptodolist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface NotesDao {


    // subscribing to the LiveData
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Insert
    Completable add(Note note);


    @Query("DELETE FROM notes WHERE id = :id")
    Completable remove(int id);

}
