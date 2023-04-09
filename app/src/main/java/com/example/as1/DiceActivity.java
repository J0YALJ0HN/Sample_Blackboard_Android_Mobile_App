package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent = getIntent();
        String getUSERNAME = intent.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);

        TextView edt1 = findViewById(R.id.edt1);
        TextView edt2 = findViewById(R.id.edt2);

        Random random = new Random();
        int random1 = random.nextInt(6) + 1; // generates a random number between 0 and 6
        int random2 = random.nextInt(6) + 1; // generates another random number between 0 and 6

        edt1.setText(Integer.toString(random1));
        edt2.setText(Integer.toString(random2));
    }
}