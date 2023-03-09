package com.example.a4365_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BreakfastCalories extends AppCompatActivity {

    Button button;
    FirebaseAuth mAuth;
    EditText foodt, foodc;

    public static final String CAL_PREFERENCES = "CalPrefs";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_calories);

        button = findViewById(R.id.confirmbutton);

        foodt = findViewById(R.id.foodtype);
        foodc = findViewById(R.id.food1calories);
        String breakfastNameAdd= foodt.getText().toString();
        String breakfastCalAdd = foodc.getText().toString();

        SharedPreferences settings = getApplicationContext().getSharedPreferences(CAL_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                editor.putString("breakfastName", breakfastNameAdd);
                editor.putString("breakfastCal", breakfastCalAdd);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
