package com.example.thisday.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thisday.R;
import com.example.thisday.database.NotesDatabase;
import com.example.thisday.entities.Note;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewNote extends AppCompatActivity {

    private EditText noteInputContent;
    private TextView month_and_date;
    private TextView day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_note);

        noteInputContent = findViewById(R.id.noteInputContent);
        month_and_date = findViewById(R.id.month_and_date);
        day = findViewById(R.id.day);

        day.setText(
                new SimpleDateFormat("EEEE", Locale.getDefault()).format(new Date())
        );

        month_and_date.setText(
                new SimpleDateFormat("MMM dd", Locale.getDefault()).format(new Date())
        );

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        saveNote();
//    }

    private void saveNote() {
            if(!(noteInputContent.getText().toString().isEmpty())) {
                final Note note = new Note();
                note.setMonthDate(month_and_date.getText().toString());
                note.setDay(day.getText().toString());
                note.setNoteContent(noteInputContent.getText().toString());

                @SuppressLint("StaticFieldLeak")
                class SaveNoteTask extends AsyncTask<Void, Void, Void> {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        NotesDatabase.getDatabase(getApplicationContext()).noteDAO().insertNote(note);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void unused) {
                        super.onPostExecute(unused);
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }

                new SaveNoteTask().execute();
            }
        }
    }