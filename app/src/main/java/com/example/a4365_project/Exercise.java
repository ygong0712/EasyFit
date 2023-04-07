package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class Exercise extends AppCompatActivity {

    Button button;
    EditText trice, toatmeal, tcorn, tbread, tbeef, tpork, tchicken, tnut, tfish, tegg;
    Chip chip_beef, chip_rice, chip_oatmeal, chip_corn, chip_bread, chip_pork, chip_chicken, chip_nut, chip_fish, chip_egg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);


        String calType = "exercise";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            calType = extras.getString("type");
        }

        button = findViewById(R.id.confirmbutton);

        trice = findViewById(R.id.rice);
        toatmeal = findViewById(R.id.oatmeal);
        tcorn = findViewById(R.id.corn);
        tbread = findViewById(R.id.bread);
        tbeef = findViewById(R.id.beef);
        tpork = findViewById(R.id.pork);
        tchicken = findViewById(R.id.chicken);
        tnut = findViewById(R.id.nut);
        tfish = findViewById(R.id.fish);
        tegg = findViewById(R.id.egg);

        chip_rice = findViewById(R.id.chip_rice);
        chip_oatmeal = findViewById(R.id.chip_oatmeal);
        chip_corn = findViewById(R.id.chip_corn);
        chip_bread = findViewById(R.id.chip_bread);
        chip_beef = findViewById(R.id.chip_beef);
        chip_pork = findViewById(R.id.chip_pork);
        chip_chicken = findViewById(R.id.chip_chicken);
        chip_nut = findViewById(R.id.chip_nut);
        chip_fish = findViewById(R.id.chip_fish);
        chip_egg = findViewById(R.id.chip_egg);


        String finalCalType = calType;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalcalorie = 0;
                if (!TextUtils.isEmpty(trice.getText().toString()) && chip_rice.isChecked()) {
                    totalcalorie +=  Integer.parseInt(trice.getText().toString()) * 280;
                }
                if (!TextUtils.isEmpty(toatmeal.getText().toString()) && chip_oatmeal.isChecked()) {

                    totalcalorie += Integer.parseInt(toatmeal.getText().toString()) * 590;
                }
                if (!TextUtils.isEmpty(tcorn.getText().toString()) && chip_corn.isChecked()) {

                    totalcalorie += Integer.parseInt(tcorn.getText().toString()) * 520;
                }
                if (!TextUtils.isEmpty(tbread.getText().toString()) && chip_bread.isChecked()) {

                    totalcalorie += Integer.parseInt(tbread.getText().toString()) * 510;
                }
                if (!TextUtils.isEmpty(tbeef.getText().toString()) && chip_beef.isChecked()) {
                    ;
                    totalcalorie += Integer.parseInt(tbeef.getText().toString()) * 480;
                }
                if (!TextUtils.isEmpty(tpork.getText().toString()) && chip_pork.isChecked()) {

                    totalcalorie += Integer.parseInt(tpork.getText().toString()) * 440;
                }
                if (!TextUtils.isEmpty(tchicken.getText().toString()) && chip_chicken.isChecked()) {

                    totalcalorie += Integer.parseInt(tchicken.getText().toString()) * 220;
                }
                if (!TextUtils.isEmpty(tnut.getText().toString()) && chip_nut.isChecked()) {

                    totalcalorie += Integer.parseInt(tnut.getText().toString()) * 600;
                }
                if (!TextUtils.isEmpty(tfish.getText().toString()) && chip_fish.isChecked()) {

                    totalcalorie += Integer.parseInt(tfish.getText().toString()) * 335;
                }
                if (!TextUtils.isEmpty(tegg.getText().toString()) && chip_egg.isChecked()) {

                    totalcalorie += Integer.parseInt(tegg.getText().toString()) * 510;
                }
                String total = String.valueOf(totalcalorie);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("cal", totalcalorie);
                intent.putExtra("type", finalCalType);
                startActivity(intent);
                finish();
            }
        });

    }
}
