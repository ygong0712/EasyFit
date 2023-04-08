package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SocialPlatform extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private Integer age;
    private  Integer weight;
    private  Integer height;
    private  Integer goal;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_platform);

        back = findViewById(R.id.back);



        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            age = extras.getInt("age");
            weight = extras.getInt("weight");
            height = extras.getInt("height");
            goal = extras.getInt("goal");
        }


        // share the food plan with other users using firebase
        back.setOnClickListener(new View.OnClickListener() {
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
