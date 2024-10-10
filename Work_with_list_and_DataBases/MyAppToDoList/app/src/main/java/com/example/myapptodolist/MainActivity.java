package com.example.myapptodolist;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;


import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNotes;
    private NotesAdapter notesAdapter;



    // creating array of notes by calling DatabaseNote.getInstance(); - the database from class
    // DatabaseNote
    private NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        noteDatabase = NoteDatabase.getInstance(getApplication());


        initViews();
        notesAdapter = new NotesAdapter();


        // realization of erasing an element from the database by calling ClickListener which is
        // connected  by  the  adapter - adapter get an  activity OnClickListener in the class
        // NoteAdapter method OnBindViewHolder
        /* notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void OnNoteClick(Note note) {
                databaseNote.removeNote(note.getId());
                // refreshing view to show how  the  element erasing
                showNotes();
            }
        });*/

        // realization of deleting note-(database.note) by swipe on left or right
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                Note note = notesAdapter.getNotes().get(position);


                // creating new thread which permit to access ```noteDatabase.notesDao().
                // remove(note.getId());``` from main thread
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        noteDatabase.notesDao().remove(note.getId());
                        // refreshing view to show how  the  element erasing

                        // this object handler call  in main  thread object runnable where  is
                        // transmitted method show notes, because we cannot call a method crunching
                        // data in background state(calling view element ) in the main thread


                    }
                });
                thread.start();


            }
        });

        recyclerViewNotes.setAdapter(notesAdapter);


        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        // attaching itemTouchHelper to the layout recyclerViewNOtes
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

        // here I subscribe  to the object of LiveData. It is made for saving phone resources,
        // in such a way we don't need methods show and on Onswipe, the data is updating
        // automatically. Also  we  don't  need a handler.
        noteDatabase.notesDao().getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNotes(notes);
            }
        });



        buttonAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddNoteActivity.newintent(MainActivity.this);
                startActivity(intent);
            }
        });


    }


    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNotes = findViewById(R.id.buttonAddNote);
    }

}






