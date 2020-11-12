package com.pht.drools.entity;

public class Senor {
    private  String name;
    private  double  num;

    public Senor() {
    }

    public Senor(String name, double num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
