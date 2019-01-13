package com.kevin.john.myapplication;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "test.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS (ID INTEGER PRIMARY KEY AUTOINCREMENT , FIRSTNAME TEXT UNIQUE, LASTNAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS");
        onCreate(sqLiteDatabase);

    }

    public void add_students (String firstname , String lastname){
        ContentValues content = new ContentValues();
        content.put("FIRSTNAME", firstname);
        content.put("LASTNAME", lastname);
        getWritableDatabase().insertOrThrow("STUDENTS" , "", content   );
    }

    public void delete_students (String firstname){
        getWritableDatabase().delete("STUDENTS" , "FIRSTNAME='"+firstname+"'", null);
    }

    public void edit_students (String old_firstname, String new_firstname){
        getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME='"+old_firstname+"'");
    }

    public void view_students (TextView textView){
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM STUDENTS" , null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1) +" "+ cursor.getString(2)+"\n");
        }
    }

}
