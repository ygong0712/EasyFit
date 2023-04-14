package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialPlatform extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private String usermail,username;
    private Button back, post,submit1, submit2, submit3;
    private LinearLayout socialPage;
    private TextView test1, test2, test3, post1, post2, post3;
    private EditText comment1, comment2, comment3;
    private FirebaseUser user;
    private ProgressBar loadingPB;
    private ArrayList<String> emaillist, postlist;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_platform);

        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        test3 = findViewById(R.id.test3);
        post1 = findViewById(R.id.post1);
        post2 = findViewById(R.id.post2);
        post3 = findViewById(R.id.post3);

        back = findViewById(R.id.back);
        submit1 = findViewById(R.id.submit1);
        submit2 = findViewById(R.id.submit2);
        submit3 = findViewById(R.id.submit3);

        post = findViewById(R.id.post);
        comment1 = findViewById(R.id.comment1);
        comment2 = findViewById(R.id.comment2);
        comment3 = findViewById(R.id.comment3);
        loadingPB = findViewById(R.id.loading);

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        usermail = mAuth.getCurrentUser().getEmail();

        emaillist = new ArrayList<>();
        postlist = new ArrayList<>();

        db.collection("Post").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            loadingPB.setVisibility(View.GONE);

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                String email = d.getString("email");
                                String post = d.getString("post");
                                emaillist.add(email);
                                postlist.add(post);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.

                            }

                            test1.setText(emaillist.get(0));
                            test2.setText(emaillist.get(1));
                            test3.setText(emaillist.get(2));
                            post1.setText("Post: " + postlist.get(0));
                            post2.setText("Post: " + postlist.get(1));
                            post3.setText("Post: " + postlist.get(2));
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(SocialPlatform.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(SocialPlatform.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Post.class);
                startActivity(intent);
                finish();
            }
        });

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tocomment = comment1.getText().toString();
                addDataToFirestore(tocomment, test1.getText().toString());
            }
        });

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tocomment = comment2.getText().toString();
                addDataToFirestore(tocomment, test2.getText().toString());
            }
        });

        submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tocomment = comment3.getText().toString();
                addDataToFirestore(tocomment, test3.getText().toString());
            }
        });



    }


    private void addDataToFirestore(String comment, String target) {
        CollectionReference dbpost = db.collection(user.getEmail());
        Map<String, String> usercomment = new HashMap<>();
        usercomment.put("target", target);
        usercomment.put("comment", comment);

        // below method is use to add data to Firebase Firestore.
        dbpost.add(usercomment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(SocialPlatform.this, "Your Comment has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(SocialPlatform.this, "Fail to add Comment \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }



}
