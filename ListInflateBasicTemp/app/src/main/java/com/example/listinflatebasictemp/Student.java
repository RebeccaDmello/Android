package com.example.listinflatebasictemp;

public class Student {
    private String stdName, stdCourse;
    private int ColorVal;

    public Student(String stdName, String stdCourse, int colorVal) {
        this.stdName = stdName;
        this.stdCourse = stdCourse;
        this.ColorVal = colorVal;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdCourse() {
        return stdCourse;
    }

    public void setStdCourse(String stdCourse) {
        this.stdCourse = stdCourse;
    }

    public int getColorVal() {
        return ColorVal;
    }

    public void setColorVal(int colorVal) {
        ColorVal = colorVal;
    }

}
