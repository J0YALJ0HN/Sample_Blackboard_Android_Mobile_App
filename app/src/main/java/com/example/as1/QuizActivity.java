package com.example.as1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    // Declare variables for UI elements
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton optionARadioButton, optionBRadioButton, optionCRadioButton, optionDRadioButton;
    TextView USERNAME;



    // Declare variables for quiz questions and answers
    private final String[] questions = {"Question 1: What is 12^2?", "Question 2: Who figured out the Theory of Relativity?", "Question 3: What is a 'String' in programming?", "Question 4: What is another name for the process of rusting?", "Question 5: Which of these religious observances lasts for the shortest period of time during the calendar year?"};
    private final String[] answers = {"144", "Albert Einstein", "A sequence of characters used to represent text", "Oxidation", "Diwali"};


    // Declare variable to keep track of current question
    private int currentQuestionIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent2 = getIntent();
        String getUSERNAME = intent2.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);


        //---------------------------------------------------------------

        // Initialize UI elements
        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        optionARadioButton = findViewById(R.id.option_a_radio_button);
        optionBRadioButton = findViewById(R.id.option_b_radio_button);
        optionCRadioButton = findViewById(R.id.option_c_radio_button);
        optionDRadioButton = findViewById(R.id.option_d_radio_button);
        Button submitButton = findViewById(R.id.submit_button);

        // Set the first question
        setQuestionAndOptions(currentQuestionIndex);

        // Add score variable and initialize it to 0
        final int[] currentScore = {0};

        // Set listener for submit button
        submitButton.setOnClickListener(view -> {
            // Check if an option is selected
            if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            } else {
                // Check if selected option is correct
                RadioButton selectedOption = findViewById(optionsRadioGroup.getCheckedRadioButtonId());
                if (selectedOption.getText().toString().equals(answers[currentQuestionIndex])) {
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                    currentScore[0]++; // increment score if answer is correct
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect. The correct answer is " + answers[currentQuestionIndex], Toast.LENGTH_SHORT).show();
                }

                // Go to next question or finish quiz if all questions have been answered
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    setQuestionAndOptions(currentQuestionIndex);
                } else {
                    Intent intent = new Intent(QuizActivity.this, QuizEndActivity.class);
                    intent.putExtra("SCORE", currentScore[0]); // add score to intent
                    intent.putExtra("USERNAME", getUSERNAME); // add username to intent
                    startActivity(intent); // start QuizEndActivity
                    finish();
                }
            }
        });
    }

    // Method to set the current question and options
    private void setQuestionAndOptions(int questionIndex) {
        questionTextView.setText(questions[questionIndex]);
        optionARadioButton.setText("Option A");
        optionBRadioButton.setText("Option B");
        optionCRadioButton.setText("Option C");
        optionDRadioButton.setText("Option D");

        switch (questionIndex) {
            case 0:
                optionBRadioButton.setText("144");
                optionARadioButton.setText("1212");
                optionCRadioButton.setText("122");
                optionDRadioButton.setText("24");
                break;
            case 1:
                optionARadioButton.setText("Albert Einstein");
                optionBRadioButton.setText("Pablo Escobar");
                optionCRadioButton.setText("Stephen Hawking");
                optionDRadioButton.setText("Gary Linekar");
                break;
            case 2:
                optionARadioButton.setText("A sequence of characters used to represent text");
                optionBRadioButton.setText("A sequenced collection of elements of the same data type with a single identifier name");
                optionCRadioButton.setText("A collection of nodes connected by directed (or undirected) edges");
                optionDRadioButton.setText("A computer program that exposes an operating system's services to a human user or other programs");
                break;
            case 3:
                optionARadioButton.setText("Homeostasis");
                optionBRadioButton.setText("Photosynthesis");
                optionCRadioButton.setText("Oxygenation");
                optionDRadioButton.setText("Oxidation");
                break;
            case 4:
                optionARadioButton.setText("Ramadan");
                optionBRadioButton.setText("Lent");
                optionCRadioButton.setText("Hanukkah");
                optionDRadioButton.setText("Diwali");
                break;
        }
        optionsRadioGroup.clearCheck();
    }

}