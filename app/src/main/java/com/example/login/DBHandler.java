package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "loginUser.db";
    public static final String TABLE_NAME = "USER";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, emailId TEXT, password TEXT, phone TEXT, gender TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public Boolean Insert(String firstName, String lastName, String emailId, String phone, String password,  String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("emailId", emailId);
        contentValues.put("phone", phone);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        long result = db.insert("user", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



       /* public Cursor getData () {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            return cursor;
        }*/

    public Boolean checkEmail(String emailId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE emailId =?", new String[]{emailId});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
  }

    public boolean checkLoginUser(String emailId, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where emailId = ? and password = ?", new String[] {emailId,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<ModelClass> getUserData(String gender){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelClass> modelClassArrayList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from user where gender like '" + gender + "'", null);
        if(cursor.getCount() != 0){
            cursor.moveToPosition(-1);

            while (cursor.moveToNext()){
                String user_firstName = cursor.getString(0);
                String user_lastName = cursor.getString(1);
                String user_email = cursor.getString(2);
                String user_phone = cursor.getString(3);

                modelClassArrayList.add(new ModelClass(user_firstName, user_lastName, user_email, user_phone));
            }
            return modelClassArrayList;
        }
        else {
            return null;
        }
    }

}






