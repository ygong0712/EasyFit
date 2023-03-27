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
    public static final String CAL_PREFERENCES = "CalPrefs";
    Button buttonl;  //logout button
    Button buttonc;  //clear button
    Button buttong;
    FloatingActionButton button1; //breakfast
    FloatingActionButton button2; //lunch
    FloatingActionButton button3; //training
    FloatingActionButton button4; //dinner
    FloatingActionButton button5; //snack
    TextView text, item1, item2, item3, item4, item5, exerciseSum, foodSummation, totalSummation;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getApplicationContext().getSharedPreferences(CAL_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        //editor.putString("breakfast", "0");
        //editor.putString("lunch", "0");
        //editor.putString("exercise", "0");
        //editor.putString("dinner", "0");
        //editor.putString("snack", "0");

        mAuth = FirebaseAuth.getInstance();
        buttonl = findViewById(R.id.logout);
        buttonc = findViewById(R.id.clear);
        buttong = findViewById(R.id.generate);

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

        exerciseSum = findViewById(R.id.activitySum);
        foodSummation = findViewById(R.id.foodTotal);
        totalSummation = findViewById(R.id.Summation);


        //update the calories
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Double calValue = extras.getDouble("cal");
            String caltype = extras.getString("type");

            if(caltype.equals("breakfast")) {
                //item1.setText("Breakfast: " + calValue);
                editor.putString("breakfast", calValue.toString());
            } else if (caltype.equals("lunch")){
                //item2.setText("Lunch: " + calValue);
                editor.putString("lunch", calValue.toString());
            } else if (caltype.equals("exercise")){
                //item3.setText("Exercise: " + calValue);
                editor.putString("exercise", calValue.toString());
            } else if (caltype.equals("dinner")){
                //item4.setText("Dinner: " + calValue);
                editor.putString("dinner", calValue.toString());
            } else if (caltype.equals("snack")){
                //item5.setText("Snack: " + calValue);
                editor.putString("snack", calValue.toString());
            }
            editor.commit();
        } else {
            editor.putString("breakfast", "0");
            editor.putString("lunch", "0");
            editor.putString("exercise", "0");
            editor.putString("dinner", "0");
            editor.putString("snack", "0");
            editor.commit();
        }

        //update UI
        String breakfastCalories = settings.getString("breakfast", null);
        Double bf = Double.parseDouble(breakfastCalories);
        breakfastCalories = (new Integer(bf.intValue())).toString();

        String lunchCalories = settings.getString("lunch", null);
        Double lc = Double.parseDouble(lunchCalories);
        lunchCalories = (new Integer(lc.intValue())).toString();

        String exerciseCalories = settings.getString("exercise", null);
        Double ec = Double.parseDouble(exerciseCalories);
        exerciseCalories = (new Integer(ec.intValue())).toString();

        String dinnerCalories = settings.getString("dinner", null);
        Double dc = Double.parseDouble(dinnerCalories);
        dinnerCalories = (new Integer(dc.intValue())).toString();

        String snackCalories = settings.getString("snack", null);
        Double sc = Double.parseDouble(snackCalories);
        snackCalories = (new Integer(sc.intValue())).toString();

        Double foodSum = 0.0;
        Double totalSum = 0.0;
        if (breakfastCalories != null && lunchCalories != null && dinnerCalories != null && snackCalories != null) {
            foodSum = Double.parseDouble(breakfastCalories) + Double.parseDouble(lunchCalories)
                    + Double.parseDouble(dinnerCalories) + Double.parseDouble(snackCalories);
            totalSum = foodSum - Double.parseDouble(exerciseCalories);
        }


        item1.setText("Breakfast: " + breakfastCalories);
        item2.setText("Lunch: " + lunchCalories);
        item3.setText("Exercise: " + exerciseCalories);
        item4.setText("Dinner: " + dinnerCalories);
        item5.setText("Snack: " + snackCalories);
        exerciseSum.setText(exerciseCalories);
        foodSummation.setText(Double.toString(foodSum));
        totalSummation.setText(Double.toString(totalSum));




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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear the number
                Intent intent = new Intent(getApplicationContext(), Person.class);
                startActivity(intent);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BreakfastCalories.class);
                intent.putExtra("type", "breakfast");
                startActivity(intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LunchCalories.class);
                intent.putExtra("type", "lunch");
                startActivity(intent);
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BreakfastCalories.class);
                intent.putExtra("type", "exercise");
                startActivity(intent);
                finish();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DinnerCalories.class);
                intent.putExtra("type", "dinner");
                startActivity(intent);
                finish();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SnackCalories.class);
                intent.putExtra("type", "snack");
                startActivity(intent);
                finish();
            }
        });
    }
}