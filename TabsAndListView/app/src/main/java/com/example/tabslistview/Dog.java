package com.example.tabslistview;

import java.time.LocalDate;

public class Dog {
    private int id;
    private String dogBreed;
    private String dogName;
    private int dogpicDrawable;
    private LocalDate dob;

    public Dog(int id, String dogBreed, String dogName, int dogpicDrawable, LocalDate dob) {
        this.id = id;
        this.dogBreed = dogBreed;
        this.dogName = dogName;
        this.dogpicDrawable = dogpicDrawable;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogpicDrawable() {
        return dogpicDrawable;
    }

    public void setDogpicDrawable(int dogpicDrawable) {
        this.dogpicDrawable = dogpicDrawable;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
