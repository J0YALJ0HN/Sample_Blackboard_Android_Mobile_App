package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class QuizEndActivity extends AppCompatActivity {
    // Declare variables for UI elements
    private TextView scoreTextView;
    private TextView usernameTextView;
    private Button printButton;
    private int score;
    private String username;
    TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent2 = getIntent();
        String getUSERNAME = intent2.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);

        // Get score and username from Intent
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE",0);
        username = intent.getStringExtra("USERNAME");

        // Initialize UI elements
        scoreTextView = findViewById(R.id.score_text_view);
        usernameTextView = findViewById(R.id.set_username);
        printButton = findViewById(R.id.print);

        // Set text for score and username TextViews
        scoreTextView.setText("Score: " + score + "/5");
        usernameTextView.setText("Username: " + username);

        // Set listener for print button
        printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the directory to save the file in
                File directory = new File(getFilesDir(), "QuizScores");
                if (!directory.exists()) {
                    directory.mkdir();
                }

                // Create the file name
                String fileName = username + "_score.txt";
                File file = new File(directory, fileName);

                try {
                    // Write the score to the file
                    FileWriter writer = new FileWriter(file);
                    writer.write("Username: " + username + "\n");
                    writer.write("Score: " + score + "/5" + "\n");
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Score saved to file " + fileName, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error saving score to file", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}