package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SocialPlatform extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private String usermail,username;
    private Button back;
    private LinearLayout socialPage;
    private TextView test1, test2, test3;
    FirebaseDatabase db;
    DatabaseReference references, ref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_platform);

        test1 = findViewById(R.id.test1);
        test2 = findViewById(R.id.test2);
        test3 = findViewById(R.id.test3);

        back = findViewById(R.id.back);



        mAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance();
        references = db.getReference("ShareContent");

        usermail = mAuth.getCurrentUser().getEmail();
        username = usermail.substring(0,usermail.indexOf("@"));

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> namelist = new ArrayList<>();
        ArrayList<String> publishedContent = new ArrayList<>();

        ref2 = references.child(username);
        references.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childshot: snapshot.getChildren()){
                    for(DataSnapshot grandchildshot: childshot.getChildren()){
                        list.add(grandchildshot.getValue().toString());
                    }
                    namelist.add(list.get(9));
                    String content = "Here is my food plan for today! For breakfast, I plan to have " + list.get(0) + "g " +
                            "calorie, " + list.get(1) + "g fat, " + list.get(2) + "g protein. For lunch, I plan to have "
                            + list.get(3) + "g " + "calorie, " + list.get(4) + "g fat, " + list.get(5) + "g protein."
                            + "For dinner, I plan to have " + list.get(6) + "g " + "calorie, "
                            + list.get(7) + "g fat, " + list.get(8) + "g protein.";
                    list.clear();
                    publishedContent.add(content);
                }
                test1.setText(namelist.get(0) + ": " + publishedContent.get(0));
                test2.setText(namelist.get(1) + ": " + publishedContent.get(1));
                test3.setText(namelist.get(2) + ": " + publishedContent.get(2));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






//        socialPage = findViewById(R.id.social_page);
//        for (int i = 0; i < namelist.size(); i++) { // create x TextViews
//            TextView textView = new TextView(this); // create a new TextView
//            //textView.setText(namelist.get(i) + ": " + publishedContent.get(i));
//            textView.setText(namelist.get(i) + ": ");
//            TableRow.LayoutParams params = new TableRow.LayoutParams(
//                    TableRow.LayoutParams.MATCH_PARENT, // width
//                    TableRow.LayoutParams.WRAP_CONTENT // height
//            );
//            textView.setLayoutParams(params);
//            textView.setPadding(4,1,0,0);
//            textView.setTextSize(16);
//
//            socialPage.addView(textView); // add the TextView to your LinearLayout
//        }


        // share the food plan with other users using firebase
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}
