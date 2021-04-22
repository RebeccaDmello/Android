package com.example.roomdatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.DAO.StudentDAO;
import com.example.roomdatabase.Entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract  class StudentDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
    private static volatile StudentDatabase INSTANCE;
    static StudentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class, "student_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
