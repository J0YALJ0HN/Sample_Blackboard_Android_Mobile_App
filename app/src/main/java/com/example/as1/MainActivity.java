package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    TextView txtDisplay;

    // ArrayList to store the login credentials
    ArrayList<String> usernames;
    ArrayList<String> passwords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtDisplay = findViewById(R.id.txtDisplay);

        // Initialize the ArrayLists with login credentials
        usernames = new ArrayList<>();
        usernames.add("JoyalJohn");
        usernames.add("NadielFassin");
        usernames.add("DhalikOsemiCircle");
        usernames.add("BobMarley");
        usernames.add("Emmanuel");
        usernames.add("Walter");
        usernames.add("Dio");
        usernames.add("CRonaldo");
        usernames.add("KidCudi");

        passwords = new ArrayList<>();
        passwords.add("21410308");
        passwords.add("supersecurepasswordyes");
        passwords.add("thisismypassword");
        passwords.add("isthislove?");
        passwords.add("Ofori");
        passwords.add("White");
        passwords.add("Brando");
        passwords.add("CR7");
        passwords.add("mrRager");

        btnLogin.setOnClickListener(view -> {

            //Get data from input field
            String strUsername = edtUsername.getText().toString();
            String strPassword = edtPassword.getText().toString();

            if(strUsername.isEmpty() || strPassword.isEmpty()) {
                txtDisplay.setText("All fields are required");
            }else{
                // Check if the entered username and password match a login credential in the ArrayLists
                if(usernames.contains(strUsername) && Objects.equals(passwords.get(usernames.indexOf(strUsername)), strPassword)){
                    Toast.makeText(MainActivity.this, "Welcome " + strUsername, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    // Pass the entered username to the UserActivity
                    intent.putExtra("USERNAME", strUsername);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
