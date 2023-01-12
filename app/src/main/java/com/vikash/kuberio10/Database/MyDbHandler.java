package com.vikash.kuberio10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;


public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + "INTEGER PRIMARY KEY," + Params.KEY_FIRST_NAME
                + " TEXT, " + Params.KEY_LAST_NAME + " TEXT, " + Params.KEY_PHONE + " TEXT, " + Params.EMAIL_ID +" TEXT" +")";
        Log.d("dbharry", "Query being run is : "+ create);
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Data data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues Values = new ContentValues();
        Values.put(Params.KEY_FIRST_NAME,data.getFirstname());
        Values.put(Params.KEY_LAST_NAME,data.getLastname());
        Values.put(Params.KEY_PHONE,data.getPhoneNumber());

        db.insert(Params.TABLE_NAME,null,Values);
        Log.d("dbharry","Successfully inserted");
        db.close();

    }
    public List<Data> getAllContacts() {
        List<Data> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Generate the query to read data from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Data data = new Data();
//                data.setId(Integer.valueOf(cursor.getString(0)));
                data.setFirstname(cursor.getString(1));
                data.setLastname(cursor.getString(2));
                data.setPhoneNumber(cursor.getString(3));
                data.setEmailid(cursor.getString(4));
                dataList.add(data);

            } while (cursor.moveToNext());

        }
        return dataList;


    }
    // return number of affected rows
    public int updateContacts(Data data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_FIRST_NAME,data.getFirstname());
        values.put(Params.KEY_LAST_NAME,data.getLastname());
        values.put(Params.KEY_PHONE,data.getPhoneNumber());
        values.put(Params.EMAIL_ID,data.getEmailid());

        //lets update
        return db.update(Params.TABLE_NAME,values,Params.KEY_ID+ "=?",
                new String[]{String.valueOf(data.getId())});

    }
    public void deleteContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }
    // deleting by contact
    public void deleteContact(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME, Params.KEY_ID +"=?", new String[]{String.valueOf(data.getId())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return  cursor.getCount();
    }

    // checking  whether a mobile_number exist in Database or not
    public boolean check_number(String number){
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do {
//                Log.d("dbharry",String.valueOf(number.equals(cursor.getString(2))));
                if(number.equals(cursor.getString(3))){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }
    public ArrayList<String> User_info(String number) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        ArrayList<String> user_info = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                if (number.equals(cursor.getString(3))) {
                    String firstname = cursor.getString(1);
                    String lastname = cursor.getString(2);
                    String email_id = cursor.getString(4);
                    user_info.add(firstname);
                    user_info.add(lastname);
                    user_info.add(email_id);
                    return user_info;
                }
            } while (cursor.moveToNext());

        }
        return user_info;
    }

}