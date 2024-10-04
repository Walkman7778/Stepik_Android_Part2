package com.example.myapptodolist;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {


    private ArrayList<Note> notes = new ArrayList<>();
    private OnNoteClickListener onNoteClickListener;

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public void setNotes(ArrayList<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }





    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.note_item,
                parent,
                false
        );


        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textViewNote.setText(note.getText());

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
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), colorResId);

        viewHolder.textViewNote.setBackgroundColor(color);

        viewHolder.textViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNoteClickListener != null) {
                    onNoteClickListener.OnNoteClick(note);
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    class NotesViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNote;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNote = itemView.findViewById(R.id.textViewNote);

        }
    }
    // realization of interface which transmit object note to the click listener in the method
    // OnBindViewHolder
    interface OnNoteClickListener {
        void OnNoteClick(Note note);


    }

}
