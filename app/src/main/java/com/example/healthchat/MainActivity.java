
package com.example.healthchat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (name.isEmpty()) {
                nameInput.setError("Please enter your name");
                nameInput.requestFocus();

                Toast.makeText(this,
                        "Please enter your name",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(
                    MainActivity.this, Dashboard.class);

            intent.putExtra("patientName", name);
            startActivity(intent);
        });
    }
}
