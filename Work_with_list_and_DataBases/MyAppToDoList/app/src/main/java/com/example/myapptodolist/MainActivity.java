package com.example.myapptodolist;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNotes;
    private NotesAdapter notesAdapter;

    // creating array of notes by calling DatabaseNote.getInstance(); - the database from class
    // DatabaseNote
    private DatabaseNote databaseNote = DatabaseNote.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        notesAdapter = new NotesAdapter();


        // realization of erasing an element from the database by calling ClickListener which is
        // connected  by  the  adapter - adapter get an  activity OnClickListener in the class
        // NoteAdapter method OnBindViewHolder
         notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void OnNoteClick(Note note) {
                databaseNote.removeNote(note.getId());
                // refreshing view to show how  the  element erasing
                showNotes();
            }
        });

        recyclerViewNotes.setAdapter(notesAdapter);


        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
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
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNotes = findViewById(R.id.buttonAddNote);
       }

    // creating method which show all notes
    private void showNotes(){

        notesAdapter.setNotes(databaseNote.getNotes());
        }
}






