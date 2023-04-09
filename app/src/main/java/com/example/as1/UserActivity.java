package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent = getIntent();
        String getUSERNAME = intent.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);



        //Intent to take to NotesActivity.java
        Intent intent2 = new Intent(UserActivity.this, NotesActivity.class);
        intent2.putExtra("USERNAME", getUSERNAME);
        Button btnNote = findViewById(R.id.notes_button);
        btnNote.setOnClickListener(view -> startActivity(intent2));

        //Intent to take to QuizActivity.java
        Intent intent3 = new Intent(UserActivity.this, QuizActivity.class);
        intent3.putExtra("USERNAME", getUSERNAME);
        Button btnQuiz = findViewById(R.id.quiz_button);
        btnQuiz.setOnClickListener(view -> startActivity(intent3));

        //Intent to take to CalculatorActivity.java
        Intent intent4 = new Intent(UserActivity.this, CalculatorActivity.class);
        intent4.putExtra("USERNAME", getUSERNAME);
        Button btnCalc = findViewById(R.id.calculator_button);
        btnCalc.setOnClickListener(view -> startActivity(intent4));

        //Intent to take to ChangeBackgroundActivity.java
        Intent intent5 = new Intent(UserActivity.this, ChangeBackgroundActivity.class);
        intent5.putExtra("USERNAME", getUSERNAME);
        Button btnColour = findViewById(R.id.colour_button);
        btnColour.setOnClickListener(view -> startActivity(intent5));

        //Intent to take to DiceActivity.java
        Intent intent6 = new Intent(UserActivity.this, DiceActivity.class);
        intent6.putExtra("USERNAME", getUSERNAME);
        Button btnDice = findViewById(R.id.dice_button);
        btnDice.setOnClickListener(view -> startActivity(intent6));


    }
}
