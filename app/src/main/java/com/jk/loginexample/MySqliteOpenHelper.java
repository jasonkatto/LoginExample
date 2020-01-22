package com.jk.loginexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.core.app.NavUtils;

import java.util.ArrayList;

public class MySqliteOpenHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EmployeesDatabase";
    private static final String TABLE_NAME = "employees";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAILADDRESS = "emailAddres";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_phonenumber  = "phoneNumber";

    String createTable= "CREATE TABLE " + TABLE_NAME + " (\n" +
            "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
            "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_EMAILADDRESS + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_PASSWORD + " varchar(200) NOT NULL,\n" +
            "    " + COLUMN_phonenumber + " varchar(200) NOT NULL\n" +
            ");";



    public MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

        db.execSQL(createTable);

    }

    //is to insert the employ database( the item) to the SQL database
    public boolean addEmployee(String name, String emailAddress, String phoneNumber, String  password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAILADDRESS, emailAddress);
        contentValues.put(COLUMN_phonenumber, phoneNumber);
        contentValues.put(COLUMN_PASSWORD, password);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public EmployModel getEmplyee(int ID){
        SQLiteDatabase db = getReadableDatabase();
        String  sql= "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
       Cursor cursor= db.rawQuery(sql, new String[] {String.valueOf(ID)});
        EmployModel employModel= new EmployModel();
        if (cursor.moveToFirst()) {

            employModel.setID(cursor.getInt(0) + "");
            employModel.setName(cursor.getString(1) + "");
            employModel.setPhoneNumber(cursor.getString(4) + "");
            employModel.setEmailAddress(cursor.getString(2) + "");
            employModel.setImage(android.R.drawable.star_on);
        }
        return employModel;
    }

    public ArrayList<EmployModel> getEmployess(){
        ArrayList <EmployModel> arrayList= new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        if (cursor.moveToFirst()){
            do {
                EmployModel employModel= new EmployModel();
                employModel.setID(cursor.getInt(0)+"");
                employModel.setName(cursor.getString(1)+"");
                employModel.setPhoneNumber(cursor.getString(4)+"");
                employModel.setEmailAddress(cursor.getString(2)+"");
                employModel.setImage(android.R.drawable.star_on);
                arrayList.add(employModel);

            }while (cursor.moveToNext());
        }
        cursor.close();



        return arrayList;
    }

    public void deleteEmployee(int ID){
        SQLiteDatabase db = getWritableDatabase();
        String  sql= "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        db.execSQL(sql, new Integer[] { ID});
    }

    public void updateEmploye(int ID, String  name, String phoneNumber, String emailAddress){
        SQLiteDatabase db =getWritableDatabase();
        String sql= "UPDATE " + TABLE_NAME + " SET name=?,phoneNumber =?, emailAddres=? WHERE id=?;";
        db.execSQL(sql, new String [] { name,phoneNumber, emailAddress, String.valueOf(ID)});

    }

}
