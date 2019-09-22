package com.example.android;

import java.util.*;

public class Course implements Comparable<Course> {
    private String title;
    private Date startDate;
    private Date endDate;
    private String status;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private ArrayList<Assessment> assessmentList;
    private Map<String, String> notes;

    public Course() {
        assessmentList = new ArrayList<>();
        notes = new HashMap<>();
    }

    public String Title() {
        return title;
    }

    public void Title(String title) {
        this.title = title;
    }

    public Date StartDate() {
        return startDate;
    }

    public void StartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date EndDate() {
        return endDate;
    }

    public void EndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String Status() {
        return status;
    }

    public void Status(String status) {
        this.status = status;
    }

    public String MentorName() {
        return mentorName;
    }

    public void MentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String MentorPhone() {
        return mentorPhone;
    }

    public void MentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public String MentorEmail() {
        return mentorEmail;
    }

    public void MentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public String[] getAssessmentTitles() {
        ArrayList<String> assessments = new ArrayList<>();

        for (int i = 0; i < assessmentList.size(); i++) {
            assessments.add(assessmentList.get(i).Title());
        }

        if (assessments.isEmpty()) {
            assessments.add("");
        }

        return assessments.toArray(new String[assessments.size()]);
    }

    public String[] getNoteTitles() {
        return notes.keySet().toArray(new String[notes.keySet().size()]);
    }

    @Override
    public int compareTo(Course comp) {
        return comp.title.compareTo(title);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Course c = (Course) o;

        return c.Title().equals(this.Title());
    }
}
