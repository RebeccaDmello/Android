package com.example.recyclerfileoperation;

import java.time.LocalDate;

public class Student {
    int imgId;
    String stdName, stdId;
    LocalDate stdDOB;


    public Student(String stdName, String stdId, LocalDate stdDOB, int imgId) {
        this.stdName = stdName;
        this.stdId = stdId;
        this.stdDOB = stdDOB;
        this.imgId = imgId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public LocalDate getStdDOB() {
        return stdDOB;
    }

    public void setStdDOB(LocalDate stdDOB) {
        this.stdDOB = stdDOB;
    }
}
