package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4365_project.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase:firebase-bom:31.3.0;
//import com.google.firebase:firebase-database;

public class Person extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button buttonc;
    private String usermail;
    private String username;
    EditText weight;
    EditText age;
    EditText height;
    EditText goal;
    FirebaseDatabase db;
    DatabaseReference references;
    //ActivityMainBinding binding;



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

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());


        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Plan.class);

                int iage = Integer.parseInt(age.getText().toString());
                int iweight = Integer.parseInt(weight.getText().toString());
                int iheight = Integer.parseInt(height.getText().toString());
                int igoal = Integer.parseInt(goal.getText().toString());


                db = FirebaseDatabase.getInstance();
                references = db.getReference("Users");

                usermail = mAuth.getCurrentUser().getEmail();
                username = usermail.substring(0,usermail.indexOf("@"));

                //String tempname = "Testman";

                Users users = new Users(iweight, iheight, iage, igoal);
                references.child(username).setValue(users);


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
