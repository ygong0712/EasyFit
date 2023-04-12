package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Foodplan extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Integer age;
    private  Integer weight;
    private  Integer height;
    private  Integer goal;
    private String usermail;
    private String username;
    private TextView bc, bp, bf, lc, lp, lf, dc, dp, df, bm, lm, dm;
    private Button share;
    FirebaseDatabase db;
    DatabaseReference references;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodplan);
        bc = findViewById(R.id.bcarb);
        bp = findViewById(R.id.bprotein);
        bf = findViewById(R.id.bfat);
        lc = findViewById(R.id.lcarb);
        lp = findViewById(R.id.lprotein);
        lf = findViewById(R.id.lfat);
        dc = findViewById(R.id.dcarb);
        df = findViewById(R.id.dfat);
        dp = findViewById(R.id.dprotein);
        bm = findViewById(R.id.bm);
        lm = findViewById(R.id.lm);
        dm = findViewById(R.id.dm);
        share = findViewById(R.id.share);


        mAuth = FirebaseAuth.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            age = extras.getInt("age");
            weight = extras.getInt("weight");
            height = extras.getInt("height");
            goal = extras.getInt("goal");
        }

        Integer goalcarb = (int) (1 * weight);
        Integer bcarb = (int) (0.5 * goalcarb);

        Integer goalfat = (int) (0.5 * weight);
        Integer bfat = (int) (0.2*weight);
        Integer bbfat = (int) (0.1*weight);

        Integer goalprotein = (int) (1.5 * weight);
        Integer bprotein = (int) (0.5 * weight);



        bc.setText("c: " + bcarb.toString() + "g");
        lc.setText("c: " + bcarb.toString() +"g");
        dc.setText("c: " + "0" + "g");

        bp.setText("p: " + bprotein.toString() + "g");
        lp.setText("p: " + bprotein.toString() + "g");
        dp.setText("p: " + bprotein.toString() + "g");

        bf.setText("f: " + bfat.toString() + "g");
        lf.setText("f: " + bfat.toString() + "g");
        df.setText("f: " + bbfat.toString() + "g");

        db = FirebaseDatabase.getInstance();
        references = db.getReference("ShareContent");

        usermail = mAuth.getCurrentUser().getEmail();
        username = usermail.substring(0,usermail.indexOf("@"));

        //String tempname = "Testman";

        ShareContent sharing = new ShareContent(username,bcarb,bcarb,0,bprotein,bprotein,bprotein,bfat,bfat,bbfat);
        references.child(username).setValue(sharing);




        Integer oat = (bcarb / 12) * 100;
        Integer egg = (bprotein /13) * 100;
        bm.setText("Example menu: " + "oatmeal " + oat.toString() +"g" + ", " + "egg " + egg.toString() +"g");

        Integer chicken = (bprotein / 31) * 100;
        Integer rice = (bcarb / 28) * 100;
        lm.setText("Example menu: " + "rice " + rice.toString() +"g" +", " + "chicken " + chicken.toString() +"g" + ", " + "veggies");

        Integer beef = (bprotein / 22) * 100;
        dm.setText("Example menu: " + "beef " + beef.toString() + "g" + ", " + "veggies");


        // share the food plan with other users using firebase
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SocialPlatform.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
