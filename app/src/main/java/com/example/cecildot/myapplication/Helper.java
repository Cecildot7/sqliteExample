package com.example.cecildot.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class Helper extends SQLiteOpenHelper {
    public static String databaseName ="student.db";
    public  static  String tableName = "StudentInfo";
    public  static  String id = "id";
    public  static  String name = "name";
    public  static  String course = "course";
    SQLiteDatabase database;
    public Helper(Context context) {
        super(context, databaseName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //resposible to create table.
        String sql = "create table "+tableName+"("+id+" integer,"+name+" text,"+course+" text)";
                db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if EXISTS "+tableName;
        db.execSQL(sql);
        onCreate(db);

    }

    public String saveData(String userid, String username, String usercourse) {
        String msg= null;
        ContentValues cv = new ContentValues();
        cv.put(id,userid);
        cv.put(name,username);
        cv.put(course,usercourse);
        database=getWritableDatabase();
        try{
            long result = database.insert(tableName,null,cv);
            //if successfully data saved it will return a long type value
            if(result>0){
                msg="data saved Successfully";
            }
        }catch(Exception ex){
            msg=ex.getMessage();
        }

        return  msg;
    }

    public Cursor fetchData() {
        Cursor cursor =null;
        database=getReadableDatabase();
        cursor=database.query(tableName,new String[]{id,name,course},null,null,
                null,null,null);


        return cursor;

    }

    public Cursor searchitem(String uid) {
        Cursor cursor =null;
        database=getReadableDatabase();
        cursor=database.query(tableName,new String[]{id,name,course},id +"=?",new String[]{uid},null,null,null,null);
    return  cursor;
    }
}
