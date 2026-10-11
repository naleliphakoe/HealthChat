
package com.example.healthchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    private TextView welcomeText;
    private Button doctorButton;
    private Button nurseButton;
    private Button otherDoctorButton;
    private Button supportButton;
    private Button emergencyButton;

    private String patientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        patientName = getIntent().getStringExtra("patientName");

        if (patientName == null || patientName.trim().isEmpty()) {
            patientName = "Patient";
        }

        welcomeText = findViewById(R.id.welcomeText);
        doctorButton = findViewById(R.id.doctorButton);
        nurseButton = findViewById(R.id.nurseButton);
        otherDoctorButton = findViewById(R.id.otherDoctorButton);
        supportButton = findViewById(R.id.supportButton);
        emergencyButton = findViewById(R.id.emergencyButton);

        welcomeText.setText("Hello, " + patientName + "!");

        doctorButton.setOnClickListener(v ->
                openChat("Dr. Thabo Mokoena",
                        "General Practitioner"));

        nurseButton.setOnClickListener(v ->
                openChat("Nurse Lerato Dlamini",
                        "Registered Nurse"));

        otherDoctorButton.setOnClickListener(v ->
                openChat("Dr. Aisha Naidoo",
                        "Women's Health"));

        supportButton.setOnClickListener(v ->
                openChat("Clinic Support",
                        "Appointment Support"));

        emergencyButton.setOnClickListener(v ->
                new AlertDialog.Builder(Dashboard.this)
                        .setTitle("Emergency Help")
                        .setMessage(
                                "This app does not provide emergency care. " +
                                        "In South Africa, call 112 from a mobile phone " +
                                        "or seek urgent medical attention if needed."
                        )
                        .setPositiveButton("OK", null)
                        .show());
    }

    private void openChat(String professional, String profession) {
        Intent intent = new Intent(
                Dashboard.this, ChatActivity.class);

        intent.putExtra("professionalName", professional);
        intent.putExtra("profession", profession);
        intent.putExtra("patientName", patientName);

        startActivity(intent);
    }
}
