package com.example.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameInput, dobInput, emailInput, confirmEmailInput, passwordInput, confirmPasswordInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbHelper = new DatabaseHelper(this);
        usernameInput = findViewById(R.id.usernameInput);
        dobInput = findViewById(R.id.dobInput);
        emailInput = findViewById(R.id.emailInput);
        confirmEmailInput = findViewById(R.id.confirmEmailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        Button signUpButton = findViewById(R.id.signUpButton);
        Button loginButton = findViewById(R.id.loginButton);
        ImageButton backbutton=  findViewById(R.id.goBackButton);


        dobInput.setOnClickListener(view -> showDatePickerDialog());

        signUpButton.setOnClickListener(view -> {
            String username = usernameInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String confirmEmail = confirmEmailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            if (validateInputs(username, dob, email, confirmEmail, password, confirmPassword)) {
                dbHelper.addUser(username, dob, email, password);
                Toast.makeText(SignupActivity.this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }

        });
        loginButton.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
        backbutton.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, MainActivity.class)));

    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> dobInput.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

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
        return true;
    }
}
