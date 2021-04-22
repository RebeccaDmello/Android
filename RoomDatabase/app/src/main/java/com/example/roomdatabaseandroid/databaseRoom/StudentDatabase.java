package com.example.roomdatabaseandroid.databaseRoom;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabaseandroid.DataACCOBJ.StudentDAO;
import com.example.roomdatabaseandroid.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

    private static volatile StudentDatabase INSTANCE;

    static StudentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StudentDatabase.class, "product_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
