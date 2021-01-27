package com.test.jarustest.model;

import android.os.Parcelable;

public class ChildAssignment {
    private int id;
    private String vin;
    private int year;
    private String make;
    private double value;
    private double length;

    public ChildAssignment(int id, String vin, int year, String make, double value, double length) {
        this.id = id;
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.value = value;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

}