package com.example.android;

import java.util.*;

public class Term implements Comparable<Term> {
    private String title;
    private Date startDate;
    private Date endDate;
    private ArrayList<Course> courseList;

    public Term() {
        courseList = new ArrayList<>();
    }

    public void Title(String title) {
        this.title = title;
    }

    public String Title () {
        return title;
    }

    public void StartDate (Date start) {
        this.startDate = start;
    }

    public Date StartDate () {
        return startDate;
    }

    public void EndDate (Date end) {
        this.endDate = end;
    }

    public Date EndDate () {
        return endDate;
    }

    public boolean AddCourse(Course newCourse) {
        if (!courseList.contains(newCourse)) {
            courseList.add((newCourse));

            return true;
        }
        else {
            return false;
        }
    }

    public boolean RemoveCourse(Course removeCourse) {
        if (courseList.contains(removeCourse)) {
            return courseList.remove(removeCourse);
        }
        else{
            return false;
        }
    }

    public ArrayList<Course> getCourseList() {
        return (ArrayList<Course>)courseList.clone();
    }

    public String[] getCourseTitles() {
        ArrayList<String> courses = new ArrayList<>();

        for (int i = 0; i < courseList.size(); i++) {
            courses.add(courseList.get(i).Title());
        }

        if (courses.isEmpty()) {
            courses.add("");
        }

        return courses.toArray(new String[courses.size()]);
    }

    @Override
    public int compareTo(Term term) {
        return this.title.compareTo(term.title);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Term t = (Term) o;

        return t.Title().equals(this.Title());
    }
}
