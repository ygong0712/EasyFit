package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;

public class Post extends AppCompatActivity {
    private EditText post;
    private Button back, submit;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        post = findViewById(R.id.post);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit1);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocialPlatform.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topost = post.getText().toString();
                addDataToFirestore(topost);
            }
        });


    }

    private void addDataToFirestore(String post) {

        // creating a collection reference
        // for our Firebase Firestore database.
        CollectionReference dbpost = db.collection("Post");
        HashMap userpost = new HashMap();
        userpost.put("email", user.getEmail());
        userpost.put("post", post);

        // below method is use to add data to Firebase Firestore.
        dbpost.add(userpost).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(Post.this, "Your Post has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(Post.this, "Fail to add Post \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
