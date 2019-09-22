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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class course_edit extends AppCompatActivity {

    private int position;
    private RecyclerView recyclerViewAssessment;
    private ViewAdapter rvAdapaterAssessment;
    private RecyclerView.LayoutManager rvLayoutManagerAssessment;
    private RecyclerView recyclerViewNotes;
    private ViewAdapter rvAdapaterNotes;
    private RecyclerView.LayoutManager rvLayoutManagerNotes;
    private final String[] COURSE_STATUS = {"plan to take", "in progress", "completed", "dropped"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
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

        Intent i = getIntent();
        position = i.getIntExtra("position", -1);

        Spinner statuses = findViewById(R.id.ceSpinnerStatus);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, COURSE_STATUS);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statuses.setAdapter(spinnerAdapter);

        if (position != -1) {
            Course course = MainActivity.Lists.CourseList.get(position);
            EditText view;
            SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

            view = findViewById(R.id.ceEditTitle);
            view.setText(course.Title());
            view = findViewById(R.id.ceEditStartDate);
            view.setText(formatDate.format(course.StartDate()));
            view = findViewById(R.id.ceEditEndDate);
            view.setText(formatDate.format(course.EndDate()));
            view = findViewById(R.id.ceEditMentorName);
            view.setText(course.MentorName());
            view = findViewById(R.id.ceEditMentorPhone);
            view.setText(course.MentorPhone());
            view = findViewById(R.id.ceEditMentorEmail);
            view.setText(course.MentorEmail());

            statuses.setSelection(getStatusPosition(course.Status()));

            recyclerViewAssessment = findViewById(R.id.ceAssessmentList);
            rvLayoutManagerAssessment = new LinearLayoutManager(this);
            recyclerViewAssessment.setLayoutManager(rvLayoutManagerAssessment);

            rvAdapaterAssessment = new ViewAdapter(course.getAssessmentTitles());
            recyclerViewAssessment.setAdapter(rvAdapaterAssessment);

            recyclerViewNotes = findViewById(R.id.ceNoteList);
            rvLayoutManagerNotes = new LinearLayoutManager(this);
            recyclerViewAssessment.setLayoutManager(rvLayoutManagerNotes);

            rvAdapaterNotes = new ViewAdapter(course.getNoteTitles());
            recyclerViewNotes.setAdapter(rvAdapaterNotes);
        }
    }

    public void onSaveButtonClick(View v) {
        Course newCourse;

        if (position == -1) {
            newCourse = new Course();
        }
        else {
            newCourse = MainActivity.Lists.CourseList.get(position);
        }
        Date date = new Date();

        EditText view = findViewById(R.id.ceEditTitle);
        newCourse.Title(view.getText().toString());
        view = findViewById(R.id.ceEditStartDate);
        try {
            date = new Date(view.getText().toString());
        }
        catch (Exception e) {
            date = new Date();
        }
        newCourse.StartDate(date);
        view = findViewById(R.id.ceEditEndDate);
        try {
            date = new Date(view.getText().toString());
        }
        catch (Exception e) {
            date = new Date();
        }
        newCourse.EndDate(date);

        view = findViewById(R.id.ceEditMentorName);
        newCourse.MentorName(view.getText().toString());
        view = findViewById(R.id.ceEditMentorPhone);
        newCourse.MentorPhone(view.getText().toString());
        view = findViewById(R.id.ceEditMentorEmail);
        newCourse.MentorEmail(view.getText().toString());

        Spinner statuses = findViewById(R.id.ceSpinnerStatus);
        newCourse.Status(statuses.getSelectedItem().toString());

        setResult(RESULT_OK, getIntent());

        if (position == -1 && !MainActivity.Lists.CourseList.contains(newCourse)) {
            MainActivity.Lists.CourseList.add(newCourse);
        }
        else if (position == -1 && MainActivity.Lists.CourseList.contains(newCourse)) {
            setResult(RESULT_CANCELED, getIntent());
        }

        finish();
    }

    public void onCancelButtonClick(View v) {
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

    private int getStatusPosition(String status) {
        int position = -1;

        for (int i = 0; i < COURSE_STATUS.length; i++) {
            if (COURSE_STATUS[i].equals(status)) {
                position = i;
            }
        }

        return position;
    }
}
