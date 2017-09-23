package com.example.rasik.sqlitelogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by rasik on 19/9/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    static  String DATABASE_NAME = "UserDatabase";
    public static final String TABLE_NAME = "UserTable";
    public static final String TABLE_Column_Id = "id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_Email="email";
    public static final String Table_Column_3_Password="password";

    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+" ("+TABLE_Column_Id+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+"VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
            sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
