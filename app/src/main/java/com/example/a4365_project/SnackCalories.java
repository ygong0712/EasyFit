package com.example.a4365_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class SnackCalories extends AppCompatActivity {

    Button button;
    EditText tchocolate, tnut, tveggie, tapple, tbanana, tberry;
    Chip chip_chocolate,  chip_nut, chip_veggie, chip_apple, chip_banana, chip_berry;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_calories);

        String calType = "snack";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            calType = extras.getString("type");
        }

        button = findViewById(R.id.confirmbutton);

        tchocolate = findViewById(R.id.chocolate);
        tnut = findViewById(R.id.nut);
        tveggie = findViewById(R.id.veggie);
        tapple = findViewById(R.id.apple);
        tberry = findViewById(R.id.berry);
        tbanana = findViewById(R.id.banana);

        chip_chocolate = findViewById(R.id.chip_chocolate);
        chip_nut = findViewById(R.id.chip_nut);
        chip_veggie = findViewById(R.id.chip_veggie);
        chip_apple = findViewById(R.id.chip_apple);
        chip_banana = findViewById(R.id.chip_banana);
        chip_berry = findViewById(R.id.chip_berry);


        String finalCalType = calType;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalcalorie = 0;

                if (!TextUtils.isEmpty(tnut.getText().toString()) && chip_nut.isChecked()) {
                    Food nut = new Food("pork");
                    totalcalorie += Integer.parseInt(tnut.getText().toString()) * nut.calculate();
                }
                if (!TextUtils.isEmpty(tveggie.getText().toString()) && chip_veggie.isChecked()) {
                    Food veggie = new Food("veggie");
                    totalcalorie += Integer.parseInt(tveggie.getText().toString()) * veggie.calculate();
                }
                if (!TextUtils.isEmpty(tchocolate.getText().toString()) && chip_chocolate.isChecked()) {
                    totalcalorie += Integer.parseInt(tchocolate.getText().toString()) * 5.46;
                }
                if (!TextUtils.isEmpty(tapple.getText().toString()) && chip_apple.isChecked()) {
                    totalcalorie += Integer.parseInt(tapple.getText().toString()) * 0.52;
                }
                if (!TextUtils.isEmpty(tbanana.getText().toString()) && chip_banana.isChecked()) {
                    totalcalorie += Integer.parseInt(tbanana.getText().toString()) * 0.89;
                }
                if (!TextUtils.isEmpty(tberry.getText().toString()) && chip_berry.isChecked()) {
                    totalcalorie += Integer.parseInt(tberry.getText().toString()) * 0.33;
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
