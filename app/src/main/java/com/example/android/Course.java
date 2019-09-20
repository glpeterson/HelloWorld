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
    private Dictionary<String, String> notes;

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

    public int compareTo(Course comp) {
        return comp.title.compareTo(title);
    }
}
