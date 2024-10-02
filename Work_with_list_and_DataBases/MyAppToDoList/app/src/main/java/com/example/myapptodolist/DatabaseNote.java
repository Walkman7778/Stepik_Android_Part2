package com.example.myapptodolist;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseNote {


    private ArrayList<Note> notes = new ArrayList<>();



    // next 2 public static field and method has name Singletone, - from 14-th to the 21-th line
    public static DatabaseNote instance = null;
    public static DatabaseNote getInstance(){

        if (instance == null){
            instance = new DatabaseNote();
        }
        return instance;
    }
    // singletone permit to use one Database in more activities, typo - synchronization in 2 activities
    // MainActivity and AddNoteActivity everytime when  we want to change data we call method
    // DatabaseNote.getInstance();




    // here we initialize the notes list and firstly add 20 notes to it in random way
    private DatabaseNote() {

        Random random =new Random();
        for (int i = 0; i < 20; i++){
            Note note = new Note("This is message № " + i, i, random.nextInt(3));
            notes.add(note);
        }
    }



    // method to add note to the notes list
    public void andNote(Note note){
        notes.add(note);
    }


    // method which remove note from note list
    public void removeNote(int id){
        for (int i = 0; i < notes.size(); i++){
            Note note = notes.get(i);
            if (note.getId() == id){
                notes.remove(note);
            }
        }

    }

    public ArrayList<Note> getNotes() {
        return new ArrayList<>(notes);
    }
}
