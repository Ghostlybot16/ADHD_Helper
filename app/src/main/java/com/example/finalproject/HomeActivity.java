package com.example.finalproject;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView focusModeStatus;
    private Switch focusModeToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        focusModeStatus = findViewById(R.id.focusModeStatus);
        focusModeToggle = findViewById(R.id.focusModeToggle);

        focusModeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                focusModeStatus.setText("ON");
                focusModeStatus.setTextColor(getResources().getColor(R.color.accent_color));
                Toast.makeText(this, "Focus Mode Activated", Toast.LENGTH_SHORT).show();
            } else {
                focusModeStatus.setText("OFF");
                focusModeStatus.setTextColor(getResources().getColor(R.color.secondary_text));
                Toast.makeText(this, "Focus Mode Deactivated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
