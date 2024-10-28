package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameInput, dobInput, emailInput, confirmEmailInput, passwordInput, confirmPasswordInput;
    private Button signUpButton, loginButton;
    private ImageButton goBackButton;
    private TextView alreadyAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Make sure the layout file is named correctly

        // Initialize all views
        usernameInput = findViewById(R.id.usernameInput);
        dobInput = findViewById(R.id.dobInput);
        emailInput = findViewById(R.id.emailInput);
        confirmEmailInput = findViewById(R.id.confirmEmailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        goBackButton = findViewById(R.id.goBackButton);
        alreadyAccountText = findViewById(R.id.alreadyAccountText);

        // Go back button functionality
        goBackButton.setOnClickListener(view -> onBackPressed());

        // Log In button functionality
        loginButton.setOnClickListener(view -> {
            // Navigate to Log In page
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Sign Up button functionality
        signUpButton.setOnClickListener(view -> {
            // Get input from fields
            String username = usernameInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String confirmEmail = confirmEmailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Validate inputs
            if (validateInputs(username, dob, email, confirmEmail, password, confirmPassword)) {
                // Proceed with sign-up process
                Toast.makeText(SignupActivity.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();

                // Optionally, redirect to another activity
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method to validate all inputs
    private boolean validateInputs(String username, String dob, String email, String confirmEmail, String password, String confirmPassword) {
        if (TextUtils.isEmpty(username)) {
            usernameInput.setError("Username is required");
            return false;
        }

        if (TextUtils.isEmpty(dob)) {
            dobInput.setError("Date of Birth is required");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
            return false;
        }

        if (!email.equals(confirmEmail)) {
            confirmEmailInput.setError("Emails do not match");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordInput.setError("Passwords do not match");
            return false;
        }

        // Additional validation can be added here (e.g., password length, email format)

        return true;
    }
}
