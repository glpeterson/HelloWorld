package com.example.android;

import java.util.*;

public class Assessment implements Comparable<Assessment> {
    private String title;
    private String type;
    private Date dueDate;

    public String Title() {
        return title;
    }

    public void Title(String title) {
        this.title = title;
    }

    public String Type() {
        return type;
    }

    public void Type(String type) {
        this.type = type;
    }

    public Date DueDate() {
        return dueDate;
    }

    public void DueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int compareTo(Assessment assessment) {
        return this.title.compareTo(assessment.title);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Assessment a = (Assessment) o;

        return a.Title().equals(this.Title());
    }
}
