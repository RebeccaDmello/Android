package com.example.curbsidethairestaurant;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG="DatabaseHelperRebecca";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="FoodDB";

    //Table Names
    private static final String TBL_Food = "FoodInfo";

    //FLIGHT INFO TABLE COLUMN NAMES
    private static final String tblFood_Name = "Name";
    private static final String tblFood_Description = "Description";
    private static final String tblFood_Price = "Price";

    //Table Create Statements
    private static final String CREATE_Foods_TBL = "CREATE TABLE "
            + TBL_Food + " ( ID " + "INTEGER PRIMARY KEY, "
            + tblFood_Name + " TEXT, "
            + tblFood_Description + " TEXT, "
            + tblFood_Price + " TEXT )";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_Food);
        db.execSQL(CREATE_Foods_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_Food);
        onCreate(db);
    }

    public long insertBookDetails(String name, String description, String price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tblFood_Name,name);
        values.put(tblFood_Description,description);
        values.put(tblFood_Price,price);
        long id = db.insert(TBL_Food, null,values);
        return id;
    }


    public List<FoodList> getBooksByIDorName(String foodName)
    {
        List<FoodList> foodLists = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT(name), description, price FROM " +TBL_Food + " WHERE " +tblFood_Name + " = '" +foodName+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        if(c.moveToFirst())
        {
            do{
                FoodList bklst = new FoodList();
                bklst.setName(c.getString(c.getColumnIndex(tblFood_Name)));
                bklst.setDescription(c.getString(c.getColumnIndex(tblFood_Description)));
                bklst.setPrice(c.getString(c.getColumnIndex(tblFood_Price)));
                foodLists.add(bklst);
            }while(c.moveToNext());
        }
        return foodLists;
    }

    public long deleteFixedAmountById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TBL_Food, "ID =?", new String[]{Integer.toString(id)});
    }
}
