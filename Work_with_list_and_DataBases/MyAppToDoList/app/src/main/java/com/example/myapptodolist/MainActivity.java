package com.example.myapptodolist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayoutsNotes;
    private FloatingActionButton buttonAddNotes;

    // creating array of notes by calling DatabaseNote.getInstance(); - the database from class
    // DatabaseNote
    private DatabaseNote databaseNote = DatabaseNote.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        buttonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddNoteActivity.newintent(MainActivity.this);
                startActivity(intent);
            }
        });



    }



    // here I moved method show notes from action - on create - to the action on Resume it was done
    // because we must recreate activity aster returning from AddNoteActivity
    @Override
    protected void onPostResume() {
        super.onPostResume();
        showNotes();
    }

    private void initViews(){
        linearLayoutsNotes = findViewById(R.id.linearLayoutsNotes);
        buttonAddNotes = findViewById(R.id.buttonAddNote);
    }

    // creating method which show all notes
    private void showNotes(){
        // here I used linearLayoutsNotes.removeAllViews() for clearing layout before recreating it
        linearLayoutsNotes.removeAllViews();
        for (Note note: databaseNote.getNotes()){


            // creating view off note_item sample layout
            // getLayoutInflater().inflate get as arguments : layout sample id, layout output, and
            // attachment in my case false

            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayoutsNotes,
                    false               );
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseNote.removeNote(note.getId());
                    showNotes();
                }
            });

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