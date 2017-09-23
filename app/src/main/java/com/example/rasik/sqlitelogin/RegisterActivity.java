package com.example.rasik.sqlitelogin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rasik on 19/9/17.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText emailRegister,passwordRegister,nameRegister;
    Button btnRegister;
    SQLiteDatabase sqLiteDatabase;
    String SQLiteDatabaseQueryHolder;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
//    String F_Result="Not Found";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button)findViewById(R.id.btnRegister);

        emailRegister = (EditText)findViewById(R.id.emailRegister);
        passwordRegister = (EditText)findViewById(R.id.passwordRegister);
        nameRegister = (EditText)findViewById(R.id.nameRegister);

        sqLiteHelper = new SQLiteHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseBuild();
                SQLiteTableBuild();
                CheckEmail();

            }
        });
    }
    public void SQLiteDatabaseBuild(){
        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE,null);
    }

    public  void SQLiteTableBuild(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.TABLE_Column_Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");
    }

    public void InsertDataIntoSQLiteDatabase(){

        System.out.println(TextUtils.isEmpty(nameRegister.getText().toString()));

        if(!(TextUtils.isEmpty(nameRegister.getText().toString().trim()) || TextUtils.isEmpty(emailRegister.getText().toString().trim()) || TextUtils.isEmpty(passwordRegister.getText().toString().trim())) )
        {

            SQLiteDatabaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password) VALUES('"+nameRegister.getText().toString()+"', '"+emailRegister.getText().toString()+"', '"+passwordRegister.getText().toString()+"');";

            sqLiteDatabase.execSQL(SQLiteDatabaseQueryHolder);

            sqLiteDatabase.close();


            Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }

        else {


            Toast.makeText(RegisterActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void CheckEmail(){
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{emailRegister.getText().toString()}, null, null, null);

        if (cursor.getCount() != 0 ){
//            F_Result = "Email Found";
            cursor.close();
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
        } else {
            InsertDataIntoSQLiteDatabase();
        }

        /*while (cursor.moveToNext()){
            if(cursor.isFirst()){
                cursor.moveToFirst();
            }
        }*/
//        checkFinal();
    }
}
