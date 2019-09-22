package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class TermEdit extends AppCompatActivity {

    private int position;
    private RecyclerView recyclerView;
    private ViewAdapter rvAdapater;
    private RecyclerView.LayoutManager rvLayoutManager;

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

        recyclerView = findViewById(R.id.recyclerCourses);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapater = new ViewAdapter(new String[] {});
        recyclerView.setAdapter(rvAdapater);


        Intent i = getIntent();
        position = i.getIntExtra("position", -1);

        if (position != -1) {
            Term t = MainActivity.Lists.TermList.get(position);
            EditText view;
            SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

            view = findViewById(R.id.editTitle);
            view.setText(t.Title());
            view = findViewById(R.id.editStartDate);
            view.setText(formatDate.format(t.StartDate()));
            view = findViewById(R.id.editEndDate);
            view.setText(formatDate.format(t.EndDate()));

            rvAdapater.updateList(t.getCourseTitles());
            rvAdapater.notifyDataSetChanged();
        }

        Spinner courses = findViewById(R.id.spinnerCourse);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.Lists.GetCourseList());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courses.setAdapter(spinnerAdapter);
    }

    public void onSaveButtonClick(View v) {
        Term newTerm;

        if (position == -1) {
            newTerm = new Term();
        }
        else {
            newTerm = MainActivity.Lists.TermList.get(position);
        }
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

        setResult(RESULT_OK, getIntent());

        if (position == -1 && !MainActivity.Lists.TermList.contains(newTerm)) {
            MainActivity.Lists.TermList.add(newTerm);
        }
        else if (position == -1 && MainActivity.Lists.TermList.contains(newTerm)) {
            setResult(RESULT_CANCELED, getIntent());
        }

        finish();
    }

    public void onCancelButtonClick(View v) {
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

    public void onAddCourseButtonClick(View v) {
        Spinner courses = findViewById(R.id.spinnerCourse);
        String course = courses.getSelectedItem().toString();

        if (course != null && !course.equals("")) {
            List<String> courseList = new ArrayList<>(Arrays.asList(rvAdapater.getList()));

            if (!courseList.contains(course)) {
                courseList.add(course);

                rvAdapater.updateList(courseList.toArray(new String[courseList.size()]));
                rvAdapater.notifyDataSetChanged();
            }
        }
    }

    public void onRemoveCourseButtonClick(View v) {
        rvAdapater.removeSelectedItem();
    }

    public void onNewCourseButtonClick(View v) {
        Intent i = new Intent(getApplicationContext(), course_edit.class);
        i.putExtra("position", -1);
        startActivityForResult(i,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Spinner courses = findViewById(R.id.spinnerCourse);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.Lists.GetCourseList());
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                courses.setAdapter(spinnerAdapter);
            }
        }
    }

}
