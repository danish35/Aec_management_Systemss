package com.example.aecmanagementsystem;

public class student {
    public String full_Name, emails, branch, semester, roll, password;

    public student() {

    }

    public student(String fullName, String email, String branches, String semesters, String rolls, String pass) {
        this.full_Name = fullName;
        emails = email;
        branch = branches;
        semester = semesters;
        roll = rolls;
        password = pass;


    }

    public String getFull_Name() {
        return full_Name;
    }

    public String getEmails() {
        return emails;
    }

    public String getBranch() {
        return branch;
    }

    public String getSemester() {
        return semester;
    }

    public String getRoll() {
        return roll;
    }

    public String getPassword() {
        return password;
    }
}
