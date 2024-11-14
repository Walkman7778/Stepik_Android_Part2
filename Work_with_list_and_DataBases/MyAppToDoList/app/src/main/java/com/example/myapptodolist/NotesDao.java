package com.example.myapptodolist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NotesDao {


    // subscribing to the data as if it would be simple data ,  and roomm  wouldn t work with it
    @Query("SELECT * FROM notes")
    List<Note> getNotes();

    @Insert
    void add(Note note);


    @Query("DELETE FROM notes WHERE id = :id")
    void remove(int id);

}
