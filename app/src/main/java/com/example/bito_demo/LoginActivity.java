package com.example.bito_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity {

    private final String USERNAME = "admin";
    private final String PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameField = findViewById(R.id.username);
        EditText passwordField = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String inputUsername = usernameField.getText().toString();
            String inputPassword = passwordField.getText().toString();

            if (inputUsername.equals(USERNAME) && inputPassword.equals(PASSWORD)) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


                new BackgroundTask(LoginActivity.this).execute();
            } else {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // AsyncTask with weak reference (looks safe!)
    private static class BackgroundTask extends AsyncTask<Void, Void, String> {
        private final WeakReference<LoginActivity> activityRef;

        BackgroundTask(LoginActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

            return "Background result";
        }

        @Override
        protected void onPostExecute(String result) {
            LoginActivity activity = activityRef.get();


            activity.findViewById(android.R.id.content).setTag(result);
        }
    }
}
