package com.example.roomdatabaseandroid.entity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int studentId;

    @NonNull
    @ColumnInfo(name = "student_name")
    private String studentName;

    @NonNull
    @ColumnInfo(name = "date_time")
    private long dateTime;


    @NonNull
    @ColumnInfo(name = "student_no")
    private int studentNo;

    public Student(@NonNull String studentName, long dateTime, int studentNo) {
        this.studentName = studentName;
        this.dateTime = dateTime;
        this.studentNo = studentNo;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @NonNull
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(@NonNull String studentName) {
        this.studentName = studentName;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getDateTimeFormatted(Context context){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(dateTime));
    }
}
