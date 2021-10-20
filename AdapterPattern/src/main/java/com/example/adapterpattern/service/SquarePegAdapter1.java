package com.example.adapterpattern.service;

public class SquarePegAdapter1 {
    public RoundPeg roundPeg;

    public SquarePegAdapter1(SquarePeg squarePeg) {
        this.roundPeg =new RoundPeg(squarePeg.getRedius());
    }
}
