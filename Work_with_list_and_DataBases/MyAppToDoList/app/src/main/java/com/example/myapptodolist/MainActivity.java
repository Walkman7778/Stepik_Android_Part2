package com.example.myapptodolist;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewNotes;
    private FloatingActionButton buttonAddNotes;
    private NotesAdapter notesAdapter;

    //creating example of class handler -  which  contain link  for  the  main thread
    //this object can crunch messages of type runnable

    private Handler handler = new Handler(Looper.getMainLooper());

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
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                showNotes();
                            }
                        });

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

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNotes = findViewById(R.id.buttonAddNote);
    }

    // creating method which show all notes
    private void showNotes() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = noteDatabase.notesDao().getNotes();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        notesAdapter.setNotes(notes);
                    }
                });

            }
        });
        thread.start();

    }
}






