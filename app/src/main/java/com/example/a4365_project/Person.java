package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Person extends AppCompatActivity {
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

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Plan.class);
                intent.putExtra("age", Integer.parseInt(age.getText().toString()));
                intent.putExtra("weight", Integer.parseInt(weight.getText().toString()));
                intent.putExtra("height", Integer.parseInt(height.getText().toString()));
                intent.putExtra("goal", Integer.parseInt(goal.getText().toString()));
                startActivity(intent);
                finish();
            }
        });

    }
}
