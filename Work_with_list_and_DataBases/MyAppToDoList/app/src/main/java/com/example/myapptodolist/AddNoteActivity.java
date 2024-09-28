package com.example.myapptodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNoteActivity extends AppCompatActivity {



    //creating vars

    private EditText editText;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button btnMakeNote;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
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
        String text = editText.getText().toString().trim();
        int priority = getPriority();
    }


    //function gets state of radioButton and set priority -- controling state of button with f()
    // isChecked()
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
