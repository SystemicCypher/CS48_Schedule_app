package com.example.quintinhill.scheduled;

/**
 * Created by Diego on 6/4/17.
 */

public class Professor {

    private String last; //last name of professor
    private String other; //first and middle name (initial) of professor
    private double rating; //professor rating

    public Professor(String last, String other, double rating) {
        this.last = last;
        this.other = other;
        this.rating = rating;
    }

    public String getLast() {
        return last;
    }

    public String getOther() {
        return other;
    }

    public double getRating() {
        return rating;
    }

    public String toString() {
        String result = "";
        result += this.last;
        result += " ";
        result += this.other;
        result += " (";
        result += Double.toString(this.rating);
        result += ")";
        return result;
    }

}
