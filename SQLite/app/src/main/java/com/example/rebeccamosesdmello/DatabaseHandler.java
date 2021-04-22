package com.example.rebeccamosesdmello;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHandler {

    private static SQLiteDatabase db;
    private static Cursor cursor;
    //primary key of database
    private static int count = 0;

    //Read database schema from raw resources and create the database
    public static void getAllData(Context context) {
        db = context.openOrCreateDatabase("DataBase", MODE_PRIVATE, null);
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.database));
        String qry = "";
        while (scan.hasNextLine()) {
            qry = qry + scan.nextLine() + "\n";
            if (qry.trim().endsWith(";")) {
                db.execSQL(qry);
                qry = "";
            }
        }
        count = (int) DatabaseUtils.queryNumEntries(db, "students");
    }

    //read everything there is in the database
    public static Cursor readData() {
        String qryAll = "SELECT * FROM students";
        cursor = db.rawQuery(qryAll, null);
        return cursor;
    }

    // add a new student's record to the database
    public static void addData(String name, String stdId) {
        count++;
        String qryIns = "INSERT INTO students VALUES (" + count + ",'" + name + "','" + stdId + "');";
        db.execSQL(qryIns);
    }

    // Search a student's record by name and delete it
    public static int deleteByName(String name) {
        String table = "students";
        String whereClause = "name=?";
        String[] whereArgs = new String[]{name};
        int deleted = db.delete(table, whereClause, whereArgs);
        return deleted;
    }

    // Search a student's record by name and updateit
    public static void updateUsingName(String oldName, String newName, String newMobile){
        String sql = "UPDATE students SET name = '"+newName+"', stdId = '"+newMobile+"' WHERE name = '"+oldName+"';";
        db.execSQL(sql);
    }
}
