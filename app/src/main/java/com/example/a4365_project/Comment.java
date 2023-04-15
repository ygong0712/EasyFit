package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Comment extends AppCompatActivity {
    private EditText post;
    private Button back;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private TextView post1, post2, post3, comment1, comment2, comment3;
    private ProgressBar loadingPB;
    private ArrayList<String> targetlist, commentlist, postidlist, contentlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);


        back = findViewById(R.id.back);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        post1 = findViewById(R.id.post1);
        post2 = findViewById(R.id.post2);
        post3 = findViewById(R.id.post3);
        loadingPB = findViewById(R.id.loading);

        comment1 = findViewById(R.id.comment1);
        comment2 = findViewById(R.id.comment2);
        comment3 = findViewById(R.id.comment3);

        targetlist = new ArrayList<>();
        commentlist = new ArrayList<>();
        postidlist = new ArrayList<>();
        contentlist = new ArrayList<>();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocialPlatform.class);
                startActivity(intent);
                finish();
            }
        });
        String currentemail = user.getEmail();

        db.collection(currentemail).get()
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

                                String comment = d.getString("comment");
                                String postid = d.getString("postid");
                                String target = d.getString("target");
                                String content = d.getString("content");

                                System.out.println(d.getString("content"));
                                commentlist.add(comment);
                                postidlist.add(postid);
                                targetlist.add(target);
                                contentlist.add(content);
                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.

                            }
                            post1.setText("Post: " + contentlist.get(0));
                            post2.setText("Post: " + contentlist.get(1));
                            post3.setText("Post: " + contentlist.get(2));
                            comment1.setText("Comment: " +commentlist.get(0));
                            comment2.setText("Comment: " +commentlist.get(1));
                            comment3.setText("Comment: " +commentlist.get(2));
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(Comment.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(Comment.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
