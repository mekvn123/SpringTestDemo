package com.example.adapterpattern.service;

public class RoundHole {
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        return this.getRadius() >= peg.getRadius();
    }

    public boolean fits(SquarePeg peg){
        return this.getRadius() >= peg.getRedius();
    }
}
