package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class BLDCalories extends AppCompatActivity {

    Button button;
    EditText trice, toatmeal, tcorn, tbread, tbeef, tpork, tchicken, tnut, tveggie, tfish, tegg;
    Chip chip_beef, chip_rice, chip_oatmeal, chip_corn, chip_bread, chip_pork, chip_chicken, chip_nut, chip_veggie, chip_fish, chip_egg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_calories);

        String calType = "breakfast";

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
        tveggie = findViewById(R.id.veggie);
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
        chip_veggie = findViewById(R.id.chip_veggie);
        chip_fish = findViewById(R.id.chip_fish);
        chip_egg = findViewById(R.id.chip_egg);



        String finalCalType = calType;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalcalorie = 0;
                if (!TextUtils.isEmpty(trice.getText().toString()) && chip_rice.isChecked()) {
                    //totalcalorie += 500;
                    Food rice = new Food("rice");
                    totalcalorie +=  Integer.parseInt(trice.getText().toString()) * rice.calculate();
                }
                if (!TextUtils.isEmpty(toatmeal.getText().toString()) && chip_oatmeal.isChecked()) {
                    Food oatmeal = new Food("oatmeal");
                    totalcalorie += Integer.parseInt(toatmeal.getText().toString()) * oatmeal.calculate();
                }
                if (!TextUtils.isEmpty(tcorn.getText().toString()) && chip_corn.isChecked()) {
                    Food corn = new Food("corn");
                    totalcalorie += Integer.parseInt(tcorn.getText().toString()) * corn.calculate();
                }
                if (!TextUtils.isEmpty(tbread.getText().toString()) && chip_bread.isChecked()) {
                    Food bread = new Food("bread");
                    totalcalorie += Integer.parseInt(tbread.getText().toString()) * bread.calculate();
                }
                if (!TextUtils.isEmpty(tbeef.getText().toString()) && chip_beef.isChecked()) {
                    Food beef = new Food("beefe");
                    totalcalorie += Integer.parseInt(tbeef.getText().toString()) * beef.calculate();
                }
                if (!TextUtils.isEmpty(tpork.getText().toString()) && chip_pork.isChecked()) {
                    Food pork = new Food("pork");
                    totalcalorie += Integer.parseInt(tpork.getText().toString()) * pork.calculate();
                }
                if (!TextUtils.isEmpty(tchicken.getText().toString()) && chip_chicken.isChecked()) {
                    Food chicken = new Food("chicken");
                    totalcalorie += Integer.parseInt(tchicken.getText().toString()) * chicken.calculate();
                }
                if (!TextUtils.isEmpty(tnut.getText().toString()) && chip_nut.isChecked()) {
                    Food nut = new Food("pork");
                    totalcalorie += Integer.parseInt(tnut.getText().toString()) * nut.calculate();
                }
                if (!TextUtils.isEmpty(tveggie.getText().toString()) && chip_veggie.isChecked()) {
                    Food veggie = new Food("veggie");
                    totalcalorie += Integer.parseInt(tveggie.getText().toString()) * veggie.calculate();
                }
                if (!TextUtils.isEmpty(tfish.getText().toString()) && chip_fish.isChecked()) {
                    Food fish = new Food("fish");
                    totalcalorie += Integer.parseInt(tfish.getText().toString()) * fish.calculate();
                }
                if (!TextUtils.isEmpty(tegg.getText().toString()) && chip_egg.isChecked()) {
                    Food egg = new Food("egg");
                    totalcalorie += Integer.parseInt(tegg.getText().toString()) * egg.calculate();
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
