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

public class course_view extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewAdapter rvAdapater;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.cvRecyclerCourses);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapater = new ViewAdapter(MainActivity.Lists.GetCourseList());
        recyclerView.setAdapter(rvAdapater);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onNewButtonClick(View v) {
        Intent i = new Intent(getApplicationContext(), course_edit.class);
        i.putExtra("position", -1);
        startActivityForResult(i,0);
    }

    public void onEditButtonClick(View v) {
        int position = rvAdapater.getSelectedItem();

        if (rvAdapater.getItemCount() > 0) {
            Intent i = new Intent(getApplicationContext(), course_edit.class);
            i.putExtra("position", position);
            startActivityForResult(i, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                rvAdapater.updateList(MainActivity.Lists.GetCourseList());
                rvAdapater.notifyDataSetChanged();
            }
        }
    }

}
