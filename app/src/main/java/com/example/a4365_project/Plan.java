package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Plan extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Integer age;
    private  Integer weight;
    private  Integer height;
    private  Integer goal;

    private TextView carb;
    private  TextView fat;
    private  TextView protein;
    private  TextView bmitext;
    private  TextView calorie;
    private  TextView time;
    private Button food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan);

        carb = findViewById(R.id.carb);
        fat = findViewById(R.id.fat);
        protein = findViewById(R.id.protein);
        bmitext = findViewById(R.id.bmi);
        calorie = findViewById(R.id.calorie);
        time = findViewById(R.id.time);
        food = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            age = extras.getInt("age");
            weight = extras.getInt("weight");
            height = extras.getInt("height");
            goal = extras.getInt("goal");
        }
        Integer bmi = (int) (10 * weight + 6.25 * height - 5*age + 5);
        Integer goalproteinlower = (int) (1.5 * weight);
        Integer goalproteinhigher = (int) (2 * weight);
        Integer goalcarblow = (int) (1 * weight);
        Integer goalcarbhigh = (int) (1.5 * weight);
        Integer goalfatlow = (int) (0.5*weight);
        Integer goalfathigh = (int) (1 * weight);
        Integer goalcalorie = (int) (24 * weight);
        Integer day = (int) (15.4 * (weight - goal));

        carb.setText("Carb range:" + " " + goalcarblow.toString() + "~" + goalcarbhigh.toString());
        protein.setText("Protein range:" + " " + goalproteinlower.toString() + "~" + goalproteinhigher.toString());
        fat.setText("Fat range:" + " " + goalfatlow.toString() + "~" + goalfathigh.toString());
        bmitext.setText("Your BMI is:" + " " + bmi.toString());
        calorie.setText("Your optimal calorie per day is:" + "" + goalcalorie.toString());
        time.setText("Estimated Time to reach the goal:" + day.toString());


        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Foodplan.class);
                intent.putExtra("age", age);
                intent.putExtra("weight", weight);
                intent.putExtra("height", height);
                intent.putExtra("goal", goal);
                startActivity(intent);
                finish();
            }
        });
    }
}
