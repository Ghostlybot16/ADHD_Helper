package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText usernameEditText, dobEditText, newPasswordEditText, confirmNewPasswordEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        dbHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.username);
        dobEditText = findViewById(R.id.dob);
        newPasswordEditText = findViewById(R.id.newPassword);
        confirmNewPasswordEditText = findViewById(R.id.confirmNewPassword);
        Button resetPasswordButton = findViewById(R.id.resetPasswordButton);

        resetPasswordButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String dob = dobEditText.getText().toString().trim();
            String newPassword = newPasswordEditText.getText().toString().trim();
            String confirmNewPassword = confirmNewPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(dob) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(confirmNewPassword)) {
                Toast.makeText(ForgotPasswordActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(confirmNewPassword)) {
                Toast.makeText(ForgotPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                boolean isUserVerified = dbHelper.verifyUserByUsernameAndDob(username, dob);
                if (isUserVerified) {
                    boolean isPasswordUpdated = dbHelper.updatePassword(username, newPassword);
                    if (isPasswordUpdated) {
                        Toast.makeText(ForgotPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Password reset failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "User verification failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
