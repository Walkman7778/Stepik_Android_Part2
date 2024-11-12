package com.example.myapptodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {



    // creating local related (List ) - database for working with it in this activity
    private NoteDatabase noteDatabase;

    //creating vars

    private EditText editText;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button btnMakeNote;
    // viewModel help us for saving and modifying AddNoteActivity changes by view ModelProvider
    // and observer
    private AddNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);


        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AddNoteViewModel.class);
        viewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {

                if (shouldClose){
                    finish();
                }

            }
        });



        initViews();


        btnMakeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });





    }


    //function initializing vars
    private void initViews(){
        editText = findViewById(R.id.textForNote);
        btnMakeNote = findViewById(R.id.sendNoteButton);
        radioButtonLow = findViewById(R.id.lowPriorityButton);
        radioButtonMedium = findViewById(R.id.normalPriorityButton);
    }

    private void saveNote(){
        // creating new thread for launching method save note and adding by
        // saving note method  MutableLiveData and ViewModelProvider
        String text = editText.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(text,   priority);
        viewModel.saveNote(note);
    }


    //function gets state of radioButton and set priority -- controling state of button with f()
    //isChecked()
    private int getPriority(){
        int priority;
        if (radioButtonLow.isChecked()){
            priority = 0;
        } else if (radioButtonMedium.isChecked()) {
            priority = 1;
        }
        else {
            priority = 2;
        }
        return priority;
    }

    // creating intent for exporting it in the main activity by clicking floatingButton / gets no
    // arguments only context of this class
    public static Intent newintent(Context context){
        return new Intent(context, AddNoteActivity.class);

    }


}
