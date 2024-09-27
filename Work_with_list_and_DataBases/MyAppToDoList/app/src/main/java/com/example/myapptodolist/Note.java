package com.example.myapptodolist;

import android.widget.TextView;

public class Note {


    private String text;
    private int id;
    private int priority;


    public Note(String text, int id, int priority) {
        this.text = text;
        this.id = id;
        this.priority = priority;
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
