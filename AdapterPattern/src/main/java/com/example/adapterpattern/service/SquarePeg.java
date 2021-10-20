package com.example.adapterpattern.service;

public class SquarePeg {
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        return Math.pow(this.width, 2);
    }

    public double getRedius() {
        return Math.sqrt(Math.pow((width / 2), 2) * 2);
    }
}
