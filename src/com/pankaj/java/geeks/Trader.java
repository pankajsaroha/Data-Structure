package com.pankaj.java.geeks;

public class Trader {
    private final String name;
    private final String city;

    public Trader (String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName () {
        return this.name;
    }

    public String getCity () {
        return this.city;
    }

    @Override public String toString () {
        return "Trader: " + this.name + " in " + this.city;
    }
}
