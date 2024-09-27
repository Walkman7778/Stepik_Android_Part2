package com.example.myapptodolist;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayoutsNotes;
    private FloatingActionButton buttonAddNotes;

    // creating array of notes
    private ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        // next cycle fill array notes with elements containing fields from class Note: text-context
        // id and priority of message
        Random random =new Random();
        for (int i = 0; i < 20; i++){
            Note note = new Note("This is message № " + i, i, random.nextInt(3));
            notes.add(note);
        }

        shoowNotes();



    }

    private void initViews(){
        linearLayoutsNotes = findViewById(R.id.linearLayoutsNotes);
        buttonAddNotes = findViewById(R.id.buttonAddNote);
    }

    // creating method which show all notes
    private void shoowNotes(){
        for (Note note:notes){


            // creating view off note_item sample layout
            // getLayoutInflater().inflate get as arguments : layout sample id, layout output, and
            // attachment in my case false

            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutsNotes,
                    false               );

            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            // creating resource value color for note background
            int colorResId;
            switch (note.getPriority()){
                case 0:
                    colorResId = android.R.color.holo_green_light;
                    break;
                case 1:
                    colorResId = android.R.color.holo_orange_light;
                    break;
                default:
                    colorResId = android.R.color.holo_red_light;
                    break;
            }

            // here function ContextCompat convert value in color for note.
            int color = ContextCompat.getColor(this, colorResId);

            textViewNote.setBackgroundColor(color);




            linearLayoutsNotes.addView(view);
        }
    }






}