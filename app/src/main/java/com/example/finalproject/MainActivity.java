package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Log In and Sign Up buttons
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);

        // Set click listeners for Log In and Sign Up
        loginButton.setOnClickListener(v -> {
            // Open Log In Activity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        signupButton.setOnClickListener(v -> {
            // Open Sign Up Activity
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });
    }
}
