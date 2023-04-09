package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;
    private EditText editText;
    private EditText edit2Text;
    private SharedPreferences sharedPreferences;
    TextView USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // Hooks
        USERNAME = findViewById(R.id.set_username);
        // Get text from Intent
        Intent intent2 = getIntent();
        String getUSERNAME = intent2.getStringExtra("USERNAME");
        // Set Text
        String loggedInAs = "Logged in as: " + getUSERNAME;
        USERNAME.setText(loggedInAs);

        // Hide the action bar
        getSupportActionBar().hide();

        // Find views by their IDs
        recyclerView = findViewById(R.id.recycler_id);
        editText = findViewById(R.id.edit_text_id);
        edit2Text = findViewById(R.id.edit2text_id);

        // Initialize the array list
        arrayList = new ArrayList<String>();

        // Retrieve the saved ArrayList from SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("list", null);
        ArrayList<String> savedArray = loadArray("notes", this);
        if (savedArray != null) {
            arrayList.addAll(savedArray);
        }

        // Create a new layout manager for the recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Set the adapter for the recycler view
        MyAdapter myAdapter = new MyAdapter(arrayList, this);
        recyclerView.setAdapter(myAdapter);
    }

    // Method to add an item to the recycler view
    public void addItem(View view) {
        String input = editText.getText().toString().trim();
        if (input.matches("[a-zA-Z0-9]+")) {
            arrayList.add(input);
            saveArray(arrayList, "notes", this);
            MyAdapter myAdapter = new MyAdapter(arrayList,this);
            recyclerView.setAdapter(myAdapter);
        } else {
            Toast.makeText(this, "Special Characters aren't allowed here", Toast.LENGTH_SHORT).show();
        }
    }



    // Method to remove an item from the recycler view
    public void removeItem(View view) {
        String input = edit2Text.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a valid input", Toast.LENGTH_SHORT).show();
        } else if (!arrayList.contains(input)) {
            Toast.makeText(this, "The entered item does not exist in the list", Toast.LENGTH_SHORT).show();
        } else {
            arrayList.remove(arrayList.indexOf(input));
            MyAdapter myAdapter = new MyAdapter(arrayList,this);
            recyclerView.setAdapter(myAdapter);

            // Save the updated ArrayList to SharedPreferences
            Set<String> set = new HashSet<String>(arrayList);
            //sharedPreferences.edit().putStringSet("list", set).apply();
            saveArray(arrayList, "notes", this);
        }
    }


    public boolean saveArray(ArrayList<String> array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        return editor.commit();
    }

    public ArrayList<String> loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>();
        for(int i=0;i<size;i++)
            array.add(i,prefs.getString(arrayName + "_" + i, null));
        return array;
    }
}