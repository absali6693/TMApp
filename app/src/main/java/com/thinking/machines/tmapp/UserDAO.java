package com.thinking.machines.tmapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by Abbas on 4/27/2015.
 */
public class UserDAO {
     private Context context;
    private DatabaseHelper databaseHelper;
    UserDAO(Context context)
    {
        this.context=context;
        this.databaseHelper=new DatabaseHelper(context);
    }
    public void add(String username,String mobileNumber,String userId,String startupStatus)
    {
        SQLiteDatabase db;
        db=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME,username);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER,mobileNumber);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_USER_ID,userId);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS,startupStatus);
        db.insert(DatabaseHelper.USER_TABLE_NAME,null,contentValues);
        db.close();
    }

    public void update(int id,String username,String mobileNumber,String userId,String startupStatus)
    {
        SQLiteDatabase db;
        db=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME,username);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER,mobileNumber);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_ID,userId);
        contentValues.put(DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS,startupStatus);
        db.update(DatabaseHelper.USER_TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void remove(int id){
        SQLiteDatabase db;
        db=databaseHelper.getWritableDatabase();
        db.delete(DatabaseHelper.USER_TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public User getById(int id)
    {
        SQLiteDatabase db;
        db=databaseHelper.getReadableDatabase();
        String columns[]=new String[5];
        columns[0]=DatabaseHelper.USER_TABLE_FIELD_NAME_ID;
        columns[1]=DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME;
        columns[2]=DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER;
        columns[3]=DatabaseHelper.USER_TABLE_FIELD_NAME_USER_ID;
        columns[4]=DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS;
        Cursor cursor=db.query(DatabaseHelper.USER_TABLE_NAME,columns,"id=?",new String[] {String.valueOf(id)},null,null,null,null);
        User user=null;
        if(cursor.moveToFirst())
        {
            user=new User();
            user.setId(id);
            user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME)).trim());
            user.setMobileNumber(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER)).trim());
            user.setUserId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_USER_ID)).trim());
            user.setStartupStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS)).trim());
        }
        cursor.close();
        db.close();
        return user;
    }

    public ArrayList<User> getAll()
    {
        ArrayList users=new ArrayList();
        SQLiteDatabase db;
        db=databaseHelper.getReadableDatabase();
        String columns[]=new String[5];
        columns[0]=DatabaseHelper.USER_TABLE_FIELD_NAME_ID;
        columns[1]=DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME;
        columns[2]=DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER;
        columns[3]=DatabaseHelper.USER_TABLE_FIELD_NAME_USER_ID;
        columns[4]=DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS;
        Cursor cursor=db.query(DatabaseHelper.USER_TABLE_NAME,columns,null,null,null,null,null);
        User user=null;
        if(cursor.moveToFirst())
        {
            do{
                user=new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_USERNAME)).trim());
                user.setMobileNumber(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_MOBILE_NUMBER)).trim());
                user.setUserId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_USER_ID)).trim());
                user.setStartupStatus(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_FIELD_NAME_STARTUP_STATUS)).trim());
                users.add(user);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }
public int getCount()
{
    SQLiteDatabase db;
    db=databaseHelper.getReadableDatabase();
    Cursor cursor=db.rawQuery("select count(*) as cnt from"+DatabaseHelper.USER_TABLE_NAME,null);
    cursor.moveToNext();
    int count=cursor.getInt(0);
    cursor.close();
    db.close();
    return count;
}
}
