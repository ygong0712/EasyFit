package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SocialPlatform extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private Integer age;
    private  Integer weight;
    private  Integer height;
    private  Integer goal;
    private String usermail,username;
    private Button back;
    private TextView test1, test2;
    FirebaseDatabase db;
    DatabaseReference references;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_platform);
        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);

        back = findViewById(R.id.back);



        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            age = extras.getInt("age");
            weight = extras.getInt("weight");
            height = extras.getInt("height");
            goal = extras.getInt("goal");
        }

        db = FirebaseDatabase.getInstance();
        references = db.getReference("ShareContent");

        usermail = mAuth.getCurrentUser().getEmail();
        username = usermail.substring(0,usermail.indexOf("@"));

        //String tempname = "Testman";

        references.child("ShareContent").child(username).child("bc").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase333", String.valueOf(task.getResult().getValue()));
                }
            }
        });


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
