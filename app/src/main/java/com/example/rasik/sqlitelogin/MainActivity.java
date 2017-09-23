package com.example.rasik.sqlitelogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin,buttonRegister;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "Not_found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        sqLiteHelper = new SQLiteHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFunction();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


    }

    public void LoginFunction(){

        if(!(TextUtils.isEmpty(editEmail.getText().toString()) || TextUtils.isEmpty(editPassword.getText().toString())) ) {

            sqLiteDatabase = sqLiteHelper.getWritableDatabase();

            cursor = sqLiteDatabase.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{editEmail.getText().toString()}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();


                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));
                    cursor.close();
                }
            }


            CheckFinalResult();

        }
        else {


            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(editPassword.getText().toString()))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

            intent.putExtra("UserEmail", editEmail.getText().toString());

            startActivity(intent);


        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
}
