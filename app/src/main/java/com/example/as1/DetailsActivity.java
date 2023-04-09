package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    // Declaration of the SharedPreferences object
    SharedPreferences prefs;
    EditText editText;
    String title;

    TextView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = getIntent().getExtras().getString("key");
        Log.d("Notes", "tile = "+title);
        // Initialization of the EditText view
        editText = findViewById(R.id.text_id);
        titleView = findViewById(R.id.textView);
        titleView.setText(title);
        // Initialization of the SharedPreferences object
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Retrieving the saved text from SharedPreferences and setting it to the EditText view
        String savedText = prefs.getString(title, "");
        editText.setText(savedText);
    }

    // Method to save the text entered in the EditText view to SharedPreferences
    private void saveTextToPrefs() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(title, editText.getText().toString());
        editor.apply();
    }

    // Override the onPause method to save the text to SharedPreferences when the activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        saveTextToPrefs();
    }
}
