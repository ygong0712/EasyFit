package com.example.a4365_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Plan extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button buttonc;
    EditText weight;
    EditText age;
    EditText height;
    EditText goal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

        mAuth = FirebaseAuth.getInstance();
        buttonc = findViewById(R.id.confirmbutton);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        age = findViewById(R.id.age);
        goal = findViewById(R.id.goal);


    }
}
