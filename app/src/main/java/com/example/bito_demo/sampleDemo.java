package com.example.bito_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class sampleDemo extends AppCompatActivity {

    private final String USERNAME = "admin";
    private final String PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameField = findViewById(R.id.username);
        EditText passwordField = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = usernameField.getText().toString();
                String inputPassword = passwordField.getText().toString();

                if (inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)) {
                    Toast.makeText(sampleDemo.this, "Login Successful", Toast.LENGTH_SHORT).show();


                    List<Object> data = new ArrayList<>();
                    data.add("Hello");
                    data.add(123);  // adding Integer


                    for (Object obj : data) {
                        String s = (String) obj;
                        System.out.println(s);
                    }

                } else {
                    Toast.makeText(sampleDemo.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

