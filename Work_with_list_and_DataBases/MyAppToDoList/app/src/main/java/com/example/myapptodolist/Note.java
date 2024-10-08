package com.example.myapptodolist;

import android.widget.TextView;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;


    public Note(  String text, int id, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    @Ignore
    public Note(String text, int priority){
        this(text, 0, priority);

    }





    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }
}
