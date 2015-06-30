package com.thinking.machines.tmapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abbas on 4/27/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private Context context;
    private final static String DATABASE_NAME="tmapp_db";
    private final static int DATABASE_VERSION=1;
    private final static String CREATE_TABLE_USER="create table user_tbl (id integer primary key autoincrement,username char(20),mobile_number char(15),user_id char(50),startup_status char(1))";
    private final static String DROP_TABLE_USER="drop table if exists user_tbl";
    public final static String USER_TABLE_NAME="user_tbl";
    public final static String USER_TABLE_FIELD_NAME_ID="id";
    public final static String USER_TABLE_FIELD_NAME_USERNAME="username";
    public final static String USER_TABLE_FIELD_NAME_MOBILE_NUMBER="mobile_number";
    public final static String USER_TABLE_FIELD_NAME_USER_ID="user_id";
    public final static String USER_TABLE_FIELD_NAME_STARTUP_STATUS="startup_status";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        onCreate(db);
    }

}
