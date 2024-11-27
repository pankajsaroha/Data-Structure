package com.pankaj.java;

public enum Color {

    RED("1"),
    GREEN("2"),
    WHITE("#");

    public String str;

    Color(String str) {
        this.str = str;
    }

    public String toString() {
        return str;
    }
}
