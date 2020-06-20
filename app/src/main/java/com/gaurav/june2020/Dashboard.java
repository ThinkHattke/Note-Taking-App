package com.gaurav.june2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gaurav.june2020.DB.Note;
import com.gaurav.june2020.DB.NoteDB;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText noteBox;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recycler_view);
        noteBox = findViewById(R.id.note_box);
        submit  = findViewById(R.id.submit);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dashboard.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        final NoteDB db = NoteDB.getInstance(this);

        final MyAdapter adapter = new MyAdapter(db.noteDao().getAllNotes(), Dashboard.this);
        recyclerView.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String note = noteBox.getText().toString().trim();
                if(!note.isEmpty()) {
                    db.noteDao().insertNote(new Note(0,note));
                    adapter.notes = db.noteDao().getAllNotes();
                    adapter.notifyDataSetChanged();
                }

            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                db.noteDao().deleteNote(adapter.notes.get(position));
                adapter.notes = db.noteDao().getAllNotes();
                adapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
    
}