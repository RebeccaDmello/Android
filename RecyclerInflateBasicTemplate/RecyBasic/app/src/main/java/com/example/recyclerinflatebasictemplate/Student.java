package com.example.recyclerinflatebasictemplate;

public class Student {
    String stdName;
    int imgId;

    public Student(String stdName, int imgId) {
        this.stdName = stdName;
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
}
