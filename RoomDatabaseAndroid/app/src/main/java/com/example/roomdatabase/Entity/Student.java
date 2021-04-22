package com.example.roomdatabase.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int studentId;
    @NonNull
    @ColumnInfo(name = "student_name")
    private String studentName;
    @NonNull
    @ColumnInfo(name = "student_dept")
    private String studentDept;
    @NonNull
    @ColumnInfo(name = "student_ID")
    private int SID;
    public Student(@NonNull String studentName, @NonNull String studentDept, int SID) {
        this.studentName = studentName;
        this.studentDept = studentDept;
        this.SID = SID;
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
    @NonNull
    public String getStudentDept() {
        return studentDept;
    }
    public void setStudentDept(@NonNull String studentDept) {
        this.studentDept = studentDept;
    }
    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

}
