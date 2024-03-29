package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //final Button button = findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTermsButtonClick(View v) {
        Intent i = new Intent(getApplicationContext(), term_view.class);
        startActivity(i);
    }

    public void onCoursesButtonClick(View v) {
        Intent i = new Intent(getApplicationContext(), course_view.class);
        startActivity(i);
    }

    public static class Lists {
        public static ArrayList<Term> TermList = new ArrayList<Term>();
        public static ArrayList<Course> CourseList = new ArrayList<Course>();

        public static String[] GetTermList() {
            ArrayList<String> terms = new ArrayList<>();

            for (int i = 0; i < TermList.size(); i++) {
                terms.add(TermList.get(i).Title());
            }

            if (terms.isEmpty()) {
                terms.add("");
            }

            return terms.toArray(new String[terms.size()]);
        }

        public static String[] GetCourseList() {
            ArrayList<String> courses = new ArrayList<>();

            for (int i = 0; i < CourseList.size(); i++) {
                courses.add(CourseList.get(i).Title());
            }

            if (courses.isEmpty()) {
                courses.add("");
            }

            return courses.toArray(new String[courses.size()]);
        }

        public static Course getCourse(String title) {
            Course course = null;
            Course tempCourse;

            for (int i = 0; i < CourseList.size(); i++) {
                tempCourse = CourseList.get(i);
                if (tempCourse.Title().equals(title)) {
                    course = tempCourse;
                }
            }

            return course;
        }
    }
}
