package com.example.a4365_project;

public class Users {

    int weight;
    int height;
    int age;
    int goal;
    public Users(int weight, int height, int age, int goal) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.goal = goal;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
