package com.test.jarustest.model;

import android.os.Parcelable;

public class ChildAssignment {
    private final int id;
    private final String vin;
    private final int year;
    private final String make;
    private final double value;
    private final double length;

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

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public double getValue() {
        return value;
    }

    public double getLength() {
        return length;
    }
}