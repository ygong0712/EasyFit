package com.example.a4365_project;
public class Food {
    public double carb = 0;
    public double fat = 0;
    public double protein = 0;
    public Food(String foodtype) {
        if (foodtype.equals("rice")) {
            carb = 0.28;
            fat = 0;
            protein = 0;
        }
        if (foodtype.equals("bread")) {
            carb = 0.5;
            fat = 0;
            protein = 0;
        }
        if (foodtype.equals("corn")) {
            carb = 0.187;
            fat = 0;
            protein = 0;
        }
        if (foodtype.equals("oatmeal")) {
            carb = 0.12;
            fat = 0;
            protein = 0;
        }
        if (foodtype.equals("beef")) {
            carb = 0;
            fat = 0.15;
            protein = 0.26;
        }
        if (foodtype.equals("pork")) {
            carb = 0;
            fat = 0.14;
            protein = 0.27;
        }
        if (foodtype.equals("chicken")) {
            carb = 0;
            fat = 0.14;
            protein = 0.27;
        }
        if (foodtype.equals("fish")) {
            carb = 0;
            fat = 0.12;
            protein = 0.22;
        }
        if (foodtype.equals("egg")) {
            carb = 0;
            fat = 0.11;
            protein = 0.13;
        }
        if (foodtype.equals("nut")) {
            carb = 0;
            fat = 0.54;
            protein = 0.2;
        }
        if (foodtype.equals("veggies")) {
            carb = 0;
            fat = 0;
            protein = 0;
        }

    }
    public double calculate() {
        return 4*protein + 4*carb + 9*fat;
    }
}
