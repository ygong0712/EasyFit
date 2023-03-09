package com.example.a4365_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button buttonl;  //logout button
    Button buttonc;  //clear button
    FloatingActionButton button1; //breakfast
    FloatingActionButton button2; //lunch
    FloatingActionButton button3; //training
    FloatingActionButton button4; //dinner
    FloatingActionButton button5; //snack
    TextView text, item1, item2, item3, item4, item5;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        buttonl = findViewById(R.id.logout);
        buttonc = findViewById(R.id.clear);

        button1 = findViewById(R.id.floatingActionButton1);
        button2 = findViewById(R.id.floatingActionButton2);
        button3 = findViewById(R.id.floatingActionButton3);
        button4 = findViewById(R.id.floatingActionButton4);
        button5 = findViewById(R.id.floatingActionButton5);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);


        text = findViewById(R.id.user);
        user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        }
        else {
            text.setText("Hi " + user.getEmail());
        }
        buttonl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear the number
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BreakfastCalories.class);
                startActivity(intent);
                finish();
            }
        });
    }
}