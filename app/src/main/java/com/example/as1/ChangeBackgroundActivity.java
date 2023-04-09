package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeBackgroundActivity extends AppCompatActivity {

    TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent = getIntent();
        String getUSERNAME = intent.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);


        LinearLayout llyMain = findViewById(R.id.llyMain);
        EditText etColor = findViewById(R.id.etColor);
        Button btnChangeBackground = findViewById(R.id.btnChangeBackground);

        btnChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputColor = etColor.getText().toString().trim();
                if (!TextUtils.isEmpty(inputColor)) {
                    try {
                        int color = Color.parseColor(inputColor);
                        llyMain.setBackgroundColor(color);
                        Toast.makeText(getApplicationContext(), "Background color changed!", Toast.LENGTH_SHORT).show();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getApplicationContext(), "Invalid color entered!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a color!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
