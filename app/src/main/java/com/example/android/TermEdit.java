package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import java.util.*;

public class TermEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onSaveButtonClick(View v) {
        Term newTerm = new Term();
        Date date = new Date();

        EditText view = findViewById(R.id.editTitle);
        newTerm.Title(view.getText().toString());
        view = findViewById(R.id.editStartDate);
        try {
            date = new Date(view.getText().toString());
        }
        catch (Exception e) {
            date = new Date();
        }
        newTerm.StartDate(date);
        view = findViewById(R.id.editEndDate);
        try {
            date = new Date(view.getText().toString());
        }
        catch (Exception e) {
            date = new Date();
        }
        newTerm.EndDate(date);

        MainActivity.Lists.TermList.add(newTerm);

        Intent i = new Intent(getApplicationContext(), term_view.class);
        startActivity(i);
    }

}
