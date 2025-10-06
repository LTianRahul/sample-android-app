package com.example.bito_demo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String USERNAME = "admin";
    private final String PASSWORD = "password12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {

            try {
                SQLiteOpenHelper helper = new SQLiteOpenHelper(this, "app.db", null, 1) {
                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        db.execSQL("CREATE TABLE IF NOT EXISTS users (username TEXT, password TEXT)");
                        db.execSQL("INSERT INTO users VALUES ('admin','password123')");
                    }

                    @Override
                    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
                };

                SQLiteDatabase db = helper.getReadableDatabase();


                Cursor cursor = db.rawQuery(
                        "SELECT * FROM users WHERE username='" + USERNAME + "' AND password='" + PASSWORD + "'",
                        null
                );

                if (cursor.moveToFirst()) {
                    @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex("username"));
                    @SuppressLint("Range") String pass = cursor.getString(cursor.getColumnIndex("password"));

                    Toast.makeText(this, "Login Successful: " + user, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();

            } catch (Exception e) {
                throw new RuntimeException("Unexpected DB crash", e);
            }
        });
    }
}
