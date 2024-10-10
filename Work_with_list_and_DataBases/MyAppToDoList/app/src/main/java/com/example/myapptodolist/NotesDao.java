package com.example.myapptodolist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {


    // subscribing to the LiveData
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Insert
    void add(Note note);


    @Query("DELETE FROM notes WHERE id = :id")
    void remove(int id);

}
