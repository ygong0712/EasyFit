package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BreakfastCalories extends AppCompatActivity {

    Button button;
    EditText foodt, foodc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_calories);

        String calType = "breakfast";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            calType = extras.getString("type");
        }

        button = findViewById(R.id.confirmbutton);

        foodt = findViewById(R.id.foodtype);
        foodc = findViewById(R.id.food1calories);

        String finalCalType = calType;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String breakfastNameAdd= foodt.getText().toString();
                String breakfastCalAdd = foodc.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("cal", breakfastCalAdd);
                intent.putExtra("type", finalCalType);
                startActivity(intent);
                finish();
            }
        });

    }
}
